package com.bedatasolutions.authServer.service;

import com.bedatasolutions.authServer.entity.permission.model.Permission;
import com.bedatasolutions.authServer.infrastructure.permission.dto.PermissionCreateDTO;
import com.bedatasolutions.authServer.infrastructure.permission.dto.PermissionResponseDTOImpl;
import com.bedatasolutions.authServer.infrastructure.permission.dto.PermissionUpdateDTO;
import com.bedatasolutions.authServer.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    // Fetch all permissions and map to DTOs
    public List<PermissionResponseDTOImpl> getAllPermissions() {
        return permissionRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    // Fetch permission by ID and return as optional DAO
    public Optional<Permission> getPermissionById(Long id) {
        return permissionRepository.findById(id);
    }

    // Fetch permission by ID and map to DTO
    public PermissionResponseDTOImpl getPermissionResponseById(Long id) {
        return permissionRepository.findById(id)
                .map(this::mapToResponseDTO)
                .orElse(null);
    }

    // Save a new permission
    public PermissionResponseDTOImpl savePermission(PermissionCreateDTO permissionCreateDTO) {
        Permission permission = mapToEntity(permissionCreateDTO);
        Permission saved = permissionRepository.save(permission);
        return mapToResponseDTO(saved);
    }

    // Delete permission by ID
    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }

    // Update existing permission
    public PermissionResponseDTOImpl updatePermission(Long id, PermissionUpdateDTO permissionUpdateDTO) {
        return permissionRepository.findById(id)
                .map(existingPermission -> {
                    if (permissionUpdateDTO.getDescription() != null) {
                        existingPermission.setDescription(permissionUpdateDTO.getDescription());
                    }
                    Permission updated = permissionRepository.save(existingPermission);
                    return mapToResponseDTO(updated);
                })
                .orElse(null);
    }

    // Utility: Map PermissionDao to PermissionResponseDTO
    private PermissionResponseDTOImpl mapToResponseDTO(Permission permission) {
        return new PermissionResponseDTOImpl(permission);
    }

    // Utility: Map PermissionCreateDTO to PermissionDao
    private Permission mapToEntity(PermissionCreateDTO createDTO) {
        Permission permission = new Permission();
        permission.setDescription(createDTO.getDescription());
        return permission;
    }
}