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
	<div data-options="region:'center',iconCls:'icon-ok'"
		title="申请单信息">
		<div class="easyui-layout" style="width: 100%; height: 100%;">
			<div region="north" split="true" border="false"
				style="overflow: hidden; height: 22%;">
				<div class="easyui-panel"
					title="客户信息"
					style="width: 100%; height: 100%; background-color: #E0ECFF">
					 <form id="withoutForm" method="post">
				        	<table>
						  		<tr>						  		
									<%-- <td><spring:message code="invoiceTrxPool.trxNumber" />：</td>
									<td><input id="without_dlg_trxNumber" class="easyui-textbox" style="width:120px;" required="true" name="trxNumber"/>								
									<td>
										<a href="#" id="newsearchbtn" class="easyui-linkbutton"
										data-options="iconCls:'icon-search'" style="width: 80px"
										onclick="searchNoCustomer()"><spring:message code="button.Search" /></a>
									</td>
									<td><a href="#" id="newclearbtn" class="easyui-linkbutton"
										style="width: 80px" onclick="clearWithOutForm()"><spring:message code="button.Clear" /></a></td> --%>
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
									<td>购方单位名称：</td>
									<td><input id="without_dlg_customerName"
										name="customerName" class="easyui-textbox"
										style="width: 120px;"></td>
									<td>纳税人类型：</td>
									<td><input id="without_dlg_legalEntityType"
										class="easyui-combobox" name="legalEntityType"
										style="width: 120px" editable="false"
										data-options="url:'nocustomer/getLegalEntityType.do',
		                                              method:'get',
		                                              valueField:'value',
		                                              textField:'text',
		                                              panelHeight:'auto'
		                                 ">
									</td>
									<td>打印发票种类：</td>
									<td><input id="without_dlg_invoiceCategory"
										class="easyui-combobox" name="invoiceCategory"
										style="width: 120px" editable="false"
										data-options="url:'nocustomer/getInvoiceCategory.do',
		                                              method:'get',
		                                              valueField:'value',
		                                              textField:'text',
		                                              panelHeight:'auto'
		                                 ">
									</td>
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
									<td>联系地址：</td>
									<td><input id="without_dlg_address"
										name="custRegistrationAddress" class="easyui-textbox"
										style="width: 120px;"></td>
								</tr>
								<tr>
									<td>
										 <a href="#" id="savebtn" class="easyui-linkbutton"
										data-options="iconCls:'icon-save'"  style="width: 80px"
										onclick="SaveNoCustomerReq()"><spring:message code="button.Save" /></a>
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
									<td><input id="reqHID" class="easyui-textbox"
									type="text" style="width: 0px;" name="reqHID"></input></td>
									<td><input id="formId" class="easyui-textbox"
										type="text" style="width: 0px;" name="id"></input></td>	
								</tr>								    
						  	</table>
			           </form>	
				</div>
			</div>
			<div data-options="region:'center',border:false"
				style="background-color: #E0ECFF">
				<div style="width: 100%; height: 100%">
					<table class="easyui-datagrid" id="dg"
						title="<spring:message code="invoiceprint.transaction"/>"
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
		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="saveWithOut()"><spring:message
				code="button.Add" /></a> <a href="javascript:void(0)"
			class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="closeDlg()"><spring:message
				code="button.Close" /></a>
		</div>		
	</div>
	<div id="without_dlg" class="easyui-dialog" style="width: 100%; height: 100%;" closed="true" buttons="#dlg-buttons">			   			   			   			 			   
			   <div class="easyui-layout" style="width:100%;height:100%;">
					<div data-options="region:'north'" style="width:100%;height:18%;">
					  <div class="easyui-panel"	style="width: 100%; height: 100%; background-color: #E0ECFF">					   
					    <form id="dlgForm" method="post">
				        	<table>
						  		<tr>						  		
									<td><spring:message code="invoiceTrxPool.trxNumber" />：</td>
									<td><input id="without_dlg_trxNumber" class="easyui-textbox" style="width:120px;" required="true" name="trxNumber"/>								
								</tr>
								<tr>
								<td>
									<a href="#" id="newsearchbtn" class="easyui-linkbutton"
									data-options="iconCls:'icon-search'" style="width: 80px"
									onclick="searchNoCustomer()"><spring:message code="button.Search" /></a>
								</td>
								<td><a href="#" id="newclearbtn" class="easyui-linkbutton"
									style="width: 80px" onclick="clearWithOutForm()"><spring:message code="button.Clear" /></a></td>
								</tr>
								<tr style="display: none">
									<%-- <td><input id="dlgpageNumber" class="easyui-textbox"
										type="text" style="width: 0px;" name="pageNumber" value=""></input></td>
									<td><input id="dlgpageSize" class="easyui-textbox" type="text"
										style="width: 0px;" name="pageSize" value=""></input></td> --%>
									<td><input id="withoutdlgId" class="easyui-textbox"
										type="text" style="width: 0px;" name="id"></input></td>
									<td><input id="dlgHid" class="easyui-textbox"
										type="text" style="width: 0px;" name="reqHID"></input></td>
									<td><input id="withoutInvoiceCatery" class="easyui-textbox"
										type="text" style="width: 0px;" name="invoiceCategory"></input></td>	
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
			<%-- <div id="dlg-buttons">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-ok" onclick="saveWithOut()"><spring:message
						code="button.Save" /></a> <a href="javascript:void(0)"
					class="easyui-linkbutton" iconCls="icon-cancel"
					onclick="javascript:$('#without_dlg').dialog('close')"><spring:message
						code="button.Close" /></a>
			</div>				 --%>
