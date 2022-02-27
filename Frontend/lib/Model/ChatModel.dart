import 'GroupModel.dart';
import 'MessageModel.dart';
import 'UserModel.dart';

class ChatModel {

  String id;
  bool isHidden1;
  bool isHidden2;
  GroupModel groupModel;
  List<MessageModel> messages;
  UserModel user1;
  UserModel user2;
  bool unread = true;

  ChatModel(
      {this.id,
      this.isHidden1,
      this.isHidden2,
      this.groupModel,
      this.messages,
      this.user1,
      this.user2,
      this.unread});

  ChatModel.fromJson(Map<String, dynamic> json) {
    this.id = json["id"].toString();
    this.isHidden1 = json["isHidden1"];
    this.isHidden2 = json["isHidden2"];
    if (json["groupModel"] != null)
      this.groupModel = GroupModel.fromJson(json["groupModel"]);
    if (json['messages'] != null) {
      this.messages = new List<MessageModel>();
      json['messages'].forEach((v) {
        this.messages.add(new MessageModel.fromJson(v));
      });
    }
    if (json["user1"] != null) this.user1 = UserModel.fromJson(json["user1"]);
    if (json["user2"] != null) this.user2 = UserModel.fromJson(json["user2"]);
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data["id"] = this.id;
    data["isHidden1"] = this.isHidden1;
    data["isHidden2"] = this.isHidden2;
    if (this.groupModel != null) data['groupModel'] = this.groupModel.toJson();
    if (this.user1 != null) data['user1'] = this.user1.toJson();
    if (this.user2 != null) data['user2'] = this.user2.toJson();
    if (this.messages != null) {
      data['messages'] = this.messages.map((v) => v.toJson()).toList();
    }
    return data;
  }

  get getId => this.id;

  set setId(id) => this.id = id;

  get getIsHidden1 => this.isHidden1;

  set setIsHidden1(isHidden1) => this.isHidden1 = isHidden1;

  get getIsHidden2 => this.isHidden2;

  set setIsHidden2(isHidden2) => this.isHidden2 = isHidden2;

  get getGroupModel => this.groupModel;

  set setGroupModel(groupModel) => this.groupModel = groupModel;

  get getMessages => this.messages;

  set setMessages(messages) => this.messages = messages;

  get getUser1 => this.user1;

  set setUser1(user1) => this.user1 = user1;

  get getUser2 => this.user2;

  set setUser2(user2) => this.user2 = user2;

  get getUnread => this.unread;

  set setUnread(unread) => this.unread = unread;
}
