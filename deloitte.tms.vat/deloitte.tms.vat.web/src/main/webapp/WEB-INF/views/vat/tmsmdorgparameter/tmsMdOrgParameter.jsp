
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/common/global.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<div region="north" split="true" border="false" style="overflow: hidden; height: 22%;">
		<div class="easyui-panel" title="<spring:message code='searchgroup'/>" style="width: 100%; height: 100%; background-color: #E0ECFF">
			<form id="TmsMdOrgParameter_searchform" method="post" scroll="no">
				<table id= "TmsMdOrgParameter_searchtable">	
			<tr>
			<!-- 
			<td>业务参数设置ID：</td>
			 <td><input id="idId" class="easyui-textbox" type="text" style="width: 150px;" name="id" value=""></input></td>
			  -->
			<td  align="right">参数类型：</td>
		<!-- 	 <td ><input id="orgParameterTypeId" class="easyui-textbox" type="text" name="orgParameterType" value=""></input></td>   -->
		    <td><input:select  easyuiStyle="width:150px;" id="orgParameterTypeId" name="orgParameterType" value="$customerManageInit_searchform_custLegalEntityType" easyuiClass="easyui-combobox">
            	<option  value=""></option>
				</input:select>
			</td>
			
			<td  align="right">组织参数：</td>
			 <td><input id="orgParameterId" class="easyui-textbox" type="text" name="orgParameter" value=""></input></td>
			<td  align="right">参数值：</td>
			 <td><input id="orgParameterValueId" class="easyui-textbox" type="text" name="orgParameterValue" value=""></input></td>
		     <td align="right">是否有效：</td>
		     <td> <input:select easyuiStyle="width:150px;required:true" id="enabledFlagId" name="enabledFlag" value="$customerManageInit_editform_enabledFlag" easyuiClass="easyui-combobox" >           						 
            		<option value=""></option>
            		<input:systemStatusOption parentCode="BASE_IS_VALID"/>				
			 </input:select> </td> 
			
			</tr>                              
			<tr>
			<td  align="right">有效日期：</td>
			<td><input id="startDateId" class="easyui-datebox"  name="startDate" ></input></td>
			<td  align="right">失效日期：</td>
			<td><input id="endDateId" class="easyui-datebox"  name="endDate" ></input></td>
			</tr>   
			<tr>
			<td></td>
			<td></td>
			       <td colspan="1">
					<!-- 		<div style="text-align: center; padding: 10px">	</div>    -->
						<a href="#" id="searchbtnTmsMdOrgParameter" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 60px" onclick="SearchTmsMdOrgParameter()"><spring:message code='client.search.button.find'/></a>
						</td>
						
					<td colspan="1">
					<a href="#" id="ClearbtnTmsMdOrgParameter" class="easyui-linkbutton"  style="width: 60px" onclick="clearSaveFormTwo()">重置</a>
						</td>			
			           </tr>                
					</table>
					<table>
					<tr style="display: none">
						<td><input id="pageNumber" class="easyui-textbox" type="text" style="width: 0px;" name="pageNumber" value=""></input></td>
						<td><input id="pageSize" class="easyui-textbox" type="text" style="width: 0px;" name="pageSize" value=""></input></td>
					</tr>
				</table>
			</form>
		</div>
		
		</div>
		<div data-options="region:'center',border:false" style="background-color: #E0ECFF;width: 100%; height:78%" >

			<table class="easyui-datagrid" id="TmsMdOrgParameterInit_dataGrid" 
			   title="<spring:message code='TmsMdOrgParameter.title'/>"
			                style="width: 100%; height:100%" 
			                data-options="
			                singleSelect:true,
			                autoRowHeight:true,
			                pagination:true,
			                pageSize:10,
			                remoteSort:false,
			                multiSort:true
			                ">
			</table>
		

	
		<div id="TmsMdOrgParameter_addOrEdit_dlg" class="easyui-dialog"
			style="width: 800px; height: 500px;" closed="true"
			buttons="#TmsMdOrgParameter_addOrEdit_dlg_dlg-buttons">
			<form id="TmsMdOrgParameter_editform" class="easyui-form" method="post"
				data-options="novalidate:true" commandsName="clientManageForm">
				<table>
					<tr>
						<td style="display: none;"><spring:message code="TmsMdOrgParameter.id" />：</td>
						<td style="display: none;"><input id="idInDialog" name="id" style="width: 150px;" class="easyui-textbox" type="text"></td>
						
						<td>组织：</td>
						<td><input id="orgNameId" name="orgName" style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td><spring:message code="TmsMdOrgParameter.orgParameterType" />：</td>
						<td><input:select easyuiStyle="width:150px;" id="orgParameterId" name="orgParameter" value="$customerManageInit_searchform_isAppointInvoice" easyuiClass="easyui-combobox" >
            				<option  value=""></option><input:systemStatusOption parentCode="BASE_SYSTEM_GLOBAL_PARAMETER"/></input:select></td>
					</tr>                             
					<tr>
						
						<td><spring:message code="TmsMdOrgParameter.orgParameterValue" />：</td>
						<td><input id="orgParameterValueInDialog" name="orgParameterValue" style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>                              
					<tr>
						<td><spring:message code="TmsMdOrgParameter.description" />：</td>
						<td><input id="descriptionInDialog" name="description" style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td><spring:message code="TmsMdOrgParameter.enabledFlag" />：</td>
						<td><input id="enabledFlagInDialog" name="enabledFlag" style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>                              
					<tr>
						<td><spring:message code="TmsMdOrgParameter.startDate" />：</td>
						<td><input id="startDateInDialog" name="startDate" style="width: 150px;" class="easyui-datebox" type="text"></td>
						<td><spring:message code="TmsMdOrgParameter.endDate" />：</td>
						<td><input id="endDateInDialog" name="endDate" style="width: 150px;" class="easyui-datebox" type="text"></td>
					</tr>                              
				</table>
			</form>
		</div>
		<div id="TmsMdOrgParameter_addOrEdit_dlg_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="TmsMdOrgParameterEditSave()"><spring:message code="button.Save" /></a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#TmsMdOrgParameter_addOrEdit_dlg').dialog('close')"><spring:message code="button.Close" /></a>
		</div>
		<div id="history_dlg" class="easyui-dialog" style="width: 800px; height: 500px;" closed="true">
			<table class="easyui-datagrid" id="history_dataGrid" title="<spring:message code='TmsMdOrgParameter.title'/>" style="width: 100%; height: 88%" data-options="singleSelect:true,autoRowHeight:false,remoteSort:false,multiSort:true">
			</table>
		</div>
	</div>
