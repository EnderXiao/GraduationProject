// pages/profix/profix.js
// pages/myself/myself.js
const app = getApp();
var hei = wx.getMenuButtonBoundingClientRect().top;
let videoAd = null;
let times = 0;
let num = 0;
let click = 0;
Page({

    /**
     * 页面的初始数据
     */
    data: {
        stateH: hei,
        name: "",
        id: "",
        adtext: "看广告解锁平时成绩查询",
        disabled: false,
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
      let that = this;
        this.setData({
            name: app.http.username,
            id: app.http.userId,
        });        
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
        clearInterval(num);
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
    logout: function (e) {
        wx.clearStorage();
        wx.reLaunch({
            url: '../login/login',
        })
    },
    record: function () {
        wx.navigateTo({
            url: './record/record',
        })
    },
    aboutus: function () {
        app.http.GetAboutus().then((res) => {
            console.log(res)
            if (res.data.code == 1)
                wx.showModal({
                    title: '你好呀',
                    content: res.data.msg,
                });
        })
    },
    change: function () {
        wx.navigateTo({
            url: './change/change',
        })
    },
})