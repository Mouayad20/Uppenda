import 'dart:io';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';
import 'package:frontend/Body/BodyGroupButton.dart';
import 'package:frontend/Body/BodyPageButton.dart';
import 'package:frontend/Body/UserFriend.dart';
import 'package:frontend/Controllers/UserController.dart';
import 'package:frontend/Model/PageModel.dart';
import 'package:frontend/Pages/profile.dart';
import 'package:frontend/Social/CreatePage.dart';
import 'package:frontend/Social/Search.dart';
import 'package:frontend/Social/Social_Home.dart';
import 'package:frontend/Controllers/PageController.dart';
import 'package:frontend/main.dart';

import '../Global/Global.dart';
import 'CreateGroup.dart';

class UpdatePage extends StatefulWidget {
  PageModel pageModel;

  UpdatePage({Key? key, required this.pageModel}) : super(key: key);

  @override
  _UpdatePageState createState() => _UpdatePageState();
}

class _UpdatePageState extends State<UpdatePage> {
  PageControler pageController = PageControler();
  UserController userController = UserController();
  File? _image;
  List<Image>? _images;
  ImagePicker picker = ImagePicker();
  final myController = TextEditingController();

  final myController2 = TextEditingController();
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
    myController.addListener(_printLatestValue);
    myController2.addListener(_printLatestValue);
    myController.text = widget.pageModel.name!;
    myController2.text = widget.pageModel.description!;
  }

  @override
  void dispose() {
    myController.dispose();
    myController2.dispose();
    super.dispose();
  }

  void _printLatestValue() {
    print('Second text field: ${myController.text}');
  }

  // void _printLatestValue2() {
  //   print('Second text field: ${myController2.text}');
  // }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.white,
        centerTitle: true,
        elevation: 1.0,
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
          Center(
            child: Padding(
              padding: const EdgeInsets.all(8.0),
              child: Stack(
                children: [
                  Container(
                    decoration: BoxDecoration(
                      color: Colors.white70,
                      borderRadius: const BorderRadius.only(
                        topLeft: Radius.circular(15),
                        topRight: Radius.circular(15),
                        bottomLeft: Radius.circular(15),
                        bottomRight: Radius.circular(15),
                      ),
                      boxShadow: [
                        BoxShadow(
                          color: Colors.purple[100]!,
                          spreadRadius: 4,
                          blurRadius: 0,
                          offset: const Offset(0, 0),
                        ),
                      ],
                    ),
                    width: 250,
                    height: 190,
                    child: _image == null
                        ? const Icon(
                            Icons.description,
                            size: 80,
                            color: Colors.purple,
                          )
                        : Image(
                            image: FileImage(_image!),
                            fit: BoxFit.fill,
                          ),
                  ),
                  Positioned(
                    left: 200,
                    top: 140,
                    child: IconButton(
                      icon: const Icon(
                        Icons.edit,
                        color: Colors.purple,
                      ),
                      onPressed: () async {
                        PickedFile? pickedFile = await picker.getImage(
                            source: ImageSource.gallery, imageQuality: 50);

                        File image = File(pickedFile!.path);
                        setState(
                          () {
                            _image = image;
                            // widget.groupModel.setImage=_image;
                          },
                        );
                      },
                    ),
                  ),
                ],
              ),
            ),
          ),
          const SizedBox(
            height: 10,
          ),
          const Padding(
            padding: EdgeInsets.only(top: 3.0, left: 15, bottom: 6.0),
            child: Text(
              "Page's Name",
              style: TextStyle(
                  fontSize: 15,
                  color: Colors.purple,
                  decorationThickness: 2,
                  fontFamily: 'Merienda'),
            ),
          ),
          Container(
            padding: const EdgeInsets.fromLTRB(10, 2, 10, 2),
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(25),
              border: Border.all(color: Colors.purple),
            ),
            child: TextField(
              controller: myController,
              textInputAction: TextInputAction.newline,
              keyboardType: TextInputType.multiline,
              maxLines: null,
              onChanged: (text) {},
            ),
          ),
          const SizedBox(
            height: 20,
          ),
          const Padding(
            padding: EdgeInsets.only(top: 3.0, left: 15, bottom: 6.0),
            child: Text(
              "Page's Description",
              style: TextStyle(
                  fontSize: 15,
                  color: Colors.purple,
                  decorationThickness: 2,
                  fontFamily: 'Merienda'),
            ),
          ),
          Container(
            height: 160,
            padding: const EdgeInsets.fromLTRB(10, 2, 10, 2),
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(25),
              border: Border.all(color: Colors.purple),
            ),
            child: TextField(
              controller: myController2,
              textInputAction: TextInputAction.newline,
              keyboardType: TextInputType.multiline,
              maxLines: null,
              onChanged: (text) {},
            ),
          ),
          const SizedBox(
            height: 5,
          ),
          Padding(
            padding: const EdgeInsets.only(left: 12),
            child: Row(
              children: [
                const Text(
                  "To add people to this page",
                  style: TextStyle(
                      fontSize: 15,
                      color: Colors.purple,
                      decorationThickness: 2,
                      fontFamily: 'Merienda'),
                ),
                IconButton(
                    icon: const Icon(
                      Icons.add,
                      size: 20,
                      color: Colors.purple,
                    ),
                    onPressed: () {
                      freinds();
                    })
              ],
            ),
          ),
          Center(
            child: Padding(
              padding: const EdgeInsets.only(top: 17),
              child: Container(
                width: 50,
                height: 40,
                color: const Color.fromRGBO(233, 207, 236, 1),
                child: IconButton(
                    icon: const Icon(
                      Icons.done_outline_rounded,
                      size: 30,
                      color: Colors.purple,
                    ),
                    onPressed: () {
                      // print(">>>>>>>>>>     " + widget.pageModel.getId);
                      widget.pageModel.setName = myController.text.trim();
                      widget.pageModel.setDescription =
                          myController2.text.trim();
                      // widget.groupModel.setImage=_image.toString();
                      pageController.updateInformation(widget.pageModel);
                      Navigator.pop(
                        context,
                      );
                      Navigator.pop(
                        context,
                      );
                    }),
              ),
            ),
          ),
          const Center(
            child: Text(
              " Done",
              style: TextStyle(color: Colors.purple, fontFamily: 'Merienda'),
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
                  onPressed: () {},
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

  freinds() {
    showCupertinoModalPopup(
      context: context,
      builder: (BuildContext context) => Padding(
        padding: const EdgeInsets.all(8.0),
        child: CupertinoActionSheet(
          title: currentUser!.friends!.isEmpty
              ? const Text(
                  "No friends",
                  style: TextStyle(
                    letterSpacing: 3,
                    color: Colors.purple,
                    fontSize: 30,
                    fontFamily: 'DancingScript',
                    fontWeight: FontWeight.w600,
                  ),
                )
              : const Text(
                  "Friends",
                  style: TextStyle(
                    letterSpacing: 3,
                    color: Colors.purple,
                    fontSize: 30,
                    fontFamily: 'DancingScript',
                    fontWeight: FontWeight.w600,
                  ),
                ),
          actions: List.generate(
            currentUser!.friends!.length,
            (index) {
              return CupertinoActionSheetAction(
                child: UserFriend(friend: currentUser!.friends![index]),
                onPressed: () {},
              );
            },
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
}
