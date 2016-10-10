$(function(){
    var rgempty=/^\S+$/;
    function empty(idText,html,num){
        if(rgempty.test(idText)){
            $(".yz-error").eq(num).html("");
        }else {
            $(".yz-error").eq(num).html(html);
            return false;
        }
    }
    $("#nextReg1").click(function(){
        var result=new Array();
        //console.log(result.length)
        var username=$("#username").val();
        result[0] = empty(username,"用户名不能为空!",0);
        var userpwd=$("#userpwd").val();
        result[1] = empty(userpwd,"密码不能为空!",1);
        var password=$("#password").val();
        result[2] = empty(password,"密码不能为空!",2);
        var mobilenumber=$("#mobilenumber").val();
        result[3] = empty(mobilenumber,"手机号不能为空!",3);
        var imgCode=$("#imgCode").val();
        result[4] = empty(imgCode,"图文验证不能为空!",4);
        var verifyCode=$("#verifyCode").val();
        result[5] = empty(verifyCode,"图文验证不能为空!",5);
        if($('#readProtocol').is(':checked')==true){
            $(".yz-error").eq(6).html("");
        }else{
            $(".yz-error").eq(6).html("请选择!");
            return false;
        }
        //$(".yz-error").eq(0).html("用户名不能为空!");
        //if(rgempty.test(username)){
        //    $(".yz-error").eq(0).html("");
        //}else {
        //    $(".yz-error").eq(0).html("用户名不能为空!");
        //    return false;
        //}
        //var userpwd=$("#userpwd").val();
        //if(rgempty.test(userpwd)){
        //    $(".yz-error").eq(1).html("");
        //}else {
        //    $(".yz-error").eq(1).html("密码不能为空!");
        //    return false;
        //}
        //var password=$("#password").val();
        //if(rgempty.test(password)){
        //    $(".yz-error").eq(2).html("");
        //}else {
        //    $(".yz-error").eq(2).html("密码不能为空!");
        //    return false;
        //}
        //var mobilenumber=$("#mobilenumber").val();
        //if(rgempty.test(mobilenumber)){
        //    $(".yz-error").eq(3).html("");
        //}else {
        //    $(".yz-error").eq(3).html("手机号不能为空!");
        //    return false;
        //}
        //var imgCode=$("#imgCode").val();
        //if(rgempty.test(imgCode)){
        //    $(".yz-error").eq(4).html("");
        //}else {
        //    $(".yz-error").eq(4).html("图文验证码不能为空!");
        //    return false;
        //}
        //var verifyCode=$("#verifyCode").val();
        //if(rgempty.test(verifyCode)){
        //    $(".yz-error").eq(5).html("");
        //}else {
        //    $(".yz-error").eq(5).html("手机验证码不能为空!");
        //    return false;
        //}
        //if($('#readProtocol').is(':checked')==true){
        //    $(".yz-error").eq(6).html("");
        //}else{
        //    $(".yz-error").eq(6).html("请选择!");
        //    return false;
        //}
        for(var i=0;i<result.length;i++){
            if(result[i]==false){
               return false;
            }
        }
        $(".ad-register1").css({"display":"none"});
        $(".ad-register3").css({"display":"none"});
        $(".ad-register4").css({"display":"none"});
        $(".ad-register5").css({"display":"none"});
        $(".ad-register2").css({"display":"block"});
    })
})
