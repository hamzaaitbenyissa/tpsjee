import 'package:digitalbank/models/Customer.dart';
import 'package:flutter/material.dart';
import 'package:flutter_profile_picture/flutter_profile_picture.dart';

class CustomerDeatils extends StatelessWidget {
  final Customer customer;
  CustomerDeatils(this.customer);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text(customer.firstName + customer.lastName),
          centerTitle: true
        ),
        body: SafeArea(
            child: Column(children: [
          Container(
            child: Container(
              width: double.infinity,
              height: 150,
              child: Container(
                alignment: Alignment(0.0, 2.5),
                child: ProfilePicture(
                  name: customer.firstName,
                  radius: 31,
                  fontsize: 21,
                ),
              ),
            ),
          ),
          SizedBox(
            height: 90,
          ),
          Text(
            customer.firstName + " " + customer.lastName,
            style: TextStyle(
                fontSize: 25.0,
                color: Colors.blueGrey,
                letterSpacing: 2.0,
                fontWeight: FontWeight.w400),
          ),
          SizedBox(
            height: 10,
          ),
          SizedBox(
            height: 10,
          ),
          Text(
            customer.email,
            style: TextStyle(
                fontSize: 18.0,
                color: Colors.black45,
                letterSpacing: 2.0,
                fontWeight: FontWeight.w300),
          ),
          Card(
              margin: EdgeInsets.symmetric(horizontal: 20.0, vertical: 8.0),
              elevation: 2.0,
              child: Padding(
                  padding: EdgeInsets.symmetric(vertical: 12, horizontal: 30),
                  child: Text(
                    "Accounts",
                    style: TextStyle(
                        letterSpacing: 2.0, fontWeight: FontWeight.w300),
                  ))),
        ])));
  }
}
