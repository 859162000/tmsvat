
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
			<form id="TmsMdOrgBsnStructures_searchform" method="post" scroll="no">
				<table id= "TmsMdOrgBsnStructures_searchtable">	
		              
			<tr >
			<td style="display: none;">成本中心：</td>
			 <td style="display: none;"><input id="costCenterId" class="easyui-textbox" type="text" style="width: 150px;" name="costCenter" value=""></input></td>
			<td style="display: none;">来源科目结构：</td>
			 <td style="display: none;"><input id="accdflexStructuresCodeId" class="easyui-textbox" type="text" style="width: 150px;" name="accdflexStructuresCode" value=""></input></td>			
			<td>来源数据结构编码：</td>
			<td style="display: none;"><input id="bsnflexStructuresIdId" class="easyui-textbox" type="text" style="width: 150px;" name="bsnFlexStructuresId" value=""></input></td>
			<td><input id="bsnflexStructuresCodeId" class="easyui-combogrid" type="text" style="width: 90;" name="bsnflexStructuresCode" value=""></input></td>
		    </tr>
			<tr>
			<td></td>
			<td></td>
			<td></td>
			<td colspan="1" align="right">								
			<a href="#" id="searchbtnTmsMdOrgBsnStructures" class="easyui-linkbutton" data-options="iconCls:'icon-search'"  style="width: 80px;"  onclick="SearchTmsMdOrgBsnStructures()"><spring:message code='client.search.button.find'/></a>								
			</td>
			<td colspan="1"><div>
			<a href="#" id="ClearbtnTmsMdOrgParameter" class="easyui-linkbutton"  style="width: 80px;" onclick="clearSaveFormTwo()">重置</a>
			</div></td>
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
		<div style="width: 100%; height: 100%">
			<table class="easyui-datagrid" id="TmsMdOrgBsnStructuresInit_dataGrid" title="<spring:message code='TmsMdOrgBsnStructures.title'/>" style="width: 100%; height: 78%" data-options="singleSelect:true,autoRowHeight:false,pagination:true,pageSize:10,remoteSort:false,multiSort:true">
			</table>
		</div>
	</div>
	<div data-options="region:'center',border:false"
		style="background-color: #E0ECFF">
		<div id="TmsMdOrgBsnStructures_addOrEdit_dlg" class="easyui-dialog"
			style="width: 800px; height: 500px;" closed="true"
			buttons="#TmsMdOrgBsnStructures_addOrEdit_dlg_dlg-buttons">
			<form id="TmsMdOrgBsnStructures_editform" class="easyui-form" method="post"
				data-options="novalidate:true" commandsName="clientManageForm">
				<table>
					<tr style="display: none;">
						<td ><spring:message code="TmsMdOrgBsnStructures.id" />：</td>
						<td ><input id="idInDialog" name="id" style="width: 150px;" class="easyui-textbox" type="text"></td>
						<!-- 组织ID非常重要 -->
						<td ><spring:message code="TmsMdOrgBsnStructures.orgId" />：</td>
						<td><input id="orgIdInDialog" name="orgId" style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>                              
					<tr style="display: none;">
						<td><spring:message code="TmsMdOrgBsnStructures.accdFlexStructuresId" />：</td>
						<td><input id="accdFlexStructuresIdInDialog" name="accdFlexStructuresId" style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td><spring:message code="TmsMdOrgBsnStructures.legalEntityId" />：</td>
						<td><input id="legalEntityIdInDialog" name="legalEntityId" style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>
					<tr>
						<td align="right">层级：</td>					
					    <td ><input:select id="allOrBaseOrgId" name="allOrBaseOrg" value="$invoice_print_newSearch_validType" easyuiClass="easyui-combobox" easyuiStyle="width:150px;">
					         <option value=""></option>	
					         <input:systemStatusOption parentCode="BASE_ORG_PARAMETER_TYPE"/>				
					         </input:select></td> 										
					</tr>                              
					<tr>
						<td style="display: none;"><spring:message code="TmsMdOrgBsnStructures.bsnFlexStructuresId" />：</td>
						<td style="display: none;"><input id="bsnFlexStructuresIdInDialog" name="bsnFlexStructuresId" style="width: 150px;" class="easyui-textbox" type="text"></td>
						
						<td align="right">组织名称：</td>
						
						<td><input id="orgNameInDialog" name="orgName" style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>
					<tr style="display: none;">
					<td  align="right">源会计结构：</td>
						<td><input id="accdflexStructuresCodeInDialog" name="accdflexStructuresCode" style="width: 150px;" class="easyui-textbox" type="text"></td>
					
					</tr>                              
					<tr style="display: none;">
						<td align="right">会计主体：</td>
						<td><input id="legalEntityNameInDialog" name="legalEntityName" style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>
					<tr style="display: none;">					
					<td align="right">成本中心：</td>
						<td><input id="costCenterInDialog" name="costCenter" style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>                              
					<tr>
						<td align="right">源业务结构编码：</td>		
					    <td><input id="bsnflexStructuresCodeInDialog" name="bsnflexStructuresCode" easyuiClass="easyui-combogrid" style="width:150px;"></input></td>	
					</tr>
					<tr>
						<td align="right">源业务结构名称：</td>
						<td><input id="bsnflexStructuresDescriptionInDialog" name="bsnflexStructuresDescription" style="width: 150px;" class="easyui-textbox" type="text" disabled='true'></td>
					</tr>                              
					<tr style="display: none;">
						<td><spring:message code="TmsMdOrgBsnStructures.parentId" />：</td>
						<td><input id="parentIdInDialog" name="parentId" style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>                              
				</table>
			</form>
		</div>
		<div id="TmsMdOrgBsnStructures_addOrEdit_dlg_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="TmsMdOrgBsnStructuresEditSave()"><spring:message code="button.Save" /></a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#TmsMdOrgBsnStructures_addOrEdit_dlg').dialog('close')"><spring:message code="button.Close" /></a>
		</div>
	 <div class="easyui-dialog" closed="true">
       <from id="getByBaseOrg" method="post">
        <table>
          	<tr style="display:none">
		        <td><input id="pageNumber1" class="easyui-textbox" type="text" style="width:0px;" name="pageNumber" value=""></input></td>
		        <td><input id="pageSize1" class="easyui-textbox" type="text" style="width:0px;" name="pageSize" value=""></input></td>
		    </tr>
        </table>
       </from>
     </div>
		
		
		<div id="history_dlg" class="easyui-dialog" style="width: 800px; height: 500px;" closed="true">
			<table class="easyui-datagrid" id="history_dataGrid" title="<spring:message code='TmsMdOrgBsnStructures.title'/>" style="width: 100%; height: 88%" data-options="singleSelect:true,autoRowHeight:false,remoteSort:false,multiSort:true">
			</table>
		</div>
	</div>
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



