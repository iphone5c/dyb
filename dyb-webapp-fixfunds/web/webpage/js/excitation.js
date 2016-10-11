    // 獲取我的激勵
    function getexcitation(){
        var param={
            pageIndex:0,
            pageSize:5
        }
        var data = invokeService('/web/merchant/accountincentive/getAccountIncentivePageList',param);
        console.log(data)
        if (data.statusCode!=1000){
            alert(data.errorMessage);
            return;
        }
    }

$(function(){
    getexcitation();
})