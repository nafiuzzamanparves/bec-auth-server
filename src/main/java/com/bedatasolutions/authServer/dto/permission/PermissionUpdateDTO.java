package com.bedatasolutions.authServer.dto.permission;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PermissionUpdateDTO {

    @NotNull(message = "ID cannot be null")
    private Long id;

    @NotNull(message = "Description cannot be null")
    @Size(min = 3, max = 100, message = "Description must be between 3 and 100 characters")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}