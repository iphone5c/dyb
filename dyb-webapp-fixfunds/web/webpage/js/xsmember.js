// 信使登录信息
$(function(){
    var data = invokeService('/web/member/getCurrentMember',{});
//    console.log(data);
    if (data.statusCode!=1000){
        alert(data.errorMessage);
        return;
    }
    $("#username").html(data.result.member.accountName);
    $("#usernames").html(data.result.member.accountType);
    $("#usernumber").html(data.result.member.accountCode);
    $("#realname").html(data.result.member.member.realName);
    $("#sex").html(data.result.member.member.sex);
    $("#jiguan").html(data.result.member.member.nativePlace);
    $("#idcardtype").html(data.result.member.member.certificate);
    $("#idcard").html(data.result.member.member.certificateNumber);
    $("#mobilenumber").html(data.result.member.accountPhone);
    $("#industry").html(data.result.member.member.industry);
    $("#birthday").html(data.result.member.member.birthday);
    $("#email").html(data.result.member.member.memberEmail);
    $("#createtime").html(data.result.member.createTime);
    $("#referrer").html(data.result.tjrRealName);
    $("#referrerphone").html(data.result.tjrPhone);



    $(".btn-fontBlue").click(function(){
        $(".sui-modal").addClass("in");
        $(".sui-modal-backdrop").css("zIndex","1000").addClass("in");
    });
    $(".sui-close").click(function(){
        $(".sui-modal").removeClass("in");
        $(".sui-modal-backdrop").css("zIndex","-1").removeClass("in");
    })
    $(".ad-blue-btn").click(function(){
        getpage1("./Merchant-xs/modifymember.html");
    })
    function getpage1(url){
        $.get(url,function(data){
            $("#ad-content").html(data);
        })
    }
})
