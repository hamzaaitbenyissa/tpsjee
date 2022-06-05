import 'dart:convert';

import 'package:digitalbank/configuration/config.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';

class AuthController {
  TextEditingController usernameController = TextEditingController();
  TextEditingController passwordController = TextEditingController();
  Config config =new Config();
  Future loginUser() async {
    
    String url = config.host +'/login';

    var response = await http.post(Uri.parse(url),
        headers: <String, String>{
          'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: 'username=' +
            usernameController.text.toString() +
            '&password=' +
            passwordController.text.toString());
    if (response.statusCode == 200) {
      var loginArr = json.decode(response.body);
      // save this token in shared prefrences and make user logged in and navigate
      print(loginArr['accessToken']);
      SharedPreferences prefs = await SharedPreferences.getInstance();
      prefs.setString('token', loginArr['accessToken']);
    } else {}
  }
}
