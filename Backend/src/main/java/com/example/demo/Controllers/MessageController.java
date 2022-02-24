package com.example.demo.Controllers;

import com.example.demo.Models.MessageModel;
import com.example.demo.Repositories.MessageRepository;
import com.example.demo.Services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/message")
public class MessageController {

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    private MessageService messageService;

    /* Put Request */

    @PutMapping("/update")
    public MessageModel update(@RequestBody MessageModel messageModel) {
        return messageService.update(messageModel);
    }

    /* Delete Request */

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
        return messageService.delete(id);
    }

    /* Get Request */

    @GetMapping("/getFormat")
    public MessageModel getFormat() {
        return new MessageModel();
    }

    @GetMapping("/getChat\'sMessages/chat_id={chat_id}")
    public List<MessageModel> getChatMessage(@PathVariable(name = "chat_id") Long chat_id) {
        return messageService.getChatMessage(chat_id);
    }

}