const crypto = requirePlugin("crypto");
var X2JS = require('./x2j/x2js/we-x2js.js');
var x2js = new X2JS();


class http {
    url = "http://localhost:8080/miniapp/";
    //url = "http://172.20.10.5:8080/miniapp/";
    userStatus = null;
    userId = "";
    password = "";
    time = "";
    username = "";
    restMoney="";
    isActive="";
    classId="";
    bankName = "";
    bankCard = "";
    Sign = "";
    Msg = "";
    AccNum = "";
    AccName = "";
    PerCode = "";
    CardID = "";
    CertCode = "";
    CustomerID = "";
    AgentID = "";
    LostDate = "";
    IsDefault = "";
    MonDBCurr = "";
    IsOpen = "";
    RandomNum = "";
    OrderNum = "";
    QRcode = "";

    AccStatus = "";
    BankTransState = "";
    JwCookie = "";
    Nanyue = "";

    setTime() {
        //当前时间化成yyyymmddhhmmss
        var d = new Date();
        var time = d.getFullYear().toString();

        if (d.getMonth() < 9) time = time + "0" + (d.getMonth() + 1).toString();
        else time += (d.getMonth() + 1).toString();

        if (d.getDate() < 10) time = time + "0" + d.getDate().toString();
        else time += d.getDate().toString();

        if (d.getHours() < 10) time = time + "0" + d.getHours().toString();
        else time += d.getHours().toString();

        if (d.getMinutes() < 10) time = time + "0" + d.getMinutes().toString();
        else time += d.getMinutes().toString();

        if (d.getSeconds() < 10) time = time + "0" + d.getSeconds().toString();
        else time += d.getSeconds().toString();

        this.time = time;

    }

    //签名生成
    // setSign(str1, str2) {
    //     //设置签名
    //     var key = "ok15we1@oid8x5afd@";
    //     var str3 = new Array(str1.length);
    //     for (var i = 0; i < str1.length; ++i) {
    //         str3[i] = str1[i];
    //     }
    //     str3.sort();
    //     var sign = "";
    //     for (var i = 0; i < str3.length; ++i) {
    //         var i2 = 0;
    //         while (true) {
    //             if (i2 >= str3.length) break;
    //             else if (str3[i] == str1[i2]) {
    //                 sign += str2[i2];
    //                 sign += "|";
    //                 break;
    //             } else {
    //                 i2++;
    //             }
    //         }
    //     }
    //     sign += key;
    //     var md5 = new crypto["MD5"](sign).toString();
    //     this.Sign = md5;
    // }

    //设置用户状态
    setUserStatus(str){
        let that = this;
        that.userStatus = str;
        wx.setStorage({
          key: 'userStatus',
          data: str,
        })
    }
    //设置用户Id
    setUserId(str) {
        let that = this;
        that.userId = str;
        wx.setStorage({
            key: 'userId',
            data: str,
        })
    }
    //设置用户名
    setUsername(str){
        let that = this;
        that.username = str;
        wx.setStorage({
            key:'username',
            data:str,
        })
    }
    //设置班级信息
    setClassId(str){
        let that = this;
        that.classId = str;
        wx.setStorage({
            key:'classId',
            data:str,
        })
    }

    //密码加密
    setPassword(str) {
        //设置密码
        // const key = "12347890";
        // const keyHex = crypto.Utf8.parse(key);
        // const encrypted = new crypto.DES().encrypt(str, keyHex, {
        //     iv: keyHex,
        //     mode: crypto.Mode.CBC,
        //     padding: crypto.Padding.Pkcs7
        //   });
        // this.Password = encrypted.toString();
        this.password = str;
    }
    ReportLost(password) {
        var that = this;
        // var a = ['Time', "AccNum", "OptType", "Password"]
        // this.setTime();
        // var b = [that.Time, that.AccNum, "1", password]
        // this.setSign(a, b);
        var url = that.url + "virtualCard/reportLoss";
        that.setTime();
        return new Promise(function (resolve, reject) {
            wx.request({
                url: url,
                data: {
                    "time": that.time,
                    "uId": that.userId,
                    "password": password,
                },
                success(res) {
                    resolve(res)
                },
                fail(error) {
                    console.log(error)
                    reject(error)
                }
            })
        })
    }

