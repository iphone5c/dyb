$(function(){
    // 新增地址彈框
    $(".btn-fontBlue1").click(function(){
        $(".sui-modal1").addClass("in");
        $(".sui-modal-backdrop").css("zIndex","1000").addClass("in");
    });
    $(".sui-close,#cancelButton").click(function(){
        $(".sui-modal1").removeClass("in");
        $(".sui-modal-backdrop").css("zIndex","-1").removeClass("in");
        $(':input','#insertForm')
            .not(':button, :submit, :reset, :hidden')
            .val('')
            .removeAttr('checked')
            .removeAttr('selected');
        $("#s_province option:selected").text("省份");
        $("#s_city option:selected").text("地级市");
    });

    // 使用商家资料的地址
    $("#useDefaultAddress").click(function(){
        var data = invokeService('/web/merchant/getMerchantByCurrent',{});
//    console.log(data);
        if (data.statusCode!=1000){
            alert(data.errorMessage);
            return;
        }
        $("#s_province option:selected").text("四川省");
        $("#s_city option:selected").text("成都市");
        var a = $("#s_province option:selected").text()+ $("#s_city option:selected").text();
        var str=data.result.merchant.merchant.merchantAddress
        str = str.replace(new RegExp(a),'');
        $("#detailaddress").val(str);
    });
    // 獲取寄送地址列表
    var data = invokeService('/web/merchant/sendaddress/getSendAddressListByCurrent',{});
//        console.log(data);
    if (data.statusCode!=1000){
        alert(data.errorMessage);
        return;
    }
    if(data.statusCode==1000){
        for(var i=0;i<data.result.length;i++){
            if(data.result[i].defaultChecked == false){
                $("#addres tbody").html( $("#addres tbody").html()+
                    "<tr>" +
                    "<td>"+data.result[i].receiver+"</td>" +
                    "<td>"+data.result[i].phone+"</td>" +
                    "<td>"+data.result[i].province+""+data.result[i].city+""+data.result[i].address+"</td>" +
                    "<td>"+data.result[i].postalcode+"</td>" +
                    "<td><a class='defaultAddress "+data.result[i].sendAddressCode+"'>设为默认地址</a>&nbsp;<a  class='deleteAddress "+data.result[i].sendAddressCode+"'>删除</a></td>" +
                    "</tr>")
            }else if(data.result[i].defaultChecked == true){
                $("#addres tbody").html( $("#addres tbody").html()+
                    "<tr id="+data.result[i].sendAddressCode+">" +
                    "<td>"+data.result[i].receiver+"</td>" +
                    "<td>"+data.result[i].phone+"</td>" +
                    "<td>"+data.result[i].province+""+data.result[i].city+""+data.result[i].address+"</td>" +
                    "<td>"+data.result[i].postalcode+"</td>" +
                    "<td><span style='color: #808080'>默认地址</span>&nbsp;<a  class='deleteAddress "+data.result[i].sendAddressCode+"'>删除</a></td>" +
                    "</tr>")
            }
        }
    }
    // 非空
    var rgempty=/\S+/;
    // 长度2-14
    var regleg1=/^.{2,14}$/;
    // 长度30
    var regleg2=/.{30}$/;
    // 长度100
    var regleg3=/.{100}$/;
    // 邮政编码
    var regPostcode=/^[1-9][0-9]{5}$/;
    // 手机
    var regphone = /0?(13|14|15|18)[0-9]{9}/;
    function names(){
        var name=$("#name").val();
        if(!rgempty.test(name)){
            $(".msg_error").eq(0).html("请填写收件人名!");
            return false;
        }else if(regleg2.test(name)){
            $(".msg_error").eq(0).html("长度不能超过30!");
        }
        else{
            $(".msg_error").eq(0).html("");
        }
    }
    function citys(){
        var s_province=$("#s_province").text();
        var s_city=$("#s_city").text();
        if(s_province=="省份" || s_city=="地级市"){
            $(".msg_error").eq(1).html("请选择!");
            return false;
        }else {
            $(".msg_error").eq(1).html("");
        }
    }
    function detailaddresss(){
        var detailaddress=$("#detailaddress").val();
        if(!rgempty.test(detailaddress)){
            $(".msg_error").eq(2).html("请填写详细地址!");
            return false;
        }else if(regleg3.test(detailaddress)){
            $(".msg_error").eq(2).html("长度不能超过100!");
        }
        else{
            $(".msg_error").eq(2).html("");
        }
    }
    function Postcodes(){
        var zip=$("#zip").val();
        if(!rgempty.test(zip)){
            $(".msg_error").eq(3).html("请填写邮政编码!");
            return false;
        }else if(!regPostcode.test(zip)){
            $(".msg_error").eq(3).html("请填写正确的邮编!");
        }
        else{
            $(".msg_error").eq(3).html("");
        }
    }
    function mobiles(){
        var mobile=$("#mobile").val();
        if(!rgempty.test(mobile)){
            $(".msg_error").eq(4).html("请填写手机号码!");
            return false;
        }else if(!regphone.test(mobile)){
            $(".msg_error").eq(4).html("请填写正确的手机号码!");
        }
        else{
            $(".msg_error").eq(4).html("");
        }
    }
    $("#name").on("keyup blur change",function(){
        names();
    });
    $('#s_city').on('change',function() {
        $(".msg_error").eq(1).html("");
    });
    $("#detailaddress").on("keyup blur change",function(){
        detailaddresss();
    });
    $("#zip").on("keyup blur change",function(){
        Postcodes();
    });
    $("#mobile").on("keyup blur change",function(){
        mobiles();
    });
    $("#insertButton").click(function(){
        var result=new Array();
        result[0] = names();
        result[1] = citys();
        result[2] = detailaddresss();
        result[3] = Postcodes();
        result[4] = mobiles();
        for(var i=0;i<result.length;i++){
            if(result[i]==false){
                return false;
            }
        }
        // 添加寄送地址
        var defaultChecked;
        if($('#detaultState').is(':checked')==true){
            defaultChecked=true;
        }else{
            defaultChecked=false;
        }
        var param={
            receiver:$("#name").val(),//收件人姓名
            province:$("#s_province option:selected").text(),//省份
            city:$("#s_city option:selected").text(),
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
                if(data.result[i].defaultChecked == false){
                    $("#addres tbody").html( $("#addres tbody").html()+
                        "<tr>" +
                        "<td>"+data.result[i].receiver+"</td>" +
                        "<td>"+data.result[i].phone+"</td>" +
                        "<td>"+data.result[i].province+""+data.result[i].city+""+data.result[i].address+"</td>" +
                        "<td>"+data.result[i].postalcode+"</td>" +
                        "<td><a class='defaultAddress "+data.result[i].sendAddressCode+"'>设为默认地址</a>&nbsp;<a  class='deleteAddress "+data.result[i].sendAddressCode+"'>删除</a></td>" +
                        "</tr>")
                }else if(data.result[i].defaultChecked == true){
                    $("#addres tbody").html( $("#addres tbody").html()+
                        "<tr id="+data.result[i].sendAddressCode+">" +
                        "<td>"+data.result[i].receiver+"</td>" +
                        "<td>"+data.result[i].phone+"</td>" +
                        "<td>"+data.result[i].province+""+data.result[i].city+""+data.result[i].address+"</td>" +
                        "<td>"+data.result[i].postalcode+"</td>" +
                        "<td><span style='color: #808080'>默认地址</span>&nbsp;<a  class='deleteAddress "+data.result[i].sendAddressCode+"'>删除</a></td>" +
                        "</tr>")
                }
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
        $("#s_province option:selected").text("省份");
        $("#s_city option:selected").text("地级市");
    });
    // 確認添加按鈕
    $("#confirm").click(function(){
        $(".sui-modal2").removeClass("in");
        $(".sui-modal-backdrop").css("zIndex","-1").removeClass("in");
    });
    // 刪除寄送地址
    var remi;
    $("#addres tbody").on("click",".deleteAddress",function(){
        remi = $(".deleteAddress").index(this);
        $(".sui-modal3").addClass("in");
        $(".sui-modal-backdrop").css("zIndex","1000").addClass("in");
    });
    $("#rembutton").click(function(){
        console.log(remi);
        var code=[];
        var code =  $(".deleteAddress").eq(0).attr("class").split(" ");
        var param={
            sendAddressCode:code[1]
        }
        var data = invokeService('/web/merchant/sendaddress/deleteSendAddress',param);
//        console.log(data);
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
                if(data.result[i].defaultChecked == false){
                    $("#addres tbody").html( $("#addres tbody").html()+
                        "<tr>" +
                        "<td>"+data.result[i].receiver+"</td>" +
                        "<td>"+data.result[i].phone+"</td>" +
                        "<td>"+data.result[i].province+""+data.result[i].city+""+data.result[i].address+"</td>" +
                        "<td>"+data.result[i].postalcode+"</td>" +
                        "<td><a class='defaultAddress "+data.result[i].sendAddressCode+"'>设为默认地址</a>&nbsp;<a  class='deleteAddress "+data.result[i].sendAddressCode+"'>删除</a></td>" +
                        "</tr>")
                }else if(data.result[i].defaultChecked == true){
                    $("#addres tbody").html( $("#addres tbody").html()+
                        "<tr id="+data.result[i].sendAddressCode+">" +
                        "<td>"+data.result[i].receiver+"</td>" +
                        "<td>"+data.result[i].phone+"</td>" +
                        "<td>"+data.result[i].province+""+data.result[i].city+""+data.result[i].address+"</td>" +
                        "<td>"+data.result[i].postalcode+"</td>" +
                        "<td><span style='color: #808080'>默认地址</span>&nbsp;<a  class='deleteAddress "+data.result[i].sendAddressCode+"'>删除</a></td>" +
                        "</tr>")
                }
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


    // 设置默认寄送地址
    $("#addres tbody").on("click",".defaultAddress",function(){
        var defi = $(".defaultAddress").index(this);
//        console.log(defi);
        var code=[];
        code =  $(".defaultAddress").eq(defi).attr("class").split(" ");
        var param={
            sendAddressCode:code[1]
        }
        var data = invokeService('/web/merchant/sendaddress/setDefaultSendAddress',param);
//        console.log(data);
        if (data.statusCode!=1000){
            alert(data.errorMessage);
            return;
        }
        var data = invokeService('/web/merchant/sendaddress/getSendAddressListByCurrent',{});
//        console.log(data);
        if (data.statusCode!=1000){
            alert(data.errorMessage);
            return;
        }
        if(data.statusCode==1000){
            $("#addres tbody").html("");
            for(var i=0;i<data.result.length;i++){
                if(data.result[i].defaultChecked == false){
                    $("#addres tbody").html( $("#addres tbody").html()+
                        "<tr>" +
                        "<td>"+data.result[i].receiver+"</td>" +
                        "<td>"+data.result[i].phone+"</td>" +
                        "<td>"+data.result[i].province+""+data.result[i].city+""+data.result[i].address+"</td>" +
                        "<td>"+data.result[i].postalcode+"</td>" +
                        "<td><a class='defaultAddress "+data.result[i].sendAddressCode+"'>设为默认地址</a>&nbsp;<a  class='deleteAddress "+data.result[i].sendAddressCode+"'>删除</a></td>" +
                        "</tr>")
                }else if(data.result[i].defaultChecked == true){
                    $("#addres tbody").html( $("#addres tbody").html()+
                        "<tr id="+data.result[i].sendAddressCode+">" +
                        "<td>"+data.result[i].receiver+"</td>" +
                        "<td>"+data.result[i].phone+"</td>" +
                        "<td>"+data.result[i].province+""+data.result[i].city+""+data.result[i].address+"</td>" +
                        "<td>"+data.result[i].postalcode+"</td>" +
                        "<td><span style='color: #808080'>默认地址</span>&nbsp;<a  class='deleteAddress "+data.result[i].sendAddressCode+"'>删除</a></td>" +
                        "</tr>")
                }
            }

        };
        $(".sui-modal5").addClass("in");
        $(".sui-modal-backdrop").css("zIndex","1000").addClass("in");
    })
    $("#default").click(function(){
        $(".sui-modal5").removeClass("in");
        $(".sui-modal-backdrop").css("zIndex","-1").removeClass("in");
    })
})