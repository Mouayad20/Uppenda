package com.example.demo.Services;

import com.example.demo.Converters.ChatConverter;
import com.example.demo.Entities.ChatEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Models.ChatModel;
import com.example.demo.Repositories.ChatRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Security.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageService messageService;
    @Autowired
    private ChatConverter chatConverter;
    @Autowired
    private TokenUtil tokenUtil;

    public ResponseEntity<String> add(ChatModel chatModel) {

        ChatEntity chatEntity = chatRepository.save(chatConverter.chatModelToEntity(chatModel));

        if (chatRepository.findById(chatEntity.getId()).isPresent())
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Chat created successfully");
        else
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Problem happened");

    }

    public ResponseEntity<String> hiddenChat(String token, Long chat_id) {

        UserEntity userEntity = userRepository.findByEmail(tokenUtil.getEmailFromToken(token)).get();
        ChatEntity chatEntity = chatRepository.findById(chat_id).get();

        if (chatEntity.getUser1().getId() == userEntity.getId())
            chatEntity.setHidden1(true);
        else
            chatEntity.setHidden2(true);

        chatEntity = chatRepository.save(chatEntity);

        if (chatEntity.isHidden1() && chatEntity.isHidden2()) {
            chatRepository.deleteById(chat_id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Chat hidden successfully");
        } else if (chatEntity.isHidden1() || chatEntity.isHidden2())
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Chat hidden successfully");
        else
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Problem happened");

    }

    public List<ChatModel> getUsersChats(String token) {
        return chatConverter.chatListEntityToListModel(
                chatRepository.findByUser1(
                        userRepository.findByEmail(
                                tokenUtil.getEmailFromToken(token)
                        ).get()
                )
        );
    }

    public ChatModel getChatById(Long chat_id) {
        return chatConverter.chatEntityToModel(chatRepository.findById(chat_id).get());
    }

}