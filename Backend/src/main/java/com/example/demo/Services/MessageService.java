package com.example.demo.Services;

import com.example.demo.Converters.MessageConverter;
import com.example.demo.Entities.MessageEntity;
import com.example.demo.Models.MessageModel;
import com.example.demo.Repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public String delete(Long id) {
        messageRepository.deleteById(id);
        if (messageRepository.findById(id).isPresent()) return "message deleted falied";
        else return "message deleted succssfly";
    }

    public List<MessageModel> getAllMessages() {
        return messageConverter.messageEntityIterableToModelList(messageRepository.findAll());
    }

    public MessageModel getLastMessage(Long chat_id) {
        return getAllMessageInSpecificChat(chat_id).get(0);
    }

    public List<MessageModel> getAllMessageInSpecificChat(Long chat_id) {
        if (messageRepository.getAllMessageInSpecificChat(chat_id) == null) {
            return new ArrayList<MessageModel>();
        } else {
            return messageConverter.messageEntityListToModelList(messageRepository.getAllMessageInSpecificChat(chat_id), false);
        }
    }

    public List<MessageModel> getAllMessageForSpecificUser(Long c_id, Long s_id) {
        return messageConverter.messageEntityListToModelList(messageRepository.getAllMessagesByUserId(c_id, s_id), false);
    }

    public MessageModel saveMessage(MessageModel messageModel) {
        return messageConverter.messageEntityToModel(messageRepository.save(messageConverter.messageModelToEntity(messageModel)), false);
    }

}

/*
    public MessageModel saveMessage(MessageModel messageModel , Long sender_id , Long reciver_id){

        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setContent(messageModel.getContent());
        messageEntity.setDateOfSent(messageModel.getDateOfSent());
        messageEntity.setSender(userRepository.findById(sender_id).get());



        MessageEntity messageEntityForMe = new MessageEntity();

        messageEntityForMe.setChatEntity(chatRepository.getChat(sender_id, reciver_id));
        messageEntityForMe.setSender    (userRepository.findById(sender_id).get());
        messageEntityForMe.setContent   (messageModel  .getContent   ());
        messageEntityForMe.setDateOfSent(messageModel  .getDateOfSent());

        MessageModel savedMessage = messageConverter.messageEntityToModle(messageRepository.save(messageEntityForMe));

        MessageEntity messageEntityForHe = new MessageEntity();
        messageEntityForHe.setChatEntity(chatRepository.getChat( reciver_id,sender_id));
        messageEntityForHe.setSender    (userRepository.findById(sender_id).get());
        messageEntityForHe.setContent   (messageModel  .getContent   ());
        messageEntityForHe.setDateOfSent(messageModel  .getDateOfSent());

        messageRepository.save(messageEntityForHe);

        return savedMessage;

 */