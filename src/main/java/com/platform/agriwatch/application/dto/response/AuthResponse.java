package com.platform.agriwatch.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String status;
    private String message;
    private String token;
    private UserResponse user;


}

