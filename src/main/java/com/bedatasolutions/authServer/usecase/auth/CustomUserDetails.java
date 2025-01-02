package com.bedatasolutions.authServer.usecase.auth;

import com.bedatasolutions.authServer.entity.user.model.User;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public record CustomUserDetails(User user) implements UserDetails {

    @JsonCreator
    public CustomUserDetails(@JsonProperty("user") User user) {
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

    @Override
    public User user() {
        return new User(user.getId(), user.getFullName(), user.getEmail(), user.getPhone(), user.getAge(), user.getAddress()
                , user.getPassword(), user.getCreatedAt(), user.getUpdatedAt(), user.getIsAccountNonExpired(), user.getIsAccountNonLocked()
                , user.getIsCredentialsNonExpired(), user.getEnabled(), user.getIsRoleResourceAccess(), user.getMfaSecret()
                , user.getMfaKeyId(), user.getMfaEnabled(), user.getMfaRegistered(), user.getRoles(), user.getResources(), user.getAuthorities());
    }
}