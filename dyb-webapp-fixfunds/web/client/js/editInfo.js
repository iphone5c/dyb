$(function(){
    $("#saveMerchantInfo").click(function(){
        $.ajax({
            url: "http://192.168.0.186:8080/web/merchant/modifyMerchantByCurrent",
            type: 'get',
            dataType: 'JSONP',//here
            //jsonp: "callbackparam", //服务端用于接收callback调用的function名的参数
            //jsonpCallback: "web", //callback的function名称,服务端会把名称和data一起传递回来
            data: {
                businessStartTime:$("#edit_startBusinessTime").val(),//营业开始时间
                businessEndTime:$("#edit_endBusinessTime").val(),//营业结束时间
                mainBusiness:$("#edit_mainbusiness").val(),//主营业务
                industry:$("#edit_businessSpan").html(),//所在行业
                scale:$("#edit_sizeSpan").html(),//商家规模
                countryPhone:$("#edit_phone").val(),//企业电话
                merchantDescription:$("#edit_remark").val(),//商家简介
                principalName:$("#edit_principal").val(),//负责人姓名
                principalJobs:$("#edit_position").val(),//负责人岗位
                principalSex:$("#edit_sexSpan").html(),//负责人性别
                principalIdCard:$("#edit_idcard").val(),//负责人身份证号
                principalEmail:$("#edit_email").val(),//负责人邮箱地址
                bankName:$("#bankName").html(),//开户行
                bankBranch:$("#edit_subbankname").val(),//开户支行
                bankNum:$("#edit_bankacount").val(),//卡号
                bankAccountName:$("#edit_bankacount").val(),//开户名
            },
            success: function (data) {
                console.log(data)
                if(data.statusCode==1000){
                    window.location.reload();
                }
            }
        });
    })

    $.ajax({
        url: "http://192.168.0.186:8080/web/merchant/getMerchantByCurrent",
        type: 'post',
        dataType: 'JSONP',//here
        //jsonp: "callbackparam", //服务端用于接收callback调用的function名的参数
        //jsonpCallback: "web", //callback的function名称,服务端会把名称和data一起传递回来
        data: {
        },
        success: function (data) {
            //console.log(data)
            if(data.statusCode==1000){
                //console.log(data);
                $(".edit_companyname").html(data.result.merchant.merchant.merchantName);
                $("#edit_merchantType").html(data.result.merchant.merchant.merchantType);
                $("#edit_businessSpan").html(data.result.merchant.merchant.industry);
                $("#edit_sizeSpan").html(data.result.merchant.merchant.scale);
                $("#edit_mainbusiness").val(data.result.merchant.merchant.mainBusiness);
                $("#edit_phone").val(data.result.merchant.merchant.countryPhone);
                $("#bankName").html(data.result.bank.bankName);
                $("#edit_subbankname").val(data.result.bank.bankBranch);
                $("#edit_accountname").val(data.result.bank.bankAccountName);
                $("#edit_bankacount").val(data.result.bank.bankNum);
                $("#edit_startBusinessTime").val(data.result.merchant.merchant.businessStartTime);
                $("#edit_endBusinessTime").val(data.result.merchant.merchant.businessEndTime);
                $("#edit_remark").val(data.result.merchant.merchant.merchantDescription);
                $("#edit_principal").val(data.result.merchant.merchant.principalName);
                $("#edit_position").val(data.result.merchant.merchant.principalJobs);
                $("#edit_sexSpan").html(data.result.merchant.merchant.principalSex);
                $("#edit_idcard").val(data.result.merchant.merchant.principalIdCard);
                $("#edit_email").val(data.result.merchant.merchant.principalEmail);
            }
        }
    });
    // ajax请求 所在行业数据
    $.ajax({
        url: "http://192.168.0.113:8080/web/commons/getIndustry",
        type: 'post',
        dataType: 'JSONP',
        data: {
        },
        success: function (data) {
            //console.log(data)
            if(data.statusCode==1000){
                for(var i=0;i<data.result.length;i++){
                    $(".sui-dropdown-menu1").html( $(".sui-dropdown-menu1").html()+"<li><a  value='"+data.result[i].name+"'>"+data.result[i].value+"</a></li>")
                }
            }
        }
    });
    // ajax请求企业规模数据
    $.ajax({
        url: "http://192.168.0.113:8080/web/commons/getScale",
        type: 'post',
        dataType: 'JSONP',//here
        //jsonp: "callbackparam", //服务端用于接收callback调用的function名的参数
        //jsonpCallback: "web", //callback的function名称,服务端会把名称和data一起传递回来
        data: {
        },
        success: function (data) {
            console.log(data)
            if(data.statusCode==1000){
                for(var i=0;i<data.result.length;i++){
                    $(".sui-dropdown-menu2").html( $(".sui-dropdown-menu2").html()+"<li><a  value='"+data.result[i].name+"'>"+data.result[i].value+"</a></li>")
                }
            }
        }
    });
    // ajax请求性别数据
    $.ajax({
        url: "http://192.168.0.113:8080/web/commons/getSex",
        type: 'post',
        dataType: 'JSONP',
        data: {
        },
        success: function (data) {
            //console.log(data)
            if(data.statusCode==1000){
                for(var i=0;i<data.result.length;i++){
                    $(".sui-dropdown-menu4").html( $(".sui-dropdown-menu4").html()+"<li><a  value='"+data.result[i].name+"'>"+data.result[i].value+"</a></li>")
                }
            }
        }
    });

    $(".dropdown-toggle").eq(0).click(function () {
        $(".select1").toggleClass("open");
    })
    $(".sui-dropdown-menu1").on("click","li",function () {
        var i = $(".sui-dropdown-menu1 li").index(this);
        var x = $(".sui-dropdown-menu1 li a").eq(i).html();
        $(".sui-dropdown-menu1 li").removeClass("active");
        $(".sui-dropdown-menu1 li").eq(i).addClass("active");
        $(".select1 span span").html(x);
        $(".select1").removeClass("open");
    })
    $(".dropdown-toggle").eq(1).click(function () {
        $(".select2").toggleClass("open");
    })
    $(".sui-dropdown-menu2").on("click","li",function () {
        var i = $(".sui-dropdown-menu2 li").index(this);
        var x = $(".sui-dropdown-menu2 li a").eq(i).html();
        $(".sui-dropdown-menu2 li").removeClass("active");
        $(".sui-dropdown-menu2 li").eq(i).addClass("active");
        $(".select2 span span").html(x);
        $(".select2").removeClass("open");
    })
    $(".dropdown-toggle").eq(2).click(function () {
        $(".select3").toggleClass("open");
    })
    $(".sui-dropdown-menu3").on("click","li",function () {
        var i = $(".sui-dropdown-menu3 li").index(this);
        var x = $(".sui-dropdown-menu3 li a").eq(i).html();
        $(".sui-dropdown-menu3 li").removeClass("active");
        $(".sui-dropdown-menu3 li").eq(i).addClass("active");
        $(".select3 span span").html(x);
        $(".select3").removeClass("open");
    })
    $(".dropdown-toggle").eq(3).click(function () {
        $(".select4").toggleClass("open");
    })
    $(".sui-dropdown-menu4").on("click","li",function () {
        var i = $(".sui-dropdown-menu4 li").index(this);
        var x = $(".sui-dropdown-menu4 li a").eq(i).html();
        $(".sui-dropdown-menu4 li").removeClass("active");
        $(".sui-dropdown-menu4 li").eq(i).addClass("active");
        $(".select4 span span").html(x);
        $(".select4").removeClass("open");
    })
    $('.dropdown-inner').on("mouseleave",function () {
        $(".select1").removeClass("open");
        $(".select2").removeClass("open");
        $(".select3").removeClass("open");
        $(".select4").removeClass("open");
    });

})
