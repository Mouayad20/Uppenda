import 'package:flutter/material.dart';
import 'package:frontend/Widgets/BottomBar.dart';
import 'package:frontend/Widgets/TopBar.dart';

class Home extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return const Scaffold(
      appBar: TopBar(),
      bottomNavigationBar: BottomBar(),
      body: Center(
        child: Text("Home"),
      ),
    );
  }
}
