package com.example.demo.Repositories;

import com.example.demo.Entities.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TypeRepository extends JpaRepository<TypeEntity, Long> {

    Optional<TypeEntity> findByType(String type);
}
