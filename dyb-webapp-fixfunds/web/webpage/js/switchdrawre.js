/**
 * Created by aaa on 2016/10/15.
 */


$(function(){
    $("#Invoice-detail").show();

//   获取转换类型
    var param={}
    var result=invokeService('/web/commons/getDonationType',param)
    $("#converttype-v").text(result.result[1].value);


//    转换类型
    var messengerBeanType= $("#converttype-v").text();


//    提交密码和转换数量
        $("#submitForm").click(function(){
            //    转换数量
            var messengerBean=$("#beannum").val()
            //    二级密码
            var tradePassword=$("#password").val()
            // 发票号
            var invoiceNum=$("#num0").val()
            // 发票面额
            var invoiceMoney=$("#money0").val()
            var param={
                messengerBeanType:messengerBeanType,
                messengerBean:messengerBean,
                tradePassword:tradePassword,
                conversionInvoiceDetailses:[
                    {invoiceNum:invoiceNum,invoiceMoney:invoiceMoney}
                ]
//                invoiceNum:invoiceNum,
//                invoiceMoney:invoiceMoney
            }
            console.log(param)
            var result=invokeService('/web/merchant/conversion/messengerBeanConversion',param);
            console.log(result)
            console.log(param)
            if(result.statusCode!=1000){
                alert(result.errorMessage);
                return;
            }
            alert("成功")
        })
//    增加发票明细条数
    $("#Invoice-detail-add").click(function(){
        $("#tbody").html(
                $("#tbody").html()+
                "<tr>"+
                "<td>"+
                    "<input id='num0' type='text'>"+
                    "</td>"+
                "<td>"+
                    "<input id='money0' type='text'>"+
                    "</td>"+
                "<td>"+
                    "<a id='Invoice-delet' onclick='deltr(this);'>"+
                    "删除"+
                    "</a>"+
                "</td>"+
                "</tr>"
        )})
//    $(arguments[0]).parent().parent().remove()
    deltr=function(obj)
    {
            $(arguments[0]).parent().parent().remove();
    }
})

