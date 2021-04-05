//index.js
//获取应用实例
const app = getApp()

Page({

    /**
     * 页面的初始数据
     */
    data: {
        btn_loading: false,
        restMoney: "",
        username: "",
        userStatus: "",
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        var that = this;
        // app.http.getmsg().then((res)=>{
        //     wx.showModal({
        //         title:"衡师服务小助手",
        //         content:res.data['msg']
        //     })
        // })
        //读取缓存
        var that = this;
        wx.getStorage({
            key: 'userId',
            success: function (res) {
                app.http.userId = res.data;
                app.http.QueryAccWallent().then((res) => {
                    console.log(res);
                    that.setData({
                        username: app.http.username,
                        restMoney: app.http.restMoney
                    })
                });
                app.http.QueryAccAuth();
            },
            fail: function () {
                that.setData({
                    username: "您未登录",
                    restMoney: "点击登录"
                });
            }
        });
        console.log(app.http);
        wx.getStorage({
            key: 'username',
            success: function (res) {
                app.http.username = res.data;
            },
        });
        wx.getStorage({
          key: 'userStatus',
          success: function(res){
                app.http.userStatus = res.data;
                console.log(res.data);
                that.setData({
                    userStatus: res.data,
                });
          },
        })

        // wx.getStorage({
        //     key: 'PerCode',
        //     success: function(res) {
        //         app.http.PerCode = res.data
        //     },
        // });
        wx.getStorage({
            key: 'CardID',
            success: function (res) {
                app.http.CardID = res.data;
            },
        });
        wx.getStorage({
          key: 'classId',
          success:function(res){
              app.http.classId = res.data;
          },
        })
        // wx.getStorage({
        //     key: 'CustomerID',
        //     success: function(res) {
        //         app.http.CustomerID = res.data
        //     },
        // });
        // wx.getStorage({
        //     key: 'AgentID',
        //     success: function(res) {
        //         app.http.AgentID = res.data
        //     },
        // });
        // wx.getStorage({
        //     key: 'LostDate',
        //     success: function(res) {
        //         app.http.LostDate = res.data
        //     },
        // });
        // wx.getStorage({
        //     key: 'IsDefault',
        //     success: function(res) {
        //         app.http.IsDefault = res.data
        //     },
        // });
        // wx.getStorage({
        //     key: 'UserNumber',
        //     success: function(res) {
        //         app.http.UserNumber = res.data
        //     },
        // })

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
        wx.showNavigationBarLoading(); //在标题栏中显示加载
        let that = this;
        if (that.data.username === "您未登录") {
            this.setData({
                username: that.data.username,
                restMoney: that.data.restMoney
            })
        } else {
            app.http.QueryAccWallent().then((res) => {
                this.setData({
                    username: app.http.username,
                    restMoney: app.http.restMoney
                })

            })
        }
        wx.hideNavigationBarLoading();
        wx.stopPullDownRefresh();
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
    Charge: function (e) {
        if (app.http.username == "") {
            wx.showModal({
                title: '请先登录',
                content: '要先登录才能使用校园卡充值功能哦',
                success(res) {
                    if (res.confirm) {
                        wx.redirectTo({
                            url: '../login/login',
                        })
                    }
                }
            });
            return;
        }
        wx.navigateTo({
            url: '../virtualCard/charge/charge',
        })
    },
    Bill: function (e) {
        if (app.http.username == "") {
            wx.showModal({
                title: '请先登录',
                content: '要先登录才能使用账单功能哦',
                success(res) {
                    if (res.confirm) {
                        wx.redirectTo({
                            url: '../login/login',
                        })
                    }
                }
            });
            return;
        }
        wx.navigateTo({
            url: '../virtualCard/bill/bill',
        })
    },
    AccessControl: function (e) {
        if (app.http.username == "") {
            wx.showModal({
                title: '请先登录',
                content: '要先登录才能使用开门密码功能哦',
                success(res) {
                    if (res.confirm) {
                        wx.redirectTo({
                            url: '../login/login',
                        })
                    }
                }
            });
            return;
        }
        wx.navigateTo({
            url: './AccessControl/AccessControl',
        })
    },
    ReportLoss: function (e) {
        if (app.http.username == "") {
            wx.showModal({
                title: '请先登录',
                content: '要先登录才能使用挂失功能哦',
                success(res) {
                    if (res.confirm) {
                        wx.redirectTo({
                            url: '../login/login',
                        })
                    }
                }
            });
            return;
        }
        wx.navigateTo({
            url: '../virtualCard/reportLoss/reportLoss',
        })
    },
    Attendance: function (e) {
        wx.navigateTo({
            url: '../profix/profix',
        })
    },
    Kbcj: function (e) {
        wx.navigateTo({
            url: '../education/index/index',
        })
    },
    login: function (e) {
        console.log("点了");
        wx.redirectTo({
            url: '../login/login',
        })
    },
    Exam: function (e) {
        wx.navigateTo({
            url: '../exam/exam',
        })
    },
    findyou: function (e) {
        wx.navigateTo({
            url: '../findYou/findYou',
        })
    },
    educationManager: function (e){
        wx.navigateTo({
          url: '../manager/educationManager/educationManager',
        })
    },
    userManager: function (e){
        wx.navigateTo({
          url: '../manager/userManager/userManager',
        })
    }
})