
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/common/global.jsp"%>
<meta charset="UTF-8">
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<div region="north" split="true" border="false"
		style="overflow: hidden; height: 99%;">
		<div class="easyui-panel" title=""
			style="width: 100%; height: 12%; background-color: #E0ECFF">
			<form id="TmsMdInventoryItems_searchform" method="post" scroll="no">
				<table cellpadding="10">
					<tr>
						<td>商品及服务编码：</td>
						<td><input id="inventoryItemNumberId" class="easyui-textbox"
							type="text" style="width: 150px;" name="inventoryItemNumber"
							value=""></input></td>

						<td>商品及服务名称：</td>
						<td><input id="inventoryItemDescriptonId"
							class="easyui-textbox" type="text" style="width: 150px;"
							name="inventoryItemDescripton" value=""></input></td>
						<td>规格型号：</td>
						<td><input id="inventoryItemModelsId" class="easyui-textbox"
							type="text" style="width: 150px;" name="inventoryItemModels"
							value=""></input></td>
						<td>客户商品及服务分类：</td>
						<td><input id="inventoryCategoryCodeId"
							class="easyui-textbox" type="text" style="width: 150px;"
							name="inventoryCategoryCode" value=""></input></td>
					</tr>
					<tr>
						<td>税目ID：</td>
						<td><input id="taxItemIdId" class="easyui-textbox"
							type="text" style="width: 150px;" name="taxItemId" value=""></input></td>
						<td>税率：</td>
						<td><input id="taxRateId" class="easyui-textbox" type="text"
							style="width: 150px;" name="taxRate" value=""></input></td>
						<td>是否有效：</td>
						<td><input id="enabledFlagId" class="easyui-textbox"
							type="text" style="width: 150px;" name="enabledFlag" value=""></input></td>

						<td>
							<div style="text-align: center; padding: 10px">
								<a href="#" id="searchbtnTmsMdInventoryItems"
									class="easyui-linkbutton" data-options="iconCls:'icon-search'"
									style="width: 120px" onclick="SearchTmsMdInventoryItems()">查找</a>
							</div>
						</td>
					</tr>
					<tr>

					</tr>
					<tr style="display: none">
						<td><input id="pageNumber" class="easyui-textbox" type="text"
							style="width: 0px;" name="pageNumber" value=""></input></td>
						<td><input id="pageSize" class="easyui-textbox" type="text"
							style="width: 0px;" name="pageSize" value=""></input></td>
					</tr>
				</table>
			</form>
		</div>
		<div style="width: 100%; height: 100%">
			<table class="easyui-datagrid" id="TmsMdInventoryItemsInit_dataGrid"
				title="<spring:message code='TmsMdInventoryItems.title'/>"
				style="width: 100%; height: 88%"
				data-options="singleSelect:true,autoRowHeight:false,pagination:true,pageSize:10,remoteSort:false,multiSort:true">
			</table>
		</div>
	</div>
	<div data-options="region:'center',border:false"
		style="background-color: #E0ECFF">
		<div id="TmsMdInventoryItems_addOrEdit_dlg" class="easyui-dialog"
			style="width: 800px; height: 500px;" closed="true"
			buttons="#TmsMdInventoryItems_addOrEdit_dlg_dlg-buttons">
			<form id="TmsMdInventoryItems_editform" class="easyui-form"
				method="post" data-options="novalidate:true"
				commandsName="clientManageForm">
				<table>
					<tr>
						<td><spring:message code="TmsMdInventoryItems.id" />：</td>
						<td><input id="idInDialog" name="id" style="width: 150px;"
							class="easyui-textbox" type="text"></td>
						<td><spring:message code="TmsMdInventoryItems.orgId" />：</td>
						<td><input id="orgIdInDialog" name="orgId"
							style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>
					<tr>
						<td><spring:message
								code="TmsMdInventoryItems.inventoryCategoryId" />：</td>
						<td><input id="inventoryCategoryIdInDialog"
							name="inventoryCategoryId" style="width: 150px;"
							class="easyui-textbox" type="text"></td>
						<td><spring:message
								code="TmsMdInventoryItems.inventoryItemNumber" />：</td>
						<td><input id="inventoryItemNumberInDialog"
							name="inventoryItemNumber" style="width: 150px;"
							class="easyui-textbox" type="text"></td>
					</tr>
					<tr>
						<td><spring:message
								code="TmsMdInventoryItems.inventoryItemDescripton" />：</td>
						<td><input id="inventoryItemDescriptonInDialog"
							name="inventoryItemDescripton" style="width: 150px;"
							class="easyui-textbox" type="text"></td>
						<td><spring:message
								code="TmsMdInventoryItems.inventoryItemModels" />：</td>
						<td><input id="inventoryItemModelsInDialog"
							name="inventoryItemModels" style="width: 150px;"
							class="easyui-textbox" type="text"></td>
					</tr>
					<tr>
						<td><spring:message code="TmsMdInventoryItems.uomCode" />：</td>
						<td><input id="uomCodeInDialog" name="uomCode"
							style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td><spring:message
								code="TmsMdInventoryItems.inventoryCategoryCode" />：</td>
						<td><input id="inventoryCategoryCodeInDialog"
							name="inventoryCategoryCode" style="width: 150px;"
							class="easyui-textbox" type="text"></td>
					</tr>
					<tr>
						<td><spring:message
								code="TmsMdInventoryItems.inventoryItemType" />：</td>
						<td><input id="inventoryItemTypeInDialog"
							name="inventoryItemType" style="width: 150px;"
							class="easyui-textbox" type="text"></td>
						<td><spring:message code="TmsMdInventoryItems.taxItemId" />：</td>
						<td><input id="taxItemIdInDialog" name="taxItemId"
							style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>
					<tr>
						<td><spring:message code="TmsMdInventoryItems.taxRate" />：</td>
						<td><input id="taxRateInDialog" name="taxRate"
							style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td><spring:message code="TmsMdInventoryItems.enabledFlag" />：</td>
						<td><input id="enabledFlagInDialog" name="enabledFlag"
							style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>
					<tr>
						<td><spring:message code="TmsMdInventoryItems.isLock" />：</td>
						<td><input id="isLockInDialog" name="isLock"
							style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td><spring:message code="TmsMdInventoryItems.startDate" />：</td>
						<td><input id="startDateInDialog" name="startDate"
							style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>
					<tr>
						<td><spring:message code="TmsMdInventoryItems.endDate" />：</td>
						<td><input id="endDateInDialog" name="endDate"
							style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="TmsMdInventoryItems_addOrEdit_dlg_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" onclick="TmsMdInventoryItemsEditSave()"><spring:message
					code="button.Save" /></a> <a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#TmsMdInventoryItems_addOrEdit_dlg').dialog('close')"><spring:message
					code="button.Close" /></a>
		</div>
		<div id="history_dlg" class="easyui-dialog"
			style="width: 800px; height: 500px;" closed="true">
			<table class="easyui-datagrid" id="history_dataGrid"
				title="<spring:message code='TmsMdInventoryItems.title'/>"
				style="width: 100%; height: 88%"
				data-options="singleSelect:true,autoRowHeight:false,remoteSort:false,multiSort:true">
			</table>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(
			function() {
				$('#TmsMdInventoryItems_searchform').form(
						'load',
						{
							pageNumber : $('#TmsMdInventoryItemsInit_dataGrid')
									.datagrid('options').pageNumber,
							pageSize : $('#TmsMdInventoryItemsInit_dataGrid')
									.datagrid('options').pageSize
						});
				SearchTmsMdInventoryItems();
			});
	$(function() {
		$('#TmsMdInventoryItemsInit_dataGrid')
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
									},
									{
										field : 'inventoryCategoryName',
										title : '商品及服务分类',
										width : 100,
										align : 'center'
									}
									/* ,{
										field : 'id',
										title : '<spring:message code="TmsMdInventoryItems.id"/>',
										width : 80,
										align : 'center'
									}                            
									,{
										field : 'orgId',
										title : '<spring:message code="TmsMdInventoryItems.orgId"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'inventoryCategoryId',
										title : '<spring:message code="TmsMdInventoryItems.inventoryCategoryId"/>',
										width : 80,
										align : 'center'
									}  */
									,
									{
										field : 'inventoryItemNumber',
										title : '<spring:message code="TmsMdInventoryItems.inventoryItemNumber"/>',
										width : 120,
										align : 'center'
									},
									{
										field : 'inventoryItemDescripton',
										title : '<spring:message code="TmsMdInventoryItems.inventoryItemDescripton"/>',
										width : 120,
										align : 'center'
									},
									{
										field : 'inventoryItemModels',
										title : '<spring:message code="TmsMdInventoryItems.inventoryItemModels"/>',
										width : 80,
										align : 'center'
									}
									 ,{
										field : 'orgName',
										title : '组织',
										width : 80,
										align : 'center'
									} 
									,
									
									{
										field : 'inventoryItemType',
										title : '属性',
										width : 80,
										align : 'center'
									}
									 ,{
										field : 'taxItemsName',
										title : '税目',
										width : 60,
										align : 'center'
									} 
									,
									{
										field : 'taxRate',
										title : '<spring:message code="TmsMdInventoryItems.taxRate"/>',
										width : 80,
										align : 'center'
									},
									{
										field : 'enabledFlag',
										title : '<spring:message code="TmsMdInventoryItems.enabledFlag"/>',
										width : 80,
										align : 'center'
									},
									{
										field : 'isLock',
										title : '<spring:message code="TmsMdInventoryItems.isLock"/>',
										width : 80,
										align : 'center'
									},
									{
										field : 'startDate',
										title : '<spring:message code="TmsMdInventoryItems.startDate"/>',
										width : 80,
										align : 'center'
									},
									{
										field : 'endDate',
										title : '<spring:message code="TmsMdInventoryItems.endDate"/>',
										width : 80,
										align : 'center'
									} ] ],
							toolbar : [ {
								text : '新增',
								iconCls : 'icon-add',
								handler : function() {
									TmsMdInventoryItemsAdd();
								}
							}, '-', {
								text : '编辑',
								iconCls : 'icon-edit',
								handler : function() {
									TmsMdInventoryItemsEdit();
								}
							}, '-', {
								text : '删除',
								iconCls : 'icon-remove',
								handler : function() {
									TmsMdInventoryItemsRemove();
								}
							} ],
							onLoadSuccess : function() {
								$('#TmsMdInventoryItemsInit_dataGrid')
										.datagrid('clearSelections')
							},
							onClickRow : function(index, data) {
								var row = $('#TmsMdInventoryItemsInit_dataGrid')
										.datagrid('getSelected');
								if (row) {
									loadSaveFormData(row);
								}
							}
						});
		//设置分页控件	
		var p = $('#TmsMdInventoryItemsInit_dataGrid').datagrid('getPager');
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
								SearchTmsMdInventoryItems();
							}
						});
	});
	function find(pageNumber, pageSize) {
		$('#TmsMdInventoryItems_searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		SearchTmsMdInventoryItems();
	}
	function SearchTmsMdInventoryItems() {
		$("#TmsMdInventoryItemsInit_dataGrid").datagrid("loading");
		$('#TmsMdInventoryItems_searchform')
				.form(
						'submit',
						{
							url : '${vat}/tmsMdInventoryItems/loadTmsMdInventoryItemsPage.do',
							onSubmit : function() {
								return $(this).form('enableValidation').form(
										'validate');
							},
							success : function(result) {
								//数据加载以及绑定
								var result = $.parseJSON(result);
								$("#TmsMdInventoryItemsInit_dataGrid")
										.datagrid('loadData', result.data);
								$("#TmsMdInventoryItemsInit_dataGrid")
										.datagrid("loaded");
							}
						});
	}
	function getHistory() {
		//	alert(0);
		//$("#TmsMdInventoryItemsInit_dataGrid").datagrid("loading");
		$('#TmsMdInventoryItems_searchform').form('submit', {
			url : '${vat}/tmsMdInventoryItems/loadhistory.do',
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(result) {
				//数据加载以及绑定
				//		alert(1);
				var result = $.parseJSON(result);
				$("#history_dataGrid").datagrid('loadData', result);
				$("#history_dataGrid").datagrid("loaded");
			}
		});
	}
	function searchTmsMdInventoryItems() {
		$("#history_dlg").dialog('open').dialog('setTitle',
				"<spring:message code='TmsMdInventoryItems.history'/>");
		$('#history_dataGrid').datagrid({
			url : '',
			fitColumns : true,
			nowrap : false,
			rownumbers : true,
			singleSelect : false,
			fitColumns : true,
			striped : true,
			idField : 'id', //主键字段  
			columns : [ [ {
				field : 'startDate',
				title : '开始时间',
				width : 80,
				align : 'center'
			}, {
				field : 'endDate',
				title : '结束时间',
				width : 100,
				align : 'center'
			}, {
				field : 'successful',
				title : '是否成功',
				width : 80,
				align : 'center'
			}, ] ],
		});

		getHistory();
	}
	//添加
	function TmsMdInventoryItemsAdd() {
		clearSaveForm();
		$("#TmsMdInventoryItems_addOrEdit_dlg").dialog('open').dialog(
				'setTitle',
				"<spring:message code='client.dialog.clientadd.title'/>");
		$("#TmsMdInventoryItems_editform").form('load', {
			clientDate : myformatter(new Date())
		});
	}
	function TmsMdInventoryItemsEdit() {
		if ($('#TmsMdInventoryItemsInit_dataGrid').datagrid('getChecked').length > 1) {
			$.messager.alert("操作提示", "请选择一条数据进行修改");
		} else {
			clearSaveForm();
			var row_list = $('#TmsMdInventoryItemsInit_dataGrid').datagrid(
					'getChecked');
			if (row_list) {
				var id = '';
				$.each(row_list, function(index, item) {
					if (index == 0) {
						id = item.id;
					}
				});
				if (id != '') {
					$
							.post(
									'${vat}/tmsMdInventoryItems/loadModifyTmsMdInventoryItems.do',
									{
										id : id
									},
									function(result) {
										if (result.status == '0') {
											$(
													"#TmsMdInventoryItems_addOrEdit_dlg")
													.dialog('open')
													.dialog('setTitle',
															"<spring:message code='client.dialog.clientedit.title'/>");
											$("#TmsMdInventoryItems_editform")
													.form(
															'load',
															result.taskShceduleForm);
										} else if (result.success == '1') {
											$.messager
													.alert(
															'<spring:message code="system.alert"/>',
															result.errorMsg);
											SearchTmsMdInventoryItems();
											clearSaveForm();
											$(
													'#TmsMdInventoryItemsInit_dataGrid')
													.dialog('close');
										}
									}, 'json');
				} else {
					$.messager.alert('<spring:message code="system.alert"/>',
							'请选择需要编辑的数据');
				}
			}
		}
	}
	function TmsMdInventoryItemsEditSave() {
		$.messager
				.confirm(
						"<spring:message code='client.datacommit.formsave.confirm.title'/>",
						"<spring:message code='client.datacommit.formsave.confirm.text'/>",
						function(result) {
							if (result) {
								$('#TmsMdInventoryItems_editform')
										.form(
												'submit',
												{
													url : '${vat}/tmsMdInventoryItems/saveTmsMdInventoryItems.do',
													onSubmit : function() {
														return $(this)
																.form(
																		'enableValidation')
																.form(
																		'validate');
													},
													success : function(result) {
														var result = $
																.parseJSON(result);
														if (result.status == '0') {
															clearSaveForm();
															SearchTmsMdInventoryItems();
															$(
																	'#TmsMdInventoryItems_addOrEdit_dlg')
																	.dialog(
																			'close');
															$.messager
																	.alert(
																			'<spring:message code="system.alert"/>',
																			'保存成功');
														}

													}
												});
							}
						});

	}
	//删除一条记录

	function TmsMdInventoryItemsRemove() {
		if (($('#TmsMdInventoryItemsInit_dataGrid').datagrid('getChecked').length == 0)
				|| ($('#TmsMdInventoryItemsInit_dataGrid').datagrid(
						'getChecked').length > 1)) {
			$.messager.alert("操作提示", "请选择需要删除的数据");
		} else {
			$.messager
					.confirm(
							"<spring:message code='client.datacommit.delete.confirm.title'/>",
							"<spring:message code='client.datacommit.delete.confirm.text'/>",
							function(result) {
								if (result) {
									var row_list = $(
											'#TmsMdInventoryItemsInit_dataGrid')
											.datagrid('getChecked');
									//var row=$('#clientManageInit_dataGrid').datagrid('getSelected');
									if (row_list) {
										var urlString = "";
										$.each(row_list, function(index, item) {
											if (index == 0)
												urlString += "ids=" + item.id;
											else
												urlString += "&ids=" + item.id;
										});
										if (urlString != '') {
											$
													.ajax({
														url : "${vat}/tmsMdInventoryItems/removeTmsMdInventoryItemss.do",
														type : "POST",
														async : true,
														data : urlString, //不能直接写成 {id:"123",code:"tomcat"}  
														dataType : "json",
														// contentType: "charset=utf-8",  
														cache : false,
														success : function(
																result) {
															SearchTmsMdInventoryItems();
															clearSaveForm();
														}
													});
										}
									}
								}

							});
		}
	}

	//清空dialog内容

	function clearSaveForm() {
		$('#TmsMdInventoryItems_editform').form('clear');
	}
</script>
</html>