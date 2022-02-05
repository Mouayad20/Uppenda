package com.example.demo.Repositories;

import com.example.demo.Entities.ReactionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReactionRepository extends CrudRepository<ReactionEntity, Long> {

    @Query(value = "SELECT * FROM reactions WHERE post_id=:post_id", nativeQuery = true)
    public List<ReactionEntity> getAllReactionsByPostId(@Param("post_id") Long post_id);

}
