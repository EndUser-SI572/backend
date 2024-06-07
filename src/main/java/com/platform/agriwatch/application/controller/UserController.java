package com.platform.agriwatch.application.controller;

import com.platform.agriwatch.application.dto.request.UserRequest;
import com.platform.agriwatch.application.dto.response.UserResponse;
import com.platform.agriwatch.domain.model.User;
import com.platform.agriwatch.domain.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/user", produces = "application/json")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest){

        User user = userRequest.toUser();
        user.setPassword(userRequest.getPassword());
        user.setNumberPlants(0);

        userService.create(user);

        return new ResponseEntity<>(user.toUserResponse(), HttpStatus.CREATED);
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserResponse>getById(@PathVariable Long userId) {
        Optional<User> user = userService.getById(userId);

        if (user.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(user.get().toUserResponse());
    }

    @PutMapping("{userId}")
    public ResponseEntity<UserResponse>update(@PathVariable Long userId, @RequestBody UserRequest userRequest) {
        Optional<User> user = userService.getById(userId);

        if (user.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        User newUser = user.get();
        newUser.setName(userRequest.getName());
        newUser.setUsername(userRequest.getUsername());
        newUser.setLastName(userRequest.getLastName());
        newUser.setAge(userRequest.getAge());
        newUser.setGender(userRequest.getGender());
        newUser.setCellPhone(userRequest.getCellPhone());

        userService.update(newUser);

        return ResponseEntity.ok(newUser.toUserResponse());

    }
}
