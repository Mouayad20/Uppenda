import 'package:flutter/material.dart';
import 'package:frontend/Controllers/CacheController.dart';
import 'package:frontend/Global/Global.dart';
import 'package:frontend/Model/UserModel.dart';
import 'package:frontend/View/Splash/splash.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Splash(),
    );
  }
}
