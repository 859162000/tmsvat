
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/common/global.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no"
	id="layoutid">
<div data-options="region:'center',border:false" style="background-color: #E0ECFF">  
	<div class="easyui-layout" style="width: 100%; height: 100%;">
		<div region="north" split="true" border="false"
			style="overflow: hidden; height: 18%;">
			<div class="easyui-panel" title="合同查询"
				style="width: 100%; height: 100%; background-color: #E0ECFF"">
				<form id="TmsMdFlexValueSets_searchform" method="post" scroll="no">
					<table align="center">
						<tr>
							<td>合同编码：</td>
							<td><select  class="easyui-combogrid" id="contractNumber"
								type="text" style="width: 150px;" name="contractNumber" value=""></input></td>
							<td>合同名称：</td>
							<td><select  class="easyui-combogrid"  id="contractName" 
								type="text" style="width: 150px;" name="contractName"
								value=""></input></td>
<!-- 							<td><input id="startDate" class="easyui-datebox"
								type="text" style="width: 150px;" name="startDate" value=""  data-options="editable:false"></input></td>
							<td>结束日期：</td>
							<td><input id="endDate" class="easyui-datebox"
								type="text" style="width: 150px;" name="endDate" value="" data-options="editable:false"></input></td> -->
						</tr>
						<tr style="text-align: center;">
							<td colspan="10"><a href="#"
								id="searchbtnTmsMdFlexValueSets" class="easyui-linkbutton"
								data-options="iconCls:'icon-search',plain:'ture'" style="width: 120px"
								onclick="SearchTmsMdContract()">查询</a> <a href="#"
								id="searchbtnTmsMdFlexValueSets" class="easyui-linkbutton"
								style="width: 120px"
								onclick="$('#TmsMdFlexValueSets_searchform').form('reset')"  data-options="iconCls:'icon-undo',plain:'ture'">重置</a></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div data-options="region:'center',border:false"
				style="background-color: #E0ECFF">
		<div style="width: 100%; height:100%;">
				<table class="easyui-datagrid" id="TmsMdContractInit_dataGrid"
					title="合同" style="width: 100%; height: 100%">
				</table>
			</div>
			</div>
			</div>
