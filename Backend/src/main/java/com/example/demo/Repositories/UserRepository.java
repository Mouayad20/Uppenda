package com.example.demo.Repositories;


import com.example.demo.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public List<UserEntity> findByFirstName(String firstName);

    public Optional<UserEntity> findByEmail(String email);

    @Query(value = "SELECT saved_post_id FROM post_savers WHERE savers_id=:u_id", nativeQuery = true)
    public List<Long> getALlSavedPost(@Param("u_id") Long u_id);

    @Query(value = "SELECT shared_post_id FROM post_participants WHERE participants_id=:u_id", nativeQuery = true)
    public List<Long> getALlSharedPost(@Param("u_id") Long u_id);

    @Query(value = "SELECT * FROM users WHERE  first_name LIKE %:name% OR  last_name LIKE %:name%   ", nativeQuery = true)
    public List<UserEntity> searchUser(@Param("name") String name);

    @Query(value = "SELECT * FROM users WHERE email=:email", nativeQuery = true)
    UserEntity getByEmail(@Param("email") String email);

    Optional<UserEntity> findByEmailAndPassword(String email, String password);

    Optional<UserEntity> findByPassword(String password);

}
