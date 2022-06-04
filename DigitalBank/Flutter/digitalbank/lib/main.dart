import 'dart:convert';
import 'package:digitalbank/pages/customers/Customer.dart';
import 'package:digitalbank/pages/customers/CustomerDeatils.dart';
import 'package:http/http.dart' as http;
import 'package:flutter_profile_picture/flutter_profile_picture.dart';

import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Customers',
      theme: ThemeData(
        primarySwatch: Colors.pink,
      ),
      home: const MyHomePage(title: 'Customers'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);
  final String title;
  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  Future<List<Customer>> _getCustomers() async {
    var data = await http.get(Uri.http('localhost:8082', 'customers'));

    print(data.body);

    List<Customer> Customers = [];
    for (var u in jsonDecode(data.body)) {
      print(u["id"]);
      Customer customer = Customer(
          u["id"].toString(), u["firstName"], u["lastName"], u["email"]);
      Customers.add(customer);
    }

    print(Customers);
    return Customers;
  }

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(
        centerTitle: true,
        title: new Text(widget.title),
      ),
      body: Container(
        child: FutureBuilder(
          future: _getCustomers(),
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
      ),
    );
  }
}