$(document).ready(
	function() {
		$('#TmsMdOrgBsnStructures_searchform').form('load',
			{
				pageNumber : $('#TmsMdOrgBsnStructuresInit_dataGrid').datagrid('options').pageNumber,
				  pageSize : $('#TmsMdOrgBsnStructuresInit_dataGrid').datagrid('options').pageSize
			});
			SearchTmsMdOrgBsnStructures();
			initFirstBaseOrgEntity();
		});
	$(function() {
		$('#TmsMdOrgBsnStructuresInit_dataGrid')
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
								/*	,{
										field : 'orgCode',
										title : '组织编码',
										width : 80,
										align : 'center'
									//	formatter:orgFormat2
								}    */
								    ,{
									   field : 'orgId',
									   title : '组织Id',
									   width : 80,
									  align : 'center'
								 //       hidden:true
								    }
									,{
										field : 'orgName',
										title : '组织名称',
										width : 80,
										align : 'center'
									//	formatter:orgFormat
									}                           
									,{
										field : 'accdflexStructuresCode',
										title : '源科目编码',
										width : 80,
										align : 'center'
									} 
									,{
										field : 'accdflexStructuresDescription',
										title : '源科目名称',
										width : 80,
										align : 'center'
									} 
									,{
										field : 'legalEntityCode',
										title : '会计主体编码',
										width : 80,
										align : 'center',
										hidden:true
									} 
									,{
										field : 'legalEntityName',
										title : '会计主体说明',
										width : 80,
										align : 'center',
										hidden:true
									}
									,{
										field : 'costCenter',
										title : '成本中心编码',
										width : 80,
										align : 'center',
										hidden:true
									} 
									,{
										field : 'costCenter',
										title : '成本中心说明',
										width : 80,
										align : 'center',
										hidden:true
									} 
									,{
										field : 'bsnflexStructuresCode',
										title : '源数据编码',
										width : 80,
										align : 'center'
									}
									,{
										field : 'bsnflexStructuresDescription',
										title : '源数据名称',
										width : 80,
										align : 'center'
									}
									/*	,{
										field : 'costCenter',
										title : '<spring:message code="TmsMdOrgBsnStructures.costCenter"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'description',
										title : '<spring:message code="TmsMdOrgBsnStructures.description"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'sourceCode',
										title : '<spring:message code="TmsMdOrgBsnStructures.sourceCode"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'startDate',
										title : '<spring:message code="TmsMdOrgBsnStructures.startDate"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'endDate',
										title : '<spring:message code="TmsMdOrgBsnStructures.endDate"/>',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'parentId',
										title : '<spring:message code="TmsMdOrgBsnStructures.parentId"/>',
										width : 80,
										align : 'center'
									} */                          
									] ],
							toolbar : [ {
								text : '新增',
								iconCls : 'icon-add',
								handler : function() {
									TmsMdOrgBsnStructuresAdd();
								}
							}, '-', {
								text : '编辑',
								iconCls : 'icon-edit',
								handler : function() {
									TmsMdOrgBsnStructuresEdit();
								}
							}/* , '-', {
								text : '删除',
								iconCls : 'icon-remove',
								handler : function() {
									TmsMdOrgBsnStructuresRemove();
								}
							} */],
							onLoadSuccess : function() {
								$('#TmsMdOrgBsnStructuresInit_dataGrid').datagrid(
										'clearSelections')
							},
							onClickRow : function(index, data) {
								var row = $('#TmsMdOrgBsnStructuresInit_dataGrid')
										.datagrid('getSelected');
								if (row) {
									loadSaveFormData(row);
								}
							}
						});
		//设置分页控件	
		var p = $('#TmsMdOrgBsnStructuresInit_dataGrid').datagrid('getPager');
		$(p).pagination(
		{
			pageSize : 10,//每页显示的记录条数，默认为10           
			//pageList: [10,20,30],//可以设置每页记录条数的列表           
			beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
			afterPageText : '<spring:message code="pagination.afterPageText"/>',
			displayMsg : '<spring:message code="pagination.displayMsg"/>',
			onSelectPage : function(pageNumber, pageSize) {//分页触发		
				find(pageNumber, pageSize);
				SearchTmsMdOrgBsnStructures();
			}
		});
	});
	function find(pageNumber, pageSize) {
		$('#TmsMdOrgBsnStructures_searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		SearchTmsMdOrgBsnStructures();
	}
	function SearchTmsMdOrgBsnStructures() {
		$("#TmsMdOrgBsnStructuresInit_dataGrid").datagrid("loading");
		$('#TmsMdOrgBsnStructures_searchform').form('submit', {
			url : '${vat}/tmsMdOrgBsnStructures/loadTmsMdOrgBsnStructuresPage.do',
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(result) {
				//数据加载以及绑定
				var result = $.parseJSON(result);
				$("#TmsMdOrgBsnStructuresInit_dataGrid").datagrid('loadData', result);
				$("#TmsMdOrgBsnStructuresInit_dataGrid").datagrid("loaded");
			}
		});
		
	}
	function getHistory() {
		//	alert(0);
		//$("#TmsMdOrgBsnStructuresInit_dataGrid").datagrid("loading");
		$('#TmsMdOrgBsnStructures_searchform').form('submit', {
			url : '${vat}/tmsMdOrgBsnStructures/loadhistory.do',
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
	function searchTmsMdOrgBsnStructures() {
		$("#history_dlg").dialog('open').dialog('setTitle',"<spring:message code='TmsMdOrgBsnStructures.history'/>");
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
	function TmsMdOrgBsnStructuresAdd() {
		clearSaveForm();
		$("#TmsMdOrgBsnStructures_addOrEdit_dlg").dialog('open').dialog('setTitle',"<spring:message code='client.dialog.clientadd.title'/>");
		$("#TmsMdOrgBsnStructures_editform").form('load', {
			clientDate : myformatter(new Date())
		});
		initBaseOrgEntity();
		initCodeInDialogPanl();
		getAllOrBaseOrg();
	}
	function TmsMdOrgBsnStructuresEdit() {
		initBaseOrgEntity();
		initCodeInDialogPanl();
		getAllOrBaseOrg();
		if ($('#TmsMdOrgBsnStructuresInit_dataGrid').datagrid('getChecked').length > 1) {
			$.messager.alert("操作提示", "请选择一条数据进行修改");
		} else {
			clearSaveForm();
			var row_list = $('#TmsMdOrgBsnStructuresInit_dataGrid').datagrid('getChecked');
			if (row_list) {
				var id = '';
				$.each(row_list, function(index, item) {
					if (index == 0) {
						id = item.id;
					}
				});
				if (id != '') {
					$.post('${vat}/tmsMdOrgBsnStructures/loadModifyTmsMdOrgBsnStructures.do',
						{
							id : id
						},
						function(result) {
							if (result.status == '0') {
								$("#TmsMdOrgBsnStructures_addOrEdit_dlg").dialog('open').dialog('setTitle',"<spring:message code='client.dialog.clientedit.title'/>");
								$("#TmsMdOrgBsnStructures_editform").form('load',result.data);
							} else if (result.success == '1') {
								$.messager.alert('<spring:message code="system.alert"/>',result.data);
								SearchTmsMdOrgBsnStructures();
								clearSaveForm();
								$('#TmsMdOrgBsnStructuresInit_dataGrid').dialog('close');
							}
						}, 'json');
				} else {
					$.messager.alert('<spring:message code="system.alert"/>','请选择需要编辑的数据');
				}
			}
		}
	}
	function TmsMdOrgBsnStructuresEditSave() {
		$.messager.confirm("<spring:message code='client.datacommit.formsave.confirm.title'/>","<spring:message code='client.datacommit.formsave.confirm.text'/>",
		function(result) {
			if (result) {
				$('#TmsMdOrgBsnStructures_editform').form(
				'submit',
				{
					url : '${vat}/tmsMdOrgBsnStructures/saveTmsMdOrgBsnStructures.do',
					onSubmit : function() {
						return $(this).form('enableValidation').form('validate');
					},
					success : function(result) {
						var result = $.parseJSON(result);
						if (result.status == '0') {
							clearSaveForm();
							SearchTmsMdOrgBsnStructures();
							$('#TmsMdOrgBsnStructures_addOrEdit_dlg').dialog('close');
							$.messager.alert('<spring:message code="system.alert"/>','保存成功');
						}

					}
				});
			}
		});

	}
	//删除一条记录

	function TmsMdOrgBsnStructuresRemove() {
		if (($('#TmsMdOrgBsnStructuresInit_dataGrid').datagrid('getChecked').length == 0)
				|| ($('#TmsMdOrgBsnStructuresInit_dataGrid').datagrid('getChecked').length > 1)) {
			$.messager.alert("操作提示", "请选择需要删除的数据");
		} else {
			$.messager.confirm("<spring:message code='client.datacommit.delete.confirm.title'/>","<spring:message code='client.datacommit.delete.confirm.text'/>",
			function(result) {
				if (result) {
					var row_list = $(
							'#TmsMdOrgBsnStructuresInit_dataGrid')
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
										url : "${vat}/tmsMdOrgBsnStructures/removeTmsMdOrgBsnStructuress.do",
										type : "POST",
										async : true,
										data : urlString, //不能直接写成 {id:"123",code:"tomcat"}  
										dataType : "json",
										// contentType: "charset=utf-8",  
										cache : false,
										success : function(
												result) {
											SearchTmsMdOrgBsnStructures();
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
		$('#TmsMdOrgBsnStructures_editform').form('clear');
	}
	function clearSaveFormTwo() {
		$('#TmsMdOrgBsnStructures_searchtable').form('clear');
	}
	//######################################################
	
	function orgFormat(val,row){  
//	console.info(val);
		    if(val) return val.orgName;  
		    else return "";  
		} 
	function orgFormat2(val,row){  
		console.info(val);
			    if(val) return val.orgCode;  
			    else return "";  
			}
//##################################################################
	//新增页面下拉面板生成
	
	function initCodeInDialogPanl() {
	$('#bsnflexStructuresCodeInDialog').combogrid(
					{
						required:true,
					    valueField: 'value',
						panelWidth : 500,
						idField : 'id', //ID字段  
						textField : 'flexStructuresCode', //显示的字段  
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
							field : 'flexStructuresCode',
							title : '业务数据结构编码',
							width : 200
						},{
							field : 'flexStructuresDescription',
							title : '业务数据结构说明',
							width : 300
						} ] ],
						toolbar : [
								{
									text : '业务数据结构编码:<input type="text" id="taxEntityId2" class="easyui-textbox"/>'
								}, {
									text : "查询",
									iconCls : 'icon-search',
									handler : function() {
										var param = $("#taxEntityId2").val();
										findTaxEntity(param);
									}
								}, '-' ],
						keyHandler : {
							up : function() { //【向上键】押下处理
								//取得选中行
								var selected = $('#bsnflexStructuresCodeInDialog').combogrid('grid').datagrid('getSelected');
								if (selected) {
									//取得选中行的rowIndex
									var index = $('#bsnflexStructuresCodeInDialog').combogrid('grid').datagrid('getRowIndex',selected);
									//向上移动到第一行为止
									if (index > 0) {
										$('#bsnflexStructuresCodeInDialog').combogrid('grid').datagrid('selectRow',index - 1);
									}
								} else {
									var rows = $('#bsnflexStructuresCodeInDialog').combogrid('grid').datagrid('getRows');
									$('#bsnflexStructuresCodeInDialog').combogrid('grid').datagrid('selectRow',rows.length - 1);
								}
							},
							down : function() { //【向下键】押下处理
								//取得选中行
								var selected = $('#bsnflexStructuresCodeInDialog').combogrid('grid').datagrid('getSelected');
								if (selected) {
									//取得选中行的rowIndex
									var index = $('#bsnflexStructuresCodeInDialog').combogrid('grid').datagrid('getRowIndex',selected);
									//向下移动到当页最后一行为止
									if (index < $('#bsnflexStructuresCodeInDialog').combogrid('grid').datagrid('getData').rows.length - 1) {
										$('#bsnflexStructuresCodeInDialog').combogrid('grid').datagrid('selectRow',index + 1);
									}
								} else {
									$('#bsnflexStructuresCodeInDialog').combogrid('grid').datagrid('selectRow', 0);
								}
							},

						},

					});

	//取得分页组件对象
	var pager = $('#bsnflexStructuresCodeInDialog').combogrid('grid').datagrid('getPager');

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
								$("#pageNumber1").textbox('setValue',pageNumber);
								$("#pageSize1").textbox('setValue',pageSize);			

							
								searchTaxCategory();
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
							$("#pageNumber1").textbox('setValue',pageNumber);
							$("#pageSize1").textbox('setValue',pageSize);				

						
							searchTaxCategory();
							}
						});
	}
	$('#getByBaseOrg').form('clear');
	$('#getByBaseOrg').form('load',
			{
				pageNumber : $('#bsnflexStructuresCodeInDialog').combogrid("grid").datagrid('options').pageNumber,
				  pageSize : $('#bsnflexStructuresCodeInDialog').combogrid("grid").datagrid('options').pageSize
			});
	searchItems();
	$('#bsnflexStructuresCodeInDialog').combogrid('grid').datagrid({
		onSelect: function(index,value){
			var selected = $('#bsnflexStructuresCodeInDialog').combogrid('grid').datagrid('getSelected');
			$("#bsnflexStructuresDescriptionInDialog").textbox('setValue',selected.flexStructuresDescription);
			$("#bsnFlexStructuresIdInDialog").textbox('setValue',selected.id);
			
		}
	});	
}
	
function searchItems(){	
		$.ajax({
			url : "${vat}/tmsMdFlexStructures/loadTmsMdFlexStructuresPageRequest.do",
			type : "POST",
			async : true,
			data : "pageNumber="+$("#pageNumber1").textbox('getValue')+"&pageSize="+$("#pageSize1").textbox('getValue'), //不能直接写成 {id:"123",code:"tomcat"}  
			dataType : "json",
			// contentType: "charset=utf-8",  
			cache : false,
			success : function(result) {
				//clearSaveForm();
				$('#bsnflexStructuresCodeInDialog').combogrid('grid').datagrid('loadData', result.rows);
			}
		});
}
	
function findTaxEntity(param){
	
	$.ajax({
		url : "${vat}/tmsMdFlexStructures/loadTmsMdFlexStructuresPageRequest.do",
		type : "POST",
		async : true,
		data : "pageNumber="+$("#pageNumber1").textbox('getValue')+"&pageSize="+$("#pageSize1").textbox('getValue')+"&flexStructuresCode="+param, //不能直接写成 {id:"123",code:"tomcat"}  
		dataType : "json",
		// contentType: "charset=utf-8",  
		cache : false,
		success : function(result) {
			//clearSaveForm();
			$('#bsnflexStructuresCodeInDialog').combogrid('grid').datagrid('loadData', result.rows);
		}
	});
}	
//##################################################################################
//新增页面组织下拉面板

function initBaseOrgEntity() {

	$('#orgNameInDialog').combogrid(
					{
						required:true,
					    valueField: 'value',
						panelWidth : 500,
						idField : 'id', //ID字段  
						textField : 'orgName', //显示的字段  
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
							field : 'orgCode',
							title : '组织编码',
							width : 200
						},{
							field : 'orgName',
							title : '组织名称',
							width : 300
						} ] ],
						toolbar : [
								{
									text : '组织名称:<input type="text" id="taxEntityId" class="easyui-textbox"/>'
								}, {
									text : "查询",
									iconCls : 'icon-search',
									handler : function() {
										var param = $("#taxEntityId").val();
										findBaseOrgEntity(param);
									}
								}, '-' ],
						keyHandler : {
							up : function() { //【向上键】押下处理
								//取得选中行
								var selected = $('#orgNameInDialog').combogrid('grid').datagrid('getSelected');
								if (selected) {
									//取得选中行的rowIndex
									var index = $('#orgNameInDialog').combogrid('grid').datagrid('getRowIndex',selected);
									//向上移动到第一行为止
									if (index > 0) {
										$('#orgNameInDialog').combogrid('grid').datagrid('selectRow',index - 1);
									}
								} else {
									var rows = $('#orgNameInDialog').combogrid('grid').datagrid('getRows');
									$('#orgNameInDialog').combogrid('grid').datagrid('selectRow',rows.length - 1);
								}
							},
							down : function() { //【向下键】押下处理
								//取得选中行
								var selected = $('#orgNameInDialog').combogrid('grid').datagrid('getSelected');
								if (selected) {
									//取得选中行的rowIndex
									var index = $('#orgNameInDialog').combogrid('grid').datagrid('getRowIndex',selected);
									//向下移动到当页最后一行为止
									if (index < $('#orgNameInDialog').combogrid('grid').datagrid('getData').rows.length - 1) {
										$('#orgNameInDialog').combogrid('grid').datagrid('selectRow',index + 1);
									}
								} else {
									$('#orgNameInDialog').combogrid('grid').datagrid('selectRow', 0);
								}
							},

						},

					});

	//取得分页组件对象
	var pager = $('#orgNameInDialog').combogrid('grid').datagrid('getPager');

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
								$("#pageNumber1").textbox('setValue',pageNumber);
								$("#pageSize1").textbox('setValue',pageSize);										
								searchBaseOrg();
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
							$("#pageNumber1").textbox('setValue',pageNumber);
							$("#pageSize1").textbox('setValue',pageSize);										
							searchBaseOrg();
							}
						});
	}
	$('#getByBaseOrg').form('clear');
	$('#getByBaseOrg').form('load',
			{
				pageNumber : $('#orgNameInDialog').combogrid("grid").datagrid('options').pageNumber,
				  pageSize : $('#orgNameInDialog').combogrid("grid").datagrid('options').pageSize
			});
	searchBaseOrg();
	
	$('#orgNameInDialog').combogrid('grid').datagrid({
		onSelect: function(index,value){
			var selected = $('#orgNameInDialog').combogrid('grid').datagrid('getSelected');
			$("#orgIdInDialog").textbox('setValue',selected.id);
		}
	});	
}
	
