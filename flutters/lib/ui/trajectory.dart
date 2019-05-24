import 'package:flutter/material.dart';

class TrajectoryPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("轨迹分析"),
      ),
      body: FutureBuilder(
        builder: (c, asyncs) {
          switch (asyncs.connectionState) {
            case ConnectionState.none:
              break;
            case ConnectionState.waiting:
            case ConnectionState.active:
              return Center(
                child: CircularProgressIndicator(),
              );
              break;
            case ConnectionState.done:
              return ListView.builder(
                itemBuilder: (c, i) => _buildItem(c, i),
                itemCount: 20,
              );
              break;
          }
        },
        future: Future.delayed(Duration(seconds: 2)),
      ),
    );
  }

  Widget _buildItem(BuildContext context, int idx) {
    return Container(
      height: 80,
      padding: EdgeInsets.symmetric(horizontal: 10),
      margin: EdgeInsets.only(left: 20, right: 20, top: 10, bottom: 10),
      decoration: BoxDecoration(
          color: Colors.white,
          boxShadow: <BoxShadow>[
            BoxShadow(
              color: Colors.grey,
            )
          ],
          borderRadius: BorderRadius.all(Radius.circular(8))),
      child: Column(crossAxisAlignment: CrossAxisAlignment.start,
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: <Widget>[
          Text("aaa"),
          Text("aaa"),
          Text("aaa"),
        ],
      ),
    );
  }
}
