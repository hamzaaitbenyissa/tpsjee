import 'package:digitalbank/pages/login/login.dart';
import 'package:flutter/material.dart';

import 'package:digitalbank/controllers/customer_controller.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        primarySwatch: Colors.pink,
      ),
      home:  LoginPage(),
    );
  }
}


