/**
 * 提交选择的队伍
 * @param key
 */
function submitTeam(key){
    var checked = $('#'+key).val();
    if(isEmpty(checked)){
        $('.bombBoxbg').show()
        return false;
    }


    jQuery('#selectTeamCompetition').load("/lol/main/jsp/mainIndexFrameTeam",{teamCode:checked},function(data){
        $(".content-steptwo").show();
        $(".content-stepone").hide();
        document.body.scrollTop=0;

        // 选择小龙数量
        $(".num").click(function(){
            $(".num4").toggle();
        });
        $(".num4,.num").mouseleave(function(){
            $(".num4").hide();

        });
        $(".num4 ul li").click(function(){
            var i= $(".num4 ul li").index(this);
            var x = $(".num4 ul li").eq(i).html();
            $(".num1").html(x)
        });
        // 第三步确认选择并弹出投注框
        $(".confirm").click(function(){
            $(".bettingBg").show();
        })
        $(".bettingTop img").click(function(){
            $(".bettingBg").hide();
        })
        // 第三步选中具体玩法
        $(".gameplayOne>ul li").click(function(){
            var i= $(".gameplayOne>ul li").index(this);
            $(".gameplayOne>ul li").removeClass("active1");
            $(".gameplayOne>ul li").eq(i).addClass("active1");
        });
        // 第三步选中玩法
        $(".concretePlay div div").click(function(){
            var i= $(".concretePlay div div").index(this);
            $(".concretePlay div div").removeClass("active2");
            $(".concretePlay div div").eq(i).addClass("active2");
        })

    })
}

function seletedTeam(key,value,old){

    var values=$("input[name="+old+"]");
    for(var i=0;i<values.length;i++){
        values[i].value="";
    }
    $('#'+key).val(value)
}

function loadData(){
    var result = invokeService('/lol/main/json/mainIndexFrameTeam',{restrainCode:$('#restrainCode').val()});
    if(result.statusCode!=1000){
        alert(result.errorMessage)
        return false;
    }
    result=result.result;
    var html = '<div class="gameplayOne">' +
        '<div class="gameplayLink"></div>' +
        '<ul>';
    var html1 = '';
    for(var i=0;i<result.length;i++){
        console.log(result[i].play+"----->"+result[i].content);
        html+='<li onclick=showButton(\"'+result[i].code+'\")>'+result[i].play+'</li>';
        html1 +=playHtml(result[i]);
    }
    html+=html1;
    html+='</ul></div>'
    $('#playGame').html(html);

}

function showButton(playCode){
    $('.concretePlay').hide();
    $('#'+playCode).show()
}

