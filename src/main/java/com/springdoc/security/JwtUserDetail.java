package com.springdoc.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springdoc.model.Authority;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by alucard on 8/12/17.
 */
@Builder
public class JwtUserDetail implements UserDetails {

    private final Long id;
    private final String phone;
    private final String email;
    private final String password;
    private final String username;
    private Collection<? extends GrantedAuthority> authorities;
    private final String address;
    private final Date lastPasswordResetDate;

    public Long getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    //here the username maybe cause some problem!!!!
    //take care
    @Override
    public String getUsername() {
        return phone;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getName() {
        return username;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @JsonIgnore
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }
}
