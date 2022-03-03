class TypeModel {
  String? id;
  String? type;

  TypeModel({this.id, this.type});

  TypeModel.fromJson(Map<String, dynamic> json) {
    id = json["id"].toString();
    type = json["type"];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['id'] = id;
    data['type'] = type;
    return data;
  }

  get getId => id;

  get getType => type;
}