</div>

	</div>
	<div data-options="region:'east'" title="添加合同"
		style="width: 100%;">
		<div class="easyui-layout" style="width: 100%; height: 100%;">
	
	<div region="north" split="true" border="false"
			style="overflow: hidden; height:18%;">
       <div class="easyui-panel"
			style="width: 100%; height: 100%; padding: 0px; background-color: #E0ECFF">
			<form id="tmsMdFlexContract_editAddform" method="post" scroll="no">
				<input type="hidden" id="id" name="id">
				<input type="hidden" id="hiddensearchvalues_id">
				<table align="center" style="text-align: center;">
					<tr>
						<td>合同编码:</td>
						<td><input id="contractNumber_id" class="easyui-textbox"
							type="text" style="width: 150px;" name="contractNumber"
							value=""></input></td>
						<td>合同名称：</td>
						<td><input id="contractName_id" class="easyui-textbox"
							type="text" style="width: 150px;" name="contractName" value=""></input></td>
						<td>是否有效：</td>
						<td><select id="contract_enabledFlag_id" name="enabledFlag" class="easyui-combobox" style="width: 150px;">
								<option value="N">否</option>
								<option value="Y">是</option>
						</select></input></td>
						
					</tr>
					<tr>
						<td>合同总金额:</td>
						<td><input id="contractAmount_id" class="easyui-textbox"
							type="text" style="width: 150px;" name="contractAmount"
							value=""></input></td>
						<td>合同累计开票金额:</td>
						<td><input id="contractTotalAmount_id" class="easyui-textbox"
							type="text" style="width: 150px;" name="contractTotalAmount"
							value=""></input></td>
						<td>合同累计收款金额：</td>
						<td><input id="contractReceiveAmount_id" class="easyui-textbox"
							type="text" style="width: 150px;" name="contractReceiveAmount" value=""></input></td>
					</tr>
					<!-- <tr>
						<td>是否有效：</td>
						<td><select id="contract_enabledFlag_id" name="enabledFlag" class="easyui-combobox" style="width: 150px;">
								<option value="N">否</option>
								<option value="Y">是</option>
						</select></input></td>
						<td>状态：</td>
						<td><input id="status_id" class="easyui-textbox" type="text" style="width: 150px;" name="status" value=""></input></td>
						<td>开始日期：</td>
						<td><input id="contract_startDate_id" class="easyui-datebox"
							type="text" style="width: 150px;" name="startDate" value=""  data-options="editable:false"></input></td>
						<td>结束日期：</td>
						<td><input id="contract_endDate_id" class="easyui-datebox"
							type="text" style="width: 150px;" name="endDate" value="" data-options="editable:false"></input></td>
					</tr> -->
					<tr>
						<td colspan="8"><a href="#" id="searchbtn"
							class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:'ture'"
							style="width: 80px" onclick="addOrUpdate()"> <spring:message code="button.Save" />
						</a> <a href="#" class="easyui-linkbutton" style="width: 80px"
							data-options="iconCls:'icon-undo',plain:'ture'"
							onclick="$('#tmsMdFlexContract_editAddform').form('reset')" >
								<spring:message code="button.Clear" />
						</a></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div data-options="region:'center',border:false"
				style="background-color: #E0ECFF">
      <div style="width: 100%; height:100%;">
			<table class="easyui-datagrid" id="addProjectList_datagrid"
				style="width: 100%; height: 100%">
			</table>
		</div>
		</div>
		
         </div>
         
         <div class="easyui-dialog"  id="project_dialog" style="height: 100%;">
         
      <div class="easyui-layout" style="width: 100%; height: 100%;">
      
      <div region="north" split="true" border="false"
			style="overflow: hidden; height: 100%;">
         <div class="easyui-panel" title="项目"
				style="width: 100%; height:100%; background-color: #E0ECFF">
         <form id="tmsMdProject_editAddform" method="post" scroll="no">
				<input type="hidden" id="contract_id" name="contractId"/><!-- 合同id -->
				<input type="hidden" id="project_id" name="id"/><!-- 项目id -->
				<table align="center" style="text-align: center;">
					<tr>
						<td>项目编码:</td>
						<td><input id="projectNumber_id" class="easyui-textbox"
							type="text" style="width: 150px;" name="projectNumber"
							value=""></input></td>
						<td>项目名称：</td>
						<td><input id="projectName_id" class="easyui-textbox"
							type="text" style="width: 150px;" name="projectName" value=""></input></td>
						<td>是否有效：</td>
						<td><select id="project_enabledFlag_id" name="enabledFlag" class="easyui-combobox" style="width: 150px;">
								<option value="N">否</option>
								<option value="Y">是</option>
						</select></input></td>
					</tr>
						
					<tr>
						<td>项目金额:</td>
						<td><input id="projectAmount_id" class="easyui-textbox"
							type="text" style="width: 150px;" name="projectAmount"
							value=""></input></td>
						<td>项目累计开票金额:</td>
						<td><input id="projectAmount_id" class="easyui-textbox"
							type="text" style="width: 150px;" name="projectAmount"
							value=""></input></td>
						<td>项目累计收款金额:</td>
						<td><input id="projectAmount_id" class="easyui-textbox"
							type="text" style="width: 150px;" name="projectAmount"
							value=""></input></td>
					</tr>
					<!-- <tr>
						<td>开始日期：</td>
						<td><input id="project_startDate_id" class="easyui-datebox"
							type="text" style="width: 150px;" name="startDate" value=""  data-options="editable:false"></input></td>
						<td>结束日期：</td>
						<td><input id="project_endDate_id" class="easyui-datebox"
							type="text" style="width: 150px;" name="endDate" value="" data-options="editable:false"></input></td>
						
					</tr> -->
					<tr>
						<td colspan="6"><a href="#" id="searchbtn"
							class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:'ture'"
							style="width: 80px" onclick="addOrUpdateProject()"> <spring:message
									code="button.Save" />
						</a> <a href="#" class="easyui-linkbutton" style="width: 80px"
							data-options="iconCls:'icon-undo',plain:'ture'"
							onclick="$('#tmsMdFlexContract_editAddform').form('reset')" >
								<spring:message code="button.Clear" />
						</a></td>
					</tr>
				</table>
			</form>
			</div>
			</div>
         
         </div>
         
	</div>

