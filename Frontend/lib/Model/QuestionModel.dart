class QuestionModel {
  String? id;
  String? question;

  QuestionModel({this.id, this.question});

  QuestionModel.fromJson(Map<String, dynamic> json) {
    id = json["id"];
    question = json["question"];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['id'] = id;
    data['question'] = question;
    return data;
  }
}
