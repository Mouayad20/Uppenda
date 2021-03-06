package com.example.demo.Repositories;

import com.example.demo.Entities.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

    GroupEntity findByName(String name);

    @Query(value = "SELECT * FROM `groups` WHERE 1", nativeQuery = true)
    List<GroupEntity> getAllGroup();

    @Query(value = "SELECT * FROM groups WHERE  name LIKE %:nameGroup% ", nativeQuery = true)
    List<GroupEntity> search(@Param("nameGroup") String nameGroup);

}
