// ignore_for_file: must_be_immutable

import 'package:flutter/material.dart';

import 'Components/Header.dart';
import 'Components/Logo.dart';

class RegisterPage extends StatefulWidget {
  const RegisterPage({Key? key}) : super(key: key);

  @override
  State<RegisterPage> createState() => _RegisterPageState();
}

class _RegisterPageState extends State<RegisterPage> {
  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Stack(
          children: [Header(), Logo()],
        )
      ],
    );
  }
}
