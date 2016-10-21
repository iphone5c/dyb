$(function(){
    // 非空
    var rgempty=/\S+/;
    // 只能是数字，字母，汉字
    var regular=/^[\u4E00-\u9FA5A-Za-z0-9_]+$/;
    // 长度2-16
    var regleg1=/^.{2,16}$/;
    // 长度6-16
    var regleg2=/^\w{6,16}$/;
    // 长度4
    var regleg3=/^.{4}$/;
    // 长度6-25
    var regleg4=/^.{6,25}$/;
    // 长度25
    var regleg5=/.{26}$/;
    // 长度30
    var regleg6=/.{31}$/;
    // 长度150
    var regleg7=/.{151}$/;
    // 长度500
    var regleg8=/.{501}$/;
    // 长度2-50
    var regleg9=/^.{2,50}$/;
    // 长度2-25
    var regleg10=/^.{2,25}$/;
    // 长度6
    var regleg11=/.{6}$/;
    // 长度2-10
    var regleg12=/^.{2,10}$/;
    // 手机
    var regphone = /0?(13|14|15|18)[0-9]{9}/;
    // 电话
    var regtelephone=/[0-9-()（）]{6,18}/;
    // 数字
    var regnum=/^([+-]?)\d*\.?\d+$/;
    // 身份证
    var regcard=/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
    // 邮箱
    var regemail=/^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+(\.[a-zA-Z]{2,3})+$/;
    function empty(idText,html,num){
        if(rgempty.test(idText)){
            $(".yz-error").eq(num).html("");
        }else {
            $(".yz-error").eq(num).html(html);
            return false;
        }
    }
    function judge(idText,price,html,num){
        if(idText != price){
            $(".yz-error").eq(num).html("");
        }else {
            $(".yz-error").eq(num).html(html);
            return false;
        }
    }
    // 用户名格式验证
    function usernames(){
        var username=$("#username").val();
        if(!rgempty.test(username)){
            $(".yz-error").eq(0).html("用户名不能为空!");
            return false;
        }else if(!regular.test(username)){
            $(".yz-error").eq(0).html("格式错误!");
            return false;
        }else if(!regleg1.test(username)){
            $(".yz-error").eq(0).html("长度不能小于2并且不能超过16!");
            return false;
        }else{
            $(".yz-error").eq(0).html("");
        }
    }
    // 密码格式验证
    function userpwds(){
        var userpwd=$("#userpwd").val();
        if(!rgempty.test(userpwd)){
            $(".yz-error").eq(1).html("密码不能为空!");
            return false;
        }else if(!regleg2.test(userpwd)){
            $(".yz-error").eq(1).html("密码必须是6-16位!");
            return false;
        } else{
            $(".yz-error").eq(1).html("");
        }
    }
    // 确认密码格式验证
    function passwords(){
        var password=$("#password").val();
        var userpwd=$("#userpwd").val();
        if(!rgempty.test(password)){
            $(".yz-error").eq(2).html("密码不能为空!");
            return false;
        }else if(password !== userpwd){
            $(".yz-error").eq(2).html("密码不一致!");
            return false;
        }else{
            $(".yz-error").eq(2).html("");
        }
    }
    // 手机号格式验证
    function mobilenumbers(){
        var mobilenumber=$("#mobilenumber").val();
        if(!rgempty.test(mobilenumber)){
            $(".yz-error").eq(3).html("手机号不能为空!");
            return false;
        }else if(!regphone.test(mobilenumber)){
            $(".yz-error").eq(3).html("手机号格式有误!");
            return false;
        }else{
            $(".yz-error").eq(3).html("");
        }
    }
    // 图文验证码格式验证
    function imgCodes(){
        var imgCode=$("#imgCode").val();
        if(!rgempty.test(imgCode)){
            $(".yz-error").eq(4).html("图文验证码不能为空!");
            return false;
        }else if(!regleg3.test(imgCode)){
            $(".yz-error").eq(4).html("长度不能少于4!");
            return false;
        }else{
            $(".yz-error").eq(4).html("");
        }
    }
    // 手机验证码格式验证
    function verifyCodes(){
        var verifyCode=$("#verifyCode").val();
        var imgCode=$("#imgCode").val();
        if(!rgempty.test(verifyCode)){
            $(".yz-error").eq(5).html("手机验证码不能为空!");
            return false;
        }else if(imgCode==""){
            $(".yz-error").eq(5).html("请先填写图文验证码!");
        }else{
            $(".yz-error").eq(5).html("");
        }
    }

    $("#username").on("keyup blur change",function(){
        usernames();
    });
    $("#userpwd").on("keyup blur change",function(){
        userpwds();
    });
    $("#password").on("keyup blur change",function(){
        passwords();
    })
    $("#mobilenumber").on("keyup blur change",function(){
        mobilenumbers()
    })
    $("#imgCode").on("keyup blur change",function(){
        imgCodes();
    })
    $("#verifyCode").on("keyup blur change",function(){
        verifyCodes();
    })

    $("#nextReg1").click(function(){
        var result=new Array();
//        console.log(result.length)
        var username=$("#username").val();
        result[0] = empty(username,"用户名不能为空!",0);
        var userpwd=$("#userpwd").val();
        result[1] = empty(userpwd,"密码不能为空!",1);
        var password=$("#password").val();
        result[2] = empty(password,"密码不能为空!",2);
        var mobilenumber=$("#mobilenumber").val();
        result[3] = empty(mobilenumber,"手机号不能为空!",3);
        var imgCode=$("#imgCode").val();
        result[4] = empty(imgCode,"图文验证不能为空!",4);
        var verifyCode=$("#verifyCode").val();
        result[5] = empty(verifyCode,"图文验证不能为空!",5);
        result[0] =usernames();
        result[1] =userpwds();
        result[2] =passwords();
        result[3] =mobilenumbers();
        result[4] =imgCodes();
        result[5] =verifyCodes();
        if($('#readProtocol').is(':checked')==true){
            $(".yz-error").eq(6).html("");
        }else{
            $(".yz-error").eq(6).html("请选择!");
            return false;
        }
        for(var i=0;i<result.length;i++){
            if(result[i]==false){
               return false;
            }
        }

        $(".ad-register1").css({"display":"none"});
        $(".ad-register3").css({"display":"none"});
        $(".ad-register4").css({"display":"none"});
        $(".ad-register5").css({"display":"none"});
        $(".ad-register2").css({"display":"block"});
    });
    // 企业名称格式验证
    function companynames(){
        var companyname=$("#companyname").val();
        if(!rgempty.test(companyname)){
            $(".yz-error").eq(7).html("企业名称不能为空!");
            return false;
        }else if(!regular.test(companyname)){
            $(".yz-error").eq(7).html("格式错误!");
            return false;
        }else if(!regleg4.test(companyname)){
            $(".yz-error").eq(7).html("长度不能小于6并且不能超过25!");
            return false;
        }else{
            $(".yz-error").eq(7).html("");
        }
    }
    // 店铺名称格式验证
    function shopnames(){
        var shopname=$("#shopname").val();
        if(!rgempty.test(shopname)){
            $(".yz-error").eq(8).html("店铺名称不能为空!");
            return false;
        }else if(!regular.test(shopname)){
            $(".yz-error").eq(8).html("格式错误!");
            return false;
        }else if(regleg5.test(shopname)){
            $(".yz-error").eq(8).html("长度不能超过25!");
            return false;
        }else{
            $(".yz-error").eq(8).html("");
        }
    }
    // 详细地址个验证
    function companyaddresss(){
        var companyaddress=$("#companyaddress").val();
        if(!rgempty.test(companyaddress)){
            $(".yz-error").eq(10).html("详细地址不能为空!");
            return false;
        }else if(regleg6.test(companyaddress)){
            $(".yz-error").eq(10).html("长度不能超过30!");
            return false;
        }else{
            $(".yz-error").eq(10).html("");
        }
    }
    // 主营业务格式验证
    function mainbusinesss(){
        var mainbusiness=$("#mainbusiness").val();
        if(!rgempty.test(mainbusiness)){
            $(".yz-error").eq(13).html("主营业务不能为空!");
            return false;
        }else if(regleg7.test(mainbusiness)){
            $(".yz-error").eq(13).html("长度不能超过150!");
            return false;
        }else{
            $(".yz-error").eq(13).html("");
        }
    }
    // 企业电话格式验证
    function phones(){
        var phone=$("#phone").val();
        if(!rgempty.test(phone)){
            $(".yz-error").eq(15).html("企业电话不能为空!");
            return false;
        }else if(!regtelephone.test(phone)){
            $(".yz-error").eq(15).html("企业电话格式错误!");
            return false;
        }else{
            $(".yz-error").eq(15).html("");
        }
    }
    // 开户支行格式验证
    function subbanknames(){
        var subbankname=$("#subbankname").val();
        if(!rgempty.test(subbankname)){
            $(".yz-error").eq(19).html("开户支行不能为空!");
            return false;
        }else if(!regular.test(subbankname)){
            $(".yz-error").eq(19).html("格式错误!");
            return false;
        }else if(!regleg9.test(subbankname)){
            $(".yz-error").eq(19).html("长度不能小于2并且不能超过50!");
            return false;
        }else{
            $(".yz-error").eq(19).html("");
        }
    }
    // 开户名格式验证
    function acountnames(){
        var acountname=$("#acountname").val();
        if(!rgempty.test(acountname)){
            $(".yz-error").eq(20).html("开户名不能为空!");
            return false;
        }else if(!regular.test(acountname)){
            $(".yz-error").eq(20).html("格式错误!");
            return false;
        }else if(!regleg10.test(acountname)){
            $(".yz-error").eq(20).html("长度不能小于2并且不能超过25!");
            return false;
        }else{
            $(".yz-error").eq(20).html("");
        }
    }
    // 银行账号格式验证
    function bankNumbers(){
        var bankNumber=$("#bankNumber").val();
        if(!rgempty.test(bankNumber)){
            $(".yz-error").eq(21).html("银行账号不能为空!");
            return false;
        }else if(!regnum.test(bankNumber)){
            $(".yz-error").eq(21).html("请输入数字!");
            return false;
        }else if(!regleg11.test(bankNumber)){
            $(".yz-error").eq(21).html("长度不能小于6!");
            return false;
        }else{
            $(".yz-error").eq(21).html("");
        }
    }
    // 商家简介格式验证
    function remarks(){
        var remark=$("#remark").val();
        if(!rgempty.test(remark)){
            $(".yz-error").eq(22).html("商家简介不能为空!");
            return false;
        }else if(regleg8.test(remark)){
            $(".yz-error").eq(22).html("长度不能超过500!");
            return false;
        }else{
            $(".yz-error").eq(22).html("");
        }
    }
    // 二级密码格式验证
    function password2s(){
        var password2=$("#password2").val();
        if(!rgempty.test(password2)){
            $(".yz-error").eq(23).html("二级密码不能为空!");
            return false;
        }else if(!regnum.test(password2)){
            $(".yz-error").eq(23).html("请输入数字!");
            return false;
        }else if(!regleg11.test(password2)){
            $(".yz-error").eq(23).html("长度不能小于6!");
            return false;
        }else{
            $(".yz-error").eq(23).html("");
        }
    }
    // 确认二级密码格式验证
    function pwds(){
        var pwd=$("#pwd").val();
        var password2=$("#password2").val();
        if(!rgempty.test(pwd)){
            $(".yz-error").eq(24).html("密码不能为空!");
            return false;
        }else if(pwd !== password2){
            $(".yz-error").eq(24).html("密码不一致!");
            return false;
        }else{
            $(".yz-error").eq(24).html("");
        }
    }
    // 姓名格式验证
    function principals(){
        var principal=$("#principal").val();
        if(!rgempty.test(principal)){
            $(".yz-error").eq(25).html("姓名不能为空!");
            return false;
        }else if(!regleg12.test(principal)){
            $(".yz-error").eq(25).html("长度必须是2-10位!");
            return false;
        }else{
            $(".yz-error").eq(25).html("");
        }
    }
    // 岗位格式验证
    function positions(){
        var position=$("#position").val();
        if(!rgempty.test(position)){
            $(".yz-error").eq(26).html("岗位不能为空!");
            return false;
        }else if(!regleg12.test(position)){
            $(".yz-error").eq(26).html("长度不能小于2并且不能大于10!");
            return false;
        }else{
            $(".yz-error").eq(26).html("");
        }
    }
    // 身份证格式验证
    function idcards(){
        var idcard=$("#idcard").val();
        if(!rgempty.test(idcard)){
            $(".yz-error").eq(29).html("身份证不能为空!");
            return false;
        }else if(!regcard.test(idcard)){
            $(".yz-error").eq(29).html("身份证格式错误!");
            return false;
        }else{
            $(".yz-error").eq(29).html("");
        }
    }
    // 邮箱格式验证
    function emails(){
        var email=$("#email").val();
        if(!rgempty.test(email)){
            $(".yz-error").eq(30).html("邮箱不能为空!");
            return false;
        }else if(!regemail.test(email)){
            $(".yz-error").eq(30).html("邮箱格式错误!");
            return false;
        }else{
            $(".yz-error").eq(30).html("");
        }
    }

    $("#companyname").on("keyup blur change",function(){
        companynames();
    });
    $("#shopname").on("keyup blur change",function(){
        shopnames();
    });
    $("#companyaddress").on("keyup blur change",function(){
        companyaddresss();
    });
    $("#mainbusiness").on("keyup blur change",function(){
        mainbusinesss();
    });
    $("#phone").on("keyup blur change",function(){
        phones();
    });
    $("#remark").on("keyup blur change",function(){
        remarks();
    });
    $("#subbankname").on("keyup blur change",function(){
        subbanknames();
    });
    $("#acountname").on("keyup blur change",function(){
        acountnames();
    });
    $("#bankNumber").on("keyup blur change",function(){
        bankNumbers();
    });
    $("#password2").on("keyup blur change",function(){
        password2s();
    });
    $("#pwd").on("keyup blur change",function(){
        pwds();
    });
    $("#principal").on("keyup blur change",function(){
        principals();
    });
    $("#idcard").on("keyup blur change",function(){
        idcards();
    });
    $("#email").on("keyup blur change",function(){
        emails();
    });
    // 请选择行业格式验证
    $(".sui-dropdown-menu5").on("click","a",function(){
        $(".yz-error").eq(11).html("");
    });
    // 请选择行业类别格式验证
    $(".sui-dropdown-menu6").on("click","a",function(){
        $(".yz-error").eq(12).html("");
    });
    // 请选择企业规模格式验证
    $(".sui-dropdown-menu7").on("click","a",function(){
        $(".yz-error").eq(14).html("");
    });
    // 请选择开户行格式验证
    $(".sui-dropdown-menu8").on("click","a",function(){
        $(".yz-error").eq(18).html("");
    });
    // 请选择性别格式验证
    $(".sui-dropdown-menu9").on("click","a",function(){
        $(".yz-error").eq(27).html("");
    });
    // 请选择出生日期格式验证
    $("#birthday").change(function(){
        $(".yz-error").eq(28).html("");
    });
    // 选择省市格式验证
    $('#s_city').on('change',function() {
        $(".yz-error").eq(9).html("");
    });
    function layoutSex(){
        var idcard=$("#idcard").val();
        var birthday=$("#birthday").val();
        var sexSpan=$("#sexSpan").html();
        var att = birthday.split("-");
        var attr=att[0]+att[1]+att[2];
        var idcardsex = idcard.substr(14,1);
        var idcardsexs = idcard.substr(14,3);
        if(idcard.length == 18){
            var sexy = idcardsexs % 2;
            if(sexy == 0){
                if(sexSpan == "男"){
                    alert("蛤蟆皮，性别与身份证不符合");
                    return false;

                }
            }else if(sexy==1){
                if(sexSpan == "女"){
                    alert("蛤蟆皮，性别与身份证不符合");
                    return false;
                }
            }
        }
        if(idcard.length == 15){
            var attrs= attr.substr(2,6);
            var sexi = idcardsex % 2;
            if(sexi==0){
                if(sexSpan == "男"){
                    alert("蛤蟆皮，性别与身份证不符合");
                    return false;
                }
            }else if(sexi==1){
                if(sexSpan == "女"){
                    alert("蛤蟆皮，性别与身份证不符合");
                    return false;
                }
            }
        }

    }
    function layoutData(){
        var idcard=$("#idcard").val();
        var birthday=$("#birthday").val();
        var sexSpan=$("#sexSpan").html();
        var idcardatt = idcard.substr(6,8);
        var att = birthday.split("-");
        var attr=att[0]+att[1]+att[2];
        if(idcard.length == 18){
            if(idcardatt != attr){
                alert("妈卖批，出生日期与身份证不符合");
                return false;
            }
        }
        if(idcard.length == 15){
            var attrs= attr.substr(2,6);
            if(idcardatt != attrs){
                alert("妈卖批，出生日期与身份证不符合");
                return false;
            }
        }
    }
    $("#nextReg2").click(function(){
        var result=new Array();
        result[0] =companynames();
        result[1] =shopnames();
        result[2] =companyaddresss();
        var businessSpan=$("#businessSpan").html();
        result[3] = judge(businessSpan,"请选择","请选择行业!",11);
        var industryName=$("#industryName").html();
        result[4] = judge(industryName,"请选择","请选择行业类别!",12);
        result[5] =mainbusinesss();
        var sizeSpan=$("#sizeSpan").html();
        result[6] = judge(sizeSpan,"请选择规模","请选择企业规模!",14);
        result[7] =phones();
        var startBusinessTime=$("#startBusinessTime").val();
        result[8] = empty(startBusinessTime,"请填写!",16);
        var endBusinessTime=$("#endBusinessTime").val();
        result[9] = empty(endBusinessTime,"请填写!",17);
        var bankName=$("#bankName").html();
        result[10] = judge(bankName,"请选择开户行","请选择开户行!",18);
        result[11] =subbanknames();
        result[12] =acountnames();
        result[13] =bankNumbers();
        result[14] =remarks();
        result[15] =password2s();
        result[16] =pwds();
        result[17] =principals();
        result[18] =positions();
        var sexSpan=$("#sexSpan").html();
        result[19] = judge(sexSpan,"请选择","请选择性别!",27);
        var birthday=$("#birthday").val();
        result[20] = empty(birthday,"出生日期不能为空!",28);
        result[21] =idcards();
        result[22] =emails();
        var s_province=$("#s_province").val();
        var s_city=$("#s_city").val();
        result[23] = layoutData();
        result[24] = layoutSex();
        if(s_province=="省份" || s_city=="地级市"){
            $(".yz-error").eq(9).html("请选择!");
            return false;
        }else {
            $(".yz-error").eq(9).html("");
        }
        for(var i=0;i<result.length;i++){
            if(result[i]==false){
               return false;
            }
        }
        $(".ad-register1").css({"display":"none"});
        $(".ad-register2").css({"display":"none"});
        $(".ad-register4").css({"display":"none"});
        $(".ad-register5").css({"display":"none"});
        $(".ad-register3").css({"display":"block"})
    });
})
