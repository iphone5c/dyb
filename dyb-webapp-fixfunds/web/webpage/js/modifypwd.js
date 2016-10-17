$(function(){
   // 修改密码

    var param={
        oldPassword:$("#oldpwd").val(),
        newPassword:$("#newpwd").val(),
        confirmPassword:$("#").val()
    };
    var data = invokeService('/web/account/getWebMenu',param);
    var fhurl=new Array();
    var addi=0;
    if (data.statusCode!=1000){
        alert(data.errorMessage);
        return;
    }



    $(".sui-nav li").click(function(){
        var i=$(".sui-nav li").index(this)
        $(".sui-nav li").removeClass("active");
        $(".sui-nav li").eq(i).addClass("active");
        $(".tab-pane").removeClass("active");
        $(".tab-pane").eq(i).addClass("active");
    })
})