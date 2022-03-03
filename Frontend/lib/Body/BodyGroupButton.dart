import 'package:flutter/material.dart';
import 'package:frontend/Model/GroupModel.dart';
import 'package:frontend/Pages/Group.dart';
import 'package:frontend/main.dart';

class BodyGroupButton extends StatefulWidget {
  GroupModel groupModel;

  BodyGroupButton({Key? key, required this.groupModel}) : super(key: key);

  @override
  _BodyGroupButtonState createState() => _BodyGroupButtonState();
}

class _BodyGroupButtonState extends State<BodyGroupButton> {
  @override
  Widget build(BuildContext context) {
    double width = MediaQuery.of(context).size.width;
    return SizedBox(
      height: 48,
      width: width,
      child: Padding(
        padding: const EdgeInsets.fromLTRB(8.0, 5.0, 8.0, 0.0),
        child: Padding(
          padding: const EdgeInsets.only(left: 4.0, right: 2.0),
          child: Row(
            children: [
              if (widget.groupModel.getImage != null)
                Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: SizedBox(
                    height: 32.0,
                    width: 32.0,
                    child: CircleAvatar(
                      backgroundColor: const Color.fromRGBO(233, 207, 236, 1),
                      backgroundImage: Image.network(
                        MyApp.mainURL +
                            widget.groupModel.getImage
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
              if (widget.groupModel.getImage == null)
                const Padding(
                  padding: EdgeInsets.all(8.0),
                  child: SizedBox(
                    height: 32.0,
                    width: 32.0,
                    child: CircleAvatar(
                      backgroundColor: Color.fromRGBO(233, 207, 236, 1),
                      foregroundColor: Colors.purple,
                      child: Icon(
                        Icons.group,
                        size: 17,
                      ),
                    ),
                  ),
                ),
              if (widget.groupModel.getAdmin.id == MyApp.currentUser.id)
                Row(
                  children: [
                    TextButton(
                        child: RichText(
                          text: TextSpan(
                            children: [
                              TextSpan(
                                text: widget.groupModel.getName,
                                style: const TextStyle(
                                  fontSize: 18,
                                  color: Colors.purple,
                                  fontFamily: 'Merienda',
                                  fontWeight: FontWeight.w500,
                                ),
                              ),
                            ],
                          ),
                        ),
                        onPressed: () {
                          Navigator.push(
                            context,
                            MaterialPageRoute(
                              builder: (context) {
                                return Group1(
                                  group_id: widget.groupModel.getId,
                                );
                              },
                            ),
                          );
                        } //
                        ),
                    Padding(
                      padding: const EdgeInsets.all(8.0),
                      child: Row(
                        children: [
                          Container(
                            height: 30,
                            width: 45.0,
                            child: const Padding(
                              padding: EdgeInsets.all(8.0),
                              child: Text(
                                "Admin",
                                style: TextStyle(
                                  fontSize: 9,
                                  color: Color.fromRGBO(233, 207, 236, 1),
                                ),
                              ),
                            ),
                            decoration: BoxDecoration(
                              color: Colors.purple[200],
                              borderRadius: const BorderRadius.only(
                                bottomLeft: Radius.circular(22.0),
                                topLeft: Radius.circular(22.0),
                                bottomRight: Radius.circular(22.0),
                              ),
                            ),
                          ),
                        ],
                      ),
                    ),
                  ],
                ),
              if (widget.groupModel.getAdmin.id != MyApp.currentUser.id)
                Padding(
                  padding: const EdgeInsets.only(top: 0.0, left: 3.0),
                  child: Row(
                    children: [
                      TextButton(
                        child: RichText(
                          text: TextSpan(
                            children: [
                              TextSpan(
                                text: widget.groupModel.getName,
                                style: const TextStyle(
                                  fontSize: 18,
                                  color: Colors.purple,
                                  fontFamily: 'Merienda',
                                  fontWeight: FontWeight.w500,
                                ),
                              ),
                            ],
                          ),
                        ),
                        onPressed: () {
                          Navigator.push(
                            context,
                            MaterialPageRoute(
                              builder: (context) {
                                return Group1(
                                  group_id: widget.groupModel.getId,
                                );
                              },
                            ),
                          );
                        },
                      ),
                    ],
                  ),
                ),
            ],
          ),
        ),
      ),
    );
  }
}
