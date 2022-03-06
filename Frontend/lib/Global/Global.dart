// ignore_for_file: file_names, avoid_print

import 'dart:io';

import 'package:flutter/material.dart';
import '../Model/UserModel.dart';

const serverIp = "192.168.1.1:8080";
const mainURL = "http://" + serverIp;
String token = "null";

UserModel? currentUser;

const purple = Colors.purple;
const dancingScript = 'DancingScript';

heightScreen(BuildContext context) {
  return MediaQuery.of(context).size.height;
}

widthScreen(BuildContext context) {
  return MediaQuery.of(context).size.width;
}

getIpAddress() {
  NetworkInterface.list().then((ipValue) {
    currentUser?.ip = ipValue.first.addresses.first.address;
  });
}
