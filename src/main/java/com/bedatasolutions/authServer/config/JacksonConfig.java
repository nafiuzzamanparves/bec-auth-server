package com.bedatasolutions.authServer.config;

import com.bedatasolutions.authServer.service.CustomUserDetails;
import com.bedatasolutions.authServer.service.CustomUserDetailsMixin;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.addMixIn(CustomUserDetails.class, CustomUserDetailsMixin.class);
        return objectMapper;
    }
}