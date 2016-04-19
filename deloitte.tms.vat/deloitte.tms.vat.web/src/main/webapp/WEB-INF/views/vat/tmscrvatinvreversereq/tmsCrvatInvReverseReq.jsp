
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.deloitte.tms.pl.core.context.utils.ContextUtils"%>

<!DOCTYPE html >
<html>
<head>
<%@ include file="/common/global.jsp"%>
<meta charset="UTF-8">
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<div region="north" split="true" border="false" style="overflow: hidden; height: 27%;">
		<div class="easyui-panel" title="查询条件" style="width: 100%; height: 100%;  background-color: #E0ECFF">
			<form id="TmsCrvatInvReverseReq_searchform" method="post" scroll="no">
				<table cellpadding="5">	
			<tr>
				<td>蓝字发票种类:</td>
			 	 <td><input:select id="categoryId" name="category" value="$categoryId" easyuiClass="easyui-combobox" easyuiStyle="width:150px;" >
							            <option value="" ></option>
							            <input:systemStatusOption parentCode="VAT_CR_INVOICE_TYPE"/>
							</input:select></td>
		    	<td>蓝字发票代码:</td>
			 	 <td><input id="invoiceCodeId" class="easyui-textbox" type="text" style="width: 150px;" name="oriInvoiceCode" value="" />
			 	 	</td>
				<td>蓝字发票号码:</td>
			 	 <td><input id="invoiceNumberId" class="easyui-textbox" type="text" style="width: 150px;" name="oriInvoiceNumber" value="">
			 	 	</input></td>
			 	<td>蓝字开票日期:</td>
			 	 <td>
			 	 	<input class="easyui-datebox" style="width: 150px" name="invoicePrintDate" data-options="formatter:myformatter,parser:myparser,required:false">
			 	 	</input></td>
			</tr>                              
			<tr>
				<td>购方证件类型:</td>
			 	 <td><input:select id="customRegisterTypeId" name="customRegisterType" value="$customRegisterTypeId" easyuiClass="easyui-combobox" easyuiStyle="width:150px;">
							            <option value=""></option>
							            <input:systemStatusOption parentCode="VAT_CUSTOMER_DISC_OPTION"/>
							</input:select></td>
			 	<td>购方证件号码:</td>
			 	 <td><input id="customRegisterNumberId" name="customRegisterNumber" class="easyui-textbox" type="text" style="width: 150px;"  value=""></input></td>
			</tr>                              
			                              
					<tr>
						<td>
							<a href="#" id="searchbtnTmsCrvatInvReverseReq" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px" onclick="SearchTmsCrvatInvReverseReq()">查询</a>
						</td>
						<td>
							<a href="#" class="easyui-linkbutton" style="width:80px" onclick="clearSearchForm()">清除</a>	
		    			</td>
					</tr>
					<tr style="display: none">
						<td><input id="pageNumber" class="easyui-textbox" type="text" style="width: 0px;" name="pageNumber" value=""></input></td>
						<td><input id="pageSize" class="easyui-textbox" type="text" style="width: 0px;" name="pageSize" value=""></input></td>
					</tr>
				</table>
			</form>
		</div>
		
	</div>
	<div data-options="region:'center',border:false"
		style="background-color: #E0ECFF">
		
		<div style="width: 100%; height: 100%">
			<table class="easyui-datagrid" id="TmsCrvatInvReverseReqInit_dataGrid" title="搜索结果" style="width:100%;height:100%" data-options="					
					singleSelect:true,
					autoRowHeight:false,
					pagination:true,
					pageSize:10,
					remoteSort:false,
				    multiSort:true	
					"></table>
		</div>
		
		<div id="TmsCrvatInvReverseReq_addOrEdit_dlg" class="easyui-dialog"
			style="width: 280px; height: 310px;" closed="true"
			buttons="#TmsCrvatInvReverseReq_addOrEdit_dlg_dlg-buttons" modal="true">
			<form id="TmsCrvatInvReverseReq_editform" class="easyui-form" method="post"
				data-options="novalidate:true" commandsName="reverseReqForm">
				<table>
					<tr style="display:none">
						<td><input id="inventoryInvoiceIdInDialog" name="inventoryInvoiceId" type="text" readOnly="true"></td>
					</tr>
					
					<tr>
						
						<td>红字申请日期：</td>
						<td><input id="invoiceReverseReqDateInDialog" name="invoiceReverseReqDate" style="width:120px;" class="easyui-textbox" type="text"></td>
					</tr>
					<tr>
						<td>购方单位名称：</td>
						<td><input id="customNameInDialog" name="customName" style="width: 120px;" class="easyui-textbox" type="text"></td>
					</tr>
					<tr>
						<td>蓝字发票种类：</td>
						<td><input id="categoryInDialog" name="category" style="width:  120px;" class="easyui-textbox" type="text" ></td>
					</tr>                              
					<tr>
						<td>蓝字发票代码：</td>
						<td><input id="invoiceCodeInDialog" name="invoiceCode" style="width: 120px;" class="easyui-textbox" type="text"></td>
					</tr>
					<tr>	
						<td>蓝字发票号码：</td>
						<td><input id="invoiceNumberInDialog" name="invoiceNumber" style="width:  120px;" class="easyui-textbox" type="text"></td>
					</tr> 
					<tr>
						<td>合计金额：</td>
						<td><input id="amountValueInDialog" name="amountValue" style="width:  120px;" class="easyui-textbox" type="text"></td>
					</tr>                             
					<tr>
						<td>红字申请原因：</td>					
						<td><input:select id="invoiceReverseReqReasonInDialog" name="invoiceReverseReqReason" value="$invoiceReverseReqReason" easyuiClass="easyui-combobox" easyuiStyle="width:120px;" >
							            <option value=""></option>
							            <input:systemStatusOption parentCode="VAT_INVOICE_RED_ISSUE_REASON"/>
						</input:select></td>
					</tr>                              
					<tr>
						<td>红字发票信息表编号：</td>
						<td><input id="invoiceReverseHeaderNoInDialog" data-options="required:true" name="invoiceReverseHeaderNo" style="width:  120px;" class="easyui-textbox" type="text" ></td>
					</tr>                              
					<tr>
						<td>是否重开发票：</td>
						<td><input:select id="isRepeatInvoiceInDialog" name="isRepeatInvoice" value="$isRepeatInvoice" easyuiClass="easyui-combobox" easyuiStyle="width:120px;" >
							            <option value=""></option>
							            <input:systemStatusOption parentCode="VAT_IS_ISSUE_INVOICE"/>
							</input:select></td>
				
					</tr>                      
				</table>
			</form>
		</div>
		<div id="TmsCrvatInvReverseReq_addOrEdit_dlg_dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="TmsCrvatInvReverseReqEditSave()"><spring:message code="button.Save" /></a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#TmsCrvatInvReverseReq_addOrEdit_dlg').dialog('close')"><spring:message code="button.Close" /></a>
		</div>
		<div id="history_dlg" class="easyui-dialog" style="width: 800px; height: 500px;" closed="true">
			<table class="easyui-datagrid" id="history_dataGrid" title="<spring:message code='TmsCrvatInvReverseReq.title'/>" style="width: 100%; height: 88%" data-options="singleSelect:true,autoRowHeight:false,remoteSort:false,multiSort:true">
			</table>
		</div>
	</div>
