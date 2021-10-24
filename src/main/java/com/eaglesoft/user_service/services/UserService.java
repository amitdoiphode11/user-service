package com.eaglesoft.user_service.services;

import com.eaglesoft.user_service.entity.User;

import java.util.List;

public interface UserService {
    public List<User> getUsers();
    public User getUser(Long id);
}
