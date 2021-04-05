// pages/manager/educationManager/gradeManage/gradeManage.js
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
    gradeYear: null,
    gradeYearIndex: 0,
    examType: null,
    examTypeIndex: 0,
    classType: null,
    classTypeIndex: 0,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getAllGrades();
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
  getAllGrades: function () {
    let that = this;
    let start = that.data.startNo;
    let length = that.data.queryLength;
    app.http.getAllUsersGrades(start, length).then((res) => {
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
    app.http.findGrade(param.userid, param.gradeName).then((res) => {
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

  pickerExamTypeChange: function (e) {
    let that = this;
    that.setData({
      examTypeIndex: e.detail.value,
    })
  },
  pickerGradeYearChange: function (e) {
    let that = this;
    that.setData({
      gradeYearIndex: e.detail.value,
    })
  },
  pickerClassTypeChange: function (e) {
    let that = this;
    that.setData({
      classTypeIndex: e.detail.value,
    })
  },
  pickerInit: function (e) {
    let that = this;
    let date = new Date();
    let nowYear = date.getFullYear();
    let list = new Array()
    for (var i = nowYear; i > nowYear - 4; i--) {
      let str = (i - 1).toString() + '-' + (i).toString() + '-2';
      let str2 = (i - 1).toString() + '-' + (i).toString() + '-1';
      list.push(str2);
      list.push(str);
    }
    that.setData({
      examType: ["考试", "考察"],
      gradeYear: list,
      classType: ["必修", "选修", "实践"],
      examTypeIndex: 0,
      gradeYearIndex: 0,
      classTypeIndex: 0,
    })
  },
  swiperChange: function (e) {
    let that = this;
    let swiperIndex = e.detail.current;
    if (swiperIndex === 1) {
      that.pickerInit();
    }
  },
  dealData(data) {
    let that = this;
    let list = new Array();
    let length = that.data.queryLength;
    console.log(data);
    for (let key in data) {
      let gradeId = data[key].gradeId;
      let gradeName = data[key].gradeName;
      let userId = data[key].uid;
      let gradeSum = data[key].gradeSum;
      let gradeYear = data[key].gradeYear;
      let userNow = {
        "gradeId": gradeId,
        "userId": userId,
        "gradeName": gradeName,
        "gradeSum": gradeSum,
        "gradeYear": gradeYear,
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
  addGrade(e) {
    console.log(e);
    let result = e.detail.value;
    let that = this;
    let userId = result["userId"];
    let gradeName = result["gradeName"];
    let dailyGrade = result["dailyGrade"];
    let examGrade = result["examGrade"];
    let credit = result["credit"];
    let examType = that.data.examType[that.data.examTypeIndex];
    let gradeYear = that.data.gradeYear[that.data.gradeYearIndex];
    let classType = that.data.classType[that.data.classTypeIndex];
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
    if (gradeName === "" || gradeName === null) {
      wx.showToast({
        title: "课程不能为空",
        icon: 'none',
        duration: 2000
      });
      return;
    }
    if (examGrade === "" || examGrade === null) {
      wx.showToast({
        title: "考试成绩不能为空",
        icon: 'none',
        duration: 2000
      });
      return;
    }
    if (credit === "" || credit === null) {
      wx.showToast({
        title: "学分不能为空",
        icon: 'none',
        duration: 2000
      });
      return;
    }
    if (dailyGrade === "" || dailyGrade === null) {
      dailyGrade = 100;
    }
    let grade = {
      "uId": userId,
      "dailyGrade": dailyGrade,
      "examGrade": examGrade,
      "examType": examType,
      "gradeName": gradeName,
      "gradeYear": gradeYear,
      "classType": classType,
      "classCredit": credit,
    }
    app.http.addGrade(grade).then((res) => {
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
  findGrade: function () {
    var that = this;
    if (that.data.inputNo == "" && that.data.inputName == "") {
      that.getAllGrades();
      return;
    }
    let param = {
      "userid": that.data.inputNo,
      "gradeName": that.data.inputName
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
    let gradeIds = new Array();
    for (let key in list) {
      if (list[key].checked) {
        gradeIds.push(list[key].gradeId);
      }
    }
    console.log(gradeIds);
    app.http.deleteGrade(gradeIds).then((res) => {
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
})