/**
 * Created by aaa on 2016/9/26.
 */

$(function(){
    getData();
//        下一步
//清除
    var err=$(".yz-error");
    $("#ad-content .input-xfat1").focus(function(){
        var a = $("#ad-content .input-xfat1").index(this);
        err.eq(a).text("")
    })
    $("#s_province").click(function(){
        err.eq(10).text("");
    })
    $("#s_city").click(function(){
        err.eq(10).text("");
    })
    $("#nextReg1").click(function () {
//        用户名
        var username = $("#username").val();
        if(username==""){
            err.eq(0).text("用户名不能为空")
        }else if(username.length<2){
            err.eq(0).text("用户名长度不能少于2")
        }
//      密码
        var userpwd = $("#userpwd").val();
        if(userpwd==""){
            err.eq(1).text("密码不能为空")
        }else if(userpwd.length<6){
            err.eq(1).text("密码长度不能少于6")
        }
//        确认密码
        var password = $("#password").val();
        if(password==""){
            err.eq(2).text("密码不能为空");
            return false;
        }else if(password.length<6){
            err.eq(2).text("密码长度不能少于6");
            return false;
        }else if(password!=userpwd){
            err.eq(2).text("两次密码不一致");
            return false;
        }
//        手机号
        var mobilenumber = $("#mobilenumber").val();
        if(mobilenumber==""){
            err.eq(3).text("手机号不能为空");
            return false;
        }
        else if(!(/0?(13|14|15|18)[0-9]{9}/.test(mobilenumber))){
            /0?(13|14|15|18)[0-9]{9}/
            err.eq(3).text("手机格式不正确");
            return false;
        }
//        图文验证码
        var imgCode = $("#imgCode").val();
        if(imgCode==""){
            err.eq(4).text("图文验证不能为空");
            return false;
        }else if(imgCode.length<4){
            err.eq(4).text("图文验证是长度是4");
            return false;
        }
//        手机验证码
        var verifyCode = $("#verifyCode").val();
        if(verifyCode==""){
            err.eq(5).text("验证码不能为空");
            return false;
        }
//        协议书
        if ($('#readProtocol').is(':checked') == false) {
            $(".yz-error").eq(6).html("请选择!");
            return false;
        }
        for (var i = 0; i < err.length; i++) {
            if (err[i] == false) {
                return;
            }
        }
        $(".ad-register1").css({"display": "none"});
        $(".ad-register3").css({"display": "none"});
        $(".ad-register2").css({"display": "block"})
    });
    $("#nextReg2").click(function () {

        //真是姓名
        var realname = $("#realname").val();
        if(realname==""){
            err.eq(7).text("真实姓名不能为空")
            return false;
        }
//        性别
        var sexSpan = $("#sexSpan").text();
        if(sexSpan=="请选择"){
            err.eq(8).text("请选择性别")
            return false;
        }
//        出生日期
        var birthday = $("#birthday").val();
        if(birthday==""){
            err.eq(9).text("出生日期不能为空")
            return false;
        }
        //        城市
        if($("#s_province").val()=="省份"||$("#s_city").val()=="地级市"){
            err.eq(10).text("请选择城市");
            return false;
        }
//      证件类型
        var idcardtypeSpan = $("#idcardtypeSpan").text();
        if(idcardtypeSpan=="请选择"){
            err.eq(11).text("请选择证件")
            return false;
        }
//        证件号码
        var idcard = $("#idcard").val();
        if(idcard==""){
            err.eq(12).text("证件号码不能为空")
            return false;
        }
//      行业
        var industrySpan = $("#industrySpan").text();
        if(industrySpan=="请选择"){
            err.eq(13).text("请选择行业")
            return false;
        }
//    邮箱
        var email = $("#email").val();
        var szReg=/^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+(\.[a-zA-Z]{2,3})+$/;
        if(email==""){
            err.eq(14).text("邮箱不能为空")
            return false;
        }else if(!szReg.test(email)){
            err.eq(14).text("邮箱格式不正确")
            return false;
        }
//        二级密码
        var password2 = $("#password2").val();
        if(password2==""){
            err.eq(15).text("密码不能为空")
            return false;
        }else if(password2.length<6){
            err.eq(15).text("密码长度不能少于6")
            return false;
        }
//        二级密码确认
        var pwd = $("#pwd").val();
        if(pwd==""){
            err.eq(16).text("密码不能为空");
            return false;
        }else if(pwd.length<6){
            err.eq(16).text("密码长度不能少于6");
            return false;
        }else if(pwd!=password2){
            err.eq(16).text("两次密码不一致");
            return false;
        }

        for (var i = 0; i < err.length; i++) {
            if (err[i] == false) {
                return;
            }
        }
        xin_submit();
    });
//        上一步
    $("#lastReg2").click(function () {
        $(".ad-register2").css({"display": "none"});
        $(".ad-register3").css({"display": "none"});
        $(".ad-register1").css({"display": "block"})
    });
    function getQueryString(referrer) {
        var reg = new RegExp("(^|&)" + referrer + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return decodeURI(r[2]); return null;
    }
//    getQueryString("referrer")
    if(getQueryString("referrer")==""){
        window.location.href='../publicModule/error404.html';
        return
    }
    var param={
        accountCode:getQueryString("referrer")
    };
    var data = invokeService('/web/commons/getAccountByCode',param);
    if (data.statusCode!=1000){
        window.location.href='../publicModule/error404.html';
        return;
    }
    if (data.statusCode==1000){
        $("#referrerName").html(data.result.name)
        $("#referrerMobile").html(data.result.phone)
        return;
    }

    function xin_submit(){
        // 账户名
        var accountName=$("#username").val();
        // 账户密码
        var password=$("#userpwd").val();
        // 二级密码
        var tradePassword=$("#password2").val();
        // 绑定手机号
        var accountPhone=$("#mobilenumber").val();
        // 推荐人code
        var tjrCode="111";
        // 真实姓名
        var realName=$("#realname").val();
        // 性别
        var sex="男";//$("#sexSpan").text();
        // 籍贯
        var  nativePlace=$("#s_province").val()+$("#s_city").val();
        // 省级代码
        var  province=$("#s_province").val();
        // 市级代码
        var  city=$("#s_city").val();
        // 证件类型
        var  certificate=$("#idcardtypeSpan").text();
        // 证件号码
        var  certificateNumber=$("#idcard").val();
        // 所在行业
        var   industry= "YL";//$("#industrySpan").text();
        // 生日
        var   birthday=$("#birthday").val();
        // 个人邮箱
        var   memberEmail=$("#email").val();
        // 默认设置选定
        var    defaultChecked="1111";
        var param={
            accountName:accountName,
            password:password,
            tradePassword:tradePassword,
            accountPhone:accountPhone,
            tjrCode:tjrCode,
            realName:realName,
            sex:sex,
            nativePlace:nativePlace,
            province:province,
            city:city,
            certificate:certificate,
            certificateNumber:certificateNumber,
            industry:industry,
            birthday:birthday,
            memberEmail:memberEmail,
            defaultChecked:defaultChecked
        };
        var result=invokeService('/web/member/registerMemberAccount',param);

        if (result.statusCode!=1000){
            alert(result.errorMessage);
            return;
        }

        $(".ad-register1").css({"display": "none"});
        $(".ad-register2").css({"display": "none"});
        $(".ad-register3").css({"display": "block"})

    }
})


