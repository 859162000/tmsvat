
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
			<form id="TmsMdTrxCatBsnStr_searchform" method="post" scroll="no">
				<table cellpadding="10">
					<tr>
						<td>业务数据组合ID：</td>
						<td><input id="bsnCombinationIdId" class="easyui-textbox"
							type="text" style="width: 150px;" name="bsnCombinationId"
							value=""></input></td>
						<td>来源代码：</td>

						<td><input id="sourceCodeId" class="easyui-textbox"
							type="text" style="width: 150px;" name="sourceCode" value=""></input></td>
						<td>
							<div style="text-align: center; padding: 10px">
								<a href="#" id="searchbtnTmsMdTrxCatBsnStr"
									class="easyui-linkbutton" data-options="iconCls:'icon-search'"
									style="width: 120px" onclick="SearchTmsMdTrxCatBsnStr()"><spring:message
										code='button.Search' /></a>
							</div>
						</td>
					</tr>

					<tr style="display: none">
						<td><input id="pageNumber" class="easyui-textbox" type="text"
							style="width: 0px;" name="pageNumber" value=""></input></td>
						<td><input id="pageSize" class="easyui-textbox" type="text"
							style="width: 0px;" name="pageSize" value=""></input></td>
						<td></td>
					</tr>
				</table>
			</form>
		</div>
		<div style="width: 100%; height: 100%">
			<table class="easyui-datagrid" id="TmsMdTrxCatBsnStrInit_dataGrid"
				title="<spring:message code='TmsMdTrxCatBsnStr.title'/>"
				style="width: 100%; height: 88%"
				data-options="singleSelect:true,autoRowHeight:false,pagination:true,pageSize:10,remoteSort:false,multiSort:true">
			</table>
		</div>
	</div>
	<div data-options="region:'center',border:false"
		style="background-color: #E0ECFF">
		<div id="TmsMdTrxCatBsnStr_addOrEdit_dlg" class="easyui-dialog"
			style="width: 800px; height: 500px;" closed="true"
			buttons="#TmsMdTrxCatBsnStr_addOrEdit_dlg_dlg-buttons">
			<form id="TmsMdTrxCatBsnStr_editform" class="easyui-form"
				method="post" data-options="novalidate:true"
				commandsName="clientManageForm">
				<table>
					<tr style="display: none">
						<td><input class="easyui-textbox" type="text"
							style="width: 0px;" name="pageNumber" value=""></input></td>
						<td><input class="easyui-textbox" type="text"
							style="width: 0px;" name="pageSize" value=""></input></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="display: none">
						<td><input id="idInDialog" name="id" style="width: 150px;"
							class="easyui-textbox" type="text"></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>全局/组织</td>
						<td><select name="globalOrLocalOgrType"
							class="easyui-combobox" style="width: 120px"
							id="globalOrLocalOgrTypeId">
								<option value="global">全局</option>
								<option value="org">组织</option>
								<%--  <input:systemStatusOption parentCode="BASE_LEGAL_ENTITY_TYPE"/>--%>
						</select></td>
						<td colspan="2">
							<div id="organizationInDialog" style="display: none">
								<table>
									<tr>
										<td>组织：</td>
										<td><select name="orgId" id="orgIdId"
											class="easyui-combogrid" style="width: 120px">

										</select></td>
									</tr>
								</table>
							</div>
						</td>

					</tr>
					<tr>
						<td>
							业务数据组合：
						</td>
						<td><input id="bsnCombinationIdInDialog"
							onclick="popUpCombination(); return true" name="bsnCombinationId"
							style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td>业务数据组合明细：</td>
						<td><input id="valuesDescription"
							onclick="popUpCombination(); return true" name="bsnCombinationId"
							style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>
					<tr>
						<td>
							<%-- <spring:message code="TmsMdTrxCatBsnStr.taxTrxTypeId" /> --%>涉税交易类型：
						</td>
						<td><select name="taxTrxTypeId" class="easyui-combobox"
							style="width: 120px">
								<option value="NotSet"></option>
								<option value="Y">111</option>
								<option value="N">222</option>
						</select> <!-- <input id="taxTrxTypeIdInDialog" name="taxTrxTypeId"
							style="width: 150px;" class="easyui-textbox" type="text"> --></td>
						<td>
							<%-- <spring:message code="TmsMdTrxCatBsnStr.enabledFlag" /> --%>是否启用：
						</td>
						<td><select name="enabledFlagInDialog"
							class="easyui-combobox" style="width: 120px">
								<option value="NotSet"></option>
								<option value="Y">是</option>
								<option value="N">否</option>
						</select> <!-- <input id="enabledFlagInDialog" name="enabledFlag"
							style="width: 150px;" class="easyui-textbox" type="text"> --></td>

					</tr>
					<tr>
						<td><spring:message code="TmsMdTrxCatBsnStr.startDate" />：</td>
						<td><input id="startDateInDialog" name="startDate"
							style="width: 150px;" class="easyui-datebox" type="text"></td>
						<td><spring:message code="TmsMdTrxCatBsnStr.endDate" />：</td>
						<td><input id="endDateInDialog" name="endDate"
							style="width: 150px;" class="easyui-datebox" type="text"></td>
					</tr>
					<tr>
						<td>
							<%-- <spring:message code="TmsMdTrxCatBsnStr.sourceCode" /> --%>来源代码：
						</td>
						<td><select name="sourceCodeInDialog" class="easyui-combobox"
							style="width: 120px">
								<option value="NotSet"></option>
								<option value="g">长江证券1</option>
								<option value="d">长江证券2</option>
						</select> <!-- <input id="sourceCodeInDialog" name="sourceCode"
							style="width: 150px;" class="easyui-textbox" type="text"> --></td>
						<td></td>
						<td></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="TmsMdTrxCatBsnStr_addOrEdit_dlg_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" onclick="TmsMdTrxCatBsnStrEditSave()"><spring:message
					code="button.Save" /></a> <a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#TmsMdTrxCatBsnStr_addOrEdit_dlg').dialog('close')"><spring:message
					code="button.Close" /></a>
		</div>
		<div id="BusinessDataCombinationDialogId" class="easyui-dialog"
			style="width: 500px; height: 300px;" closed="true">
			<form id="segmentForm">
				<table id="segment">
					
				</table>
			</form>
			
			<br><br>
			
			<div id="TmsMdTrxCatBsnStr_addOrEdit_dlg_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" onclick="businessDataSave()"><spring:message
					code="button.Save" /></a> <a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#BusinessDataCombinationDialogId').dialog('close')">取消</a>
			</div>
			
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		$("input", $("#bsnCombinationIdInDialog").next("span"))
				.click(
						function() {
							var g = $('#orgIdId').combogrid('grid');
							var r = g.datagrid('getSelected');
							var id = r.id;
							if (id != '') {
								$.post('${vat}/tmsMdOrgBsnStructures/loadTmsMdOrgBsnStructuresPageById.do',
												{
													id : id
												},
												function(result) {
													if (result.success) {
														$("#BusinessDataCombinationDialogId").dialog('open').dialog('setTitle', "业务数据组合");//弹出业务数据组合
													
														$("#segment").empty();
														
														var html="<tr>";
														html+="<td>业务数据结构：</td>";
														html+="<td><input id='taxTrxTypeIdInDialog1' name='taxTrxTypeId' value='"+result.flexStructuresDescription+"' style='width: 120px;' class='easyui-textbox' type='text'  disabled></td>";
														html+="</tr>";
														
														var tmsMdFlexValueCodes = $("#bsnCombinationIdInDialog").textbox("getValue",tmsMdFlexValueCodes).split(".");
	
														
														var tmsMdFlexSegments = result.tmsMdFlexSegments;
														$.each(tmsMdFlexSegments, function(i, tmsMdFlexSegment) {
															 
															var flexValueSetId =  tmsMdFlexSegment.flexValueSetId;
															html += "<tr id='"+tmsMdFlexSegment.flexValueSetId+"'>"
															html += "<td>"+tmsMdFlexSegment.segmentName+"</td>"
															html += " <td><select name='globalOrLocalOgrType' class='easyui-combobox segmentSeclect' style='width: 120px'>";
															
															var tmsMdFlexValues = result[flexValueSetId].tmsMdFlexValues;
															 $.each(tmsMdFlexValues, function(j, tmsMdFlexValue) {
																  if(tmsMdFlexValueCodes.length>0&&tmsMdFlexValue.flexValueCode==tmsMdFlexValueCodes[i]){
																	  html += " <option selected value='"+tmsMdFlexValue.flexValueCode+","+tmsMdFlexValue.description+"'>"+tmsMdFlexValue.flexValueCode+"</option>";
																  }else{
																	  html += " <option value='"+tmsMdFlexValue.flexValueCode+","+tmsMdFlexValue.description+"'>"+tmsMdFlexValue.flexValueCode+"</option>";
																  }
															 }); 
															  
															html += " </select></td>";
															html +="</tr>" 
														});
														$("#segment").append(html);
														$(".easyui-combobox").combobox();
														
													}else{
														alert(result.errMsg)
													}
												}, 'json');
							}
						});
	})

	$(document).ready(
			function() {
				$('#TmsMdTrxCatBsnStr_searchform').form(
						'load',
						{
							pageNumber : $('#TmsMdTrxCatBsnStrInit_dataGrid')
									.datagrid('options').pageNumber,
							pageSize : $('#TmsMdTrxCatBsnStrInit_dataGrid')
									.datagrid('options').pageSize
						});
				SearchTmsMdTrxCatBsnStr();
			});
	$(function() {
		$('#TmsMdTrxCatBsnStrInit_dataGrid')
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
									},
									{
										field : 'legalEntityName',
										title : '纳税人',
										width : 80,
										align : 'center'
									},
									{
										field : 'bsnCombinationName',
										title : '业务组合说明',
										width : 80,
										align : 'center'
									},
									{
										field : 'taxTrxTypeName',
										title : '涉税交易类型',
										width : 80,
										align : 'center'
									},
									{
										field : 'globalOrLocalOgrType',
										title : '<spring:message code="TmsMdTrxCatBsnStr.globalOrLocalOgrType"/>',
										width : 80,
										align : 'center'
									},
									{
										field : 'sourceCode',
										title : '<spring:message code="TmsMdTrxCatBsnStr.sourceCode"/>',
										width : 80,
										align : 'center'
									},
									{
										field : 'enabledFlag',
										title : '<spring:message code="TmsMdTrxCatBsnStr.enabledFlag"/>',
										width : 80,
										align : 'center',
									 formatter:onFormatter
									},
									{
										field : 'description',
										title : '<spring:message code="TmsMdTrxCatBsnStr.description"/>',
										width : 80,
										align : 'center'
									},
									{
										field : 'startDate',
										title : '<spring:message code="TmsMdTrxCatBsnStr.startDate"/>',
										width : 80,
										align : 'center'
									},
									{
										field : 'endDate',
										title : '<spring:message code="TmsMdTrxCatBsnStr.endDate"/>',
										width : 80,
										align : 'center'
									},
									{
										field : 'orgName',
										title : '组织',
										width : 80,
										align : 'center'
									} ] ],
							toolbar : [ {
								text : '新增',
								iconCls : 'icon-add',
								handler : function() {
									TmsMdTrxCatBsnStrAdd();
								}
							}, '-', {
								text : '编辑',
								iconCls : 'icon-edit',
								handler : function() {
									TmsMdTrxCatBsnStrEdit();
								}
							}, '-', {
								text : '删除',
								iconCls : 'icon-remove',
								handler : function() {
									TmsMdTrxCatBsnStrRemove();
								}
							} ],
							onLoadSuccess : function() {
								$('#TmsMdTrxCatBsnStrInit_dataGrid').datagrid(
										'clearSelections')
							},
							onClickRow : function(index, data) {
								var row = $('#TmsMdTrxCatBsnStrInit_dataGrid')
										.datagrid('getSelected');
								if (row) {
									loadSaveFormData(row);
								}
							}
						});
		//设置分页控件	
		var p = $('#TmsMdTrxCatBsnStrInit_dataGrid').datagrid('getPager');
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
								SearchTmsMdTrxCatBsnStr();
							}
						});
	});
	function find(pageNumber, pageSize) {
		$('#TmsMdTrxCatBsnStr_searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		SearchTmsMdTrxCatBsnStr();
	}

	function popUpCombination() {

		alert(1);
	}

	function initOrgInDialogForm() {
		//初始化位于SendForm里面的客户信
		$('#orgIdId').combogrid({
			panelWidth : 500,
			idField : 'id', //ID字段  
			textField : 'orgName', //显示组织名称
			url : "",
			fitColumns : true,
			striped : true,
			editable : true,
			pagination : true, //是否分页
			rownumbers : true, //序号
			collapsible : false, //是否可折叠的
			fit : true, //自动大小
			method : 'post',
			columns : [ [ {
				field : 'orgCode',
				title : '组织编号',
				width : 60
			}, {
				field : 'orgName',
				title : '组织名称',
				width : 100
			} ] ],
			toolbar : [ {
				text : '组织名称<input type="text" id="orgInSearchBox"/>'
			}, {
				text : "查询",
				iconCls : 'icon-search',
				handler : function() {
					//findCustomerName();
					var orgName = $("#orgInSearchBox").val();
					InitOrgId(orgName);
				}
			}, '-' ]
		});

		//取得分页组件对象
		var pager = $('#orgIdId').combogrid('grid').datagrid('getPager');

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
									$('#orgIdId').combogrid("grid").datagrid(
											'options').pageSize = pageSize;
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
		InitOrgId('');
		var getData = function(page, rows) {
			$('#TmsMdTrxCatBsnStr_editform').form('load', {
				pageNumber : page,
				pageSize : rows
			});

			$('#TmsMdTrxCatBsnStr_editform').form(
					'submit',
					{
						url : '${vat}/baseOrg/loadBaseOrgPage.do',
						onSubmit : function() {
							return $(this).form('enableValidation').form(
									'validate');
						},
						success : function(result) {
							//数据加载以及绑定

							var result = $.parseJSON(result);
							$('#orgIdId').combogrid('grid').datagrid(
									'loadData', result.data);
							//	$("#invoiceList_datagrid").datagrid("loaded");
						}
					});
		};

	}

	function InitOrgId(orgName) {
		//alert("orgName:"+orgName);
		$('#TmsMdTrxCatBsnStr_editform').form(
				'load',
				{
					pageNumber : $('#orgIdId').combogrid('grid').datagrid(
							'options').pageNumber,
					pageSize : $('#orgIdId').combogrid('grid').datagrid(
							'options').pageSize
				});

		$('#TmsMdTrxCatBsnStr_editform').form(
				'submit',
				{
					url : '${vat}/baseOrg/loadBaseOrgPage.do',
					queryParams : {
						orgName : orgName,
					},
					onSubmit : function() {
						return $(this).form('enableValidation')
								.form('validate');
					},
					success : function(result) {
						//数据加载以及绑定
						var result = $.parseJSON(result);
						$('#orgIdId').combogrid('grid').datagrid('loadData',
								result.data);
						//	$("#invoiceList_datagrid").datagrid("loaded");
					}
				});

	}

	function SearchTmsMdTrxCatBsnStr() {
		$("#TmsMdTrxCatBsnStrInit_dataGrid").datagrid("loading");
		$('#TmsMdTrxCatBsnStr_searchform')
				.form(
						'submit',
						{
							url : '${vat}/tmsMdTrxCatBsnStr/loadTmsMdTrxCatBsnStrPage.do',
							onSubmit : function() {
								return $(this).form('enableValidation').form(
										'validate');
							},
							success : function(result) {
								//数据加载以及绑定
								var result = $.parseJSON(result);
								$("#TmsMdTrxCatBsnStrInit_dataGrid").datagrid(
										'loadData', result);
								$("#TmsMdTrxCatBsnStrInit_dataGrid").datagrid(
										"loaded");
							}
						});
	}
	function getHistory() {
		//	alert(0);
		//$("#TmsMdTrxCatBsnStrInit_dataGrid").datagrid("loading");
		$('#TmsMdTrxCatBsnStr_searchform').form('submit', {
			url : '${vat}/tmsMdTrxCatBsnStr/loadhistory.do',
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

	function changeTypeToOrg() {
		$('#globalOrLocalOgrTypeIdDiv').css('display', 'block');
		alert(111);
		initOrgInDialogForm();//初始化组织
		//	$('#legalEntityIdDiv').css('display', 'none');
	}
	function changeTypeToGlobal() {
		$('#globalOrLocalOgrTypeIdDiv').css('display', 'none');
		//	$('#legalEntityIdDiv').css('display', 'none');
	}

	function searchTmsMdTrxCatBsnStr() {
		$("#history_dlg").dialog('open').dialog('setTitle',
				"<spring:message code='TmsMdTrxCatBsnStr.history'/>");
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
	function TmsMdTrxCatBsnStrAdd() {
		clearSaveForm();
		$("#TmsMdTrxCatBsnStr_addOrEdit_dlg").dialog('open').dialog('setTitle',
				"<spring:message code='client.dialog.clientadd.title'/>");
		$("#organizationInDialog").css('display', 'none');
		$('#globalOrLocalOgrTypeId').combobox({
			onSelect : function(record) {
				var peo = $('#globalOrLocalOgrTypeId').combobox('getValue');

				if (peo == 'global') {
					$("#organizationInDialog").css('display', 'none');
					//	alert("global");
				} else if (peo == 'org') {
					$("#organizationInDialog").css('display', 'block');
					initOrgInDialogForm();
				}

			}
		});

		$("#TmsMdTrxCatBsnStr_editform").form('load', {
			clientDate : myformatter(new Date())
		});
	}
	function TmsMdTrxCatBsnStrEdit() {
		if ($('#TmsMdTrxCatBsnStrInit_dataGrid').datagrid('getChecked').length > 1) {
			$.messager.alert("操作提示", "请选择一条数据进行修改");
		} else {
			clearSaveForm();
			var row_list = $('#TmsMdTrxCatBsnStrInit_dataGrid').datagrid(
					'getChecked');
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
									'${vat}/tmsMdTrxCatBsnStr/loadModifyTmsMdTrxCatBsnStr.do',
									{
										id : id
									},
									function(result) {
										if (result.status == '0') {
											$(
													"#TmsMdTrxCatBsnStr_addOrEdit_dlg")
													.dialog('open')
													.dialog('setTitle',
															"<spring:message code='client.dialog.clientedit.title'/>");
											$("#TmsMdTrxCatBsnStr_editform")
													.form('load', result.data);
										} else if (result.success == '1') {
											$.messager
													.alert(
															'<spring:message code="system.alert"/>',
															result.errorMsg);
											SearchTmsMdTrxCatBsnStr();
											clearSaveForm();
											$('#TmsMdTrxCatBsnStrInit_dataGrid')
													.dialog('close');
										}
									}, 'json');
				} else {
					$.messager.alert('<spring:message code="system.alert"/>',
							'请选择需要编辑的数据');
				}
			}
		}
	}
	function TmsMdTrxCatBsnStrEditSave() {
		$.messager
				.confirm(
						"<spring:message code='client.datacommit.formsave.confirm.title'/>",
						"<spring:message code='client.datacommit.formsave.confirm.text'/>",
						function(result) {
							if (result) {
								$('#TmsMdTrxCatBsnStr_editform')
										.form(
												'submit',
												{
													url : '${vat}/tmsMdTrxCatBsnStr/saveTmsMdTrxCatBsnStr.do',
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
															SearchTmsMdTrxCatBsnStr();
															$(
																	'#TmsMdTrxCatBsnStr_addOrEdit_dlg')
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

	function TmsMdTrxCatBsnStrRemove() {
		if (($('#TmsMdTrxCatBsnStrInit_dataGrid').datagrid('getChecked').length == 0)
				|| ($('#TmsMdTrxCatBsnStrInit_dataGrid').datagrid('getChecked').length > 1)) {
			$.messager.alert("操作提示", "请选择需要删除的数据");
		} else {
			$.messager
					.confirm(
							"<spring:message code='client.datacommit.delete.confirm.title'/>",
							"<spring:message code='client.datacommit.delete.confirm.text'/>",
							function(result) {
								if (result) {
									var row_list = $(
											'#TmsMdTrxCatBsnStrInit_dataGrid')
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
											$
													.ajax({
														url : "${vat}/tmsMdTrxCatBsnStr/removeTmsMdTrxCatBsnStrs.do",
														type : "POST",
														async : true,
														data : urlString, //不能直接写成 {id:"123",code:"tomcat"}  
														dataType : "json",
														// contentType: "charset=utf-8",  
														cache : false,
														success : function(
																result) {
															SearchTmsMdTrxCatBsnStr();
															clearSaveForm();
														}
													});
										}
									}
								}

							});
		}
	}
	
	function InitCombobox() {
		$('#customerManageInit_searchform_discOption')
				.combobox(
						{
							url : "${vat}/customer/getDicEntityByParentCode.do?parentCode=VAT_CUSTOMER_DISC_OPTION",
							valueField : "code",
							method : 'GET',
							editable : false,
							textField : "name",
							onLoadSuccess : function(data) {
								if (data) {
									$(
											'#customerManageInit_searchform_discOption')
											.combobox('setValue', data[0].code);
									var temp = $(
											'#customerManageInit_searchform_discOption')
											.combobox('getText');
									temp = temp + ':';
									$('#customerManageInit_searchform')
											.find(
													'#customerManageInit_searchform_customerEntityNumText')
											.html(temp);
								}
							},
							onChange : function(n, o) {
								var temp = $(
										'#customerManageInit_searchform_discOption')
										.combobox('getText');
								temp = temp + ':';
								$('#customerManageInit_searchform')
										.find(
												'#customerManageInit_searchform_customerEntityNumText')
										.html(temp);
							}
						});

	}
	
	function businessDataSave(){
		var selects = $("#segmentForm").find("select");
		var tmsMdFlexValueCodes="";
		var tmsMdFlexValueDescriptions="";
		for(var i=0;i<selects.length;i++){
			tmsMdFlexValueCodes += selects.eq(i).combobox('getValue').split(",")[0]+".";
			tmsMdFlexValueDescriptions += selects.eq(i).combobox('getValue').split(",")[1]+".";
		}
		tmsMdFlexValueCodes = tmsMdFlexValueCodes.substring(0,tmsMdFlexValueCodes.length-1)
		tmsMdFlexValueDescriptions = tmsMdFlexValueDescriptions.substring(0,tmsMdFlexValueDescriptions.length-1)
		$("#bsnCombinationIdInDialog").textbox("setText",tmsMdFlexValueCodes);
		$("#bsnCombinationIdInDialog").textbox("setValue",tmsMdFlexValueCodes);
		
		
		$("#valuesDescription").textbox("setText",tmsMdFlexValueDescriptions);
		$("#valuesDescription").textbox("setValue",tmsMdFlexValueDescriptions);
		
		$('#BusinessDataCombinationDialogId').dialog('close');
		
		
	}
	
	function clearSaveForm() {
		$('#TmsMdTrxCatBsnStr_editform').form('clear');
	}
	
	
	
	
	
	
	function onFormatter(val,row){
		if(val == 'Y' || val == '1'){
			
			return "是";
		}else if(val == 'N' || val == '0'){
			
			return "否";
		}
		
	}
	
	
	
	
	
</script>
</html>