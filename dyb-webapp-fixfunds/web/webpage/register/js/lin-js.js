/**
 * Created by aaa on 2016/9/26.
 */


$(function(){
    $(".btn_timepicki").timepicki();
    //$(".register-form-cell-widget").click(function(){
    //    alert(111)
    //});
    function submit(){
        var flag=$("#flag").val();
        var param={
            // 账户名
            accountName:$("#username").val(),
            // 账户密码
            password:$("#userpwd").val(),
            //二级密码
            tradePassword:$("#password2").val(),
            // 绑定手机号
            accountPhone:$("#mobilenumber").val(),
            // 推荐人
            referrerCode:"1",
            // 商家名称
            merchantName:$("#companyname").val(),
            // 激励模式
            incentiveMode:$("#drivetypeSpan").text(),
            // 店铺名称
            shopName:$("#shopname").val(),
            // 商家类型
            merchantType:"商家",
            // 营业开始时间
            businessStartTime:$("#startBusinessTime").val(),
            // 营业结束时间
            businessEndTime:$("#endBusinessTime").val(),
            // 商家地址
            merchantAddress:$("#provinceid-s").text()+$("#cityid-s").text()+$("#companyaddress").val(),
            // 省级代码
            province:$("#provinceid-s").text(),
            // 市级代码
            city:$("#cityid-s").text(),
            // 主营业务
            mainBusiness:$("#mainbusiness").val(),
            // 行业类别
            industryType:"红木",//$("#industryName").text()
            // 所在行业
            industry:"YL",//$("#businessSpan").text()
            // 商家规模
            scale:"A",//$("#sizeSpan").text()
            // 企业电话
            countryPhone:$("#phone").val(),
            // 商家简介
            merchantDescription:$("#remark").val(),
            // 负责人姓名
            principalName:$("#principal").val(),
            // 负责人岗位
            principalJobs:$("#position").val(),
            // 负责人性别
            principalSex:"男",//$("#sex").val()
            // 负责人身份证号
            principalIdCard:$("#idcard").val(),
            //负责人生日
            birthday:$("#birthday").val().toLocaleString(),
            // 负责人邮箱地址
            principalEmail:$("#email").val(),
            // 证件资料文件路径
            certificateFile:"2",//$("#up_img_WU_FILE_2").val()
            // 开户行
            bankName:$("#bankName").text(),
            // 开户支行
            bankBranch:$("#subbankname").val(),
            // 卡号
            bankNum:$("#bankNumber").val(),
            // 开户名称
            bankAccountName:$("#acountname").val(),
            // 默认设置选定
            defaultChecked:true,
            //执照类型1：表示新版  2：表示旧版
            flag:flag,
            //营业执照第一张
            businessLicensePhoto1:flag==1?$("#businessLicensePhoto1").val():$("#oldBusinessLicensePhoto1").val(),
            //营业执照第二张
            businessLicensePhoto2:flag==1?"":$("#oldBusinessLicensePhoto2").val(),
            //法人身份证照片
            legalPersonPhoto:$("#legalPersonPhoto").val(),
            //推荐人身份证照片
            recommendPersonPhoto:$("#recommendPersonPhoto").val(),
            //捐赠承诺书照片
            donationPhoto:$("#donationPhoto").val(),
            //店面门头照照片
            storePhoto:$("#storePhoto").val()
        }
        var data = invokeService('/web/merchant/registerMerchantAccount',param);
        if (data.statusCode!=1000){
            alert(data.errorMessage);
            return;
        }
        alert(data.flag)
    }
    $(".lin_submit").click(function(){
            submit();
        }
    )
});