package com.bedatasolutions.authServer.infrastructure.permission.controller;

import com.bedatasolutions.authServer.infrastructure.common.dto.ApiResponse;
import com.bedatasolutions.authServer.infrastructure.permission.dto.PermissionCreateDTO;
import com.bedatasolutions.authServer.infrastructure.permission.dto.PermissionResponseDTOImpl;
import com.bedatasolutions.authServer.infrastructure.permission.dto.PermissionUpdateDTO;
import com.bedatasolutions.authServer.service.PermissionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PermissionResponseDTOImpl>>> getAllPermissions() {
        List<PermissionResponseDTOImpl> permissions = permissionService.getAllPermissions();
        return ResponseEntity.ok(ApiResponse.success(permissions, "Fetched all permissions successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PermissionResponseDTOImpl>> getPermissionById(@PathVariable Long id) {
        PermissionResponseDTOImpl responseDTO = permissionService.getPermissionResponseById(id);
        if (responseDTO != null) {
            return ResponseEntity.ok(ApiResponse.success(responseDTO, "Permission fetched successfully"));
        } else {
            return ResponseEntity.ok(ApiResponse.failed("Permission not found with given id"));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PermissionResponseDTOImpl>> createPermission(@RequestBody @Valid PermissionCreateDTO createDTO) {
        PermissionResponseDTOImpl createdPermission = permissionService.savePermission(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(createdPermission, "Permission created successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PermissionResponseDTOImpl>> updatePermission(@PathVariable Long id, @RequestBody @Valid PermissionUpdateDTO updateDTO) {
        return permissionService.updatePermission(id, updateDTO) != null
                ? ResponseEntity.ok(ApiResponse.success(permissionService.updatePermission(id, updateDTO), "Permission updated successfully"))
                : ResponseEntity.ok(ApiResponse.failed("Failed to update permission or permission not found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deletePermission(@PathVariable Long id) {
        if (permissionService.getPermissionById(id).isEmpty()) {
            return ResponseEntity.ok(ApiResponse.failed("Permission not found"));
        }
        permissionService.deletePermission(id);
        return ResponseEntity.ok(ApiResponse.success("Permission deleted successfully"));
    }
}