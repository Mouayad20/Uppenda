package com.example.demo.Repositories;

import com.example.demo.Entities.ReactionEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionsRepository extends CrudRepository<ReactionEntity,Long> {
    
}
