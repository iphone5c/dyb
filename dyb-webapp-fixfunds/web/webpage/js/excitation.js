    // 獲取我的激勵
function getexcitation(pageIndex){
//    var pageArray=[];
    var param={
        pageIndex:pageIndex,
        pageSize:1
    }
    var data = invokeService('/web/merchant/accountincentive/getAccountIncentivePageList',param);
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
                "<td>"+data.result.list[i].accountIncentiveTime+"</td>"+
                "<td>"+data.result.list[i].loveNum+"</td>"+
                "<td>"+data.result.list[i].lovePrice+"</td>"+
                "<td>"+data.result.list[i].messengerBean+"</td>"+
                "</tr>"
        )
    }
    return pageCount;

}
$(function(){
    var pageIndex=0;
    var pageCount = getexcitation(pageIndex);
    page("excit",pageCount,pageIndex);
    // 下一頁
        $("#excit").on("click","#nextBtn",function(){
            if(pageIndex+1 >= pageCount){
                return;
            }
            pageIndex++;
            getexcitation(pageIndex);
            page("excit",pageCount,pageIndex);
        })
    // 上一頁
    $("#excit").on("click","#prevBtn",function(){
        if(pageIndex == 0){
            return;
        }
        pageIndex--;
        getexcitation(pageIndex);
        page("excit",pageCount,pageIndex);
    })
    // 點擊調頁
    $("#excit").on("click","#selectPage",function(){
        var valIndex=$("#selectPageNum").val()-1;
        if(valIndex >= 0 && valIndex+1 <= pageCount){
            pageIndex=valIndex;
            getexcitation(pageIndex);
            page("excit",pageCount,pageIndex);
        }else{
            $("#selectPageNum").val("");
            return;
        }
    });
})