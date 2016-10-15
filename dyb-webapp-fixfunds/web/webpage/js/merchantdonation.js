/**
 * Created by aaa on 2016/10/15.
 */
$(function(){
    $(".dropdown-toggle").eq(0).click(function(){
        $(".select1").toggleClass("open");
    })
    $(".sui-dropdown-menu1 li").click(function(){
        var i=$(".sui-dropdown-menu1 li").index(this);
        var x= $(".sui-dropdown-menu1 li a").eq(i).html();
        $(".sui-dropdown-menu1 li").removeClass("active");
        $(".sui-dropdown-menu1 li").eq(i).addClass("active");
        $(".select1 span span").html(x);
        $(".select1").removeClass("open");
    })
    $('.dropdown-inner').mouseleave(function(){
        $(".select1").removeClass("open");
    });
//      让利捐赠分页列表

    var a=0;
    var param={
//        当前页
        pageIndex:a,
//        请求条数
        pageSize:5
    };
    var result=invokeService('/web/merchant/benefitdonation/getBenefitDonationPageList',param);
    console.log(result);
    if(result.statusCode!=1000){
        alert(result.errorMessage);
        return;
    }
console.log(result)
    $("#xg_page").text("共 " +result.result.pageCount+ " 页");
    $("#onpage").text("第 " +(a+1)+ " 页");
    for(var i =0;i<result.result.list.length;i++){
        $("#table>table>tbody").html($("#table>table>tbody").html()+
                "<tr>"+
                "<td>"+
                result.result.list[i].donationTime+
                "</td>"+
                "<td>" +
                result.result.list[i].donationMessengerBean+
                "</td>"+
                "</tr>"
        )
    }
//          下一页
    $("#nextBtn").click(function(){
        a++;
        var param={
            // 当前页
            pageIndex:a,
            //每页显示条数
            pageSize:5
        };
        var result = invokeService('/web/merchant/benefitdonation/getBenefitDonationPageList',param);
        if(result.statusCode!=1000){
            alert(result.errorMessage)
            return;
        }
        if(a<result.result.pageCount){
            $("#onpage").text("第 " +(a+1)+ " 页");
            $("#table>table>tbody").html("");
            for(var i =0;i<result.result.list.length;i++){
                $("#table>table>tbody").html($("#table>table>tbody").html()+
                        "<tr>"+
                        "<td>"+
                        result.result.list[i].donationTime+
                        "</td>"+
                        "<td>" +
                        result.result.list[i].donationMessengerBean+
                        "</td>"+
                        "</tr>"
                )
            }
        }
        else{
            a=result.result.pageCount;
            a--;
        }
    });
    //		上一页
    $("#prevBtn").click(function(){
        a--;
        if(a<0){
            a=0;
        }
        var param={
            // 当前页
            pageIndex:a,
            //每页显示条数
            pageSize:5
        };
        var result = invokeService('/web/merchant/benefitdonation/getBenefitDonationPageList',param);
        if(result.statusCode!=1000){
            alert(result.errorMessage)
            return;
        }
        if(a>=0){
            $("#onpage").text("第 " +(a+1)+ " 页");
            $("#table>table>tbody").html("");
            for(var i =0;i<result.result.list.length;i++){
                $("#table>table>tbody").html($("#table>table>tbody").html()+
                        "<tr>"+
                        "<td>"+
                        result.result.list[i].donationTime+
                        "</td>"+
                        "<td>" +
                        result.result.list[i].donationMessengerBean+
                        "</td>"+
                        "</tr>"
                )
            }
        }
        else{

        }
    });
})