</body>
<script type="text/javascript">
/**
 * 初始化页面
 */
$(document).ready(function() {
	$("#layoutid").layout('collapse', 'east');//关闭右侧面板
	initTmsMdContractInit_datagrid();//初始化合同设置数据表格
	initValues_dialog();//操作窗口初始化
});

/**
 * 值操作弹出框
 */
 function initValues_dialog(){
	$("#project_dialog").dialog({
		title:'值操作',
		closed:true,
		region:'center',//弹出框位置
	});
}


/**
 *打开增加项目弹框
 */
function tmsMdProjectAdd(){
	var contract_id = $("#id").val();//合同id
	//alert(contract_id);
	
	$("#project_dialog").dialog('open');//打开弹出框
	$("#contract_id").val(contract_id)//合同id
	//clearProjectForm();//清空弹出框的from表单内容
	}
/**
 * 值修改前赋值
 */
function tmsMdProjctEdit(){
	if ($('#addProjectList_datagrid').datagrid('getChecked').length != 1) {//判断选择数据行数
		$.messager.alert("操作提示", "请选择一条数据进行修改");
	} else {
		$("#project_dialog").dialog('open');//打开弹出面板
		var row_list = $('#addProjectList_datagrid').datagrid('getChecked');//得到选中行
				
		var contract_id = $("#id").val();//合同id
		var project_id = row_list[0].id;//项目id
		var projectNumber = row_list[0].projectNumber;//获取项目编码
		var projectName = row_list[0].projectName;//获取项目名称
		var projectAmount = row_list[0].projectAmount;//获取项目类型
		var enabledFlag = row_list[0].enabledFlag;//获取项目类型
		/* var startDate = row_list[0].startDate;//获取开始时间
		var endDate = row_list[0].endDate;//获取结束时间 */
		

		$("#contract_id").val(contract_id)//设置合同id
		$("#project_id").val(project_id)//设置项目id
		$("#projectNumber_id").textbox('setValue', projectNumber)//设置项目编码
		$("#projectName_id").textbox('setValue', projectName)//设置项目名称
		$("#projectAmount_id").textbox('setValue', projectAmount)//设置项目分类
		$("#project_enabledFlag_id").textbox('setValue', enabledFlag);//设置合同总金额
		/* $("#project_startDate_id").textbox('setValue', startDate)//设置开始日期
		$("#project_endDate_id").textbox('setValue', endDate)//设置结束日期 */
	}
}


/**
 * 项目修改增加操作
 */
function addOrUpdateProject(){
	$.messager
	.confirm(
			"<spring:message code='client.datacommit.formsave.confirm.title'/>",
			"<spring:message code='client.datacommit.formsave.confirm.text'/>",
			function(result) {
				if (result) {
					$('#tmsMdProject_editAddform').form('submit',{
										url : '${vat}/tmsMdContractDevice/saveOrUpdateProject.do',
										onSubmit : function() {
											return $(this).form('enableValidation').form('validate');
										},
										success : function(result) {
											var result = $.parseJSON(result);
											if (result.status) {
												clearProjectForm();
												$.messager.alert('<spring:message code="system.alert"/>','保存成功');
												$("#project_dialog").dialog('close');
												addProjectList_datagrid('') ;
												
											}
										}
									});
				}
			});
	
}
 
/**
 * 项目删除
 */
 function tmsMdProjectRemove(){
	 if ($('#addProjectList_datagrid').datagrid('getChecked').length == 0) {
			$.messager.alert("操作提示", "请选择需要删除的数据");
		} else {
			$.messager.confirm(
							"<spring:message code='client.datacommit.delete.confirm.title'/>",
							"<spring:message code='client.datacommit.delete.confirm.text'/>",
							function(result) {
								if (result) {
									var row_list = $('#addProjectList_datagrid').datagrid('getChecked');
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
													url : "${vat}/tmsMdContractDevice/removeTmsMdProjects.do",
													type : "POST",
													async : true,
													data : urlString, //不能直接写成 {id:"123",code:"tomcat"}  
													dataType : "json",
													// contentType: "charset=utf-8",  
													cache : false,
													success : function(result) {
														//SearchTmsMdContract();
														addProjectList_datagrid('');
														clearProjectForm();
													}
												});
										}
									}
								}

				});
		}
}

