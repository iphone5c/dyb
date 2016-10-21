function getrecommend(){
    var data = invokeService('/web/merchant/recommend/getRecommend',{});
    console.log(data);
    if (data.statusCode!=1000){
        alert(data.errorMessage);
        return;
    }
    if (data.statusCode == 1000){
        $("#wxQrCode img").attr("src",data.result.member.imagePath);
        $("#copyLink").val(data.result.member.url);
    }
}
$(function(){
    getrecommend();
})