function playHtml(result){
    var html = '';
    var content = result.content;
    console.log(content)
    if(result.play=='总局输赢'){
        var button = content.split(",");
        var tA = button[0].split(":");
        var tB = button[1].split(":");
        html+='<div class="concretePlay" id="'+result.code+'">' +
                '<span class="gameTitle">竞猜全球总决赛获胜的战队</span>' +
                '<div class="gameTwo">' +
                    '<div onclick="selectedPlay(\'总局输赢\',\'投注'+tA[1]+'-'+tA[2]+'\',\''+result.code+'||'+button[0]+'\')">投注'+tA[1]+'('+tA[2]+')</div>' +
                    '<div onclick="selectedPlay(\'总局输赢\',\'投注'+tB[1]+'-'+tB[2]+'\',\''+result.code+'||'+button[1]+'\')">投注'+tB[1]+'('+tB[2]+')</div>' +
                '</div>' +
            '</div>';
    }else if(result.play=='单局输赢'){
        var button = content.split(",");
        var tA = button[0].split(":");
        var tB = button[1].split(":");
        html+='<div class="concretePlay" id="'+result.code+'">' +
                '<span class="gameTitle">竞猜获得当局胜利的战队</span>' +
                '<div class="gameTwo">' +
                    '<div onclick="selectedPlay(\'单局输赢\',\'投注'+tA[1]+'-'+tA[2]+'\',\''+result.code+'||'+button[0]+'\')">投注'+tA[1]+'('+tA[2]+')</div>' +
                    '<div onclick="selectedPlay(\'单局输赢\',\'投注'+tB[1]+'-'+tB[2]+'\',\''+result.code+'||'+button[1]+'\')">投注'+tB[1]+'('+tB[2]+')</div>' +
                '</div>' +
            '</div>';
    }else if(result.play=='比分'){
        var button = content.split(",");
        var t1 = button[0].split(":");
        var t1_bf = t1[0].split(";")
        var t2 = button[1].split(":");
        var t2_bf = t2[0].split(";")
        var t3 = button[2].split(":");
        var t3_bf = t3[0].split(";")
        var t4 = button[3].split(":");
        var t4_bf = t4[0].split(";")
        var t5 = button[4].split(":");
        var t5_bf = t5[0].split(";")
        var t6 = button[5].split(":");
        var t6_bf = t6[0].split(";")
        html+='<div class="concretePlay" id="'+result.code+'">' +
                '<span class="gameTitle">竞猜对战局数比分</span>' +
                '<div class="gameThree">' +
                    '<div style="margin-left: 295px" onclick="selectedPlay(\'比分\',\''+t1_bf[1]+'-'+t1[1]+'\',\''+result.code+'||'+button[0]+'\')">'+t1_bf[1]+'('+t1[1]+')'+'</div>' +
                    '<div onclick="selectedPlay(\'比分\',\''+t2_bf[1]+'-'+t2[1]+'\',\''+result.code+'||'+button[1]+'\')">'+t2_bf[1]+'('+t2[1]+')'+'</div>' +
                    '<div onclick="selectedPlay(\'比分\',\''+t3_bf[1]+'-'+t3[1]+'\',\''+result.code+'||'+button[2]+'\')">'+t3_bf[1]+'('+t3[1]+')'+'</div>' +

                    '<div style="margin-left: 295px" onclick="selectedPlay(\'比分\',\''+t4_bf[1]+'-'+t4[1]+'\',\''+result.code+'||'+button[3]+'\')">'+t4_bf[1]+'('+t4[1]+')'+'</div>' +
                    '<div onclick="selectedPlay(\'比分\',\''+t5_bf[1]+'-'+t5[1]+'\',\''+result.code+'||'+button[4]+'\')">'+t5_bf[1]+'('+t5[1]+')'+'</div>' +
                    '<div onclick="selectedPlay(\'比分\',\''+t6_bf[1]+'-'+t6[1]+'\',\''+result.code+'||'+button[5]+'\')">'+t6_bf[1]+'('+t6[1]+')'+'</div>' +
                '</div>' +
            '</div>';
    }else if(result.play=='一血'){

        var button = content.split(",");
        var UP = button[0].split(":");
        var CENTER = button[1].split(":");
        var DOWN = button[2].split(":");
        var QT = button[3].split(":");
        html+='<div class="concretePlay" id="'+result.code+'">' +
                '<span class="gameTitle">竞猜"一血"所属位置</span>' +
                    '<div class="gameFour">' +
                        '<div onclick="selectedPlay(\'一血\',\'上单-'+UP[1]+'\',\''+result.code+'||'+button[0]+'\')">上单('+UP[1]+')</div>' +
                        '<div onclick="selectedPlay(\'一血\',\'中单-'+CENTER[1]+'\',\''+result.code+'||'+button[1]+'\')">中单('+CENTER[1]+')</div>' +
                        '<div onclick="selectedPlay(\'一血\',\'下路组合-'+DOWN[1]+'\',\''+result.code+'||'+button[2]+'\')">下路组合('+DOWN[1]+')</div>' +
                        '<div onclick="selectedPlay(\'一血\',\'打野-'+QT[1]+'\',\''+result.code+'||'+button[3]+'\')">打野('+QT[1]+')</div>' +
                    '</div>' +
                '</div>';
    }else if(result.play=='一塔'){
        var button = content.split(",");
        var UP = button[0].split(":");
        var CENTER = button[1].split(":");
        var DOWN = button[2].split(":");
        html+='<div class="concretePlay" id="'+result.code+'">' +
                '<span class="gameTitle">竞猜"一塔"所属线路</span>' +
                '<div class="gameFour">' +
                    '<div style="margin-left: 215px"  onclick="selectedPlay(\'一塔\',\'上路-'+UP[1]+'\',\''+result.code+'||'+button[0]+'\')">上路('+UP[1]+')</div>' +
                    '<div onclick="selectedPlay(\'一塔\',\'中路-'+CENTER[1]+'\',\''+result.code+'||'+button[1]+'\')">中路('+CENTER[1]+')</div>' +
                    '<div onclick="selectedPlay(\'一塔\',\'下路-'+DOWN[1]+'\',\''+result.code+'||'+button[2]+'\')">下路('+DOWN[1]+')</div>' +
                '</div>' +
            '</div>';
    }else if(result.play=='一小龙'){
        var button = content.split(",");
        var tA = button[0].split(":");
        var tB = button[1].split(":");
        var OTHER = button[2].split(":");
        html+='<div class="concretePlay" id="'+result.code+'">' +
                '<span class="gameTitle">竞猜"一小龙"所属战队</span>' +
                '<div class="gameFive">' +
                    '<div style="margin-left: 215px" onclick="selectedPlay(\'一小龙\',\'投注'+tA[1]+'-'+tA[2]+'\',\''+result.code+'||'+button[0]+'\')">投注'+tA[1]+'('+tA[2]+')</div>' +
                    '<div onclick="selectedPlay(\'一小龙\',\'投注'+tB[1]+'-'+tB[2]+'\',\''+result.code+'||'+button[1]+'\')">投注'+tB[1]+'('+tB[2]+')</div>' +
                    '<div onclick="selectedPlay(\'一小龙\',\'投注均无-'+OTHER[1]+'\',\''+result.code+'||'+button[2]+'\')">均无('+OTHER[1]+')</div>' +
                '</div>' +
            '</div>';
    }else if(result.play=='第一亚龙属性'){
        var button = content.split(",");
        var WIND = button[0].split(":");
        var FIRE = button[1].split(":");
        var SOIL = button[2].split(":");
        var WATER = button[3].split(":");
        html+='<div class="concretePlay" id="'+result.code+'">' +
                '<span class="gameTitle">竞猜"第一亚龙属性"所属属性</span>' +
                '<div class="gameFour">' +
                    '<div onclick="selectedPlay(\'第一亚龙属性\',\'风属性-'+WIND[1]+'\',\''+result.code+'||'+button[0]+'\')">风属性('+WIND[1]+')</div>' +
                    '<div onclick="selectedPlay(\'第一亚龙属性\',\'火属性-'+FIRE[1]+'\',\''+result.code+'||'+button[1]+'\')">火属性('+FIRE[1]+')</div>' +
                    '<div onclick="selectedPlay(\'第一亚龙属性\',\'土属性-'+SOIL[1]+'\',\''+result.code+'||'+button[2]+'\')">土属性('+SOIL[1]+')</div>' +
                    '<div onclick="selectedPlay(\'第一亚龙属性\',\'水属性-'+WATER[1]+'\',\''+result.code+'||'+button[3]+'\')">水属性('+WATER[1]+')</div>' +
                '</div>' +
            '</div>';
    }else if(result.play=='小龙数量'){
        var button = content.split(",");
        var tA = button[0].split(":");
        var tB = button[1].split(":");
        var OTHER = button[2].split(":");
        html+='<div class="concretePlay" id="'+result.code+'">' +
                '<span class="gameTitle">竞猜对战结束时战队所获小龙数量</span>' +
                '<div class="gameFive">' +
                    '<div style="margin-left: 215px" onclick="selectedPlay(\'小龙数量\',\'投注'+tA[1]+'-'+tA[2]+'\',\''+result.code+'||'+button[0]+'\')">投注'+tA[1]+'('+tA[2]+')</div>' +
                    '<div onclick="selectedPlay(\'小龙数量\',\'投注'+tB[1]+'-'+tB[2]+'\',\''+result.code+'||'+button[1]+'\')">投注'+tB[1]+'('+tB[2]+')</div>' +
                    '<div onclick="selectedPlay(\'小龙数量\',\'投注相同-'+OTHER[1]+'\',\''+result.code+'||'+button[2]+'\')">相同('+OTHER[1]+')</div>' +
                '</div>' +
            '</div>';
    }else if(result.play=='一大龙'){
        var button = content.split(",");
        var tA = button[0].split(":");
        var tB = button[1].split(":");
        var OTHER = button[2].split(":");
        html+='<div class="concretePlay" id="'+result.code+'">' +
                '<span class="gameTitle">竞猜对战中所获的第一条大龙所属战队</span>' +
                '<div class="gameFive">' +
                    '<div style="margin-left: 215px" onclick="selectedPlay(\'一大龙\',\'投注'+tA[1]+'-'+tA[2]+'\',\''+result.code+'||'+button[0]+'\')">投注'+tA[1]+'('+tA[2]+')</div>' +
                    '<div onclick="selectedPlay(\'一大龙\',\'投注'+tB[1]+'-'+tB[2]+'\',\''+result.code+'||'+button[1]+'\')">投注'+tB[1]+'('+tB[2]+')</div>' +
                    '<div onclick="selectedPlay(\'一大龙\',\'投注均无-'+OTHER[1]+'\',\''+result.code+'||'+button[2]+'\')">均无('+OTHER[1]+')</div>' +
                '</div>' +
            '</div>';
    }else if(result.play=='一峡谷先锋'){
        var button = content.split(",");
        var tA = button[0].split(":");
        var tB = button[1].split(":");
        var OTHER = button[2].split(":");
        html+='<div class="concretePlay" id="'+result.code+'">' +
                '<span class="gameTitle">竞猜对战中攻占第一条峡谷先锋的战队</span>' +
                '<div class="gameFive">' +
                    '<div style="margin-left: 215px" onclick="selectedPlay(\'一峡谷先锋\',\'投注'+tA[1]+'-'+tA[2]+'\',\''+result.code+'||'+button[0]+'\')">投注'+tA[1]+'('+tA[2]+')</div>' +
                    '<div onclick="selectedPlay(\'一峡谷先锋\',\'投注'+tB[1]+'-'+tB[2]+'\',\''+result.code+'||'+button[1]+'\')">投注'+tB[1]+'('+tB[2]+')</div>' +
                    '<div onclick="selectedPlay(\'一峡谷先锋\',\'投注均无-'+OTHER[1]+'\',\''+result.code+'||'+button[2]+'\')">均无('+OTHER[1]+')</div>' +
                '</div>' +
            '</div>';
    }else if(result.play=='单局四杀'){
        var button = content.split(",");
        var tA = button[0].split(":");
        var tB = button[1].split(":");
        var OTHER = button[2].split(":");
        html+='<div class="concretePlay" id="'+result.code+'">' +
                '<span class="gameTitle">竞猜单局对战中获得四杀的的战队</span>' +
                '<div class="gameFive">' +
                    '<div style="margin-left: 215px" onclick="selectedPlay(\'单局四杀\',\'投注'+tA[1]+'-'+tA[2]+'\',\''+result.code+'||'+button[0]+'\')">投注'+tA[1]+'('+tA[2]+')</div>' +
                    '<div onclick="selectedPlay(\'单局四杀\',\'投注'+tB[1]+'-'+tB[2]+'\',\''+result.code+'||'+button[1]+'\')">投注'+tB[1]+'('+tB[2]+')</div>' +
                    '<div onclick="selectedPlay(\'单局四杀\',\'投注均无-'+OTHER[1]+'\',\''+result.code+'||'+button[2]+'\')">均无('+OTHER[1]+')</div>' +
                '</div>' +
            '</div>';
    }else if(result.play=='单局五杀'){
        var button = content.split(",");
        var tA = button[0].split(":");
        var tB = button[1].split(":");
        var OTHER = button[2].split(":");
        html+='<div class="concretePlay" id="'+result.code+'">' +
                '<span class="gameTitle">竞猜单局对战中获得五杀的的战队</span>' +
                '<div class="gameFive">' +
                    '<div style="margin-left: 215px" onclick="selectedPlay(\'单局五杀\',\'投注'+tA[1]+'-'+tA[2]+'\',\''+result.code+'||'+button[0]+'\')">投注'+tA[1]+'('+tA[2]+')</div>' +
                    '<div onclick="selectedPlay(\'单局五杀杀\',\'投注'+tB[1]+'-'+tB[2]+'\',\''+result.code+'||'+button[1]+'\')">投注'+tB[1]+'('+tB[2]+')</div>' +
                    '<div onclick="selectedPlay(\'单局五杀\',\'投注均无-'+OTHER[1]+'\',\''+result.code+'||'+button[2]+'\')">均无('+OTHER[1]+')</div>' +
                '</div>' +
            '</div>';
    }else if(result.play=='率先十杀'){
        var button = content.split(",");
        var tA = button[0].split(":");
        var tB = button[1].split(":");
        var OTHER = button[2].split(":");
        html+='<div class="concretePlay" id="'+result.code+'">' +
                '<span class="gameTitle">竞猜单局对战中率先获得10个人头的战队</span>' +
                '<div class="gameFive">' +
                    '<div style="margin-left: 215px" onclick="selectedPlay(\'率先十杀\',\'投注'+tA[1]+'-'+tA[2]+'\',\''+result.code+'||'+button[0]+'\')">投注'+tA[1]+'('+tA[2]+')</div>' +
                    '<div onclick="selectedPlay(\'率先十杀\',\'投注'+tB[1]+'-'+tB[2]+'\',\''+result.code+'||'+button[1]+'\')">投注'+tB[1]+'('+tB[2]+')</div>' +
                    '<div onclick="selectedPlay(\'率先十杀\',\'投注均无-'+OTHER[1]+'\',\''+result.code+'||'+button[2]+'\')">均无('+OTHER[1]+')</div>' +
                '</div>' +
            '</div>';
    }else if(result.play=='单局超神'){
        var button = content.split(",");
        var tA = button[0].split(":");
        var tB = button[1].split(":");
        var OTHER = button[2].split(":");
        html+='<div class="concretePlay" id="'+result.code+'">' +
                '<span class="gameTitle">竞猜单局对战中“单局超神”所属战队</span>' +
                '<div class="gameFive">' +
                    '<div style="margin-left: 215px" onclick="selectedPlay(\'单局超神\',\'投注'+tA[1]+'-'+tA[2]+'\',\''+result.code+'||'+button[0]+'\')">投注'+tA[1]+'('+tA[2]+')</div>' +
                    '<div onclick="selectedPlay(\'单局超神\',\'投注'+tB[1]+'-'+tB[2]+'\',\''+result.code+'||'+button[1]+'\')">投注'+tB[1]+'('+tB[2]+')</div>' +
                    '<div onclick="selectedPlay(\'单局超神\',\'投注均无-'+OTHER[1]+'\',\''+result.code+'||'+button[2]+'\')">均无('+OTHER[1]+')</div>' +
                '</div>' +
            '</div>';
    }else if(result.play=='一水晶'){
        var button = content.split(",");
        var UP = button[0].split(":");
        var CENTER = button[1].split(":");
        var DOWN = button[2].split(":");
        html+='<div class="concretePlay" id="'+result.code+'">' +
                '<span class="gameTitle">竞猜单局对战中所获的第一座水晶所属线路</span>' +
                '<div class="gameFour">' +
                    '<div style="margin-left: 215px"  onclick="selectedPlay(\'一水晶\',\'上路-'+UP[1]+'\',\''+result.code+'||'+button[0]+'\')">上路('+UP[1]+')</div>' +
                    '<div onclick="selectedPlay(\'一水晶\',\'中路-'+CENTER[1]+'\',\''+result.code+'||'+button[1]+'\')">中路('+CENTER[1]+')</div>' +
                    '<div onclick="selectedPlay(\'一水晶\',\'下路-'+DOWN[1]+'\',\''+result.code+'||'+button[2]+'\')">下路('+DOWN[1]+')</div>' +
                '</div>' +
            '</div>';
    }else if(result.play=='单方人头数单双'){
        var button = content.split(",");
        var tA = button[0].split(":");
        var tB = button[1].split(":");
        var tC = button[2].split(":");
        var tD = button[3].split(":");
        html+='<div class="concretePlay" id="'+result.code+'">' +
                '<span class="gameTitle">竞猜对战单方最终所获人头数的单双</span>' +
                '<div class="gameFour">' +
                    '<div onclick="selectedPlay(\'单方人头数单双\',\'投注'+tA[1]+tA[2]+'-'+tA[3]+'\',\''+result.code+'||'+button[0]+'\')">投注'+tA[1]+tA[2]+'('+tA[3]+')</div>' +
                    '<div onclick="selectedPlay(\'单方人头数单双\',\'投注'+tB[1]+tB[2]+'-'+tB[3]+'\',\''+result.code+'||'+button[1]+'\')">投注'+tB[1]+tB[2]+'('+tB[3]+')</div>' +
                    '<div onclick="selectedPlay(\'单方人头数单双\',\'投注'+tC[1]+tC[2]+'-'+tC[3]+'\',\''+result.code+'||'+button[2]+'\')">投注'+tC[1]+tC[2]+'('+tC[3]+')</div>' +
                    '<div onclick="selectedPlay(\'单方人头数单双\',\'投注'+tD[1]+tD[2]+'-'+tD[3]+'\',\''+result.code+'||'+button[3]+'\')">投注'+tD[1]+tD[2]+'('+tD[3]+')</div>' +
                '</div>' +
            '</div>';
    }else if(result.play=='总人头数单双'){
        var button = content.split(",");
        var SINGULAR = button[0].split(":");
        var DUAL = button[1].split(":");
        html+='<div class="concretePlay" id="'+result.code+'">' +
                '<span class="gameTitle">竞猜对战双方最终所获人头总数的单双</span>' +
                '<div class="gameTwo">' +
                    '<div onclick="selectedPlay(\'总人头数单双\',\'投注单数-'+SINGULAR[1]+'\',\''+result.code+'||'+button[0]+'\')">投注单数('+SINGULAR[1]+')</div>' +
                    '<div onclick="selectedPlay(\'总人头数单双\',\'投注双数-'+DUAL[1]+'\',\''+result.code+'||'+button[1]+'\')">投注双数('+DUAL[1]+')</div>' +
            '   </div>' +
            '</div>';
    }
    return html;
}

