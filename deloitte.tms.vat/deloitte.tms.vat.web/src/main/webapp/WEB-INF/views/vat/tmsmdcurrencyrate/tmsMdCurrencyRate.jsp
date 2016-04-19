
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
			<form id="TmsMdCurrencyRate_searchform" method="post" scroll="no">
				<table id="TmsMdCurrencyRate_searchform_table">	
			<tr>
			<!-- 
			<td>汇率ID：</td>
			 <td><input id="idId" class="easyui-textbox" type="text" style="width: 150px;" name="id" value=""></input></td>
			 -->
			 <td align="right">组织：</td>
			 <td><input id="orgNameId" class="easyui-textbox" type="text" style="width: 150px;" name="orgName" value=""></input></td>
			<td align="right">来源币种：</td>
			 <td><input id="sourceCurrencyCodeId" class="easyui-textbox" type="text" style="width: 150px;" name="sourceCurrencyCode" value=""></input></td>
			<td align="right">目标币种：</td>
			 <td><input id="targetCurrencyCodeId" class="easyui-textbox" type="text" style="width: 150px;" name="targetCurrencyCode" value=""></input></td>
			<td align="right">汇率日期：</td>
			<td><input id="exchangeDateId" class="easyui-datebox" type="text" style="width: 90;" name="exchangeDate" value="" ></input></td>
		      </tr><tr>
		      <td></td>
		       <td></td>
		        <td></td>
						<td  align="right">
					<!--  	<div style="text-align: center; padding: 10px"></div> -->	
								<a href="#" id="searchbtnTmsMdCurrencyRate" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px" onclick="SearchTmsMdCurrencyRate()"><spring:message code='button.Search' /></a>
							
						</td>
						<td>
							<a href="#" id="researchbtn"  class="easyui-linkbutton" data-options="" style="width:80px" onclick="clearSaveFormT()">重置</a>
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
		<div style="width: 100%; height: 100%">
			<table class="easyui-datagrid" id="TmsMdCurrencyRateInit_dataGrid" title="<spring:message code='TmsMdCurrencyRate.title'/>" style="width: 100%; height: 78%" data-options="singleSelect:true,autoRowHeight:false,pagination:true,pageSize:10,remoteSort:false,multiSort:true">
			</table>
		</div>
	</div>
	<div data-options="region:'center',border:false"
		style="background-color: #E0ECFF">
		<div id="TmsMdCurrencyRate_addOrEdit_dlg" class="easyui-dialog"
			style="width: 800px; height: 500px;" closed="true"
			buttons="#TmsMdCurrencyRate_addOrEdit_dlg_dlg-buttons">
			<form id="TmsMdCurrencyRate_editform" class="easyui-form" method="post"
				data-options="novalidate:true" commandsName="clientManageForm">
				<table>
					<tr>
						<td style="display: none;"><spring:message code="TmsMdCurrencyRate.id" />：</td>
						<td style="display: none;"><input id="idInDialog" name="id" style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td><spring:message code="TmsMdCurrencyRate.sourceCurrencyCode" />：</td>
						<td><input id="sourceCurrencyCodeInDialog" name="sourceCurrencyCode" style="width: 150px;" class="easyui-textbox" type="text"></td>
					<td><spring:message code="TmsMdCurrencyRate.targetCurrencyCode" />：</td>
						<td><input id="targetCurrencyCodeInDialog" name="targetCurrencyCode" style="width: 150px;" class="easyui-textbox" type="text"></td>
					
					</tr>                              
					<tr>
						
						<td><spring:message code="TmsMdCurrencyRate.currencyRate" />：</td>
						<td><input id="currencyRateInDialog" name="currencyRate" style="width: 150px;" class="easyui-textbox" type="text"></td>
						
						
						<td><spring:message code="TmsMdCurrencyRate.exchangeDate" />：</td>
						<td><input id="exchangeDateInDialog" class="easyui-datebox" type="text" style="width: 90;" name="exchangeDate" value="" ></input></td>
					
					</tr>                              
					<tr>
	
						<td><spring:message code="TmsMdCurrencyRate.description" />：</td>
						<td><input id="descriptionInDialog" name="description" style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>                              
				</table>
			</form>
		</div>
		<div id="TmsMdCurrencyRate_addOrEdit_dlg_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="TmsMdCurrencyRateEditSave()"><spring:message code="button.Save" /></a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#TmsMdCurrencyRate_addOrEdit_dlg').dialog('close')"><spring:message code="button.Close" /></a>
		</div>
		<div id="history_dlg" class="easyui-dialog" style="width: 800px; height: 500px;" closed="true">
			<table class="easyui-datagrid" id="history_dataGrid" title="<spring:message code='TmsMdCurrencyRate.title'/>" style="width: 100%; height: 88%" data-options="singleSelect:true,autoRowHeight:false,remoteSort:false,multiSort:true">
			</table>
		</div>
	</div>
