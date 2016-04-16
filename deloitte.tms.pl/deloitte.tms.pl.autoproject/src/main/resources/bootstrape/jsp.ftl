<#assign pojo=oneToManyPojo.getOne()>
<#assign many=oneToManyPojo.getMany()>
<#assign ones=oneToManyPojo.getOnes()>
<#assign fields=pojo.getFilterdPojoFields()>
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign declarationNameFirstLetterLower = pojo.importType(pojo.getDeclarationNameFirstLetterLower())>

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/common/global.jsp"%>
<meta charset="UTF-8">
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<div region="north" split="true" border="false" style="overflow: hidden; height: 99%;">
		<div class="easyui-panel" title="" style="width: 100%; height: 12%; background-color: #E0ECFF">
			<form id="${declarationName}_searchform" method="post" scroll="no">
				<table cellpadding="10">	
<#list fields?chunk(4) as row>
			<tr>
	<#list row as field>
		<#if field.getIsSampleField()&&field.getIsNotBaseEntiry()>
			<td><#if field.getComment()?exists>${field.getComment()}<#else>${field.getName()}</#if>：</td>
			<#if field.getHtmlType()?exists&&field.getHtmlType()='date'>
			<td><input id="${field.getName()}Id" class="easyui-datebox" type="text" style="width: 90;" name="${field.getName()}" value=""></input></td>
			<#else>
			 <td><input id="${field.getName()}Id" class="easyui-textbox" type="text" style="width: 150px;" name="${field.getName()}" value=""></input></td>
			</#if>
		</#if>	
	</#list> 
			</tr>                              
</#list> 
					<tr>
						<td>
							<div style="text-align: center; padding: 10px">
								<a href="#" id="searchbtn${declarationName}" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 120px" onclick="Search${declarationName}()"><spring:message code='${declarationName}.history' /></a>
							</div>
						</td>
					</tr>
					<tr style="display: none">
						<td><input id="pageNumber" class="easyui-textbox" type="text" style="width: 0px;" name="pageNumber" value=""></input></td>
						<td><input id="pageSize" class="easyui-textbox" type="text" style="width: 0px;" name="pageSize" value=""></input></td>
					</tr>
				</table>
			</form>
		</div>
		<div style="width: 100%; height: 100%">
			<table class="easyui-datagrid" id="${declarationName}Init_dataGrid" title="<spring:message code='${declarationName}.title'/>" style="width: 100%; height: 88%" data-options="singleSelect:true,autoRowHeight:false,pagination:true,pageSize:10,remoteSort:false,multiSort:true">
			</table>
		</div>
	</div>
	<div data-options="region:'center',border:false"
		style="background-color: #E0ECFF">
		<div id="${declarationName}_addOrEdit_dlg" class="easyui-dialog"
			style="width: 800px; height: 500px;" closed="true"
			buttons="#${declarationName}_addOrEdit_dlg_dlg-buttons">
			<form id="${declarationName}_editform" class="easyui-form" method="post"
				data-options="novalidate:true" commandsName="clientManageForm">
				<table>
<#list fields?chunk(2) as row>
					<tr>
	<#list row as field>
		<#if field.getIsSampleField()&&field.getIsNotBaseEntiry()>
						<td><spring:message code="${declarationName}.${field.getName()}" />：</td>
			<#if field.getHtmlType()?exists&&field.getHtmlType()='date'>
						<td><input id="${field.getName()}InDialog" name="${field.getName()}" style="width: 150px;" class="easyui-textbox" type="text"></td>
			<#else>
						<td><input id="${field.getName()}InDialog" name="${field.getName()}" style="width: 150px;" class="easyui-textbox" type="text"></td>
			</#if>
		</#if>	
	</#list> 
					</tr>                              
</#list> 
				</table>
			</form>
		</div>
		<div id="${declarationName}_addOrEdit_dlg_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="${declarationName}EditSave()"><spring:message code="button.Save" /></a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#${declarationName}_addOrEdit_dlg').dialog('close')"><spring:message code="button.Close" /></a>
		</div>
		<div id="history_dlg" class="easyui-dialog" style="width: 800px; height: 500px;" closed="true">
			<table class="easyui-datagrid" id="history_dataGrid" title="<spring:message code='${declarationName}.title'/>" style="width: 100%; height: 88%" data-options="singleSelect:true,autoRowHeight:false,remoteSort:false,multiSort:true">
			</table>
		</div>
	</div>
