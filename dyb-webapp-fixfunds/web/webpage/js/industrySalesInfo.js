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
    $("#salesDetailtable").on("click",".btn_xql",function(){
        $(".sui-modal").addClass("in");
        $(".sui-modal-backdrop").css("zIndex","1").addClass("in");
    });
    $(".sui-close").click(function(){
        $(".sui-modal").removeClass("in");
        $(".sui-modal-backdrop").css("zIndex","-1").removeClass("in");
    })
})
function getdonatelist(pageIndex){
//订单管理分页列表
    var a=0;
    var param={
        // 当前页
        pageIndex:pageIndex,
        //每页显示条数
        pageSize:5
    };
    var result = invokeService('/web/merchant/order/getOrderPageList',param);
    if(result.statusCode!=1000){
        alert(result.errorMessage);
        return;
    }
//    console.log(result);
    var pageCount=result.result.pageCount;
    $("#salesDetailtable>table>tbody").html("")
    for(var i =0;i<result.result.list.length;i++){
        $("#salesDetailtable>table>tbody").html($("#salesDetailtable>table>tbody").html()+
                "<tr>"+
                "<td>"+
                    result.result.list[i].order.tradeTime+
                "</td>"+
                "<td>" +
                result.result.list[i].order.memberCode+
                "</td>"+
                "<td>"+
                result.result.list[i].account.member.realName+
                "</td>"+
                "<td>"+
                result.result.list[i].account.accountPhone+
                "</td>"+
                "<td>"+
                result.result.list[i].order.price+
                "</td>"+
                "<td>"+
                result.result.list[i].order.incentiveMode+
                "</td>"+
                "<td>"+
                "<a class='btn_xql'>"+
                result.result.list[i].order.orderCode+
                "</a>"+
                "</td>"+
                "<td>"+
                result.result.list[i].order.status+
                "</td>"+
                "<td>"+
                ""+
                "</td>"+
                "</tr>"
        )
    }
//    订单详情列表
//    $("#salesDetailtable").on("click",".btn_xql",(function(){
//        var i = $(".btn_xql").index(this);
////        alert($(".btn_xql").eq(i).text());
//    }))
    return pageCount
};
$(function() {
    var pageIndex = 0;
    var pageCount = getdonatelist(pageIndex);
    page("excit", pageCount, pageIndex);
    // 下一頁
    $("#excit").on("click", "#nextBtn", function () {
        if (pageIndex + 1 >= pageCount) {
            return;
        }
        pageIndex++;
        getdonatelist(pageIndex);
        page("excit", pageCount, pageIndex);
    })
    // 上一頁
    $("#excit").on("click", "#prevBtn", function () {
        if (pageIndex == 0) {
            return;
        }
        pageIndex--;
        getdonatelist(pageIndex);
        page("excit", pageCount, pageIndex);
    })
    // 點擊調頁
    $("#excit").on("click", "#selectPage", function () {
        var valIndex = $("#selectPageNum").val() - 1;
        if (valIndex >= 0 && valIndex + 1 <= pageCount) {
            pageIndex = valIndex;
            getdonatelist(pageIndex);
            page("excit", pageCount, pageIndex);
        } else {
            $("#selectPageNum").val("");
            return;
        }
    })
})
