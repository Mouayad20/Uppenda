import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter_datetime_picker/flutter_datetime_picker.dart';

import 'package:image_picker/image_picker.dart';
import 'package:frontend/Model/UserModel.dart';
import 'package:frontend/Pages/Login.dart';
import 'package:frontend/Social/Social_Home.dart';
import 'package:frontend/Controllers/UserController.dart';
import 'package:frontend/main.dart';
import '../Global/Global.dart';
import '../Widgets/Header.dart';
import '../Widgets/Logo.dart';
import '../Widgets/TextFieldCustom.dart';

class SignUp extends StatefulWidget {
  const SignUp({Key? key}) : super(key: key);

  @override
  _SignUpState createState() => _SignUpState();
}

class _SignUpState extends State<SignUp> {
  bool male = false;
  bool female = false;
  UserController userController = UserController();
  UserModel? _userModel;
  TextEditingController firstNameController = TextEditingController();
  TextEditingController lastNameController = TextEditingController();
  TextEditingController emailController = TextEditingController();
  TextEditingController mobileController = TextEditingController();
  TextEditingController passwordController = TextEditingController();
  TextEditingController configPasswordController = TextEditingController();
  TextEditingController locationController = TextEditingController();
  TextEditingController studyLevelController = TextEditingController();
  File? _image;

  DateTime? _dateTime;

  String userIp = "";
  final picker = ImagePicker();

  @override
  void initState() {
    super.initState();
    NetworkInterface.list().then((value) {
      setState(() {
        userIp = "/" + value.first.addresses.first.address;
      });
    });
  }

  Future getImageFromCamera() async {
    final pickedFile = await picker.getImage(
        source: ImageSource.camera, maxHeight: 480, maxWidth: 640);

    setState(() {
      if (pickedFile != null) {
        _image = File(pickedFile.path);
      } else {
        print('No image selected.');
      }
      print(' ////////////////////' + _image!.path);
    });
  }

