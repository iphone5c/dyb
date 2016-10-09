function getMainHeight() {
    var iframe = window.parent.document.getElementById("framecenter");
    var h = $(iframe).height();
    return parseInt(h);
}

function setGridHeight(o, h) {
    var hei = getMainHeight();
    $(o).setHeight(parseInt(hei) - h);
};

//异步
function ajax(u, d, s, e, c, b) {//d表示参数数组，最好与后台方法对应，顺序【参数，参数值,参数，参数值...】
    $.ajax({
        type: 'POST', url: getRequestUrl(u), data: d.toJSON(), timeout: 60000,
        contentType: 'application/json; charset=utf-8', dataType: 'json',
        success: function (data) { if (s) s(data.d); },
        error: function (xhr, msg, ex) { if (e) { var err = eval('(' + xhr.responseText + ')'); e(err.Message); } },
        complete: function (xhr, str) { if (c) c(str); },
        beforeSend: function (xhr) { if (b) b(); }
    });
};


//同步
function ajaxSync(u, d) {//d表示参数数组，最好与后台方法对应，顺序【参数，参数值,参数，参数值...】
    var data = $.ajax({
        type: 'POST', url: getRequestUrl(u), data: d.toJSON(), timeout: 60000,
        contentType: 'application/json; charset=utf-8',
        dataType: 'json', async: false
    }).responseText;
    data = eval('(' + data + ')');
    return data.d;
};

function getRequestUrl(method) {
    var array = self.location.href.split("/");
    var u = array[array.length - 1].split("?")[0].trimSpaces().trimBadWords('/', '').trimBadWords('#', '');
    u = u ? u : 'default.aspx';
    return "{0}/{1}".format(u, method);
};

function eventAttach(e, f) {
    if (window.attachEvent) window.attachEvent('on' + e, f);
    else window.addEventListener(e, f, false);
};

//将数组转化为JSON格式
Array.prototype.toJSON = function () {
    try {
        var array = this;
        if (array && (array.length > 0) && !(array.length % 2)) {
            var d = "";
            for (var i = 0; i < array.length; i++) {
                if (i % 2) {
                    if (typeof (array[i]) === "string")
                        d += "\"" + array[i].replace(/\"/g, "\\\"") + "\",";
                    else if (typeof (array[i]) === "undefined")
                        d += ",";
                    else if (typeof (array[i]) === "number" || typeof (array[i]) === "boolean")
                        d += array[i] + ",";
                    else if (typeof (array[i]) === "object" && array[i].constructor === array)
                        d += ",";
                    else d += ",";
                }
                else d += "\"" + array[i] + "\":";
            }
            return d = "{" + d.substr(0, d.length - 1) + "}";
        } else { return "{}"; }
    } catch (e) { throw e; }
};

//copy数组
Array.prototype.copy = function () { return this.slice(); };

//返回数组中指定字符串的索引
/*
var a=[1,4,5,7,84,45,35]
alert(a.indexOf(5)) //opt 5
*/
Array.prototype.indexOf = function (str) {
    for (var q = 0; q < this.length; q++) {
        if (this[q] == str) { return q; }
    }
    return -1;
};

//数组随机排序	
/*
var a=[1,4,5,7,84,45,35]
a.aSort(2)
alert(a.toString())
*/
Array.prototype.aSort = function (method) {
    function Sort(a, b) {
        if (method == 0 || method == 1) {
            if (a > b) { if (method == 0) { return 1 } else { return -1 } }
            if (a < b) { if (method == 0) { return -1 } else { return 1 } }
            else { return 0 }
        }
        else if (method == 2) { return Math.random() > .5 ? -1 : 1; } //用Math.random()函数生成0~1之间的随机数与0.5比较，返回-1或1       	
    }
    this.sort(Sort);
};

//在数组任意索引处删除一项
Array.prototype.delOfIndex = function (index) { this.splice(index, 1) };

//在数组任意索引处删除多项	
/*
var a=['甲','乙','丙','丁'];
alert(a.delOfIndexs(3,1));
*/
Array.prototype.delOfIndexs = function () {
    var opts = this.sort.call(arguments, Function('a,b', 'return a > b?-1:1;'));
    for (var i = 0; i < opts.length; i++) { this.splice(opts[i], 1); }
    return this;
};

