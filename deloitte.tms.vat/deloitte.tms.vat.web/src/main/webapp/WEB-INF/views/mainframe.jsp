<%@page import="com.deloitte.tms.pl.core.context.utils.ContextUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>税务管理系统</title>
<%@ include file="/common/global.jsp" %>
<style type="text/css">
body {
	font: 12px/20px "微软雅黑", "宋体", Arial, sans-serif, Verdana, Tahoma;
	padding: 0;
	margin: 0;
}
.layout-split-proxy-h{
	position:absolute;
	width:2px;
	background:#888;
	font-size:1px;
	cursor:e-resize;
	display:none;
	z-index:5;
}

.layout-split-north{
	border-bottom:5px solid #efefef;
}
.layout-split-south{
	border-top:5px solid #efefef;
}
.layout-split-east{
	border-left:0px solid #efefef;
}
.layout-split-west{
	border-right:0px solid #efefef;
}
a:link {
 text-decoration: none;
}
a:visited {
 text-decoration: none;
}
a:hover {
 text-decoration: underline;
}
a:active {
 text-decoration: none;
}
.cs-north {
	height:60px;
}
.cs-north-bg {
	width: 100%;
	height: 100%;
	background: url(themes/gray/images/header_bg.png) repeat-x;
}
.cs-north-logo {
	height: 40px;
	margin: 15px 0px 0px 5px;
	display: inline-block;
	color:#000000;font-size:22px;font-weight:bold;text-decoration:none
}
.cs-west {
	width:200px;padding:0px;
}
.cs-navi-tab {
	padding: 5px;
}
.cs-tab-menu {
	width:120px;
}
.cs-home-remark {
	padding: 10px;
}
.wrapper {
    float: right;
    height: 30px;
    margin-left: 10px;
}
.ui-skin-nav {
    float: right;
	padding: 0;
	margin-right: 10px;
	list-style: none outside none;
	height: 30px;
}

.ui-skin-nav .li-skinitem {
    float: left;
    font-size: 12px;
    line-height: 30px;
	margin-left: 10px;
    text-align: center;
}
.ui-skin-nav .li-skinitem span {
	cursor: pointer;
	width:10px;
	height:10px;
	display:inline-block;
}
.ui-skin-nav .li-skinitem span.cs-skin-on{
	border: 1px solid #FFFFFF;
}

.ui-skin-nav .li-skinitem span.gray{background-color:gray;}
.ui-skin-nav .li-skinitem span.default{background-color:blue;}
.ui-skin-nav .li-skinitem span.bootstrap{background-color:#D7EBF9;}
.ui-skin-nav .li-skinitem span.black{background-color:black;}
.ui-skin-nav .li-skinitem span.metro{background-color:#FFE57E;}
.ui-skin-nav .li-skinitem span.pepper-grinder{background-color:#BC3604;}
.ui-skin-nav .li-skinitem span.blue{background-color:blue;}
.ui-skin-nav .li-skinitem span.cupertino{background-color:#D7EBF9;}
.ui-skin-nav .li-skinitem span.dark-hive{background-color:black;}
.ui-skin-nav .li-skinitem span.sunny{background-color:#FFE57E;}
#u12_img {
    position: absolute;
    left: 10px;
    top: 5px;
    width: 135px;
    height: 50px;
}
#u14 {
    position: absolute;
    left: 158px;
    top: 2px;
    width: 169px;
    height: 36px;
    font-family: '微软雅黑 Regular', '微软雅黑';
    font-weight: 400;
    font-style: normal;
    font-size: 28px;
    color: #81BC00;
    text-align: center;
}
#u15 {
    position: absolute;
    left: 0px;
    top: 0px;
    width: 169px;
    white-space: nowrap;
}
#u17 {
    position: absolute;
    left: 0px;
    top: 0px;
    width: 265px;
    word-wrap: break-word;
}
#u16 {
    position: absolute;
    left: 159px;
    width: 265px;
    height: 20px;
    top:20px;
    font-family: '微软雅黑 Regular', '微软雅黑';
    font-weight: 400;
    font-style: normal;
    font-size: 14px;
    color: #CCCCCC;
}
#logout {
    position: absolute;
    right: 30px;
    top:20px;
    height:20px;
}
#loginfo {
    position: absolute;
    right: 170px;
    top:15px;
    height:30px;
}
#loginfoicon {
    position: absolute;
    right: 190px;
    top:15px;
    height:30px;
}
</style>
<script type="text/javascript">
function addTab(title, url){
	if ($('#tabs').tabs('exists', title)){
		$('#tabs').tabs('select', title);//选中并刷新
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		if(url != undefined && currTab.panel('options').title != 'Home') {
			$('#tabs').tabs('update',{
				tab:currTab,
				options:{
					content:createFrame(url)
				}
			})
		}
	} else {
		var content = createFrame(url);
		$('#tabs').tabs('add',{
			title:title,
			content:content,
			closable:true
		});
	}
	tabClose();
}
//invoicePrintPoolH/printPoolHQueryInit.do
function createFrame(url) {
	var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	return s;
}
		
