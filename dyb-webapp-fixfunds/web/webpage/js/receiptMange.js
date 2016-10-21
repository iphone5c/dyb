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
    $("#table").on("click",".btn_sql",function(){
        $(".sui-modal").addClass("in");
        $(".sui-modal-backdrop").css("zIndex","1").addClass("in");
    });
    $(".sui-close").click(function(){
        $(".sui-modal").removeClass("in");
        $(".sui-modal-backdrop").css("zIndex","-1").removeClass("in");
    })
    $("#cancelButton").click(function(){
        $(".sui-modal").removeClass("in");
        $(".sui-modal-backdrop").css("zIndex","-1").removeClass("in");
    })
});

//    让利款发票申请列表
function getdonatelist(pageIndex){
    var param={
        //    当前页
        pageIndex:pageIndex,
        //        显示条数
        pageSize:1
    };
    var result=invokeService('/web/merchant/invoiceapply/getInvoiceApplyPageList',param)
    if(result.statusCode!=1000){
        alert(result.errorMessage);
        return;
    }
    var pageCount=result.result.pageCount;
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
                "<a class='btn_sql' id=\'"+result.result.list[i].invoiceApplyCode+"\'>"+
                "申请发票"+
                "</a>"+
                "</td>"+
                "</tr>"
        )
        return pageCount;
    }
//获取申请数据的Code
    $(".btn_sql").click(function(){
        var nice=$(".btn_sql").index(this);
        var invoiceApplyCode = $(".btn_sql").eq(nice).attr("id");

//    申请发票
        var param={
//        申请数据的Code
            invoiceApplyCode:invoiceApplyCode
        };

        var result=invokeService('/web/merchant/invoiceapply/getInvoiceApplyByCode',param);
        if(result.statusCode!=1000){
            alert(result.errorMessage);
            return;
        }
//          商家名称
        $("#companyname").text(result.result.merchant.merchantName);
//          商家地址
        $("#companyaddress").text(result.result.merchant.merchantAddress);
//        商家ID
        $("#busernumber").text(result.result.accountCode);
//        开户行
        $("#bankname").text(result.result.bankAccount.bankName);
//        开户支行
        $("#subbankname").text(result.result.bankAccount.bankBranch);
//        开户名
        $("#accountname").text(result.result.bankAccount.bankAccountName);
//        卡号
        $("#bankacount").text(result.result.bankAccount.bankNum);
//        年月
        $("#yearmonth").text(result.result.invoiceApply.invoiceTime);
//        已交让利款
        $("#returnamount").text(result.result.invoiceApply.yetBenefitPrice);
//        申请发票
        $("#applyamount").val(result.result.invoiceApply.applyInvoiceMoney);
//        快递费
        $("#expressfee2").text(result.result.invoiceApply.poundage);
//        收件地址
        $("#postaddress").text(result.result.invoiceApply.address);
//        收件人
        $("#postname").text(result.result.invoiceApply.receiver);

//          手机
        $("#postphone").text(result.result.invoiceApply.phone);
//  申请发票确定
        var invoiceApplyCode2 = result.result.invoiceApply.invoiceApplyCode;

        $("#okButton").click(function() {
            var countryPhone = $("#companyphone").val();
            var taxpayers =  $("#paytaxno").val();
            var param = {
//            申请数据的Code
                invoiceApplyCode:invoiceApplyCode2,
//                公司座机
                countryPhone:countryPhone,
//                纳税人识别号
                taxpayers:taxpayers
            };
            var result = invokeService('/web/merchant/invoiceapply/getInvoiceApplyByCode', param);
            if (result.statusCode != 1000) {
                alert(result.errorMessage)
                return
            }
            alert("已发出申请")
            $(".sui-modal").removeClass("in");
            $(".sui-modal-backdrop").css("zIndex","-1").removeClass("in");
        })
    });
}
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