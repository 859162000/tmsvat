
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
			<div class="easyui-panel" title="值集定义"
				style="width: 100%; height: 100%; background-color: #E0ECFF"">
				<form id="TmsMdFlexValueSets_searchform" method="post" scroll="no">
					<table align="center">
						<tr>
							<td>值集编码：</td>
							<td><select  class="easyui-combogrid" id="ivaluesetencoding_id"
								type="text" style="width: 150px;" name="ivaluesetencoding" value=""></input></td>
							<td>值集说明：</td>
							<td><select  class="easyui-combogrid"  id="valuesetthat_id" 
								type="text" style="width: 150px;" name="valuesetthat"
								value=""></input></td>
							<td>子值编码范围：</td>
							<td><input id="childcodestrat_id" class="easyui-textbox"
								type="text" style="width: 50px;" name="childcodestrat" value=""></input></td>
							<td>至：</td>
							<td><input id="childcodeend_id" class="easyui-textbox"
								type="text" style="width: 50px;" name="childcodeend" value=""></input></td>
							<td>子值说明：</td>
							<td><input id="childcodthat_id" class="easyui-textbox"
								type="text" style="width: 150px;" name="childcodthat" value=""></input></td>
						</tr>
						<tr style="text-align: center;">
							<td colspan="10"><a href="#"
								id="searchbtnTmsMdFlexValueSets" class="easyui-linkbutton"
								data-options="iconCls:'icon-search',plain:'ture'" style="width: 120px"
								onclick="SearchTmsMdFlexValueSets()">查询</a> <a href="#"
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
				<table class="easyui-datagrid" id="TmsMdFlexValueSetsInit_dataGrid"
					title="值集" style="width: 100%; height: 100%">
				</table>
			</div>
			</div>
			</div>
</div>

	</div>
	<div data-options="region:'east'" title="添加值集定义"
		style="width: 100%;">
		<div class="easyui-layout" style="width: 100%; height: 100%;">
	
	<div region="north" split="true" border="false"
			style="overflow: hidden; height:18%;">
       <div class="easyui-panel"
			style="width: 100%; height: 100%; padding: 0px; background-color: #E0ECFF">
			<form id="tmsMdFlexValueSets_editAddform" method="post" scroll="no">
				<input type="hidden" id="id" name="id">
				<input type="hidden" id="hiddensearchvalues_id">
				<table align="center" style="text-align: center;">
					<tr>
						<td>值集编码:</td>
						<td><input id="flexValueSetCode_id" class="easyui-textbox"
							type="text" style="width: 150px;" name="flexValueSetCode"
							value=""></input></td>
						<td>值集说明：</td>
						<td><input id="description_id" class="easyui-textbox"
							type="text" style="width: 150px;" name="description" value=""></input></td>
								<td>值集名称：</td>
						<td><input id="flexValueSetName_id" class="easyui-textbox"
							type="text" style="width: 150px;" name="flexValueSetName" value=""></input></td>
						<td>数据来源：</td>
						<td><select id="sourceCode_id" name="sourceCode"
							class="easyui-combobox" style="width: 150px;">
								<option value="人工">人工</option>
								<option value="系统同步">系统同步</option>
						</select></td>
					</tr>
					<tr>
						<td>开始日期：</td>
						<td><input id="startDateActive_id" class="easyui-datebox"
							type="text" style="width: 150px;" name="startDateActive" value=""  data-options="editable:false"></input></td>
						<td>结束日期：</td>
						<td><input id="endDateActive_id" class="easyui-datebox"
							type="text" style="width: 150px;" name="endDateActive" value="" data-options="editable:false"></input></td>
						<td>是否启用：</td>
						<td><select id="enabledFlag_id" name="enabledFlag"
							class="easyui-combobox" style="width: 150px;">
								<option value="0">否</option>
								<option value="1">是</option>
						</select></input></td>
					</tr>
					<tr>
						<td colspan="6"><a href="#" id="searchbtn"
							class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:'ture'"
							style="width: 80px" onclick="addUpdate()"> <spring:message
									code="button.Save" />
						</a> <a href="#" class="easyui-linkbutton" style="width: 80px"
							data-options="iconCls:'icon-undo',plain:'ture'"
							onclick="$('#tmsMdFlexValueSets_editAddform').form('reset')" >
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
			<table class="easyui-datagrid" id="addInvoiceList_datagrid"
				style="width: 100%; height: 100%">
			</table>
		</div>
		</div>
		
         </div>
         
         <div class="easyui-dialog"  id="values_dialog" style=" width: 800px; height: 300px;">
         <div class="easyui-panel" title="值"
				style="width: 100%; height:100%; background-color: #E0ECFF">
         <form id="tmsMdFlexValues_editAddform" method="post" scroll="no">
				<input type="hidden" id="flexValueSetId_id" name="flexValueSetId"/><!-- 值集设置id -->
				<input type="hidden" id="flexValuesId_id" name="id"/><!-- 值集设置值id -->
				<table align="center" style="text-align: center;">
					<tr>
						<td>值编码:</td>
						<td><input id="flexValuesCode_id" class="easyui-textbox"
							type="text" style="width: 150px;" name="flexValueCode"
							value=""></input></td>
						<td>值说明：</td>
						<td><input id="valuesdescription_id" class="easyui-textbox"
							type="text" style="width: 150px;" name="description" value=""></input></td>
						<td>是否启用：</td>
						<td><select id="valuesenabledFlag_id" name="enabledFlag"
							class="easyui-combobox" style="width: 150px;">
								<option value="0">否</option>
								<option value="1">是</option>
						</select></td>
					</tr>
					<tr>
					<td>值：</td>
						<td>
						<input id="flexValue_id" class="easyui-textbox"
							type="text" style="width: 150px;" name="flexValue" value=""></input></td>
						<td>开始日期：</td>
						<td><input id="valuesstartDateActive_id" class="easyui-datebox"
							type="text" style="width: 150px;" name="startDateActive" value=""  data-options="editable:false"></input></td>
						<td>结束日期：</td>
						<td><input id="valuesendDateActive_id" class="easyui-datebox"
							type="text" style="width: 150px;" name="endDateActive" value="" data-options="editable:false"></input></td>
						
					</tr>
					<tr>
						<td colspan="6"><a href="#" id="searchbtn"
							class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:'ture'"
							style="width: 80px" onclick="addUpdateValues()"> <spring:message
									code="button.Save" />
						</a> <a href="#" class="easyui-linkbutton" style="width: 80px"
							data-options="iconCls:'icon-undo',plain:'ture'"
							onclick="$('#tmsMdFlexValueSets_editAddform').form('reset')" >
								<spring:message code="button.Clear" />
						</a></td>
					</tr>
				</table>
			</form>
         </div>
         
	</div>

