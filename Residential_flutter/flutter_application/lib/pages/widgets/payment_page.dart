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
                  // Redirect to the payment page
                  Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (context) => PaymentPage(paymentItem),
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
  final StatementResponseModel paymentItem;

  PaymentPage(this.paymentItem);

  void _openWebsite() async {
    const url = 'http://localhost:4200/payment';

    try {
      if (await canLaunch(url)) {
        await launch(url);
      } else {
        throw 'Could not launch $url';
      }
    } catch (e) {
      print('Error launching URL: $e');
    }
  }

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
            Text('Payment Item: ${paymentItem.year}'),
            Text('Amount: ${paymentItem.amount}'),
            ElevatedButton(
              onPressed: () {
                _openWebsite();
              },
              child: Text('Pay Now'),
            ),
          ],
        ),
      ),
    );
  }
}
