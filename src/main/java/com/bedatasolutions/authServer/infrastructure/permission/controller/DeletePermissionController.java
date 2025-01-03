package com.bedatasolutions.authServer.infrastructure.permission.controller;

import com.bedatasolutions.authServer.entity.permission.exception.PermissionNotFoundException;
import com.bedatasolutions.authServer.infrastructure.common.dto.ApiResponse;
import com.bedatasolutions.authServer.usecase.permission.DeletePermissionUseCase;
import com.bedatasolutions.authServer.usecase.permission.GetPermissionUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeletePermissionController {

    private final DeletePermissionUseCase deletePermissionUseCase;
    private final GetPermissionUseCase getPermissionUseCase;

    public DeletePermissionController(DeletePermissionUseCase deletePermissionUseCase, GetPermissionUseCase getPermissionUseCase) {
        this.deletePermissionUseCase = deletePermissionUseCase;
        this.getPermissionUseCase = getPermissionUseCase;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deletePermission(@PathVariable Long id) throws PermissionNotFoundException {
        try {
            getPermissionUseCase.execute(id);
            deletePermissionUseCase.execute(id);

            return ResponseEntity.ok(ApiResponse.success("Permission deleted successfully"));
        } catch (PermissionNotFoundException e) {
            return ResponseEntity.ok(ApiResponse.error(e.getMessage()));
        }
    }
}