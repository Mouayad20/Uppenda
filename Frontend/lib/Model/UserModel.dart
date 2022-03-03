import 'dart:core';

import 'package:frontend/Model/PostModel.dart';

import 'AnswerModel.dart';
import 'CommentModel.dart';
import 'GroupModel.dart';
import 'MessageModel.dart';
import 'PageModel.dart';
import 'ReactionModel.dart';

class UserModel {
  String? id;
  String? firstName;
  String? lastName;
  String? mobile;
  String? gender;
  String? imagePath;
  String? password;
  String? email;
  String? studyLevel;
  String? location;
  String? ip;
  DateTime? age;
  DateTime? createdAt;
  bool? onLine;
  List<PostModel>? postModels;
  List<PostModel>? savedPost;
  List<PostModel>? sharedPost;
  List<MessageModel>? messages;
  List<AnswerModel>? answerModels;
  List<GroupModel>? groups;
  List<PageModel>? pages;
  List<UserModel>? friends;
  List<CommentModel>? commentModels;
  List<ReactionModel>? reactionModels;

  UserModel(
      {this.firstName,
      this.groups,
      this.pages,
      this.id,
      this.ip,
      this.imagePath,
      this.friends,
      this.lastName,
      this.location,
      this.age,
      this.onLine,
      this.createdAt,
      this.email,
      this.gender,
      this.mobile,
      this.password,
      this.studyLevel,
      this.postModels,
      this.messages,
      this.savedPost,
      this.sharedPost,
      this.answerModels,
      this.commentModels,
      this.reactionModels}) {
    age = DateTime.now();
    createdAt = DateTime.now();
  }

