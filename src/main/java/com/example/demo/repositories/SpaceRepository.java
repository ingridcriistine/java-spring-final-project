package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Space;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Long> {
    List<Space> findByName(String name);
}
