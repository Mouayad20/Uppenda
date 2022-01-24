package com.example.demo.Repositories;

import java.util.List;
import java.util.Optional;

import com.example.demo.Entities.PageEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends CrudRepository<PageEntity,Long> {

    @Query(value = "SELECT * FROM `pages` WHERE 1",nativeQuery = true)
    List<PageEntity> getAllPages();

    @Query(value = "SELECT * FROM `pages` WHERE `admin_id` = :admin_id",nativeQuery = true)
    Optional<List<PageEntity>> getAllPagesThatUserIsAdimnIn(@Param("admin_id")Long id);


    @Query(value = "SELECT * FROM pages WHERE  name LIKE %:namePage% ",nativeQuery = true)
    public List<PageEntity> searchPage(@Param("namePage") String namePage);
    
}
