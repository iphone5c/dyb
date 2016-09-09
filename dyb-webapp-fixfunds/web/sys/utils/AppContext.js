/**
 * Created by Administrator on 2016/3/1.
 */
Ext.define('DYB.utils.AppContext', {

    invokeSyncService: function (service, op, param, scope, successCallback, fiallCallback) {
        Ext.Ajax.request({
            url: this.getServiceUrl(service, op),
            method: "POST",
            async: true,   //ASYNC 是否异步( TRUE 异步 , FALSE 同步)
            params: param,
            timeout: 6000000,
            scope: this,
            callbackName: name,
            success: function (response, opts) {
                var result = Ext.util.JSON.decode(response.responseText);
                var status= response.getResponseHeader('statusFlag');
                if (status == 1002) {
                    Ext.Msg.alert('提示', '会话超时，请重新登录!',function(){
                        window.location = '/index.jsp';
                    });
                }
                if (typeof successCallback === 'function') {
                    successCallback.call(scope || window, result.result, response, opts);
                }


            }, //请求成功的回调函数
            failure: function (response, opts) {
                if (typeof fiallCallback === 'function') {
                    fiallCallback.call(scope || window, {exceptionCode: response.status, exceptionMessage: response.statusText}, response, opts);
                }
            }  // 请求失败的回调函数
        });
    },
    /**
     * 同步调用服务
     * @param service 服务名称
     * @param op 操作名称
     * @param param 参数
     * @returns {*}
     */
    invokeService: function (service, op, param) {
        var result;
        Ext.Ajax.request({
            url: this.getServiceUrl(service, op),
            method: "POST",
            async: false,   //ASYNC 是否异步( TRUE 异步 , FALSE 同步)
            params: param,
            scope: this,
            callback:function(opts,flag,response){
                console.log(response)
                if(response.responseText==''){
                    var info='{"statusCode":1000,"result":""}'
                    result=Ext.util.JSON.decode(info);
                }else{
                    result = Ext.util.JSON.decode(response.responseText);
                }
                result.response=response;
                result.opts=opts;
            }
//            success: function (response, opts) {
//                var status= response.getResponseHeader('statusFlag');
//                if(response.responseText==''){
//                    var info='{"result":0,"success":true}';
//                    result = Ext.util.JSON.decode(info);
//                }else{
//                    result = Ext.util.JSON.decode(response.responseText);
//                }
//                result.response = response;
//                result.opts = opts;
//                if (status == 1002) {
//                    result.success = false;
//                    result.errorMessage = '没有登录或登录已过期，请重新登录！';
//                    Ext.Msg.alert('提示', '会话超时，请重新登录!',function(){
//                        window.location = '/index.jsp';
//                    });
//                }
//                if (status == 1003) {
//                    result.success = false;
//                    result.errorMessage = '你无此操作权限，请联系管理员';
//                }
//            }, //请求成功的回调函数
//            failure: function (response, opts) {
//                result = {exceptionCode: response.status, exceptionMessage: response.statusText};
//                result.response = response;
//                result.opts = opts;
//            }  // 请求失败的回调函数
        });
        return result;
    },

    getServiceUrl: function (service, op, param) {
        var url =  service +  op + "?v=" + (new Date()).valueOf();
        var params = this.getServiceParams(param);
        if (params != null)
            url += "&" + encodeURI(params.params);
        return url;
    },
    getServiceParams: function (param) {
        return param ? { params: Ext.util.JSON.encode(param)} : null;
    }
});