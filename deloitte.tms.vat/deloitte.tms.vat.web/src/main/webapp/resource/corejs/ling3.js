var sidkey="LING_sid"
var _last_jumping_url=path+"_last_jumping_url";

var ERRO=0;
var NOTLOGIN=1;
var NOTAUTH=2;
var BUSINESSEXCEPTION=3;
var INNEREXCEPTION=4;
var NULLDATA=5;

var DATA="data";
var STATUS="status";
var ERROCODE="erroCode";
var ERROMESSAGE="erroMessage";

var SUCCESS=0;
var FAIL=1;

var MAX_HIS_NUM=100;

var NIKINAME="LING_nickName";
var LOGIN_ERROKEY="LOGIN_ERROKEY";
processLoginErroCode=function(){
	var erroinfo=getStorage(LOGIN_ERROKEY);
	console.log(erroinfo);
	if(erroinfo){
		erro(erroinfo);
		delStorage(LOGIN_ERROKEY);
	}
}
processErroCode=function(data){
	if(data.status == 1){
		switch(data.erroCode){
			case NOTLOGIN:
				setStorage(_last_jumping_url,window.location.pathname+window.location.search);
				window.location.href=loginpath;
			default :
				warning(data.erroMessage);
		}
	}
}
toastr.options = {
		  "closeButton": true,
		  "debug": false,
		  "positionClass": "toast-bottom-right",
		  "onclick": null,
		  "showDuration": "1000",
		  "hideDuration": "1000",
		  "timeOut": "5000",
		  "extendedTimeOut": "1000",
		  "showEasing": "swing",
		  "hideEasing": "linear",
		  "showMethod": "fadeIn",
		  "hideMethod": "fadeOut"
}
success=function(message){
//	$.dialog({
//		content : message,
//		title: "alert",
//		time : 2000
//	});
	toastr['success'](message, "Success");
}
warning=function(message){
//	$.dialog({
//		content : message,
//		title: "alert",
//		time : 2000
//	});
	toastr['warning'](message, "Warning");
}
info=function(message){
//	$.dialog({
//		content : message,
//		title: "alert",
//		time : 2000
//	});
	toastr['info'](message, "Info");
}
erro=function(message){
//	$.dialog({
//		content : message,
//		title: "alert",
//		time : 2000
//	});
	toastr['error'](message, "Error");
}
alert=function(message){
//	$.dialog({
//		content : message,
//		title: "alert",
//		time : 2000
//	});
	toastr['info'](message, "Info");
}
infoandgopage=function(message,path){
	info(message);
	setTimeout(function () {
		gopage(path);
    },700);	 
}
gopage=function(path){
	window.location.href=path;
}
confirm=function(message,ok,cancel){
	$.dialog({
        content : message,
        title : 'ok',
        ok : ok,
        cancel : cancel,
        lock : false
    });
}
join=function (oldstr,newstr,split){
	if(oldstr!=null&&''!=oldstr){
		return oldstr+split+newstr;
	}else{
		return newstr;
	}
} 
convertToUrlParam = function(obj)  
{    
    var query = '';  
    var name, value, fullSubName, subName, subValue, innerObj, i;  
    //console.log(obj);  
    for(name in obj)  
    {  
        value = obj[name];  
        //console.log(value);  
        if(value instanceof Array)  
        {  
            //console.log("Array");  
            for(i=0; i<value.length; ++i)  
            {  
                subValue = value[i];  
                fullSubName = name + '[' + i + ']';  
                innerObj = {};  
                innerObj[fullSubName] = subValue;  
                query += convertToUrlParam(innerObj) + '&';  
            }  
        }  
        else if(value instanceof Object)  
        {  
             //console.log("object");  
            for(subName in value)  
            {  
                subValue = value[subName];  
                if(subValue != null){  
                    // fullSubName = name + '[' + subName + ']';  
                    //user.userName = hmm & user.userPassword = 111  
                    fullSubName = name + '.' + subName;  
                    // fullSubName =  subName;  
                    innerObj = {};  
                    innerObj[fullSubName] = subValue;  
                    query += convertToUrlParam(innerObj) + '&';  
                }  
            }  
        }  
        else if(value !== undefined && value !== null) //&& value !== null  
        {  
            //console.log(value);  
            query += encodeURIComponent(name) + '=' + encodeURIComponent(value) + '&';  
        }  
    }  
    //console.log(query);
    return query.length ? query.substr(0, query.length - 1) : query;  
};
getUrlParam=function(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var href = window.location.href.split("?");
    if (href.length > 1) {
        var r = window.location.href.split("?")[1].match(reg);
        if (r != null){
        	return decodeURI(r[2]);
        }        	
        return null;
    }
    return null;
};
if (!Array.prototype.indexOf) {
    Array.prototype.indexOf = function (elt /*, from*/) {
        var len = this.length >>> 0;
        var from = Number(arguments[1]) || 0;
        from = (from < 0)
            ? Math.ceil(from)
            : Math.floor(from);
        if (from < 0)
            from += len;
        for (; from < len; from++) {
            if (from in this &&
                this[from] === elt)
                return from;
        }
        return -1;
    };
}

