// 獲取直捐記錄
function getswitchDrawRecord(pageIndex){
//    var pageArray=[];
    var param={
        pageIndex:pageIndex,
        pageSize:1
    }
    var data = invokeService('/web/merchant/conversion/getConversionPageList',param);
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
                "<td>"+data.result.list[i].conversionCode+"</td>"+
                "<td>"+data.result.list[i].conversionType+"</td>"+
                "<td>"+data.result.list[i].applyConversionNum+"</td>"+
                "<td>"+data.result.list[i].deductions+"</td>"+
                "<td>"+data.result.list[i].conversionNum+"</td>"+
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
    var pageCount = getswitchDrawRecord(pageIndex);
    page("excit",pageCount,pageIndex);
    // 下一頁
    $("#excit").on("click","#nextBtn",function(){
        if(pageIndex+1 >= pageCount){
            return;
        }
        pageIndex++;
        getswitchDrawRecord(pageIndex);
        page("excit",pageCount,pageIndex);
    })
    // 上一頁
    $("#excit").on("click","#prevBtn",function(){
        if(pageIndex == 0){
            return;
        }
        pageIndex--;
        getswitchDrawRecord(pageIndex);
        page("excit",pageCount,pageIndex);
    })
    // 點擊調頁
    $("#excit").on("click","#selectPage",function(){
        var valIndex=$("#selectPageNum").val()-1;
        if(valIndex >= 0 && valIndex+1 <= pageCount){
            pageIndex=valIndex;
            getswitchDrawRecord(pageIndex);
            page("excit",pageCount,pageIndex);
        }else{
            $("#selectPageNum").val("");
            return;
        }
    });


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
    $('.dropdown-inner').mouseleave(function(){
        $(".select1").removeClass("open");
    });
})


