package com.bedatasolutions.authServer.infrastructure.config.db.repository;

import com.bedatasolutions.authServer.entity.resource.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
}