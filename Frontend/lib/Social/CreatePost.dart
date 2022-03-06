import 'dart:io';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:image_picker/image_picker.dart';
import 'package:frontend/Body/BodyGroupButton.dart';
import 'package:frontend/Body/BodyPageButton.dart';
import 'package:frontend/Controllers/UserController.dart';
import 'package:frontend/Model/MediaModel.dart';
import 'package:frontend/Model/PostModel.dart';
import 'package:frontend/Model/TypeModel.dart';
import 'package:frontend/Social/CreateGroup.dart';
import 'package:frontend/Social/CreatePage.dart';
import 'package:frontend/Social/Search.dart';
import 'package:frontend/Pages/profile.dart';
import 'package:frontend/Controllers/PostController.dart';
import 'package:frontend/Controllers/TypeController.dart';
import 'package:frontend/main.dart';
import '../Global/Global.dart';
import 'Social_Home.dart';

class CreatePost extends StatefulWidget {
  PostModel? postModel;

  CreatePost({Key? key, this.postModel}) : super(key: key);

  @override
  _CreatePostState createState() => _CreatePostState();
}

class _CreatePostState extends State<CreatePost> {
  TypeController typeController = TypeController();
  UserController userController = UserController();
  PostController postController = PostController();
  List<TypeModel>? list;
  List<PopupMenuItem> popupMenuItems = [];
  bool notLoop = true;
  TextEditingController postContentController = TextEditingController();
  String selectText = " Select Your Post Type..";
  File? _image;
  List<Image>? _images;
  File? _cameraImage;
  File? _video;
  List<File>? _Videos;
  ImagePicker picker = ImagePicker();

  List<MediaModel> mediaModels = [];
  String? profileId;

  Future<String> getUserFromCache() async {
    // SharedPreferences cache = await SharedPreferences.getInstance();
    // return cache.getString('id');
    return "1";
  }

