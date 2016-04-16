
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
		
		 <div  class="easyui-layout" style="width: 100%; height: 100%;">
		<div region="north" split="true" border="false" style="overflow: hidden; height: 24%;">
		<div class="easyui-panel" title="" style="width: 100%; height: 100%; background-color: #E0ECFF">
			<form id="TmsMdBsnCombination_searchform" method="post" scroll="no">
				<table id="TmsMdBsnCombination_searchform_table">	
			<tr>
			<!-- 
			<td>业务数据组合ID：</td>
			 <td><input id="idId" class="easyui-textbox" type="text" style="width: 150px;" name="id" value=""></input></td>
			
			<td>业务数据组合代码：</td>
			 <td><input id="bsnCombinationCodeId" class="easyui-textbox" type="text" style="width: 150px;" name="bsnCombinationCode" value=""></input></td>
			<td>业务数据组合说明：</td>
			 <td><input id="bsnCombinationNameId" class="easyui-textbox" type="text" style="width: 150px;" name="bsnCombinationName" value=""></input></td>
			<td>数据结构定义ID(业务)：</td>
			 <td><input id="flexStructuresIdId" class="easyui-textbox" type="text" style="width: 150px;" name="flexStructuresId" value=""></input></td>
			
			 -->
			 </tr> 
			<tr> 
			<td>业务字段1：</td>
			 <td><input id="transSegment1Id" class="easyui-textbox" type="text" style="width: 150px;" name="transSegment1" value=""></input></td>
			                             
				
			<td>业务字段2：</td>
			 <td><input id="transSegment2Id" class="easyui-textbox" type="text" style="width: 150px;" name="transSegment2" value=""></input></td>
			<td>业务字段3：</td>
			 <td><input id="transSegment3Id" class="easyui-textbox" type="text" style="width: 150px;" name="transSegment3" value=""></input></td>
			<td>业务字段4：</td>
			 <td><input id="transSegment4Id" class="easyui-textbox" type="text" style="width: 150px;" name="transSegment4" value=""></input></td>
				</tr>                              		
			<tr>
			<td>业务字段5：</td>
			 <td><input id="transSegment15Id" class="easyui-textbox" type="text" style="width: 150px;" name="transSegment5" value=""></input></td>
			
			<td>业务字段6：</td>
			 <td><input id="transSegment16Id" class="easyui-textbox" type="text" style="width: 150px;" name="transSegment6" value=""></input></td>
			<td>业务字段7：</td>
			 <td><input id="transSegment17Id" class="easyui-textbox" type="text" style="width: 150px;" name="transSegment7" value=""></input></td>
			<td>业务字段8：</td>
			 <td><input id="transSegment18Id" class="easyui-textbox" type="text" style="width: 150px;" name="transSegment8" value=""></input></td>
			</tr>                              
			<tr>
			<td>业务字段9：</td>
			 <td><input id="transSegment19Id" class="easyui-textbox" type="text" style="width: 150px;" name="transSegment9" value=""></input></td>		
			<td>业务字段10：</td>
			 <td><input id="transSegment110Id" class="easyui-textbox" type="text" style="width: 150px;" name="transSegment10" value=""></input></td>
			
			<!-- 
			
			<td>纳税主体ID：</td>
			 <td><input id="legalEntityIdId" class="easyui-textbox" type="text" style="width: 150px;" name="legalEntityId" value=""></input></td>
		 -->
			<td>是否启用：</td>
		<!--  <td><input id="enabledFlagId" class="easyui-textbox" type="text" style="width: 150px;" name="enabledFlag" value=""></input></td>  -->	
			 <td>	
			 <input:select easyuiStyle="width:150px;" id="enabledFlagId" name="enabledFlag" value="$customerManageInit_searchform_isAppointInvoice" easyuiClass="easyui-combobox" >
             <option  value=""></option>
             <input:systemStatusOption parentCode="BASE_IS_VALID"/>
			 </input:select>	
		     </td>
				
				<!-- 			
				<td>备注：</td>
			 <td><input id="descriptionId" class="easyui-textbox" type="text" style="width: 150px;" name="description" value=""></input></td>
			 -->
		
			
			<!-- 
			<td>组织ID：</td>
			 <td><input id="orgIdId" class="easyui-textbox" type="text" style="width: 150px;" name="orgId" value=""></input></td>
			 -->
			
			<td>有效日期：</td>
			<td><input id="startDateId" class="easyui-datebox" type="text" style="width: 90;" name="startDate" value=""></input></td>
			</tr>                              		
			<tr>
		
			<td>失效日期：</td>
			<td><input id="endDateId" class="easyui-datebox" type="text" style="width: 90;" name="endDate" value=""></input></td>
			
						<td colspan="2">
						<!-- 	<div style="text-align: center; padding: 10px"></div> -->
								<a href="#" id="searchbtnTmsMdBsnCombination" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 120px" onclick="SearchTmsMdBsnCombination()"><spring:message code='button.Search' /></a>
							
						</td>
						
						<td>
						<a href="#" id="ClearbtnTmsMdOrgParameter" class="easyui-linkbutton"  style="width: 80px;" onclick="clearSaveFormT()">重置</a>
						</td>
					</tr>
					</table><table>
					<tr style="display: none">
						<td><input id="pageNumber" class="easyui-textbox" type="text" style="width: 0px;" name="pageNumber" value=""></input></td>
						<td><input id="pageSize" class="easyui-textbox" type="text" style="width: 0px;" name="pageSize" value=""></input></td>
					</tr>
				</table>
			</form>
		</div>
		</div>
		
		<div region="center" split="true" border="false" style="overflow: hidden; height: 76%;">
		<div style="width: 100%; height: 100%">
			<table class="easyui-datagrid" id="TmsMdBsnCombinationInit_dataGrid" title="<spring:message code='TmsMdBsnCombination.title'/>" style="width: 100%; height: 100%" data-options="singleSelect:true,autoRowHeight:false,pagination:true,pageSize:10,remoteSort:false,multiSort:true">
			</table>
		</div>
		</div>
		
		</div>
	</div>
	
	
	
	<div data-options="region:'center',border:false"
		style="background-color: #E0ECFF">
		<div id="TmsMdBsnCombination_addOrEdit_dlg" class="easyui-dialog"
			style="width: 800px; height: 500px;" closed="true"
			buttons="#TmsMdBsnCombination_addOrEdit_dlg_dlg-buttons">
			<form id="TmsMdBsnCombination_editform" class="easyui-form" method="post"
				data-options="novalidate:true" commandsName="clientManageForm">
				<table>
					<tr>
						<td style="display: none;"><spring:message code="TmsMdBsnCombination.id" />：</td>
						<td style="display: none;"><input id="idInDialog" name="id" style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td><spring:message code="TmsMdBsnCombination.bsnCombinationCode" />：</td>
						<td><input id="bsnCombinationCodeInDialog" name="bsnCombinationCode" style="width: 150px;" class="easyui-textbox" type="text"></td>
				<!--  
					</tr>                              
					<tr>
				-->
						<td><spring:message code="TmsMdBsnCombination.bsnCombinationName" />：</td>
						<td><input id="bsnCombinationNameInDialog" name="bsnCombinationName" style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td  style="display: none;"><spring:message code="TmsMdBsnCombination.flexStructuresId" />：</td>
						<td  style="display: none;"><input id="flexStructuresIdInDialog" name="flexStructuresId" style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>                              
					<tr>
						<td><spring:message code="TmsMdBsnCombination.transSegment1" />：</td>
						<td><input id="transSegment1InDialog" name="transSegment1" style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td><spring:message code="TmsMdBsnCombination.transSegment2" />：</td>
						<td><input id="transSegment2InDialog" name="transSegment2" style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>                              
					<tr>
						<td><spring:message code="TmsMdBsnCombination.transSegment3" />：</td>
						<td><input id="transSegment3InDialog" name="transSegment3" style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td><spring:message code="TmsMdBsnCombination.transSegment4" />：</td>
						<td><input id="transSegment4InDialog" name="transSegment4" style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>                              
					<tr>
						<td><spring:message code="TmsMdBsnCombination.transSegment15" />：</td>
						<td><input id="transSegment15InDialog" name="transSegment5" style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td><spring:message code="TmsMdBsnCombination.transSegment16" />：</td>
						<td><input id="transSegment16InDialog" name="transSegment6" style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>                              
					<tr>
						<td><spring:message code="TmsMdBsnCombination.transSegment17" />：</td>
						<td><input id="transSegment17InDialog" name="transSegment7" style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td><spring:message code="TmsMdBsnCombination.transSegment18" />：</td>
						<td><input id="transSegment18InDialog" name="transSegment8" style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>                              
					<tr>
						<td  ><spring:message code="TmsMdBsnCombination.transSegment19" />：</td>
						<td ><input id="transSegment19InDialog" name="transSegment9" style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td><spring:message code="TmsMdBsnCombination.transSegment110" />：</td>
						<td><input id="transSegment110InDialog" name="transSegment10" style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>                              
					<tr>
						<td  style="display: none;"><spring:message code="TmsMdBsnCombination.legalEntityId" />：</td>
						<td   style="display: none;"><input id="legalEntityIdInDialog" name="legalEntityId" style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td><spring:message code="TmsMdBsnCombination.enabledFlag" />：</td>
					    <td>    <input:select easyuiStyle="width:150px;" id="enabledFlagInDialog" name="enabledFlag" value="$customerManageInit_searchform_isAppointInvoice" easyuiClass="easyui-combobox" >
                            <option  value=""></option>
                            <input:systemStatusOption parentCode="BASE_IS_VALID"/>
			                </input:select>
				</td>			
						<td><spring:message code="TmsMdBsnCombination.description" />：</td>
						<td><input id="descriptionInDialog" name="description" style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td style="display: none;"><spring:message code="TmsMdBsnCombination.orgId" />：</td>
						<td style="display: none;"><input id="orgIdInDialog" name="orgId" style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>                              
					<tr>
						<td><spring:message code="TmsMdBsnCombination.startDate" />：</td>
						<td><input id="startDateInDialog" name="startDate" style="width: 150px;" class="easyui-datebox" type="text"></td>
						<td><spring:message code="TmsMdBsnCombination.endDate" />：</td>
						<td><input id="endDateInDialog" name="endDate" style="width: 150px;" class="easyui-datebox" type="text"></td>
					</tr>                              
				</table>
			</form>
		</div>
		<div id="TmsMdBsnCombination_addOrEdit_dlg_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="TmsMdBsnCombinationEditSave()"><spring:message code="button.Save" /></a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#TmsMdBsnCombination_addOrEdit_dlg').dialog('close')"><spring:message code="button.Close" /></a>
		</div>
		<div id="history_dlg" class="easyui-dialog" style="width: 800px; height: 500px;" closed="true">
			<table class="easyui-datagrid" id="history_dataGrid" title="<spring:message code='TmsMdBsnCombination.title'/>" style="width: 100%; height: 88%" data-options="singleSelect:true,autoRowHeight:false,remoteSort:false,multiSort:true">
			</table>
		</div>
	</div>
