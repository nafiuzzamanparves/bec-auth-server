package com.bedatasolutions.authServer.infrastructure.permission.controller;

import com.bedatasolutions.authServer.entity.permission.model.Permission;
import com.bedatasolutions.authServer.infrastructure.common.dto.ApiResponse;
import com.bedatasolutions.authServer.infrastructure.permission.dto.PermissionCreateDTO;
import com.bedatasolutions.authServer.infrastructure.permission.dto.PermissionResponseDTO;
import com.bedatasolutions.authServer.usecase.permission.CreatePermissionUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreatePermissionController {

    private final CreatePermissionUseCase createPermissionUseCase;

    public CreatePermissionController(CreatePermissionUseCase createPermissionUseCase) {
        this.createPermissionUseCase = createPermissionUseCase;
    }

    @PostMapping("/api/v1/permissions")
    public ResponseEntity<ApiResponse<PermissionResponseDTO>> createPermission(@RequestBody @Valid PermissionCreateDTO createDTO) {
        Permission permission = createPermissionUseCase.execute(createDTO);
        PermissionResponseDTO createdPermission = new PermissionResponseDTO(permission);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(createdPermission, "Permission created successfully"));
    }
}