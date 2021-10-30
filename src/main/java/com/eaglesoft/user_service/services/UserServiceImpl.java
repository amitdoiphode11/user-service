package com.eaglesoft.user_service.services;

import com.eaglesoft.user_service.entity.User;
import com.eaglesoft.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User updateUser(User user) {
        User user1 = userRepository.findById(user.getUserId()).get();

        if (user.getName() != null) {
            user1.setName(user.getName());
        }
        if (user.getPhone() != null) {
            user1.setPhone(user.getPhone());
        }

        return userRepository.save(user1);
    }

    @Override
    public Optional<User> deleteUser(Long id) {
        userRepository.deleteById(id);
        return userRepository.findById(id);
    }
}
