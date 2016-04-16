<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
	<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
	
	<div id="tt" class="easyui-tabs" data-options="" style="width:100%;height:100%;">
		<div title="待受理任务" data-options="closable:false" style="overflow:hidden">
			<iframe scrolling="yes" frameborder="0"  src="${vat}/invoicePreProcess/invoicePreProcessInit.do" style="width:100%;height:100%;"></iframe>
		</div>
		<div title="已受理任务" data-options="closable:false" style="overflow:hidden">
			<iframe scrolling="yes" frameborder="0"  src="${vat}/invoicePreProcess/invoicePreProcessQueryInit.do" style="width:100%;height:100%;"></iframe>
		</div>
	</div>
</body> 
</html>