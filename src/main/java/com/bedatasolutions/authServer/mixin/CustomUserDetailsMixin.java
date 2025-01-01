package com.bedatasolutions.authServer.mixin;

import com.bedatasolutions.authServer.entity.user.model.User;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public abstract class CustomUserDetailsMixin {
    @JsonCreator
    public CustomUserDetailsMixin(@JsonProperty("user") User user) {
    }

    @JsonIgnore
    public abstract Collection<? extends GrantedAuthority> getAuthorities();

    @JsonIgnore
    public abstract String getPassword();

    @JsonIgnore
    public abstract String getUsername();

    @JsonIgnore
    public abstract boolean isAccountNonExpired();

    @JsonIgnore
    public abstract boolean isAccountNonLocked();

    @JsonIgnore
    public abstract boolean isCredentialsNonExpired();

    @JsonIgnore
    public abstract boolean isEnabled();
}