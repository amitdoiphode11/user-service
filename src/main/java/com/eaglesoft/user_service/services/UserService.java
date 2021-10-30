package com.eaglesoft.user_service.services;

import com.eaglesoft.user_service.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    List<User> getUsers();

    User getUser(Long id);

    User updateUser(User user);

    Optional<User> deleteUser(Long id);
}
