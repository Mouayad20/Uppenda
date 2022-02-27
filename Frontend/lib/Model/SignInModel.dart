import 'dart:core';

class SignInModel {
  String email;
  String password;

  SignInModel({this.email, this.password});

  SignInModel.fromJson(Map<String, dynamic> json) {
    this.password = json['password'];
    this.email = json['email'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['email'] = this.email;
    data['password'] = this.password;
    return data;
  }

  get getPassword => this.password;

  get getEmail => this.email;

  set setEmail(email) => this.email = email;

  set setPassword(password) => this.password = password;
}
