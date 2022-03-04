import 'package:flutter/material.dart';
import 'package:frontend/Model/UserModel.dart';

class UseWidget extends StatefulWidget {
  UserModel pageModel;

  UseWidget({Key? key, required this.pageModel}) : super(key: key);

  @override
  _UseWidgetState createState() => _UseWidgetState();
}

class _UseWidgetState extends State<UseWidget> {
  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Text(widget.pageModel.getId),
        Text(widget.pageModel.getFirstName),
        Text(widget.pageModel.getLastName),
        Text(widget.pageModel.getCreatedAt.toString()),
        Text(widget.pageModel.getImage),
      ],
    );
  }
}
