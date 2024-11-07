package com.funnelsensai.core.security.util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.access.token.expiry}")
    private long accessTokenExpiry;

    @Value("${jwt.refresh.token.expiry}")
    private long refreshTokenExpiry;

    public String generateAccessToken(String username) {
        return generateToken(username, accessTokenExpiry);
    }

    public String generateRefreshToken(String username) {
        return generateToken(username, refreshTokenExpiry);
    }

    private String generateToken(String username, long expiryTime) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiryTime))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}