//在数组任意索引后增加一项或多项
Array.prototype.addOfIndex = function (index, arr) { this.splice(index + 1, 0, arr) };

//返回数组中最大项
Array.prototype.max = function () {
    return Math.max.apply({}, this);
};

//返回数组中最小项
Array.prototype.min = function () {
    return Math.min.apply({}, this);
};

//应用模版 d:{'a':'b','c':'d'}
String.prototype.applyTemplate = function (d) {
    try {
        if (d === '') return this;
        return this.replace(/{([^{}]*)}/g,
                    function (a, b) {
                        var r;
                        if (b.indexOf('.') !== -1) { // handle dot notation in {}, such as {Thumbnail.Url}
                            var ary = b.split('.');
                            var obj = d;
                            for (var i = 0; i < ary.length; i++)
                                obj = obj[ary[i]];
                            r = obj;
                        }
                        else
                            r = d[b];
                        if (r === null) r = "";
                        if (typeof r === 'string' || typeof r === 'number') return r; else throw (a);
                    }
                );
    } catch (e) {
        throw 'Invalid JSON property ' + e + ' found when trying to apply Template.\nPlease check your spelling and try again.';
    }
};

//字符串格式化
String.prototype.format = function (params) {
    if (arguments.length == 0) throw 'arguments is null, Please check your spelling and try again.';
    if (arguments.length > 1 && params.constructor != Array) {
        params = $.makeArray(arguments).slice(0);
    }
    if (params.constructor != Array) {
        params = [params];
    }
    var source = this;
    $.each(params, function (i, n) {
        source = source.replace(new RegExp("\\{" + i + "\\}", "g"), n);
    });
    return source;
};

//得到有汉字字符串的长度
String.prototype.chLength = function () {
    var strLen = 0;
    for (i = 0; i < this.length; i++) {
        if (this.charCodeAt(i) > 255) { strLen += 2; }
        else { strLen++; }
    }
    return strLen;
};

String.prototype.valRepalce = function () {
    if (this && this != null) {
        var that = this, s;
        s = that.replace(/\\{1}/g, '/');
        s = that.trimBadWords('"', '\\\\\"');
        return s;
    }
    return '';
};

//去除敏感字符
String.prototype.trimBadWords = function (str, rstr) {
    var reg = new RegExp(str, "gi");
    return this.replace(reg, function (str_bad) { return str_bad.replace(/./g, rstr) });
};

$.fn.readOnly = function (b) {
    if (b == undefined) b = false;
    var emlChildrens = this.find('input,select,textarea');
    emlChildrens = emlChildrens.length ? emlChildrens : this;
    $.each(emlChildrens, function () {
        try { this.readOnly = b; } catch (e) { }
        if (this.type == 'checkbox' || this.type == 'radio' || this.tagName.toLowerCase() == 'select')
            this.disabled = b;
    });
};

$(function () {
    document.onkeypress = shieldBackspace;
    document.onkeydown = shieldBackspace;
});

$.fn.clearTag = function () {
    var _ = this.find("input,select,textarea");
    _ = _.length ? _ : this;
    $.each(_,
    function () {
        var D = this.type,
        E = this.tagName.toLowerCase(),
        B = $(this),
        A = B.attr("static") && B.attr("static").toLowerCase() === "true";
        if (!A) if (D == "text" || D == "password" || D == "hidden" || E == "textarea") { this.value = ""; this.title = ""; }
        else if (D == "checkbox" || D == "radio") {
            this.checked = false;
            try {
                $("." + this.name + this.value).attr("setChecked", "false").click()
            } catch (_) { }
        } else if (E == "select") {
            this.selectedIndex = 0;
            try {
                if (B.hasClass("jqTransformHidden")) {
                    var C = B.prev("ul");
                    $("a:eq(0)", C).click()
                }
            } catch (_) { }
        }
    })
};

function shieldBackspace(event) {
    var e = event || window.event, that = e.target || e.srcElement, t = that.type || that.getAttribute('type');
    var r = that.readOnly || false, d = that.disabled || false;
    if (e.keyCode == 8 && t != "password" && t != "text" && t != "textarea" ||
        e.keyCode == 8 && (t == "password" || t == "text" || t == "textarea") && (r || d))
        return false;
};

