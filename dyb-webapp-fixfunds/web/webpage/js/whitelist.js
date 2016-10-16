/**
 * Created by aaa on 2016/10/16.
 */
$(function(){
    $(".sui-nav li").click(function(){
        var i=$(".sui-nav li").index(this)
        $(".sui-nav li").removeClass("active");
        $(".sui-nav li").eq(i).addClass("active");
    });
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
    $(".dropdown-toggle").eq(1).click(function(){
    $(".select2").toggleClass("open");
    })
    $(".sui-dropdown-menu2 li").click(function(){
    var i=$(".sui-dropdown-menu2 li").index(this);
    var x= $(".sui-dropdown-menu2 li a").eq(i).html();
    $(".sui-dropdown-menu2 li").removeClass("active");
    $(".sui-dropdown-menu2 li").eq(i).addClass("active");
    $(".select2 span span").html(x);
    $(".select2").removeClass("open");
    })
    $('.dropdown-inner').mouseleave(function(){
    $(".select1").removeClass("open");
    $(".select2").removeClass("open");
    });

    var a=0;
    var para={
//        当前页
        pageIndex:a,
//        请求数据条数
        pageSize:1
    }
    var result=invokeService('')








    })