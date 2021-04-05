// pages/education/schedule/schedule.js

var hei = wx.getMenuButtonBoundingClientRect().top;
var weeks = [7, 1, 2, 3, 4, 5, 6];
var date = new Date();
var currentYear = date.getFullYear();
var month = [];
for (var i = 0; i < 12; i++) {
    var temp = new Date(currentYear, i + 1, 0);
    month[i] = temp.getDate();
}
var currentMonth = date.getMonth() + 1;
var currentDay = date.getDate();
var currentWeek = weeks[date.getUTCDay()];
var week = [];
for (var i = 0; i < currentWeek; i++) {
    week[i] = currentDay - currentWeek + i + 1;
    if (week[i] <= 0)
        week[i] = week[i] + month[(currentMonth - 2 >= 0) ? (currentMonth - 2) : (12 + (currentMonth - 2))];
}
for (var i = currentWeek, j = 1; i < 7; i++, j++) {
    week[i] = currentDay + j;
    if (week[i] > month[currentMonth - 1])
        week[i] = week[i] - month[currentMonth - 1];
}

const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        inputMonth: "",
        inputDay: "",
        Date: "",
        hiddenmodalput: true,
        hiddenModalDetail: true,
        detail: null,
        stateH: hei,
        year: currentYear,
        month: currentMonth,
        startMonth: 0,
        startDay: 0,
        day: currentDay,
        schoolWeek: null,
        weekIndex: 0,
        classArray: ["1//2", "3//4", "5//6", "7//8", "9//10"],
        dayArray: [{
            index: 1,
            day: week[0]
        }, {
            index: 2,
            day: week[1]
        }, {
            index: 3,
            day: week[2]
        }, {
            index: 4,
            day: week[3]
        }, {
            index: 5,
            day: week[4]
        }, {
            index: 6,
            day: week[5]
        }, {
            index: 7,
            day: week[6]
        }],
        listData: null,
        cout: 0
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        //this.getWeek();
        this.setData({
            stateH: app.hei
        })
        let that = this;
        wx.showLoading({
            title: '加载中',
        })
        let weeklist = Array();
        for (let i = 1; i <= 25; ++i)
            weeklist.push(i.toString());
        this.setData({
            schoolWeek: weeklist
        })
        let thisWeek = that.getWeek();
        that.setData({
            weekIndex: thisWeek,
        })
        this.getData(weeklist[thisWeek]);
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
    getWeek: function () {
        var that = this
        try {
            let myDay = wx.getStorageSync('startDay');
            that.setData({
                startDay: myDay,
            })
        } catch (e) {
            console.log("获取开始日期失败");
        }
        try {
            let myMonth = wx.getStorageSync('startMonth');
            that.setData({
                startMonth: myMonth,
            })
        } catch (e) {
            console.log("获取开始日期失败");
        }
        var myMonth = that.data.startMonth;
        var myDay = that.data.startDay;
        if (myDay == 0 || myMonth == 0) {
            return 0;
        }
        var curMon = that.data.month;
        var curday = that.data.day;
        var disDays = 0;
        for (var i = myMonth - 1; i < curMon - 1; i++) {
            disDays += month[i];
        }
        disDays = disDays - myDay + curday;
        var thisWeek = Math.floor(disDays / 7) + 1;
        return thisWeek;
    },
    getData: function (week) {
        console.log(week);
        var that = this;
        if (week == "0") week = '';
        app.http.findSchedule(week).then((res) => {
            wx.hideLoading();
            let data = res.data;
            var listData = that.dealData(data);
            that.setData({
                listData: listData,
            })
        }, (error) => {
            wx.hideLoading();
            wx.showModal({
                title: '错误',
                content: '网络错误，请重新登录',
                // success() {
                //     wx.navigateTo({
                //         url: '../../kbcj',
                //     })
                // }
            })
        })
    },
    pickerChange: function (e) {
        let that = this;
        this.setData({
            weekIndex: e.detail.value,
        })
        wx.showLoading({
            title: '加载中',
        })
        this.getData(parseInt(e.detail.value) + 1);
    },
    dealData: function (data) {
        console.log(data);
        return data
    },
    myMonth: function (e) {
        var that = this;
        that.setData({
            inputMonth: e.detail.value,
        })
    },
    myDay: function (e) {
        var that = this;
        that.setData({
            inputDay: e.detail.value,
        })
    },
    refresh: function (e) {
        var that = this;
        let thisWeek = that.getWeek();
        that.setData({
            weekIndex: thisWeek,
        })
        that.getData(thisWeek);
    },
    setting: function (e) {
        var that = this;
        that.setData({
            hiddenmodalput: false,
        })
    },
    //取消按钮
    cancel: function () {
        this.setData({
            hiddenmodalput: true,
        });
    },
    //确认  
    confirm: function (e) {
        var that = this;
        var myMonth = parseInt(that.data.inputMonth);
        var myDay = parseInt(that.data.inputDay);
        if (myMonth > 12 || myDay > 31) {
            wx.showToast({
                title: '请输入正确日期',
                icon: 'none',
                duration: 2000
            })
            return;
        }
        wx.showToast({
            title: '成功',
            icon: 'success',
            duration: 2000
        })
        try {
            wx.setStorageSync('startMonth', myMonth);
        } catch (e) {
            console.log("保存开始日期失败");
        }
        try {
            wx.setStorageSync('startDay', myDay);
        } catch (e) {
            console.log("保存开始日期失败");
        }
        let thisWeek = that.getWeek();
        that.setData({
            hiddenmodalput: true,
            weekIndex: thisWeek,
        })
        that.getData(parseInt(thisWeek) + 1);
    },
    showDetail: function (e) {
        let that = this;
        let nowuse = e.currentTarget.id;
        console.log(nowuse);
        nowuse = nowuse.split("-");
        let courseNo = nowuse[0];
        let dayNo = nowuse[1];
        let data = that.data.listData;
        that.setData({
            hiddenModalDetail:false,
            detail: data[courseNo][dayNo]
        });
    },
    detailConfirm:function(){
        let that = this;
        that.setData({
            hiddenModalDetail: true,
        })
    }
})