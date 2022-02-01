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

    public ChatEntity chatModelToEntity(ChatModel chatModel) {
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setId(chatModel.getId());
        chatEntity.setTittleGroup(chatModel.getTittleGroup());
        chatEntity.setImageGroup(chatModel.getImageGroup());
        if (chatModel.getUsers() != null) {
            for (int i = 0; i < chatModel.getUsers().size(); i++) {
                chatEntity.getUsers().add(userConverter.convertUserModelToUserEntity(chatModel.getUsers().get(i), true));
            }
        } else
            chatEntity.setUsers(new ArrayList<>());

        if (chatModel.getMessages() != null) {
            for (int i = 0; i < chatModel.getMessages().size(); i++) {
                chatEntity.getMessages().add(messageConverter.messageModelToEntity(chatModel.getMessages().get(i)));
            }
        } else
            chatEntity.setMessages(new ArrayList<>());
        if (chatModel.getUsersHiddenChats() != null) {
            for (int i = 0; i < chatModel.getUsers().size(); i++) {
                chatEntity.getUsersHiddenChats()
                        .add(userConverter.convertUserModelToUserEntity(chatModel.getUsersHiddenChats().get(i), true));
            }
        } else
            chatEntity.setUsersHiddenChats(new ArrayList<>());
        return chatEntity;
    }

    public ChatModel chatEntityToModel(ChatEntity chatEntity, boolean withUsers, boolean withMessage) {

        ChatModel chatModel = new ChatModel();
        chatModel.setId(chatEntity.getId());
        chatModel.setTittleGroup(chatEntity.getTittleGroup());
        chatModel.setImageGroup(chatEntity.getImageGroup());
        if (withMessage) {
            // System.out.println("\n__________\n" +
            // messageEntityListToModleList(chatEntity.getMessages(), false) +
            // "\n__________\n");
            chatModel.setMessages(messageConverter.messageEntityListToModelList(chatEntity.getMessages(), false));
        }
        if (withUsers) {
            chatModel.setUsers(userConverter.convertUserListEntityToListModel(chatEntity.getUsers()));
            chatModel.setUsersHiddenChats(userConverter.convertUserListEntityToListModel(chatEntity.getUsersHiddenChats()));
        }

        return chatModel;

    }

    public List<ChatModel> chatIterableEntityToListModel(Iterable<ChatEntity> chatEntities) {
        List<ChatModel> chatModels = new ArrayList<>();
        if (chatEntities != null) {
            for (ChatEntity chatEntity : chatEntities) {
                chatModels.add(chatEntityToModel(chatEntity, false, true));
            }
        }
        return chatModels;
    }

    public List<ChatModel> chatListEntityToListModel(List<ChatEntity> chatEntities, boolean withUsers) {
        List<ChatModel> chatModels = new ArrayList<>();
        if (chatEntities != null) {
            for (ChatEntity chatEntity : chatEntities) {
                chatModels.add(chatEntityToModel(chatEntity, withUsers, true));
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
