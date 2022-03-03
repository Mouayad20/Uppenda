import 'GroupModel.dart';
import 'MessageModel.dart';
import 'UserModel.dart';

class ChatModel {

  String? id;
  bool? isHidden1;
  bool? isHidden2;
  GroupModel? groupModel;
  List<MessageModel>? messages;
  UserModel? user1;
  UserModel? user2;
  bool? unread = true;

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
    id = json["id"].toString();
    isHidden1 = json["isHidden1"];
    isHidden2 = json["isHidden2"];
    if (json["groupModel"] != null) {
      groupModel = GroupModel.fromJson(json["groupModel"]);
    }
    if (json['messages'] != null) {
      messages = <MessageModel>[];
      json['messages'].forEach((v) {
        messages!.add(MessageModel.fromJson(v));
      });
    }
    if (json["user1"] != null) user1 = UserModel.fromJson(json["user1"]);
    if (json["user2"] != null) user2 = UserModel.fromJson(json["user2"]);
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data["id"] = id;
    data["isHidden1"] = isHidden1;
    data["isHidden2"] = isHidden2;
    if (groupModel != null) data['groupModel'] = groupModel!.toJson();
    if (user1 != null) data['user1'] = user1!.toJson();
    if (user2 != null) data['user2'] = user2!.toJson();
    if (messages != null) {
      data['messages'] = messages!.map((v) => v.toJson()).toList();
    }
    return data;
  }

  get getId => id;

  set setId(id) => this.id = id;

  get getIsHidden1 => isHidden1;

  set setIsHidden1(isHidden1) => this.isHidden1 = isHidden1;

  get getIsHidden2 => isHidden2;

  set setIsHidden2(isHidden2) => this.isHidden2 = isHidden2;

  get getGroupModel => groupModel;

  set setGroupModel(groupModel) => this.groupModel = groupModel;

  get getMessages => messages;

  set setMessages(messages) => this.messages = messages;

  get getUser1 => user1;

  set setUser1(user1) => this.user1 = user1;

  get getUser2 => user2;

  set setUser2(user2) => this.user2 = user2;

  get getUnread => unread;

  set setUnread(unread) => this.unread = unread;
}
