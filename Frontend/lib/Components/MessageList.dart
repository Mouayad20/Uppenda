// ignore_for_file: unrelated_type_equality_checks
//////////////////////////////////
import 'package:frontend/Model/MessageModel.dart';
import 'package:flutter/material.dart';

import '../main.dart';
import 'MessageBubble.dart';

class MessageList extends StatelessWidget {
  final List list;

  const MessageList({Key? key, required this.list}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    int prevUserId = -1;
    return Column(
      children: <Widget>[
        Expanded(
          child: ListView.builder(
            reverse: true,
            padding: const EdgeInsets.all(20),
            itemCount: list.length,
            itemBuilder: (BuildContext context, int index) {
              // print("\n\n_________________________\n\n");
              // print(list);
              // print("\n\n_________________________\n\n");
              // print("\n\n\n    >>>>>>>>>>>>>>>>>>>>    \n" +
              //     list[index].toString());
              final MessageModel message = MessageModel.fromJson(list[index]);
              // print("\n________________________\n");
              // print("\n" + message.content);
              final bool isMe = message.getSender.id == MyApp.currentUser!.id;
              final bool isSameUser = prevUserId == message.getSender.id;
              prevUserId = int.parse(message.getSender.id.toString());
              return MessageBubble(
                message: message,
                isMe: isMe,
                isSameUser: isSameUser,
              );
            },
          ),
        ),
      ],
    );
  }
}
