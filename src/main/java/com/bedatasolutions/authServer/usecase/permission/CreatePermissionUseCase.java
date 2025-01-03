package com.bedatasolutions.authServer.usecase.permission;

import com.bedatasolutions.authServer.entity.permission.gateway.IPermissionGateway;
import com.bedatasolutions.authServer.entity.permission.model.Permission;
import com.bedatasolutions.authServer.usecase.permission.dto.IPermissionCreateDTO;
import org.springframework.stereotype.Service;

@Service
public class CreatePermissionUseCase {

    private final IPermissionGateway IPermissionGateway;

    public CreatePermissionUseCase(IPermissionGateway IPermissionGateway) {
        this.IPermissionGateway = IPermissionGateway;
    }

    public Permission execute(IPermissionCreateDTO createDTO) {
        Permission permission = new Permission();
        permission.setDescription(createDTO.description());
        return this.IPermissionGateway.create(new Permission());
    }
}