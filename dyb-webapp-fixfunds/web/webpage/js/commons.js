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
                window.location.href='./home/login.html';
                return;
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
function invokeSyncService(url,param,success,failer){
    $.ajax({
        url: getServiceUrl(url),
        type: 'POST',
        async:true,
        data: param,
        success: function (data, textStatus, jqXHR) {
            if(data.statusCode==9999){
                //跳转登录页面
                window.location.href='./home/login.html';
            }
            success(data, textStatus, jqXHR);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown){
            failer(XMLHttpRequest, textStatus, errorThrown);
        }
    });
}

/**
 * 同步上传文件请求
 * @param url 请求地址
 * @param id file文件的控件的ID
 * @param name file文件的控件name
 * @returns {*}
 */
function uploadService(url,id,name){
    var result;
    var formData = new FormData();
    formData.append(name, $('#'+id)[0].files[0]);
    $.ajax({
        url: getServiceUrl(url),
        type: 'POST',
        cache: false,
        async:false,
        data: formData,
        processData: false,
        contentType: false
    }).done(function(res) {
        result=res;
    }).fail(function(res) {
        result=res;
    });
    return result;
}

/**
 * 异步上传文件请求
 * @param url 请求地址
 * @param id file文件的控件的ID
 * @param name file文件的控件name
 * @param success 成功之后的回调函数
 * @param fail 失败之后的回调函数
 */
function uploadSyncService(url,id,name,success,fail){
    var formData = new FormData();
    formData.append(name, $('#'+id)[0].files[0]);
    $.ajax({
        url: getServiceUrl(url),
        type: 'POST',
        cache: false,
        async:true,
        data: formData,
        processData: false,
        contentType: false
    }).done(function(res) {
        success(res);
    }).fail(function(res) {
        fail(res);
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


function getData(){
    // ajax请求 所在行业数据 industry
    var data = invokeService('/web/commons/getIndustry',{});
    console.log(data)
    if (data.statusCode!=1000){
        alert(data.errorMessage);
        return;
    }
    if(data.statusCode==1000){
        for(var i=0;i<data.result.length;i++){
            $(".industry").html( $(".industry").html()+"<li><a  value='"+data.result[i].name+"'>"+data.result[i].value+"</a></li>")
        }
    }
//    // ajax请求企业规模数据 scale
    var data = invokeService('/web/commons/getScale',{});
    console.log(data)
    if (data.statusCode!=1000){
        alert(data.errorMessage);
        return;
    }
    if(data.statusCode==1000){
        for(var i=0;i<data.result.length;i++){
            $(".scale").html( $(".scale").html()+"<li><a  value='"+data.result[i].name+"'>"+data.result[i].value+"</a></li>")
        }
    }
//    // ajax请求性别数据 gender
    var data = invokeService('/web/commons/getSex',{});
    console.log(data)
    if (data.statusCode!=1000){
        alert(data.errorMessage);
        return;
    }
    if(data.statusCode==1000){
        for(var i=0;i<data.result.length;i++){
            $(".gender").html( $(".gender").html()+"<li><a  value='"+data.result[i].name+"'>"+data.result[i].value+"</a></li>")
        }
    }
    // ajax 请求行业类别 industryType
    var data = invokeService('/web/commons/getIndustryType',{});
    console.log(data)
    if (data.statusCode!=1000){
        alert(data.errorMessage);
        return;
    }
    if(data.statusCode==1000){
        for(var i=0;i<data.result.length;i++){
            $(".industryType").html( $(".industryType").html()+"<li><a  value='"+data.result[i].name+"'>"+data.result[i].value+"</a></li>")
        }
    }
}

