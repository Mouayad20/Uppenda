import 'package:flutter/material.dart';
import 'package:frontend/Body/BodyGroupButton.dart';
import 'package:frontend/Model/GroupModel.dart';

class Groups extends StatefulWidget {
  List<GroupModel> groupModel;

  Groups({Key? key, required this.groupModel}) : super(key: key);

  @override
  GroupsState createState() => GroupsState();
}

class GroupsState extends State<Groups> {
  @override
  Widget build(BuildContext context) {
    return ListView.separated(
        itemBuilder: (context, i) {
          return BodyGroupButton(
            groupModel: widget.groupModel[i],
          );
        },
        separatorBuilder: (ctx, i) {
          return const Padding(
            padding: EdgeInsets.only(left: 14.0, right: 14.0),
            child: Divider(
              thickness: 0.5,
              color: Colors.purple,
            ),
          );
        },
        itemCount: widget.groupModel.length);
  }
}
