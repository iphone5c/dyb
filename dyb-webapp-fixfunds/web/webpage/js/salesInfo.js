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
    $(".sui-dropdown-menu1 li").click(function(){
        $("#datePicker").hide();
        $("#datePicker1").hide();
    })
    $(".sui-dropdown-menu1 li:last").click(function(){
        $("#datePicker").show();
        $("#datePicker1").show();
    })
    $('.dropdown-inner').mouseleave(function(){
        $(".select1").removeClass("open");
        $(".select2").removeClass("open");
    });
    $("#table").on("click",".btn_xql",function(){
        $(".sui-modal").addClass("in");
        $(".sui-modal-backdrop").css("zIndex","1").addClass("in");
    });
    $(".sui-close").click(function(){
        $(".sui-modal").removeClass("in");
        $(".sui-modal-backdrop").css("zIndex","-1").removeClass("in");
    });


    //营业额分页列表
    var a=0;
    var param={
        // 当前页
        pageIndex:a,
        //每页显示条数
        pageSize:5
    };
    var result = invokeService('/web/merchant/turnover/getTurnoverPageList',param);
    if(result.statusCode!=1000){
        alert(result.errorMessage);
        return;
    }
    $("#xg_page").text("共 " +result.result.pageCount+ " 页");
    $("#onpage").text("第 " +(a+1)+ " 页");
    for(var i =0;i<result.result.list.length;i++){
        $("#table>table>tbody").html($("#table>table>tbody").html()+
                "<tr>"+
                "<td>"+
                result.result.list[i].turnoverTime+
                "</td>"+
                "<td>" +
                result.result.list[i].turnoverPrice+
                "</td>"+
                "<td>"+
                result.result.list[i].benefitPrice+
                "</td>"+
                "<td>"+
                result.result.list[i].yetBenefitPrice+
                "</td>"+
                "<td>"+
                result.result.list[i].residueBenefitPrice+
                "</td>"+
                "<td>"+
                result.result.list[i].benefitPriceStatus+
                "</td>"+
                "<td>"+
                "<a class='btn_xql'>"+
                "<input  type='hidden' value=\'"+result.result.list[i].turnoverCode+"\'>"+
                    "查看详情"+
                "</a>"+
                "</td>"+
                "<td>"+
                result.result.list[i].remark+
                "</td>"+
                "</tr>"
        )
    }
////    营业额详情列表
    $("#table").on("click",".btn_xql",(function(){
        var b=0;
        var i = $(".btn_xql").index(this);
        var turnoverCode=$(".btn_xql>input").eq(i).val();
        var param={
            // 当前页
            pageIndex:b,
            //每页显示条数
            pageSize:1,
            //  主键
            turnoverCode:turnoverCode
        };

        var result = invokeService('/web/merchant/turnover/getTurnoverDetailsPageList',param);
        if(result.statusCode!=1000){
            alert(result.errorMessage);
            return;
        }
        console.log(result)
        $("#xg_page2").text("共 " +result.result.pageCount+ " 页");
        $("#onpage2").text("第 " +(b+1)+ " 页");
        for(var i =0;i<result.result.list.length;i++){
            $("#salesDetailtable>table>tbody").html($("#salesDetailtable>table>tbody").html()+
                    "<tr>"+
                    "<td>"+
                    result.result.list[i].order.turnoverTime+
                    "</td>"+
                    "<td>" +
                    result.result.list[i].member.accountCode+
                    "</td>"+
                    "<td>"+
                    result.result.list[i].member.member.realName+
                    "</td>"+
                    "<td>"+
                    result.result.list[i].member.accountPhone+
                    "</td>"+
                    "<td>"+
                    result.result.list[i].order.price+
                    "</td>"+
                    "<td>"+
                    result.result.list[i].order.incentiveMode+"%"+
                    "</td>"+
                    "<td>"+
                    result.result.list[i].order.orderCode+
                    "</td>"+
                    "<td>"+
                    result.result.list[i].order.status+
                    "</td>"+
                    "</tr>"
            )
        }
        //      营业额详情下一页
        $("#nextBtn2").click(function(){
            b++;
            var param={
                // 当前页
                pageIndex:b,
                //每页显示条数
                pageSize:1,
                //  主键
                turnoverCode:turnoverCode
            };
            var result = invokeService('/web/merchant/turnover/getTurnoverDetailsPageList',param);
            if(result.statusCode!=1000){
                alert(result.errorMessage)
                return;
            }
            if(b<result.result.pageCount){
                $("#onpage2").text("第 " +(b+1)+ " 页");
                $("#salesDetailtable>table>tbody").html("");
                for(var i =0;i<result.result.list.length;i++){
                    $("#salesDetailtable>table>tbody").html($("#salesDetailtable>table>tbody").html()+
                            "<tr>"+
                            "<td>"+
                            result.result.list[i].order.turnoverTime+
                            "</td>"+
                            "<td>" +
                            result.result.list[i].member.accountCode+
                            "</td>"+
                            "<td>"+
                            result.result.list[i].member.member.realName+
                            "</td>"+
                            "<td>"+
                            result.result.list[i].member.accountPhone+
                            "</td>"+
                            "<td>"+
                            result.result.list[i].order.price+
                            "</td>"+
                            "<td>"+
                            result.result.list[i].order.incentiveMode+"%"+
                            "</td>"+
                            "<td>"+
                            result.result.list[i].order.orderCode+
                            "</td>"+
                            "<td>"+
                            result.result.list[i].order.status+
                            "</td>"+
                            "</tr>"
                    )
                }
//											window.location.href="http://localhost:63342/untitled/comm/client/index.html"
            }
            else{
                b=result.result.pageCount;
                b--;
            }
        });
        //		营业额详情上一页
        $("#prevBtn2").click(function(){
            b--;
            if(b<0){
                b=0;
            }
            var param={
                // 当前页
                pageIndex:b,
                //每页显示条数
                pageSize:1,
                //  主键
                turnoverCode:turnoverCode
            };
            var result = invokeService('/web/merchant/turnover/getTurnoverDetailsPageList',param);
            if(result.statusCode!=1000){
                alert(result.errorMessage)
                return;
            }
            if(b>=0){
                $("#onpage2").text("第 " +(b+1)+ " 页");
                $("#salesDetailtable>table>tbody").html("");
                for(var i =0;i<result.result.list.length;i++){
                    $("#salesDetailtable>table>tbody").html($("#salesDetailtable>table>tbody").html()+
                            "<tr>"+
                            "<td>"+
                            result.result.list[i].order.turnoverTime+
                            "</td>"+
                            "<td>" +
                            result.result.list[i].member.accountCode+
                            "</td>"+
                            "<td>"+
                            result.result.list[i].member.member.realName+
                            "</td>"+
                            "<td>"+
                            result.result.list[i].member.accountPhone+
                            "</td>"+
                            "<td>"+
                            result.result.list[i].order.price+
                            "</td>"+
                            "<td>"+
                            result.result.list[i].order.incentiveMode+"%"+
                            "</td>"+
                            "<td>"+
                            result.result.list[i].order.orderCode+
                            "</td>"+
                            "<td>"+
                            result.result.list[i].order.status+
                            "</td>"+
                            "</tr>"
                    )
                }
            }
            else{

            }
        });





































    }));
