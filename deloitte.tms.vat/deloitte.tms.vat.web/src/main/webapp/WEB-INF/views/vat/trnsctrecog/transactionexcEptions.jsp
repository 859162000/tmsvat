<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/common/global.jsp"%>
<%@ taglib uri="/tags/input" prefix="input"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no"
	id="layoutid">
	<div region="north" split="true" border="false"
		style="overflow: hidden; height: 28%;" data-options="region:'west'">
		<div class="easyui-panel" title="快速搜索"
			style="width: 100%; height: 100%;" data-options="collapsible: true">
			<form id="transactionException_searchform" method="post" scroll="no">
				<input type="hidden" name="tips" value="0">
				<table cellpadding="5" align="center" id="dga"
					style="text-align: center; width: 100%">
					<tr>
						<td>组织:</td>
						<td><select id="orgId_id" name="orgId"
							class="easyui-combogrid" style="width: 150px;"
							data-options="editable:false"></select></td>
						<td>来源系统:</td>
						<td><input:select easyuiStyle="width:150px;"
								id="customerManageInit_searchform_custLegalEntityType"
								name="custLegalEntityType"
								value="$customerManageInit_searchform_custLegalEntityType"
								easyuiClass="easyui-combobox">
								<option value="0">---------请选择-----------</option>
								<input:systemStatusOption
									parentCode="VAT_TRX_AFFIRM_DATA_SOURCE" />
							</input:select></td>
						<td>客户编号：</td>
						<td><input id="customerName_id" class="easyui-textbox"
							type="text" style="width: 150px;" name="customerName" value="">
						</td>

						<td>涉税交易类型:</td>
						<td><select id="transactiontypeId_id"
							name="transactiontypeId" class="easyui-combogrid"
							style="width: 150px;" data-options="editable:false"></select></td>
					</tr>
					<tr>
						<td>交易批次号:</td>
						<td><input id="trxBatchNum_id" class="easyui-textbox"
							type="text" style="width: 150px;" name="trxBatchNum" value="">
						</td>
						<td>交易流水号:</td>
						<td><input id="trxNumber_id" class="easyui-textbox"
							type="text" style="width: 150px;" name="trxNumber" value="">
						</td>

						<td>记账开始日期:</td>
						<td><input class="easyui-datebox" type="text"
							style="width: 150px;" name="trxBeginDate"></input></td>
						<td>记账结束日期:</td>
						<td><input class="easyui-datebox" type="text"
							style="width: 150px;" name="trxEndDate"></input></td>
					</tr>
					<tr style="background-color: #E0ECFF; width: 100%">
						<td colspan="8" align="center"><a href="#" id="searchbtn"
							class="easyui-linkbutton" data-options="iconCls:'icon-search'"
							style="width: 80px" onclick="Search()"> <spring:message
									code="button.Search" />
						</a> <a href="#" class="easyui-linkbutton" style="width: 80px"
							onclick="$('#transactionException_searchform').form('reset')">
								<spring:message code="button.Clear" />
						</a></td>
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

			<table id="transactionException_dataGrid"
				style="width: 100%; height: 100%; text-align: center;">

			</table>
		</div>

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
			for (var i = 0; i < fields.length; i++) {
				var fildOption = grid.datagrid('getColumnOption', fields[i]);
				if (!fildOption.hidden) {
					$('<div iconCls="'+okCls+'" field="' + fields[i] + '"/>')
							.html(fildOption.title).appendTo(tmenu);
				} else {
					$('<div iconCls="'+emptyCls+'" field="' + fields[i] + '"/>')
							.html(fildOption.title).appendTo(tmenu);
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
				$('#transactionException_searchform').form(
						//读取记录填充到表单中。数据参数可以是一个字符串或一个对象类型，如果是字符串则作为远程URL，否则作为本地记录。
						'load',
						{
							pageNumber : $('#transactionException_dataGrid')
									.datagrid('options').pageNumber,
							pageSize : $('#transactionException_dataGrid')
									.datagrid('options').pageSize
						});
				Search();
			});

	//初始化异常信息显示表格
	$("#transactionException_dataGrid").datagrid({
		loadMsg : "<spring:message code='client.datagrid.dataloding'/>",
		striped : true,//显示斑马线效果
		singleSelect : false, //多选
		collapsible : false,//可折叠  
		fitColumns : false,//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		nowrap : false,//换行
		iconCls : 'icon icon-list',//图标
		idField : 'id',//交易认定后分录流水ID
		rownumbers : true, //显示行数
		pagination : true,//分页
		url : '', //数据请求地址
		title : '搜索结果',//标题
		border : true,
		remoteSort : false,//定义从服务器对数据进行排序。
		columns : [ [ {
			rowspan : 2,
			field : 'id',
			checkbox : 'true',
			align : 'center',
			sortable : true
		}, {
			rowspan : 2,
			field : 'salasTrxId',
			title : '业务流水id',
			align : 'center',
			sortable : true,
			hidden : true
		}, {
			rowspan : 2,
			field : 'versionId',
			title : '版本号',
			align : 'center',
			sortable : true,
			hidden : true
		},  {
			rowspan : 2,
			field : 'orgCode',
			title : '组织编码',
			width : 150,
			align : 'center',
			sortable : true,
		},{
			rowspan : 2,
			field : 'baseOrg',
			title : '组织名称',
			width : 150,
			align : 'left',
			sortable : true,
			formatter : orgFormat
		}, {
			rowspan : 2,
			field : 'orgId',
			title : '组织id',
			width : 120,
			align : 'center',
			sortable : true,
			hidden : true,
		}, {
			rowspan : 2,
			field : 'periodName',
			title : '所属期间',
			width : 120,
			align : 'center',
			sortable : true
		}, {
			rowspan : 2,
			field : 'trxBatchNum',
			title : '交易批次号',
			width : 120,
			align : 'center',
			sortable : true
		}, {
			rowspan : 2,
			field : 'trxNumber',
			title : '交易流水号',
			width : 120,
			align : 'center',
			sortable : true
		}, {
			rowspan : 2,
			field : 'birthday',
			title : ' 交易分录行号',
			width : 120,
			align : 'center',
			sortable : true
		}, {
			rowspan : 2,
			field : 'sourceCode',
			title : ' 来源系统',
			width : 120,
			align : 'left',
			sortable : true
		}, {
			rowspan : 2,
			field : 'trxDate',
			title : ' 交易时间',
			width : 120,
			align : 'center',
			sortable : true
		}, {
			rowspan : 2,
			field : 'accountDate',
			title : ' 记账日期',
			width : 120,
			align : 'center',
			sortable : true
		}, {
			rowspan : 2,
			field : 'bsnConsolidationCode',
			title : ' 业务数据结构',
			width : 120,
			align : 'center',
			sortable : true
		}, {
			rowspan : 2,
			field : 'bsnConsolidationName',
			title : ' 业务数据结构说明',
			width : 120,
			align : 'center',
			sortable : true
		}, {
			rowspan : 2,
			field : 'accdConsolidationId',
			title : ' 科目组合编码',
			width : 120,
			align : 'center',
			sortable : true
		}, {
			rowspan : 2,
			field : 'accdConsolidationName',
			title : ' 科目组合说明',
			width : 120,
			align : 'center',
			sortable : true
		}, {
			rowspan : 2,
			field : 'taxTrxTypeId',
			title : ' 涉税交易类型名称',
			width : 120,
			align : 'center',
			sortable : true
		}, {
			rowspan : 2,
			field : 'taxRate',
			title : ' 税率（%）',
			width : 120,
			align : 'center',
			sortable : true
		}, {
			rowspan : 2,
			field : 'six',
			title : ' 是否计税',
			width : 120,
			align : 'center',
			sortable : true
		}, {
			rowspan : 2,
			field : 'invoiceRule',
			title : '开票规则',
			width : 120,
			align : 'center',
			sortable : true
		}, {
			rowspan : 2,
			field : 'idnumber',
			title : ' 属地\汇总',
			width : 120,
			align : 'center',
			sortable : true
		}, {
			rowspan : 2,
			field : 'taxBaseId',
			title : '  计税基础',
			width : 120,
			align : 'center',
			sortable : true
		}, {
			rowspan : 2,
			field : 'taxMethod',
			title : ' 计税方法',
			width : 120,
			align : 'center',
			sortable : true
		}, {
			colspan : 3,
			title : '  原币',
			width : 240,
			align : 'center'
		}, {
			rowspan : 2,
			field : 'acountRate',
			title : ' 核算汇率',
			width : 120,
			align : 'center',
			sortable : true
		}, {
			colspan : 3,
			title : ' 本位币',
			width : 240,
			align : 'center'
		}, {
			rowspan : 2,
			field : 'exchangeRate',
			title : ' 开票汇率',
			width : 120,
			align : 'center',
			sortable : true
		}, {
			rowspan : 2,
			field : 'rateDate',
			title : ' 汇率日期',
			width : 120,
			align : 'center',
			sortable : true
		}, {
			rowspan : 2,
			field : 'customerId',
			title : '   客户id',
			width : 120,
			align : 'center',
			sortable : true,
			hidden : true,
		}, {
			rowspan : 2,
			field : 'customerNumber',
			title : '   客户编号',
			width : 120,
			align : 'center',
			sortable : true
		},{
			rowspan : 2,
			field : 'customerName',
			title : '   客户名称',
			width : 400,
			align : 'left',
			sortable : true
		},{
			rowspan : 2,
			field : 'impTrxDate',
			title : '  导入日期',
			width : 120,
			align : 'center',
			sortable : true
		}, ], [ {
			rowspan : 1,
			field : 'originalCurrencyCode',
			title : '币种',
			width : 120,
			align : 'center',
			sortable : true
		}, {
			rowspan : 1,
			field : 'originalCurrencyAmountDr',
			title : ' 借方金额',
			width : 120,
			align : 'center',
			sortable : true
		}, {
			rowspan : 1,
			field : 'originalCurrencyAmountCr',
			title : ' 贷方金额',
			width : 120,
			align : 'center',
			sortable : true
		}, {
			rowspan : 1,
			field : 'currencyCode',
			title : '币种',
			width : 120,
			align : 'center',
			sortable : true
		}, {
			rowspan : 1,
			field : 'currencyAmountDr',
			title : ' 借方金额',
			width : 120,
			align : 'center',
			sortable : true
		}, {
			rowspan : 1,
			field : 'currencyAmountCr',
			title : ' 贷方金额',
			width : 120,
			align : 'center',
			sortable : true
		}, ] ],
		toolbar : [ {
			text : "全选",
			iconCls : 'icon-add',
			handler : function() {
				all();
			}
		}, '-', {
			text : "手工认定",
			iconCls : 'icon-edit',
			handler : function() {
				editException();
			}
		}, /* '-', {
					text : "自动认定",
					iconCls : 'icon-edit',
					handler : function() {
						
					} }*/
		],

	});
	//得到分页控件
	var p = $('#transactionException_dataGrid').datagrid('getPager');
	//设置分页控件	
	$(p).pagination({
		pageSize : 10,//每页显示的记录条数，默认为10           
		beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
		afterPageText : '<spring:message code="pagination.afterPageText"/>',
		displayMsg : '<spring:message code="pagination.displayMsg"/>',
		onSelectPage : function(pageNumber, pageSize) {//用户选择一个新页面的时候触发	
			find(pageNumber, pageSize);//改变后的页数传给后台
			Search();
		}
	});
	//对分页数数据条数改编后进行操作
	function find(pageNumber, pageSize) {
		$('#transactionException_searchform').form('load', {//读取记录填充到表单中。数据参数可以是一个字符串或一个对象类型，如果是字符串则作为远程URL，否则作为本地记录。
			pageNumber : pageNumber,
			pageSize : pageSize
		});
	}

	/**
	 * 全选反选
	 */
	function all() {
		$('#transactionException_dataGrid').datagrid('selectAll');
	}

	/**
	 * 请求查询异常信息
	 */
	function Search() {
		$("#transactionException_dataGrid").datagrid("loading");//显示载入状态。
		$('#transactionException_searchform')
				.form(
						'submit',
						{//提交form表单
							url : '${vat}/tmsCrvatTrxAffirm/loadTransactionExceptionPage.do',
							success : function(result) {//成功之后
								//数据加载以及绑定
								var result = $.parseJSON(result);//将数据格式化成json
								$("#transactionException_dataGrid").datagrid(
										'loadData', result);//加载本地数据，旧的行将被移除。
								$("#transactionException_dataGrid").datagrid(
										"loaded");//隐藏载入状态。
							}
						});
	}

	function editException() {//手工
		if ($('#transactionException_dataGrid').datagrid('getChecked').length == 0) {
			$.messager.alert("操作提示", "请选择数据进行认定");
		} else {
			clearSaveForm();
			var row_list = $('#transactionException_dataGrid').datagrid(
					'getChecked');
			if (row_list) {
				var ids = [];
				var orgids = [];
				var versionId = [];
				$.each(row_list, function(index, item) {
					ids.push(item.salasTrxId);
					orgids.push(item.orgId);
					versionId.push(item.versionId);
				});

				if (id != '') {
					$
							.ajax({
								url : "${vat}/tmsCrvatTrxAffirm/loadTransactionException.do",
								dataType : "json",
								cache : false,
								data : 'ids=' + ids + '&orgids=' + orgids
										+ "&versionId=" + versionId,
								success : function(object) {
									if (object.success == 'success') {
										Search();
									}
								}

							});
					$('#transactionException_dataGrid').datagrid(
							'clearSelections');
				} else {
					$.messager.alert('<spring:message code="system.alert"/>',
							'请选择需要编辑的数据');
				}
			}
		}
	}

	function clearSaveForm() {
		$('#editExceptionForm').form('clear');
	}

	/**
	 * 涉税交易类型下拉框数据处理
	 */
	function transactiontype_id(data) {
		$("#transactiontypeId_id")
				.combogrid(
						{
							panelWidth : 500,
							panelHeight : 300,
							pagination : true,//分页
							hideColumn : 'id',
							url : "${vat}/tmsCrvatTrxAffirm/transactiontype_id.do",
							queryParams : {
								customerNameInSendFormid : data,
							},
							toolbar : [
									{
										text : '<input type="text" id="customerNameInSendFormid"/>'
									},
									{
										text : "查询",
										iconCls : 'icon-search',
										handler : function() {

											var customerNameInSendFormid = $(
													"#customerNameInSendFormid")
													.val();//得到输入框的值
											transactiontype_id(customerNameInSendFormid);
											$("#transactiontypeId_id")
													.combogrid('hidePanel');
											
											$("#transactiontypeId_id")
													.combogrid('showPanel');
											$("#transactiontypeId_id").combogrid('grid').datagrid(
											'options').onClickRow = function(index, row) {
										search = false;
										$("#transactiontypeId_id").combogrid('hidePanel');
										$("#transactiontypeId_id").combogrid('setValue',
												row.id);
										$("#transactiontypeId_id").combogrid('setText',
												row.taxTrxTypeName);
									}
										}
									}, '-' ],
							columns : [ [ {
								field : 'taxTrxTypeCode',
								title : "交易类型编码",
								width : 250
							}, {
								field : 'taxTrxTypeName',
								title : "交易类型名称",
								width : 250
							}, {
								field : 'id',
								title : "交易类型名称id",
								width : 150,
								hidden : true,
							} ] ]
						});
	}

	/**
	 * 组织下拉列表数据生成
	 */
	function organization_id(data) {
		$("#orgId_id")
				.combogrid(
						{
							panelWidth :500,
							panelHeight : 300,
							pagination : true,//分页
							hideColumn : 'id',
							url : "${vat}/tmsCrvatTrxAffirm/organization_id.do",
							queryParams : {
								searchivaluesetencoding_id : data,
							},
							toolbar : [
									{
										text : '<input type="text" id="organizationInput_id"/>'
									},
									{
										text : "查询",
										iconCls : 'icon-search',
										handler : function() {
											var organizationInput_id = $(
													"#organizationInput_id")
													.val();//得到输入框的值
											organization_id(organizationInput_id);
											$("#orgId_id").combogrid(
													'hidePanel');
											$("#orgId_id").combogrid(
													'showPanel');
											$("#orgId_id").combogrid('grid').datagrid('options').onClickRow = function(
													index, row) {
												search = false;
												$("#orgId_id").combogrid('hidePanel');
												$("#orgId_id").combogrid('setValue', row.id);
												$("#orgId_id").combogrid('setText', row.orgName);
											}

										}
									}, '-' ],
							columns : [ [ {
								field : 'id',
								title : "组织id",
								width : 150,
								hidden : true,
							}, {
								field : 'orgName',
								title : "组织名称",
								width : 250
							}, {
								field : 'orgCode',
								title : "组织编码",
								width : 250
							} ] ]
						});
	}

	function orgFormat(val, row) {
		if (val)
			return val.orgName;
		else
			return "";
	}

	/**
	 * 初始化下拉列表数据
	 */
	$(document)
			.ready(
					function() {
						transactiontype_id('');
						organization_id('');
						$("#orgId_id").combogrid('grid').datagrid('options').onClickRow = function(
								index, row) {
							search = false;
							$("#orgId_id").combogrid('hidePanel');
							$("#orgId_id").combogrid('setValue', row.id);
							$("#orgId_id").combogrid('setText', row.orgName);
						}

						$("#transactiontypeId_id").combogrid('grid').datagrid(
								'options').onClickRow = function(index, row) {
							search = false;
							$("#transactiontypeId_id").combogrid('hidePanel');
							$("#transactiontypeId_id").combogrid('setValue',
									row.id);
							$("#transactiontypeId_id").combogrid('setText',
									row.taxTrxTypeName);
						}

					});
</script>


</html>