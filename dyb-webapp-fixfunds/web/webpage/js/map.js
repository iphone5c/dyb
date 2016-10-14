//console.log(document.getElementById("longitude").value)
//console.log(document.getElementById("latitude").value)
//console.log(document.getElementById("address").value)
//var cs=[Number($("#longitude").val()),Number($("#latitude").val())];

$(function(){
    var data = invokeService('/web/merchant/getMerchantByCurrent',{});
    console.log(data);
    if (data.statusCode!=1000){
        alert(data.errorMessage);
        return;
    }
        var longitude = data.result.merchant.merchant.longitude;
        var latitude =data.result.merchant.merchant.latitude;
        var address =data.result.merchant.merchant.merchantAddress;
    document.getElementById("lnglat").innerHTML = longitude + "," + latitude;
//    alert(longitude)
    var cs=[Number(longitude),Number(latitude)];
    $("#iAddress").html(address);
    var windowsArr = [];
    var marker = [];
    var geolocation;
    var result;
    var mapObj, toolbar, overview, scale, geo;
    var date, startTime;
//地图初始化
    var map = new AMap.Map("container", {
        map: map,
        resizeEnable: true,
        center:cs,
        zoom: 17,
        doubleClickZoom:false, //双击放大地图
        keyboardEnable: false
    });

// 加载功能
    AMap.plugin(["AMap.ToolBar", "AMap.OverView", "AMap.Scale"], function () {
        toolbar = new AMap.ToolBar();
        toolbar.autoPosition = true; //加载工具条
        map.addControl(toolbar);
        overview = new AMap.OverView(); //加载鹰眼
        map.addControl(overview);
        scale = new AMap.Scale(); //加载比例尺
        map.addControl(scale);
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

//    addMarker2();
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
//实例化信息窗体
        var title;
        content = [];
        title=data.result.merchant.merchant.merchantName;
        content.push("<img src='http://tpc.googlesyndication.com/simgad/5843493769827749134'>"+data.result.merchant.merchant.merchantAddress+"");
        content.push("联系电话："+data.result.merchant.merchant.countryPhone);
        content.push("<div style='margin-top: 10px'> </div>");
        AMap.event.addListener(marker, 'click', function() {
            var infoWindow = new AMap.InfoWindow({
                isCustom: true,  //使用自定义窗体
                content: createInfoWindow(title, content.join("<br/>")),
                offset: new AMap.Pixel(16, -45)
            });
            infoWindow.open(map, marker.getPosition());
        });
    }


//构建自定义信息窗体
    function createInfoWindow(title, content) {
        var info = document.createElement("div");
        info.className = "info";

        //可以通过下面的方式修改自定义窗体的宽高
        //info.style.width = "400px";
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
                var lngX = geocode[0].location.getLng();
                var latY = geocode[0].location.getLat();
                map.setCenter(new AMap.LngLat(lngX, latY));
                addMarker2(lngX,latY);
            });
            MGeocoder.getLocation(key_2);  //地理编码

        });
    }
// 逆地理编码 得到具体的地理位置
    function geocoder(x,y) {
        alert(11);
        AMap.plugin('AMap.Geocoder',function(){
            var geocoder = new AMap.Geocoder({

            });
            var lnglatXY=[x,y];
            console.log(x,y);
            geocoder.getAddress(lnglatXY,function(status,result){
                console.log(status);
                if(status=='complete'){
                    console.log(result);
                    document.getElementById('iAddress').innerHTML = result.regeocode.formattedAddress
                }
            })
        });
    }
//鼠标点击，获取经纬度坐标
    function getLnglat(e) {

        map.clearMap();

        var x = e.lnglat.getLng();

        var y = e.lnglat.getLat();

        document.getElementById("lnglat").innerHTML = x + "," + y;

//        lnglatXY = new AMap.LngLat(x, y);
        geocoder(x,y);
        addMarker2(x,y);
    }
    AMap.event.addListener(map, 'click', getLnglat); //点击事件
    addMarker2(Number(longitude),Number(latitude));
    $("#search").click(function(){
        geocoderss();
    })
//    提交更改地址
    $("#mapsubmit").click(function(){
        var num=$("#lnglat").html();
        var arr = num.split(',');
        var longitude = Number(arr[0]);
        var latitude=Number(arr[1]);
        console.log(longitude,latitude);
        var param={
            address:$("#iAddress").html(),
            longitude:longitude,
            latitude:latitude
        }
        var data = invokeService('/web/merchant/modifyMerchantAddressByCurrent',param);
//        console.log(data);
        if (data.statusCode!=1000){
            alert(data.errorMessage);
            return;
        }
    })
})
