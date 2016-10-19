$(function(){
    $("#modifypicture").click(function(){
        $(".sui-modal").addClass("in");
        $(".sui-modal-backdrop").css("zIndex","1").addClass("in");
    });
    $(".sui-close").click(function(){
        $(".sui-modal").removeClass("in");
        $(".sui-modal-backdrop").css("zIndex","-1").removeClass("in");
    })
    $(".dropdown-toggle").eq(0).click(function(){
        $(".select1").toggleClass("open");
    });
    $(".sui-dropdown-menu1").on("click","li",function(){
        var i=$(".sui-dropdown-menu1 li").index(this);
        var x= $(".sui-dropdown-menu1 li a").eq(i).html();
        $(".sui-dropdown-menu1 li").removeClass("active");
        $(".sui-dropdown-menu1 li").eq(i).addClass("active");
        $(".select1 span span").html(x);
        $(".select1").removeClass("open");
    });
    $(".dropdown-toggle").eq(1).click(function(){
        $(".select4").toggleClass("open");
    });
    $(".sui-dropdown-menu4").on("click","li",function(){
        var i=$(".sui-dropdown-menu4 li").index(this);
        var x= $(".sui-dropdown-menu4 li a").eq(i).html();
        $(".sui-dropdown-menu4 li").removeClass("active");
        $(".sui-dropdown-menu4 li").eq(i).addClass("active");
        $(".select4 span span").html(x);
        $(".select4").removeClass("open");
    });
    $('.dropdown-inner').mouseleave(function(){
        $(".select1").removeClass("open");
        $(".select2").removeClass("open");
    });
    // 获取下拉框数据
    getData();
    var data = invokeService('/web/member/getCurrentMember',{});
    console.log(data);
    if (data.statusCode!=1000){
        alert(data.errorMessage);
        return;
    }
    $("#realname").html(data.result.member.member.realName);
    $("#edit_sexSpan").html(data.result.member.member.sex);
    $("#s_province option:selected").text(data.result.member.member.province);
    $("#s_city option:selected").text(data.result.member.member.city);
    $("#birthday").text(data.result.member.member.birthday);
    $("#idcard").text(data.result.member.member.certificateNumber);
    $("#email").text(data.result.member.member.memberEmail);
    $("#edit_businessSpan").text(data.result.member.member.industry);
    $("#modify").click(function(){

    })
})
