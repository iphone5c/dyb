/**
 * Created by aaa on 2016/10/9.
 */

//当前登录商家获取分页商品列表     //第一次加载
    function getdonatelists(pageIndex){
//    var pageArray=[];
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
        page("excit", pageCount, pageIndex);
        $(".sui-table>tbody").html("");
        for(var i =0;i<result.result.list.length;i++){
            $(".sui-table>tbody").html($(".sui-table>tbody").html()+
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
                    "<td>"+
                    "<a class='js-checkName js-checkName1' style='display: inline-block;width:30%;text-align:center' id='"+result.result.list[i].commodityCode+"'>"+
                    "修改"+
                    "</a>"+
                    "<a class='js-checkName js-checkName2' style='display: inline-block;width:30%;text-align:center' id='"+result.result.list[i].commodityCode+"'>"+
                    "删除"+
                    "</a>"+
                    "</td>"+
                    "</tr>"
            );
    }
        return pageCount;
    }
    $(function() {
        var pageIndex = 0;
        var pageCount = getdonatelists(pageIndex);
        page("excit", pageCount, pageIndex);
        // 下一頁
        $("#excit").on("click", "#nextBtn", function () {
           var pageCount = getdonatelists(pageIndex);
            if (pageIndex + 1 >= pageCount) {

                return;
            }
            pageIndex++;
            getdonatelists(pageIndex)
        })
        // 上一頁
        $("#excit").on("click", "#prevBtn", function () {
            if (pageIndex == 0) {
                return;
            }
            pageIndex--;
            getdonatelists(pageIndex);
        })
        // 點擊調頁
        $("#excit").on("click", "#selectPage", function () {
            var valIndex = $("#selectPageNum").val() - 1;
             var pageCount = getdonatelists(pageIndex);
            if (valIndex >= 0 && valIndex + 1 <= pageCount) {
                pageIndex = valIndex;
                getdonatelists(pageIndex);
            } else {
                $("#selectPageNum").val("");
                return;
            }
        });

        //添加
        $("#addProduct").click(function(){
            // 商品名称
            var name=$("#productname").val();
            // 商品编号
            var commodityNum=$("#productID").val();
            // 型号/规格
            var specifications=$("#modelnumber").val();
            // 单价
            var price=$("#price").val();
            var param={
                name:name,
                commodityNum:commodityNum,
                specifications:specifications,
                price:price
            };
            var result=invokeService('/web/merchant/commodity/addCommodity',param);
            if(result.statusCode!=1000){
                alert(result.errorMessage);
                return;
            }
            getdonatelists(pageIndex);
        });
        //删除
        $("#productListTable").on("click",".js-checkName2",function(){
            var i=$(".js-checkName2").index(this);
            var commodityCode=$(".js-checkName2").eq(i).attr("id");
            var param={
                commodityCode:commodityCode
            }
            var result=invokeService('/web/merchant/commodity/deleteCommodity',param);
            if(result.statusCode!=1000){
                alert(result.errorMessage);
                return;
            }
            var pageCount = getdonatelists(pageIndex);
            if(pageIndex + 1 >pageCount){
                pageIndex--;
            }
            if(pageIndex<0){
                pageIndex=0;
            }
                getdonatelists(pageIndex);

        })
//        批量删除
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
// 商品编号集合（多个用逗号隔开，格式：20108196,254135,65645,...）
            var  commodityCodeList=a;
            var param={
                commodityCodeList:commodityCodeList
            };
            var result=invokeService('/web/merchant/commodity/deleteCommodityList',param)
            if(result.statusCode!=1000){
                alert(result.errorMessage);
                return;
            }
            getdonatelists(pageIndex);
        });
//        修改
        $("#productListTable").on("click",".js-checkName1",function() {
            var i=$(".js-checkName1").index(this);
            var commodityCode1=$(".js-checkName1").eq(i).attr("id");
            var param = {
                commodityCode: commodityCode1
            };
            var result = invokeService('/web/merchant/commodity/getCommodityByCode', param);
            if (result.statusCode != 1000) {
                alert(result.errorMessage);
                return;
            }
            //商品名称
            $("#productname_1").val(result.result.name);
            // 商品编号
            $("#productID_1").val(result.result.commodityNum);
            // 型号/规格
            $("#modelnumber_1").val(result.result.specifications);
            // 单价
            $("#price_1").val(result.result.price);

//修改 → → → → → → 确定
            $("#delete182082_btn").click(function () {
                // 商品编号code [主键]
                var commodityCode = commodityCode1;
                // 商品名称
                var name = $("#productname_1").val();
                // 商品编号
                var commodityNum = $("#productID_1").val();
                // 型号/规格
                var specifications = $("#modelnumber_1").val();
                // 单价
                var price = $("#price_1").val();
                var param = {
                    commodityCode: commodityCode,
                    name: name,
                    commodityNum: commodityNum,
                    specifications: specifications,
                    price: price
                };
                var result = invokeService('/web/merchant/commodity/updateCommodity', param);
                if (result.statusCode != 1000) {
                    alert(result.errorMessage);
                    return;
                }
                getdonatelists(pageIndex);
            });
            $(".sui-modal").addClass("in");
            $(".sui-modal-backdrop").css("zIndex", "1").addClass("in");
            $(".sui-close").click(function () {
                $(".sui-modal").removeClass("in");
                $(".sui-modal-backdrop").css("zIndex", "-1").removeClass("in");
            });
            $(".btn-large").click(function () {
                $(".sui-modal").removeClass("in");
                $(".sui-modal-backdrop").css("zIndex", "-1").removeClass("in");
            })
        })
    });

