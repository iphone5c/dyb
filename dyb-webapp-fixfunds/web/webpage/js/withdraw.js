/**
 * Created by aaa on 2016/10/17.
 */
$(function(){
//    下拉框
    $("#select").click(function(){
        $(".select").toggleClass("open")
    });
    $("#withdrawtype-ul li").click(function(){
        var i=$("#withdrawtype-ul li").index(this);
        var x= $("#withdrawtype-ul li a").eq(i).html();
        $("#withdrawtype-ul li").removeClass("active");
        $("#withdrawtype-ul li").eq(i).addClass("active");
//        alert(11)
        $(".select").removeClass("open");
        $("#size_s").html(x);
    });

//初始化
    $(function(){
        var param={};
        var result=invokeService('/web/merchant/messengerbean/getMessengerBeanTypeByCurrent',param);
        $("#size_s").text(result.result[0].value);
        $(".xg_a").eq(0).text(result.result[0].value);
        $(".xg_a").eq(1).text(result.result[1].value);

        //2.        获取信使豆,手续费~~~~~
        var withdrawalType=$("#size_s").text();
        var param={
            withdrawalType:withdrawalType
        };
        var result=invokeService('/web/merchant/withdrawal/getWithdrawalApplyEdit',param);
        $("#couriercount").text(result.result.messengerBean);
        //手续费
        $("#span-line>span").text(result.result.poundage.systemParamsValue);
        $("#tax_tip1").hide();
        $("#tax_tip2").hide();
        $("#beannum").keyup(function(){
            var x=$("#beannum").val();
            var y=$("#span-line>span").text();
            var o=x-y;
            var p=Number(0);
            if(o<=80){
                $("#beanmoney").text(p)
            }else{
                $("#beanmoney").text(o)
            }
        });
        //3.        获取默认银行信息
        var param={};
        var result=invokeService('/web/merchant/bankaccount/getBankAccountByDefaultChecked',param);
//                console.log(result)
        $("#bankname").text(result.result.bankName);
        $("#showbranchBank").text(result.result.bankBranch);
        $("#accountname").text(result.result.bankAccountName);
        $("#bankacount").text(result.result.bankNum)

    })

    // 普通信使豆
    $("#withdrawtype-ul>li").eq(0).click(function(){
            $("#Invoice-detail").hide(200);

        //初始化页面
        //1.        获取信使豆类型
        var param={};
        var result=invokeService('/web/merchant/messengerbean/getMessengerBeanTypeByCurrent',param);
        $("#size_s").text(result.result[0].value);
        $(".xg_a").eq(0).text(result.result[0].value);
        $(".xg_a").eq(1).text(result.result[1].value);

        //2.        获取信使豆,手续费~~~~~
        var withdrawalType=$("#size_s").text();
        var param={
            withdrawalType:withdrawalType
        };
        var result=invokeService('/web/merchant/withdrawal/getWithdrawalApplyEdit',param);
//        console.log(result);
        $("#couriercount").text(result.result.messengerBean);
        //手续费
        $("#span-line>span").text(result.result.poundage.systemParamsValue);
        $("#tax_tip1").hide();
        $("#tax_tip2").hide();
        $("#beannum").keyup(function(){
            var x=$("#beannum").val();
            var z=$("#taxbean").text();
            var o=x-z;
            var p=Number(0);
            if(o<=80){
                $("#beanmoney").text(p)
            }else{
                $("#beanmoney").text(o)
            }
        });
        //3.        获取默认银行信息
        var param={};
        var result=invokeService('/web/merchant/bankaccount/getBankAccountByDefaultChecked',param);
//                console.log(result)
        $("#bankname").text(result.result.bankName);
        $("#showbranchBank").text(result.result.bankBranch);
        $("#accountname").text(result.result.bankAccountName);
        $("#bankacount").text(result.result.bankNum)
    });
    // 待提供发票
    $("#withdrawtype-ul>li").eq(1).click(function(){
            $("#Invoice-detail").show(200);
        //2.        获取信使豆,手续费~~~~~
        var withdrawalType=$("#size_s").text();
        var param={
            withdrawalType:withdrawalType
        };
        var result=invokeService('/web/merchant/withdrawal/getWithdrawalApplyEdit',param);
        console.log(result);
        $("#couriercount").text(result.result.messengerBean);
        //手续费
        $("#span-line>span").text(result.result.poundage.systemParamsValue);

        //扣税比例
        var j=result.result.deductions;
        $("#tax_tip1").show();
        $("#tax_tip2").show();
        //2.1 计算代扣税
        var k=0;
        function tax(){
            k=Number($("#beannum").val())*Number(j);
            return k;
        }
        $("#taxbean").text(tax());
        $("#beannum").keyup(function(){
            $("#taxbean").text(tax());
            //2.2  计算可兑换金额
            var x=$("#beannum").val();
            var y=$("#span-line>span").text();
            var z=$("#taxbean").text();
            var o=x-y-z;
            var p=Number(0)
            if(o<=80){
                $("#beanmoney").text(p)
            }else{
                $("#beanmoney").text(o)
            }
        });
        //3.        获取默认银行信息
        var param={};
        var result=invokeService('/web/merchant/bankaccount/getBankAccountByDefaultChecked',param);
//                console.log(result)
        $("#bankname").text(result.result.bankName);
        $("#showbranchBank").text(result.result.bankBranch);
        $("#accountname").text(result.result.bankAccountName);
        $("#bankacount").text(result.result.bankNum);

        //    增加发票明细条数
        $("#Invoice-detail-add").click(function(){
            $("#tbody").append(
                    "<tr class='contentLine'>"+
                    "<td>"+
                    "<input class='num0' type='text'>"+
                    "</td>"+
                    "<td>"+
                    "<input class='money0' type='text'>"+
                    "</td>"+
                    "<td>"+
                    "<a class='Invoice-delet' onclick='deltr(this);'>"+
                    "删除"+
                    "</a>"+
                    "</td>"+
                    "</tr>"
            )})
//        计算合计

       function sun(){
           var w = $(".money0").length;
           var hj=0; parseInt(hj);
           for(var i=0;i<=w;i++){
               var num=$(".money0").eq(i).val();
               if(empty(num)){
                   num=0;
               }else{
                   num=Number(num);
               }
               hj+=num;
           }
           return hj;
       }

        $("#tbody").on("keyup",".money0",function(){
                $("#Invoice-Total").text(sun())
        })
//   删除发票条数
        deltr=function(obj)
        {
            $(arguments[0]).parent().parent().remove();
            $("#Invoice-Total").text(sun())
        }
    })

//    提交
        $("#submitForm").click(function(){
            if($("#size_s").text()=="普通信使豆"){
                var withdrawalType=$("#size_s").text();
                var withdrawalNum=$("#beannum").val();
                var tradePassword=$("#password").val();
                var param={
                    withdrawalType:withdrawalType,
                    withdrawalNum:withdrawalNum,
                    tradePassword:tradePassword
                };
                var result=invokeService('/web/merchant/withdrawal/withdrawalMessengerBean',{withdrawalMessengerBeanParam:JSON.stringify(param)})
                if(result.statusCode!=1000){
                    alert(result.errorMessage);
                    return;
                }
                alert("成功")
            }else if($("#size_s").text()=="待提供发票"){
                var withdrawalType=$("#size_s").text();
                var withdrawalNum=$("#beannum").val();
                var tradePassword=$("#password").val();
                var tr=$("#tbody tr").length
                var Invoice=[];
                    for(var i=0;i<=tr;i++){
                        Invoice[i]={
                            invoiceNum:$(".num0").eq(i).text(),
                            invoiceMoney:$(".money0").eq(i).text()
                        }
                    }
                var param={
                    withdrawalType:withdrawalType,
                    withdrawalNum:withdrawalNum,
                    tradePassword:tradePassword,
                    invoiceList:Invoice
                };
                var result=invokeService('/web/merchant/withdrawal/withdrawalMessengerBean',{withdrawalMessengerBeanParam:JSON.stringify(param)})
                if(result.statusCode!=1000){
                    alert(result.errorMessage);
                    return;
                }
                alert("成功")
            }
        })










































});