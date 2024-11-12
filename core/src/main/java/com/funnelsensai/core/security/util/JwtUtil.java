package com.funnelsensai.core.security.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
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
                .setExpiration(new Date(System.currentTimeMillis() + (expiryTime*1000L)))
                .signWith(getSigningKey())
                .compact();
    }

    public Jws<Claims> getTokenClaims(String token) {
        JwtParser jwtParser = Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
                .build();

        return jwtParser.parseClaimsJws(token);
    }

    public boolean validateToken(String token) {
        try {
            getTokenClaims(token);
            return true;

        } catch (JwtException e) {
            return false;
        }
    }

    public boolean isTokenExpired(String token) {
        try {
            Date expirationDate = getTokenClaims(token)
                    .getBody()
                    .getExpiration();
            return expirationDate.before(new Date());
        } catch (JwtException e) {
            return true; // If token is invalid, treat as expired
        }
    }

    public String getUsernameFromToken(String token) {
        return getTokenClaims(token)
                .getBody()
                .getSubject();
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}