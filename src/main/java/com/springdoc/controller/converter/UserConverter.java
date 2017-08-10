package com.springdoc.controller.converter;

import com.google.common.base.Converter;
import com.springdoc.controller.vo.UserVo;
import com.springdoc.model.User;
import org.springframework.stereotype.Component;

/**
 * Created by alucard on 8/4/17.
 */

@Component
public class UserConverter extends Converter<User, UserVo>{

    @Override
    protected UserVo doForward(User user){
        return UserVo.builder()
                .password(user.getPassword())
                .address(user.getAddress())
                .email(user.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .build();
    }

    @Override
    protected User doBackward(UserVo userVo){
        return User.builder()
                .phone(userVo.getPhone())
                .name(userVo.getName())
                .password(userVo.getPassword())
                .email(userVo.getEmail())
                .address(userVo.getAddress())
                .build();
    }
}
