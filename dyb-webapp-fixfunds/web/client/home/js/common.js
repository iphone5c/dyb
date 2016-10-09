//headtop服务下拉
$(function(){
	$(".headtop .headtopL li").click(function(){
		$(this).toggleClass("hover");
		$(this).children(".subnav").slideToggle(300).siblings("hover").slideUp("slow");
	})
});

//header导航
$(function(){
	$(".header .fr a").click(function(){
		$(this).addClass("hover").siblings().removeClass("hover");
	})
});

//一点学堂
$(function(){
	$(".educationLink li").click(function(){
		$(this).addClass("hover").siblings().removeClass("hover");	
	})
});

//首页顶部跳转
$(function(){
	$("#one").click(function(){
		var one = $("#onepoint").offset();
		$("body,html").animate({
		   scrollTop:one.top
		},200)
	})
	$("#two").click(function(){
		var two = $("#example").offset();
		$("body,html").animate({
		   scrollTop:two.top - 150
		},200)
	})
	$("#three").click(function(){
		var three = $("#commonweal").offset();
		$("body,html").animate({
		   scrollTop:three.top - 150
		},200)
	})
	$("#four").click(function(){
		var four = $("#news").offset();
		$("body,html").animate({
		   scrollTop:four.top - 150
		},200)
	})
});

//超过40px隐藏headtop
$(function(){
	$(window).scroll(function(){ 
		var scrollh = document.documentElement.scrollTop + document.body.scrollTop; 
	    if( scrollh > 40){ 
	    	$(".header").addClass("headertop");
	        $(".headertop").fadeIn(400); //淡出
	    }else{
	    	$(".header").removeClass("headertop");
	    }
	})
});

//评论
$(function(){
	$(".shareBtn").click(function(){
		$(".tempcont .comment").toggleClass("hover");	
	}) 
});

//右侧悬浮
$(function(){
	$(".floatingRight li").click(function(){
		$(this).toggleClass("hover").siblings().removeClass("hover");;	
	})
});