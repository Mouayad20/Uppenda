package com.example.demo.Repositories;

import java.util.List;

import com.example.demo.Entities.AnswersEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends CrudRepository<AnswersEntity,Long>{

    @Query(value = "SELECT * FROM answers WHERE user_id=:u_id ",nativeQuery = true)
    List<AnswersEntity> getAllAnswersForUserByUID(@Param("u_id") Long u_id);
    
}
