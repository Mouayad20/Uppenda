// ignore_for_file: unrelated_type_equality_checks

import 'dart:io';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';
import 'package:material_design_icons_flutter/material_design_icons_flutter.dart';
import 'package:frontend/Body/CommentBody.dart';
import 'package:frontend/Controllers/GroupController.dart';
import 'package:frontend/Controllers/PageController.dart';
import 'package:frontend/Model/CommentModel.dart';
import 'package:frontend/Model/ReactionModel.dart';
import 'package:frontend/Model/PostModel.dart';
import 'package:frontend/Model/UserModel.dart';
import 'package:frontend/Pages/Group.dart';
import 'package:frontend/Pages/Page.dart';
import 'package:frontend/Pages/profile.dart';
import 'package:frontend/Social/SharedBody.dart';
import 'package:frontend/Social/UpdatePost.dart';
import 'package:frontend/Social/VideoPlayer.dart';
import 'package:frontend/Controllers/CommentController.dart';
import 'package:frontend/Controllers/PostController.dart';
import 'package:frontend/Controllers/ReactionController.dart';
import 'package:frontend/Controllers/UserController.dart';
import 'package:frontend/main.dart';
import 'package:readmore/readmore.dart';
import 'LikeBody.dart';

class PostBody extends StatefulWidget {
  PostModel post;

  PostBody({Key? key, required this.post}) : super(key: key);

  @override
  PostBodyState createState() => PostBodyState();
}

class PostBodyState extends State<PostBody> {
  ReactionController reactionController = ReactionController();
  GroupController groupController = GroupController();
  PageControler pageController = PageControler();
  PostController postController = PostController();
  UserController userController = UserController();
  CommentController commentController = CommentController();
  List<ReactionModel> reactionModels = [];
  List<Column> reactionsWidget = [];
  CommentModel commentModel = CommentModel();
  List<PopupMenuItem> popList = [];
  ReactionModel likeModel = ReactionModel();

  //////////////

  File? _image;
  List<Image>? _images;
  ImagePicker picker = ImagePicker();
  bool b1 = false;
  bool b2 = true;
  Color? colorHeart;
  Icon? reaction;
  TextEditingController commentContent = TextEditingController();

  String numLikes = "";
  String numComments = "";
  String numShares = "";

