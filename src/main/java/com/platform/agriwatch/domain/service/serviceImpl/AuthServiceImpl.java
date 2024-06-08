package com.platform.agriwatch.domain.service.serviceImpl;

import com.platform.agriwatch.application.dto.request.LoginRequest;
import com.platform.agriwatch.application.dto.request.UserRequest;
import com.platform.agriwatch.application.dto.response.AuthResponse;
import com.platform.agriwatch.domain.model.Role;
import com.platform.agriwatch.domain.model.User;
import com.platform.agriwatch.domain.service.AuthService;
import com.platform.agriwatch.domain.service.UserService;
import com.platform.agriwatch.infrastructure.config.jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserService userService, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(), loginRequest.getPassword()));

            Optional<User> user = userService.getByUsername(loginRequest.getUsername());

            if (user.isPresent()) {
                String token = jwtService.getToken(user.get());
                return AuthResponse.builder()
                        .status("success")
                        .message("Login successful")
                        .token(token)
                        .user(user.get().toUserResponse())
                        .build();
            } else {
                return AuthResponse.builder()
                        .status("error")
                        .message("User not found")
                        .build();
            }
        } catch (AuthenticationException e) {
            return AuthResponse.builder()
                    .status("error")
                    .message("Invalid username or password")
                    .build();
        }
    }


    @Override
    public AuthResponse register(UserRequest userRequest) {

        Optional<User> existingUser = userService.getByUsername(userRequest.getUsername());
        if (existingUser.isPresent()) {
            return new AuthResponse("error", "Username already exists", null, null);
        }

        User newUser = userRequest.toUser();
        newUser.setRole(Role.USER);
        newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        newUser.setNumberPlants(0);

        userService.create(newUser);
        String token = jwtService.getToken(newUser);

        return new AuthResponse("success", "User registered successfully", token, newUser.toUserResponse());


    }
}
