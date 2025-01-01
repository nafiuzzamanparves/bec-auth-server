package com.bedatasolutions.authServer.mixin;

import com.bedatasolutions.authServer.entity.resource.model.Resource;
import com.bedatasolutions.authServer.entity.role.model.Role;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

public abstract class UserDaoMixin {

    @JsonCreator
    public UserDaoMixin(
            @JsonProperty("id") Long id,
            @JsonProperty("fullName") String fullName,
            @JsonProperty("email") String email,
            @JsonProperty("phone") String phone,
            @JsonProperty("age") Integer age,
            @JsonProperty("address") String address,
            @JsonProperty("password") String password,
            @JsonProperty("createdAt") String createdAt,
            @JsonProperty("updatedAt") String updatedAt,
            @JsonProperty("isAccountNonExpired") Boolean isAccountNonExpired,
            @JsonProperty("isAccountNonLocked") Boolean isAccountNonLocked,
            @JsonProperty("isCredentialsNonExpired") Boolean isCredentialsNonExpired,
            @JsonProperty("enabled") Boolean enabled,
            @JsonProperty("isRoleResourceAccess") Boolean isRoleResourceAccess,
            @JsonProperty("mfaSecret") String mfaSecret,
            @JsonProperty("mfaKeyId") String mfaKeyId,
            @JsonProperty("mfaEnabled") Boolean mfaEnabled,
            @JsonProperty("mfaRegistered") Boolean mfaRegistered,
            @JsonProperty("roles") Set<Role> roles,
            @JsonProperty("resources") Set<Resource> resources,
            @JsonProperty("authorities") Set<GrantedAuthority> authorities
    ) {
    }
}