function searchBaseOrg(){	
		$.ajax({
			url : "${vat}/baseOrg/loadBaseOrgPageRequest.do",
			type : "POST",
			async : true,
			data : "pageNumber="+$("#pageNumber1").textbox('getValue')+"&pageSize="+$("#pageSize1").textbox('getValue'), //不能直接写成 {id:"123",code:"tomcat"}  
			dataType : "json",
			// contentType: "charset=utf-8",  
			cache : false,
			success : function(result) {
				//clearSaveForm();
				$('#orgNameInDialog').combogrid('grid').datagrid('loadData', result.data);
				
			}
		});
}

function findBaseOrgEntity(param){
	
	$.ajax({
		url : "${vat}/baseOrg/loadBaseOrgPageRequest.do",
		type : "POST",
		async : true,
		data : "pageNumber="+$("#pageNumber1").textbox('getValue')+"&pageSize="+$("#pageSize1").textbox('getValue')+"&orgName="+param, //不能直接写成 {id:"123",code:"tomcat"}  
		dataType : "json",
		// contentType: "charset=utf-8",  
		cache : false,
		success : function(result) {
			//clearSaveForm();
			$('#orgNameInDialog').combogrid('grid').datagrid('loadData', result.data.rows);
		}
	});
}
//##########################################################################################################
//查询框下拉面板生成
function initFirstBaseOrgEntity() {

	$('#bsnflexStructuresCodeId').combogrid(
					{
						
					    valueField: 'value',
						panelWidth : 500,
						idField : 'id', //ID字段  
						textField : 'flexStructuresCode', //显示的字段  
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
							field : 'flexStructuresCode',
							title : '业务数据结构编码',
							width : 200
						},{
							field : 'flexStructuresDescription',
							title : '业务数据结构名称',
							width : 300
						} ] ],
						toolbar : [
								{
									text : '业务数据结构编码:<input type="text" id="taxEntityId3" class="easyui-textbox"/>'
								}, {
									text : "查询",
									iconCls : 'icon-search',
									handler : function() {
										var param = $("#taxEntityId3").val();
										findbsnflexStructuresEntity(param);
									}
								}, '-' ],
								onSelect: function(index,value){
									var selected = $('#bsnflexStructuresCodeId').combogrid('grid').datagrid('getSelected');
									$("#bsnflexStructuresIdId").textbox('setValue',selected.id);
								},
						keyHandler : {
							up : function() { //【向上键】押下处理
								//取得选中行
								var selected = $('#bsnflexStructuresCodeId').combogrid('grid').datagrid('getSelected');
								if (selected) {
									//取得选中行的rowIndex
									var index = $('#bsnflexStructuresCodeId').combogrid('grid').datagrid('getRowIndex',selected);
									//向上移动到第一行为止
									if (index > 0) {
										$('#bsnflexStructuresCodeId').combogrid('grid').datagrid('selectRow',index - 1);
									}
								} else {
									var rows = $('#bsnflexStructuresCodeId').combogrid('grid').datagrid('getRows');
									$('#bsnflexStructuresCodeId').combogrid('grid').datagrid('selectRow',rows.length - 1);
								}
							},
							down : function() { //【向下键】押下处理
								//取得选中行
								var selected = $('#bsnflexStructuresCodeId').combogrid('grid').datagrid('getSelected');
								if (selected) {
									//取得选中行的rowIndex
									var index = $('#bsnflexStructuresCodeId').combogrid('grid').datagrid('getRowIndex',selected);
									//向下移动到当页最后一行为止
									if (index < $('#bsnflexStructuresCodeId').combogrid('grid').datagrid('getData').rows.length - 1) {
										$('#bsnflexStructuresCodeId').combogrid('grid').datagrid('selectRow',index + 1);
									}
								} else {
									$('#bsnflexStructuresCodeId').combogrid('grid').datagrid('selectRow', 0);
								}
							},

						},

					});

	//取得分页组件对象
	var pager = $('#bsnflexStructuresCodeId').combogrid('grid').datagrid('getPager');

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
								$("#pageNumber1").textbox('setValue',pageNumber);
								$("#pageSize1").textbox('setValue',pageSize);										
								searchbsnflexStructures();
							},
							//改变页显示条数的处理
					
			/* 				onChangePageSize : function(pageNumber, pageSize) {
								$("#pageNumber1").textbox('setValue',pageNumber);
								$("#pageSize1").textbox('setValue',pageSize);										
								searchFirstBaseOrg();
							}, */
						
						onRefresh : function(pageNumber, pageSize) {
								//按分页的设置取数据
							$('#getByBaseOrg').form('clear');
							$("#pageNumber1").textbox('setValue',pageNumber);
							$("#pageSize1").textbox('setValue',pageSize);										
							searchbsnflexStructures();
							}
						});
	}
	$('#getByBaseOrg').form('clear');
	$('#getByBaseOrg').form('load',
			{
				pageNumber : $('#bsnflexStructuresCodeId').combogrid("grid").datagrid('options').pageNumber,
				  pageSize : $('#bsnflexStructuresCodeId').combogrid("grid").datagrid('options').pageSize
			});
	searchbsnflexStructures();

}
	