/**
 * 合同编码设置
 */
function setsearchivaluesetencoding_id(obj){
	 $('#hiddensearchvalues_id').val(obj.value);
}
/**
 * 值集说明设置
 */
function setsearchvaluesetthat_id(obj){
	 $('#hiddensearchvalues_id').val(obj.value);
}
	
/**
 * 值集初始化
 */
function initTmsMdContractInit_datagrid() {
	
    var contractNumber = $("#contractNumber").combogrid("getValue");
	var contractName = $("#contractName").combogrid("getValue");
	/* var startDate = $("#startDate").textbox("getValue");
	var endDate = $("#endDate").textbox("getValue");   */
	$('#TmsMdContractInit_dataGrid')
			.datagrid(
					{
						url : '${vat}/tmsMdContractDevice/loadTmsMdContractPage.do',
						nowrap : false,
						pagination : true,
						rownumbers : true,
						singleSelect : false,
						striped : true,
						idField : 'id', //主键字段  
						queryParams : {
							contractNumber : contractNumber,
							contractName : contractName,
							/* startDate : startDate,
							endDate : endDate,  */
						},
						columns : [ [
										{
											field : 'ck',
											title : '序号',
											checkbox : true,
											width : 2
										},{
											field : 'id',
											title : '合同id',
											width : 300,
											align : 'center',
											sortable:true,
											hidden:true,
										},
										{
											field : 'contractNumber',
											title : '合同编号',
											width : 200,
											align : 'center',
											sortable:true
										},
										{
											field : 'contractName',
											title : '合同名称',
											width : 200,
											align : 'center',
											sortable:true
										},
										{
											field : 'enabledFlag',
											title : '是否可用',
											width : 120,
											align : 'center',
											sortable:true
										},
										{
											field : 'contractAmount',
											title : '合同总金额',
											width : 200,
											align : 'center',
											sortable:true
										},
										{
											field : 'contractTotalAmount',
											title : '合同累计开票金额',
											width : 200,
											align : 'center',
											sortable:true
										},
										{
											field : 'contractReceiveAmount',
											title : '合同累计首款金额',
											width : 200,
											align : 'center',
											sortable:true
										}/* ,
										{
											field : 'contractCategory',
											title : '合同类型',
											width : 120,
											align : 'center',
											sortable:true
										},
										
										{
											field : 'description',
											title : '描述',
											width : 120,
											align : 'center',
											sortable:true
										},
										{
											field : 'startDate',
											title : '开始日期',
											width : 120,
											align : 'center',
											sortable:true
										},
										{
											field : 'endDate',
											title : '结束日期',
											width : 120,
											align : 'center',
											sortable:true
										} */
										] ],
						toolbar : [ {
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								$("#valuesdiv").hide();
								TmsMdContractAdd();
								$("#id").val("0");
								addProjectList_datagrid('');
							}
						}, '-', {
							text : '编辑',
							iconCls : 'icon-edit',
							handler : function() {
								$("#valuesdiv").show();
								TmsMdContractEdit();
								addProjectList_datagrid('');
							}
						}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								TmsMdContractRemove();
							}
						} ],
						onLoadSuccess : function() {
							$('#TmsMdContractInit_dataGrid').datagrid(
									'clearSelections')
						},
						onClickRow : function(index, data) {
							var row = $('#TmsMdContractInit_dataGrid')
									.datagrid('getSelected');
							if (row) {
								loadSaveFormData(row);
							}
						}
					});

}

/**
 * 项目初始化
 */
