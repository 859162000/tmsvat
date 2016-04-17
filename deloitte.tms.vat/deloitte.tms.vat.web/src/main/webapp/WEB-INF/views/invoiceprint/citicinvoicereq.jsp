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
                                <td>客户账号：</td>
								<td><input class="easyui-textbox"
								id="invoice_print_newSearch_custBankAccountNum"
								name="custBankAccountNum"
								style="width: 120px;"></td>										
							</tr>
							<tr>
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
								<td><spring:message code="invoiceprint.validType" />：<font color=red>*</font></td>
								<td>
								<input id="invoice_print_searchOut_validType"
									class="easyui-combobox" name="custRegistrationCode"
									style="width: 120px"  editable="false"
									data-options="url:'invoiceReqH/getTypedictionary.do',
	                                              method:'get',
	                                              valueField:'value',
	                                              textField:'text',
	                                              panelHeight:'auto'
	                                 ">
									</td>
								<td><spring:message code="invoiceprint.validName" />：<font color=red>*</font></td>
								<td><input id="invoice_print_newSearch_validNo"  class="easyui-textbox"  name="custRegistrationNumber" <%-- data-options="required:true" --%> style="width: 120px;"></input></td>																								
								<td><spring:message code="com.vat.base.model.customer.customerCode.text" />：<font color=red>*</font></td>
								<td><input id="invoice_print_newSearch_customerCode"  class="easyui-textbox"  name="customerNumber" <%-- data-options="required:true" --%> style="width: 120px;"></input></td>							
								
	
							</tr>
							<tr>
								<td><spring:message code="client.bank" />：</td>
								<td><input id="invoice_print_newSearch_bank"
									name="bankBranchName" class="easyui-textbox" readonly="true"
									style="width: 120px;"></td>
								<td><spring:message code="client.bankNum" />：</td>
								<td><input id="invoice_print_newSearch_bankNum"
									name="bankNum" class="easyui-textbox" readonly="true"
									style="width: 120px;"></td>
								<td><spring:message code="contacts" />：</td>
								<td><input id="invoice_print_newSearch_contactName"
									name="contactName" class="easyui-textbox" readonly="true"
									style="width: 120px;"></td>
								<td><spring:message code="address" />：</td>
								<td><input id="invoice_print_newSearch_address"
									name="address" class="easyui-textbox" readonly="true"
									style="width: 120px;"></td>
							</tr>
							<tr>						
								<td><spring:message code="invoiceprint.search.buyerCompanyName" />：</td>
								<td><input id="invoice_print_newSearch_buyerCompanyName"
									name="customerName" class="easyui-textbox" 
									style="width: 120px;"></td>
								<td><spring:message code="invoiceprint.amountAll" />：</td>
								<td><input id="invoice_print_newSearch_amountAll"
									name="reqAmount" class="easyui-textbox" 
									style="width: 120px;"></td>
							</tr>
							<tr>
								<td><a href="#" id="saveHeadbtn" class="easyui-linkbutton"
									data-options="iconCls:'icon-large-shapes'" style="width: 90px"
									onclick="saveHead()">保存申请单</a></td>
								<td><a href="#" id="submitHeadbtn"
									class="easyui-linkbutton"
									data-options="iconCls:'icon-large-smartart'"
									style="width: 90px" onclick="submitHead()">提交申请单</a></td>
								<td><a href="#" id="backbtn" class="easyui-linkbutton"
									style="width: 90px" onclick="back()"><spring:message
											code="button.Back" /></a></td> 
							</tr>
							<tr style="display: none">
								<td><input id="newpageNumber" class="easyui-textbox"
									type="text" style="width: 0px;" name="pageNumber" value=""></input></td>
								<td><input id="newpageSize" class="easyui-textbox"
									type="text" style="width: 0px;" name="pageSize" value=""></input></td>
								<td><input id="newformflag" class="easyui-textbox"
									type="text" style="width: 0px;" name="newformflag" value=""></input></td>
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
						  		   <%--  <td><spring:message code="invoiceprint.withoutTrade"/>:</td>
					    			<td>
					    				<select id="tax_req_dlg_owner" name="isExitsCustomer" class="easyui-combobox" style="width:70px">
				    					<option value="" selected>&nbsp;</option>
				    					<option value="1">是</option>
				    					<option value="0" >否</option>
				    				</select>
					    			</td> --%>
					    			<td>无主<spring:message code="invoiceTrxPool.trxNumber" />：</td>
									<td><input id="without_dlg_trxNumber" class="easyui-textbox" style="width:120px;" name="trxNumber"/><!--  required="true" -->
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
									<!-- <td input class="easyui-textbox" id="dlg_customerId" name="customerId" style="width: 0px;"> -->
									<td input id="dlg_hid" name="crvatInvoiceReqHId" class="easyui-textbox" style="height:0px"></td>
									<td input id="dlg_number" name="crvatInvoiceReqNumber" class="easyui-textbox"></td>
									<td input id="invoice_req_dlg_reqStatus" class="easyui-textbox" name="status"></td>
									<td input id="invoice_req_dlg_cardType" class="easyui-textbox" name="custRegistrationCode" style="width:0px"></td>
									<td input id="invoice_req_dlg_cardNO" name="custRegistrationNumber" class="easyui-textbox"></td>
									<td input id="invoice_req_dlg_customerCode" name="customerCode" class="easyui-textbox"></td> 
									<td input class="easyui-textbox" id="invoice_req_dlg_customerId" name="customerId"  style="width: 0px;">
									<td input class="easyui-textbox" id="invoice_req_dlg_createdBy" name="createdBy"  style="width: 0px;">
									<td input class="easyui-textbox" id="dlg_reqInvoiceRange" name="reqInvoiceRange" style="width: 0px;">
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
			<div id="without_dlg" class="easyui-dialog" style="width: 100%; height: 100%;" closed="true" buttons="#dlg-buttons">			   			   			   			 			   
			   <div class="easyui-layout" style="width:100%;height:100%;">
					<div data-options="region:'north'" style="width:100%;height:20%;">
					  <div class="easyui-panel"	style="width: 100%; height: 100%; background-color: #E0ECFF">					   
					    <form id="withoutForm" method="post">
				        	<table>
						  		<tr>						  		
									<td><spring:message code="invoiceTrxPool.trxNumber" />：</td>
									<td><input id="without_dlg_trxNumber" class="easyui-textbox" style="width:120px;" required="true" name="trxNumber"/>								
									<td>
										<a href="#" id="dlawithoutsearchbtn" class="easyui-linkbutton"
										data-options="iconCls:'icon-search'" style="width: 80px"
										onclick="searchNoCustomer()"><spring:message code="button.Search" /></a>
									</td>
									<td><a href="#" id="newwithoutclearbtn" class="easyui-linkbutton"
										style="width: 80px" onclick="clearWithOutForm()"><spring:message code="button.Clear" /></a></td>
								</tr>
								<tr>
									<td><spring:message code="invoiceprint.validType" />：</td>
									<td><input id="without_dlg_validType"
										class="easyui-combobox" name="custRegistrationCode"
										style="width: 120px" editable="false"
										data-options="url:'invoiceReqH/getTypedictionary.do',
		                                              method:'get',
		                                              valueField:'value',
		                                              textField:'text',
		                                              panelHeight:'auto'
		                                 ">
										</td>
									
									<td><spring:message code="invoiceprint.validName" />：</td>
									<td><input id="without_dlg_validNo"
										name="custRegistrationNumber" class="easyui-textbox"
										style="width: 120px;"></td>
									<td><spring:message code="invoiceTrxPool.customerName" />：</td>
									<td><input id="without_dlg_customerName"
										name="customerName" class="easyui-textbox"
										style="width: 120px;"></td>
								</tr>
								<tr>
									<td><spring:message code="client.bank" />：</td>
									<td><select id="without_dlg__bank"
										name="custDepositBankName" class="easyui-textbox" 
										style="width: 120px;"></td>
									<td><spring:message code="client.bankNum" />：</td>
									<td><input id="without_dlg_bankNum"
										name="custDepositBankAccountNum" class="easyui-textbox" 
										style="width: 120px;"></td>
									<td><spring:message code="com.vat.base.model.customer.contactPhone.text" />：</td>
									<td><select id="without_dlg_contactPhone"
										name="contactPhone" class="easyui-textbox" 
										style="width: 120px;"></td>
									<td><spring:message code="address" />：</td>
									<td><input id="without_dlg_address"
										name="custRegistrationAddress" class="easyui-textbox"
										style="width: 120px;"></td>
								</tr>
								<tr style="display: none">
									<%-- <td><input id="dlgpageNumber" class="easyui-textbox"
										type="text" style="width: 0px;" name="pageNumber" value=""></input></td>
									<td><input id="dlgpageSize" class="easyui-textbox" type="text"
										style="width: 0px;" name="pageSize" value=""></input></td> --%>
									<td><input id="withoutdlgId" class="easyui-textbox"
										type="text" style="width: 0px;" name="id" value=""></input></td>	
								</tr>								    
						  	</table>
			           </form>	
			           </div>			
					</div>				
					<div data-options="region:'center'" style="width:100%;height:80%;">
						 <table class="easyui-datagrid" id="dlgwithout" title="<spring:message code="taxItemsmaintain.trxdetailInfo"/>" style="width:100%;height:100%" 
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
					iconCls="icon-ok" onclick="saveWithOut()"><spring:message
						code="button.Save" /></a> <a href="javascript:void(0)"
					class="easyui-linkbutton" iconCls="icon-cancel"
					onclick="javascript:$('#without_dlg').dialog('close')"><spring:message
						code="button.Close" /></a>
			</div>						
		</div>
	</div>
	<div data-options="region:'center',iconCls:'icon-ok'"
		title="<spring:message code="sourcesubject.quicksearch"/>">
		<div class="easyui-layout" style="width: 100%; height: 100%;">
			<div region="north" split="true" border="false"
				style="overflow: hidden; height: 25%;">
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
									name="customerId" /></input></td>			
								
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
									<td align="left"><spring:message code="invoiceprint.readyStatus" />:</td>
									<td align="left"><input class="easyui-combobox" name="status"
										style="width: 120px" editable="false"
										data-options="url:'invoiceReqH/getdictionary.do',
	                                           method:'get',
	                                           valueField:'value',
	                                           textField:'text',
	                                           panelHeight:'auto' " />                                
								     </td>
								     <td><spring:message code="invoiceprint.readyNo" />：</td>
									 <td><input class="easyui-textbox"
										id="Search_readyNo"
										name="crvatInvoiceReqNumber"
										style="width: 120px;"></td>  
								   
				    			
							</tr>
							<tr>
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
	$(function(){
		init_common_combo_customer("#invoice_print_newSearch_Code");
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
										width : 130,
										align : 'center'
									},
									{
										field : 'orgName',
										title : '<spring:message code="invoiceprint.dept"/>',
										width : 180,
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
										title : '<spring:message code="invoiceTrxPool.customerCode"/>',
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
										width : 150,
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
										align : 'right',
										formatter: function(value,row,index){
											return fmoney(value,2);
										 }
									},
									{
										field : 'acctdAmountCr',
										title : '<spring:message code="invoiceprint.amount"/>',
										width : 100,
										align : 'right',
										formatter: function(value,row,index){
											return fmoney(value,2);
										 }
									}, ] ],
							toolbar : [ {
								text : '添加申请单',
								iconCls : 'icon-add',
								handler : function() {
									addReq();
								}
							}, '-', {
								text : '删除申请单',
								iconCls : 'icon-remove',
								handler : function() {
									removeReq();

								}
							},'-', {								
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
							}, '-' , {
								text : '查看申请单',
								iconCls : 'icon-search',
								handler : function() {
									lookDetail(); //申请单查询页面提交申请单
								}
							}, '-'],
							onLoadSuccess : function() {
								$('#dg').datagrid('clearSelections')
							},
							onClickRow : function(index, data) {
								var row = $('#dg').datagrid('getSelected');
								if (row) {
									//loadSaveFormData(row);
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
										field : 'inventoryItemNumber',
										title : '<spring:message code="TmsMdInventoryItems.inventoryItemNumber"/>',
										width : 100,
										align : 'center'
									},
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
		$('#dlgwithout')
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
								field : 'inventoryItemNumber',
								title : '<spring:message code="TmsMdInventoryItems.inventoryItemNumber"/>',
								width : 100,
								align : 'center'
							},
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
						$('#dlgwithout').datagrid('clearSelections')
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
										width : 150,
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
										field : 'inventoryItemNumber',
										title : '<spring:message code="TmsMdInventoryItems.inventoryItemNumber"/>',
										width : 120,
										align : 'center'
									},
									{
										field : 'inventoryItemDescripton',
										title : '<spring:message code="goodsName"/>',
										width : 150,
										align : 'center'
									},
									{
										field : 'invoiceAmount',
										title : '<spring:message code="invoiceprint.amountAll"/>',
										width : 100,
										align : 'center'
									},									
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
								id : 'dgrequestdetailaddbtn',
								text : '添加交易明细',
								iconCls : 'icon-add',
								handler : function() {
									addDetails();
								}
							},'-',{
								id : 'dgrequestdetaildelbtn',
								text : '删除交易明细',								
								iconCls : 'icon-remove',
								handler : function() {
									remove(); //删除

								}
							},'-'/* , {
								text : '选择无主交易',
								iconCls : 'icon-add',
								handler : function() {
									addWithOut(); //新增无主交易

								}
							},'-' */],
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
								findDetail(pageNumber, pageSize);
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

		 $("input",$("#invoice_print_newSearch_validNo").next("span")).blur(function(){
		    
			getCustomerInfoByCode();
		  });
		 $("input",$("#invoice_print_newSearch_customerCode").next("span")).blur(function(){				
				getCustomerInfoByNumber();
		 }); 
		 

		 $('#invoice_print_newSearch_validNo').textbox('textbox').keydown(function (e) {
		        if (e.keyCode == 13) {
		             getCustomerInfoByCode();
		        }

		   });
		 $('#invoice_print_newSearch_customerCode').textbox('textbox').keydown(function (e) {
             if (e.keyCode == 13) {
            	 getCustomerInfoByNumber();
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
	function addWithOut(){
		$("#without_dlg").dialog('open').dialog('setTitle',
		'<spring:message code="invoiceprint.addDetails"/>');
		$('#dlgwithout').datagrid('loadData', {
					total : 0,
					rows : []
			 });
		$('#withoutForm').form('clear');
	}
	//提交无主交易
	function saveWithOut(){
		$('#withoutForm').form('submit', {
			url : 'citicInvoiceReq/saveCustomerAndReq.do',
			success : function(result) {
				
			}
		});
	}
	//搜索无主交易
	function searchNoCustomer(){
		var number=$('#without_dlg_trxNumber').val();
		if(number){
			$("#dlgwithout").datagrid("loading");
			$('#withoutForm').form('submit', {
				url : 'citicInvoiceReq/withOutCustomerTransactionList.do',
				success : function(result) {
					var object = $.parseJSON(result);
					$("#dlgwithout").datagrid('loadData', object);
					$("#dlgwithout").datagrid("loaded");
					//$("input[name='chk_list']").attr("checked",true);  
					$('#dlgwithout').datagrid('selectAll');
					$('#withoutdlgId').textbox('setValue',object.lid);
					//$('#rowsid').textbox('setValue',result.allIds);
				}
			});
		}else{
			$.messager.alert(
					'<spring:message code="invoiceprint.reqinfo"/>',
					'<spring:message code="invoiceprint.need"/>');
			return;
		}
		
	}
	//搜索dlg框
	function searchWithOut() {
		$("#dlgDetial").datagrid("loading");
		$('#reqForm').form('submit', {
			url : 'citicInvoiceReq/transactionlist.do',
			success : function(result) {
				var result = $.parseJSON(result);
				if(result.success){
					$("#dlgDetial").datagrid('loadData', result);
					//$("input[name='chk_list']").attr("checked",true);  
					$('#dlgDetial').datagrid('selectAll');
					$('#rowsid').textbox('setValue',result.allIds);
				}else{
					$.messager.alert('<spring:message code="system.alert"/>',result.errorMessage,'info');
				}
				$("#dlgDetial").datagrid("loaded");
			}
		});
	}
	function searchDetails() {
		$("#dgrequestdetail").datagrid("loading");
		$('#newDetailForm').form('submit', {
			url : 'citicInvoiceReq/transactionlist.do',
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
			url : 'citicInvoiceReq/getInvoiceReqAll.do',
			success : function(result) {
				var result = $.parseJSON(result);
				if(result.success){
					$("#dg").datagrid('loadData', result);
					
				}else{
					$.messager.alert('提示信息',result.errorMessage,'info');
				}
				$("#dg").datagrid("loaded");
			}
		});

	}
	function findDetail(pageNumber, pageSize){
		var id = $('#detail_Hid').val();
		var flag = $('#newformflag').val();		
		if(flag=='edit'){
			$('#dgrequestdetail').datagrid('loading');
			$.post('${vat}/citicInvoiceReq/getEditInfo.do',
					{
						crvatInvoiceReqHId : id,				
						pageNumber : pageNumber,
						pageSize : pageSize
					}, function(result) {					
						$("#dgrequestdetail").datagrid('loadData', result);																	
						$('#dgrequestdetail').datagrid('loaded');
					}, 'json');
		}
		if(flag=='add'){
			$('#dgrequestdetail').datagrid('loading');
			$.post('${vat}/citicInvoiceReq/gettempEditInfo.do',
					{
						crvatInvoiceReqHId : id,				
						pageNumber : pageNumber,
						pageSize : pageSize
					}, function(result) {					
						$("#dgrequestdetail").datagrid('loadData', result);																	
						$('#dgrequestdetail').datagrid('loaded');
					}, 'json');
		}
		
	}
	function editDetail(pageNumber,pageSize) {
		//var rows = $('#dg').datagrid('getChRecked');
		$('#dgrequestdetail').datagrid('loadData', {
				total : 0,
				rows : []
		});
		var rows = $('#dg').datagrid('getSelections');		
		if (rows.length == 1) {
			var row = $('#dg').datagrid('getSelected');
			if ("10" == row.reqStatus) {
				var crvatInvoiceReqNumber = row.crvatInvoiceReqNumber;				
				$('#layoutid').layout('expand', 'east');							
				enableOrdisable(true);					
				$('#newDetailForm').form('load', {
					id : row.id,
					crvatInvoiceReqNumber : row.crvatInvoiceReqNumber,
					invoiceReqDate : row.invoiceReqDate,
					status : row.status,
					reqInvoiceRange : row.reqInvoiceRange,
					customerName : row.customerName,
					bankBranchName : row.bankBranchName,
					bankNum : row.bankNum,
					contactName : row.contactName,
					address : row.address,
					custRegistrationCode : row.custRegistrationCode,
					custRegistrationNumber : row.custRegistrationNumber,
					customerNumber : row.customerNumber,
					reqAmount : row.reqAmount,
					newformflag : 'edit'				
					/* pageNumber : 1,
					pageSize : 10 */
				});  
				var flag = $('#newformflag').textbox('getValue');	
				$('#newclearbtn').hide();
				$('#saveHeadbtn').hide();
				$('#submitHeadbtn').linkbutton('enable');
				$('#dgrequestdetailaddbtn').linkbutton('enable');
				$('#dgrequestdetaildelbtn').linkbutton('enable');
				$('#dgrequestdetail').datagrid('loading');
				var id = row.id;
				$.post('citicInvoiceReq/getEditInfo.do',
						{
							crvatInvoiceReqHId : id,
							crvatInvoiceReqNumber : crvatInvoiceReqNumber,
							pageNumber : $('#dgrequestdetail').datagrid(
									'options').pageNumber,
							pageSize : $('#dgrequestdetail')
									.datagrid('options').pageSize
						}, function(result) {							
							$("#dgrequestdetail").datagrid('loadData', result);																																		
							$('#dgrequestdetail').datagrid('loaded');
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
	
	function lookDetail(){
		//var rows = $('#dg').datagrid('getChRecked');
		$('#dgrequestdetail').datagrid('loadData', {
				total : 0,
				rows : []
		});
		var rows = $('#dg').datagrid('getSelections');
		if (rows.length == 1) {
			var row = $('#dg').datagrid('getSelected');			
			var crvatInvoiceReqNumber = row.crvatInvoiceReqNumber;				
			$('#layoutid').layout('expand', 'east');							
			enableOrdisable(true);	
			$('#dgrequestdetailaddbtn').linkbutton('disable');
			$('#dgrequestdetaildelbtn').linkbutton('disable');
			$('#saveHeadbtn').show();
			$('#saveHeadbtn').linkbutton('disable');
			$('#submitHeadbtn').linkbutton('disable');
			
			$('#newDetailForm').form('load', {
					id : row.id,
					crvatInvoiceReqNumber : row.crvatInvoiceReqNumber,
					invoiceReqDate : row.invoiceReqDate,
					status : row.status,
					reqInvoiceRange : row.reqInvoiceRange,
					customerName : row.customerName,
					bankBranchName : row.bankBranchName,
					bankNum : row.bankNum,
					contactName : row.contactName,
					address : row.address,
					custRegistrationCode : row.custRegistrationCode,
					custRegistrationNumber : row.custRegistrationNumber,
					customerNumber : row.customerNumber,
					reqAmount : row.reqAmount,
					newformflag : 'edit',	
					pageNumber : 1,
					pageSize : 10
			});  
				
			$('#newclearbtn').hide();				
			var id = row.id;
			$('#dgrequestdetail').datagrid('loading');
			$.post('citicInvoiceReq/getEditInfo.do',
				{
							crvatInvoiceReqHId : id,
							crvatInvoiceReqNumber : crvatInvoiceReqNumber,
							pageNumber : $('#dgrequestdetail').datagrid(
									'options').pageNumber,
							pageSize : $('#dgrequestdetail')
									.datagrid('options').pageSize
				}, function(result) {
							
							$("#dgrequestdetail").datagrid('loadData', result);														
							$('#dgrequestdetail').datagrid('loaded');
							
						
						}, 'json');
			
		} else {
			$.messager.confirm('<spring:message code="invoiceprint.reqinfo"/>',
					'<spring:message code="invoiceprint.error"/>');
		}
	}

	function addDetails() {
		var customerNumber=$('#invoice_print_newSearch_validNo').val();
		/* if(customerNumber){ */
			$('#dlgDetial').datagrid('loadData', {
				total : 0,
				rows : []
			});
			$("#dlg_hid").textbox('setValue',$('#detail_Hid').val());			
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
			
			$('#dlg_reqInvoiceRange').textbox('setValue',$('#invoice_print_newSearch_level').combobox('getValue'));
			//$('#reqForm').form('load',$('#newDetailForm'));
			$("#tax_req_dlg").dialog('open').dialog('setTitle',
					'<spring:message code="invoiceprint.addDetails"/>');	
		/* }else{
			$.messager.alert('<spring:message code="invoiceprint.reqinfo"/>','<spring:message code="invoiceprint.need"/>' );
		} */
	}
	//从选择框页面里面选中数据进入申请单流水列表
	function addNo() {
		var flag = $('#newformflag').textbox('getValue');
		var rows = $('#dlgDetial').datagrid("getRows");		
		if(rows.length>0){
			if(flag=='add'){
				$("#dlgDetial").datagrid("loading");			
				$('#reqForm').form('submit', {
					url : 'citicInvoiceReq/addtransactionlisttotemp.do',
					success : function(result) {
						var result = $.parseJSON(result);				
						if(result.success){
							$('#dgrequestdetail').datagrid('loading');
							var id = $('#detail_Hid').val();
							
							$.post('citicInvoiceReq/gettempEditInfo.do',
									{
										crvatInvoiceReqHId : id,									
										pageNumber : $('#dgrequestdetail').datagrid(
												'options').pageNumber,
										pageSize : $('#dgrequestdetail')
												.datagrid('options').pageSize
									}, function(result) {
										//var result = $.parseJSON(result);
										$("#dgrequestdetail").datagrid('loadData', result);							
										$('#saveHeadbtn').linkbutton('enable');
										$('#submitHeadbtn').linkbutton('enable');															
										$('#dgrequestdetail').datagrid('loaded');
									}, 'json');
						}
						$("#dlgDetial").datagrid("loaded");
					}
				});
			}
			if(flag=='edit'){
				$("#dlgDetial").datagrid("loading");
				$('#reqForm').form('submit', {
					url : 'citicInvoiceReq/addtransactionlisttoreh.do',
					success : function(result) {
						var result = $.parseJSON(result);	
						if(result.success){
							$('#dgrequestdetail').datagrid('loading');
							var id = $('#detail_Hid').val();						
							$.post('citicInvoiceReq/getEditInfo.do',
									{
										crvatInvoiceReqHId : id,								
										pageNumber : $('#dgrequestdetail').datagrid(
												'options').pageNumber,
										pageSize : $('#dgrequestdetail')
												.datagrid('options').pageSize
									}, function(result) {									
										$("#dgrequestdetail").datagrid('loadData', result);																																		
										$('#dgrequestdetail').datagrid('loaded');
									}, 'json');
						}
						$("#dlgDetial").datagrid("loaded");
					}
				});
			}
			$("#tax_req_dlg").dialog('close');	
		}else{
			$("#tax_req_dlg").dialog('close');	
		}
			
		
		
		
	}	
		



	function detail() {
		$('#layoutid').layout('expand', 'east');
	}
	function clearNewSearchForm() {
		$("#layoutid").layout('collapse', 'east');
	}
	function back(){
		$("#layoutid").layout('collapse', 'east');
		$.post('citicInvoiceReq/deletetempbyusername.do',
				 function(result) {																			
				}, 'json');
	}
	function remove() {
		$.messager.confirm('<spring:message code="system.alert"/>','确定删除该交易流水？',function(result){  
			  if (result){
				 var rows = $('#dgrequestdetail').datagrid('getSelections');				
				 var ps = "";
					$.each(rows, function(i, n) {
						if (i == 0)
							ps += n.id;
						else
							ps += "," + n.id;
					});
				 var id = $('#detail_Hid').val();
				 var flag = $('#newformflag').textbox('getValue');
					if(flag=='add'){
						$("#dgrequestdetail").datagrid("loading");											
						$.post('citicInvoiceReq/deletetempreql.do',
								{
							        crvatInvoiceReqHId : id,
									tempids : ps,
									pageNumber : $('#dgrequestdetail').datagrid(
											'options').pageNumber,
									pageSize : $('#dgrequestdetail')
											.datagrid('options').pageSize
								}, function(result) {								
									$("#dgrequestdetail").datagrid('loadData', result);	
									$("#dgrequestdetail").datagrid("loaded");
									$('#saveHeadbtn').linkbutton('enable');								
								}, 'json');
						
					}
					if(flag=='edit'){
						$("#dgrequestdetail").datagrid("loading");					
						var id = $('#detail_Hid').val();
						$.post('citicInvoiceReq/deletereql.do',
								{
							        crvatInvoiceReqHId : id,
									reqlids : ps,
									pageNumber : $('#dgrequestdetail').datagrid(
											'options').pageNumber,
									pageSize : $('#dgrequestdetail')
											.datagrid('options').pageSize
								}, function(result) {								
									$("#dgrequestdetail").datagrid('loadData', result);																												
									$('#dgrequestdetail').datagrid('loaded');
								}, 'json');
					}						
			  }
		});
		
	}
	function removeReq() {
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
			$.messager.confirm('<spring:message code="system.alert"/>','确定删除该申请单？',function(result){  
			  if (result){
					var ps = "";
					$.each(rows, function(i, n) {
						if (i == 0)
							ps += n.id;
						else
							ps += "," + n.id;
					});
					$('#dg').datagrid('loading');
					 $.ajax({
						url : "citicInvoiceReq/removeInvoiceReqHs.do?ids=" + ps,
						type : 'POST',
						dataType : "json",
						cache : false,
						success : function(object) {
							$.messager.alert('<spring:message code="system.alert"/>',
									object.msg);
							$('#dg').datagrid('loaded');
							Search();
						}
					});
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
	//无主交易搜索框清除
	function clearWithOutForm(){
		$('#withoutForm').form('clear');
	}
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
						url : 'citicInvoiceReq/updateCommitStatus.do',
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
	function addReq() {
		clearFormAndData();
		$('#newDetailForm').form('load', {
			newformflag : 'add'
		});
		enableOrdisable(true);
		$('#invoice_print_newSearch_level').combobox("enable");
		$("#invoice_print_searchOut_validType").combobox("enable");
		$("#invoice_print_newSearch_validNo").textbox({ disabled: false });
		$("#invoice_print_newSearch_customerCode").textbox({ disabled: false });
		  $("input",$("#invoice_print_newSearch_validNo").next("span")).blur(function(){
		    
			getCustomerInfoByCode();
		  });
		 $("input",$("#invoice_print_newSearch_customerCode").next("span")).blur(function(){				
				getCustomerInfoByNumber();
		 }); 
		 

		 $('#invoice_print_newSearch_validNo').textbox('textbox').keydown(function (e) {
		        if (e.keyCode == 13) {
		             getCustomerInfoByCode();
		        }

		   });
		 $('#invoice_print_newSearch_customerCode').textbox('textbox').keydown(function (e) {
             if (e.keyCode == 13) {
            	 getCustomerInfoByNumber();
             }

          }); 
		//$('#newsearchbtn').linkbutton('disable');
		$('#saveHeadbtn').show();
		$('#dgrequestdetailaddbtn').linkbutton('enable');
		$('#dgrequestdetaildelbtn').linkbutton('enable');
		$.ajax({
			url : "citicInvoiceReq/getNewReadyParam.do",
			dataType : "json",
			cache : false,
			success : function(object) {				
				$("#newDetailForm").form('load', object.invoiceReadyData);				
				$('#invoice_print_newSearch_reqStatus').combobox('setValue',
						object.invoiceReadyData.status);
				var rows = $('#dgrequestdetail').datagrid('getRows');				
				$('#invoice_print_newSearch_level').combobox('setValue','2');
				
				if('0' == rows.length){
					$('#saveHeadbtn').linkbutton('disable');
					$('#submitHeadbtn').linkbutton('disable');
				}				
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
			url : "citicInvoiceReq/getCustomerParam.do?custRegistrationNumber="
					+ validNo + "&customerNumber=" + number+"&custRegistrationCode="+code,
			dataType : "json",
			cache : false,
			success : function(object) {
				if(object.success){
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
					/* $('#invoice_print_newSearch_customerCode').textbox(
							'setValue', object.customer.customerNumber); */
					$('#invoice_print_newSearch_validNo').textbox('setValue',
							object.customer.custRegistrationNumber);
					//$('#invoice_print_newSearch_customerCode').val(object.customer.customerNumber);
					$('#invoice_print_newSearch_validNo').val(object.customer.custRegistrationNumber); 
					$('#invoice_print_searchOut_validType').combobox('setValue',object.customer.custRegistrationCode);
					if (object.customer.id) {
						$('#newsearchbtn').linkbutton('enable');
					}
				}else{
					$('#invoice_print_newSearch_bank').textbox('clear');
					$('#invoice_print_newSearch_bankNum').textbox('clear');
					$('#invoice_print_newSearch_contactName').textbox('clear');
					$('#invoice_print_newSearch_address').textbox('clear');
					$('#invoice_print_newSearch_buyerCompanyName').textbox('clear');
					$('#invoice_print_newSearch_validNo').textbox('clear'); 
					$('#invoice_print_searchOut_validType').combobox('clear');
					$.messager.alert('<spring:message code="invoiceprint.reqinfo"/>',
					'系统内没有相关客户');
				}
				
			}
		});
	}
	//通过购方证件号码和证件类型带出
	function getCustomerInfoByCode() {
		var validNo = $('#invoice_print_newSearch_validNo').val();
		//var number = $('#invoice_print_newSearch_customerCode').val();
		
		var number = $('#invoice_print_newSearch_customerCode').textbox('getValue');
		var code=$('#invoice_print_searchOut_validType').combobox('getValue');
		var as = true;
		if(validNo){
			number="";
		}
		if(validNo){
			$.ajax({
				url : "citicInvoiceReq/getCustomerParam.do?custRegistrationNumber="
						+ validNo + "&customerNumber=" + number+"&custRegistrationCode="+code,
				dataType : "json",
				cache : false,
				success : function(object) {
					if(object.success){
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
						$('#invoice_print_newSearch_customerCode').textbox(
								'setValue', object.customer.customerNumber);
						/* $('#invoice_print_newSearch_validNo').textbox('setValue',
								object.customer.custRegistrationNumber); */
						
					 	$('#invoice_print_newSearch_customerCode').val(object.customer.customerNumber);
						/* $('#invoice_print_newSearch_validNo').val(object.customer.custRegistrationNumber); */ 
						$('#invoice_print_searchOut_validType').combobox('setValue',object.customer.custRegistrationCode);
						//$('invoice_print_newSearch_customerCode').textbox('required','false');//将另外一个条件变为非必输项
						/* if (object.customer.id) {
							$('#newsearchbtn').linkbutton('enable');
						} */
					}else{
						$('#invoice_print_newSearch_bank').textbox('clear');
						$('#invoice_print_newSearch_bankNum').textbox('clear');
						$('#invoice_print_newSearch_contactName').textbox('clear');
						$('#invoice_print_newSearch_address').textbox('clear');
						$('#invoice_print_newSearch_buyerCompanyName').textbox('clear');
						$('#invoice_print_newSearch_customerCode').textbox('clear');
						$.messager.alert('<spring:message code="invoiceprint.reqinfo"/>',
						'系统内没有相关客户');
					}
				}
			});
		}
	}
	function saveHead() {		
 		$("#dgrequestdetail").datagrid("loading");
 		//forbiddenPage();
		$('#newDetailForm').form(
				'submit',
				{
					url : 'citicInvoiceReq/saveInvoiceReqHead.do',
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
						releasePage();
						("#dgrequestdetail").datagrid("loaded");
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
			if(!r.havereql){
				isCommit=false;
				$.messager.alert('<spring:message code="invoiceprint.reqinfo"/>','请选择有明细的申请单提交！' );
				return;
			}
		}
		if(rows.length=='0'){
			$.messager.alert('<spring:message code="invoiceprint.reqinfo"/>','<spring:message code="invoiceprint.error"/>' );
			return;
		} 
			var ps = "";
			$.each(rows, function(i, n) {
				if (i == 0)
					ps += n.id;
				else
					ps += "," + n.id;
			});
			$('#dg').datagrid('loading');
			 $.ajax({
				url : "citicInvoiceReq/submitFromPage.do?ids=" + ps,
				dataType : "json",
				type : "POST",
				cache : false,
				success : function(object) {
					if (object.success) {
						$('#dg').datagrid('loaded');
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
		if(rows.length==0){
			isCommit=false;
			$.messager.alert('<spring:message code="invoiceprint.reqinfo"/>','请添加明细！');
			return;
		}
		/* for (var i = 0; i < rows.length; i++) {
			var r = rows[i];
			ss.push(r.trxid);
		}
		//var ids = ss.join(";");
		var ids=$('#rowsid').textbox('getValue');
		$('#newDetailForm').form('load', {
			ids : ids
		});	 */
		$('#dgrequestdetail').datagrid('loading');
		$('#newDetailForm').form(
				'submit',
				{
					url : 'citicInvoiceReq/updateCommitStatus.do',
					success : function(object) {
						var object = $.parseJSON(object);
						//clearNewSearchForm();
						$.messager.alert(
								'<spring:message code="system.alert"/>',
								object.msg);
						$('#dgrequestdetail').datagrid('loaded');
						$("#layoutid").layout('collapse', 'east');
						Search();
					}
				});		
	}
	
	function enableOrdisable(enable){
		$("#invoice_print_newSearch_readyNo").textbox({ disabled: enable }); 
		$("#invoice_print_newSearch_applyTime").datebox({ disabled: enable }); 				
		$("#invoice_print_newSearch_reqStatus").combobox({ disabled: enable });
		$("#invoice_print_newSearch_level").combobox({ disabled: enable });
		$("#invoice_print_newSearch_buyerCompanyName").textbox({ disabled: enable }); 
		$("#invoice_print_newSearch_bank").textbox({ disabled: enable }); 
		$("#invoice_print_newSearch_bankNum").textbox({ disabled: enable });
		$("#invoice_print_newSearch_contactName").textbox({ disabled: enable });
		$("#invoice_print_newSearch_address").textbox({ disabled: enable });
		$("#invoice_print_searchOut_validType").combobox({ disabled: enable });
		$("#invoice_print_newSearch_validNo").textbox({ disabled: enable });
		$("#invoice_print_newSearch_customerCode").textbox({ disabled: enable });
		$("#invoice_print_newSearch_amountAll").textbox({ disabled: enable });
	}
</script>

</html>