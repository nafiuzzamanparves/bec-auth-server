package com.bedatasolutions.authServer.repository;

import com.bedatasolutions.authServer.dao.UserDao;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDao, Long> {
    Optional<UserDao> findByFullName(String fullName);
}