//去除字符串首尾空格
String.prototype.trimSpaces = function () {
    var reg = /^\s*(.*?)\s*$/gim;
    return this.replace(reg, "$1");
};

//转化<>标签为实体字符
String.prototype.trimTab = function () {
    var reg = /<|>/g;
    return this.replace(reg, function (s) { if (s == "<") { return "&lt;"; } else { return "&gt;"; } })
};

//转化实体字符为<>标签
String.prototype.formatTab = function () {
    var reg = /&lt;|&gt;|&#39;/ig;
    return this.replace(reg, function (s) { if (s == "&lt;") { return "<"; } else if (s == "&gt;") { return ">"; } else { return "'"; } })
};

//去除任意HTML标签
String.prototype.trimHtml = function (tag) {//不写标签名代表所有标签
    tag ? reg = new RegExp("<\/?" + tag + "(?:(.|\s)*?)>", "gi") : reg = /<(?:.|\s)*?>/gi;
    return this.replace(reg, "");
};


Date.prototype.format = function (format) //author: meizz 
{
    var o = {
        "M+": this.getMonth() + 1, //month 
        "d+": this.getDate(),    //day 
        "h+": this.getHours(),   //hour 
        "m+": this.getMinutes(), //minute 
        "s+": this.getSeconds(), //second 
        "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter 
        "S": this.getMilliseconds() //millisecond 
    }
    if (/(y+)/.test(format)) format = format.replace(RegExp.$1,
    (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o) if (new RegExp("(" + k + ")").test(format))
        format = format.replace(RegExp.$1,
      RegExp.$1.length == 1 ? o[k] :
        ("00" + o[k]).substr(("" + o[k]).length));
    return format;
}

//js浮点数精确计算函数(加，减，乘，除)//浮点数加法运算  
function floatAdd(arg1, arg2) {
    var r1, r2, m;
    try { r1 = arg1.toString().split(".")[1].length } catch (e) { r1 = 0 }
    try { r2 = arg2.toString().split(".")[1].length } catch (e) { r2 = 0 }
    m = Math.pow(10, Math.max(r1, r2));
    return (arg1 * m + arg2 * m) / m;
};

//浮点数减法运算  
function floatSub(arg1, arg2) {
    var r1, r2, m, n;
    try { r1 = arg1.toString().split(".")[1].length } catch (e) { r1 = 0 }
    try { r2 = arg2.toString().split(".")[1].length } catch (e) { r2 = 0 }
    m = Math.pow(10, Math.max(r1, r2));
    //动态控制精度长度  
    n = (r1 >= r2) ? r1 : r2;
    return ((arg1 * m - arg2 * m) / m).toFixed(n);
};

//浮点数乘法运算  
function floatMul(arg1, arg2) {
    var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
    try { m += s1.split(".")[1].length } catch (e) { }
    try { m += s2.split(".")[1].length } catch (e) { }
    return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m)
};

//浮点数除法运算  
function floatDiv(arg1, arg2) {
    var t1 = 0, t2 = 0, r1, r2;
    try { t1 = arg1.toString().split(".")[1].length } catch (e) { }
    try { t2 = arg2.toString().split(".")[1].length } catch (e) { }
    with (Math) {
        r1 = Number(arg1.toString().replace(".", ""));
        r2 = Number(arg2.toString().replace(".", ""));
        return (r1 / r2) * pow(10, t2 - t1);
    }
};

$.fn.resetForm = function () {
    return this.each(function () {
        // guard against an input with the name of 'reset'
        // note that IE reports the reset function as an 'object'
        if (typeof this.reset == 'function' || (typeof this.reset == 'object' && !this.reset.nodeType))
            this.reset();
    });
};