  @override
  void initState() {
    super.initState();
    getUserFromCache().then((idFromCash) {
      setState(() {
        profileId = idFromCash;
        userController.getUserById(idFromCash).then((value) {
          setState(() {
            currentUser = value;
          });
        });
      });
    });
    _images ??= [];
    widget.postModel = PostModel();
    typeController.getAllPostType().then((value) {
      setState(() {
        list = value;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    if (list != null && notLoop) {
      for (var i = 0; i < list!.length; i++) {
        popupMenuItems.add(malaz(list![i]));
      }
      notLoop = false;
    }
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.white,
        centerTitle: true,
        title: SizedBox(
          height: 40.0,
          child: InkWell(
            child: const Text(
              "Uppenda",
              style: TextStyle(
                  letterSpacing: 4,
                  fontFamily: 'DancingScript',
                  fontSize: 30,
                  fontWeight: FontWeight.w600,
                  color: Colors.purple),
            ),
            onTap: () {},
          ),
        ),
        elevation: 1.0,
        leading: IconButton(
          onPressed: () {
            Navigator.push(
              context,
              MaterialPageRoute(
                builder: (context) => Search(),
              ),
            );
          },
          icon: const Icon(
            Icons.cake_rounded,
            // MdiIcons.homeSearchOutline,
            size: 30,
            color: Colors.purple,
          ),
        ),
        actions: const [
          Padding(
            padding: EdgeInsets.only(right: 13.0),
            child: Icon(
              Icons.cake_rounded,
              // MdiIcons.messageOutline,
              size: 28,
              color: Colors.purple,
            ),
          ),
        ],
      ),
      body: ListView(
        children: [
          Padding(
            padding: const EdgeInsets.fromLTRB(12.0, 8.0, 8.0, 8.0),
            child: Row(
              children: [
                Container(
                  width: 40,
                  height: 40,
                  color: const Color.fromRGBO(233, 207, 236, 1),
                  child: getList(),
                ),
                Padding(
                  padding: const EdgeInsets.fromLTRB(12.0, 8.0, 8.0, 8.0),
                  child: Text(
                    selectText,
                    style: TextStyle(
                        color: Colors.purple[200], fontFamily: 'Merienda'),
                  ),
                ),
              ],
            ),
          ),
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: Container(
              height: 200,
              decoration: const BoxDecoration(
                color: Colors.white,
                borderRadius: BorderRadius.only(
                  topLeft: Radius.circular(10),
                  topRight: Radius.circular(10),
                  bottomLeft: Radius.circular(10),
                  bottomRight: Radius.circular(10),
                ),
                boxShadow: [
                  BoxShadow(
                    color: Color.fromRGBO(233, 207, 236, 1),
                    spreadRadius: 4,
                    blurRadius: 7,
                    offset: Offset(0, 0),
                  ),
                ],
              ),
              child: Padding(
                padding: const EdgeInsets.fromLTRB(8.0, 0.0, 8.0, 8.0),
                child: TextField(
                  controller: postContentController,
                  maxLines: 10,
                  decoration: InputDecoration(
                    border: InputBorder.none,
                    hintText: "Write Post Text Here...",
                    hintStyle: TextStyle(
                        fontSize: 20,
                        color: Colors.purple[200],
                        decorationThickness: 2,
                        fontFamily: 'Merienda'),
                  ),
                ),
              ),
            ),
          ),
          Padding(
            padding: const EdgeInsets.fromLTRB(12.0, 8.0, 8.0, 8.0),
            child: Row(
              children: [
                Container(
                  width: 40,
                  height: 40,
                  color: const Color.fromRGBO(233, 207, 236, 1),
                  child: getListCameraGallery(),
                ),
                Padding(
                  padding: const EdgeInsets.fromLTRB(12.0, 8.0, 8.0, 8.0),
                  child: Text(
                    " To Upload Photo To Your Post..",
                    style: TextStyle(
                        color: Colors.purple[200], fontFamily: 'Merienda'),
                  ),
                ),
              ],
            ),
          ),
          Padding(
            padding: const EdgeInsets.fromLTRB(12.0, 8.0, 8.0, 8.0),
            child: Row(
              children: [
                Container(
                  width: 40,
                  height: 40,
                  color: const Color.fromRGBO(233, 207, 236, 1),
                  child: getListCameraGalleryVideo(),
                ),
                Padding(
                  padding: const EdgeInsets.fromLTRB(12.0, 8.0, 8.0, 8.0),
                  child: Text(
                    " To Upload Video To Your Post..",
                    style: TextStyle(
                        color: Colors.purple[200], fontFamily: 'Merienda'),
                  ),
                ),
              ],
            ),
          ),
          _images != null || _images!.isNotEmpty
              ? SizedBox(
                  height: 110.0,
                  width: 350.0,
                  child: ListView.builder(
                    scrollDirection: Axis.horizontal,
                    itemCount: _images!.length,
                    itemBuilder: (context, index) {
                      // return _images![index];
                      return Container();
                    },
                  ),
                )
              : const Text("no Images selected"),
          _Videos != null
              ? SizedBox(
                  height: 200.0,
                  width: 200.0,
                  child: ListView.builder(
                    scrollDirection: Axis.horizontal,
                    itemCount: _Videos!.length,
                    itemBuilder: (context, index) {
                      return const CircularProgressIndicator();
                      // _Videos != null
                      //     ? VideoPlayerWidget2(_Videos![index])
                    },
                  ),
                )
              : const Text(""),
          Padding(
            padding: const EdgeInsets.fromLTRB(5.0, 8.0, 8.0, 8.0),
            child: Column(
              children: [
                Container(
                  width: 60,
                  height: 40,
                  color: const Color.fromRGBO(233, 207, 236, 1),
                  child: IconButton(
                      icon: const Icon(
                        Icons.done_outline_rounded,
                        size: 30,
                        color: Colors.purple,
                      ),
                      onPressed: () {
                        widget.postModel!.setContent =
                            postContentController.text.trim();
                        widget.postModel!.setMedia = mediaModels;
                        widget.postModel!.setCreatedAt = DateTime.now();
                        postController.addPostOnProfile(widget.postModel!);
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) => SocialHome(),
                          ),
                        );
                      }),
                ),
                const Padding(
                  padding: EdgeInsets.fromLTRB(5.0, 8.0, 8.0, 8.0),
                  child: Text(
                    " Done",
                    style:
                        TextStyle(color: Colors.purple, fontFamily: 'Merienda'),
                  ),
                ),
              ],
            ),
          ),
        ],
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerDocked,
      bottomNavigationBar: Container(
        color: Colors.white,
        height: 55.0,
        child: BottomAppBar(
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceAround,
            children: [
              Padding(
                padding: const EdgeInsets.fromLTRB(0.0, 5.0, 2.0, 0.0),
                child: IconButton(
                  onPressed: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => Profile(
                          user_id: profileId!,
                        ),
                      ),
                    );
                  },
                  icon: const Icon(
                    Icons.cake_rounded,
                    // MdiIcons.account,
                    size: 30,
                    color: Colors.purple,
                  ),
                ),
              ),
              Padding(
                padding: const EdgeInsets.fromLTRB(0.0, 5.0, 2.0, 0.0),
                child: IconButton(
                  icon: const Icon(Icons.supervised_user_circle,
                      color: Colors.purple, size: 30),
                  onPressed: () {
                    showGroupsButton();
                  },
                ),
              ),
              FloatingActionButton(
                  heroTag: 2,
                  child: const Icon(
                    Icons.add_circle_sharp,
                    size: 40,
                    color: Color.fromRGBO(233, 207, 236, 1),
                  ),
                  onPressed: () {},
                  backgroundColor: Colors.purple),
              Padding(
                padding: const EdgeInsets.fromLTRB(0.0, 5.0, 5.0, 0.0),
                child: IconButton(
                  icon: const Icon(
                    Icons.description,
                    color: Colors.purple,
                    size: 30,
                  ),
                  onPressed: () {
                    showPagesButton();
                  },
                ),
              ),
              Padding(
                padding: const EdgeInsets.fromLTRB(0.0, 5.0, 3.0, 0.0),
                child: IconButton(
                  icon: const Icon(Icons.home, color: Colors.purple, size: 30),
                  onPressed: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => SocialHome(),
                      ),
                    );
                  },
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  showGroupsButton() {
    showCupertinoModalPopup(
      context: context,
      builder: (BuildContext context) => Padding(
        padding: const EdgeInsets.all(8.0),
        child: Stack(
          children: [
            CupertinoActionSheet(
              title: currentUser!.getGroups.length == 0
                  ? const Text(
                      "No Groups",
                      style: TextStyle(
                        letterSpacing: 3,
                        color: Colors.purple,
                        fontSize: 30,
                        fontFamily: 'DancingScript',
                        fontWeight: FontWeight.w600,
                      ),
                    )
                  : const Text(
                      "Groups",
                      style: TextStyle(
                        letterSpacing: 3,
                        color: Colors.purple,
                        fontSize: 30,
                        fontFamily: 'DancingScript',
                        fontWeight: FontWeight.w600,
                      ),
                    ),
              actions: List.generate(
                currentUser!.getGroups.length,
                (index) {
                  return CupertinoActionSheetAction(
                    child: BodyGroupButton(
                        groupModel: currentUser!.getGroups[index]),
                    onPressed: () {},
                  );
                },
              ),
            ),
            Positioned(
              top: 50,
              left: 50,
              child: SizedBox(
                width: 40,
                height: 40,
                child: FloatingActionButton(
                  heroTag: 3,
                  child: const Icon(
                    Icons.add,
                    size: 25,
                    color: Color.fromRGBO(233, 207, 236, 1),
                  ),
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(15),
                  ),
                  onPressed: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => CreateGroup(),
                      ),
                    );
                  },
                  backgroundColor: Colors.purple[200],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }

  showPagesButton() {
    showCupertinoModalPopup(
      context: context,
      builder: (BuildContext context) => Padding(
        padding: const EdgeInsets.all(8.0),
        child: Stack(
          children: [
            CupertinoActionSheet(
              title: currentUser!.getPages.length == 0
                  ? const Text(
                      "No Pages",
                      style: TextStyle(
                        letterSpacing: 3,
                        color: Colors.purple,
                        fontSize: 30,
                        fontFamily: 'DancingScript',
                        fontWeight: FontWeight.w600,
                      ),
                    )
                  : const Text(
                      "Pages",
                      style: TextStyle(
                        letterSpacing: 3,
                        color: Colors.purple,
                        fontSize: 30,
                        fontFamily: 'DancingScript',
                        fontWeight: FontWeight.w600,
                      ),
                    ),
              actions: List.generate(
                currentUser!.getPages.length,
                (index) {
                  return CupertinoActionSheetAction(
                    child: BodyPageButton(
                        pageModel: currentUser!.getPages[index]),
                    onPressed: () {},
                  );
                },
              ),
            ),
            Positioned(
              top: 50,
              left: 50,
              child: SizedBox(
                width: 40,
                height: 40,
                child: FloatingActionButton(
                  heroTag: 3,
                  child: const Icon(
                    Icons.add,
                    size: 25,
                    color: Color.fromRGBO(233, 207, 236, 1),
                  ),
                  shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(15)),
                  onPressed: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => CreatePage(),
                      ),
                    );
                  },
                  backgroundColor: Colors.purple[200],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }

  malaz(TypeModel typeWidgetModel) {
    return PopupMenuItem(
      child: TextButton(
        child: Text(
          typeWidgetModel.type!,
          style: const TextStyle(color: Colors.purple, fontFamily: 'Merienda'),
        ),
        onPressed: () {
          setState(
            () {
              selectText = typeWidgetModel.type!;
              TypeModel typeModel = TypeModel();
              typeModel.id = typeWidgetModel.getId();
              typeModel.type = typeWidgetModel.type;
              widget.postModel!.type = typeModel;
              // print(">>>>>>>  " + typeWidgetModel.getId());
            },
          );
        },
      ),
    );
  }

  getList() {
    return PopupMenuButton(
      itemBuilder: (context) => popupMenuItems,
      child: const Icon(
        Icons.menu,
        size: 25,
        color: Colors.purple,
      ),
    );
  }

  getListCameraGallery() {
    return PopupMenuButton(
      itemBuilder: (context) => [
        PopupMenuItem(
          child: TextButton(
            child: const Text(
              "Camera",
              style: TextStyle(color: Colors.purple, fontFamily: 'Merienda'),
            ),
            onPressed: () async {
              PickedFile? pickedFile = await picker.getImage(
                  source: ImageSource.camera, imageQuality: 50);

              File image = File(pickedFile!.path);

              setState(
                () {
                  _cameraImage = image;
                  _images!.add(
                    Image.file(image),
                  );
                  MediaModel mediaModel = MediaModel();
                  mediaModel.setImage = image.path;
                  mediaModel.setType = "image";
                  mediaModels.add(mediaModel);
                },
              );
            },
          ),
        ),
        PopupMenuItem(
          child: TextButton(
            child: const Text(
              "Gallery",
              style: TextStyle(color: Colors.purple, fontFamily: 'Merienda'),
            ),
            onPressed: () async {
              PickedFile? pickedFile = await picker.getImage(
                  source: ImageSource.gallery, imageQuality: 50);
              File image;

              image = File(pickedFile!.path);
              setState(
                () {
                  _image = image;
                  _images!.add(
                    Image.file(image),
                  );
                  MediaModel mediaModel = MediaModel();
                  mediaModel.setImage = image.path;
                  mediaModel.setType = "image";
                  mediaModels.add(mediaModel);
                },
              );
            },
          ),
        ),
      ],
      child: const Icon(
        Icons.add_a_photo,
        size: 25,
        color: Colors.purple,
      ),
    );
  }

  getListCameraGalleryVideo() {
    return PopupMenuButton(
      itemBuilder: (context) => [
        PopupMenuItem(
          child: TextButton(
            child: const Text(
              "Camera",
              style: TextStyle(color: Colors.purple, fontFamily: 'Merienda'),
            ),
            onPressed: () async {
              PickedFile? pickedFile =
                  await picker.getVideo(source: ImageSource.camera);

              _video = File(pickedFile!.path);
              if (_video != null) {
                _Videos ??= [];
                setState(
                  () {
                    _Videos!.add(_video!);
                    MediaModel mediaModel = MediaModel();
                    mediaModel.setImage = _video!.path;
                    mediaModel.setType = "video";
                    mediaModels.add(mediaModel);
                  },
                );
              }
            },
          ),
        ),
        PopupMenuItem(
          child: TextButton(
            child: const Text(
              "Gallery",
              style: TextStyle(color: Colors.purple, fontFamily: 'Merienda'),
            ),
            onPressed: () async {
              PickedFile? pickedFile =
                  await picker.getVideo(source: ImageSource.gallery);

              _video = File(pickedFile!.path);
              if (_video != null) {
                _Videos ??= [];
                setState(
                  () {
                    _Videos!.add(_video!);
                    MediaModel mediaModel = MediaModel();
                    mediaModel.setImage = _video!.path;
                    mediaModel.setType = "video";
                    mediaModels.add(mediaModel);
                  },
                );
              }
            },
          ),
        ),
      ],
      child: const Icon(
        Icons.video_collection,
        size: 25,
        color: Colors.purple,
      ),
    );
  }
}
