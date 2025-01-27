package com.funnelsensai.core.web;

import com.funnelsensai.core.dto.users.UserDTO;
import com.funnelsensai.core.service.GetUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class UserController {

    private final GetUserService getUserService;

    public UserController(GetUserService getUserService) {
        this.getUserService = getUserService;
    }

    @GetMapping("api/getuserDTO")
    public UserDTO getUserDTO() throws IOException {
        return getUserService.fetchUserDTO();
    }

}
