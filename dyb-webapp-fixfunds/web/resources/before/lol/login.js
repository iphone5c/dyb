/**
 * 登陆
 */
function submitLogin(){
    var loginAccount = $('#loginAccount').val();
    var password = $('#password').val();
    var param={
        loginAccount:loginAccount,
        password:password
    }
    var result = invokeService('/lol/account/json/login',param);
    if(result.statusCode!=1000){
        alert(result.errorMessage)
        return false;
    }
    window.location.href="/lol/main/jsp/mainIndex";
}

/**
 * 注册
 */
function submitSignup(){
    var loginName = $('#loginName').val();
    var loginPassword = $('#loginPassword').val();
    var repassword = $('#repassword').val();
    var withdrawalsPassword = $('#withdrawalsPassword').val();
    var securityCode = $('#securityCode').val();

    var errorMessage="";
    if(isEmpty(loginName)){
        errorMessage="登录账户不能为空";
    }else if(isEmpty(loginPassword)){
        errorMessage="登录密码不能为空";
    }else if(isEmpty(repassword)){
        errorMessage="确认密码不能为空";
    }else if(isEmpty(withdrawalsPassword)){
        errorMessage="取款密码不能为空";
    }else if(isEmpty(securityCode)){
        errorMessage="验证码不能为空";
    }else if(loginPassword!=repassword){
        errorMessage="两次密码输入不一致";
    }
    if(!isEmpty(errorMessage)){
        alert(errorMessage);
        return false;
    }

    var param={
        loginAccount:loginName,
        password:loginPassword,
        withdrawalsPassword:withdrawalsPassword,
        securityCode:securityCode
    }
    var result = invokeService('/lol/account/json/registerAccount',param);
    if(result.statusCode!=100){
        alert(result.errorMessage)
        return false;
    }
    window.location.href="/lol/main/jsp/mainIndex";
}

/**
 * 注销当前登陆用户
 * @returns {boolean}
 */
function logout(){
    var result = invokeService('/lol/account/json/logout',{});
    if(result.statusCode!=1000){
        alert(result.errorMessage)
        return false;
    }
    window.location.href="/lol/main/jsp/mainIndex";
}