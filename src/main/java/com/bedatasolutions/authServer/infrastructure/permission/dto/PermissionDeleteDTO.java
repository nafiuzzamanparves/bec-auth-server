package com.bedatasolutions.authServer.infrastructure.permission.dto;

import com.bedatasolutions.authServer.usecase.permission.dto.IPermissionDeleteDTO;

public record PermissionDeleteDTO(Long id) implements IPermissionDeleteDTO {
}