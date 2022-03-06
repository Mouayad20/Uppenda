// ignore_for_file: non_constant_identifier_names, import_of_legacy_library_into_null_safe

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:frontend/Body/BodyGroupButton.dart';
import 'package:frontend/Body/BodyPageButton.dart';
import 'package:frontend/Body/PostBody.dart';
import 'package:frontend/Controllers/PageController.dart';
import 'package:frontend/Controllers/PostController.dart';
import 'package:frontend/Pages/profile.dart';
import 'package:frontend/Social/CreateGroup.dart';
import 'package:frontend/Social/CreatePage.dart';
import 'package:frontend/Social/CreatePost.dart';
import 'package:frontend/Social/Search.dart';
import 'package:frontend/Social/Social_Home.dart';
import 'package:frontend/Social/UpdatePage.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:frontend/Model/PageModel.dart';
import 'package:frontend/Model/UserModel.dart';
import 'package:frontend/Widgets/UserInfo.dart';

import '../Global/Global.dart';
import '../main.dart';
import 'MainPage.dart';

class Page1 extends StatefulWidget {
  final String page_id;

  const Page1({Key? key, required this.page_id}) : super(key: key);

  @override
  _Page1State createState() => _Page1State();
}

class _Page1State extends State<Page1> {
  final PageControler _pageController = PageControler();
  PageModel? _pageModel;
  PostController postController = PostController();

  String? profileId;

  Future<String> getUserFromCache() async {
    // SharedPreferences cache = await SharedPreferences.getInstance();
    return ""; // cache.getString('id');
  }

