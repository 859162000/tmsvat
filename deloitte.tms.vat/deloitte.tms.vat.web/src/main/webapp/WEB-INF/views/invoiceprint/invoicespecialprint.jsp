<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/tags/input" prefix="input"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<!-- <link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.4/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.4/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.4/demo.css">
	<script type="text/javascript" src="jquery-easyui-1.4.4/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.4.4/jquery-1.8.0.js"></script> 
	<script type="text/javascript" src="resource/corejs/dateformat.js"></script>  -->

<%@ include file="/common/global.jsp"%>
</head>
<body class="easyui-layout" id="layoutid" style="overflow-y: hidden"
	scroll="no">
	<div data-options="region:'east',split:true"
		title="<spring:message code="invoiceprint.reqinfo"/>"
		style="width: 100%;">
		<div region="north" split="true" border="false"
				style="overflow: hidden; height: 25%;">
				<div class="easyui-panel"
					title="<spring:message code="invoiceprint.newAdd"/>"
					style="width: 1500px; height: 100%; background-color: #E0ECFF">
					<form id="newDetailForm" method="post" >
						<table>
							 <tr>
								<td><spring:message
										code="invoiceprint.readyNo" />:</td>
								<td input class="easyui-textbox" id="invoice_print_newSearch_readyNo" name="crvatInvoiceReqNumber" style="width: 100px;">
		    					</td>
		    					<td><spring:message
										code="invoiceprint.search.time" />:</td>
								<td input class="easyui-textbox" id="invoice_print_newSearch_time" name="invoiceReqDate"  style="width: 100px;">
		    					</td>															
								<td><spring:message
										code="invoiceprint.search.commitDate" />:</td>
								<td><input id="invoice_print_newSearch_submitDate" class="easyui-textbox"
									 type="text" style="width: 100px;" name="submitDate"></input></td>
								<td><spring:message code="invoiceprint.readyStatus" />:</td>
								<td>
									<select id="invoice_print_newSearch_readyStatus" name="status"  class="easyui-combobox" style="width:130px">
				    					<option value="">请选择申请单状态</option>
				    					<option value="10">草稿</option>
				    					<option value="30">已提交</option>
				    					<option value="50" >已审核</option>
				    					<option value="60">准备单生成中</option>
				    					<option value="70">已生成准备单</option>
				    					<option value="40" >已撤销</option>
				    				</select>
		    					</td>
							</tr>
							<tr>
								<td><spring:message
										code="crvatInvoicePre.orgname" />:</td>
								<td><input id="invoice_print_newSearch_orgname" class="easyui-textbox"
									 disabled="true" type="text" style="width: 100px;" name="operationOrgCode"></input></td>
								<td><spring:message
										code="invoiceprint.applicant" />:</td>
								<td><input id="invoice_print_newSearch_applicant" class="easyui-textbox"
									 disabled="true" type="text" style="width: 100px;" name="applicant"></input></td> 				
								<td><spring:message
										code="invoiceprint.validType" />:</td>
								<td>
									<select id="invoice_print_newSearch_validType" name="custRegistrationCode" class="easyui-combobox" style="width:120px">
				    					<option value="">&nbsp;</option>
				    					<option value="0">购方纳税人识别号</option>
				    					<option value="1">社会征信号</option>
				    					<option value="2" >客户号</option>
				    				</select>
		    					</td>
		    					<td input id="invoice_print_newSearch_idType" name="custRegistrationNumber" class="easyui-textbox"   style="width: 120px;">
		    					</td>															
							</tr>
							<tr style="display: none">
								<td input id="invoice_print_newSearch_ids" name="ids" class="easyui-textbox"></td>
								<td input id="invoice_print_newSearch_No" name="readyNo" class="easyui-textbox"></td>
								<!-- <td input class="easyui-textbox" id="invoice_print_newSearch_invoiceReqDate" name="invoiceReqDate"  style="width: 100px;"> -->
								<td input class="easyui-textbox" id="invoice_print_newSearch_customerId" name="customerId"  style="width: 100px;">
								<td input class="easyui-textbox" id="invoice_print_newSearch_createdBy" name="createdBy"  style="width: 100px;">
							</tr>
							<tr>
								<td>
									<a href="#" id="newsearchbtn" class="easyui-linkbutton"
									data-options="iconCls:'icon-search'" style="width: 80px"
									onclick="searchDetails()"><spring:message code="button.Search" /></a>
								</td>
								<td><a href="#" class="easyui-linkbutton"
									style="width: 80px" onclick="clearSearchForm()"><spring:message code="button.Clear" /></a></td>
							</tr>
							<tr style="display: none">
								<td><input id="newpageNumber" class="easyui-textbox"
									type="text" style="width: 0px;" name="pageNumber" value=""></input></td>
								<td><input id="newpageSize" class="easyui-textbox" type="text"
									style="width: 0px;" name="pageSize" value=""></input></td>
							</tr>
						</table>
					</form>
				</div>

			</div>
		<div class="easyui-panel"
			style="width: 100%; height: 100%; padding: 0px;">
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
	</div>

	<div data-options="region:'center',iconCls:'icon-ok'"
		title="<spring:message code="sourcesubject.quicksearch"/>">
		<div class="easyui-layout" style="width: 100%; height: 100%;">
			<div region="north" split="true" border="false"
				style="overflow: hidden; height: 25%;">
				<div class="easyui-panel"
					title="<spring:message code="searchgroup"/>"
					style="width: 1500px; height: 100%; background-color: #E0ECFF">
					<form id="searchform" method="post">
						<!-- commandName="taxItemSearchForm" -->
						<table>
							<tr>
								<td><spring:message
										code="invoiceAbolish.purchaseName" />:</td>
								<td>
									<select id="invoice_print_counter_validType" name=custRegistrationCode class="easyui-combobox" style="width:120px">
				    					<option value="">&nbsp;</option>
				    					<option value="0">购方纳税人识别号</option>
				    					<option value="1">社会征信号</option>
				    					<option value="2" >客户号</option>
				    				</select>
				    				<%-- <input:select id="invoice_print_counter_validType" name="custRegistrationCode" value="$invoice_print_counter_validType" easyuiClass="easyui-combobox" easyuiStyle="width:150px;">
							            <option value=""></option>
							            <input:systemStatusOption parentCode="INVOICE_TYPE"/>
									</input:select> --%>
		    					</td>
		    					<td>输入证件号码：</td>
								<td input class="easyui-textbox" id="invoice_print_dlg_idType" name="custRegistrationNumber"  style="width: 120px;">
		    					</td>															
								<td><spring:message
										code="crvatInvoicePre.clientname" />:</td>
								<td><input id="invoice_print_counter_customerName" class="easyui-textbox"
									type="text" style="width: 150px;" name="customerName"></input></td>
								<td><spring:message code="invoiceprint.readyStatus" />:</td>
								<td>
									<select id="invoice_print_counter_readyStatus" name="status" class="easyui-combobox" style="width:130px">
				    					<option value="">请选择申请单状态</option>
				    					<option value="0">草稿</option>
				    					<option value="1">已提交</option>
				    					<option value="2" >已审核</option>
				    					<option value="3">准备单生成中</option>
				    					<option value="4">已生成准备单</option>
				    					<option value="5" >已撤销</option>
				    				</select>
		    					</td>
							</tr>
							<tr>
							    <td><spring:message
										code="crvatInvoicePre.orgname" />:</td>
								<td>
									<select id="invoice_print_counter_orgId" name="orgId" class="easyui-combobox" style="width:130px">
				    					<option value="-1" selected>请输入查询条件</option>
				    					<option value="0">纳税人识别号</option>
				    					<option value="1">统一社会征信号码</option>
				    				</select>
				    			<td>
				    			<td input class="easyui-textbox" id="invoice_print_dlg_orgType" name="orgType"  style="width: 120px;">														
								<td><spring:message code="crvatInvoicePre.printdatefrom" />:</td>
								<td><input id="time" class="easyui-datebox" width="90"
									name="startDate" /></input></td>
								<td><spring:message code="crvatInvoicePre.printdateto" />:</td>
								<td><input id="to" class="easyui-datebox" width="90"
									name="endDate" /></input></td>
							</tr>
							<tr>
								<td>
									<!-- <div style="text-align:center;padding:10px"> --> <a
									href="#" id="searchbtn" class="easyui-linkbutton"
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
						title="<spring:message code="searchResult"/>"
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
				<%-- <div id="dlg-buttons">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-ok" onclick="saveItems()"><spring:message
							code="button.Save" /></a> <a href="javascript:void(0)"
						class="easyui-linkbutton" iconCls="icon-cancel"
						onclick="javascript:$('#tax_items_dlg').dialog('close')"><spring:message
							code="button.Close" /></a>
				</div> --%>
			</div>
		</div>

	</div>

