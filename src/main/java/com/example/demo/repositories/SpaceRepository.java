package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Space;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Long> {
    List<Space> findByName(String name);

    @Query(value = "SELECT * FROM tb_space WHERE name LIKE %:name% ORDER BY id OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY", nativeQuery = true)
    List<Space> findSpacesWithPagination(@Param("name") String name, @Param("offset") int offset, @Param("limit") int limit);
}