//批量删除

//修改按钮


//						下一頁
//    $("#nextBtn").click(function(){
//        a++;
//        var param={
//            // 当前页
//            pageIndex:a,
//            //每页显示条数
//            pageSize:5
//        };
//        var result = invokeService('/web/merchant/commodity/getCommodityPageList',param);
//        if(result.statusCode!=1000){
//            alert(result.errorMessage)
//            return;
//        }
//        if(a<result.result.pageCount){
//            $("#onpage").text("第 " +(a+1)+ " 页");
//            $(".sui-table>tbody").html("");
//            for(var i =0;i<result.result.list.length;i++){
//                $(".sui-table>tbody").html($(".sui-table>tbody").html()+
//                        "<tr>"+
//                        "<td>"+
//                        "<input class='subSelect' type='checkbox' name='checkbox' value=\'"+result.result.list[i].commodityCode+"\'>"+
//                        "</td>"+
//                        "<td>" +
//                        result.result.list[i].name+
//                        "</td>"+
//                        "<td>"+
//                        result.result.list[i].commodityNum+
//                        "</td>"+
//                        "<td>"+
//                        result.result.list[i].specifications+
//                        "</td>"+
//                        "<td>"+
//                        result.result.list[i].price+
//                        "</td>"+
//                        "<td>"+
//                        "<a class='js-checkName' style='display: inline-block;width:30%;text-align:center' class=\'"+result.result.list[i].commodityCode+"\' onclick=updateCommodity(\'"+result.result.list[i].commodityCode+"\')>"+
//                        "修改"+
//                        "</a>"+
//                        "<a class='js-checkName' style='display: inline-block;width:30%;text-align:center' class=\'"+result.result.list[i].commodityCode+"\' onclick=deleteCommodity(\'"+result.result.list[i].commodityCode+"\')>"+
//                        "删除"+
//                        "</a>"+
//                        "</td>"+
//                        "</tr>"
//                )
//            }
////											window.location.href="http://localhost:63342/untitled/comm/client/index.html"
//        }
//        else{
//            a=result.result.pageCount;
//            a--;
//        }
//    });
////				//		上一页
//    $("#prevBtn").click(function(){
//        a--;
//        if(a<0){
//            a=0;
//        }
//        var param={
//            // 当前页
//            pageIndex:a,
//            //每页显示条数
//            pageSize:5
//        };
//        var result = invokeService('/web/merchant/commodity/getCommodityPageList',param);
//        if(result.statusCode!=1000){
//            alert(result.errorMessage)
//            return;
//        }
//        if(a>=0){
//            $("#onpage").text("第 " +(a+1)+ " 页");
//            $(".sui-table>tbody").html("");
//            for(var i =0;i<result.result.list.length;i++){
//                $(".sui-table>tbody").html($(".sui-table>tbody").html()+
//                        "<tr>"+
//                        "<td>"+
//                        "<input class='subSelect' type='checkbox' name='checkbox' value=\'"+result.result.list[i].commodityCode+"\'>"+
//                        "</td>"+
//                        "<td>" +
//                        result.result.list[i].name+
//                        "</td>"+
//                        "<td>"+
//                        result.result.list[i].commodityNum+
//                        "</td>"+
//                        "<td>"+
//                        result.result.list[i].specifications+
//                        "</td>"+
//                        "<td>"+
//                        result.result.list[i].price+
//                        "</td>"+
//                        "<td>"+
//                        "<a class='js-checkName' style='display: inline-block;width:30%;text-align:center' class=\'"+result.result.list[i].commodityCode+"\' onclick=updateCommodity(\'"+result.result.list[i].commodityCode+"\')>"+
//                        "修改"+
//                        "</a>"+
//                        "<a class='js-checkName' style='display: inline-block;width:30%;text-align:center' class=\'"+result.result.list[i].commodityCode+"\' onclick=deleteCommodity(\'"+result.result.list[i].commodityCode+"\')>"+
//                        "删除"+
//                        "</a>"+
//                        "</td>"+
//                        "</tr>"
//                )
//            }
//        }
//        else{
//
//        }
//    })
//});
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