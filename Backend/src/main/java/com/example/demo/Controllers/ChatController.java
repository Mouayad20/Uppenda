package com.example.demo.Controllers;

import com.example.demo.Converters.ChatConverter;
import com.example.demo.Models.ChatModel;
import com.example.demo.Repositories.ChatRepository;
import com.example.demo.Services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    /* Post Request */

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody ChatModel chatModel) {
        return chatService.add(chatModel);
    }

    @PostMapping("/hideChat/chat_id={chat_id}")
    public ResponseEntity<String> hiddenChat(@RequestHeader("Authorization") String token,
                                             @PathVariable(name = "chat_id") Long chat_id) {
        return chatService.hiddenChat(token.substring("Bearer ".length()), chat_id);
    }

    /* Get Request */

    @GetMapping("/getFormat")
    public ChatModel getFormat() {
        return new ChatModel();
    }

    @GetMapping("/getUser\'sChats")
    public List<ChatModel> getUsersChats(@RequestHeader("Authorization") String token) {
        return chatService.getUsersChats(token.substring("Bearer ".length()));
    }

    @GetMapping("/getChat/chat_id={chat_id}")
    public ChatModel getChatById(@PathVariable(name = "chat_id") Long chat_id) {
        return chatService.getChatById(chat_id);
    }
}
