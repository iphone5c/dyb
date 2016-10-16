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
            var messengerBean=$("#beannum").val();
            //    二级密码
            var tradePassword=$("#password").val();

            var tr=$("#tbody .contentLine .num0")
            var tr2=$("#tbody .contentLine .money0")
            var conversionInvoiceDetailses=[]
            for(var i=0;i<tr.length;i++){
                conversionInvoiceDetailses[i]= {
                    invoiceNum:tr.eq(i).val(),
                    invoiceMoney:tr2.eq(i).val()
                }

            }

            var param={
                messengerBeanType:messengerBeanType,
                messengerBean:messengerBean,
                tradePassword:tradePassword,
                conversionInvoiceDetailses:conversionInvoiceDetailses
//                invoiceNum:invoiceNum,
//                invoiceMoney:invoiceMoney
            }
            console.log(conversionInvoiceDetailses)
            var result=invokeService('/web/merchant/conversion/messengerBeanConversion',param);
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
//    $(arguments[0]).parent().parent().remove()
    deltr=function(obj)
    {
            $(arguments[0]).parent().parent().remove();
    }
})

