import 'package:flutter/material.dart';

import 'Components/Header.dart';
import 'Components/Logo.dart';
import 'Components/MiddleBar.dart';

class SignUpPage extends StatefulWidget {
  const SignUpPage({Key? key}) : super(key: key);

  @override
  State<SignUpPage> createState() => _SignUpPageState();
}

class _SignUpPageState extends State<SignUpPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: [
          Stack(
            children: const [Header(), Logo()],
          ),
          MiddleBar(1),
          // const SizedBox(height: 20),
        ],
      ),
    );
  }
}
