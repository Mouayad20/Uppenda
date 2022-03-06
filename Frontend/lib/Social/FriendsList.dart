import 'package:flutter/material.dart';
import 'package:frontend/Model/UserModel.dart';
import 'package:frontend/Pages/profile.dart';
import 'package:frontend/main.dart';

import '../Global/Global.dart';

class FriendsList extends StatefulWidget {
  UserModel friend;
  FriendsList({Key? key, required this.friend}) : super(key: key);

  @override
  _FriendsListState createState() => _FriendsListState();
}

class _FriendsListState extends State<FriendsList> {
  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.transparent,
      height: 35,
      child: Row(
        children: [
          if (widget.friend.getImage == null)
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
          if (widget.friend.imagePath != null)
            Container(
              height: 30.0,
              width: 30.0,
              color: Colors.transparent,
              child: CircleAvatar(
                backgroundColor: const Color.fromRGBO(233, 207, 236, 1),
                backgroundImage: Image.network(
                  mainURL +
                      widget.friend.imagePath.toString().replaceAll("\\", "/"),
                  // headers: {
                  //   "Authorization": "Bearer " + MyApp.currentUser.getToken
                  // },
                ).image,
              ),
            ),
          TextButton(
            child: Text(
              widget.friend.getFirstName + ' ' + widget.friend.getLastName,
              style: const TextStyle(
                fontSize: 15,
                color: Colors.purple,
                fontFamily: 'Merienda',
                fontWeight: FontWeight.w700,
              ),
            ),
            onPressed: () {
              Navigator.push(
                context,
                MaterialPageRoute(
                  builder: (context) {
                    return Profile(
                      user_id: widget.friend.getId,
                    );
                  },
                ),
              );
            },
          ),
        ],
      ),
    );
  }
}
