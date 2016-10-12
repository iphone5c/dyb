// 獲取直捐記錄
function getwithdrawrecord(pageIndex){
//    var pageArray=[];
    var param={
        pageIndex:pageIndex,
        pageSize:1
    }
    var data = invokeService('/web/merchant/withdrawal/getWithdrawalPageList',param);
//    console.log(data)
    if (data.statusCode!=1000){
        alert(data.errorMessage);
        return;
    }
    var pageCount=data.result.pageCount;
    $("#table tbody").html("");
    for(var i=0;i<data.result.list.length;i++){
        $("#table tbody").html( $("#table tbody").html()+
                "<tr>" +
                "<td><a class='js-checkName' id='"+data.result.list[i].withdrawalCode+"'>"+data.result.list[i].withdrawalCode+"</a></td>"+
                "<td>"+data.result.list[i].withdrawalType+"</td>"+
                "<td>"+data.result.list[i].applyWithdrawalNum+"</td>"+
                "<td>"+data.result.list[i].deductions+"</td>"+
                "<td>"+data.result.list[i].poundage+"</td>"+
                "<td>"+data.result.list[i].withdrawalNum+"</td>"+
                "<td>"+data.result.list[i].bankNum+"</td>"+
                "<td>"+data.result.list[i].applyTime+"</td>"+
                "<td>"+data.result.list[i].applyStatus+"</td>"+
                "<td></td>"+
                "</tr>"
        )
    }
    return pageCount;
}
$(function(){
    var pageIndex=0;
    var pageCount = getwithdrawrecord(pageIndex);
    page("excit",pageCount,pageIndex);
    // 下一頁
    $("#excit").on("click","#nextBtn",function(){
        if(pageIndex+1 >= pageCount){
            return;
        }
        pageIndex++;
        getwithdrawrecord(pageIndex);
        page("excit",pageCount,pageIndex);
    })
    // 上一頁
    $("#excit").on("click","#prevBtn",function(){
        if(pageIndex == 0){
            return;
        }
        pageIndex--;
        getwithdrawrecord(pageIndex);
        page("excit",pageCount,pageIndex);
    })
    // 點擊調頁
    $("#excit").on("click","#selectPage",function(){
        var valIndex=$("#selectPageNum").val()-1;
        if(valIndex >= 0 && valIndex+1 <= pageCount){
            pageIndex=valIndex;
            getwithdrawrecord(pageIndex);
            page("excit",pageCount,pageIndex);
        }else{
            $("#selectPageNum").val("");
            return;
        }
    });
    $("#table").on("click",".js-checkName",function(){
        $(".sui-modal").addClass("in");
        $(".sui-modal-backdrop").css("zIndex","1000").addClass("in");
    })
    $(".sui-close").click(function(){
        $(".sui-modal").removeClass("in");
        $(".sui-modal-backdrop").css("zIndex","-1").removeClass("in");
    })

    $(".dropdown-toggle").eq(0).click(function(){
        $(".select1").toggleClass("open");
    });
    $(".sui-dropdown-menu1 li").click(function(){
        var i=$(".sui-dropdown-menu1 li").index(this);
        var x= $(".sui-dropdown-menu1 li a").eq(i).html();
        $(".sui-dropdown-menu1 li").removeClass("active");
        $(".sui-dropdown-menu1 li").eq(i).addClass("active");
        $(".select1 span span").html(x);
        $(".select1").removeClass("open");
    });
    $(".dropdown-toggle").eq(1).click(function(){
        $(".select2").toggleClass("open");
    });
    $(".sui-dropdown-menu2 li").click(function(){
        var i=$(".sui-dropdown-menu2 li").index(this);
        var x= $(".sui-dropdown-menu2 li a").eq(i).html();
        $(".sui-dropdown-menu2 li").removeClass("active");
        $(".sui-dropdown-menu2 li").eq(i).addClass("active");
        $(".select2 span span").html(x);
        $(".select2").removeClass("open");
    });
    $('.dropdown-inner').mouseleave(function(){
        $(".select1").removeClass("open");
        $(".select2").removeClass("open");
    });
})

