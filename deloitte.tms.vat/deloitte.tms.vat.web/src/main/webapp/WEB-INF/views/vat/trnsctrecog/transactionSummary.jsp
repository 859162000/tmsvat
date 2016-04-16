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
			<form id="transactionSummary_searchform" method="post" scroll="no">
				<table>
					<tr>
						<td>交易流水号：</td>
						<td><input id="trxNumberId" class="easyui-textbox"
							type="text" style="width: 150px;" name="trxNumber" value=""></input></td>
						<td>
							<!-- 组织： -->
						</td>
						<td>
							<!-- <input id="operationOrgCodeId" class="easyui-combogrid"
							type="text" style="width: 150px;" name="orgId"
							value=""></input> -->
						</td>
						<td>
							<!-- 交易批次号： -->
						</td>
						<td>
							<!-- <input id="trxBatchNumId" class="easyui-textbox"
							type="text" style="width: 150px;" name="trxBatchNum" value=""></input> -->
						</td>

						<td>
							<!-- 系统来源： -->
						</td>
						<td>
							<!-- <input id="sourceCodeId" class="easyui-textbox"
							type="text" style="width: 150px;" name="sourceCode" value=""></input> -->
						</td>
						<td></td>
					</tr>
					<tr>


						<td colspan="2">
							<div style="text-align: center; padding: 10px">
								<a href="#" id="searchbtn" class="easyui-linkbutton"
									data-options="iconCls:'icon-search'" style="width: 200px"
									onclick="Search()">客户交易汇总数据查询</a>
							</div>
						</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="display: none">
						<td></td>
						<td><input id="pageNumberId" class="easyui-textbox"
							type="text" style="width: 0px;" name="pageNumber" value=""></input></td>
						<td><input id="pageSizeId" class="easyui-textbox" type="text"
							style="width: 0px;" name="pageSize" value=""></input></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</table>
			</form>
		</div>

		<div style="width: 100%; height: 100%">
			<table class="easyui-datagrid" id="transactionSummaryInit_dataGrid"
				title="客户交易汇总数据" style="width: 100%; height: 88%"
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
	</div>
	<div data-options="region:'center',border:false"
		style="background-color: #E0ECFF">
		<div id="taskSchedule_addOrEdit_dlg" class="easyui-dialog"
			style="width: 800px; height: 500px;" closed="true"
			buttons="#taskSchedule_addOrEdit_dlg_dlg-buttons">

			<form id="taskSchedule_editform" class="easyui-form" method="post"
				data-options="novalidate:true" commandsName="clientManageForm">
				<table>
					<tr>
						<td><spring:message code="taskSchedule.jobName" />：</td>
						<td><input id="nameInDialog" name="name"
							style="width: 150px;" class="easyui-textbox" type="text"></td>

					</tr>
					<tr>
						<td><spring:message code="taskSchedule.usedBeanId" />：</td>
						<td><input id="beanIdInDialog" class="easyui-textbox"
							type="text" style="width: 150px;" name="beanId" value=""></input>
						</td>
					</tr>

					<tr>
						<td><spring:message code="taskSchedule.cronExpression" />：</td>
						<td><input id="cronExpressionInDialog" class="easyui-textbox"
							type="text" style="width: 150px;" name="cronExpression" value=""></input>
						</td>
					</tr>
					<tr>
						<td><spring:message code="taskSchedule.comments" />：</td>
						<td><input id="descInDialog" name="desc"
							class="easyui-textbox" type="text" style="width: 150px;"></td>

					</tr>
					<tr style="display: none">
						<td input id="id" name="id" class="easyui-textbox"></td>
						<td><input id="uuid" class="easyui-textbox"
							name="appuseruuid" style="width: 200px"></input></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="taskSchedule_addOrEdit_dlg_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" onclick="taskScheduleEditSave()"><spring:message
					code="button.Save" /></a> <a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#taskSchedule_addOrEdit_dlg').dialog('close')"><spring:message
					code="button.Close" /></a>
		</div>
		<div id="history_dlg" class="easyui-dialog"
			style="width: 800px; height: 500px;" closed="true">
			<table class="easyui-datagrid" id="history_dataGrid"
				title="<spring:message code='taskSchedule.title'/>"
				style="width: 100%; height: 88%"
				data-options="					
					singleSelect:true,
					autoRowHeight:false,				
					remoteSort:false,
				    multiSort:true,
				    pageSize:10
					">
			</table>
		</div>
	</div>
	<div class="easyui-dialog" closed="true" id="fileInputDialog"
		closed="true">
		<div class="easyui-panel" title="Upload File"
			style="width: 400px; padding: 30px 70px 50px 70px">
			<div>请选择需要导入的文件:</div>
			<input class="easyui-filebox" name="file1"
				data-options="prompt:'Choose a file...'" style="width: 100%">
			<div style="text-align: center; padding: 10px">
				<a href="#" id="searchbtn" class="easyui-linkbutton"
					data-options="iconCls:'icon-search'" style="width: 200px"
					onclick="importData()">导入</a>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function importData() {
		alert("in progress");

	}
	$(document).ready(
			function() {
				//pageDataSettingInit();
				//InitCombobox();
				$('#transactionSummary_searchform').form(
						'load',
						{
							pageNumber : $('#transactionSummaryInit_dataGrid')
									.datagrid('options').pageNumber,
							pageSize : $('#transactionSummaryInit_dataGrid')
									.datagrid('options').pageSize
						});
				Search();//初始化table数据

				//	InitComboboxs();//位下拉框添加数据
			});
	$(document).ready(function() {
		//initOrg();

	});

	$(function() {
		$('#transactionSummaryInit_dataGrid').datagrid(
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
					columns : [ [ {
						field : 'ck',
						checkbox : true,
						width : 2
					}, //显示复选框        
					{
						field : 'orgName',
						title : '组织名称',
						width : 80,
						align : 'center'
					}, //显示复选框        
					{
						field : 'orgCode',
						title : '组织编码',
						width : 80,
						align : 'center'
					}, {
						field : 'trxNumber',
						title : '交易流水号',
						width : 80,
						align : 'center'
					}, {
						field : 'trxDate',
						title : '交易日期',
						width : 80,
						align : 'center'
					}, {
						field : 'originalCurrencyCode',
						title : '货币种类',
						width : 80,
						align : 'center'
					},

					{
						field : 'cutomerName',
						title : '客户名称',
						width : 80,
						align : 'center'
					},

					{
						field : 'customerCode',
						title : '客户编码',
						width : 80,
						align : 'center'
					},

					{
						field : 'customerType',
						title : '客户类型',
						width : 80,
						align : 'center',
						formatter : function(value, row, index) {
							return customerTypeFormat(value);
						}

					},

					{
						field : 'capitalAccountNum',
						title : '资金账号',
						width : 80,
						align : 'center'
					},

					{
						field : 'transSegment1',
						title : '交易品种',
						width : 80,
						align : 'center'
					},

					{
						field : 'transSegment2',
						title : '证券类别',
						width : 80,
						align : 'center'
					},

					{
						field : 'attribute7',
						title : '成交金额',
						width : 80,
						align : 'center'
					},

					{
						field : 'originalCurrencyAmountCr',
						title : '手续费收入',
						width : 80,
						align : 'center'
					}
					/*
					 {
						field : 'sourceInvItemModels',
						title : '来源规格型号',
						width : 80,
						align : 'center'
					}, */

					] ],
					toolbar : [ {
						text : '导入数据',
						iconCls : 'icon-add',
						handler : function() {
							importFromExcel();
						}
					} ],
					onLoadSuccess : function() {
						$('#transactionSummaryInit_dataGrid').datagrid(
								'clearSelections');
					},
					onClickRow : function(index, data) {
						var row = $('#transactionSummaryInit_dataGrid')
								.datagrid('getSelected');
						if (row) {
							loadSaveFormData(row);
						}
					}
				});
		//设置分页控件	
		var p = $('#transactionSummaryInit_dataGrid').datagrid('getPager');
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
								//	Search();
							}
						});
	});
	function find(pageNumber, pageSize) {
		$('#transactionSummary_searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		Search();
	}

	function customerTypeFormat(value) {
		if (value == '2') {
			return '公司';
		} else {
			return '个人';
		}
	}

	function initOrg() {
		$('#operationOrgCodeId').combogrid({
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
				text : '组织名称<input type="text" id="orgInSearchBox1"/>'
			}, {
				text : "查询",
				iconCls : 'icon-search',
				handler : function() {
					//findCustomerName();
				}
			}, '-' ]
		});

		//取得分页组件对象
		var pager = $('#operationOrgCodeId').combogrid('grid').datagrid(
				'getPager');

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
									$('#operationOrgCodeId').combogrid("grid")
											.datagrid('options').pageSize = pageSize;
									$('#operationOrgCodeId').combogrid("grid")
											.datagrid('options').pageNumber = pageNumber;
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
		//	InitOrgId();
		var getData = function(page, rows) {
			$('#transactionSummary_searchform').form('load', {
				pageNumber : page,
				pageSize : rows
			});

			$('#transactionSummary_searchform').form(
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
							$('#operationOrgCodeId').combogrid('grid')
									.datagrid('loadData', result.data);
							//	$("#invoiceList_datagrid").datagrid("loaded");
						}
					});
		};

	}

	function InitOrgId() {
		$('#transactionSummary_searchform').form(
				'load',
				{
					pageNumber : $('#operationOrgCodeId').combogrid('grid')
							.datagrid('options').pageNumber,
					pageSize : $('#operationOrgCodeId').combogrid('grid')
							.datagrid('options').pageSize
				});
		$('#transactionSummary_searchform').form(
				'submit',
				{
					url : '${vat}/baseOrg/loadBaseOrgPage.do',
					onSubmit : function() {
						//	return $(this).form('enableValidation')
						//		.form('validate');
					},
					success : function(result) {
						//数据加载以及绑定
						var result = $.parseJSON(result);
						$('#operationOrgCodeId').combogrid('grid').datagrid(
								'loadData', result.data);
						//	$("#invoiceList_datagrid").datagrid("loaded");
					}
				});

	}

	function Search() {
		$("#transactionSummaryInit_dataGrid").datagrid("loading");
		$('#transactionSummary_searchform')
				.form(
						'submit',
						{
							url : '${vat}/tmsCrvatSsTrxAll/loadTmsCrvatSsTrxAllPage.do',
							onSubmit : function() {
								return $(this).form('enableValidation').form(
										'validate');
							},
							success : function(result) {
								//数据加载以及绑定
								var result = $.parseJSON(result);
								$("#transactionSummaryInit_dataGrid").datagrid(
										'loadData', result);
								$("#transactionSummaryInit_dataGrid").datagrid(
										"loaded");
							}
						});
	}
	/* 	function getHistory() {
	 //	alert(0);
	 //$("#transactionSummaryInit_dataGrid").datagrid("loading");
	 $('#transactionSummary_searchform').form('submit', {
	 url : '${vat}/jobs/loadhistory.do',
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
	 } */
	/* function SearchHistory() {
		$("#history_dlg").dialog('open').dialog('setTitle',
				"<spring:message code='taskSchedule.history'/>");

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
	} */
	//添加
	function importFromExcel() {
		$("#fileInputDialog").dialog('open').dialog('setTitle', "文件输入");

	}/* 

		function taskScheduleAdd() {
			clearSaveForm();
			$("#taskSchedule_addOrEdit_dlg").dialog('open').dialog('setTitle',
					"<spring:message code='client.dialog.clientadd.title'/>");
			$("#taskSchedule_editform").form('load', {
				clientDate : myformatter(new Date())
			});
		}
		function taskScheduleEdit() {
			if ($('#transactionSummaryInit_dataGrid').datagrid('getChecked').length > 1) {
				$.messager.alert("操作提示", "请选择一条数据进行修改");
			} else {
				clearSaveForm();
				var row_list = $('#transactionSummaryInit_dataGrid').datagrid(
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
										'${vat}/jobs/loadModifyJobDefinition.do',
										{
											id : id
										},
										function(result) {
											if (result.success == 'true') {
												$("#taskSchedule_addOrEdit_dlg")
														.dialog('open')
														.dialog('setTitle',
																"<spring:message code='client.dialog.clientedit.title'/>");

												$("#taskSchedule_editform").form(
														'load',
														result.taskShceduleForm);
											} else if (result.success == 'false') {
												$.messager
														.alert(
																'<spring:message code="system.alert"/>',
																result.errorMsg);
												Search();
												clearSaveForm();
												$(
														'#transactionSummaryInit_dataGrid')
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
		function taskScheduleEditSave() {
			$.messager
					.confirm(
							"<spring:message code='client.datacommit.formsave.confirm.title'/>",
							"<spring:message code='client.datacommit.formsave.confirm.text'/>",
							function(result) {
								if (result) {
									$('#taskSchedule_editform')
											.form(
													'submit',
													{
														url : '${vat}/jobs/saveJobDefinition.do',
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
																Search();
																$(
																		'#taskSchedule_addOrEdit_dlg')
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

		function taskScheduleRemove() {
			if (($('#transactionSummaryInit_dataGrid').datagrid('getChecked').length == 0)
					|| ($('#transactionSummaryInit_dataGrid')
							.datagrid('getChecked').length > 1)) {
				$.messager.alert("操作提示", "请选择需要删除的数据");
			} else {
				$.messager
						.confirm(
								"<spring:message code='client.datacommit.delete.confirm.title'/>",
								"<spring:message code='client.datacommit.delete.confirm.text'/>",
								function(result) {
									if (result) {
										var row_list = $(
												'#transactionSummaryInit_dataGrid')
												.datagrid('getChecked');
										//var row=$('#clientManageInit_dataGrid').datagrid('getSelected');
										if (row_list) {
											var urlString = "";
											$.each(row_list, function(index, item) {
												if (index == 0)
													urlString += "id=" + item.id;
												else
													urlString += "&id=" + item.id;
											});
											if (urlString != '') {
												$
														.ajax({
															url : "${vat}/jobs/removeJobDefinition.do",
															type : "POST",
															async : true,
															data : urlString, //不能直接写成 {id:"123",code:"tomcat"}  
															dataType : "json",
															// contentType: "charset=utf-8",  
															cache : false,
															success : function(
																	result) {
																Search();
																clearSaveForm();
															}
														});
											}
										}
									}

								});
			}
		}
		function executeImmediately() {
			if ($('#transactionSummaryInit_dataGrid').datagrid('getChecked').length != 1) {
				$.messager.alert("操作提示", "请选择且只选择一条数据");
			} else {
				var row_list = $('#transactionSummaryInit_dataGrid').datagrid(
						'getChecked');
				//var row=$('#clientManageInit_dataGrid').datagrid('getSelected');

				if (row_list) {
					var selectid = row_list[0].id;
					$.ajax({
						url : "${vat}/jobs/exeJob.do",
						type : "POST",
						async : true,
						data : 'selectid=' + selectid, //不能直接写成 {id:"123",code:"tomcat"}  
						dataType : "json",
						// contentType: "charset=utf-8",  
						cache : false,
						success : function(result) {
							$.messager.alert('提示信息', '调用成功');
						}
					});
				}
			}
		}

		//清空dialog内容

		function clearSaveForm() {
			$('#taskSchedule_editform').form('clear');
		} */
</script>
</html>