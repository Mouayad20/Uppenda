import 'ChatModel.dart';
import 'UserModel.dart';

class MessageModel {
  String id;
  String content;
  String dateOfSent;
  ChatModel chatModel;
  UserModel sender;
  bool unread;

  MessageModel({
    this.id,
    this.content,
    this.dateOfSent,
    this.chatModel,
    this.sender,
    this.unread,
  });

  MessageModel.fromJson(Map<String, dynamic> json) {
    this.id = json["id"].toString();
    this.content = json["content"];
    this.dateOfSent = json["dateOfSent"].toString();
    if (json["chatModel"] != null)
      this.chatModel = ChatModel.fromJson(json["chatModel"]);
    if (json["sender"] != null)
      this.sender = UserModel.fromJson(json["sender"]);
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['content'] = this.content;
    data['dateOfSent'] = this.dateOfSent;
    if (this.chatModel != null) data['chatModel'] = this.chatModel.toJson();
    if (this.sender != null) data['sender'] = this.sender.toJson();
    return data;
  }

  get getId => this.id;

  set setId(String id) => this.id = id;

  get getContent => this.content;

  set setContent(content) => this.content = content;

  get getDateOfSent => this.dateOfSent;

  set setDateOfSent(dateOfSent) => this.dateOfSent = dateOfSent;

  get getChatModel => this.chatModel;

  set setChatModel(chatModel) => this.chatModel = chatModel;

  get getSender => this.sender;

  set setSender(sender) => this.sender = sender;

  get getUnread => this.unread;

  set setUnread(unread) => this.unread = unread;
}
