// ignore_for_file: deprecated_member_use

import 'package:flutter/material.dart';
import 'package:frontend/View/Loging/SignInPage.dart';

import '../SignUpPage.dart';

class MiddleBar extends StatelessWidget {
  int index;

  MiddleBar(this.index, {Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(15.0),
      child: Row(
        children: [
          FlatButton(
              child: Text(
                'SIGN IN',
                style: TextStyle(
                  fontFamily: 'Merienda',
                  fontSize: 25,
                  fontWeight: FontWeight.bold,
                  color: index == 0 ? Colors.black : Colors.grey,
                ),
              ),
              onPressed: () {
                Navigator.of(context).push(
                  MaterialPageRoute(
                    builder: (context) => const SignInPage(),
                  ),
                );
              }),
          const Text(
            '/',
            style: TextStyle(
              fontSize: 25,
              fontFamily: 'Merienda',
              color: Colors.grey,
            ),
          ),
          FlatButton(
            child: Text(
              'SIGN UP',
              style: TextStyle(
                fontFamily: 'Merienda',
                fontSize: 18,
                fontWeight: FontWeight.bold,
                color: index == 1 ? Colors.black : Colors.grey,
              ),
            ),
            onPressed: () => Navigator.of(context).push(
              MaterialPageRoute(
                builder: (context) => const SignUpPage(),
              ),
            ),
          ),
        ],
      ),
    );
  }
}
