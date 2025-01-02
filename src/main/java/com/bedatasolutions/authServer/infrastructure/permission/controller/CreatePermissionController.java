package com.bedatasolutions.authServer.infrastructure.permission.controller;

import com.bedatasolutions.authServer.usecase.permission.CreatePermissionUseCase;

public class CreatePermissionController {

    private final CreatePermissionUseCase createPermissionUseCase;

    public CreatePermissionController(CreatePermissionUseCase createPermissionUseCase) {
        this.createPermissionUseCase = createPermissionUseCase;
    }
}