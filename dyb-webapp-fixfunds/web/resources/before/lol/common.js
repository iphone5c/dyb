/**
 * 同步请求
 * @param url 请求路径
 * @param param 请求参数
 */
function invokeService(url,param){
    var result;
    $.ajax({
        type: "POST",
        url: url,
        data: param,
        async:false,
        success: function(data, textStatus, jqXHR){
            result=data;
        }
    });
    return result;
}

/**
 * 异步请求
 * @param url 请求路径
 * @param param 请求参数
 */
function invokeSyncService(url,param){
    var result;
    $.ajax({
        type: "POST",
        url: url,
        data: param,
        async:true,
        success: function(data, textStatus, jqXHR){
            result=data;
            return result;
        }
    });
}

/**
 * 判空
 * @param info
 */
function isEmpty(info){
    if(info==null || info==undefined || info==''){
        return true;
    }else{
        return false;
    }
}

/**
 * 获取验证码
 * @param me 当前对象
 */
function getKaptchaImage(me){
    $(me).attr('src','/lol/account/jsp/getKaptchaImage');
}