/**
 * Created by aaa on 2016/10/9.
 */
$(function(){
    $(".btn-fontBlue1").click(function(){
        $(".sui-modal").addClass("in");
        $(".sui-modal-backdrop").css("zIndex","1000").addClass("in");
    });
    $(".sui-close").click(function(){
        $(".sui-modal").removeClass("in");
        $(".sui-modal-backdrop").css("zIndex","-1").removeClass("in");
    });


//		商品表
    var a=0;
    $.ajax({
        url:"http://192.168.0.186:8080/web/merchant/commodity/getCommodityPageList",
        type:"post",
        dataType: 'JSONP',
        data:{
            // 当前页
            pageIndex:a,
            //每页显示条数
            pageSize:5
        },
        success: function (data) {
            a=a+1;
            $("#xg_page").text("共 " +data.result.pageCount+ " 页");
            $("#onpage").text("第 " +a+ " 页");
            for(var i =0;i<data.result.list.length;i++){
                $("#xg_table>tbody").html($("#xg_table>tbody").html()+
                        "<tr>"+
                        "<td>"+
                        "<input class='subSelect' type='checkbox' name='checkbox' value=\'"+data.result.list[i].commodityCode+"\'>"+
                        "</td>"+
                        "<td>" +
                        data.result.list[i].name+
                        "</td>"+
                        "<td>"+
                        data.result.list[i].commodityNum+
                        "</td>"+
                        "<td>"+
                        data.result.list[i].specifications+
                        "</td>"+
                        "<td>"+
                        data.result.list[i].price+
                        "</td>"+
                        "</tr>"
                )
            }
//				确认选择
            var resultArray =[data.result]
            $("#submitGoods").click(function(){
                for(var i=0; i<=$('.subSelect').length; i++){
                    if($('.subSelect').eq(i).is(':checked')){
                        var allprice = $("#xg_sum").val()*data.result.list[i].price
                        $("#goodsTable tbody").html($("#goodsTable tbody").html()+
                                "<tr>"+
                                "<td>"+
                                data.result.list[i].name+
                                "</td>"+
                                "<th>"+
                                data.result.list[i].commodityNum+
                                "</th>"+
                                "<th>"+
                                data.result.list[i].specifications+
                                "</th>"+
                                "<th>"+
                                data.result.list[i].price+
                                "</th>"+
                                "<th>"+
                                "<input type='number'id='xg_sum' value='1'>"+
                                "</th>"+
                                "<th>"+
                                data.result.list[i].price+
                                "</th>"+
                                "<th>"+
                                "<a class='js-checkName' style='display: inline-block;width:30%;text-align:center' class=\'"+data.result.list[i].commodityCode+"\' onclick=deleteCommodity(\'"+data.result.list[i].commodityCode+"\')>"+
                                "删除"+
                                "</a>"+
                                "</th>"+
                                "</tr>"
                        )
                    }
                }
            });
//				function xg_blur(i){
//					alert(11);
////					for(var i=0; i<=$('.subSelect').length; i++){
////						if($('.subSelect').eq(i).is(':checked')){
////							var code=$(".subSelect").eq(i).val();
////							$("#goodsTable tbody").html($("#goodsTable tbody").html()+
////									"<tr>"+
////									"<td>"+
////									data.result.list[i].name+
////									"</td>"+
////									"<th>"+
////									data.result.list[i].commodityNum+
////									"</th>"+
////									"<th>"+
////									data.result.list[i].specifications+
////									"</th>"+
////									"<th>"+
////									data.result.list[i].price+
////									"</th>"+
////									"<th>"+
////									"<input type='text'id='xg_sum' value='2'>"+
////									"</th>"+
////									"<th>"+
////									data.result.list[i].price*$("#xg_sum").val()+
////									"</th>"+
////									"<th>"+
////									"<a class='js-checkName' style='display: inline-block;width:30%;text-align:center' class=\'"+data.result.list[i].commodityCode+"\' onclick=deleteCommodity(\'"+data.result.list[i].commodityCode+"\')>"+
////									"删除"+
////									"</a>"+
////									"</th>"+
////									"</tr>"
////							)
////						}
////					}
//				};
            //删除
            function deleteCommodity(deleteCode){
                $.ajax({
                    url:"http://192.168.0.186:8080/web/merchant/commodity/deleteCommodity",
                    type:"post",
                    dataType: 'JSONP',
                    data:{
                        // 商品编号code [主键]
                        commodityCode:deleteCode
                    },
                    success: function (data) {
                        window.location.href="http://localhost:63342/untitled/comm/client/index.html"
                    }
                })
            }
        }

    });
    //下一頁
    $("#nextBtn").click(function(){
        $.ajax({
            url:"http://192.168.0.186:8080/web/merchant/commodity/getCommodityPageList",
            type:"post",
            dataType: 'JSONP',
            data:{
                // 当前页
                pageIndex:a,
                //每页显示条数
                pageSize:2
            },
            success: function (data) {
                a++;
                if(a<=data.result.pageCount){
                    $("#onpage").text("第 " +a+ " 页");
                    $(".sui-table>tbody").html("");
                    for(var i =0;i<data.result.list.length;i++){
                        $(".sui-table>tbody").html($(".sui-table>tbody").html()+
                                "<tr>"+
                                "<td>"+
                                "<input class='subSelect' type='checkbox' name='checkbox' value=\'"+data.result.list[i].commodityCode+"\'>"+
                                "</td>"+
                                "<td>" +
                                data.result.list[i].name+
                                "</td>"+
                                "<td>"+
                                data.result.list[i].commodityNum+
                                "</td>"+
                                "<td>"+
                                data.result.list[i].specifications+
                                "</td>"+
                                "<td>"+
                                data.result.list[i].price+
                                "</td>"+
                                "</tr>"
                        )
                    }
//											window.location.href="http://localhost:63342/untitled/comm/client/index.html"
                }
                else{
                    a=4;
                }
            }
        });
    });
//				//		上一页
    $("#prevBtn").click(function(){
        a=a-2;
        if(a<0){
            a=0
        }
        $.ajax({
            url:"http://192.168.0.186:8080/web/merchant/commodity/getCommodityPageList",
            type:"post",
            dataType: 'JSONP',
            data:{
                // 当前页
                pageIndex:a,
                //每页显示条数
                pageSize:2
            },
            success: function (data) {
                a++;
                if(a>=1){
                    $("#onpage").text("第 " +a+ " 页");
                    $(".sui-table>tbody").html("");
                    for(var i =0;i<data.result.list.length;i++){
                        $(".sui-table>tbody").html($(".sui-table>tbody").html()+
                                "<tr>"+
                                "<td>"+
                                "<input class='subSelect' type='checkbox' name='checkbox' value=\'"+data.result.list[i].commodityCode+"\'>"+
                                "</td>"+
                                "<td>" +
                                data.result.list[i].name+
                                "</td>"+
                                "<td>"+
                                data.result.list[i].commodityNum+
                                "</td>"+
                                "<td>"+
                                data.result.list[i].specifications+
                                "</td>"+
                                "<td>"+
                                data.result.list[i].price+
                                "</td>"+
                                "</tr>"
                        )
                    }
//											window.location.href="http://localhost:63342/untitled/comm/client/index.html"
                }
                else{
                }
            }
        });
    });
//		提交请求
    $("#transCheckIn").click(function(){
        $.ajax({
            url:"http://192.168.0.186:8080/web/merchant/commodity/consumerRegistration",
            type:"post",
            dataType: 'JSONP',
            data:{
                // 信使的Code或绑定手机号
                accountKey:$("#userNumber").val(),
                //订单明细
                orderItemList:$("#amount").val()
            },
            success: function (data) {

            }
        })
    })
});
