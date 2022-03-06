import 'package:flutter/material.dart';
import 'package:animated_text_kit/animated_text_kit.dart';

import 'package:frontend/Controllers/UserController.dart';
import 'package:frontend/View/Loging/SignInPage.dart';
import '../../Controllers/CacheController.dart';
import '../../Global/Global.dart';
import '../../Pages/Login.dart';
import '../../Social/Social_Home.dart';

class Splash extends StatefulWidget {
  const Splash({Key? key}) : super(key: key);

  @override
  State<Splash> createState() => _SplashState();
}

class _SplashState extends State<Splash> {
  UserController userController = UserController();
  CacheController cacheController = CacheController();

  @override
  void initState() {
    super.initState();
    cacheController.getUser();
    cacheController.getToken();
    Future.delayed(
      const Duration(seconds: 4),
      () {
        Navigator.pushReplacement(
          context,
          MaterialPageRoute(
            builder: (context) => Scaffold(
              body: token == "null" ? LogInPage() : SocialHome(),
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
      child: TextLiquidFill(
        text: 'Uppenda',
        waveColor: purple,
        boxBackgroundColor: Colors.white,
        boxHeight: heightScreen(context),
        loadDuration: const Duration(
          seconds: 2,
        ),
        textStyle: const TextStyle(
          fontSize: 80.0,
          fontWeight: FontWeight.w500,
          letterSpacing: 6,
          fontFamily: dancingScript,
        ),
      ),
    );
  }
}
