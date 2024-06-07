package com.platform.agriwatch.domain.service;

import com.platform.agriwatch.domain.model.User;

import java.util.Optional;

public interface UserService {

    User create(User user);
    Optional<User> getById(Long userId);
    User update(User user);

}
