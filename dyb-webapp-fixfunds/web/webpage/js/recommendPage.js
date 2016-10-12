/**
 * Created by aaa on 2016/10/12.
 */
$(function(){
    $(".sui-nav li").click(function(){
        var i=$(".sui-nav li").index(this)
        $(".sui-nav li").removeClass("active");
        $(".sui-nav li").eq(i).addClass("active");
        $(".tab-pane").removeClass("active");
        $(".tab-pane").eq(i).addClass("active");
    });
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
    $(".dropdown-toggle").eq(1).click(function(){
        $(".select2").toggleClass("open");
    });
    $(".sui-dropdown-menu2 li").click(function(){
        var i=$(".sui-dropdown-menu2 li").index(this);
        var x= $(".sui-dropdown-menu2 li a").eq(i).html();
        $(".sui-dropdown-menu2 li").removeClass("active");
        $(".sui-dropdown-menu2 li").eq(i).addClass("active");
        $(".select2 span span").html(x);
        $(".select2").removeClass("open");
    });
    $('.dropdown-inner').mouseleave(function(){
        $(".select1").removeClass("open");
        $(".select2").removeClass("open");
    });
    $(".sui-dropdown-menu1 li").click(function(){
        $("#datePicker1").hide();
        $("#datePicker2").hide();
    })
    $(".sui-dropdown-menu1 li:last").click(function(){
        $("#datePicker1").show();
        $("#datePicker2").show();
    })
    $(".sui-dropdown-menu2 li").click(function(){
        $("#datePicker3").hide();
        $("#datePicker4").hide();
    })
    $(".sui-dropdown-menu2 li:last").click(function(){
        $("#datePicker3").show();
        $("#datePicker4").show();
    })


//    我要推荐   推荐记录表
    var a =0;
    var param={
//        当前页
        pageIndex:a,
//        请求数据条数
        pageSize:5
    }
    var result=invokeService('/web/merchant/recommend/getRecommendRecordPageList',param)

    if(result.statusCode!=1000){
        alert(result.errorMessage);
        return;
    };
    console.log(result)
    $("#xg_page").text("共 " +result.result.pageCount+ " 页");
    $("#onpage").text("第 " +(a+1)+ " 页");
//    for(var i =0;i<result.result.list.length;i++){
//        $("#table1>tbody").html($("#table1>tbody").html()+
//                "<tr>"+
//                "<td>"+
//                result.result.list[i].order.realName+
//                "</td>"+
//                "<td>" +
//                result.result.list[i].name+
//                "</td>"+
//                "<td>"+
//                result.result.list[i].address+
//                "</td>"+
//                "<td>"+
//                result.result.list[i].industry+
//                "</td>"+
//                "<td>"+
//                result.result.list[i].price+
//                "</td>"+
//                "<td>"+
//                result.result.list[i].email+
//                "</td>"+
//                "</tr>"
//        )}








})