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
				<input type="hidden" name="tips" value="1">
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
		style="width: 100%; height: 100%; background-color: #E0ECFF">
		<div style="width: 100%; height: 100%">
			<table id="transactionSuccess_dataGrid"
				style="width: 100%; height: 100%; text-align: center;">
			</table>
		</div>
	</div>

</body>
<script type="text/javascript">
//初始化异常信息显示表格
$("#transactionSuccess_dataGrid").datagrid({
	loadMsg : "<spring:message code='client.datagrid.dataloding'/>",
	striped : true,//显示斑马线效果
	singleSelect : false, //多选
	collapsible : false,//可折叠  
	fitColumns : false,//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
	nowrap : false,//换行
	iconCls : 'icon icon-list',//图标
	idField : 'id',//主键字段
    rownumbers:true, //显示行数
    pagination:true,//分页
    url:'',  //数据请求地址
    title:'搜索结果',//标题
    border: true,
    remoteSort:false,//定义从服务器对数据进行排序。
    columns:[[  
            {rowspan:2,field:'id',checkbox:'true',align : 'center'},
            {
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
    		},
            {rowspan:2,field:'baseOrg',title:'组织',width:150,align : 'left',formatter:orgFormat},
            {rowspan:2,field:'periodName',title:'所属期间',width:100,align : 'center'},
            {rowspan:2,field:'trxBatchNum',title:'交易批次号',width:100,align : 'center'} ,
            {rowspan:2,field:'trxNumber',title:'交易流水号',width:100,align : 'center'} , 
            {rowspan:2,field:'birthday',title:' 交易分录行号',width:100,align : 'center'} ,
            {rowspan:2,field:'sourceCode',title:' 来源系统',width:100,align : 'left'} ,
            {rowspan:2,field:'trxDate',title:' 交易时间',width:100,align : 'center'},
            {rowspan:2,field:'accountDate',title:' 记账日期',width:100,align : 'center'} ,
            {rowspan:2,field:'bsnConsolidationCode',title:' 业务数据结构',width:100,align : 'center'} , 
            {rowspan:2,field:'bsnConsolidationName',title:' 业务数据结构说明',width:100,align : 'center'},
            {rowspan:2,field:'accdConsolidationId',title:' 科目组合编码',width:100,align : 'center'} ,
            {rowspan:2,field:'accdConsolidationName',title:' 科目组合说明',width:100,align : 'center'},
            
            {
    			rowspan : 2,
    			field : 'taxTrxTypeCode',
    			title : ' 涉税交易类型编码',
    			width : 120,
    			align : 'center',
    			sortable : true
    		}, {
    			rowspan : 2,
    			field : 'taxTrxTypeName',
    			title : ' 涉税交易类型名称',
    			width : 300,
    			align : 'left',
    			sortable : true
    		}, 
            {rowspan:2,field:'taxRate',title:' 税率（%）',width:100,align : 'center'} ,
            {rowspan:2,field:'isAccount',title:' 是否计税',width:100,align : 'center'} , 
            {rowspan:2,field:'invoiceRule',title:'开票规则',width:100,align : 'center'} ,
            {rowspan:2,field:'invoicingType',title:' 属地\汇总',width:100,align : 'center'} ,
            {rowspan:2,field:'taxBaseId',title:'  计税基础',width:100,align : 'center'},
            {rowspan:2,field:'taxMethod',title:' 计税方法',width:100,align : 'center'} ,
            {colspan:3,title:'  原币',width:100,align : 'center'} , 
            {rowspan:2,field:'acountRate',title:' 核算汇率',width:100,align : 'center'},
            {colspan:3,title:' 本位币',width:100,align : 'center'},
            {rowspan:2,field:'exchangeRate',title:' 开票汇率',width:100,align : 'center'} ,
            {rowspan:2,field:'rateDate',title:' 汇率日期',width:100,align : 'center'} ,{
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
    		},
            {rowspan:2,field:'impTrxDate',title:'  导入日期',width:100,align : 'center'} ,
            ],
            [
             {rowspan:1,field:'originalCurrencyCode',title:'币种',width:100,align : 'center'},
             {rowspan:1,field:'originalCurrencyAmountDr',title:' 借方金额',width:100,align : 'center'},
             {rowspan:1,field:'originalCurrencyAmountCr',title:' 贷方金额',width:100,align : 'center'} ,
             {rowspan:1,field:'currencyCode',title:'币种',width:100,align : 'center'},
             {rowspan:1,field:'currencyAmountDr',title:' 借方金额',width:100,align : 'center'},
             {rowspan:1,field:'currencyAmountCr',title:' 贷方金额',width:100,align : 'center'} ,
             ]],
         	/* toolbar : [ {
				text : "认定回滚",
				iconCls : 'icon-edit',
				handler : function() {
					
				}
			}, '-'], */
	
	
	});
//得到分页控件
var p = $('#transactionSuccess_dataGrid').datagrid('getPager');
//设置分页控件	
$(p).pagination({
	pageSize: 10,//每页显示的记录条数，默认为10           
	beforePageText: '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
	afterPageText: '<spring:message code="pagination.afterPageText"/>',           
	displayMsg: '<spring:message code="pagination.displayMsg"/>',
	onSelectPage: function (pageNumber, pageSize) {//用户选择一个新页面的时候触发	
    	 find(pageNumber,pageSize);//改变后的页数传给后台
		 Search(); 
     }
});
//对分页数数据条数改编后进行操作
function find(pageNumber, pageSize) {
	$('#transactionSuccess_searchform').form('load', {//读取记录填充到表单中。数据参数可以是一个字符串或一个对象类型，如果是字符串则作为远程URL，否则作为本地记录。
		pageNumber : pageNumber,
		pageSize : pageSize
	});
}
$(document).ready(
		function() {
			$('#transactionException_searchform').form(//读取记录填充到表单中。数据参数可以是一个字符串或一个对象类型，如果是字符串则作为远程URL，否则作为本地记录。
					'load',
					{
						pageNumber : $('#transactionSuccess_dataGrid')
								.datagrid('options').pageNumber,
						pageSize : $('#transactionSuccess_dataGrid')
								.datagrid('options').pageSize
					});
			Search();
		});
/**
 * 请求查询异常信息
 */
function Search() {
	$("#transactionSuccess_dataGrid").datagrid("loading");
	$('#transactionException_searchform').form('submit', {//提交form表单
		url : '${vat}/tmsCrvatTrxAffirm/loadTmsCrvatTrxAffirmSucessPage.do',
		success : function(result) {//成功之后
			//数据加载以及绑定
			var result = $.parseJSON(result);//将数据格式化成json
			$("#transactionSuccess_dataGrid").datagrid('loadData', result);//加载本地数据，旧的行将被移除。
			$("#transactionSuccess_dataGrid").datagrid("loaded");//隐藏载入状态。
		}
	});
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
function clearSearchform() {

	$('#transactionSuccess_searchform').form('clear');
}

</script>


</html>