package com.bedatasolutions.authServer.infrastructure.permission.gateway;

import com.bedatasolutions.authServer.entity.permission.gateway.PermissionGateway;
import com.bedatasolutions.authServer.entity.permission.model.Permission;
import com.bedatasolutions.authServer.repository.PermissionRepository;
import org.springframework.stereotype.Service;

@Service
public class PermissionDatabaseGateway implements PermissionGateway {

    private final PermissionRepository permissionRepository;

    public PermissionDatabaseGateway(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Permission create(Permission permission) {
        return permissionRepository.save(permission);
    }
}