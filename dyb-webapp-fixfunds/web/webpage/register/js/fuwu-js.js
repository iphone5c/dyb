/**
 * Created by aaa on 2016/9/27.
 */


$(function(){

        //        下一步
//清除
        var err=$(".yz-error");
        $("#ad-content .input-xfat1").focus(function(){
            var a = $("#ad-content .input-xfat1").index(this);
            err.eq(a).text("")
        });
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
                    return false;
                }
            }
        $(".ad-register1").css({"display":"none"});
        $(".ad-register3").css({"display":"none"});
        $(".ad-register2").css({"display":"block"})
    });
        $("#nextReg2").click(function(){
//            联合群组号
            var groupno = $("#groupno").val();
            if(groupno==""){
                err.eq(7).text("联合群组号不能为空");
                return false;
            }
            //真是姓名
            var realname = $("#realname").val();
            if(realname==""){
                err.eq(8).text("真实姓名不能为空")
                return false;
            }
//        性别
            var sexSpan = $("#sexSpan").text();
            if(sexSpan=="请选择"){
                err.eq(9).text("请选择性别")
                return false;
            }
//        出生日期
            var birthday = $("#birthday").val();
            if(birthday==""){
                err.eq(10).text("出生日期不能为空")
                return false;
            }
//      行业
            var industrySpan = $("#industrySpan").text();
            if(industrySpan=="请选择"){
                err.eq(11).text("请选择行业")
                return false;
            }
            //        证件号码
            var idcard = $("#idcard").val();
            if(idcard==""){
                err.eq(12).text("证件号码不能为空")
                return false;
            }
//    邮箱
            var email = $("#email").val();
            var szReg=/^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+(\.[a-zA-Z]{2,3})+$/;
            if(email==""){
                err.eq(13).text("邮箱不能为空")
                return false;
            }else if(!szReg.test(email)){
                err.eq(13).text("邮箱格式不正确")
                return false;
            }
            //        城市
            if($("#provinceid-s").text()=="省份"||$("#cityid-s").text()=="城市"){
                err.eq(14).text("请选择城市");
                return false;
            }
            $("#cityid-s").click(function(){
                err.eq(14).text("123");
            })
//            详细地址
            var address=$("#address").val()
            if(address==""){
                err.eq(15).text("详细地址不能为空")
            }
            //            开户行
            var bankName=$("#bankName").text()
            if(bankName=="请选择开户行"){
                err.eq(16).text("请选择开户行")
            }
            //            开户名
            var acountname=$("#acountname").val()
            if(acountname==""){
                err.eq(17).text("开户名不能为空")
            }
            //            银行账号
            var bankNumber=$("#bankNumber").val()
            if(bankNumber==""){
                err.eq(18).text("银行卡号不能为空")
            }
//            二级密码
            var password2 = $("#password2").val();
            if(password2==""){
                err.eq(19).text("密码不能为空")
                return false;
            }else if(password2.length<6){
                err.eq(19).text("密码长度不能少于6")
                return false;
            }
//        二级密码确认
            var pwd = $("#pwd").val();
            if(pwd==""){
                err.eq(20).text("密码不能为空");
                return false;
            }else if(pwd.length<6){
                err.eq(20).text("密码长度不能少于6");
                return false;
            }else if(pwd!=password2){
                err.eq(20).text("两次密码不一致");
                return false;
            }
            for (var i = 0; i < err.length; i++) {
                if (err[i] == false) {
                    return false;
                }
            }

                // 账户名
                var accountName=$("#username").val();
                // 账户密码
                var password=$("#userpwd").val();
                // 二级密码
                var tradePassword=$("#password2").val();
                // 绑定手机号
                var accountPhone=$("#mobilenumber").val();
                // 推荐人code
                var referrerCode="111";
                // 姓名
                var serviceProviderName=$("#realname").val();
                // 所属行业
                varindustry=$("#industrySpan").text();
                // 性别
                var sex=$("#sexSpan").text();
                // 出生日期
                var birthday=$("#birthday").val();
                // 邮箱地址
                var email=$("#email").val();
                // 地址
                var address=$("#provinceid-s").text()+$("#cityid-s").text()+$("#address").val();
                // 省级代码
                var province=$("#provinceid-s").text();
                // 市级代码
                var city=$("#cityid-s").text();
                // 证件资料
                var certificateFile="img";
                // 身份证号码
                var idCard=$("#idcard").val();
                // 开户行
                var bankName=$("#bankName").text();
                // 卡号
                var bankNum=$("#bankNumber").val();
                // 开户名称
                var bankAccountName=$("#acountname").val();
                // 默认设置选定
                var defaultChecked="111";
                var param={
                    // 账户名
                    accountName:accountName,
                    // 账户密码
                    password:password,
                    // 二级密码
                    tradePassword:tradePassword,
                    // 绑定手机号
                    accountPhone:accountPhone,
                    // 推荐人code
                    referrerCode:referrerCode,
                    // 姓名
                    serviceProviderName:serviceProviderName,
                    // 所属行业
                    industry:industry,
                    // 性别
                    sex:sex,
                    // 出生日期
                    birthday:birthday,
                    // 邮箱地址
                    email:email,
                    // 地址
                    address:address,
                    // 省级代码
                    province:province,
                    // 市级代码
                    city:city,
                    // 证件资料
                    certificateFile:certificateFile,
                    // 身份证号码
                    idCard:idCard,
                    // 开户行
                    bankName:bankName,
                    // 卡号
                    bankNum:bankNum,
                    // 开户名称
                    bankAccountName:bankAccountName,
                    // 默认设置选定
                    defaultChecked:defaultChecked
                };
                var result=invokeService('/web/serviceproviders/registerServiceProvidersAccount',param)
                if (result.statusCode!=1000){
                    alert(result.errorMessage);
                    return;
                }
                $(".ad-register1").css({"display":"none"});
                $(".ad-register2").css({"display":"none"});
                $(".ad-register3").css({"display":"block"})

    });
})
