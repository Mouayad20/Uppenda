import 'dart:convert';
import 'dart:io';

import 'package:frontend/Model/ReactionModel.dart';
import 'package:http/http.dart' as http;
import '../main.dart';

import '../Global/Global.dart';

class ReactionController {
  List<ReactionModel>? reactionModels;
  ReactionModel? reactionModel;
  String currentUri = mainURL + "/typeReaction";

  Future<ReactionModel?> reaction(String pId, String uId, String rId) async {
    int p = int.parse(pId);
    int u = int.parse(uId);
    int r = int.parse(rId);
    final response = await http
        .post(Uri.parse(currentUri + "/reaction/p=$p/u=$u/r=$r"), headers: {
      "Content-Type": "application/json",
      // "Authorization": "Bearer " + MyApp.currentUser.getToken
    });

    this.reactionModel = ReactionModel.fromJson(json.decode(response.body));

    print("\n***************\n");
    print(response.body);
    print("\n***************\n");

    return this.reactionModel;
  }

  Future<String> unReaction(String lId) async {
    int l = int.parse(lId);
    final response =
        await http.post(Uri.parse(currentUri + "/unReaction/l=$lId"), headers: {
      "Content-Type": "application/json",
      // "Authorization": "Bearer " + MyApp.currentUser.getToken
    });

    print("\n***************\n");
    print(response.body);
    print("\n***************\n");
    return response.body;
  }

  Future<List<ReactionModel>> getAllReactionType() async {
    var response = await http.get(
      Uri.parse(currentUri + "/getAllTypes"),
      // headers: {"Authorization": "Bearer " + MyApp.currentUser.getToken},
    );
    List<dynamic> sss = json.decode(response.body);
    List<ReactionModel> list = [];
    for (int i = 0; i < sss.length; i++) {
      if (sss.isNotEmpty) {
        // print("********************");
        list.add(ReactionModel.fromJson(sss[i]));
        //   print(sss[i]);
        //   print(list[i].id);
        //   print("********************");
      }
    }
    return list;
  }
}
