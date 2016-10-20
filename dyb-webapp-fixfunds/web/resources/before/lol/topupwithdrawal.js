/**
 * 充值
 */
function topup(){
    var tradeNumber = $('#tradeNumber').val();
    var applyMoney = $('#applyMoney').val();
    var tradeType = $('#tradeType').val();
    var errorMessage="";
    if(isEmpty(tradeNumber)){
        errorMessage="充值账号不能为空";
    }else if(isEmpty(applyMoney)){
        errorMessage="充值金额不能为空";
    }else if(isEmpty(tradeType)){
        errorMessage="充值方式不能为空";
    }else if(applyMoney<0){
        errorMessage="充值金额不能小于零";
    }
    if(!isEmpty(errorMessage)){
        alert(errorMessage);
        return false;
    }

    var param={
        applyMoney: applyMoney,
        tradeType:tradeType,
        tradeNumber:tradeNumber
    }

    var result = invokeService('/lol/topupwithdrawal/json/applyTopUp',param);
    if(result.statusCode!=1000){
        alert(result.errorMessage)
        return false;
    }
    alert("充值成功");
    $('#confirmTop').val('继续充值')
//    window.location.href="/lol/topupwithdrawal/jsp/topUpWithdrawal";
}