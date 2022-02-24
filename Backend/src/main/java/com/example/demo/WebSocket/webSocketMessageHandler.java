package com.example.demo.WebSocket;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.Converters.MessageConverter;
import com.example.demo.Entities.MessageEntity;
import com.example.demo.Models.MessageModel;
import com.example.demo.Repositories.ChatRepository;
import com.example.demo.Repositories.MessageRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Services.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class webSocketMessageHandler extends TextWebSocketHandler {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MessageConverter messageConverter;

    final Map<String, WebSocketSession> sessions = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String ip = session.getRemoteAddress().getAddress().toString();
        System.out.println(ip);
        sessions.put(ip, session);
        System.out.println("\n\n" + sessions + "\n\n");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//
//        ///// message.Payload() -> only these attributes is valid { "content" : hi ,
//        ///// "s_id" : 1 , "c_id" : 5 }
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        MessageModel messageModel = objectMapper.readValue(message.getPayload(), MessageModel.class);
//        MessageEntity messageEntity = new MessageEntity();
//        messageEntity.setContent(messageModel.getContent());
//        messageEntity.setDateOfSent(new Date());
//        messageEntity.setSender(userRepository.findById(messageModel.getS_id()).get());
//        messageEntity.setChatEntity(chatRepository.findById(messageModel.getC_id()).get());
//
//        System.out.println();
//        System.out.println("\tid      :\t" + messageEntity.getId());
//        System.out.println("\tcontent :\t" + messageEntity.getContent());
//        System.out.println("\tdate    :\t" + messageEntity.getDateOfSent());
//        System.out.println("\ts_id    :\t" + messageEntity.getSender().getId());
//        System.out.println("\tc_id    :\t" + messageEntity.getChatEntity().getId());
//        System.out.println();
//
//        List<Long> list = chatRepository.getIDsUsersInChatByCID(messageEntity.getChatEntity().getId());
//
//        System.out.println("\n\n######################\n\n");
//        System.out.println(list);
//        System.out.println("\n\n######################\n\n");
//
//        MessageModel savedMessage = messageConverter.messageEntityToModel(messageRepository.save(messageEntity), false);
//
//
//
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i) == savedMessage.getSender().getId())
//                continue;
//            else
//                sessions.get(userRepository.findById(list.get(i)).get().getIp())
//                        .sendMessage(new TextMessage(objectMapper.writeValueAsString(savedMessage)));
//        }
//
//        // ChatEntity chatEntity = chatRepository.findById(messageModel.getC_id()).get()
//        // ;
//
//        // ChatModel chatModel = formatFactory.chatEntityToModel(chatEntity,false) ;
//        // messageService.saveMessage(messageModel);
//
//        // List<UserModel> list = formatFactory.userEntityIterableToModelList( );
//        // System.out.println(">>>>>"+chatEntity.giveMeLength());
//        // for ( int i = 0 ; i <
//        // chatRepository.findById(messageModel.getC_id()).get().getUsers().size() ; i
//        // ++ ) {
//        // sessions.get(chatRepository.findById(messageModel.getC_id()).get().getUsers().get(i).getId()).sendMessage(new
//        // TextMessage(savedMessage.toString()));
//        // }
    }


}
