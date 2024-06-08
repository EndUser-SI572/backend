package com.platform.agriwatch.domain.service;

import com.platform.agriwatch.application.dto.request.LoginRequest;
import com.platform.agriwatch.application.dto.request.UserRequest;
import com.platform.agriwatch.application.dto.response.AuthResponse;

public interface AuthService {

    AuthResponse login(LoginRequest loginRequest);
    AuthResponse register(UserRequest userRequest);
}
