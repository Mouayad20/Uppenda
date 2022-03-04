// ignore_for_file: non_constant_identifier_names

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'package:frontend/Body/PostBody.dart';
import 'package:frontend/Controllers/PostController.dart';
import 'package:frontend/Model/PostModel.dart';
import 'package:frontend/Pages/EditProfile.dart';
import 'package:frontend/Pages/settings.dart';
import 'package:frontend/Social/FriendsList.dart';
import 'package:frontend/Social/Social_Home.dart';
import 'package:frontend/main.dart';
import 'package:frontend/Model/UserModel.dart';
import 'package:frontend/Controllers/UserController.dart';

class Profile extends StatefulWidget {
  final String user_id;

  const Profile({Key? key, required this.user_id}) : super(key: key);

  @override
  _ProfileState createState() => _ProfileState();
}

class _ProfileState extends State<Profile> {
  List<PostModel>? postModels;
  PostController postController = PostController();
  UserController userController = UserController();
  UserModel? userModel;

  String? profileId;

  Future<String> getUserFromCache() async {
    // SharedPreferences cache = await SharedPreferences.getInstance();
    // return cache.getString('id');
    return "";
  }

  @override
  void initState() {
    super.initState();
    getUserFromCache().then((idFromCash) {
      setState(() {
        // print("**********id1**********");
        // print(idFromCash);
        // print("**********id2***********");
        profileId = idFromCash;
        userController.getUserById(widget.user_id).then((value) {
          setState(() {
            userModel = value;
          });
        });
        userController.getUserById(idFromCash).then((value) {
          setState(() {
            MyApp.currentUser = value;
          });
        });
        postController.getAllPostsForUser(widget.user_id).then((value) {
          setState(() {
            postModels = value;
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
      body: userModel == null
          ? const Center(
              child: CircularProgressIndicator(),
            )
          : Stack(
              fit: StackFit.expand,
              children: [
                Container(
                  decoration: const BoxDecoration(
                    gradient: LinearGradient(
                      colors: [
                        Colors.purple,
                        Colors.deepPurple,
                      ],
                      begin: FractionalOffset.bottomCenter,
                      end: FractionalOffset.topCenter,
                    ),
                  ),
                ),
                Scaffold(
                  backgroundColor: Colors.transparent,
                  body: SingleChildScrollView(
                    physics: const BouncingScrollPhysics(),
                    //لرفع سكرول كل الشغلات الموجودة بالصفحة للاعلى
                    child: Padding(
                      padding: const EdgeInsets.symmetric(
                          horizontal: 14, vertical: 50),
                      child: Column(
                        children: [
                          Row(
                            mainAxisSize: MainAxisSize.max,
                            mainAxisAlignment: MainAxisAlignment.spaceBetween,
                            children: [
                              IconButton(
                                icon: const Icon(Icons.chevron_left),
                                color: Colors.white,
                                onPressed: () {
                                  Navigator.of(context).push(
                                    MaterialPageRoute(
                                      builder: (context) => SocialHome(),
                                    ),
                                  );
                                },
                              ),
                              Container(
                                  child: profileId == userModel!.getId
                                      ? IconButton(
                                          icon: const Icon(Icons.settings),
                                          color: Colors.white,
                                          onPressed: () {
                                            Navigator.of(context).push(
                                              MaterialPageRoute(
                                                builder: (context) =>
                                                    Settings(),
                                              ),
                                            );
                                          },
                                        )
                                      : Container())
                            ],
                          ),

//////////////////////////////////////////////////End top page/////////////////////////////////////////////////////////

                          const SizedBox(
                            height: 25,
                          ),

///////////////////////////////////////////////////start info//////////////////////////////////////////////////////////

                          const Text(
                            'PROFILE',
                            textAlign: TextAlign.center,
                            style: TextStyle(
                              color: Colors.white,
                              fontSize: 44,
                              fontFamily: 'DancingScript',
                            ),
                          ),
                          const SizedBox(
                            height: 22,
                          ),

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                          SizedBox(
                            // height: height * 0.43,
                            height: height / 2,
                            child: LayoutBuilder(
                              builder: (context, constraints) {
                                double innerHeight = constraints.maxHeight;
                                double innerWidth = constraints.maxWidth;
                                return Stack(
                                  fit: StackFit.expand,
                                  children: [
                                    Positioned(
                                      bottom: 0,
                                      left: 0,
                                      right: 0,
                                      child: Container(
                                        height: innerHeight * 0.65,
                                        width: innerWidth,
                                        decoration: BoxDecoration(
                                          borderRadius:
                                              BorderRadius.circular(30),
                                          color: Colors.white,
                                        ),
                                        child: Column(
                                          children: [
                                            const SizedBox(
                                              height: 65,
                                            ),
                                            if (userModel!.getFirstName !=
                                                    null &&
                                                userModel!.getLastName != null)
                                              Text(
                                                userModel!.getFirstName +
                                                    " " +
                                                    userModel!.getLastName,
                                                style: const TextStyle(
                                                  color: Colors.purple,
                                                  fontFamily: 'Merienda',
                                                  fontSize: 30,
                                                ),
                                              ),
                                            const SizedBox(height: 5),
                                            Row(
                                              mainAxisAlignment:
                                                  MainAxisAlignment.center,
                                              children: [
                                                Container(
                                                  child: profileId ==
                                                          userModel!.getId
                                                      ? IconButton(
                                                          icon: const Icon(
                                                              Icons.group,
                                                              color: Colors
                                                                  .purple),
                                                          color: Colors.grey,
                                                          onPressed: () {
                                                            getBottomSheetFriend(
                                                              MyApp.currentUser!
                                                                  .getFriends,
                                                            );
                                                          },
                                                        )
                                                      : _isMyFriend(
                                                              MyApp.currentUser!
                                                                  .getFriends,
                                                              userModel!.getId)
                                                          ? IconButton(
                                                              icon: const Icon(
                                                                Icons
                                                                    .person_remove,
                                                                color: Colors
                                                                    .purple,
                                                              ),
                                                              onPressed: () {
                                                                userController
                                                                    .unFriend(
                                                                  MyApp
                                                                      .currentUser!
                                                                      .getId,
                                                                  userModel!
                                                                      .getId,
                                                                );
                                                              },
                                                            )
                                                          : IconButton(
                                                              icon: const Icon(
                                                                  Icons.add,
                                                                  color: Colors
                                                                      .purple),
                                                              onPressed: () {
                                                                userController.addFriend(
                                                                    MyApp
                                                                        .currentUser!
                                                                        .getId,
                                                                    userModel!
                                                                        .getId);
                                                              },
                                                            ),
                                                ),
                                              ],
                                            )
                                          ],
                                        ),
                                      ),
                                    ),
                                    Positioned(
                                      top: 0,
                                      left: 0,
                                      right: 0,
                                      child: Center(
                                        child: Container(
                                          decoration: BoxDecoration(
                                              shape: BoxShape.circle,
                                              border: Border.all(
                                                  color: Colors.purple[600]!,
                                                  width: 4)),
                                          child: userModel!.getImage == null
                                              ? const CircleAvatar(
                                                  child: Icon(
                                                    Icons.person,
                                                    size: 80,
                                                    color: Colors.purple,
                                                  ),
                                                  radius: 85,
                                                )
                                              : CircleAvatar(
                                                  backgroundImage: NetworkImage(
                                                    MyApp.mainURL +
                                                        userModel!.getImage
                                                            .toString()
                                                            .replaceAll(
                                                                "\\", "/"),
                                                    // headers: {
                                                    //   "Authorization":
                                                    //       "Bearer " +
                                                    //           MyApp.currentUser!
                                                    //               .getToken
                                                    // },
                                                  ),
                                                  backgroundColor: Colors.blue,
                                                  // minRadius: 50,
                                                  maxRadius: 80,
                                                ),
                                        ),
                                        // child: Container(),
                                      ),
                                    ),
                                    Positioned(
                                      top: 130,
                                      right: 20,
                                      child: SizedBox(
                                          height: 50,
                                          width: 30,
                                          child: profileId == userModel!.getId
                                              ? IconButton(
                                                  icon: const Icon(
                                                    Icons.edit,
                                                    color: Colors.purple,
                                                  ),
                                                  color: Colors.grey[700],
                                                  iconSize: 27,
                                                  onPressed: () {
                                                    Navigator.of(context).push(
                                                      MaterialPageRoute(
                                                        builder: (context) =>
                                                            EditProfile(
                                                                userModel:
                                                                    userModel!),
                                                      ),
                                                    );
                                                  },
                                                )
                                              : Container()),
                                    ),
                                  ],
                                );
                              },
                            ),
                          ),
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                          const SizedBox(
                            height: 30,
                          ),

                          SizedBox(
                            height: height * 0.33,
                            child: LayoutBuilder(
                              builder: (context, constraints) {
                                double innerHeight = constraints.maxHeight;
                                double innerWidth = constraints.maxWidth;
                                return Stack(
                                  fit: StackFit.expand,
                                  children: [
                                    const SizedBox(
                                      height: 10,
                                    ),
                                    Container(
                                      height: innerHeight * 0.65,
                                      width: innerWidth,
                                      decoration: BoxDecoration(
                                        borderRadius: BorderRadius.circular(30),
                                        color: Colors.white,
                                      ),
                                      child: Padding(
                                        padding: const EdgeInsets.symmetric(
                                            horizontal: 8),
                                        child: ListView(
                                          children: profileInfo(userModel!),
                                        ),
                                      ),
                                    ),
                                  ],
                                );
                              },
                            ),
                          ),

////////////////////////////////////////////////////start list of posts///////////////////////////////////////////////////////////////

                          const SizedBox(height: 30),

                          Container(
                            height: height * 0.92,
                            width: width,
                            decoration: BoxDecoration(
                              borderRadius: BorderRadius.circular(30),
                              color: Colors.white,
                            ),
                            child: Padding(
                              padding: const EdgeInsets.only(
                                  bottom: 10, left: 8, right: 8, top: 0),
                              child: postModels == null
                                  ? const Center(
                                      child: CircularProgressIndicator(),
                                    )
                                  : ListView.builder(
                                      itemCount: postModels!.length,
                                      itemBuilder: (context, i) {
                                        return PostBody(
                                          post: postModels![i],
                                        );
                                      },
                                    ),
                            ),
                          )
                        ],
                      ),
                    ),
                  ),
                ),
              ],
            ),
    );
  }

  getBottomSheetFriend(List<UserModel> listUsers) {
    return showCupertinoModalPopup(
      context: context,
      builder: (BuildContext context) => Padding(
        padding: const EdgeInsets.all(8.0),
        child: CupertinoActionSheet(
          title: listUsers.isEmpty
              ? const Center(child: CircularProgressIndicator())
              : listUsers.isEmpty
                  ? const Text(
                      "No friends yet",
                      style: TextStyle(
                        letterSpacing: 3,
                        color: Colors.purple,
                        fontSize: 30,
                        fontFamily: 'DancingScript',
                        fontWeight: FontWeight.w600,
                      ),
                    )
                  : const Text(
                      "friends",
                      style: TextStyle(
                        letterSpacing: 2,
                        color: Colors.purple,
                        fontSize: 30,
                        fontFamily: 'DancingScript',
                        fontWeight: FontWeight.w600,
                      ),
                    ),
          actions: List.generate(
            listUsers.length,
            (index) {
              return FriendsList(
                friend: listUsers[index],
              );
            },
          ),
        ),
      ),
    );
  }

  bool _isMyFriend(List<UserModel> list, String userPostId) {
    bool isFriend = false;
    for (var userModel in list) {
      if (userModel.getId == userPostId) isFriend = true;
    }
    return isFriend;
  }
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// List posts() {
//   List<Widget> list = new List();
//   list.add(SizedBox(height: 0));
//   list.add();
//   list.add();
//   list.add();
//   list.add();

//   return list;
// }

List<Widget> profileInfo(UserModel user) {
  List<Widget> list = [];
  if (user.getGender != null) {
    list.add(
      ListTile(
        title: Text(
          user.getGender.toString(),
          style: const TextStyle(
              color: Colors.deepPurple, fontSize: 20, fontFamily: 'Merienda'),
        ),
        leading: const Icon(
          Icons.person,
          color: Colors.purple,
        ),
      ),
    );
  }
  if (user.getAge != null) {
    list.add(
      const ListTile(
        title: Text(
          // user.getAge().year.toString() +
          //     "/" +
          //     user.getAge().month.toString() +
          //     "/" +
          //     user.getAge().day.toString(),
          "lolo",
          style: TextStyle(
              color: Colors.deepPurple, fontSize: 20, fontFamily: 'Merienda'),
        ),
        leading: Icon(
          Icons.date_range_outlined,
          color: Colors.purple,
        ),
      ),
    );
  }
  if (user.getStudyLevel != null) {
    list.add(
      ListTile(
        title: Text(
          user.getStudyLevel.toString(),
          style: const TextStyle(
              color: Colors.deepPurple, fontSize: 20, fontFamily: 'Merienda'),
        ),
        leading: const Icon(
          Icons.school_outlined,
          color: Colors.purple,
        ),
      ),
    );
  }
  if (user.getLocation != null) {
    list.add(
      ListTile(
        title: Text(
          user.getLocation.toString(),
          style: const TextStyle(
              color: Colors.deepPurple, fontSize: 20, fontFamily: 'Merienda'),
        ),
        leading: const Icon(
          Icons.location_on_outlined,
          color: Colors.purple,
        ),
      ),
    );
  }
  if (user.getEmail != null) {
    list.add(
      ListTile(
        title: Text(
          user.getEmail.toString(),
          style: const TextStyle(
              color: Colors.deepPurple, fontSize: 20, fontFamily: 'Merienda'),
        ),
        leading: const Icon(
          Icons.alternate_email,
          color: Colors.purple,
        ),
      ),
    );
  }
  if (user.getMobile != null) {
    list.add(
      ListTile(
        title: Text(
          user.getMobile.toString(),
          style: const TextStyle(
              color: Colors.deepPurple, fontSize: 20, fontFamily: 'Merienda'),
        ),
        leading: const Icon(
          Icons.phone_in_talk_outlined,
          color: Colors.purple,
        ),
      ),
    );
  }
  return list;
}
