import 'package:frontend/Model/MessageModel.dart';
import 'package:flutter/material.dart';

import '../Global/Global.dart';
import '../main.dart';

class MessageBubble extends StatelessWidget {
  MessageModel message;
  bool isMe;
  bool isSameUser;

  MessageBubble(
      {Key? key,
      required this.message,
      required this.isMe,
      required this.isSameUser})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        Container(
          alignment: isMe ? Alignment.topRight : Alignment.topLeft,
          child: Container(
            constraints: BoxConstraints(
              maxWidth: MediaQuery.of(context).size.width * 0.80,
            ),
            padding: const EdgeInsets.all(10),
            margin: const EdgeInsets.symmetric(vertical: 10),
            decoration: BoxDecoration(
              color: isMe ? Colors.purple : Colors.white,
              borderRadius: isMe
                  ? const BorderRadius.only(
                      topLeft: Radius.circular(30),
                      bottomLeft: Radius.circular(30),
                      bottomRight: Radius.circular(30),
                    )
                  : const BorderRadius.only(
                      topRight: Radius.circular(30),
                      bottomLeft: Radius.circular(30),
                      bottomRight: Radius.circular(30),
                    ),
              boxShadow: [
                BoxShadow(
                  color: Colors.grey.withOpacity(0.5),
                  spreadRadius: 2,
                  blurRadius: 5,
                ),
              ],
            ),
            child: Text(
              message.getContent,
              style: TextStyle(color: isMe ? Colors.white : Colors.purple),
            ),
          ),
        ),
        !isSameUser
            ? Row(
                mainAxisAlignment:
                    isMe ? MainAxisAlignment.end : MainAxisAlignment.start,
                children: <Widget>[
                  isMe
                      ? Text(
                          message.getDateOfSent.substring(11, 16),
                          style: const TextStyle(
                            fontSize: 12,
                            color: Colors.black45,
                          ),
                        )
                      : Container(
                          decoration: BoxDecoration(
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
                            radius: 15,
                            backgroundImage: Image.network(
                              mainURL +
                                  message.getSender.getImage
                                      .toString()
                                      .replaceAll("\\", "/"),
                              // headers: {
                              //   "Authorization":
                              //       "Bearer " + MyApp.currentUser.getToken
                              // },
                            ).image,
                          ),
                        ),
                  const SizedBox(
                    width: 10,
                  ),
                  isMe
                      ? Container(
                          decoration: BoxDecoration(
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
                            radius: 15,
                            backgroundImage: Image.network(
                              mainURL +
                                  message.getSender.getImage
                                      .toString()
                                      .replaceAll("\\", "/"),
                              // headers: {
                              //   "Authorization":
                              //       "Bearer " + MyApp.currentUser.getToken
                              // },
                            ).image,
                          ),
                        )
                      : Text(
                          message.getDateOfSent.substring(11, 16),
                          style: const TextStyle(
                            fontSize: 12,
                            color: Colors.black45,
                          ),
                        )
                ],
              )
            : Container(
                child: null,
              ),
      ],
    );
  }
}
