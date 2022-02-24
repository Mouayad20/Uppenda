package com.example.demo.Converters;

import com.example.demo.Entities.ChatEntity;
import com.example.demo.Models.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatConverter {

    @Autowired
    private UserConverter userConverter;
    @Autowired
    private MessageConverter messageConverter;
    @Autowired
    private GroupConverter groupConverter;

    public ChatEntity chatModelToEntity(ChatModel chatModel) {
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setHidden1(chatModel.isHidden1());
        chatEntity.setHidden2(chatModel.isHidden2());
        if (chatModel.getId() != null)
            chatEntity.setId(chatModel.getId());
        if (chatModel.getGroupModel() != null)
            chatEntity.setGroupEntity(groupConverter.convertGroupModelToGroupEntity(chatModel.getGroupModel()));
        if (chatModel.getMessages() != null)
            chatEntity.setMessages(messageConverter.messageModelListToEntityList(chatModel.getMessages()));
        if (chatModel.getUser1() != null)
            chatEntity.setUser1(userConverter.convertUserModelToUserEntity(chatModel.getUser1(), true));
        if (chatModel.getUser2() != null)
            chatEntity.setUser2(userConverter.convertUserModelToUserEntity(chatModel.getUser2(), true));

        return chatEntity;
    }

    public ChatModel chatEntityToModel(ChatEntity chatEntity) {

        ChatModel chatModel = new ChatModel();
        chatModel.setHidden1(chatEntity.isHidden1());
        chatModel.setHidden2(chatEntity.isHidden2());
        if (chatEntity.getId() != null)
            chatModel.setId(chatEntity.getId());
        if (chatEntity.getGroupEntity() != null)
            chatModel.setGroupModel(groupConverter.convertGroupEntityToGroupModel(chatEntity.getGroupEntity()));
        if (chatEntity.getMessages() != null)
            chatModel.setMessages(messageConverter.messageEntityListToModelList(chatEntity.getMessages(), false));
        if (chatEntity.getUser1() != null)
            chatModel.setUser1(userConverter.convertUserEntityToUserModel(chatEntity.getUser1()));
        if (chatEntity.getUser2() != null)
            chatModel.setUser2(userConverter.convertUserEntityToUserModel(chatEntity.getUser2()));

        return chatModel;

    }

    public List<ChatModel> chatIterableEntityToListModel(Iterable<ChatEntity> chatEntities) {
        List<ChatModel> chatModels = new ArrayList<>();
        if (chatEntities != null) {
            for (ChatEntity chatEntity : chatEntities) {
                chatModels.add(chatEntityToModel(chatEntity));
            }
        }
        return chatModels;
    }

    public List<ChatModel> chatListEntityToListModel(List<ChatEntity> chatEntities) {
        List<ChatModel> chatModels = new ArrayList<>();
        if (chatEntities != null) {
            for (ChatEntity chatEntity : chatEntities) {
                chatModels.add(chatEntityToModel(chatEntity));
            }
        }
        return chatModels;
    }

    public List<ChatEntity> chatListModelToListEntity(List<ChatModel> chatModels) {
        List<ChatEntity> chatEntities = new ArrayList<>();
        if (chatModels != null) {
            for (ChatModel chatModel : chatModels) {
                chatEntities.add(chatModelToEntity(chatModel));
            }
        }
        return chatEntities;
    }

}
