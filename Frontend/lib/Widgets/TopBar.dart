import 'package:flutter/material.dart';
import 'package:material_design_icons_flutter/material_design_icons_flutter.dart';

class TopBar extends StatefulWidget implements PreferredSizeWidget {
  const TopBar({Key? key})
      : preferredSize = const Size.fromHeight(kToolbarHeight),
        super(key: key);

  @override
  final Size preferredSize; // default is 56.0

  @override
  _TopBarState createState() => _TopBarState();
}

class _TopBarState extends State<TopBar> {
  @override
  Widget build(BuildContext context) {
    return AppBar(
      backgroundColor: Colors.white,
      centerTitle: true,
      elevation: 1.0,
      leading: const Icon(
        MdiIcons.searchWeb,
        size: 30,
        color: Colors.purple,
      ),
      title: const SizedBox(
        height: 40.0,
        child: Text(
          "Uppenda",
          style: TextStyle(
              letterSpacing: 3, fontFamily: 'DancingScript', fontSize: 30),
        ),
      ),
      actions: const [
        Padding(
          padding: EdgeInsets.only(right: 13.0),
          child: Icon(
            MdiIcons.messageBulleted,
            size: 28,
            color: Colors.purple,
          ),
        )
      ],
    );
  }
}
