$(function(){
    var rgempty=/\S+/;
    var regular=/^[A-Za-z0-9\u4e00-\u9fa5]+$/;
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
    var regular=/^[\u4E00-\u9FA5A-Za-z0-9_]+$/;
    $("#username").on("keyup blur",function(){
        var username=$("#username").val();
        if(regular.test(username)){
            $(".yz-error").eq(0).html("");
        }else if(!rgempty.test(username)){
            $(".yz-error").eq(0).html("用户名不能为空！");
        }else{
            $(".yz-error").eq(0).html("格式错误！");
        }
    })
    $("#nextReg1").click(function(){
        var result=new Array();
        //console.log(result.length)
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
    $("#nextReg2").click(function(){
        var result=new Array();
        //console.log(result.length)
        var companyname=$("#companyname").val();
        result[0] = empty(companyname,"企业名称不能为空!",7);
        var shopname=$("#shopname").val();
        result[1] = empty(shopname,"店铺名称不能为空!",8);
        var companyaddress=$("#companyaddress").val();
        result[2] = empty(companyaddress,"详细地址不能为空!",10);
        var businessSpan=$("#businessSpan").html();
        result[3] = judge(businessSpan,"请选择","请选择行业!",11);
        var industryName=$("#industryName").html();
        result[4] = judge(industryName,"请选择","请选择行业类别!",12);
        var mainbusiness=$("#mainbusiness").val();
        result[5] = empty(mainbusiness,"主营业务不能为空!",13);
        var sizeSpan=$("#sizeSpan").html();
        result[6] = judge(sizeSpan,"请选择规模","请选择企业规模!",14);
        var phone=$("#phone").val();
        result[7] = empty(phone,"企业电话不能为空!",15);
        var startBusinessTime=$("#startBusinessTime").val();
        result[8] = empty(startBusinessTime,"请填写!",16);
        var endBusinessTime=$("#endBusinessTime").val();
        result[9] = empty(endBusinessTime,"请填写!",17);
        var bankName=$("#bankName").html();
        result[10] = judge(bankName,"请选择开户行","请选择开户行!",18);
        var subbankname=$("#subbankname").val();
        result[11] = empty(subbankname,"开户支行不能为空!",19);
        var acountname=$("#acountname").val();
        result[12] = empty(acountname,"开户名不能为空!",20);
        var bankNumber=$("#bankNumber").val();
        result[13] = empty(bankNumber,"银行账号不能为空!",21);
        var remark=$("#remark").val();
        result[14] = empty(remark,"请填写!",22);
        var password2=$("#password2").val();
        result[15] = empty(password2,"二级密码不能为空!",23);
        var pwd=$("#pwd").val();
        result[16] = empty(pwd,"确认密码不能为空!",24);
        var principal=$("#principal").val();
        result[17] = empty(principal,"姓名不能为空!",25);
        var position=$("#position").val();
        result[18] = empty(position,"岗位不能为空!",26);
        var sexSpan=$("#sexSpan").html();
        result[19] = judge(sexSpan,"请选择","请选择性别!",27);
        var birthday=$("#birthday").val();
        result[20] = empty(birthday,"出生日期不能为空!",28);
        var idcard=$("#idcard").val();
        result[21] = empty(idcard,"身份证号不能为空!",29);
        var email=$("#email").val();
        result[22] = empty(email,"邮箱不能为空!",30);
        console.log(startBusinessTime);
        var s_province=$("#s_province").val();
        var s_city=$("#s_city").val();
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
