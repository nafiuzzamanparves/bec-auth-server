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
        try {
            List<PermissionResponseDTO> permissionResponseDTOList = permissionService.getAllPermissions();
            return ResponseEntity.ok(ApiResponse.success(permissionResponseDTOList, "Fetched all permission successfully"));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(e.getMessage()));
        }
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
        PermissionResponseDTO permission = permissionService.savePermission(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(permission, "Permission fetched successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PermissionResponseDTO>> updatePermission(@PathVariable Long id, @RequestBody @Valid PermissionUpdateDTO updateDTO) {
        try {
            PermissionResponseDTO permission = permissionService.updatePermission(id, updateDTO);
            if (permission != null) {
                return ResponseEntity.ok(ApiResponse.success(permission, "Permission updated successfully"));
            } else {
                return ResponseEntity.ok(ApiResponse.failed("Failed to update permission"));
            }
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deletePermission(@PathVariable Long id) {
        try {
            if (permissionService.getPermissionById(id).isEmpty()) {
                return ResponseEntity.ok(ApiResponse.success("No permission found"));
            } else {
                permissionService.deletePermission(id);
                return ResponseEntity.ok(ApiResponse.success("Permission deleted"));
            }
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(e.getMessage()));
        }
    }
}