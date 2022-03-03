import 'PostModel.dart';

class MediaModel {
  String? id;
  String? path;
  String? type;
  PostModel? postModel;

  MediaModel({this.id, this.path, this.type, this.postModel});

  MediaModel.fromJson(Map<String, dynamic> json) {
    id = json["id"].toString();
    path = json["path"];
    type = json["type"];
    if (json["postModel"] != null) {
      postModel = PostModel.fromJson(json["postModel"]);
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['id'] = id;
    data['path'] = path;
    data['type'] = type;
    if (postModel != null) data['postModel'] = postModel!.toJson();
    return data;
  }

  get getId => id;

  set setId(id) => this.id = id;

  get getImage => path;

  set setImage(path) => this.path = path;

  get getType => type;

  set setType(type) => this.type = type;

  get getPostModel => postModel;

  set setPostModel(postModel) => this.postModel = postModel;
}