</body>
<script type="text/javascript">
	/**
	 * 初始化页面
	 */
	$(document).ready(function() {
		$("#layoutid").layout('collapse', 'east');//关闭右侧面板
		inittmsmdflexvaluesetsinit_datagrid();//初始化值集设置数据表格
		initIvaluesetencoding_id('');//值集编码初始化
		initvaluesetthat_id('');//值集说明初始化
		initValues_dialog();//值操作窗口初始化
		initValues_datagrid();// 值操作表格初始化
	});
/**
 * 值操作表格
 */
 function initValues_datagrid(){
	$("#values_datagrid").datagrid({
		url : '',
		nowrap : false,
		pagination : true,
		rownumbers : true,
		singleSelect : false,
		striped : true,
		idField : 'id', //主键字段 
		columns : [ [
				{
					field : 'ck',
					title:'序号',
					checkbox : true,
					width : 2,
					sortable:true
				},
				{
					field : 'id',//值集设置值id
					title : '<spring:message code="TmsMdFlexValues.id"/>',
					width :280,
					align : 'center',
					sortable:true,
					hidden:true
				},
				{
					field : 'flexValueCode',//值集值代码
					title : '值集值代码',
					width : 120,
					align : 'center',
					sortable:true
				},	{
					field : 'flexValue',//值集设置值
					title : '值集设置值',
					width : 400,
					align : 'center',
					sortable:true
				},
				{
					field : 'description',//描述（值说明）
					title : '值说明',
					width : 120,
					align : 'center',
					sortable:true
				},
				{
					field : 'enabledFlag',//是否启用
					title : '<spring:message code="TmsMdFlexValues.enabledFlag"/>',
					width : 120,
					align : 'center',
					sortable:true
				},
				{
					field : 'sourceCode',//来源代码
					title : '来源代码',
					width : 120,
					align : 'center',
					sortable:true
				},
				{
					field : 'sourceHeaderId',//来源头ID
					title : '来源头ID',
					width : 120,
					align : 'center',
					sortable:true,
					hidden:true
				},
				{
					field : 'sourceLineId',//来源行ID
					title : '来源行ID',
					width : 120,
					align : 'center',
					sortable:true,
					hidden:true
				},
				{
					field : 'orgId',//组织ID
					title : '组织ID',
					width : 120,
					align : 'center',
					sortable:true,
					hidden:true
				},
				{
					field : 'startDateActive',//开始日期
					title : '开始日期',
					width : 120,
					align : 'center',
					sortable:true
				},
				{
					field : 'endDateActive',//结束日期
					title : '结束日期',
					width : 120,
					align : 'center',
					sortable:true
				}, 
				] ],
	});
}
/**
 * 值操作弹出框
 */
 function initValues_dialog(){
	$("#values_dialog").dialog({
		title:'值操作',
		closed:true,
		region:'center',//弹出框位置
	});
}
/**
 *值增加
 */
