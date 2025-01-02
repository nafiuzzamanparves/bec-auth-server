package com.bedatasolutions.authServer.infrastructure.permission.controller;

import com.bedatasolutions.authServer.usecase.permission.GetPermissionUseCase;

public class GetPermissionController {

    private final GetPermissionUseCase getPermissionUseCase;

    public GetPermissionController(GetPermissionUseCase getPermissionUseCase) {
        this.getPermissionUseCase = getPermissionUseCase;
    }
}