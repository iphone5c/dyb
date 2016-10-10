/**
 * Created by aaa on 2016/9/26.
 */
$(function(){
$(".xin_submit").click(function(){
    $.ajax({
        url:"http://192.168.0.113:8080/web/member/registerMemberAccount",
        type:"post",
        dataType: 'JSONP',
        jsonp: "callbackparam", //服务端用于接收callback调用的function名的参数
        jsonpCallback: "web", //callback的function名称,服务端会把名称和data一起传递回来
        data: {
            // 账户名
            accountName:$("#username").val(),
            // 账户密码
            password:$("#userpwd").val(),
            // 二级密码
            tradePassword:$("#password2").val(),
            // 绑定手机号
            accountPhone:$("#mobilenumber").val(),
            // 推荐人code
            tjrCode:"111",
            // 真实姓名
            realName:$("#realname").val(),
            // 性别
            sex:"男",//$("#sexSpan").text(),
            // 籍贯
            nativePlace:$("#provinceid-s").text()+$("#cityid-s").text(),
            // 省级代码
            province:$("#provinceid-s").text(),
            // 市级代码
            city:$("#cityid-s").text(),
            // 证件类型
            certificate:$("#idcardtypeSpan").text(),
            // 证件号码
            certificateNumber:$("#idcard").val(),
            // 所在行业
            industry: "YL",//$("#industrySpan").text(),
            // 生日
            birthday:$("#birthday").val(),
            // 个人邮箱
            memberEmail:$("#email").val(),
            // 默认设置选定
            defaultChecked:"1111"
        },
        success: function (data) {
            console.log(data)
        }
    })
})






})