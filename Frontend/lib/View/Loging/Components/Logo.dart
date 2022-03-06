
import 'package:flutter/material.dart';
import 'package:frontend/Global/Global.dart';

class Logo extends StatelessWidget {
  const Logo({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Positioned(
      top: heightScreen(context) / 8,
      left: widthScreen(context) / 4,
      child: const Text(
        'Uppenda',
        style: TextStyle(
          color: Colors.white,
          fontSize: 50,
          fontWeight: FontWeight.w500,
          letterSpacing: 6,
          fontFamily: dancingScript,
        ),
      ),
    );
  }
}
