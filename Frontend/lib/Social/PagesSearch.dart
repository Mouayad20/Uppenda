import 'package:flutter/material.dart';
import 'package:frontend/Model/PageModel.dart';
import 'package:frontend/Pages/Page.dart';

class PagesSearch extends StatefulWidget {
  PageModel pageModel;

  PagesSearch({Key? key, required this.pageModel}) : super(key: key);

  @override
  _PagesSearchState createState() => _PagesSearchState();
}

class _PagesSearchState extends State<PagesSearch> {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Row(
        children: [
          const Padding(
            padding: EdgeInsets.only(top: 6.0, left: 8.0, right: 12),
            child: Icon(
              Icons.search,
              color: Color.fromRGBO(233, 207, 236, 1),
            ),
          ),
          InkWell(
            child: RichText(
              text: TextSpan(
                children: [
                  TextSpan(
                    text: widget.pageModel.name,
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
    );
  }
}
