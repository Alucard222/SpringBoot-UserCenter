package com.springdoc.controller;

import com.springdoc.controller.converter.UserConverter;
import com.springdoc.controller.vo.ResponseVo;
import com.springdoc.controller.vo.UserVo;
import com.springdoc.model.User;
import com.springdoc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by alucard on 8/4/17.
 */

@RestController
public class LoginController {


    private UserService userService;
    private UserConverter userConverter;

    @Autowired
    public LoginController(UserService userService, UserConverter userConverter){
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @RequestMapping(value="/dashboard", method=RequestMethod.GET)
    public String dashboard(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        return "here is the dashboard! hello" + user.getName();
    }



    //test page
    @RequestMapping(value="/test", method=RequestMethod.GET)
    public String test(){
        return "hello!";
    }

    //registration page
    @CrossOrigin
    @RequestMapping(value="/registration", method= RequestMethod.POST)
    public ResponseEntity<ResponseVo> registration(@RequestBody @Valid UserVo userVo){

        return userService.registration(userConverter.reverse().convert(userVo));
    }
}

