package com.bedatasolutions.authServer.usecase.permission;

import com.bedatasolutions.authServer.entity.permission.gateway.PermissionGateway;
import com.bedatasolutions.authServer.entity.permission.model.Permission;
import com.bedatasolutions.authServer.usecase.permission.dto.IPermissionCreateDTO;
import org.springframework.stereotype.Service;

@Service
public class CreatePermissionUseCase {
    private final PermissionGateway permissionGateway;

    public CreatePermissionUseCase(PermissionGateway permissionGateway) {
        this.permissionGateway = permissionGateway;
    }

    public Permission execute(IPermissionCreateDTO createDTO)  {
        Permission permission = new Permission();
        permission.setDescription(createDTO.description());
        return this.permissionGateway.create(new Permission());
    }
}