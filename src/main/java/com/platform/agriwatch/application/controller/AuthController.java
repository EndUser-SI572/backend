package com.platform.agriwatch.application.controller;

import com.platform.agriwatch.application.dto.request.LoginRequest;
import com.platform.agriwatch.application.dto.request.UserRequest;
import com.platform.agriwatch.application.dto.response.AuthResponse;
import com.platform.agriwatch.domain.service.AuthService;
import com.platform.agriwatch.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController

@RequestMapping(value = "auth/api/v1", produces = "application/json")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        return authService.login(request);

    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody UserRequest request) {
        return authService.register(request);
    }

}
