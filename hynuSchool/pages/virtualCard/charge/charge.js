// pages/virtualCard/charge/charge.js
const app = getApp()
var Money = ""
Page({

    /**
     * 页面的初始数据
     */
    data: {
        bankName: "",
        bankCard: "",
        showPassword: false,
        password: "",
        stateH: app.hei
    },
    valueSix(money,password) {
        // 模态交互效果
        app.http.Charge(money, password).then((res) => {
            let result = res.data;
            console.log(result);
            if (result.Code == "1") {
                wx.showModal({
                    title: '提交成功',
                    content: result.msg,
                    success(res) {
                        wx.navigateBack({
                            delta: 1,
                        })
                    }
                })

            } else {
                wx.showModal({
                    title: '错误',
                    content: result.msg,
                })
            }
        })


    },
    //显示交易密码框
    passwordInput: function(e) {
        let objData = e.detail.value;
        console.log(objData.money);
        if (objData.money == "") {
            wx.showModal({
                title: '错误',
                content: '金额不能为空',
            });
            return;
        }
        this.setData({
            showPassword: false,
        })
        console.log(objData.money);
        let money = objData.money;
        let password = objData.password;
        this.valueSix(money,password);
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        app.http.QueryAccWallent().then((res) => {
            this.setData({
                bankName: app.http.bankName,
                bankCard: app.http.bankCard.substr(app.http.bankCard.length - 4)
            })
        })
        this.setData({
            stateH: app.hei
        })
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
})