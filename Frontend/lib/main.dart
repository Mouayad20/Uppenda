import 'dart:io';

import 'package:flutter/material.dart';
import 'package:frontend/Global/Global.dart';
import 'package:frontend/Pages/Splash.dart';
import 'Global/Global.dart';
import 'Model/UserModel.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: FirstScreen(),
    );
  }
}