function tmsMdFlexValuesAdd(){
	
	var flexValueSetId_id = $("#id").val();//值集设置id
	
	$("#values_dialog").dialog('open');//打开弹出框

	$("#flexValueSetId_id").val(flexValueSetId_id)//值集设置id
	//clearTmsMdFlexValues_editAddform();//清空弹出框的from表单内容
	}
/**
 * 值修改前赋值
 */
function tmsMdFlexValuesEdit(){
	if ($('#addInvoiceList_datagrid').datagrid('getChecked').length != 1) {//判断选择数据行数
		$.messager.alert("操作提示", "请选择一条数据进行修改");
	} else {
		$("#values_dialog").dialog('open');//打开弹出面板
		var row_list = $('#addInvoiceList_datagrid').datagrid(
				'getChecked');//得到选中行
				
		var flexValueSetId_id = $("#id").val();//值集设置id
		var flexValuesId_id = row_list[0].id;//设置值集值id
		var flexValue_id = row_list[0].flexValue;//设置值集值id
		var flexValueSetCode = row_list[0].flexValueCode;//设置值集值编码
		var description = row_list[0].description;//设置值集值说明
		var startDateActive = row_list[0].startDateActive;//设置开始日期
		var endDateActive = row_list[0].endDateActive;//设置结束
		var enabledFlag = row_list[0].enabledFlag;//设置是否启用

		$("#flexValueSetId_id").val(flexValueSetId_id);//值集设置id
		
		$("#flexValuesId_id").val(flexValuesId_id);//设置值集值id
		$("#flexValue_id").textbox('setValue',flexValue_id);//设置值集值id
		$("#flexValuesCode_id").textbox('setValue', flexValueSetCode);//设置值集值编码
		$("#valuesdescription_id").textbox('setValue', description);//设置值集值说明
		$("#valuesenabledFlag_id").textbox('setValue', enabledFlag);//设置是否启用
		$("#valuesstartDateActive_id").textbox('setValue', startDateActive);//设置开始日期
		$("#valuesendDateActive_id").textbox('setValue', endDateActive);//设置结束日期

	}
}
/**
 * 值修改增加操作
 */
