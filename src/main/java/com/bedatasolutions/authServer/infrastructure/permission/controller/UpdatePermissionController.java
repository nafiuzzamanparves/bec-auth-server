package com.bedatasolutions.authServer.infrastructure.permission.controller;

import com.bedatasolutions.authServer.entity.permission.model.Permission;
import com.bedatasolutions.authServer.infrastructure.common.dto.ApiResponse;
import com.bedatasolutions.authServer.usecase.permission.UpdatePermissionUseCase;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdatePermissionController {

    private final UpdatePermissionUseCase updatePermissionUseCase;

    public UpdatePermissionController(UpdatePermissionUseCase updatePermissionUseCase) {
        this.updatePermissionUseCase = updatePermissionUseCase;
    }

    // @PutMapping("/permissions/{id}")
    // public ApiResponse<Permission> updatePermission(@PathVariable Long id, String description) {
    //     return ApiResponse.success(updatePermissionUseCase.execute(id, description));
    // }
}