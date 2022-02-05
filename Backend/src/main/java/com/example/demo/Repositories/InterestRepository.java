package com.example.demo.Repositories;

import com.example.demo.Entities.InterestEntity;
import com.example.demo.Entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InterestRepository extends CrudRepository<InterestEntity, Long> {
    Optional<InterestEntity> findByTypeAndUser(String type, UserEntity user);
}
