package com.example.demo.Repositories;

import com.example.demo.Entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    @Query(value = "SELECT * FROM comment WHERE post_id = :post_id", nativeQuery = true)
    List<CommentEntity> getCommentsPost(@Param("post_id") Long post_id);
}
