package com.bedatasolutions.authServer.usecase.permission;

import com.bedatasolutions.authServer.entity.permission.gateway.IPermissionGateway;
import org.springframework.stereotype.Service;

@Service
public class DeletePermissionUseCase {

    private final IPermissionGateway IPermissionGateway;

    public DeletePermissionUseCase(IPermissionGateway IPermissionGateway) {
        this.IPermissionGateway = IPermissionGateway;
    }

    public void execute(Long id) {
        IPermissionGateway.delete(id);
    }
}