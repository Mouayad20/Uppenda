package com.example.demo.Repositories;

import com.example.demo.Entities.PostEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepositroy extends CrudRepository<PostEntity, Long> {

    @Query(value = "SELECT * FROM post WHERE u_id=:u_id OR g_id IN (SELECT groups_id FROM users_groups WHERE members_id=:u_id) OR page_id IN (SELECT pages_id FROM users_pages WHERE members_id=:u_id) OR u_id IN (SELECT friends_id FROM users_friends WHERE user_entity_id=:u_id) ORDER BY `post`.`created_at` DESC", nativeQuery = true)
    public List<PostEntity> getSummery(@Param("u_id") Long u_id);

    @Query(value = "SELECT * FROM post WHERE u_id=:u_id", nativeQuery = true)
    public List<PostEntity> getAllPostByUserId(@Param("u_id") Long u_id);

    @Query(value = "SELECT * FROM post WHERE g_id=:g_id", nativeQuery = true)
    public List<PostEntity> getAllPostByGroupId(@Param("g_id") Long g_id);

    @Query(value = "SELECT * FROM post WHERE page_id=:p_id", nativeQuery = true)
    public List<PostEntity> getAllPostByPageId(@Param("p_id") Long p_id);

    /*
    "SELECT * FROM post WHERE u_id=:u_id OR ( u_id=:u_id AND g_id=(SELECT groups_id FROM users_groups WHERE members_id=:u_id)) OR ( u_id=:u_id AND page_id=(SELECT pages_id FROM users_pages WHERE members_id=:u_id)) ORDER BY `post`.`created_at` DESC"
     */

}
