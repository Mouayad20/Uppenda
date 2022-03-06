import 'package:flutter/material.dart';
import 'package:frontend/Model/GroupModel.dart';
import 'package:frontend/Pages/Group.dart';

class GroupsSearch extends StatefulWidget {
  GroupModel groupModel;

  GroupsSearch({Key? key, required this.groupModel}) : super(key: key);

  @override
  _GroupsSearchState createState() => _GroupsSearchState();
}

class _GroupsSearchState extends State<GroupsSearch> {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Row(
        children: [
          const Padding(
            padding: EdgeInsets.only(top: 6.0, left: 8.0, right: 12),
            child: Icon(Icons.search, color: Color.fromRGBO(233, 207, 236, 1)),
          ),
          InkWell(
            child: RichText(
              text: TextSpan(
                children: [
                  TextSpan(
                    text: widget.groupModel.getName,
                    style: const TextStyle(
                      fontSize: 18,
                      color: Colors.black38,
                      fontFamily: 'Merienda',
                      fontWeight: FontWeight.w500,
                    ),
                  ),
                ],
              ),
            ),
            onTap: () {
              if (widget.groupModel.getId != null) {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) {
                      if (widget.groupModel.getId == null) {
                        return const Center(
                            child: CircularProgressIndicator());
                      } else {
                        return Group1(
                          groupId: widget.groupModel.getId,
                        );
                      }
                    },
                  ),
                );
              }
            },
          ),
        ],
      ),
    );
  }
}
