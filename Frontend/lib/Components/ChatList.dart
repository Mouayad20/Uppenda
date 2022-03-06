import 'package:flutter/material.dart';

import 'ChatBlock.dart';

class ChatList extends StatelessWidget {
  final List list;

  const ChatList({Key? key, required this.list}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemCount: list.length,
      itemBuilder: (context, i) {
        return ChatBlocke(chatModel: list[i]);
      },
    );
  }
}