  @override
  void initState() {
    super.initState();
    _images ??= [];
    reactionController.getAllReactionType().then((value) {
      setState(() {
        reactionModels = value;
      });
    });
    setState(() {
      numLikes = widget.post.getReactionModels.length.toString();
      numComments = widget.post.getCommentModels.length.toString();
      numShares = widget.post.getParticipants.length.toString();
    });

    colorHeart = Colors.purple;
    likeModel = checkIfLike(widget.post.reactionModels!)!;
    if (likeModel == null) {
      setState(() {
        reaction = const Icon(
          MdiIcons.heartOutline,
          color: Colors.purple,
          size: 30,
        );
      });
    } else {
      setState(() {
        reaction = Icon(
          MdiIcons.heart,
          color: Color(int.parse(
              likeModel.getReactionTypeModel.getColorName.toString())),
          size: 30,
        );
      });
    }
    if (MyApp.currentUser!.getId == widget.post.getUserModel.getId) {
      popList.add(
        PopupMenuItem(
          child: InkWell(
            child: const Text(
              "Update",
              style: TextStyle(color: Colors.purple, fontFamily: 'Merienda'),
            ),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(
                  builder: (context) => UpdatePost(
                    postModel: widget.post,
                  ),
                ),
              );
            },
          ),
        ),
      );
      popList.add(
        PopupMenuItem(
          child: InkWell(
            child: const Text(
              "Delete",
              style: TextStyle(color: Colors.purple, fontFamily: 'Merienda'),
            ),
            onTap: () {
              PostController().deleteById(widget.post.getId);
            },
          ),
        ),
      );
    }
    if (MyApp.currentUser!.getId != widget.post.getUserModel.getId &&
        widget.post.getGroupModel != null &&
        MyApp.currentUser!.getId != widget.post.getGroupModel.admin.id &&
        _meIn(widget.post.getGroupModel.getMembers)) {
      popList.add(
        PopupMenuItem(
          child: InkWell(
            child: const Text(
              "Un follow",
              style: TextStyle(color: Colors.purple, fontFamily: 'Merienda'),
            ),
            onTap: () {
              groupController.leaveGroup(
                  MyApp.currentUser!.getId, widget.post.getGroupModel.getId);
            },
          ),
        ),
      );
    }
    if (MyApp.currentUser!.getId != widget.post.getUserModel.getId &&
        widget.post.getGroupModel != null &&
        MyApp.currentUser!.getId != widget.post.getGroupModel.admin.id &&
        !_meIn(widget.post.getGroupModel.getMembers)) {
      popList.add(
        PopupMenuItem(
          child: InkWell(
            child: const Text(
              "Follow",
              style: TextStyle(color: Colors.purple, fontFamily: 'Merienda'),
            ),
            onTap: () {
              groupController.joinToGroup(
                  MyApp.currentUser!.getId, widget.post.getGroupModel.getId);
            },
          ),
        ),
      );
    }

    if (MyApp.currentUser!.getId != widget.post.getUserModel.getId &&
        widget.post.getPageModel != null &&
        _meIn(widget.post.getPageModel.getMembers)) {
      popList.add(
        PopupMenuItem(
          child: InkWell(
            child: const Text(
              "Un follow",
              style: TextStyle(color: Colors.purple, fontFamily: 'Merienda'),
            ),
            onTap: () {
              pageController.unFollowToThisPage(
                  MyApp.currentUser!.getId, widget.post.getPageModel.getId);
            },
          ),
        ),
      );
    }
    if (MyApp.currentUser!.getId != widget.post.getUserModel.getId &&
        widget.post.getPageModel != null &&
        !_meIn(widget.post.getPageModel.getMembers)) {
      popList.add(
        PopupMenuItem(
          child: InkWell(
            child: const Text(
              "Follow",
              style: TextStyle(color: Colors.purple, fontFamily: 'Merienda'),
            ),
            onTap: () {
              pageController.followThisPage(
                  MyApp.currentUser!.getId, widget.post.getPageModel.getId);
            },
          ),
        ),
      );
    }
    if ((_isMyFriend(
                MyApp.currentUser!.getFriends, widget.post.getUserModel.getId) &&
            widget.post.getGroupModel != null) ||
        (_isMyFriend(
                MyApp.currentUser!.getFriends, widget.post.getUserModel.getId) &&
            widget.post.getGroupModel == null &&
            widget.post.getPageModel == null)) {
      popList.add(
        PopupMenuItem(
          child: InkWell(
            child: const Text(
              "Un friend",
              style: TextStyle(color: Colors.purple, fontFamily: 'Merienda'),
            ),
            onTap: () {
              userController.unFriend(
                  MyApp.currentUser!.getId, widget.post.getUserModel.getId);
            },
          ),
        ),
      );
    }

    if (!_isMyFriend(
            MyApp.currentUser!.getFriends, widget.post.getUserModel.getId) &&
        widget.post.getGroupModel == null &&
        widget.post.getPageModel == null &&
        MyApp.currentUser!.getId != widget.post.getUserModel.getId) {
      popList.add(
        PopupMenuItem(
          child: InkWell(
            child: const Text(
              "Add friend",
              style: TextStyle(color: Colors.purple, fontFamily: 'Merienda'),
            ),
            onTap: () {
              userController.addFriend(
                  MyApp.currentUser!.getId, widget.post.getUserModel.getId);
            },
          ),
        ),
      );
    }
    if (!_isMyFriend(
            MyApp.currentUser!.getFriends, widget.post.getUserModel.getId) &&
        widget.post.getGroupModel != null &&
        widget.post.getPageModel == null &&
        MyApp.currentUser!.getId != widget.post.getUserModel.getId) {
      popList.add(
        PopupMenuItem(
          child: InkWell(
            child: const Text(
              "Add friend",
              style: TextStyle(color: Colors.purple, fontFamily: 'Merienda'),
            ),
            onTap: () {
              userController.addFriend(
                  MyApp.currentUser!.getId, widget.post.getUserModel.getId);
            },
          ),
        ),
      );
    }
    if (_isSavedBefore(widget.post.getSavers)) {
      popList.add(
        PopupMenuItem(
          child: InkWell(
            child: const Text(
              "Un save",
              style: TextStyle(color: Colors.purple, fontFamily: 'Merienda'),
            ),
            onTap: () {
              userController.unSavePost(
                  MyApp.currentUser!.getId, widget.post.getId);
            },
          ),
        ),
      );
    } else {
      popList.add(
        PopupMenuItem(
          child: InkWell(
            child: const Text(
              "Save",
              style: TextStyle(color: Colors.purple, fontFamily: 'Merienda'),
            ),
            onTap: () {
              userController.savePost(
                  MyApp.currentUser!.getId, widget.post.getId);
            },
          ),
        ),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    for (var i = 0; i < reactionModels.length; i++) {
      reactionsWidget.add(reactionsModelToWidget(reactionModels[i], context));
    }
    return Card(
      margin: const EdgeInsets.only(top: 15),
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(30),
      ),
      child: Container(
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
              offset: Offset(0, 0),
            ),
          ],
        ),
        child: Column(
          children: [
            widget.post.pageModel == null && widget.post.groupModel == null
                ? Padding(
                    padding: const EdgeInsets.only(top: 8.0, left: 8.0),
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        Row(
                          children: [
                            widget.post.getUserModel.getOnLine
                                ? widget.post.getUserModel.getImage != null
                                    ? CircleAvatar(
                                        radius: 27,
                                        backgroundColor: Colors.green,
                                        child: CircleAvatar(
                                          radius: 25,
                                          backgroundImage: Image.network(
                                            MyApp.mainURL +
                                                widget
                                                    .post.getUserModel.getImage
                                                    .toString()
                                                    .replaceAll("\\", "/"),
                                            // headers: {
                                            //   "Authorization": "Bearer " +
                                            //       MyApp.currentUser!.getToken
                                            // },
                                          ).image,
                                        ),
                                      )
                                    : const CircleAvatar(
                                        radius: 27,
                                        backgroundColor: Colors.green,
                                        child: CircleAvatar(
                                          radius: 25,
                                          backgroundColor:
                                              Color.fromRGBO(233, 207, 236, 1),
                                          foregroundColor: Colors.purple,
                                          child: Icon(
                                            Icons.person,
                                            size: 30,
                                          ),
                                        ),
                                      )
                                : widget.post.getUserModel.getImage != null
                                    ? CircleAvatar(
                                        radius: 25,
                                        backgroundImage: Image.network(
                                          MyApp.mainURL +
                                              widget.post.getUserModel.getImage
                                                  .toString()
                                                  .replaceAll("\\", "/"),
                                          // headers: {
                                          //   "Authorization": "Bearer " +
                                          //       MyApp.currentUser!.getToken
                                          // },
                                        ).image,
                                      )
                                    : const CircleAvatar(
                                        radius: 25,
                                        backgroundColor:
                                            Color.fromRGBO(233, 207, 236, 1),
                                        foregroundColor: Colors.purple,
                                        child: Icon(
                                          Icons.person,
                                          size: 30,
                                        ),
                                      ),
                            Padding(
                              padding: const EdgeInsets.all(8.0),
                              child: SizedBox(
                                width: 200,
                                child: InkWell(
                                  child: Text(
                                    widget.post.getUserModel.getFirstName +
                                        ' ' +
                                        widget.post.getUserModel.getLastName,
                                    overflow: TextOverflow.clip,
                                    style: const TextStyle(
                                      fontWeight: FontWeight.w700,
                                      color: Colors.purple,
                                      fontSize: 13,
                                      fontFamily: 'Merienda',
                                    ),
                                  ),
                                  onTap: () {
                                    Navigator.push(
                                      context,
                                      MaterialPageRoute(
                                        builder: (context) {
                                          return Profile(
                                            user_id:
                                                widget.post.getUserModel.getId,
                                          );
                                        },
                                      ),
                                    );
                                  },
                                ),
                              ),
                            ),
                          ],
                        ),
                        getList(),
                      ],
                    ),
                  )
                : widget.post.pageModel != null &&
                        widget.post.groupModel == null
                    ? Padding(
                        padding: const EdgeInsets.only(top: 8.0, left: 8.0),
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            Row(
                              children: [
                                widget.post.getPageModel.getImage != null
                                    ? CircleAvatar(
                                        radius: 25,
                                        backgroundImage: Image.network(
                                          MyApp.mainURL +
                                              widget.post.getPageModel.getImage
                                                  .toString()
                                                  .replaceAll("\\", "/"),
                                          // headers: {
                                          //   "Authorization": "Bearer " +
                                          //       MyApp.currentUser!.getToken
                                          // },
                                        ).image,
                                      )
                                    : const CircleAvatar(
                                        radius: 25,
                                        backgroundColor:
                                            Color.fromRGBO(233, 207, 236, 1),
                                        foregroundColor: Colors.purple,
                                        child: Icon(
                                          Icons.description,
                                          size: 30,
                                        ),
                                      ),
                                Padding(
                                  padding: const EdgeInsets.all(8.0),
                                  child: SizedBox(
                                    width: 200,
                                    child: InkWell(
                                      child: Text(
                                        widget.post.getPageModel.getName,
                                        overflow: TextOverflow.clip,
                                        style: const TextStyle(
                                            fontWeight: FontWeight.w700,
                                            color: Colors.purple,
                                            fontSize: 13,
                                            fontFamily: 'Merienda'),
                                      ),
                                      onTap: () {
                                        Navigator.push(
                                          context,
                                          MaterialPageRoute(
                                            builder: (context) {
                                              return Page1(
                                                page_id: widget
                                                    .post.getPageModel.getId,
                                              );
                                            },
                                          ),
                                        );
                                      },
                                    ),
                                  ),
                                ),
                              ],
                            ),
                            getList(),
                          ],
                        ),
                      )
                    : widget.post.groupModel != null &&
                            widget.post.pageModel == null
                        ? Padding(
                            padding: const EdgeInsets.only(top: 8.0, left: 8.0),
                            child: Row(
                              mainAxisAlignment: MainAxisAlignment.spaceBetween,
                              children: [
                                Row(
                                  children: [
                                    widget.post.getGroupModel.getImage != null
                                        ? CircleAvatar(
                                            radius: 25,
                                            backgroundImage: Image.network(
                                              MyApp.mainURL +
                                                  widget.post.getGroupModel
                                                      .getImage
                                                      .toString()
                                                      .replaceAll("\\", "/"),
                                              // headers: {
                                              //   "Authorization": "Bearer " +
                                              //       MyApp.currentUser!.getToken
                                              // },
                                            ).image,
                                          )
                                        : const CircleAvatar(
                                            radius: 25,
                                            backgroundColor: Color.fromRGBO(
                                                233, 207, 236, 1),
                                            foregroundColor: Colors.purple,
                                            child: Icon(
                                              Icons.supervised_user_circle,
                                              size: 30,
                                            ),
                                          ),
                                    Padding(
                                      padding: const EdgeInsets.only(left: 3.0),
                                      child: InkWell(
                                        child: Text(
                                          widget.post.getUserModel
                                                  .getFirstName +
                                              ' ' +
                                              widget.post.getUserModel
                                                  .getLastName,
                                          overflow: TextOverflow.clip,
                                          style: const TextStyle(
                                            fontWeight: FontWeight.w700,
                                            color: Colors.purple,
                                            fontFamily: 'Merienda',
                                            fontSize: 13,
                                          ),
                                        ),
                                        onTap: () {
                                          Navigator.push(
                                            context,
                                            MaterialPageRoute(
                                              builder: (context) {
                                                return Profile(
                                                  user_id: widget
                                                      .post.getUserModel.getId,
                                                );
                                              },
                                            ),
                                          );
                                        },
                                      ),
                                    ),
                                    const Icon(
                                      MdiIcons.arrowRightDropCircle,
                                      size: 15,
                                      color: Colors.grey,
                                    ),
                                    InkWell(
                                      child: Text(
                                        widget.post.getGroupModel.getName,
                                        overflow: TextOverflow.clip,
                                        style: const TextStyle(
                                            fontWeight: FontWeight.w700,
                                            color: Colors.purple,
                                            fontSize: 13,
                                            fontFamily: 'Merienda'),
                                      ),
                                      onTap: () {
                                        Navigator.push(
                                          context,
                                          MaterialPageRoute(
                                            builder: (context) {
                                              return Group1(
                                                group_id: widget
                                                    .post.getGroupModel.getId,
                                              );
                                            },
                                          ),
                                        );
                                      },
                                    ),
                                  ],
                                ),
                                getList(),
                              ],
                            ),
                          )
                        : Container(),
            const Divider(color: Colors.purple, thickness: 0.5),
            Container(child: getBodyOfPost()),
            Padding(
              padding: const EdgeInsets.fromLTRB(3.0, 10.0, 0.0, 13.0),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      Padding(
                        padding:
                            const EdgeInsets.only(right: 0, left: 0, top: 10),
                        child: Column(
                          children: [
                            InkWell(
                              child: reaction,
                              onTap: () {
                                setState(
                                  () {
                                    if (reaction!.icon ==
                                        MdiIcons.heartOutline) {
                                      reaction = const Icon(
                                        MdiIcons.heart,
                                        color: Colors.purple,
                                        size: 30,
                                      );
                                      for (var i = 0;
                                          i < reactionModels.length;
                                          i++) {
                                        if (reactionModels[i]
                                                .reactionTypeModel ==
                                            "Like") {
                                          reactionController
                                              .reaction(
                                                  widget.post.getId,
                                                  MyApp.currentUser!.getId,
                                                  reactionModels[i].getId())
                                              .then(
                                            (value) {
                                              setState(
                                                () {
                                                  likeModel = value!;
                                                  int a =
                                                      int.parse(numLikes) + 1;
                                                  numLikes = a.toString();
                                                },
                                              );
                                            },
                                          );
                                        }
                                      }
                                    } else {
                                      reaction = const Icon(
                                        MdiIcons.heartOutline,
                                        color: Colors.purple,
                                        size: 30,
                                      );
                                      reactionController
                                          .unReaction(likeModel.getId)
                                          .then((value) {
                                        setState(() {
                                          int a = int.parse(numLikes) - 1;
                                          numLikes = a.toString();
                                        });
                                      });
                                    }
                                  },
                                );
                              },
                              onLongPress: () {
                                showDialog(
                                  context: context,
                                  builder: (BuildContext context) =>
                                      _buildPopupDialog(context),
                                );
                              },
                            ),
                            SizedBox(
                              height: 30,
                              child: TextButton(
                                child: Text(
                                  numLikes,
                                  style: const TextStyle(
                                      fontWeight: FontWeight.w500,
                                      color: Colors.purple,
                                      fontSize: 12,
                                      fontFamily: 'Merienda'),
                                ),
                                onPressed: () {
                                  getButtomSheetLike();
                                },
                              ),
                            ),
                          ],
                        ),
                      ),
                      const SizedBox(width: 18.0),
                      Padding(
                        padding: const EdgeInsets.only(right: 2),
                        child: Column(
                          children: [
                            IconButton(
                              onPressed: () {
                                getBottomSheet();
                              },
                              icon: const Padding(
                                padding:
                                    EdgeInsets.fromLTRB(3.0, 0.0, 5.0, 5.0),
                                child: Icon(
                                  MdiIcons.commentMultipleOutline,
                                  size: 30,
                                  color: Colors.purple,
                                ),
                              ),
                            ),
                            Text(
                              numComments,
                              style: const TextStyle(
                                  fontWeight: FontWeight.w500,
                                  color: Colors.purple,
                                  fontSize: 12,
                                  fontFamily: 'Merienda'),
                            ),
                          ],
                        ),
                      ),
                      const SizedBox(width: 18.0),
                      Column(
                        children: [
                          IconButton(
                            onPressed: () {
                              showAlertDialog(context);
                            },
                            icon: Icon(
                                _isSharedBefor(widget.post.getParticipants)
                                    ? MdiIcons.share
                                    : MdiIcons.shareOutline,
                                size: 33,
                                color: Colors.purple),
                          ),
                          InkWell(
                            child: Text(
                              numShares,
                              style: const TextStyle(
                                  fontWeight: FontWeight.w500,
                                  color: Colors.purple,
                                  fontSize: 12,
                                  fontFamily: 'Merienda'),
                            ),
                            onTap: () {
                              getButtomSheetShare();
                            },
                          ),
                        ],
                      ),
                      const SizedBox(width: 18.0),
                      Padding(
                        padding: const EdgeInsets.fromLTRB(
                            20.0, 0.0, 0.0, 17.0), //20.0 >> 50
                        child: Container(
                          width: 55,
                          color: Colors.white,
                          child: Row(
                            children: [
                              Text(
                                "\t\t\t\t" +
                                    widget.post.getCreatedAt.hour.toString() +
                                    ":" +
                                    widget.post.getCreatedAt.minute.toString() +
                                    "\n" +
                                    widget.post.getCreatedAt.day.toString() +
                                    "/" +
                                    widget.post.getCreatedAt.month.toString() +
                                    "/" +
                                    widget.post.getCreatedAt.year.toString(),
                                style: const TextStyle(
                                    fontWeight: FontWeight.w200,
                                    fontSize: 10,
                                    color: Colors.purple,
                                    fontFamily: 'Merienda'),
                              ),
                            ],
                          ),
                        ),
                      ),
                    ],
                  ),
                ],
              ),
            ),
            const Divider(color: Colors.purple, thickness: 0.5),
            Padding(
              padding: const EdgeInsets.fromLTRB(16.0, 0.0, 8.0, 5.0),
              child: Row(
                children: [
                  Expanded(
                    child: TextField(
                      controller: commentContent,
                      maxLines: 2,
                      decoration: const InputDecoration(
                          border: InputBorder.none,
                          hintText: "Add a comment...",
                          hintStyle: TextStyle(
                              fontSize: 14,
                              color: Colors.grey,
                              decorationThickness: 2,
                              fontFamily: 'Merienda')),
                    ),
                  ),
                  getListCameraGallery(),
                  IconButton(
                    icon: const Icon(MdiIcons.send,
                        size: 27, color: Colors.purple),
                    onPressed: () {
                      SnackBar mySnackBar = const SnackBar(
                          duration: Duration(seconds: 1),
                          backgroundColor: Color.fromRGBO(233, 207, 236, 1),
                          content: Text(
                            "Yup !!   <<you add comment>>",
                            style: TextStyle(
                                fontFamily: 'Merienda',
                                fontSize: 14,
                                color: Colors.purple),
                          ));
                      ScaffoldMessenger.maybeOf(context)!
                          .showSnackBar(mySnackBar);

                      setState(() {
                        commentModel.setContent = commentContent.text.trim();
                        commentModel.setCreatedAt = DateTime.now().toString();
                        commentController.addComment(commentModel,
                            MyApp.currentUser!.getId, widget.post.getId);
                        FocusScope.of(context).unfocus();
                        int a = int.parse(numComments) + 1;
                        numComments = a.toString();
                      });
                      commentContent.clear();
                      _image = null;
                    },
                  ),
                ],
              ),
            ),
          ],
        ),
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
                  _image = image;
                  commentModel.setImage = _image!.path;
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

              File image = File(pickedFile!.path);
              setState(
                () {
                  _image = image;
                  commentModel.setImage = _image!.path;
                },
              );
            },
          ),
        ),
      ],
      child: _image == null
          ? const Icon(
              Icons.image,
              size: 27,
              color: Colors.purple,
            )
          : SizedBox(
              height: 21.0,
              width: 21.0,
              child: Image(
                image: FileImage(_image!),
                fit: BoxFit.fill,
              ),
            ),
    );
  }

  showAlertDialog(BuildContext context) {
    Widget cancelButton = TextButton(
      child: const Text(
        "Cancel",
        style: TextStyle(color: Color.fromRGBO(233, 207, 236, 1)),
      ),
      onPressed: () {
        Navigator.of(context).pop;
      },
    );
    Widget continueButton = TextButton(
      child: const Text(
        "Continue",
        style: TextStyle(
          color: Color.fromRGBO(233, 207, 236, 1),
        ),
      ),
      onPressed: () {
        _isSharedBefor(widget.post.getParticipants)
            ? userController
                .unSharePost(MyApp.currentUser!.getId, widget.post.getId)
                .then((value) {
                setState(() {
                  int a = int.parse(numShares) - 1;
                  numLikes = a.toString();
                });
              })
            : userController
                .sharePost(MyApp.currentUser!.getId, widget.post.getId)
                .then((value) {
                setState(() {
                  int a = int.parse(numShares) + 1;
                  numLikes = a.toString();
                });
              });
        Navigator.pop(context);
      },
    );

    AlertDialog alert = AlertDialog(
      content: Text(
        _isSharedBefor(widget.post.getParticipants)
            ? "Would you like to Unshare post on your profile?"
            : "Would you like to share post on your profile?",
        style: const TextStyle(
          color: Colors.purple,
          fontFamily: 'Merienda',
        ),
      ),
      actions: [
        cancelButton,
        continueButton,
      ],
    );
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return alert;
      },
    );
  }

  getBottomSheet() {
    showCupertinoModalPopup(
      context: context,
      builder: (BuildContext context) => Padding(
        padding: const EdgeInsets.all(8.0),
        child: CupertinoActionSheet(
          title: widget.post.getCommentModels.isEmpty
              ? const Text(
                  "No Comments yet",
                  style: TextStyle(
                    letterSpacing: 2,
                    color: Colors.purple,
                    fontSize: 30,
                    fontFamily: 'DancingScript',
                    fontWeight: FontWeight.w600,
                  ),
                )
              : const Text(
                  "Comments",
                  style: TextStyle(
                    letterSpacing: 2,
                    color: Colors.purple,
                    fontSize: 30,
                    fontFamily: 'DancingScript',
                    fontWeight: FontWeight.w600,
                  ),
                ),
          actions: List.generate(
            widget.post.getCommentModels.length,
            (index) {
              return CupertinoActionSheetAction(
                child: CommentBody(
                    commentModel: widget.post.getCommentModels[index]),
                onPressed: () {},
              );
            },
          ),
        ),
      ),
    );
  }

  getButtomSheetLike() {
    showCupertinoModalPopup(
      context: context,
      builder: (BuildContext context) => Padding(
        padding: const EdgeInsets.all(8.0),
        child: CupertinoActionSheet(
          title: widget.post.getReactionModels.isEmpty
              ? const Text(
                  "No Reactions yet",
                  style: TextStyle(
                    letterSpacing: 3,
                    color: Colors.purple,
                    fontSize: 30,
                    fontFamily: 'DancingScript',
                    fontWeight: FontWeight.w600,
                  ),
                )
              : const Text(
                  "Reactions",
                  style: TextStyle(
                    letterSpacing: 2,
                    color: Colors.purple,
                    fontSize: 30,
                    fontFamily: 'DancingScript',
                    fontWeight: FontWeight.w600,
                  ),
                ),
          actions: List.generate(
            widget.post.getReactionModels.length,
            (index) {
              return CupertinoActionSheetAction(
                child: LikeBody(
                    reactionModel: widget.post.getReactionModels[index]),
                onPressed: () {},
              );
            },
          ),
        ),
      ),
    );
  }

  getList() {
    return PopupMenuButton(
      itemBuilder: (context) => popList,
      child: const Icon(
        Icons.more_vert,
        size: 25.0,
        color: Colors.purple,
      ),
    );
  }

  Widget _buildPopupDialog(BuildContext context) {
    return AlertDialog(
      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(39)),
      content: SizedBox(
        height: 65,
        width: 65,
        child: ListView(
          scrollDirection: Axis.horizontal,
          children: reactionsWidget,
        ),
      ),
    );
  }

  ReactionModel? checkIfLike(List<ReactionModel> list) {
    for (var item in list) {
      if (item.getUserModel.getId == MyApp.currentUser!.id) return item;
    }
    return null;
  }

  Column reactionsModelToWidget(
      ReactionModel reactionsModel, BuildContext context) {
    return Column(
      children: [
        IconButton(
          icon: Icon(
            MdiIcons.heart,
            color:
                Color(int.parse(reactionsModel.getReactionTypeModel.colorName)),
          ),
          iconSize: 22,
          ///// malaz ////
          onPressed: () {
            // print(" >>>>>> " + reactionsModel.reactionTypeModel);
            setState(() {
              if (reaction!.icon == MdiIcons.heart) {
                reaction = Icon(
                  MdiIcons.heart,
                  color: Color(
                      int.parse(reactionsModel.getReactionTypeModel.colorName)),
                  size: 30,
                );
                reactionController.unReaction(likeModel.getId).then((value) {
                  setState(() {
                    int a = int.parse(numLikes) - 1;
                    numLikes = a.toString();
                  });
                });
                reactionController
                    .reaction(widget.post.getId, MyApp.currentUser!.getId,
                        reactionsModel.getId())
                    .then((value) {
                  setState(() {
                    likeModel = value!;
                    int a = int.parse(numLikes) + 1;
                    numLikes = a.toString();
                  });
                });
              }
              if (reaction!.icon == MdiIcons.heartOutline) {
                reaction = Icon(
                  MdiIcons.heart,
                  color: Color(
                      int.parse(reactionsModel.getReactionTypeModel.colorName)),
                  size: 30,
                );
                reactionController
                    .reaction(widget.post.getId, MyApp.currentUser!.getId,
                        reactionsModel.getId())
                    .then((value) {
                  setState(() {
                    int a = int.parse(numLikes) + 1;
                    numLikes = a.toString();
                  });
                });
              }
            });
            // Navigator.pop(context);
          },
        ),
        Text(
          reactionsModel.getReactionTypeModel.getName(),
          style: TextStyle(
            color:
                Color(int.parse(reactionsModel.getReactionTypeModel.colorName)),
            fontFamily: 'Merienda',
            fontSize: 10,
          ),
        ),
      ],
    );
  }

  bool _isSavedBefore(List<UserModel> list) {
    bool isSaved = false;
    for (var userModel in list) {
      if (userModel.getId == MyApp.currentUser!.getId) isSaved = true;
    }
    return isSaved;
  }

  bool _isSharedBefor(List<UserModel> list) {
    bool isShared = false;
    for (var userModel in list) {
      if (userModel.getId == MyApp.currentUser!.getId) isShared = true;
    }
    return isShared;
  }

  bool _isMyFriend(List<UserModel> list, String userPostId) {
    bool isFriend = false;
    for (var userModel in list) {
      if (userModel.getId == userPostId) isFriend = true;
    }
    return isFriend;
  }

  bool _meIn(List<UserModel> list) {
    bool inn = false;
    for (var userModel in list) {
      if (userModel.getId == MyApp.currentUser!.getId) inn = true;
    }
    return inn;
  }

  getButtomSheetShare() {
    showCupertinoModalPopup(
      context: context,
      builder: (BuildContext context) => Padding(
        padding: const EdgeInsets.all(8.0),
        child: CupertinoActionSheet(
          title: widget.post.getParticipants.isEmpty
              ? const Text(
                  "No participants yet",
                  style: TextStyle(
                    letterSpacing: 3,
                    color: Colors.purple,
                    fontSize: 30,
                    fontFamily: 'DancingScript',
                    fontWeight: FontWeight.w600,
                  ),
                )
              : const Text(
                  "participants",
                  style: TextStyle(
                    letterSpacing: 2,
                    color: Colors.purple,
                    fontSize: 30,
                    fontFamily: 'DancingScript',
                    fontWeight: FontWeight.w600,
                  ),
                ),
          actions: List.generate(
            widget.post.getParticipants.length,
            (index) {
              return CupertinoActionSheetAction(
                child: ShareBody(
                    participantModel: widget.post.getParticipants[index]),
                onPressed: () {},
              );
            },
          ),
        ),
      ),
    );
  }

  Widget getBodyOfPost() {
    double width = MediaQuery.of(context).size.width;
    List<Widget> media = [];
    if (widget.post.getMedia != null) {
      for (int i = 0; i < widget.post.getMedia.length; i++) {
        if (widget.post.getMedia[i].type == 'image') {
          media.add(
            SizedBox(
              width: width,
              child: Image(
                image: Image.network(
                  MyApp.mainURL +
                      widget.post.getMedia[i].getImage
                          .toString()
                          .replaceAll("\\", "/"),
                  // headers: {
                  //   "Authorization": "Bearer " + MyApp.currentUser!.getToken
                  // },
                ).image,
                fit: BoxFit.cover,
                // width: double.infinity,
              ),
            ),
          );
          media.add(
            const SizedBox(
              width: 2,
            ),
          );
        } else if (widget.post.getMedia[i].type == 'video') {
          media.add(
            Container(
              color: Colors.purple[100],
              width: width,
              child: Center(
                child: VideoPlayerWidget(MyApp.mainURL +
                    widget.post.getMedia[i].getImage.replaceAll("\\", "/")),
              ),
            ),
          );
        } else {
          return Container();
        }
        media.add(const SizedBox(
          width: 2,
        ));
      }
      ListView mediaWidget = ListView(
        children: media,
        scrollDirection: Axis.horizontal,
      );

      return Column(
        children: [
          Container(
            width: double.infinity,
            padding: const EdgeInsets.symmetric(horizontal: 15),
            child: widget.post.content != null
                ? Align(
                    alignment: Alignment.topLeft,
                    child: ReadMoreText(
                      widget.post.getContent,
                      trimLines: 2,
                      colorClickableText: Colors.black,
                      style: const TextStyle(color: Colors.black, fontSize: 15),
                      textAlign: TextAlign.left,
                      trimMode: TrimMode.Line,
                      trimCollapsedText: 'Show more',
                      trimExpandedText: 'Show less',
                      lessStyle: const TextStyle(
                          fontSize: 13, fontWeight: FontWeight.bold),
                      moreStyle: const TextStyle(
                          fontSize: 13, fontWeight: FontWeight.bold),
                    ),
                  )
                : Container(),
          ),
          widget.post.content != null
              ? SizedBox(
                  width: width,
                  height: 10,
                )
              : SizedBox(
                  width: width,
                  height: 0,
                ),
          SizedBox(
            height: widget.post.getMedia.isEmpty ? 0 : 250,
            child: widget.post.getMedia.isEmpty
                ? SizedBox(
                    width: width,
                    height: 10,
                  )
                : mediaWidget,
          )
        ],
      );
    } else {
      return Container();
    }
  }
}
