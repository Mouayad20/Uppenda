// ignore_for_file: unnecessary_null_comparison

import 'package:flutter/material.dart';
import 'package:frontend/Model/PageModel.dart';
import 'package:frontend/Pages/Page.dart';
import 'package:frontend/main.dart';

class BodyPageButton extends StatefulWidget {
  PageModel pageModel;
  BodyPageButton({Key? key, required this.pageModel}) : super(key: key);

  @override
  _BodyPageButtonState createState() => _BodyPageButtonState();
}

class _BodyPageButtonState extends State<BodyPageButton> {
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
              if (widget.pageModel.imgPath != null)
                Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: SizedBox(
                    height: 32.0,
                    width: 32.0,
                    child: CircleAvatar(
                      backgroundColor: const Color.fromRGBO(233, 207, 236, 1),
                      backgroundImage: Image.network(
                        MyApp.mainURL +
                            widget.pageModel.getImage
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
              if (widget.pageModel.imgPath == null)
                const Padding(
                  padding: EdgeInsets.all(8.0),
                  child: SizedBox(
                    height: 32.0,
                    width: 32.0,
                    child: CircleAvatar(
                      backgroundColor: Color.fromRGBO(233, 207, 236, 1),
                      foregroundColor: Colors.purple,
                      child: Icon(
                        Icons.description,
                        size: 15,
                      ),
                    ),
                  ),
                ),
              if (widget.pageModel.admin!.id == MyApp.currentUser!.id)
                Padding(
                  padding: const EdgeInsets.only(bottom: 0.0, left: 3.0),
                  child: Row(
                    children: [
                      TextButton(
                        child: RichText(
                          text: TextSpan(
                            children: [
                              TextSpan(
                                text: widget.pageModel.name,
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
                                return Page1(
                                  page_id: widget.pageModel.getId,
                                );
                              },
                            ),
                          );
                        },
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
                ),
              if (widget.pageModel.admin!.id != MyApp.currentUser!.id)
                Padding(
                  padding: const EdgeInsets.only(top: 0.0, left: 3.0),
                  child: Row(
                    children: [
                      TextButton(
                        child: RichText(
                          text: TextSpan(
                            children: [
                              TextSpan(
                                text: widget.pageModel.name,
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
                                return Page1(
                                  page_id: widget.pageModel.getId,
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
