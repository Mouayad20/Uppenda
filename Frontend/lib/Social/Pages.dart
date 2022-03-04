import 'package:flutter/material.dart';
import 'package:frontend/Body/BodyPageButton.dart';
import 'package:frontend/Model/PageModel.dart';

class Pages extends StatefulWidget {
  List<PageModel> pageModel;

  Pages({Key? key, required this.pageModel}) : super(key: key);

  @override
  PagesState createState() => PagesState();
}

class PagesState extends State<Pages> {
  @override
  Widget build(BuildContext context) {
    return ListView.separated(
        itemBuilder: (context, i) {
          return BodyPageButton(pageModel: widget.pageModel[i]);
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
        itemCount: widget.pageModel.length);
  }
}