function searchbsnflexStructures(){	
		$.ajax({
			url : "${vat}/tmsMdFlexStructures/loadTmsMdFlexStructuresPageRequest.do",
			type : "POST",
			async : true,
			data : "pageNumber="+$("#pageNumber1").textbox('getValue')+"&pageSize="+$("#pageSize1").textbox('getValue'), //不能直接写成 {id:"123",code:"tomcat"}  
			dataType : "json",
			// contentType: "charset=utf-8",  
			cache : false,
			success : function(result) {
				//clearSaveForm();
				$('#bsnflexStructuresCodeId').combogrid('grid').datagrid('loadData', result.rows);
				
			}
		});
}

function findbsnflexStructuresEntity(param){
	
	$.ajax({
		url : "${vat}/tmsMdFlexStructures/loadTmsMdFlexStructuresPageRequest.do",
		type : "POST",
		async : true,
		data : "pageNumber="+$("#pageNumber1").textbox('getValue')+"&pageSize="+$("#pageSize1").textbox('getValue')+"&flexStructuresCode="+param, //不能直接写成 {id:"123",code:"tomcat"}  
		dataType : "json",
		// contentType: "charset=utf-8",  
		cache : false,
		success : function(result) {
			//clearSaveForm();
			$('#bsnflexStructuresCodeId').combogrid('grid').datagrid('loadData', result.rows);
		}
	});
}


function getAllOrBaseOrg(){
	
	$("#allOrBaseOrgId").combobox({
		panelHeight:'auto',
		 required:true,
		valueField: 'value',
		textField: 'text',
		editable:false	
	});
	chanageAllOrBaseOrg();
}




function chanageAllOrBaseOrg(){
	$('#allOrBaseOrgId').combobox({			
		onSelect:function(data){				
		// 1 是全局
			if(data.value == "1"){
				
				$('#orgNameInDialog').combogrid('clear');
				$('#orgNameInDialog').combogrid(
						{
						   required:false,
						   disabled:true
						});
			}else{
				initBaseOrgEntity();
				$('#orgNameInDialog').combogrid(
						{
						   required:true,
						   disabled:false
						});
				
			}
		}	
	});
}



</script>
</html>