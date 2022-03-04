import 'package:flutter/material.dart';

class TextFieldCustom extends StatelessWidget {
  final IconData icon;
  final TextInputType type;
  final bool pass;
  final String text;
  final TextEditingController controller;

  const TextFieldCustom(
      {Key? key,
       required this.icon,
       required this.type,
      this.pass = false,
       required this.text,
       required this.controller})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return TextField(
      controller: controller,
      keyboardType: type,
      obscureText: pass,
      decoration: InputDecoration(
        hintText: text,
        filled: true,
        fillColor: const Color.fromRGBO(233, 207, 236, 1.0),
        prefixIcon: Icon(icon, color: Colors.grey),
        border: OutlineInputBorder(
            borderSide: const BorderSide(color: Color(0xffEBDCFA)),
            borderRadius: BorderRadius.circular(50)),
        enabledBorder: OutlineInputBorder(
          borderSide: const BorderSide(color: Color(0xffEBDCFA)),
          borderRadius: BorderRadius.circular(50),
        ),
      ),
    );
  }
}
