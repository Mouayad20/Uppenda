import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:image_picker/image_picker.dart';
import 'package:material_design_icons_flutter/material_design_icons_flutter.dart';
import 'package:frontend/Body/BodyGroupButton.dart';
import 'package:frontend/Body/BodyPageButton.dart';
import 'package:frontend/Model/CommentModel.dart';
import 'package:frontend/Model/PostModel.dart';
import 'package:frontend/Model/ReactionModel.dart';
import 'package:frontend/Model/TypeModel.dart';
import 'package:frontend/Pages/profile.dart';
import 'package:frontend/Social/CreateGroup.dart';
import 'package:frontend/Social/CreatePage.dart';
import 'package:frontend/Social/Search.dart';
import 'package:frontend/Controllers/CommentController.dart';
import 'package:frontend/Controllers/PostController.dart';
import 'package:frontend/Controllers/ReactionController.dart';
import 'package:frontend/Controllers/TypeController.dart';
import 'package:frontend/Controllers/UserController.dart';
import '../main.dart';
import 'Social_Home.dart';

class UpdatePost extends StatefulWidget {
  PostModel postModel;

  UpdatePost({Key? key, required this.postModel}) : super(key: key);

  @override
  _UpdatePostState createState() => _UpdatePostState();
}

class _UpdatePostState extends State<UpdatePost> {
  PostController postController = PostController();
  UserController userController = UserController();
  TypeController typeController = TypeController();
  ReactionController reactionController = ReactionController();
  CommentController commentController = CommentController();
  List<ReactionModel> reactionModels = [];
  List<Column> reactionsWidget = [];
  TypeModel typeModel = TypeModel();
  CommentModel commentModel = CommentModel();
  bool notLoop = true;
  String? selectText;
  List<dynamic>? photoVideo;
  List<Image>? _images;
  ImagePicker picker = ImagePicker();
  List<TypeModel> list = [];
  List<PopupMenuItem> popMenuItems = [];
  final myController = TextEditingController();

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
            MyApp.currentUser = value;
          });
        });
      });
    });
    _images ??= [];
    photoVideo ??= [];
    typeController.getAllPostType().then((value) {
      setState(() {
        list = value;
      });
    });

    myController.addListener(_printLatestValue);
    myController.text = widget.postModel.content!;
    selectText = widget.postModel.type!.type;
    typeModel = widget.postModel.type!;
  }

  @override
  void dispose() {
    myController.dispose();
    super.dispose();
  }

  void _printLatestValue() {
    print('Second text field: ${myController.text}');
  }

  @override
  Widget build(BuildContext context) {
    if (notLoop) {
      for (var i = 0; i < list.length; i++) {
        popMenuItems.add(malaz(list[i]));
      }
      notLoop = false;
    }
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.white,
        centerTitle: true,
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
            MdiIcons.homeSearchOutline,
            size: 30,
            color: Colors.purple,
          ),
        ),
        title: const SizedBox(
          height: 40.0,
          child: Text(
            "Uppenda",
            style: TextStyle(
                letterSpacing: 4,
                fontFamily: 'DancingScript',
                fontSize: 30,
                fontWeight: FontWeight.w600),
          ),
        ),
        actions: const [
          Padding(
            padding: EdgeInsets.only(right: 13.0),
            child: Icon(
              MdiIcons.messageOutline,
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
                    selectText!,
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
                child: ListView(
                  children: [
                    TextField(
                      controller: myController,
                      textInputAction: TextInputAction.newline,
                      keyboardType: TextInputType.multiline,
                      maxLines: null,
                      onChanged: (text) {},
                    ),
                  ],
                ),
              ),
            ),
          ),
          Padding(
            padding: const EdgeInsets.fromLTRB(5.0, 38.0, 8.0, 8.0),
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
                        widget.postModel.setContent = myController.text.trim();
                        widget.postModel.setType = typeModel;
                        // print("_______________");
                        // print(widget.postModel.type.id);
                        // print(widget.postModel.type.typename);
                        // print("_______________");
                        postController.updatePost(widget.postModel);
                        Navigator.pop(
                          context,
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
                    MdiIcons.account,
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
              title: MyApp.currentUser!.getGroups.length == 0
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
                MyApp.currentUser!.getGroups.length,
                (index) {
                  return CupertinoActionSheetAction(
                    child: BodyGroupButton(
                        groupModel: MyApp.currentUser!.getGroups[index]),
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
              title: MyApp.currentUser!.getPages.length == 0
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
                MyApp.currentUser!.getPages.length,
                (index) {
                  return CupertinoActionSheetAction(
                    child: BodyPageButton(
                        pageModel: MyApp.currentUser!.getPages[index]),
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
              selectText = typeWidgetModel.type;
              typeModel.id = typeWidgetModel.getId();
              typeModel.type = typeWidgetModel.type;
            },
          );
        },
      ),
    );
  }

  getList() {
    return PopupMenuButton(
      itemBuilder: (context) => popMenuItems,
      child: const Icon(
        Icons.menu,
        size: 25,
        color: Colors.purple,
      ),
    );
  }
}
