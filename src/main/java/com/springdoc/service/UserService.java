package com.springdoc.service;

import com.springdoc.common.TimeProvider;
import com.springdoc.controller.vo.ProcessStatus;
import com.springdoc.controller.vo.ResponseVo;
import com.springdoc.model.Authority;
import com.springdoc.model.Role;
import com.springdoc.model.User;
import com.springdoc.repository.RoleRepository;
import com.springdoc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by alucard on 8/4/17.
 */

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private TimeProvider timeProvider;


//    public User findUserByEmail(String email){
//        return userRepository.findByEmail(email);
//    }

    public User findUserByPhone(String phone){
        return userRepository.findByPhone(phone);
    }

    public void saveUser(User user){
        User newUser = User.builder()
                .password(bCryptPasswordEncoder.encode(user.getPassword()))
                .phone(user.getPhone())
                .address(user.getAddress())
                .email(user.getEmail())
                .username(user.getUsername())
                .role(roleRepository.findByRole(Authority.USER))
                .lastPasswordResetDate(timeProvider.now())
                .build();
        userRepository.save(newUser);
    }


    public ResponseEntity<ResponseVo> registration(User user){

        User userExists = findUserByPhone(user.getPhone());

        if(userExists != null){
            ResponseVo  responseVo = ResponseVo.builder()
                    .timestamp(Timestamp.valueOf(LocalDateTime.now()).getNanos())
                    .processStatus(ProcessStatus.FAIL)
                    .message("the phone number is already used, please input a new one")
                    .build();
            return new ResponseEntity<>(responseVo, HttpStatus.BAD_REQUEST);
        }else{
            saveUser(user);
        }

        ResponseVo responseVo = ResponseVo.builder()
                .timestamp(Timestamp.valueOf(LocalDateTime.now()).getNanos())
                .processStatus(ProcessStatus.SUCCESS)
                .message("success, your account number is " + user.getPhone() + " !")
                .build();
        return new ResponseEntity<>(responseVo, HttpStatus.OK);
    }
}
