package com.bedatasolutions.authServer.repository;

import com.bedatasolutions.authServer.dao.RoleDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleDao, Long> {
}