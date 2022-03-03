import 'package:frontend/Model/QuestionModel.dart';

import 'UserModel.dart';

class AnswerModel {
  String? id;
  String? answer;
  UserModel? user;
  QuestionModel? question;

  AnswerModel(
      {required this.id,
      required this.answer,
      required this.question,
      required this.user});

  AnswerModel.fromJson(Map<String, dynamic> json) {
    id = json["id"];
    answer = json["answer"];
    if (json["user"] != null) user = UserModel.fromJson(json["user"]);
    if (json["question"] != null) {
      question = QuestionModel.fromJson(json["question"]);
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['id'] = id;
    data['answer'] = answer;
    if (user != null) data['user'] = user!.toJson();
    if (question != null) data['question'] = question!.toJson();
    return data;
  }

  get getId => id;

  set setId(id) => this.id = id;

  get getAnswer => answer;

  set setAnswer(answer) => this.answer = answer;

  get getQuestion => question;

  set setQuestion(question) => this.question = question;

  get getUser => user;

  set setUsers(user) => this.user = user;
}
