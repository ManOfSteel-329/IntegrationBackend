package com.funnelsensai.core.security;

import com.funnelsensai.core.domain.User;
import com.funnelsensai.core.security.util.JwtUtil;
import com.funnelsensai.core.util.CookieUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    @Value("${jwt.access.token.expiry}")
    private int accessTokenExpiry;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String accessToken = CookieUtils.getTokenFromCookie(request, "accessToken");
        String refreshToken = CookieUtils.getTokenFromCookie(request, "refreshToken");

        if (accessToken != null) {
            if (jwtUtil.isTokenExpired(accessToken) && refreshToken != null && jwtUtil.validateToken(refreshToken)) {
                // Access token is expired but refresh token is valid; generate new access token
                String username = jwtUtil.getUsernameFromToken(refreshToken);
                String newAccessToken = jwtUtil.generateAccessToken(username);
                CookieUtils.setCookie(response, "accessToken", newAccessToken, accessTokenExpiry);

                accessToken = newAccessToken; // Update to use the new token
            }

            if (jwtUtil.validateToken(accessToken)) {
                String username = jwtUtil.getUsernameFromToken(accessToken);
                User user = (User) userDetailsService.loadUserByUsername(username); // Note: THIS IS INEFFICIENT!!! At some point we should consider using caching
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        user, null, null);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication); // This is the code that actually "logs the user in"
            }
        }
        filterChain.doFilter(request, response);
    }


}