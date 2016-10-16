$(function(){
    // 获取当前登录商家信息
    var data = invokeService('/web/merchant/getMerchantByCurrent',{});
    console.log(data);
    if (data.statusCode!=1000){
        alert(data.errorMessage);
        return;
    }
    $("#companyname").html(data.result.merchant.merchant.merchantName);
    $("#usernumber").html(data.result.merchant.merchant.accountCode);
    $("#drivetype").html(data.result.merchant.merchant.incentiveMode );
    $("#shopname").html(data.result.merchant.merchant.shopName );
    $("#merchantType").html(data.result.merchant.merchant.merchantType );
    $("#businesstime").html(data.result.merchant.merchant.businessStartTime+'-'+data.result.merchant.merchant.businessEndTime);
    $("#companyaddress").html(data.result.merchant.merchant.merchantAddress );
    $("#mainbusiness").html(data.result.merchant.merchant.mainBusiness );
    $("#industry").html(data.result.merchant.merchant.industryType);
    $("#business").html(data.result.merchant.merchant.industry);
    $("#size").html(data.result.merchant.merchant.scale);
    $("#phone").html(data.result.merchant.merchant.countryPhone);
    $("#bankname").html(data.result.bank.bankName);
    $("#accountname").html(data.result.bank.bankAccountName);
    $("#bankacount").html(data.result.bank.bankNum);
    $("#createtime").html(data.result.bank.createTime);
    $("#agentname").html(data.result.tjrRealName);
    $("#agentphone").html(data.result.tjrPhone);
    $("#remark").html(data.result.merchant.merchant.merchantDescription);
    $("#principal").html(data.result.merchant.merchant.principalName);
    $("#position").html(data.result.merchant.merchant.principalJobs);
    $("#sex").html(data.result.merchant.merchant.principalSex);
    $("#idcard").html(data.result.merchant.merchant.principalIdCard);
    $("#email").html(data.result.merchant.merchant.principalEmail);
    $("#spanTitle").html(data.result.merchant.merchant.accountPhone);
//    $("#longitude").val(data.result.merchant.merchant.longitude);
//    $("#latitude").val(data.result.merchant.merchant.latitude);
//    $("#address").val(data.result.merchant.merchant.merchantAddress);
})
