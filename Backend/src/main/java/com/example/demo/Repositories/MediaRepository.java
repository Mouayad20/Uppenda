package com.example.demo.Repositories;

import com.example.demo.Entities.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MediaRepository extends JpaRepository<MediaEntity, Long> {
}
