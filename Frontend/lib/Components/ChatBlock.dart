// ignore_for_file: unnecessary_null_comparison

import 'package:frontend/Model/ChatModel.dart';
import 'package:frontend/Pages/ChatScreen.dart';
import 'package:flutter/material.dart';
import 'package:web_socket_channel/io.dart';

import '../main.dart';

class ChatBlocke extends StatelessWidget {
  ChatModel chatModel;

  ChatBlocke({required this.chatModel});

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        // chatModel.unread = false;
        Navigator.push(
          context,
          MaterialPageRoute(
            builder: (_) => ChatScreen(
              chatModel: chatModel,
              channel: IOWebSocketChannel.connect("ws://" + MyApp.ip + "/chat"),
            ),
          ),
        );
      },
      child: Column(
        children: [
          Padding(
            padding: const EdgeInsets.only(top: 8.0),
            child: Container(
              decoration: BoxDecoration(
                color: Colors.white,
                borderRadius: BorderRadius.circular(30.0),
                boxShadow: const [
                  BoxShadow(
                    color: Color.fromRGBO(233, 207, 236, 1),
                    spreadRadius: 4,
                    blurRadius: 7,
                    offset: Offset(0, 0),
                  ),
                ],
              ),
              width: double.infinity,
              height: 110,
              padding: const EdgeInsets.symmetric(
                horizontal: 20,
                vertical: 15,
              ),
              child: Row(
                children: [
                  Container(
                    padding: const EdgeInsets.all(2),
                    decoration: chatModel.getUnread //// unred set from frontEnd
                        ? BoxDecoration(
                            borderRadius:
                                const BorderRadius.all(Radius.circular(40)),
                            border: Border.all(
                              width: 2,
                              color: Theme.of(context).primaryColor,
                            ),
                            boxShadow: [
                              BoxShadow(
                                color: Colors.grey.withOpacity(0.5),
                                spreadRadius: 2,
                                blurRadius: 5,
                              ),
                            ],
                          )
                        : BoxDecoration(
                            shape: BoxShape.circle,
                            boxShadow: [
                              BoxShadow(
                                color: Colors.grey.withOpacity(0.5),
                                spreadRadius: 2,
                                blurRadius: 5,
                              ),
                            ],
                          ),
                    child: CircleAvatar(
                      radius: 30,
                      backgroundImage: chatModel.getGroupModel.name == null
                          ? Image.network(
                              MyApp.mainURL +
                                  chatModel.getUser2.getImage
                                      .toString()
                                      .replaceAll("\\", "/"),
                              // headers: {
                              //   "Authorization":
                              //       "Bearer " + MyApp.currentUser.getToken
                              // },
                            ).image
                          : Image.network(
                              MyApp.mainURL +
                                  chatModel.getGroupModel.imgPath
                                      .toString()
                                      .replaceAll("\\", "/"),
                              // headers: {
                              //   "Authorization":
                              //       "Bearer " + MyApp.currentUser.getToken
                              // },
                            ).image,
                    ),
                  ),
                  Container(
                    width: MediaQuery.of(context).size.width * 0.65,
                    padding: const EdgeInsets.only(left: 20, top: 15),
                    child: Column(
                      children: [
                        Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: <Widget>[
                            Row(
                              children: <Widget>[
                                Text(
                                  chatModel.groupModel != null
                                      ? chatModel.getGroupModel.name
                                      : chatModel.getUser2.getFirstName +
                                          " " +
                                          chatModel.getUser2.getLastName,
                                  style: const TextStyle(
                                    fontFamily: 'Merienda',
                                    fontSize: 13,
                                    fontWeight: FontWeight.bold,
                                  ),
                                ),
                                chatModel.getGroupModel.name == null
                                    ? chatModel.getUser2.getOnLine
                                        ? Container(
                                            margin:
                                                const EdgeInsets.only(left: 5),
                                            width: 7,
                                            height: 7,
                                            decoration: BoxDecoration(
                                              shape: BoxShape.circle,
                                              color: Theme.of(context)
                                                  .primaryColor,
                                            ),
                                          )
                                        : Container()
                                    : Container()
                              ],
                            ),
                            Text(
                              chatModel.getMessages[0].dateOfSent,
                              style: const TextStyle(
                                fontSize: 8,
                                fontWeight: FontWeight.w300,
                                color: Colors.black54,
                              ),
                            ),
                          ],
                        ),
                        const SizedBox(
                          height: 10,
                        ),
                        Container(
                          alignment: Alignment.topLeft,
                          child: Text(
                            chatModel.getGroupModel.name != null
                                ? chatModel.getMessages[0].sender == null
                                    ? " " + chatModel.getMessages[0].getContent
                                    : chatModel.getMessages[0].sender
                                            .getFirstName +
                                        " : " +
                                        chatModel.getMessages[0].getContent
                                : chatModel.getMessages[0].getContent,
                            style: const TextStyle(
                              fontSize: 13,
                              color: Colors.black54,
                            ),
                            overflow: TextOverflow.ellipsis,
                            maxLines: 2,
                          ),
                        ),
                      ],
                    ),
                  ),
                ],
              ),
            ),
          ),
          const SizedBox(
            height: 3,
          )
        ],
      ),
    );
  }
}
