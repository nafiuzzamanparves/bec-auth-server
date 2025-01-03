package com.bedatasolutions.authServer.service;

import com.bedatasolutions.authServer.entity.permission.model.Permission;
import com.bedatasolutions.authServer.infrastructure.permission.dto.PermissionCreateDTO;
import com.bedatasolutions.authServer.infrastructure.permission.dto.PermissionResponseDTO;
import com.bedatasolutions.authServer.infrastructure.permission.dto.PermissionUpdateDTO;
import com.bedatasolutions.authServer.infrastructure.config.db.repository.PermissionRepository;
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
    public List<PermissionResponseDTO> getAllPermissions() {
        return permissionRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    // Fetch permission by ID and return as optional DAO
    public Optional<Permission> getPermissionById(Long id) {
        return permissionRepository.findById(id);
    }

    // Fetch permission by ID and map to DTO
    public PermissionResponseDTO getPermissionResponseById(Long id) {
        return permissionRepository.findById(id)
                .map(this::mapToResponseDTO)
                .orElse(null);
    }

    // Save a new permission
    public PermissionResponseDTO savePermission(PermissionCreateDTO permissionCreateDTO) {
        Permission permission = mapToEntity(permissionCreateDTO);
        Permission saved = permissionRepository.save(permission);
        return mapToResponseDTO(saved);
    }

    // Delete permission by ID
    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }

    // Update existing permission
    public PermissionResponseDTO updatePermission(Long id, PermissionUpdateDTO permissionUpdateDTO) {
        return permissionRepository.findById(id)
                .map(existingPermission -> {
                    if (permissionUpdateDTO.description() != null) {
                        existingPermission.setDescription(permissionUpdateDTO.description());
                    }
                    Permission updated = permissionRepository.save(existingPermission);
                    return mapToResponseDTO(updated);
                })
                .orElse(null);
    }

    // Utility: Map PermissionDao to PermissionResponseDTO
    private PermissionResponseDTO mapToResponseDTO(Permission permission) {
        return new PermissionResponseDTO(permission);
    }

    // Utility: Map PermissionCreateDTO to PermissionDao
    private Permission mapToEntity(PermissionCreateDTO createDTO) {
        Permission permission = new Permission();
        permission.setDescription(createDTO.description());
        return permission;
    }
}