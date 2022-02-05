package com.example.demo.Controllers;


import com.example.demo.Models.MessageModel;
import com.example.demo.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    /* Put Request */

    @PutMapping("/update")
    public MessageModel update(@RequestBody MessageModel messageModel) {
        return messageService.update(messageModel);
    }

    /* Delete Request */

    @DeleteMapping("deleteById/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        return messageService.delete(id);
    }

    /* Get Request */

    @GetMapping("/getFormat")
    public MessageModel test() {
        return new MessageModel();
    }

    @GetMapping("/getALl")
    public List<MessageModel> getAll() {
        return messageService.getAllMessages();
    }

    @GetMapping("/getAllMessageBy/CID={c_id},SID={s_id}")
    ///// الاندكس صفر فيو اجدد رسالة انبعتت (اخر رسالة يعني ) وبرد الليست من الجديد للقديم
    public List<MessageModel> getAllMessages(@PathVariable(name = "c_id") Long c_id, @PathVariable(name = "s_id") Long s_id) {
        return messageService.getAllMessageForSpecifacUser(c_id, s_id);
    }

    @GetMapping("/getLastMessage/chatId={c_id}")
    public MessageModel getLastMessage(@PathVariable(name = "c_id") Long c_id) {
        return messageService.getLastMessage(c_id);
    }

    @GetMapping("/getAllMessageFromChat/chat_id={c_id}")
    public List<MessageModel> getAllMessageFromChat(@PathVariable(name = "c_id") Long c_id) {
        return messageService.getAllMessageInSpecificeChat(c_id);
    }
}
//    @PostMapping("/sendMessage/senderId={s_id},reciverId={r_id}")
//    public MessageModel sendMessage(@RequestBody MessageModel messageModel,
//    @PathVariable(name = "s_id")Long sender_id,@PathVariable(name = "r_id")Long reciver_id){
//        return messageService.saveMessage(messageModel, sender_id, reciver_id);
//    }