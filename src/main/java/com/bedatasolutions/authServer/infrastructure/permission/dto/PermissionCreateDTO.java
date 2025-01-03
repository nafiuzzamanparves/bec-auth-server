package com.bedatasolutions.authServer.infrastructure.permission.dto;

import com.bedatasolutions.authServer.usecase.permission.dto.IPermissionCreateDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PermissionCreateDTO(
        @NotNull(message = "Description cannot be null")
        @Size(min = 3, max = 100, message = "Description must be between 3 and 100 characters")
        String description
) implements IPermissionCreateDTO {
}