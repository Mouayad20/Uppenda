import 'package:flutter/material.dart';
import 'package:frontend/Model/GroupModel.dart';

class Go extends StatefulWidget {
  GroupModel pageModel;
  Go({Key? key, required this.pageModel}) : super(key: key);

  @override
  _GoState createState() => _GoState();
}

class _GoState extends State<Go> {
  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Text(widget.pageModel.getId),
        Text(widget.pageModel.getName),
        Text(widget.pageModel.getDescription),
        Text(widget.pageModel.getCreatedAt),
        Text(widget.pageModel.getImage),
      ],
    );
  }
}
