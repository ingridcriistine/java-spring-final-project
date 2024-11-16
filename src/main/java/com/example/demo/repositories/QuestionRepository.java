package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByQuestion(String question);

    @Query(value = "SELECT * FROM tb_question WHERE space_id = :spaceId ORDER BY id LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Question> findQuestionsBySpaceIdWithPagination(
        @Param("spaceId") Long spaceId,
        @Param("offset") int limit,
        @Param("limit") int offset
    );
    
}
