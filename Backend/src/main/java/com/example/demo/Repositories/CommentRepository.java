package com.example.demo.Repositories;

import com.example.demo.Entities.CommentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity, Long> {

    @Query(value = "SELECT * FROM comment WHERE post_id = :post_id", nativeQuery = true)
    List<CommentEntity> getAllCommentByPostId(@Param("post_id") Long post_id);
}
