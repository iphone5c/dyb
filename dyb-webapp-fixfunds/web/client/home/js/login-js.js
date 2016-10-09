/**
 * Created by aaa on 2016/9/27.
 */

$(function () {
    $("#loginUp").click(function () {
        var username=$("#username").val();
        var password=$("#userpwd").val();
        var accountType=$("#usertypeSpan").text();

        var param={
            loginName:username,
            password:password,
            accountType:accountType
        };
        var result = invokeService('/web/commons/loginAccount',param);

        if(result.statusCode!=1000){
            alert(result.errorMessage);
            return;
        }
        document.location = "../index.html?name=" + $("#usertypeSpan").text() + "";
    })
});