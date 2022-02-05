package com.example.demo.Controllers;

import com.example.demo.Models.ChatModel;
import com.example.demo.Services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    /* Post Request */

    @PostMapping("/add")
    public ChatModel add(@RequestBody ChatModel chatModel) {
        return chatService.add(chatModel);
    }

    @PostMapping("/addMember/user_id={user_id},chat_id={chat_id}")
    public boolean addMember(@PathVariable(name = "user_id") Long user_id, @PathVariable(name = "chat_id") Long chat_id) {
        return chatService.addMember(user_id, chat_id);
    }

    @PostMapping("/hiddenChat/user_id={user_id},chat_id={chat_id}")
    public boolean hiddenChat(@PathVariable(name = "user_id") Long user_id, @PathVariable(name = "chat_id") Long chat_id) {
        return chatService.hiddenChat(user_id, chat_id);
    }

    /* Get Request */

    @GetMapping("/getFormat")
    public ChatModel getFormat() {
        return new ChatModel();
    }

    @GetMapping("/getAllChatByUserID/user_id={user_id}")
    public List<ChatModel> getAllChatByUID(@PathVariable(name = "user_id") Long user_id) {
        return chatService.getALlChatByUserId(user_id);
    }

    @GetMapping("/getChatById/chat_id={c_id}")
    public ChatModel getChatById(@PathVariable(name = "c_id") Long c_id) {
        return chatService.getChatById(c_id);
    }
}
