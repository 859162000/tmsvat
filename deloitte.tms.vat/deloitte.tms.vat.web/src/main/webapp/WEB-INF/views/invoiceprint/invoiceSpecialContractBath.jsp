<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/tags/input" prefix="input"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<%@ include file="/common/global.jsp"%>
</head>
<body class="easyui-layout" id="layoutid" style="overflow-y: hidden"
	scroll="no">
	<%-- <div data-options="region:'east',split:true"
		title="<spring:message code="invoiceprint.reqinfo"/>"
		style="width: 100%;">

		<div class="easyui-layout" style="width: 100%; height: 100%;">

			<div data-options="region:'north'" split="true"
				style="width: 100%; height: 26%;">

				<div class="easyui-layout" style="width: 100%; height: 100%;">

					<div data-options="region:'north'"
						style="width: 100%; height: 100%;">
						<div class="easyui-panel"
							style="width: 100%; height: 100%; background-color: #E0ECFF">
							<form id="newDetailForm" method="post">
								<table style="width: 1000px; height: 100%">
									<tr>

										申请单编号
										<td><spring:message
												code="invoiceSpecialContractBath.save.readyNo" />：</td>
										<td><input class="easyui-textbox"
											id="invoice_print_newSearch_readyNo"
											name="crvatInvoiceReqNumber" readonly="true"
											style="width: 120px;" /></td>
										申请日期
										<td><spring:message
												code="invoiceSpecialContractBath.save.invoiceReqDate" />：</td>
										<td><input id="invoice_print_newSearch_applyTime"
											readonly="true" name="invoiceReqDate" readonly="true"
											class="easyui-datebox" style="width: 120px;" /></td>
										申请状态
										<td><spring:message
												code="invoiceSpecialContractBath.save.readyStatus" />：</td>
										<td><input:select easyuiStyle="width:150px;"
												id="customerManageInit_searchform_custLegalEntityType"
												name="status" easyuiClass="easyui-combobox" value="$status">
												<input:systemStatusOption
													parentCode="VAT_CR_INVOICE_APPFORM_STATUS" />
											</input:select></td>
										合计金额
										<td><spring:message code="invoiceprint.amountAll" />：</td>
										<td><input id="invoice_print_newSearch_amountAll"
											name="invoiceAmount" class="easyui-textbox" readonly="true"
											style="width: 120px;"></td>
									</tr>
									<tr>
										销方纳税人识别号registration_number
										<td><spring:message
												code="invoiceSpecialContractBath.save.registrationNumber" /></td>
										<td><input id="legalEntityCode" name="registrationNumber"
											class="easyui-textbox" style="width: 120px;" readonly="true" /></td>
										销方纳税人名称taxpayerName
										<td><spring:message
												code="invoiceSpecialContractBath.save.taxpayerName" /></td>
										<td><input id="taxpayerName" name="legalEntityName"
											class="easyui-textbox" style="width: 120px;" readonly="true" /></td>
										发票打印组织
										<td><spring:message
												code="invoiceSpecialContractBath.save.orgName" /></td>
										<td><input id="orgNameId" name="orgName"
											class="easyui-textbox" style="width: 150px;" readonly="true" /></td>
										打印终端编号
										<td><spring:message
												code="invoiceSpecialContractBath.save.equipmentNumber" /></td>
										<td><input id="equipmentId" name="equipmentCode"
											class="easyui-textbox" style="width: 120px;" readonly="true" /></td>

									</tr>
									<tr>
										开户银行
										<td><spring:message code="client.bank" />：</td>
										<td><select id="invoice_print_newSearch_bank"
											name="bankBranchName" class="easyui-textbox" readonly="true"
											style="width: 120px;"></td>
										开户账号
										<td><spring:message code="client.bankNum" />：</td>
										<td><input id="invoice_print_newSearch_bankNum"
											name="bankAccountNum" class="easyui-textbox" readonly="true"
											style="width: 120px;"></td>
										联系电话
										<td><spring:message
												code="com.vat.base.model.customer.contactPhone.text" />：</td>
										<td><select id="invoice_print_newSearch_contactName"
											name="registrationContactPhone" class="easyui-textbox"
											readonly="true" style="width: 150px;"></td>
										注册地址
										<td><spring:message code="crvatInvoicePre.address" />：</td>
										<td><input id="invoice_print_newSearch_address"
											name="registrationContactAddress" class="easyui-textbox"
											readonly="true" style="width: 120px;"></td>

									</tr>
									<tr>
										是否已收款
										<td>是否已收款：</td>
										<td><select class="easyui-combobox" style="width: 120px;"
											id="isReceipts" name="isReceipts">
												<option value="0">是</option>
												<option value="1">否</option>
										</select></td>

										<td colspan="4"><a href="#" id="saveHeadbtn"
											class="easyui-linkbutton"
											data-options="iconCls:'icon-large-shapes'"
											style="width: 120px" onclick="saveHead('save')">保存申请单</a> <a
											href="#" id="submitHeadbtn" class="easyui-linkbutton"
											data-options="iconCls:'icon-large-smartart'"
											style="width: 120px" onclick="saveHead('submit')">提交申请单</a></td>

									</tr>
									<tr style="display: none">
										分页数据
										<td><input id="newpageNumber" class="easyui-textbox"
											type="text" style="width: 0px;" name="pageNumber" value=""></input></td>
										<td><input id="newpageSize" class="easyui-textbox"
											type="text" style="width: 0px;" name="pageSize" value=""></input></td>
									</tr>

									<tr style="display: none">
										销方纳税人id
										<td><input id="legalEntityId" class="easyui-textbox"
											name="legalEntityId" style="height: 20px"></input></td>
										特殊批量开票申请ID
										<td><input id="appuseruuid_id" class="easyui-textbox"
											name="appuseruuid" style="height: 20px"></input></td>
										发票明细数据
										<td input id="rowsid" name="dgrequestdetaildata"
											class="easyui-textbox" style="height: 20px"></td>
										组织id
										<td input id="orgId_id" name="orgId" class="easyui-textbox"
											style="height: 20px"></td>
											提交或者保存标识
										<td input id="tips_id" name="tips" class="easyui-textbox"
											style="height: 20px"></td>
									</tr>
								</table>
							</form>
						</div>
					</div>

				</div>

			</div>

			<div data-options="region:'center'" style="width: 100%; height: 72%;">

				<div class="easyui-layout" style="width: 100%; height: 100%;">
					<div data-options="region:'center'"
						style="width: 100%; height: 100%;" title="发票明细">
						<div style="width: 100%; height: 100%">
							<table class="easyui-datagrid" id="dgrequestdetail"
								style="width: 100%; height: 100%">
							</table>
						</div>

					</div>

				</div>

			</div>
		</div>






	</div> --%>

	<div data-options="region:'center',iconCls:'icon-ok'"
		title="<spring:message code="sourcesubject.quicksearch"/>">
		<div class="easyui-layout" style="width: 100%; height: 100%;">
			<div region="north" split="true" border="false"
				style="overflow: hidden; height: 20%;">
				<div class="easyui-panel"
					title="<spring:message code="searchgroup"/>"
					style="width: 100%; height: 100%; background-color: #E0ECFF">
					<form id="searchform" method="post">
						<table>
							<tr>
							<%--申请单编号 --%>
								<td><spring:message
										code="invoiceSpecialContractBath.search.customerNumber" />:</td>
								<td><input id="invoice_print_newSearch_Code"
									class="easyui-textbox" style="width: 120px;"
									name="crvatInvoiceReqNumber" /></input></td>
								<%--申请单状态 --%>
								<td><spring:message
										code="invoiceSpecialContractBath.search.readyStatus" />:</td>
								<td><input:select name="status" value="$status"
										easyuiClass="easyui-combobox" easyuiStyle="width:120px;">
										<option value=""></option>
										<input:systemStatusOption
											parentCode="VAT_CR_INVOICE_APPFORM_STATUS" />
									</input:select></td>
								<%--申请单时间 --%>	
								<td><spring:message
										code="invoiceSpecialContractBath.search.applyTime" />:</td>
								<td>
									<div>
										<input id="applytime" class="easyui-datebox"
											style="width: 110px;" name="invoiceReqStartDate" /></input>
										<spring:message code="invoiceSpecialContractBath.search.to" />
										: <input id="applyto" class="easyui-datebox"
											style="width: 110px;" name="invoiceReqendDate" /></input>
									</div>
								</td>
								<td colspan="2"><a href="#" id="searchbtn"
									class="easyui-linkbutton" data-options="iconCls:'icon-search'"
									style="width: 80px" onclick="Search()"><spring:message
											code="button.Search" /></a> <a href="#" id="clearbtn"
									class="easyui-linkbutton" style="width: 80px"
									onclick="clearSearchForm()"><spring:message
											code="button.Clear" /></a></td>
							</tr>
							<tr style="display: none">
								<td><input id="pageNumber" class="easyui-textbox"
									type="text" style="width: 0px;" name="pageNumber" value=""></input></td>
								<td><input id="pageSize" class="easyui-textbox" type="text"
									style="width: 0px;" name="pageSize" value=""></input></td>
								<td><input id="categoryName" name="categoryName"
									class="easyui-combobox" style="width: 0px;"></input></td>
									<td><input id="appuseruuid_ids" class="easyui-textbox"
											name="appuseruuid" style="height: 20px"></td>
							</tr>
						</table>
					</form>
				</div>

			</div>
			<div data-options="region:'center',border:false"
				style="background-color: #E0ECFF">
				<div style="width: 100%; height: 100%">
					<table class="easyui-datagrid" id="dg"
						title="<spring:message code="invoiceprint.applicantList"/>"
						style="width: 100%; height: 100%"
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
		</div>
	</div>

	<%-- 
	<div class="easyui-dialog" id="values_dialog"
		style="height: 30%; width: 30%" title="导入发票明细"
		data-options="				
				buttons: [{
					text:'<spring:message code="button.Save"/>',
					iconCls:'icon-ok',
					handler:function(){
						contractNumberSave();
					}
				},{
					text:'<spring:message code="button.Close"/>',
					iconCls:'icon-cancel',
					handler:function(){
						$('#values_dialog').dialog('close');
					}
				}]
			">
		<a href="#" id="download" class="easyui-linkbutton"
			data-options="iconCls:'icon-large-shapes'" style="width: 120px"
			onclick="downloadExcel()">下载导入模板</a>

		<form id="ff" method="post" enctype="multipart/form-data">
			<input id="teacher_dialog" name="sourceFile" />
		</form>

	</div> --%>
