/**
 * Created by aaa on 2016/9/26.
 */


$(function(){

    //$(".register-form-cell-widget").click(function(){
    //    alert(111)
    //});

    $(".lin_submit").click(function(){
            $.ajax({
                url: "http://192.168.0.186:8080/web/merchant/registerMerchantAccount",
                type: 'POST',
                dataType: 'JSONP',
                jsonp: "callbackparam", //服务端用于接收callback调用的function名的参数
                jsonpCallback: "web", //callback的function名称,服务端会把名称和data一起传递回来
                data: {
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
                    defaultChecked:true
                },
                success: function (data) {
                    console.log(data)
                }
            });
        }
    )});