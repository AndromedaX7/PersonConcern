//import 'dart:async';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import 'screen_adapter/screen_adapter.dart';
import 'ui/add_approve.dart';
import 'ui/application_approve.dart';
import 'ui/approve.dart';
import 'ui/home.dart';
import 'ui/push.dart';
import 'ui/trajectory.dart';

void main() => InnerWidgetsFlutterBinding.ensureInitialized()
  ..attachRootWidget(new MyApp())
  ..scheduleWarmUpFrame();

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    SystemChrome.setSystemUIOverlayStyle(SystemUiOverlayStyle.light);
    return new MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Flutter Demo',
      theme: new ThemeData(
        primarySwatch: Colors.blue,
      ),
      routes: {
        "/": (c) => HomePage(),
        "pendingApprove": (c) => PendingApproveListPage(),
        "addApproveList": (c) => AddApproveListPage(),
        "trajectory": (c) => TrajectoryPage(),
        "applicationApprove": (c) => ApplicationApprovePage(),
        "push": (c) => PushPage()
      },
    );
  }
}
