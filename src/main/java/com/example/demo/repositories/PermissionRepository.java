package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Permission;
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    List<Permission> findByIsAdmin(Boolean isAdmin);
    List<Permission> findByUserId(Long id);
    List<Permission> findBySpaceId(Long id);

    @Query(value = "SELECT COUNT(id) FROM tb_permission WHERE user_id = :userId and space_id = :spaceId", nativeQuery = true)
    Integer havePermission(@Param("userId") Long userId,@Param("spaceId") Long spaceId);
}
