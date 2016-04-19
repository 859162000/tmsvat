
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/common/global.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body class="easyui-layout" id="layoutid" style="overflow-y: hidden;" scroll="no" >
	<div data-options="region:'center',iconCls:'icon-ok'" title="数据结构查询" split="true" style="width: 100%;height: 100%;overflow: hidden;" >
	<div  class="easyui-layout" style="width: 100%; height: 100%;">
		<div region="north" split="true" border="false" split="true" style="overflow: hidden; height: 10%;">
			<div class="easyui-panel" title="" style="width: 100%; height: 100%; background-color: #E0ECFF" >
				<form id="TmsMdFlexStructures_searchform" method="post" scroll="no">
					<table id="TmsMdFlexStructures_table_search">
						<tr>						 												 							
							<td>数据结构：</td>
							<td><select id="TmsMdFlexStructuresListInSendForm" name="flexStructuresDescription" class="easyui-combogrid" style="width: 150px;"></select></td>																 
							<td colspan="2"><a href="#" id="searchbtnTmsMdFlexStructures" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 120px" onclick="SearchTmsMdFlexStructures()"><spring:message code='client.search.button.find'/></a>	</td>
							<td colspan="2"><a href="#" id="searchbtnTmsMdFlexStructures" class="easyui-linkbutton" style="width: 120px" onclick="clearSelect()">重置</a>	</td>									
						</tr>
					</table> 
					<table>
						<tr style="display: none;">
						<td><input id="pageNumber" class="easyui-textbox" type="text" style="width: 150px;" name="pageNumber" value=""></input></td>
						<td><input id="pageSize" class="easyui-textbox" type="text"style="width: 150px;" name="pageSize" value=""></input></td>
						</tr>
					</table>
				</form>
			</div>			
		</div>
			
		<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
		<div style="width: 100%; height: 35%"  region="center" split="true"> 
			<table class="easyui-datagrid" id="TmsMdFlexStructuresInit_dataGrid" title="<spring:message code='TmsMdFlexStructures.title'/>" style="width: 100%; height: 100%"
					data-options="singleSelect:true,
					              autoRowHeight:false,
					              pagination:true,
					              pageSize:10,
					              remoteSort:false,
					              multiSort:true">
			</table>
		</div>

		<div style="background-color: #E0ECFF" >
			<div id="TmsMdFlexStructures_addOrEdit_dlg" class="easyui-dialog" style="width: 800px; height: 500px;" closed="true" buttons="#TmsMdFlexStructures_addOrEdit_dlg_dlg-buttons">
				<form id="TmsMdFlexStructures_editform" class="easyui-form" method="post" data-options="novalidate:true" commandsName="clientManageForm">
					<table id="TmsMdFlexStructures_edittable" >
						<tr style="display: none;">
						    <td><spring:message code="TmsMdFlexStructures.id" />：</td>
							<td><input id="idInDialog" name="id" style="width: 150px;" class="easyui-textbox" type="text"></td>
						</tr>
						<tr>	
							<td align="right">数据结构编码：</td>
							<td><input id="flexStructuresCodeInDialog" name="flexStructuresCode" style="width: 150px;" class="easyui-textbox" type="text"></td>
						</tr>
						<tr>
							<td align="right">数据结构说明：</td>
							<td><input id="flexStructuresDescriptionInDialog" name="flexStructuresDescription" style="width: 150px;" class="easyui-textbox" type="text"></td>
						</tr>
						<tr >	
							<td align="right"><spring:message code="TmsMdFlexStructures.flexStructuresType" />：</td>														
							<td><input:select  easyuiStyle="width:150px;" id="flexStructuresTypeInDialog" name="flexStructuresType" value="$customerManageInit_editform_enabledFlag" easyuiClass="easyui-combobox">
            					<option  value=""></option>
            					<input:systemStatusOption parentCode="VAT_DATA_MAPPING_TYPE"/>
								</input:select></td>	
						</tr>
						<tr style="display: none;">
							<td><spring:message code="TmsMdFlexStructures.tableId" />：</td>
							<td><input id="tableIdInDialog" name="tableId" style="width: 150px;" class="easyui-textbox" type="text"></td>
						</tr>
						<tr style="display: none;">	
							<td><spring:message code="TmsMdFlexStructures.tableName" />：</td>
							<td><input id="tableNameInDialog" name="tableName" style="width: 150px;" class="easyui-textbox" type="text"></td>
						</tr>
						<tr style="display: none;">
							<td><spring:message code="TmsMdFlexStructures.description" />：</td>
							<td><input id="descriptionInDialog" name="description" style="width: 150px;" class="easyui-textbox" type="text"></td>
						</tr>
						<tr >	
							<td align="right">是否启用：</td>
							<td><input:select easyuiStyle="width:150px;required:true," id="enabledFlagInDialog" name="enabledFlag" value="$customerManageInit_editform_enabledFlag" easyuiClass="easyui-combobox" >           						 
            		        <option value=""></option>
 		                    <input:systemStatusOption parentCode="BASE_IS_VALID"/>	
			                </input:select></td>																				
						</tr>
						<tr>
							<td align="right"><spring:message code="TmsMdFlexStructures.startDate" />：</td>
							<td><input id="startDateInDialog" name="startDate" style="width: 150px;" type="text" class="easyui-datebox" data-options="required:true"></input></td>							
						</tr>
						<tr>	
							<td align="right"><spring:message code="TmsMdFlexStructures.endDate" />：</td>
							<td><input id="endDateInDialog" name="endDate" style="width: 150px;" class="easyui-datebox" type="text"></td>
						</tr>
						<tr style="display: none;">
							<td><spring:message code="TmsMdFlexStructures.parentId" />：</td>
							<td><input id="parentIdInDialog" name="parentId" style="width: 150px;" class="easyui-textbox" type="text"></td>
							<td><spring:message code="TmsMdFlexStructures.orgId" />：</td>
							<td><input id="orgIdInDialog" name="orgId" style="width: 150px;" class="easyui-textbox" type="text"></td>
						</tr>
					</table>
				</form>
				<div id="TmsMdFlexStructures_addOrEdit_dlg_dlg-buttons">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="TmsMdFlexStructuresEditSave()"><spring:message code="button.Save" />
				</a> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#TmsMdFlexStructures_addOrEdit_dlg').dialog('close')"><spring:message code="button.Close" /></a>
				</div>
			</div>
	
		
			<div id="history_dlg" class="easyui-dialog" style="width: 800px; height: 500px;" closed="true">
				<table class="easyui-datagrid" id="history_dataGrid" title="<spring:message code='TmsMdFlexStructures.title'/>" style="width: 100%; height: 88%"
					data-options="singleSelect:true,
								  autoRowHeight:false,
					              remoteSort:false,
					              multiSort:true">
				</table>
			</div>
		</div>			
	     <div class="easyui-panel" region="south" split="true" title="值集" style="width: 100%; height: 45%; background-color: #E0ECFF" >  	
			<table class="easyui-datagrid" id="TmsMdFlexStructures_table" style="width: 100%; height: 100%"
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
		   <div style="background-color: #E0ECFF" >
		   <div   id="tmsmdflexstructures_flexsegments_dlg" class="easyui-dialog" style="width: 800px; height: 500px;" closed="true" buttons="#TmsMdflexsegments_addOrEdit_dlg_dlg-buttons">
			<form id="flexsegments_searchform" class="easyui-form" method="post" data-options="novalidate:true">
				<table id="tmsmdflexstructures_flexsegments_table">
					<tr > 
					    <td style="display: none;"></td>
					    <td style="display: none;"><input class="easyui-textbox" type="text" name=id /></td>
					    <td style="display: none;"></td>
					    <!-- 非常重要 获取数据结构ID -->
					    <td td style="display: none;"><input id="flexsegments_StructuresId" class="easyui-textbox" type="text" name=flexStructuresId /></td>
					</tr>
					<tr>
						<td align="right">段编码:</td>
						<td><input class="easyui-textbox" id="flexsegments.segmentNum" type="text" name=segmentNum style="width: 150px; "/></td>
					</tr>
					<tr>
						<td align="right">段说明:</td>
						<td><input class="easyui-textbox" id="flexsegments.segmentName" type="text" name="segmentName" style="width: 150px; "/></td>													
					</tr>
					<tr style="display: none;">
						<td align="right">段属性:</td>
						<td><input id="flexsegments.segmentAttribute" type="text" class="easyui-textbox" style="width: 150px; " name="segmentAttribute" /></td>
					</tr>
					<tr>
					    <td style="display: none;"><input id="flexsegments_flexValueSetId_getId" type="text" class="easyui-textbox" width="150" name="flexValueSetId" /></td>
						<td align="right">值集:</td>
					    <td><select id="flexsegments_flexValueSetId" name="flexValueSetName" class="easyui-combogrid" style="width: 150px; " ></select></td>  
					</tr>
					<tr style="display: none;">	
					  <td >是否显示：</td>
						 <td><input:select easyuiStyle="width:150px;required:true" id="displayFlagInDialog" name="displayFlag" value="$customerManageInit_editform_enabledFlag" easyuiClass="easyui-combobox" >           						 
            		        <option value=""></option>
            		        <input:systemStatusOption parentCode="BASE_IS_VALID"/>				
			                </input:select></td>	
					</tr>
					<tr > 
					  <td >是否必须：</td>
					 <td><input:select easyuiStyle="width:150px;required:true" id="requiredFlagInDialog" name="requiredFlag" value="$customerManageInit_editform_enabledFlag" easyuiClass="easyui-combobox" >           						 
            		        <option value=""></option>
            		        <input:systemStatusOption parentCode="BASE_IS_VALID"/>				
			                </input:select></td>
					</tr>
					<tr >
					  <td>启用:</td>	 
  						<td><input:select easyuiStyle="width:150px;required:true" id="enabledFlagInDialog2" name="enabledFlag" value="$customerManageInit_editform_enabledFlag" easyuiClass="easyui-combobox" >           						 
            		        <option value=""></option>
            		        <input:systemStatusOption parentCode="BASE_IS_VALID"/>				
			                </input:select></td>
  					</tr>
  					<tr  style="display: none;">		
					  <td>组织:</td>
				      <td><input id="flexsegments。orgId" class="easyui-textbox" width="90" name="orgId" /></td>	
					</tr>
                </table>		
			</form>	
			<div id="TmsMdflexsegments_addOrEdit_dlg_dlg-buttons">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="TmsMdFlexSegmentsEditSave()"><spring:message code="button.Save" />
				</a> <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#tmsmdflexstructures_flexsegments_dlg').dialog('close')"><spring:message code="button.Close" /></a>
			</div>
		   <div class="easyui-dialog" closed="true">
      		 <from id="getByBaseOrg" method="post">
        		<table>
          			<tr style="display:none">
		        	<td><input id="pageNumber2" class="easyui-textbox" type="text" style="width:0px;" name="pageNumber" value=""></input></td>
		       	 	<td><input id="pageSize2" class="easyui-textbox" type="text" style="width:0px;" name="pageSize" value=""></input></td>
		    		</tr>
        		</table>
       		</from>
     	  </div>
		</div>   				
	  </div>
	</div>
	
		<div title="添加数据结构" id="twodiv" style="width: 0%;height: 0%" border=0 buttons="#customerManageInit_add_dlg-buttons" modal="true"  maximized='true' closed="true">

		<div class="easyui-panel" id="div3" style="width: 100%; height: 0%; padding: 0px; background-color: #E0ECFF" >
			<form id="TmsMdFlexStructures_editAddform" method="post" scroll="no">
				<table id="flexStructures_table">
					<tr>
						<td style="display: none;">数据结构定义ID：</td>
						<td style="display: none;"><input id="idId" class="easyui-textbox" type="text" style="width: 150px;" name="id" value=""></input></td>		
						<td  align="right">数据结构代码：</td>
						<td ><input id="flexStructuresCodeId" class="easyui-textbox" type="text" style="width: 150px;" name="flexStructuresCode" value=""></input></td>
						<td align="right">数据结构说明：</td>
						<td><input id="flexStructuresDescriptionId" class="easyui-textbox" type="text" style="width: 150px;" name="flexStructuresDescription" value=""></input></td>
						<td align="right">数据结构类型：</td>
						<td><input id="flexStructuresTypeId" class="easyui-textbox" type="text" style="width: 150px;" name="flexStructuresType" value=""></input></td>
			        	<td style="display: none;">数据表名ID：</td>
						<td style="display: none;"><input id="tableIdId" class="easyui-textbox" type="text" style="width: 150px;" name="tableId" value=""></input></td>								
						<td align="right">有效日期：</td>
						<td><input id="startDateId" class="easyui-datebox" type="text" style="width: 150px;" name="startDate" value=""></input></td>
					</tr>				
				    <tr>					
						<td align="right">数据表名：</td>
						<td><input id="tableNameId" class="easyui-textbox" type="text" style="width: 150px;" name="tableName" value=""></input></td>
						<td style="display: none;">描述：</td>
						<td style="display: none;"><input id="descriptionId" class="easyui-textbox" type="text" style="width: 150px;" name="description" value=""></input></td>
						<td align="right">是否有效：</td>
						<td><input id="enabledFlagId" class="easyui-textbox" type="text" style="width: 150px;" name="enabledFlag" value=""></input></td>			
						<td align="right">失效日期：</td>
						<td><input id="endDateId" class="easyui-datebox" type="text" style="width: 150px;" name="endDate" value=""></input></td>
						<td style="display: none;">父结点ID：</td>
						<td style="display: none;"><input id="parentIdId" class="easyui-textbox" type="text" style="width: 150px;" name="parentId" value=""></input></td>
						<td align="right">组织ID：</td>
						<td><input id="orgIdId" class="easyui-textbox" type="text" style="width: 150px;" name="orgId" value=""></input></td>															
				    </tr>
					<tr>
				       <td></td>
				       <td></td>
				       <td></td>
				       <td><a href="javascript:void(0)"  class="easyui-linkbutton" iconCls="icon-save"" style="width: 120px" onclick="ChanageSave()">保存</a></td>
					</tr>
				</table>
				<table>
					<tr style="display: none;">
						<td><input id="pageNumber1" class="easyui-textbox" type="text" style="width: 130px;" name="pageNumber" value=""></input></td>
						<td><input id="pageSize1" class="easyui-textbox" type="text" style="width: 130px;" name="pageSize" value=""></input></td>
					</tr>
				</table>
			</form>		 
		</div>  


	</div>
	
	
	
	
	
	</div>
	
	<!-- ################################################################################################### -->
	
	
