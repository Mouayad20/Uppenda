package com.example.demo.Repositories;

import com.example.demo.Entities.QuestionEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<QuestionEntity,Long> {
    
}
