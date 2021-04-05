// pages/logIn/login.js
var app = getApp();

Page({

    /**
     * 页面的初始数据
     */
    data: {
        userId: '',
        password: '',
        btn_loading: false
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        var conf = {
            title: '提示',
            content: '校园卡设有两个密码：“查询密码(登陆校园一卡通APP及无卡登陆圈存机时所使用)”和“交易密码(卡片交易操作所使用的密码，包括卡片的挂失、圈存转账充值、消费限额等场所使用)”，持卡人应妥善保管好密码。1.初始密码：校园卡校查询和交易初始密码是身份证号后6位数字（若身份证末位为字母，则字母用0代替。示例：如身份证号码为36062219830330203X，则初始密码是302030）。若持卡人的证件是护照、台胞证、港澳居民回乡证、军官证等，则为对应证件号后六位数字。为确保用户使用安全，收到校园卡后应尽快修改密码。',
            cancelText: '不再显示',
            confirmText: '确定',
            success(res) {
                if (res.cancel) {
                    wx.setStorage({
                        key: 'msgconfim',
                        data: '1',
                    })
                }
            }
        };
        wx.getStorage({
            key: 'msgconfim',
            success: function(res) {},
            fail(res) {
                wx.showModal(conf);
            }
        })

        var that = this;
        wx.getStorage({
            key: 'userId',
            success: function(res) {
                app.http.setUserId(res.data);
                that.setData({
                    userId: res.data
                })
            },
        });
        wx.getStorage({
            key: 'password',
            success: function(res) {
                that.setData({
                    password: res.data
                })
                wx.showLoading({
                    title: '登录中',
                });
                app.http.setPassword(res.data);
                app.http.Login().then((res) => {
                    let result = res.data;
                    wx.hideLoading();
                    if (result.code == undefined) {
                        wx.showModal({
                            title: '提示',
                            content: '网络错误',
                        });
                        app.http.Msg = "";

                    } else if (result.code == 1) {
                        app.http.setUsername(result.data.username);
                        app.http.setUserStatus(result.data.userStatus);
                        console.log(result.data.classesId);
                        wx.redirectTo({
                            url: '../index/index',
                        })

                    } else {
                        wx.showModal({
                            title: '提示',
                            content: app.http.Msg,
                        });
                        app.http.Msg = "";
                    }
                }).catch((error) => {
                    wx.showModal({
                        title: '提示',
                        content: error,
                    });
                    console.log(error);
                })
            },
        });




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
    submit: function(e) {
        wx.showLoading({
            title: '加载中',
        })
        var objData = e.detail.value;
        var that = this;
        if (objData.userId && objData.password) {
            that.setData({
                btn_loading: !that.data.btn_loading
            })
            wx.setStorage({
                key: 'userId',
                data: objData.userId,
            });
            wx.setStorage({
                key: 'password',
                data: objData.password,
            });
            app.http.setUserId(objData.userId);
            app.http.setPassword(objData.password);
            app.http.Login().then((res) => {
                let result = res.data;
                console.log(result);
                wx.hideLoading();
                if (result.code == undefined) {
                    wx.showModal({
                        title: '提示',
                        content: '网络错误',
                    });
                    app.http.Msg = "";

                } else if (result.code == 1) {
                    console.log(result.data);
                    app.http.setUsername(result.data.username);
                    app.http.setUserStatus(result.data.userStatus);
                    wx.redirectTo({
                        url: '../index/index',
                    })

                } else {
                    wx.showModal({
                        title: '提示',
                        content: '用户名或密码错误',
                    });
                    app.http.Msg = "";
                }
            }).catch((error) => {
                console.log(error);
            })
        } else {
            wx.hideLoading();
            wx.showModal({
                title: '错误',
                content: '请输入学号或密码',
            })
        }
    },

})