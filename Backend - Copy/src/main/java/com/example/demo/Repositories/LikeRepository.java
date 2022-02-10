package com.example.demo.Repositories;

import java.util.List;

import com.example.demo.Entities.LikeEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends CrudRepository<LikeEntity,Long> {

    @Query(value = "SELECT * FROM likes WHERE post_id=:post_id",nativeQuery = true)
    public List<LikeEntity> getAllLikeByPostId(@Param("post_id")Long post_id);
    
}