/**
* Returns the value(s) of the element in the matched set.  For example, consider the following form:
*
*  <form><fieldset>
*	  <input name="A" type="text" />
*	  <input name="A" type="text" />
*	  <input name="B" type="checkbox" value="B1" />
*	  <input name="B" type="checkbox" value="B2"/>
*	  <input name="C" type="radio" value="C1" />
*	  <input name="C" type="radio" value="C2" />
*  </fieldset></form>
*
*  var v = $(':text').fieldValue();
*  // if no values are entered into the text inputs
*  v == ['','']
*  // if values entered into the text inputs are 'foo' and 'bar'
*  v == ['foo','bar']
*
*  var v = $(':checkbox').fieldValue();
*  // if neither checkbox is checked
*  v === undefined
*  // if both checkboxes are checked
*  v == ['B1', 'B2']
*
*  var v = $(':radio').fieldValue();
*  // if neither radio is checked
*  v === undefined
*  // if first radio is checked
*  v == ['C1']
*
* The successful argument controls whether or not the field element must be 'successful'
* (per http://www.w3.org/TR/html4/interact/forms.html#successful-controls).
* The default value of the successful argument is true.  If this value is false the value(s)
* for each element is returned.
*
* Note: This method *always* returns an array.  If no valid value can be determined the
*	   array will be empty, otherwise it will contain one or more values.
*/
$.fn.fieldValue = function (successful) {
    for (var val = [], i = 0, max = this.length; i < max; i++) {
        var el = this[i];
        var v = $.fieldValue(el, successful);
        if (v === null || typeof v == 'undefined' || (v.constructor == Array && !v.length))
            continue;
        v.constructor == Array ? $.merge(val, v) : val.push(v);
    }
    return val;
};

/**
* Returns the value of the field element.
*/
$.fieldValue = function (el, successful) {
    var n = el.name, t = el.type, tag = el.tagName.toLowerCase();
    if (typeof successful == 'undefined') successful = true;

    if (successful && (!n || el.disabled || t == 'reset' || t == 'button' ||
		(t == 'checkbox' || t == 'radio') && !el.checked ||
		(t == 'submit' || t == 'image') && el.form && el.form.clk != el ||
		tag == 'select' && el.selectedIndex == -1))
        return null;

    if (tag == 'select') {
        var index = el.selectedIndex;
        if (index < 0) return null;
        var a = [], ops = el.options;
        var one = (t == 'select-one');
        var max = (one ? index + 1 : ops.length);
        for (var i = (one ? index : 0); i < max; i++) {
            var op = ops[i];
            if (op.selected) {
                var v = op.value;
                if (!v) // extra pain for IE...
                    v = (op.attributes && op.attributes['value'] && !(op.attributes['value'].specified)) ? op.text : op.value;
                if (one) return v;
                a.push(v);
            }
        }
        return a;
    }
    return el.value;
};

/**
* Clears the form data.  Takes the following actions on the form's input fields:
*  - input text fields will have their 'value' property set to the empty string
*  - select elements will have their 'selectedIndex' property set to -1
*  - checkbox and radio inputs will have their 'checked' property set to false
*  - inputs of type submit, button, reset, and hidden will *not* be effected
*  - button elements will *not* be effected
*/
$.fn.clearForm = function () {
    return this.each(function () {
        $('input,select,textarea', this).clearFields();
    });
};

/**
* Clears the selected form elements.
*/
$.fn.clearFields = $.fn.clearInputs = function () {
    return this.each(function () {
        var t = this.type, tag = this.tagName.toLowerCase();
        if (t == 'text' || t == 'password' || tag == 'textarea')
            this.value = '';
        else if (t == 'checkbox' || t == 'radio')
            this.checked = false;
        else if (tag == 'select')
            this.selectedIndex = -1;
    });
};

/**
* Enables or disables any matching elements.
*/
$.fn.enable = function (b) {
    if (b == undefined) b = false;
    var emlChildrens = this.find('input,select,textarea');
    emlChildrens = emlChildrens.length ? emlChildrens : this;
    $.each(emlChildrens, function () {
        try { this.disabled = b; } catch (e) { }
    });
};

/**
* Checks/unchecks any matching checkboxes or radio buttons and
* selects/deselects and matching option elements.
*/
$.fn.selected = function (select) {
    if (select == undefined) select = true;
    return this.each(function () {
        var t = this.type;
        if (t == 'checkbox' || t == 'radio')
            this.checked = select;
        else if (this.tagName.toLowerCase() == 'option') {
            var $sel = $(this).parent('select');
            if (select && $sel[0] && $sel[0].type == 'select-one') {
                // deselect all other options
                $sel.find('option').selected(false);
            }
            this.selected = select;
        }
    });
};

