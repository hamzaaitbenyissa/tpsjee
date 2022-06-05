import 'package:digitalbank/controllers/auth_controller.dart';
import 'package:digitalbank/pages/customers/customersPage.dart';
import 'package:flutter/material.dart';

class LoginPage extends StatelessWidget {
  const LoginPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    AuthController authController = AuthController();
    return Scaffold(
      appBar: AppBar(
        title: Text('Login'),
        centerTitle: true,
      ),
      body: Column(
        children: [
          SizedBox(
            height: 20,
          ),
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 20),
            child: SizedBox(
              height: 50,
              child: TextFormField(
                controller: authController.usernameController,
                decoration: InputDecoration(
                  hintText: 'Username',
                  enabledBorder: OutlineInputBorder(),
                  focusedBorder: OutlineInputBorder(),
                ),
              ),
            ),
          ),
          SizedBox(
            height: 20,
          ),
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 20),
            child: SizedBox(
              height: 50,
              child: TextFormField(
                controller: authController.passwordController,
                decoration: InputDecoration(
                  hintText: 'Password',
                  enabledBorder: OutlineInputBorder(),
                  focusedBorder: OutlineInputBorder(),
                ),
              ),
            ),
          ),
          SizedBox(
            height: 35,
          ),
          ElevatedButton(
              onPressed: () {
                authController.loginUser();
                Navigator.push(
                    context,
                    new MaterialPageRoute(
                        builder: (context) => CustomersPage()));
              },
              child: Text(
                'Login',
              )),
        ],
      ),
    );
  }
}
