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
    $(".dropdown-toggle").eq(2).click(function(){
        $(".select3").toggleClass("open");
    })
    $(".sui-dropdown-menu3 li").click(function(){
        var i=$(".sui-dropdown-menu3 li").index(this);
        var x= $(".sui-dropdown-menu3 li a").eq(i).html();
        $(".sui-dropdown-menu3 li").removeClass("active");
        $(".sui-dropdown-menu3 li").eq(i).addClass("active");
        $(".select3 span span").html(x);
        $(".select3").removeClass("open");
    })
    $('.dropdown-inner').mouseleave(function(){
        $(".select1").removeClass("open");
        $(".select2").removeClass("open");
        $(".select3").removeClass("open");
    });


//    让利款发票申请列表
    var a = 0
//    // 让利款发票申请编号code [主键]
//    private String invoiceApplyCode;
//    // 发票计算年月
//    private String invoiceTime;
//    // 营业总额
//    private Double turnoverPrice;
//    // 已交让利款
//    private Double yetBenefitPrice;
//    // 申请发票
//    private Double applyInvoiceMoney;
//    // 已开发票
//    private Double yetInvoiceMoney;
//    // 快递费
//    private Double poundage;
//    // 状态
//    private InvoiceApplyStatus invoiceApplyStatus;
            var param={
                //    当前页
                pageIndex:a,
                //        显示条数
                pageSize:1
            }
            var result=invokeService('/web/merchant/invoiceapply/getAccountIncentivePageList',param)
            if(result.statusCode!=1000){
                alert(result.errorMessage);
                return;
            }
    console.log(result);
    $("#xg_page").text("共 " +result.result.pageCount+ " 页");
    $("#onpage").text("第 " +(a+1)+ " 页");
    for(var i =0;i<result.result.list.length;i++){
        $("#table>table>tbody").html($("#table>table>tbody").html()+
                "<tr>"+
                "<td>"+
                result.result.list[i].invoiceTime+
                "</td>"+
                "<td>" +
                result.result.list[i].turnoverPrice+
                "</td>"+
                "<td>"+
                result.result.list[i].yetBenefitPrice+
                "</td>"+
                "<td>"+
                result.result.list[i].applyInvoiceMoney+
                "</td>"+
                "<td>"+
                result.result.list[i].yetInvoiceMoney+
                "</td>"+
                "<td>"+
                result.result.list[i].poundage+
                "</td>"+
                "<td>"+
                result.result.list[i].invoiceApplyStatus+
                "</td>"+
                "<td>"+
                result.result.list[i].applyTime+
                "</td>"+
                "<td>"+
                ""+
                "</td>"+
                "</tr>"
        )}

    //      下一页
    $("#nextBtn").click(function(){
        a++;
        var param={
            // 当前页
            pageIndex:a,
            //每页显示条数
            pageSize:1
        };
        var result = invokeService('/web/merchant/invoiceapply/getAccountIncentivePageList',param);
        if(result.statusCode!=1000){
            alert(result.errorMessage)
            return;
        }
        if(a<result.result.pageCount){
            $("#onpage").text("第 " +(a+1)+ " 页");
            $("#table>table>tbody").html("");
            for(var i =0;i<result.result.list.length;i++){
                $("#table>table>tbody").html($("#table>table>tbody").html()+
                        "<tr>"+
                        "<td>"+
                        result.result.list[i].invoiceTime+
                        "</td>"+
                        "<td>" +
                        result.result.list[i].turnoverPrice+
                        "</td>"+
                        "<td>"+
                        result.result.list[i].yetBenefitPrice+
                        "</td>"+
                        "<td>"+
                        result.result.list[i].applyInvoiceMoney+
                        "</td>"+
                        "<td>"+
                        result.result.list[i].yetInvoiceMoney+
                        "</td>"+
                        "<td>"+
                        result.result.list[i].poundage+
                        "</td>"+
                        "<td>"+
                        result.result.list[i].invoiceApplyStatus+
                        "</td>"+
                        "<td>"+
                        result.result.list[i].applyTime+
                        "</td>"+
                        "<td>"+
                        ""+
                        "</td>"+
                        "</tr>"
                )}
        }
        else{
            a=result.result.pageCount;
            a--;
        }
    });
    //		上一页
    $("#prevBtn").click(function(){
        a--;
        if(a<0){
            a=0;
        }
        var param={
            // 当前页
            pageIndex:a,
            //每页显示条数
            pageSize:1
        };
        var result = invokeService('/web/merchant/invoiceapply/getAccountIncentivePageList',param);
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
                        result.result.list[i].invoiceTime+
                        "</td>"+
                        "<td>" +
                        result.result.list[i].turnoverPrice+
                        "</td>"+
                        "<td>"+
                        result.result.list[i].yetBenefitPrice+
                        "</td>"+
                        "<td>"+
                        result.result.list[i].applyInvoiceMoney+
                        "</td>"+
                        "<td>"+
                        result.result.list[i].yetInvoiceMoney+
                        "</td>"+
                        "<td>"+
                        result.result.list[i].poundage+
                        "</td>"+
                        "<td>"+
                        result.result.list[i].invoiceApplyStatus+
                        "</td>"+
                        "<td>"+
                        result.result.list[i].applyTime+
                        "</td>"+
                        "<td>"+
                        ""+
                        "</td>"+
                        "</tr>"
                )}
        }
        else{

        }
    });

        })