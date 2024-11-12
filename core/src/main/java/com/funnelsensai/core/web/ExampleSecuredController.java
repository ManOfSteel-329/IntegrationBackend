package com.funnelsensai.core.web;


import com.funnelsensai.core.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleSecuredController {

    @GetMapping("/api/example-secured-endpoint")
    public ResponseEntity<User> exampleMethod (@AuthenticationPrincipal User user) {

        return ResponseEntity.ok(user);

    }
}
