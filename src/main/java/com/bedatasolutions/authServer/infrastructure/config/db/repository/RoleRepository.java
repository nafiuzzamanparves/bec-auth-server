package com.bedatasolutions.authServer.infrastructure.config.db.repository;

import com.bedatasolutions.authServer.entity.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}