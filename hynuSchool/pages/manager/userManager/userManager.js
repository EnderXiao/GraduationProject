// pages/manager/userManager/userManager.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    stateH: null,
    inputNo: "",
    inputName: "",
    memberList: null, //用于存储查询结果
    showloading: false,
    startNo: 0,
    queryLength: 5,
    selectAll: false,
    userStatus: ["学生", "管理员"],
    userStatusIndex: 0,
    userClass: null,
    userClassIndex: 0,
    userMajor: null,
    userMajorIndex: 0,
    userCollege: null,
    userCollegeIndex: 0,
    userCollegeNO: null,
    userMajorNO: null,
    userClass: null,
    userClassIndex: 0,
    userClassNo: null,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getAllUsers();
    this.setData({
      stateH: app.hei
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  getAllUsers: function () {
    let that = this;
    let start = that.data.startNo;
    let length = that.data.queryLength;
    app.http.getAllUsers(start, length).then((res) => {
      let result = res.data;
      if (result.code === 1) {
        let list = that.dealData(result.data);
        that.setData({
          memberList: list,
          showloading: false,
        })
      } else {
        wx.showModal({
          title: '提示',
          content: result.msg,
        });
      }
    });
  },
  noInput: function (e) {
    var that = this;
    that.setData({
      inputNo: e.detail.value,
    })
  },
  nameInput: function (e) {
    var that = this;
    that.setData({
      inputName: e.detail.value,
    })
  },
  getData(param) { //接受数据函数
    let that = this;
    app.http.FindYou(param.userid, param.xm).then((res) => {
      if (res.data.code != 1) {
        wx.showToast({
          title: res.data.msg,
          icon: 'none',
          duration: 2000
        });
        that.setData({
          memberList: null,
          showloading: false,
        });
        return;
      }
      let list = that.dealData(res.data.data);
      that.setData({
        memberList: list,
        showloading: false,
      })
    })

  },
  selectAll: function () {
    let that = this;
    let list = that.data.memberList;
    let selectAll = that.data.selectAll;
    let status = false;
    if (!selectAll) {
      status = true;
    }
    for (let key in list) {
      if (list[key].checked != null) {
        list[key].checked = status;
      }
    }
    that.setData({
      memberList: list,
      selectAll: status,
    })
  },

  pickerCollegeChange: function (e) {
    let that = this;
    let collegeIndexList = that.data.userCollegeNO;
    console.log(collegeIndexList);
    console.log(e.detail.value);
    let collegeIndex = collegeIndexList[e.detail.value];
    that.setData({
      userCollegeIndex: e.detail.value,
    })
    console.log(collegeIndex);
    that.getMajor(collegeIndex);
  },
  pickerMajorChange: function (e) {
    let that = this;
    let majorIndexList = that.data.userMajorNO;
    let majorIndex = majorIndexList[e.detail.value];
    that.setData({
      userMajorIndex: e.detail.value,
    })
    that.getClassNo(majorIndex);
  },
  pickerClassChange: function (e) {
    let that = this;
    let classIndexList = that.data.userClassNo;
    let classIndex = classIndexList[e.detail.value];
    that.setData({
      userClassIndex: e.detail.value,
    })
  },
  pickerInit: function (e) {
    let that = this;
    that.getCollege();
    that.setData({
      userMajor: ["请先选择学院"],
      userClass: ["请先选择专业"],
      userMajorNO: [0],
      userClassNo: [0],
      userMajorIndex: 0,
      userClassIndex: 0,
      userCollegeIndex: 0,
    })
  },
  swiperChange: function (e) {
    let that = this;
    let swiperIndex = e.detail.current;
    if (swiperIndex === 1) {
      that.pickerInit();
    }
  },
  getClassNo(majorIndex) {
    let that = this;
    if (majorIndex == 0) {
      that.setData({
        userClass: ["请先选择专业"],
        userClassNO: [0],
        userClassIndex: 0,
      })
      return;
    }
    app.http.getAllClasses(majorIndex).then((res) => {
      let result = res.data;
      if (result.code != 1) {
        wx.showToast({
          title: result.msg,
          icon: 'none',
          duration: 2000
        });
        return;
      }
      let list = [];
      let indexList = [];
      list.push("请选择班级");
      indexList.push(0);
      for (let key in result.data) {
        list.push(result.data[key].schoolYear + "级" + result.data[key].classesNo + "班");
        indexList.push(result.data[key].classesId);
      }
      that.setData({
        userClass: list,
        userClassNo: indexList,
        userClassIndex: 0,
      });
    })
  },
  getMajor(collegeIndex) {
    if (collegeIndex == 0) {
      this.pickerInit();
      return;
    }
    let that = this;
    app.http.getAllMajor(collegeIndex).then((res) => {
      let result = res.data;
      if (result.code != 1) {
        wx.showToast({
          title: result.msg,
          icon: 'none',
          duration: 2000
        });
        return;
      }
      let list = [];
      let indexList = [];
      list.push("请选择专业");
      indexList.push(0);
      for (let key in result.data) {
        list.push(result.data[key].majorName);
        indexList.push(result.data[key].majorId);
      }
      console.log(list);
      that.setData({
        userMajor: list,
        userMajorNO: indexList,
        userMajorIndex: 0,
      });
    })
  },
  getCollege() {
    let that = this;
    app.http.getAllCollege().then((res) => {
      let result = res.data;
      if (result.code != 1) {
        wx.showToast({
          title: result.msg,
          icon: 'none',
          duration: 2000
        });
        return;
      }
      let list = [];
      let indexList = [];
      list.push("请选择学院");
      indexList.push(0);
      for (let key in result.data) {
        list.push(result.data[key].collegeName);
        indexList.push(result.data[key].collegeId);
      }
      that.setData({
        userCollege: list,
        userCollegeNO: indexList,
        userCollegeIndex: 0,
      });
    });
  },
  dealData(data) {
    let that = this;
    let list = new Array();
    let length = that.data.queryLength;
    for (let key in data) {
      let classId = data[key].classes.major.majorName + '' + data[key].classes.classesNo + "班";
      let userId = data[key].uid;
      let username = data[key].username;
      let userNow = {
        "userId": userId,
        "username": username,
        "class": classId,
        "checked": false,
      }
      list.push(userNow);
    };
    while (list.length < length) {
      let userSpace = {
        "userId": "",
        "username": "",
        "class": "",
        "checked": null,
      };
      list.push(userSpace);
    }
    return list
  },
  addUser(e) {
    let that = this;
    let userId = e.detail.value["userId"];
    let username = e.detail.value["username"];
    let classId = that.data.userClassNo[that.data.userClassIndex];
    let userStatus = that.data.userStatusIndex + 1;
    let bankCardNo = e.detail.value["bankCardNo"];
    if (userId === "" || userId === null) {
      wx.showToast({
        title: "学号不能为空",
        icon: 'none',
        duration: 2000
      });
      return;
    }
    if (userId.length < 7) {
      wx.showToast({
        title: "学号不小于7位",
        icon: 'none',
        duration: 2000
      });
      return;
    }
    if (username === "" || username === null) {
      wx.showToast({
        title: "姓名不能为空",
        icon: 'none',
        duration: 2000
      });
      return;
    }
    if (bankCardNo === "" || bankCardNo === null) {
      wx.showToast({
        title: "银行卡号不能为空",
        icon: 'none',
        duration: 2000
      });
      return;
    }
    if (classId === 0) {
      wx.showToast({
        title: "班级无效",
        icon: 'none',
        duration: 2000
      });
      return;
    }
    app.http.addUser(userId, username, classId, userStatus, bankCardNo).then((res) => {
      let result = res.data;
      if (result.code != 1) {
        wx.showToast({
          title: result.msg,
          icon: 'none',
          duration: 2000
        });
        return;
      } else {
        wx.showModal({
          title: '提示',
          content: result.msg,
        });

      }
    })
  },
  noInput: function (e) {
    var that = this;
    that.setData({
      inputNo: e.detail.value,
    })
  },
  nameInput: function (e) {
    var that = this;
    that.setData({
      inputName: e.detail.value,
    })
  },
  findPeople: function () {
    var that = this;
    that.setData({
      showloading: true,
    })
    if (that.data.inputNo == "" && that.data.inputName == "") {
      that.getAllUsers();
      return;
    }
    let param = {
      "userid": that.data.inputNo,
      "xm": that.data.inputName
    };
    that.getData(param);
  },
  previous: function () {
    let that = this;
    let length = that.data.queryLength;
    let start = that.data.startNo;
    if (start == 0) {
      wx.showToast({
        title: '已到达第一页',
        icon: 'none',
        duration: 2000
      });
      return;
    }
    let startNow = start - length;
    that.setData({
      startNo: startNow,
      selectAll: false,
    })
    that.getAllUsers();
  },
  next: function () {
    let that = this;
    let length = that.data.queryLength;
    let start = that.data.startNo;
    let list = that.data.memberList;
    for (let key in list) {
      if (list[key].userId == "") {
        wx.showToast({
          title: '已到达最后一页',
          icon: 'none',
          duration: 2000
        });
        return;
      }
    }
    let startNow = start + length;
    that.setData({
      startNo: startNow,
      selectAll: false,
    })
    that.getAllUsers();
  },
  selectOne: function (e) {
    let that = this;
    let id = e.currentTarget.id;
    let list = that.data.memberList;
    list[id].checked = e.detail.value;
    that.setData({
      memberList: list,
    })
  },
  delete: function () {
    let that = this;
    let list = that.data.memberList;
    let uIds = new Array();
    for (let key in list) {
      if (list[key].checked) {
        uIds.push(list[key].userId);
      }
    }
    console.log(uIds);
    app.http.deleteUser(uIds).then((res) => {
      let result = res.data;
      console.log(result);
      if (result.code === 1) {
        wx.showModal({
          title: '提示',
          content: result.msg,
        });
      } else {
        wx.showModal({
          title: '提示',
          content: result.msg,
        });
      }
    });
  },
  resetPassword: function () {
    let that = this;
    let list = that.data.memberList;
    let uIds = new Array();
    for (let key in list) {
      if (list[key].checked) {
        uIds.push(list[key].userId);
      }
    }
    console.log(uIds);
    app.http.resetPassword(uIds).then((res) => {
      let result = res.data;
      console.log(result);
      if (result.code === 1) {
        wx.showModal({
          title: '提示',
          content: result.msg,
        });
      } else {
        wx.showModal({
          title: '提示',
          content: result.msg,
        });
      }
    });
  }
})