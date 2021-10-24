package com.eaglesoft.user_service.services;

import com.eaglesoft.user_service.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    //Fake user list
    List<User> list = List.of(
            new User(1L, "Amit", "123456"),
            new User(2L, "Aarya", "156"),
            new User(3L, "Puja", "123")
    );

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User getUser(Long id) {
        return list.stream().filter(user->user.getUserId().equals(id)).findAny().orElse(null);
    }
}
