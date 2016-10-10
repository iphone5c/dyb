
$(function(){
    function getpage(url){
        $.get(url,function(data){
            $("#ad-content").html(data)
        })
    }
    getpage("./Merchant-sj/home.html");
    var s=true;
    $("#leftmenu").on("click",".js-menu-parent-li-a",function(){
        var i=$(".js-menu-parent-li-a").index(this);
        var x=$(".js-menu-parent-li").eq(i).height();
        if(x>52){
            $(".js-subMenu-absolute").hide(500);
            $(".icons1").eq(i).show();
            $(".icons2").eq(i).hide();
            s=true;
        }
       else  if(s==true){
            $(".js-subMenu-absolute").hide(500);
            $(".js-menu-parent-li i").eq(1).hide();
            $(".icons1").show();
            $(".icons2").hide();
            $(".icons1").eq(0).show();
            $(".icons1").eq(i).hide();
            $(".icons2").eq(i).show();
            $(".js-subMenu-absolute").eq(i).show(500);
            s=false;
        }
        else if(s==false){
            $(".js-subMenu-absolute").hide(500);
            $(".icons1").show();
            $(".icons2").hide();
            $(".icons1").eq(0).show();
            $(".icons1").eq(i).hide();
            $(".icons2").eq(i).show();
            $(".js-subMenu-absolute").eq(i).show(500);
            s=true;
        }
    })
    var n=true;
    $("#menuZoom").click(function(){
        if(n==true){
            $(".icons1").hide();
            $(".icons2").hide();
            $("#menu").animate({marginRight:"-60px",width:"60px"});
            $(".js-adShowHide").hide();
            $("#content").animate({marginLeft:"70px"});
            $(".js-subMenu-absolute").hide();
            n=false;
        }else if(n==false){
            $("#menu").animate({marginRight:"-220px",width:"220px"});
            $(".js-adShowHide").show();
            $(".icons1").show();
            $(".icons2").show();
            $("#content").animate({marginLeft:"230px"});
            n=true;
        }
    })
})