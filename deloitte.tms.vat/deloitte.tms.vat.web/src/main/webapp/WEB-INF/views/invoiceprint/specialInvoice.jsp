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
	<div data-options="region:'east',split:true"
		title="<spring:message code="invoiceprint.reqinfo"/>"
		style="width: 100%;">

		<div class="easyui-layout" style="width: 100%; height: 100%;">

			<div data-options="region:'north'" split="true"
				style="width: 100%; height: 30%;">

				<div class="easyui-layout" style="width: 100%; height: 100%;">

					<div data-options="region:'north'"
						style="width: 100%; height: 100%;">
						<div class="easyui-panel"
							style="width: 100%; height: 100%; background-color: #E0ECFF">
							<form id="newDetailForm" method="post">
								<table style="width: 1000px; height: 100%">
									<tr>
										<td><spring:message code="invoiceprint.validType" />：</td>
										<td><input id="invoice_print_searchOut_validType"
											class="easyui-combobox" name="custRegistrationCode"
											style="width: 120px" required="true"
											data-options="url:'${vat}/invoiceSpecial/getTypedictionary.do',
	                                              method:'get',
	                                              valueField:'value',
	                                              textField:'text',
	                                              panelHeight:'auto'
	                                 ">
										</td>

										<td><spring:message code="invoiceprint.validName" />：</td>
										<td><input id="invoice_print_newSearch_validNo"
											name="custRegistrationNumber" class="easyui-validatebox"
											required="true" onblur="getCustomerInfoByCode()"
											style="width: 120px;"></td>
										<td><spring:message
												code="com.vat.base.model.customer.customerCode.text" />：</td>
										<td><input id="invoice_print_newSearch_customerCode"
											name="customerNumber" class="easyui-validatebox"
											required="true" onblur="getCustomerInfoByNumber()"
											style="width: 120px;"></td>
										<td>业务类型：</td>
										<td><select id="" class="easyui-combobox" name=""
											style="width: 120px">
												<option value=""></option>
												<option value="1">是</option>
												<option value="2">否</option>
										</select></td>
									</tr>
									<tr>
										<td><spring:message
												code="invoiceprint.search.buyerCompanyName" />：</td>
										<td><input id="invoice_print_newSearch_buyerCompanyName"
											name="customerName" class="easyui-textbox" readonly="true"
											style="width: 120px;"></td>
										<td><spring:message code="invoiceprint.level" />：</td>
										<td><input id="invoice_print_newSearch_level"
											class="easyui-combobox" name="reqInvoiceRange"
											style="width: 120px"
											data-options="
									 url:'${vat}/invoiceSpecial/getLeveldictionary.do',
	                                 method:'get',
	                                 valueField:'value',
	                                 textField:'text',
	                                 panelHeight:'auto'
	                                 ">
										</td>
										<td>是否含税：</td>
										<td><select id="isTaxid" class="easyui-combobox" name="isTax"
											style="width: 120px" data-options="required:true">
												<option value=""></option>
												<option value="1">是</option>
												<option value="2">否</option>
										</select></td>
										<td>是否已收款：</td>
										<td><select id="isReceiptsid" class="easyui-combobox" name="isReceipts"
											style="width: 120px" data-options="required:true">
												<option value=""></option>
												<option value="1">是</option>
												<option value="2">否</option>
										</select></td>
									</tr>
									<tr>
										<td><spring:message code="client.bank" />：</td>
										<td><select id="invoice_print_newSearch_bank"
											name="custDepositBankName" class="easyui-textbox" readonly="true"
											style="width: 120px;"></td>
										<td><spring:message code="client.bankNum" />：</td>
										<td><input id="invoice_print_newSearch_bankNum"
											name="custDepositBankAccountNum" class="easyui-textbox" readonly="true"
											style="width: 120px;"></td>
										<td><spring:message code="contacts" />：</td>
										<td><select id="invoice_print_newSearch_contactName"
											name="contactName" class="easyui-textbox" readonly="true"
											style="width: 120px;"></td>
										<td>公司地址：</td>
										<td><input id="invoice_print_newSearch_address"
											name="custRegistrationAddress" class="easyui-textbox" readonly="true"
											style="width: 120px;"></td>
									</tr>
									<tr>
										<td><spring:message code="invoiceprint.readyNo" />：</td>
										<td><input class="easyui-textbox"
											id="invoice_print_newSearch_readyNo"
											name="crvatInvoiceReqNumber" readonly="true"
											style="width: 120px;"></td>
										<td>申请单生成日期：</td>
										<td><input id="invoice_print_newSearch_applyTime"
											readonly="true" name="invoiceReqDate" readonly="true"
											class="easyui-datebox" style="width: 120px;"></td>
										<td><spring:message code="invoiceprint.readyStatus" />：</td>
										<td><input class="easyui-combobox"
											id="invoice_print_newSearch_reqStatus" name="status"
											readonly="true" style="width: 120px"
											data-options="
									 url:'${vat}/invoiceSpecial/getdictionary.do',
	                                 method:'get',
	                                 valueField:'value',
	                                 textField:'text',
	                                 panelHeight:'auto'
	                               ">
										</td>
										<td><spring:message code="invoiceprint.amountAll" />：</td>
										<td><input id="invoice_print_newSearch_amountAll"
											name="reqAmount" class="easyui-textbox" readonly="true"
											style="width: 120px;"></td>
									</tr>
									<tr>
										<td><a href="#" id="saveHeadbtn"
											class="easyui-linkbutton"
											data-options="iconCls:'icon-large-shapes'"
											style="width: 120px" onclick="saveHead()">保存申请单</a></td>
										<td><a href="#" id="submitHeadbtn"
											class="easyui-linkbutton"
											data-options="iconCls:'icon-large-smartart'"
											style="width: 120px" onclick="submitHead()">提交申请单</a></td>

									</tr>
									<tr style="display: none">
										<td><input id="newpageNumber" class="easyui-textbox"
											type="text" style="width: 0px;" name="pageNumber" value=""></input></td>
										<td><input id="newpageSize" class="easyui-textbox"
											type="text" style="width: 0px;" name="pageSize" value=""></input></td>
									</tr>
									<tr style="display: none">
										<td input id="detail_Hid" name="id" class="easyui-textbox"
											style="height: 20px"></td>
										<td><input id="uuid" class="easyui-textbox"
											name="appuseruuid" style="height: 20px"></input></td>
									</tr>
									<tr style="display: none">
										<td input id="rowsid" name="rowsids" class="easyui-textbox"
											style="height: 20px"></td>
										<td input id="rowsid" name="dgrequestdetaildata" class="easyui-textbox"
											style="height: 20px"></td>
										<td input id="rowsid" class="easyui-textbox"
											style="height: 20px"></td>
									</tr>
									<tr style="display: none">
										<td input id="invoice_req_dlg_bornTime" class="easyui-textbox" name="invoiceReqDate"></td>
										<td input id="dlg_id" name="id" class="easyui-textbox" style="height:0px"></td>
										<td input id="dlg_ids" name="ids" class="easyui-textbox" style="height:0px"></td> 
										<td input id="dlg_hid" name="crvatInvoiceReqHId" class="easyui-textbox" style="height:0px"></td>
										<td input id="dlg_number" name="crvatInvoiceReqNumber" class="easyui-textbox"></td>
										<td input id="invoice_req_dlg_reqStatus" class="easyui-textbox" name="status"></td>
										<td input id="invoice_req_dlg_cardType" class="easyui-textbox" name="custRegistrationCode" style="width:0px"></td>
										<td input id="invoice_req_dlg_cardNO" name="custRegistrationNumber" class="easyui-textbox"></td>
										<td input id="invoice_req_dlg_customerCode" name="customerCode" class="easyui-textbox"></td> 
										<td input class="easyui-textbox" id="invoice_req_dlg_customerId" name="customerId"  style="width: 0px;">
										<td input class="easyui-textbox" id="invoice_req_dlg_createdBy" name="createdBy"  style="width: 0px;">
										<td input class="easyui-textbox" id="dlg_reqInvoiceRange" name="reqInvoiceRange" style="width: 0px;">
										<td input id="invoice_print_newSearch_No" name="readyNo" class="easyui-textbox"></td>
										<td><select id="invoice_print_newSearch_isCommitPaper" name="isCommit" class="easyui-textbox" style="width: 0px;"></td>
									</tr>
								</table>
							</form>
						</div>
					</div>
				</div>
			</div>
			<div data-options="region:'center'" style="width: 100%; height: 70%;">
				<div class="easyui-layout" style="width: 100%; height: 100%;">
					<div data-options="region:'center'"
						style="width: 100%; height: 50%;" title="发票明细">
						<div style="width: 100%; height: 100%">
							<table class="easyui-datagrid" id="dgrequestdetail"
								style="width: 100%; height: 100%">
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
	</div>
	</div>
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
								<td><spring:message code="invoiceprint.validType" />:</td>
								<td>
									<div>
										<input:select id="invoice_print_newSearch_validType"
											name="custRegistrationCode"
											value="$invoice_print_newSearch_validType"
											easyuiClass="easyui-combobox" easyuiStyle="width:120px;">
											<option value=""></option>
											<input:systemStatusOption
												parentCode="VAT_CUSTOMER_DISC_OPTION" />
										</input:select>
										<input id="invoice_print_newSearch_name"
											class="easyui-textbox" style="width: 120px"
											name="custRegistrationNumber" /></input>
									</div>
								</td>

								<td><spring:message code="invoiceTrxPool.customerCode" />:</td>
								<td><input id="invoice_print_newSearch_Code"
									class="easyui-textbox" style="width: 120px;"
									name="customerNumber" /></input></td>
								<td><spring:message code="invoiceprint.readyStatus" />:</td>
								<td><input class="easyui-combobox" name="status"
									style="width: 120px"
									data-options="url:'${vat}/invoiceSpecial/getdictionary.do',
                                           method:'get',
                                           valueField:'value',
                                           textField:'text',
                                           panelHeight:'auto' " />
								</td>
							</tr>
							<tr>
								<td><spring:message code="invoiceprint.search.applyTime" />:</td>
								<td>
									<div>
										<input id="applytime" class="easyui-datebox"
											style="width: 110px;" name="invoiceReqStartDate" /></input>
										<spring:message code="invoiceprint.search.to" />
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
		$('#dg').datagrid(
						{
							url : '',
							nowrap : false,
							pagination : true,
							rownumbers : true,
							singleSelect : false,
							fitColumns : false,
							striped : true,
							idField : 'id', //主键字段  
							columns : [ [
									{
										field : 'ck',
										checkbox : true,
										width : 2
									}, //显示复选框     
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
										field : 'orgCode',//开票申请组织
										title : '<spring:message code="invoiceprint.dept"/>',
										width : 100,
										align : 'center'
									},{
										field : 'invoiceReqType',//申请开票类型(柜台/特殊/自动)
										title : '申请开票类型',
										width : 100,
										align : 'center'
									},
									{
										field : 'customerName',//购方单位名称
										title : '<spring:message code="invoiceAbolish.purchaseName"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'customerNumber',//购方编码
										title : '<spring:message code="client.clientCode"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'custRegistrationCode',//购方证件类型
										title : '<spring:message code="invoiceprint.validType"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'custRegistrationNumber',//购方证件号码
										title : '<spring:message code="invoiceprint.validName"/>',
										width : 120,
										align : 'center'
									},
									{
										field : 'contactName',//购方纳税联系人
										title : '购方纳税联系人',
										width : 120,
										align : 'center',
										hidden:true
									},
									{
										field : 'custRegistrationAddress',//购方纳税人地址
										title : '购方纳税人地址',
										width : 120,
										align : 'center',
											hidden:true
									},{
										field : 'custDepositBankName',//购方开户银行名称
										title : '购方开户银行名称',
										width : 120,
										align : 'center',
										hidden:true
									},{
										field : 'custDepositBankAccountNum',//购方开户账号
										title : '购方开户账号',
										width : 120,
										align : 'center',
										hidden:true//,
									},{
										field : 'isReceipts',//是否已首款
										title : '是否已首款',
										width : 120,
										align : 'center',
										hidden:true
									},{
										field : 'isTax',//是否含税
										title : '是否含税',
										width : 120,
										align : 'center',
										hidden:true
									},{
										field : 'reqInvoiceRange',//发票打印受理层级范围
										title : '发票打印受理层级范围',
										width : 120,
										align : 'center',
										hidden:true
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
										field : 'reqAmount',//合计金额
										title : '<spring:message code="invoiceprint.amountAll"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'acctdAmountCr',
										title : '<spring:message code="invoiceprint.amount"/>',
										width : 100,
										align : 'center'
									}, ] ],
							toolbar : [ {
								text : '新增申请单',
								iconCls : 'icon-add',
								handler : function() {
									addPre();//新增申请单方法
								}
							}, '-', {
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
							}, '-' ],
							onLoadSuccess : function() {
								$('#dg').datagrid('clearSelections')
							},
							onClickRow : function(index, data) {
								var row = $('#dg').datagrid('getSelected');
								if (row) {
									//alert("");
								}
							}
						});
	
		$('#dgrequestdetail').datagrid({//左滑面板发票明细表格
			url : '',
			nowrap : false,
			rownumbers : true,
			fitColumns : false,
			striped : true,
			idField : 'id', //主键字段  
			columns : [[ {
				field : 'ck',
				checkbox : true,
				width : 2
					}, {
						field : 'id',
						title : "商品及服务id",
						width : 80,
						editor : 'text',
						hidden:true
					}, 
					{
						field : 'inventoryItemDescripton',
						title : '应税劳务、服务名称',
						width : 120,
						editor : {
							type : 'combogrid',
							options : {
								panelWidth : 1000,
								panelHeight :300,
								pagination:true,//分页
								idField : 'inventoryItemDescripton', //主键字段
								url : "${vat}/invoiceSpecial/getTmsMdInventoryCategories.do",
								columns : [ [{
									field : 'id',
									title : "商品及服务id",
									width : 80,
									hidden:true
								}, {
									field : 'inventoryItemNumber',
									title : "商品及服务代码",
									width : 80
								}, {
									field : 'inventoryItemDescripton',
									title : "应税劳务、服务名称",
									width : 120
								} ,{
									field : 'inventoryItemModels',
									title : "规格型号",
									width : 120
								},{
									field : 'uomCode',
									title : "单位",
									width : 120
								},{
									field : 'taxRate',
									title : "税率",
									width : 120
								}] ],
								onSelect : function (rowIndex,rowData) {
									var row = $('#dgrequestdetail').datagrid('getSelected');
									var rindex = $('#dgrequestdetail').datagrid('getRowIndex', row);
									var ed = $('#dgrequestdetail').datagrid('getEditor', {
										index : rindex,
										field : 'uomCode'
									});
									$(ed.target).val(rowData.uomCode);
									var inventoryItemModels = $('#dgrequestdetail').datagrid('getEditor', {
										index : rindex,
										field : 'inventoryItemModels'
									});
									$(inventoryItemModels.target).val(rowData.inventoryItemModels);
											
									var id = $('#dgrequestdetail').datagrid('getEditor', {
										index : rindex,
										field : 'id'
									});
											 
									$(id.target).val(rowData.id);
									
									var taxRate = $('#dgrequestdetail').datagrid('getEditor', {
										index : rindex,
										field : 'taxRate'
									});
											
									$(taxRate.target).numberbox('setValue',rowData.taxRate);
								}
							}
						}
					},
					{
						field : 'inventoryItemModels',
						title : '规格型号',
						width : 100,
						align : 'center',
						editor : 'text'
					}, {
						field : 'uomCode',
						title : '单位',
						width : 100,
						align : 'center',
						editor : 'text'
					}, {
						field : 'taxTrxTypeCode',
						title : '数量',
						width : 100,
						align : 'center',
						editor:{
							type : 'numberbox',
						    options : {
						      	required:true
						      }
						}
					
					}, {
						field : 'legalEntityName',
						title : '单价',
						width : 100,
						align : 'center',
						editor:{
							type : 'numberbox',
						    options : {
							     precision:"2",
						      	required:true
						      }
						}
					}, {
						field : 'legalEntityCode',
						title : '合计金额',
						width : 100,
						align : 'center',
						editor:{
							type : 'numberbox',
						    options : {
							     precision:"2",
						      }
						}
					}, {
						field : 'taxRate',
						title : '税率（%）',
						width : 100,
						align : 'center',
						editor : 'numberbox'
					}, {
						field : 'trxDate',
						title : '净额',
						width : 100,
						align : 'center',
						editor : 'numberbox'
					}, {
						field : 'inventory',
						title : '税额',
						width : 100,
						align : 'center',
						editor : 'numberbox'
					} 
				]],toolbar : [ {
					text : '新增明细',
					iconCls : 'icon-add',
					handler : function() {
						addDetails();
					}
				}, '-',{
					text : '删除明细',
					iconCls : 'icon-remove',
					handler : function() {
						inventoryItemRemove(); //删除

					}
				}, '-',  {
					text : '保存明细',
					iconCls : 'icon-save',
					handler : function() {
						inventoryItemSave(); 
					}
				}, '-' ],
			onBeforeLoad : function () {
				$(this).datagrid('rejectChanges');
			},onDblClickRow:function(index,data){
		           
	             $(this).datagrid('beginEdit', index);
	             
	  },
			/* onClickRow : function (rowIndex) {
				if (lastIndex != rowIndex) {
					$dg.datagrid('endEdit', lastIndex);
					$dg.datagrid('beginEdit', rowIndex);
				}
				lastIndex = rowIndex;
			} */
		});

		//设置分页控件	
		var p = $('#dg').datagrid('getPager');
		$(p).pagination(
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

	$(document).ready(function() {
		enableOrdisable(true);
		$("#layoutid").layout('collapse', 'east');//隐藏右侧面板
		$('#searchform').form('load', {
			pageNumber : $('#dg').datagrid('options').pageNumber,
			pageSize : $('#dg').datagrid('options').pageSize
		});
		$("#invoice_print_searchOut_validType").combobox({
				onSelect:function(record){
				getCustomerInfoByCode();
			}
		});
		Search();
	});

	/**
	 * 发票明细保存
	 */
	function inventoryItemSave(){
		$('#dgrequestdetail').datagrid('acceptChanges');
		var rows = $("#dgrequestdetail").datagrid('getRows');
		console.info(rows);
	}
	/**
	 * 添加交易信息
	 */
	 var leijisumCount= 0;
	 function addDetails(){
		var flag = $("#isTaxid").combobox('getValue');//是否含税
		var isReceiptsid = $("#isReceiptsid").combobox('getValue');//是否已收款
		if(isReceiptsid===""){
			 $.messager.alert('错误','请先选择是否已收款！！');
			 return;
		}
		if(flag===""){
			 $.messager.alert('错误','请先选择是否含税！！');
			 return;
		}
		if(flag==="1"){
			var data=$('#dgrequestdetail').datagrid('getData');
			var rows = data.total;
		 	if(rows>0){
			 	inventoryItemSave();
				var rowdata = $("#dgrequestdetail").datagrid('getSelected');
				var legalEntityName = parseInt(rowdata.legalEntityName);//单价
				var taxTrxTypeCode = parseInt(rowdata.taxTrxTypeCode);//数量
				var taxRate = parseInt(rowdata.taxRate)/100;//税率
				var sumCount = legalEntityName*taxTrxTypeCode;//合计金额
				var inventory = (sumCount/(1+taxRate))*taxRate;//税额
				var trxDate = sumCount-inventory;//净额
				leijisumCount = leijisumCount+sumCount;
				$('#dgrequestdetail').datagrid('updateRow',{index:0,row:{legalEntityCode:sumCount,inventory:inventory,trxDate:trxDate}});
				if(isReceiptsid==="1"){//
					$("#invoice_print_newSearch_amountAll").textbox("setValue",leijisumCount);
				}else{
					$("#invoice_print_newSearch_amountAll").textbox("setValue",0);
				}
		  }
		  if(rows<8){
			  $('#dgrequestdetail').datagrid('insertRow', {
					index : 0,
					row : {
					},
			  });
			  $('#dgrequestdetail').datagrid('clearSelections');
			  //将第一行设置为可编辑状态
			  $('#dgrequestdetail').datagrid('beginEdit',0);
			  $('#dgrequestdetail').datagrid('selectRow', 0);
			  this.editRow = 0;
		 }else{
			 $.messager.alert('错误','最多只能添加八张！！');
		 }
		}
	}

	/**
	 * 发票明细删除
	 */
	 function inventoryItemRemove(){
		 $.messager.confirm('确认','确认删除?',function(row){ 
	         if(row){   
	        	 var rows = $('#dgrequestdetail').datagrid('getSelections');
	        	 var rowsLength = rows.length+1;
	        	 for(var i=0; i<rowsLength; i++){
	             var rowIndex=$('#dgrequestdetail').datagrid('getRowIndex',rows[0]);
	             	$('#dgrequestdetail').datagrid('deleteRow',rowIndex);   
	        	 }
	        	 $('#dgrequestdetail').datagrid('clearSelections');
	     		 $('#dgrequestdetail').datagrid('selectRow', 0);
	         }
		  });
	}
	
	function findDg(pageNumber, pageSize) {//重新对申请表单明细分页信息
		$('#searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		Search();
	}
	
	function Search() {//搜索申请单
		$("#dg").datagrid("loading");
		$('#searchform').form('submit', {
			url : '${vat}/invoiceSpecial/getInvoiceReqAll.do',
			success : function(result) {
				var result = $.parseJSON(result);
				$("#dg").datagrid('loadData', result);
				$("#dg").datagrid("loaded");
			}
		});
	}
	function editDetail() {//编辑申请单
		var rows = $('#dg').datagrid('getSelections');//得到选择数据行

		if (rows.length == 1) {//判断行数
			var row = $('#dg').datagrid('getSelected');//得到行数据
			if ("10" == row.reqStatus) {//判断申请单状态
				$('#layoutid').layout('expand', 'east');//关闭右侧滑动面板
				enableOrdisable(true);
				$("#newDetailForm").form('load',row);
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
				if('10'!=r.reqStatus){
					isCommit=false;
					$.messager.alert('<spring:message code="invoiceprint.reqinfo"/>','<spring:message code="invoiceprint.deleteDisabled"/>' );
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
				url : "${vat}/invoiceReqH/removeInvoiceReqHs.do?ids=" + ps,
				type : 'POST',
				dataType : "json",
				cache : false,
				success : function(object) {
					$.messager.alert('<spring:message code="system.alert"/>',
							object.msg);
					Search();
				}
			});
		} else {
			$.messager.alert('<spring:message code="invoiceprint.reqinfo"/>',
					'<spring:message code="invoiceprint.error"/>');
		}
	}
	function clearSearchForm() {//清除申请表单
		$('#searchform').form('clear');
		var pageNumber = $('#dg').datagrid('options').pageNumber;
		var pageSize = $('#dg').datagrid('options').pageSize;
		$('#searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
	}
	function clearDetailForm() {//清空右侧滑动面版表单
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
	function commitPaper() { //提交申请表
		var rows = $('#dgrequestdetail').datagrid("getRows");
		var id = $('#invoice_print_newSearch_readyNo').val();
		$('#invoice_print_newSearch_isCommitPaper').textbox('setValue', '1');
		if (id && typeof (id) != "undefined") {
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
			$('#newDetailForm').form(
					'submit',
					{
						url : '${vat}/invoiceReqAll/updateCommitStatus.do',
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
						url : '${vat}/invoiceReqAll/saveInvoiceReqAll.do',
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
		clearFormAndData();
		$('#layoutid').layout('expand', 'east');
		$('#invoice_print_newSearch_reqStatus').combobox('select','10');//默认将申请状态设置为草稿
		$('#invoice_print_newSearch_applyTime').datebox('setValue', formatterDate(new Date()));//默认将申请单生成日期设置为当前日期
		
		$.ajax({
			url : "${vat}/invoiceSpecial/getCreanumber.do",
			dataType : "json",
			cache : false,
			success : function(object) {
				$('#invoice_print_newSearch_readyNo').textbox('setValue', object.invoicereq);
				$('#dlg_number').textbox('setValue', object.invoicereq);
			}
		});
	}
	//通过购方编码带出其他条件
	function getCustomerInfoByNumber() {
		var validNo = $('#invoice_print_newSearch_validNo').val();
		var number = $('#invoice_print_newSearch_customerCode').val();
		var code=$('#invoice_print_searchOut_validType').combobox('getValue');
		var as = true;
		if(number){
			code="";
			validNo="";
		} 
		$.ajax({
			url : "${vat}/invoiceSpecial/getCustomerParam.do?custRegistrationNumber="
					+ validNo + "&customerNumber=" + number+"&custRegistrationCode="+code,
			dataType : "json",
			cache : false,
			success : function(object) {
				$('#invoice_print_newSearch_bank').textbox('setValue',
						object.customer.custDepositBankName);
				$('#invoice_print_newSearch_bankNum').textbox('setValue',
						object.customer.custDepositBankAccountNum);
				$('#invoice_print_newSearch_contactName').textbox('setValue',
						object.customer.contactName);
				$('#invoice_print_newSearch_address').textbox('setValue',
						object.customer.custRegistrationAddress);
				$('#invoice_print_newSearch_buyerCompanyName').textbox(
						'setValue', object.customer.customerName);
				$('#invoice_print_newSearch_customerId').textbox('setValue',
						object.customer.id);
				$('#invoice_print_newSearch_customerCode').val(object.customer.customerNumber);
				$('#invoice_print_newSearch_validNo').val(object.customer.custRegistrationNumber);
				$('#invoice_print_searchOut_validType').combobox('setValue',object.customer.custRegistrationCode);
				if (object.customer.id) {
					$('#newsearchbtn').linkbutton('enable');
				}
			}
		});
	}
	//通过购方证件号码和证件类型带出
	function getCustomerInfoByCode() {
		var validNo = $('#invoice_print_newSearch_validNo').val();
		var number = $('#invoice_print_newSearch_customerCode').val();
		var code=$('#invoice_print_searchOut_validType').combobox('getValue');
		var as = true;
		if(validNo){
			number="";
		}
		if(validNo){
			$.ajax({
				url : "${vat}/invoiceReqH/getCustomerParam.do?custRegistrationNumber="
						+ validNo + "&customerNumber=" + number+"&custRegistrationCode="+code,
				dataType : "json",
				cache : false,
				success : function(object) {
					$('#invoice_print_newSearch_bank').textbox('setValue',
							object.customer.custDepositBankName);
					$('#invoice_print_newSearch_bankNum').textbox('setValue',
							object.customer.custDepositBankAccountNum);
					$('#invoice_print_newSearch_contactName').textbox('setValue',
							object.customer.contactName);
					$('#invoice_print_newSearch_address').textbox('setValue',
							object.customer.custRegistrationAddress);
					$('#invoice_print_newSearch_buyerCompanyName').textbox(
							'setValue', object.customer.customerName);
					$('#invoice_print_newSearch_customerId').textbox('setValue',
							object.customer.id);
					$('#invoice_print_newSearch_customerCode').val(object.customer.customerNumber);
					$('#invoice_print_newSearch_validNo').val(object.customer.custRegistrationNumber);
					$('#invoice_print_searchOut_validType').combobox('setValue',object.customer.custRegistrationCode);
					if (object.customer.id) {
						$('#newsearchbtn').linkbutton('enable');
					}
				}
			});
		}
	}

	function saveHead() {//保存申请单
		var dgrequestdetaildata = $('#dgrequestdetail').datagrid('getRows');//得到发票明细数据
		$('#newDetailForm').form('load', {
			dgrequestdetaildata : JSON.stringify(dgrequestdetaildata),
		});

		$('#newDetailForm').form(
				'submit',
				{
					url : '${vat}/invoiceSpecial/saveInvoiceReqHead.do',
					success : function(object) {
						$("#layoutid").layout('collapse', 'east');
						Search();
						var object = $.parseJSON(object);
						clearNewSearchForm();
						$.messager.alert(
								'<spring:message code="system.alert"/>',
								object.msg);
					}
				});

	}
	//从申请单查询页面提交申请单
	function submitFromPage() {
		var rows = $('#dg').datagrid('getChecked');
		var isCommit=true;
		for (var i = 0; i < rows.length; i++) {
			var r = rows[i];
			if('10'!=r.pageStatus){
				isCommit=false;
				$.messager.alert('<spring:message code="invoiceprint.reqinfo"/>','<spring:message code="invoceReqstatus"/>' );
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
			url : "${vat}/invoiceReqAll/submitFromPage.do?ids=" + ps,
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
	//交易明细页面提交申请单
	function submitHead() {
		var hid = $('#detail_Hid').val();
		/* if(hid){ */
		var ss = [];
		var rows = $('#dgrequestdetail').datagrid('getRows');
		for (var i = 0; i < rows.length; i++) {
			var r = rows[i];
			ss.push(r.trxid);
		}
		var ids = ss.join(";");
		$('#newDetailForm').form('load', {
			ids : ids
		});
		$('#newDetailForm').form(
				'submit',
				{
					url : '${vat}/invoiceReqAll/updateCommitStatus.do',
					success : function(object) {
						var object = $.parseJSON(object);
						//clearNewSearchForm();
						$.messager.alert(
								'<spring:message code="system.alert"/>',
								object.msg);
						$("#layoutid").layout('collapse', 'east');
						Search();
					}
				});
		/* }else{
			$.messager.alert('<spring:message code="invoiceprint.reqinfo"/>','<spring:message code="invoiceprint.info"/>' );
		} */
	}
	
	//回车事件
	$("#invoice_print_newSearch_validNo").keyup(function(event){        
		if(event.keyCode == 13){  
			getCustomerInfoByCode();
		}   
	});
	$("#invoice_print_newSearch_customerCode").keyup(function(event){        
		if(event.keyCode == 13){    
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
	
	function enableOrdisable(enable){
		$("#invoice_print_newSearch_readyNo").textbox({ disabled: enable }); 
		$("#invoice_print_newSearch_applyTime").datebox({ disabled: enable }); 				
		$("#invoice_print_newSearch_reqStatus").combobox({ disabled: enable });
		$("#invoice_print_newSearch_buyerCompanyName").textbox({ disabled: enable }); 
		$("#invoice_print_newSearch_bank").textbox({ disabled: enable }); 
		$("#invoice_print_newSearch_bankNum").textbox({ disabled: enable });
		$("#invoice_print_newSearch_contactName").textbox({ disabled: enable });
		$("#invoice_print_newSearch_address").textbox({ disabled: enable });
		$("#invoice_print_newSearch_amountAll").textbox({ disabled: enable });
	}
</script>

</html>