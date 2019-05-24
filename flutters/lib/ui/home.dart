import 'package:flutter/material.dart';

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        height: double.infinity,
        child: Stack(
          children: <Widget>[
            Image.asset(
              "images/banner.png",
              fit: BoxFit.fitWidth,
            ),
            Positioned(
              left: 40,
              right: 40,
              height: 150,
              top: 130,
              child: Header(),
            ),
            Positioned(
              child: Image.asset(
                "images/logo.png",
                fit: BoxFit.cover,
              ),
              height: 120,
              left: 140,
              right: 140,
              top: 70,
            ),
            Positioned(
              left: 40,
              right: 40,
              top: 290,
              height: 120,
              child: GestureDetector(
                onTap: () {
                  Navigator.of(context).pushNamed("pendingApprove");
                },
                child: Container(
                  child: Stack(
                    alignment: Alignment(0, 0),
                    children: <Widget>[
                      Positioned(
                        child: Column(
                          children: <Widget>[
                            Text(
                              "1",
                              style:
                                  TextStyle(fontSize: 20, color: Colors.white),
                            ),
                            Text("待审批",
                                style: TextStyle(
                                    color: Colors.white, fontSize: 18)),
                          ],
                          crossAxisAlignment: CrossAxisAlignment.end,
                        ),
                        right: 20,
                      ),
                      Positioned(
                        child: Image.asset("images/icon_shenpi.png"),
                        left: 0,
                        width: 100,
                      ),
                    ],
                  ),
                  color: Color(0xff1c98a4),
                ),
              ),
            ),
            Positioned(
              left: 40,
              top: 420,
              child: Block(
                route: "applicationApprove",
                width: 152,
                height: 80,
                assets: "images/icon_shenqing.png",
                title: "关注申请",
                color: Color(0xff920882),
              ),
            ),
            Positioned(
                right: 40,
                top: 420,
                child: Block( route: "trajectory",
                  width: 152,
                  height: 80,
                  title: "轨迹分析",
                  assets: "images/icon_guiji.png",
                  color: Color(0xfffeb211),
                )),
            Positioned(
              left: 40,
              top: 510,
              child: Block(
                width: 152, route: "addApproveList",
                height: 90,
                color: Color(0xff63ec94),
                title: "关注列表",
                assets: "images/icon_list.png",
              ),
            ),
            Positioned(
              right: 40,
              top: 510,
              child: Block( route: "push",
                width: 152,
                height: 90,
                title: "线索推送",
                color: Color(0xff0077e8),
                assets: "images/icon_tuisong.png",
              ),
            ),
          ],
        ),
      ),
    );
  }
}

class Header extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        boxShadow: <BoxShadow>[
          BoxShadow(color: Colors.grey, blurRadius: 10, spreadRadius: 2)
        ],
        image: DecorationImage(
            image: AssetImage("images/user_bg.png"), fit: BoxFit.fill),
      ),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.end,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: <Widget>[
          Text("222"),
          SizedBox(
            height: 8,
          ),
          Text("222  444"),
          SizedBox(
            height: 20,
          )
        ],
      ),
    );
  }
}

class Block extends StatelessWidget {
  double width;
  double height;
  String title;
  String assets;
  Color color;
  String route = "";

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        Navigator.of(context).pushNamed(route);
      },
      child: Container(
        width: width,
        height: height,
        color: color,
        child: Stack(
          alignment: Alignment(0, 0),
          children: <Widget>[
            Positioned(
              child: Text(
                title,
                style: TextStyle(color: Colors.white, fontSize: 16),
              ),
              left: 5,
            ),
            Positioned(
              width: 40,
              right: 5,
              bottom: 5,
              child: Image.asset(
                assets,
                fit: BoxFit.contain,
              ),
            ),
          ],
        ),
      ),
    );
  }

  Block(
      {this.width,
      this.height,
      this.title,
      this.assets,
      this.color,
      this.route});
}
