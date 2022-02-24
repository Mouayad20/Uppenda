package com.example.demo.Repositories;

import com.example.demo.Entities.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    @Query(value = "SELECT * FROM messages WHERE chat_id=:chat_id ORDER BY `messages`.`date_of_sent` DESC", nativeQuery = true)
    List<MessageEntity> getChatMessage(@Param("chat_id") Long chat_id);


}
