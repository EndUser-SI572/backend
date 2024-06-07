package com.platform.agriwatch.application.dto.request;

import com.platform.agriwatch.domain.model.User;
import lombok.Data;

@Data
public class UserRequest {

    private String name;
    private String lastName;
    private String username;
    private String password;
    private Integer age;
    private String gender;
    private String cellPhone;

    public  User toUser(){

        return User.builder()
                .name(this.name)
                .lastName(this.lastName)
                .username(this.username)
                .age(this.age)
                .gender(this.gender)
                .cellPhone(this.cellPhone)
                .build();
    }
}
