// ignore_for_file: use_key_in_widget_constructors

import 'package:flutter/material.dart';
import 'package:frontend/Global/Global.dart';

class Logo extends StatelessWidget {
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
