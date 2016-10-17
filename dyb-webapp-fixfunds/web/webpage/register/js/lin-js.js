/**
 * Created by aaa on 2016/9/26.
 */
$(function(){
    getData();
    $(".btn_timepicki").timepicki();
    function submit(){
        var flag=$("#flag").val();
        var param={
            // 账户名
            accountName:$("#username").val(),
            // 账户密码
            password:$("#userpwd").val(),
            //二级密码
            tradePassword:$("#password2").val(),
            // 绑定手机号
            accountPhone:$("#mobilenumber").val(),
            // 推荐人
            referrerCode:"1",
            // 商家名称
            merchantName:$("#companyname").val(),
            // 激励模式
            incentiveMode:$("#drivetypeSpan").text(),
            // 店铺名称
            shopName:$("#shopname").val(),
            // 商家类型
            merchantType:"商家",
            // 营业开始时间
            businessStartTime:$("#startBusinessTime").val(),
            // 营业结束时间
            businessEndTime:$("#endBusinessTime").val(),
            // 商家地址
            merchantAddress:$("#s_province option:selected").text()+$("#s_city option:selected").text()+$("#companyaddress").val(),
            // 省级代码
            province:provinceVal,
            // 市级代码
            city:provinceVal+"_"+cityVal,
            // 主营业务
            mainBusiness:$("#mainbusiness").val(),
            // 行业类别
            industryType:"红木",//$("#industryName").text()
            // 所在行业
            industry:"YL",//$("#businessSpan").text()
            // 商家规模
            scale:"A",//$("#sizeSpan").text()
            // 企业电话
            countryPhone:$("#phone").val(),
            // 商家简介
            merchantDescription:$("#remark").val(),
            // 负责人姓名
            principalName:$("#principal").val(),
            // 负责人岗位
            principalJobs:$("#position").val(),
            // 负责人性别
            principalSex:"男",//$("#sex").val()
            // 负责人身份证号
            principalIdCard:$("#idcard").val(),
            //负责人生日
            birthday:$("#birthday").val().toLocaleString(),
            // 负责人邮箱地址
            principalEmail:$("#email").val(),
            // 证件资料文件路径
            certificateFile:"2",//$("#up_img_WU_FILE_2").val()
            // 开户行
            bankName:$("#bankName").text(),
            // 开户支行
            bankBranch:$("#subbankname").val(),
            // 卡号
            bankNum:$("#bankNumber").val(),
            // 开户名称
            bankAccountName:$("#acountname").val(),
            // 默认设置选定
            defaultChecked:true,
            //执照类型1：表示新版  2：表示旧版
            flag:flag,
            //营业执照第一张
            businessLicensePhoto1:flag==1?$("#businessLicensePhoto1").val():$("#oldBusinessLicensePhoto1").val(),
            //营业执照第二张
            businessLicensePhoto2:flag==1?"":$("#oldBusinessLicensePhoto2").val(),
            //法人身份证照片
            legalPersonPhoto:$("#legalPersonPhoto").val(),
            //推荐人身份证照片
            recommendPersonPhoto:$("#recommendPersonPhoto").val(),
            //捐赠承诺书照片
            donationPhoto:$("#donationPhoto").val(),
            //店面门头照照片
            storePhoto:$("#storePhoto").val(),
            //经度
            longitude:lngX,
            //纬度
            latitude:latY,
            //关键字
            businessCircle :$("#category input").val()
        }
        var data = invokeService('/web/merchant/registerMerchantAccount',param);
        if (data.statusCode!=1000){
            alert(data.errorMessage);
            return;
        }
//        alert(data.flag)
        $(".ad-register1").css({"display":"none"});
        $(".ad-register2").css({"display":"none"});
        $(".ad-register3").css({"display":"none"});
        $(".ad-register4").css({"display":"none"});
        $(".ad-register5").css({"display":"block"});
    }
    $("#nextReg4").click(function(){
        submit();

    });
    $('#s_province').on('change',function() {
        var i=$("#s_province").index(this);
        provinceVal = $('#s_province').eq(i).val();
    });
    $('#s_city').on('change',function() {
        var i=$("#s_city").index(this);
        cityVal = $('#s_city').eq(i).val();
    });


    var lngX;
    var latY;
    var provinceVal;
    var cityVal;
    // 地图
    var mapObj;
    // 初始化地图
    var map = new AMap.Map("container", {
        map: map,
        resizeEnable: true,
        center: [116.397428, 39.90923],
        zoom: 17,
        doubleClickZoom:false, //双击放大地图
        keyboardEnable: false
    });
    // 搜索提示
    AMap.plugin(['AMap.Autocomplete','AMap.PlaceSearch'],function(){
        var autoOptions = {
            city: "北京", //城市，默认全国
            input: "key_2"//使用你想输入的input的id
        };
        autocomplete= new AMap.Autocomplete(autoOptions);
        var placeSearch = new AMap.PlaceSearch({
            city:'北京',
            map:mapObj
        })
        AMap.event.addListener(autocomplete, "select", function(e){
            placeSearch.search(e.poi.name)
        });
    });
    //添加marker标记
    function addMarker2(x,y) {
        map.clearMap();
        var marker = new AMap.Marker({
            map: map,
            position:(new AMap.LngLat(x,y)),
            draggable: true,
            cursor: 'move',
            raiseOnDrag: true
        });

        marker.setAnimation('AMAP_ANIMATION_DROP');
        AMap.event.addListener(marker, 'dragend', function(){
            var tag= marker.getPosition(map);
            //console.log(tag);
            var tagX = tag.lng;
            var tagY = tag.lat;
//            console.log(tagX,tagY);
            geocoder(tagX,tagY);
        }); //点击事件
        //鼠标点击marker弹出自定义的信息窗体
        AMap.event.addListener(marker, 'click', function() {
            var infoWindow = new AMap.InfoWindow({
                isCustom: true,  //使用自定义窗体
                content: createInfoWindow(title, content.join("<br/>")),
                offset: new AMap.Pixel(16, -45)
            });
            infoWindow.open(map, marker.getPosition());
        });
    }
    //实例化信息窗体
    var title;
    content = [];
    $("#nextReg3").click(function(){
        var key2Val=$("#s_province option:selected").text()+$("#s_city option:selected").text()+$("#companyaddress").val();
        $("#key_2").val(key2Val);
        geocoderss();
        title= $("#companyname").val();
        var imgSrc = $("#storePhoto").val()
        content.push("<img src="+imgSrc+">");
        content.push("<div>名称：<span>"+$("#companyname").val()+"</span></div>");
        content.push("<div>联系人：<span>"+$("#principal").val()+"</span></div>");
        content.push("<div>电话：<span>"+$("#phone").val()+"</span></div>");
        content.push("<div>地址：<span>"+$("#s_province option:selected").text()+$("#s_city option:selected").text()+$("#companyaddress").val()+"</span></div>");
    })


    var data = invokeService('/web/commons/getBusinessCircle',{});
    console.log(data)
    if (data.statusCode!=1000){
        alert(data.errorMessage);
        return;
    }

    var categoryText;
    categoryText+="<div id='categorybox'><div id='category' style='margin-top: 0px;position: absolute;top: 40px;left: 5px'>关键字：<input value='' style='width: 130px' type='text' placeholder='选择或输入类目'>" +
    "<i class='iconfont' style='position: absolute;left:170px;top:3px;line-height: 1.5;font-size: 12px'></i>" +
//        ""+categoryText+""+
    "</div>"
    categoryText+="<ul id='categoryText' class='sui-dropdown-menu sui-suggestion-container container' style='position: absolute;top: 64px;left: 53px;width: 150px;z-index: 9999;display: none'>"
    for(var i=0;i<data.result.length;i++){
    categoryText+=
        "<li class='autocomplete-suggestion'><a>"+data.result[i].name+"</a></li>"
    }
categoryText+=
    "</ul></div>"
    $("#container").append(categoryText);

    $("#container").on("click","#category input",function(){
            $("#categoryText").show();
    })
    $('#container').bind('input propertychange', function() {
        if($("#category input").val() != null){
            $("#categoryText").hide();
        }
    });
    $('#container').bind('input propertychange', function() {
        if($("#category input").val() == ""){
            $("#categoryText").show();
        }
    });
    $("#container").on("click","#categoryText li",function(){
        var i=$("#categoryText li").index(this);
          var ss = $("#categoryText li a").eq(i).html()
        $("#category input").val(ss);
        $("#categoryText").hide();
    })
    $("#container").on("mouseleave","#categorybox",function(){
        $("#categoryText").hide();
    })




    //构建自定义信息窗体
    function createInfoWindow(title, content) {
        var info = document.createElement("div");
        info.className = "info";

        //可以通过下面的方式修改自定义窗体的宽高
        info.style.width = "400px";
        info.style.height = "180px";
        // 定义顶部标题
        var top = document.createElement("div");
        var titleD = document.createElement("div");
        var closeX = document.createElement("img");
        top.className = "info-top";
        titleD.innerHTML = title;
        closeX.src = "http://webapi.amap.com/images/close2.gif";
        closeX.onclick = closeInfoWindow;

        top.appendChild(titleD);
        top.appendChild(closeX);
        info.appendChild(top);

        // 定义中部内容
        var middle = document.createElement("div");
        middle.className = "info-middle";
        middle.style.backgroundColor = 'white';
        middle.innerHTML = content;
        info.appendChild(middle);

        // 定义底部内容
        var bottom = document.createElement("div");
        bottom.className = "info-bottom";
        bottom.style.position = 'relative';
        bottom.style.top = '0px';
        bottom.style.margin = '0 auto';
        var sharp = document.createElement("img");
        sharp.src = "http://webapi.amap.com/images/sharp.png";
        bottom.appendChild(sharp);
        info.appendChild(bottom);
        return info;
    }

//关闭信息窗体
    function closeInfoWindow() {
        map.clearInfoWindow();
    }
    // 搜索功能
    var MGeocoder;
    var key_2;
    function geocoderss() {  //地理编码返回结果展示
        key_2 = document.getElementById("key_2").value;
//        document.getElementById('result').innerHTML = "您输入的是：" + key_2;
        map.plugin(["AMap.Geocoder"], function() {     //加载地理编码插件
            MGeocoder = new AMap.Geocoder({
                radius:1000, //范围，默认：500
                level:1000000
            });
            //返回地理编码结果
            AMap.event.addListener(MGeocoder, "complete", function(data){
                var geocode = data.geocodes;
                lngX = geocode[0].location.getLng();
                latY = geocode[0].location.getLat();
                map.setCenter(new AMap.LngLat(lngX, latY));
                addMarker2(lngX,latY);
            });
            MGeocoder.getLocation(key_2);  //地理编码

        });
    }
// 逆地理编码 得到具体的地理位置
    function geocoder(x,y) {
        AMap.plugin('AMap.Geocoder',function(){
            var geocoder = new AMap.Geocoder({

            });
            var lnglatXY=[x,y];
            //console.log(x,y);
            geocoder.getAddress(lnglatXY,function(status,result){
                //console.log(status);
                if(status=='complete'){
                    console.log(result);
                    //document.getElementById('iAddress').innerHTML = result.regeocode.formattedAddress
                }
            })
        });
    }
//鼠标点击，获取经纬度坐标
    function getLnglat(e) {

        map.clearMap();

        var x = e.lnglat.getLng();

        var y = e.lnglat.getLat();

        lngX=x;
        latY=y;

        //document.getElementById("lnglat").innerHTML = x + "," + y;

//        lnglatXY = new AMap.LngLat(x, y);
        geocoder(x,y);
        addMarker2(x,y);
    }
    AMap.event.addListener(map, 'click', getLnglat); //点击事件
    $("#search").click(function(){
        geocoderss();
    })
});