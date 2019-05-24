import 'package:flutter/material.dart';

class AddApproveListPage extends StatefulWidget {
  @override
  _AddApproveListPageState createState() => _AddApproveListPageState();
}

class _AddApproveListPageState extends State<AddApproveListPage> {
  int _currentIdx = 0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("人员关注"),
      ),
      body: Container(
        child: ListView.builder(
          itemBuilder: (c, i) => _buildItem(c),
          itemCount: 10,
        ),
      ),
      bottomNavigationBar: BottomNavigationBar(
        currentIndex: _currentIdx,
        items: <BottomNavigationBarItem>[
          BottomNavigationBarItem(
            icon: Icon(
              Icons.check,
            ),
            title: Text("审批通过"),
          ),
          BottomNavigationBarItem(
            icon: Icon(
              Icons.clear,
            ),
            title: Text("审批未通过"),
          ),
        ],
        onTap: (idx) {
          setState(() {
            _currentIdx = idx;
          });
        },
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          Navigator.of(context).pushNamed("applicationApprove");
        },
        child: Icon(
          Icons.add,
          color: Colors.white,
        ),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerDocked,
    );
  }

  Widget _buildItem(BuildContext context) {
    return Container(
      height: 120,
      margin: EdgeInsets.only(
        top: 10,
        left: 20,
        right: 20,
        bottom: 10,
      ),
      decoration: BoxDecoration(
          color: Color(0xffffffff),
          borderRadius: BorderRadius.all(Radius.circular(8.0)),
          boxShadow: <BoxShadow>[
            BoxShadow(
              color: Colors.grey,
              spreadRadius: 0,
              blurRadius: 0,
            )
          ]),
      width: double.infinity,
      child: Row( crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          SizedBox(
            width: 10,
          ),
          Flexible(
            fit: FlexFit.tight,
            flex: 1,
            child: Container(
              width: double.infinity,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: <Widget>[
                  Text("name"),
                  Text("name"),
                  Text("name"),
                ],
              ),
            ),
          ),
          Container(
            padding: EdgeInsets.all(4.0),
            decoration: BoxDecoration(
                color: Colors.grey,
                borderRadius: BorderRadius.only(topRight: Radius.circular(8))),
            child: Text(
              "待审批",
              style: TextStyle(color: Colors.white),
            ),
          ),
        ],
      ),
    );
  }
}