</body>
<script type="text/javascript">
$(document).ready(
	function() {
		$('#TmsMdOrgParameter_searchform').form('load',
			{
				pageNumber : $('#TmsMdOrgParameterInit_dataGrid').datagrid('options').pageNumber,
				pageSize : $('#TmsMdOrgParameterInit_dataGrid').datagrid('options').pageSize
			});
			SearchTmsMdOrgParameter();
			addSelect();	
		});
	$(function() {
		$('#TmsMdOrgParameterInit_dataGrid')
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
								//	,{
								//		field : 'id',
								//		title : '<spring:message code="TmsMdOrgParameter.id"/>',
								//		width : 80,
								//		align : 'center'
								//	}                           
									,{
										field : 'orgName',
										title : '组织',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'orgParameter',
										title : '配置文件名称',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'orgParameterValue',
										title : '参数值',
										width : 80,
										align : 'center'
									}                                                      
									,{
										field : 'enabledFlag',
										title : '<spring:message code="TmsMdOrgParameter.enabledFlag"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'startDate',
										title : '<spring:message code="TmsMdOrgParameter.startDate"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'endDate',
										title : '<spring:message code="TmsMdOrgParameter.endDate"/>',
										width : 80,
										align : 'center'
									},{
										field : 'createPer',
										title : '创建人',
										width : 80,
										align : 'center'
									}
									,{
										field : 'lastWritePer',
										title : '最后修改人',
										width : 80,
										align : 'center'
									}
									,{
										field : 'lastWriteDate',
										title : '最后修改日期',
										width : 80,
										align : 'center'
									}
									] ],
							toolbar : [ {
								text : '新增',
								iconCls : 'icon-add',
								handler : function() {
									TmsMdOrgParameterAdd();
								}
							}, '-', {
								text : '编辑',
								iconCls : 'icon-edit',
								handler : function() {
									TmsMdOrgParameterEdit();
								}
							}, '-', {
								text : '删除',
								iconCls : 'icon-remove',
								handler : function() {
									TmsMdOrgParameterRemove();
								}
							}],
							onLoadSuccess : function() {
								$('#TmsMdOrgParameterInit_dataGrid').datagrid(
										'clearSelections')
							},
							onClickRow : function(index, data) {
								var row = $('#TmsMdOrgParameterInit_dataGrid')
										.datagrid('getSelected');
								if (row) {
									loadSaveFormData(row);
								}
							}
						});
		//设置分页控件	
		var p = $('#TmsMdOrgParameterInit_dataGrid').datagrid('getPager');
		
		$(p).pagination(
		{
			pageSize : 10,//每页显示的记录条数，默认为10           
			//pageList: [10,20,30],//可以设置每页记录条数的列表           
			beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
			afterPageText : '<spring:message code="pagination.afterPageText"/>',
			displayMsg : '<spring:message code="pagination.displayMsg"/>',
			onSelectPage : function(pageNumber, pageSize) {//分页触发		
				find(pageNumber, pageSize);
				SearchTmsMdOrgParameter();
			}
		});
	});
	function find(pageNumber, pageSize) {
		$('#TmsMdOrgParameter_searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		SearchTmsMdOrgParameter();
	}
	function SearchTmsMdOrgParameter() {
		$("#TmsMdOrgParameterInit_dataGrid").datagrid("loading");		  
		$('#TmsMdOrgParameter_searchform').form('submit', {
			url : '${vat}/tmsMdOrgParameter/loadTmsMdOrgParameterPage.do',
			onSubmit : function(data) {
			
				return $(this).form('enableValidation').form('validate');
			},
			success : function(result) {
				//数据加载以及绑定
				var result = $.parseJSON(result);
				$("#TmsMdOrgParameterInit_dataGrid").datagrid('loadData', result);
				$("#TmsMdOrgParameterInit_dataGrid").datagrid("loaded");
			}
		});
	}
	function getHistory() {
		//	alert(0);
		//$("#TmsMdOrgParameterInit_dataGrid").datagrid("loading");
		$('#TmsMdOrgParameter_searchform').form('submit', {
			url : '${vat}/tmsMdOrgParameter/loadhistory.do',
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
	function searchTmsMdOrgParameter() {
		$("#history_dlg").dialog('open').dialog('setTitle',"<spring:message code='TmsMdOrgParameter.history'/>");
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
	function TmsMdOrgParameterAdd() {
		clearSaveForm();
		$("#TmsMdOrgParameter_addOrEdit_dlg").dialog('open').dialog('setTitle',"<spring:message code='client.dialog.clientadd.title'/>");
		$("#TmsMdOrgParameter_editform").form('load', {
			clientDate : myformatter(new Date())
		});
	}
	function TmsMdOrgParameterEdit() {
		if ($('#TmsMdOrgParameterInit_dataGrid').datagrid('getChecked').length > 1) {
			$.messager.alert("操作提示", "请选择一条数据进行修改");
		} else {
			clearSaveForm();
			var row_list = $('#TmsMdOrgParameterInit_dataGrid').datagrid('getChecked');
			if (row_list) {
				var id = '';
				$.each(row_list, function(index, item) {
					if (index == 0) {
						id = item.id;
					}
				});
				if (id != '') {
					$.post('${vat}/tmsMdOrgParameter/loadModifyTmsMdOrgParameter.do',
						{
							id : id
						},
						function(result) {
							if (result.status == '0') {
								$("#TmsMdOrgParameter_addOrEdit_dlg").dialog('open').dialog('setTitle',"<spring:message code='client.dialog.clientedit.title'/>");
								$("#TmsMdOrgParameter_editform").form('load',result.data);
							} else if (result.success == '1') {
								$.messager.alert('<spring:message code="system.alert"/>',result.errorMsg);
								SearchTmsMdOrgParameter();
								clearSaveForm();
								$('#TmsMdOrgParameterInit_dataGrid').dialog('close');
							}
						}, 'json');
				} else {
					$.messager.alert('<spring:message code="system.alert"/>','请选择需要编辑的数据');
				}
			}
		}
	}
	function TmsMdOrgParameterEditSave() {
	
		$.messager.confirm("<spring:message code='client.datacommit.formsave.confirm.title'/>","<spring:message code='client.datacommit.formsave.confirm.text'/>",
		function(result) {
		
			if (result) {
				$('#TmsMdOrgParameter_editform').form(
				'submit',
				{
					url : '${vat}/tmsMdOrgParameter/saveTmsMdOrgParameter.do',
					onSubmit : function() {
						return $(this).form('enableValidation').form('validate');
					},
					success : function(result) {
			
							clearSaveForm();
							SearchTmsMdOrgParameter();
							$('#TmsMdOrgParameter_addOrEdit_dlg').dialog('close');
							$.messager.alert('<spring:message code="system.alert"/>','保存成功');
						

					}
				});
			}
		});

	}
	//删除一条记录

	function TmsMdOrgParameterRemove() {
		if (($('#TmsMdOrgParameterInit_dataGrid').datagrid('getChecked').length == 0)
				|| ($('#TmsMdOrgParameterInit_dataGrid').datagrid('getChecked').length > 1)) {
			$.messager.alert("操作提示", "请选择需要删除的数据");
		} else {
			$.messager.confirm("<spring:message code='client.datacommit.delete.confirm.title'/>","<spring:message code='client.datacommit.delete.confirm.text'/>",
			function(result) {
				if (result) {
					var row_list = $(
							'#TmsMdOrgParameterInit_dataGrid')
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
										url : "${vat}/tmsMdOrgParameter/removeTmsMdOrgParameters.do",
										type : "POST",
										async : true,
										data : urlString, //不能直接写成 {id:"123",code:"tomcat"}  
										dataType : "json",
										// contentType: "charset=utf-8",  
										cache : false,
										success : function(
												result) {
											SearchTmsMdOrgParameter();
											clearSaveForm();
										}
									});
						}
					}
				}

			});
		}
	}

	function loadSaveFormData(row) {
		$('#status').combobox('setValue', row.status);
		$('#TmsMdOrgParameter_searchform').form('load', {
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
	
	
	
	function addSelect(){
		
		$('#orgParameterTypeId').combobox(
				{
					valueField: 'value',
					textField: 'text',
					data: [{
						text: '全局',
						value: '全局'
					},{
						text: '组织',
						value: '组织'
					}]
					
				});
	}
	
	
	
	
	//清空dialog内容

	function clearSaveForm() {
		$('#TmsMdOrgParameter_editform').form('clear');
	}
	function clearSaveFormTwo() {
		$('#TmsMdOrgParameter_searchtable').form('clear');
	}
	
	
</script>
</html>