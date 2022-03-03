import 'package:frontend/Model/UserModel.dart';
import 'package:frontend/main.dart';

import 'PostModel.dart';

class CommentModel {
  String? id;
  String? content;
  String? imagePath;
  String? createdAt;
  PostModel? postModel;
  UserModel? userModel;

  CommentModel({
    this.id,
    this.userModel,
    this.postModel,
    this.imagePath,
    this.content,
    this.createdAt,
  });

  CommentModel.fromJson(Map<String, dynamic> json) {
    id = json["id"].toString();
    content = json["content"];
    createdAt = json["createdAt"];
    if (json["imagePath"] != null) imagePath = json["imagePath"];
    if (json["postModel"] != null) {
      postModel = PostModel.fromJson(json["postModel"]);
    }
    if (json["userModel"] != null) {
      userModel = UserModel.fromJson(json["userModel"]);
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['id'] = id;
    data['content'] = content;
    data['createdAt'] = createdAt;
    if (imagePath != null) data['imagePath'] = imagePath;
    if (postModel != null) data['postModel'] = postModel!.toJson();
    if (userModel != null) data['userModel'] = userModel!.toJson();
    return data;
  }

  get getId => id;

  set setId(id) => this.id = id;

  get getUserModel => userModel;

  set setUserModel(userModel) => this.userModel = userModel;

  get getPostModel => postModel;

  set setPostModel(postModel) => this.postModel = postModel;

  get getContent => content;

  set setContent(content) => this.content = content;

  get getImage => imagePath;

  set setImage(imagePath) => this.imagePath = imagePath;

  get getCreatedAt => createdAt;

  set setCreatedAt(createdAt) => this.createdAt = createdAt;

  static List<CommentModel> comments = [
    CommentModel(
      id: "1",
      userModel: MyApp.currentUser,
      imagePath: 'images/photo1.jpg',
      content:
          " My name is ghalia sabbagh , I have one sisterhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh and three brothers , I live in Damascus , I am studying IT",
      createdAt: "1/1/2001",
    ),
    CommentModel(
      id: "2",
      userModel: UserModel.users[4],
      imagePath: null,
      content:
          "Sunday\nMonday\nTuesday\nWednesday\nThursday\nFridady\nSaturday",
      createdAt: "1/1/2001",
    ),
    CommentModel(
        id: "3",
        userModel: MyApp.currentUser,
        imagePath: null,
        content: "can i help you \n can i help you \n can i help you \n ",
        createdAt: "1/1/2001"),
    CommentModel(
      id: "4",
      userModel: MyApp.currentUser,
      imagePath: 'images/photo1.jpg',
      content: null,
      createdAt: "1/1/2001",
    ),
    CommentModel(
      id: "5",
      userModel: UserModel.users[2],
      imagePath: 'images/photo1.jpg',
      content: "lloly",
      createdAt: "1/1/2001",
    ),
    CommentModel(
      id: "6",
      userModel: UserModel.users[3],
      imagePath: null,
      content:
          "Listen to the audio without reading the text. Just listen and try to hear how much you can understand.\n\n Do you recognise any words or phrases? Can you understand what they are talking about?",
      createdAt: "1/1/2001",
    ),
  ];
}
