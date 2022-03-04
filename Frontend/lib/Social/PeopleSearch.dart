import 'package:flutter/material.dart';
import 'package:frontend/Model/UserModel.dart';
import 'package:frontend/Pages/profile.dart';

class PeopleSearch extends StatefulWidget {
  UserModel userModel;

  PeopleSearch({Key? key, required this.userModel}) : super(key: key);

  @override
  _PeopleSearchState createState() => _PeopleSearchState();
}

class _PeopleSearchState extends State<PeopleSearch> {
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
                    text: widget.userModel.firstName! +
                        ' ' +
                        widget.userModel.lastName!,
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
                    return Profile(
                      user_id: widget.userModel.getId,
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
