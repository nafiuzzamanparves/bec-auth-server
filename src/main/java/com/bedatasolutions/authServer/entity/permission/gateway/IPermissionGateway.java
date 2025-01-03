package com.bedatasolutions.authServer.entity.permission.gateway;

import com.bedatasolutions.authServer.entity.permission.model.Permission;

import java.util.List;
import java.util.Optional;

public interface IPermissionGateway {
    Permission create(Permission permission);

    Permission update(Permission permission);

    void delete(Long id);

    Optional<Permission> findById(Long id);

    List<Permission> findAll();
}