function addProjectList_datagrid(contract_id) {
     var id = 	$("#id").val();
     //alert("id="+id);
	$('#addProjectList_datagrid')
			.datagrid(
					{
						nowrap : false,
						url : '${vat}/tmsMdContractDevice/loadTmsMdProjectPage.do',
						pagination : true,
						rownumbers : true,
						singleSelect : false,
						striped : true,
						idField : 'id', //主键字段  
						queryParams : {
							 contract_id:id,
							 /*id:id, */
						},
						columns : [ [
								{
									field : 'ck',
									title:'序号',
									checkbox : true,
									width : 2,
									sortable:true
								},
								{
									field : 'id',//项目id
									title : '项目id',
									width :200,
									align : 'center',
									sortable:true,
									hidden:true
								},
								{
									field : 'contract_id',//项目id
									title : '合同id',
									width :200,
									align : 'center',
									sortable:true,
									hidden:true
								},
								{
									field : 'projectNumber',//项目编码
									title : '项目编码',
									width : 200,
									align : 'center',
									sortable:true
								},
								{
									field : 'projectName',//项目名称
									title : '项目名称',
									width : 200,
									align : 'center',
									sortable:true
								},
								{
									field : 'enabledFlag',
									title : '是否可用',
									width : 120,
									align : 'center',
									sortable:true
								},
								{
									field : 'projectAmount',//项目金额
									title : '项目金额',
									width : 200,
									align : 'center',
									sortable:true
								},
								{
									field : 'projectTotalAmount',//项目累计开票金额
									title : '项目累计开票名称',
									width : 200,
									align : 'center',
									sortable:true
								},
								{
									field : 'projectReceiveAmount',//项目累计首款金额
									title : '项目累计收款金额',
									width : 200,
									align : 'center',
									sortable:true
								}/* ,
								{
									field : 'startDate',//开始日期
									title : '开始日期',
									width : 150,
									align : 'center',
									sortable:true
								},
								{
									field : 'endDate',//结束日期
									title : '结束日期',
									width : 150,
									align : 'center',
									sortable:true
								}, */
								] ],
						toolbar : [ {
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								tmsMdProjectAdd();
							}
						}, '-', {
							text : '编辑',
							iconCls : 'icon-edit',
							handler : function() {
								tmsMdProjctEdit();
							}
						}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								tmsMdProjectRemove();
							}
						}
						],
						onLoadSuccess : function() {
							$('#TmsMdContractInit_dataGrid').datagrid(
									'clearSelections')
						},
						onClickRow : function(index, data) {
							var row = $('#TmsMdContractInit_dataGrid')
									.datagrid('getSelected');
							if (row) {
								loadSaveFormData(row);
							}
						}
					});

}
	
/**
 * 值搜索框值设置
 */
 function  setHiddensearchvalues_id(obj){
	 $('#hiddensearchvalues_id').val(obj.value);
}

/**
 * 值集搜索
 */
function SearchTmsMdContract() {
	initTmsMdContractInit_datagrid();
}

/**
 * 跳转合同添加
 */
function TmsMdContractAdd() {
	clearContractForm();
	$("#layoutid").layout('expand', 'east');//打开右侧面板
}
/**
 * 合同增加修改
 */
 function addOrUpdate(){
	 $('#tmsMdFlexContract_editAddform').form('submit', {//提交form表单
			url : '${vat}/tmsMdContractDevice/saveOrUpdateTmsMdContract.do',
			success : function(result) {//成功之后
				//数据加载以及绑定
				var result = $.parseJSON(result);//将数据格式化成json
				if(result.status){//判断是否成功
					$.messager.alert("操作提示", "添加成功");
				    //alert("新增合同id="+result.contract_id);
					$("#id").val(result.contract_id);//设置合同id
					//initTmsMdContractInit_datagrid();//刷新合同数据表格
				}
			}
		});
	_
}
/**
 * 合同修改
 */
