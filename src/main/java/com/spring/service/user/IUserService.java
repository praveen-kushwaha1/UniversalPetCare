package com.spring.service.user;

import com.spring.dto.UserDto;
import com.spring.model.User;
import com.spring.request.RegistrationRequest;
import com.spring.request.UserUpdateRequest;

import java.util.List;

public interface IUserService {
    User register(RegistrationRequest request);

    User update(Long userId, UserUpdateRequest request);

    User findById(Long userId);

    void delete(Long userId);

    List<UserDto> getAllUsers();
}
