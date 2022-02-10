package com.example.demo.Repositories;

import com.example.demo.Entities.InterstEntity;
import com.example.demo.Entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InterstRepository extends CrudRepository<InterstEntity, Long> {
    Optional<InterstEntity> findByTypeAndUser(String type, UserEntity user);
}