function TmsMdContractEdit() {

	if ($('#TmsMdContractInit_dataGrid').datagrid('getChecked').length != 1) {//判断选择数据行数
		$.messager.alert("操作提示", "请选择一条数据进行修改");
	} else {
		$("#layoutid").layout('expand', 'east');//打开右侧面板
		var row_list = $('#TmsMdContractInit_dataGrid').datagrid(
				'getChecked');
		var id = row_list[0].id;
		var contractNumber = row_list[0].contractNumber;
		var contractName = row_list[0].contractName;
		var contractAmount = row_list[0].contractAmount;
		var enabledFlag = row_list[0].enabledFlag;
	/* 	var startDate = row_list[0].startDate;
		var endDate = row_list[0].endDate;  */

		$("#id").val(id);//设置合同id
		$("#contractNumber_id").textbox('setValue', contractNumber);//设置合同编码
		$("#contractName_id").textbox('setValue', contractName);//设置合同名称
		$("#contractAmount_id").textbox('setValue', contractAmount);//设置合同总金额
		$("#contract_enabledFlag_id").textbox('setValue', enabledFlag);//设置合同总金额
	 	/* $("#contract_startDate_id").textbox('setValue', startDate);//设置开始时间
		$("#contract_endDate_id").textbox('setValue', endDate);//设置结束时间   */
	}
}
/**
 * 合同保存修改
 */
function TmsMdContractEditSave() {
	$.messager
			.confirm(
					"<spring:message code='client.datacommit.formsave.confirm.title'/>",
					"<spring:message code='client.datacommit.formsave.confirm.text'/>",
					function(result) {
						if (result) {
							$('#TmsMdFlexValueSets_editform')
									.form(
											'submit',
											{
												url : '${vat}/tmsMdFlexValueSets/saveTmsMdFlexValueSets.do',
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
														clearContractForm();
														SearchTmsMdContract();
														$(
																'#TmsMdFlexValueSets_addOrEdit_dlg')
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
/**
 * 合同删除
 */
function TmsMdContractRemove() {
	if ($('#TmsMdContractInit_dataGrid').datagrid('getChecked').length == 0) {
		$.messager.alert("操作提示", "请选择需要删除的数据");
	} else {
		$.messager
				.confirm(
						"<spring:message code='client.datacommit.delete.confirm.title'/>",
						"<spring:message code='client.datacommit.delete.confirm.text'/>",
						function(result) {
							if (result) {
								var row_list = $(
										'#TmsMdContractInit_dataGrid')
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
													url : "${vat}/tmsMdContractDevice/removeTmsMdContracts.do",
													type : "POST",
													async : true,
													data : urlString, //不能直接写成 {id:"123",code:"tomcat"}  
													dataType : "json",
													// contentType: "charset=utf-8",  
													cache : false,
													success : function(
															result) {
														SearchTmsMdContract();
														clearContractForm();
													}
												});
									}
								}
							}

						});
	}
}

//清空右侧面板表单内容
function clearContractForm() {
	$('#tmsMdFlexContract_editAddform').form('clear');
}
//清空项目弹出框表单内容
function clearProjectForm() {
	$('#tmsMdProject_editAddform').form('clear');
}
	
/**
 * 表格头右键菜单可选列生成
 */
var createGridHeaderContextMenu = function(e, field) {  
e.preventDefault();  
var grid = $(this);/* grid本身 */  
var headerContextMenu = this.headerContextMenu;/* grid上的列头菜单对象 */  
var okCls = 'tree-checkbox1';//选中  
var emptyCls = 'tree-checkbox0';//取消  
if (!headerContextMenu) {  
    var tmenu = $('<div style="width:100px;"></div>').appendTo('body');  
    var fields = grid.datagrid('getColumnFields');  
    for ( var i = 0; i < fields.length; i++) {  
        var fildOption = grid.datagrid('getColumnOption', fields[i]);  
        if (!fildOption.hidden) {  
           $('<div iconCls="'+okCls+'" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);  
        } else {  
            $('<div iconCls="'+emptyCls+'" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);  
        }  
   }  
    headerContextMenu = this.headerContextMenu = tmenu.menu({  
        onClick : function(item) {  
            var field = $(item.target).attr('field');  
            if (item.iconCls == okCls) {  
                grid.datagrid('hideColumn', field);  
                $(this).menu('setIcon', {  
                    target : item.target,  
                    iconCls : emptyCls  
                });  
            } else {  
                grid.datagrid('showColumn', field);  
                $(this).menu('setIcon', {  
                    target : item.target,  
                    iconCls : okCls  
                });  
           }  
        }  
    });  
}  
headerContextMenu.menu('show', {  
      left : e.pageX,  
      top : e.pageY  
  }); 
};  
$.fn.datagrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;  
$.fn.treegrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;  

</script>
</html>