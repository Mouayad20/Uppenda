import 'dart:convert';

import 'package:frontend/Model/TypeModel.dart';
import 'package:http/http.dart' as http;
import 'package:frontend/Social/CreatePost.dart';

import '../main.dart';

import '../Global/Global.dart';
class TypeController {
  List<TypeModel>? typeModels;
  String currentUri = mainURL + "/typePost";

  Future<List<TypeModel>> getAllPostType() async {
    var response = await http.get(
      Uri.parse(currentUri + "/getAllTypes"),
      // headers: {"Authorization": "Bearer " + MyApp.currentUser.getToken},
    );
    List<dynamic> sss = json.decode(response.body);
    List<TypeModel> list = [];
    for (int i = 0; i < sss.length; i++) {
      if (sss.isNotEmpty) {
        // print("********************");
        list.add(TypeModel.fromJson(sss[i]));
        // print(sss[i]);
        // print(list[i].id);
        // print("********************");
      }
    }
    return list;
  }
}
