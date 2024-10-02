package com.spring.service.user;

import com.spring.factory.UserFactory;
import com.spring.model.User;
import com.spring.repository.UserRepository;
import com.spring.request.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserFactory userFactory;


    public User add(RegistrationRequest request) {
      return userFactory.createUser(request);
    }
}
