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
			<div data-options="region:'north'" style="width: 100%; height: 30%;">
				<div class="easyui-panel"				
					style="width: 100%; height: 100%; background-color: #E0ECFF">
					<form id="newDetailForm" method="post">
						<table style="width: 1000px; height: 100%">
							<tr>
								<td><spring:message code="invoiceprint.readyNo" />：</td>
								<td><input class="easyui-textbox"
									id="invoice_print_newSearch_readyNo"
									name="crvatInvoiceReqNumber" readonly="true"
									style="width: 120px;"></td>
								<td><spring:message code="invoiceprint.search.time" />：</td>
								<td><input id="invoice_print_newSearch_applyTime"
									readonly="true" name="invoiceReqDate" readonly="true"
									class="easyui-datebox" style="width: 120px;"></td>
								<td><spring:message code="invoiceprint.readyStatus" />：</td>
								<td><input class="easyui-combobox"
									id="invoice_print_newSearch_reqStatus" name="status"
									readonly="true" style="width: 120px"
									data-options="
									 url:'invoiceReqH/getdictionary.do',
	                                 method:'get',
	                                 valueField:'value',
	                                 textField:'text',
	                                 panelHeight:'auto'
	                               ">
                                </td>								
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
									style="width: 120px" editable="false"
									data-options="
									 url:'invoiceReqH/getLeveldictionary.do',
	                                 method:'get',
	                                 valueField:'value',
	                                 textField:'text',
	                                 panelHeight:'auto'
	                                 ">
								</td>
							</tr>
							<tr>
								<td><spring:message code="client.bank" />：</td>
								<td><select id="invoice_print_newSearch_bank"
									name="bankBranchName" class="easyui-textbox" readonly="true"
									style="width: 120px;"></td>
								<td><spring:message code="client.bankNum" />：</td>
								<td><input id="invoice_print_newSearch_bankNum"
									name="bankNum" class="easyui-textbox" readonly="true"
									style="width: 120px;"></td>
								<td><spring:message code="contacts" />：</td>
								<td><select id="invoice_print_newSearch_contactName"
									name="contactName" class="easyui-textbox" readonly="true"
									style="width: 120px;"></td>
								<td><spring:message code="address" />：</td>
								<td><input id="invoice_print_newSearch_address"
									name="address" class="easyui-textbox" readonly="true"
									style="width: 120px;"></td>
							</tr>
							<tr>
								<td><spring:message code="invoiceprint.validType" />：</td>
								<td><input id="invoice_print_searchOut_validType"
									class="easyui-combobox" name="custRegistrationCode"
									style="width: 120px" required="true" editable="false"
									data-options="url:'invoiceReqH/getTypedictionary.do',
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
								<td><spring:message code="invoiceprint.amountAll" />：</td>
								<td><input id="invoice_print_newSearch_amountAll"
									name="reqAmount" class="easyui-textbox" readonly="true"
									style="width: 120px;"></td>
							</tr>
							<tr>
								<td><a href="#" id="saveHeadbtn" class="easyui-linkbutton"
									data-options="iconCls:'icon-large-shapes'" style="width: 120px"
									onclick="saveHead()">保存申请单</a></td>
								<td><a href="#" id="submitHeadbtn"
									class="easyui-linkbutton"
									data-options="iconCls:'icon-large-smartart'"
									style="width: 120px" onclick="submitHead()">提交申请单</a></td>
								<td><a href="#" id="newclearbtn" class="easyui-linkbutton"
									style="width: 80px" onclick="back()"><spring:message
											code="button.Back" /></a></td> 
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


							</tr>
							<tr style="display: none">
								<td input id="invoice_print_newSearch_ids" name="ids"
									class="easyui-textbox"></td>
								<!-- <td input id="invoice_print_newSearch_status" name="status" class="easyui-textbox"></td> -->
								<td input id="invoice_print_newSearch_No" name="readyNo"
									class="easyui-textbox"></td>
								<td input class="easyui-textbox"
									id="invoice_print_newSearch_customerId" name="customerId"
									style="width: 0px;">
								<td input class="easyui-textbox"
									id="invoice_print_newSearch_createdBy" name="createdBy"
									style="width: 0px;">
								<td><select id="invoice_print_newSearch_isCommitPaper"
									name="isCommit" class="easyui-textbox" style="width: 0px;"></td>
							</tr>
						</table>
					</form>
				</div>

			</div>
			<div data-options="region:'center'" style="width: 100%; height: 70%;">
				   <div style="width: 100%; height: 100%">
						<table class="easyui-datagrid" id="dgrequestdetail"
							toolbar="#requestdetailtoolbar" style="width: 100%; height: 100%"
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
				   <div id="requestdetailtoolbar" region="north" border="false"
						style="border-bottom: 1px solid #ddd; height: 32px; padding: 2px 5px; background: #fafafa;">
						<div style="float: left;">
							<a href="#" class="easyui-linkbutton" plain="true"
								onclick="addDetail()" icon="icon-add"><spring:message
									code="button.Add" /></a>
						</div>
						<div class="datagrid-btn-separator"></div>
						<div style="float: left;">
							<a href="#" class="easyui-linkbutton" plain="true"
								onclick="deleteDetail()" icon="icon-remove"><spring:message
									code="button.Remove" /></a>
						</div>
				 </div>		
			</div>
			
			<div id="tax_req_dlg" class="easyui-dialog" style="width: 100%; height: 100%;" closed="true" buttons="#dlg-buttons">			   			   			   
			   <div class="easyui-layout" style="width:100%;height:100%;">
					<div data-options="region:'north'" style="width:100%;height:10%;">					   
					    <form id="reqForm" method="post">
				        	<table>
						  		<tr>						  		
						  		    <td><spring:message code="invoiceprint.withoutTrade"/>:</td>
					    			<td>
					    				<select id="tax_req_dlg_owner" name="isExitsCustomer" class="easyui-combobox" style="width:70px">
				    					<option value="" selected>&nbsp;</option>
				    					<option value="1">是</option>
				    					<option value="0" >否</option>
				    				</select>
					    			</td>
									<td><spring:message code="invoiceTrxPool.trxDate"/>:</td>
					    			<td>
					    				 <input id="time" class="easyui-datebox" style="width:110px;" name="trxStartDate" /></input>
					    			</td>
					    			<td><spring:message code="invoiceprint.search.to"/>:</td>
					    			<td>
					    				 <input id="to" class="easyui-datebox" style="width:110px;" name="trxEndDate" /></input>
					    			</td>
					    		
									<td><spring:message code="goodsCode" />：</td>
									<td><input id="tax_req_dlg_goodsCode" class="easyui-textbox" style="width:110px;" name="inventoryItemNumber"/>								
									<td>
										<a href="#" id="newsearchbtn" class="easyui-linkbutton"
										data-options="iconCls:'icon-search'" style="width: 80px"
										onclick="searchWithOut()"><spring:message code="button.Search" /></a>
									</td>
									<td><a href="#" id="newclearbtn" class="easyui-linkbutton"
										style="width: 80px" onclick="clearDlgForm()"><spring:message code="button.Clear" /></a></td>
								</tr>
								<tr style="display: none">
									<td><input id="dlgpageNumber" class="easyui-textbox"
										type="text" style="width: 0px;" name="pageNumber" value=""></input></td>
									<td><input id="dlgpageSize" class="easyui-textbox" type="text"
										style="width: 0px;" name="pageSize" value=""></input></td>
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
								</tr>	    
						  	</table>
			           </form>				
					</div>				
					<div data-options="region:'center'" style="width:100%;height:80%;">
						 <table class="easyui-datagrid" id="dlgDetial" title="<spring:message code="taxItemsmaintain.trxdetailInfo"/>" style="width:100%;height:100%" 
			                 data-options="					
								singleSelect:false,
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
			<div id="dlg-buttons">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-ok" onclick="addNo()"><spring:message
						code="button.Add" /></a> <a href="javascript:void(0)"
					class="easyui-linkbutton" iconCls="icon-cancel"
					onclick="javascript:$('#tax_req_dlg').dialog('close')"><spring:message
						code="button.Close" /></a>
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
							   <td><spring:message code="invoiceprint.validType"/>:</td>																									
							   <td align="left">
								   <div>
					    				<input:select id="invoice_print_newSearch_validType"
												name="custRegistrationCode" value="$invoice_print_newSearch_validType"
												easyuiClass="easyui-combobox" easyuiStyle="width:120px;">
												<option value=""></option>
												<input:systemStatusOption parentCode="VAT_CUSTOMER_DISC_OPTION"  />
										</input:select> 	    			
					    			    <input id="invoice_print_newSearch_name" class="easyui-textbox" style="width:120px" name="custRegistrationNumber"/></input>
				    			   </div>
				    			</td>  

								<td align="left"><spring:message code="invoiceTrxPool.customerCode" />:</td>
								<td align="left"><input id="invoice_print_newSearch_Code"
									class="easyui-textbox" style="width: 120px;"
									name="customerNumber" /></input></td>
								<td align="left"><spring:message code="invoiceprint.readyStatus" />:</td>
								<td align="left"><input class="easyui-combobox" name="status"
									style="width: 120px" editable="false"
									data-options="url:'invoiceReqH/getdictionary.do',
                                           method:'get',
                                           valueField:'value',
                                           textField:'text',
                                           panelHeight:'auto' " />                                
							     </td>  
							</tr>
							<tr>
							    <td><spring:message code="invoiceprint.search.applyTime"/>:</td>
				    			<td>
				    			   <div>
				    				 <input id="applytime" class="easyui-datebox" style="width:110px;" name="invoiceReqStartDate" /></input>				    			
				    			      <spring:message code="invoiceprint.search.to"/>:				    			
				    				 <input id="applyto" class="easyui-datebox" style="width:110px;" name="invoiceReqendDate" /></input>
				    			   </div>
				    			</td>						    
								<td>
									 <a href="#" id="searchbtn" class="easyui-linkbutton"
									data-options="iconCls:'icon-search'"  style="width: 80px"
									onclick="Search()"><spring:message code="button.Search" /></a>
								</td>
								<td align="left"><a href="#" id="clearbtn" class="easyui-linkbutton"
									style="width: 80px" onclick="clearSearchForm()"><spring:message
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

	$(function() {
		$('#dg')
				.datagrid(
						{
							url : '',
							fitColumns : true,
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
										field : 'crvatInvoiceReqNumber',
										title : '<spring:message code="invoiceprint.readyNo"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'status',
										title : '<spring:message code="invoiceprint.search.bootStatus"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'orgName',
										title : '<spring:message code="invoiceprint.dept"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'customerName',
										title : '<spring:message code="invoiceAbolish.purchaseName"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'customerNumber',
										title : '<spring:message code="client.clientCode"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'custRegistrationCode',
										title : '<spring:message code="invoiceprint.validType"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'custRegistrationNumber',
										title : '<spring:message code="invoiceprint.validName"/>',
										width : 120,
										align : 'center'
									},
									{
										field : 'invoiceReqDate',
										title : '<spring:message code="invoiceprint.search.applyTime"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'createdBy',
										title : '<spring:message code="invoiceprint.applicant"/>',
										width : 100,
										align : 'center'
									},
									//{ field:'', title:'<spring:message code="invoiceprint.search.bootStatus"/>',width:100,align:'center'},
									{
										field : 'reqAmount',
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
								text : '添加申请单',
								iconCls : 'icon-add',
								handler : function() {
									addPre();
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
									loadSaveFormData(row);
								}
							}
						});
		$('#dlgDetial')
				.datagrid(
						{
							url : '',
							fitColumns : true,
							nowrap : false,
							pagination : true,
							rownumbers : true,
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
										field : 'orgCode',
										title : '<spring:message code="invoiceTrxPool.orgCode"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'orgName',
										title : '<spring:message code="invoiceTrxPool.orgName"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'taxTrxTypeName',
										title : '<spring:message code="invoiceprint.taxType"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'taxTrxTypeCode',
										title : '<spring:message code="invoiceprint.taxCode"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'legalEntityName',
										title : '<spring:message code="invoiceprint.search.sellerCompanyName"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'legalEntityCode',
										title : '<spring:message code="invoiceprint.sellerCompanyNo"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'trxNumber',
										title : '<spring:message code="invoiceTrxPool.trxNumber"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'trxDate',
										title : '<spring:message code="invoiceTrxPool.trxDate"/>',
										width : 100,
										align : 'center'
									},
									//{ field:'inventoryItemDescripton', title:'<spring:message code="product"/>',width:100,align:'center'},
									{
										field : 'inventoryItemDescripton',
										title : '<spring:message code="goodsName"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'invoiceAmount',
										title : '<spring:message code="invoiceprint.amountAll"/>',
										width : 100,
										align : 'center'
									},
									//{ field:'taxTrxTypeName', title:'<spring:message code="taxItemsmaintain.itemInfo"/>',width:100,align:'center'},
									{
										field : 'taxRate',
										title : '<spring:message code="taxRate"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'userfulAmount',
										title : '<spring:message code="invoiceprint.canAmount"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'usedAmount',
										title : '<spring:message code="invoiceprint.billingAmount"/>',
										width : 100,
										align : 'center'
									}, ] ],
							onLoadSuccess : function() {
								$('#dlgDetial').datagrid('clearSelections')
							},
						});
		$('#dgrequestdetail')
				.datagrid(
						{
							url : '',
							title : '<spring:message code="invoiceprint.transaction"/>',
							fitColumns : true,
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
										field : 'orgCode',
										title : '<spring:message code="invoiceTrxPool.orgCode"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'orgName',
										title : '<spring:message code="invoiceTrxPool.orgName"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'taxTrxTypeName',
										title : '<spring:message code="invoiceprint.taxType"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'taxTrxTypeCode',
										title : '<spring:message code="invoiceprint.taxCode"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'legalEntityName',
										title : '<spring:message code="invoiceprint.search.sellerCompanyName"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'legalEntityCode',
										title : '<spring:message code="invoiceprint.sellerCompanyNo"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'trxNumber',
										title : '<spring:message code="invoiceTrxPool.trxNumber"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'trxDate',
										title : '<spring:message code="invoiceTrxPool.trxDate"/>',
										width : 100,
										align : 'center'
									},
									//{ field:'inventoryItemDescripton', title:'<spring:message code="product"/>',width:100,align:'center'},
									{
										field : 'inventoryItemDescripton',
										title : '<spring:message code="goodsName"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'invoiceAmount',
										title : '<spring:message code="invoiceprint.amountAll"/>',
										width : 100,
										align : 'center'
									},
									//{ field:'taxTrxTypeName', title:'<spring:message code="taxItemsmaintain.itemInfo"/>',width:100,align:'center'},
									{
										field : 'taxRate',
										title : '<spring:message code="taxRate"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'userfulAmount',
										title : '<spring:message code="invoiceprint.canAmount"/>',
										width : 100,
										align : 'center'
									},
									{
										field : 'usedAmount',
										title : '<spring:message code="invoiceprint.billingAmount"/>',
										width : 100,
										align : 'center'
									}, ] ],
							toolbar : [ {
								text : '添加交易明细',
								iconCls : 'icon-add',
								handler : function() {
									addDetails();
								}
							}, '-', {
								text : '删除交易明细',
								iconCls : 'icon-remove',
								handler : function() {
									remove(); //删除

								}
							}, '-' ],
							onLoadSuccess : function() {
								$('#dgrequestdetail').datagrid(
										'clearSelections')
							},
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

		//设置分页控件	
		var detailp = $('#dgrequestdetail').datagrid('getPager');
		$(detailp)
				.pagination(
						{
							pageSize : 10,//每页显示dgrequestdetail的记录条数，默认为10           
							//pageList: [5,10,15,20],//可以设置每页记录条数的列表           
							beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
							afterPageText : '<spring:message code="pagination.afterPageText"/>',
							displayMsg : '<spring:message code="pagination.displayMsg"/>',
							onSelectPage : function(pageNumber, pageSize) {//分页触发		
								find(pageNumber, pageSize);
								//searchDetails(); 
								//clearSaveForm();
							}

						});

		var dlgpage = $('#dlgDetial').datagrid('getPager');
		$(dlgpage)
				.pagination(
						{
							pageSize : 10,//每页显示dgrequestdetail的记录条数，默认为10           
							//pageList: [5,10,15,20],//可以设置每页记录条数的列表           
							beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
							afterPageText : '<spring:message code="pagination.afterPageText"/>',
							displayMsg : '<spring:message code="pagination.displayMsg"/>',
							onSelectPage : function(pageNumber, pageSize) {//分页触发		
								findDlg(pageNumber, pageSize);
								//searchDetails(); 
								//clearSaveForm();
							}

						});
	});

	$(document).ready(function() {
		$("#layoutid").layout('collapse', 'east');
		$('#newDetailForm').form('load', {
			pageNumber : $('#dgrequestdetail').datagrid('options').pageNumber,
			pageSize : $('#dgrequestdetail').datagrid('options').pageSize
		});
		$('#searchform').form('load', {
			pageNumber : $('#dg').datagrid('options').pageNumber,
			pageSize : $('#dg').datagrid('options').pageSize
		});
		$('#reqForm').form('load', {
			pageNumber : $('#dlgDetial').datagrid('options').pageNumber,
			pageSize : $('#dlgDetial').datagrid('options').pageSize
		});
		$("#invoice_print_searchOut_validType").combobox({
				onSelect:function(record){
				getCustomerInfoByCode();
			}
		});

		Search();
	});

	function findDg(pageNumber, pageSize) {
		$('#searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		Search();
	}
	function find(pageNumber, pageSize) {
		$('#newDetailForm').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		searchDetails();
	}
	function findDlg(pageNumber, pageSize) {
		$('#reqForm').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		searchWithOut();
	}
	function searchWithOut() {
		$("#dlgDetial").datagrid("loading");
		$('#reqForm').form('submit', {
			url : 'invoiceReqH/transactionlist.do',
			success : function(result) {
				var result = $.parseJSON(result);
				$("#dlgDetial").datagrid('loadData', result);
				$("#dlgDetial").datagrid("loaded");
			}
		});
	}
	function searchDetails() {
		$("#dgrequestdetail").datagrid("loading");
		$('#newDetailForm').form('submit', {
			url : 'invoiceReqH/transactionlist.do',
			success : function(result) {
				var result = $.parseJSON(result);
				$("#dgrequestdetail").datagrid('loadData', result);
				//$('#invoice_print_newSearch_customerId').textbox('setValue',result.customerId);
				$("#dgrequestdetail").datagrid("loaded");
			}
		});
	}
	function Search() {
		$("#dg").datagrid("loading");
		$('#searchform').form('submit', {
			url : 'invoiceReqAll/getInvoiceReqAll.do',
			success : function(result) {
				var result = $.parseJSON(result);
				$("#dg").datagrid('loadData', result);
				$("#dg").datagrid("loaded");
			}
		});

	}
	function editDetail() {
		//var rows = $('#dg').datagrid('getChRecked');
		var rows = $('#dg').datagrid('getSelections');
		if (rows.length == 1) {
			var row = $('#dg').datagrid('getSelected');
			if ("10" == row.reqStatus) {
				var crvatInvoiceReqNumber = row.crvatInvoiceReqNumber;
				var status = row.status;
				//$('#invoice_print_newSearch_readyStatus').combobox('setValue',status);
				$('#layoutid').layout('expand', 'east');
				$('#newclearbtn').hide();
				var id = row.id;
				$.post('invoiceReqH/getEditInfo.do',
						{
							crvatInvoiceReqHId : id,
							crvatInvoiceReqNumber : crvatInvoiceReqNumber,
							pageNumber : $('#dgrequestdetail').datagrid(
									'options').pageNumber,
							pageSize : $('#dgrequestdetail')
									.datagrid('options').pageSize
						}, function(result) {
							$("#dgrequestdetail").datagrid('loadData', result);
							$('#newDetailForm').form('load', result.reqH);
							$('#detail_Hid')
									.textbox('setValue', result.reqH.id);
							$('#invoice_print_newSearch_validNo').attr(
									"readonly", "readonly");
							$('#invoice_print_newSearch_customerCode').attr(
									"readonly", "readonly");
							$('#invoice_print_newSearch_validNo').addClass(
									"textbox-icon-disabled");
							/* $('#invoice_print_newSearch_readyNo').addClass(
									"textbox-icon-disabled"); */
							$('#invoice_print_newSearch_customerCode')
									.addClass("textbox-icon-disabled");
							$('#invoice_print_newSearch_validType').combobox(
									"disable");
							$('#invoice_print_newSearch_level').combobox(
									"disable");
						}, 'json');
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

	function addDetails() {
		$('#dlgDetial').datagrid('loadData', {
			total : 0,
			rows : []
		});
		//$("#dlg_hid").textbox('setValue',$('#detail_Hid').val());
		$('#invoice_req_dlg_cardNO').textbox('setValue',
				$('#invoice_print_newSearch_validNo').val());
		$('#invoice_req_dlg_cardType').textbox('setValue',
				$('#invoice_print_newSearch_validType').val());
		$('#invoice_req_dlg_customerCode').textbox('setValue',
				$('#invoice_print_newSearch_customerCode').val());
		$('#invoice_req_dlg_customerId').textbox('setValue',
				$('#invoice_print_newSearch_customerId').val());
		$('#invoice_req_dlg_reqStatus').textbox('setValue',
				$('#invoice_print_newSearch_reqStatus').val());
		$('#invoice_req_dlg_bornTime').textbox('setValue',
				$('#invoice_print_newSearch_applyTime').val());
		//$('#reqForm').form('load',$('#newDetailForm'));
		$("#tax_req_dlg").dialog('open').dialog('setTitle',
				'<spring:message code="invoiceprint.addDetails"/>');
	}
	//从选择框页面里面选中数据进入申请单流水列表
	function addNo() {
		var rows = $('#dlgDetial').datagrid('getChecked');
		for (var i = 0; i < rows.length; i++) {
			$("#dgrequestdetail").datagrid('insertRow', {
				index : 0,
				row : rows[i]
			});
		}
		$('#tax_req_dlg').dialog('close');
		$('#tax_req_dlg').dialog('close');
		var ss = [];
		var rows = $('#dgrequestdetail').datagrid('getRows');
		for (var i = 0; i < rows.length; i++) {
			var r = rows[i];
			ss.push(r.trxid);
		}
		var ids = ss.join(",");
		$.ajax({
			url : "invoiceReqL/changeStatus.do?ids=" + ids,
			type : 'POST',
			dataType : "json",
			cache : false,
			success : function(object) {
				/* $.messager.alert('<spring:message code="system.alert"/>',object.msg);
				Search(); */
			}
		});
	}
	/* function editPre(){
		$('#layoutid').layout('expand', 'east');
	} */

	function detail() {
		$('#layoutid').layout('expand', 'east');
	}
	function clearNewSearchForm() {
		$("#layoutid").layout('collapse', 'east');
	}
	function back(){
		$("#layoutid").layout('collapse', 'east');
	}
	function remove() {

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

		/* for(var i =0;i<rows.length;i++){                
			  var index = $('#dgrequestdetail').datagrid('getRowIndex',rows[i]);//获取某行的行号          
			  $('#dgrequestdetail').datagrid('deleteRow',index);	//通过行号移除该行        
		   } */
	}
	function removePre() {
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
				url : "invoiceReqH/removeInvoiceReqHs.do?ids=" + ps,
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
	function clearSearchForm() {
		$('#searchform').form('clear');
		var pageNumber = $('#dg').datagrid('options').pageNumber;
		var pageSize = $('#dg').datagrid('options').pageSize;
		$('#searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
	}
	function clearDetailForm() {
		$('#newDetailForm').form('clear');
		var pageNumber = $('#dgrequestdetail').datagrid('options').pageNumber;
		var pageSize = $('#dgrequestdetail').datagrid('options').pageSize;
		$('#newDetailForm').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
	}
	function clearDlgForm() {
		$('#reqForm').form('clear');
		//$('#dlgDetial').datagrid('loadData',{total:0,rows:[]});
		var pageNumber = $('#dlgDetial').datagrid('options').pageNumber;
		var pageSize = $('#dlgDetial').datagrid('options').pageSize;
		$('#reqForm').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
	}
	/* function clearDialogForm(){
		$('#reqForm').form('clear');
	} */
	function clearFormAndData() {
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
	function commitPaper() { //提交
		//var rows = $('#dgrequestdetail').datagrid('getSelections');
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
						url : 'invoiceReqAll/updateCommitStatus.do',
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
	function save() {
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
	function addPre() {
		clearFormAndData();
		//$('#newsearchbtn').linkbutton('disable');
		$.ajax({
			url : "invoiceReqH/getNewReadyParam.do",
			dataType : "json",
			cache : false,
			success : function(object) {
				//$("#newDetailForm").form('clear');
				//$("#invoice_print_searchOut_validType").combobox({editable:false});
				$('#invoice_print_newSearch_readyNo').addClass("textbox-icon-disabled");
				/* $('#invoice_print_newSearch_bank').addClass("textbox-icon-disabled");
				$('#invoice_print_newSearch_bankNum').addClass("textbox-icon-disabled");
				$('#invoice_print_newSearch_contactName').addClass("textbox-icon-disabled");
				$('#invoice_print_newSearch_address').addClass("textbox-icon-disabled"); */
				$('#invoice_print_newSearch_validNo').attr("readonly", false);
				$('#invoice_print_newSearch_customerCode').attr("readonly",
						false);
				$('#invoice_print_newSearch_validNo').removeClass(
						"textbox-icon-disabled");
				$('#invoice_print_newSearch_customerCode').removeClass(
						"textbox-icon-disabled");
				$("#newDetailForm").form('load', object.invoiceReadyData);
				$('#invoice_print_newSearch_reqStatus').combobox('setValue',
						object.invoiceReadyData.status);
				//$('#detailPanel').hide();
				/* $('#newsearchbtn').show();
				$('#newclearbtn').show(); */
			}
		});
		$('#layoutid').layout('expand', 'east');
		/* $('#newsearchbtn').hide(); */
		/* $('#newclearbtn').hide(); */
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
			url : "invoiceReqH/getCustomerParam.do?custRegistrationNumber="
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
				url : "invoiceReqH/getCustomerParam.do?custRegistrationNumber="
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
	/* function saveEditHead(){
		var ss = [];
		var rowchange = $('#dgrequestdetail').datagrid('getChanges');
	    for(var i=0;i<rowchange.length;i++){
	    	var r = rows[i];				
			ss.push(r.id);
	    }
	    var ids = ss.join(";") ;		
		$('#newDetailForm').form('load', {
			rowsids : ids		
		});	
		//做form提交
	} */

	function saveHead() {
		var ss = [];
		var rows = $('#dgrequestdetail').datagrid('getRows');
		for (var i = 0; i < rows.length; i++) {
			var r = rows[i];
			ss.push(r.trxid);
		}
		var ids = ss.join(";");
		$('#newDetailForm').form('load', {
			rowsids : ids
		});

		$('#newDetailForm').form(
				'submit',
				{
					url : 'invoiceReqHead/saveInvoiceReqHead.do',
					success : function(object) {
						$("#layoutid").layout('collapse', 'east');
						Search();
						var object = $.parseJSON(object);
						/*  $('#invoice_print_newSearch_validNo').attr("readonly", "readonly");
						 $('#invoice_print_newSearch_customerCode').attr("readonly", "readonly");
						 $('#invoice_print_newSearch_validType').combobox("disable");
						 $('#invoice_print_newSearch_level').combobox("disable");*/
						clearNewSearchForm();
						$.messager.alert(
								'<spring:message code="system.alert"/>',
								object.msg);
						// $('#detailPanel').show(); 
						//Search();
					}
				});

	}
	//从申请单查询页面提交申请单
	function submitFromPage() {
		var rows = $('#dg').datagrid('getSelections');
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
				url : "invoiceReqAll/submitFromPage.do?ids=" + ps,
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
					url : 'invoiceReqAll/updateCommitStatus.do',
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
</script>

</html>