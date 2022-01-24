package com.example.demo.Controllers;

import java.util.List;

import com.example.demo.Entities.ChatEntity;
import com.example.demo.Models.ChatModel;
import com.example.demo.Repositories.ChatRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.services.ChatService;

import com.example.demo.services.FormatFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/chat")
public class ChatController {

    @Autowired 
    ChatService chatService;
    @Autowired
    ChatRepository chatRepository;
    @Autowired
    UserRepository userRepository;


    @GetMapping("/test1")
    public ChatEntity test1(){
        return new ChatEntity();
    }

    @PostMapping("/add")
    public ChatModel addChat(@RequestBody ChatModel chatModel){
        System.out.println("\n\n________________________\n\n");
        System.out.println("titlte  "+chatModel.getTittleGroup());
        System.out.println("image   "+chatModel.getImageGroup());
        System.out.println("last    "+chatModel.getLastMessage());
        System.out.println("rec     "+chatModel.getReceiver());
        System.out.println("mess  si    "+chatModel.getMessages().size());
        System.out.println("users si    "+chatModel.getUsers().size());
        // System.out.println("users hsi   "+chatModel.getUsersHiddenChats().size());
        System.out.println("\n\n________________________\n\n");
        return chatService.addChat(chatModel);
        // return new ChatModel();
    }

    @PostMapping("/addMember/user_id={user_id},chat_id={chat_id}")
    public boolean addChat(@PathVariable(name = "user_id")Long user_id,@PathVariable(name = "chat_id")Long chat_id){
        return chatService.addMemberToChat(user_id,chat_id);
    }

    @PostMapping("/hiddenChat/user_id={user_id},chat_id={chat_id}")
    public boolean hiddenChat(@PathVariable(name = "user_id")Long user_id,@PathVariable(name = "chat_id")Long chat_id){
        return chatService.hiddenChat(user_id,chat_id);
    }

    @GetMapping("/getAllChatByUserID/user_id={user_id}")
    public List<ChatModel> getAllChatByUID(@PathVariable(name = "user_id")Long user_id){
        return chatService.getALlChatByUserId(user_id);
    }

    @GetMapping("/getAllChats")
    public Iterable<ChatEntity> getAllChats(){
        return chatRepository.findAll();
    }

    @GetMapping("/getChatById/chat_id={c_id}")
    public ChatModel getChatById(@PathVariable(name = "c_id")Long c_id){
        return new FormatFactory().chatEntityToModel( chatRepository.findById(c_id).get(),true,true);
    }

//    @DeleteMapping("/deleteById/chat_id={chat_id}")
//    public void deleteChatById(@PathVariable(name = "chat_id")Long chat_id){
//         chatRepository.deleteById(chat_id);
//    }



    ///////////// delete this code when you finish
/*

//    @GetMapping("/getAllChatsByUID/meId={me_id}")
//    public List<ChatModel> getAllChatByUID(@PathVariable(name = "me_id")Long me_id){
//        return chatService.getAllByUID(me_id);
//    }
    
//    @GetMapping("/getAll")
//    public List<ChatModel> getAll(){
//        return chatService.getAll();
//    }

//    @DeleteMapping("/delete/SenderID={s_id},ReciverID={r_id}")
//    public String delete(@PathVariable(name = "s_id")Long s_id,@PathVariable(name = "r_id")Long r_id){
//        return chatService.deleteChat(s_id,r_id);
//    }

//    @GetMapping("/getALl")
//    public Iterable<ChatEntity> getall(){
//        return chatRepository.findAll();
//    }

//    @GetMapping("/getListChatModelByUID/meId={me_id}")
//    public List<ChatModel> getListChatModel(@PathVariable(name = "me_id") Long me_id){
//
//    }



 */
}
