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

    public List<PermissionResponseDTO> getAllPermissions() {
        List<PermissionDao> permissions = permissionRepository.findAll();

        return permissions.stream()
                .map(permissionDao -> {
                    PermissionResponseDTO permissionResponseDTO = new PermissionResponseDTO();
                    permissionResponseDTO.setId(permissionDao.getId());
                    permissionResponseDTO.setDescription(permissionDao.getDescription());
                    return permissionResponseDTO;
                })
                .toList();
    }

    public Optional<PermissionDao> getPermissionById(Long id) {
        return permissionRepository.findById(id);
    }

    public PermissionResponseDTO getPermissionResponseById(Long id) {
        Optional<PermissionDao> permissionDao = permissionRepository.findById(id);
        PermissionResponseDTO permissionResponseDTO = new PermissionResponseDTO();

        if (permissionDao.isPresent()) {
            permissionResponseDTO.setId(permissionDao.get().getId());
            permissionResponseDTO.setDescription(permissionDao.get().getDescription());
        } else {
            permissionResponseDTO = null;
        }

        return permissionResponseDTO;
    }

    public PermissionResponseDTO savePermission(PermissionCreateDTO permission) {
        PermissionDao permissionDao = new PermissionDao();
        permissionDao.setDescription(permission.getDescription());

        PermissionDao saved = permissionRepository.save(permissionDao);

        PermissionResponseDTO responseDTO = new PermissionResponseDTO();
        responseDTO.setId(saved.getId());
        responseDTO.setDescription(saved.getDescription());

        return responseDTO;
    }

    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }

    public PermissionResponseDTO updatePermission(Long id, PermissionUpdateDTO updateDTO) {
        // Find the existing permission by ID
        Optional<PermissionDao> existingPermissionOpt = permissionRepository.findById(id);
        PermissionResponseDTO permissionResponseDTO = null;

        if (existingPermissionOpt.isPresent()) {
            PermissionDao existingPermission = existingPermissionOpt.get();

            if (updateDTO.getDescription() != null) {
                existingPermission.setDescription(updateDTO.getDescription());
            }

            PermissionDao saved = permissionRepository.save(existingPermission);
            permissionResponseDTO = new PermissionResponseDTO();
            permissionResponseDTO.setId(saved.getId());
            permissionResponseDTO.setDescription(saved.getDescription());
        }

        return permissionResponseDTO;
    }


}