import 'package:frontend/Model/CommentModel.dart';
import 'package:frontend/Model/GroupModel.dart';
import 'package:frontend/Model/MediaModel.dart';
import 'package:frontend/Model/PageModel.dart';
import 'package:frontend/Model/ReactionModel.dart';
import 'package:frontend/Model/TypeModel.dart';
import 'package:frontend/Model/UserModel.dart';

class PostModel {
  String? id;
  String? content;
  DateTime? createdAt;
  UserModel? userModel;
  GroupModel? groupModel;
  PageModel? pageModel;
  TypeModel? type;
  List<ReactionModel>? reactionModels;
  List<CommentModel>? commentModels;
  List<MediaModel>? media = [];
  List<UserModel>? savers;
  List<UserModel>? participants;

  PostModel(
      {this.id,
      this.userModel,
      this.createdAt,
      this.pageModel,
      this.groupModel,
      this.content,
      this.type,
      this.commentModels,
      this.reactionModels,
      this.media,
      this.savers,
      this.participants});

  PostModel.fromJson(Map<String, dynamic> json) {
    id = json["id"].toString();
    content = json["content"];
    createdAt = DateTime.parse(json["createdAt"]);
    if (json["userModel"] != null) {
      userModel = UserModel.fromJson(json["userModel"]);
    }
    if (json["groupModel"] != null) {
      groupModel = GroupModel.fromJson(json["groupModel"]);
    }
    if (json["pageModel"] != null) {
      pageModel = PageModel.fromJson(json["pageModel"]);
    }
    if (json["type"] != null) type = TypeModel.fromJson(json["type"]);
    if (json['reactionModels'] != null) {
      reactionModels = [];
      json['reactionModels'].forEach((v) {
        reactionModels!.add(ReactionModel.fromJson(v));
      });
    }
    if (json['commentModels'] != null) {
      commentModels = [];
      json['commentModels'].forEach((v) {
        commentModels!.add(CommentModel.fromJson(v));
      });
    }
    if (json['media'] != null) {
      media = [];
      json['media'].forEach((v) {
        media!.add(MediaModel.fromJson(v));
      });
    }
    if (json['savers'] != null) {
      savers = [];
      json['savers'].forEach((v) {
        savers!.add(UserModel.fromJson(v));
      });
    }
    if (json['participants'] != null) {
      participants = [];
      json['participants'].forEach((v) {
        participants!.add(UserModel.fromJson(v));
      });
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['id'] = id;
    data['content'] = content;
    data['createdAt'] = createdAt.toString();
    if (userModel != null) data['userModel'] = userModel!.toJson();
    if (groupModel != null) data['groupModel'] = groupModel!.toJson();
    if (pageModel != null) data['pageModel'] = pageModel!.toJson();
    if (type != null) data['type'] = type!.toJson();
    if (reactionModels != null) {
      data['reactionModels'] = reactionModels!.map((v) => v.toJson()).toList();
    }
    if (commentModels != null) {
      data['commentModels'] = commentModels!.map((v) => v.toJson()).toList();
    }
    if (media != null) {
      data['media'] = media!.map((v) => v.toJson()).toList();
    }
    if (savers != null) {
      data['savers'] = savers!.map((v) => v.toJson()).toList();
    }
    if (participants != null) {
      data['participants'] = participants!.map((v) => v.toJson()).toList();
    }

    return data;
  }

  get getId => id;

  set setId(String id) => this.id = id;

  get getUserModel => userModel;

  set setUserModel(userModel) => this.userModel = userModel;

  get getPageModel => pageModel;

  set setPageModel(pageModel) => this.pageModel = pageModel;

  get getCreatedAt => createdAt;

  set setCreatedAt(createdAt) => this.createdAt = createdAt;

  get getContent => content;

  set setContent(content) => this.content = content;

  get getGroupModel => groupModel;

  set setGroupModel(groupModel) => this.groupModel = groupModel;

  get getType => type;

  set setType(type) => this.type = type;

  get getCommentModels => commentModels;

  set setCommentModels(commentModels) => this.commentModels = commentModels;

  get getReactionModels => reactionModels;

  set setReactionModels(reactionModels) => this.reactionModels = reactionModels;

  get getMedia => media;

  set setMedia(media) => this.media = media;

  get getSavers => savers;

  set setSavers(savers) => this.savers = savers;

  get getParticipants => participants;

  set setParticipants(participants) => this.participants = participants;

// static List<PostModel> posts = [
//   PostModel(
//     id: "1",
//     // createdAt: "28/7/2021",
//     content:
//         "Love the design of all the products at an affordable price. Everything is so easy to put together. Ordering and shipping \n is easy and very professionally\n handled, and all customer service has been timely and helpful.together. Ordering and shipping \n is easy and very professionally\n handled, and all customer service has been timely and helpfultogether. Ordering and shipping \n is easy and very professionally\n handled, and all customer service has been timely and helpfultogether. Ordering and shipping \n is easy and very professionally\n handled, and all customer service has been timely and helpfultogether. Ordering and shipping \n is easy and very professionally\n handled, and all customer service has been timely and helpfultogether. Ordering and shipping \n is easy and very professionally\n handled, and all customer service has been timely and helpful",
//     commentModels: CommentModel.comments,
//     likeModels: LikeModel.likes,
//     userModel: MyApp.currentUser,
//     share: ShareModel.shares,
//     type: TypeModel(id: "1", typename: "Sport"),
//   ),
//   PostModel(
//     id: "2",
//     // createdAt: "5/7/2021",
//     imagePost: "images/LongTermMemory.jpg",
//     commentModels: CommentModel.comments,
//     likeModels: LikeModel.likes,
//     userModel: MyApp.currentUser,
//     share: ShareModel.shares,
//     type: TypeModel(id: "2", typename: "Historical"),
//   ),
//   PostModel(
//     id: "15",
//     // createdAt: "12/7/2021",
//     videoPost: "images/videoo.mp4",
//     commentModels: CommentModel.comments,
//     likeModels: LikeModel.likes,
//     userModel: MyApp.currentUser,
//     groupModel: GroupModel.groups[2],
//     share: ShareModel.shares,
//     type: TypeModel(id: "2", typename: "Historical"),
//   ),
//   PostModel(
//     id: "4",
//     // createdAt: "2/5/2021",
//     imagePost: "images/logo.jpg",
//     content:
//         "Love the design of all the products at an affordable price. Everything is so easy to put together. Ordering and shipping\n is easy and very professionally\n handled, and all customer service has been timely and helpful.",
//     commentModels: CommentModel.comments,
//     likeModels: LikeModel.likes,
//     userModel: UserModel.users[8],
//     share: ShareModel.shares,
//     type: TypeModel(id: "5", typename: "Fashoin"),
//   ),
//   PostModel(
//     id: "15",
//     // createdAt: "2/9/2021",
//     videoPost: "images/videoo.mp4",
//     content:
//         "Love the design of all the products at an affordable price. Everything is so easy to put together. Ordering and shipping\n is easy and very professionally\n handled, and all customer service has been timely and helpful.",
//     commentModels: CommentModel.comments,
//     likeModels: LikeModel.likes,
//     share: ShareModel.shares,
//     pageModel: PageModel.pages[2],
//     type: TypeModel(id: "5", typename: "Fashoin"),
//   ),
// ];

}
