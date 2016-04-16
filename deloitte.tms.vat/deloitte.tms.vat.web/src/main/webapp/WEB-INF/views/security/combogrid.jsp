<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- <title>Basic Form - jQuery EasyUI Demo</title>   
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.4/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.4/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.4/demo.css">
	<script type="text/javascript" src="jquery-easyui-1.4.4/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.4.4/jquery-1.8.0.js"></script> 
	<script type="text/javascript" src="resource/corejs/dateformat.js"></script>  -->

<%@ include file="/common/global.jsp"%>
</head>
<body>

	<table cellpadding="10">
		<tr>
			
			<td><input id="combogrid" class="easyui-combogrid"
				style="width: 250px"
				
				name="combogrid"> <input type="text" id="txtgender" ></input></td>
		</tr>
		
	</table>


</body>
<script type="text/javascript">
	$(function() {
		$('#combogrid')
				.combogrid(
						{
							panelWidth : 500,
							idField : 'personId', //ID字段  
							textField : 'personName', //显示的字段  
							url : "",
							fitColumns : true,
							striped : true,
							editable : true,
							pagination : true, //是否分页
							rownumbers : true, //序号
							collapsible : false, //是否可折叠的
							fit : true, //自动大小
							method : 'post',
							columns : [ [ {
								field : 'personId',
								title : '用户编号',
								width : 10,
								hidden : true
							}, {
								field : 'personName',
								title : '用户名称',
								width : 100
							}, {
								field : 'gender',
								title : '用户性别',
								width : 100
							} ] ],
							keyHandler : {
								up : function() { //【向上键】押下处理
									//取得选中行
									var selected = $('#combogrid').combogrid('grid')
											.datagrid('getSelected');
									if (selected) {
										//取得选中行的rowIndex
										var index = $('#combogrid').combogrid('grid')
												.datagrid('getRowIndex',
														selected);
										//向上移动到第一行为止
										if (index > 0) {
											$('#combogrid').combogrid('grid')
													.datagrid('selectRow',
															index - 1);
										}
									} else {
										var rows = $('#combogrid').combogrid('grid')
												.datagrid('getRows');
										$('#combogrid').combogrid('grid').datagrid(
												'selectRow', rows.length - 1);
									}
								},
								down : function() { //【向下键】押下处理
									//取得选中行
									var selected = $('#combogrid').combogrid('grid')
											.datagrid('getSelected');
									if (selected) {
										//取得选中行的rowIndex
										var index = $('#combogrid').combogrid('grid')
												.datagrid('getRowIndex',
														selected);
										//向下移动到当页最后一行为止
										if (index < $('#combogrid').combogrid('grid')
												.datagrid('getData').rows.length - 1) {
											$('#combogrid').combogrid('grid')
													.datagrid('selectRow',
															index + 1);
										}
									} else {
										$('#combogrid').combogrid('grid').datagrid(
												'selectRow', 0);
									}
								},
								enter : function() { //【回车键】押下处理
									//设置【性别】文本框的内容为选中行的的性别字段内容
									$('#txtgender')
											.val(
													$('#combogrid')
															.combogrid('grid')
															.datagrid(
																	'getSelected').gender);
									//选中后让下拉表格消失
									$('#combogrid').combogrid('hidePanel');
								},
								query : function(keyword) { //【动态搜索】处理
									//设置查询参数
									var queryParams = $('#combogrid')
											.combogrid("grid").datagrid(
													'options').queryParams;
									queryParams.keyword = keyword;
									$('#combogrid').combogrid("grid").datagrid(
											'options').queryParams = queryParams;
									//重新加载
									$('#combogrid').combogrid("setValue", keyword);
									 $('#combogrid').combogrid("grid").datagrid("reload", { 'keyword': keyword });
                                //$('#combogrid').combogrid("setValue", keyword);
 
									//$('#combogrid').combogrid("grid").datagrid(
									//		"reload");

									//$('#combogrid').combogrid("setValue", keyword);
									//将查询条件存入隐藏域
									//$('#hdKeyword').val(keyword);
								}
							},
							onSelect : function() { //选中处理
								$('#txtgender').val(
										$('#combogrid').combogrid('grid').datagrid(
												'getSelected').gender);
							}
						});

		//取得分页组件对象
		var pager = $('#combogrid').combogrid('grid').datagrid('getPager');

		if (pager) {
			$(pager)
					.pagination(
							{
								pageSize: 10,//每页显示的记录条数，默认为10           
								//pageList: [5,10,15,20],//可以设置每页记录条数的列表           
								beforePageText: '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
								afterPageText: '<spring:message code="pagination.afterPageText"/>',           
								displayMsg: '<spring:message code="pagination.displayMsg"/>',
								//选择页的处理
								onSelectPage : function(pageNumber, pageSize) {
									//按分页的设置取数据
									getData(pageNumber, pageSize);
									//设置表格的pageSize属性，表格变化时按分页组件设置的pageSize显示数据
									$('#combogrid').combogrid("grid").datagrid(
											'options').pageSize = pageSize;
									//将隐藏域中存放的查询条件显示在combogrid的文本框中
									//$('#combogrid').combogrid("setValue",
									//		$('#hdKeyword').val());
									
								},
								//改变页显示条数的处理
								//（处理后还是走onSelectPage事件，所以设置也写到onSelectPage事件中了）
								onChangePageSize : function() {
								},
								//点击刷新的处理
								onRefresh : function(pageNumber, pageSize) {
									//按分页的设置取数据
									getData(pageNumber, pageSize);
									//将隐藏域中存放的查询条件显示在combogrid的文本框中
									/* $('#combogrid').combogrid("setValue",
											$('#hdKeyword').val()); */
									
								}
							});
		}
		
		
			//InitCombobox();	
			
			
		
		
		

		var getData = function(page, rows) {
			$.ajax({
				type : "POST",
				url : "combogrid/search.do",
				data : "page=" + page + "&rows=" + rows + "&keyword="
						+ $('#hdKeyword').val(),
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
					$.messager.progress('close');
				},
				success : function(data) {					
					 var result = $.parseJSON(data);				
					$('#combogrid').combogrid('grid').datagrid('loadData', result);
				}
			});
		};
	});
	$(document).ready(function() {
		
		InitCombobox();	
		
	});
	function InitCombobox(){
		$.ajax({
			type : "POST",
			url : "combogrid/search.do",
			
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			
			},
			success : function(data) {					
				 var result = $.parseJSON(data);				
				$('#combogrid').combogrid('grid').datagrid('loadData', result);
			}
		});
	}
</script>
</html>