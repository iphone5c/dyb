$(function(){
   // 修改密码
    $("#sureButton1").click(function(){
        var param={
            oldPassword:$("#oldpwd").val(),
            newPassword:$("#newpwd").val(),
            confirmPassword:$("#newpwdagain").val()
        };
        var data = invokeService('/web/account/modifyPasswordByCurrentAccount',param);
        if (data.statusCode!=1000){
            $(".sui-modal1").addClass("in");
            $(".sui-modal-backdrop").css("zIndex","1000").addClass("in");
            return;
        }
        if(data.statusCode==1000){
            $(".sui-modal2").addClass("in");
            $(".sui-modal-backdrop").css("zIndex","1000").addClass("in");
        }
    })

    $(".sui-close1,.sui-btn1").click(function(){
        $(".sui-modal").removeClass("in");
        $(".sui-modal-backdrop").css("zIndex","-1").removeClass("in");
    });
    $(".sui-close2,.sui-btn2").click(function(){
        $(".sui-moda2").removeClass("in");
        $(".sui-modal-backdrop").css("zIndex","-1").removeClass("in");
        var data = invokeService('/web/commons/signOut',{});
        if (data.statusCode!=1000){
            alert(data.errorMessage);
            return;
        }
        if (data.statusCode==1000){
            window.location.href='./home/login.html';
        }
    });

    $("#cancelButton1").click(function(){
        window.location.reload();
    })
    $(".sui-nav li").click(function(){
        var i=$(".sui-nav li").index(this)
        $(".sui-nav li").removeClass("active");
        $(".sui-nav li").eq(i).addClass("active");
        $(".tab-pane").removeClass("active");
        $(".tab-pane").eq(i).addClass("active");
    })
})