</body>
<script type="text/javascript">
$(document).ready(
	function() {
		$('#${declarationName}_searchform').form('load',
			{
				pageNumber : $('#${declarationName}Init_dataGrid').datagrid('options').pageNumber,
				pageSize : $('#${declarationName}Init_dataGrid').datagrid('options').pageSize
			});
			Search${declarationName}();
		});
	$(function() {
		$('#${declarationName}Init_dataGrid')
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
<#list fields as field>
									,{
										field : '${field.getName()}',
										title : '<spring:message code="${declarationName}.${field.getName()}"/>',
										width : 80,
										align : 'center'
									}                           
</#list> 
									] ],
							toolbar : [ {
								text : '新增',
								iconCls : 'icon-add',
								handler : function() {
									${declarationName}Add();
								}
							}, '-', {
								text : '编辑',
								iconCls : 'icon-edit',
								handler : function() {
									${declarationName}Edit();
								}
							}, '-', {
								text : '删除',
								iconCls : 'icon-remove',
								handler : function() {
									${declarationName}Remove();
								}
							}],
							onLoadSuccess : function() {
								$('#${declarationName}Init_dataGrid').datagrid(
										'clearSelections')
							},
							onClickRow : function(index, data) {
								var row = $('#${declarationName}Init_dataGrid')
										.datagrid('getSelected');
								if (row) {
									loadSaveFormData(row);
								}
							}
						});
		//设置分页控件	
		var p = $('#${declarationName}Init_dataGrid').datagrid('getPager');
		$(p).pagination(
		{
			pageSize : 10,//每页显示的记录条数，默认为10           
			//pageList: [10,20,30],//可以设置每页记录条数的列表           
			beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
			afterPageText : '<spring:message code="pagination.afterPageText"/>',
			displayMsg : '<spring:message code="pagination.displayMsg"/>',
			onSelectPage : function(pageNumber, pageSize) {//分页触发		
				find(pageNumber, pageSize);
				Search${declarationName}();
			}
		});
	});
	function find(pageNumber, pageSize) {
		$('#${declarationName}_searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		Search${declarationName}();
	}
	function Search${declarationName}() {
		$("#${declarationName}Init_dataGrid").datagrid("loading");
		$('#${declarationName}_searchform').form('submit', {
			url : '${r"${vat}"}/${pojo.getDeclarationNameFirstLetterLower()}/load${declarationName}Page.do',
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(result) {
				//数据加载以及绑定
				var result = $.parseJSON(result);
				$("#${declarationName}Init_dataGrid").datagrid('loadData', result.data);
				$("#${declarationName}Init_dataGrid").datagrid("loaded");
			}
		});
	}
	function getHistory() {
		//	alert(0);
		//$("#${declarationName}Init_dataGrid").datagrid("loading");
		$('#${declarationName}_searchform').form('submit', {
			url : '${r"${vat}"}/${pojo.getDeclarationNameFirstLetterLower()}/loadhistory.do',
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
	function search${declarationName}() {
		$("#history_dlg").dialog('open').dialog('setTitle',"<spring:message code='${declarationName}.history'/>");
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
	function ${declarationName}Add() {
		clearSaveForm();
		$("#${declarationName}_addOrEdit_dlg").dialog('open').dialog('setTitle',"<spring:message code='client.dialog.clientadd.title'/>");
		$("#${declarationName}_editform").form('load', {
			clientDate : myformatter(new Date())
		});
	}
	function ${declarationName}Edit() {
		if ($('#${declarationName}Init_dataGrid').datagrid('getChecked').length > 1) {
			$.messager.alert("操作提示", "请选择一条数据进行修改");
		} else {
			clearSaveForm();
			var row_list = $('#${declarationName}Init_dataGrid').datagrid('getChecked');
			if (row_list) {
				var id = '';
				$.each(row_list, function(index, item) {
					if (index == 0) {
						id = item.id;
					}
				});
				if (id != '') {
					$.post('${r"${vat}"}/${pojo.getDeclarationNameFirstLetterLower()}/loadModify${declarationName}.do',
						{
							id : id
						},
						function(result) {
							if (result.status == '0') {
								$("#${declarationName}_addOrEdit_dlg").dialog('open').dialog('setTitle',"<spring:message code='client.dialog.clientedit.title'/>");
								$("#${declarationName}_editform").form('load',result.taskShceduleForm);
							} else if (result.success == '1') {
								$.messager.alert('<spring:message code="system.alert"/>',result.errorMsg);
								Search${declarationName}();
								clearSaveForm();
								$('#${declarationName}Init_dataGrid').dialog('close');
							}
						}, 'json');
				} else {
					$.messager.alert('<spring:message code="system.alert"/>','请选择需要编辑的数据');
				}
			}
		}
	}
	function ${declarationName}EditSave() {
		$.messager.confirm("<spring:message code='client.datacommit.formsave.confirm.title'/>","<spring:message code='client.datacommit.formsave.confirm.text'/>",
		function(result) {
			if (result) {
				$('#${declarationName}_editform').form(
				'submit',
				{
					url : '${r"${vat}"}/${pojo.getDeclarationNameFirstLetterLower()}/save${declarationName}.do',
					onSubmit : function() {
						return $(this).form('enableValidation').form('validate');
					},
					success : function(result) {
						var result = $.parseJSON(result);
						if (result.status == '0') {
							clearSaveForm();
							Search${declarationName}();
							$('#${declarationName}_addOrEdit_dlg').dialog('close');
							$.messager.alert('<spring:message code="system.alert"/>','保存成功');
						}

					}
				});
			}
		});

	}
	//删除一条记录

	function ${declarationName}Remove() {
		if (($('#${declarationName}Init_dataGrid').datagrid('getChecked').length == 0)
				|| ($('#${declarationName}Init_dataGrid').datagrid('getChecked').length > 1)) {
			$.messager.alert("操作提示", "请选择需要删除的数据");
		} else {
			$.messager.confirm("<spring:message code='client.datacommit.delete.confirm.title'/>","<spring:message code='client.datacommit.delete.confirm.text'/>",
			function(result) {
				if (result) {
					var row_list = $(
							'#${declarationName}Init_dataGrid')
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
										url : "${r"${vat}"}/${pojo.getDeclarationNameFirstLetterLower()}/remove${declarationName}s.do",
										type : "POST",
										async : true,
										data : urlString, //不能直接写成 {id:"123",code:"tomcat"}  
										dataType : "json",
										// contentType: "charset=utf-8",  
										cache : false,
										success : function(
												result) {
											Search${declarationName}();
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
		$('#${declarationName}_editform').form('clear');
	}
</script>
</html>