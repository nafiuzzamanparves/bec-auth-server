package com.bedatasolutions.authServer.controller;

import com.bedatasolutions.authServer.dto.ApiResponse;
import com.bedatasolutions.authServer.dto.permission.PermissionCreateDTO;
import com.bedatasolutions.authServer.dto.permission.PermissionResponseDTO;
import com.bedatasolutions.authServer.dto.permission.PermissionUpdateDTO;
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
    public ResponseEntity<ApiResponse<List<PermissionResponseDTO>>> getAllPermissions() {
        List<PermissionResponseDTO> permissions = permissionService.getAllPermissions();
        return ResponseEntity.ok(ApiResponse.success(permissions, "Fetched all permissions successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PermissionResponseDTO>> getPermissionById(@PathVariable Long id) {
        PermissionResponseDTO responseDTO = permissionService.getPermissionResponseById(id);
        if (responseDTO != null) {
            return ResponseEntity.ok(ApiResponse.success(responseDTO, "Permission fetched successfully"));
        } else {
            return ResponseEntity.ok(ApiResponse.failed("Permission not found with given id"));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PermissionResponseDTO>> createPermission(@RequestBody @Valid PermissionCreateDTO createDTO) {
        PermissionResponseDTO createdPermission = permissionService.savePermission(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(createdPermission, "Permission created successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PermissionResponseDTO>> updatePermission(@PathVariable Long id, @RequestBody @Valid PermissionUpdateDTO updateDTO) {
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