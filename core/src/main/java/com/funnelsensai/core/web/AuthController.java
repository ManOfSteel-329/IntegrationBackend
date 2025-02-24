package com.funnelsensai.core.web;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.funnelsensai.core.dto.ResponseDto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.funnelsensai.core.security.util.JwtUtil;
import com.funnelsensai.core.service.UserService;
import com.funnelsensai.core.util.CookieUtils;
import com.funnelsensai.core.web.request.AuthRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.authentication.AuthenticationManager;

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

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody AuthRequest authRequest, HttpServletResponse response) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

            String accessToken = jwtUtil.generateAccessToken(authRequest.getEmail());
            String refreshToken = jwtUtil.generateRefreshToken(authRequest.getEmail());

            // Set tokens in cookies.
            CookieUtils.setCookie(response, "accessToken", accessToken, accessTokenExpiry);
            CookieUtils.setCookie(response, "refreshToken", refreshToken, refreshTokenExpiry);

            Map<String, String> tokens = new HashMap<>();
            tokens.put("accessToken", accessToken);
            tokens.put("refreshToken", refreshToken);
            return tokens;
        } catch (AuthenticationException ex) {
            throw new RuntimeException("Invalid username or password");
        }
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody AuthRequest authRequest) {
        try {
            UserResponseDTO userResponseDTO = userService.createUser(authRequest.getEmail(), authRequest.getPassword(), authRequest.getCompanyName());
            return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("error", e.getMessage()));
        }
    }
}