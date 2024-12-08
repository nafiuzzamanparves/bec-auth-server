package com.bedatasolutions.authServer.dto.permission;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class PermissionCreateDTO {

    @NotNull(message = "Description cannot be null")
    @Size(min = 3, max = 100, message = "Description must be between 3 and 100 characters")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}