  Future getImageFromGallery() async {
    final pickedFile = await picker.getImage(
        source: ImageSource.gallery, maxHeight: 480, maxWidth: 640);

    setState(() {
      if (pickedFile != null) {
        _image = File(pickedFile.path);
      } else {
        print('No image selected.');
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: ListView(
      padding: const EdgeInsets.only(top: 0),
      physics: const BouncingScrollPhysics(),
      children: [
        Stack(
          children: [HeaderSignUp(), LogoHeader()],
        ),
        Title(),
        Padding(
          padding: const EdgeInsets.symmetric(horizontal: 20.0),
          child: Column(
            children: [
              Container(
                child: CircleAvatar(
                  backgroundImage: const AssetImage("images/profile.png"),
                  // _image != null
                  //?  AssetImage("images/profile.png")
                  //: FileImage(_image!),
                  backgroundColor: Colors.purple[100],
                  // minRadius: 50,
                  maxRadius: 80,
                ),
                decoration: BoxDecoration(
                  shape: BoxShape.circle,
                  border: Border.all(
                    color: Colors.purple[600]!,
                    width: 4,
                  ),
                ),
              ),
              const Divider(
                height: 20,
                color: Colors.purple,
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceAround,
                children: [
                  FlatButton(
                    onPressed: getImageFromGallery,
                    child: const Icon(Icons.wallpaper,
                        size: 35, color: Colors.grey),
                  ),
                  FlatButton(
                    onPressed: getImageFromCamera,
                    child: const Icon(Icons.camera_alt,
                        size: 35, color: Colors.grey),
                  )
                ],
              ),
              const Divider(
                height: 20,
                color: Colors.purple,
              ),
              TextFieldCustom(
                icon: Icons.person,
                type: TextInputType.text,
                text: 'Fist name',
                controller: firstNameController,
              ),
              const SizedBox(height: 20),
              TextFieldCustom(
                icon: Icons.person,
                type: TextInputType.text,
                text: 'Last name',
                controller: lastNameController,
              ),
              const SizedBox(height: 20),
              TextFieldCustom(
                icon: Icons.mail_outline,
                type: TextInputType.emailAddress,
                text: 'Email',
                controller: emailController,
              ),
              const SizedBox(height: 20),
              TextFieldCustom(
                icon: Icons.phone,
                type: TextInputType.text,
                text: 'Mobile',
                controller: mobileController,
              ),
              const SizedBox(height: 20),
              TextFieldCustom(
                icon: Icons.location_on,
                type: TextInputType.text,
                pass: false,
                text: 'Location',
                controller: locationController,
              ),
              const SizedBox(height: 20),
              TextFieldCustom(
                icon: Icons.school,
                type: TextInputType.text,
                pass: false,
                text: 'Study level',
                controller: studyLevelController,
              ),
              const SizedBox(height: 20),
              TextFieldCustom(
                icon: Icons.visibility_off,
                type: TextInputType.text,
                pass: true,
                text: 'Password',
                controller: passwordController,
              ),
              const SizedBox(height: 20),
              TextFieldCustom(
                icon: Icons.visibility_off,
                type: TextInputType.text,
                pass: true,
                text: 'Confirm Password',
                controller: configPasswordController,
              ),
              const SizedBox(height: 20),
            ],
          ),
        ),
        const Divider(
          height: 20,
          color: Colors.purple,
        ),
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: [
            Text(
              (_dateTime == null)
                  ? "no selected date"
                  : "selected date :             " +
                      _dateTime!.year.toString() +
                      "/" +
                      _dateTime!.month.toString() +
                      "/" +
                      _dateTime!.day.toString(),
              style: const TextStyle(
                  fontSize: 18,
                  fontWeight: FontWeight.bold,
                  color: Colors.grey),
            ),
            FlatButton(
                onPressed: () {
                  DatePicker.showDatePicker(context,
                      showTitleActions: true,
                      minTime: DateTime(1990, 1, 1),
                      maxTime: DateTime.now(), onChanged: (date) {
                    // print('change $date');
                  }, onConfirm: (date) {
                    setState(() {
                      // print('confirm $date');
                      _dateTime = date;
                    });
                  }, currentTime: DateTime.now(), locale: LocaleType.en);
                },
                child:
                    const Icon(Icons.date_range, size: 35, color: Colors.grey)),
          ],
        ),
        const Divider(
          height: 20,
          color: Colors.purple,
        ),
        Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: const [
            Text('male',
                style: TextStyle(
                    fontSize: 18,
                    fontWeight: FontWeight.bold,
                    color: Colors.grey)),
            // Checkbox(
            //   value: male,
            // onChanged: (bool value) {
            //   print("Fds");
            // setState(() {
            //   this.male = value;
            //   this.female = !value;
            // });
            // },
            // ),
            SizedBox(
              width: 70,
            ),
            // Checkbox(
            //   value: female,
            //   onChanged: (bool value) {
            //     setState(() {
            //       this.female = value;
            //       this.male = !value;
            //     });
            //   },
            // ),
            Text('female',
                style: TextStyle(
                    fontSize: 18,
                    fontWeight: FontWeight.bold,
                    color: Colors.grey)),
          ],
        ),
        const Divider(
          height: 20,
          color: Colors.purple,
        ),
        Container(
          margin: const EdgeInsets.all(25),
          decoration: BoxDecoration(
              color: Colors.purple, borderRadius: BorderRadius.circular(50)),
          child: FlatButton(
              onPressed: () {
                _userModel = UserModel(
                    firstName: firstNameController.text,
                    lastName: lastNameController.text,
                    email: emailController.text,
                    mobile: mobileController.text,
                    password: passwordController.text,
                    age: _dateTime,
                    createdAt: null,
                    ip: userIp,
                    gender:
                        (male == true && female == false) ? "male" : "female",
                    friends: null,
                    answerModels: null,
                    groups: null,
                    location: locationController.text,
                    messages: null,
                    pages: null,
                    onLine: true,
                    postModels: null,
                    savedPost: null,
                    sharedPost: null,
                    studyLevel: studyLevelController.text,
                    imagePath: _image == null ? null : _image!.path);
                if (firstNameController.text.isNotEmpty &&
                    lastNameController.text.isNotEmpty &&
                    emailController.text.isNotEmpty &&
                    mobileController.text.isNotEmpty &&
                    locationController.text.isNotEmpty &&
                    studyLevelController.text.isNotEmpty)
                // showAlertDialog(context, "complete all fields pleas");
                {
                  if (passwordController.text !=
                      configPasswordController.text) {
                    showAlertDialog(context, "password doesnot match");
                  }
                  if (passwordController.text ==
                      configPasswordController.text) {
                    userController.addUser(_userModel!).then((value) {
                      if (value['userModel'] != null) {
                        // print("  in singup");
                        setState(() {
                          currentUser =
                              UserModel.fromJson(value["userModel"]);
                        });
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) => SocialHome(),
                          ),
                        );
                      }
                      // else
                      //   showAlertDialog(context, "there is no connection");
                    });
                  }
                } else {
                  showAlertDialog(context, "complete all fields pleas");
                }
              },
              child: const Text(
                'SIGN UP',
                style: TextStyle(
                  fontFamily: 'Merienda',
                  color: Colors.white,
                  fontSize: 18,
                ),
              )),
        ),
      ],
    ));
  }
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
                fontSize: 18,
                fontWeight: FontWeight.bold,
                color: Colors.grey,
              ),
            ),
          ),
          const Text(
            '/',
            style: TextStyle(
              fontFamily: 'Merienda',
              fontSize: 25,
              color: Colors.grey,
            ),
          ),
          FlatButton(
            onPressed: () => Navigator.of(context).push(
              MaterialPageRoute(
                builder: (context) => const SignUp(),
              ),
            ),
            child: const Text(
              'SIGN UP',
              style: TextStyle(
                fontFamily: 'Merienda',
                fontSize: 25,
                fontWeight: FontWeight.bold,
              ),
            ),
          ),
        ],
      ),
    );
  }
}

void showAlertDialog(BuildContext context, String message) {
  // Widget cancelButton = TextButton(
  //   child: Text(
  //     "OK",
  //     style: TextStyle(color: Colors.purple),
  //   ),
  //   onPressed: () {
  //     // Navigator.pop(context);
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
