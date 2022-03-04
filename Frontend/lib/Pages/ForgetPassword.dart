import 'package:flutter/material.dart';
import 'package:frontend/Model/QuestionModel.dart';
import '../Widgets/Header.dart';
import '../Widgets/Logo.dart';
import '../Widgets/TextFieldCustom.dart';

class ForgetPasswordPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: ListView(
          padding: const EdgeInsets.only(top: 0),
          physics: const BouncingScrollPhysics(),
          children: [
            Stack(
              children: [HeaderLogin(), LogoHeader()],
            ),
            const SizedBox(height: 30),
            Check(),
            const SizedBox(height: 10),
            ButtonOk()
          ]),
    );
  }
}

class Check extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    TextEditingController? t = TextEditingController();
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 20.0),
      child: Column(
        children: [
          TextFieldCustom(
            icon: Icons.person,
            type: TextInputType.text,
            text: 'Please Enter Your Firstname ',
            controller: t,
          ),
          const SizedBox(height: 20),
          TextFieldCustom(
            icon: Icons.person,
            type: TextInputType.text,
            text: 'Please Enter Your Lastname',
            controller: t,
          ),
          const SizedBox(height: 20),
          TextFieldCustom(
            icon: Icons.phone,
            type: TextInputType.text,
            text: 'Please Enter Your Number',
            controller: t,
          ),
          const SizedBox(height: 20),
        ],
      ),
    );
  }
}

class ButtonOk extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      margin: const EdgeInsets.all(25),
      decoration: BoxDecoration(
          color: Colors.purple, borderRadius: BorderRadius.circular(50)),
      child: FlatButton(
          onPressed: () => Navigator.pushNamed(context, 'Login'),
          child: const Text(
            'OK',
            style: TextStyle(color: Colors.white, fontSize: 18),
          )),
    );
  }
}
