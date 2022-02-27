import 'dart:core';

import 'package:ppp/Model/PostModel.dart';

import 'AnswerModel.dart';
import 'CommentModel.dart';
import 'GroupModel.dart';
import 'MessageModel.dart';
import 'PageModel.dart';
import 'ReactionModel.dart';

class UserModel {
  String id;
  String firstName;
  String lastName;
  String mobile;
  String gender;
  String imagePath;
  String password;
  String email;
  String studyLevel;
  String location;
  String ip;
  DateTime age;
  DateTime createdAt;
  bool onLine;
  List<PostModel> postModels;
  List<PostModel> savedPost;
  List<PostModel> sharedPost;
  List<MessageModel> messages;
  List<AnswerModel> answerModels;
  List<GroupModel> groups;
  List<PageModel> pages;
  List<UserModel> friends;
  List<CommentModel> commentModels;
  List<ReactionModel> reactionModels;

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
    this.age = DateTime.now();
    this.createdAt = DateTime.now();
  }

  UserModel.fromJson(Map<String, dynamic> json) {
    this.id = json["id"].toString();
    this.firstName = json["firstName"];
    this.lastName = json["lastName"];
    this.mobile = json["mobile"];
    this.gender = json["gender"];
    this.password = json["password"];
    this.email = json["email"];
    this.studyLevel = json["studyLevel"];
    this.location = json["location"];
    this.ip = json["ip"];
    if (json['age'] != null) this.age = DateTime.parse(json["age"]);
    if (json["createdAt"] != null)
      this.createdAt = DateTime.parse(json["createdAt"]);
    this.onLine = json["onLine"];
    if (json["imagePath"] != null) this.imagePath = json["imagePath"];
    if (json['postModels'] != null) {
      this.postModels = [];
      json['postModels'].forEach((v) {
        this.postModels.add(new PostModel.fromJson(v));
      });
    }
    if (json['savedPost'] != null) {
      this.savedPost = [];
      json['savedPost'].forEach((v) {
        this.savedPost.add(new PostModel.fromJson(v));
      });
    }
    if (json['sharedPost'] != null) {
      this.sharedPost = [];
      json['sharedPost'].forEach((v) {
        this.sharedPost.add(new PostModel.fromJson(v));
      });
    }
    if (json['messages'] != null) {
      this.messages = [];
      json['messages'].forEach((v) {
        this.messages.add(new MessageModel.fromJson(v));
      });
    }
    if (json['answerModels'] != null) {
      this.answerModels = [];
      json['answerModels'].forEach((v) {
        this.answerModels.add(new AnswerModel.fromJson(v));
      });
    }
    if (json['groups'] != null) {
      this.groups = [];
      json['groups'].forEach((v) {
        this.groups.add(new GroupModel.fromJson(v));
      });
    }
    if (json['pages'] != null) {
      this.pages = [];
      json['pages'].forEach((v) {
        this.pages.add(new PageModel.fromJson(v));
      });
    }
    if (json['friends'] != null) {
      this.friends = [];
      json['friends'].forEach((v) {
        this.friends.add(new UserModel.fromJson(v));
      });
    }
    if (json['commentModels'] != null) {
      this.commentModels = [];
      json['commentModels'].forEach((v) {
        this.commentModels.add(new CommentModel.fromJson(v));
      });
    }
    if (json['reactionModels'] != null) {
      this.reactionModels = [];
      json['reactionModels'].forEach((v) {
        this.reactionModels.add(new ReactionModel.fromJson(v));
      });
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['firstName'] = this.firstName;
    data['lastName'] = this.lastName;
    data['mobile'] = this.mobile;
    data['gender'] = this.gender;
    data['imagePath'] = this.imagePath;
    data['password'] = this.password;
    data['email'] = this.email;
    data['studyLevel'] = this.studyLevel;
    data['location'] = this.location;
    data['ip'] = this.ip;
    data['age'] = this.age.toString();
    data['createdAt'] = this.createdAt.toString();
    data['onLine'] = this.onLine;
    if (this.postModels != null) {
      data['postModels'] = this.postModels.map((v) => v.toJson()).toList();
    }
    if (this.savedPost != null) {
      data['savedPost'] = this.savedPost.map((v) => v.toJson()).toList();
    }
    if (this.sharedPost != null) {
      data['sharedPost'] = this.sharedPost.map((v) => v.toJson()).toList();
    }
    if (this.messages != null) {
      data['messages'] = this.messages.map((v) => v.toJson()).toList();
    }
    if (this.answerModels != null) {
      data['answerModels'] = this.answerModels.map((v) => v.toJson()).toList();
    }
    if (this.groups != null) {
      data['groups'] = this.groups.map((v) => v.toJson()).toList();
    }
    if (this.pages != null) {
      data['pages'] = this.pages.map((v) => v.toJson()).toList();
    }
    if (this.friends != null) {
      data['friends'] = this.friends.map((v) => v.toJson()).toList();
    }
    if (this.commentModels != null) {
      data['commentModels'] =
          this.commentModels.map((v) => v.toJson()).toList();
    }
    if (this.reactionModels != null) {
      data['reactionModels'] =
          this.reactionModels.map((v) => v.toJson()).toList();
    }
    return data;
  }

  get getId => this.id;

  set setId(String id) => this.id = id;

  get getImage => this.imagePath;

  set setImage(image) => this.imagePath = image;

  get getFirstName => this.firstName;

  set setFirstName(firstName) => this.firstName = firstName;

  get getLastName => this.lastName;

  set setLastName(lastName) => this.lastName = lastName;

  get getMobile => this.mobile;

  set setMobile(mobile) => this.mobile = mobile;

  get getGender => this.gender;

  set setGender(gender) => this.gender = gender;

  get getOnLine => this.onLine;

  set setOnLine(onLine) => this.onLine = onLine;

  get getPassword => this.password;

  set setPassword(password) => this.password = password;

  get getEmail => this.email;

  set setEmail(email) => this.email = email;

  get getStudyLevel => this.studyLevel;

  set setStudyLevel(studyLevel) => this.studyLevel = studyLevel;

  get getLocation => this.location;

  set setLocation(location) => this.location = location;

  get getAge => this.age;

  set setAge(age) => this.age = age;

  get getCreatedAt => this.createdAt;

  set setCreatedAt(createdAt) => this.createdAt = createdAt;

  get getGroups => this.groups;

  set setGroups(groups) => this.groups = groups;

  get getPages => this.pages;

  set setPages(pages) => this.pages = pages;

  get getFriends => this.friends;

  set setFriends(friends) => this.friends = friends;

  get getPostModels => this.postModels;

  set setPostModels(postModels) => this.postModels = postModels;

  get getSavedPost => this.savedPost;

  set setSavedPost(savedPost) => this.savedPost = savedPost;

  get getSharedPost => this.sharedPost;

  set setSharedPost(sharedPost) => this.sharedPost = sharedPost;

  get getMessages => this.messages;

  set setMessages(messages) => this.messages = messages;

  get getIp => this.ip;

  set setIp(ip) => this.ip = ip;

  get getCommentModel => this.commentModels;

  set setCommentModel(commentModels) => this.commentModels = commentModels;

  get getReactionModel => this.reactionModels;

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