<%--查看明细 --%>
<div class="easyui-dialog" id="examineList"
		style="height: 100%; width: 100%" title="查看发票明细"
		data-options="				
				buttons: [{
					text:'<spring:message code="button.Close"/>',
					iconCls:'icon-cancel',
					handler:function(){
						$('#examineList').dialog('close');
					}
				}]
			">
<div class="easyui-layout" style="width: 100%; height: 100%;">
			<div data-options="region:'center',border:false"
				style="background-color: #E0ECFF">
	<div style="width: 100%; height: 100%">
<table class="easyui-datagrid" id="dgrequestdetailexamineList"
								style="width: 100%; height: 100%"  data-options="					
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
							</div>
					</div>

</body>
<script type="text/javascript">
	$.extend($.messager.defaults, {
		ok : '<spring:message code="confirm"/>',
		cancel : '<spring:message code="cancel"/>'
	});

	formatterDate = function(date) {//默认申请单生成日期格式format
		var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
		var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
				+ (date.getMonth() + 1);
		return date.getFullYear() + '-' + month + '-' + day;
	};

	$(function() {//申请单列表数据初始化
		$('#dg')
				.datagrid(
						{
							url : '',
							nowrap : true,
							pagination : true,
							rownumbers : true,
							singleSelect : false,
							columns : [ [
									{
										field : 'id',
										checkbox : true,
										width : 2
									}, //显示复选框     legalEntityId
									{
										field : 'legalEntityId',//纳税主体id
										title : '纳税主体id',
										width : 100,
										align : 'center',
										hidden : true,
									},
									{
										field : 'crvatInvoiceReqNumber',//申请单编号
										title : '<spring:message code="invoiceprint.readyNo"/>',
										width : 100,
										align : 'center',
									},
									{
										field : 'status',//申请单状态
										title : '<spring:message code="invoiceprint.search.bootStatus"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'invoiceReqDate',//申请日期
										title : '<spring:message code="invoiceprint.search.applyTime"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'createdBy',//申请人
										title : '<spring:message code="invoiceprint.applicant"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'approvalBy',//审批人
										title : '审批人',
										width : 100,
										align : 'center'
									},
									{
										field : 'attribute1',//合计金额
										title : '<spring:message code="invoiceprint.amountAll"/>',
										width : 100,
										align : 'center'
									}, {
										field : 'attribute4',//申请单处理状态
										title : '申请单处理状态',
										width : 100,
										align : 'center'
									}, 
									
									] ],
							toolbar : [/*  {
								text : '新增申请单',
								iconCls : 'icon-add',
								handler : function() {
									addPre();//新增申请单方法

								}
							}, '-',  {
								text : '删除申请单',
								iconCls : 'icon-remove',
								handler : function() {
									removePre();

								}
							}, '-', {
								text : '编辑申请单',
								iconCls : 'icon-edit',
								handler : function() {
									editDetail();
								}
							}, '-', {
								text : '提交申请单',
								iconCls : 'icon-search',
								handler : function() {
									submitFromPage(); //申请单查询页面提交申请单
								}
							}, '-', {
								text : '撤回申请单',
								iconCls : 'icon-redo',
								handler : function() {
									submitFromPage(); //申请单查询页面提交申请单
								}
							}, '-',  {
								text : '审批',
								iconCls : 'icon-redo',
								handler : function() {//
								cautious(); //申请单查询页面提交申请单
								}
							}, '-' ,
							    */ {
											text : '查看明细',
											iconCls : 'icon-redo',
											handler : function() {
												examineList(); //申请单查询页面提交申请单
											}
										}, '-'
							],
							onLoadSuccess : function() {//数据加载完成
								$('#dg').datagrid('clearSelections')
							},
							onClickRow : function(index, data) {//双击行
								var row = $('#dg').datagrid('getSelected');
								if (row) {
								}
							}
						});

		//设置分页控件	
		var p = $('#dg').datagrid('getPager');
		$(p)
				.pagination(
						{
							pageSize : 10,//每页显示的记录条数，默认为10           
							//pageList: [5,10,15,20],//可以设置每页记录条数的列表           
							beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
							afterPageText : '<spring:message code="pagination.afterPageText"/>',
							displayMsg : '<spring:message code="pagination.displayMsg"/>',
							onSelectPage : function(pageNumber, pageSize) {//分页触发		
								findDg(pageNumber, pageSize);
								Search();
								clearSaveForm();
							}

						});
	});
	/**
	 * ready加载事件
	 */
	$(document).ready(function() {
		//$("#layoutid").layout('collapse', 'east');//隐藏右侧面板
		$('#searchform').form('load', {
			pageNumber : $('#dg').datagrid('options').pageNumber,
			pageSize : $('#dg').datagrid('options').pageSize
		});
		Search();
		initValues_dialog();
	});
