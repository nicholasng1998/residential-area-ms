import 'package:flutter/material.dart';
import 'package:flutter_application/utils/user_state.dart';
import 'package:provider/provider.dart';
import 'dart:async';
import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:flutter_application/apis.dart';
import 'package:url_launcher/url_launcher.dart';

class StatementResponseModel {
  final int id;
  final int year;
  final int month;
  final String status;
  final double amount;

  StatementResponseModel({
    required this.id,
    required this.year,
    required this.month,
    required this.status,
    required this.amount,
  });

  factory StatementResponseModel.fromJson(Map<String, dynamic> json) {
    return StatementResponseModel(
      id: json['id'],
      year: json['year'],
      month: json['month'],
      status: json['status'],
      amount: json['amount'],
    );
  }
}

class PaymentScreen extends StatefulWidget {
  final String username;
  PaymentScreen({required this.username});

  @override
  _PaymentScreen createState() => _PaymentScreen();
}

class _PaymentScreen extends State<PaymentScreen> {
  @override
  void initState() {
    super.initState();
    fetchPendingStatementFromAPI(widget.username);
  }

  List<StatementResponseModel> tableData = [];

  Future<void> fetchPendingStatementFromAPI(String username) async {
    final queryParameters = {'username': username};
    final uri =
        Uri.parse(STATEMENT_READ_API).replace(queryParameters: queryParameters);
    final response = await http.get(uri);

    if (response.statusCode == 200) {
      final jsonData = json.decode(response.body);
      final List<dynamic> contentList = jsonData['content'];

      final List<StatementResponseModel> statementResponseModels =
          contentList.map((item) {
        return StatementResponseModel.fromJson(item);
      }).toList();
      print(statementResponseModels);
      setState(() {
        tableData = statementResponseModels;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: ListView.builder(
        itemCount: tableData.length,
        itemBuilder: (context, index) {
          final paymentItem = tableData[index];
          return Card(
            child: ListTile(
              title: Text("Year: " +
                  paymentItem.year.toString() +
                  " Month: " +
                  paymentItem.month.toString()),
              subtitle: Text("RM" + paymentItem.amount.toString()),
              trailing: ElevatedButton(
                onPressed: () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (context) =>
                          PaymentPage(widget.username, paymentItem),
                    ),
                  );
                },
                child: Text('Pay Now'),
              ),
            ),
          );
        },
      ),
    );
  }
}

class PaymentPage extends StatelessWidget {
  final String username;
  final StatementResponseModel paymentItem;

  Future<bool> create() async {
    var url = Uri.parse(PAYMENT_CREATE_API);

    Map<String, dynamic> data = {
      'statementId': paymentItem.id,
      'username': username,
      'amount': paymentItem.amount,
    };

    String jsonBody = json.encode(data);

    Map<String, String> headers = {
      'Content-Type': 'application/json',
    };

    http.Response response = await http.post(
      url,
      headers: headers,
      body: jsonBody,
    );

    if (response.statusCode == 200) {
      return true;
    } else {
      return false;
    }
  }

  void _payNow(BuildContext context) async {
    bool created = await create();
    if (created) {
      showDialog(
          context: context,
          builder: (context) => AlertDialog(
                title: Text('Create Successfully'),
                content: Text('Please Check.'),
                actions: [
                  TextButton(
                    onPressed: () => {Navigator.pop(context)},
                    child: Text('OK'),
                  ),
                ],
              ));
    } else {
      showDialog(
          context: context,
          builder: (context) => AlertDialog(
                title: Text('Create Failed'),
                content: Text('Please Try Again.'),
                actions: [
                  TextButton(
                    onPressed: () => Navigator.pop(context),
                    child: Text('OK'),
                  ),
                ],
              ));
    }
  }

  PaymentPage(this.username, this.paymentItem);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Payment Details'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(
                'Payment For Year: ${paymentItem.year} Month: ${paymentItem.month}'),
            Text('Amount: ${paymentItem.amount}'),
            SizedBox(height: 16.0),
            ElevatedButton(
              onPressed: () {
                _payNow(context);
              },
              child: Text('Pay Now'),
            ),
          ],
        ),
      ),
    );
  }
}
