import 'dart:convert';

import 'package:digitalbank/config.dart';
import 'package:digitalbank/models/Customer.dart';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';

class CustomerController {

  Config config =new Config();


  Future<List<Customer>> getCustomers() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    print("token test" + prefs.getString("token").toString());
    var data = await http.get(
      Uri.http(
        config.domain,
        'customers',
      ),
      headers: <String, String>{
        'authorization': 'Bearer ' + prefs.getString("token").toString(),
      },
    );

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
}
