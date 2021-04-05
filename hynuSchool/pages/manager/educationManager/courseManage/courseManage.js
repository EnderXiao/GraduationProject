// pages/manager/educationManager/courseManage/courseManage.js
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
    weekDay: null,
    weekDayIndex: 0,
    startWeek: null,
    startWeekIndex: 0,
    endWeek: null,
    endWeekIndex: 0,
    courseNo: null,
    courseNoIndex: 0,
    classroom: null,
    classroomName: null,
    classroomIndex: 0,
    classList: null,
    classNameList: null,
    classIndex: 0,
    insertClassNameList: null,
    insertClassIndex: 0,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getAllClass();
    this.getAllCourse();
    this.getAllClassroom();
    this.pickerInit();
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
  getAllClassroom: function(){
    let that = this;
    app.http.getAllClassroom().then((res)=>{
      let result = res.data;
      if (result.code === 1) {
        console.log(result.data);
        let list = that.dealClassroom(result.data);
        that.setData({
          classroom: result.data,
          classroomNameList: list,
          classroomIndex: 0,
        })
      } else {
        wx.showModal({
          title: '提示',
          content: result.msg,
        });
      }
    })
  },
  getAllClass: function (){
    let that = this;
    app.http.getAllClasses("").then((res)=>{
      let result = res.data;
      if (result.code === 1) {
        let list = that.dealClass(result.data);
        that.setData({
          classList: list,
          classIndex: 0,
          insertClassIndex: 0,
        })
        console.log(list);
      } else {
        wx.showModal({
          title: '提示',
          content: result.msg,
        });
      }
    })
  },
  dealClassroom(list){
    let classroomList = new Array();
    for(let key in list){
      let classroomName = list[key].areaName+list[key].classBuilding+list[key].classRoomNo;
      classroomList.push(classroomName);
    }
    return classroomList;
  },
  dealClass(list){
    let that = this;
    let result = new Array();
    let classNameList = new Array();
    for(let key in list){
      let classId = list[key].classesId;
      let majorName = list[key].major.majorName;
      let schoolYear = list[key].schoolYear;
      let classNo = list[key].classesNo;
      let item = {
        "classId": classId,
        "name": schoolYear+''+majorName+''+classNo,
      }
      classNameList.push(item.name);
      result.push(item);
    }
    that.setData({
      classNameList: classNameList,
      insertClassNameList: classNameList,
    })
    return result;
  },
  getAllCourse: function () {
    let that = this;
    let start = that.data.startNo;
    let length = that.data.queryLength;
    app.http.getAllCourses(start, length).then((res) => {
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
    app.http.findCourse(param).then((res) => {
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

  pickerSearchClassChange: function (e){
    let that = this;
    that.setData({
      classIndex: e.detail.value,
    })
  },
  pickerInsertClassChange: function (e){
    let that = this;
    that.setData({
      insertClassIndex: e.detail.value,
    })
  },
  pickerStartWeekChange: function (e) {
    let that = this;
    that.setData({
      startWeekIndex: e.detail.value,
    })
  },
  pickerEndWeekChange: function (e){
    let that = this;
    that.setData({
      endWeekIndex: e.detail.value,
    })
  },
  pickerWeekDayChange: function (e) {
    let that = this;
    that.setData({
      weekDayIndex: e.detail.value,
    })
  },
  pickerCourseNoChange: function (e) {
    let that = this;
    that.setData({
      courseNoIndex: e.detail.value,
    })
  },
  pickerClassRoomChange: function (e){
    let that = this;
    that.setData({
      classroomIndex: e.detail.value,
    })
  },
  pickerInit: function (e) {
    let that = this;
    let startWeek = new Array();
    let weekDay = new Array();
    let courseNo = new Array();
    for(let i = 1; i <= 26; i++){
      startWeek.push(i);
    }
    for(let i = 1; i <= 7; i++){
      weekDay.push(i);
    }
    for(let i = 1; i <= 5; i++){
      courseNo.push(i);
    }
    that.setData({
      startWeek: startWeek,
      endWeek: startWeek,
      weekDay: weekDay,
      courseNo: courseNo,
      insertClassIndex: 0,
      startWeekIndex: 0,
      weekDayIndex: 0,
      courseNoIndex: 0,
      classroomIndex: 0,
      endWeekIndex: 0,
    })
  },
  dealData(data) {
    let that = this;
    let list = new Array();
    let length = that.data.queryLength;
    console.log(data);
    for (let key in data) {
      let courseName = data[key].courseName;
      let courseId = data[key].courseId;
      let weekNo = data[key].weekNo;
      let weekDay = data[key].weekDay;
      let courseNo = data[key].courseNo;
      let userNow = {
        "courseId": courseId,
        "courseName": courseName,
        "weekNo": "第"+weekNo+"周",
        "weekDay": "周"+weekDay,
        "courseNo": "第"+courseNo+"节",
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
    let courseName = result["courseName"];
    let classesId = that.data.classList[that.data.insertClassIndex].classId;
    let weekNo = that.data.startWeek[that.data.startWeekIndex];
    let weekEnd = that.data.endWeek[that.data.endWeekIndex];
    let weekDay = that.data.weekDay[that.data.weekDayIndex];
    let courseNo = that.data.courseNo[that.data.courseNoIndex];
    let classRoomId = that.data.classroom[that.data.classroomIndex].classRoomId;
    if (courseName === "" || courseName === null) {
      wx.showToast({
        title: "学号不能为空",
        icon: 'none',
        duration: 2000
      });
      return;
    }
    if (weekNo > weekEnd) {
      wx.showToast({
        title: "开始时间不能小于结束时间",
        icon: 'none',
        duration: 2000
      });
      return;
    }
    let course = {
      "classesId": classesId,
      "courseName": courseName,
      "weekNo": weekNo,
      "weekEnd": weekEnd,
      "weekDay": weekDay,
      "courseNo": courseNo,
      "classRoomId": classRoomId,
    }
    console.log(course);
    app.http.addCourses(course).then((res) => {
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
  findCourse: function () {
    var that = this;
    if (that.data.inputNo == "" && that.data.inputName == "") {
      that.getAllCourse();
      return;
    }
    let classId = that.data.classList[that.data.classIndex].classId;
    let courseName = that.data.inputName;
    let param = {
      "classId": classId,
      "courseName": courseName,
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
    that.getAllCourse();
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
    that.getAllCourse();
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
    console.log(list);
    let courseIds = new Array();
    for (let key in list) {
      if (list[key].checked) {
        courseIds.push(list[key].courseId);
      }
    }
    console.log(courseIds);
    app.http.deleteCourse(courseIds).then((res) => {
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