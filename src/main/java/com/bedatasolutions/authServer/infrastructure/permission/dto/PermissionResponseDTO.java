package com.bedatasolutions.authServer.infrastructure.permission.dto;

import com.bedatasolutions.authServer.entity.permission.model.Permission;
import com.bedatasolutions.authServer.usecase.permission.dto.IPermissionResponseDTO;

public record PermissionResponseDTO(Long id, String description) implements IPermissionResponseDTO {

    public PermissionResponseDTO(Permission permission) {
        this(permission.getId(), permission.getDescription());
    }

}