</body>
<script type="text/javascript">

$(document).ready(
	
	
		
	function() {
		$('#TmsCrvatInvReverseReq_searchform').form('load',
			{
				pageNumber : $('#TmsCrvatInvReverseReqInit_dataGrid').datagrid('options').pageNumber,
				pageSize : $('#TmsCrvatInvReverseReqInit_dataGrid').datagrid('options').pageSize
			});
			SearchTmsCrvatInvReverseReq();
		});
	$(function() {
		$('#TmsCrvatInvReverseReqInit_dataGrid')
				.datagrid(
						{
							url : '',
							fitColumns : true,
							nowrap : false,
							pagination : true,
							rownumbers : true,
							singleSelect : true,
							fitColumns : false,
							striped : true,
							idField : 'id', //主键字段 
							frozenColumns:[[
											{field:'id',hidden:true,width:80,align:'center'},
											{field:'oriInventoryInvoiceId',hidden:true,width:80,align:'center'},
										]],
							columns : [ [
									{
										field : 'ck',
										checkbox : true,
										width : 2
									}
									,{
										field : 'category',
										title : '蓝字发票种类',
										width : 80,
										align : 'center'
									}
									,{
										field : 'version',
										title : '蓝字发票版本',
										width : 80,
										align : 'center'
									}
									,{
										field : 'oriInvoiceCode',
										title : '蓝字发票代码',
										width : 90,
										align : 'center'
									}                           
									,{
										field : 'oriInvoiceNumber',
										title : '蓝字发票号码',
										width : 80,
										align : 'center'
									}
									,{
										field : 'printTerminalNumber',
										title : '打印终端编号',
										width : 80,
										align : 'center'
									}
									,{
										field : 'customName',
										title : '购方单位名称',
										width : 100,
										align : 'center'
									}
									,{
										field : 'customRegisterType',
										title : '购方证件类型',
										width : 80,
										align : 'center'
									}
									,{
										field : 'customRegisterNumber',
										title : '购方证件号码',
										width : 110,
										align : 'center'
									}
									,{
										field : 'vatNetValue',
										title : '蓝字发票净额',
										width : 80,
										halign: 'center',
										align : 'right',
										formatter: function(val,rec){
											return val.toFixed(2);
										}
									}
									,{
										field : 'vatTaxValue',
										title : '蓝字发票税额',
										width : 80,
										halign: 'center',
										align : 'right',
										formatter: function(val,rec){
											return val.toFixed(2);
										}
									}
									,{
										field : 'vatAmountValue',
										title : '蓝字价税合计',
										width : 80,
										halign: 'center',
										align : 'right',
										formatter: function(val,rec){
											return val.toFixed(2);
										}
									}
									,{
										field : 'invoicePrintDate',
										title : '开具日期',
										width : 80,
										align : 'center'
									}               
									,{
										field : 'requestBy',
										title : '申请人',
										width : 80,
										align : 'center'
									}] ],
							toolbar : [ {
								text : '红字申请',
								iconCls : 'icon-undo',
								handler : function() {
									TmsCrvatInvReverseReqAdd();
								}
							}],
							onLoadSuccess : function() {
								$('#TmsCrvatInvReverseReqInit_dataGrid').datagrid(
										'clearSelections')
							},
							onClickRow : function(index, data) {
								var row = $('#TmsCrvatInvReverseReqInit_dataGrid')
										.datagrid('getSelected');
								if (row) {
									loadSaveFormData(row);
								}
							}
						});
		//MARK:Search_form-enter-action
		
		$(function(){
			document.onkeydown = function(e){
    		var ev = document.all ? window.event : e;
    		if(ev.keyCode==13) {
    			var isOpen = $("#TmsCrvatInvReverseReq_addOrEdit_dlg").dialog("options").closed;
    			if (isOpen){
    				SearchTmsCrvatInvReverseReq();
    			}
     		}
		}
		});
		
		/*$('#invoiceCodeId').textbox('textbox').keydown(function (e) {
        	if (e.keyCode == 13) {
        		SearchTmsCrvatInvReverseReq();
        	}
    	});
		$('#invoiceNumberId').textbox('textbox').keydown(function (e) {
        	if (e.keyCode == 13) {
        		SearchTmsCrvatInvReverseReq();
        	}
    	});
		$('#customRegisterNumberId').textbox('textbox').keydown(function (e) {
        	if (e.keyCode == 13) {
        		SearchTmsCrvatInvReverseReq();
        	}
    	});*/
		
		//设置分页控件	
		var p = $('#TmsCrvatInvReverseReqInit_dataGrid').datagrid('getPager');
		$(p).pagination(
		{
			pageSize : 10,//每页显示的记录条数，默认为10           
			//pageList: [10,20,30],//可以设置每页记录条数的列表           
			beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
			afterPageText : '<spring:message code="pagination.afterPageText"/>',
			displayMsg : '<spring:message code="pagination.displayMsg"/>',
			onSelectPage : function(pageNumber, pageSize) {//分页触发		
				find(pageNumber, pageSize);
				SearchTmsCrvatInvReverseReq();
			}
		});
	});
	

	
	function find(pageNumber, pageSize) {
		$('#TmsCrvatInvReverseReq_searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		SearchTmsCrvatInvReverseReq();
	}
	function SearchTmsCrvatInvReverseReq() {
		$("#TmsCrvatInvReverseReqInit_dataGrid").datagrid("loading");
		$('#TmsCrvatInvReverseReq_searchform').form('submit', {
			url : '${vat}/tmsCrvatInvReverseReq/loadTmsCrvatInvReverseReqPage.do',
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(result) {
				//数据加载以及绑定
				
				var result = $.parseJSON(result);
				$("#TmsCrvatInvReverseReqInit_dataGrid").datagrid('loadData', result);
				$("#TmsCrvatInvReverseReqInit_dataGrid").datagrid("loaded");
			}
		});
	}
	function getHistory() {
		//	alert(0);
		//$("#TmsCrvatInvReverseReqInit_dataGrid").datagrid("loading");
		$('#TmsCrvatInvReverseReq_searchform').form('submit', {
			url : '${vat}/tmsCrvatInvReverseReq/loadhistory.do',
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(result) {
				//数据加载以及绑定
				//		alert(1);
				var result = $.parseJSON(result);
				$("#history_dataGrid").datagrid('loadData', result);
				$("#history_dataGrid").datagrid("loaded");
			}
		});
	}
	function searchTmsCrvatInvReverseReq() {
		$("#history_dlg").dialog('open').dialog('setTitle',"<spring:message code='TmsCrvatInvReverseReq.history'/>");
		$('#history_dataGrid').datagrid({
			url : '',
			fitColumns : true,
			nowrap : false,
			rownumbers : true,
			singleSelect : false,
			fitColumns : true,
			striped : true,
			idField : 'id', //主键字段  
			columns : [ [ {
				field : 'startDate',
				title : '开始时间',
				width : 80,
				align : 'center'
			}, {
				field : 'endDate',
				title : '结束时间',
				width : 100,
				align : 'center'
			}, {
				field : 'successful',
				title : '是否成功',
				width : 80,
				align : 'center'
			}, ] ],
		});

		getHistory();
	}
	
	//remove
	function TmsCrvatInvReverseReqRemove(){
		
		var row = $('#TmsCrvatInvReverseReqInit_dataGrid').datagrid('getChecked');
		if (row.length != 0) {
			var id;
			$.each(row,function(index,item){
				if(index==0){
					id=item.id;
				}
			});
		}
		var data ="id="+id;
		 $.ajax({ 
	            type: "POST", 
	            url: '${vat}/tmsCrvatInvReverseReq/removeTmsCrvatInvReverseReq.do', 
	            data : data,
				dataType : "json", 
				cache : false,
	            success: function() { 
	                 
	                 } 
	         }); 
		
			$.messager.alert("操作提示", id);	
	}
	
	
	//添加
	function TmsCrvatInvReverseReqAdd() {
		clearSaveForm();
		$("#invoiceReverseReqDateInDialog").textbox('readonly',true); 
		$("#customNameInDialog").textbox('readonly',true); 
		$("#categoryInDialog").textbox('readonly',true); 
		$("#invoiceCodeInDialog").textbox('readonly',true); 
		$("#invoiceNumberInDialog").textbox('readonly',true); 
		$("#amountValueInDialog").textbox('readonly',true); 
		
		
			var row = $('#TmsCrvatInvReverseReqInit_dataGrid').datagrid('getChecked');
			
			
			if (row.length != 0) {
				var dialog_item;
				$.each(row,function(index,item){
					if(index==0){
						dialog_item=item;
					}
				});
				
				if (dialog_item.id == null) {
					var cuurentDate = new Date()
					$("#TmsCrvatInvReverseReq_addOrEdit_dlg").dialog('open').dialog('setTitle',"红字申请");
					$("#TmsCrvatInvReverseReq_editform").form('load', {
						inventoryInvoiceId:dialog_item.oriInventoryInvoiceId,
						invoiceReverseReqDate:cuurentDate.toLocaleDateString(),
						customName:dialog_item.customName,
						category:dialog_item.category,
						invoiceCode:dialog_item.oriInvoiceCode,
						invoiceNumber:dialog_item.oriInvoiceNumber,
						amountValue:dialog_item.vatAmountValue
						});
					}else{
						$.messager.alert("操作提示", "本张发票已经进行过红字申请，请重新选择");
					}
				
				}else{
					$.messager.alert("操作提示", "请选择一条数据进行红字申请");
				}

	}
	
	function TmsCrvatInvReverseReqEditSave() {
		var reason = $("#invoiceReverseReqReasonInDialog").textbox('getValue'); 
		if (reason == ""){
			$.messager.alert("操作提示", "红字申请原因不可为空");
			return;
		}
		var headerNo = $("#invoiceReverseHeaderNoInDialog").textbox('getValue'); 
		if (headerNo == ""){
			$.messager.alert("操作提示", "红字发票信息表编号不可为空");
			return;
		}
		var isRepeat = $("#isRepeatInvoiceInDialog").textbox('getValue'); 
		if (isRepeat == ""){
			$.messager.alert("操作提示", "是否重新开具不可为空");
			return;
		}
		$.messager.confirm("<spring:message code='client.datacommit.formsave.confirm.title'/>","<spring:message code='client.datacommit.formsave.confirm.text'/>",
		function(result) {
			if (result) {
				$('#TmsCrvatInvReverseReq_editform').form(
				'submit',
				{
					url : '${vat}/tmsCrvatInvReverseReq/saveTmsCrvatInvReverseReq.do',
					onSubmit : function() {
						return $(this).form('enableValidation').form('validate');
					},
					success : function(result) {
						var result = $.parseJSON(result);
							clearSaveForm();
							SearchTmsCrvatInvReverseReq();
							$('#TmsCrvatInvReverseReq_addOrEdit_dlg').dialog('close');
							$.messager.alert('<spring:message code="system.alert"/>',result.msg);

					}
				});
			}
		});

	}
	

	//清空dialog内容

	function clearSaveForm() {
		$('#TmsCrvatInvReverseReq_editform').form('clear');
	}
	
	//MARK：clear the search form
	function clearSearchForm(){
		$('#TmsCrvatInvReverseReq_searchform').form('clear');
		$('#TmsCrvatInvReverseReq_searchform').form('load',
				{
					pageNumber : $('#TmsCrvatInvReverseReqInit_dataGrid').datagrid('options').pageNumber,
					pageSize : $('#TmsCrvatInvReverseReqInit_dataGrid').datagrid('options').pageSize
				});
	}	
	
	//MARK：number restriction
	$(function(){
		    $("#invoiceReverseHeaderNoInDialog").textbox('textbox').bind('keyup', function(e){
			        $("#invoiceReverseHeaderNoInDialog").textbox('setValue', $(this).val().replace(/\D/g,''));
		    });
});
	
</script>
</html>