package com.bedatasolutions.authServer.entity.permission.gateway;

import com.bedatasolutions.authServer.entity.permission.model.Permission;

public interface PermissionGateway {
    Permission create(Permission permission);
}