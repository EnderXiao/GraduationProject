// pages/education/grade/grade.js

var hei = wx.getMenuButtonBoundingClientRect().top;
const app = getApp();
let videoAd = null

Page({

    /**
     * 页面的初始数据
     */
    data: {
        slideStyle: "slideInDown",
        stateH: hei,
        allData: null,
        memberList: null,
        pickerIndex: 0,
        schoolYear: null,
    },
    isOpen: function (e) {
        let idx = Number(e.currentTarget.dataset.index);
        let that = this;
        let list = that.data.memberList;
        list[idx].hidden = !list[idx].hidden;
        app.http.queryGradeById(that.data.memberList[idx]['gradeId']).then((res) => {
            let datas = res.data;
            list[idx].detail[1].value = datas['dailyGrade'];
            list[idx].detail[2].value = datas['examGrade'];
            that.setData({
                memberList: list
            })
        })
        this.setData({
            memberList: list
        });
        return true;
    },


    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        let that = this
        this.setData({
            stateH: app.hei
        })
        wx.getStorage({
          key: 'classId',
          success: function(res){
            app.http.QueryAccInfo(res.data).then((res) => {
                let inYear = res.data.schoolYear;
                let date = new Date();
                let nowYear = date.getFullYear();
                let list = new Array()
                if(nowYear - inYear > 4){
                    for (var i = 0; i < 4; ++i) {
                        var str = (inYear + i).toString() + '-' + (inYear + i + 1).toString() + '-2';
                        var str2 = (inYear + i).toString() + '-' + (inYear + i + 1).toString() + '-1';
                        list.push(str2);
                        list.push(str);
                    }
                }
                for (var i = inYear; i < nowYear; ++i) {
                    let str = (i).toString() + '-' + (i + 1).toString() + '-2';
                    let str2 = (i).toString() + '-' + (i + 1).toString() + '-1';
                    list.push(str2);
                    list.push(str);
                }
                that.setData({
                    schoolYear: list,
                    showloading: true
                });
                let temp = that.getAllGrade(list[that.data.pickerIndex]);
            })
          }
        })
    },
    getAllGrade: function (str) {
        var that = this;
        app.http.QueryGradeByTime(str).then((res) => {
            let result = res.data;
            let list = that.dealData(result);
            that.setData({
                allData: result,
                memberList: list,
                showloading: false
            })
        })
    },
    pickerChange: function (e) {
        this.setData({
            pickerIndex: e.detail.value,
            showloading: true
        })
        let list = this.data.schoolYear;
        this.getAllGrade(list[e.detail.value]);
    },
    dealData: function (data) {
        let list = Array();
        for (let i in data) {
            let conf = {
                cont: data[i]['gradeName'],
                gradeTotal: data[i]['gradeSum'],
                hidden: true,
                id: i,
                gradeId: data[i]['gradeId'],
                detail: [{
                        name: "课程名：",
                        value: data[i]['gradeName']
                    },
                    {
                        name: "平时成绩：",
                        value: "加载中",
                    },
                    {
                        name: "考试成绩：",
                        value: "加载中",
                    },
                    {
                        name: "学分：",
                        value: data[i]['classCredit'],
                    },
                    {
                        name: "课程类型：",
                        value: data[i]['examType'],
                    },
                    {
                        name: "类型：",
                        value: data[i]['classType']
                    }
                ]
            }
            list.push(conf);
        }
        return list;
    },
    adCreate: function () {
        var that = this;

        if (wx.createRewardedVideoAd) {
            videoAd = wx.createRewardedVideoAd({
                adUnitId: 'adunit-f1cbccc279268640'
            })
            videoAd.onLoad(() => {
                console.log("正在准备ad")
            })
            videoAd.onError((err) => {
                console.log(err);
                wx.showToast({
                    icon: "none",
                    title: '广告拉取失败',
                })
            })
            videoAd.onClose((res) => {
                console.log(res);
                if (res && res.isEnded) {
                    app.http.ViewAd();
                } else {
                    // 播放中途退出，不下发游戏奖励
                    wx.showModal({
                        title: '好吧',
                        content: '看广告才能查排名哦，还有GPA，难道你不想知道自己考的怎么样吗🤭',
                    })
                }
            })
        }
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
     * 排名按钮点击事件
     */
    rankTap: function () {
        var that = this;
        wx.getStorage({
            key: 'Jwnanyue',
            success: function (res) {
                console.log(res.data);
                var time;
                var index = that.data.pickerIndex;
                time = that.data.schoolYear[index];
                wx.navigateTo({
                    url: './pm/pm?time=' + time,
                })
            },
        })

    },
    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    },
})