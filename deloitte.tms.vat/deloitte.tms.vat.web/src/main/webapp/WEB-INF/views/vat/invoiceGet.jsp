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
			<form id="taskSchedule_searchform" method="post" scroll="no">
				<table cellpadding="10">
					<tr>
					<td>
					<input type="text" style="width: 80px"/> 
					</td>
						<td>
							<div style="text-align: center; padding: 10px">
								<a href="#" id="searchbtn" class="easyui-linkbutton"
									data-options="iconCls:'icon-search'" style="width: 80px"
									onclick="Search()"><spring:message
										code='client.search.button.find' /></a>
								<%-- <a href="#" class="easyui-linkbutton" style="width:80px" onclick="clearSearchForm()"><spring:message code='client.search.button.clear'/></a> --%>
							</div>
						</td>
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
			<table class="easyui-datagrid" id="taskScheduleInit_dataGrid"
				title="<spring:message code='taskSchedule.title'/>"
				style="width: 100%; height: 88%"
				data-options="					
					singleSelect:true,
					autoRowHeight:false,
					pagination:true,
					pageSize:10,
					remoteSort:false,
				    multiSort:true	
					">
			</table>
		</div>
	</div>
	<div data-options="region:'center',border:false"
		style="background-color: #E0ECFF">
		<div id="taskSchedule_addOrEdit_dlg" class="easyui-dialog"
			style="width: 800px; height: 500px;" closed="true"
			buttons="#taskSchedule_addOrEdit_dlg_dlg-buttons">

			<form id="taskSchedule_editform" class="easyui-form" method="post"
				data-options="novalidate:true" commandsName="taskScheduleForm">
				<table>
					<tr>
						<td><spring:message code="taskSchedule.jobName" />：</td>
						<td><input id="name" name="name"
							style="width: 150px;"></td>

					</tr>
					<tr>
						<td><spring:message code="taskSchedule.usedBeanId" />：</td>
						<td><select class="easyui-combobox" name="beanId"
							style="width: 150px">
								<option value="1"></option>
								<option value="2">workBookTask</option>
								<option value="3">testJobs</option>
						</select></td>
					</tr>

					<tr>
						<td><spring:message code="taskSchedule.cronExpression" />：</td>
						<td><select class="easyui-combobox" name=cronExpression
							style="width: 150px">
								<option value="1"></option>
								<option value="2">0 0 3 ? * *</option>
								<option value="3">0 0 2 ? * *</option>
								<option value="4">0 0 1 ? * *</option>
						</select></td>
					</tr>
					<tr>
						<td><spring:message code="taskSchedule.comments" />：</td>
						<td><input id="desc" name="desc"
							style="width: 150px;"></td>

					</tr>
					<tr style="display: none">
						<td input id="id" name="id" class="easyui-textbox"></td>
						<td><input id="uuid" class="easyui-textbox"
							name="appuseruuid" style="width: 200px"></input></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="taskSchedule_addOrEdit_dlg_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" onclick="taskScheduleEditSave()"><spring:message
					code="button.Save" /></a> <a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#taskSchedule_addOrEdit_dlg').dialog('close')"><spring:message
					code="button.Close" /></a>
		</div>
	</div>
