package com.bedatasolutions.authServer.infrastructure.permission.dto;

import com.bedatasolutions.authServer.usecase.permission.dto.IPermissionUpdateDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PermissionUpdateDTO(
        @NotNull(message = "Description cannot be null")
        @Size(min = 3, max = 100, message = "Description must be between 3 and 100 characters")
        String description
) implements IPermissionUpdateDTO {
}