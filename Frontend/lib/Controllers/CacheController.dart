// ignore_for_file: non_constant_identifier_names

import 'dart:convert';

import 'package:frontend/Global/Global.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../Model/UserModel.dart';

class CacheController {
  ChatController() {}

  void addUserToCache(UserModel userModel) async {
    SharedPreferences cache = await SharedPreferences.getInstance();
    cache.setString("userModel", jsonEncode(userModel.toJson()));
  }

  Future<UserModel> getUserFromCache() async {
    SharedPreferences cache = await SharedPreferences.getInstance();
    return UserModel.fromJson(jsonDecode(cache.getString("userModel")!));
  }

  getUser() async {
    currentUser = await getUserFromCache();
  }

  void addTokenToCache(String token) async {
    SharedPreferences cache = await SharedPreferences.getInstance();
    cache.setString("token", token);
  }

  Future<String?> getTokenFromCache() async {
    SharedPreferences cache = await SharedPreferences.getInstance();
    if (cache.getString("token") != null) {
      return cache.getString("token");
    } else {
      return null;
    }
  }

  getToken() async {
    if (await getTokenFromCache() != null) {
      token = (await getTokenFromCache())!;
    } else {
      token = "null";
    }
  }

  void clear() async {
    SharedPreferences cache = await SharedPreferences.getInstance();
    cache.clear();
  }
}
