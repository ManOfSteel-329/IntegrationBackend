package com.funnelsensai.core.web;

import com.funnelsensai.core.domain.User;
import com.funnelsensai.core.security.util.JwtUtil;
import com.funnelsensai.core.service.UserService;
import com.funnelsensai.core.util.CookieUtils;
import com.funnelsensai.core.web.request.AuthRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Value("${jwt.access.token.expiry}")
    private int accessTokenExpiry;
    @Value("${jwt.refresh.token.expiry}")
    private int refreshTokenExpiry;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody AuthRequest authRequest, HttpServletResponse response) {
        try {
            // String encodedPassword = bCryptPasswordEncoder.encode(authRequest.getPassword());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

            String accessToken = jwtUtil.generateAccessToken(authRequest.getUsername());
            String refreshToken = jwtUtil.generateRefreshToken(authRequest.getUsername());

            // Set tokens in cookies
            CookieUtils.setCookie(response, "accessToken", accessToken, accessTokenExpiry);
            CookieUtils.setCookie(response, "refreshToken", refreshToken, refreshTokenExpiry);

            Map<String, String> tokens = new HashMap<>();
            tokens.put("accessToken", accessToken);
            tokens.put("refreshToken", refreshToken);

            System.out.println("user succesfully logged in"+ authRequest.getUsername()); //todo: delete later :)
            return tokens;
        } catch (AuthenticationException ex) {
            throw new RuntimeException("Invalid username or password");
        }
    }

    @PostMapping("/createUser")
    public User createUser (@RequestBody AuthRequest authRequest) {
        System.out.println("user succesfully created: --->" + authRequest.getUsername()); //todo: delete later :)
        return userService.createUser(authRequest.getUsername(), authRequest.getPassword());

    }
}