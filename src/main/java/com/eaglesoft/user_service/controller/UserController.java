package com.eaglesoft.user_service.controller;

import com.eaglesoft.user_service.entity.Contact;
import com.eaglesoft.user_service.entity.User;
import com.eaglesoft.user_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") Long userId) {
        User user = this.userService.getUser(userId);
        //http://localhost:9002/contact/user/1
        List contacts = restTemplate.getForObject("http://contact-service/contact/user/" + user.getUserId(), List.class);
        user.setContacts((ArrayList<Contact>) contacts);
        return user;
    }
}
