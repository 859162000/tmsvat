<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/common/global.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<div region="north" split="true" border="false"
		style="overflow: hidden; height: 99%;">
		<div class="easyui-panel" title=""
			style="width: 100%; height: 12%; background-color: #E0ECFF">
			<form id="searchAll" method="post" scroll="no">
				<table>
					
						<td style="display: none;"><input id="pageNumber"
							class="easyui-textbox" type="text" name="pageNumber"
							style="display: none;" value=""></input></td>
						<td style="display: none;"><input id="pageSize"
							class="easyui-textbox" type="text" name="pageSize"
							style="display: none;" value=""></input></td>
					</table>
					<table id= "searchAll_table">		
						<tr>
						<td nowrap="nowrap">税种:</td>
						<td><input id="query" class="easyui-textbox" type="text" name="categoryName" value="" />
						<a href="#"
							class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px"
							onclick="loadAll()"><spring:message code='client.search.button.find'/></a>
						</td>
							<td colspan="1">
					<a href="#" id="ClearbtnTmsMdOrgParameter" class="easyui-linkbutton" style="width: 80px" onclick="clearSaveFormTwo()">重置</a>
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
			style="width: 800px; height: 500px;" closed="true"
			buttons="#dlg-buttons">

			<form id="taxForm" method="post" commandName="taxFormParam"
				scroll="no">
				<table>
					<tr>
						<td><spring:message code="taxCategpryMaintain.categoryName" />：</td>
						<td><input id="categoryName" name="categoryName"
							style="width: 150px;" class="easyui-textbox"></td>

					</tr>
					<tr>
				  <td><spring:message code="taxCategpryMaintain.isUsed" />：</td>						 			
		          <td> <input:select easyuiStyle="width:150px;required:true" id="isUsed_id" name="isUsed" value="$customerManageInit_editform_enabledFlag" easyuiClass="easyui-combobox" >           						 
            		<option value=""></option>
            		<input:systemStatusOption parentCode="BASE_IS_VALID"/>				
			      </input:select> </td> 						
					</tr>

					<tr>
						<td><spring:message code="taxCategpryMaintain.categoryState" />：</td>						
					<td> <input:select easyuiStyle="width:150px;required:true" id="categoryState_id" name="categoryState" value="$customerManageInit_editform_enabledFlag" easyuiClass="easyui-combobox" >           						 
            		<option value=""></option>
            		<input:systemStatusOption parentCode="BASE_TAX_CATEGORY_TYPE"/>				
			         </input:select> </td> 										
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
				onclick="javascript:$('#dlg').dialog('close')"><spring:message code="button.Close" /></a>
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
										field : 'ck',
										checkbox : true,
										width : 2
									}, //显示复选框        
									{
										field : 'categoryName',
										title : '<spring:message code="taxCategpryMaintain.categoryName"/>',
										width : 80,
										align : 'center'
									},
									{
										field : 'categoryState',
										title : '<spring:message code="taxCategpryMaintain.categoryState"/>',
										width : 100,
										align : 'center',
									 formatter:orgFormat2
									},
									{
										field : 'isUsed',
										title : '<spring:message code="taxCategpryMaintain.isUsed"/>',
										width : 80,
										align : 'center',
									 formatter:orgFormat
									},
									{
										field : 'createdBy',
										title : '<spring:message code="taxCategpryMaintain.createdBy"/>',
										width : 80,
										align : 'center'
									},
									{
										field : 'createDate',
										title : '<spring:message code="taxCategpryMaintain.createDate"/>',
										width : 80,
										align : 'center'
									},
									{
										field : 'modifiedBy',
										title : '<spring:message code="taxCategpryMaintain.modifiedBy"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'modifiedDate',
										title : '<spring:message code="taxCategpryMaintain.modifiedDate"/>',
										width : 80,
										align : 'center'
									}, ] ],
							toolbar : [ {
								text : '新增',
								iconCls : 'icon-add',
								handler : function() {
									add();
								}
							}, '-', {
								text : '编辑',
								iconCls : 'icon-edit',
								handler : function() {
									edit();
								}
							}, '-', {
								text : '删除',
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
		$(p).pagination(
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
			createby :    row.createby,
			createdate :  row.createdate,
			updateby : row.updateby,
			updatedate : row.updatedate,
		/* appuseruuid:row.appuseruuid */
		});
	}
	function find(pageNumber, pageSize) {
	
		$('#searchAll').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		loadAll();
	}
	function searched() {
		//alert(1);
		var searchs = $('#query').val();
		///alert(2);
		//alert(searchs);
		if (searchs != null) {
			$.ajax({
				url : "${vat}/taxCategory/getTaxCategory.do?searchTax=" + searchs,
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
		$("#dgTax").datagrid("loading");
		$('#searchAll').form('submit', {
			url : '${vat}/taxCategory/getTaxCategorys.do',
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
		clearSaveForm();
		$("#dlg").dialog('open').dialog('setTitle','<spring:message code="taxItemsmaintain.add"/>');
		$("#taxForm").form('load', {
			clientDate : myformatter(new Date())
		});
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
		$.post('${vat}/taxCategory/loadModifyCategory.do',
							{
								id : tax_item.id
							},
							function(result) {
								if (result.success == 'true') {
									$("#dlg").dialog('open').dialog('setTitle','<spring:message code="taxItemsmaintain.edittax"/>');
									$("#taxForm").form('load',result.taxItmesForm);
								} else if (result.success == 'false') {
									$.messager.alert('<spring:message code="system.alert"/>',result.errorMsg);
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
					url : "${vat}taxCategory/removeTaxCategory.do?ids=" + ps,
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
					url : '${vat}/taxCategory/saveTaxCategory.do',
					onSubmit : function() {
					
						return $(this).form('enableValidation').form('validate');
					},	
					success : function(result) {

							clearSaveForm();
		
							loadAll();
							$('#dlg').dialog('close');
							$.messager.alert('<spring:message code="system.alert"/>',"保存成功");
					}
				});
	}
	
	function clearSaveForm() {
		$('#dlg').form('clear');
	}
	function clearSaveFormTwo() {
		$('#searchAll_table').form('clear');
	}
	function orgFormat(val,row){
		if(val == 'Y' || val == '1'){
			
			return "是";
		}else if(val == 'N' || val == '0'){
			
			return "否";
		}
		
	}
	
	function orgFormat2(val,row){
		
	if(val == '1' || val == "ALL"){
			
			return "共享税";
		}else if(val == '2' || val == "G"){
			
			return "国税";
		}else if(val == '3' || val == "L"){
			
			return "地方税";
		}
		
	}
	
</script>
</html>