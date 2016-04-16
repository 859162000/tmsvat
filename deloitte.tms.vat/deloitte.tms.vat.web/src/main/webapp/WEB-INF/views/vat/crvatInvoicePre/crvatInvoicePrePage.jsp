<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
 <%@taglib uri="/tags/input" prefix="input"%> 
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
		title="<spring:message code="crvatInvoicePre.crvatInvoicePrePage"/>"
		style="width: 100%;">
		<div region="north" split="true" border="false"
				style="overflow: hidden; height: 30%;">
				<div class="easyui-panel"
						style="width: 100%; height: 100%; background-color: #E0ECFF">
						<form id="prehform" method="get">
							<table style="width: 1000px; height: 100%" >
								<tr>								
								    <td><spring:message code="invoiceprint.readyNo" />:</td>
									<td><input id="clientinfo" class="easyui-textbox" disabled="true"
										type="text" style="width: 120px;" name="invoiceReqNum"></input></td>							   
								    
								    <td>开票申请机构:</td>
								    <td><input id="clientinfo" class="easyui-textbox" disabled="true"
										type="text" style="width: 120px;" name="operationOrgCode"></input></td>
										
								    <td>申请日期:</td>
									<td><input id="clientinfo" class="easyui-textbox"
										type="text" style="width: 120px;" name="submitdate" disabled="true"></input></td>
								    
								    <td>受理日期:</td>
									<td><input id="clientinfo" class="easyui-textbox"
										type="text" style="width: 120px;" name="createdate" disabled="true"></input></td>
								</tr>
								
								<tr>
								    <td><spring:message code="crvatInvoicePre.status" />:</td>
									<td><input id="prehformstatus" class="easyui-combobox" name="" style="width: 120px;" disabled="true"
										data-options="
														url:'crvatInvoicePre/getstatus.do',
														method:'get',
														valueField:'value',
														textField:'text',
														panelHeight:'auto'
												">
								    </td>
								    
								    <td><spring:message code="invoiceprint.validType"/>:</td>
									<td>
									   <div>
						    				<input id="clientinfo" class="easyui-textbox" type="text" style="width: 120px;" name="buyidentitytype" disabled="true"></input>	    			
						    			    <input id="invoice_print_newSearch_name" class="easyui-textbox" style="width:120px" name="buyidentitynum" disabled="true"/></input>
					    			   </div>
					    			</td>
					    			
					    			<td>购方客户：</td>
									<td><input id="invoiceTrxPool_searchform_customerName_input"
										class="easyui-textbox" style="width: 120px" name="customerId" disabled="true"/>
									</td>
									
									<td>购方单位名称:</td>
									<td><input id="clientinfo" class="easyui-textbox"
										type="text" style="width: 120px;" name="customerName" disabled="true"></input></td>
								</tr>
								
								<tr>
								    <td><spring:message code="client.bank" />：</td>
									<td><select id="invoice_print_newSearch_bank" name="bankname" class="easyui-textbox" readonly="true" style="width: 120px;" disabled="true"></td>
									<td><spring:message code="client.bankNum" />：</td>
									<td><input id="invoice_print_newSearch_bankNum" name="bankAccount" class="easyui-textbox" readonly="true" style="width: 120px;" disabled="true"></td>
									
									<td>联系电话：</td>
									<td><input id="invoice_print_newSearch_bankNum" name="bankAccount" class="easyui-textbox" readonly="true" style="width: 120px;" disabled="true"></td>
								
								    <td><spring:message code="address" />：</td>
								    <td><input id="invoice_print_newSearch_address" name="address" class="easyui-textbox" readonly="true" style="width: 120px;" disabled="true"></td>
								</tr>	
														
								<tr>
								    <td><spring:message code="crvatInvoicePre.totalamount" />:</td>
									<td><input id="time" class="easyui-textbox"style="width: 120px;"
										name="totalamount" disabled="true"/></input></td>
								</tr>
								
								<tr style="display:none">
								   <td><input id="preHid" class="easyui-textbox" type="text" style="width:0px;" name="preHid" value=""></input></td>
					    		   <td><input id="pageNumber" class="easyui-textbox" type="text" style="width:0px;" name="pageNumber" value=""></input></td>
					    		   <td><input id="pageSize" class="easyui-textbox" type="text" style="width:0px;" name="pageSize" value=""></input></td>
					    		</tr>
					    		<tr>								
								   <td>						    		  
						    	        <a href="#" id="acceptbtn"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px" onclick="Accept()"><spring:message code="button.Accept"/></a>
						    	   </td>    						    	         			                      
						           <td> 
						                 <a href="#" id="revertbtn" class="easyui-linkbutton" style="width:80px" onclick="Revert()"><spring:message code="button.Revert"/></a>  
						           </td>
								   <td> 
						                 <a href="#" id="backbtn" class="easyui-linkbutton" style="width:80px" onclick="Back()"><spring:message code="button.Back"/></a>   			   
						    	   </td>
								</tr>	
							</table>
						</form>
					</div>

			</div>
		<div class="easyui-panel"
			style="width: 100%; height: 70%; padding: 0px;">
			<div style="width: 100%; height: 100%">
				<table class="easyui-datagrid" id="dgrequestdetail" title="<spring:message code="crvatInvoicePre。detail"/>"
					 style="width: 100%; height: 100%"dgrequestdetail
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

	<div data-options="region:'center',iconCls:'icon-ok'"
		title="<spring:message code="sourcesubject.quicksearch"/>">
		<div class="easyui-layout" style="width: 100%; height: 100%;">
			<div region="north" split="true" border="false"
				style="overflow: hidden; height: 30%;">
				<div class="easyui-panel"
					title="<spring:message code="crvatInvoicePre.searchgroup"/>"
					style="width: 100%; height: 100%; background-color: #E0ECFF">
					<form id="searchform" method="post">
					
						<table style="width: 1000px; height: 100%">
							<tr>						    
								<td>购方证件号码：</td>																									
								 <td>
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
				    			
				    			<td>购方客户：</td>
								<td><input id="invoiceTrxPool_searchform_customerName_input2"
									class="easyui-textbox" style="width: 120px" name="customerId" />
								</td>
								
				    			<td><spring:message code="invoiceprint.readyNo" />：</td>
							    <td><input class="easyui-textbox" id="invoice_print_newSearch_readyNo" name="crvatInvoiceReqNumber"  style="width: 120px;"></td>
				    			<td><spring:message code="crvatInvoicePre.prestatus"/>：</td>				    		
								<td><input class="easyui-combobox" name="approveStatus" style="width: 120px;"
										data-options="
														url:'crvatInvoicePre/getstatus.do',
														method:'get',
														valueField:'value',
														textField:'text',
														panelHeight:'auto'
												">
								</td>
				    			
				    			
				    		</tr>
				    		<tr>
				    		    <td><spring:message code="invoiceprint.search.applyTime"/>：</td>
				    			<td>
				    			   <div>
				    				 <input id="applytime" class="easyui-datebox" style="width:110px;" name="applyDatefrom" /></input>
				    			
				    			      <spring:message code="invoiceprint.search.to"/>：
				    			
				    				 <input id="applyto" class="easyui-datebox" style="width:110px;" name="applyDateto" /></input>
				    			   </div>
				    			</td>
				    			<td>开票申请机构：</td>
								<td><input class="easyui-combobox" name="reqid" style="width:120px;"
										data-options="
														url:'crvatInvoicePre/getOrgId.do',
														method:'get',
														valueField:'value',
														textField:'text',
														panelHeight:'300px'
												">
								</td>
								<td>开票申请受理机构：</td>
								<td><input class="easyui-combobox" name="acceptid" style="width:120px;"
										data-options="
														url:'crvatInvoicePre/getOrgId.do',
														method:'get',
														valueField:'value',
														textField:'text',
														panelHeight:'300px'
												">
								</td>
				    			
				    			
	    					</tr>
	    					
	    					
							<tr>
								<td>
								 <a href="#" id="searchbtn" class="easyui-linkbutton"
									data-options="iconCls:'icon-search'" style="width: 80px"
									onclick="Search()"><spring:message code="button.Search" /></a>
								</td>
								<td><a href="#" class="easyui-linkbutton"
									style="width: 80px" onclick="clearSearchForm()"><spring:message code="button.Clear" /></a></td>
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
						title="<spring:message code="crvatInvoicePre。searchResult"/>"
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
	$("#layoutid").layout('collapse', 'east');
	$('#dg').datagrid({
		url: '',			
		fitColumns: true,			
		nowrap: false,
		pagination:true,
		rownumbers:true,
		singleSelect:true,
		fitColumns: false,
		striped: true,
		idField:'id', //主键字段  
		columns:[[
		    
		    { field:'crvatInvoicePreNumber', title:'<spring:message code="crvatInvoicePre.cratInvoiceNum"/>',width:140,align:'center'},
		    { field:'invoiceReqNum', title:'<spring:message code="invoiceprint.readyNo"/>',width:140,align:'center'},
		    { field:'astatus', title:'准备单状态',width:100,align:'center'},
		    { field:'requestOrg', title:'开票申请机构',width:180,align:'left'},
		    { field:'customerNumber', title:'购方客户编号',width:140,align:'left'}, 
		    { field:'customerName', title:'购方单位名称',width:100,align:'center'},
			{ field:'custRegistrationCodeStr', title:'<spring:message code="invoiceprint.validType"/>',width:100,align:'center'},
			{ field:'custRegistrationNumber', title:'<spring:message code="invoiceprint.validName"/>',width:150,align:'center'},
			{ field:'requestDate', title:'<spring:message code="invoiceprint.search.applyTime"/>',width:100,align:'center'},
			{ field:'requestPerson', title:'<spring:message code="invoiceprint.applicant"/>',width:100,align:'center'},			
			{ field:'approvalBy', title:'受理人',width:100,align:'center'},
			{ field:'approveDate', title:'受理日期',width:100,align:'center'},			
			{ field:'invoiceAmount', title:'<spring:message code="invoiceprint.amountAll"/>',width:100,align:'right',
				 formatter: function(value,row,index){
						return fmoney(value,3);
					 }	
			},
			{ field:'acctdAmountCr', title:'<spring:message code="invoiceprint.amount"/>',width:100,align:'right',
				formatter: function(value,row,index){
					return fmoney(value,3);
				 }	
			},
		]],
	   toolbar:[{  
             text:'<spring:message code="button.Detail"/>',  
             iconCls:'icon-search',  
             handler:function(){  
            	 detail();  //监控
             }  
         },'-',{
        	 text:'<spring:message code="button.Accept"/>',  
             iconCls:'icon-edit',  
             handler:function(){  
            	 mainAccept();  //主页面受理
             }  
     },'-'],  
		 onLoadSuccess:function(){  
			 $('#dg').datagrid('clearSelections')
		 },
		onClickRow:function(index,data){
			var row = $('#dg').datagrid('getSelected');
			if (row){
				loadPreLData(row);
			}
		}
	});
	
	$('#dgrequestdetail').datagrid({
		url: '',			
		fitColumns: true,
		nowrap: false,
		pagination:true,
		rownumbers:true,
		singleSelect:false,
		fitColumns: false,
		striped: true,
		idField:'id', //主键字段  
		columns:[[
		    { field:'orgCode', title:'销方交易机构代码',width:100,align:'center'},
		    { field:'orgName', title:'销方交易机构名称',width:150,align:'center'},
		    { field:'trxBatchNum', title:'交易批次号',width:150,align:'center'},
		    { field:'trxSquence', title:'<spring:message code="invoiceTrxPool.trxNumber"/>',width:100,align:'center'},
			{ field:'taxTrxTypeName', title:'<spring:message code="invoiceprint.taxType"/>',width:100,align:'center'}, 
			{ field:'taxTrxTypeCode', title:'<spring:message code="invoiceprint.taxCode"/>',width:100,align:'center'},
			{ field:'trxDate', title:'<spring:message code="invoiceTrxPool.trxDate"/>',width:100,align:'center'},				
			{ field:'invoiceAmount', title:'<spring:message code="invoiceprint.amountAll"/>',width:100,align:'center'},
			{ field:'taxTrxTypeName', title:'<spring:message code="taxItemsmaintain.itemInfo"/>',width:100,align:'center'},
			{ field:'taxRate', title:'<spring:message code="taxRate"/>',width:100,align:'center'},
			{ field:'invoiceAmount', title:'<spring:message code="invoiceprint.canAmount"/>',width:100,align:'center'},
			{ field:'acctdAmountCr', title:'<spring:message code="invoiceprint.billingAmount"/>',width:100,align:'center'},
			{ field:'legalEntityName', title:'<spring:message code="crvatInvoicePre.legalentityName"/>',width:150,align:'center'},
			{ field:'registrationNumber', title:'<spring:message code="crvatInvoicePre.legalentityNum"/>',width:100,align:'center'},
		]],
	 
		 onLoadSuccess:function(){  
			 $('#dgrequestdetail').datagrid('clearSelections')
		 },
		
	});
	//设置分页控件	
	var p = $('#dg').datagrid('getPager'); 
	$(p).pagination({           
		pageSize: 10,//每页显示的记录条数，默认为10           
		//pageList: [5,10,15,20],//可以设置每页记录条数的列表           
		beforePageText: '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
		afterPageText: '<spring:message code="pagination.afterPageText"/>',           
		displayMsg: '<spring:message code="pagination.displayMsg"/>',
		onSelectPage: function (pageNumber,pageSize) {//分页触发		
	    	 find(pageNumber,pageSize);
			 Search(); 
			
         }

	});
	var pdginvoicePre = $('#dgrequestdetail').datagrid('getPager');
	$(pdginvoicePre).pagination(
			{
				pageSize : 10,//每页显示的记录条数，默认为10           
				//pageList: [5,10,15,20],//可以设置每页记录条数的列表           
				beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
				afterPageText : '<spring:message code="pagination.afterPageText"/>',
				displayMsg : '<spring:message code="pagination.displayMsg"/>',
				onSelectPage : function(pageNumber, pageSize) {//分页触发	
					var preHid = $("#preHid").val();
					$.post('crvatInvoicePre/searchPre.do', {
						id : preHid,
						pageNumber : pageNumber,
						pageSize : pageSize
					}, function(result) {
						$('#dgrequestdetail').datagrid('loadData', result);	
						
						 					

					}, 'json');   
					
				}

			});
});


	$(document).ready(function() {
		init_common_combo_customer("#invoiceTrxPool_searchform_customerName_input2");
		$('#invoice_print_newSearch_customerCode').addClass('textbox-disabled'); 
		$('#invoice_print_newSearch_customerName').addClass('textbox-disabled'); 
		$('#prehform').form('load', {
			pageNumber : $('#dgrequestdetail').datagrid('options').pageNumber,
			pageSize : $('#dgrequestdetail').datagrid('options').pageSize
		});
		$('#searchform').form('load', {
			pageNumber : $('#dg').datagrid('options').pageNumber,
			pageSize : $('#dg').datagrid('options').pageSize
		});
		Search();
	});
	
	
	function find(pageNumber,pageSize){
		$('#searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		Search();
	}
	function Search(){	
		$("#dg").datagrid("loading"); 
		$('#searchform').form('submit', {
			url:'crvatInvoicePre/search.do',			
			 success : function(result) {
			   var result = $.parseJSON(result);
			   $("#dg").datagrid('loadData', result);
			   $("#dg").datagrid("loaded"); 
		     } 
		});
	}
	function clearSearchForm(){
		$('#searchform').form('clear');
		$('#searchform').form('load', {
			pageNumber : $('#dg').datagrid('options').pageNumber,
			pageSize : $('#dg').datagrid('options').pageSize
		});
	}
	
	
	function mainAccept(){
		 
		var row = $('#dg').datagrid('getSelected');		
		if(row!=null){
			$("#dg").datagrid("loading");
			var preHid = row.id;
			 if(row.approvalStatus!=10){
				 $.messager.alert('<spring:message code="system.alert"/>','<spring:message code="crvatInvoicePre.selectnoacceptstatus"/>');		
			 }else{
				 $.post('crvatInvoicePre/acceptPre.do', {
						id : preHid,				
					}, function(result) {										
						if(result.success){	
							$("#dg").datagrid("loaded"); 
							Search();
						   $.messager.alert('<spring:message code="system.alert"/>',result.msg);					  
						}								 				
					}, 'json'); 
			 }
			
		}else{
			$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="noselectrecord"/>');
		}
	}
		
	function detail(){		
		var row = $('#dg').datagrid('getSelected');		
		if(row!=null){
			$("#layoutid").layout('expand', 'east');
		}else{
			$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="noselectrecord"/>');
		}
		
		
	}
	
	
	function loadPreLData(row){
		if(row){						
			 if(row.approvalStatus==10){
				$('#acceptbtn').linkbutton('enable');
				$('#revertbtn').linkbutton('disable');				
			}else if(row.approvalStatus==20){
				$('#acceptbtn').linkbutton('disable');
				$('#revertbtn').linkbutton('enable');
			}else{
				$('#acceptbtn').linkbutton('disable');
				$('#revertbtn').linkbutton('disable');
			}	 	
			$('#prehform').form('load',{
				invoiceReqNum:row.invoiceReqNum,
				operationOrgCode:row.requestOrg,
				acceptorg:row.acceptOrg,
				buyidentitytype:row.custRegistrationCodeStr,
				buyidentitynum:row.custRegistrationNumber,
				bankname:row.bankName,
				bankAccount:row.bankAccount,
				contactName:row.contactName,
				address:row.address,
				cratInvoicePreNum:row.crvatInvoicePreNumber,
				createdate:row.invoicePreDate,
				submitdate:row.strSubmitDate,
				status:row.approvalStatus,
				totalamount:row.invoiceAmount,
				retainamount:row.acctdAmountCr,
				preHid:row.id
			});
			$('#prehformstatus').combobox('setValues',row.approvalStatus); 
			var id = row.id;	
			var pageNumber = $('#dgrequestdetail').datagrid('options').pageNumber;
			var pageSize = $('#dgrequestdetail').datagrid('options').pageSize;
			$.post('crvatInvoicePre/searchPre.do', {
				id : id,
				pageNumber : pageNumber,
				pageSize : pageSize
			}, function(result) {
				$('#dgrequestdetail').datagrid('loadData', result);									 				
			}, 'json');   
		}
	}
	
	function Accept(){
		 var preHid = $('#preHid').textbox('getValue');
		 $.post('crvatInvoicePre/acceptPre.do', {
				id : preHid,				
			}, function(result) {										
				if(result.success){	
					Search();
				   $.messager.alert('<spring:message code="system.alert"/>',result.msg);
				   Back();
				}								 				
			}, 'json'); 
		
	}
	
	function Back(){
		$("#layoutid").layout('collapse', 'east');
	}
	
	function Revert(){
		 var preHid = $('#preHid').textbox('getValue');
		 $.post('crvatInvoicePre/revertPre.do', {
				id : preHid,				
			}, function(result) {				
				if(result.success){	
					Search();
				   $.messager.alert('<spring:message code="system.alert"/>',result.msg);
				}								 				
			}, 'json'); 
	}
	
	
	
	
	
</script>

</html>