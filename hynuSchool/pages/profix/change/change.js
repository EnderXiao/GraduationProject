// pages/profix/change/change.js
var hei = wx.getMenuButtonBoundingClientRect().top;
const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        stateH: hei,
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        this.setData({
            stateH: app.hei
        })
        if (app.http.userId == "") {
            wx.showModal({
                title: '请先登录',
                content: '要先登录才能使用校园卡修改密码功能哦',
                success(res) {
                    if (res.confirm) {
                        wx.redirectTo({
                            url: '../../login/login',
                        })
                    }
                }
            });
            return;
        }
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function() {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function() {

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function() {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function() {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function() {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function() {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function() {

    },
    judgePassword: function(e){
      let flg1 = e.oldpassword === ""||e.newpassword === "" || e.newpassword2 === "";
      let flg2 = e.newpassword !== e.newpassword2;
      let flg3 = e.newpassword == e.oldpassword;
      if (flg1)
      {
        wx.showModal({
          title: '错误',
          content: '请输入完整信息',
        })
        return 0;
      }
      if(flg2)
      {
        wx.showModal({
          title: '错误',
          content: '两次密码不一致',
        })
        return 0;
      }
      if(flg3)
      {
        wx.showModal({
          title: '错误',
          content: '新密码不能与旧密码一致',
        });
        return 0;
      }
      return 1;
    },
    changep1: function(e) {
      let objData = e.detail.value;
      console.log(objData);
      if(this.judgePassword(objData)){
        app.http.ModifyPassword(objData.oldpassword,objData.newpassword,1).then((res)=>{
          let result = res.data;
          if(result.code!="1")
              wx.showModal({
                  title: '错误',
                  content: result.msg,
              })
          else{
            wx.showModal({
              title: '成功',
              content: result.msg,
              success(){
                wx.redirectTo({
                  url: '../../login/login',
                })
              }
            })
          }
        })
      }
    },
    changep2: function(e) {
      let objData = e.detail.value;
      console.log(objData);
      if(this.judgePassword(objData)){
        app.http.ModifyPassword(objData.oldpassword, objData.newpassword, 2).then((res) => {
          let result = res.data;
          if (result.code != "1")
          {
            wx.showModal({
                title: '错误',
                content: result.msg,
            })
          }
          else
          {
            wx.showModal({
                title: '成功',
                content: result.msg,
                success() {
                    wx.redirectTo({
                        url: '../../login/login',
                    })
                }
            })
          }
        })
      }
    }
})