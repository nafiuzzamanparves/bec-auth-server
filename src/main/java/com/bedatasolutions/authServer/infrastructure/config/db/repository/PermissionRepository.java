package com.bedatasolutions.authServer.infrastructure.config.db.repository;

import com.bedatasolutions.authServer.entity.permission.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    // You can add custom query methods if needed, e.g.:
    // List<Permission> findByDescription(String description);
}