/**
 * 查看明细
 */
function examineList(){
		var rows = $('#dg').datagrid('getSelections');//得到选择数据行
		if (rows.length == 1) {//判断行数
			var row = $('#dg').datagrid('getSelected');//得到行数据
		
				$('#appuseruuid_ids').textbox('setValue', row.id);
				
				var url = "${vat}/invoiceSpecialContractBathController/listTmsCrvatInvReqBatchesL.do";
				dgrequestdetailexamineList(url);
				$("#examineList").dialog('open');//打开弹出框
			
		} else {
			$.messager.confirm('<spring:message code="invoiceprint.reqinfo"/>',
					'<spring:message code="invoiceprint.error"/>');
		}
}
/**
 * 查看面板
 */
function dgrequestdetailexamineList(url) {
	$('#dgrequestdetailexamineList').datagrid(
			{
				url : url,
				nowrap : true,
				rownumbers : true,
				pagination : true,
				queryParams : {
					appuseruuid : $("#appuseruuid_ids").textbox('getValue'),
				},
				columns : [ [ {
					colspan : 2,
					title : '处理结果',
					align : 'center',
				}, {
					colspan : 8,
					title : '客户基本信息',
					align : 'center',
				}, {
					colspan : 21,
					title : ' 开票交易信息',
					align : 'center',
				}, ], 
				[ {
					field : 'attribute4',//申请单处理状态
					title : '申请单处理状态',
					width : 100,
					align : 'center',
				 

				}, {
					field : 'attribute5',//错误信息
					title : '错误信息',
					width : 500,
					align : 'center'
				},
				  {
					field : 'customerNumber',
					title : "客户编码",
					width : 80,
					editor : 'text',
				}, {
					field : 'id',
					title : "特殊开票申请单行ID",
					width : 80,
					editor : 'text',
					hidden : true,
				}, {
					field : 'customerName',
					title : "客户名称",
					width : 80,
					editor : 'text',
				}, {
					field : 'custRegistrationNumber',
					title : "纳税人识别号",
					width : 90,
					editor : 'text',
				}, {
					field : 'custLegalEntityType',
					title : "纳税人身份",
					width : 80,
					editor : 'text',
				}, {
					field : 'custRegistrationAddress',
					title : "注册地址",
					width : 80,
					editor : 'text',
				}, {
					field : 'contactPhone',
					title : "注册电话",
					width : 80,
					editor : 'text',
				}, {
					field : 'custDepositBankName',
					title : "开户银行",
					width : 80,
					editor : 'text',
				}, {
					field : 'custDepositBankAccountNum',
					title : "开户银行账号",
					width : 100,
					editor : 'text',
				}, {
					field : 'customerId',
					title : "购方id",
					width : 80,
					hidden : true,
					editor : 'text',
				}, {
					field : 'contractId',
					title : "合同id",
					width : 80,
					hidden : true,
					editor : 'text',
				}, {
					field : 'projectId',
					title : "项目id",
					width : 80,
					hidden : true,
					editor : 'text',
				}, {
					field : 'taxTrxTypeId',
					title : "涉税交易类型ID",
					width : 80,
					hidden : true,
					editor : 'text',
				}, {
					field : 'inventoryItemId',
					title : "商品及服务编码ID",
					width : 80,
					hidden : true,
					editor : 'text',
				}, {
					field : 'trxAffirmSettingId',
					title : "涉税交易认定设置规则ID",
					width : 80,
					hidden : true,
					editor : 'text',
				}, {
					field : 'contractNumber',
					title : "合同编号",
					width : 80,
					editor : 'text',
				}, {
					field : 'contractName',
					title : "合同名称",
					width : 80,
					editor : 'text',
				}, {
					field : 'projectNumber',
					title : "项目编号",
					width : 80,
					editor : 'text',
				}, {
					field : 'projectName',
					title : "项目名称",
					width : 80,
					editor : 'text',
				}, {
					field : 'taxTrxTypeCode',
					title : "涉税交易类型编码",
					width : 100,
					editor : 'text',
				}, {
					field : 'taxTrxTypeName',
					title : "涉税交易类型名称",
					width : 100,
					editor : 'text',
				}, {
					field : 'inventoryItemNumber',
					title : '商品及服务编码',
					width : 100,
					editor : 'combogrid',
				}, {
					field : 'inventoryItemDescripton',
					title : "商品及服务名称",
					width : 100,
					editor : 'text',
				}, {
					field : 'inventoryItemQty',
					title : '数量',
					width : 50,
					align : 'center',
					editor : 'text',

				}, {
					field : 'invoiceCategories',
					title : '发票类型',
					width : 80,
					align : 'center',
					editor : 'text',
				}, {
					field : 'taxRate',
					title : '税率',
					width : 80,
					align : 'center',
					editor : {
						type : 'numberbox',
						options : {
							precision : "2",
						}
					}
				}, {
					field : 'isTax',
					title : '是否含税',
					width : 80,
					editor : 'text',
				},  {
					field : 'isReceipts',
					title : '是否已收款',
					width : 80,
					editor : 'text',
				}, {
					field : 'invoiceAmount',
					title : '开票金额',
					width : 80,
					align : 'center',
					editor : 'numberbox'
				} ] ],
				   onLoadSuccess:function(data){   
				        $('#dgrequestdetailexamineList').datagrid('doCellTip',{cls:{'background-color':'red'},delay:1000});   
				    }  
				
			});

}
function orgFormat(val, row) {
	if (val)
		return val.orgName;
	else
		return "";
}
//弹出框
function initValues_dialog() {
	$("#examineList").dialog({
		closed : true,
		region : 'center',//弹出框位置
	});
}
	/* 
	 * 左滑面板发票明细表格
	
	function rightDgrequestdetail(url) {
		$('#dgrequestdetail').datagrid(
				{
					url : url,
					nowrap : true,
					rownumbers : true,
					pagination : true,
					queryParams : {
						appuseruuid : $("#appuseruuid_id").textbox('getValue'),
					},
					columns : [ [ {
						colspan : 8,
						title : '客户基本信息',
						align : 'center',
					}, {
						colspan : 20,
						title : ' 开票交易信息',
						align : 'center',
					}, ], [ {
						field : 'customerNumber',
						title : "客户编码",
						width : 80,
						editor : {
							type : 'text',
							options : {
								onKeyPress : function(event) {
									alert("ooo");
									if (event.keyCode == 13) {
										alert("ooo");
									}
								}
							}
						}
					}, {
						field : 'id',
						title : "特殊开票申请单行ID",
						width : 80,
						editor : 'text',
						hidden : true,
					}, {
						field : 'customerName',
						title : "客户名称",
						width : 80,
						editor : 'text',
					}, {
						field : 'custRegistrationNumber',
						title : "纳税人识别号",
						width : 90,
						editor : 'text',
					}, {
						field : 'custLegalEntityType',
						title : "纳税人身份",
						width : 80,
						editor : 'text',
					}, {
						field : 'custRegistrationAddress',
						title : "注册地址",
						width : 80,
						editor : 'text',
					}, {
						field : 'contactPhone',
						title : "注册电话",
						width : 80,
						editor : 'text',
					}, {
						field : 'custDepositBankName',
						title : "开户银行",
						width : 80,
						editor : 'text',
					}, {
						field : 'custDepositBankAccountNum',
						title : "开户银行账号",
						width : 100,
						editor : 'text',
					}, {
						field : 'customerId',
						title : "购方id",
						width : 80,
						hidden : true,
						editor : 'text',
					}, {
						field : 'contractId',
						title : "合同id",
						width : 80,
						hidden : true,
						editor : 'text',
					}, {
						field : 'projectId',
						title : "项目id",
						width : 80,
						hidden : true,
						editor : 'text',
					}, {
						field : 'taxTrxTypeId',
						title : "涉税交易类型ID",
						width : 80,
						hidden : true,
						editor : 'text',
					}, {
						field : 'inventoryItemId',
						title : "商品及服务编码ID",
						width : 80,
						hidden : true,
						editor : 'text',
					}, {
						field : 'trxAffirmSettingId',
						title : "涉税交易认定设置规则ID",
						width : 80,
						hidden : true,
						editor : 'text',
					}, {
						field : 'contractNumber',
						title : "合同编号",
						width : 80,
						editor : 'text',
					}, {
						field : 'contractName',
						title : "合同名称",
						width : 80,
						editor : 'text',
					}, {
						field : 'projectNumber',
						title : "项目编号",
						width : 80,
						editor : 'text',
					}, {
						field : 'projectName',
						title : "项目名称",
						width : 80,
						editor : 'text',
					}, {
						field : 'taxTrxTypeCode',
						title : "涉税交易类型编码",
						width : 100,
						editor : 'text',
					}, {
						field : 'taxTrxTypeName',
						title : "涉税交易类型名称",
						width : 100,
						editor : 'text',
					}, {
						field : 'inventoryItemNumber',
						title : '商品及服务编码',
						width : 100,
						editor : 'combogrid',
					}, {
						field : 'inventoryItemDescripton',
						title : "商品及服务名称",
						width : 100,
						editor : 'text',
					}, {
						field : 'inventoryItemQty',
						title : '数量',
						width : 50,
						align : 'center',
						editor : 'text',

					}, {
						field : 'invoiceCategories',
						title : '发票类型',
						width : 80,
						align : 'center',
						editor : 'text',
					}, {
						field : 'taxRate',
						title : '税率',
						width : 80,
						align : 'center',
						editor : {
							type : 'numberbox',
							options : {
								precision : "2",
							}
						}
					}, {
						field : 'isTax',
						title : '是否含税',
						width : 80,
						editor : 'text',
					}, {
						field : 'invoiceAmount',
						title : '开票金额',
						width : 80,
						align : 'center',
						editor : 'numberbox'
					} ] ],
					toolbar : [ {
						id : "batch",
						text : '导入',
						iconCls : 'icon-add',
						handler : function() {
							teacher();

						}
					}, '-', {
						id : "addba",
						text : '新增',
						iconCls : 'icon-add',
						handler : function() {
							addDetails();

						}
					}, '-', {
						id : "deletem",
						text : '删除',
						iconCls : 'icon-remove',
						handler : function() {
							inventoryItemRemove(); //删除

						}
					}, '-', {
						id : 'savess',
						text : '保存',
						iconCls : 'icon-save',
						handler : function() {
							inventoryItemSave();

						}
					}, '-' ],
					onLoadSuccess : function(data) {
						$('#invoice_print_newSearch_amountAll').textbox(
								'setValue', data.invoiceAmount);//总金额
					}
				});

	}

	
	 * 发票弹出框
	 
	function initValues_dialog() {
		$("#values_dialog").dialog({
			closed : true,
			region : 'center',//弹出框位置
		});
		$("#examineList").dialog({
			closed : true,
			region : 'center',//弹出框位置
		});
	}

	
	 * 发票明细保存
	 
	function inventoryItemSave() {
		$('#dgrequestdetail').datagrid('acceptChanges');
		$('#dgrequestdetail').datagrid('clearSelections');
		$("#batch").linkbutton("enable");
		$("#addba").linkbutton("enable");
		$("#deletem").linkbutton("enable");
	}
	
	 * 添加交易信息
	
	var leijisumCount = 0;
	function addDetails() {

		var data = $('#dgrequestdetail').datagrid('getData');
		var rows = data.total;
		if (rows > 0) {
			inventoryItemSave();
		}
		$('#dgrequestdetail').datagrid('insertRow', {
			index : leijisumCount,
			row : {},
		});
		$('#dgrequestdetail').datagrid('clearSelections');
		//将第一行设置为可编辑状态
		$('#dgrequestdetail').datagrid('beginEdit', leijisumCount);
		$('#dgrequestdetail').datagrid('selectRow', leijisumCount);
		this.editRow = leijisumCount;
		leijisumCount = leijisumCount + 1;

	}
	
	 * 导入事件
	 
	function teacher() {
		$("#values_dialog").dialog('open');//打开弹出框
	}
	/**
	 * 设置文件框
	
	$('#teacher_dialog').filebox({
		buttonText : '选择文件',
	});
	/**
	 * 上传文件
	 
	function contractNumberSave() {
		$("#ff")
				.form(
						'submit',
						{
							url : '${vat}/invoiceSpecialContractBathController/contractNumberSave.do',
							onSubmit : function() {
							},
							success : function(data) {
								var result = $.parseJSON(data);
								var tips = result.success;
								if (tips == "erro") {
									$.messager.alert('导入数据失败！', result.rows[0]);
								} else if (tips == "success") {
									$("#values_dialog").dialog('close');//关闭弹出框
									$("#batch").linkbutton("disable");
									$("#addba").linkbutton("disable");
									$("#deletem").linkbutton("disable");
									$("#savess").linkbutton("disable");
									$('#invoice_print_newSearch_amountAll')
											.textbox('setValue',
													result.invoiceAmount);//总金额
									$("#dgrequestdetail").datagrid('loadData',
											result);
									$("#dgrequestdetail").datagrid("loaded");

								}
							}
						});

	}
/**
 * 下载
 
function downloadExcel(){
	window.location.href='${vat}/invoiceSpecialContractBathController/download.do';
	}
	

	/**
	 * 发票明细删除
	 
	function inventoryItemRemove() {
		$.messager.confirm('确认', '确认删除?', function(row) {
			if (row) {
				var rows = $('#dgrequestdetail').datagrid('getSelections');
				var copyRows = [];
				for (var j = 0; j < rows.length; j++) {
					copyRows.push(rows[j]);
				}
				for (var i = 0; i < copyRows.length; i++) {
					var index = $('#dgrequestdetail').datagrid('getRowIndex',
							copyRows[i]);
					$('#dgrequestdetail').datagrid('deleteRow', index);
				}

				$('#dgrequestdetail').datagrid('clearSelections');
				$('#dgrequestdetail').datagrid('selectRow', 0);
			}
		});
	} */
	/**
	 * 重新对申请表单明细分页信息
	 */
	function findDg(pageNumber, pageSize) {
		$('#searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		Search();
	}

	function Search() {//搜索申请单
		$("#dg").datagrid("loading");
		$('#searchform')
				.form(
						'submit',
						{
							url : '${vat}/invoiceSpecialContractBathController/getInvoiceReqAll.do',
							success : function(result) {
								var result = $.parseJSON(result);
								$("#dg").datagrid('loadData', result);
								$("#dg").datagrid("loaded");
							}
						});

	}
/* 	
	 * 编辑申请单
	 
	function editDetail() {
		var rows = $('#dg').datagrid('getSelections');//得到选择数据行
		if (rows.length == 1) {//判断行数
			var row = $('#dg').datagrid('getSelected');//得到行数据
			if ("草稿" == row.status) {//判断申请单状态
				$('#layoutid').layout('expand', 'east');//关闭右侧滑动面板
				$("#newDetailForm").form('load', row);
				$('#appuseruuid_id').textbox('setValue', row.id);//id
				var url = "${vat}/invoiceSpecialContractBathController/listTmsCrvatInvReqBatchesL.do";
				rightDgrequestdetail(url);
				$
						.ajax({
							url : "${vat}/invoiceSpecialContractBathController/getTmsMdLegalEntity.do",
							dataType : "json",
							cache : false,
							success : function(object) {
								$('#legalEntityCode').textbox('setValue',
										object.result.legalEntityCode);//纳税人编号
								$('#taxpayerName').textbox('setValue',
										object.result.legalEntityName);//纳税人名称	
								$('#invoice_print_newSearch_bank').textbox(
										'setValue',
										object.result.bankBranchName);//开户银行
								$('#invoice_print_newSearch_bankNum').textbox(
										'setValue',
										object.result.bankAccountNum);//账户
								$('#invoice_print_newSearch_contactName')
										.textbox(
												'setValue',
												object.result.registrationContactPhone);//联系电话
								$('#invoice_print_newSearch_address')
										.textbox(
												'setValue',
												object.result.registrationContactAddress);//地址
								$('#legalEntityId').textbox('setValue',
										object.result.legalEntityId);//纳税人id
								$('#equipmentId').textbox('setValue',
										object.result.equipmentCode);//终端编号
								$('#orgNameId').textbox('setValue',
										object.result.orgName);//打印组织
								$('#orgId_id').textbox('setValue',
										object.result.orgId);//打印组织id
								$('#invoice_print_newSearch_amountAll')
										.textbox('setValue',
												object.result.invoiceAmount);//打印组织id

							}
						});

			} else {
				$.messager.confirm(
						'<spring:message code="invoiceprint.reqinfo"/>',
						'<spring:message code="invoiceprint.disabled"/>');
			}
		} else {
			$.messager.confirm('<spring:message code="invoiceprint.reqinfo"/>',
					'<spring:message code="invoiceprint.error"/>');
		}

	}

	function removePre() {//删除申请表信息
		var rows = $('#dg').datagrid('getSelections');
		if ('0' != rows.length) {
			for (var i = 0; i < rows.length; i++) {
				var r = rows[i];
				if ('草稿' != r.status) {
					isCommit = false;
					$.messager
							.alert(
									'<spring:message code="invoiceprint.reqinfo"/>',
									'<spring:message code="invoiceprint.deleteDisabled"/>');
					return;
				}
			}
			var ps = "";
			$.each(rows, function(i, n) {
				if (i == 0)
					ps += n.id;
				else
					ps += "," + n.id;
			});
			$.ajax({
				url : "${vat}/invoiceSpecialContractBathController/removeInvoiceReqHs.do?ids=" + ps,
				type : 'POST',
				dataType : "json",
				cache : false,
				success : function(object) {
					$.messager.alert('删除成功！',
							object.msg);
					Search();
				}
			});
		} else {
			$.messager.alert('<spring:message code="invoiceprint.reqinfo"/>',
					'<spring:message code="invoiceprint.error"/>');
		}
	}

 * 审批

 function cautious(){
		var rows = $('#dg').datagrid('getSelections');//得到选择数据行
		var id = [];
		for (var j = 0; j < rows.length; j++) {
			id.push(rows[j].id);
			if ("已提交" == rows[j].status) {//判断申请单状态
	 $.ajax({
			url : "${vat}/tmsCrvatInvReqBatchesL/batchSaveTmsCrvatInvReqBatchesLToPrintPool.do",
			type:'post',
			data:"id="+id,
			success : function(object) {
				var result = $.parseJSON(object);
				if(result.success=="true"){
				Search();
				}
				}
		});
			}else {
				$.messager.confirm(
						'<spring:message code="invoiceprint.reqinfo"/>',
						'非已提交数据不允许审批');
			}
		
		}
			
} */
	function clearSearchForm() {//清除申请表单
		$('#searchform').form('clear');
		var pageNumber = $('#dg').datagrid('options').pageNumber;
		var pageSize = $('#dg').datagrid('options').pageSize;
		$('#searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
	}
	/* function clearDetailForm() {//清空右侧滑动面版表单
		$('#newDetailForm').form('clear');
		var pageNumber = $('#dgrequestdetail').datagrid('options').pageNumber;
		var pageSize = $('#dgrequestdetail').datagrid('options').pageSize;
		$('#newDetailForm').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
	}
	function clearFormAndData() {//清空右侧滑动面版表单以及表格数据
		var pageNumber = $('#dgrequestdetail').datagrid('options').pageNumber;
		var pageSize = $('#dgrequestdetail').datagrid('options').pageSize;
		$('#newDetailForm').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		$('#dgrequestdetail').datagrid('loadData', {
			total : 0,
			rows : []
		});
	}
	function save() {//保存
		var time = $('#invoice_print_newSearch_applyTime').val();
		var rows = $('#dgrequestdetail').datagrid("getRows");
		if (time != "" && '0' != rows.length) {
			var ps = "";
			$.each(rows, function(i, n) {
				if (i == 0)
					ps += n.id;
				else
					ps += "," + n.id;
			});
			$('#newDetailForm').form('load', {
				ids : ps
			});
			$('#invoice_print_newSearch_isCommitPaper')
					.textbox('setValue', '0');
			$('#newDetailForm').form(
					'submit',
					{
						url : 'invoiceReqAll/saveInvoiceReqAll.do',
						success : function(object) {
							var object = $.parseJSON(object);
							clearNewSearchForm();
							$.messager.alert(
									'<spring:message code="system.alert"/>',
									object.msg);
							Search();
						}
					});
		} else {
			$.messager.alert('<spring:message code="invoiceprint.reqinfo"/>',
					'<spring:message code="invoiceprint.info"/>');
		}
	}
	function addPre() {//新增申请单
		clearDetailForm();
		$('#layoutid').layout('expand', 'east');
		$('#customerManageInit_searchform_custLegalEntityType').combobox(
				'select', '10');//默认将申请状态设置为草稿
		$('#invoice_print_newSearch_applyTime').datebox('setValue',
				formatterDate(new Date()));//默认将申请单生成日期设置为当前日期
		$("#customerManageInit_searchform_custLegalEntityType").combobox({
			readonly : true
		});
		$
				.ajax({
					url : "${vat}/invoiceSpecialContractBathController/getCreanumber.do",
					dataType : "json",
					cache : false,
					success : function(object) {
						$('#invoice_print_newSearch_readyNo')
								.textbox('setValue',
										object.result.crvatInvoiceReqNumber);//申请单编号
						$('#legalEntityCode').textbox('setValue',
								object.result.legalEntityCode);//纳税人编号
						$('#taxpayerName').textbox('setValue',
								object.result.legalEntityName);//纳税人名称	
						$('#invoice_print_newSearch_bank').textbox('setValue',
								object.result.bankBranchName);//开户银行
						$('#invoice_print_newSearch_bankNum').textbox(
								'setValue', object.result.bankAccountNum);//账户
						$('#invoice_print_newSearch_contactName').textbox(
								'setValue',
								object.result.registrationContactPhone);//联系电话
						$('#invoice_print_newSearch_address').textbox(
								'setValue',
								object.result.registrationContactAddress);//地址
						$('#legalEntityId').textbox('setValue',
								object.result.legalEntityId);//纳税人id
						$('#equipmentId').textbox('setValue',
								object.result.equipmentCode);//终端编号
						$('#orgNameId').textbox('setValue',
								object.result.orgName);//打印组织
						$('#orgId_id').textbox('setValue', object.result.orgId);//打印组织id
						$('#invoice_print_newSearch_amountAll').textbox(
								'setValue', object.result.invoiceAmount);//打印组织id
						rightDgrequestdetail("");

					}
				});
	}

	function saveHead(tips) {//保存申请单或提交申请单
		if(tips=="save"){
			
			$("#tips_id").textbox("setValue","save");
			alert($("#tips_id").textbox("getValue"));
			
		}
      if(tips=="submit"){
    	  $("#tips_id").textbox("setValue","submit");
		}
		var dgrequestdetaildata = $('#dgrequestdetail').datagrid('getRows');//得到发票明细数据
		$('#newDetailForm').form('load', {
			dgrequestdetaildata : JSON.stringify(dgrequestdetaildata),
		});

		$('#newDetailForm')
				.form(
						'submit',
						{
							url : '${vat}/invoiceSpecialContractBathController/saveInvoiceReqHead.do',
							success : function(object) {
								$("#layoutid").layout('collapse', 'east');
								Search();
								var object = $.parseJSON(object);
								clearNewSearchForm();
								$.messager
										.alert(
												'<spring:message code="system.alert"/>',
												object.msg);
							}
						});

	}
	//从申请单查询页面提交申请单
	function submitFromPage() {
		var rows = $('#dg').datagrid('getChecked');
		var isCommit = true;
		for (var i = 0; i < rows.length; i++) {
			var r = rows[i];
			if ("草稿" != r.status) {
				isCommit = false;
				$.messager.alert(
						'<spring:message code="invoiceprint.reqinfo"/>',
						'<spring:message code="invoceReqstatus"/>');
				return;
			}
		}
		var ps = "";
		$.each(rows, function(i, n) {
			if (i == 0)
				ps += n.id;
			else
				ps += "," + n.id;
		});
		$.ajax({
			url : "${vat}/invoiceSpecialContractBathController/submitFromPage.do?ids=" + ps,
			dataType : "json",
			type : "POST",
			cache : false,
			success : function(object) {
				if (object.success) {
					Search();
				}
				$.messager.alert('<spring:message code="system.alert"/>',
						object.msg); 
			}
		});
	}
	 */
	function addTo() {
		$('#saveform').form('clear');
		initProjdg('');
		$("#dlg").dialog('open').dialog('setTitle', '新增合同');
	}

	//回车事件
	$("#invoice_print_newSearch_validNo").keyup(function(event) {
		if (event.keyCode == 13) {
			getCustomerInfoByCode();
		}
	});
	$("#invoice_print_newSearch_customerCode").keyup(function(event) {
		if (event.keyCode == 13) {
			getCustomerInfoByNumber();
		}
	});

	/**
	 * 表格头右键菜单可选列生成
	 */
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
	

    /**  
     * 扩展两个方法  
     */  
    $.extend($.fn.datagrid.methods, {   
        /**
         * 开打提示功能  
         * @param {} jq  
         * @param {} params 提示消息框的样式  
         * @return {}  
         */  
        doCellTip : function(jq, params) {   
            function showTip(data, td, e) {   
                if ($(td).text() == "")   
                    return;   
                data.tooltip.text($(td).text()).css({   
                            top : (e.pageY + 10) + 'px',   
                            left : (e.pageX + 20) + 'px',   
                            'z-index' : $.fn.window.defaults.zIndex,   
                            display : 'block'   
                        });   
            };   
            return jq.each(function() {   
                var grid = $(this);   
                var options = $(this).data('datagrid');   
                if (!options.tooltip) {   
                    var panel = grid.datagrid('getPanel').panel('panel');   
                    var defaultCls = {   
                        'border' : '1px solid #333',   
                        'padding' : '1px',   
                        'color' : '#333',   
                        'background' : '#f7f5d1',   
                        'position' : 'absolute',   
                        'max-width' : '200px',   
                        'border-radius' : '4px',   
                        '-moz-border-radius' : '4px',   
                        '-webkit-border-radius' : '4px',   
                        'display' : 'none'   
                    }   
                    var tooltip = $("<div id='celltip'></div>").appendTo('body');   
                    tooltip.css($.extend({}, defaultCls, params.cls));   
                    options.tooltip = tooltip;   
                    panel.find('.datagrid-body').each(function() {   
                        var delegateEle = $(this).find('> div.datagrid-body-inner').length   
                                ? $(this).find('> div.datagrid-body-inner')[0]   
                                : this;   
                        $(delegateEle).undelegate('td', 'mouseover').undelegate(   
                                'td', 'mouseout').undelegate('td', 'mousemove')   
                                .delegate('td', {   
                                    'mouseover' : function(e) {   
                                        if (params.delay) {   
                                            if (options.tipDelayTime)   
                                                clearTimeout(options.tipDelayTime);   
                                            var that = this;   
                                            options.tipDelayTime = setTimeout(   
                                                    function() {   
                                                        showTip(options, that, e);   
                                                    }, params.delay);   
                                        } else {   
                                            showTip(options, this, e);   
                                        }   
      
                                    },   
                                    'mouseout' : function(e) {   
                                        if (options.tipDelayTime)   
                                            clearTimeout(options.tipDelayTime);   
                                        options.tooltip.css({   
                                                    'display' : 'none'   
                                                });   
                                    },   
                                    'mousemove' : function(e) {   
                                        var that = this;   
                                        if (options.tipDelayTime) {   
                                            clearTimeout(options.tipDelayTime);   
                                            options.tipDelayTime = setTimeout(   
                                                    function() {   
                                                        showTip(options, that, e);   
                                                    }, params.delay);   
                                        } else {   
                                            showTip(options, that, e);   
                                        }   
                                    }   
                                });   
                    });   
      
                }   
      
            });   
        },   
        /**
         * 关闭消息提示功能  
         * @param {} jq  
         * @return {}  
         */  
        cancelCellTip : function(jq) {   
            return jq.each(function() {   
                        var data = $(this).data('datagrid');   
                        if (data.tooltip) {   
                            data.tooltip.remove();   
                            data.tooltip = null;   
                            var panel = $(this).datagrid('getPanel').panel('panel');   
                            panel.find('.datagrid-body').undelegate('td',   
                                    'mouseover').undelegate('td', 'mouseout')   
                                    .undelegate('td', 'mousemove')   
                        }   
                        if (data.tipDelayTime) {   
                            clearTimeout(data.tipDelayTime);   
                            data.tipDelayTime = null;   
                        }   
                    });   
        }   
    });  

	
</script>

</html>