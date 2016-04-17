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
		<div region="north" split="true" border="false"
				style="overflow: hidden; height: 90%;">
				<div class="easyui-panel"
					title="<spring:message code="invoiceprint.newAdd"/>"
					style="width: 1500px; height: 100%; background-color: #E0ECFF">
					<form id="newDetailForm" method="post" >
						<table>
							<tr>
								<td><spring:message code="invoiceAbolish.category" />：</td>
								<td>
								<input  class="easyui-combobox" name="invoiceCategory" style="width:120px" editable="false" data-options="url:'getdictionary.do',
                                                                                                       method:'get',
                                                                                                       valueField:'value',
                                                                                                       textField:'text',
                                                                                                       panelHeight:'auto'
                                                                                         ">
								<%-- <input:select id="invoice_print_newSearch_invoiceCategory"
										name="invoiceCategory" value="$invoice_print_newSearch_invoiceCategory"
										easyuiClass="easyui-combobox" easyuiStyle="width:110px;">
										<option value=""></option>
										<input:systemStatusOption parentCode="INVOICE_TYPE" />
								</input:select>  --%>
								</td>
								<%-- <td><spring:message code="invoiceprint.limittype" />：</td>
								<td>
								<input:select id="invoice_print_newSearch_limittype"
										name="invoiceLimitAmount" value="$invoice_print_newSearch_limittype"
										easyuiClass="easyui-combobox" easyuiStyle="width:110px;">
										<option value=""></option>
										<input:systemStatusOption parentCode="INVOICE_LIMIT" />
								</input:select> 
								</td> --%>
								<td><spring:message code="invoiceprint.quota" />：</td>
								<td><input id="invoice_print_newSearch_invoiceLimitAmountValue" name="invoiceLimitAmountValue" required="true" class="easyui-textbox" style="width: 120px;"></td>
							</tr>
							<tr>
								<td><spring:message code="com.vat.base.model.customer.customerEntityNum.text" />：</td>
								<td><input id="invoice_print_newSearch_clientEntityNum" name="legalEntityCode" class="easyui-validatebox" required="true" onblur="getLegalInfo()" style="width: 120px;"></td>
								<td><spring:message code="orgTaxpayerRelation.taxpayerName" />：</td>
								<td><input id="invoice_print_newSearch_taxpayerName" name="legalEntityName" class="easyui-textbox" style="width: 120px;"></td>  
								<td><spring:message code="taxItemsmaintain.isUsed" />：</td>
								<td>
									<select id="tax_req_dlg_isUsed" name="enabledFlag" class="easyui-combobox" style="width:120px">
				    					<option value="-1" selected>&nbsp;</option>
				    					<option value="1">是</option>
				    					<option value="0" >否</option>
				    				</select>
		    					</td>
		    					<td><spring:message code="usermaintain.validatedatefrom" />：</td>
		    					<td><input id="from" class="easyui-datebox" style="width:100px;" name="startDate" /></input></td>
		    					<td><spring:message code="usermaintain.validatedateto" />：</td>
		    					<td><input id="to" class="easyui-datebox" style="width:100px;" name="endDate" /></input></td>
							</tr>
							 <tr>
								<td>
									<a href="#" id="newsearchbtn" class="easyui-linkbutton"
									data-options="iconCls:'icon-search'" style="width: 80px"
									onclick="saveDetails()"><spring:message code="button.Save" /></a>
								</td>
								<td><a href="#" id="newclearbtn" class="easyui-linkbutton"
									style="width: 80px" onclick="closeDialog()"><spring:message code="button.Close" /></a></td>
							</tr> 
							<tr style="display: none">
								<td><input id="newpageNumber" class="easyui-textbox"
									type="text" style="width: 0px;" name="pageNumber" value=""></input></td>
								<td><input id="newpageSize" class="easyui-textbox" type="text"
									style="width: 0px;" name="pageSize" value=""></input></td>
							</tr>
							<tr style="display: none">
								<td input id="id" name="id" class="easyui-textbox" style="height:20px"></td>
								<td><input id="invoice_print_newSearch_legalEntityId" name="legalEntityId" class="easyui-textbox" style="width: 20px;"></td>
							</tr>	
						</table>
					</form>
				</div>
			</div>
		<!-- <div class="easyui-panel"
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
		</div> -->
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
								<td><spring:message code="com.vat.base.model.customer.customerEntityNum.text"/>:</td>
								<td> <input id="invoice_dlg_clientEntityNum" class="easyui-textbox" style="width:120px;" name="legalEntityCode" /></input></td>
				    			<%-- <td><spring:message code="invoiceprint.readyStatus"/>:</td>
				    			<td>
				    				<input:select id="invoice_dlg_bootStatus"
											name="status" value="$invoice_print_newSearch_bootStatus"
											easyuiClass="easyui-combobox" easyuiStyle="width:110px;">
											<option value=""></option>
											<input:systemStatusOption parentCode="tms.vat.invoicereqstatus" />
									</input:select> 
				    			</td> --%>
	    					</tr>
							<tr>
								<td>
									<!-- <div style="text-align:center;padding:10px"> --> <a
									href="#" id="searchbtn" class="easyui-linkbutton"
									data-options="iconCls:'icon-search'" style="width: 80px"
									onclick="Search()"><spring:message code="button.Search" /></a>
								</td>
								<td><a href="#" id="clearbtn" class="easyui-linkbutton"
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
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#layoutid").layout('collapse', 'east');
		$('#newDetailForm').form('load', {
			pageNumber : $('#dg').datagrid('options').pageNumber,
			pageSize : $('#dg').datagrid('options').pageSize
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
			nowrap: false,
			pagination:true,
			rownumbers:true,
			singleSelect:false,
			fitColumns: true,
			striped: true,
			idField:'id', //主键字段  
			columns:[[
				{field:'ck',checkbox:true,width:2}, //显示复选框     
				{ field:'legalEntityCode', title:'<spring:message code="client.clientEntityNum"/>',width:100,align:'center'},
				{ field:'legalEntityName', title:'<spring:message code="client.search.entityName"/>',width:100,align:'center'},
				{ field:'invoiceTypeName', title:'<spring:message code="invoiceAbolish.category"/>',width:100,align:'center'}, 
				{ field:'invoiceLimitAmountValue', title:'<spring:message code="invoiceprint.quota"/>',width:100,align:'center'},
				{ field:'startDate', title:'<spring:message code="usermaintain.validatedatefrom"/>',width:100,align:'center'},
				{ field:'endDate', title:'<spring:message code="usermaintain.validatedateto"/>',width:100,align:'center'},
				{ field:'enabledFlag', title:'<spring:message code="equipment。enabledFlag"/>',width:100,align:'center',formatter:formatIsUsed},
			]],
		   toolbar:[{  
                 text:'<spring:message code="button.Add"/>',  
                iconCls:'icon-add',  
                handler:function(){  
                	addInvoiceTax();  
                }  
             },'-',{  
                 text:'<spring:message code="button.Remove"/>',  
                 iconCls:'icon-remove',  
                 handler:function(){  
                	 removeInvoiceTax();  
                      
                 }  
             },'-',{  
                 text:'<spring:message code="button.Edit"/>',  
                 iconCls:'icon-edit',  
                 handler:function(){  
                	 editDetail();
                 }  
             },'-'/* ,{  
                 text:'<spring:message code="button.Detail"/>',  
                 iconCls:'icon-search',  
                 handler:function(){  
                	 detail();  //监控
                 }  
             },'-' */],  
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
	/* function searchDetails(){
		$('#newDetailForm').form('submit', {
			url:'invoiceReqH/transactionlist.do',			
			 success : function(result) {
			   var result = $.parseJSON(result);
			   $("#dgrequestdetail").datagrid('loadData', result);
			   //$("#newDetailForm").form('loadData', result.customerId);
			   $('#invoice_print_newSearch_customerId').textbox('setValue',result.customerId);
		     } 
		});
	} */
	function find(pageNumber,pageSize){
		$('#searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
	}
	function Search(){
		$("#dg").datagrid("loading");
		$('#searchform').form('submit', {
			url:'loadTmsMdLegalInvoicePage.do',			
			 success : function(result) {
			   var result = $.parseJSON(result);
			   $("#dg").datagrid("loaded");
			   $("#dg").datagrid('loadData', result);
		     } 
		});
	}
	function saveDetails(){
		if($('#invoice_print_newSearch_legalEntityId').val().length>0){
			$('#newDetailForm').form('submit', {
				url:'saveTmsMdLegalInvoice.do',			
				 success : function(object) {
					var object = $.parseJSON(object);
				 	$.messager.alert('<spring:message code="system.alert"/>',object.msg);
				 	closeDialog();
				 	Search();
			     } 
			});
		}else{
			$.messager.alert('<spring:message code="system.error"/>','纳税人信息不存在或不完整，请重新填写！');
		}
	}
	function addInvoiceTax(){
		$('#layoutid').layout('expand', 'east');
	}
	function editDetail(){
		//var rows = $('#dg').datagrid('getChecked');
		var rows  = $('#dg').datagrid('getSelections');
		if(rows.length==1){
			var row = $('#dg').datagrid('getSelected');
			var legalentityid=row.legalEntityId;
			$('#layoutid').layout('expand', 'east');
			$('#newDetailForm').form('load',row);
		}else{
		 	$.messager.confirm('<spring:message code="invoiceprint.reqinfo"/>','<spring:message code="invoiceprint.error"/>' );
		}
	}
	function getLegalInfo(){
		var legalEntityCode=$('#invoice_print_newSearch_clientEntityNum').val();
		$('#invoice_print_newSearch_taxpayerName').textbox('setValue','');
		$.ajax({
			url : "getLegalInfoList.do?legalEntityCode="+legalEntityCode,
            dataType : "json",
            cache:false,
            success : function(object) {
            	if(object.legalEntityId){
	            	$('#invoice_print_newSearch_legalEntityId').textbox('setValue',object.legalEntityId);
	            	$('#invoice_print_newSearch_taxpayerName').textbox('setValue',object.legalEntityName);
            	}else{
            		$.messager.alert('<spring:message code="system.error"/>','纳税人信息不存在或不完整，请重新填写！');
            	}
            }
		});
	}
	function addDetails(){
		/* $('#layoutid').layout('expand', 'east'); */
		$("#tax_req_dlg").dialog('open').dialog('setTitle',
		'<spring:message code="invoiceprint.addDetails"/>');
	}
	function clearSearchForm(){
		$('#searchform').form('clear');
	} 
	function closeDialog(){
		$("#layoutid").layout('collapse', 'east');
		$('#newDetailForm').form('clear');
	}
	function removeInvoiceTax(){
		$.messager.confirm('提示','确定要删除吗?',function(result){
            if (result){  
                var rows = $('#dg').datagrid('getChecked');  
                var ps = "";  
                $.each(rows,function(i,n){  
                    if(i==0)   
                        ps +=n.id;  
                    else  
                        ps += ","+n.id;  
                }); 
                $.ajax({
                    url : "${vat}/taxpayerinvoicetype/removeTmsMdLegalInvoices.do?ids="+ps,
                    type : 'POST',
                    dataType : "json",
                    cache:false,
                    success : function(object) {
                        if (object.result) {	                        		                        	
                        	Search();
                        } 
                        $.messager.alert('<spring:message code="system.alert"/>',object.msg);
                    }
                });	
            }  
        });  
	}
</script>

</html>