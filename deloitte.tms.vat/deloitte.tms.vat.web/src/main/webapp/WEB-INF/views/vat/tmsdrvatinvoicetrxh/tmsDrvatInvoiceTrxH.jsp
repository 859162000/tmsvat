
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
		style="overflow: hidden; height: 18%;">
		<div class="easyui-panel" title=""
			style="width: 100%; height:100%;">
			<form id="TmsDrvatInvoiceTrxH_searchform" method="post" scroll="no">
				<table style="text-align:center;width:100%;">
					<tr>
						<td>业务发票类型：</td>
						<td><input id="invoiceBusinessCategoryId"
							class="easyui-textbox" type="text" style="width: 150px;"
							name="invoiceBusinessCategory" value=""></input></td>
						<td>发票批次：</td>
						<td><input id="invoiceTrxBatchNumberId"
							class="easyui-textbox" type="text" style="width: 150px;"
							name="invoiceTrxBatchNumber" value=""></input></td>
						<td>申请部门：</td>
						<td><input id="requestionDeptId" class="easyui-textbox"
							type="text" style="width: 150px;" name="requestionDept" value=""></input></td>
						<td>组织ID：</td>
						<td><input id="orgIdId" class="easyui-textbox" type="text"
							style="width: 150px;" name="orgId" value=""></input></td>
					</tr>
					<tr>
						<td>发票类型:</td>
						<td><input id="invoiceCategoryId" class="easyui-textbox"
							type="text" style="width: 150px;" name="invoiceCategory" value=""></input></td>
						<td>申请开始时间：</td>
						<td><input id="startRequestionDateId" class="easyui-datebox"
							type="text" style="width:150px;" name="startRequestionDate"
							value=""></input></td>
						<td>至：<input id="endRequestionDateId" class="easyui-datebox"
							type="text" style="width:150px;" name="endRequestionDate" value=""></input></td>
					</tr>
					<tr >
						<td colspan="8" style="background-color:#E0ECFF;"><a href="#" id="searchbtnTmsDrvatInvoiceTrxH"
									class="easyui-linkbutton" data-options="iconCls:'icon-search'"
									style="width: 120px" onclick="SearchTmsDrvatInvoiceTrxH()"><spring:message
										code='TmsDrvatInvoiceTrxH.history' /></a>
						</td>
					</tr>
					<tr style="display: none">
						<td><input id="pageNumber" class="easyui-textbox" type="text"
							style="width: 0px;" name="pageNumber" value=""></input></td>
						<td><input id="pageSize" class="easyui-textbox" type="text"
							style="width: 0px;" name="pageSize" value=""></input></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div data-options="region:'center',border:false"
		style="background-color: #E0ECFF">
		<div style="width: 100%; height: 100%">
			<table class="easyui-datagrid" id="TmsDrvatInvoiceTrxHInit_dataGrid"
				title="<spring:message code='TmsDrvatInvoiceTrxH.title'/>"
				style="width: 100%; height:100%"
				data-options="singleSelect:true,autoRowHeight:false,pagination:true,pageSize:10,remoteSort:false,multiSort:true">
			</table>
		</div>
		
	</div>
	
		<div id="TmsDrvatInvoiceTrxH_addOrEdit_dlg" class="easyui-dialog"
			style="width:100%; height:100%;" closed="true"
			buttons="#TmsDrvatInvoiceTrxH_addOrEdit_dlg_dlg-buttons">
			<form id="TmsDrvatInvoiceTrxH_editform" class="easyui-form"
				method="post" data-options="novalidate:true"
				commandsName="clientManageForm">
				<table>
					<tr>
						<td><spring:message code="TmsDrvatInvoiceTrxH.id" />：</td>
						<td><input id="idInDialog" name="id" style="width: 150px;"
							class="easyui-textbox" type="text"></td>
						<td><spring:message
								code="TmsDrvatInvoiceTrxH.invoiceBusinessCategory" />：</td>
						<td><input id="invoiceBusinessCategoryInDialog"
							name="invoiceBusinessCategory" style="width: 150px;"
							class="easyui-textbox" type="text"></td>
					</tr>
					<tr>
						<td><spring:message
								code="TmsDrvatInvoiceTrxH.invoiceCategory" />：</td>
						<td><input id="invoiceCategoryInDialog"
							name="invoiceCategory" style="width: 150px;"
							class="easyui-textbox" type="text"></td>
						<td><spring:message
								code="TmsDrvatInvoiceTrxH.invoiceTrxBatchNumber" />：</td>
						<td><input id="invoiceTrxBatchNumberInDialog"
							name="invoiceTrxBatchNumber" style="width: 150px;"
							class="easyui-textbox" type="text"></td>
					</tr>
					<tr>
						<td><spring:message code="TmsDrvatInvoiceTrxH.description" />：</td>
						<td><input id="descriptionInDialog" name="description"
							style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td><spring:message code="TmsDrvatInvoiceTrxH.requestionBy" />：</td>
						<td><input id="requestionByInDialog" name="requestionBy"
							style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>
					<tr>
						<td><spring:message code="TmsDrvatInvoiceTrxH.requestionDept" />：</td>
						<td><input id="requestionDeptInDialog" name="requestionDept"
							style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td><spring:message
								code="TmsDrvatInvoiceTrxH.endRequestionDate" />：</td>
						<td><input id="endRequestionDateInDialog"
							name="endRequestionDate" style="width: 150px;"
							class="easyui-textbox" type="text"></td>
					</tr>
					<tr>
						<td><spring:message
								code="TmsDrvatInvoiceTrxH.startRequestionDate" />：</td>
						<td><input id="startRequestionDateInDialog"
							name="startRequestionDate" style="width: 150px;"
							class="easyui-textbox" type="text"></td>
						<td><spring:message code="TmsDrvatInvoiceTrxH.legalEntityId" />：</td>
						<td><input id="legalEntityIdInDialog" name="legalEntityId"
							style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>
					<tr>
						<td><spring:message code="TmsDrvatInvoiceTrxH.orgId" />：</td>
						<td><input id="orgIdInDialog" name="orgId"
							style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td><spring:message code="TmsDrvatInvoiceTrxH.sourceCode" />：</td>
						<td><input id="sourceCodeInDialog" name="sourceCode"
							style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>
					<tr>
						<td><spring:message code="TmsDrvatInvoiceTrxH.approvalDate" />：</td>
						<td><input id="approvalDateInDialog" name="approvalDate"
							style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td><spring:message code="TmsDrvatInvoiceTrxH.approvalBy" />：</td>
						<td><input id="approvalByInDialog" name="approvalBy"
							style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>
					<tr>
						<td><spring:message code="TmsDrvatInvoiceTrxH.approvalOrgId" />：</td>
						<td><input id="approvalOrgIdInDialog" name="approvalOrgId"
							style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td><spring:message code="TmsDrvatInvoiceTrxH.approvalStatus" />：</td>
						<td><input id="approvalStatusInDialog" name="approvalStatus"
							style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>
					<tr>
						<td><spring:message code="TmsDrvatInvoiceTrxH.wfTaskId" />：</td>
						<td><input id="wfTaskIdInDialog" name="wfTaskId"
							style="width: 150px;" class="easyui-textbox" type="text"></td>
						<td><spring:message code="TmsDrvatInvoiceTrxH.approvalDesc" />：</td>
						<td><input id="approvalDescInDialog" name="approvalDesc"
							style="width: 150px;" class="easyui-textbox" type="text"></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="TmsDrvatInvoiceTrxH_addOrEdit_dlg_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" onclick="TmsDrvatInvoiceTrxHEditSave()"><spring:message
					code="button.Save" /></a> <a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#TmsDrvatInvoiceTrxH_addOrEdit_dlg').dialog('close')"><spring:message
					code="button.Close" /></a>
		</div>
		<div id="history_dlg" class="easyui-dialog"
			style="width: 800px; height: 500px;" closed="true">
			<table class="easyui-datagrid" id="history_dataGrid"
				title="<spring:message code='TmsDrvatInvoiceTrxH.title'/>"
				style="width: 100%; height: 88%"
				data-options="singleSelect:true,autoRowHeight:false,remoteSort:false,multiSort:true">
			</table>
		</div>
	
	
