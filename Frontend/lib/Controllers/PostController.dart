import 'package:flutter/material.dart';
import 'package:frontend/Model/PostModel.dart';
import 'package:http/http.dart' as http;
import 'package:frontend/main.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'dart:convert';

import '../Global/Global.dart';
import 'UploadController.dart';

class PostController {
  PostModel? postModel;
  List<PostModel>? postModels;
  UploadController uploadController = UploadController();

  String currentUri = mainURL + "/post";

  void clearCache() async {
    SharedPreferences cache = await SharedPreferences.getInstance();
    cache.clear();
  }

  Future<List<PostModel>> getAll() async {
    SharedPreferences cache = await SharedPreferences.getInstance();

    var response = await http.get(
      Uri.parse(currentUri + "/getAll"),
      headers: {
        "Content-Type": "application/json",
        // "Authorization": "Bearer " + cache.getString("token")
      },
    );
    List<dynamic> sss = json.decode(response.body);
    List<PostModel> list = [];
    for (int i = 0; i < sss.length; i++) {
      if (sss.isNotEmpty) {
        list.add(PostModel.fromJson(sss[i]));
      }
    }

    return list;
  }

  Future<List<PostModel>> getSummery(String u_id) async {
    SharedPreferences cache = await SharedPreferences.getInstance();
    int u = int.parse(u_id);

    var response = await http.get(
      Uri.parse(currentUri + "/getSummery/user_id=$u"),
      headers: {
        "Content-Type": "application/json",
        // "Authorization": "Bearer " + cache.getString("token")
      },
    );
    List<dynamic> sss = json.decode(response.body);
    List<PostModel> list = [];
    for (int i = 0; i < sss.length; i++) {
      if (sss.isNotEmpty) {
        list.add(PostModel.fromJson(sss[i]));
      }
    }

    // print("\n************************\n");
    // print("   " + list[0].getParticipants.length.toString());
    // print("\n************************\n");

    return list;
  }

  Future<List<PostModel>> getAllPostsForUser(String u_id) async {
    SharedPreferences cache = await SharedPreferences.getInstance();
    int u = int.parse(u_id);

    var response = await http.get(
      Uri.parse(currentUri + "/getAllPostsForUserByUID/user_id=$u"),
      headers: {
        "Content-Type": "application/json",
        // "Authorization": "Bearer " + cache.getString("token")
      },
    );
    List<dynamic> sss = json.decode(response.body);
    List<PostModel> list = [];
    for (int i = 0; i < sss.length; i++) {
      if (sss.isNotEmpty) {
        list.add(PostModel.fromJson(sss[i]));
      }
    }

    return list;
  }

  Future<List<PostModel>> getAllPostsForGroup(String g_id) async {
    SharedPreferences cache = await SharedPreferences.getInstance();
    int g = int.parse(g_id);

    var response = await http.get(
      Uri.parse(currentUri + "/getAllPostsForGroupByGID/group_id=$g"),
      headers: {
        "Content-Type": "application/json",
        // "Authorization": "Bearer " + cache.getString("token")
      },
    );
    List<dynamic> sss = json.decode(response.body);
    List<PostModel> list = [];
    for (int i = 0; i < sss.length; i++) {
      if (sss.isNotEmpty) {
        list.add(PostModel.fromJson(sss[i]));
      }
    }

    print("\n************************\n");
    print("   " + json.decode(response.body).toString());
    print("\n************************\n");

    return list;
  }

  Future<List<PostModel>> getAllPostsForPage(String p_id) async {
    SharedPreferences cache = await SharedPreferences.getInstance();
    int p = int.parse(p_id);

    var response = await http.get(
      Uri.parse(currentUri + "/getAllPostsForPageByPID/page_id=$p"),
      headers: {
        "Content-Type": "application/json",
        // "Authorization": "Bearer " + cache.getString("token")
      },
    );
    List<dynamic> sss = json.decode(response.body);
    List<PostModel> list = [];
    for (int i = 0; i < sss.length; i++) {
      if (sss.isNotEmpty) {
        list.add(PostModel.fromJson(sss[i]));
      }
    }

    return list;
  }

  Future<PostModel> addPostOnProfile(PostModel postModel) async {
    SharedPreferences cache = await SharedPreferences.getInstance();
    int u_id = 1; // int.parse(cache.getString('id'));
    var response = await http.post(
        Uri.parse(currentUri + "/addProfilePost/$u_id"),
        body: json.encode(postModel.toJson()),
        headers: {
          "Content-Type": "application/json",
          // "Authorization": "Bearer " + cache.getString("token")
        });

    this.postModel = PostModel.fromJson(json.decode(response.body));
    print("\n************************\n");
    print("   " + json.decode(response.body).toString());
    print("\n************************\n");

    return postModel;
    // print("   ??????  " + this.postModel.getMedia.length.toString());
    //
    // if (this.postModel.getMedia != null) {
    //   for (var i = 0; i < this.postModel.getMedia.length; i++) {
    //     uploadController.uploadFile(this.postModel.getMedia[i], "posts");
    //   }
    // }
  }

  Future<PostModel> updatePost(PostModel postModel) async {
    int u_id = int.parse(currentUser!.getId);
    var response = await http.post(Uri.parse(currentUri + "/update"),
        body: json.encode(postModel.toJson()),
        headers: {
          "Content-Type": "application/json",
          // "Authorization": "Bearer " + currentUser.getToken
        });
    print("\n************************\n");
    print(response.body);
    print("\n************************\n");
    return postModel;
  }

  Future<PostModel?> deleteById(String id) async {
    int p = int.parse(id);
    var response =
        await http.delete(Uri.parse(currentUri + "/delete/$p"), headers: {
      "Content-Type": "application/json",
      // "Authorization": "Bearer " + currentUser.getToken
    });

    print("\n***************\n");
    print(response.body);
    print("\n***************\n");
    return postModel;
  }
}
