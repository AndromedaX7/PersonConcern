import 'package:flutter/material.dart';

class ApplicationApprovePage extends StatefulWidget {
  @override
  _ApplicationApprovePageState createState() => _ApplicationApprovePageState();
}

class _ApplicationApprovePageState extends State<ApplicationApprovePage> {
  var _formKey = GlobalKey<FormState>();
  var _scaffold = GlobalKey<ScaffoldState>();
  var _startDate = "起始时间";
  var _endDate = "结束时间";

  var _name = "";
  var _idCode = "";
  var _things = "";

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      key: _scaffold,
      appBar: AppBar(
        title: Text("人员关注申请"),
      ),
      body: SingleChildScrollView(
        child: Container(
          margin: EdgeInsets.symmetric(horizontal: 20),
          child: Form(
            key: _formKey,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: <Widget>[
                SizedBox(
                  height: 20,
                ),
                Text("关注人员信息"),
                SizedBox(
                  height: 10,
                ),
                TextFormField(
                  autovalidate: true,
                  onSaved: (val) {
                    _name = val;
                  },
                  validator: (val) {
                    if (val == null || val.isEmpty) {
                      return "请输入姓名";
                    }
                  },
                  decoration: InputDecoration(
                      hintText: "请输入姓名",
                      labelText: "姓名",
                      border: OutlineInputBorder()),
                ),
                SizedBox(
                  height: 10,
                ),
                TextFormField(
                  autovalidate: true,
                  onSaved: (val) {
                    _idCode = val;
                  },
                  validator: (val) {
                    if (val == null || val.isEmpty) {
                      return "请输入身份证号";
                    }
                  },
                  decoration: InputDecoration(
                      hintText: "请输入身份证号",
                      labelText: "身份证号",
                      border: OutlineInputBorder()),
                ),
                SizedBox(
                  height: 10,
                ),
                Text("申请项"),
                SizedBox(
                  height: 10,
                ),
                Container(
                  width: double.infinity,
                  padding: EdgeInsets.all(8),
                  color: Colors.white,
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: <Widget>[
                      RichText(
                        text: TextSpan(
                          text: "关注时限",
                          style: TextStyle(color: Colors.black),
                          children: <TextSpan>[
                            TextSpan(
                              text: "*",
                              style: TextStyle(color: Colors.red),
                            ),
                          ],
                        ),
                      ),
                      Row(
                        children: <Widget>[
                          Flexible(
                            flex: 1,
                            fit: FlexFit.tight,
                            child: FlatButton(
                              onPressed: () {
                                showDatePicker(
                                        context: context,
                                        initialDate: DateTime.now(),
                                        firstDate: DateTime(2000),
                                        lastDate: DateTime(2100))
                                    .then((val) {
                                  setState(() {
                                    var current =
                                        val.toLocal().toIso8601String();

                                    _startDate = current.substring(
                                        0, current.indexOf("T"));
                                  });
                                });
                              },
                              child: Text(_startDate),
                            ),
                          ),
                          Text("--"),
                          Flexible(
                            flex: 1,
                            fit: FlexFit.tight,
                            child: FlatButton(
                              onPressed: () {
                                showDatePicker(
                                        context: context,
                                        initialDate: DateTime.now(),
                                        firstDate: DateTime(2000),
                                        lastDate: DateTime(2100))
                                    .then((val) {
                                  setState(() {
                                    var current =
                                        val.toLocal().toIso8601String();

                                    _endDate = current.substring(
                                        0, current.indexOf("T"));
                                  });
                                });
                              },
                              child: Text(_endDate),
                            ),
                          ),
                        ],
                      ),
                      TextFormField(
                        autovalidate: true,
                        decoration: InputDecoration(
                            labelText: "申请事由",
                            hintText: "请输入申请事由",
                            border: OutlineInputBorder()),
                        validator: (val) {
                          if (val == null || val.isEmpty) {
                            return "请输入申请事由";
                          }
                        },
                        onSaved: (val) {
                          _things = val;
                        },
                      )
                    ],
                  ),
                ),
                SizedBox(
                  height:60,
                ),
                Center(
                  child: Container(width: 120,
                    child: RaisedButton(
                      child: Text("提交申请",style: TextStyle(color: Colors.white),),
                      color: Colors.blue,
                      onPressed: () {
                        if (_formKey.currentState.validate()) {
                          _formKey.currentState.save();
                          _scaffold.currentState
                              .showSnackBar(SnackBar(content: Text("已验证")));
                        }
                      },
                    ),
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
