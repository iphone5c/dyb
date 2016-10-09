/**
 * Created by aaa on 2016/10/9.
 */

var a = 0;
var commodityCode="";
//修改 → → → → → → 确定
$("#delete182082_btn").click(function(){
    $.ajax({
        url:"http://192.168.0.186:8080/web/merchant/commodity/updateCommodity",
        type:"post",
        dataType: 'JSONP',
        data:{
            // 商品编号code [主键]
            commodityCode:$("#commodityCode").val(),
            // 商品名称
            name:$("#productname_1").val(),
            // 商品编号
            commodityNum:$("#productID_1").val(),
            // 型号/规格
            specifications:$("#modelnumber_1").val(),
            // 单价
            price:$("#price_1").val()
        },
        success: function (data) {
            window.location.href="http://localhost:63342/untitled/comm/client/index.html"
        }

    })
});
//添加
$("#addProduct").click(function(){
    $.ajax({
        url:"http://192.168.0.186:8080/web/merchant/commodity/addCommodity",
        type:"post",
        dataType: 'JSONP',
        data:{
            // 商品名称
            name:$("#productname").val(),
            // 商品编号
            commodityNum:$("#productID").val(),
            // 型号/规格
            specifications:$("#modelnumber").val(),
            // 单价
            price:$("#price").val()
        },
        success: function (data) {
            window.location.href="http://localhost:63342/untitled/comm/client/index.html"
        }
    })
});
//删除
function deleteCommodity(deleteCode){
    $.ajax({
        url:"http://192.168.0.186:8080/web/merchant/commodity/deleteCommodity",
        type:"post",
        dataType: 'JSONP',
        data:{
            // 商品编号code [主键]
            commodityCode:deleteCode,
        },
        success: function (data) {
            window.location.href="http://localhost:63342/untitled/comm/client/index.html"
        }
    })
}
//批量删除
$("#deleteProduct").click(function(){
    var a='';
    for(var i=0; i<=$('.subSelect').length; i++){
        if($('.subSelect').eq(i).is(':checked')){
            var code=$(".subSelect").eq(i).val();
            a+=code+",";
        }
    }

    if(a.length<=0){
        alert('请选择要删除记录前面的复选框')
        return  false;
    }
    a=a.substring(0,a.length-1);
    $.ajax({
        url:"http://192.168.0.186:8080/web/merchant/commodity/deleteCommodityList",
        type:"post",
        dataType: 'JSONP',
        data:{
            // 商品编号集合（多个用逗号隔开，格式：20108196,254135,65645,...）
            commodityCodeList:a,
        },
        success: function (data) {
            window.location.href="http://localhost:63342/untitled/comm/client/index.html"
        }
    });
});
//修改按钮
function updateCommodity(commodityCode){
    $(".sui-modal").addClass("in");
    $(".sui-modal-backdrop").css("zIndex","1").addClass("in");
    $.ajax({
        url:"http://192.168.0.186:8080/web/merchant/commodity/getCommodityByCode",
        type:"post",
        dataType: 'JSONP',
        data:{
            // 商品编号code [主键]
            commodityCode:commodityCode
        },
        success: function (data) {

            $("#commodityCode").val(commodityCode);
            $("#productname_1").val(data.result.name);
            $("#productID_1").val(data.result.commodityNum);
            $("#modelnumber_1").val(data.result.specifications);
            $("#price_1").val(data.result.price);
        }
    });
    $(".sui-close").click(function(){
        $(".sui-modal").removeClass("in");
        $(".sui-modal-backdrop").css("zIndex","-1").removeClass("in");
    });
    $(".btn-large").click(function(){
        $(".sui-modal").removeClass("in");
        $(".sui-modal-backdrop").css("zIndex","-1").removeClass("in");
    })
}
//当前登录商家获取分页商品列表     //第一次加载
$(function(){
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
            a=a+1;
            $("#xg_page").text("共 " +data.result.pageCount+ " 页");
            $("#onpage").text("第 " +a+ " 页");
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
                        "<td>"+
                        "<a class='js-checkName' style='display: inline-block;width:30%;text-align:center' class=\'"+data.result.list[i].commodityCode+"\' onclick=updateCommodity(\'"+data.result.list[i].commodityCode+"\')>"+
                        "修改"+
                        "</a>"+
                        "<a class='js-checkName' style='display: inline-block;width:30%;text-align:center' class=\'"+data.result.list[i].commodityCode+"\' onclick=deleteCommodity(\'"+data.result.list[i].commodityCode+"\')>"+
                        "删除"+
                        "</a>"+
                        "</td>"+
                        "</tr>"
                )
            }
        }
    });
});
//						下一頁
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
                            "<td>"+
                            "<a class='js-checkName' style='display: inline-block;width:30%;text-align:center' class=\'"+data.result.list[i].commodityCode+"\' onclick=updateCommodity(\'"+data.result.list[i].commodityCode+"\')>"+
                            "修改"+
                            "</a>"+
                            "<a class='js-checkName' style='display: inline-block;width:30%;text-align:center' class=\'"+data.result.list[i].commodityCode+"\' onclick=deleteCommodity(\'"+data.result.list[i].commodityCode+"\')>"+
                            "删除"+
                            "</a>"+
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
                            "<td>"+
                            "<a class='js-checkName' style='display: inline-block;width:30%;text-align:center' class=\'"+data.result.list[i].commodityCode+"\' onclick=updateCommodity(\'"+data.result.list[i].commodityCode+"\')>"+
                            "修改"+
                            "</a>"+
                            "<a class='js-checkName' style='display: inline-block;width:30%;text-align:center' class=\'"+data.result.list[i].commodityCode+"\' onclick=deleteCommodity(\'"+data.result.list[i].commodityCode+"\')>"+
                            "删除"+
                            "</a>"+
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


//	查询商品信息
//				$("#searchProductList").click(function(){
//
//							$.ajax({
//								url:"http://192.168.0.186:8080/web/merchant/commodity/getCommodityByCode",
//								type:"post",
//								dataType: 'JSONP',
//								jsonp: "callbackparam", //服务端用于接收callback调用的function名的参数
//								jsonpCallback: "web", //callback的function名称,服务端会把名称和data一起传递回来
//								data:{
//									// 商品编号code [主键]
//									commodityCode:"1"+"1"
//								},
//								success: function (data) {
//									if(data.statusCode==1000){
//										alert(data.result)
//									}else{
//										alert(data.errorMessage)
//									}
//								}
//							})
//						});