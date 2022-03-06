import 'package:flutter/material.dart';
import 'package:frontend/Model/UserModel.dart';
import 'package:frontend/main.dart';

import '../Global/Global.dart';

class ShareBody extends StatefulWidget {
  UserModel participantModel;

  ShareBody({Key? key, required this.participantModel}) : super(key: key);

  @override
  _ShareBodyState createState() => _ShareBodyState();
}

class _ShareBodyState extends State<ShareBody> {
  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.transparent,
      height: 35,
      child: Row(
        children: [
          if (widget.participantModel.getImage == null)
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
          if (widget.participantModel.imagePath != null)
            Container(
              height: 30.0,
              width: 30.0,
              color: Colors.transparent,
              child: CircleAvatar(
                backgroundColor: const Color.fromRGBO(233, 207, 236, 1),
                backgroundImage: Image.network(
                  mainURL +
                      widget.participantModel.imagePath
                          .toString()
                          .replaceAll("\\", "/"),
                  // headers: {
                  //   "Authorization": "Bearer " + MyApp.currentUser.getToken
                  // },
                ).image,
              ),
            ),
          Padding(
            padding: const EdgeInsets.fromLTRB(8.0, 0.0, 8.0, 4.0),
            child: Text(
              widget.participantModel.getFirstName +
                  ' ' +
                  widget.participantModel.getLastName,
              style: const TextStyle(
                fontSize: 15,
                color: Colors.purple,
                fontFamily: 'Merienda',
                fontWeight: FontWeight.w700,
              ),
            ),
          ),
        ],
      ),
    );
  }
}