function addUpdateValues(){
	$.messager
	.confirm(
			"<spring:message code='client.datacommit.formsave.confirm.title'/>",
			"<spring:message code='client.datacommit.formsave.confirm.text'/>",
			function(result) {
				if (result) {
					$('#tmsMdFlexValues_editAddform')
							.form(
									'submit',
									{
										url : '${vat}/tmsMdFlexValueSets/saveTmsMdFlexValues.do',
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
											var temp = result.status;
											if (temp === 'aaa') {
												clearTmsMdFlexValues_editAddform();
												$("#values_dialog").dialog('close');
												addInvoiceList_datagrid('') ;
												$(
														'#values_dialog')
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
 * 值删除
 */
 function tmsMdFlexValuesRemove(){
	
}

	/**
	 * 值集编码下拉列表初始化
	 */
	function initIvaluesetencoding_id(data) {
		$("#ivaluesetencoding_id")
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
 * 值集设置编码设置
 */
function 	setsearchivaluesetencoding_id(obj){
	 $('#hiddensearchvalues_id').val(obj.value);
}
/**
 * 值集说明设置
 */
function setsearchvaluesetthat_id(obj){
	 $('#hiddensearchvalues_id').val(obj.value);
}
/**
 * 值集说明下拉列表初始化
 */
	function initvaluesetthat_id(data){
		$("#valuesetthat_id")
		.combogrid(
				{
					pagination : true,
					panelWidth : 450,
					singleSelect : true,
					url:'${vat}/tmsMdFlexValueSets/setsearchvaluesetthat.do',
					queryParams : {
						setsearchvaluesetthat_id : data,
					},
					toolbar : [
							{
								text : "<input type='text' id='searchivaluesetencoding_id' class='easyui-textbox' onblur='setsearchvaluesetthat_id(this)'/>"
							}, {
								width : 80,
								text:'查询',
								iconCls : 'icon-search',
								handler : function() {
									var  setsearchvaluesetthat_id = $("#hiddensearchvalues_id").val();
									initvaluesetthat_id(setsearchvaluesetthat_id);
									  $("#valuesetthat_id").combogrid('hidePanel');
									  $("#valuesetthat_id").combogrid('showPanel');
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
						title : '值集说明',
						width : 100,
						align : 'right'
					} ] ],
					onClickRow : function(index, data) {
			        	 search = false;
			        	 $("#valuesetthat_id").combogrid('hidePanel');
			        	  $("#valuesetthat_id").combogrid('setValue', data.flexValueSetCode);
				          $("#valuesetthat_id").combogrid('setText', data.flexValueSetName);
					}

				});

}
	
	/**
	 * 值集初始化
	 */
	function inittmsmdflexvaluesetsinit_datagrid() {
		var ivaluesetencoding_id = $("#ivaluesetencoding_id").combogrid("getValue");
		var valuesetthat_id = $("#valuesetthat_id").combogrid("getValue");
		var childcodestrat_id = $("#childcodestrat_id").textbox("getValue");
		var childcodeend_id = $("#childcodeend_id").textbox("getValue");
		var childcodthat_id = $("#childcodthat_id").textbox("getValue");
		$('#TmsMdFlexValueSetsInit_dataGrid')
				.datagrid(
						{
							url : '${vat}/tmsMdFlexValueSets/loadTmsMdFlexValueSetsPage.do',
							nowrap : false,
							pagination : true,
							rownumbers : true,
							singleSelect : false,
							striped : true,
							idField : 'id', //主键字段  
							queryParams : {
								ivaluesetencoding_id : ivaluesetencoding_id,
								valuesetthat_id : valuesetthat_id,
								childcodestrat_id : childcodestrat_id,
								childcodeend_id : childcodeend_id,
								childcodthat_id : childcodthat_id,
							},
							columns : [ [
									{
										field : 'ck',
										title : '序号',
										checkbox : true,
										width : 2
									},{
										field : 'id',
										title : '值集id',
										width : 300,
										align : 'center',
										sortable:true,
										hidden:true,
									},
									{
										field : 'flexValueSetCode',
										title : '值集代码',
										width : 120,
										align : 'center',
										sortable:true
									},{
										field : 'flexValueSetName',
										title : '值集名称',
										width : 120,
										align : 'center',
										sortable:true
									},
									{
										field : 'description',
										title : '<spring:message code="TmsMdFlexValueSets.description"/>',
										width : 120,
										align : 'center',
										sortable:true
									},
									{
										field : 'sourceCode',
										title : '<spring:message code="TmsMdFlexValueSets.sourceCode"/>',
										width : 120,
										align : 'center',
										sortable:true
									},
									{
										field : 'enabledFlag',
										title : '<spring:message code="TmsMdFlexValueSets.enabledFlag"/>',
										width : 120,
										align : 'center',
										sortable:true
									},
									{
										field : 'startDateActive',
										title : '<spring:message code="TmsMdFlexValueSets.startDateActive"/>',
										width : 120,
										align : 'center',
										sortable:true
									},
									{
										field : 'endDateActive',
										title : '<spring:message code="TmsMdFlexValueSets.endDateActive"/>',
										width : 120,
										align : 'center',
										sortable:true
									},{
										field : 'sourceHeaderId',
										title : '来源id',
										width : 120,
										align : 'center',
										hidden:true,
										sortable:true
									},{
										field : 'parentFlexValueSetId',
										title : '父节点',
										width : 120,
										align : 'center',
										hidden:true,
										sortable:true
									}
									] ],
							toolbar : [ {
								text : '新增',
								iconCls : 'icon-add',
								handler : function() {
									//$("#valuesdiv").hide();
									$("#id").val('0');
									addInvoiceList_datagrid('');
									TmsMdFlexValueSetsAdd();
								}
							}, '-', {
								text : '编辑',
								iconCls : 'icon-edit',
								handler : function() {
									$("#valuesdiv").show();
									TmsMdFlexValueSetsEdit();
									addInvoiceList_datagrid('');
								}
							}, '-', {
								text : '删除',
								iconCls : 'icon-remove',
								handler : function() {
									TmsMdFlexValueSetsRemove();
								}
							} ],
							onLoadSuccess : function() {
								$('#TmsMdFlexValueSetsInit_dataGrid').datagrid(
										'clearSelections')
							},
							onClickRow : function(index, data) {
								var row = $('#TmsMdFlexValueSetsInit_dataGrid')
										.datagrid('getSelected');
								if (row) {
									loadSaveFormData(row);
								}
							}
						});

	}

	/**
	 * 值初始化
	 */
	function addInvoiceList_datagrid(searchvaluesthat_id) {
      var id = 	$("#id").val();
		$('#addInvoiceList_datagrid')
				.datagrid(
						{
							url : '${vat}/tmsMdFlexValueSets/loadTmsMdFlexValuesPage.do',
							nowrap : false,
							pagination : true,
							rownumbers : true,
							singleSelect : false,
							striped : true,
							idField : 'id', //主键字段  
							queryParams : {
								searchvaluesthat_id:searchvaluesthat_id,
						        id:id,
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
										field : 'id',//值集设置值id
										title : '<spring:message code="TmsMdFlexValues.id"/>',
										width :280,
										align : 'center',
										sortable:true,
										hidden:true
									},
									{
										field : 'flexValueCode',//值集值代码
										title : '值集值代码',
										width : 120,
										align : 'center',
										sortable:true
									},	{
										field : 'flexValue',//值集设置值
										title : '值集设置值',
										width : 400,
										align : 'center',
										sortable:true
									},
									{
										field : 'description',//描述（值说明）
										title : '值说明',
										width : 120,
										align : 'center',
										sortable:true
									},
									{
										field : 'enabledFlag',//是否启用
										title : '<spring:message code="TmsMdFlexValues.enabledFlag"/>',
										width : 120,
										align : 'center',
										sortable:true
									},
									{
										field : 'sourceCode',//来源代码
										title : '来源代码',
										width : 120,
										align : 'center',
										sortable:true
									},
									{
										field : 'sourceHeaderId',//来源头ID
										title : '来源头ID',
										width : 120,
										align : 'center',
										sortable:true,
										hidden:true
									},
									{
										field : 'sourceLineId',//来源行ID
										title : '来源行ID',
										width : 120,
										align : 'center',
										sortable:true,
										hidden:true
									},
									{
										field : 'orgId',//组织ID
										title : '组织ID',
										width : 120,
										align : 'center',
										sortable:true,
										hidden:true
									},
									{
										field : 'startDateActive',//开始日期
										title : '开始日期',
										width : 120,
										align : 'center',
										sortable:true
									},
									{
										field : 'endDateActive',//结束日期
										title : '结束日期',
										width : 120,
										align : 'center',
										sortable:true
									}, 
									] ],
							toolbar : [ {
								text : '新增',
								iconCls : 'icon-add',
								handler : function() {
									tmsMdFlexValuesAdd();
								}
							}, '-', {
								text : '编辑',
								iconCls : 'icon-edit',
								handler : function() {
									tmsMdFlexValuesEdit();
								}
							}, '-', {
								text : '删除',
								iconCls : 'icon-remove',
								handler : function() {
									tmsMdFlexValuesRemove();
								}
							},'-',/* {
								text:"<input type='text' id='searchvalues_id' class='easyui-textbox' onblur='setHiddensearchvalues_id(this)'/>"
							},'-',{
								text:'查询',
								iconCls : 'icon-search',
								handler : function() {
									var  searchvaluesthat_id = $("#hiddensearchvalues_id").val();
									addInvoiceList_datagrid(searchvaluesthat_id);
								}
							} */
							],
							onLoadSuccess : function() {
								$('#TmsMdFlexValueSetsInit_dataGrid').datagrid(
										'clearSelections')
							},
							onClickRow : function(index, data) {
								var row = $('#TmsMdFlexValueSetsInit_dataGrid')
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
	function SearchTmsMdFlexValueSets() {
		inittmsmdflexvaluesetsinit_datagrid();
	}

	/**
	 * 跳转值集设置添加
	 */
	function TmsMdFlexValueSetsAdd() {
		clearSaveForm();
		$("#layoutid").layout('expand', 'east');//打开右侧面板
	}
/**
 * 值集设置增加修改
 */
 function    addUpdate(){
	 $('#tmsMdFlexValueSets_editAddform').form('submit', {//提交form表单
			url : '${vat}/tmsMdFlexValueSets/saveTmsMdFlexValueSets.do',
			success : function(result) {//成功之后
				//数据加载以及绑定
				var result = $.parseJSON(result);//将数据格式化成json
				if(result.success==true){//判断是否成功
					$.messager.alert("操作提示", "添加成功");
					inittmsmdflexvaluesetsinit_datagrid();//刷新值集设置数据表格
					alert(result.id);
					$("#id").val(result.id)//设置值集编码
				}
			}
		});
	
}
	/**
	 * 值集设置修改
	 */
	function TmsMdFlexValueSetsEdit() {

		if ($('#TmsMdFlexValueSetsInit_dataGrid').datagrid('getChecked').length != 1) {//判断选择数据行数
			$.messager.alert("操作提示", "请选择一条数据进行修改");
		} else {
			$("#layoutid").layout('expand', 'east');//打开右侧面板
			var row_list = $('#TmsMdFlexValueSetsInit_dataGrid').datagrid(
					'getChecked');
			var id = row_list[0].id;
			var flexValueSetCode = row_list[0].flexValueSetCode;
			var description = row_list[0].description;
			var sourceCode = row_list[0].sourceCode;
			var startDateActive = row_list[0].startDateActive;
			var endDateActive = row_list[0].endDateActive;
			var enabledFlag = row_list[0].enabledFlag;


			var flexValueSetName_id = row_list[0].flexValueSetName;//设置值集值id
			$("#flexValueSetName_id").textbox('setValue',flexValueSetName_id);//设置值集值id
			$("#id").val(id)//设置值集编码
			$("#flexValueSetCode_id").textbox('setValue', flexValueSetCode)//设置值集编码
			$("#description_id").textbox('setValue', description)//设置值集说明
			$("#sourceCode_id").combobox('setValue', sourceCode)//设置系统来源
			$("#startDateActive_id").textbox('setValue', startDateActive)//设置开始日期
			$("#endDateActive_id").textbox('setValue', endDateActive)//设置结束日期
			$("#enabledFlag_id").combobox('setValue', enabledFlag)//设置是否启用

		}
	}
	/**
	 * 值集设置保存修改
	 */
	function TmsMdFlexValueSetsEditSave() {
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
															clearSaveForm();
															SearchTmsMdFlexValueSets();
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
	 * 值集设置删除
	 */
	function TmsMdFlexValueSetsRemove() {
		if ($('#TmsMdFlexValueSetsInit_dataGrid').datagrid('getChecked').length == 0) {
			$.messager.alert("操作提示", "请选择需要删除的数据");
		} else {
			$.messager
					.confirm(
							"<spring:message code='client.datacommit.delete.confirm.title'/>",
							"<spring:message code='client.datacommit.delete.confirm.text'/>",
							function(result) {
								if (result) {
									var row_list = $(
											'#TmsMdFlexValueSetsInit_dataGrid')
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
														url : "${vat}/tmsMdFlexValueSets/removeTmsMdFlexValueSetss.do",
														type : "POST",
														async : true,
														data : urlString, //不能直接写成 {id:"123",code:"tomcat"}  
														dataType : "json",
														// contentType: "charset=utf-8",  
														cache : false,
														success : function(
																result) {
															SearchTmsMdFlexValueSets();
															clearSaveForm();
														}
													});
										}
									}
								}

							});
		}
	}

	//清空右侧面板表单内容
	function clearSaveForm() {
		$('#tmsMdFlexValueSets_editAddform').form('clear');
	}
	//清空值弹出框表单内容
	function clearTmsMdFlexValues_editAddform() {
		$('#tmsMdFlexValues_editAddform').form('clear');
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