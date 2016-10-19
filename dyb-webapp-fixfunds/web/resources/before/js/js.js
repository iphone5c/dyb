/**
 * Created by aaa on 2016/9/30.
 */
$(function(){
    //充值提现 切换
    var a =$(".left ul li");
    a.eq(0).click(function(){
        a.eq(0).addClass("active");
        a.eq(1).removeClass("active");
        $(".right2").hide();
        $(".right").show();
        $(".left").css({"height":"1499px"})
        b.eq(0).addClass("active");
        b.eq(1).removeClass("active");
        b.eq(2).addClass("active");
        b.eq(3).removeClass("active");
        $(".img2").hide();
        $(".img1").show();
    });
    a.eq(1).click(function(){
        a.eq(1).addClass("active");
        a.eq(0).removeClass("active");
        $(".right").hide();
        $(".right2").show();
        $(".left").css({"height":"1347px"})
    });

    //充值提现角标
    var b =$(".xg_input_in");
    b.eq(0).click(function(){
        b.eq(0).addClass("active");
        b.eq(1).removeClass("active");
        b.eq(2).addClass("active");
        b.eq(3).removeClass("active");
        $(".img2").hide();
        $(".img1").show();
        $("#tradeType").val("微信");
    });
    b.eq(1).click(function(){
        b.eq(0).removeClass("active");
        b.eq(1).addClass("active");
        b.eq(2).addClass("active");
        b.eq(3).removeClass("active")
        $(".img1").hide();
        $(".img2").show();
        $("#tradeType").val("支付宝");
    });
    b.eq(2).click(function(){
        b.eq(0).addClass("active");
        b.eq(1).removeClass("active");
        b.eq(2).addClass("active");
        b.eq(3).removeClass("active")
    });
    b.eq(3).click(function(){
        b.eq(0).addClass("active");
        b.eq(1).removeClass("active");
        b.eq(2).removeClass("active");
        b.eq(3).addClass("active")
    });
    //竞猜记录

    //记录查询切换


    $(".record_ul>li").click(function(){
        var c = $(".record_ul>li").index(this);
        $(".record_ul>li").removeClass("active");
        $(".record_ul>li").eq(c).addClass("active");
    })



});