  @override
  void initState() {
    super.initState();
    getUserFromCache().then((idFromCash) {
      setState(() {
        profileId = idFromCash;
      });
    });
    _pageController.getPageById1(widget.page_id).then((value) {
      setState(() {
        _pageModel = value;
        postController.getAllPostsForPage(_pageModel!.getId).then((value) {
          setState(() {
            _pageModel!.setPostModels = value;
            // print("___________");
            // print(_pageModel!.getPostModels.length.toString());
            // print("___________");
          });
        });
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    double width = MediaQuery.of(context).size.width;
    double height = MediaQuery.of(context).size.height;
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
            Icons.cake_rounded,
            // MdiIcons.homeSearchOutline,
            size: 30,
            color: Colors.purple,
          ),
        ),
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
        actions: [
          Padding(
            padding: const EdgeInsets.only(right: 13.0),
            child: IconButton(
                onPressed: () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (context) => ChatPage(),
                    ),
                  );
                },
                icon: const Icon(
                  Icons.cake_rounded,
                  // MdiIcons.messageOutline,
                  size: 28,
                  color: Colors.purple,
                )),
          ),
        ],
      ),
      body: _pageModel == null
          ? const Center(
              child: CircularProgressIndicator(),
            )
          : SingleChildScrollView(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.stretch,
                mainAxisAlignment: MainAxisAlignment.center,
                children: <Widget>[
                  Padding(
                    padding: const EdgeInsets.only(top: 15, bottom: 20),
                    child: Center(
                      child: Text(
                        _pageModel!.getName,
                        style: TextStyle(
                          color: Colors.purple,
                          fontFamily: 'Merienda',
                          fontSize: 32,
                          shadows: [
                            Shadow(
                              blurRadius: 5,
                              color: Colors.purple[100]!,
                              offset: const Offset(5.0, 5.0),
                            ),
                          ],
                        ),
                      ),
                    ),
                  ),
                  Container(
                    height: height * 0.31,
                    width: double.infinity,
                    decoration: BoxDecoration(
                      image: DecorationImage(
                        image: _pageModel!.getImage == null
                            ? const AssetImage("images/download.jpg")
                            : Image.network(
                                mainURL +
                                    _pageModel!.getImage
                                        .toString()
                                        .replaceAll("\\", "/"),
                                // headers: {
                                //   "Authorization":
                                //       "Bearer " + currentUser!.getToken
                                // },
                              ).image,
                        fit: BoxFit.cover,
                      ),
                      borderRadius: BorderRadius.circular(90),
                      color: Colors.white60,
                      boxShadow: const [
                        BoxShadow(
                          color: Color.fromRGBO(233, 177, 236, 1),
                          spreadRadius: 4,
                          blurRadius: 8,
                          offset: Offset(
                            0,
                            0,
                          ),
                        ),
                      ],
                    ),
                  ),
                  const SizedBox(
                    height: 20,
                  ),
                  FutureBuilder<bool>(
                      future: _pageController.isUserAdmin(_pageModel!),
                      builder: (context, snapshot) {
                        if (snapshot.data == null) return const SizedBox();
                        if (snapshot.data != null) {
                          return Row(
                            mainAxisSize: MainAxisSize.max,
                            mainAxisAlignment: MainAxisAlignment.spaceAround,
                            children: <Widget>[
                              Column(
                                children: [
                                  Card(
                                    shape: RoundedRectangleBorder(
                                        borderRadius:
                                            BorderRadius.circular(50)),
                                    color: Colors.purple[200],
                                    child: Container(
                                      decoration: const BoxDecoration(
                                        color: Colors.purple,
                                        borderRadius: BorderRadius.only(
                                            topLeft: Radius.circular(40),
                                            topRight: Radius.circular(40),
                                            bottomLeft: Radius.circular(40),
                                            bottomRight: Radius.circular(40)),
                                        boxShadow: [
                                          BoxShadow(
                                            color: Color.fromRGBO(
                                                233, 177, 236, 1),
                                            spreadRadius: 4,
                                            blurRadius: 8,
                                            offset: Offset(
                                              0,
                                              0,
                                            ),
                                          ),
                                        ],
                                      ),
                                      child: Padding(
                                        padding: const EdgeInsets.symmetric(
                                            horizontal: 10),
                                        child: Column(
                                          mainAxisAlignment:
                                              MainAxisAlignment.spaceAround,
                                          mainAxisSize: MainAxisSize.max,
                                          children: [
                                            IconButton(
                                              icon: const Icon(
                                                Icons.delete,
                                              ),
                                              iconSize: 25,
                                              onPressed: () {
                                                _pageController
                                                    .deletePage(_pageModel!);
                                                Navigator.push(
                                                  context,
                                                  MaterialPageRoute(
                                                    builder: (context) =>
                                                        SocialHome(),
                                                  ),
                                                );
                                              },
                                              color: Colors.white,
                                            ),
                                          ],
                                        ),
                                      ),
                                    ),
                                  ),
                                  const SizedBox(
                                    height: 3,
                                  ),
                                  Text(
                                    "Delete",
                                    style: TextStyle(
                                        color: Colors.purple[400],
                                        fontFamily: 'Merienda'),
                                  ),
                                ],
                              ),
                              Column(
                                children: [
                                  Card(
                                    shape: RoundedRectangleBorder(
                                        borderRadius:
                                            BorderRadius.circular(50)),
                                    color: Colors.purple[200],
                                    child: Container(
                                      decoration: const BoxDecoration(
                                          color: Colors.purple,
                                          borderRadius: BorderRadius.only(
                                              topLeft: Radius.circular(40),
                                              topRight: Radius.circular(40),
                                              bottomLeft: Radius.circular(40),
                                              bottomRight: Radius.circular(40)),
                                          boxShadow: [
                                            BoxShadow(
                                                color: Color.fromRGBO(
                                                    233, 177, 236, 1),
                                                spreadRadius: 4,
                                                blurRadius: 8,
                                                offset: Offset(0, 0)),
                                          ]),
                                      child: Padding(
                                        padding: const EdgeInsets.symmetric(
                                            horizontal: 10),
                                        child: Column(
                                          mainAxisAlignment:
                                              MainAxisAlignment.spaceAround,
                                          mainAxisSize: MainAxisSize.max,
                                          children: [
                                            IconButton(
                                              icon: const Icon(
                                                Icons.supervised_user_circle,
                                              ),
                                              iconSize: 25,
                                              onPressed: () {
                                                showMembers(context,
                                                    _pageModel!.getMembers);
                                              },
                                              color: Colors.white,
                                            ),
                                          ],
                                        ),
                                      ),
                                    ),
                                  ),
                                  const SizedBox(
                                    height: 3,
                                  ),
                                  Text(
                                    "Members",
                                    style: TextStyle(
                                        color: Colors.purple[400],
                                        fontFamily: 'Merienda'),
                                  ),
                                ],
                              ),
                              Column(
                                children: [
                                  Card(
                                    shape: RoundedRectangleBorder(
                                        borderRadius:
                                            BorderRadius.circular(50)),
                                    color: Colors.purple[200],
                                    child: Container(
                                      decoration: const BoxDecoration(
                                          color: Colors.purple,
                                          borderRadius: BorderRadius.only(
                                              topLeft: Radius.circular(40),
                                              topRight: Radius.circular(40),
                                              bottomLeft: Radius.circular(40),
                                              bottomRight: Radius.circular(40)),
                                          boxShadow: [
                                            BoxShadow(
                                                color: Color.fromRGBO(
                                                    233, 177, 236, 1),
                                                spreadRadius: 4,
                                                blurRadius: 8,
                                                offset: Offset(0, 0)),
                                          ]),
                                      child: Padding(
                                        padding: const EdgeInsets.symmetric(
                                            horizontal: 10),
                                        child: Row(
                                          mainAxisAlignment:
                                              MainAxisAlignment.center,
                                          mainAxisSize: MainAxisSize.max,
                                          children: [
                                            IconButton(
                                              icon: const Icon(
                                                Icons.cake_rounded,
                                                // MdiIcons.informationVariant,
                                              ),
                                              iconSize: 25,
                                              onPressed: () {
                                                showInformation(
                                                    context, _pageModel!);
                                              },
                                              color: Colors.white,
                                            ),
                                          ],
                                        ),
                                      ),
                                    ),
                                  ),
                                  const SizedBox(
                                    height: 3,
                                  ),
                                  Text(
                                    "Info",
                                    style: TextStyle(
                                        color: Colors.purple[400],
                                        fontFamily: 'Merienda'),
                                  ),
                                ],
                              ),
                            ],
                          );
                        } else {
                          return Row(
                            mainAxisSize: MainAxisSize.max,
                            mainAxisAlignment: MainAxisAlignment.spaceAround,
                            children: <Widget>[
                              followOrUnFollowButton(
                                  context, _pageModel!, _pageController),
                              Column(
                                children: [
                                  Card(
                                    shape: RoundedRectangleBorder(
                                        borderRadius:
                                            BorderRadius.circular(50)),
                                    color: Colors.purple[200],
                                    child: Container(
                                      decoration: const BoxDecoration(
                                          color: Colors.purple,
                                          borderRadius: BorderRadius.only(
                                              topLeft: Radius.circular(40),
                                              topRight: Radius.circular(40),
                                              bottomLeft: Radius.circular(40),
                                              bottomRight: Radius.circular(40)),
                                          boxShadow: [
                                            BoxShadow(
                                                color: Color.fromRGBO(
                                                    233, 177, 236, 1),
                                                spreadRadius: 4,
                                                blurRadius: 8,
                                                offset: Offset(0, 0)),
                                          ]),
                                      child: Padding(
                                        padding: const EdgeInsets.symmetric(
                                            horizontal: 10),
                                        child: Column(
                                          mainAxisAlignment:
                                              MainAxisAlignment.spaceAround,
                                          mainAxisSize: MainAxisSize.max,
                                          children: [
                                            IconButton(
                                              icon: const Icon(
                                                Icons.supervised_user_circle,
                                              ),
                                              iconSize: 25,
                                              onPressed: () {
                                                showMembers(context,
                                                    _pageModel!.getMembers);
                                              },
                                              color: Colors.white,
                                            ),
                                          ],
                                        ),
                                      ),
                                    ),
                                  ),
                                  const SizedBox(
                                    height: 3,
                                  ),
                                  Text(
                                    "Members",
                                    style: TextStyle(
                                        color: Colors.purple[400],
                                        fontFamily: 'Merienda'),
                                  ),
                                ],
                              ),
                              Column(
                                children: [
                                  Card(
                                    shape: RoundedRectangleBorder(
                                        borderRadius:
                                            BorderRadius.circular(50)),
                                    color: Colors.purple[200],
                                    child: Container(
                                      decoration: const BoxDecoration(
                                          color: Colors.purple,
                                          borderRadius: BorderRadius.only(
                                              topLeft: Radius.circular(40),
                                              topRight: Radius.circular(40),
                                              bottomLeft: Radius.circular(40),
                                              bottomRight: Radius.circular(40)),
                                          boxShadow: [
                                            BoxShadow(
                                                color: Color.fromRGBO(
                                                    233, 177, 236, 1),
                                                spreadRadius: 4,
                                                blurRadius: 8,
                                                offset: Offset(0, 0)),
                                          ]),
                                      child: Padding(
                                        padding: const EdgeInsets.symmetric(
                                            horizontal: 10),
                                        child: Row(
                                          mainAxisAlignment:
                                              MainAxisAlignment.center,
                                          mainAxisSize: MainAxisSize.max,
                                          children: [
                                            /* Padding(
                                padding: const EdgeInsets.only(left: 7),
                                child: Text(
                                  "About ",
                                  style: TextStyle(
                                      color: Colors.white,
                                      fontSize: 11,
                                      wordSpacing: -3),
                                ),
                              ), */
                                            IconButton(
                                              icon: const Icon(
                                                Icons.cake_rounded,
                                                // MdiIcons.informationVariant,
                                              ),
                                              iconSize: 25,
                                              onPressed: () {
                                                showInformation(
                                                    context, _pageModel!);
                                              },
                                              color: Colors.white,
                                            ),
                                          ],
                                        ),
                                      ),
                                    ),
                                  ),
                                  const SizedBox(
                                    height: 3,
                                  ),
                                  Text(
                                    "Info",
                                    style: TextStyle(
                                        color: Colors.purple[400],
                                        fontFamily: 'Merienda'),
                                  ),
                                ],
                              ),
                            ],
                          );
                        }
                      }),
                  const SizedBox(
                    height: 10,
                  ),
                  const Divider(
                    color: Colors.purple,
                    thickness: 0.7,
                  ),
                  Padding(
                    padding: const EdgeInsets.only(bottom: 10),
                    child: Container(
                      height: height * 0.80,
                      width: width,
                      decoration: const BoxDecoration(
                        color: Colors.white,
                        borderRadius: BorderRadius.only(
                          topLeft: Radius.circular(30),
                          topRight: Radius.circular(30),
                          bottomLeft: Radius.circular(30),
                          bottomRight: Radius.circular(30),
                        ),
                        boxShadow: [
                          BoxShadow(
                              color: Color.fromRGBO(233, 207, 236, 1),
                              spreadRadius: 4,
                              blurRadius: 7,
                              offset: Offset(0, 0))
                        ],
                      ),
                      child: Padding(
                        padding: const EdgeInsets.only(
                          bottom: 10,
                          left: 8,
                          right: 8,
                          top: 10,
                        ),
                        child: _pageModel!.getPostModels.length == 0
                            ? const Center(
                                child: Text(
                                  "There are no posts yet...",
                                  style: TextStyle(
                                      letterSpacing: 1,
                                      fontFamily: 'Merienda',
                                      fontSize: 20,
                                      fontWeight: FontWeight.w500,
                                      color: Colors.purple),
                                ),
                              )
                            : ListView.builder(
                                itemCount: _pageModel!.getPostModels.length,
                                itemBuilder: (context, i) {
                                  return PostBody(
                                    post: _pageModel!.getPostModels[i],
                                  );
                                },
                              ),
                      ),
                    ),
                  )
                ],
              ),
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

                    /// _showgroups(context);
                  },
                ),
              ),
              FloatingActionButton(
                  heroTag: 3,
                  child: const Icon(
                    Icons.add_circle_sharp,
                    size: 40,
                    color: Color.fromRGBO(233, 207, 236, 1),
                  ),
                  onPressed: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => CreatePost(),
                      ),
                    );
                  },
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
                  icon: const Icon(
                    Icons.home,
                    color: Colors.purple,
                    size: 30,
                  ),
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

  List posts() {
    List<Widget> list = [];
    list.add(const SizedBox(height: 0));
    list.add(const Text(
      'Page Posts',
      textAlign: TextAlign.center,
      style: TextStyle(
        color: Colors.purple,
        fontSize: 37,
        fontFamily: 'Merienda',
      ),
    ));
    list.add(const SizedBox(
      height: 3,
    ));
    list.add(const Divider(
      thickness: 2.5,
    ));
    list.add(const SizedBox(height: 10));
    return list;
  }

  void showInformation(BuildContext context, PageModel pageModel) {
    Widget cancelButton = TextButton(
      child: const Text(
        "OK",
        style: TextStyle(color: Colors.purple),
      ),
      onPressed: () {
        Navigator.pop(context);
      },
    );
    AlertDialog alert = AlertDialog(
      content: SizedBox(
        width: 380,
        child: ListView(children: [
          Column(
            children: [
              const Center(
                  child: Text(
                "Description",
                style: TextStyle(color: Colors.black, fontFamily: 'Merienda'),
              )),
              const Divider(
                color: Colors.purple,
                thickness: 1,
              ),
              Center(
                  child: Text(
                pageModel.getDescription,
                style: const TextStyle(
                    color: Colors.purple, fontFamily: 'Merienda'),
              )),
              const Divider(
                color: Colors.purple,
                thickness: 1,
              ),
              Text(
                "Created at : " +
                    pageModel.getCreatedAt.year.toString() +
                    "/" +
                    pageModel.getCreatedAt.month.toString() +
                    "/" +
                    pageModel.getCreatedAt.day.toString(),
                style: const TextStyle(
                    color: Colors.purple, fontFamily: 'Merienda'),
              ),
              const Divider(
                color: Colors.purple,
                thickness: 1,
              ),
              Row(
                children: [
                  const Text(
                    "To Update Group:",
                    style: TextStyle(color: Colors.purple),
                  ),
                  IconButton(
                      icon: const Icon(Icons.edit, color: Colors.purple),
                      onPressed: () {
                        Navigator.of(context).push(MaterialPageRoute(
                            builder: (context) =>
                                UpdatePage(pageModel: _pageModel!)));
                      }),
                ],
              ),
            ],
          ),
        ]),
      ),
      actions: const [
        // cancelButton,
      ],
    );
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return alert;
      },
    );
  }

  void showMembers(BuildContext context, List<UserModel> members) {
    List<Widget> usersInfo = [];
    for (int i = 0; i < members.length; i++) {
      usersInfo.add(UserInfo(
        userModel: members.elementAt(i),
      ));
      usersInfo.add(const SizedBox(
        height: 10,
      ));
    }
    Widget cancelButton = TextButton(
      child: const Text(
        "OK",
        style: TextStyle(color: Colors.purple),
      ),
      onPressed: () {
        Navigator.pop(context);
      },
    );
    AlertDialog alert = AlertDialog(
      content: SizedBox(
          width: 350,
          child: ListView(
            children: usersInfo,
          )),
      actions: const [
        // cancelButton,
      ],
    );
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return alert;
      },
    );
  }

  Future<bool> amIMemberAtThisPage(PageModel pageModel) async {
    SharedPreferences cache = await SharedPreferences.getInstance();
    bool amIn = false;
    pageModel.getMembers.forEach((element) {
      if (element.getId == cache.getString('id')) amIn = true;
    });
    return amIn;
  }

  Widget followOrUnFollowButton(
      BuildContext context, PageModel pageModel, PageControler pageController) {
    return FutureBuilder<bool>(
        future: amIMemberAtThisPage(pageModel),
        builder: (context, snapshot) {
          if (snapshot.data == null) {
            return const SizedBox(
              width: 0,
              height: 0,
            );
          }
          if (snapshot.data == false) {
            return Column(
              children: [
                Card(
                  shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(50)),
                  color: Colors.purple[200],
                  child: Container(
                    decoration: const BoxDecoration(
                        color: Colors.purple,
                        borderRadius: BorderRadius.only(
                            topLeft: Radius.circular(40),
                            topRight: Radius.circular(40),
                            bottomLeft: Radius.circular(40),
                            bottomRight: Radius.circular(40)),
                        boxShadow: [
                          BoxShadow(
                              color: Color.fromRGBO(233, 177, 236, 1),
                              spreadRadius: 4,
                              blurRadius: 8,
                              offset: Offset(0, 0)),
                        ]),
                    child: Padding(
                      padding: const EdgeInsets.symmetric(horizontal: 10),
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.spaceAround,
                        mainAxisSize: MainAxisSize.max,
                        children: [
                          IconButton(
                            icon: const Icon(
                              Icons.thumb_up_alt_rounded,
                            ),
                            iconSize: 25,
                            onPressed: () {
                              pageController.followThisPage(
                                  currentUser!.getId, pageModel.getId);
                              SnackBar mySnackBar = const SnackBar(
                                  duration: Duration(seconds: 1),
                                  backgroundColor:
                                      Color.fromRGBO(233, 207, 236, 1),
                                  content: Text(
                                    "You are follow this page now",
                                    style: TextStyle(
                                        fontFamily: 'Merienda',
                                        fontSize: 14,
                                        color: Colors.purple),
                                  ));
                              ScaffoldMessenger.maybeOf(context)!
                                  .showSnackBar(mySnackBar);
                            },
                            color: Colors.white,
                          ),
                        ],
                      ),
                    ),
                  ),
                ),
                const SizedBox(
                  height: 3,
                ),
                Text(
                  "Follow",
                  style: TextStyle(
                      color: Colors.purple[400], fontFamily: 'Merienda'),
                ),
              ],
            );
          }
          if (snapshot.data == true) {
            return Column(
              children: [
                Card(
                  shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(50)),
                  color: Colors.purple[200],
                  child: Container(
                    decoration: const BoxDecoration(
                        color: Colors.purple,
                        borderRadius: BorderRadius.only(
                            topLeft: Radius.circular(40),
                            topRight: Radius.circular(40),
                            bottomLeft: Radius.circular(40),
                            bottomRight: Radius.circular(40)),
                        boxShadow: [
                          BoxShadow(
                              color: Color.fromRGBO(233, 177, 236, 1),
                              spreadRadius: 4,
                              blurRadius: 8,
                              offset: Offset(0, 0)),
                        ]),
                    child: Padding(
                      padding: const EdgeInsets.symmetric(horizontal: 10),
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.spaceAround,
                        mainAxisSize: MainAxisSize.max,
                        children: [
                          IconButton(
                            icon: const Icon(
                              Icons.thumb_up_alt_rounded,
                            ),
                            iconSize: 25,
                            onPressed: () {
                              pageController.unFollowToThisPage(
                                  currentUser!.getId, pageModel.getId);
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                  builder: (context) => SocialHome(),
                                ),
                              );
                            },
                            color: Colors.white,
                          ),
                        ],
                      ),
                    ),
                  ),
                ),
                const SizedBox(
                  height: 3,
                ),
                Text(
                  "Un follow",
                  style: TextStyle(
                      color: Colors.purple[400], fontFamily: 'Merienda'),
                ),
              ],
            );
          } else {
            return Container();
          }
        });
  }
}
