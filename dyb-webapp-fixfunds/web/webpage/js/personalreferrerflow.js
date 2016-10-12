/**
 * Created by aaa on 2016/10/12.
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

    var a=0;
    var param={
//        当前页
        pageIndex:a,
//        请求条数
        pageSize:5
    };
    var result=invokeService('/web/merchant/recommend/getRecommendIncentivePageList',param);
    if(result.statusCode!=1000){
        alert(result.errorMessage);
        return;
    }
//    console.log(result);
    $("#xg_page").text("共 " +result.result.pageCount+ " 页");
    $("#onpage").text("第 " +(a+1)+ " 页");
    for(var i =0;i<result.result.list.length;i++){
        $("#table1>table>tbody").html($("#table1>table>tbody").html()+
                "<tr>"+
                "<td>"+
                result.result.list[i].recommendIncentive.recommendIncentiveTime+
                "</td>"+
                "<td>" +
                result.result.list[i].name+
                "</td>"+
                "<td>"+
                result.result.list[i].recommendIncentive.recommendAccountCode+
                "</td>"+
                "<td>"+
                result.result.list[i].recommendIncentive.messengerBean+
                "</td>"+
                "<td>"+
                result.result.list[i].recommendIncentive.incentiveType+
                "</td>"+
                "<td>"+
                result.result.list[i].recommendIncentive.incentiveSources+
                "</td>"+
                "</tr>"
        )}

//    下一页
    $("#nextBtn").click(function(){
        a++;
        var param={
            // 当前页
            pageIndex:a,
            //每页显示条数
            pageSize:5
        };
        var result = invokeService('/web/merchant/recommend/getRecommendIncentivePageList',param);
        if(result.statusCode!=1000){
            alert(result.errorMessage)
            return;
        }
        if(a<result.result.pageCount){
            $("#onpage").text("第 " +(a+1)+ " 页");
            $("#table1>table>tbody").html("");
            for(var i =0;i<result.result.list.length;i++){
                $("#table1>table>tbody").html($("#table1>table>tbody").html()+
                        "<tr>"+
                        "<td>"+
                        result.result.list[i].recommendIncentive.recommendIncentiveTime+
                        "</td>"+
                        "<td>" +
                        result.result.list[i].name+
                        "</td>"+
                        "<td>"+
                        result.result.list[i].recommendIncentive.recommendAccountCode+
                        "</td>"+
                        "<td>"+
                        result.result.list[i].recommendIncentive.messengerBean+
                        "</td>"+
                        "<td>"+
                        result.result.list[i].recommendIncentive.incentiveType+
                        "</td>"+
                        "<td>"+
                        result.result.list[i].recommendIncentive.incentiveSources+
                        "</td>"+
                        "</tr>"
                )}

        }
        else{
            a=result.result.pageCount;
            a--;
        }
    });
//				//		上一页
    $("#prevBtn").click(function(){
        a--;
        if(a<0){
            a=0;
        }
        var param={
            // 当前页
            pageIndex:a,
            //每页显示条数
            pageSize:5
        };
        var result = invokeService('/web/merchant/recommend/getRecommendIncentivePageList',param);
        if(result.statusCode!=1000){
            alert(result.errorMessage)
            return;
        }
        if(a>=0){
            $("#onpage").text("第 " +(a+1)+ " 页");
            $("#table1>table>tbody").html("");
            for(var i =0;i<result.result.list.length;i++){
                $("#table1>table>tbody").html($("#table1>table>tbody").html()+
                        "<tr>"+
                        "<td>"+
                        result.result.list[i].recommendIncentive.recommendIncentiveTime+
                        "</td>"+
                        "<td>" +
                        result.result.list[i].name+
                        "</td>"+
                        "<td>"+
                        result.result.list[i].recommendIncentive.recommendAccountCode+
                        "</td>"+
                        "<td>"+
                        result.result.list[i].recommendIncentive.messengerBean+
                        "</td>"+
                        "<td>"+
                        result.result.list[i].recommendIncentive.incentiveType+
                        "</td>"+
                        "<td>"+
                        result.result.list[i].recommendIncentive.incentiveSources+
                        "</td>"+
                        "</tr>"
                )}

        }
        else{

        }
    })



})