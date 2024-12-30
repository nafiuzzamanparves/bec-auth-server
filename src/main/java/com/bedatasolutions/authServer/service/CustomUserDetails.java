package com.bedatasolutions.authServer.service;

import com.bedatasolutions.authServer.dao.UserDao;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;

// @JsonDeserialize(as = CustomUserDetails.class)
public class CustomUserDetails implements UserDetails, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private final UserDao user;

    /* public CustomUserDetails(UserDao user) {
        this.user = user;
    } */
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