package com.eaglesoft.user_service.VO;

import com.eaglesoft.user_service.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseVO {
    private User user;
    private List<Contact> contacts;
}
