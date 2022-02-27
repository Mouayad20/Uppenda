class TypeModel {
  String id;
  String type;

  TypeModel({this.id, this.type});

  TypeModel.fromJson(Map<String, dynamic> json) {
    this.id = json["id"].toString();
    this.type = json["type"];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['type'] = this.type;
    return data;
  }

  get getId => id;

  get getType => type;
}