</body>
<script type="text/javascript">
$(document).ready(function() {
	$('#withoutForm').form('load', {
		pageNumber : $('#dg').datagrid('options').pageNumber,
		pageSize : $('#dg').datagrid('options').pageSize
	});
	$('#dlgForm').form('load', {
		pageNumber : $('#dlgwithout').datagrid('options').pageNumber,
		pageSize : $('#dlgwithout').datagrid('options').pageSize
	});
	getReqHID();
});
$('#dg')
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
						field : 'legalEntityCode',
						title : '销方交易机构代码',
						width : 100,
						align : 'center'
					},
					{
						field : 'legalEntityName',
						title : '销方交易机构名称',
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
						field : 'trxBatchNum',
						title : '交易批次号',
						width : 100,
						align : 'center'
					},
					{
						field : 'trxDate',
						title : '<spring:message code="invoiceTrxPool.trxDate"/>',
						width : 100,
						align : 'center'
					},
					{
						field : 'sourceCode',
						title : '系统来源',
						width : 100,
						align : 'center'
					},
					{
						field : 'customerNumber',
						title : '购方客户编码',
						width : 100,
						align : 'center'
					},
					{
						field : 'customerName',
						title : '购方单位名称',
						width : 100,
						align : 'center'
					},
					{
						field : 'custBankAccountNum',
						title : '购方客户账号',
						width : 100,
						align : 'center'
					},
					{
						field : 'custBankBranchName',
						title : '购方客户账号开户机构',
						width : 100,
						align : 'center'
					},
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
						title : '开票交易金额',
						width : 100,
						align : 'center'
					},
					{
						field : 'taxBaseName',
						title : '税目',
						width : 100,
						align : 'center'
					},
					{
						field : 'taxSettingMethod',
						title : '计税规则',
						width : 100,
						align : 'center'
					},
					{
						field : 'taxRate',
						title : '税率',
						width : 100,
						align : 'center'
					},
					{
						field : 'invoiceCategoryName',
						title : '开票规则',
						width : 100,
						align : 'center'
					},
					{
						field : 'registrationNumber',
						title : '销方纳税人识别号',
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
						field : 'invoiceAmount',
						title : '实际开具金额',
						width : 100,
						align : 'center'
					},] ],
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
			}, /*  '-' , {
				text : '选择无主交易',
				iconCls : 'icon-add',
				handler : function() {
					addWithOut(); //新增无主交易

				}
			}, */ '-'],
			onLoadSuccess : function() {
				$('#dg').datagrid(
						'clearSelections')
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
						field : 'legalEntityCode',
						title : '销方交易机构代码',
						width : 100,
						align : 'center'
					},
					{
						field : 'legalEntityName',
						title : '销方交易机构名称',
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
						field : 'trxBatchNum',
						title : '交易批次号',
						width : 100,
						align : 'center'
					},
					{
						field : 'trxDate',
						title : '<spring:message code="invoiceTrxPool.trxDate"/>',
						width : 100,
						align : 'center'
					},
					{
						field : 'sourceCode',
						title : '系统来源',
						width : 100,
						align : 'center'
					},
					{
						field : 'customerNumber',
						title : '购方客户编码',
						width : 100,
						align : 'center'
					},
					{
						field : 'customerName',
						title : '购方单位名称',
						width : 100,
						align : 'center'
					},
					{
						field : 'custBankAccountNum',
						title : '购方客户账号',
						width : 100,
						align : 'center'
					},
					{
						field : 'custBankBranchName',
						title : '购方客户账号开户机构',
						width : 100,
						align : 'center'
					},
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
						title : '开票交易金额',
						width : 100,
						align : 'center'
					},
					{
						field : 'taxBaseName',
						title : '税目',
						width : 100,
						align : 'center'
					},
					{
						field : 'taxSettingMethod',
						title : '计税规则',
						width : 100,
						align : 'center'
					},
					{
						field : 'taxRate',
						title : '税率',
						width : 100,
						align : 'center'
					},
					{
						field : 'invoiceCategoryName',
						title : '开票规则',
						width : 100,
						align : 'center'
					},
					{
						field : 'registrationNumber',
						title : '销方纳税人识别号',
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
						field : 'invoiceAmount',
						title : '实际开具金额',
						width : 100,
						align : 'center'
					},] ],
			onLoadSuccess : function() {
				$('#dlgwithout').datagrid('clearSelections')
			},
		});
		var p = $('#dg').datagrid('getPager');
		$(p).pagination(
			{
				pageSize : 10,//每页显示的记录条数，默认为10           
				//pageList: [5,10,15,20],//可以设置每页记录条数的列表           
				beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
				afterPageText : '<spring:message code="pagination.afterPageText"/>',
				displayMsg : '<spring:message code="pagination.displayMsg"/>',
				onSelectPage : function(pageNumber, pageSize) {//分页触发		
					loadNocustomer();
				}
		
			});
		function JTrim(s)
		{
		    return s.replace(/(^\s*)|(\s*$)/g, "");
		}
		function addDetails() {
			var invoiceType=$('#without_dlg_invoiceCategory').combobox('getValue');
			var legalType=$('#without_dlg_legalEntityType').combobox('getValue');
			var companyName=$('#without_dlg_customerName').val();
			var bank = $('#without_dlg__bank').val();
			var bankNumber=$('#without_dlg_bankNum').val();
			var address=$('#without_dlg_address').val();
			var phone=$('#without_dlg_contactPhone').val();
			if(""!=invoiceType){
				if('2'==invoiceType){
					if('1'!=legalType){
						$.messager.alert('<spring:message code="system.alert"/>','专票只能由一般纳税人申请');
						return;
					}
					if(companyName==""||bank==""||bankNumber==""||address==""||phone==""){
						$.messager.alert('<spring:message code="system.alert"/>','专票申请必须补全客户信息');
						return;
					}
				}
				$('#dlgwithout').datagrid('loadData', {
					total : 0,
					rows : []
				});
				$("#without_dlg").dialog('open').dialog('setTitle',
						'<spring:message code="invoiceprint.addDetails"/>');
				$('#withoutInvoiceCatery').textbox('setValue',$('#without_dlg_invoiceCategory').combobox('getValue'));
			}else{
				$.messager.alert('<spring:message code="system.alert"/>','请补全必输项');
				return;
			}
			
		}
		function getReqHID(){
			$.ajax({
				url : "nocustomer/getReqhId.do",
				type : 'GET',
				dataType : "json",
				cache : false,
				success : function(result) {
					//var object = $.parseJSON(result);
					$('#reqHID').textbox('setValue',result.reqHID);
					$('#dlgHid').textbox('setValue',result.reqHID);
				}
			});
		}
		function remove(){
			var rows = $('#dg').datagrid('getSelections');
			if ('0' != rows.length) {
			$.messager.confirm('<spring:message code="system.alert"/>','确定删除该交易流水？',function(result){  
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
							url : "nocustomer/removeTempInvoice.do?ids=" + ps,
							type : 'POST',
							dataType : "json",
							cache : false,
							success : function(object) {
								$.messager.alert('<spring:message code="system.alert"/>',
										object.msg);
								$('#dg').datagrid('loaded');
								loadNocustomer();
							}
						});
				  }
			});
			}else {
				$.messager.alert('提示信息',
				'请选择一条交易纪录');
			}
		}
		//保存无主交易申请单并回写客户信息
		function SaveNoCustomerReq(){
			var id=$('#reqHID').textbox('getValue');
			/* $.ajax({
				url : "nocustomer/saveCustomerAndReq.do?id=" + id,
				type : 'POST',
				dataType : "json",
				cache : false,
				success : function(object) {
					
				}
			}); */
			$('#dg').datagrid('loading');
			$('#withoutForm').form('submit', {
				url : 'nocustomer/saveCustomerAndReq.do',
				success : function(result) {
					var object = $.parseJSON(result);
					if(object.success){
						$.messager.alert(
								'提示信息','保存成功');
						//location.reload();
						setTimeout("window.location.reload()",2000);
					}
					else{
						$.messager.alert(
								'提示信息',
								object.errorMessage);
					}
					$('#dg').datagrid('loaded');
				}
			});
		}
		//将选中的交易流水保存到临时表
		function saveWithOut(){
			var rows = $('#dlgwithout').datagrid('getChecked');
			for (var i = 0; i < rows.length; i++) {
				$("#dg").datagrid('insertRow', {
					index : 0,
					row : rows[i]
				});
			}
			$('#without_dlg').dialog('close');
			$('#dlgForm').form('submit', {
				url : 'nocustomer/saveTempReqL.do',
				success : function(result) {
					var object = $.parseJSON(result);
					if(object.success){
						loadNocustomer();
					}
					else{
						$.messager.alert(
								'提示信息',
								result.errorMessage);
					}
				}
			});
		}
		function loadNocustomer(){
			$('#dg').datagrid('loading');
			//$('#reqHID').textbox('getValue');
			$('#withoutForm').form('submit', {
				url : 'nocustomer/loadNoCustomer.do',
				success : function(result) {
					var result = $.parseJSON(result);
					if(result.status=='0') {
						$("#dg").datagrid("loadData",result.data);
						$("#dg").datagrid("loaded")
					}else{
						$.messager.alert(
								'提示信息',
								result.erroMessage);
						$("#dg").datagrid("loaded");
					}
					/* var result = $.parseJSON(result);
					$("#dg").datagrid('loadData', result); */
					
				}
			});
		}
		function searchNoCustomer(){
			var number=$('#without_dlg_trxNumber').val();
			if(number){
				$("#dlgwithout").datagrid("loading");
				$('#dlgForm').form('submit', {
					url : 'nocustomer/noCustomerTransactionList.do',
					success : function(result) {
						$("#dlgwithout").datagrid("loaded");
						var object = $.parseJSON(result);
						if(object.success){
							$("#dlgwithout").datagrid('loadData', object);
							//$("input[name='chk_list']").attr("checked",true);  
							$('#dlgwithout').datagrid('selectAll');
							$('#withoutdlgId').textbox('setValue',object.lid);
						}else{
							$.messager.alert(
									'提示信息',
									object.errorMessage);
						}
						
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
		function clearSearchForm(){
			var reqHID=$('#reqHID').textbox('getValue');
			$('#withoutForm').form('clear');
			$('#reqHID').textbox('setValue',reqHID);
		}
		function clearWithOutForm(){
			var reqHID=$('#dlgHid').textbox('getValue');
			$('#dlgForm').form('clear');
			$('#dlgHid').textbox('setValue',reqHID);
		}
		function closeDlg(){
			$('#without_dlg').dialog('close')
			$('#without_dlg_trxNumber').textbox('clear');
		}
</script>