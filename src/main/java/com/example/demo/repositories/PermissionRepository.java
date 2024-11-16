package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    List<Permission> findByIsAdmin(Boolean isAdmin);
    List<Permission> findBySpaceId(Long id);
    List<Permission> findByUserId(Long id);
}
