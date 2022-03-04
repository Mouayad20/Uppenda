import 'package:flutter/material.dart';
import 'package:frontend/Model/CommentModel.dart';
import 'package:frontend/Pages/profile.dart';
import 'package:frontend/Controllers/CommentController.dart';
import 'package:frontend/main.dart';
import 'package:readmore/readmore.dart';

class CommentBody extends StatefulWidget {
  CommentModel commentModel;

  CommentBody({Key? key, required this.commentModel}) : super(key: key);

  @override
  _CommentBodyState createState() => _CommentBodyState();
}

class _CommentBodyState extends State<CommentBody> {
  TextEditingController commentContentController = TextEditingController();
  CommentController commentController = CommentController();

  @override
  void initState() {
    super.initState();
    commentContentController.text = widget.commentModel.content!;
  }

  @override
  Widget build(BuildContext context) {
    double width = MediaQuery.of(context).size.width;
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
        height: 180,
        width: width,
        child: Padding(
          padding: const EdgeInsets.all(10.0),
          child: SingleChildScrollView(
            child: Column(
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Row(
                      children: [
                        if (widget.commentModel.getUserModel.imagePath == null)
                          const Padding(
                            padding: EdgeInsets.all(8.0),
                            child: SizedBox(
                              height: 30.0,
                              width: 30.0,
                              child: CircleAvatar(
                                backgroundColor:
                                    Color.fromRGBO(233, 207, 236, 1),
                                foregroundColor: Colors.purple,
                                child: Icon(
                                  Icons.person,
                                  size: 20,
                                ),
                              ),
                            ),
                          ),
                        if (widget.commentModel.getUserModel.imagePath != null)
                          Padding(
                            padding: const EdgeInsets.all(8.0),
                            child: SizedBox(
                              height: 30.0,
                              width: 30.0,
                              child: CircleAvatar(
                                backgroundColor:
                                    const Color.fromRGBO(233, 207, 236, 1),
                                backgroundImage: Image.network(
                                  MyApp.mainURL +
                                      widget.commentModel.getUserModel.imagePath
                                          .toString()
                                          .replaceAll("\\", "/"),
                                  // headers: {
                                  //   "Authorization":
                                  //       "Bearer " + MyApp.currentUser.getToken
                                  // },
                                ).image,
                              ),
                            ),
                          ),
                        SizedBox(
                          width: 140,
                          child: InkWell(
                            child: RichText(
                              text: TextSpan(
                                children: [
                                  TextSpan(
                                    text: widget.commentModel.getUserModel
                                            .getFirstName +
                                        ' ' +
                                        widget.commentModel.getUserModel
                                            .getLastName,
                                    style: const TextStyle(
                                      fontSize: 11,
                                      color: Colors.purple,
                                      fontFamily: 'Merienda',
                                      fontWeight: FontWeight.w700,
                                    ),
                                  ),
                                  const TextSpan(
                                    text: "\n",
                                  ),
                                  TextSpan(
                                    text: widget.commentModel.getCreatedAt,
                                    style: const TextStyle(
                                      fontSize: 8,
                                      color: Colors.grey,
                                      fontFamily: 'Merienda',
                                      fontWeight: FontWeight.w700,
                                    ),
                                  ),
                                ],
                              ),
                            ),
                            onTap: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                  builder: (context) {
                                    return Profile(
                                      user_id: widget
                                          .commentModel.getUserModel.getId,
                                    );
                                  },
                                ),
                              );
                            },
                          ),
                        ),
                        getList(),
                      ],
                    ),
                  ],
                ),
                const Divider(
                  thickness: 0.5,
                  color: Colors.purple,
                ),
                if (widget.commentModel.getImage == null &&
                    widget.commentModel.getContent != null)
                  Column(
                    children: [
                      Padding(
                        padding: const EdgeInsets.fromLTRB(15, 0.0, 8.0, 4.0),
                        child: Align(
                          alignment: Alignment.topLeft,
                          child: ReadMoreText(
                            widget.commentModel.getContent,
                            trimLines: 6,
                            colorClickableText: Colors.black,
                            style: const TextStyle(
                                color: Colors.black, fontSize: 15),
                            textAlign: TextAlign.left,
                            trimMode: TrimMode.Line,
                            trimCollapsedText: 'Show more',
                            trimExpandedText: 'Show less',
                            lessStyle: const TextStyle(
                                fontSize: 13, fontWeight: FontWeight.bold),
                            moreStyle: const TextStyle(
                                fontSize: 13, fontWeight: FontWeight.bold),
                          ),
                        ),
                      ),
                    ],
                  ),
                if (widget.commentModel.getImage != null &&
                    widget.commentModel.getContent == null)
                  Container(
                    width: 300,
                    height: 130,
                    padding:
                        const EdgeInsets.only(right: 40, left: 30, top: 10),
                    child: Image(
                      image: Image.network(
                              MyApp.mainURL + widget.commentModel.imagePath!)
                          .image,
                      fit: BoxFit.fill,
                    ),
                  ),
                if (widget.commentModel.getImage != null &&
                    widget.commentModel.getContent != null)
                  Column(
                    children: [
                      Padding(
                        padding: const EdgeInsets.fromLTRB(15, 0.0, 8.0, 4.0),
                        child: Align(
                          alignment: Alignment.topLeft,
                          child: ReadMoreText(
                            widget.commentModel.getContent,
                            trimLines: 2,
                            colorClickableText: Colors.black,
                            style: const TextStyle(
                                color: Colors.black, fontSize: 15),
                            textAlign: TextAlign.left,
                            trimMode: TrimMode.Line,
                            trimCollapsedText: 'Show more',
                            trimExpandedText: 'Show less',
                            lessStyle: const TextStyle(
                                fontSize: 13, fontWeight: FontWeight.bold),
                            moreStyle: const TextStyle(
                                fontSize: 13, fontWeight: FontWeight.bold),
                          ),
                        ),
                      ),
                      Container(
                        width: 200,
                        height: 120,
                        padding:
                            const EdgeInsets.only(right: 10, left: 10, top: 10),
                        child: Image(
                          image: Image.network(
                            MyApp.mainURL +
                                widget.commentModel.imagePath
                                    .toString()
                                    .replaceAll("\\", "/"),
                            // headers: {
                            //   "Authorization":
                            //       "Bearer " + MyApp.currentUser.getToken
                            // },
                          ).image,
                          fit: BoxFit.fill,
                        ),
                      ),
                    ],
                  ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  getList() {
    if (MyApp.currentUser!.id == widget.commentModel.getUserModel.id) {
      return PopupMenuButton(
        itemBuilder: (context) => [
          PopupMenuItem(
            child: InkWell(
              child: const Text(
                "Update",
                style: TextStyle(color: Colors.purple, fontFamily: 'Merienda'),
              ),
              onTap: () {
                updateComment();
              },
            ),
          ),
          PopupMenuItem(
            child: InkWell(
              child: const Text(
                "Delete",
                style: TextStyle(color: Colors.purple, fontFamily: 'Merienda'),
              ),
              onTap: () {
                CommentController().deleteById(widget.commentModel.getId);
              },
            ),
          ),
        ],
        child: const Icon(
          Icons.more_vert,
          size: 15.0,
          color: Colors.purple,
        ),
      );
    }
  }

  void updateComment() {
    showModalBottomSheet(
      context: context,
      builder: (context1) {
        return ListView(
          children: [
            Padding(
              padding: const EdgeInsets.fromLTRB(8.0, 30.0, 8.0, 8.0),
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
                        controller: commentContentController,
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
                      widget.commentModel.setContent =
                          commentContentController.text.trim();
                      commentController.updateComment(widget.commentModel);
                      Navigator.pop(context);
                    },
                  ),
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
        );
      },
    );
  }
}
