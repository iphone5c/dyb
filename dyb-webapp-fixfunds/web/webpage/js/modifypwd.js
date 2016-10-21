$(function(){
   // 修改密码
    // 非空
    var rgempty=/\S+/;
    // 长度6
    var regleg2=/.{6}$/;
    //　旧密码格式验证
    function oldpwds(){
        var oldpwd=$("#oldpwd").val();
        if(!rgempty.test(oldpwd)){
            $(".msg_error").eq(0).html("请填写!");
            return false;
        }else{
            $(".msg_error").eq(0).html("")
        }
    }
    //　新密码格式验证
    function newpwds(){
        var newpwd = $("#newpwd").val();
        if(!rgempty.test(newpwd)){
            $(".msg_error").eq(1).html("密码不能为空!");
            return false;
        }else if (regleg2.test(newpwd)){
            $(".msg_error").eq(1).html("密码长度不能小于6位!");
            return false;
        }else{
            $(".msg_error").eq(1).html("");
        }
    }
    // 确认密码格式验证
    function newpwdagain(){
        var newpwdagain = $("#newpwdagain").val();
        var newpwd = $("#newpwd").val();
        if(!rgempty.test(newpwdagain)){
            $(".msg_error").eq(2).html("密码不能为空!");
            return false;
        }else if (newpwdagain !=newpwd){
            $(".msg_error").eq(2).html("密码不一致!");
            return false;
        }else{
            $(".msg_error").eq(2).html("");
        }
    }
        $("#oldpwd").on("keyup blur change",function(){
            oldpwds()
        });
        $("#newpwd").on("keyup blur change",function(){
            newpwds()
        });
        $("#newpwdagain").on("keyup blur change",function(){
            newpwdagain()
        });

    $("#sureButton1").click(function(){
        var result=new Array();
        result[0] = oldpwds();
        result[1] = newpwds();
        result[2] = newpwdagain();
        for(var i=0;i<result.length;i++){
            if(result[i]==false){
                return false;
            }
        }
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
    });

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