function tabClose() {
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close',subtitle);
	})
	/*为选项卡绑定右键*/
	$(".tabs-inner").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});

		var subtitle =$(this).children(".tabs-closable").text();

		$('#mm').data("currtab",subtitle);
		$('#tabs').tabs('select',subtitle);
		return false;
	});
}		
//绑定右键菜单事件
function tabCloseEven() {
	//刷新
	$('#mm-tabupdate').click(function(){
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		if(url != undefined && currTab.panel('options').title != 'Home') {
			$('#tabs').tabs('update',{
				tab:currTab,
				options:{
					content:createFrame(url)
				}
			})
		}
	})
	//关闭当前
	$('#mm-tabclose').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close',currtab_title);
	})
	//全部关闭
	$('#mm-tabcloseall').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			if(t != 'Home') {
				$('#tabs').tabs('close',t);
			}
		});
	});
	//关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		var nextall = $('.tabs-selected').nextAll();		
		if(prevall.length>0){
			prevall.each(function(i,n){
				var t=$('a:eq(0) span',$(n)).text();
				if(t != 'Home') {
					$('#tabs').tabs('close',t);
				}
			});
		}
		if(nextall.length>0) {
			nextall.each(function(i,n){
				var t=$('a:eq(0) span',$(n)).text();
				if(t != 'Home') {
					$('#tabs').tabs('close',t);
				}
			});
		}
		return false;
	});
	//关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			//msgShow('系统提示','后边没有啦~~','error');
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});
	//关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==0){
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});

	//退出
	$("#mm-exit").click(function(){
		$('#mm').menu('hide');
	})
}

$(function() {
	tabCloseEven();

	$('.cs-navi-tab').click(function() {
		var $this = $(this);
		var href = $this.attr('src');
		var title = $this.text();
		addTab(title, href);
	});
	
    $('#tt').tree({
        onClick: function(node){
           addTab(node.text, node.url, node.icon);
        }
    	});

	var themes = {
		'gray' : 'themes/gray/easyui.css',
		'black' : 'themes/black/easyui.css',
		'bootstrap' : 'themes/bootstrap/easyui.css',
		'default' : 'themes/default/easyui.css',
		'metro' : 'themes/metro/easyui.css',
		'pepper-grinder' : 'themes/pepper-grinder/easyui.css',
		'blue' : 'themes/default/easyui.css',
		'cupertino' : 'themes/cupertino/easyui.css',
		'dark-hive' : 'themes/dark-hive/easyui.css',
		'sunny' : 'themes/sunny/easyui.css'
	};

	var skins = $('.li-skinitem span').click(function() {
		var $this = $(this);
		if($this.hasClass('cs-skin-on')) return;
		skins.removeClass('cs-skin-on');
		$this.addClass('cs-skin-on');
		var skin = $this.attr('rel');
		$('#swicth-style').attr('href', themes[skin]);
		setCookie('cs-skin', skin);
		skin == 'dark-hive' ? $('.cs-north-logo').css('color', '#FFFFFF') : $('.cs-north-logo').css('color', '#000000');
	});

	if(getCookie('cs-skin')) {
		var skin = getCookie('cs-skin');
		$('#swicth-style').attr('href', themes[skin]);
		$this = $('.li-skinitem span[rel='+skin+']');
		$this.addClass('cs-skin-on');
		skin == 'dark-hive' ? $('.cs-north-logo').css('color', '#FFFFFF') : $('.cs-north-logo').css('color', '#000000');
	}
});


