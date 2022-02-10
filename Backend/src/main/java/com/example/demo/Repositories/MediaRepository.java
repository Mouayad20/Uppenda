package com.example.demo.Repositories;

import com.example.demo.Entities.MediaEntity;
import org.springframework.data.repository.CrudRepository;

public interface MediaRepository extends CrudRepository<MediaEntity, Long> {
}
