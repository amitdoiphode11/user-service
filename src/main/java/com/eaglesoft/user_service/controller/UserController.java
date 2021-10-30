package com.eaglesoft.user_service.controller;

import com.eaglesoft.user_service.VO.UserResponseVO;
import com.eaglesoft.user_service.entity.User;
import com.eaglesoft.user_service.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{userId}")
    public UserResponseVO getUser(@PathVariable("userId") Long userId) {
        log.info("getUser() " + userId);
        User user = this.userService.getUser(userId);
        List contacts = restTemplate.getForObject("http://contact-service/contact/user/" + user.getUserId(), List.class);

        UserResponseVO vo = new UserResponseVO();
        vo.setUser(user);
        vo.setContacts(contacts);
        return vo;
    }

    @PostMapping("/")
    public UserResponseVO saveUser(@Validated @RequestBody UserResponseVO requestUserVo) {
        log.info("saveUser:" + requestUserVo);
        UserResponseVO responseVO = new UserResponseVO();
        log.info("saveUser: save user service call");
        User user = userService.saveUser(requestUserVo.getUser());
        log.info("saveUser: update the user id in contact list");
        requestUserVo.getContacts().forEach(contact -> {
            contact.setUserId(user.getUserId());
        });

        log.info("saveUser: contact service");
        List contacts = restTemplate.postForObject("http://contact-service/contact/", requestUserVo.getContacts(), List.class);
        responseVO.setContacts((contacts));
        log.info("saveUser: contact service response" + contacts);

        responseVO.setUser(user);
        log.info("saveUser: return response" + requestUserVo);
        return responseVO;
    }

    @PutMapping("/")
    public UserResponseVO updateUser(@Validated @RequestBody UserResponseVO requestUserVo) {
        log.info("updateUser" + requestUserVo);

        log.info("update user service call");
        userService.updateUser(requestUserVo.getUser());

        log.info("update contact service call");
        restTemplate.put("http://contact-service/contact/user/{userId}",
                requestUserVo.getContacts(),
                Map.of("userId", requestUserVo.getUser().getUserId().toString()));

        UserResponseVO responseVO = getUser(requestUserVo.getUser().getUserId());
        return responseVO;
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        log.info("deleteUser");

        log.info("delete user service call");
        userService.deleteUser(userId);

        log.info("delete contact service call");
        restTemplate.delete("http://contact-service/contact/user/{userId}",
                userId);
    }
}
