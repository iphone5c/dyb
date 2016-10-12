// 獲取直捐記錄
function getdonationreclist(pageIndex){
//    var pageArray=[];
    var param={
        pageIndex:pageIndex,
        pageSize:1
    }
    var data = invokeService('/web/merchant/transfer/getTransferPageList',param);
    console.log(data)
    if (data.statusCode!=1000){
        alert(data.errorMessage);
        return;
    }
    var pageCount=data.result.pageCount;
    $("#table tbody").html("");
    for(var i=0;i<data.result.list.length;i++){
        $("#table tbody").html( $("#table tbody").html()+
                "<tr>" +
                "<td>"+data.result.list[i].transferTime+"</td>"+
                "<td>"+data.result.list[i].transferAccount+"</td>"+
                "<td>"+data.result.list[i].donationType+"</td>"+
                "<td>"+data.result.list[i].donationType+"</td>"+
                "<td>"+data.result.list[i].donationType+"</td>"+
                "</tr>"
        )
    }
    return pageCount;
}
$(function(){
    var pageIndex=0;
    var pageCount = getdonationreclist(pageIndex);
    page("excit",pageCount,pageIndex);
    // 下一頁
    $("#excit").on("click","#nextBtn",function(){
        if(pageIndex+1 >= pageCount){
            return;
        }
        pageIndex++;
        getdonationreclist(pageIndex);
        page("excit",pageCount,pageIndex);
    })
    // 上一頁
    $("#excit").on("click","#prevBtn",function(){
        if(pageIndex == 0){
            return;
        }
        pageIndex--;
        getdonationreclist(pageIndex);
        page("excit",pageCount,pageIndex);
    })
    // 點擊調頁
    $("#excit").on("click","#selectPage",function(){
        var valIndex=$("#selectPageNum").val()-1;
        if(valIndex >= 0 && valIndex+1 <= pageCount){
            pageIndex=valIndex;
            getdonationreclist(pageIndex);
            page("excit",pageCount,pageIndex);
        }else{
            $("#selectPageNum").val("");
            return;
        }
    });
})

