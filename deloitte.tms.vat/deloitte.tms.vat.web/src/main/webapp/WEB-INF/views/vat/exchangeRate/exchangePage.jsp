<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Basic Form - jQuery EasyUI Demo</title>
<link rel="stylesheet" tyRpe="text/css"
	href="jquery-easyui-1.4.4/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.4.4/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.4.4/demo.css">
<script type="text/javascript" src="jquery-easyui-1.4.4/jquery.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.4/jquery-1.8.0.js"></script>
<script type="text/javascript" src="resource/corejs/dateformat.js"></script>
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<div region="north" split="true" border="false"
		style="overflow: hidden; height: 99%;">
		<div class="easyui-panel" title=""
			style="width: 100%; height: 12%; background-color: #E0ECFF;>
			<form id="searchAll" method="post" scroll="no">
				<table cellpadding="5">
					<tr>
						<td style="display: none;"><input id="pageNumber"
							class="easyui-textbox" type="text" name="pageNumber"
							style="display: none;" value=""></input></td>
						<td style="display: none;"><input id="pageSize"
							class="easyui-textbox" type="text" name="pageSize"
							style="display: none;" value=""></input></td>
						<td >
							<spring:message code="exchangeRate。operationOrgCode"/>
							<input type="text" id="operationOrgName" name="operationOrgName" class="easyui-textbox"/> 
							<spring:message code="exchangeRate。sourceCurrencyCode"/>
							<input type="text" id="sourceCurrencyName" name="sourceCurrencyName" class="easyui-textbox"/> 
							<spring:message code="exchangeRate。targetCurrencyCode"/>
							<input type="text" id="targetCurrencyName" name="targetCurrencyName" class="easyui-textbox"/> 
							<spring:message code="exchangeRate。enchangeDate"/>
							<input class="easyui-datetimebox" name="enchangeDate"
							id="enchangeDate" data-options="showSeconds:false"  style="width:150px">
							<a href="#" id="searchbtn"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px" onclick="searched()"><spring:message code="button.Search"/></a>
							<a href="#" class="easyui-linkbutton" style="width:80px" onclick="clearSearchForm()">
							<spring:message code="button.Clear"/></a>	
						</td>
						</td>
						<td></td>
					</tr>
				</table>
			</form>
		</div>
		<div style="width: 100%; height: 100%">
			<table class="easyui-datagrid" id="dgTax"
				style="width: 100%; height: 88%"
				data-options="					
					autoRowHeight:false,
					pagination:true,
					pageSize:10,
					remoteSort:false,
				    multiSort:true,
				    iconCls: 'icon-edit',
				singleSelect: true
					">
			</table>
		</div>
	</div>
	<div data-options="region:'center',border:false"
		style="background-color: #E0ECFF">
		<div id="dlg" class="easyui-dialog"
			style="width: 500px; height: 300px;" closed="true"
			buttons="#dlg-buttons">

			<form id="taxForm" method="post" commandName="taxFormParam"
				scroll="no">
				<table cellpadding="10">
					<tr>
						<td><spring:message code="exchangeRate。operationOrgCode" />：</td>
						<td><input id="categoryName" name="operationOrgName" class="easyui-textbox"
							style="width: 150px;" data-options="required:true"></td>
							
						<td><spring:message code="exchangeRate。sourceCurrencyCode" />：</td>
						<td><input id="categoryName" name="sourceCurrencyName" class="easyui-textbox"
							style="width: 150px;" data-options="required:true"></td>
					</tr>
					<tr>
						<td><spring:message code="exchangeRate。targetCurrencyCode" />：</td>
						<td><input id="targetCurrencyCode" name="targetCurrencyName" class="easyui-textbox"
							style="width: 150px;" data-options="required:true"></td>
							
						<td><spring:message code="exchangeRate。enchangeDate" />：</td>
						<td><input class="easyui-datetimebox" name="enchangeDate"
							id="enchangeDateIn" data-options="showSeconds:false"  style="width:150px" ></td>
					</tr>
					<tr>
						<td><spring:message code="taskSchedule.comments" />：</td>
						<td><input id="description" name="description" class="easyui-textbox"
							style="width: 150px;" data-options="required:true"></td>
					</tr>
					<tr style="display: none">
						<td input id="id" name="id" class="easyui-textbox"></td>
						<td><input id="uuid" class="easyui-textbox"
							name="appuseruuid" style="width: 200px"></input></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" onclick="save()"><spring:message
					code="button.Save" /></a> <a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#dlg').dialog('close')"><spring:message
					code="button.close" /></a>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$('#searchAll').form('load', {
			pageNumber : $('#dgTax').datagrid('options').pageNumber,
			pageSize : $('#dgTax').datagrid('options').pageSize
		});
		loadAll();
	});
	$.extend($.messager.defaults, {
		ok : '<spring:message code="confirm"/>',
		cancel : '<spring:message code="cancel"/>'
	});
	$(function() {
		$('#dgTax')
				.datagrid(
						{
							url : '',
							fitColumns : true,
							nowrap : false,
							pagination : true,
							 rownumbers : true, 
							singleSelect : false,
							fitColumns : true,
							striped : true,
							idField : 'id', //主键字段  
							columns : [ [
									{
										field : 'operationOrgCode',
										title : '<spring:message code="exchangeRate。operationOrgCode"/>', 
										width : 80,
										align : 'center'
									},
									{
										field : 'sourceCurrencyCode',
										title : '<spring:message code="exchangeRate。sourceCurrencyCode"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'currencyRate',
										title : '<spring:message code="exchangeRate。currencyRate"/>',
										width : 80,
										align : 'center'
									},
									{
										field : 'targetCurrencyCode',
										title : '<spring:message code="exchangeRate。targetCurrencyCode"/>',
										width : 80,
										align : 'center'
									},
									{
										field : 'enchangeDate',
										title : '<spring:message code="exchangeRate。enchangeDate"/>',
										width : 80,
										align : 'center'
									} ] ],
							toolbar : [ {
								text : '<spring:message code="button.Add"/>',
								iconCls : 'icon-add',
								handler : function() {
									add();
								}
							}, '-', {
								text : '<spring:message code="button.Edit"/>',
								iconCls : 'icon-edit',
								handler : function() {
									edit();
								}
							}, '-', {
								text : '<spring:message code="button.Remove"/>',
								iconCls : 'icon-remove',
								handler : function() {
									removeit();
								}
							}, '-' ],
							onLoadSuccess : function() {
								$('#dgTax').datagrid('clearSelections')
							},
							onClickRow : function(index, data) {
								var row = $('#dgTax').datagrid('getSelected');
								if (row) {
									loadSaveFormData(row);
								}
							}
						});
		//设置分页控件	
		var p = $('#dgTax').datagrid('getPager');
		$(p)
				.pagination(
						{
							pageSize : 10,//每页显示的记录条数，默认为10           
							//pageList: [10,20,30],//可以设置每页记录条数的列表           
							beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
							afterPageText : '<spring:message code="pagination.afterPageText"/>',
							displayMsg : '<spring:message code="pagination.displayMsg"/>',
							onSelectPage : function(pageNumber, pageSize) {//分页触发		
								find(pageNumber, pageSize);
								loadAll();
							}
						});
	});
	function loadSaveFormData(row) {
		$('#status').combobox('setValue', row.status);
		$('#searchAll').form('load', {
			id : row.id,
			name : row.itemName,
			adaptIndustryId : row.adaptIndustryId,
			itemTaxMethod : row.itemTaxMethod,
			itemTaxBasis : row.itemTaxBasis,
			itemTaxRules : row.itemTaxRules,
			createby : row.createby,
			createdate : row.createdate,
			updateby : row.updateby,
			updatedate : row.updatedate,
		/* appuseruuid:row.appuseruuid */
		});
	}
	function find(pageNumber, pageSize) {
		alert(1);
		$('#searchAll').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		loadAll();
	}
	function searched() {
		var operationOrgName = $('#operationOrgName').val();
		var targetCurrencyName = $('#targetCurrencyName').val();
		var sourceCurrencyName = $('#sourceCurrencyName').val();
		var enchangeDate = $('#enchangeDate').val();
		
		if (searchs != null) {
			$.ajax({
				url : "taxCategory/getTaxCategory.do?searchTax=" + searchs,
				//	dataType : "json",
				//cache : false,
				success : function(result) {
					var result = $.parseJSON(result);
					$("#dgTax").datagrid('loadData', result);
					$("#dgTax").datagrid("loaded");
				}
			});
		} else {
			loadAll();
		}
	}
	function loadAll() {
		//alert(2);
		$('#searchAll').form('submit', {
			url : 'taxCategory/getTaxCategorys.do',
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(result) {
				var result = $.parseJSON(result);
				$("#dgTax").datagrid('loadData', result);
				$("#dgTax").datagrid("loaded");
			}
		});
	}

	function add() {
		$("#dlg").dialog('open').dialog('setTitle',
				'<spring:message code="taxItemsmaintain.add"/>');

	}
	function edit() {

		var row = $('#dgTax').datagrid('getChecked');
		if (row.length == 0) {
			$.messager.alert('提示', "请选择你要更新的税目数据", 'info');
			return;
		}
		if (row.length > 1) {
			$.messager.alert('提示', "只能选择一条税目进行更新", 'info');
			return;
		}
		var tax_item;
		$.each(row, function(index, item) {
			if (index == 0) {
				tax_item = item;
			}
		});
		//clearSaveForm();
		if (tax_item.id != '') {
			$
					.post(
							'taxCategory/loadModifyCategory.do',
							{
								id : tax_item.id
							},
							function(result) {
								if (result.success == 'true') {
									$("#dlg")
											.dialog('open')
											.dialog('setTitle',
													'<spring:message code="taxItemsmaintain.edittax"/>');
									$("#taxForm").form('load',
											result.taxItmesForm);
								} else if (result.success == 'false') {
									$.messager
											.alert(
													'<spring:message code="system.alert"/>',
													result.errorMsg);
									loadAll();
									//clearSaveForm();
									$('#dlg').dialog('close');
								}
							}, 'json');
		} else {
			$.messager.alert('<spring:message code="system.alert"/>',
					'编辑的主键不能为空');
		}
	}
	function removeit() {
		$.messager.confirm('提示', '确定要删除吗?', function(result) {
			if (result) {
				var rows = $('#dgTax').datagrid('getChecked');
				var ps = "";
				$.each(rows, function(i, n) {
					if (i == 0)
						ps += n.id;
					else
						ps += "," + n.id;
				});
				$.ajax({
					url : "taxCategory/removeTaxCategory.do?ids=" + ps,
					dataType : "json",
					//	cache : false,

					success : function(object) {
						if (object.result) {
							loadAll();
						}
						$.messager.alert(
								'<spring:message code="system.alert"/>',
								object.msg);
					}
				});
			}
		});

	}
	function save() {
		$('#taxForm').form(
				'submit',
				{
					url : 'taxCategory/save.do',
					onSubmit : function() {
						return $(this).form('enableValidation')
								.form('validate');
					},
					success : function(result) {
						var result = $.parseJSON(result);
						if (result.success) {
							loadAll();
						}
						$.messager.alert(
								'<spring:message code="system.alert"/>',
								result.msg);
					}
				});
	}
	function clearSearchForm(){
		var pageNumber = $('#pageNumber').val();
		var pageSize = $('#pageSize').val()		
		$('#searchAll').form('clear');
		$('#searchAll').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
	}
</script>
</html>