package com.bedatasolutions.authServer.repository;

import com.bedatasolutions.authServer.entity.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}