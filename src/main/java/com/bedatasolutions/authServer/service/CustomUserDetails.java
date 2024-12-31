package com.bedatasolutions.authServer.service;

import com.bedatasolutions.authServer.dao.UserDao;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final UserDao user;

    @JsonCreator
    public CustomUserDetails(@JsonProperty("user") UserDao user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getFullName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getIsAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getIsAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getIsCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.getEnabled();
    }

    public UserDao getUser() {
        return new UserDao(user.getId(), user.getFullName(), user.getEmail(), user.getPhone(), user.getAge(), user.getAddress()
                , user.getPassword(), user.getCreatedAt(), user.getUpdatedAt(), user.getIsAccountNonExpired(), user.getIsAccountNonLocked()
                , user.getIsCredentialsNonExpired(), user.getEnabled(), user.getIsRoleResourceAccess(), user.getMfaSecret()
                , user.getMfaKeyId(), user.getMfaEnabled(), user.getMfaRegistered(), user.getRoles(), user.getResources(), user.getAuthorities());
    }
}