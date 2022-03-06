import 'package:flutter/material.dart';
import 'package:frontend/Model/ReactionModel.dart';

import '../Global/Global.dart';
import '../main.dart';

class LikeBody extends StatefulWidget {
  ReactionModel reactionModel;

  LikeBody({Key? key, required this.reactionModel}) : super(key: key);

  @override
  LikeBodyState createState() => LikeBodyState();
}

class LikeBodyState extends State<LikeBody> {
  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.transparent,
      height: 35,
      child: Row(
        children: [
          if (widget.reactionModel.userModel!.getImage == null)
            Container(
              height: 30.0,
              width: 30.0,
              color: Colors.transparent,
              child: const CircleAvatar(
                backgroundColor: Color.fromRGBO(233, 207, 236, 1),
                foregroundColor: Colors.purple,
                child: Icon(Icons.person),
              ),
            ),
          if (widget.reactionModel.userModel!.imagePath != null)
            Container(
              height: 30.0,
              width: 30.0,
              color: Colors.transparent,
              child: CircleAvatar(
                backgroundColor: const Color.fromRGBO(233, 207, 236, 1),
                backgroundImage: Image
                    .network(
                  mainURL +
                      widget.reactionModel.userModel!.imagePath
                          .toString()
                          .replaceAll("\\", "/"),
                  // headers: {
                  //   "Authorization": "Bearer " + MyApp.currentUser.getToken
                  // },
                )
                    .image,
              ),
            ),
          Padding(
            padding: const EdgeInsets.fromLTRB(8.0, 0.0, 8.0, 4.0),
            child: Text(
              widget.reactionModel.userModel!.getFirstName +
                  ' ' +
                  widget.reactionModel.userModel!.getLastName,
              style: const TextStyle(
                fontSize: 15,
                color: Colors.purple,
                fontFamily: 'Merienda',
                fontWeight: FontWeight.w700,
              ),
            ),
          ),
          Icon(
            Icons.cake_rounded,
            // MdiIcons.heart,
            color: Color(
                int.parse(widget.reactionModel.reactionTypeModel!.colorName!)),
          )
        ],
      ),
    );
  }
}