function setCookie(name,value) {//两个参数，一个是cookie的名子，一个是值
    var Days = 30; //此 cookie 将被保存 30 天
    var exp = new Date();    //new Date("December 31, 9998");
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}

function getCookie(name) {//取cookies函数        
    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
     if(arr != null) return unescape(arr[2]); return null;
}

</script>
</head>
<body class="easyui-layout">
	<div region="north" border="true" class="cs-north" style="overflow:hidden">
		<div id="u12" class="ax_image">
        <img id="u12_img" class="img " src="resource/login_files/deloitte.png">
        <!-- Unnamed () -->
        <div id="u13" class="text" style="top: 30px; transform-origin: 65.5px 0px 0px;">
          <p><span></span></p>
        </div>
      </div>
		<div class="cs-north-bg">
		<div id="u14" class="ax_文本段落">
        <!-- Unnamed () -->
        <div id="u15" class="text">
          <p><span>税务</span><span>管理</span><span>平台</span></p>
        </div>
      </div>
      <!--<div id="u16" class="ax_文本段落">
         Unnamed () 
        <div id="u17" class="text">
          <p><span>Tax Management System</span></p>
        </div>
      </div>-->
		<div class="cs-north-logo"></div>
		<ul class="ui-skin-nav">				
			<!-- <li class="li-skinitem" title="gray"><span class="gray" rel="gray"></span></li>
			<li class="li-skinitem" title="default"><span class="default" rel="default"></span></li>
			<li class="li-skinitem" title="bootstrap"><span class="bootstrap" rel="bootstrap"></span></li>
			<li class="li-skinitem" title="black"><span class="black" rel="black"></span></li>
			<li class="li-skinitem" title="metro"><span class="metro" rel="metro"></span></li>
			<li class="li-skinitem" title="pepper-grinder"><span class="pepper-grinder" rel="pepper-grinder"></span></li>
			<li class="li-skinitem" title="blue"><span class="blue" rel="blue"></span></li>
			<li class="li-skinitem" title="cupertino"><span class="cupertino" rel="cupertino"></span></li>
			<li class="li-skinitem" title="dark-hive"><span class="dark-hive" rel="dark-hive"></span></li> -->
			<li class="li-skinitem" title="当前用户"><img id="loginfoicon" src="resource/login_files/user.png"><span id="loginfo"><%=ContextUtils.getCurrentUserName() %></span></li>
			<li class="li-skinitem" title="退出登录"><a href="security_logout_"><img id="logout" src="resource/login_files/Quit.png"></a></li>
		</ul>	
		</div>
	</div>
	 <div id="p" data-options="region:'west'" title="导航菜单" style="width:20%;padding:10px">
             <ul id="tt" class="easyui-tree" url="mainframe/getUserManus.do">
             </ul>
  </div>
	
	<div id="mainPanle" region="center" border="true" border="false">
		 <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
                <div title="首页">
				<iframe scrolling="auto" frameborder="0"  src="invoicePreProcess/invoicePreProcessCheckInit.do" style="width:100%;height:100%;"></iframe>
				</div>
        </div>
	</div>

	<div region="south" border="false" class="cs-south"></div>
	
	<div id="mm" class="easyui-menu cs-tab-menu">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseother">关闭其他</div>
		<div id="mm-tabcloseall">关闭全部</div>
	</div>
</body>
</html>