</body>
<script type="text/javascript">
//选中文本框按Backspace禁止返回上一格页面
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


$(document).ready(
	function() {
		$('#TmsMdBsnCombination_searchform').form('load',
			{
				pageNumber : $('#TmsMdBsnCombinationInit_dataGrid').datagrid('options').pageNumber,
				pageSize : $('#TmsMdBsnCombinationInit_dataGrid').datagrid('options').pageSize
			});
			SearchTmsMdBsnCombination();
		});
	$(function() {
		$('#TmsMdBsnCombinationInit_dataGrid')
				.datagrid(
						{
							url : '',
							fitColumns : true,
							nowrap : false,
							pagination : true,
							rownumbers : true,
							singleSelect : false,
							nowrap:true,//超出文本框截掉	
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
									//	title : '<spring:message code="TmsMdBsnCombination.id"/>',
									//	width : 80,
									//	align : 'center'
									//}                           
									,{
										field : 'bsnCombinationCode',
										title : '<spring:message code="TmsMdBsnCombination.bsnCombinationCode"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'bsnCombinationName',
										title : '<spring:message code="TmsMdBsnCombination.bsnCombinationName"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'flexStructuresId',
										title : '<spring:message code="TmsMdBsnCombination.flexStructuresId"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'transSegment1',
										title : '<spring:message code="TmsMdBsnCombination.transSegment1"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'transSegment2',
										title : '<spring:message code="TmsMdBsnCombination.transSegment2"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'transSegment3',
										title : '<spring:message code="TmsMdBsnCombination.transSegment3"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'transSegment4',
										title : '<spring:message code="TmsMdBsnCombination.transSegment4"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'transSegment5',
										title : '<spring:message code="TmsMdBsnCombination.transSegment15"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'transSegment6',
										title : '<spring:message code="TmsMdBsnCombination.transSegment16"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'transSegment7',
										title : '<spring:message code="TmsMdBsnCombination.transSegment17"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'transSegment8',
										title : '<spring:message code="TmsMdBsnCombination.transSegment18"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'transSegment9',
										title : '<spring:message code="TmsMdBsnCombination.transSegment19"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'transSegment10',
										title : '<spring:message code="TmsMdBsnCombination.transSegment110"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'legalEntityId',
										title : '<spring:message code="TmsMdBsnCombination.legalEntityId"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'enabledFlag',
										title : '<spring:message code="TmsMdBsnCombination.enabledFlag"/>',
										width : 80,
										align : 'center',
									 formatter:orgFormat2
									}                           
									,{
										field : 'description',
										title : '<spring:message code="TmsMdBsnCombination.description"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'orgName',
										title : '组织名称',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'startDate',
										title : '<spring:message code="TmsMdBsnCombination.startDate"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'endDate',
										title : '<spring:message code="TmsMdBsnCombination.endDate"/>',
										width : 80,
										align : 'center'
									}                           
									] ],
							toolbar : [ {
								text : '新增',
								iconCls : 'icon-add',
								handler : function() {
									mustWrite();
									TmsMdBsnCombinationAdd();
								}
							}, '-', {
								text : '编辑',
								iconCls : 'icon-edit',
								handler : function() {
									mustWrite();
									TmsMdBsnCombinationEdit();
								}
							}, '-', {
								text : '删除',
								iconCls : 'icon-remove',
								handler : function() {
									TmsMdBsnCombinationRemove();
								}
							}],
							onLoadSuccess : function() {
								$('#TmsMdBsnCombinationInit_dataGrid').datagrid(
										'clearSelections')
							},
							onClickRow : function(index, data) {
								var row = $('#TmsMdBsnCombinationInit_dataGrid')
										.datagrid('getSelected');
								if (row) {
									loadSaveFormData(row);
								}
							}
						});
		//设置分页控件	
		var p = $('#TmsMdBsnCombinationInit_dataGrid').datagrid('getPager');
		$(p).pagination(
		{
			pageSize : 10,//每页显示的记录条数，默认为10           
			//pageList: [10,20,30],//可以设置每页记录条数的列表           
			beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
			afterPageText : '<spring:message code="pagination.afterPageText"/>',
			displayMsg : '<spring:message code="pagination.displayMsg"/>',
			onSelectPage : function(pageNumber, pageSize) {//分页触发		
				find(pageNumber, pageSize);
				SearchTmsMdBsnCombination();
			}
		});
	});
	function find(pageNumber, pageSize) {
		$('#TmsMdBsnCombination_searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		SearchTmsMdBsnCombination();
	}
	function SearchTmsMdBsnCombination() {
		$("#TmsMdBsnCombinationInit_dataGrid").datagrid("loading");
		$('#TmsMdBsnCombination_searchform').form('submit', {
			url : '${vat}/tmsMdBsnCombination/loadTmsMdBsnCombinationPage.do',
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(result) {
				//数据加载以及绑定
				var result = $.parseJSON(result);
				$("#TmsMdBsnCombinationInit_dataGrid").datagrid('loadData', result);
				$("#TmsMdBsnCombinationInit_dataGrid").datagrid("loaded");
			}
		});
	}
	function getHistory() {
		//	alert(0);
		//$("#TmsMdBsnCombinationInit_dataGrid").datagrid("loading");
		$('#TmsMdBsnCombination_searchform').form('submit', {
			url : '${vat}/tmsMdBsnCombination/loadhistory.do',
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
	function searchTmsMdBsnCombination() {
		$("#history_dlg").dialog('open').dialog('setTitle',"<spring:message code='TmsMdBsnCombination.history'/>");
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
	function TmsMdBsnCombinationAdd() {
		clearSaveForm();
		$("#TmsMdBsnCombination_addOrEdit_dlg").dialog('open').dialog('setTitle',"<spring:message code='client.dialog.clientadd.title'/>");
		$("#TmsMdBsnCombination_editform").form('load', {
			clientDate : myformatter(new Date())
		});
	}
	function TmsMdBsnCombinationEdit() {
		if ($('#TmsMdBsnCombinationInit_dataGrid').datagrid('getChecked').length > 1) {
			$.messager.alert("操作提示", "请选择一条数据进行修改");
		} else {
			clearSaveForm();
			var row_list = $('#TmsMdBsnCombinationInit_dataGrid').datagrid('getChecked');
			if (row_list) {
				var id = '';
				$.each(row_list, function(index, item) {
					if (index == 0) {
						id = item.id;
					}
				});
				if (id != '') {
					$.post('${vat}/tmsMdBsnCombination/loadModifyTmsMdBsnCombination.do',
						{
							id : id
						},
						function(result) {
							if (result.status == '0') {
								$("#TmsMdBsnCombination_addOrEdit_dlg").dialog('open').dialog('setTitle',"<spring:message code='client.dialog.clientedit.title'/>");
								$("#TmsMdBsnCombination_editform").form('load',result.data);
							} else if (result.success == '1') {
								$.messager.alert('<spring:message code="system.alert"/>',result.errorMsg);
								SearchTmsMdBsnCombination();
								clearSaveForm();
								$('#TmsMdBsnCombinationInit_dataGrid').dialog('close');
							}
						}, 'json');
				} else {
					$.messager.alert('<spring:message code="system.alert"/>','请选择需要编辑的数据');
				}
			}
		}
	}
	function TmsMdBsnCombinationEditSave() {
	
		$.messager.confirm("<spring:message code='client.datacommit.formsave.confirm.title'/>","<spring:message code='client.datacommit.formsave.confirm.text'/>",
		function(result) {
			if (result) {
				$('#TmsMdBsnCombination_editform').form(
				'submit',
				{
					url : '${vat}/tmsMdBsnCombination/saveTmsMdBsnCombination.do',
					onSubmit : function() {
						return $(this).form('enableValidation').form('validate');
					},
					success : function(result) {
						var result = $.parseJSON(result);
						if (result.status == '0') {
							clearSaveForm();
							SearchTmsMdBsnCombination();
							$('#TmsMdBsnCombination_addOrEdit_dlg').dialog('close');
							$.messager.alert('<spring:message code="system.alert"/>','保存成功');
						}

					}
				});
			}
		});

	}
	//删除一条记录

	function TmsMdBsnCombinationRemove() {
		if (($('#TmsMdBsnCombinationInit_dataGrid').datagrid('getChecked').length == 0)
				|| ($('#TmsMdBsnCombinationInit_dataGrid').datagrid('getChecked').length > 1)) {
			$.messager.alert("操作提示", "请选择需要删除的数据");
		} else {
			$.messager.confirm("<spring:message code='client.datacommit.delete.confirm.title'/>","<spring:message code='client.datacommit.delete.confirm.text'/>",
			function(result) {
				if (result) {
					var row_list = $(
							'#TmsMdBsnCombinationInit_dataGrid')
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
										url : "${vat}/tmsMdBsnCombination/removeTmsMdBsnCombinations.do",
										type : "POST",
										async : true,
										data : urlString, //不能直接写成 {id:"123",code:"tomcat"}  
										dataType : "json",
										// contentType: "charset=utf-8",  
										cache : false,
										success : function(
												result) {
											SearchTmsMdBsnCombination();
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
		$('#TmsMdBsnCombination_editform').form('clear');
	}
	
	
	function clearSaveFormT() {
		$('#TmsMdBsnCombination_searchform_table').form('clear');
	}
	
	
	function orgFormat2(val,row){
		if(val == 'Y' || val == '1'){
			
			return "是";
		}else if(val == 'N' || val == '0'){
			
			return "否";
		}
		
	}
	
	function mustWrite(){
		
		$("#enabledFlagInDialog").combobox({
			panelHeight:'auto',//高度自使用
			 required:true,
			valueField: 'value',
			textField: 'text',
			editable:false,
		});
	}
	
	
	
</script>
</html>