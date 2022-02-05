package com.example.demo.Repositories;

import com.example.demo.Entities.MessageEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<MessageEntity, Long> {

    //    @Query(value = "SELECT * FROM messages WHERE chat_id=:c_id AND sender_id=:s_id ORDER BY `messages`.`date_of_sent` DESC ",nativeQuery = true)
    @Query(value = "SELECT * FROM messages WHERE chat_id=:c_id AND sender_id=:s_id  ORDER BY `messages`.`date_of_sent` DESC ", nativeQuery = true)
    List<MessageEntity> getAllMessagesByUserId(@Param("c_id") Long c_id, @Param("s_id") Long s_id);

    // @Query(value = "SELECT TOP * FROM messages WHERE chat_id=:c_id AND sender_id=:x_id")
    // MessageEntity getLatestMessage(Long x_id, Long c_id);

    @Query(value = "SELECT * FROM messages WHERE chat_id=:c_id ORDER BY `messages`.`date_of_sent` DESC", nativeQuery = true)
    public List<MessageEntity> getAllMessageInSpecificChat(@Param("c_id") Long c_id);


}
