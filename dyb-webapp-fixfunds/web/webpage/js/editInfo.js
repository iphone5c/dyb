$(function(){
    getData();
    // 非空
    var rgempty=/\S+/;
    // 长度2-14
    var regleg1=/^.{2,14}$/;
    // 长度6
    var regleg2=/.{14}$/;
    // 长度50
    var regleg3=/.{50}$/;
    // 长度30
    var regleg4=/.{30}$/;
    // 长度500
    var regleg5=/.{500}$/;
    // 长度2-10
    var regleg6=/^.{2,10}$/;
    // 电话
    var regtelephone=/[0-9-()（）]{6,18}/;
    // 数字
    var regnum=/^([+-]?)\d*\.?\d+$/;
    // 身份证
    var regcard=/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
    // 邮箱
    var regemail=/^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+(\.[a-zA-Z]{2,3})+$/;
    $(".edit_error").css("color","#ea4a36");
    // 修改当前登陆商家信息
    // 主营业务格式验证
    function mainbusinesss(){
        var edit_mainbusiness=$("#edit_mainbusiness").val();
        if(!rgempty.test(edit_mainbusiness)){
            $(".edit_error").eq(0).html("主营业务不能为空!");
            return false;
        }else{
            $(".edit_error").eq(0).html("");
        }
    }
    // 电话格式验证
    function phones(){
        var edit_phone=$("#edit_phone").val();
        if(!rgempty.test(edit_phone)){
            $(".edit_error").eq(1).html("请填写!");
            return false;
        }else if(!regtelephone.test(edit_phone)){
            $(".edit_error").eq(1).html("电话格式错误!");
            return false;
        }else if(regleg2.test(edit_phone)){
            $(".edit_error").eq(1).html("长度不能超过14!");
            return false;
        }else{
            $(".edit_error").eq(1).html("");
        }
    }
    // 开户支行格式验证
    function subbanknames(){
        var edit_subbankname=$("#edit_subbankname").val();
        if(!rgempty.test(edit_subbankname)){
            $(".edit_error").eq(2).html("请填写!");
            return false;
        }else if(regleg3.test(edit_subbankname)){
            $(".edit_error").eq(2).html("长度不能超过50!");
            return false;
        }else{
            $(".edit_error").eq(2).html("");
        }
    }
    // 开户名格式验证
    function accountnames(){
        var edit_accountname=$("#edit_accountname").val();
        if(!rgempty.test(edit_accountname)){
            $(".edit_error").eq(3).html("请填写!");
            return false;
        }else if(regleg3.test(edit_accountname)){
            $(".edit_error").eq(3).html("长度不能超过50!");
            return false;
        }else{
            $(".edit_error").eq(3).html("");
        }
    }
    // 银行账号格式验证
    function bankacounts(){
        var edit_bankacount=$("#edit_bankacount").val();
        if(!rgempty.test(edit_bankacount)){
            $(".edit_error").eq(4).html("请填写!");
            return false;
        }else if(!regnum.test(edit_bankacount)){
            $(".edit_error").eq(4).html("请输入数字!");
            return false;
        }else if(regleg4.test(edit_bankacount)){
            $(".edit_error").eq(4).html("长度不能超过30!");
            return false;
        }else{
            $(".edit_error").eq(4).html("");
        }
    }
    // 商家简介格式验证
    function remarks(){
        var edit_remark=$("#edit_remark").val();
        if(!rgempty.test(edit_remark)){
            $(".edit_error").eq(5).html("请填写!");
            return false;
        }else if(regleg5.test(edit_remark)){
            $(".edit_error").eq(5).html("长度不能超过500!");
            return false;
        }else{
            $(".edit_error").eq(5).html("");
        }
    }
    // 姓名格式验证
    function principals(){
        var edit_principal=$("#edit_principal").val();
        if(!rgempty.test(edit_principal)){
            $(".edit_error").eq(6).html("姓名不能为空!");
            return false;
        }else if(!regleg6.test(edit_principal)){
            $(".edit_error").eq(6).html("格式错误!");
            return false;
        }else{
            $(".edit_error").eq(6).html("");
        }
    }
    // 岗位格式验证
    function positions(){
        var edit_position=$("#edit_position").val();
        if(!rgempty.test(edit_position)){
            $(".edit_error").eq(7).html("岗位不能为空!");
            return false;
        }else{
            $(".edit_error").eq(7).html("");
        }
    }
    // 身份证格式验证
    function idcards(){
        var edit_idcard=$("#edit_idcard").val();
        if(!rgempty.test(edit_idcard)){
            $(".edit_error").eq(8).html("身份证不能为空!");
            return false;
        }else if(!regcard.test(edit_idcard)){
            $(".edit_error").eq(8).html("身份证格式错误!");
            return false;
        }else{
            $(".edit_error").eq(8).html("");
        }
    }
    // 邮箱格式验证
    function emails(){
        var edit_email=$("#edit_email").val();
        if(!rgempty.test(edit_email)){
            $(".edit_error").eq(9).html("邮箱不能为空!");
            return false;
        }else if(!regemail.test(edit_email)){
            $(".edit_error").eq(9).html("邮箱格式错误!");
            return false;
        }else{
            $(".edit_error").eq(9).html("");
        }
    }
    $("#edit_mainbusiness").on("keyup blur change",function(){
        mainbusinesss();
    });
    $("#edit_phone").on("keyup blur change",function(){
        phones();
    });
    $("#edit_subbankname").on("keyup blur change",function(){
        subbanknames();
    });
    $("#edit_accountname").on("keyup blur change",function(){
        accountnames();
    });
    $("#edit_bankacount").on("keyup blur change",function(){
        bankacounts();
    });
    $("#edit_remark").on("keyup blur change",function(){
        remarks();
    });
    $("#edit_principal").on("keyup blur change",function(){
        principals();
    });
    $("#edit_position").on("keyup blur change",function(){
        positions();
    });
    $("#edit_idcard").on("keyup blur change",function(){
        idcards();
    });
    $("#edit_email").on("keyup blur change",function(){
        emails();
    });
    $("#saveMerchantInfo").click(function(){
        var result=new Array();
        result[0] = mainbusinesss();
        result[1] = phones();
        result[2] = subbanknames();
        result[3] = accountnames();
        result[4] = bankacounts();
        result[5] = remarks();
        result[6] = principals();
        result[7] = positions();
        result[8] = idcards();
        result[9] = emails();
        for(var i=0;i<result.length;i++){
            if(result[i]==false){
                return false;
            }
        }
       var param={
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
            bankAccountName:$("#edit_accountname").val()//开户名
        };
        var data = invokeService('/web/merchant/modifyMerchantByCurrent',param);
        if (data.statusCode!=1000){
            alert(data.errorMessage);
            return;
        }else if(data.statusCode ==1000){
                $(".sui-modal").addClass("in");
                $(".sui-modal-backdrop").css("zIndex","1000").addClass("in");
//                window.location.reload();
        }
    });
    $(".sui-close,.sui-btn").click(function(){
        $(".sui-modal").removeClass("in");
        $(".sui-modal-backdrop").css("zIndex","-1").removeClass("in");
        window.location.reload();
    })
    // 获取当前登陆商家信息
    var data = invokeService('/web/merchant/getMerchantByCurrent',{});
//    console.log(data)
    if (data.statusCode!=1000){
        alert(data.errorMessage);
        return;
    }
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
    $("#imgUrlDiv img").attr("src",data.result.certificateFile.storePhoto);
    // 取消修改
    $("#cancleMerchantInfo").click(function(){
        window.location.reload();
    })
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
