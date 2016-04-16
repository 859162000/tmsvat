
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/common/global.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<div region="north" split="true" border="false" style="overflow: hidden; height: 99%;">
		<div class="easyui-panel" title="" style="width: 100%; height: 12%; background-color: #E0ECFF">
			<form id="TmsDrvatInvoiceOnhand_searchform" method="post" scroll="no">
				<table cellpadding="10">	
			<tr>
			<td>发票库存ID：</td>
			 <td><input id="idId" class="easyui-textbox" type="text" style="width: 150px;" name="id" value=""></input></td>
			<td>发票批次ID：</td>
			 <td><input id="invoiceTrxLIdId" class="easyui-textbox" type="text" style="width: 150px;" name="invoiceTrxLId" value=""></input></td>
			<td>库存类型：</td>
			 <td><input id="subinventoryTypeId" class="easyui-textbox" type="text" style="width: 150px;" name="subinventoryType" value=""></input></td>
			<td>发票代码：</td>
			 <td><input id="invoiceCodeId" class="easyui-textbox" type="text" style="width: 150px;" name="invoiceCode" value=""></input></td>
			</tr>                              
			<tr>
			<td>发票类型_枚举值(专票/普票)：</td>
			 <td><input id="invoiceCategoryId" class="easyui-textbox" type="text" style="width: 150px;" name="invoiceCategory" value=""></input></td>
			<td>发票号码：</td>
			 <td><input id="invoiceNumberId" class="easyui-textbox" type="text" style="width: 150px;" name="invoiceNumber" value=""></input></td>
			<td>发票份数：</td>
			 <td><input id="invoiceQtyId" class="easyui-textbox" type="text" style="width: 150px;" name="invoiceQty" value=""></input></td>
			<td>纳税人主体ID：</td>
			 <td><input id="legalEntityIdId" class="easyui-textbox" type="text" style="width: 150px;" name="legalEntityId" value=""></input></td>
			</tr>                              
			<tr>
			<td>是否丢失：</td>
			 <td><input id="isLostId" class="easyui-textbox" type="text" style="width: 150px;" name="isLost" value=""></input></td>
			<td>是否退回：</td>
			 <td><input id="isReturnId" class="easyui-textbox" type="text" style="width: 150px;" name="isReturn" value=""></input></td>
			<td>开始序列号：</td>
			 <td><input id="startSerialNumberId" class="easyui-textbox" type="text" style="width: 150px;" name="startSerialNumber" value=""></input></td>
			<td>结束序列号：</td>
			 <td><input id="endSerialNumberId" class="easyui-textbox" type="text" style="width: 150px;" name="endSerialNumber" value=""></input></td>
			</tr>                              
			<tr>
			<td>批号：</td>
			 <td><input id="lotNumberId" class="easyui-textbox" type="text" style="width: 150px;" name="lotNumber" value=""></input></td>
			</tr>                              
					<tr>
						<td>
							<div style="text-align: center; padding: 10px">
								<a href="#" id="searchbtnTmsDrvatInvoiceOnhand" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 120px" onclick="SearchTmsDrvatInvoiceOnhand()"><spring:message code='TmsDrvatInvoiceOnhand.history' /></a>
							</div>
						</td>
					</tr>
					<tr style="display: none">
						<td><input id="pageNumber" class="easyui-textbox" type="text" style="width: 0px;" name="pageNumber" value=""></input></td>
						<td><input id="pageSize" class="easyui-textbox" type="text" style="width: 0px;" name="pageSize" value=""></input></td>
					</tr>
				</table>
			</form>
		</div>
		<div style="width: 100%; height: 100%">
			<table class="easyui-datagrid" id="TmsDrvatInvoiceOnhandInit_dataGrid" title="<spring:message code='TmsDrvatInvoiceOnhand.title'/>" style="width: 100%; height: 88%" data-options="singleSelect:true,autoRowHeight:false,pagination:true,pageSize:10,remoteSort:false,multiSort:true">
			</table>
		</div>
	</div>
	<div data-options="region:'center',border:false"
		style="background-color: #E0ECFF">
		<div id="TmsDrvatInvoiceOnhand_addOrEdit_dlg" class="easyui-dialog"
			style="width: 800px; height: 500px;" closed="true"
			buttons="#TmsDrvatInvoiceOnhand_addOrEdit_dlg_dlg-buttons">
			<form id="TmsDrvatInvoiceOnhand_editform" class="easyui-form" method="post"
				data-options="novalidate:true" commandsName="clientManageForm">
				<table>
					<tr>
						<td><spring:message code="TmsDrvatInvoiceOnhand.id" />：</td>
						<td><input id="idInDialog" name="id" style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td><spring:message code="TmsDrvatInvoiceOnhand.invoiceTrxLId" />：</td>
						<td><input id="invoiceTrxLIdInDialog" name="invoiceTrxLId" style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>                              
					<tr>
						<td><spring:message code="TmsDrvatInvoiceOnhand.subinventoryType" />：</td>
						<td><input id="subinventoryTypeInDialog" name="subinventoryType" style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td><spring:message code="TmsDrvatInvoiceOnhand.invoiceCode" />：</td>
						<td><input id="invoiceCodeInDialog" name="invoiceCode" style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>                              
					<tr>
						<td><spring:message code="TmsDrvatInvoiceOnhand.invoiceCategory" />：</td>
						<td><input id="invoiceCategoryInDialog" name="invoiceCategory" style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td><spring:message code="TmsDrvatInvoiceOnhand.invoiceNumber" />：</td>
						<td><input id="invoiceNumberInDialog" name="invoiceNumber" style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>                              
					<tr>
						<td><spring:message code="TmsDrvatInvoiceOnhand.invoiceQty" />：</td>
						<td><input id="invoiceQtyInDialog" name="invoiceQty" style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td><spring:message code="TmsDrvatInvoiceOnhand.legalEntityId" />：</td>
						<td><input id="legalEntityIdInDialog" name="legalEntityId" style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>                              
					<tr>
						<td><spring:message code="TmsDrvatInvoiceOnhand.isLost" />：</td>
						<td><input id="isLostInDialog" name="isLost" style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td><spring:message code="TmsDrvatInvoiceOnhand.isReturn" />：</td>
						<td><input id="isReturnInDialog" name="isReturn" style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>                              
					<tr>
						<td><spring:message code="TmsDrvatInvoiceOnhand.startSerialNumber" />：</td>
						<td><input id="startSerialNumberInDialog" name="startSerialNumber" style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td><spring:message code="TmsDrvatInvoiceOnhand.endSerialNumber" />：</td>
						<td><input id="endSerialNumberInDialog" name="endSerialNumber" style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>                              
					<tr>
						<td><spring:message code="TmsDrvatInvoiceOnhand.lotNumber" />：</td>
						<td><input id="lotNumberInDialog" name="lotNumber" style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>                              
				</table>
			</form>
		</div>
		<div id="TmsDrvatInvoiceOnhand_addOrEdit_dlg_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="TmsDrvatInvoiceOnhandEditSave()"><spring:message code="button.Save" /></a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#TmsDrvatInvoiceOnhand_addOrEdit_dlg').dialog('close')"><spring:message code="button.Close" /></a>
		</div>
		<div id="history_dlg" class="easyui-dialog" style="width: 800px; height: 500px;" closed="true">
			<table class="easyui-datagrid" id="history_dataGrid" title="<spring:message code='TmsDrvatInvoiceOnhand.title'/>" style="width: 100%; height: 88%" data-options="singleSelect:true,autoRowHeight:false,remoteSort:false,multiSort:true">
			</table>
		</div>
	</div>
