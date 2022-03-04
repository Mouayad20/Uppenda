import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:material_design_icons_flutter/material_design_icons_flutter.dart';
import 'package:frontend/Body/BodyGroupButton.dart';
import 'package:frontend/Body/BodyPageButton.dart';
import 'package:frontend/Controllers/GroupController.dart';
import 'package:frontend/Controllers/PageController.dart';
import 'package:frontend/Controllers/UserController.dart';
import 'package:frontend/Model/GroupModel.dart';
import 'package:frontend/Model/PageModel.dart';
import 'package:frontend/Model/UserModel.dart';
import 'package:frontend/Social/CreateGroup.dart';
import 'package:frontend/Social/CreatePage.dart';
import 'package:frontend/Social/CreatePost.dart';
import 'package:frontend/Social/GroupsSearch.dart';
import 'package:frontend/Social/PagesSearch.dart';
import 'package:frontend/Social/PeopleSearch.dart';
import 'package:frontend/Social/Social_Home.dart';
import 'package:frontend/Pages/profile.dart';

import '../main.dart';

class Search extends StatefulWidget {
  @override
  SearchState createState() => SearchState();
}

class SearchState extends State<Search> {
  TextEditingController textSearch = TextEditingController();
  UserController userController = UserController();
  GroupController groupController = GroupController();
  PageControler pageController = PageControler();

  List<UserModel> users = [];
  List<PageModel> pages = [];
  List<GroupModel> groups = [];
  bool typing = false;

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
  }

  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
      length: 3,
      child: Scaffold(
        appBar: AppBar(
          automaticallyImplyLeading: false,
          backgroundColor: Colors.white,
          title: textBox(),
          bottom: const TabBar(
            isScrollable: true,
            indicatorColor: Colors.purple,
            indicatorWeight: 3.0,
            tabs: [
              Tab(
                child: Text(
                  'People',
                  style: TextStyle(
                      color: Colors.purple,
                      fontSize: 18.0,
                      fontWeight: FontWeight.w200,
                      fontFamily: 'Merienda'),
                ),
              ),
              Tab(
                child: Text(
                  'Pages',
                  style: TextStyle(
                      color: Colors.purple,
                      fontSize: 18.0,
                      fontWeight: FontWeight.w200,
                      fontFamily: 'Merienda'),
                ),
              ),
              Tab(
                child: Text(
                  'Groups',
                  style: TextStyle(
                      color: Colors.purple,
                      fontSize: 18.0,
                      fontWeight: FontWeight.w200,
                      fontFamily: 'Merienda'),
                ),
              ),
            ],
          ),
        ),
        body: TabBarView(
          children: [
            users.isEmpty
                ? const Center(
                    child: Text("No pages in this name",
                        style: TextStyle(fontFamily: 'Merienda')),
                  )
                : ListView.builder(
                    itemCount: users.length,
                    itemBuilder: (context, i) {
                      return PeopleSearch(userModel: users[i]);
                    },
                  ),
            pages.isEmpty
                ? const Center(
                    child: Text(
                      "No pages in this name",
                      style: TextStyle(fontFamily: 'Merienda'),
                    ),
                  )
                : ListView.builder(
                    itemCount: pages.length,
                    itemBuilder: (context, i) {
                      return PagesSearch(
                        pageModel: pages[i],
                      );
                    },
                  ),
            groups.isEmpty
                ? const Center(
                    child: Text(
                      "No groups in this name",
                      style: TextStyle(fontFamily: 'Merienda'),
                    ),
                  )
                : ListView.builder(
                    itemCount: groups.length,
                    itemBuilder: (context, i) {
                      return GroupsSearch(
                        groupModel: groups[i],
                      );
                    },
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
                    heroTag: 1,
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

  Widget textBox() {
    return Row(
      children: [
        Container(
          width: 280,
          height: 45,
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
            padding: const EdgeInsets.only(left: 5),
            child: TextField(
              controller: textSearch,
              decoration: const InputDecoration(
                border: InputBorder.none,
                hintText: 'Search..',
                hintStyle: TextStyle(
                    fontSize: 18,
                    color: Colors.grey,
                    decorationThickness: 2,
                    fontFamily: 'Merienda'),
              ),
            ),
          ),
        ),
        IconButton(
          icon: const Icon(
            Icons.search,
            color: Color.fromRGBO(233, 207, 236, 1),
            size: 25,
          ),
          onPressed: () {
            userController.search(textSearch.text.trim()).then((value) {
              setState(() {
                users = value;
              });
            });
            pageController.search(textSearch.text.trim()).then((value) {
              setState(() {
                pages = value;
              });
            });
            groupController.search(textSearch.text.trim()).then((value) {
              setState(() {
                groups = value;
              });
            });
          },
        )
      ],
    );
  }
}