    // //支付密码加密
    // OtherPassword(str) {
    //     //设置密码
    //     var key = "12347890";
    //     var keyHex = crypto.Utf8.parse(key);
    //     var encrypted = new crypto.DES().encrypt(str, keyHex, {
    //         iv: keyHex,
    //         mode: crypto.Mode.CBC,
    //         padding: crypto.Padding.Pkcs7
    //     });
    //     return encrypted.toString();
    // }

    //登录
    Login() {
        this.setTime();
        // this.setSign(a, b);
        var that = this;
        let msg ={
            // "Sign": that.Sign,
            uId: that.userId,
            password: that.password,
            lastEditTime: that.time
        }
        return new Promise(function (resolve, reject) {
            wx.request({
                url: that.url+"user/logIn",
                method: 'POST',
                data: msg,
                header: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"     /*更改头部*/
                },
                success(res) {
                    let result = res.data;
                    if (result.code == '1') {
                        that.Msg = result.msg;
                        that.setUserId(result.data.uid);
                        that.setClassId(result.data.classesId);
                    } else {
                        that.Msg = result.msg;
                    }
                    resolve(res);
                },
                fail(error) {
                    that.Msg = "NetError";
                    reject(error);
                }
            })
        })

    }

    ModifyPassword(oldPassword, newPassword, pswType) {
        let that = this;
        // var a = ['Time', "AccNum", "PswType", "OldPassword", "NewPassword"]
        // this.setTime();
        // var b = [that.Time, that.AccNum, PswType, that.OtherPassword(oldpassword), that.OtherPassword(newpassword)]
        // this.setSign(a, b);
        let url = that.url + "user/changePassword";
        that.setTime();
        return new Promise(function (resolve, reject) {
            wx.request({
                url: url,
                method: 'POST',
                data: {
                    "time": that.time,
                    "uId": that.userId,
                    "type": pswType,
                    "oldPassword": oldPassword,
                    "newPassword": newPassword,
                },
                header: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"     /*更改头部*/
                },
                success(res) {
                    resolve(res)
                },
                fail(error) {
                    console.log(error)
                    reject(error)
                }
            })
        })
    }

    QueryDealRec(time) {
        var that = this;
        // var a = ['Time', "AccNum", "BeginDate", "EndDate", "Type", "ViceAccNum", "WalletNum", "RecNum", "Count"]
        // this.setTime();
        // var b = [that.Time, that.AccNum, begindate, enddate, type, viceaccnum, walletnum, recnum, count]
        // this.setSign(a, b);
        that.setTime();
        var url = that.url + "consumption/selectByTime"
        let msg={
            "time": time,
            "uId": that.userId
        };
        return new Promise(function (resolve, reject) {
            wx.request({
                url: url,
                method: 'POST',
                data: msg,
                header: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"     /*更改头部*/
                },
                success(res) {
                    resolve(res)
                },
                fail(error) {
                    console.log(error)
                    reject(error)
                }
            })
        })
    }

    // //查询钱包信息
    QueryAccWallent() {
        //接口:AccNum
        let that = this;
        this.setTime();
        let url = that.url + "virtualCard/findYourVc"
        let msg = {
            uId:that.userId
        }
        return new Promise(function (resolve, reject) {
            wx.request({
                url: url,
                data: msg,
                method:'POST',
                header: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"     /*更改头部*/
                },
                success(res) {
                    // var json = x2js.xml2js(res.data).ZYTK;
                    // var code = json.Code;
                    let result = res.data;
                    console.log(result);
                    if (result.code == "1") {
                        that.restMoney = result.data.restMoney;
                        that.isActive = result.data.cardStatus;
                        that.bankCard = result.data.bankCard;
                        that.bankName = result.data.bankName;
                    }
                    resolve(res);
                },
                fail(error) {
                    reject(error)
                    console.error("QueryAccWallent" + error);
                }
            })
        })
    }

    // //查询用户信息
    QueryAccInfo(classId) {
        let that = this;
        let url = that.url + "/classes/selectOne";
        let msg = {
            "id": classId,
        }
        return new Promise(function (resolve, reject) {
            wx.request({
                url: url,
                data: msg,
                method:'POST',
                header: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"     /*更改头部*/
                },
                success(res) {
                    resolve(res);
                },
                fail(error) {
                    reject(error)
                    console.error("QueryAccInfo" + error);
                }
            })
        })
    };
    QueryGradeByTime(str) {
        var that = this;
        return new Promise(function (resolve, reject) {
            wx.request({
                url: that.url + 'grade/selectByIdAndTime',
                method: 'POST',
                header: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"     /*更改头部*/
                },
                data: {
                    "uId":that.userId,
                    "gradeYear": str,
                },
                success(res) {
                    resolve(res)
                },
                fail(res) {
                    reject(res)
                }
            })
        })
    }
    queryGradeById(id) {
        let that = this;
        return new Promise(function (resolve, reject) {
            wx.request({
                url: that.url + 'grade/selectById',
                method: 'POST',
                header: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"     /*更改头部*/
                },
                data: {
                    "id": id,
                },
                success(res) {
                    resolve(res);
                },
                fail(error) {
                    reject(error);
                }
            })
        })
    }

    findSchedule(week) {
        let that = this;
        return new Promise(function (resolve, reject) {
            wx.request({
                url: that.url + 'course/selectByClass',
                method: 'POST',
                header: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"     /*更改头部*/
                },
                data: {
                    "classId":that.classId,
                    "week":week
                },
                success(res) {
                    resolve(res)
                },
                fail(res) {
                    reject(res);
                }
            })
        })
    }

    FindYou(uId,username){
        let that = this;
        let user = {
            "uId": uId,
            "username": username,
        }
        // if(uId != "" && uId != null){
        //     user.uId = uId;
        // }
        // if(username != "" && username != null){
        //     user.username = username;
        // }
        return new Promise(function(resolve,reject){
            wx.request({
                url: that.url + "user/findYou",
                method: "POST",
                header: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"     /*更改头部*/
                },
                data: user,
                success(res) {
                    resolve(res);
                },
                fail(error) {
                    reject(error);
                }
            })
        })
    }
    //充值
    Charge(money, password) {
        var that = this;
        // var a = ['Time', 'AccNum', 'MonTrans', 'Password']
        // this.setTime();
        // var b = [that.Time, that.AccNum, money, that.OtherPassword(password)]
        // this.setSign(a, b);
        var url = that.url + "virtualCard/charge";
        return new Promise(function (resolve, reject) {
            wx.request({
                url: url,
                data: {
                    "time": that.time,
                    "uId": that.userId,
                    "money": money,
                    "password": password,
                },
                method: "POST",
                header: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"     /*更改头部*/
                },
                success(res) {
                    resolve(res);
                },
                fail(error) {
                    reject(error)
                    console.error("BankTransfer" + error);
                }
            })
        })
    }
    //获取所有用户
    getAllUsers(start, length){
        let that = this;
        let url = that.url + "user/getAllUers";
        return new Promise(function (resolve, reject) {
            wx.request({
                url: url,
                data: {
                    "uId": that.userId,
                    "start": start,
                    "length": length,
                },
                method: "POST",
                header: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"     /*更改头部*/
                },
                success(res) {
                    resolve(res);
                },
                fail(error) {
                    reject(error)
                    console.error("BankTransfer" + error);
                }
            })
        })
    }

    getAllCollege(){
        let that = this;
        let url = that.url + "college/selectAll";
        return new Promise(function (resolve, reject) {
            wx.request({
                url: url,
                data: {
                    "uId": that.userId,
                },
                method: "POST",
                header: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"     /*更改头部*/
                },
                success(res) {
                    resolve(res);
                },
                fail(error) {
                    reject(error)
                    console.error("BankTransfer" + error);
                }
            })
        })
    }

    getAllMajor(collegeId){
        let that = this;
        let url = that.url + "major/selectAll";
        console.log(collegeId);
        return new Promise(function (resolve, reject) {
            wx.request({
                url: url,
                data: {
                    "uId": that.userId,
                    "collegeId": collegeId,
                },
                method: "POST",
                header: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"     /*更改头部*/
                },
                success(res) {
                    resolve(res);
                },
                fail(error) {
                    reject(error)
                    console.error("BankTransfer" + error);
                }
            })
        })
    }

    getAllClasses(majorId){
        let that = this;
        let url = that.url + "classes/selectAll";
        return new Promise(function (resolve, reject) {
            wx.request({
                url: url,
                data: {
                    "uId": that.userId,
                    "majorId": majorId,
                },
                method: "POST",
                header: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"     /*更改头部*/
                },
                success(res) {
                    resolve(res);
                },
                fail(error) {
                    reject(error)
                    console.error("BankTransfer" + error);
                }
            })
        })
    }

    addUser(uId,username,classId,userStatus,bankCard){
        let that = this;
        let url = that.url+ "user/addUser";
        this.setTime();
        let time = that.time;
        return new Promise(function (resolve, reject) {
            wx.request({
                url: url,
                data: {
                    "uId": uId,
                    "username": username,
                    "classId": classId,
                    "userStatus": userStatus,
                    "time": time,
                    "bankCard": bankCard
                },
                method: "POST",
                header: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"     /*更改头部*/
                },
                success(res) {
                    resolve(res);
                },
                fail(error) {
                    reject(error)
                    console.error("BankTransfer" + error);
                }
            })
        })
    }

    deleteUser(uIds){
        let that = this;
        let url = that.url+ "user/delete";
        return new Promise(function (resolve, reject) {
            wx.request({
                url: url,
                data: {
                    "uIds": uIds
                },
                method: "POST",
                header: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"     /*更改头部*/
                },
                success(res) {
                    resolve(res);
                },
                fail(error) {
                    reject(error)
                    console.error("BankTransfer" + error);
                }
            })
        })
    }

    resetPassword(uIds){
        let that = this;
        let url = that.url+ "user/resetPassword";
        return new Promise(function (resolve, reject) {
            wx.request({
                url: url,
                data: {
                    "uIds": uIds
                },
                method: "POST",
                header: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"     /*更改头部*/
                },
                success(res) {
                    resolve(res);
                },
                fail(error) {
                    reject(error)
                    console.error("BankTransfer" + error);
                }
            })
        })
    }

    getAllUsersGrades(start,length){
        let that = this;
        let url = that.url + "grade/selectAll";
        return new Promise(function (resolve, reject) {
            wx.request({
                url: url,
                data: {
                    "uId": that.userId,
                    "start": start,
                    "length": length,
                },
                method: "POST",
                header: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"     /*更改头部*/
                },
                success(res) {
                    resolve(res);
                },
                fail(error) {
                    reject(error)
                    console.error("BankTransfer" + error);
                }
            })
        })
    }
    findGrade(uId,gradeName){
        let that = this;
        let user = {
            "uId": uId,
            "gradeName": gradeName,
        }
        return new Promise(function(resolve,reject){
            wx.request({
                url: that.url + "grade/findGradeByLimit",
                method: "POST",
                header: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"     /*更改头部*/
                },
                data: user,
                success(res) {
                    resolve(res);
                },
                fail(error) {
                    reject(error);
                }
            })
        })
    }
    addGrade(grade){
        let that = this;
        return new Promise(function(resolve,reject){
            wx.request({
                url: that.url + "grade/addGrade",
                method: "POST",
                header: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"     /*更改头部*/
                },
                data: grade,
                success(res) {
                    resolve(res);
                },
                fail(error) {
                    reject(error);
                }
            })
        })
    }
    deleteGrade(gradeIds){
        let that = this;
        let url = that.url+ "grade/delete";
        return new Promise(function (resolve, reject) {
            wx.request({
                url: url,
                data: {
                    "gradeIds": gradeIds
                },
                method: "POST",
                header: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"     /*更改头部*/
                },
                success(res) {
                    resolve(res);
                },
                fail(error) {
                    reject(error)
                    console.error("BankTransfer" + error);
                }
            })
        })
    }
    getAllCourses(start,length){
        let that = this;
        let url = that.url + "course/selectAll";
        console.log(that.userId);
        return new Promise(function (resolve, reject) {
            wx.request({
                url: url,
                data: {
                    "uId": that.userId,
                    "start": start,
                    "length": length,
                },
                method: "POST",
                header: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"     /*更改头部*/
                },
                success(res) {
                    resolve(res);
                },
                fail(error) {
                    reject(error)
                    console.error("BankTransfer" + error);
                }
            })
        })
    }
    getAllClassroom(start,length){
        let that = this;
        let url = that.url + "classroom/selectAll";
        console.log(that.userId);
        return new Promise(function (resolve, reject) {
            wx.request({
                url: url,
                data: {
                    "uId": that.userId,
                    "start": start,
                    "length": length,
                },
                method: "POST",
                header: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"     /*更改头部*/
                },
                success(res) {
                    resolve(res);
                },
                fail(error) {
                    reject(error)
                    console.error("BankTransfer" + error);
                }
            })
        })
    }
    addCourses(course){
        let that = this;
        return new Promise(function(resolve,reject){
            wx.request({
                url: that.url + "course/addCourse",
                method: "POST",
                header: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"     /*更改头部*/
                },
                data: course,
                success(res) {
                    resolve(res);
                },
                fail(error) {
                    reject(error);
                }
            })
        })
    }
    findCourse(course){
        console.log(course);
        let that = this;
        return new Promise(function(resolve,reject){
            wx.request({
                url: that.url + "course/findCourse",
                method: "POST",
                header: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"     /*更改头部*/
                },
                data: course,
                success(res) {
                    resolve(res);
                },
                fail(error) {
                    reject(error);
                }
            })
        })

    }
    deleteCourse(courseIds){
        let that = this;
        let url = that.url+ "course/delete";
        return new Promise(function (resolve, reject) {
            wx.request({
                url: url,
                data: {
                    "courseIds": courseIds
                },
                method: "POST",
                header: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"     /*更改头部*/
                },
                success(res) {
                    resolve(res);
                },
                fail(error) {
                    reject(error)
                    console.error("BankTransfer" + error);
                }
            })
        })
    }

    // //请求RandomNum
    // getRandomNum() {
    //     var that = this;
    //     var a = ['Time', 'AccNum']
    //     this.setTime();
    //     var b = [that.Time, that.AccNum]
    //     this.setSign(a, b);
    //     var url = that.url + "GetRandomNum.aspx"
    //     return new Promise(function (resolve, reject) {
    //         wx.request({
    //             url: url,
    //             data: {
    //                 "Time": that.Time,
    //                 "Sign": that.Sign,
    //                 "AccNum": that.AccNum
    //             },
    //             success(res) {
    //                 var json = x2js.xml2js(res.data).ZYTK;
    //                 var RandomNum = json.RandomNum;
    //                 that.RandomNum = RandomNum;
    //                 resolve(res);
    //             },
    //             fail(error) {
    //                 reject(error)
    //                 console.error("getRandomNum" + error);
    //             }
    //         })
    //     })
    // } //请求OrderNum
    // getOrderNum() {
    //     var that = this;
    //     var a = ['Time', 'AccNum']
    //     this.setTime();
    //     var b = [that.Time, that.AccNum]
    //     this.setSign(a, b);
    //     var url = that.url + "GetOrderNum.aspx"
    //     return new Promise(function (resolve, reject) {
    //         wx.request({
    //             url: url,
    //             data: {
    //                 "Time": that.Time,
    //                 "Sign": that.Sign,
    //                 "AccNum": that.AccNum
    //             },
    //             success(res) {
    //                 var json = x2js.xml2js(res.data).ZYTK;
    //                 var OrderNum = json.OrderNum;
    //                 that.OrderNum = OrderNum;
    //                 resolve(res);
    //             },
    //             fail(error) {
    //                 reject(error)
    //                 console.error("OrderNum" + error);
    //             }
    //         })
    //     })
    // }

    // //虚拟校园卡
    // getQRcode(height, width) {
    //     var that = this;
    //     //var url = that.url + "proxy/qr"
    //     var url = that.url + "proxy/getqr";
    //     return new Promise(function (resolve, reject) {
    //         wx.request({
    //             url: url,
    //             method: "POST",
    //             data: {
    //                 "randomNum": that.RandomNum,
    //                 "perCode": that.PerCode,
    //                 "orderNumb": that.OrderNum,
    //                 "customerID": that.CustomerID,
    //                 "cardID": that.CardID,
    //                 "agentID": that.AgentID,
    //                 "accNum": that.AccNum,
    //                 "accName": that.AccName,
    //                 "height": height.toString(),
    //                 "width": width.toString()
    //             },
    //             success(res) {
    //                 resolve(res)
    //             },
    //             error(error) {
    //                 console.log("getQRcode" + error)
    //             }
    //         })
    //     })
    // }

    // //扫描二维码
    // ScanQR(str) {
    //     var that = this;
    //     var url = that.url + "proxy/scan"
    //     return new Promise(function (resolve, reject) {
    //         wx.request({
    //             url: url,
    //             method: "POST",
    //             data: {
    //                 "agentID": that.AgentID,
    //                 "customID": that.CustomerID,
    //                 "data": str
    //             },
    //             success(res) {
    //                 resolve(res)
    //             },
    //             error(error) {
    //                 reject(error)
    //             }
    //         })
    //     })
    // }

    // //获取账号有效性
    QueryAccAuth() {
        var that = this;
        // var a = ['Time', 'AccNum']
        this.setTime();
        // var b = [that.Time, that.AccNum]
        // this.setSign(a, b);
        var url = that.url + "QueryAccAuth.aspx"
        return new Promise(function (resolve, reject) {
            // wx.request({
            //     url: url,
            //     data: {
            //         "Time": that.Time,
            //         "Sign": that.Sign,
            //         "AccNum": that.AccNum
            //     },
            //     success(res) {
            //         var json = x2js.xml2js(res.data).ZYTK;
            //         var AccStatus = json.AccStatus;
            //         var BankTransState = json.BankTransState;
            //         that.AccStatus = AccStatus;
            //         that.BankTransState = BankTransState;
            //         resolve(res)
            //     },
            //     fail(error) {
            //         reject(error)
            //     }
            // })
        })
    }

    // //获取付款信息
    // GetDealerInfo(dealerNum, staNum, terNum) {
    //     var that = this;
    //     var a = ['Time', "AccNum", "DealerNum", "StaNum", "TerNum"]
    //     this.setTime();
    //     var b = [that.Time, that.AccNum, dealerNum, staNum, terNum]
    //     this.setSign(a, b);
    //     var url = that.url + "GetDealerInfo.aspx"

    //     return new Promise(function (resolve, reject) {
    //         wx.request({
    //             url: url,
    //             data: {
    //                 "Time": that.Time,
    //                 "Sign": that.Sign,
    //                 "AccNum": that.AccNum,
    //                 "DealerNum": dealerNum,
    //                 "StaNum": staNum,
    //                 "TerNum": terNum
    //             },
    //             success(res) {
    //                 resolve(res)
    //             },
    //             fail(error) {
    //                 console.log(error)
    //                 reject(error)
    //             }
    //         })
    //     })
    // }

    // Pay(dealerNum, staNum, terNum, password, mondeal) {
    //     var that = this;
    //     var a = ['Time', "AccNum", "OrderNum", "DealerNum", "StaNum", "TerNum", "Password", "MonDeal"]
    //     this.setTime();
    //     password = that.OtherPassword(password);

    //     var b = [that.Time, that.AccNum, that.OrderNum, dealerNum, staNum, terNum, password, mondeal]
    //     this.setSign(a, b);
    //     var url = that.url + "ScanPayMon.aspx"

    //     return new Promise(function (resolve, reject) {
    //         wx.request({
    //             url: url,
    //             data: {
    //                 "Time": that.Time,
    //                 "Sign": that.Sign,
    //                 "AccNum": that.AccNum,
    //                 "OrderNum": that.OrderNum,
    //                 "DealerNum": dealerNum,
    //                 "StaNum": staNum,
    //                 "TerNum": terNum,
    //                 "Password": password,
    //                 "MonDeal": mondeal
    //             },
    //             success(res) {
    //                 resolve(res)
    //             },
    //             fail(error) {
    //                 console.log(error)
    //                 reject(error)
    //             }
    //         })
    //     })
    // }


    // QueryAccountDoor(date, recnum, count) {
    //     var that = this;
    //     var a = ['Time', "AccNum", "Date", "RecNum", "Count"]
    //     this.setTime();
    //     var b = [that.Time, that.AccNum, date, recnum, count]
    //     this.setSign(a, b);
    //     var url = that.url + "QueryAccountDoor.aspx"
    //     return new Promise(function (resolve, reject) {
    //         wx.request({
    //             url: url,
    //             data: {
    //                 "Time": that.Time,
    //                 "Sign": that.Sign,
    //                 "AccNum": that.AccNum,
    //                 "Date": date,
    //                 "RecNum": recnum,
    //                 "Count": count
    //             },
    //             success(res) {
    //                 resolve(res)
    //             },
    //             fail(error) {
    //                 console.log(error)
    //                 reject(error)
    //             }
    //         })
    //     })
    // }

    // ApplyDoorPwd(devicesnum, doorid) {
    //     var that = this;
    //     var a = ['Time', "AccNum", "DeviceNum", "DoorID"]
    //     this.setTime();
    //     var b = [that.Time, that.AccNum, devicesnum, doorid]
    //     this.setSign(a, b);
    //     var url = that.url + "ApplyDoorPwd.aspx"
    //     return new Promise(function (resolve, reject) {
    //         wx.request({
    //             url: url,
    //             data: {
    //                 "Time": that.Time,
    //                 "Sign": that.Sign,
    //                 "AccNum": that.AccNum,
    //                 "DeviceNum": devicesnum,
    //                 "DoorID": doorid
    //             },
    //             success(res) {
    //                 resolve(res)
    //             },
    //             fail(error) {
    //                 console.log(error)
    //                 reject(error)
    //             }
    //         })
    //     })
    // }



    // JwLogin(username, password) {
    //     var that = this;
    //     var url = that.url + "query/login"
    //     return new Promise(function (resolve, reject) {
    //         wx.request({
    //             url: url,
    //             method: 'POST',
    //             data: {
    //                 "username": username,
    //                 "password": password,
    //                 "nanyue": that.Nanyue
    //             },
    //             success(res) {
    //                 resolve(res)
    //             },
    //             fail(res) {
    //                 reject(res)
    //             }
    //         })
    //     })
    // }


    // getmsg() {
    //     var that = this;
    //     return new Promise(function (resolve, reject) {
    //         wx.request({
    //             url: that.url + 'query/getMsg',
    //             method: "POST",
    //             success(res) {
    //                 resolve(res);
    //             },
    //             fail(error) {
    //                 reject(res);
    //             }
    //         })
    //     })
    // }

    // ResetPassword(username, idcard) {
    //     var that = this;
    //     return new Promise(function (resolve, reject) {
    //         wx.request({
    //             url: that.url + 'query/resetPassword',
    //             //url:"http://127.0.0.1:5000/query/resetPassword",
    //             method: "POST",
    //             data: {
    //                 "username": that.UserNumber,
    //                 "idcardnum": that.CertCode,
    //                 "nanyue": that.Nanyue
    //             },
    //             success(res) {
    //                 console.log(res)
    //                 resolve(res);
    //             },
    //             fail(error) {
    //                 reject(error)
    //             }
    //         })
    //     })
    // }

    // CetGetVerify(id_num, name) {
    //     var that = this;
    //     return new Promise(function (resolve, reject) {
    //         wx.request({
    //             url: that.url + 'publicexam/cetgetverify',
    //             method: "POST",
    //             data: {
    //                 "id_num": id_num,
    //                 "name": name
    //             },
    //             success(res) {
    //                 resolve(res)
    //             },
    //             fail(error) {
    //                 reject(error)
    //             }
    //         })
    //     })
    // }

    // CetGetScore(id_num, name, capcha, cookie) {
    //     var that = this;
    //     return new Promise(function (resolve, reject) {
    //         wx.request({
    //             url: that.url + 'publicexam/cetgetscore',
    //             method: "POST",
    //             data: {
    //                 "id_num": id_num,
    //                 "name": name,
    //                 "capcha": capcha,
    //                 "cookie": cookie
    //             },
    //             success(res) {
    //                 resolve(res)
    //             },
    //             fail(error) {
    //                 reject(error)
    //             }
    //         })
    //     })
    // }
    // GetAboutus() {
    //     var that = this;
    //     return new Promise(function (resolve, reject) {
    //         wx.request({
    //             url: that.url + 'query/getaboutus',
    //             method: "GET",
    //             data: null,
    //             success(res) {
    //                 resolve(res)
    //             },
    //             fail(error) {
    //                 reject(error)
    //             }
    //         })
    //     })
    // }
    // ViewAd() {
    //     var that = this;
    //     wx.request({
    //         url: that.url + 'query/viewadd',
    //         method: "POST",
    //         data: {
    //             "userid": that.UserNumber == "" ? "游客" : that.UserNumber,
    //             "username": that.AccName == "" ? "游客" : that.AccName
    //         },
    //         success(res) {},
    //         fail(error) {}
    //     })
    // }
    // GetAdData() {
    //     var that = this;
    //     return new Promise(function (resolve, reject) {
    //         wx.request({
    //             url: that.url + 'query/getviewad',
    //             method: "GET",
    //             data: null,
    //             success(res) {
    //                 resolve(res)
    //             },
    //             fail(error) {
    //                 reject(error)
    //             }
    //         })
    //     })
    // }
    // GetRankList(userid, xqmc) {
    //     var that = this;
    //     return new Promise(function (resolve, reject) {
    //         wx.request({
    //             url: that.url + "rank/getrankmsg",
    //             method: "POST",
    //             data: {
    //                 "userid": userid,
    //                 "xqmc": xqmc
    //             },
    //             success(res) {
    //                 resolve(res);
    //             },
    //             fail(error) {
    //                 reject(error);
    //             }
    //         })
    //     })
    // }
}

export  {
    http
}