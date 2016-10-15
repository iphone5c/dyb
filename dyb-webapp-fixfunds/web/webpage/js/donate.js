/**
 * Created by aaa on 2016/10/15.
 */
$(function(){
    $(".dropdown-toggle").eq(0).click(function(){
        $(".select1").toggleClass("open");
    });
    $(".sui-dropdown-menu1 li").click(function(){
        var i=$(".sui-dropdown-menu1 li").index(this);
        var x= $(".sui-dropdown-menu1 li a").eq(i).html();
        $(".sui-dropdown-menu1 li").removeClass("active");
        $(".sui-dropdown-menu1 li").eq(i).addClass("active");
        $(".select1 span span").html(x);
        $(".select1").removeClass("open");
    });
    $('.dropdown-inner').mouseleave(function(){
        $(".select1").removeClass("open");
    });



//    获取银行信息
    var param={};
    var result=invokeService('/web/commons/getFoundation',param);

    $("#bankName").text(result.result.bankName);
    $("#bankNum").text(result.result.bankNum);
//    获取信使豆类型
    var param={};
    var result=invokeService('/web/commons/getDonationType',param);
    $("#0").text(result.result[0].value);
    $("#1").text(result.result[1].value);
//提交捐赠豆和二级密码
    $("#submitForm").click(function(){
        var donationType=$("#size_s").text()
        var donationMessengerBean=$("#beannum").val()
        var tradePassword=$("#password").val()
        var param={
            donationMessengerBean:donationMessengerBean,
            donationType:donationType,
            tradePassword:tradePassword
        };
        var result=invokeService('/web/commons/getDonationType',param);
        if(result.statusCode!=1000){
            alert(result.errorMessage)
            return;
        }
        alert("捐赠成功")
    })







})