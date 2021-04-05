// pages/virtualCard/bill/bill.js
const app = getApp();
var pages = 1;

var date = new Date();
var currenttime_year = date.getFullYear();
var currenttime_month = date.getMonth() + 1;
var currenttime_day = date.getDate();

Page({

    /**
     * 页面的初始数据
     */
    data: {
        stateH: app.hei,
        currentDate: null,
        pickerDay: null,
        dayIndex: 0,
        swiperItem: [{
            id: 1,
            year: currenttime_year,
            month: currenttime_month,
            over: 0,
            table: null,
            dayList: null,
        }, {
            id: 2,
            year: currenttime_month == 1 ? currenttime_year - 1 : currenttime_year,
            month: currenttime_month == 1 ? 12 : currenttime_month - 1,
            over: 0,
            table: null,
            dayList: null,
        }],
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        let that = this;
        this.setData({
            stateH: app.hei
        });
        let swiperItem = that.data.swiperItem;
        for (let i = 0; i < swiperItem.length; i++) {
            let dayList = new Array();
            let times = that.createDate(i);
            for (let j = 1; j <= times.endTime; j++) {
                dayList[j - 1] = j;
            }
            swiperItem[i].dayList = dayList;
        }
        this.setData({
            swiperItem: swiperItem,
        })
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {
        this.createNewSwiper({
            detail: {
                current: 0
            }
        });

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
    pickerDayChange: function (e) {
        this.setData({
            dayIndex: e.detail.value
        });
        let that = this;
        let nowUse = that.data.currentItem;
        let swiperItem = this.data.swiperItem;

        //加载当前标签
        let year = swiperItem[nowUse].year;
        let month = swiperItem[nowUse].month;
        let day = that.data.pickerDay[that.data.dayIndex];
        let time = that.dateFormate(year, month, day);
        console.log(time);
        app.http.QueryDealRec(time).then((res) => {
            let result = res.data;
            if (result.code == "1" && result.data.length != 0) {
                swiperItem[nowUse].over = 0;
                swiperItem[nowUse].table = that.dealdate(result.data);
                that.setData({
                    swiperItem: swiperItem
                });
            } else {
                swiperItem[nowUse].table = null;
                swiperItem[nowUse].over = 1;
                that.setData({
                    swiperItem: swiperItem
                })
            }
        });
    },
    //创建开始结束时间
    createDate: function (id) {
        let day = 0;
        if (id != 0) {
            let that = this;
            let swiperItem = that.data.swiperItem;
            let year = swiperItem[id].year;
            let month = swiperItem[id].month;
            day = new Date(year, month, 0).getDate();
            // let beginTime = year.toString() + month + "01";
            // let endTime = year.toString() + month + day;
        } else day = currenttime_day;
        let result = {
            "beginTime": 1,
            "endTime": day
        };
        // console.log(result);
        return result;
    },
    dateFormate: function (year, month, day) {
        if (month <= 9) month = "0" + month;
        else month = month.toString();
        if (day <= 9) day = "0" + day;
        else day = day.toString();
        let date = year + month + day;
        return date;
    },
    //监听页面切换,生成新的标签和加载数据
    createNewSwiper: function (e) {

        let that = this;
        let nowUse = e.detail.current;
        //设置当前item_id
        this.setData({
            currentItem: nowUse
        });
        let swiperItem = this.data.swiperItem;
        let lens = swiperItem.length;
        this.setData({
            pickerDay: swiperItem[nowUse].dayList,
            dayIndex: 0
        })
        //加入新的标签
        if (nowUse + 1 == lens && swiperItem[lens - 1].id <= 12) {
            console.log(swiperItem[lens - 1].year);
            let idNow = swiperItem[lens - 1].id + 1
            let yearNow = swiperItem[lens - 1].year - (swiperItem[lens - 1].month == 1 ? 1 : 0);
            let monthNow = (swiperItem[lens - 1].month - 1) == 0 ? 12 : (swiperItem[lens - 1].month - 1);
            swiperItem.push({
                id: idNow,
                year: yearNow,
                month: monthNow,
                over: 0,
                table: null,
                dayList: null,
            })
            console.log(swiperItem[idNow - 1]);
            let times = that.createDate(idNow - 1);
            let dayListNow = new Array();
            for (let i = 1; i <= times.endTime; i++) {
                dayListNow[i - 1] = i;
            }
            swiperItem[idNow - 1].dayList = dayListNow;
            that.setData({
                swiperItem: swiperItem
            })
        }

        //加载当前标签
        let year = swiperItem[nowUse].year;
        let month = swiperItem[nowUse].month;
        let day = that.data.pickerDay[that.data.dayIndex];
        let time = that.dateFormate(year, month, day);
        console.log(time);
        app.http.QueryDealRec(time).then((res) => {
            let result = res.data;
            if (result.code == "1" && result.data.length != 0) {
                swiperItem[nowUse].over = 0;
                swiperItem[nowUse].table = that.dealdate(result.data);
                that.setData({
                    swiperItem: swiperItem
                });
            } else {
                swiperItem[nowUse].table = null;
                swiperItem[nowUse].over = 1;
                that.setData({
                    swiperItem: swiperItem
                })
            }
        })

    },
    //处理日期
    dealdate: function (row) {
        console.log(row);
        if (row.length == undefined)
            row = [row]
        for (let i = 0; i < row.length; ++i) {
            let date = row[i].cspDate;
            let time = row[i].cspTime;
            let money = row[i].cspMoney;
            console.log(date);
            console.log(time);
            date = date.split(" ");
            time = time.split(" ");
            let lens = time.length;
            time = time[lens - 1];
            date = date[0];
            date = date.split("-");
            lens = date.length;
            date = date[lens - 1];
            row[i].cspDate = date;
            row[i].cspTime = time;
            row[i].cspMoney = money.toFixed(2);
        }
        console.log(row);
        return row;
    },
    paymsg: function (e) {
        var that = this;
        var nowUse = Number(e.currentTarget.id);
        var data = that.data.swiperItem[that.data.currentItem].table[nowUse];
        wx.navigateTo({
            url: './paymsg/paymsg?FeeName=' + data.FeeName + '&Time=' + data.Time + '&MonDeal=' + data.MonDeal + '&MonCard=' + data.MonCard + '&Source=' + data.Source,
        })
    }
})