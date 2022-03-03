import 'dart:core';

class SignInModel {
  String? email;
  String? password;

  SignInModel({this.email, this.password});

  SignInModel.fromJson(Map<String, dynamic> json) {
    password = json['password'];
    email = json['email'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['email'] = email;
    data['password'] = password;
    return data;
  }

  get getPassword => password;

  get getEmail => email;

  set setEmail(email) => this.email = email;

  set setPassword(password) => this.password = password;
}
