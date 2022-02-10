package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.Entities.MessageEntity;
import com.example.demo.Models.MessageModel;
import com.example.demo.Repositories.ChatRepository;
import com.example.demo.Repositories.MessageRepository;
import com.example.demo.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService{

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ChatRepository chatRepository ;
    @Autowired
    FormatFactory formatFactory;

    public MessageModel saveMessage(MessageModel messageModel ){

        return formatFactory.messageEntityToModle( messageRepository.save(formatFactory.messageModleToEntity(messageModel)),false);


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

        MessageModel savedMessage = formatFactory.messageEntityToModle(messageRepository.save(messageEntityForMe));

        MessageEntity messageEntityForHe = new MessageEntity();
        messageEntityForHe.setChatEntity(chatRepository.getChat( reciver_id,sender_id));
        messageEntityForHe.setSender    (userRepository.findById(sender_id).get());
        messageEntityForHe.setContent   (messageModel  .getContent   ());
        messageEntityForHe.setDateOfSent(messageModel  .getDateOfSent());

        messageRepository.save(messageEntityForHe);

        return savedMessage;

 */
    }

    /***
     *
     * @param c_id  chat ID
     * @return
     */
    public List<MessageModel> getAllMessageForSpecifacUser(Long c_id ,Long s_id ){

        return formatFactory.messageEntityListToModleList(messageRepository.getAllMessagesByUserId(c_id,s_id),false);
    }

    public List<MessageModel> getAllMessageInSpecificeChat(Long chat_id){
        if (messageRepository.getAllMessageInSpecificChat(chat_id) == null ){
            return new ArrayList<MessageModel>();
        }
        else{
            return formatFactory.messageEntityListToModleList(messageRepository.getAllMessageInSpecificChat(chat_id),false);
        }
    }

    public List<MessageModel> getAllMessages(){
        return formatFactory.messageEntityIterableToModleList(messageRepository.findAll());
    }

    public MessageModel update(MessageModel messageModel) {
        MessageEntity messageEntity = messageRepository.findById(messageModel.getId()).get();
        messageEntity.setContent(messageModel.getContent());
        messageEntity.setDateOfSent(messageModel.getDateOfSent());
        return formatFactory.messageEntityToModle(messageRepository.save(messageEntity),false);
    }

    public String delete(Long id) {
        messageRepository.deleteById(id);
        if(messageRepository.findById(id).isPresent())return "message deleted falied";
        else return "message deleted succssfly";
    }


    public MessageModel getLastMessage( Long chat_id) {
        return  getAllMessageInSpecificeChat(chat_id).get(0);
    }
     


    



}