</body>
<script type="text/javascript">
$(document).ready(
	function() {
		$('#TmsMdCurrencyRate_searchform').form('load',
			{
				pageNumber : $('#TmsMdCurrencyRateInit_dataGrid').datagrid('options').pageNumber,
				pageSize : $('#TmsMdCurrencyRateInit_dataGrid').datagrid('options').pageSize
			});
			SearchTmsMdCurrencyRate();
		});
	$(function() {
		$('#TmsMdCurrencyRateInit_dataGrid')
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
									,{
										field : 'orgName',
										title : '组织',
										width : 80,
										align : 'center'
									}                           
									,{
										field : 'sourceCurrencyCode',
										title : '<spring:message code="TmsMdCurrencyRate.sourceCurrencyCode"/>',
										width : 80,
										align : 'center'
									} 
									,{
										field : 'currencyRate',
										title : '<spring:message code="TmsMdCurrencyRate.currencyRate"/>',
										width : 80,
										align : 'center'
									} 
									,{
										field : 'targetCurrencyCode',
										title : '<spring:message code="TmsMdCurrencyRate.targetCurrencyCode"/>',
										width : 80,
										align : 'center'
									}                           
								                          
									,{
										field : 'exchangeDate',
										title : '<spring:message code="TmsMdCurrencyRate.exchangeDate"/>',
										width : 80,
										align : 'center'
									}                           
									                          
									] ],
							toolbar : [ {
								text : '新增',
								iconCls : 'icon-add',
								handler : function() {
									TmsMdCurrencyRateAdd();
								}
							}, '-', {
								text : '编辑',
								iconCls : 'icon-edit',
								handler : function() {
									TmsMdCurrencyRateEdit();
								}
							}, '-', {
								text : '删除',
								iconCls : 'icon-remove',
								handler : function() {
									TmsMdCurrencyRateRemove();
								}
							}],
							onLoadSuccess : function() {
								$('#TmsMdCurrencyRateInit_dataGrid').datagrid(
										'clearSelections')
							},
							onClickRow : function(index, data) {
								var row = $('#TmsMdCurrencyRateInit_dataGrid')
										.datagrid('getSelected');
								if (row) {
									loadSaveFormData(row);
								}
							}
						});
		//设置分页控件	
		var p = $('#TmsMdCurrencyRateInit_dataGrid').datagrid('getPager');
		$(p).pagination(
		{
			pageSize : 10,//每页显示的记录条数，默认为10           
			//pageList: [10,20,30],//可以设置每页记录条数的列表           
			beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
			afterPageText : '<spring:message code="pagination.afterPageText"/>',
			displayMsg : '<spring:message code="pagination.displayMsg"/>',
			onSelectPage : function(pageNumber, pageSize) {//分页触发		
				find(pageNumber, pageSize);
				SearchTmsMdCurrencyRate();
			}
		});
	});
	function find(pageNumber, pageSize) {
		$('#TmsMdCurrencyRate_searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		SearchTmsMdCurrencyRate();
	}
	function SearchTmsMdCurrencyRate() {
		$("#TmsMdCurrencyRateInit_dataGrid").datagrid("loading");
		$('#TmsMdCurrencyRate_searchform').form('submit', {
			url : '${vat}/tmsMdCurrencyRate/loadTmsMdCurrencyRatePage.do',
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(result) {
				//数据加载以及绑定
				var result = $.parseJSON(result);
			
				$("#TmsMdCurrencyRateInit_dataGrid").datagrid('loadData', result);			
				$("#TmsMdCurrencyRateInit_dataGrid").datagrid("loaded");
			}
		});
	}
	function getHistory() {
		//	alert(0);
		//$("#TmsMdCurrencyRateInit_dataGrid").datagrid("loading");
		$('#TmsMdCurrencyRate_searchform').form('submit', {
			url : '${vat}/tmsMdCurrencyRate/loadhistory.do',
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
	function searchTmsMdCurrencyRate() {
		$("#history_dlg").dialog('open').dialog('setTitle',"<spring:message code='TmsMdCurrencyRate.history'/>");
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
	function TmsMdCurrencyRateAdd() {
		clearSaveForm();
		$("#TmsMdCurrencyRate_addOrEdit_dlg").dialog('open').dialog('setTitle',"<spring:message code='client.dialog.clientadd.title'/>");
		$("#TmsMdCurrencyRate_editform").form('load', {
			clientDate : myformatter(new Date())
		});
	}
	function TmsMdCurrencyRateEdit() {
		if ($('#TmsMdCurrencyRateInit_dataGrid').datagrid('getChecked').length > 1) {
			$.messager.alert("操作提示", "请选择一条数据进行修改");
		} else {
			clearSaveForm();
			var row_list = $('#TmsMdCurrencyRateInit_dataGrid').datagrid('getChecked');
		//	var rows = $('#TmsMdCurrencyRateInit_dataGrid').datagrid('getData');
		//	alert(rows[0].CurrencyRate);
			if (row_list) {
				var id = '';
				$.each(row_list, function(index, item) {
					if (index == 0) {
						id = item.id;
					}
				});
				if (id != '') {
					$.post('${vat}/tmsMdCurrencyRate/loadModifyTmsMdCurrencyRate.do',
						{
							id : id
						},
						function(result) {
							if (result.status == '0') {
								$("#TmsMdCurrencyRate_addOrEdit_dlg").dialog('open').dialog('setTitle',"<spring:message code='client.dialog.clientedit.title'/>");
								$("#TmsMdCurrencyRate_editform").form('load',result.data);
							} else if (result.success == '1') {
								$.messager.alert('<spring:message code="system.alert"/>',result.errorMsg);
								SearchTmsMdCurrencyRate();
								clearSaveForm();
								$('#TmsMdCurrencyRateInit_dataGrid').dialog('close');
							}
						}, 'json');
				} else {
					$.messager.alert('<spring:message code="system.alert"/>','请选择需要编辑的数据');
				}
			}
		}
	}
	function TmsMdCurrencyRateEditSave() {
		
		//alert($("#currencyRateInDialog").textbox("getValue"));
		
		$.messager.confirm("<spring:message code='client.datacommit.formsave.confirm.title'/>","<spring:message code='client.datacommit.formsave.confirm.text'/>",
		function(result) {
			if (result) {
				$('#TmsMdCurrencyRate_editform').form(
				'submit',
				{
					url : '${vat}/tmsMdCurrencyRate/saveTmsMdCurrencyRate.do',
					onSubmit : function(param) {
						
						param.crrencyRate = $("#currencyRateInDialog").textbox("getValue");
						return $(this).form('enableValidation').form('validate');
					},
					success : function(result) {
						var result = $.parseJSON(result);
						if (result.status == '0') {
							clearSaveForm();
							SearchTmsMdCurrencyRate();
							$('#TmsMdCurrencyRate_addOrEdit_dlg').dialog('close');
							$.messager.alert('<spring:message code="system.alert"/>','保存成功');
						}

					}
				});
			}
		});

	}
	//删除一条记录

	function TmsMdCurrencyRateRemove() {
		if (($('#TmsMdCurrencyRateInit_dataGrid').datagrid('getChecked').length == 0)
				|| ($('#TmsMdCurrencyRateInit_dataGrid').datagrid('getChecked').length > 1)) {
			$.messager.alert("操作提示", "请选择需要删除的数据");
		} else {
			$.messager.confirm("<spring:message code='client.datacommit.delete.confirm.title'/>","<spring:message code='client.datacommit.delete.confirm.text'/>",
			function(result) {
				if (result) {
					var row_list = $(
							'#TmsMdCurrencyRateInit_dataGrid')
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
										url : "${vat}/tmsMdCurrencyRate/removeTmsMdCurrencyRates.do",
										type : "POST",
										async : true,
										data : urlString, //不能直接写成 {id:"123",code:"tomcat"}  
										dataType : "json",
										// contentType: "charset=utf-8",  
										cache : false,
										success : function(
												result) {
											SearchTmsMdCurrencyRate();
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
		$('#TmsMdCurrencyRate_editform').form('clear');
	}
	function clearSaveFormT() {
		$('#TmsMdCurrencyRate_searchform_table').form('clear');
	}
	
	
	
</script>
</html>