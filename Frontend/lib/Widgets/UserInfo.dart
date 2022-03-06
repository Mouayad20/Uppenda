import 'package:flutter/material.dart';
import 'package:frontend/Model/UserModel.dart';

import '../Global/Global.dart';
import '../main.dart';

class UserInfo extends StatelessWidget {
  UserModel userModel;

  UserInfo({Key? key, required this.userModel}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: [
        Row(
          children: [
            Container(
              child: CircleAvatar(
                backgroundImage: userModel.getImage == null
                    ? const AssetImage("images/download.jpg")
                    : Image.network(
                        mainURL +
                            userModel.getImage.toString().replaceAll("\\", "/"),
                        // headers: {
                        //   "Authorization":
                        //       "Bearer " + MyApp.currentUser.getToken
                        // },
                      ).image,
                backgroundColor: Colors.purple[100],
                // minRadius: 50,
                maxRadius: 20,
              ),
              decoration: BoxDecoration(
                shape: BoxShape.circle,
                border: Border.all(
                  color: Colors.purple[600]!,
                  width: 2,
                ),
              ),
            ),
            const SizedBox(
              width: 5,
            ),
            Text(
              userModel.getFirstName + " " + userModel.getLastName,
              style: const TextStyle(
                fontWeight: FontWeight.w700,
                color: Colors.purple,
                fontSize: 13,
                fontFamily: 'Merienda',
              ),
            ),
          ],
        ),
        IconButton(
          icon: const Icon(
            Icons.close,
            color: Colors.purple,
          ),
          onPressed: () {},
        ),
      ],
    );
  }
}
