

function formatIsUsed(val, row) {
	if (val == "1") {
		return '是';
	} else if (val == "0") {
		return "否";
	}
}

function myformatter(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}
function myparser(s){
	if (!s) return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0],10);
	var m = parseInt(ss[1],10);
	var d = parseInt(ss[2],10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
		return new Date(y,m-1,d);
	} else {
		return new Date();
	}
}
function fmoney(s, n) {  
    n = n > 0 && n <= 20 ? n : 2;  
    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";  
    var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];  
    t = "";  
    for (i = 0; i < l.length; i++) {  
        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");  
    }  
    return t.split("").reverse().join("") + "." + r;  
}  
/**
 * 改变jQuery的AJAX默认属性和方法
 * 
 * @author bo.wang
 * 
 * @requires jQuery
 * 
 */


function forbiddenPage(){  
  $("<div class=\"datagrid-mask\" style=\"background:#666666;\"></div>").css({display:"block",width:$("body")[0].offsetWidth+10,height:$(window).height()}).appendTo("body");   
  $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候……").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});       
}  
/** 
 * 释放页面 
 * @return 
 */  
function releasePage(){  
    $(".datagrid-mask,.datagrid-mask-msg").hide();  
}  

var MaskUtil = (function(){  
    
    var $mask,$maskMsg;  
      
    var defMsg = '正在处理，请稍待。。。';  
      
    function init(){  
        if(!$mask){  
            $mask = $("<div class=\"datagrid-mask mymask\"></div>").appendTo("body");  
        }  
        if(!$maskMsg){  
            $maskMsg = $("<div class=\"datagrid-mask-msg mymask\">"+defMsg+"</div>")  
                .appendTo("body").css({'font-size':'12px'});
        }  
          
        $mask.css({width:"100%",height:$(document).height()});  
          
        $maskMsg.css({  
            left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2,
        });   
                  
    }  
      
    return {  
        mask:function(msg){  
            init();  
            $mask.show();  
            $maskMsg.html(msg||defMsg).show();  
        }  
        ,unmask:function(){  
            $mask.hide();  
            $maskMsg.hide();  
        }  
    }  
      
}()); 



	


