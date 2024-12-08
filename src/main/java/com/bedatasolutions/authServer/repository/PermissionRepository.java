package com.bedatasolutions.authServer.repository;

import com.bedatasolutions.authServer.dao.PermissionDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionDao, Long> {
    // You can add custom query methods if needed, e.g.:
    // List<PermissionDao> findByDescription(String description);
}