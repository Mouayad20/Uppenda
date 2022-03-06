// ignore_for_file: must_be_immutable

import 'package:flutter/material.dart';
import 'package:frontend/Controllers/CacheController.dart';
import 'package:frontend/View/Splash/splash.dart';
import 'package:get_storage/get_storage.dart';

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
