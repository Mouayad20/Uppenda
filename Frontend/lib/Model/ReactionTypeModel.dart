import 'package:flutter/material.dart';

class ReactionTypeModel {
  String? id;
  String? reactionType;
  String? colorName;

  Color color = const Color(0x000000c8);

  ReactionTypeModel({this.id, this.reactionType, this.colorName}) {
    color = Color(int.parse(colorName!));
  }

  ReactionTypeModel.fromJson(Map<String, dynamic> json) {
    id = json["id"].toString();
    reactionType = json["reactionType"];
    colorName = json["colorName"];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['id'] = id;
    data['colorName'] = colorName;
    data['reactionType'] = reactionType;
    return data;
  }

  get getColorName => colorName;

  set setColorName(String colorName) => this.colorName = colorName;

  get getId => id;

  get getColor => color;

  get getName => reactionType;
}
