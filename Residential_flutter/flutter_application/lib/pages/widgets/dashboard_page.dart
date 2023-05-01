import 'package:flutter/material.dart';
import 'package:flutter_application/pages/login_page.dart';
import 'package:flutter_application/pages/widgets/add_visitor_page.dart';
import 'package:flutter_application/pages/widgets/emergency_page.dart';
import 'package:flutter_application/pages/widgets/notice_page.dart';
import 'package:flutter_application/pages/widgets/payment_page.dart';
import 'package:flutter_application/utils/user_state.dart';
import 'package:provider/provider.dart';

enum NavBarItem { Notice, Payment, AddVisitor, Emergency }

class DashboardPage extends StatefulWidget {
  @override
  State<DashboardPage> createState() => _DashboardPageState();
}

class _DashboardPageState extends State<DashboardPage> {
  NavBarItem _currentNavBarItem = NavBarItem.Notice;

  void _logout(BuildContext context) {
    Provider.of<UserState>(context, listen: false).logout();
    Navigator.pushReplacement(
      context,
      MaterialPageRoute(builder: (_) => LoginPage()),
    );
  }

  @override
  Widget build(BuildContext context) {
    String? username = Provider.of<UserState>(context).username;

    return Scaffold(
      appBar: AppBar(
        title: Text('Welcome back! $username',
            style: TextStyle(color: Colors.white)),
        actions: [
          IconButton(
            icon: Icon(Icons.logout),
            onPressed: () => _logout(context),
          ),
        ],
      ),
      body: IndexedStack(
        index: _currentNavBarItem.index,
        children: <Widget>[
          NoticeScreen(),
          PaymentScreen(username: '$username'),
          AddVisitorScreen(username: '$username'),
          EmergencyScreen(username: '$username',)
        ],
      ),
      bottomNavigationBar: BottomNavigationBar(
          backgroundColor: Colors.blue,
          selectedItemColor: Colors.blue,
          unselectedItemColor: Colors.grey,
          currentIndex: _currentNavBarItem.index,
          onTap: (int index) {
            setState(() {
              _currentNavBarItem = NavBarItem.values[index];
            });
          },
          items: [
            BottomNavigationBarItem(
              icon: Icon(Icons.dashboard),
              label: 'Announcement Board',
            ),
            BottomNavigationBarItem(
              icon: Icon(Icons.payment),
              label: 'Payment',
            ),
            BottomNavigationBarItem(
              icon: Icon(Icons.emoji_people),
              label: 'Add Visitor',
            ),
            BottomNavigationBarItem(
              icon: Icon(Icons.emergency),
              label: 'Emergency Request',
            ),
          ]),
    );
  }
}
