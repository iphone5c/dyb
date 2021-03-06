/**
 * Created by aaa on 2016/10/9.
 */
function getdonatelist(pageIndex){
    $(".btn-fontBlue1").click(function(){
        $(".sui-modal").addClass("in");
        $(".sui-modal-backdrop").css("zIndex","1000").addClass("in");
    });
    $(".sui-close").click(function(){
        $(".sui-modal").removeClass("in");
        $(".sui-modal-backdrop").css("zIndex","-1").removeClass("in");
    });
//		商品表
        var param={
            // 当前页
            pageIndex:pageIndex,
            //每页显示条数
            pageSize:5
        };
        var result = invokeService('/web/merchant/commodity/getCommodityPageList',param);
        if(result.statusCode!=1000){
            alert(result.errorMessage);
            return;
        }
        var pageCount=result.result.pageCount;
        $("#xg_table>tbody").html("")
        for(var i =0;i<result.result.list.length;i++){
            $("#xg_table>tbody").html($("#xg_table>tbody").html()+
                    "<tr>"+
                    "<td>"+
                    "<input class='subSelect' type='checkbox' name='checkbox' value=\'"+result.result.list[i].commodityCode+"\'>"+
                    "</td>"+
                    "<td>" +
                    result.result.list[i].name+
                    "</td>"+
                    "<td>"+
                    result.result.list[i].commodityNum+
                    "</td>"+
                    "<td>"+
                    result.result.list[i].specifications+
                    "</td>"+
                    "<td>"+
                    result.result.list[i].price+
                    "</td>"+
                    "</tr>"
            );
        }
    //        确认选择
    $("#submitGoods").click(function () {
        $(".sui-modal").removeClass("in");
        $(".sui-modal-backdrop").css("zIndex", "-1").removeClass("in");
        $("#goodsTable tbody").html("");
        for (var i = 0; i <= $('.subSelect').length; i++) {
            if ($('.subSelect').eq(i).is(':checked')) {
                $("#goodsTable tbody").html($("#goodsTable tbody").html() +
                        "<tr id=" + i + ">" +
                        "<td>" +
                        result.result.list[i].name +
                        "</td>" +
                        "<td>" +
                        result.result.list[i].commodityNum +
                        "</td>" +
                        "<td>" +
                        result.result.list[i].specifications +
                        "</td>" +
                        "<td class='xg_price'>" +
                        result.result.list[i].price +
                        "</td>" +
                        "<td>" +
                        "<input type='number' id=\'" + result.result.list[i].commodityCode + "\' value='1' class='xg_input' min='0'>" +
                        "</td>" +
                        "<td class='xg_sum'>" +
                        result.result.list[i].price +
                        "</td>" +
                        "<td>" +
                        "<a class='js-checkName del' style='display: inline-block;widtd:30%;text-align:center')>" +
                        "删除" +
                        "</a>" +
                        "</td>" +
                        "</tr>"
                );
            }
        }
//          获取消费金额并赋值
        function sun() {
            var a = $(".xg_sum").length;
            var k = 0;
            parseInt(k);
            for (var i = 0; i <= a; i++) {
                k += Number($(".xg_sum").eq(i).text())
            }
            return k;
        }

        var tr = $("#goodsTable tbody input");
        $("#amount").val(sun());
//          计算总价
        tr.keyup(function () {
            var o = tr.index(this);
            var a = $(".xg_price").eq(o).text();//单价
            var b = tr.eq(o).val();//数量
            $(".xg_sum").eq(o).text(a * b);//赋值
//                tr.eq(o).css({"border-color":"red"});
            $("#amount").val(sun());
        });

        ////		提交请求
        var orderItemList = [];
        $("#transCheckIn").click(function () {
            // 信使的Code或绑定手机号
            var accountKey = $("#userNumber").val();
//                订单明细

            for (i = 0; i < tr.length; i++) {
                orderItemList[i] = {
                    commodityCode: tr.eq(i).attr("id"),
                    tradeAmount: tr.eq(i).val()
                }
            }
//                console.log(orderItemList)
            var param = {
                accountKey: accountKey,
                orderItemList: orderItemList
            };
//                console.log(param)
            var result = invokeService('/web/merchant/order/consumerRegistration', {orderConsumerRegistrationParam: JSON.stringify(param)});

//                console.log(result)
            if (result.statusCode != 1000) {
                alert(result.errorMessage);
                return;
            }
            alert("成功")
        });
//删除
        $("#goodsTable tbody").on("click", ".del", function () {
            var l = $(".del").index(this);
            $("#goodsTable tbody tr").eq(l).remove();
            $("#amount").val(sun());
        })
    });
    return pageCount;
 };


//    查看信使资料
$("#queryMemberBtn").click(function () {
    $("#memberInfoTip").removeClass("dsn");
    $("#queryMemberBtn_close").show();
});
$("#queryMemberBtn_close").click(function () {
    $("#memberInfoTip").addClass("dsn");
    $("#queryMemberBtn_close").hide();
})

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
//