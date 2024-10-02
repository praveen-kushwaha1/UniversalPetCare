package com.spring.service;

import com.spring.model.User;

import com.spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void add(User user) {
        userRepository.save(user);
    }
}
