// ignore_for_file: prefer_typing_uninitialized_variables

import 'dart:async';
import 'dart:convert';

import 'package:frontend/Controllers/MessageController.dart';
import 'package:frontend/Model/ChatModel.dart';
import 'package:frontend/Model/MessageModel.dart';
import 'package:flutter/material.dart';

import 'package:web_socket_channel/web_socket_channel.dart';

import '../Global/Global.dart';
import '../main.dart';
import 'MainPage.dart';

class ChatScreen extends StatefulWidget {
  final WebSocketChannel channel;

  ChatModel chatModel;

  ChatScreen({Key? key, required this.chatModel, required this.channel})
      : super(key: key);

  @override
  _ChatScreenState createState() => _ChatScreenState();
}

class _ChatScreenState extends State<ChatScreen> {
  MessageController messageController = MessageController();
  TextEditingController messageText = TextEditingController();
  MessageModel? messageModel;
  Future<List<dynamic>>? listBack;
  List listFront = [];
  var currentSnapShot;

  void addMessageToListFront(dynamic value) {
    // print("\n\nlololloloo\n\n");
    setState(() {
      listFront.add(value);
    });
  }

  @override
  Widget build(BuildContext context) {
    listBack =
        messageController.getAllMessagesByCID(widget.chatModel.id.toString());

    return Scaffold(
      backgroundColor: Colors.white.withOpacity(0.95),
      appBar: AppBar(
        // brightness: Brightness.dark,
        centerTitle: true,
        leading: IconButton(
          icon: const Icon(Icons.arrow_back),
          color: Colors.purple,
          onPressed: () {
            Navigator.push(
                context, MaterialPageRoute(builder: (_) => ChatPage()));
          },
        ),
        title: RichText(
          textAlign: TextAlign.center,
          text: TextSpan(
            children: [
              TextSpan(
                text: widget.chatModel.getGroupModel != null
                    ? widget.chatModel.getGroupModel.name
                    : widget.chatModel.getUser2.getFirstName +
                        " " +
                        widget.chatModel.getUser2.getLastName,
                style: const TextStyle(
                  fontFamily: 'Merienda',
                  color: Colors.purple,
                  fontSize: 20,
                  fontWeight: FontWeight.w400,
                ),
              ),
              const TextSpan(text: '\n'),
              widget.chatModel.getGroupModel.name != null
                  ? const TextSpan()
                  : widget.chatModel.getUser2.getOnLine
                      ? const TextSpan(
                          text: 'Online',
                          style: TextStyle(
                            fontFamily: 'Merienda',
                            color: Colors.purple,
                            fontSize: 11,
                            fontWeight: FontWeight.w400,
                          ),
                        )
                      : const TextSpan(
                          text: 'Offline',
                          style: TextStyle(
                            fontFamily: 'Merienda',
                            color: Colors.purple,
                            fontSize: 11,
                            fontWeight: FontWeight.w400,
                          ),
                        )
            ],
          ),
        ),
      ),
      body: Column(
        children: [
          Expanded(
            child: FutureBuilder<List>(
              future: listBack,
              builder: (context, snapshot) {
                return Container();
                // if (snapshot.data == null) return const SizedBox();
                // if (snapshot.hasError) {
                //   print(snapshot.error);
                //   return Container();
                // }
                // if (snapshot.hasData) {
                //   listFront = snapshot.data!;
                //   if (listFront.isEmpty) {
                //     return const Center(
                //       child: Text(
                //         "There are no messages yet...",
                //         style: TextStyle(
                //             letterSpacing: 1,
                //             fontFamily: 'Merienda',
                //             fontSize: 20,
                //             fontWeight: FontWeight.w500,
                //             color: Colors.purple),
                //       ),
                //     );
                //   }
                //   if (listFront.isNotEmpty) {
                //     return MessageList(
                //       list: listFront,
                //     );
                //   }
                // } else {
                //   return const Center(
                //     child: CircularProgressIndicator(),
                //   );
                // }
              },
            ),
          ),
          StreamBuilder(
            stream: widget.channel.stream,
            builder: (context, snapshot) {
              if (snapshot.hasData) {
                print(">>>>  snap  " + snapshot.hasData.toString());
                // messageModel = MessageModel.fromJson(json.decode(snapshot.data));
                if (currentSnapShot != snapshot.data) {
                  currentSnapShot = snapshot.data;
                  Timer(const Duration(microseconds: 1), () {
                    addMessageToListFront(messageModel!.toJson());
                  });

                  return Container();
                }
                return Container();
              } else {
                return Container();
              }
            },
          ),
          _sendMessageArea(),
        ],
      ),
    );
  }

  _sendMessageArea() {
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 8),
      height: 70,
      color: Colors.white,
      child: Row(
        children: <Widget>[
          IconButton(
            icon: const Icon(Icons.photo),
            iconSize: 25,
            color: Colors.purple,
            onPressed: () {},
          ),
          Expanded(
            child: TextField(
              decoration: const InputDecoration(
                hintText: 'Send a message..',
              ),
              controller: messageText,
            ),
          ),
          IconButton(
            icon: const Icon(Icons.send),
            iconSize: 25,
            color: Colors.purple,
            onPressed: () {
              if (messageText.text.isNotEmpty) {
                print("\n\t   Onpressed   \n");
                MessageModel sendedMessage = MessageModel(
                  content: messageText.text.trim(),
                  sender: currentUser,
                  chatModel: widget.chatModel,
                );
                print("\t\t\t" + messageText.text);
                sendedMessage.setUnread = true;
                // sendedMessage.setSender = currentUser;
                messageText.clear();
                addMessageToListFront(sendedMessage.toJson());
                widget.channel.sink.add(json.encode(sendedMessage.toJson()));
              }
            },
          ),
        ],
      ),
    );
  }

// @override
// void dispose() {
//   widget.channel.sink.close();
//   super.dispose();
// }
}
