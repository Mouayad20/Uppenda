package com.example.demo.Controllers;

import com.example.demo.Converters.ChatConverter;
import com.example.demo.Entities.ChatEntity;
import com.example.demo.Models.ChatModel;
import com.example.demo.Repositories.ChatRepository;
import com.example.demo.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/chat")
public class ChatController {

    @Autowired
    ChatService chatService;
    @Autowired
    ChatRepository chatRepository;

    /* Post Request */

    @PostMapping("/add")
    public ChatModel addChat(@RequestBody ChatModel chatModel) {
        return chatService.addChat(chatModel);
    }

    @PostMapping("/addMember/user_id={user_id},chat_id={chat_id}")
    public boolean addChat(@PathVariable(name = "user_id") Long user_id, @PathVariable(name = "chat_id") Long chat_id) {
        return chatService.addMemberToChat(user_id, chat_id);
    }

    @PostMapping("/hiddenChat/user_id={user_id},chat_id={chat_id}")
    public boolean hiddenChat(@PathVariable(name = "user_id") Long user_id, @PathVariable(name = "chat_id") Long chat_id) {
        return chatService.hiddenChat(user_id, chat_id);
    }

    /* Get Request */

    @GetMapping("/getFormat")
    public ChatEntity getFormat() {
        return new ChatEntity();
    }

    @GetMapping("/getAllChatByUserID/user_id={user_id}")
    public List<ChatModel> getAllChatByUID(@PathVariable(name = "user_id") Long user_id) {
        return chatService.getALlChatByUserId(user_id);
    }

    @GetMapping("/getAllChats")
    public Iterable<ChatEntity> getAllChats() {
        return chatRepository.findAll();
    }

    @GetMapping("/getChatById/chat_id={c_id}")
    public ChatModel getChatById(@PathVariable(name = "c_id") Long c_id) {
        return new ChatConverter().chatEntityToModel(chatRepository.findById(c_id).get(), true, true);
    }
}
