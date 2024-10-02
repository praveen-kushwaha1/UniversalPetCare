package com.spring.factory;

import com.spring.model.User;
import com.spring.request.RegistrationRequest;

public interface UserFactory {
    public User createUser(RegistrationRequest registrationRequest);
}