//验证是否是数字
function checkPriceValidata(obj) {
    obj.value = obj.value.replace(/[^\d.]/g, "");
    //必须保证第一位为数字而不是. 
    obj.value = obj.value.replace(/^\./g, "");
    //保证只有出现一个.而没有多个. 
    obj.value = obj.value.replace(/\.{2,}/g, ".");
    //保证.只出现一次，而不能出现两次以上 
    obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
    var value = $(obj).val();
}
//jquery的trim处理不了&nbsp;产生的"空格"
function trim(obj) {
    if (obj) { return obj.replace(/(^[\s\xA0]*)|([\s\xA0]*$)/g, ""); }
}

String.prototype.trim = function () {
    if (this) return $.trim(this).replace(/(^[\s\xA0]*)|([\s\xA0]*$)/g, "");
    else return ""
};


$.fn.setTagValueJSON = function (oJson) {
    if (!oJson) return;
    if (typeof oJson === "string") {
        try {
            oJson = oJson.parseJSON();
        } catch (e) { return; }
    }
    if (typeof oJson !== "object") return;

    var emlChildrens = this.find('input,select,textarea');
    emlChildrens = emlChildrens.length ? emlChildrens : this;
    $.each(emlChildrens, function () {
        $.setTagValueByJSON(this, oJson);
    });
};

$.setTagValueByJSON = function (el, oJson) {
    var e = el, n = e.name, t = e.type, tag = e.tagName.toLowerCase();
    if (!n || !oJson[n] || t == 'reset' || t == 'button' || t == 'submit' || t == 'image' || t == 'file') return;
    if (t == 'checkbox' || t == 'radio') {
        if (e.value.trim() == oJson[n].trim()) {
            e.checked = true;
            try {
                if ($(e).hasClass('jqTransformHidden'))
                    $('.' + n + e.value).attr('setChecked', 'true').click();
            } catch (ex) { }
        }
        return;
    }
    if (tag == 'select') {
        if (t != 'select-one') return;
        for (var i = 0; i < e.options.length; i++) {
            var o = e.options[i];
            if (o.value.trim() == oJson[n].trim() || o.text.trim() == oJson[n].trim()) {
                e.selectedIndex = i;
                try {
                    if ($(e).hasClass('jqTransformHidden')) {
                        var $ul = $(e).prev('ul');
                        $('a:eq(' + i + ')', $ul).click();
                    }
                } catch (ex) { }
                break;
            }
        }
        return;
    }
    e.value = oJson[n];
};
//验证是否是数字
function checkChValidata(obj) {
    obj.value = obj.value.replace(/[^\u3447-\uFA29]/, "");
    var value = $(obj).val();
}
//验证如果是非法字符，那么就截取
function checkObjValidata(obj) {
    // 如果出现非法字符就截取掉
    if (obj.value.substr((obj.value.length - 1), 1) == '.')
        obj.value = obj.value.substr(0, (obj.value.length - 1));
    obj.value = FormatTwoDecimal(obj.value); //调用方法，强制保留两位小数
}

function FormatThree(number) {
    var s = FormatMoney(number);
    return s.substr(0, s.length - 3);
}