<!-- 2级页面   ························································································· -->	
	
</body>
<script type="text/javascript">

$(document).keydown(function(e){
    var target = e.target ;
    var tag = e.target.tagName.toUpperCase();
    if(e.keyCode == 8){
     if((tag == 'INPUT' && !$(target).attr("readonly"))||(tag == 'TEXTAREA' && !$(target).attr("readonly"))){
      if((target.type.toUpperCase() == "RADIO") || (target.type.toUpperCase() == "CHECKBOX")){
       return false ;
      }else{
       return true ; 
      }
     }else{
      return false ;
     }
    }
}); 

//  var sign;
	$(document).ready(
			function() {
				
				$('#TmsMdFlexStructures_searchform').form(
						'load',
						{
							pageNumber : $('#TmsMdFlexStructuresInit_dataGrid').datagrid('options').pageNumber,
							pageSize : $('#TmsMdFlexStructuresInit_dataGrid').datagrid('options').pageSize
						});
			
				
				$('#TmsMdFlexStructures_editAddform').form(
						'load',
						{
							pageNumber : $('#TmsMdFlexStructures_table').datagrid('options').pageNumber,
							pageSize : $('#TmsMdFlexStructures_table').datagrid('options').pageSize
						});
				SearchTmsMdFlexStructures();
				TmsMdFlexStructuresEdit1();
				// addTwo();
			
		
				
				
			});

	
	$(function() {
		$('#TmsMdFlexStructuresInit_dataGrid')
				.datagrid(
						{
							url : '',
							fitColumns : true,
							nowrap : false,							
							remoteSort:false,
							pagination : true,
							rownumbers : true,
							singleSelect : true,
							fitColumns : true,
							striped : true,
							idField : 'id', //主键字段  
							columns : [ [
								/*	{
										field : 'ck',
										checkbox : true,
										width : 2,
										hidden:true
									},  */
									{
										field : 'flexStructuresCode',
										title : '数据结构编码',
										width : 80,
										align : 'center'
									},
									{
										field : 'flexStructuresDescription',
										title : '数据结构说明',
										width : 80,
										align : 'center'
									},
									{
										field : 'flexStructuresType',
										title : '<spring:message code="TmsMdFlexStructures.flexStructuresType"/>',
										width : 80,
										align : 'center',
									 formatter:orgFormat
										
									},
							// 4		{
									//	field : 'tableId',
									//	title : '<spring:message code="TmsMdFlexStructures.tableId"/>',
									//	width : 80,
									//	align : 'center'
									//},
							// 5		{
							//			field : 'tableName',
							//			title : '<spring:message code="TmsMdFlexStructures.tableName"/>',
							//			width : 80,
							//			align : 'center'
							//		},
						//6			{
						//				field : 'description',
						//				title : '<spring:message code="TmsMdFlexStructures.description"/>',
						//				width : 80,
						//				align : 'center'
						//			},
									{
										field : 'enabledFlag',
										title : '<spring:message code="TmsMdFlexStructures.enabledFlag"/>',
										width : 80,
										align : 'center',
									 formatter:orgFormat2	
									},
									{
										field : 'startDate',
										title : '<spring:message code="TmsMdFlexStructures.startDate"/>',
										width : 80,
										align : 'center',
										sortable:true
									},
									{
										field : 'endDate',
										title : '<spring:message code="TmsMdFlexStructures.endDate"/>',
										width : 80,
										align : 'center'
						//			},
						//			{
						//				field : 'parentId',
						//				title : '<spring:message code="TmsMdFlexStructures.parentId"/>',
						//				width : 80,
						//				align : 'center'
						//			},
						//			{
						//				field : 'orgId',
						//				title : '<spring:message code="TmsMdFlexStructures.orgId"/>',
						//				width : 80,
						//				align : 'center'
									} ] ],
							toolbar : [ {
								text : '新增',
								iconCls : 'icon-add',
								handler : function() {
									TmsMdFlexStructuresAdd();
								}
							}, '-', {
								text : '编辑',
								iconCls : 'icon-edit',
								handler : function() {
									TmsMdFlexStructuresEdit();
								}
							}
						/* 	, '-', {
								text : '删除',
								iconCls : 'icon-remove',
								handler : function() {
									TmsMdFlexStructuresRemove();
								}
							} */ 
							],
							onLoadSuccess : function() {
								$('#TmsMdFlexStructuresInit_dataGrid')
										.datagrid('clearSelections')
							},
							onClickRow : function(index, data) {
								var row = $('#TmsMdFlexStructuresInit_dataGrid')
										.datagrid('getSelected');
								if (row) {
									getChecked();
								}
							}
						});
		initTaxEntity();
	//	InitCombobox();
		//设置分页控件	
		var p = $('#TmsMdFlexStructuresInit_dataGrid').datagrid('getPager');
		$(p).pagination(
						{
							pageSize : 10,//每页显示的记录条数，默认为10           
							//pageList: [10,20,30],//可以设置每页记录条数的列表           
							beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
							afterPageText : '<spring:message code="pagination.afterPageText"/>',
							displayMsg : '<spring:message code="pagination.displayMsg"/>',
							onSelectPage : function(pageNumber, pageSize) {//分页触发		
								find(pageNumber, pageSize);
								SearchTmsMdFlexStructures();
							}
						});
	});
	

	
	
		
	function find(pageNumber, pageSize) {
		$('#TmsMdFlexStructures_searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		SearchTmsMdFlexStructures();
	}
	function SearchTmsMdFlexStructures() {
		
		$("#TmsMdFlexStructuresInit_dataGrid").datagrid("loading");
		$('#TmsMdFlexStructures_searchform')
				.form(
						'submit',
						{
							url : '${vat}/tmsMdFlexStructures/loadTmsMdFlexStructuresPage.do',
							onSubmit : function() {
								return $(this).form('enableValidation').form('validate');
							},
							success : function(result) {
								//数据加载以及绑定
								var result = $.parseJSON(result);
								$("#TmsMdFlexStructuresInit_dataGrid").datagrid('loadData', result);
								$("#TmsMdFlexStructuresInit_dataGrid").datagrid("loaded");
							}
						});
	}
	function getHistory() {
		//	alert(0);
		//$("#TmsMdFlexStructuresInit_dataGrid").datagrid("loading");
		$('#TmsMdFlexStructures_searchform').form('submit', {
			url : '${vat}/tmsMdFlexStructures/loadhistory.do',
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
	function searchTmsMdFlexStructures() {
		$("#history_dlg").dialog('open').dialog('setTitle',
				"<spring:message code='TmsMdFlexStructures.history'/>");
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
	function TmsMdFlexStructuresAdd(){	
		
		clearSaveForm();
		$("#TmsMdFlexStructures_addOrEdit_dlg").dialog('open').dialog('setTitle',"<spring:message code='client.dialog.clientadd.title'/>");		
		clearSaveFormTwoS();	
	    $('#TmsMdFlexStructures_table').datagrid('loadData', { total: 0, rows: [] });
		$("#startDateInDialog").datebox({
			required:true	
		});
		$("#flexStructuresTypeInDialog").combobox({
				panelHeight:'auto'
				});
		$("#enabledFlagInDialog").combobox('setValue','是');
		$("#enabledFlagInDialog").combobox({
				panelHeight:'auto'
				});
	}
	
	//编辑
	function TmsMdFlexStructuresEdit() {

		if ($('#TmsMdFlexStructuresInit_dataGrid').datagrid('getChecked').length > 1) {
			$.messager.alert("操作提示", "请选择一条数据进行修改");
		} else {
		//	
			var row_list = $('#TmsMdFlexStructuresInit_dataGrid').datagrid(
					'getChecked');
			if (row_list) {
				var id = '';
				$.each(row_list, function(index, item) {
					if (index == 0) {
						id = item.id;
					}
				});
					
				if (id != '') {
					$.post('${vat}/tmsMdFlexStructures/loadModifyTmsMdFlexStructures.do',
									{
										id : id
									},
									function(result) {
										if (result.status == '0') {
											//打开2级页面
										//	$("#layoutid").layout("expand","east");
																															
											$("#TmsMdFlexStructures_addOrEdit_dlg").dialog('open').dialog('setTitle',"<spring:message code='client.dialog.clientedit.title'/>");
											//2级页面上半部分数据绑定																						
									        $("#TmsMdFlexStructures_editform").form('load',result.data);

											
										$("#TmsMdFlexStructures_editform").form('load',result.data);
										} else if (result.success == '1') {
											$.messager.alert('<spring:message code="system.alert"/>',result.errorMsg);
											SearchTmsMdFlexStructures();
									
										}
									}, 'json');
				} else {
					$.messager.alert('<spring:message code="system.alert"/>',
							'请选择需要编辑的数据');
				}
			}
		}
	}
	
	
	
  //保存
	function TmsMdFlexStructuresEditSave() {
	  
		
		 $.messager
			.confirm(
					"<spring:message code='client.datacommit.formsave.confirm.title'/>",
					"<spring:message code='client.datacommit.formsave.confirm.text'/>",
					function(result) {
						if (result) {
							$('#TmsMdFlexStructures_editform')
									.form(
											'submit',
											{
												url : '${vat}/tmsMdFlexStructures/saveTmsMdFlexStructures.do',
												onSubmit : function(data) {
													
												//	alert(data);
													return $(this).form('enableValidation').form('validate');
												},
												success : function(result) {
													var result = $.parseJSON(result);
													if (result.status == '0') {
													//	clearSaveFormS();
													//	SearchTmsMdFlexStructures();
												
														clearSaveForm();
														SearchTmsMdFlexStructures();
														$('#TmsMdFlexStructures_addOrEdit_dlg').dialog('close');																														
														$.messager.alert('<spring:message code="system.alert"/>','保存成功');
													}

												}
											});
						}else{
							
							$.messager.alert('<spring:message code="system.alert"/>','保存失败');
						}
							
						
					});       
	}
	//删除一条记录

	function TmsMdFlexStructuresRemove() {
		if (($('#TmsMdFlexStructuresInit_dataGrid').datagrid('getChecked').length == 0)
				|| ($('#TmsMdFlexStructuresInit_dataGrid').datagrid(
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
											'#TmsMdFlexStructuresInit_dataGrid')
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
														url : "${vat}/tmsMdFlexStructures/removeTmsMdFlexStructuress.do",
														type : "POST",
														async : true,
														data : urlString, //不能直接写成 {id:"123",code:"tomcat"}  
														dataType : "json",
														// contentType: "charset=utf-8",  
														cache : false,
														success : function(
																result) {
															SearchTmsMdFlexStructures();
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
		$('#TmsMdFlexStructures_editform').form('clear');
	}
	
	//重置查询框
	function clearSelect(){
		
		$("#TmsMdFlexStructures_table_search").form('clear');
	}
	
	
	
	// 2级页面代码····················································································································
	
	function findtwo(pageNumber, pageSize) {
		
		$('#TmsMdFlexStructures_editAddform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		
		SearchTmsMdFlexSegments();
	}
	function SearchTmsMdFlexSegments() {     
		$("#TmsMdFlexStructures_table").datagrid("loading");
		$('#TmsMdFlexStructures_editAddform')
				.form(
						'submit',
						{
							url : '${vat}/tmsMdFlexStructures/loadTmsMdFlexSegmentsPage.do',
							onSubmit : function() {
								return $(this).form('enableValidation').form('validate');
							},
							success : function(result) {
								//数据加载以及绑定
								var result = $.parseJSON(result);
								$("#TmsMdFlexStructures_table").datagrid('loadData', result);
								$("#TmsMdFlexStructures_table").datagrid("loaded");
							}
						});
	}
	
	
	
	function TmsMdFlexStructuresEdit1() {
			
		          
		$('#TmsMdFlexStructures_table')
				.datagrid(
						{
							url : '',
							fitColumns : true,
							nowrap : false,
							pagination : true,
							rownumbers : true,
							singleSelect : true,
							fitColumns : true,
							striped : true,
							idField : 'id', //主键字段  
							columns : [ [
								/*	{
										field : 'ck',
										checkbox : true,
										width : 2
									},  */
									{
							        	field : 'id',
										title : '数据结构段定义ID',
										width : 80,
										align : 'center',
										hidden:true
									},
									{
										field : 'flexStructuresId',
										title : '数据结构定义ID',
										width : 80,
										align : 'center',
										hidden:true
									},
									{
										field : 'segmentNum',
										title : '段编码',
										width : 80,
										align : 'center'
									},
									{
										field : 'segmentName',
										title : '段说明',
										width : 80,
										align : 'center'
									},
									{
										field : 'segmentAttribute',
										title : '段属性',
										width : 80,
										align : 'center',
										hidden:true
									},
									{   //这是值集ID 页面显示值集名称
										field : 'flexValueSetId',
										title : '值集ID',
										width : 80,
										align : 'center',
										hidden:true
								//	 formatter:getFlexValueName
									},  
									{
										field : 'flexValueSetName',
										title : '值集',
										width : 80,
										align : 'center',
								//	 formatter:setFlexValueName
									},   
									{
										field : 'displayFlag',
										title : '是否显示',
										width : 80,
										align : 'center',
										hidden:true,
									 formatter:orgFormat2	
									},
									{
										field : 'requiredFlag',
										title : '是否必须',
										width : 80,
										align : 'center',
									 formatter:orgFormat2	
									},
									{
										field : 'enabledFlag',
										title : '启用',
										width : 80,
										align : 'center',
								     formatter:orgFormat2	
									} ,
									{
										field : 'orgId',
										title : '组织',
										width : 80,
										hidden:true,
										align : 'center'
									}] ],
							toolbar : [ {
								text : '新增',
								iconCls : 'icon-add',
								handler : function() {
									
									TmsMdFlexSegmentsAdd();
								}
							}, '-', {
								text : '编辑',
								iconCls : 'icon-edit',
								handler : function() {
									TmsMdFlexSegmentsEdit();
									
									var getFlexValueName = $("#TmsMdFlexStructures_table").datagrid('getSelected');
									$("#flexsegments_flexValueSetId").combobox('setValue',getFlexValueName.rows[0].flexValueSetName);
								}
							}
					/* 		, '-', {
								text : '删除',
								iconCls : 'icon-remove',
								handler : function() {
									TmsMdFlexStructuresRemove();
								}
							}  */
							],
							onLoadSuccess : function() {
								$('#TmsMdFlexStructures_table')
										.datagrid('clearSelections')
							},
							onClickRow : function(index, data) {
								var row = $('#TmsMdFlexStructures_table')
										.datagrid('getSelected');
								if (row) {
								//	loadSaveFormData(row);
								}
							}
						});
		
		var p1 = $("#TmsMdFlexStructures_table").datagrid('getPager');
		$(p1).pagination(
						{
							pageSize : 10,//每页显示的记录条数，默认为10           
							//pageList: [10,20,30],//可以设置每页记录条数的列表           
							beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
							afterPageText : '<spring:message code="pagination.afterPageText"/>',
							displayMsg : '<spring:message code="pagination.displayMsg"/>',
							onSelectPage : function(pageNumber, pageSize) {//分页触发		
														
								findtwo(pageNumber, pageSize);
							//	SearchTmsMdFlexSegments();
							}
						});		
		
	}
	
	
	//2级添加
	function TmsMdFlexSegmentsAdd(){
		clearSaveFormTwo();
		$("#tmsmdflexstructures_flexsegments_dlg").dialog('open').dialog('setTitle',"<spring:message code='client.dialog.clientadd.title'/>");	
	   
		$("#displayFlagInDialog").combobox('setValue','1');
	    $("#displayFlagInDialog").combobox('setText','是');
	   
	    $("#requiredFlagInDialog").combobox('setValue','1');
	    $("#requiredFlagInDialog").combobox('setText','是');
	  
	    $("#enabledFlagInDialog2").combobox('setValue','1');	    
	    $("#enabledFlagInDialog2").combobox('setText','是');
	   
	    getFlexValuePanl();
	    
	    //设置数据段面板数据结构ID
	    var getFlexStructuresId = $("#TmsMdFlexStructuresInit_dataGrid").datagrid('getSelected');
	    $("#flexsegments_StructuresId").textbox('setValue',getFlexStructuresId.id);   
	    
	    
	    
	}
	
	//清除3级页面from
	function clearSaveFormTwo() {
		$('#flexsegments_searchform').form('clear');
	}
	
	//清除2级页面from
	function clearSaveFormTwoT() {
		$("#TmsMdFlexStructures_editAddform").form('clear');
	}
	//2级页面表格
	function clearSaveFormTwoS(){
		$("#flexStructures_table").form('clear');
	}
	//2级页面下边表格
	function clearSaveFormTwoSD(){
		$("#TmsMdFlexStructures_table_from").form('clear');
//	    $('#TmsMdFlexStructures_table').datagrid('reload');
	}
	// 表 是否显示不能为空
	function TmsMdFlexSegmentsEditSave() {
		$.messager
				.confirm(
						"<spring:message code='client.datacommit.formsave.confirm.title'/>",
						"<spring:message code='client.datacommit.formsave.confirm.text'/>",
						function(result) {
							if (result) {
								$('#flexsegments_searchform')
										.form(
												'submit',
												{
													url : '${vat}/tmsMdFlexStructures/saveTmsMdFlexSegments.do',
													onSubmit : function() {
														return $(this).form('enableValidation').form('validate');
													},
													success : function(result) {
														var result = $.parseJSON(result);
														if (result.status == '0') {
															clearSaveForm();
															SearchTmsMdFlexSegments();
															$('#tmsmdflexstructures_flexsegments_dlg').dialog('close');
															$.messager.alert('<spring:message code="system.alert"/>','保存成功');
														}

													}
												});
							}
						});
	}
	
	//2级页面编辑
	function TmsMdFlexSegmentsEdit() {
				
		if ($('#TmsMdFlexStructures_table').datagrid('getChecked').length > 1) {
			$.messager.alert("操作提示", "请选择一条数据进行修改");
		} else {
		//	clearSaveForm();
			var row_list = $('#TmsMdFlexStructures_table').datagrid(
					'getChecked');
			if (row_list) {
				var id = '';
				$.each(row_list, function(index, item) {
					if (index == 0) {
						id = item.id;
					}
				});
					
				if (id != '') {
					$.post('${vat}/tmsMdFlexStructures/loadModifyTmsMdFlexSegmentsInParam.do',
									{
										id : id
									},
									function(result) {
										if (result.status == '0') {
																		
											$("#tmsmdflexstructures_flexsegments_dlg").dialog('open').dialog('setTitle',"编辑窗口");	
										
											$("#flexsegments_searchform").form('load',result.data);
										} else if (result.success == '1') {
											$.messager.alert('<spring:message code="system.alert"/>',result.errorMsg);
											SearchTmsMdFlexStructures();
											clearSaveForm();
											$('#tmsmdflexstructures_flexsegments_dlg').dialog('close');
										}
									}, 'json');
				} else {
					$.messager.alert('<spring:message code="system.alert"/>',
							'请选择需要编辑的数据');
				}
			}
		}
	}
	
		function TmsMdFlexStructuresEditSaveTwo() {
			$.messager
					.confirm(
							"<spring:message code='client.datacommit.formsave.confirm.title'/>",
							"<spring:message code='client.datacommit.formsave.confirm.text'/>",
							function(result) {
								if (result) {
									$("#TmsMdFlexStructures_editAddform")
											.form(
													'submit',
													{
														url : '${vat}/tmsMdFlexStructures/loadSelect.do',
														onSubmit : function() {
															return $(this).form('enableValidation').form('validate');
														},
														success : function(result) {
															var result = $.parseJSON(result);
																																																													
     													if (result.status == '0') {
     														$("#TmsMdFlexStructures_editAddform").form('load',result.data);
     														SearchTmsMdFlexStructures();
     														$("#layoutid").layout("collapse","east");
     														$.messager.alert('<spring:message code="system.alert"/>','保存成功');
     													
     														
															// $('#TmsMdFlexStructures_addOrEdit_dlg').dialog('close');
																
															}

														}
													});
								}
							});

		}
	  
//##############################################################################################################################		
	//####################################################################################################################	

	
	
	//·················································································································  

	//第一页下拉面板
function initTaxEntity() {
	
	$('#TmsMdFlexStructuresListInSendForm').combogrid(
					{
						panelWidth : 500,
						idField : 'flexStructuresDescription', //ID字段  
						textField : 'flexStructuresDescription', //显示的字段  
						url : "",
						fitColumns : true,
						striped : true,
						editable : false,
						pagination : true, //是否分页
						rownumbers : true, //序号
						collapsible : false, //是否可折叠的
						fit : false, //自动大小
						method : 'post',
						columns : [ [ {
							field : 'flexStructuresCode',
							title : '数据结构代码',
							width : 200,
						//	hidden : true
						}, {
							field : 'flexStructuresDescription',
							title : '数据结构说明',
							width : 300
						} ] ],
						toolbar : [
								{
									text : '数据结构说明:<input type="text" id="taxEntityId" class="easyui-textbox"/>'
								}, {
									text : "查询",
									iconCls : 'icon-search',
									handler : function() {
										findTaxEntity();
									}
								}, '-' ],
						keyHandler : {
							up : function() { //【向上键】押下处理
								//取得选中行
								var selected = $(
										'#TmsMdFlexStructuresListInSendForm')
										.combogrid('grid').datagrid(
												'getSelected');
								if (selected) {
									//取得选中行的rowIndex
									var index = $(
											'#TmsMdFlexStructuresListInSendForm')
											.combogrid('grid')
											.datagrid('getRowIndex',
													selected);
									//向上移动到第一行为止
									if (index > 0) {
										$('#TmsMdFlexStructuresListInSendForm')
												.combogrid('grid')
												.datagrid('selectRow',
														index - 1);
									}
								} else {
									var rows = $(
											'#TmsMdFlexStructuresListInSendForm')
											.combogrid('grid').datagrid(
													'getRows');
									$('#TmsMdFlexStructuresListInSendForm')
											.combogrid('grid').datagrid(
													'selectRow',
													rows.length - 1);
								}
							},
							down : function() { //【向下键】押下处理
								//取得选中行
								var selected = $(
										'#TmsMdFlexStructuresListInSendForm')
										.combogrid('grid').datagrid(
												'getSelected');
								if (selected) {
									//取得选中行的rowIndex
									var index = $(
											'#TmsMdFlexStructuresListInSendForm')
											.combogrid('grid')
											.datagrid('getRowIndex',
													selected);
									//向下移动到当页最后一行为止
									if (index < $(
											'#TmsMdFlexStructuresListInSendForm')
											.combogrid('grid').datagrid(
													'getData').rows.length - 1) {
										$('#TmsMdFlexStructuresListInSendForm')
												.combogrid('grid')
												.datagrid('selectRow',
														index + 1);
									}
								} else {
									$('#TmsMdFlexStructuresListInSendForm')
											.combogrid('grid').datagrid(
													'selectRow', 0);
								}
							},	

						},		

					});

	//取得分页组件对象
	var pager = $('#TmsMdFlexStructuresListInSendForm').combogrid('grid')
			.datagrid('getPager');

	if (pager) {
		$(pager)
				.pagination(
						{
							pageSize : 10,//每页显示的记录条数，默认为10           
							//pageList: [5,10,15,20],//可以设置每页记录条数的列表           
							beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
							afterPageText : '<spring:message code="pagination.afterPageText"/>',
							displayMsg : '<spring:message code="pagination.displayMsg"/>',
							//选择页的处理
							onSelectPage : function(pageNumber, pageSize) {
								//按分页的设置取数据
								getData(pageNumber, pageSize);
								//设置表格的pageSize属性，表格变化时按分页组件设置的pageSize显示数据
								$('#TmsMdFlexStructuresListInSendForm').combogrid(
										"grid").datagrid('options').pageSize = pageSize;
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

	var getData = function(page, rows) {
		$.ajax({
			url : "${vat}/tmsMdFlexStructures/getResult.do",
			type : "POST",
			async : true,
			data : '', //不能直接写成 {id:"123",code:"tomcat"}  
			dataType : "json",
			// contentType: "charset=utf-8",  
			cache : false,
			success : function(result) {
				//clearSaveForm();
				$('#TmsMdFlexStructuresListInSendForm').combogrid('grid')
						.datagrid('loadData', result.data);
			}
		});
	};

	searchTaxEntityInDialod();//初始完后加载数据
}
	
function searchTaxEntityInDialod() {
	$.ajax({
		type : "POST",
		url : "${vat}/tmsMdFlexStructures/getResult.do",
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		},
		success : function(data) {
			var result = $.parseJSON(data);
			$('#TmsMdFlexStructuresListInSendForm').combogrid('grid').datagrid(
					'loadData', result.data);
		}
	});
}


function close(){
	
	clearSaveForm();
	$('#TmsMdFlexStructures_table').datagrid('close');	

}


function getChecked(){
	
	if ($('#TmsMdFlexStructuresInit_dataGrid').datagrid('getChecked').length > 1) {
		$.messager.alert("操作提示", "请选择一条数据进行修改");
	} else {
	//	
		var row_list = $('#TmsMdFlexStructuresInit_dataGrid').datagrid(
				'getChecked');
		if (row_list) {
			var id = '';
			$.each(row_list, function(index, item) {
				if (index == 0) {
					id = item.id;
				}
			});
				
			if (id != '') {
				$.post('${vat}/tmsMdFlexStructures/loadModifyTmsMdFlexStructures.do',
								{
									id : id
								},
								function(result) {
									if (result.status == '0') {
										//打开2级页面
									//	$("#layoutid").layout("expand","east");
																														
									//	$("#TmsMdFlexStructures_addOrEdit_dlg").dialog('open').dialog('setTitle',"<spring:message code='client.dialog.clientedit.title'/>");
										//2级页面上半部分数据绑定																						
								    $("#TmsMdFlexStructures_editAddform").form('load',result.data);
											
									    SearchTmsMdFlexSegments();     
										//2级页面发送拉取数据请求
									    TmsMdFlexStructuresEdit1();
										
									$("#TmsMdFlexStructures_editform").form('load',result.data);
									} else if (result.success == '1') {
										$.messager.alert('<spring:message code="system.alert"/>',result.errorMsg);
										SearchTmsMdFlexStructures();
								
									}
								}, 'json');
			} else {
				$.messager.alert('<spring:message code="system.alert"/>',
						'请选择需要编辑的数据');
			}
		}
	}

}


function orgFormat(val,row){
    if(val == "1"){
    	
    	return "会计";
    }else if(val == "2"){
    	
    	return "业务";
    }  
}

function orgFormat2(val,row){
	if(val == 'Y' || val == '1'){
		
		return "是";
	}else if(val == 'N' || val == '0'){
		
		return "否";
	}
	
}

//###################################获取值集名称 同步加载
/* var str;
function getFlexValueName(page, rows){  
str = null;
$("#TmsMdFlexStructures_table").datagrid("loading");
	if(page){
	 var pageNumber =	$("#pageNumber1").textbox("getValue");
	 var pageSize =	$("#pageSize1").textbox("getValue");
					$.ajax({
						type : "POST",
						url : "${vat}/tmsMdFlexValueSets/getName.do",
					     async: false,
						data :"flexValueSetId="+page+"&pageNumber="+pageNumber+"&pageSize="+"1", //不能直接写成 {id:"123",code:"tomcat"}  
						dataType : "json",
						error : function(XMLHttpRequest, textStatus, errorThrown) {
						},
						success : function(data) {
						str =	data.rows[0].flexValueSetName;
						
						}
					});
	}
//	alert(str);
	return page;	
//	flexValueSetId	
} */

function setFlexValueName(page, rows){ 
	
	page= str;
	   return page;
}

function getFlexValuePanl(){

		$('#flexsegments_flexValueSetId').combogrid(
						{
							required:true,
						    valueField: 'value',
							panelWidth : 500,
							idField : 'id', //ID字段  
							textField : 'flexValueSetName', //显示的字段  
							url : "",
							fitColumns : true,
							striped : true,
					//		pageSize:'10',
							editable : false,
							pagination : true, //是否分页
							rownumbers : true, //序号
							collapsible : false, //是否可折叠的
							fit : false, //自动大小
							method : 'post',
							columns : [ [ {
								field : 'flexValueSetCode',
								title : '值集编码',
								width : 200
							},{
								field : 'flexValueSetName',
								title : '值集名称',
								width : 300
							} ] ],
							toolbar : [
									{
										text : '值集:<input type="text" id="taxEntityId" class="easyui-textbox"/>'
									}, {
										text : "查询",
										iconCls : 'icon-search',
										handler : function() {
											findTaxEntity();
										}
									}, '-' ],
							keyHandler : {
								up : function() { //【向上键】押下处理
									//取得选中行
									var selected = $('#flexsegments_flexValueSetId').combogrid('grid').datagrid('getSelected');
									if (selected) {
										//取得选中行的rowIndex
										var index = $('#flexsegments_flexValueSetId').combogrid('grid').datagrid('getRowIndex',selected);
										//向上移动到第一行为止
										if (index > 0) {
											$('#flexsegments_flexValueSetId').combogrid('grid').datagrid('selectRow',index - 1);
										}
									} else {
										var rows = $('#flexsegments_flexValueSetId').combogrid('grid').datagrid('getRows');
										$('#flexsegments_flexValueSetId').combogrid('grid').datagrid('selectRow',rows.length - 1);
									}
								},
								down : function() { //【向下键】押下处理
									//取得选中行
									var selected = $('#flexsegments_flexValueSetId').combogrid('grid').datagrid('getSelected');
									if (selected) {
										//取得选中行的rowIndex
										var index = $('#flexsegments_flexValueSetId').combogrid('grid').datagrid('getRowIndex',selected);
										//向下移动到当页最后一行为止
										if (index < $('#flexsegments_flexValueSetId').combogrid('grid').datagrid('getData').rows.length - 1) {
											$('#flexsegments_flexValueSetId').combogrid('grid').datagrid('selectRow',index + 1);
										}
									} else {
										$('#flexsegments_flexValueSetId').combogrid('grid').datagrid('selectRow', 0);
									}
								},

							},

						});

		//取得分页组件对象
		var pager = $('#flexsegments_flexValueSetId').combogrid('grid').datagrid('getPager');

		if (pager) {
			$(pager).pagination(
							{
								pageSize : 10,//每页显示的记录条数，默认为10           
								//pageList: [5,10,15,20],//可以设置每页记录条数的列表           
								beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
								afterPageText : '<spring:message code="pagination.afterPageText"/>',
								displayMsg : '<spring:message code="pagination.displayMsg"/>',
								//选择页的处理
								onSelectPage : function(pageNumber, pageSize) {
									//按分页的设置取数据
									$('#getByBaseOrg').form('clear');
									$("#pageNumber2").textbox('setValue',pageNumber);
									$("#pageSize2").textbox('setValue',pageSize);										
									searchFlexValue();
								},
								//改变页显示条数的处理
						
				/* 				onChangePageSize : function(pageNumber, pageSize) {
									$("#pageNumber1").textbox('setValue',pageNumber);
									$("#pageSize1").textbox('setValue',pageSize);										
									searchBaseOrg();
								}, */
							
							onRefresh : function(pageNumber, pageSize) {
									//按分页的设置取数据
								$('#getByBaseOrg').form('clear');
								$("#pageNumber2").textbox('setValue',pageNumber);
								$("#pageSize2").textbox('setValue',pageSize);										
								searchFlexValue();
								}
							});
		}
		$('#getByBaseOrg').form('clear');
		$('#getByBaseOrg').form('load',
				{
					pageNumber : $('#flexsegments_flexValueSetId').combogrid("grid").datagrid('options').pageNumber,
					  pageSize : $('#flexsegments_flexValueSetId').combogrid("grid").datagrid('options').pageSize
				});
		searchFlexValue();
		$('#flexsegments_flexValueSetId').combogrid('grid').datagrid({
			onSelect: function(index,value){
				var selected = $('#flexsegments_flexValueSetId').combogrid('grid').datagrid('getSelected');
				$("#flexsegments_flexValueSetId_getId").textbox('setValue',selected.id);
			}
		});	
	}
		
	function searchFlexValue(){	
			$.ajax({
				url : "${vat}/tmsMdFlexValueSets/getName.do",
				type : "POST",
				async : true,
				data : "pageNumber="+$("#pageNumber2").textbox('getValue')+"&pageSize="+$("#pageSize2").textbox('getValue'), //不能直接写成 {id:"123",code:"tomcat"}  
				dataType : "json",
				// contentType: "charset=utf-8",  
				cache : false,
				success : function(result) {
					//clearSaveForm();
					$('#flexsegments_flexValueSetId').combogrid('grid').datagrid('loadData', result.rows);
				}
			});
	}
	






</script>
</html>