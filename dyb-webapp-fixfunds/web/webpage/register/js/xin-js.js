/**
 * Created by aaa on 2016/9/26.
 */
$(function(){
$(".xin_submit").click(function(){
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
        var  nativePlace=$("#provinceid-s").text()+$("#cityid-s").text();
            // 省级代码
        var  province=$("#provinceid-s").text();
            // 市级代码
        var  city=$("#cityid-s").text();
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
    console.log(result)
    console.log(param)



//    $.ajax({
//        url:"http://192.168.0.113:8080/web/member/registerMemberAccount",
//        type:"post",
//        dataType: 'JSONP',
//        jsonp: "callbackparam", //服务端用于接收callback调用的function名的参数
//        jsonpCallback: "web", //callback的function名称,服务端会把名称和data一起传递回来
//        data: {
//
//        },
//        success: function (data) {
//            console.log(data)
//        }
//    })
})






})