$(function(){
    // 新增地址彈框
    $(".btn-fontBlue1").click(function(){
        $(".sui-modal1").addClass("in");
        $(".sui-modal-backdrop").css("zIndex","1000").addClass("in");
    });
    $(".sui-close").click(function(){
        $(".sui-modal1").removeClass("in");
        $(".sui-modal2").removeClass("in");
        $(".sui-modal-backdrop").css("zIndex","-1").removeClass("in");
    });

    // 獲取寄送地址列表
//    var param={
//        receiver:$("#iAddress").html(),
//        longitude:longitude,
//        latitude:latitude
//    }
    var data = invokeService('/web/merchant/sendaddress/getSendAddressListByCurrent',{});
        console.log(data);
    if (data.statusCode!=1000){
        alert(data.errorMessage);
        return;
    }
    if(data.statusCode==1000){
        for(var i=0;i<data.result.length;i++){
           $("#addres tbody").html( $("#addres tbody").html()+
                "<tr>" +
               "<td>"+data.result[i].receiver+"</td>" +
               "<td>"+data.result[i].phone+"</td>" +
               "<td>"+data.result[i].province+""+data.result[i].city+""+data.result[i].address+"</td>" +
               "<td>"+data.result[i].postalcode+"</td>" +
               "<td><a class='defaultAddress'>设为默认地址</a>&nbsp;<a class='deleteAddress' id="+data.result[i].sendAddressCode+">删除</a></td>" +
               "</tr>")
        }
    }
    $("#insertButton").click(function(){
        // 添加寄送地址
        var defaultChecked;
        if($('#detaultState').is(':checked')==true){
            defaultChecked=true;
        }else{
            defaultChecked=false;
        }
        var param={
            receiver:$("#name").val(),//收件人姓名
            province:$("#s_province").val(),//省份
            city:$("#s_city").val(),
            address:$("#detailaddress").val(),
            postalcode:$("#zip").val(),
            phone:$("#mobile").val(),
            defaultChecked:defaultChecked
    }
    var data = invokeService('/web/merchant/sendaddress/addSendAddress',param);
//    console.log(data);
    if (data.statusCode!=1000){
        alert(data.errorMessage);
        return;
    }
        var data = invokeService('/web/merchant/sendaddress/getSendAddressListByCurrent',{});
        console.log(data);
        if (data.statusCode!=1000){
            alert(data.errorMessage);
            return;
        }
        if(data.statusCode==1000){
            $("#addres tbody").html("");
            for(var i=0;i<data.result.length;i++){
                $("#addres tbody").html( $("#addres tbody").html()+
                    "<tr>" +
                    "<td>"+data.result[i].receiver+"</td>" +
                    "<td>"+data.result[i].phone+"</td>" +
                    "<td>"+data.result[i].province+""+data.result[i].city+""+data.result[i].address+"</td>" +
                    "<td>"+data.result[i].postalcode+"</td>" +
                    "<td><a class='defaultAddress'>设为默认地址</a>&nbsp;<a class='deleteAddress' id="+data.result[i].sendAddressCode+">删除</a></td>" +
                    "</tr>")
            }
            // 確認添加
            $(".sui-modal1").removeClass("in");
            $(".sui-modal2").addClass("in");

        }
        $(':input','#insertForm')
            .not(':button, :submit, :reset, :hidden')
            .val('')
            .removeAttr('checked')
            .removeAttr('selected');
    })
    // 確認添加按鈕
    $("#confirm").click(function(){
        $(".sui-modal2").removeClass("in");
        $(".sui-modal-backdrop").css("zIndex","-1").removeClass("in");
    })
    // 刪除寄送地址
    var remi;
    $("#addres tbody").on("click",".deleteAddress",function(){
        remi = $(".deleteAddress").index(this);
        $(".sui-modal3").addClass("in");
        $(".sui-modal-backdrop").css("zIndex","1000").addClass("in");
        console.log(remi);
    });
    $("#rembutton").click(function(){
        var code=  $(".deleteAddress").eq(remi).attr("id");
        console.log(remi);
        var param={
            sendAddressCode:code
        }
        var data = invokeService('/web/merchant/sendaddress/deleteSendAddress',param);
        console.log(data);
        if (data.statusCode!=1000){
            alert(data.errorMessage);
            return;
        }
        var data = invokeService('/web/merchant/sendaddress/getSendAddressListByCurrent',{});
        console.log(data);
        if (data.statusCode!=1000){
            alert(data.errorMessage);
            return;
        }
        if(data.statusCode==1000){
            $("#addres tbody").html("");
            for(var i=0;i<data.result.length;i++){
                $("#addres tbody").html( $("#addres tbody").html()+
                    "<tr>" +
                    "<td>"+data.result[i].receiver+"</td>" +
                    "<td>"+data.result[i].phone+"</td>" +
                    "<td>"+data.result[i].province+""+data.result[i].city+""+data.result[i].address+"</td>" +
                    "<td>"+data.result[i].postalcode+"</td>" +
                    "<td><a class='defaultAddress'>设为默认地址</a>&nbsp;<a class='deleteAddress' id="+data.result[i].sendAddressCode+">删除</a></td>" +
                    "</tr>")
            }
        // 確認刪除
        $(".sui-modal3").removeClass("in");
        $(".sui-modal4").addClass("in");
    };

})
    // 取消刪除
    $("#closebutton").click(function(){
        $(".sui-modal3").removeClass("in");
        $(".sui-modal-backdrop").css("zIndex","-1").removeClass("in");
    });
    // 確定刪除按鈕
    $("#closebutton1").click(function(){
        $(".sui-modal4").removeClass("in");
        $(".sui-modal-backdrop").css("zIndex","-1").removeClass("in");
    });

})