//财务千位符转换
function FormatMoney(number) {
    if (!number) return "0.00";
    number = number.replace(/\,/g, "");
    if (isNaN(number) || number == "") return "0.00";
    number = Math.round(number * 100) / 100;
    if (number < 0)
        return '-' + outputDollars(Math.floor(Math.abs(number) - 0) + '') + outputCents(Math.abs(number) - 0);
    else
        return outputDollars(Math.floor(number - 0) + '') + outputCents(number - 0);
}
//千位符转换
function outputDollars(number) {
    if (!number) return "0";
    if (number.length <= 3)
        return (number == '' ? '0' : number);
    else {
        var mod = number.length % 3;
        var output = (mod == 0 ? '' : (number.substring(0, mod)));
        for (i = 0; i < Math.floor(number.length / 3); i++) {
            if ((mod == 0) && (i == 0))
                output += number.substring(mod + 3 * i, mod + 3 * i + 3);
            else
                output += ',' + number.substring(mod + 3 * i, mod + 3 * i + 3);
        }
        return (output);
    }
}
//千位符转换
function outputCents(amount) {
    amount = Math.round(((amount) - Math.floor(amount)) * 100);
    return (amount < 10 ? '.0' + amount : '.' + amount);
}
//日期格式化
function FormatDataTime(s) {
    if (s != "" && s != null) {
        var d = new Date(s.replace(/-/g, "/"));
        return d.format("yyyy-MM-dd");
    } else return "";
}
function FormatDataTimeSecond(s) {
    if (s != "" && s != null) {
        var d = new Date(s.replace(/-/g, "/"));
        return d.format("yyyy-MM-dd hh:mm:ss");
    } else return "";
}
//四舍五入，强制保留2位小数
function FormatTwoDecimal(x) {
    var f_x = parseFloat(x);
    if (isNaN(f_x)) {
        return "0.00";
    }
    f_x = Math.round(f_x * 100) / 100;
    var s_x = f_x.toString();
    var pos_decimal = s_x.indexOf('.');
    if (pos_decimal < 0) {
        pos_decimal = s_x.length;
        s_x += '.';
    }
    while (s_x.length <= pos_decimal + 2) {
        s_x += '0';
    }
    return s_x;
}

function getPositionForInputText(inputText) {
    var gPos = 0;
    if (inputText.tagName == 'INPUT') {//获取单行文本框光标位置
        if (document.selection) {
            inputText.focus();
            var cRange = document.selection.createRange();
            cRange.moveStart('character', -inputText.value.length);
            gPos = cRange.text.length;
        } else if (inputText.selectionStart || inputText.selectionStart == '0') {
            gPos = inputText.selectionStart;
        }
    } else {//获取多行文本框光标位置        
        if (document.selection) {
            inputText.focus();
            var cRange = document.selection.createRange();
            var dCate = cRange.duplicate();
            dCate.moveToElementText(inputText);
            gPos = -1;
            while (dCate.inRange(cRange)) {
                dCate.moveStart('character');
                gPos++;
            }
        } else if (inputText.selectionStart || inputText.selectionStart == '0') {
            gPos = inputText.selectionStart;
        }
    }
    return (gPos);
}

//设置光标位置函数 
function setCursorPosition(inputText, gPos) {
    if (inputText.setSelectionRange) {
        inputText.focus();
        inputText.setSelectionRange(gPos, gPos);
    }
    else if (inputText.createTextRange) {
        var tRange = inputText.createTextRange();
        tRange.collapse(true);
        tRange.moveEnd('character', gPos);
        tRange.moveStart('character', gPos);
        tRange.select();
    }
}

//过滤N/A
function FormatFilterNA(v) {
    return v == "N/A" ? "" : v;
}

//上移
function UpTr(obj) {
    var onthis = $(obj).parents("tr");
    var getup = $(obj).parents("tr").prev();
    if (getup.length == 0) {
        //  alert("已经是最上一层啦");
        return false;
    }
    $(onthis).fadeOut(50, function () {
        if ($(getup).before(onthis)) {
            $(onthis).fadeIn(50);
        }
    });
    return true;
}
//下移 
function DownTr(obj) {
    var onthis = $(obj).parents("tr");
    var getdown = $(obj).parents("tr").next();
    if (getdown.length == 0) {
        return false;
    }
    $(onthis).fadeOut(50, function () {
        if ($(getdown).after(onthis)) {
            $(onthis).fadeIn(50);
        }
    });
    return true;
}
//删除[上移,下移]
function RemoveTr(obj) {
    $(obj).parent().remove();
}
//整形转换成二进制格式
function FormatIntToBinary(v) {
    return parseInt(v).toString(2);
}
//二进制转换成字符串  例如：二进制格式的数据、转换后：2012-07-06 09:22:24.854
function FormatByteToString(d) {
    // 声明变量。
    var data;
    data = d.getFullYear() + "-";
    data += ("0" + (d.getMonth() + 1)).slice(-2) + "-";
    data += ("0" + d.getDate()).slice(-2) + " ";
    data += ("0" + d.getHours()).slice(-2) + ":";
    data += ("0" + d.getMinutes()).slice(-2) + ":";
    data += ("0" + d.getSeconds()).slice(-2) + ".";
    data += ("00" + d.getMilliseconds()).slice(-3);
    return data;
}

