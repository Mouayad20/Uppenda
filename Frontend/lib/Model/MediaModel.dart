import 'PostModel.dart';

class MediaModel {
  String id;
  String path;
  String type;
  PostModel postModel;

  MediaModel({this.id, this.path, this.type, this.postModel});

  MediaModel.fromJson(Map<String, dynamic> json) {
    this.id = json["id"].toString();
    this.path = json["path"];
    this.type = json["type"];
    if (json["postModel"] != null)
      this.postModel = PostModel.fromJson(json["postModel"]);
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['path'] = this.path;
    data['type'] = this.type;
    if (this.postModel != null) data['postModel'] = this.postModel.toJson();
    return data;
  }

  get getId => this.id;

  set setId(id) => this.id = id;

  get getImage => this.path;

  set setImage(path) => this.path = path;

  get getType => this.type;

  set setType(type) => this.type = type;

  get getPostModel => this.postModel;

  set setPostModel(postModel) => this.postModel = postModel;
}
