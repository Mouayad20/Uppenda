import 'dart:io';

import 'package:animated_text_kit/animated_text_kit.dart';
import 'package:flutter/material.dart';
import 'package:frontend/Controllers/UserController.dart';
import 'package:frontend/Pages/Login.dart';
import 'package:frontend/Social/Social_Home.dart';
import 'package:frontend/main.dart';

import '../Controllers/CacheController.dart';
import '../Global/Global.dart';

class FirstScreen extends StatefulWidget {
  @override
  _FirstScreenState createState() => _FirstScreenState();
}

class _FirstScreenState extends State<FirstScreen> {
  UserController userController = UserController();
  CacheController cacheController = CacheController();
  bool? mimo;

  @override
  void initState() {
    super.initState();
    // cacheController.getUserFromCache().then((value1) {
    //   setState(() {
    //     if (value1 != null) {
    //       setState(() {
    //         cacheController.getTokenFromCache().then((tkn) {
    //           setState(() {
    //             // currentUser.setToken = tkn;
    //           });
    //         });
    //         userController.getUserById(value1).then((value) {
    //           setState(() {
    //             currentUser = value;
    //             mimo = true;
    //             NetworkInterface.list().then((value2) {
    //               setState(() {
    //                 currentUser!.setIp =
    //                     "/" + value2.first.addresses.first.address;
    //                 currentUser!.setOnLine = true;
    //                 userController
    //                     .updateUser(currentUser!, false)
    //                     .then((value3) {
    //                   setState(() {
    //                     currentUser = value3;
    //                   });
    //                 });
    //               });
    //             });
    //           });
    //         });
    //       });
    //     } else {
    //       setState(() {
    //         mimo = false;
    //       });
    //     }
    //   });
    // });
    Future.delayed(
      const Duration(seconds: 7),
      () {
        Navigator.pushReplacement(
          context,
          MaterialPageRoute(
            builder: (context) => MaterialApp(
              title: 'SocialApp',
              debugShowCheckedModeBanner: false,
              theme: ThemeData(
                visualDensity: VisualDensity.adaptivePlatformDensity,
                primaryColor: Colors.white,
                primaryIconTheme: const IconThemeData(
                  color: Colors.purple,
                ),
                primaryTextTheme: const TextTheme(
                  titleLarge: TextStyle(
                    color: Colors.purple,
                  ),
                ),
                textTheme: const TextTheme(
                  titleLarge: TextStyle(
                    color: Colors.purple,
                  ),
                ),
              ),
              home: Scaffold(
                body: mimo == null
                    ? const Center(
                        child: CircularProgressIndicator(),
                      )
                    : mimo!
                        ? SocialHome()
                        : LogInPage(),
              ),
            ),
          ),
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return CircleAvatar(
      backgroundColor: Colors.black,
      radius: 50,
      child: TextLiquidFill(
        text: 'Uppenda',
        waveColor: Colors.purple,
        boxBackgroundColor: Colors.white,
        boxHeight: 800.0,
        loadDuration: const Duration(
          seconds: 3,
        ),
        textStyle: const TextStyle(
          fontSize: 80.0,
          fontWeight: FontWeight.w500,
          letterSpacing: 6,
          fontFamily: 'DancingScript',
        ),
      ),
    );
  }
}
