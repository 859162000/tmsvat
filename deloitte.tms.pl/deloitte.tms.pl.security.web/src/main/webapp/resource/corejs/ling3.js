var sidkey="LING_sid"
var _last_jumping_url="_last_jumping_url";

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
warning=function(message){
	$.dialog({
		content : message,
		title: "alert",
		time : 2000
	});
}
info=function(message){
	$.dialog({
		content : message,
		title: "alert",
		time : 2000
	});
}
erro=function(message){
	$.dialog({
		content : message,
		title: "alert",
		time : 2000
	});
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
    console.log(query);
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