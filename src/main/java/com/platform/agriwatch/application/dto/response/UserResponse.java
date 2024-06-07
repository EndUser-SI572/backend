package com.platform.agriwatch.application.dto.response;

import com.platform.agriwatch.domain.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private Long id;
    private String name;
    private String lastName;
    private String username;
    private Integer age;
    private String gender;
    private String cellPhone;
    private Integer numberPlants;

}
