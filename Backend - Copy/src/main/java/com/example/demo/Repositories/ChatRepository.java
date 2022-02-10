package com.example.demo.Repositories;

import java.util.List;


import com.example.demo.Entities.ChatEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository

public interface ChatRepository extends CrudRepository<ChatEntity,Long> {


    @Query(value = "SELECT users_id FROM users_chats WHERE chats_id=:c_id ",nativeQuery = true)
    public List<Long> getIDsUsersInChatByCID(@Param("c_id")Long c_id);
    
//    @Query(value = "SELECT * FROM chats WHERE me_id = :id ",nativeQuery = true)
//    List<ChatEntity> getChatByUserId( @Param("id")Long id );
//
//    @Query(value = "SELECT * FROM chats WHERE me_id=:m_id AND he_id=:h_id",nativeQuery = true)
//    ChatEntity getChat(@Param("m_id")Long x1,@Param("h_id")Long x2);


}
