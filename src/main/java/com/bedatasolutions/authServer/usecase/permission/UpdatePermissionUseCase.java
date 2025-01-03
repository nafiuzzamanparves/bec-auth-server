package com.bedatasolutions.authServer.usecase.permission;

import com.bedatasolutions.authServer.entity.permission.exception.PermissionNotFoundException;
import com.bedatasolutions.authServer.entity.permission.gateway.IPermissionGateway;
import com.bedatasolutions.authServer.entity.permission.model.Permission;
import com.bedatasolutions.authServer.usecase.permission.dto.IPermissionUpdateDTO;
import org.springframework.stereotype.Service;

@Service
public class UpdatePermissionUseCase {

    private final IPermissionGateway permissionGateway;

    public UpdatePermissionUseCase(IPermissionGateway permissionGateway) {
        this.permissionGateway = permissionGateway;
    }

    public Permission execute(Long id, IPermissionUpdateDTO updateDTO) throws PermissionNotFoundException {
        Permission permission = this.permissionGateway
                .findById(id)
                .orElseThrow(PermissionNotFoundException::new);

        return this.permissionGateway.update(permission);
    }
}