Date.prototype.toJSON = function () {
    return this.format("yyyy-MM-ddTHH:mm:ss.SSSZ");
};
Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //小时
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S+": this.getMilliseconds() //毫秒
    };
    var week = {
        "0": "\u65e5",
        "1": "\u4e00",
        "2": "\u4e8c",
        "3": "\u4e09",
        "4": "\u56db",
        "5": "\u4e94",
        "6": "\u516d"
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    if (/(E+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "\u661f\u671f" : "\u5468") : "") + week[this.getDay() + ""]);
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(-RegExp.$1.length)));
        }
    }
    return fmt;
};

Array.prototype.remove = function (item) {
    this.splice(this.indexOf(item), 1);
};

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var href = window.location.href.split("?");
    if (href.length > 1) {
        var r = window.location.href.split("?")[1].match(reg);
        if (r != null){
        	return decodeURI(r[2]);
        }        	
        return null;
    }
    return null;
};

if (!String.prototype.startsWith) {
    String.prototype.startsWith = function (str) {
        var reg = new RegExp("^" + str);
        return reg.test(this);
    }
}
if (!String.prototype.endsWith) {
    String.prototype.endsWith = function (str) {
        var reg = new RegExp(str + "$");
        return reg.test(this);
    }
}
//读取存储数据
function getStorage(key){
	var res = null;
    if($.trim(key)){
//        if(typeof window.localStorage === "object"){
//            if(localStorage.length > 0){
//                for(var i = 0; i < localStorage.length; i++){
//                    if(localStorage.getItem(key)){
//                        res = decodeURIComponent(localStorage.getItem(key));
//						break;
//                    }
//                }
//            }
//        }else{
            res = document.cookie.match(new RegExp("(^| )" + key + "=([^;]*)(;|$)"));
            if(res != null){
                //return decodeURI(unescape(res[2]));
                return decodeURI(res[2]);
            }
//        }
    }
    return res;
}
// 设置存储数据
function setStorage(key, val){
    if($.trim(key)){
//        if(typeof window.localStorage === "object"){
//            localStorage.setItem(key, encodeURIComponent(val));
//        }else{
           //var cookie = key + "=" + escape(encodeURI(val));
            var cookie = key + "=" + encodeURI(val);
            days = (typeof(days) == 'undefined' || days == 0) ? 3650 : days;// 过期时间，默认10年
            var exp = new Date();
            exp.setTime(exp.getTime() + days * 24 * 60 * 60 * 1000);
            cookie = cookie + ";domain="+domain+";path=/;expires=" + exp.toGMTString();
            document.cookie = cookie;
//        }
    }
}
//设置存储临时数据
function setTempStorage(key, val){
    if($.trim(key)){
//        if(typeof window.localStorage === "object"){
//            localStorage.setItem(key, encodeURIComponent(val));
//        }else{
            //var cookie = key + "=" + escape(encodeURI(val));
            var cookie = key + "=" + encodeURI(val);
            minutes = (typeof(minutes) == 'undefined' || minutes == 0) ? 600 : minutes;// 过期时间，默认30分钟
            var exp = new Date();
            exp.setTime(exp.getTime() + minutes * 60 * 1000);
            cookie = cookie + ";domain="+domain+";path=/;expires=" + exp.toGMTString();
            document.cookie = cookie;
//        }
    }
}
// 删除存储数据
function delStorage(key){
    if($.trim(key)){
//        if(typeof window.localStorage === "object"){
//            if(localStorage.length > 0 && key != ""){
//                localStorage.removeItem(key);
//            }
//        }else{
            var exp = new Date(),
                cval = getStorage(key);
            exp.setTime(exp.getTime() - 1);
            if(cval != null){
                document.cookie = key + "=" + cval + ";domain="+domain+";expires=" + exp.toGMTString();
            }
//        }
    }
}
// 清除存储数据
function clearStorage(){
//    if(typeof window.localStorage === "object"){
//        localStorage.clear();
//    }else{
        var key = document.cookie.match(/[^ =;]+(?=\=)/g);//console.log(key);
        if(key){
            for(var i = key.length - 1; i >= 0; i--){
                var exp = new Date(),
                    cval = getStorage(key[i]);
                exp.setTime(exp.getTime() - 1);
                document.cookie = key[i] + "=" + cval + ";domain="+domain+";expires=" + exp.toGMTString();
            }
        }
//    }
}
