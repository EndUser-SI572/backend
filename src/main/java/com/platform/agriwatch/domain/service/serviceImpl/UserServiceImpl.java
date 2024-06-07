package com.platform.agriwatch.domain.service.serviceImpl;

import com.platform.agriwatch.domain.model.User;
import com.platform.agriwatch.domain.repository.UserRepository;
import com.platform.agriwatch.domain.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public Optional<User> getById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }
}
