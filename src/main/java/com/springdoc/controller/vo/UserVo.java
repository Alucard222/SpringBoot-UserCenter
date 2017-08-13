package com.springdoc.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by alucard on 8/4/17.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {

    @NotEmpty(message = "please input your phone number!")
    private String phone;
    @NotEmpty(message = "please set your password!")
    private String password;
    private String username;
    private String email;
    private String address;

    @Override
    public String toString() {
        return "UserVo{" +
                "email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
