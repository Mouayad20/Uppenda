package com.example.demo.Repositories;


import java.util.List;
import java.util.Optional;

import com.example.demo.Entities.UserEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long> {

  
   public List<UserEntity> findByfirstName(String firstName);

   public Optional<UserEntity> findByEmail(String email);

   @Query(value = "SELECT saved_post_id FROM users_saved_post WHERE savers_id=:u_id",nativeQuery = true)
   public List<Long> getALlSavedPost(@Param("u_id") Long u_id);

   @Query(value = "SELECT * FROM users WHERE  first_name LIKE %:name% OR  last_name LIKE %:name%   ",nativeQuery = true)
   public List<UserEntity> searchUser(@Param("name") String name);

   
   @Query(value = "SELECT shared_post_id FROM users_shared_post WHERE participants_id=:u_id",nativeQuery = true)
   public List<Long> getALlSharedPost(@Param("u_id") Long u_id);

   @Query(value = "SELECT * FROM users WHERE email=:email",nativeQuery = true)
   UserEntity getByEmail(@Param("email")String email);

   Optional<UserEntity> findByEmailAndPassword(String email,String password);
}
