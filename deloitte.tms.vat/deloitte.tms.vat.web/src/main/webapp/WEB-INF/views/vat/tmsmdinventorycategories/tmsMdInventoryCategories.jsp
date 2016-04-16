
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
		<div class="easyui-panel" title="" style="width: 100%; height: 22%; background-color: #E0ECFF">
			<form id="TmsMdInventoryCategories_searchform" method="post" scroll="no">
				<table >	
			<tr >
		<!-- 	<td>商品及服务分类ID：</td>
			   <td><input id="idId" class="easyui-textbox" type="text" style="width: 150px;" name="id" value=""></input></td> -->
	

			<td >商品及服务分类名称：</td>
			 <td><input id="inventoryCategoryNameId" class="easyui-textbox" type="text" style="width: 150px;" name="inventoryCategoryName" value=""></input></td>		
			
			<td>有效日期：</td>
			<td><input id="startDateId" class="easyui-datebox" type="text" style="width: 90;" name="startDate" value=""></input></td>
			<td>失效日期：</td>
			<td><input id="endDateId" class="easyui-datebox" type="text" style="width: 90;" name="endDate" value=""></input></td>
				<td colspan="2">
							
								<a href="#" id="searchbtnTmsMdInventoryCategories" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 120px" onclick="SearchTmsMdInventoryCategories()"><spring:message code='button.Search' /></a>
						
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
			<table class="easyui-datagrid" id="TmsMdInventoryCategoriesInit_dataGrid" title="<spring:message code='TmsMdInventoryCategories.title'/>" style="width: 100%; height: 76%" data-options="singleSelect:true,autoRowHeight:false,pagination:true,pageSize:10,remoteSort:false,multiSort:true">
			</table>
		</div>
	</div>
	<div data-options="region:'center',border:false"
		style="background-color: #E0ECFF">
		<div id="TmsMdInventoryCategories_addOrEdit_dlg" class="easyui-dialog"
			style="width: 800px; height: 500px;" closed="true"
			buttons="#TmsMdInventoryCategories_addOrEdit_dlg_dlg-buttons">
			<form id="TmsMdInventoryCategories_editform" class="easyui-form" method="post"
				data-options="novalidate:true" commandsName="clientManageForm">
				<table>
					<tr style="display: none;">
						<td ><spring:message code="TmsMdInventoryCategories.id" />：</td>
						<td ><input id="idInDialog" name="id" style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td><spring:message code="TmsMdInventoryCategories.orgId" />：</td>
						<td><input id="orgIdInDialog" name="orgId" style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>                              
					<tr>
						<td><spring:message code="TmsMdInventoryCategories.inventoryCategoryCode" />：</td>
						<td><input id="inventoryCategoryCodeInDialog" name="inventoryCategoryCode" style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td><spring:message code="TmsMdInventoryCategories.inventoryCategoryName" />：</td>
						<td><input id="inventoryCategoryNameInDialog" name="inventoryCategoryName" style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>                              
					<tr>
						<td><spring:message code="TmsMdInventoryCategories.description" />：</td>
						<td><input id="descriptionInDialog" name="description" style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td><spring:message code="TmsMdInventoryCategories.enabledFlag" />：</td>
						<td><input id="enabledFlagInDialog" name="enabledFlag" style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>                              
					<tr>
						<td><spring:message code="TmsMdInventoryCategories.startDate" />：</td>
						<td><input id="startDateInDialog" name="startDate" style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td><spring:message code="TmsMdInventoryCategories.endDate" />：</td>
						<td><input id="endDateInDialog" name="endDate" style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>                              
				</table>
			</form>
		</div>
		<div id="TmsMdInventoryCategories_addOrEdit_dlg_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="TmsMdInventoryCategoriesEditSave()"><spring:message code="button.Save" /></a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#TmsMdInventoryCategories_addOrEdit_dlg').dialog('close')"><spring:message code="button.Close" /></a>
		</div>
		<div id="history_dlg" class="easyui-dialog" style="width: 800px; height: 500px;" closed="true">
			<table class="easyui-datagrid" id="history_dataGrid" title="<spring:message code='TmsMdInventoryCategories.title'/>" style="width: 100%; height: 88%" data-options="singleSelect:true,autoRowHeight:false,remoteSort:false,multiSort:true">
			</table>
		</div>
	</div>
