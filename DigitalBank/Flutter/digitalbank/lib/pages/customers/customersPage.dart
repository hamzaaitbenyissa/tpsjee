import 'package:digitalbank/controllers/auth_controller.dart';
import 'package:digitalbank/controllers/customer_controller.dart';
import 'package:digitalbank/pages/customers/CustomerDeatils.dart';
import 'package:flutter/material.dart';
import 'package:flutter_profile_picture/flutter_profile_picture.dart';

class CustomersPage extends StatelessWidget {
  CustomersPage({Key? key}) : super(key: key);
  CustomerController customerController = CustomerController();

  @override
  Widget build(BuildContext context) {
    AuthController authController = AuthController();
    return Scaffold(
        appBar: AppBar(title: Text('Customers'), centerTitle: true),
        body: Container(
          child: FutureBuilder(
            future: customerController.getCustomers(),
            builder: (BuildContext context, AsyncSnapshot snapshot) {
              if (snapshot.data == null) {
                return Container(child: Center(child: Text("Loading...")));
              } else {
                return ListView.builder(
                  itemCount: snapshot.data.length,
                  itemBuilder: (BuildContext context, int index) {
                    return Card(
                        child: ListTile(
                      leading: ProfilePicture(
                        name: snapshot.data[index].firstName,
                        radius: 31,
                        fontsize: 21,
                        random: false,
                      ),
                      title: Text(snapshot.data[index].firstName +
                          " " +
                          snapshot.data[index].lastName),
                      subtitle: Text(snapshot.data[index].email),
                      onTap: () {
                        Navigator.push(
                            context,
                            new MaterialPageRoute(
                                builder: (context) =>
                                    CustomerDeatils(snapshot.data[index])));
                      },
                    ));
                  },
                );
              }
            },
          ),
        ));
  }
}