  UserModel.fromJson(Map<String, dynamic> json) {
    id = json["id"].toString();
    firstName = json["firstName"];
    lastName = json["lastName"];
    mobile = json["mobile"];
    gender = json["gender"];
    password = json["password"];
    email = json["email"];
    studyLevel = json["studyLevel"];
    location = json["location"];
    ip = json["ip"];
    if (json['age'] != null) age = DateTime.parse(json["age"]);
    if (json["createdAt"] != null) {
      createdAt = DateTime.parse(json["createdAt"]);
    }
    onLine = json["onLine"];
    if (json["imagePath"] != null) imagePath = json["imagePath"];
    if (json['postModels'] != null) {
      postModels = [];
      json['postModels'].forEach((v) {
        postModels!.add(PostModel.fromJson(v));
      });
    }
    if (json['savedPost'] != null) {
      savedPost = [];
      json['savedPost'].forEach((v) {
        savedPost!.add(PostModel.fromJson(v));
      });
    }
    if (json['sharedPost'] != null) {
      sharedPost = [];
      json['sharedPost'].forEach((v) {
        sharedPost!.add(PostModel.fromJson(v));
      });
    }
    if (json['messages'] != null) {
      messages = [];
      json['messages'].forEach((v) {
        messages!.add(MessageModel.fromJson(v));
      });
    }
    if (json['answerModels'] != null) {
      answerModels = [];
      json['answerModels'].forEach((v) {
        answerModels!.add(AnswerModel.fromJson(v));
      });
    }
    if (json['groups'] != null) {
      groups = [];
      json['groups'].forEach((v) {
        groups!.add(GroupModel.fromJson(v));
      });
    }
    if (json['pages'] != null) {
      pages = [];
      json['pages'].forEach((v) {
        pages!.add(PageModel.fromJson(v));
      });
    }
    if (json['friends'] != null) {
      friends = [];
      json['friends'].forEach((v) {
        friends!.add(UserModel.fromJson(v));
      });
    }
    if (json['commentModels'] != null) {
      commentModels = [];
      json['commentModels'].forEach((v) {
        commentModels!.add(CommentModel.fromJson(v));
      });
    }
    if (json['reactionModels'] != null) {
      reactionModels = [];
      json['reactionModels'].forEach((v) {
        reactionModels!.add(ReactionModel.fromJson(v));
      });
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['id'] = id;
    data['firstName'] = firstName;
    data['lastName'] = lastName;
    data['mobile'] = mobile;
    data['gender'] = gender;
    data['imagePath'] = imagePath;
    data['password'] = password;
    data['email'] = email;
    data['studyLevel'] = studyLevel;
    data['location'] = location;
    data['ip'] = ip;
    data['age'] = age.toString();
    data['createdAt'] = createdAt.toString();
    data['onLine'] = onLine;
    if (postModels != null) {
      data['postModels'] = postModels!.map((v) => v.toJson()).toList();
    }
    if (savedPost != null) {
      data['savedPost'] = savedPost!.map((v) => v.toJson()).toList();
    }
    if (sharedPost != null) {
      data['sharedPost'] = sharedPost!.map((v) => v.toJson()).toList();
    }
    if (messages != null) {
      data['messages'] = messages!.map((v) => v.toJson()).toList();
    }
    if (answerModels != null) {
      data['answerModels'] = answerModels!.map((v) => v.toJson()).toList();
    }
    if (groups != null) {
      data['groups'] = groups!.map((v) => v.toJson()).toList();
    }
    if (pages != null) {
      data['pages'] = pages!.map((v) => v.toJson()).toList();
    }
    if (friends != null) {
      data['friends'] = friends!.map((v) => v.toJson()).toList();
    }
    if (commentModels != null) {
      data['commentModels'] = commentModels!.map((v) => v.toJson()).toList();
    }
    if (reactionModels != null) {
      data['reactionModels'] = reactionModels!.map((v) => v.toJson()).toList();
    }
    return data;
  }

  get getId => id;

  set setId(String id) => this.id = id;

  get getImage => imagePath;

  set setImage(image) => imagePath = image;

  get getFirstName => firstName;

  set setFirstName(firstName) => this.firstName = firstName;

  get getLastName => lastName;

  set setLastName(lastName) => this.lastName = lastName;

  get getMobile => mobile;

  set setMobile(mobile) => this.mobile = mobile;

  get getGender => gender;

  set setGender(gender) => this.gender = gender;

  get getOnLine => onLine;

  set setOnLine(onLine) => this.onLine = onLine;

  get getPassword => password;

  set setPassword(password) => this.password = password;

  get getEmail => email;

  set setEmail(email) => this.email = email;

  get getStudyLevel => studyLevel;

  set setStudyLevel(studyLevel) => this.studyLevel = studyLevel;

  get getLocation => location;

  set setLocation(location) => this.location = location;

  get getAge => age;

  set setAge(age) => this.age = age;

  get getCreatedAt => createdAt;

  set setCreatedAt(createdAt) => this.createdAt = createdAt;

  get getGroups => groups;

  set setGroups(groups) => this.groups = groups;

  get getPages => pages;

  set setPages(pages) => this.pages = pages;

  get getFriends => friends;

  set setFriends(friends) => this.friends = friends;

  get getPostModels => postModels;

  set setPostModels(postModels) => this.postModels = postModels;

  get getSavedPost => savedPost;

  set setSavedPost(savedPost) => this.savedPost = savedPost;

  get getSharedPost => sharedPost;

  set setSharedPost(sharedPost) => this.sharedPost = sharedPost;

  get getMessages => messages;

  set setMessages(messages) => this.messages = messages;

  get getIp => ip;

  set setIp(ip) => this.ip = ip;

  get getCommentModel => commentModels;

  set setCommentModel(commentModels) => this.commentModels = commentModels;

  get getReactionModel => reactionModels;

  set setReactionModel(reactionModels) => this.reactionModels = reactionModels;

  static List<UserModel> users = [
    UserModel(
      id: "10",
      firstName: "mouayad",
      lastName: "kadoura",
      imagePath: "images/LongTermMemory.jpg",
    ),
    UserModel(
      id: "11",
      firstName: "malaz",
      lastName: "alkhwam",
      imagePath: "images/photo1.jpg",
    ),
    UserModel(
      id: "12",
      firstName: "rama",
      lastName: "tabaja",
      imagePath: "images/LongTermMemory.jpg",
    ),
    UserModel(
      id: "13",
      firstName: "mahmood",
      lastName: "shamieh",
      imagePath: "images/logo.jpg",
    ),
    UserModel(
      id: "14",
      firstName: "leen",
      lastName: "alshkar",
      imagePath: "images/LongTermMemory.jpg",
    ),
    UserModel(
      id: "55",
      firstName: "dema",
      lastName: "ramadan",
      imagePath: "images/photo1.jpg",
    ),
    UserModel(
      id: "16",
      firstName: "ghalia",
      lastName: "sabbagh",
      imagePath: "images/logo.jpg",
    ),
    UserModel(
      id: "17",
      firstName: "maisa",
      lastName: "taki",
      imagePath: "images/LongTermMemory.jpg",
    ),
    UserModel(
      id: "18",
      firstName: "hla",
      lastName: "shaker",
      imagePath: "images/photo1.jpg",
    ),
    UserModel(
      id: "19",
      firstName: "osama",
      lastName: "abd rbo",
    ),
    UserModel(
      id: "20",
      firstName: "fadi",
      lastName: "futenah",
      imagePath: "images/photo1.jpg",
    ),
    UserModel(
      id: "21",
      firstName: "faten",
      lastName: "sadder",
    )
  ];
}
