/**
 * Created by aaa on 2016/9/27.
 */


$(function(){

    $(".fu_submit").click(function(){
        $.ajax({
            url:"http://192.168.0.113:8080/web/serviceproviders/registerServiceProvidersAccount",
            type:"post",
            dataType: 'JSONP',
            jsonp: "callbackparam", //服务端用于接收callback调用的function名的参数
            jsonpCallback: "web", //callback的function名称,服务端会把名称和data一起传递回来
            data:{
                    // 账户名
                    accountName:$("#username").val(),
                    // 账户密码
                    password:$("#userpwd").val(),
                    // 二级密码
                    tradePassword:$("#password2").val(),
                    // 绑定手机号
                    accountPhone:$("#mobilenumber").val(),
                    // 推荐人code
                    referrerCode:"111",
                    // 姓名
                    serviceProviderName:$("#realname").val(),
                    // 所属行业
                    industry:$("#industrySpan").text(),
                    // 性别
                    sex:$("#sexSpan").text(),
                    // 出生日期
                    birthday:$("#birthday").val(),
                    // 邮箱地址
                    email:$("#email").val(),
                    // 地址
                    address:$("#provinceid-s").text()+$("#cityid-s").text()+$("#address").val(),
                    // 省级代码
                    province:$("#provinceid-s").text(),
                    // 市级代码
                    city:$("#cityid-s").text(),
                    // 证件资料
                    certificateFile:"img",
                    // 身份证号码
                    idCard:$("#idcard").val(),
                    // 开户行
                    bankName:$("#bankName").text(),
                    // 卡号
                    bankNum:$("#bankNumber").val(),
                    // 开户名称
                    bankAccountName:$("#acountname").val(),
                    // 默认设置选定
                    defaultChecked:"111"
            },
            success: function (data) {
                console.log(data)
            }
        })
    })














})
