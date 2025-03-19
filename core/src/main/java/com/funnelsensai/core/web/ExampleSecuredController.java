package com.funnelsensai.core.web;

import com.funnelsensai.core.domain.User;
import com.funnelsensai.core.dto.ResponseDto.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleSecuredController {

    @GetMapping("/api/example-secured-endpoint")
    public ResponseEntity<UserResponseDTO> exampleMethod(@AuthenticationPrincipal User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                (user.getCompany() != null) ? user.getCompany().getName() : null
        );

        return ResponseEntity.ok(userResponseDTO);
    }
}
