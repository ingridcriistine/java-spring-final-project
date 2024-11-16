package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmail(String email);
    List<User> findByEdv(String edv);
    List<User> findByName(String name);

    @Query("SELECT u FROM User u WHERE u.email = :loginValue or u.email = :loginValue")
    List<User> login(@Param("loginValue") String loginValue);

    @Query(value = "SELECT * FROM tb_user ORDER BY id LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<User> findUsersWithPagination(@Param("offset") int offset, @Param("limit") int limit);
}
