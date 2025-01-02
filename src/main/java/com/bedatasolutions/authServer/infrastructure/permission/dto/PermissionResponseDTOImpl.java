package com.bedatasolutions.authServer.infrastructure.permission.dto;

import com.bedatasolutions.authServer.entity.permission.model.Permission;
import com.bedatasolutions.authServer.usecase.permission.dto.IPermissionResponseDTO;

public record PermissionResponseDTOImpl(Long id, String description) implements IPermissionResponseDTO {

    public PermissionResponseDTOImpl(Permission permission) {
        this(permission.getId(), permission.getDescription());
    }

}