</body>
<script type="text/javascript">
$(document).ready(
	function() {
		$('#TmsMdInventoryCategories_searchform').form('load',
			{
				pageNumber : $('#TmsMdInventoryCategoriesInit_dataGrid').datagrid('options').pageNumber,
				pageSize : $('#TmsMdInventoryCategoriesInit_dataGrid').datagrid('options').pageSize
			});
			SearchTmsMdInventoryCategories();
		});
	$(function() {
		$('#TmsMdInventoryCategoriesInit_dataGrid')
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
									//,{
									//	field : 'id',
									//	title : '<spring:message code="TmsMdInventoryCategories.id"/>',
									//	width : 80,
									//	align : 'center'
									//}                           
									,{
										field : 'orgId',
										title : '<spring:message code="TmsMdInventoryCategories.orgId"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'inventoryCategoryCode',
										title : '<spring:message code="TmsMdInventoryCategories.inventoryCategoryCode"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'inventoryCategoryName',
										title : '<spring:message code="TmsMdInventoryCategories.inventoryCategoryName"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'description',
										title : '<spring:message code="TmsMdInventoryCategories.description"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'enabledFlag',
										title : '<spring:message code="TmsMdInventoryCategories.enabledFlag"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'startDate',
										title : '<spring:message code="TmsMdInventoryCategories.startDate"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'endDate',
										title : '<spring:message code="TmsMdInventoryCategories.endDate"/>',
										width : 80,
										align : 'center'
									}                           
									] ],
							toolbar : [ {
								text : '新增',
								iconCls : 'icon-add',
								handler : function() {
									TmsMdInventoryCategoriesAdd();
								}
							}, '-', {
								text : '编辑',
								iconCls : 'icon-edit',
								handler : function() {
									TmsMdInventoryCategoriesEdit();
								}
							}, '-', {
								text : '删除',
								iconCls : 'icon-remove',
								handler : function() {
									TmsMdInventoryCategoriesRemove();
								}
							}],
							onLoadSuccess : function() {
								$('#TmsMdInventoryCategoriesInit_dataGrid').datagrid(
										'clearSelections')
							},
							onClickRow : function(index, data) {
								var row = $('#TmsMdInventoryCategoriesInit_dataGrid')
										.datagrid('getSelected');
								if (row) {
									loadSaveFormData(row);
								}
							}
						});
		//设置分页控件	
		var p = $('#TmsMdInventoryCategoriesInit_dataGrid').datagrid('getPager');
		$(p).pagination(
		{
			pageSize : 10,//每页显示的记录条数，默认为10           
			//pageList: [10,20,30],//可以设置每页记录条数的列表           
			beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
			afterPageText : '<spring:message code="pagination.afterPageText"/>',
			displayMsg : '<spring:message code="pagination.displayMsg"/>',
			onSelectPage : function(pageNumber, pageSize) {//分页触发		
				find(pageNumber, pageSize);
				SearchTmsMdInventoryCategories();
			}
		});
	});
	function find(pageNumber, pageSize) {
		$('#TmsMdInventoryCategories_searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		SearchTmsMdInventoryCategories();
	}
	function SearchTmsMdInventoryCategories() {
		
		$("#TmsMdInventoryCategoriesInit_dataGrid").datagrid("loading");
		$('#TmsMdInventoryCategories_searchform').form('submit', {
			url : '${vat}/tmsMdInventoryCategories/loadTmsMdInventoryCategoriesPage.do',
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(result) {
				//数据加载以及绑定
				
				var result = $.parseJSON(result);
				$("#TmsMdInventoryCategoriesInit_dataGrid").datagrid('loadData', result);
				$("#TmsMdInventoryCategoriesInit_dataGrid").datagrid("loaded");
				
				
								
			}
		});
	}
	function getHistory() {
		//	alert(0);
		//$("#TmsMdInventoryCategoriesInit_dataGrid").datagrid("loading");
		$('#TmsMdInventoryCategories_searchform').form('submit', {
			url : '${vat}/tmsMdInventoryCategories/loadhistory.do',
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
	function searchTmsMdInventoryCategories() {
		$("#history_dlg").dialog('open').dialog('setTitle',"<spring:message code='TmsMdInventoryCategories.history'/>");
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
	function TmsMdInventoryCategoriesAdd() {
		clearSaveForm();
		$("#TmsMdInventoryCategories_addOrEdit_dlg").dialog('open').dialog('setTitle',"<spring:message code='client.dialog.clientadd.title'/>");
		$("#TmsMdInventoryCategories_editform").form('load', {
			clientDate : myformatter(new Date())
		});
	}
	function TmsMdInventoryCategoriesEdit() {
		if ($('#TmsMdInventoryCategoriesInit_dataGrid').datagrid('getChecked').length > 1) {
			$.messager.alert("操作提示", "请选择一条数据进行修改");
		} else {
			clearSaveForm();
			var row_list = $('#TmsMdInventoryCategoriesInit_dataGrid').datagrid('getChecked');
			if (row_list) {
				var id = '';
				$.each(row_list, function(index, item) {
					if (index == 0) {
						id = item.id;
					}
				});
				if (id != '') {
					$.post('${vat}/tmsMdInventoryCategories/loadModifyTmsMdInventoryCategories.do',
						{
							id : id
						},
						function(result) {
							
							if (result.status == '0') {
							
							$("#TmsMdInventoryCategories_addOrEdit_dlg").dialog('open').dialog('setTitle',"<spring:message code='client.dialog.clientedit.title'/>");								
							$("#TmsMdInventoryCategories_editform").form('load',result.data);												
							//	$("#TmsMdInventoryCategories_editform").form('load',result.taskShceduleForm);
									
							} else if (result.success == '1') {
								$.messager.alert('<spring:message code="system.alert"/>',result.errorMsg);
								SearchTmsMdInventoryCategories();
								clearSaveForm();
								$('#TmsMdInventoryCategoriesInit_dataGrid').dialog('close');
							}
						}, 'json');
				} else {
					$.messager.alert('<spring:message code="system.alert"/>','请选择需要编辑的数据');
				}
			}
		}
	}
	function TmsMdInventoryCategoriesEditSave() {
		$.messager.confirm("<spring:message code='client.datacommit.formsave.confirm.title'/>","<spring:message code='client.datacommit.formsave.confirm.text'/>",
		function(result) {
			if (result) {
				$('#TmsMdInventoryCategories_editform').form(
				'submit',
				{
					url : '${vat}/tmsMdInventoryCategories/saveTmsMdInventoryCategories.do',
					onSubmit : function() {
						return $(this).form('enableValidation').form('validate');
					},
					success : function(result) {
						var result = $.parseJSON(result);
						if (result.status == '0') {
							clearSaveForm();
							SearchTmsMdInventoryCategories();
							$('#TmsMdInventoryCategories_addOrEdit_dlg').dialog('close');
							$.messager.alert('<spring:message code="system.alert"/>','保存成功');
						}

					}
				});
			}
		});

	}
	//删除一条记录

	function TmsMdInventoryCategoriesRemove() {
		if (($('#TmsMdInventoryCategoriesInit_dataGrid').datagrid('getChecked').length == 0)
				|| ($('#TmsMdInventoryCategoriesInit_dataGrid').datagrid('getChecked').length > 1)) {
			$.messager.alert("操作提示", "请选择需要删除的数据");
		} else {
			$.messager.confirm("<spring:message code='client.datacommit.delete.confirm.title'/>","<spring:message code='client.datacommit.delete.confirm.text'/>",
			function(result) {
				if (result) {
					var row_list = $(
							'#TmsMdInventoryCategoriesInit_dataGrid')
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
										url : "${vat}/tmsMdInventoryCategories/removeTmsMdInventoryCategoriess.do",
										type : "POST",
										async : true,
										data : urlString, //不能直接写成 {id:"123",code:"tomcat"}  
										dataType : "json",
										// contentType: "charset=utf-8",  
										cache : false,
										success : function(
												result) {
											SearchTmsMdInventoryCategories();
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
		$('#TmsMdInventoryCategories_editform').form('clear');
	}
</script>
</html>