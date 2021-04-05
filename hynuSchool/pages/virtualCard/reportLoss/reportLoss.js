// pages/virtualCard/reportLoss/reportLoss.js
const app = getApp();

Page({

    /**
     * 页面的初始数据
     */
    data: {
        stateH: app.hei,
        showPassword: false,
        buttonMsg: "",
        states: "加载中......",
        password: "",
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        console.log(app.http.isActive);
        let that = this;
        if (app.http.isActive == "1") {
            that.setData({
                states: "可用",
                buttonMsg: "挂失"
            })
        } else {
            that.setData({
                states: "不可用",
                buttonMsg: "激活"
            })
        }
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
    report: function (e) {

    },
    passwordInput: function (e) {
        let objData = e.detail.value;
        console.log(objData);
        if (objData.password == "") {
            wx.showModal({
                title: '错误',
                content: '请输入密码',
            });
            return;
        }
        let password = objData.password;
        this.valueSix(password);
    },
    valueSix(password) {
        // 模态交互效果
        var that = this;
        var psd = password;
        app.http.ReportLost(psd).then((res) => {
            let result = res.data;
            if (result.code == "1") {
                app.http.isActive = result.data;
                if (result.data == "0") {
                    that.setData({
                        states: "不可用",
                        buttonMsg: "激活",
                    });
                    wx.showModal({
                        title: '成功',
                        content: result.msg,
                    });

                } else {
                    that.setData({
                        states: "可用",
                        buttonMsg: "冻结",
                    });
                    wx.showModal({
                        title: '成功',
                        content: result.msg,
                    });

                }
            } else wx.showModal({
                title: '错误',
                content: result.msg,
            })
        })

    },
})