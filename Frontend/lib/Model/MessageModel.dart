import 'ChatModel.dart';
import 'UserModel.dart';

class MessageModel {
  String? id;
  String? content;
  String? dateOfSent;
  ChatModel? chatModel;
  UserModel? sender;
  bool? unread;

  MessageModel({
    this.id,
    this.content,
    this.dateOfSent,
    this.chatModel,
    this.sender,
    this.unread,
  });

  MessageModel.fromJson(Map<String, dynamic> json) {
    id = json["id"].toString();
    content = json["content"];
    dateOfSent = json["dateOfSent"].toString();
    if (json["chatModel"] != null) {
      chatModel = ChatModel.fromJson(json["chatModel"]);
    }
    if (json["sender"] != null) {
      sender = UserModel.fromJson(json["sender"]);
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['id'] = id;
    data['content'] = content;
    data['dateOfSent'] = dateOfSent;
    if (chatModel != null) data['chatModel'] = chatModel!.toJson();
    if (sender != null) data['sender'] = sender!.toJson();
    return data;
  }

  get getId => id;

  set setId(String id) => this.id = id;

  get getContent => content;

  set setContent(content) => this.content = content;

  get getDateOfSent => dateOfSent;

  set setDateOfSent(dateOfSent) => this.dateOfSent = dateOfSent;

  get getChatModel => chatModel;

  set setChatModel(chatModel) => this.chatModel = chatModel;

  get getSender => sender;

  set setSender(sender) => this.sender = sender;

  get getUnread => unread;

  set setUnread(unread) => this.unread = unread;
}
