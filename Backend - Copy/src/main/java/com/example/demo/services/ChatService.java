package com.example.demo.services;

import java.util.Date;
import java.util.List;

import com.example.demo.Entities.ChatEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Models.ChatModel;
import com.example.demo.Models.MessageModel;
import com.example.demo.Repositories.ChatRepository;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    @Autowired
    ChatRepository chatRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MessageService messageService;
    @Autowired
    FormatFactory formatFactory;

    public ChatModel addChat(ChatModel chatModel) {

        boolean isSoso = false;

        ChatEntity chatEntity = new ChatEntity();
        ChatEntity savedChat;

        chatEntity = formatFactory.chatModelToEntity(chatModel);

        for (ChatEntity chatEntity2 : chatRepository.findAll()) {
            for (int i = 0; i < chatEntity2.getUsers().size(); i++) {
                if (chatEntity2.getUsers().get(i).getId() == chatModel.getUsers().get(i).getId()) {
                    isSoso = true;
                } else {
                    isSoso = false;
                }
            }
        }
        if (isSoso) {
            return new ChatModel();
        } else
            savedChat = chatRepository.save(chatEntity);

        for (int i = 0; i < savedChat.getUsers().size(); i++) {
            userRepository.findById(savedChat.getUsers().get(i).getId()).get().getChats().add(savedChat);
            userRepository.save(userRepository.findById(savedChat.getUsers().get(i).getId()).get());
        }

        if (chatRepository.findById(savedChat.getId()).isPresent())
            return formatFactory.chatEntityToModel(savedChat, true, true);
        else
            return new ChatModel();

    }

    public boolean addMemberToChat(Long user_id, Long chat_id) {
        UserEntity userEntity = userRepository.findById(user_id).get();
        ChatEntity chatEntity = chatRepository.findById(chat_id).get();

        userEntity.getChats().add(chatEntity);
        chatEntity.getUsers().add(userEntity);

        UserEntity savedUser = userRepository.save(userEntity);
        ChatEntity savedChat = chatRepository.save(chatEntity);

        if (userRepository.findById(savedUser.getId()).isPresent()
                && chatRepository.findById(savedChat.getId()).isPresent())
            return true;
        else
            return false;
    }

    public boolean hiddenChat(Long user_id, Long chat_id) {

        UserEntity userEntity = userRepository.findById(user_id).get();
        ChatEntity chatEntity = chatRepository.findById(chat_id).get();

        userEntity.getHiddenChats().add(chatEntity);
        chatEntity.getUsersHiddenChats().add(userEntity);

        UserEntity savedUser = userRepository.save(userEntity);
        ChatEntity savedChat = chatRepository.save(chatEntity);

        if (userRepository.findById(savedUser.getId()).isPresent()
                && chatRepository.findById(savedChat.getId()).isPresent())
            return true;
        else
            return false;

    }

    public List<ChatModel> getALlChatByUserId(Long user_id) {
        List<ChatModel> list = formatFactory
                .chatListEntityToListModel(userRepository.findById(user_id).get().getChats(), true);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMessages().size() == 0) {
                list.get(i).setLastMessage(new MessageModel("there is no message yet", new Date()));
            } else {
                System.out.println(messageService.getLastMessage(list.get(i).getId()));
                list.get(i).setLastMessage(messageService.getLastMessage(list.get(i).getId()));
            }
            if (list.get(i).getUsers().size() == 2 && list.get(i).getTittleGroup() == null) {
                for (int j = 0; j < list.get(i).getUsers().size(); j++) {
                    if (list.get(i).getUsers().get(j).getId() != user_id)
                        list.get(i).setReceiver(list.get(i).getUsers().get(j));
                }
            }
        }
        return list;
    }

    // delete this code when you finish

    // public List<ChatModel> getAllChatByUserId(Long user_id){
    //
    // }

    // public List<ChatModel> getAllByUID(Long me_id){
    // List<ChatModel> list = new ArrayList<>();
    // for (ChatEntity chatEntity : chatRepository.getChatByUserId(me_id)) {
    // ChatModel chatModel = new ChatModel();
    //// chatModel.setReceiver(formatFactory.userEntityToModel(chatEntity.getHeEntity()));
    // chatModel.setLastMessage(messageService.getLastMessage(chatEntity.getId())==null?"there
    // is no messgaes
    // yet":messageService.getLastMessage(chatEntity.getId()).getContent());
    // chatModel.setDateOfLastMessage(messageService.getLastMessage(chatEntity.getId())==null?"there
    // is no messgaes
    // yet":messageService.getLastMessage(chatEntity.getId()).getDateOfSent().toString());
    //
    // list.add(chatModel);
    // }
    //
    // return list;
    // }

    // public String deleteById(Long c_id,Long u_id){
    //
    // String isPresent = "deleted";
    //
    // UserEntity userEntity = userRepository.findById(u_id).get();
    //
    // System.out.println("\n\n\n---------1---------\n\n\t\t\t\t"+userEntity.getChats()+"\n\n\n*****************\n\n");
    // System.out.println("\n\n\n---------2---------\n\n\t\t\t\t"+chatRepository.findById(c_id).get()+"\n\n\n*****************\n\n");
    //
    // userEntity.getChats().remove(chatRepository.findById(c_id).get());
    //
    // System.out.println("\n\n\n\n\n\t\t\t\t"+userEntity.getChats()+"\n\n\n*****************\n\n");
    // UserEntity savedUser = userRepository.save(userEntity);
    //
    // System.out.println("\n\n\n\n\n\t\t\t\t"+savedUser.getName()+"\n\n\n");
    // System.out.println("\n\n\n\n\n\t\t\t\t"+savedUser.getChats()+"\n\n\n");
    // for (ChatEntity chatEntity : savedUser.getChats()) {
    // if (chatEntity.getId() == c_id)
    // isPresent = "not deleted" ;
    // }
    // return isPresent;
    // }
    //
    // public String deleteChat(Long s_id, Long r_id) {
    // Long chat_id = chatRepository.getChat(s_id, r_id).getId();
    // chatRepository.delete(chatRepository.getChat(s_id, r_id));
    // if (chatRepository.findById(chat_id).isPresent()) return "deleted chat falied
    // ";
    // else return "deleted chat sucsfuly";
    // }
    //
    // public ChatEntity findById(Long id){
    // return chatRepository.findById(id).get();
    // }
    //
    // public ChatEntity getChat(Long s_id,Long r_id) {
    // return chatRepository.getChat(s_id,r_id);
    // }
    //

}
