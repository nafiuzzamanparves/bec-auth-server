package com.bedatasolutions.authServer.service;

import com.bedatasolutions.authServer.dao.PermissionDao;
import com.bedatasolutions.authServer.dto.permission.PermissionCreateDTO;
import com.bedatasolutions.authServer.dto.permission.PermissionResponseDTO;
import com.bedatasolutions.authServer.dto.permission.PermissionUpdateDTO;
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
    public List<PermissionResponseDTO> getAllPermissions() {
        return permissionRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    // Fetch permission by ID and return as optional DAO
    public Optional<PermissionDao> getPermissionById(Long id) {
        return permissionRepository.findById(id);
    }

    // Fetch permission by ID and map to DTO
    public PermissionResponseDTO getPermissionResponseById(Long id) {
        return permissionRepository.findById(id)
                .map(this::mapToResponseDTO)
                .orElse(null);
    }

    // Save a new permission
    public PermissionResponseDTO savePermission(PermissionCreateDTO permission) {
        PermissionDao permissionDao = mapToEntity(permission);
        PermissionDao saved = permissionRepository.save(permissionDao);
        return mapToResponseDTO(saved);
    }

    // Delete permission by ID
    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }

    // Update existing permission
    public PermissionResponseDTO updatePermission(Long id, PermissionUpdateDTO updateDTO) {
        return permissionRepository.findById(id)
                .map(existingPermission -> {
                    if (updateDTO.getDescription() != null) {
                        existingPermission.setDescription(updateDTO.getDescription());
                    }
                    PermissionDao updated = permissionRepository.save(existingPermission);
                    return mapToResponseDTO(updated);
                })
                .orElse(null);
    }

    // Utility: Map PermissionDao to PermissionResponseDTO
    private PermissionResponseDTO mapToResponseDTO(PermissionDao permissionDao) {
        PermissionResponseDTO dto = new PermissionResponseDTO();
        dto.setId(permissionDao.getId());
        dto.setDescription(permissionDao.getDescription());
        return dto;
    }

    // Utility: Map PermissionCreateDTO to PermissionDao
    private PermissionDao mapToEntity(PermissionCreateDTO createDTO) {
        PermissionDao permissionDao = new PermissionDao();
        permissionDao.setDescription(createDTO.getDescription());
        return permissionDao;
    }
}