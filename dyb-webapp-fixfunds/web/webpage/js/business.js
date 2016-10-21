$(function(){
    // 获取当前登录商家信息
    var data = invokeService('/web/merchant/getMerchantByCurrent',{});
//    console.log(data);
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
    $("#phone").html(data.result.merchant.merchant.accountPhone);
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
    $("#mobilenumber").html(data.result.merchant.merchant.countryPhone);
    if(data.result.certificateFile.flag==0){// 旧版营业执照
        $("#businesslicenceNew_img").hide();
        $("#businesslicence_img").show();
        $("#taxregistration_img").show();
        $("#businesslicence_img").attr("src",data.result.certificateFile.businessLicensePhoto1);
        $("#taxregistration_img").attr("src",data.result.certificateFile.businessLicensePhoto2);
    }else if(data.result.certificateFile.flag==1){ // 新版营业执照
        $("#businesslicenceNew_img").show();
        $("#businesslicence_img").hide();
        $("#taxregistration_img").hide();
        $("#businesslicenceNew_img").attr("src",data.result.certificateFile.businessLicensePhoto1);
    }
    $("#idCard_img").attr("src",data.result.certificateFile.legalPersonPhoto);// 法人身份证
    $("#idthtree_img").attr("src",data.result.certificateFile.recommendPersonPhoto);// 推荐人身份证
    $("#idfour_img").attr("src",data.result.certificateFile.donationPhoto);// 捐赠承诺书
    $("#imgUrl_img").attr("src",data.result.certificateFile.storePhoto);// 店面门头照照片
});
