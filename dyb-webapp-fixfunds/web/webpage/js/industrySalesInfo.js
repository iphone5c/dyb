/**
 * Created by aaa on 2016/10/11.
 */
$(function(){
    $(".dropdown-toggle").eq(0).click(function(){
        $(".select1").toggleClass("open");
    })
    $(".sui-dropdown-menu1 li").click(function(){
        var i=$(".sui-dropdown-menu1 li").index(this);
        var x= $(".sui-dropdown-menu1 li a").eq(i).html();
        $(".sui-dropdown-menu1 li").removeClass("active");
        $(".sui-dropdown-menu1 li").eq(i).addClass("active");
        $(".select1 span span").html(x);
        $(".select1").removeClass("open");
    })
    $('.dropdown-inner').mouseleave(function(){
        $(".select1").removeClass("open");
    });
    $(".sui-dropdown-menu1 li").click(function(){
        $("#datePicker").hide();
        $("#datePicker1").hide();
        $(".zdydata").hide();
    })
    $(".sui-dropdown-menu1 li:last").click(function(){
        $("#datePicker").show();
        $("#datePicker1").show();
        $(".zdydata").show();
    })
    $(".btn-fontBlue1").click(function(){
        $(".sui-modal").addClass("in");
        $(".sui-modal-backdrop").css("zIndex","1").addClass("in");
    });
    $(".sui-close").click(function(){
        $(".sui-modal").removeClass("in");
        $(".sui-modal-backdrop").css("zIndex","-1").removeClass("in");
    })
//订单管理分页列表
    var a=0
    var param={
        // 当前页
        pageIndex:a,
        //每页显示条数
        pageSize:5
    };
    var result = invokeService('/web/merchant/commodity/getOrderPageList',param);
    if(result.statusCode!=1000){
        alert(result.errorMessage);
        return;
    }
    console.log(result)
})