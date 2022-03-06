// ignore_for_file: non_constant_identifier_names

import 'dart:convert';

import 'package:shared_preferences/shared_preferences.dart';

import '../Model/UserModel.dart';

class CacheController {
  UserModel? userModel;
  SharedPreferences? cache;

  ChatController() async {
    cache = await SharedPreferences.getInstance();
  }

  void addUserToCache(UserModel userModel) async {
    cache!.remove("userModel");
    cache!.setString("userModel", jsonEncode(userModel.toJson()));
  }

  Future<UserModel?> getUserFromCache() async {
    userModel = UserModel.fromJson(jsonDecode(cache!.getString("userModel")!));
    return userModel;
  }

  void addTokenToCache(String token) async {
    cache!.remove("token");
    cache!.setString("token", token);
  }

  Future<String?> getTokenFromCache() async {
    return cache!.getString("token");
  }

  void clearCache() {
    cache!.clear();
  }
}
