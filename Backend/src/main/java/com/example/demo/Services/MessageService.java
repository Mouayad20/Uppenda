package com.example.demo.Services;

import com.example.demo.Converters.MessageConverter;
import com.example.demo.Entities.MessageEntity;
import com.example.demo.Models.MessageModel;
import com.example.demo.Repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MessageConverter messageConverter;

    public MessageModel update(MessageModel messageModel) {
        MessageEntity messageEntity = messageRepository.findById(messageModel.getId()).get();
        messageEntity.setContent(messageModel.getContent());
        messageEntity.setDateOfSent(messageModel.getDateOfSent());
        return messageConverter.messageEntityToModel(messageRepository.save(messageEntity), false);
    }

    public ResponseEntity<String> delete(Long id) {
        messageRepository.deleteById(id);
        if (!messageRepository.findById(id).isPresent())
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Message deleted successfully ");
        else
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Message not deleted, problem happened");
    }

    public List<MessageModel> getChatMessage(Long chat_id) {
        return messageConverter.messageEntityListToModelList(messageRepository.getChatMessage(chat_id), false);
    }

}