function selectedPlay(title,info,select){
    $('#title').val(title);
    $('#info').val(info);
    $('#selectPlayRecord').val(select);
}

function confirmSelect(){
    $('#titleConfirm').html($('#title').val());
    $('#infoConfirm').html($('#info').val());
    $('.bettingBg').show();
}

function submitTz(){
    var content = $('#selectPlayRecord').val().split("||");
    var quizMoney = $('#quizMoney').val();
    var reg = new RegExp("^[0-9]+(.[0-9]{1,3})?$");
    if(!reg.test(quizMoney)){
        alert("请输入数字!");
        return false;
    }
    var param = {
        playRecordCode:content[0],
        quizMoney:quizMoney,
        betting:content[1]
    }
    var result = invokeService('/lol/bettingrecord/json/applyTopUp',param);
    if(result.statusCode!=1000){
        alert(result.errorMessage)
        return false;
    }
    window.location.href='/lol/main/jsp/mainIndex';
}

function expectNum(){
    var content = $('#infoConfirm').html().split('-');
    var quizMoney = $('#quizMoney').val();
    var reg = new RegExp("^[0-9]+(.[0-9]{1,3})?$");
    if(!reg.test(quizMoney)){
        $('#quizMoney').val();
        return false;
    }
    $('#expectMoney').html(content[content.length-1]*quizMoney);
}