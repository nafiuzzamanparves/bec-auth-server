package com.bedatasolutions.authServer.infrastructure.permission.controller;

import com.bedatasolutions.authServer.usecase.permission.DeletePermissionUseCase;

public class DeletePermissionController {

    private final DeletePermissionUseCase deletePermissionUseCase;

    public DeletePermissionController(DeletePermissionUseCase deletePermissionUseCase) {
        this.deletePermissionUseCase = deletePermissionUseCase;
    }
}