/**
 * Created by Administrator on 2016/10/8.
 */
var service='127.0.0.1';
var port='8080';

/**
 * 同步请求
 * @param url
 * @param param
 * @returns {*}
 */
function invokeService(url,param){
    var result;
    $.ajax({
        url: getServiceUrl(url),
        type: 'POST',
        async:false,
        data: param,
        success: function (data) {
            if(data.statusCode==9999){
                //跳转登录页面
                window.location.href='';
            }
            result=data;
        }
    });
    return result;
}

/**
 * 异步请求
 * @param url
 * @param param
 */
function invokeSyncService(url,param){
    $.ajax({
        url: getServiceUrl(url),
        type: 'POST',
        async:true,
        data: param,
        success: function (data) {
//            if(data.statusCode==9999){
//                //跳转登录页面
//                window.location.href='';
//            }
//            return data;
        }
    });
}

/**
 * 拼接url全路径
 * @param url
 * @returns {string}
 */
function getServiceUrl(url,param){
    var url =  url + "?v=" + (new Date()).valueOf();
    var params = this.getServiceParams(param);
    if (params != null)
        url += "&" + encodeURI(params.params);
    return url;
}

function getServiceParams (param) {
    return param ? { params: Ext.util.JSON.encode(param)} : null;
}