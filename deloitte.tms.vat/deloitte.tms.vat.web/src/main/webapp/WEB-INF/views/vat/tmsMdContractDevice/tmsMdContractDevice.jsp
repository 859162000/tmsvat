
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
							<td><select  class="easyui-combogrid" id="search_contractNumber_id"
								type="text" style="width: 150px;" name="contractNumber" value=""></input></td>
							<td>合同名称：</td>
							<td><select  class="easyui-combogrid"  id="search_contractName_id" 
								type="text" style="width: 150px;" name="contractName"
								value=""></input></td>
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
						<td>合同总金额:</td>
						<td><input id="contractAmount_id" class="easyui-textbox"
							type="text" style="width: 150px;" name="contractAmount"
							value=""></input></td>
					</tr>
					<tr>
						<td>合同累计开票金额:</td>
						<td><input id="contract_virtualTotalInvoiceAmt_id" class="easyui-textbox"
							type="text" style="width: 150px;" name="virtualTotalInvoiceAmt"
							value=""></input></td>
						<td>合同累计收款金额：</td>
						<td><input id="contract_virtualTotalReceiptsAmt_id" class="easyui-textbox"
							type="text" style="width: 150px;" name="virtualTotalReceiptsAmt" value=""></input></td>
					</tr>
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
						<td>项目金额:</td>
						<td><input id="projectAmount_id" class="easyui-textbox"
							type="text" style="width: 150px;" name="projectAmount"
							value=""></input></td>
					</tr>
					<tr>
						<td>项目累计开票金额:</td>
						<td><input id="project_virtualTotalInvoiceAmt_id" class="easyui-textbox"
							type="text" style="width: 150px;" name="virtualTotalInvoiceAmt"
							value=""></input></td>
						<td>项目累计收款金额:</td>
						<td><input id="project_virtualTotalReceiptsAmt_id" class="easyui-textbox"
							type="text" style="width: 150px;" name="virtualTotalReceiptsAmt"
							value=""></input></td>
					</tr>
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
	initSearchContractNumber('');//初始化合同编码查询框
	initSearchContractName('');//初始化合同编码查询框
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
	clearProjectForm();//清空弹出框的from表单内容
	
	var contract_id = $("#id").val();//合同id
	//alert("contract_id="+contract_id);
	$("#contract_id").val(contract_id)//合同id
	$("#project_virtualTotalInvoiceAmt_id").textbox('enable');
	$("#project_virtualTotalReceiptsAmt_id").textbox('enable');
	
	$("#project_dialog").dialog('open');//打开弹出框
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
		var projectAmount = row_list[0].projectAmount;//获取项项目金额
		var virtualTotalInvoiceAmt = row_list[0].virtualTotalInvoiceAmt;//获取项项目金额
		var virtualTotalReceiptsAmt = row_list[0].virtualTotalReceiptsAmt;//获取项项目金额
		

		$("#contract_id").val(contract_id)//设置合同id
		$("#project_id").val(project_id)//设置项目id
		$("#projectNumber_id").textbox('setValue', projectNumber)//设置项目编码
		$("#projectName_id").textbox('setValue', projectName)//设置项目名称
		$("#projectAmount_id").textbox('setValue', projectAmount)//设置项目金额
		$("#project_virtualTotalInvoiceAmt_id").textbox('setValue', virtualTotalInvoiceAmt)//设置项目开具金额
		$("#project_virtualTotalReceiptsAmt_id").textbox('setValue', virtualTotalReceiptsAmt)//设置项目收款金额
		$("#project_virtualTotalInvoiceAmt_id").textbox('disable');
		$("#project_virtualTotalReceiptsAmt_id").textbox('disable');
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
												$.messager.alert('<spring:message code="system.alert"/>','保存成功');
												$("#project_dialog").dialog('close');
												
												addProjectList_datagrid('') ;
												clearProjectForm();
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
	
    var contractNumber = $("#search_contractNumber_id").combogrid("getValue");
	var contractName = $("#search_contractName_id").combogrid("getValue");
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
											field : 'contractAmount',
											title : '合同总金额',
											width : 200,
											align : 'center',
											sortable:true,
											formatter:function(value,sec){
												return value.toFixed(2);
											}
										},
										{
											field : 'virtualTotalInvoiceAmt',
											title : '合同累计开票金额',
											width : 200,
											align : 'center',
											sortable:true,
											formatter:function(value,sec){
												return value.toFixed(2);
											}
										},
										{
											field : 'virtualTotalReceiptsAmt',
											title : '合同累计首款金额',
											width : 200,
											align : 'center',
											sortable:true,
											formatter:function(value,sec){
												return value.toFixed(2);
												
											}
										}
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
									field : 'projectAmount',//项目总金额
									title : '项目金额',
									width : 200,
									align : 'center',
									sortable:true,
									formatter:function(value,sec){
										return value.toFixed(2);
									}
								},
								{
									field : 'virtualTotalInvoiceAmt',//项目累计开票金额
									title : '项目累计开票名称',
									width : 200,
									align : 'center',
									sortable:true,
									formatter:function(value,sec){
										return value.toFixed(2);
									}
								},
								{
									field : 'virtualTotalReceiptsAmt',//项目累计首款金额
									title : '项目累计收款金额',
									width : 200,
									align : 'center',
									sortable:true,
									formatter:function(value,sec){
										return value.toFixed(2);
									}
								}
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
	$("#contract_virtualTotalInvoiceAmt_id").textbox('enable');
	$("#contract_virtualTotalReceiptsAmt_id").textbox('enable');
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
				}else{
					$.messager.alert("操作提示", result.errMsg);
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
		var virtualTotalInvoiceAmt = row_list[0].virtualTotalInvoiceAmt;//获取项项目金额
		var virtualTotalReceiptsAmt = row_list[0].virtualTotalReceiptsAmt;//获取项项目金额

		$("#id").val(id);//设置合同id
		$("#contractNumber_id").textbox('setValue', contractNumber);//设置合同编码
		$("#contractName_id").textbox('setValue', contractName);//设置合同名称
		$("#contractAmount_id").textbox('setValue', contractAmount);//设置合同总金额
		$("#contract_virtualTotalInvoiceAmt_id").textbox('setValue', virtualTotalInvoiceAmt)//设置项目开具金额
		$("#contract_virtualTotalReceiptsAmt_id").textbox('setValue', virtualTotalReceiptsAmt)//设置项目收款金额
		$("#contract_virtualTotalInvoiceAmt_id").textbox('disable');
		$("#contract_virtualTotalReceiptsAmt_id").textbox('disable');
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
 * 值集编码下拉列表初始化
 */
function initContractEncoding(data) {
	$("#contractNumber_id")
			.combogrid(
					{
						pagination : true,
						panelWidth : 450,
						singleSelect : true,
						url:'${vat}/tmsMdFlexValueSets/searchivaluesetencoding.do',
						queryParams : {
							searchivaluesetencoding_id : data,
						},
						toolbar : [
								{
									text : "<input type='text' id='searchivaluesetencoding_id' class='easyui-textbox'  onblur='setsearchivaluesetencoding_id(this)'/>"
								}, {
									width : 80,
									text:'查询',
									iconCls : 'icon-search',
									handler : function() {
										var  searchivaluesetencoding_id = $("#hiddensearchvalues_id").val();
										initIvaluesetencoding_id(searchivaluesetencoding_id);
										  $("#ivaluesetencoding_id").combogrid('hidePanel');
										  $("#ivaluesetencoding_id").combogrid('showPanel');
									}
								} ],
						columns : [ [ {
							field : 'id',
							title : '值集id',
							width : 100,
							hidden:true,
						}, {
							field : 'flexValueSetCode',
							title : '值集编码',
							width : 100
						}, {
							field : 'flexValueSetName',
							title : '值集名称',
							width : 100,
							align : 'right'
						} ] ],
						onClickRow : function(index, data) {
				        	 search = false;
				        	 $("#ivaluesetencoding_id").combogrid('hidePanel');
				        	  $("#ivaluesetencoding_id").combogrid('setValue', data.flexValueSetCode);
					          $("#ivaluesetencoding_id").combogrid('setText', data.flexValueSetName);
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

/**
 * 合同编码下拉列表初始化
 */
 function initSearchContractNumber(contractNumber) {
	$("#search_contractNumber_id")
			.combogrid(
					{
						pagination : true,
						panelWidth : 450,
						singleSelect : true,
						url:'${vat}/tmsMdContractDevice/loadTmsMdContractPage.do',
						queryParams : {
							contractNumber : contractNumber,
						},
						toolbar : [
								{
									text : "<input type='text' id='searchContractCode' class='easyui-textbox'  onblur='setsearchivaluesetencoding_id(this)'/>"
								}, {
									width : 80,
									text:'查询',
									iconCls : 'icon-search',
									handler : function() {
										var  searchContractCode = $("#searchContractCode").val();
										initSearchContractNumber(searchContractCode);
										$("#search_contractNumber_id").combogrid('hidePanel');
										$("#search_contractNumber_id").combogrid('showPanel');
									}
								} ],
						columns : [ [ 
									{
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
									}
							] ],
						onClickRow : function(index, data) {
				        	 search = false;
				        	 $("#search_contractNumber_id").combogrid('hidePanel');
				        	  $("#search_contractNumber_id").combogrid('setValue', data.contractNumber);
					          $("#search_contractNumber_id").combogrid('setText', data.contractNumber);
						}

					});

}

 /**
  * 合同名称下拉列表初始化
  */
  function initSearchContractName(contractName) {
 	$("#search_contractName_id")
 			.combogrid(
 					{
 						pagination : true,
 						panelWidth : 450,
 						singleSelect : true,
 						url:'${vat}/tmsMdContractDevice/loadTmsMdContractPage.do',
 						queryParams : {
 							contractName : contractName,
 						},
 						toolbar : [
 								{
 									text : "<input type='text' id='searchContractName' class='easyui-textbox'  onblur='setsearchivaluesetencoding_id(this)'/>"
 								}, {
 									width : 80,
 									text:'查询',
 									iconCls : 'icon-search',
 									handler : function() {
 										var  searchContractName = $("#searchContractName").val();
 										 initSearchContractName(searchContractName);
 										  $("#search_contractName_id").combogrid('hidePanel');
 										  $("#search_contractName_id").combogrid('showPanel');
 									}
 								} ],
 						columns : [ [ 
 									{
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
 									}
 							] ],
 						onClickRow : function(index, data) {
 				        	 search = false;
 				        	 $("#search_contractName_id").combogrid('hidePanel');
 				        	  $("#search_contractName_id").combogrid('setValue', data.contractName);
 					          $("#search_contractName_id").combogrid('setText', data.contractName);
 						}

 					});

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