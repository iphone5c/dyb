$(function(){
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
    $("#longitude").val(data.result.merchant.merchant.longitude);
    $("#latitude").val(data.result.merchant.merchant.latitude);
    $("#address").val(data.result.merchant.merchant.merchantAddress);
    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return decodeURI(r[2]); return null;
    }
    console.log(getQueryString("name"));
    var param={
        accountType:getQueryString("name")
    };
    var data = invokeService('/web/commons/getWebMenu',param);
    var fhurl=new Array();
    var addi=0;
    console.log(data);
    if (data.statusCode!=1000){
        alert(data.errorMessage);
        return;
    }
    for(var i=0;i<data.result.length;i++){
        var html ='';
        html+=
            "<li class='js-menu-parent-li' style='position: relative'>" +
            "<a class='js-menu-parent-li-a'>"+
            "<i class='ad-icon sui-icon iconfonts  icon-tb-emoji' style='margin-top:14px;'></i>"+
            "<span class='ad-menu-parent js-adShowHide'>"+data.result[i].text+"</span>"+
            "<i class='iconfont icons1' style='font-size: 16px'>&#xe60b;</i>"+
            "<i class='iconfont icons2'' style='font-size: 16px;display: none'>&#xe68b;</i>"+
            "</a>"+
            "<ul class='js-subMenu-absolute' style='display: none;'>";
        for(var y=0;y<data.result[i].children.length;y++){
            html+=
                "<li>" +
                "<a class='can_dispatch js-subActive'>"+data.result[i].children[y].text+"</a>" +
                "</li>";
            fhurl[addi]=data.result[i].children[y].url;
            addi++;
        }
        html+="</ul></li>";
        $("#leftmenu").html( $("#leftmenu").html()+html);
    }
    $("#leftmenu").on("click",".js-subActive",function(){
        var ss= $(".js-subActive").index(this);
        getpage1(fhurl[ss]);
//        console.log(fhurl);
    });
    function getpage1(url){
        $.get(url,function(data){
            $("#ad-content").html(data);

        })
    }
//    $.ajax({
//        url: "http://192.168.0.186:8080/web/merchant/getMerchantByCurrent",
//        type: 'post',
//        dataType: 'JSONP',//here
//        //jsonp: "callbackparam", //服务端用于接收callback调用的function名的参数
//        //jsonpCallback: "web", //callback的function名称,服务端会把名称和data一起传递回来
//        data: {
//        },
//        success: function (data) {
//            if(data.statusCode==1000){
//                //console.log(data.result)
//                $("#companyname").html(data.result.merchant.merchant.merchantName);
//                $("#usernumber").html(data.result.merchant.merchant.accountCode);
//                $("#drivetype").html(data.result.merchant.merchant.incentiveMode );
//                $("#shopname").html(data.result.merchant.merchant.shopName );
//                $("#merchantType").html(data.result.merchant.merchant.merchantType );
//                $("#businesstime").html(data.result.merchant.merchant.businessStartTime+'-'+data.result.merchant.merchant.businessEndTime);
//                $("#companyaddress").html(data.result.merchant.merchant.merchantAddress );
//                $("#mainbusiness").html(data.result.merchant.merchant.mainBusiness );
//                $("#industry").html(data.result.merchant.merchant.industryType);
//                $("#business").html(data.result.merchant.merchant.industry);
//                $("#size").html(data.result.merchant.merchant.scale);
//                $("#phone").html(data.result.merchant.merchant.countryPhone);
//                $("#bankname").html(data.result.bank.bankName);
//                $("#accountname").html(data.result.bank.bankAccountName);
//                $("#bankacount").html(data.result.bank.bankNum);
//                $("#createtime").html(data.result.bank.createTime);
//                $("#agentname").html(data.result.tjrRealName);
//                $("#agentphone").html(data.result.tjrPhone);
//                $("#remark").html(data.result.merchant.merchant.merchantDescription);
//                $("#principal").html(data.result.merchant.merchant.principalName);
//                $("#position").html(data.result.merchant.merchant.principalJobs);
//                $("#sex").html(data.result.merchant.merchant.principalSex);
//                $("#idcard").html(data.result.merchant.merchant.principalIdCard);
//                $("#email").html(data.result.merchant.merchant.principalEmail);
//                $("#spanTitle").html(data.result.merchant.merchant.accountPhone);
//                $("#longitude").val(data.result.merchant.merchant.longitude);
//                $("#latitude").val(data.result.merchant.merchant.latitude);
//                $("#address").val(data.result.merchant.merchant.merchantAddress);
//            }
//        }
//    });

//    $.ajax({
//        url: "http://192.168.0.186:8080/web/commons/getWebMenu",
//        type: 'post',
//        dataType: 'JSONP',//here
//        //jsonp: "callbackparam", //服务端用于接收callback调用的function名的参数
//        //jsonpCallback: "web", //callback的function名称,服务端会把名称和data一起传递回来
//        data: {
//            accountType:getQueryString("name")
//        },
//        success: function (data) {
//            if(data.statusCode==1000){
//                //console.log(data)
//                for(var i=0;i<data.result.length;i++){
//                    var html ='';
//                    html+=
//                        "<li class='js-menu-parent-li' style='position: relative'>" +
//                            "<a class='js-menu-parent-li-a'>"+
//                                "<i class='ad-icon sui-icon iconfonts  icon-tb-emoji' style='margin-top:14px;'></i>"+
//                                "<span class='ad-menu-parent js-adShowHide'>"+data.result[i].text+"</span>"+
//                                "<i class='iconfont icons1' style='font-size: 16px'>&#xe60b;</i>"+
//                                "<i class='iconfont icons2'' style='font-size: 16px;display: none'>&#xe68b;</i>"+
//                            "</a>"+
//                            "<ul class='js-subMenu-absolute' style='display: none;'>";
//                            for(var y=0;y<data.result[i].children.length;y++){
//                                html+=
//                                    "<li>" +
//                                         "<a class='can_dispatch js-subActive'>"+data.result[i].children[y].text+"</a>" +
//                                    "</li>";
//                            }
//                    html+="</ul></li>";
//                    $("#leftmenu").html( $("#leftmenu").html()+html);
//                }
//            }
//        }
//    });
})
