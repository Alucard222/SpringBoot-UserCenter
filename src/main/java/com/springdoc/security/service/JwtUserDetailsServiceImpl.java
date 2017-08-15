package com.springdoc.security.service;

import com.springdoc.model.User;
import com.springdoc.repository.UserRepository;
import com.springdoc.security.JwtUserDetail;
import com.springdoc.security.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by alucard on 8/14/17.
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByPhone(username);
        //System.out.println(user);
        if(user == null){
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }else{
            return JwtUserFactory.create(user);
        }
    }
}
