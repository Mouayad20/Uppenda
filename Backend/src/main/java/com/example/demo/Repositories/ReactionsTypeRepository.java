package com.example.demo.Repositories;

import com.example.demo.Entities.ReactionTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionsTypeRepository extends CrudRepository<ReactionTypeEntity, Long> {

}
