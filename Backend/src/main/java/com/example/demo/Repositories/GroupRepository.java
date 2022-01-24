package com.example.demo.Repositories;

import java.util.List;
import java.util.Optional;

import com.example.demo.Entities.GroupEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends CrudRepository<GroupEntity,Long>{
    GroupEntity findByName(String name);

    @Query(value = "SELECT * FROM `groups` WHERE `admin_id` = :admin_id",nativeQuery = true)
    Optional<List<GroupEntity>> getAllGroupsThatUserIsAdiminIn(@Param("admin_id")Long id);

    @Query(value = "SELECT * FROM `groups` WHERE 1",nativeQuery = true)
    List<GroupEntity> getAllGroup();


    @Query(value = "SELECT * FROM groups WHERE  name LIKE %:nameGroup% ",nativeQuery = true)
    public List<GroupEntity> searchGroup(@Param("nameGroup") String nameGroup);
    
}
