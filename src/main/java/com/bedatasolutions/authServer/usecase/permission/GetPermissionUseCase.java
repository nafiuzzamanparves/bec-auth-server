package com.bedatasolutions.authServer.usecase.permission;

import com.bedatasolutions.authServer.entity.permission.exception.PermissionNotFoundException;
import com.bedatasolutions.authServer.entity.permission.gateway.IPermissionGateway;
import com.bedatasolutions.authServer.entity.permission.model.Permission;
import org.springframework.stereotype.Service;

@Service
public class GetPermissionUseCase {

    private final IPermissionGateway IPermissionGateway;

    public GetPermissionUseCase(IPermissionGateway IPermissionGateway) {
        this.IPermissionGateway = IPermissionGateway;
    }

    public Permission execute(Long id) throws PermissionNotFoundException {
        return IPermissionGateway
                .findById(id)
                .orElseThrow(PermissionNotFoundException::new);
    }
}