</body>
<script type="text/javascript">
	//
	$(document).ready(
			function() {
				//pageDataSettingInit();
				//InitCombobox();
				$('#taskSchedule_searchform').form(
						'load',
						{
							pageNumber : $('#taskScheduleInit_dataGrid')
									.datagrid('options').pageNumber,
							pageSize : $('#taskScheduleInit_dataGrid')
									.datagrid('options').pageSize
						});
				Search();
			});
	$(function() {
		$('#taskScheduleInit_dataGrid')
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
										field : 'name',
										title : '<spring:message code="taskSchedule.jobName"/>',
										width : 80,
										align : 'center'
									},
									{
										field : 'beanId',
										title : '<spring:message code="taskSchedule.usedBeanId"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'cronExpression',
										title : '<spring:message code="taskSchedule.cronExpression"/>',
										width : 80,
										align : 'center'
									},
									{
										field : 'desc',
										title : '<spring:message code="taskSchedule.comments"/>',
										width : 80,
										align : 'center'
									},] ],
							toolbar : [ {
								text : '新增',
								iconCls : 'icon-add',
								handler : function() {
									taskScheduleAdd();
								}
							}, '-', {
								text : '编辑',
								iconCls : 'icon-edit',
								handler : function() {
									taskScheduleEdit();
								}
							}, '-', {
								text : '删除',
								iconCls : 'icon-remove',
								handler : function() {
									taskScheduleRemove();
								}
							}, '-' ],
							onLoadSuccess : function() {
								$('#taskScheduleInit_dataGrid').datagrid('clearSelections')
							},
							onClickRow : function(index, data) {
								var row = $('#taskScheduleInit_dataGrid').datagrid(
										'getSelected');
								if (row) {
									loadSaveFormData(row);
								}
							}
						});
		//设置分页控件	
		var p = $('#taskScheduleInit_dataGrid').datagrid('getPager');
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
								Search();
							}
						});
	});
	function find(pageNumber, pageSize) {
		$('#taskSchedule_searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		Search();
	}
	function Search() {
		$("#taskScheduleInit_dataGrid").datagrid("loading");
		$('#taskSchedule_searchform').form(
				'submit',
				{
					url : '${vat}/jobs/getJobDefinitions.do',
					onSubmit : function() {
						return $(this).form('enableValidation')
								.form('validate');
					},
					success : function(result) {
						//数据加载以及绑定
						var result = $.parseJSON(result);
						$("#taskScheduleInit_dataGrid").datagrid('loadData',
								result.data);
						$("#taskScheduleInit_dataGrid").datagrid("loaded");
					}
				});
	}

	


	//添加
	function taskScheduleAdd() {
		clearSaveForm();
		$("#taskSchedule_addOrEdit_dlg").dialog('open').dialog('setTitle',
				"<spring:message code='client.dialog.clientadd.title'/>");
		$("#taskSchedule_editform").form('load', {
			clientDate : myformatter(new Date())
		});
	}
	function taskScheduleEdit() {
		clearSaveForm();
		var row_list = $('#taskScheduleInit_dataGrid').datagrid('getChecked');
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
								'${vat}/jobs/loadModifyJobDefinition.do',
								{
									id : id
								},
								function(result) {
									if (result.status == '0') {
										$("#taskSchedule_addOrEdit_dlg")
												.dialog('open')
												.dialog('setTitle',
														"<spring:message code='client.dialog.clientedit.title'/>");
										$("#taskSchedule_editform")
												.form(
														'load',
														{
															clientDate : myformatter(new Date(
																	result.data.clientDate))
														});
										$("#taskSchedule_editform").form(
												'load', {
													isUsed : result.data.isUsed
												});
										$("#taskSchedule_editform").form(
												'load', result.data);
									} else if (result.success == 'false') {
										$.messager
												.alert(
														'<spring:message code="system.alert"/>',
														result.errorMsg);
										Search();
										clearSaveForm();
										$('#taskScheduleInit_dataGrid')
												.dialog('close');
									}
								}, 'json');
			} else {
				$.messager.alert('<spring:message code="system.alert"/>',
						'编辑的主键不能为空');
			}
		}
	}

	function taskScheduleEditSave() {
		$.messager
				.confirm(
						"<spring:message code='client.datacommit.formsave.confirm.title'/>",
						"<spring:message code='client.datacommit.formsave.confirm.text'/>",
						function(result) {
							if (result) {
								$('#taskSchedule_editform')
										.form(
												'submit',
												{
													url : '${vat}/jobs/saveJobDefinition.do',
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
															Search();
															$(
																	'#taskSchedule_addOrEdit_dlg')
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

	function taskScheduleRemove() {
		$.messager
				.confirm(
						"<spring:message code='client.datacommit.delete.confirm.title'/>",
						"<spring:message code='client.datacommit.delete.confirm.text'/>",
						function(result) {
							if (result) {
								var row_list = $('#taskScheduleInit_dataGrid')
										.datagrid('getChecked');
								//var row=$('#clientManageInit_dataGrid').datagrid('getSelected');
								if (row_list) {
									var urlString = "";
									$.each(row_list, function(index, item) {
										if (index == 0)
											urlString += "id=" + item.id;
										else
											urlString += "&id=" + item.id;
									});
									if (urlString != '') {
										$
												.ajax({
													url : "${vat}/jobs/removeClients.do",
													type : "POST",
													async : true,
													data : urlString, //不能直接写成 {id:"123",code:"tomcat"}  
													dataType : "json",
													// contentType: "charset=utf-8",  
													cache : false,
													success : function(result) {
														Search();
														clearSaveForm();
													}
												});
									}
								}
							}

						});
	}
	//清空dialog内容

	function clearSaveForm() {
		$('#taskSchedule_editform').form('clear');
	}
</script>
</html>