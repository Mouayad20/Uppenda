// ignore_for_file: must_be_immutable

import 'package:flutter/material.dart';
import 'package:frontend/View/Loging/Components/MiddleBar.dart';

import 'Components/Header.dart';
import 'Components/Logo.dart';

class SignInPage extends StatefulWidget {
  const SignInPage({Key? key}) : super(key: key);

  @override
  State<SignInPage> createState() => _SignInPageState();
}

class _SignInPageState extends State<SignInPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: [
          Stack(
            children: const [Header(), Logo()],
          ),
          MiddleBar(0),
          // const SizedBox(height: 20),
        ],
      ),
    );
  }
}
