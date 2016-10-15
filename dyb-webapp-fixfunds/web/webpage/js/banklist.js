$(function(){
    // 获取银行卡号信息
function getbanklist(){
    var data = invokeService('/web/merchant/bankaccount/getBankAccountListByCurrent',{});
    console.log(data)
    if (data.statusCode!=1000){
        alert(data.errorMessage);
        return;
    }
    if(data.statusCode==1000){
        for(var i=0;i<data.result.length;i++){
            if(data.result[i].defaultChecked == false){
                $(".table-zebra tbody").html( $(".table-zebra tbody").html()+
                    "<tr>" +
                    "<td>"+data.result[i].bankName+"</td>" +
                    "<td>"+data.result[i].bankBranch+"</td>" +
                    "<td>"+data.result[i].bankNum+"</td>" +
                    "<td>"+data.result[i].bankAccountName+"</td>" +
                    "<td><label class='inline checked'>" +
                          "<input type='radio'>"+
                          "<span style='margin-left: 5px'>默认银行卡</span>"+
                    "</label></td>" +
                    "<td><a class='js-checkName "+data.result[i].bankAccountCode+"'>删除</a></td>" +
                    "</tr>")
            }else if(data.result[i].defaultChecked == true){
                $(".table-zebra tbody").html( $(".table-zebra tbody").html()+
                    "<tr>" +
                    "<td>"+data.result[i].bankName+"</td>" +
                    "<td>"+data.result[i].bankBranch+"</td>" +
                    "<td>"+data.result[i].bankNum+"</td>" +
                    "<td>"+data.result[i].bankAccountName+"</td>" +
                    "<td><label class='inline checked'>"+
                    "<input type='radio' checked='checked'>"+
                    "<span style='margin-left: 5px'>默认银行卡</span>"+
                    "</label></td>" +
                    "<td><a class='js-checkName "+data.result[i].bankAccountCode+"'>删除</a></td>" +
                    "</tr>")
            }
        }
    }

}

   // 添加银行卡号
$("#btn_addbank").click(function(){
    if(bankName == "-------------"){
        $(".sui-modal1").addClass("in");
        $(".sui-modal-backdrop").css("zIndex","1000").addClass("in");
        return;
    }
    if($("#bankacount").val()==""){
        return;
    }
    if($("#subbankname").val()==""){
        return;
    }
    if($("#accountname_show").val()==""){
        return;
    }
     var param={
         bankName:bankName,
         bankNum:$("#bankacount").val(),
         bankBranch:$("#subbankname").val(),
         bankAccountName:$("#accountname_show").val(),
         defaultChecked:true
    }
    var data = invokeService('/web/merchant/bankaccount/addBankAccount',param);
    console.log(data)
    if (data.statusCode!=1000){
        alert(data.errorMessage);
        return;
    }
    if (data.statusCode==1000){
        $(".table-zebra tbody").html("");
        getbanklist();
    }
})
    $(".sui-close,.sui-btn1").click(function(){
        $(".sui-modal1").removeClass("in");
        $(".sui-modal2").removeClass("in");
        $(".sui-modal-backdrop").css("zIndex","-1").removeClass("in");
    });
  // 删除银行卡
    var remi;
    $(".table-zebra tbody").on("click",".js-checkName",function(){
        remi = $(".js-checkName").index(this);
        $(".sui-modal2").addClass("in");
        $(".sui-modal-backdrop").css("zIndex","1000").addClass("in");
    });
    $("#remBtn").click(function(){
        var code=[];
        code =  $(".js-checkName").eq(remi).attr("class").split(" ");
        var param={
            bankAccountCode:code[1]
        }
        var data = invokeService('/web/merchant/bankaccount/deleteBankAccount',param);
        console.log(data)
        if (data.statusCode!=1000){
            alert(data.errorMessage);
            return;
        }
        if (data.statusCode==1000){
            $(".table-zebra tbody").html("");
            getbanklist();
            $(".sui-modal2").removeClass("in");
            $(".sui-modal-backdrop").css("zIndex","-1").removeClass("in");
        }
    });
   // 设置默认银行

    getbanklist();






















    // 选择银行
    var bankName="-------------";
    $(".js-checkBank").css("border","2px solid #dcdcdc");
    $(".js-checkBank").click(function(){
            var i = $(".js-checkBank").index(this);
            $(".js-checkBank").css("border","2px solid #dcdcdc");
            $(".js-checkBank").eq(i).css("border","2px solid #C80712");
            bankName=$(".banklist-bank-title").eq(i).html();
           $("#select span").html("-------------");
    });
    $(".sui-dropdown-menu1 li a").click(function(){
        var i = $(".sui-dropdown-menu1 li a").index(this);
        $(".js-checkBank").css("border","2px solid #dcdcdc");
        bankName=$(".sui-dropdown-menu1 li a").eq(i).html();
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
    $('.dropdown-inner').mouseleave(function(){
        $(".select1").removeClass("open");
    });
})