/** 
* Map对象，实现Map功能 
* size() 获取Map元素个数  
* isEmpty() 判断Map是否为空  
* clear() 删除Map所有元素  
* put(key, value) 向Map中增加元素（key, value)  
* remove(key) 删除指定key的元素，成功返回true，失败返回false  
* get(key) 获取指定key的元素值value，失败返回null  
* element(index) 获取指定索引的元素（使用element.key，element.value获取key和value），失败返回null  
* containsKey(key) 判断Map中是否含有指定key的元素  
* containsValue(value) 判断Map中是否含有指定value的元素  
* keys() 获取Map中所有key的数组（array）  
* values() 获取Map中所有value的数组（array） 
*/
function Map() {
    this.elements = new Array();

    // 获取Map元素个数  
    this.size = function () {
        return this.elements.length;
    },

    // 判断Map是否为空  
        this.isEmpty = function () {
            return (this.elements.length < 1);
        },

    // 删除Map所有元素  
        this.clear = function () {
            this.elements = new Array();
        },

    // 向Map中增加元素（key, value)  
        this.put = function (_key, _value) {
            if (this.containsKey(_key) == true) {
                if (this.containsValue(_value)) {
                    if (this.remove(_key) == true) {
                        this.elements.push({
                            key: _key,
                            value: _value
                        });
                    }
                } else {
                    this.elements.push({
                        key: _key,
                        value: _value
                    });
                }
            } else {
                this.elements.push({
                    key: _key,
                    value: _value
                });
            }
        },

    // 删除指定key的元素，成功返回true，失败返回false  
        this.remove = function (_key) {
            var bln = false;
            try {
                for (i = 0; i < this.elements.length; i++) {
                    if (this.elements[i].key == _key) {
                        this.elements.splice(i, 1);
                        return true;
                    }
                }
            } catch (e) {
                bln = false;
            }
            return bln;
        },

    // 获取指定key的元素值value，失败返回null  
        this.get = function (_key) {
            try {
                for (i = 0; i < this.elements.length; i++) {
                    if (this.elements[i].key == _key) {
                        return this.elements[i].value;
                    }
                }
            } catch (e) {
                return null;
            }
        },

    // 获取指定索引的元素（使用element.key，element.value获取key和value），失败返回null  
        this.element = function (_index) {
            if (_index < 0 || _index >= this.elements.length) {
                return null;
            }
            return this.elements[_index];
        },

    // 判断Map中是否含有指定key的元素  
        this.containsKey = function (_key) {
            var bln = false;
            try {
                for (i = 0; i < this.elements.length; i++) {
                    if (this.elements[i].key == _key) {
                        bln = true;
                    }
                }
            } catch (e) {
                bln = false;
            }
            return bln;
        },

    // 判断Map中是否含有指定value的元素  
        this.containsValue = function (_value) {
            var bln = false;
            try {
                for (i = 0; i < this.elements.length; i++) {
                    if (this.elements[i].value == _value) {
                        bln = true;
                    }
                }
            } catch (e) {
                bln = false;
            }
            return bln;
        },

    // 获取Map中所有key的数组（array）  
        this.keys = function () {
            var arr = new Array();
            for (i = 0; i < this.elements.length; i++) {
                arr.push(this.elements[i].key);
            }
            return arr;
        },

    // 获取Map中所有value的数组（array）  
        this.values = function () {
            var arr = new Array();
            for (i = 0; i < this.elements.length; i++) {
                arr.push(this.elements[i].value);
            }
            return arr;
        };
}
function extend(des, src, override) {
    if (src instanceof Array) {
        for (var i = 0, len = src.length; i < len; i++)
            extend(des, src[i], override);
    }
    for (var i in src) {
        if (override || !(i in des)) {
            des[i] = src[i];
        }
    }
    return des;
}