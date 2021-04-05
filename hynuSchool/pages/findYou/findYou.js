// pages/findYou/findYou.js
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
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
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
  findPeople: function () {
    var that = this;
    that.setData({
      showloading: true,
    })
    if (that.data.inputNo == "" && that.data.inputName == "") {
      wx.showToast({
        title: '请输入学号或姓名',
        icon: 'none',
        duration: 2000
      });
      that.setData({
        memberList: null,
        showloading: false,
      });
      return;
    }
    let param = {
      "userid": that.data.inputNo,
      "xm": that.data.inputName
    };
    that.getData(param);
  },
  dealData(data) {
    delete data.code;
    delete data.msg;
    let list = new Array();
    let index = 0;
    for (let i in data) {
      let cur = {};
      cur.name = data[i].username;
      cur.no = data[i].uid;
      let detailList = new Array();
      let item = {
        "班级": data[i].classes.major.majorName + data[i].classes.classesNo + "班",
        "年级": data[i].classes.schoolYear,
        "学号": data[i].uid,
        "姓名": data[i].username,
        "学院": data[i].classes.major.college.collegeName,
      };
      for(let key in item){
        let itemNow={};
        itemNow.name = key;
        itemNow.value = item[key];
        detailList.push(itemNow);
      }
      cur.detail = detailList;
      cur.hiddena = true;
      cur.id = index;
      list.push(cur);
      index++;
    }
    return list
  },
  isOpen: function (e) {
    var idx = Number(e.currentTarget.dataset.index);
    var that = this;
    var list = that.data.memberList;
    list[idx].hiddena = !list[idx].hiddena;
    this.setData({
      memberList: list
    });
    return true;
  },
})