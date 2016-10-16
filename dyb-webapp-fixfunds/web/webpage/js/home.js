$(function(){
    // 获取菜单
    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return decodeURI(r[2]); return null;
    }
//    console.log(getQueryString("name"));
    var account=invokeService('/web/account/getCurrentAccount',{});
    var param={
        accountType:account.result.accountType
    };
    var data = invokeService('/web/account/getWebMenu',param);
    var fhurl=new Array();
    var addi=0;
    if (data.statusCode!=1000){
        alert(data.errorMessage);
        return;
    }
    for(var i=0;i<data.result.length;i++){
        var html ='';
        html+=
            "<li class='js-menu-parent-li' style='position: relative'>" +
            "<a class='js-menu-parent-li-a'>"+
            "<i class='ad-icon sui-icon iconfonts  icon-tb-emoji' style='margin-top:14px;'></i>"+
            "<span class='ad-menu-parent js-adShowHide'>"+data.result[i].text+"</span>"+
            "<i class='iconfont icons1' style='font-size: 16px'>&#xe60b;</i>"+
            "<i class='iconfont icons2'' style='font-size: 16px;display: none'>&#xe68b;</i>"+
            "</a>"+
            "<ul class='js-subMenu-absolute' style='display: none;'>";
        for(var y=0;y<data.result[i].children.length;y++){
            html+=
                "<li>" +
                "<a class='can_dispatch js-subActive'>"+data.result[i].children[y].text+"</a>" +
                "</li>";
            fhurl[addi]=data.result[i].children[y].url;
            addi++;
        }
        html+="</ul></li>";
        $("#leftmenu").html( $("#leftmenu").html()+html);
    }
    $("#leftmenu").on("click",".js-subActive",function(){
        var ss= $(".js-subActive").index(this);
        getpage1(fhurl[ss]);
//        console.log(fhurl);
    });
    function getpage1(url){
        $.get(url,function(data){
            $("#ad-content").html(data);
        })
    }
})