</body>
<script type="text/javascript">
$(document).ready(
	function() {
		$('#TmsDrvatInvoiceOnhand_searchform').form('load',
			{
				pageNumber : $('#TmsDrvatInvoiceOnhandInit_dataGrid').datagrid('options').pageNumber,
				pageSize : $('#TmsDrvatInvoiceOnhandInit_dataGrid').datagrid('options').pageSize
			});
			SearchTmsDrvatInvoiceOnhand();
		});
	$(function() {
		$('#TmsDrvatInvoiceOnhandInit_dataGrid')
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
									} 
									,{
										field : 'id',
										title : '<spring:message code="TmsDrvatInvoiceOnhand.id"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'invoiceTrxLId',
										title : '<spring:message code="TmsDrvatInvoiceOnhand.invoiceTrxLId"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'subinventoryType',
										title : '<spring:message code="TmsDrvatInvoiceOnhand.subinventoryType"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'invoiceCode',
										title : '<spring:message code="TmsDrvatInvoiceOnhand.invoiceCode"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'invoiceCategory',
										title : '<spring:message code="TmsDrvatInvoiceOnhand.invoiceCategory"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'invoiceNumber',
										title : '<spring:message code="TmsDrvatInvoiceOnhand.invoiceNumber"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'invoiceQty',
										title : '<spring:message code="TmsDrvatInvoiceOnhand.invoiceQty"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'legalEntityId',
										title : '<spring:message code="TmsDrvatInvoiceOnhand.legalEntityId"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'isLost',
										title : '<spring:message code="TmsDrvatInvoiceOnhand.isLost"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'isReturn',
										title : '<spring:message code="TmsDrvatInvoiceOnhand.isReturn"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'startSerialNumber',
										title : '<spring:message code="TmsDrvatInvoiceOnhand.startSerialNumber"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'endSerialNumber',
										title : '<spring:message code="TmsDrvatInvoiceOnhand.endSerialNumber"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'lotNumber',
										title : '<spring:message code="TmsDrvatInvoiceOnhand.lotNumber"/>',
										width : 80,
										align : 'center'
									}                           
									] ],
							toolbar : [ {
								text : '新增',
								iconCls : 'icon-add',
								handler : function() {
									TmsDrvatInvoiceOnhandAdd();
								}
							}, '-', {
								text : '编辑',
								iconCls : 'icon-edit',
								handler : function() {
									TmsDrvatInvoiceOnhandEdit();
								}
							}, '-', {
								text : '删除',
								iconCls : 'icon-remove',
								handler : function() {
									TmsDrvatInvoiceOnhandRemove();
								}
							}],
							onLoadSuccess : function() {
								$('#TmsDrvatInvoiceOnhandInit_dataGrid').datagrid(
										'clearSelections')
							},
							onClickRow : function(index, data) {
								var row = $('#TmsDrvatInvoiceOnhandInit_dataGrid')
										.datagrid('getSelected');
								if (row) {
									loadSaveFormData(row);
								}
							}
						});
		//设置分页控件	
		var p = $('#TmsDrvatInvoiceOnhandInit_dataGrid').datagrid('getPager');
		$(p).pagination(
		{
			pageSize : 10,//每页显示的记录条数，默认为10           
			//pageList: [10,20,30],//可以设置每页记录条数的列表           
			beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
			afterPageText : '<spring:message code="pagination.afterPageText"/>',
			displayMsg : '<spring:message code="pagination.displayMsg"/>',
			onSelectPage : function(pageNumber, pageSize) {//分页触发		
				find(pageNumber, pageSize);
				SearchTmsDrvatInvoiceOnhand();
			}
		});
	});
	function find(pageNumber, pageSize) {
		$('#TmsDrvatInvoiceOnhand_searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		SearchTmsDrvatInvoiceOnhand();
	}
	function SearchTmsDrvatInvoiceOnhand() {
		$("#TmsDrvatInvoiceOnhandInit_dataGrid").datagrid("loading");
		$('#TmsDrvatInvoiceOnhand_searchform').form('submit', {
			url : '${vat}/tmsDrvatInvoiceOnhand/loadTmsDrvatInvoiceOnhandPage.do',
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(result) {
				//数据加载以及绑定
				var result = $.parseJSON(result);
				$("#TmsDrvatInvoiceOnhandInit_dataGrid").datagrid('loadData', result.data);
				$("#TmsDrvatInvoiceOnhandInit_dataGrid").datagrid("loaded");
			}
		});
	}
	function getHistory() {
		//	alert(0);
		//$("#TmsDrvatInvoiceOnhandInit_dataGrid").datagrid("loading");
		$('#TmsDrvatInvoiceOnhand_searchform').form('submit', {
			url : '${vat}/tmsDrvatInvoiceOnhand/loadhistory.do',
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
	function searchTmsDrvatInvoiceOnhand() {
		$("#history_dlg").dialog('open').dialog('setTitle',"<spring:message code='TmsDrvatInvoiceOnhand.history'/>");
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
	function TmsDrvatInvoiceOnhandAdd() {
		clearSaveForm();
		$("#TmsDrvatInvoiceOnhand_addOrEdit_dlg").dialog('open').dialog('setTitle',"<spring:message code='client.dialog.clientadd.title'/>");
		$("#TmsDrvatInvoiceOnhand_editform").form('load', {
			clientDate : myformatter(new Date())
		});
	}
	function TmsDrvatInvoiceOnhandEdit() {
		if ($('#TmsDrvatInvoiceOnhandInit_dataGrid').datagrid('getChecked').length > 1) {
			$.messager.alert("操作提示", "请选择一条数据进行修改");
		} else {
			clearSaveForm();
			var row_list = $('#TmsDrvatInvoiceOnhandInit_dataGrid').datagrid('getChecked');
			if (row_list) {
				var id = '';
				$.each(row_list, function(index, item) {
					if (index == 0) {
						id = item.id;
					}
				});
				if (id != '') {
					$.post('${vat}/tmsDrvatInvoiceOnhand/loadModifyTmsDrvatInvoiceOnhand.do',
						{
							id : id
						},
						function(result) {
							if (result.status == '0') {
								$("#TmsDrvatInvoiceOnhand_addOrEdit_dlg").dialog('open').dialog('setTitle',"<spring:message code='client.dialog.clientedit.title'/>");
								$("#TmsDrvatInvoiceOnhand_editform").form('load',result.taskShceduleForm);
							} else if (result.success == '1') {
								$.messager.alert('<spring:message code="system.alert"/>',result.errorMsg);
								SearchTmsDrvatInvoiceOnhand();
								clearSaveForm();
								$('#TmsDrvatInvoiceOnhandInit_dataGrid').dialog('close');
							}
						}, 'json');
				} else {
					$.messager.alert('<spring:message code="system.alert"/>','请选择需要编辑的数据');
				}
			}
		}
	}
	function TmsDrvatInvoiceOnhandEditSave() {
		$.messager.confirm("<spring:message code='client.datacommit.formsave.confirm.title'/>","<spring:message code='client.datacommit.formsave.confirm.text'/>",
		function(result) {
			if (result) {
				$('#TmsDrvatInvoiceOnhand_editform').form(
				'submit',
				{
					url : '${vat}/tmsDrvatInvoiceOnhand/saveTmsDrvatInvoiceOnhand.do',
					onSubmit : function() {
						return $(this).form('enableValidation').form('validate');
					},
					success : function(result) {
						var result = $.parseJSON(result);
						if (result.status == '0') {
							clearSaveForm();
							SearchTmsDrvatInvoiceOnhand();
							$('#TmsDrvatInvoiceOnhand_addOrEdit_dlg').dialog('close');
							$.messager.alert('<spring:message code="system.alert"/>','保存成功');
						}

					}
				});
			}
		});

	}
	//删除一条记录

	function TmsDrvatInvoiceOnhandRemove() {
		if (($('#TmsDrvatInvoiceOnhandInit_dataGrid').datagrid('getChecked').length == 0)
				|| ($('#TmsDrvatInvoiceOnhandInit_dataGrid').datagrid('getChecked').length > 1)) {
			$.messager.alert("操作提示", "请选择需要删除的数据");
		} else {
			$.messager.confirm("<spring:message code='client.datacommit.delete.confirm.title'/>","<spring:message code='client.datacommit.delete.confirm.text'/>",
			function(result) {
				if (result) {
					var row_list = $(
							'#TmsDrvatInvoiceOnhandInit_dataGrid')
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
							$.ajax({
										url : "${vat}/tmsDrvatInvoiceOnhand/removeTmsDrvatInvoiceOnhands.do",
										type : "POST",
										async : true,
										data : urlString, //不能直接写成 {id:"123",code:"tomcat"}  
										dataType : "json",
										// contentType: "charset=utf-8",  
										cache : false,
										success : function(
												result) {
											SearchTmsDrvatInvoiceOnhand();
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
		$('#TmsDrvatInvoiceOnhand_editform').form('clear');
	}
</script>
</html>