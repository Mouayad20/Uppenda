import 'package:flutter/material.dart';
import 'package:frontend/Model/PageModel.dart';

class pdfWidget extends StatefulWidget {
  PageModel pageModel;

  pdfWidget({Key? key, required this.pageModel}) : super(key: key);

  @override
  _pdfWidgetState createState() => _pdfWidgetState();
}

class _pdfWidgetState extends State<pdfWidget> {
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