</body>
<script type="text/javascript">


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

	$(document).ready(
			function() {
				$('#TmsDrvatInvoiceTrxH_searchform').form(
						'load',
						{
							pageNumber : $('#TmsDrvatInvoiceTrxHInit_dataGrid')
									.datagrid('options').pageNumber,
							pageSize : $('#TmsDrvatInvoiceTrxHInit_dataGrid')
									.datagrid('options').pageSize
						});
				SearchTmsDrvatInvoiceTrxH();
			});
	$(function() {
		$('#TmsDrvatInvoiceTrxHInit_dataGrid')
				.datagrid(
						{
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
										checkbox : true,
										width : 2
									},
									{
										field : 'id',
										title : '<spring:message code="TmsDrvatInvoiceTrxH.id"/>',
										width : 130,
										align : 'center',
										sortable:true
									},
									{
										field : 'invoiceBusinessCategory',
										title : '<spring:message code="TmsDrvatInvoiceTrxH.invoiceBusinessCategory"/>',
										width : 130,
										align : 'center',
										sortable:true
									},
									{
										field : 'invoiceCategory',
										title : '<spring:message code="TmsDrvatInvoiceTrxH.invoiceCategory"/>',
										width : 130,
										align : 'center',
										sortable:true
									},
									{
										field : 'invoiceTrxBatchNumber',
										title : '<spring:message code="TmsDrvatInvoiceTrxH.invoiceTrxBatchNumber"/>',
										width : 130,
										align : 'center',
										sortable:true
									},
									{
										field : 'description',
										title : '<spring:message code="TmsDrvatInvoiceTrxH.description"/>',
										width : 130,
										align : 'center',
										sortable:true
									},
									{
										field : 'requestionBy',
										title : '<spring:message code="TmsDrvatInvoiceTrxH.requestionBy"/>',
										width : 130,
										align : 'center',
										sortable:true
									},
									{
										field : 'requestionDept',
										title : '<spring:message code="TmsDrvatInvoiceTrxH.requestionDept"/>',
										width : 130,
										align : 'center',
										sortable:true
									},
									{
										field : 'endRequestionDate',
										title : '<spring:message code="TmsDrvatInvoiceTrxH.endRequestionDate"/>',
										width : 130,
										align : 'center',
										sortable:true
									},
									{
										field : 'startRequestionDate',
										title : '<spring:message code="TmsDrvatInvoiceTrxH.startRequestionDate"/>',
										width : 130,
										align : 'center',
										sortable:true
									},
									{
										field : 'legalEntityId',
										title : '<spring:message code="TmsDrvatInvoiceTrxH.legalEntityId"/>',
										width : 130,
										align : 'center',
										sortable:true
									},
									{
										field : 'orgId',
										title : '<spring:message code="TmsDrvatInvoiceTrxH.orgId"/>',
										width : 130,
										align : 'center',
										sortable:true
									},
									{
										field : 'sourceCode',
										title : '<spring:message code="TmsDrvatInvoiceTrxH.sourceCode"/>',
										width : 130,
										align : 'center',
										sortable:true
									},
									{
										field : 'approvalDate',
										title : '<spring:message code="TmsDrvatInvoiceTrxH.approvalDate"/>',
										width : 130,
										align : 'center',
										sortable:true
									},
									{
										field : 'approvalBy',
										title : '<spring:message code="TmsDrvatInvoiceTrxH.approvalBy"/>',
										width : 130,
										align : 'center',
										sortable:true
									},
									{
										field : 'approvalOrgId',
										title : '<spring:message code="TmsDrvatInvoiceTrxH.approvalOrgId"/>',
										width : 130,
										align : 'center',
										sortable:true
									},
									{
										field : 'approvalStatus',
										title : '<spring:message code="TmsDrvatInvoiceTrxH.approvalStatus"/>',
										width : 130,
										align : 'center',
										sortable:true
									},
									{
										field : 'wfTaskId',
										title : '<spring:message code="TmsDrvatInvoiceTrxH.wfTaskId"/>',
										width : 130,
										align : 'center',
										sortable:true
									},
									{
										field : 'approvalDesc',
										title : '<spring:message code="TmsDrvatInvoiceTrxH.approvalDesc"/>',
										width : 130,
										align : 'center',
										sortable:true
									} ] ],
							toolbar : [ {
								text : '新增',
								iconCls : 'icon-add',
								handler : function() {
									TmsDrvatInvoiceTrxHAdd();
								}
							}, '-', {
								text : '编辑',
								iconCls : 'icon-edit',
								handler : function() {
									TmsDrvatInvoiceTrxHEdit();
								}
							}, '-', {
								text : '删除',
								iconCls : 'icon-remove',
								handler : function() {
									TmsDrvatInvoiceTrxHRemove();
								}
							} ],
							onLoadSuccess : function() {
								$('#TmsDrvatInvoiceTrxHInit_dataGrid')
										.datagrid('clearSelections')
							},
							onClickRow : function(index, data) {
								var row = $('#TmsDrvatInvoiceTrxHInit_dataGrid')
										.datagrid('getSelected');
								if (row) {
									loadSaveFormData(row);
								}
							}
						});
		//设置分页控件	
		var p = $('#TmsDrvatInvoiceTrxHInit_dataGrid').datagrid('getPager');
		$(p).pagination(
						{
							pageSize : 10,//每页显示的记录条数，默认为10           
							//pageList: [10,20,30],//可以设置每页记录条数的列表           
							beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
							afterPageText : '<spring:message code="pagination.afterPageText"/>',
							displayMsg : '<spring:message code="pagination.displayMsg"/>',
							onSelectPage : function(pageNumber, pageSize) {//分页触发		
								find(pageNumber, pageSize);
								SearchTmsDrvatInvoiceTrxH();
							}
						});
	});
	function find(pageNumber, pageSize) {
		$('#TmsDrvatInvoiceTrxH_searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		SearchTmsDrvatInvoiceTrxH();
	}
	function SearchTmsDrvatInvoiceTrxH() {
		$("#TmsDrvatInvoiceTrxHInit_dataGrid").datagrid("loading");
		$('#TmsDrvatInvoiceTrxH_searchform')
				.form(
						'submit',
						{
							url : '${vat}/tmsDrvatInvoiceTrxH/loadTmsDrvatInvoiceTrxHPage.do',
							onSubmit : function() {
								return $(this).form('enableValidation').form(
										'validate');
							},
							success : function(result) {
								//数据加载以及绑定
								var result = $.parseJSON(result);
								$("#TmsDrvatInvoiceTrxHInit_dataGrid")
										.datagrid('loadData', result.data);
								$("#TmsDrvatInvoiceTrxHInit_dataGrid")
										.datagrid("loaded");
							}
						});
	}
	function getHistory() {
		//	alert(0);
		//$("#TmsDrvatInvoiceTrxHInit_dataGrid").datagrid("loading");
		$('#TmsDrvatInvoiceTrxH_searchform').form('submit', {
			url : '${vat}/tmsDrvatInvoiceTrxH/loadhistory.do',
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
	function searchTmsDrvatInvoiceTrxH() {
		$("#history_dlg").dialog('open').dialog('setTitle',
				"<spring:message code='TmsDrvatInvoiceTrxH.history'/>");
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
	function TmsDrvatInvoiceTrxHAdd() {
		clearSaveForm();
		$("#TmsDrvatInvoiceTrxH_addOrEdit_dlg").dialog('open').dialog(
				'setTitle',
				"<spring:message code='client.dialog.clientadd.title'/>");
		$("#TmsDrvatInvoiceTrxH_editform").form('load', {
			clientDate : myformatter(new Date())
		});
	}
	function TmsDrvatInvoiceTrxHEdit() {
		if ($('#TmsDrvatInvoiceTrxHInit_dataGrid').datagrid('getChecked').length > 1) {
			$.messager.alert("操作提示", "请选择一条数据进行修改");
		} else {
			clearSaveForm();
			var row_list = $('#TmsDrvatInvoiceTrxHInit_dataGrid').datagrid(
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
									'${vat}/tmsDrvatInvoiceTrxH/loadModifyTmsDrvatInvoiceTrxH.do',
									{
										id : id
									},
									function(result) {
										if (result.status == '0') {
											$(
													"#TmsDrvatInvoiceTrxH_addOrEdit_dlg")
													.dialog('open')
													.dialog('setTitle',
															"<spring:message code='client.dialog.clientedit.title'/>");
											$("#TmsDrvatInvoiceTrxH_editform")
													.form(
															'load',
															result.taskShceduleForm);
										} else if (result.success == '1') {
											$.messager
													.alert(
															'<spring:message code="system.alert"/>',
															result.errorMsg);
											SearchTmsDrvatInvoiceTrxH();
											clearSaveForm();
											$(
													'#TmsDrvatInvoiceTrxHInit_dataGrid')
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
	function TmsDrvatInvoiceTrxHEditSave() {
		$.messager
				.confirm(
						"<spring:message code='client.datacommit.formsave.confirm.title'/>",
						"<spring:message code='client.datacommit.formsave.confirm.text'/>",
						function(result) {
							if (result) {
								$('#TmsDrvatInvoiceTrxH_editform')
										.form(
												'submit',
												{
													url : '${vat}/tmsDrvatInvoiceTrxH/saveTmsDrvatInvoiceTrxH.do',
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
															SearchTmsDrvatInvoiceTrxH();
															$(
																	'#TmsDrvatInvoiceTrxH_addOrEdit_dlg')
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

	function TmsDrvatInvoiceTrxHRemove() {
		if (($('#TmsDrvatInvoiceTrxHInit_dataGrid').datagrid('getChecked').length == 0)
				|| ($('#TmsDrvatInvoiceTrxHInit_dataGrid').datagrid(
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
											'#TmsDrvatInvoiceTrxHInit_dataGrid')
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
														url : "${vat}/tmsDrvatInvoiceTrxH/removeTmsDrvatInvoiceTrxHs.do",
														type : "POST",
														async : true,
														data : urlString, //不能直接写成 {id:"123",code:"tomcat"}  
														dataType : "json",
														// contentType: "charset=utf-8",  
														cache : false,
														success : function(
																result) {
															SearchTmsDrvatInvoiceTrxH();
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
		$('#TmsDrvatInvoiceTrxH_editform').form('clear');
	}
</script>
</html>