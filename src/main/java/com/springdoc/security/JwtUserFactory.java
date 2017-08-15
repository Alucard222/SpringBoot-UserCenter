package com.springdoc.security;

import com.springdoc.model.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

import com.springdoc.model.*;

/**
 * Created by alucard on 8/14/17.
 */

@NoArgsConstructor
public final class JwtUserFactory {

    public static JwtUserDetail create(User user) {

        return JwtUserDetail.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .phone(user.getPhone())
                .username(user.getUsername())
                .authorities(mapToGrantedAuthorities(user.getRole()))
                .address(user.getAddress())
                .lastPasswordResetDate(user.getLastPasswordResetDate())
                .build();

    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().name()))
                .collect(Collectors.toList());
    }
}