</body>
<script type="text/javascript">
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
	});
	
	$.extend($.messager.defaults, {
		ok : '<spring:message code="confirm"/>',
		cancel : '<spring:message code="cancel"/>'
	});
	
	
	$(function(){
		$('#dg').datagrid({
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
			    {field:'ck',checkbox:true,width:2}, //显示复选框     
			    { field:'crvatInvoiceReqNumber', title:'<spring:message code="invoiceprint.readyNo"/>',width:100,align:'center'},
				{ field:'customerName', title:'<spring:message code="client.clientName"/>',width:100,align:'center'}, 
				{ field:'customerCode', title:'<spring:message code="client.clientCode"/>',width:100,align:'center'},
				{ field:'custRegistrationCode', title:'<spring:message code="invoiceprint.validType"/>',width:100,align:'center'},
				{ field:'custRegistrationNumber', title:'<spring:message code="invoiceprint.validName"/>',width:100,align:'center'},
				{ field:'invoiceReqDate', title:'<spring:message code="invoiceprint.search.applyTime"/>',width:100,align:'center'},
				{ field:'createdBy', title:'<spring:message code="invoiceprint.applicant"/>',width:100,align:'center'},
				//{ field:'', title:'<spring:message code="invoiceprint.search.bootStatus"/>',width:100,align:'center'},
				{ field:'invoiceAmount', title:'<spring:message code="invoiceprint.amountAll"/>',width:100,align:'center'},
				{ field:'acctdAmountCr', title:'<spring:message code="invoiceprint.amount"/>',width:100,align:'center'},
			]],
		   toolbar:[{  
                 text:'<spring:message code="button.Add"/>',  
                iconCls:'icon-add',  
                handler:function(){  
                	addPre();  
                }  
             },'-',{  
                 text:'<spring:message code="button.Remove"/>',  
                 iconCls:'icon-remove',  
                 handler:function(){  
                	 removePre();  
                      
                 }  
             },'-',{  
                 text:'<spring:message code="button.Edit"/>',  
                 iconCls:'icon-edit',  
                 handler:function(){  
                	 editDetail();
                 }  
             },'-',{  
                 text:'<spring:message code="button.Detail"/>',  
                 iconCls:'icon-search',  
                 handler:function(){  
                	 detail();  //监控
                 }  
             },'-'],  
			 onLoadSuccess:function(){  
				 $('#dg').datagrid('clearSelections')
			 },
			onClickRow:function(index,data){
				var row = $('#dg').datagrid('getSelected');
				if (row){
					loadSaveFormData(row);
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
			    {field:'ck',checkbox:true,width:2}, //显示复选框     
			    { field:'customerName', title:'<spring:message code="client.clientName"/>',width:100,align:'center'},
				{ field:'taxTrxTypeName', title:'<spring:message code="invoiceprint.taxType"/>',width:100,align:'center'}, 
				{ field:'taxTrxTypeId', title:'<spring:message code="invoiceprint.taxCode"/>',width:100,align:'center'},
				{ field:'crvatTrxPoolId', title:'<spring:message code="invoiceTrxPool.trxNumber"/>',width:100,align:'center'},
				{ field:'trxDate', title:'<spring:message code="invoiceTrxPool.trxDate"/>',width:100,align:'center'},
				{ field:'inventoryItemDescripton', title:'<spring:message code="product"/>',width:100,align:'center'},
				{ field:'productAct', title:'<spring:message code="productAct"/>',width:100,align:'center'},
				{ field:'invoiceAmount', title:'<spring:message code="invoiceprint.amountAll"/>',width:100,align:'center'},
				{ field:'taxTrxTypeName', title:'<spring:message code="taxItemsmaintain.itemInfo"/>',width:100,align:'center'},
				{ field:'taxRate', title:'<spring:message code="taxRate"/>',width:100,align:'center'},
				{ field:'invoiceAmount', title:'<spring:message code="invoiceprint.canAmount"/>',width:100,align:'center'},
				{ field:'acctdAmountCr', title:'<spring:message code="invoiceprint.billingAmount"/>',width:100,align:'center'},
			]],
		   toolbar:[{  
                 text:'<spring:message code="button.Add"/>',  
                iconCls:'icon-add',  
                handler:function(){  
                	addDetails();  
                }  
             },'-',{  
                 text:'<spring:message code="button.Remove"/>',  
                 iconCls:'icon-remove',  
                 handler:function(){  
                	 removePre();  
                      
                 }  
             },'-' ,{  
                 text:'<spring:message code="button.Save"/>',  
                 iconCls:'icon-large-shapes',  
                 handler:function(){  
                	 save();//保存
                 }  
             },'-',{  
                 text:'<spring:message code="button.submit"/>',  
                 iconCls:'icon-large-smartart',  
                 handler:function(){  
                	 commit();  //提交
                 }  
             },'-'],  
			 onLoadSuccess:function(){  
				 $('#dgrequestdetail').datagrid('clearSelections')
			 },
			/* onClickRow:function(index,data){
				var row = $('#dgrequestdetail').datagrid('getSelected');
				if (row){
					addToData(row);
				}
			} */
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
				 clearSaveForm();
	         }
	
		});
	});
	
	
	
	
	
	

	function searchDetails(){
		$('#newDetailForm').form('submit', {
			url:'invoiceReqH/transactionlist.do',			
			 success : function(result) {
			   var result = $.parseJSON(result);
			   $("#dgrequestdetail").datagrid('loadData', result);
			   $("#newDetailForm").form('loadData', result.customerId);
		     } 
		});
	}
	function Search(){
		$('#searchform').form('submit', {
			url:'invoiceReqAll/getInvoiceReqAll.do',			
			 success : function(result) {
			   var result = $.parseJSON(result);
			   $("#dg").datagrid('loadData', result);
		     } 
		});
	}
	function doSearch(value) {
	
	}
	
	function addPre(){
		$.ajax({
			url : "invoiceReqH/getNewReadyParam.do",
            dataType : "json",
            cache:false,
            success : function(object) {
            	$("#newDetailForm").form('load', object.invoiceReadyData);
            	$("#invoice_print_newSearch_readyNo").textbox('disable');
            	$("#invoice_print_newSearch_time").textbox('disable');
            	$('#invoice_print_newSearch_readyStatus').combobox('setValue',object.invoiceReadyData.status);
            	$('#invoice_print_newSearch_readyStatus').combobox('disable');
            	$('#invoice_print_newSearch_submitDate').textbox('disable');
            	//$('#invoice_print_newSearch_No').textbox('setValue',object.invoiceReadyData.crvatInvoiceReqNumber);
            	//$('#invoice_print_newSearch_invoiceReqDate').textbox('setValue',object.invoiceReadyData.invoiceReqDate);
            }
		});
		$('#layoutid').layout('expand', 'east');
	}
	function editDetail(){
		//var rows = $('#dg').datagrid('getChecked');
		var rows  = $('#dg').datagrid('getSelections');
		//alert(row.crvatInvoiceReqNumber);
		if(rows.length==1){
			var row = $('#dg').datagrid('getSelected');
			var crvatInvoiceReqNumber=row.crvatInvoiceReqNumber;
			$('#layoutid').layout('expand', 'east');
			$('#newDetailForm').form('load', row);
			var id = row.id;
			 $.post('invoiceReqH/getEditInfo.do', {
				crvatInvoiceReqHId : id,
	    		crvatInvoiceReqNumber:crvatInvoiceReqNumber,
	    		pageNumber : $('#dgrequestdetail').datagrid('options').pageNumber,
				pageSize : $('#dgrequestdetail').datagrid('options').pageSize
			}, function(result) {
			     $("#dgrequestdetail").datagrid('loadData', result);
			}, 'json');  
			 /* $.ajax({
				url : "invoiceReqH/getEditInfo.do",
	            dataType : "json",
	            method:post,
	            cache:false,
	            success : function(object) {
	            }
			});  */
		}else{
		 	$.messager.confirm('<spring:message code="invoiceprint.reqinfo"/>','<spring:message code="invoiceprint.error"/>' );
		}
	}
	function addDetails(){
		$('#layoutid').layout('expand', 'east');
	}
	function editPre(){
		$('#layoutid').layout('expand', 'east');
	}
		
	function detail(){
		$('#layoutid').layout('expand', 'east');
	}
	function clearNewSearchForm(){
		$("#layoutid").layout('collapse', 'east');
		
	} 
	function save(){
		var time = $('#invoice_print_newSearch_time').val();
		var rows = $('#dgrequestdetail').datagrid('getChecked'); 
		if(time!=""&&rows!=null){ 
			var ps = "";  
	        $.each(rows,function(i,n){  
	            if(i==0)   
	                ps +=n.id;  
	            else  
	                ps += ","+n.id;  
	        }); 
	        $('#newDetailForm').form('load', {
	        	ids : ps         
	        });
	         $('#newDetailForm').form('submit', {
				url:'invoiceReqAll/saveInvoiceReqAll.do',			
				 success : function(object) {
					 var object = $.parseJSON(object);
					 clearNewSearchForm();
					 $.messager.alert('<spring:message code="system.alert"/>',object.msg);
					 Search();
			     } 
			}); 
		 }else{
			 $.messager.alert('<spring:message code="invoiceprint.reqinfo"/>','<spring:message code="invoiceprint.info"/>' );
		} 
	}
	
</script>

</html>