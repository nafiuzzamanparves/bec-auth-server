package com.bedatasolutions.authServer.service;

import com.bedatasolutions.authServer.dao.UserDao;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public abstract class CustomUserDetailsMixin extends CustomUserDetails {
    public CustomUserDetailsMixin(UserDao user) {
        super(user);
    }
}
