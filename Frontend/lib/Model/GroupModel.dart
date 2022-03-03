import 'dart:core';
import 'package:frontend/Model/UserModel.dart';
import 'package:frontend/main.dart';

import 'PostModel.dart';

class GroupModel {
  String? id;
  String? name;
  String? imgPath;
  String? description;
  UserModel? admin;
  DateTime? createdAt;
  List<PostModel>? postModels;
  List<UserModel>? members;

  GroupModel(
      {this.name,
      this.id,
      this.admin,
      this.postModels,
      this.members,
      this.imgPath,
      this.createdAt,
      this.description});

  GroupModel.fromJson(Map<String, dynamic> json) {
    id = json["id"].toString();
    name = json["name"];
    description = json["description"];
    createdAt = DateTime.parse(json["createdAt"]);
    if (json["imgPath"] != null) imgPath = json["imgPath"];
    if (json["admin"] != null) admin = UserModel.fromJson(json["admin"]);
    if (json['members'] != null) {
      members = <UserModel>[];
      json['members'].forEach((v) {
        members!.add(UserModel.fromJson(v));
      });
    }
    if (json['postModels'] != null) {
      postModels = <PostModel>[];
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

  set setName(name) => this.name = name;

  get getAdmin => admin;

  set setAdmin(admin) => this.admin = admin;

  get getImage => imgPath;

  set setImage(imgPath) => this.imgPath = imgPath;

  get getCreatedAt => createdAt;

  set setCreatedAt(createdAt) => this.createdAt = createdAt;

  get getDescription => description;

  set setDescription(description) => this.description = description;

  get getPostModels => postModels;

  set setPostModels(postModels) => this.postModels = postModels;

  get getMembers => members;

  set setMembers(members) => this.members = members;

  static List<GroupModel> groups = [
    GroupModel(
        id: "1",
        name: "drama news",
        admin: MyApp.currentUser,
        members: UserModel.users,
        imgPath: "images/photo1.jpg"),
    GroupModel(
        id: "2",
        name: "drama news",
        admin: MyApp.currentUser,
        members: UserModel.users,
        imgPath: "images/photo1.jpg"),
    GroupModel(
        id: "3",
        name: "drama news",
        admin: MyApp.currentUser,
        members: UserModel.users,
        imgPath: "images/photo1.jpg"),
    GroupModel(
        id: "4",
        name: "drama news",
        admin: MyApp.currentUser,
        members: UserModel.users,
        imgPath: "images/photo1.jpg"),
    GroupModel(
        id: "5",
        name: "drama news",
        admin: UserModel.users[3],
        members: UserModel.users,
        imgPath: "images/photo1.jpg"),
    GroupModel(
        id: "6",
        name: "drama news",
        admin: MyApp.currentUser,
        members: UserModel.users,
        imgPath: null),
    GroupModel(
        id: "7",
        name: "drama news",
        admin: UserModel.users[3],
        members: UserModel.users,
        imgPath: "images/photo1.jpg"),
    GroupModel(
        id: "8",
        name: "drama news",
        admin: UserModel.users[3],
        members: UserModel.users,
        imgPath: "images/photo1.jpg"),
  ];
}