//    //      营业额下一页
    $("#nextBtn").click(function(){
        a++;
        var param={
            // 当前页
            pageIndex:a,
            //每页显示条数
            pageSize:5
        };
        var result = invokeService('/web/merchant/turnover/getTurnoverPageList',param);
        if(result.statusCode!=1000){
            alert(result.errorMessage)
            return;
        }
        if(a<result.result.pageCount){
            $("#onpage").text("第 " +(a+1)+ " 页");
            $("#salesDetailtable>table>tbody").html("");
            for(var i =0;i<result.result.list.length;i++){
                $("#salesDetailtable>table>tbody").html($("#salesDetailtable>table>tbody").html()+
                        "<tr>"+
                        "<td>"+
                        result.result.list[i].order.turnoverTime+
                        "</td>"+
                        "<td>" +
                        result.result.list[i].member.accountCode+
                        "</td>"+
                        "<td>"+
                        result.result.list[i].member.member.realName+
                        "</td>"+
                        "<td>"+
                        result.result.list[i].member.accountPhone+
                        "</td>"+
                        "<td>"+
                        result.result.list[i].order.price+
                        "</td>"+
                        "<td>"+
                        result.result.list[i].order.incentiveMode+"%"+
                        "</td>"+
                        "<td>"+
                        result.result.list[i].order.orderCode+
                        "</td>"+
                        "<td>"+
                        result.result.list[i].order.status+
                        "</td>"+
                        "</tr>"
                )
            }
//											window.location.href="http://localhost:63342/untitled/comm/client/index.html"
        }
        else{
            a=result.result.pageCount;
            a--;
        }
    });
    //		营业额上一页
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
        var result = invokeService('/web/merchant/turnover/getTurnoverPageList',param);
        if(result.statusCode!=1000){
            alert(result.errorMessage)
            return;
        }
        if(a>=0){
            $("#onpage").text("第 " +(a+1)+ " 页");
            $("#table>table>tbody").html("");
            for(var i =0;i<result.result.list.length;i++){
                $("#table>table>tbody").html($("#table>table>tbody").html()+
                        "<tr>"+
                        "<td>"+
                        result.result.list[i].turnoverTime+
                        "</td>"+
                        "<td>" +
                        result.result.list[i].turnoverPrice+
                        "</td>"+
                        "<td>"+
                        result.result.list[i].benefitPrice+
                        "</td>"+
                        "<td>"+
                        result.result.list[i].yetBenefitPrice+
                        "</td>"+
                        "<td>"+
                        result.result.list[i].residueBenefitPrice+
                        "</td>"+
                        "<td>"+
                        result.result.list[i].benefitPriceStatus+
                        "</td>"+
                        "<td>"+
                        "<a class='btn_xql'>"+
                        "<input  type='hidden' value=\'"+result.result.list[i].turnoverCode+"\'>"+
                        "查看详情"+
                        "</a>"+
                        "</td>"+
                        "<td>"+
                        result.result.list[i].remark+
                        "</td>"+
                        "</tr>"
                )
            }
        }
        else{

        }
    });

})