import 'package:frontend/Model/UserModel.dart';
import 'package:frontend/main.dart';

import '../Global/Global.dart';
import 'PostModel.dart';

class PageModel {
  String? id;
  String? name;
  String? description;
  String? imgPath;
  DateTime? createdAt;
  UserModel? admin;
  List<UserModel>? members;
  List<PostModel>? postModels;

  PageModel(
      {this.id,
      this.name,
      this.admin,
      this.members,
      this.imgPath,
      this.description,
      this.createdAt,
      this.postModels});

  PageModel.fromJson(Map<String, dynamic> json) {
    id = json["id"].toString();
    name = json["name"];
    description = json["description"];
    if (json["createdAt"] != null) {
      createdAt = DateTime.parse(json["createdAt"]);
    }
    if (json["imgPath"] != null) imgPath = json["imgPath"];
    if (json["admin"] != null) admin = UserModel.fromJson(json["admin"]);
    if (json['members'] != null) {
      members = [];
      json['members'].forEach((v) {
        members!.add(UserModel.fromJson(v));
      });
    }
    if (json['postModels'] != null) {
      postModels = [];
      json['postModels'].forEach((v) {
        postModels!.add(PostModel.fromJson(v));
      });
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['id'] = id;
    data['name'] = name;
    data['description'] = description;
    data['createdAt'] = createdAt.toString();
    if (imgPath != null) data['imgPath'] = imgPath;
    if (admin != null) data['admin'] = admin!.toJson();
    if (members != null) {
      data['members'] = members!.map((v) => v.toJson()).toList();
    }
    if (postModels != null) {
      data['postModels'] = postModels!.map((v) => v.toJson()).toList();
    }
    return data;
  }

  get getId => id;

  set setId(String id) => this.id = id;

  get getName => name;

  set setName(pageName) => name = name;

  get getAdmin => admin;

  set setAdmin(admin) => this.admin = admin;

  get getMembers => members;

  set setMembers(members) => this.members = members;

  get getImage => imgPath;

  set setImage(imgPath) => this.imgPath = imgPath;

  get getDescription => description;

  set setDescription(description) => this.description = description;

  get getCreatedAt => createdAt;

  set setCreatedAt(createdAt) => this.createdAt = createdAt;

  get getPostModels => postModels;

  set setPostModels(postModels) => this.postModels = postModels;

  static List<PageModel> pages = [
    PageModel(
        id: "1",
        name: "drama news",
        admin: UserModel.users[2],
        members: UserModel.users,
        imgPath: null),
    PageModel(
        id: "2",
        name: "drama news",
        admin: currentUser,
        members: UserModel.users,
        imgPath: "images/photo.jpg"),
    PageModel(
        id: "3",
        name: "drama news",
        admin: UserModel.users[4],
        members: UserModel.users,
        imgPath: null),
    PageModel(
        id: "4",
        name: "drama news",
        admin: UserModel.users[2],
        members: UserModel.users,
        imgPath: "images/photo.jpg"),
    PageModel(
        id: "5",
        name: "drama news",
        admin: currentUser,
        members: UserModel.users,
        imgPath: "images/photo.jpg"),
    PageModel(
        id: "6",
        name: "drama news",
        admin: UserModel.users[3],
        members: UserModel.users,
        imgPath: "images/photo.jpg"),
    PageModel(
        id: "7",
        name: "drama news",
        admin: UserModel.users[2],
        members: UserModel.users,
        imgPath: "images/photo.jpg"),
  ];
}
