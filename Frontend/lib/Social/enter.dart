import 'dart:io';
import 'package:flutter/material.dart';
import 'package:frontend/Model/UserModel.dart';
import 'package:image_picker/image_picker.dart';
import 'package:frontend/Controllers/UserController.dart';

import 'Social_Home.dart';

class Enter extends StatefulWidget {
  UserModel userModel;

  Enter({Key? key, required this.userModel}) : super(key: key);

  @override
  _EnterState createState() => _EnterState();
}

class _EnterState extends State<Enter> {
  UserController userController = UserController();
  final TextEditingController firstNameController = TextEditingController();
  final TextEditingController lastNameController = TextEditingController();
  final TextEditingController mobileController = TextEditingController();
  final TextEditingController genderController = TextEditingController();
  final TextEditingController passwordController = TextEditingController();
  final TextEditingController emailController = TextEditingController();
  final TextEditingController studyLevelController = TextEditingController();
  final TextEditingController locationController = TextEditingController();
  final TextEditingController ipController = TextEditingController();
  File? _image;
  ImagePicker picker = ImagePicker();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: ListView(
        padding: const EdgeInsets.only(
            top: 62, left: 12.0, right: 12.0, bottom: 12.0),
        children: [
          Center(
            child: Padding(
              padding: const EdgeInsets.all(8.0),
              child: Stack(
                children: [
                  Container(
                    decoration: BoxDecoration(
                      color: Colors.white70,
                      borderRadius: const BorderRadius.only(
                        topLeft: Radius.circular(15),
                        topRight: Radius.circular(15),
                        bottomLeft: Radius.circular(15),
                        bottomRight: Radius.circular(15),
                      ),
                      boxShadow: [
                        BoxShadow(
                          color: Colors.purple[100]!,
                          spreadRadius: 4,
                          blurRadius: 0,
                          offset: const Offset(0, 0),
                        ),
                      ],
                    ),
                    width: 250,
                    height: 190,
                    child: _image == null
                        ? const Icon(
                            Icons.description,
                            size: 80,
                            color: Colors.purple,
                          )
                        : Image(
                            image: FileImage(_image!),
                            fit: BoxFit.fill,
                          ),
                  ),
                  Positioned(
                    left: 200,
                    top: 140,
                    child: IconButton(
                      icon: const Icon(
                        Icons.edit,
                        color: Colors.purple,
                      ),
                      onPressed: () async {
                        PickedFile? pickedFile = await picker.getImage(
                            source: ImageSource.gallery, imageQuality: 50);

                        File image = File(pickedFile!.path);

                        setState(
                          () {
                            _image = image;
                          },
                        );
                      },
                    ),
                  ),
                ],
              ),
            ),
          ),
          SizedBox(
            height: 50,
            child: TextField(
              controller: firstNameController,
              decoration: const InputDecoration(
                labelText: 'getFirstName',
                hintText: 'getFirstName',
                icon: Icon(Icons.person),
              ),
            ),
          ),
          SizedBox(
            height: 50,
            child: TextField(
              controller: lastNameController,
              decoration: const InputDecoration(
                labelText: 'getLastName',
                hintText: 'getLastName',
                icon: Icon(Icons.email),
              ),
            ),
          ),
          SizedBox(
            height: 50,
            child: TextField(
              controller: mobileController,
              decoration: const InputDecoration(
                labelText: 'getMobile',
                hintText: 'getMobile',
                icon: Icon(Icons.place),
              ),
            ),
          ),
          SizedBox(
            height: 50,
            child: TextField(
              controller: genderController,
              decoration: const InputDecoration(
                labelText: 'getGender',
                hintText: 'getGender',
                icon: Icon(Icons.place),
              ),
            ),
          ),
          SizedBox(
            height: 50,
            child: TextField(
              controller: passwordController,
              decoration: const InputDecoration(
                labelText: 'getPassword',
                hintText: 'getPassword',
                icon: Icon(Icons.place),
              ),
            ),
          ),
          SizedBox(
            height: 50,
            child: TextField(
              controller: emailController,
              decoration: const InputDecoration(
                labelText: 'getEmail',
                hintText: 'getEmail',
                icon: Icon(Icons.place),
              ),
            ),
          ),
          SizedBox(
            height: 50,
            child: TextField(
              controller: studyLevelController,
              decoration: const InputDecoration(
                labelText: 'getStudyLevel',
                hintText: 'getStudyLevel',
                icon: Icon(Icons.place),
              ),
            ),
          ),
          SizedBox(
            height: 50,
            child: TextField(
              controller: locationController,
              decoration: const InputDecoration(
                labelText: 'getLocation',
                hintText: 'getLocation',
                icon: Icon(Icons.place),
              ),
            ),
          ),
          SizedBox(
            height: 50,
            child: TextField(
              controller: ipController,
              decoration: const InputDecoration(
                labelText: 'getIp',
                hintText: 'getIp',
                icon: Icon(Icons.place),
              ),
            ),
          ),
          const Padding(
            padding: EdgeInsets.only(top: 44.0),
          ),
          SizedBox(
            height: 50,
            child: RaisedButton(
              onPressed: () {
                widget.userModel = UserModel(
                    firstName: firstNameController.text,
                    lastName: lastNameController.text,
                    mobile: mobileController.text,
                    gender: genderController.text,
                    password: passwordController.text,
                    email: emailController.text,
                    studyLevel: studyLevelController.text,
                    location: locationController.text,
                    ip: ipController.text,
                    imagePath: _image == null ? null : _image!.path);
                userController.addUser(widget.userModel);
              },
              color: Colors.green,
              child: const Text(
                'Add',
                style: TextStyle(
                  color: Colors.pink,
                  backgroundColor: Colors.grey,
                ),
              ),
            ),
          ),
          IconButton(
            icon: const Icon(Icons.backpack),
            iconSize: 50,
            onPressed: () async {
              Navigator.push(
                context,
                MaterialPageRoute(
                  builder: (context) => SocialHome(),
                ),
              );
            },
          ),
        ],
      ),
    );
  }
}
