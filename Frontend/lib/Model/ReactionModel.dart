import 'package:frontend/main.dart';

import 'PostModel.dart';
import 'ReactionTypeModel.dart';
import 'UserModel.dart';

class ReactionModel {
  String? id;
  PostModel? postModel;
  UserModel? userModel;
  ReactionTypeModel? reactionTypeModel;

  ReactionModel(
      {this.id, this.postModel, this.userModel, this.reactionTypeModel});

  ReactionModel.fromJson(Map<String, dynamic> json) {
    id = json["id"].toString();
    if (json["postModel"] != null) {
      postModel = PostModel.fromJson(json["postModel"]);
    }
    if (json["userModel"] != null) {
      userModel = UserModel.fromJson(json["userModel"]);
    }
    if (json["reactionTypeModel"] != null) {
      reactionTypeModel =
          ReactionTypeModel.fromJson(json["reactionTypeModel"]);
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['id'] = id;
    if (postModel != null) data['postModel'] = postModel!.toJson();
    if (userModel != null) data['userModel'] = userModel!.toJson();
    if (reactionTypeModel != null) {
      data['reactionTypeModel'] = reactionTypeModel!.toJson();
    }
    return data;
  }

  get getId => id;

  set setId(String id) => this.id = id;

  get getPostModel => postModel;

  set setPostModel(postModel) => this.postModel = postModel;

  get getUserModel => userModel;

  set setUserModel(userModel) => this.userModel = userModel;

  get getReactionTypeModel => reactionTypeModel;

  set setReactionTypeModel(reactionTypeModel) =>
      this.reactionTypeModel = reactionTypeModel;

  static List<ReactionModel> likes = [
    ReactionModel(
      id: "1",
      userModel: UserModel.users[0],
      reactionTypeModel: ReactionTypeModel(
          id: "1", reactionType: "Love", colorName: "0xFFFB71C1C"),
    ),
    ReactionModel(
      id: "2",
      userModel: UserModel.users[1],
      reactionTypeModel: ReactionTypeModel(
          id: "5", reactionType: "Fun", colorName: "0xFFF06292"),
    ),
    ReactionModel(
      id: "3",
      userModel: UserModel.users[2],
      reactionTypeModel: ReactionTypeModel(
          id: "3", reactionType: "Sad", colorName: "0xFFF9A825"),
    ),
    ReactionModel(
      id: "4",
      userModel: MyApp.currentUser,
      reactionTypeModel: ReactionTypeModel(
          id: "1", reactionType: "Love", colorName: "0xFFFB71C1C"),
    ),
    ReactionModel(
      id: "5",
      userModel: UserModel.users[4],
      reactionTypeModel: ReactionTypeModel(
          id: "2", reactionType: "Angry", colorName: "0xFF000000"),
    ),
    ReactionModel(
      id: "6",
      userModel: UserModel.users[5],
      reactionTypeModel: ReactionTypeModel(
          id: "4", reactionType: "Like", colorName: "0xFF9C27B0"),
    ),
    ReactionModel(
      id: "7",
      userModel: UserModel.users[6],
      reactionTypeModel: ReactionTypeModel(
          id: "2", reactionType: "Angry", colorName: "0xFF000000"),
    ),
    ReactionModel(
      id: "8",
      userModel: UserModel.users[7],
      reactionTypeModel: ReactionTypeModel(
          id: "3", reactionType: "Sad", colorName: "0xFFF9A825"),
    ),
    ReactionModel(
      id: "9",
      userModel: UserModel.users[8],
      reactionTypeModel: ReactionTypeModel(
          id: "1", reactionType: "Love", colorName: "0xFFFB71C1C"),
    ),
    ReactionModel(
      id: "10",
      userModel: UserModel.users[9],
      reactionTypeModel: ReactionTypeModel(
          id: "5", reactionType: "Fun", colorName: "0xFFF06292"),
    ),
    ReactionModel(
      id: "11",
      userModel: UserModel.users[10],
      reactionTypeModel: ReactionTypeModel(
          id: "4", reactionType: "Like", colorName: "0xFF9C27B0"),
    ),
    ReactionModel(
      id: "12",
      userModel: UserModel.users[11],
      reactionTypeModel: ReactionTypeModel(
          id: "1", reactionType: "Love", colorName: "0xFFFB71C1C"),
    ),
  ];

  static List<ReactionModel> likes2 = [
    ReactionModel(
      id: "1",
      userModel: UserModel.users[0],
      reactionTypeModel: ReactionTypeModel(
          id: "1", reactionType: "Love", colorName: "0xFFFB71C1C"),
    ),
    ReactionModel(
      id: "2",
      userModel: UserModel.users[1],
      reactionTypeModel: ReactionTypeModel(
          id: "5", reactionType: "Fun", colorName: "0xFFF06292"),
    ),
    ReactionModel(
      id: "3",
      userModel: UserModel.users[2],
      reactionTypeModel: ReactionTypeModel(
          id: "3", reactionType: "Sad", colorName: "0xFFF9A825"),
    ),
    ReactionModel(
      id: "5",
      userModel: UserModel.users[4],
      reactionTypeModel: ReactionTypeModel(
          id: "2", reactionType: "Angry", colorName: "0xFF000000"),
    ),
    ReactionModel(
      id: "6",
      userModel: UserModel.users[5],
      reactionTypeModel: ReactionTypeModel(
          id: "4", reactionType: "Like", colorName: "0xFF9C27B0"),
    ),
    ReactionModel(
      id: "7",
      userModel: UserModel.users[6],
      reactionTypeModel: ReactionTypeModel(
          id: "2", reactionType: "Angry", colorName: "0xFF000000"),
    ),
    ReactionModel(
      id: "8",
      userModel: UserModel.users[7],
      reactionTypeModel: ReactionTypeModel(
          id: "3", reactionType: "Sad", colorName: "0xFFF9A825"),
    ),
    ReactionModel(
      id: "9",
      userModel: UserModel.users[8],
      reactionTypeModel: ReactionTypeModel(
          id: "1", reactionType: "Love", colorName: "0xFFFB71C1C"),
    ),
    ReactionModel(
      id: "10",
      userModel: UserModel.users[9],
      reactionTypeModel: ReactionTypeModel(
          id: "5", reactionType: "Fun", colorName: "0xFFF06292"),
    ),
    ReactionModel(
      id: "11",
      userModel: UserModel.users[10],
      reactionTypeModel: ReactionTypeModel(
          id: "4", reactionType: "Like", colorName: "0xFF9C27B0"),
    ),
    ReactionModel(
      id: "12",
      userModel: UserModel.users[11],
      reactionTypeModel: ReactionTypeModel(
          id: "1", reactionType: "Love", colorName: "0xFFFB71C1C"),
    ),
  ];
}
