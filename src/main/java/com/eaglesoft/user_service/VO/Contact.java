package com.eaglesoft.user_service.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contact {
    private Long contactId;
    @NotBlank(message = "Please add user email")
    private String email;
    private String contactName;
    @NotBlank(message = "Please add user id")
    private Long userId;

}
