package com.example.demo.Repositories;

import com.example.demo.Entities.PageEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepository extends CrudRepository<PageEntity, Long> {

    @Query(value = "SELECT * FROM `pages` WHERE 1", nativeQuery = true)
    List<PageEntity> getAll();

    @Query(value = "SELECT * FROM pages WHERE  name LIKE %:namePage% ", nativeQuery = true)
    List<PageEntity> search(@Param("namePage") String namePage);

    //    @Query(value = "SELECT * FROM `pages` WHERE `admin_id` = :admin_id", nativeQuery = true)
    //    Optional<List<PageEntity>> getAllPagesThatUserIsAdimnIn(@Param("admin_id") Long id);

}
