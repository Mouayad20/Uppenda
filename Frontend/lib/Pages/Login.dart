import 'package:flutter/material.dart';
import 'package:frontend/Model/SignInModel.dart';
import 'package:frontend/Model/UserModel.dart';
import 'package:frontend/Pages/Signup.dart';
import 'package:frontend/Social/Social_Home.dart';
import 'package:frontend/Controllers/UserController.dart';
import 'package:frontend/main.dart';

import '../Widgets/Header.dart';
import '../Widgets/Logo.dart';
import '../Widgets/TextFieldCustom.dart';

class LogInPage extends StatelessWidget {
  UserController userController = UserController();
  TextEditingController emailController = TextEditingController();
  TextEditingController passwordController = TextEditingController();

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
        Title(),
        const SizedBox(height: 20),
        // EmailAndPassword()
        Padding(
          padding: const EdgeInsets.symmetric(horizontal: 20.0),
          child: Column(
            children: [
              TextFieldCustom(
                icon: Icons.mail_outline,
                type: TextInputType.emailAddress,
                text: 'Email',
                controller: emailController,
              ),
              const SizedBox(height: 20),
              TextFieldCustom(
                icon: Icons.visibility_off,
                type: TextInputType.text,
                pass: true,
                text: 'Password',
                controller: passwordController,
              ),
            ],
          ),
        ),

        ForgetPassword(),

        const SizedBox(height: 10),

        // ButtonSignIn()
        Container(
          margin: const EdgeInsets.all(25),
          decoration: BoxDecoration(
              color: Colors.purple, borderRadius: BorderRadius.circular(50)),
          child: FlatButton(
              onPressed: () {
                if (emailController.text.isEmpty ||
                    passwordController.text.isEmpty) {
                  showAlertDialog(context, "complete all field please");
                }
                SignInModel authRequest = SignInModel(
                    email: emailController.text.trim(),
                    password: passwordController.text.trim());
                if (emailController.text.isNotEmpty &&
                    passwordController.text.isNotEmpty) {
                  userController.signIn(authRequest).then((value) {
                    print('>>>>>>>>>>>>   ' + value['userModel'].toString());
                    if (value['userModel'] == null) {
                      showAlertDialog(context, "invalid email or password");
                    } else {
                      MyApp.currentUser =
                          UserModel.fromJson(value['userModel']);
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (context) => SocialHome(),
                        ),
                      );
                    }
                  });
                }
                // Navigator.pushNamed(context  , '');
                // if(== null)
                //   showAlertDialog(context);
              },
              child: const Text(
                'SIGN IN',
                style: TextStyle(
                    fontFamily: 'Merienda', color: Colors.white, fontSize: 18),
              )),
        )
      ],
    ));
  }
}

void showAlertDialog(BuildContext context, String message) {
  // Widget cancelButton = TextButton(
  //   child: Text(
  //     "OK",
  //     style: TextStyle(color: Colors.purple),
  //   ),
  //   onPressed: () {
  //     Navigator.pop(context);
  //   },
  // );
  AlertDialog alert = AlertDialog(
    content: Text(
      message,
      style: const TextStyle(
        color: Colors.purple,
      ),
    ),
    // actions: [
    //   cancelButton,
    // ],
  );
  showDialog(
    context: context,
    builder: (BuildContext context) {
      return alert;
    },
  );
}

class Title extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(15.0),
      child: Row(
        children: [
          FlatButton(
            onPressed: () => Navigator.of(context).push(
              MaterialPageRoute(
                builder: (context) => LogInPage(),
              ),
            ),
            child: const Text(
              'SIGN IN',
              style: TextStyle(
                fontFamily: 'Merienda',
                fontSize: 25,
                fontWeight: FontWeight.bold,
              ),
            ),
          ),
          const Text(
            '/',
            style: TextStyle(
              fontSize: 25,
              fontFamily: 'Merienda',
              color: Colors.grey,
            ),
          ),
          FlatButton(
            onPressed: () => Navigator.of(context).push(
              MaterialPageRoute(
                builder: (context) => SignUp(),
              ),
            ),
            child: const Text(
              'SIGN UP',
              style: TextStyle(
                fontFamily: 'Merienda',
                fontSize: 18,
                fontWeight: FontWeight.bold,
                color: Colors.grey,
              ),
            ),
          ),
        ],
      ),
    );
  }
}

class ForgetPassword extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.only(right: 25, top: 20),
      alignment: Alignment.centerRight,
      child: FlatButton(
        onPressed: () => Navigator.pushNamed(context, 'ForgetPassword'),
        child: const Text(
          'Forget Password?',
          style: TextStyle(
            fontFamily: 'Merienda',
            fontSize: 18,
          ),
        ),
      ),
    );
  }
}
