package com.example.demo.Converters;

import com.example.demo.Entities.MessageEntity;
import com.example.demo.Models.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageConverter {

    @Autowired
    private UserConverter userConverter;
    @Autowired
    private ChatConverter chatConverter;

    public MessageModel messageEntityToModel(MessageEntity messageEntity, boolean withChats) {
        MessageModel messageModel = new MessageModel();
        messageModel.setId(messageEntity.getId());
        messageModel.setContent(messageEntity.getContent());
        messageModel.setDateOfSent(messageEntity.getDateOfSent());
        messageModel.setS_id(messageEntity.getSender().getId());
        if (withChats)
            messageModel.setChatModel(chatConverter.chatEntityToModel(messageEntity.getChatEntity()));
        messageModel.setSender(userConverter.getUserModelWithBasicInformation(messageEntity.getSender()));
        return messageModel;

    }

    public MessageEntity messageModelToEntity(MessageModel messageModel) {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setId(messageModel.getId());
        messageEntity.setContent(messageModel.getContent());
        messageEntity.setDateOfSent(messageModel.getDateOfSent());
        if (messageModel.getChatModel() != null)
            messageEntity.setChatEntity(chatConverter.chatModelToEntity(messageModel.getChatModel()));
        messageEntity.setSender(userConverter.convertUserModelToUserEntity(messageModel.getSender(), false));
        return messageEntity;
    }

    public List<MessageModel> messageEntityListToModelList(List<MessageEntity> messageEntity, boolean withChats) {
        List<MessageModel> list = new ArrayList<>();
        // System.out.println("\nmessageEntityListToModleList\n");
        if (messageEntity != null) {
            for (MessageEntity message : messageEntity) {
                // System.out.println("\nmessage == \n"+message.getContent()+"\n__________\n");
                list.add(messageEntityToModel(message, withChats));
            }
        }
        return list;
    }

    public List<MessageModel> messageEntityIterableToModelList(Iterable<MessageEntity> messageEntity) {
        List<MessageModel> list = new ArrayList<>();
        if (messageEntity != null) {
            for (MessageEntity message : messageEntity) {
                list.add(messageEntityToModel(message, false));
            }
        }
        return list;
    }

    public List<MessageEntity> messageModelListToEntityList(List<MessageModel> messageModel) {
        List<MessageEntity> list = new ArrayList<>();
        if (messageModel != null) {
            for (MessageModel message : messageModel) {
                list.add(messageModelToEntity(message));
            }
        }
        return list;
    }

}
