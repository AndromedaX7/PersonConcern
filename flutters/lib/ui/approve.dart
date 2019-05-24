import 'package:flutter/material.dart';

class PendingApproveListPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
      length: 2,
      child: Scaffold(
        appBar: AppBar(
          elevation: 12,
          title: Text("待审批"),
          bottom: TabBar(
            indicatorSize: TabBarIndicatorSize.label,
            tabs: <Tab>[
              Tab(
                text: "待审批",
              ),
              Tab(
                text: "已审批",
              ),
            ],
          ),
        ),
        body: TabBarView(children: <Widget>[
          TabItem(),
          TabItem(),
        ]),
      ),
    );
  }
}

class TabItem extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: ListView.builder(
        itemBuilder: (c, idx) => _buildItem(c),
        itemCount: 10,
      ),
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
      child: Row(
        children: <Widget>[
          Container(
            width: 2,
            height: 112,
            color: Colors.blue,
          ),
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
          Flexible(
            fit: FlexFit.tight,
            flex: 1,
            child: Container(
              width: double.infinity,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.end,
                mainAxisAlignment: MainAxisAlignment.start,
                children: <Widget>[
//                  SizedBox(height: 8,),
                  Container(
                    padding: EdgeInsets.all(4.0),
                    decoration: BoxDecoration(
                        color: Colors.grey,
                        borderRadius:
                            BorderRadius.only(topRight: Radius.circular(8))),
                    child: Text(
                      "待审批",
                      style: TextStyle(color: Colors.white),
                    ),
                  ),

                  Icon(Icons.navigate_next),
                  SizedBox(
                    height: 32,
                  ),
                  Container(
                    margin: EdgeInsets.only(right: 8),
                    child: Text("2019-05-01 22:10:00"),
                  )
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}
