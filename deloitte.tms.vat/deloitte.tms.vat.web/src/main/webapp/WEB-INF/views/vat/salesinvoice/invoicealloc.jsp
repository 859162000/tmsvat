<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/tags/input" prefix="input"%>
<%@page import="com.deloitte.tms.pl.core.context.utils.ContextUtils"%>
<%
	String userCode = ContextUtils.getCurrentUserCode();
%> 
<!DOCTYPE html >
<html>
<head>
<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">    
    <div region="north" split="true" border="false" style="overflow: hidden; height: 20%;">  
        <div class="easyui-panel" title="<spring:message code="searchgroup"/>" style="width:100%;height:100%; background-color: #E0ECFF">		
		    <form id="searchform" method="post" >
		    	<table style="width: 1000px; height: 100%" >
		    		<tr>
		    			<td align="right">分发单号: </td>
		    			<td>
		    				<input class="easyui-textbox" id="searchform_invoiceAllotNumber" name="invoiceAllotNumber" style="width: 150px;">
		    			</td>
		    		    <td align="right">分发单状态:</td>
		    			<td>
							<input:select id="searchform_status" name="approvalStatus" value="$approvalStatus" easyuiClass="easyui-combobox" easyuiStyle="width:150px;">
								<option value=""></option>
								<input:systemStatusOption parentCode="VAT_CR_INVOICE_DISTRIBUTE_STATUS"/>
							</input:select>
		    			</td>
		    			<td align="right">分发日期:</td>
		    			<td>
		    				 <input id="searchform_time" name="startDate" class="easyui-datebox" width="90" /></input>
		    			</td>
		    			<td>
		    				 至: <input id="searchform_endDate" name="endDate" class="easyui-datebox" width="90" /></input>
		    			</td>
	    			</tr>
	    			<tr>
		    			<td>
		    	             <a href="#" id="searchbtn"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px;" onclick="search_h()">查询</a>
		    			</td>
		    			<td>
	    					<a href="#" class="easyui-linkbutton" style="width:80px" onclick="clearSearchForm()">重置</a>
		    			</td>
		    		</tr>
		    		<tr style="display:none">
		    		   <td><input id="pageNumber" class="easyui-textbox" type="text" style="width:0px;" name="pageNumber" value=""></input></td>
		    		   <td><input id="pageSize" class="easyui-textbox" type="text" style="width:0px;" name="pageSize" value=""></input></td>
		    		</tr>		 	    	
		    	</table>
		    </form>
	    </div>
	    	         
    </div>
	<div data-options="region:'center',border:false" style="background-color: #E0ECFF">
    	<div style="width:100%;height:100%">
	         <table class="easyui-datagrid" id="dg" title="搜索结果" style="width:100%;height:100%" data-options="					
					singleSelect:true,
					autoRowHeight:false,
					pagination:true,
					pageSize:10,
					remoteSort:false,
				    multiSort:true	
					">			 
		   </table>   
		</div>		     
		<div id="alloc_dialog" class="easyui-dialog"
			style="width: 880px; height: 432px;" closed="true" buttons="#alloc_dialog-buttons">
			<form id="alloc_dialog_Form" class="easyui-form" method="post" data-options="novalidate:true" commandName="allocForm">
				<table>
					<tr>
						<td id="div_text" align="right">分发单号：</td>
						<td id="div_invoiceAllotNumber"><input class="easyui-textbox" id="invoiceAllotNumber" name="invoiceAllotNumber" style="width: 120px;"></td>
						<td align="right">申请人：</td>
						<td><input class="easyui-textbox" id="invoiceAllotReqBy" name="invoiceAllotReqBy" style="width: 120px;"></td>
						<td align="right">分发状态：</td>
						<td><input class="easyui-textbox" id="approvalStatus" name="approvalStatus" style="width: 120px;"></td>
					</tr>
					<tr style="display: none">							
						<td><input id="edit_id" class="easyui-textbox" name="id" style="width: 200px"></input></td>
						<td><input id="details" class="easyui-textbox" name="details" style="width: 200px"></input></td>
					</tr>
				</table>
			</form>
		    <div style="width:100%;height:330px">
				<table class="easyui-datagrid" id="dg_L" style="width:100%;height:100%" data-options="					
						singleSelect:true,
						autoRowHeight:false,
						pagination:true,
						pageSize:10,
						remoteSort:false,
					    multiSort:true
						">			 
				</table>   
		    </div>
			<div id="alloc_dialog-buttons">
				<a id="saveButton" href="javascript:void(0)" class="easyui-linkbutton"	iconCls="icon-ok" onclick="save()"> 
					<spring:message	code="button.Save" /></a> 
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" 
					onclick="javascript:$('#alloc_dialog').dialog('close')"><spring:message code="button.Close" /></a>
			</div>  		
	</div>		     
</body>
<script type="text/javascript">
	var validListString = undefined;
	var editRow = undefined;
	var validListString = undefined;
	var usedNumber = 0;
    var legalEntityIdValue = null;
    var invoiceCategoryValue = null;
    var invoiceCodeValue = null;
    var invoiceCategoryColumn = null;
    var invoiceAllotQtyColumn = null;
    var invoiceAllotQtyValue = null;
    $(document).ready(function() {
	initCombobox();	
	$('#searchform').form('load', {
		pageNumber : $('#dg').datagrid('options').pageNumber,
		pageSize : $('#dg').datagrid('options').pageSize
	});
	search_h();
	});

	$.extend($.messager.defaults,{
	    ok:'<spring:message code="confirm"/>',
	    cancel:'<spring:message code="cancel"/>'
	});	

	function initCombobox(){
		$('#combobox_status').combobox({
			url:'${vat}/invoiceAlloc/loadDataDict.do',
			valueField:'id',
			textField:'text'
		});
	}
	
	$(function(){
		$('#dg').datagrid({
			url: '',			
			fitColumns: true,
			nowrap: false,
			pagination:true,
			rownumbers:true,
			singleSelect:true,
			fitColumns: true,
			striped: true,
			idField:'id', //主键字段  
			columns:[[
			    { field:'invoiceAllotNumber', title:'分发单号',width:200,align:'center'},
				{ field:'invoiceAllotReqBy', title:'申请人',width:200,align:'center'},
				{ field:'orgName', title:'申请部门',width:200,align:'center'},
				{ field:'invoiceAllotDate', title:'申请日期',width:150,align:'center',formatter:function(value){
					if(value){
						return myformatter(new Date(value));
					}
				}},
				{ field:'approvalStatus', title:'状态',width:200,align:'center'},
				{ field:'approvalDate', title:'分发日期',width:150,align:'center'},
			]],
		   toolbar:[{  
                 text:'新增分发单',  
                 id:'addtoolbar',
               iconCls:'icon-add',  
                handler:function(){  
                	add();  
                }  
             },'-',{  
                 text:'查看分发单',  
                 id:'viewtoolbar',
                iconCls:'icon-edit',  
                 handler:function(){  
                     edit();  
                 }  
             },'-',{  
                 text:'删除分发单',  
                 id:'deletetoolbar',
	             iconCls:'icon-remove',  
                 handler:function(){  
                     remove();  
                 } 
             },'-',{  
                 text:'审批分发单',  
                 id:'approvetoolbar',
                 iconCls:'icon-ok',  
                 handler:function(){  
                     approve();  
                 }  
             },'-'],  
			 onLoadSuccess:function(){  
				 $('#dg').datagrid('clearSelections')
			 },
			 onClickRow:function(rowIndex,rowData){
		            if (rowData.approvalDate != undefined && rowData.approvalDate != null && rowData.approvalDate != "") {
		            	$("#deletetoolbar").linkbutton('disable');
		        		$("#approvetoolbar").linkbutton('disable');		            	
		            }else{
		        		$("#deletetoolbar").linkbutton('enable');
		        		$("#approvetoolbar").linkbutton('enable');		
		            }
		     }
		});
		var edit_legalEntityId = undefined;
		var dg = undefined;
		$('#dg_L').datagrid({
			url: '',			
			fitColumns: true,
			nowrap: false,
			pagination:true,
			rownumbers:true,
			singleSelect:false,
			fitColumns: true,
			striped: true,
			idField:'id', //主键字段  
			columns:[[
						{field:'checkFlag',checkbox:true,width:80,align:'center'},
						{ field:'invoiceAllotHId', title:'invoiceAllotHId',hidden : true}, 
						{ field:'legalEntityId', title:'纳税人名称',width:100,align:'center',editor:{
	                        type:'combogrid',
	                        options:{
		                            required: true,
		                			panelWidth : 580,
		                			idField : 'id',  
		                			textField : 'legalEntityName', 
		                   		 	url:"${vat}/invoiceAlloc/loadLegalEntityForEditPanel.do",
		                			fitColumns : true,
		                			striped : true,
		                			editable : true,
		                			rownumbers : true, //序号
		                			collapsible : false, //是否可折叠的
		                			pagination : true, //是否分页
		                			queryParams: { pageNumber:1,pageSize:10},
		                			method : 'post',
		                			columns : [[{
		                				field : 'legalEntityCode',
		                				title : '纳税人编号',
		                				width : 13
		                			}, {
		                				field : 'legalEntityName',
		                				title : '纳税人名称',
		                				width : 20
		                			}]],
		                			toolbar : [
												{
													text : '纳税人编号&nbsp;<input type="text" id="querylegalEntityCode" style="width: 130px;"/>'
												}, '-',{
													
													text : '纳税人名称&nbsp;<input type="text" id="querylegalEntityName" style="width: 150px;"/>'
												}, '-', {
													text : "查询",
													iconCls : 'icon-search',
													handler : function() {
														$.post('${vat}/invoiceAlloc/loadLegalEntityForEditPanel.do', {
															legalEntityCode : $('#querylegalEntityCode').val(),
															legalEntityName : $('#querylegalEntityName').val(),
															pageNumber : 1,
															pageSize : 10
														}, function(result) {			
															if (result!=null) {
		                						        		dg.datagrid('loadData', result);
			                									dg.datagrid("loaded"); 	 
															} else {
																return;
															}
														}, 'json');											
													}
												}, '-', {
													text : "重置",
													iconCls : 'icon-remove',
													handler : function() {
														$('#querylegalEntityCode').val('');
														$('#querylegalEntityName').val('');
													}
												}, '-' ],
		                			onBeforeLoad:function(){
		                				var pager = $(this).datagrid('getPager');
		                				dg = $(this);
		                				if (pager) {
		                					$(pager).pagination({
		                						pageSize: 10,           
		                						beforePageText: '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
		                						afterPageText: '页',           
		                						displayMsg: '<spring:message code="pagination.displayMsg"/>',
		                						onSelectPage : function(pageNumber, pageSize) {
		                					    	var senddata = 'legalEntityCode='+$('#querylegalEntityCode').val()+'&legalEntityName='+$('#querylegalEntityName').val()+'&pageNumber='+pageNumber+'&pageSize='+pageSize;
		                						    $.ajax({
		                						        url: '${vat}/invoiceAlloc/loadLegalEntityForEditPanel.do',  
		                						        type: "POST",  
		                						        async: true,  
		                						        data: senddata,
		                						        dataType: "json",  
		                						        cache: false,  
		                						        success: function(result){
		                						        	if(result!=null){
		                						        		dg.datagrid('loadData', result);
			                									dg.datagrid("loaded"); 	     		
		                						        	}
		                						        }
		                						    });
		                						},
		                						onChangePageSize : function() {
		                						},
		                						onRefresh : function(pageNumber, pageSize) {
		                					    	var senddata = 'legalEntityCode='+$('#querylegalEntityCode').val()+'&legalEntityName='+$('#querylegalEntityName').val()+'&pageNumber='+pageNumber+'&pageSize='+pageSize;
		                						    $.ajax({
		                						        url: '${vat}/invoiceAlloc/loadLegalEntityForEditPanel.do',  
		                						        type: "POST",  
		                						        async: true,  
		                						        data: senddata,
		                						        dataType: "json",  
		                						        cache: false,  
		                						        success: function(result){
		                						        	if(result!=null){
		                						        		dg.datagrid('loadData', result);
			                									dg.datagrid("loaded"); 	
		                						        	}
		                						        }
		                						    });
		                						 }
		                					});
		                				}		                				
		                            },
		                            onSelect: function (rowIndex, rowData) {
		                            	 edit_legalEntityId = rowData.id;
             							if(edit_legalEntityId != undefined){
	   		         	                    var cellEdit = $('#dg_L').datagrid('getEditor', {index:editRow,field:'equipmentId'});
			        	                    cellEdit.target.combogrid('grid').datagrid('reload',"${vat}/invoiceAlloc/loadEquipmentForEditPanel.do?legalEntityId="+edit_legalEntityId+"&pageNumber=1&pageSize=10");
             							}
             					     	 legalEntityIdValue = rowData.id;
             					     	 
             		                	if(invoiceAllotQtyValue != undefined && invoiceAllotQtyValue != null && invoiceAllotQtyValue != ""){
	               		                   	 usedNumber = parseInt(usedNumber) - parseInt(invoiceAllotQtyValue);
	            			                 invoiceAllotQtyColumn.target.val("");
	            			                 invoiceAllotQtyValue = null;
             		                	}
             		                     var row = $('#dg_L').datagrid('getEditors', editRow);                       
			                 
            	                    	 if(invoiceCategoryValue !=null && invoiceCodeValue!=null){
            		 				    	var senddata = 'legalEntityId='+legalEntityIdValue+'&invoiceCategory='+invoiceCategoryValue+'&invoiceCode='+invoiceCodeValue;
            		 	                    var row = $('#dg_L').datagrid('getEditors', editRow);                       
           								
            						    	$.ajax({  
            							        url: '${vat}/invoiceAlloc/queryValidAllocNumber.do',  
            							        type: "POST",  
            							        async: true,  
            							        data: senddata,
            							        dataType: "json",  
            							        cache: false,  
            							        success: function(result){
            										if(result!=null){
        												var validList = result.validAlloc;
        												validListString = validList.join(",");
        												var validNum = validList.length-parseInt(usedNumber);
        												row[3].target.val(validNum);
        							                     var cellEditQty = $('#dg_L').datagrid('getEditor', {index:editRow,field:'invoiceAllotQty'});
        							                     var $inputQty = cellEditQty.target; // 得到文本框对象
        												if(validNum != undefined && validNum != null && validNum != 0){
        								                     $inputQty.prop('readonly',false); // 设值只读  
        												}else{
        								                     $inputQty.prop('readonly',true); // 设值只读  
        												}
        											}
            							        }
            							    });	                    		 
            	                    	 }                  	 
				    	            }
								}
	                    }}, 
	                    { field:'invoiceCategory', title:'发票种类',width:100,align:'center',editor:{
    						type:'combobox',
    						options:{
    							url:'${vat}/invoiceTrx/loadDataDict.do',
    							valueField:'value',
    							textField:'text',
    							panelHeight:'auto',
    							required:true,
    						     onSelect: function () {
//      		 				    	var senddata = 'legalEntityId='+legalEntityIdColumn.target.combogrid('getValue')+'&invoiceCategory='+
//      		 				    	invoiceCategoryColumn.target.combobox('getValue')+'&invoiceCode='+$(this).val();
          		                	if(invoiceAllotQtyValue != undefined && invoiceAllotQtyValue != null && invoiceAllotQtyValue != ""){
              		                   	 usedNumber = parseInt(usedNumber) - parseInt(invoiceAllotQtyValue);
           			                 	 invoiceAllotQtyColumn.target.val("");  
            			                 invoiceAllotQtyValue = null;
        		                	}					    	 
          		                     var row = $('#dg_L').datagrid('getEditors', editRow);                       
	    	 
  						    	 invoiceCategoryValue = invoiceCategoryColumn.target.combobox('getValue');
    		                     	 if(legalEntityIdValue !=null && invoiceCodeValue!=null){
    				 				    	var senddata = 'legalEntityId='+legalEntityIdValue+'&invoiceCategory='+invoiceCategoryValue+'&invoiceCode='+invoiceCodeValue;
    				 	                    var row = $('#dg_L').datagrid('getEditors', editRow);                       
									
    								    	$.ajax({  
    									        url: '${vat}/invoiceAlloc/queryValidAllocNumber.do',  
    									        type: "POST",  
    									        async: true,  
    									        data: senddata,
    									        dataType: "json",  
    									        cache: false,  
    									        success: function(result){
    												if(result!=null){
    													var validList = result.validAlloc;
    													validListString = validList.join(",");
    													var validNum = validList.length-parseInt(usedNumber);
    													row[3].target.val(validNum);
    								                     var cellEditQty = $('#dg_L').datagrid('getEditor', {index:editRow,field:'invoiceAllotQty'});
    								                     var $inputQty = cellEditQty.target; // 得到文本框对象
    													if(validNum != undefined && validNum != null && validNum != 0){
    									                     $inputQty.prop('readonly',false); // 设值只读  
    													}else{
    									                     $inputQty.prop('readonly',true); // 设值只读  
    													}
    												}
    									        }
    									    });	                    		 
    			                    	 }               	 
			    	            	}
    						}
    					}}, 
    					{ field:'invoiceCode', title:'发票代码',width:100,align:'center',editor:{type:'text', options: {required:true}}},
					    { field:'validAllocNumber', title:'可分发数量',width:100,align:'center',editor:'text'},
					    { field:'startInvoiceNumber', title:'起始发票号码',width:100,align:'center',editor:{type:'numberbox', options: {required:true}}},
						{ field:'invoiceAllotQty', title:'分发数量',width:80,align:'center',editor:{type:'text', options: {required:true}}},
						{ field:'equipmentId', title:'终端名称',width:100,align:'center',editor:{
	                        type:'combogrid',
	                        options:{
		                            required: true,
		                			panelWidth : 300,
		                			idField : 'id',  
		                			textField : 'equipmentName', 
// 		                   		 	url:"${vat}/invoiceAlloc/loadEquipmentForEditPanel.do?legalEntityId"+edit_legalEntityId,
		                			fitColumns : true,
		                			striped : true,
		                			editable : true,
		                			rownumbers : true, //序号
		                			collapsible : false, //是否可折叠的
		                			pagination : true, //是否分页
// 		                			queryParams: { pageNumber:1,pageSize:10},
		                			method : 'post',
		                			columns : [[{
		                				field : 'equipmentCode',
		                				title : '终端编号',
		                				width : 13
		                			}, {
		                				field : 'equipmentName',
		                				title : '终端名称',
		                				width : 20
		                			},{ 
		                				field:'equipmentManager', 
		                				title:'equipmentManager',
		                				hidden : true
		                			}]],
		                			onSelect: function (rowIndex, rowData) {
		       	                     var row = $('#dg_L').datagrid('getEditors', editRow);                       
		     	                     row[7].target.val(rowData.equipmentManager);
			    	                },
		                			onBeforeLoad:function(){
		                				var pager = $(this).datagrid('getPager');
		                				var dg = $(this);
		                				if (pager) {
		                					$(pager).pagination({
		                						pageSize: 10,           
		                						beforePageText: '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
		                						afterPageText: '页',           
		                						displayMsg: '<spring:message code="pagination.displayMsg"/>',
		                						onSelectPage : function(pageNumber, pageSize) {
		                							if(edit_legalEntityId != undefined){
			                					    	var senddata = 'legalEntityId='+edit_legalEntityId+'&pageNumber='+pageNumber+'&pageSize='+pageSize;
			                						    $.ajax({
			                						        url: '${vat}/invoiceAlloc/loadEquipmentForEditPanel.do',  
			                						        type: "POST",  
			                						        async: true,  
			                						        data: senddata,
			                						        dataType: "json",  
			                						        cache: false,  
			                						        success: function(result){
			                						        	if(result!=null){
			                						        		dg.datagrid('loadData', result);
				                									dg.datagrid("loaded"); 	     		
			                						        	}
			                						        }
			                						    });		                								
		                							}
		                						},
		                						onChangePageSize : function() {
		                						},
		                						onRefresh : function(pageNumber, pageSize) {
		                							if(edit_legalEntityId != undefined){
			                					    	var senddata = 'legalEntityId='+edit_legalEntityId+'&pageNumber='+pageNumber+'&pageSize='+pageSize;
			                						    $.ajax({
			                						        url: '${vat}/invoiceAlloc/loadEquipmentForEditPanel.do',  
			                						        type: "POST",  
			                						        async: true,  
			                						        data: senddata,
			                						        dataType: "json",  
			                						        cache: false,  
			                						        success: function(result){
			                						        	if(result!=null){
			                						        		dg.datagrid('loadData', result);
				                									dg.datagrid("loaded"); 	
			                						        	}
			                						        }
			                						    });	                								
		                							}
		                						 }
		                					});
		                				}		                				
		                            }
								}
	                    }}, 
	                    { field:'equipmentManager', title:'终端管理人员',width:100,align:'center',editor:'text'},
					]],
		   toolbar:[{
                 text:'新增', 
                 id:'addAllocToolbar',
                 iconCls:'icon-add',  
                 handler:function(){
					 editRow = undefined;
	               	 if (editRow != undefined) {
	                     $("#dg_L").datagrid('endEdit', editRow);
	                 }
	                 if (editRow == undefined) {
	                     $("#dg_L").datagrid('insertRow', {
	                         index: 0,
	                         row: {}
	                     });
	                     $("#dg_L").datagrid('beginEdit', 0);
	                     editRow = 0;

	                     var cellEdit = $('#dg_L').datagrid('getEditor', {index:editRow,field:'validAllocNumber'});
	                     var $input = cellEdit.target; // 得到文本框对象
	                     $input.prop('readonly',true); // 设值只读  
	            
   	                     var cellEdit2 = $('#dg_L').datagrid('getEditor', {index:editRow,field:'equipmentManager'});
	                     var $input2 = cellEdit2.target; // 得到文本框对象
	                     $input2.prop('readonly',true); // 设值只读  
	
	                     var cellEdit3 = $('#dg_L').datagrid('getEditor', {index:editRow,field:'legalEntityId'});
	                     var $input3 = cellEdit3.target; // 得到文本框对象
	                     $input3.prop('readonly',true); // 设值只读  

	                     var cellEditQty = $('#dg_L').datagrid('getEditor', {index:editRow,field:'invoiceAllotQty'});
	                     var $inputQty = cellEditQty.target; // 得到文本框对象
	                     $inputQty.prop('readonly',true); // 设值只读  

	                     var row = $('#dg_L').datagrid('getEditors', editRow);                       
	                     var legalEntityIdColumn = row[0];
	                     invoiceCategoryColumn = row[1];
	                     var invoiceCodeColumn = row[2];
	                     invoiceAllotQtyColumn = row[5];
	         
// 	                     invoiceAllotQtyColumn.target.bind("change", function(){  
// 	                    	 usedNumber = parseInt(usedNumber)+parseInt($(this).val());
//                       	 }); 
		                invoiceAllotQtyColumn.target.bind("focus", function(){  
		                	invoiceAllotQtyValue = $(this).val();
		                	if(invoiceAllotQtyValue != undefined && invoiceAllotQtyValue != null && invoiceAllotQtyValue != ""){
			                   	 usedNumber = parseInt(usedNumber) - parseInt(invoiceAllotQtyValue);
				                 invoiceAllotQtyColumn.target.val("");
    			                 invoiceAllotQtyValue = null;
					       	}
	                 	 }); 
	                    invoiceAllotQtyColumn.target.bind("change", function(){
	                    	invoiceAllotQtyValue = $(this).val();
		                	if(invoiceAllotQtyValue != undefined && invoiceAllotQtyValue != null && invoiceAllotQtyValue != ""){
			                   	 usedNumber = parseInt(usedNumber)+parseInt(invoiceAllotQtyValue);
		                	}
	                 	 });                  
	                     invoiceCodeColumn.target.bind("change", function(){
  		                	if(invoiceAllotQtyValue != undefined && invoiceAllotQtyValue != null && invoiceAllotQtyValue != ""){
      		                   	 usedNumber = parseInt(usedNumber) - parseInt(invoiceAllotQtyValue);
   			                 	 invoiceAllotQtyColumn.target.val("");    
    			                 invoiceAllotQtyValue = null;
		                	}
	                    	 invoiceCodeValue = $(this).val();
	                     	 if(legalEntityIdValue !=null && invoiceCategoryValue!=null){
			 				    	var senddata = 'legalEntityId='+legalEntityIdValue+'&invoiceCategory='+invoiceCategoryValue+'&invoiceCode='+invoiceCodeValue;
									
							    	$.ajax({  
								        url: '${vat}/invoiceAlloc/queryValidAllocNumber.do',  
								        type: "POST",  
								        async: true,  
								        data: senddata,
								        dataType: "json",  
								        cache: false,  
								        success: function(result){
											if(result!=null){
												var validList = result.validAlloc;
												validListString = validList.join(",");
												var validNum = validList.length-parseInt(usedNumber);
												row[3].target.val(validNum);
							                     var cellEditQty = $('#dg_L').datagrid('getEditor', {index:editRow,field:'invoiceAllotQty'});
							                     var $inputQty = cellEditQty.target; // 得到文本框对象
												if(validNum != undefined && validNum != null && validNum != 0){
								                     $inputQty.prop('readonly',false); // 设值只读  
												}else{
								                     $inputQty.prop('readonly',true); // 设值只读  
												}
								    		}
								        }
								    });                  	 
	                     	 }	
                         });  
	          		}
              	}
             },'-',{  
                 text:'删除',  
                 id:'delteAllocToolbar',
                iconCls:'icon-remove',  
                 handler:function(){  
                	 var row = $('#dg_L').datagrid('getSelected');
                	 if (row){
               	          var rowIndex = $('#dg_L').datagrid('getRowIndex', row);
 	                      var row = $('#dg_L').datagrid('getEditors', rowIndex);                       
 	                      usedNumber = parseInt(usedNumber) - parseInt(row[5].target.val());
               	          $('#dg_L').datagrid('deleteRow', rowIndex);  
                	 }
               	} 
             },'-'],  
			 onLoadSuccess:function(){  
				 $('#dg_L').datagrid('clearSelections')
			 },
			 onAfterEdit: function (rowIndex, rowData, changes) {
		            editRow = undefined;
		        },
		        onDblClickRow:function (rowIndex, rowData) {
		            if (editRow != undefined) {
		                $("#dg_L").datagrid('endEdit', editRow);
		            }
		 
		            if (editRow == undefined) {
		                $("#dg_L").datagrid('beginEdit', rowIndex);
		                editRow = rowIndex;
		            }
		            var cellEdit = $('#dg_L').datagrid('getEditor', {index:editRow,field:'validAllocNumber'});
                    var $input = cellEdit.target; // 得到文本框对象
                    $input.prop('readonly',true); // 设值只读  
           
	                var cellEdit2 = $('#dg_L').datagrid('getEditor', {index:editRow,field:'equipmentManager'});
                    var $input2 = cellEdit2.target; // 得到文本框对象
                    $input2.prop('readonly',true); // 设值只读  

                    var cellEdit3 = $('#dg_L').datagrid('getEditor', {index:editRow,field:'legalEntityId'});
                    var $input3 = cellEdit3.target; // 得到文本框对象
                    $input3.prop('readonly',true); // 设值只读  

                    var row = $('#dg_L').datagrid('getEditors', editRow);                       
                    var legalEntityIdColumn = row[0];
                    invoiceCategoryColumn = row[1];
                    var invoiceCodeColumn = row[2];
                    var invoiceAllotQtyColumn = row[5];
                  	
                    invoiceAllotQtyValue = invoiceAllotQtyColumn.target.val();
                	if(invoiceAllotQtyValue != undefined && invoiceAllotQtyValue != null && invoiceAllotQtyValue != ""){
                        usedNumber = parseInt(usedNumber) - parseInt(invoiceAllotQtyValue);
                	}

                    invoiceAllotQtyColumn.target.val("");
	                 invoiceAllotQtyValue = null;
                    
	                invoiceAllotQtyColumn.target.bind("focus", function(){  
	                	invoiceAllotQtyValue = $(this).val();
	                	if(invoiceAllotQtyValue != undefined && invoiceAllotQtyValue != null && invoiceAllotQtyValue != ""){
		                   	 usedNumber = parseInt(usedNumber) - parseInt(invoiceAllotQtyValue);
			                 invoiceAllotQtyColumn.target.val("");
			                 invoiceAllotQtyValue = null;
				       	}
                 	 }); 
                    invoiceAllotQtyColumn.target.bind("change", function(){
                    	invoiceAllotQtyValue = $(this).val();
	                	if(invoiceAllotQtyValue != undefined && invoiceAllotQtyValue != null && invoiceAllotQtyValue != ""){
		                   	 usedNumber = parseInt(usedNumber)+parseInt(invoiceAllotQtyValue);
	                	}
                 	 });                  
                    invoiceCodeColumn.target.bind("change", function(){
						if(invoiceAllotQtyValue != undefined && invoiceAllotQtyValue != null && invoiceAllotQtyValue != 0){
		                   	 usedNumber = parseInt(usedNumber) - parseInt(invoiceAllotQtyValue);
			                 invoiceAllotQtyColumn.target.val("");
			                 invoiceAllotQtyValue = null;
			            }

                   	 invoiceCodeValue = $(this).val();
                    	 if(legalEntityIdValue !=null && invoiceCategoryValue!=null){
		 				    	var senddata = 'legalEntityId='+legalEntityIdValue+'&invoiceCategory='+invoiceCategoryValue+'&invoiceCode='+invoiceCodeValue;
								
						    	$.ajax({  
							        url: '${vat}/invoiceAlloc/queryValidAllocNumber.do',  
							        type: "POST",  
							        async: true,  
							        data: senddata,
							        dataType: "json",  
							        cache: false,  
							        success: function(result){
										if(result!=null){
											var validList = result.validAlloc;
											validListString = validList.join(",");
											var validNum = validList.length-parseInt(usedNumber);
											row[3].target.val(validNum);
						                     var cellEditQty = $('#dg_L').datagrid('getEditor', {index:editRow,field:'invoiceAllotQty'});
						                     var $inputQty = cellEditQty.target; // 得到文本框对象
											if(validNum != undefined && validNum != null && validNum != 0){
							                     $inputQty.prop('readonly',false); // 设值只读  
											}else{
							                     $inputQty.prop('readonly',true); // 设值只读  
											}
							    		}
							        }
							    });                  	 
                    	 }	
                    });      
		        },
		        onClickRow:function(rowIndex,rowData){
		            if (editRow != undefined) {
		                $("#dg_L").datagrid('endEdit', editRow);
		            }
		        }
		});

		//设置分页控件	
		var p = $('#dg').datagrid('getPager'); 
		$(p).pagination({           
			pageSize: 10,//每页显示的记录条数，默认为10           
			beforePageText: '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
			afterPageText: '<spring:message code="pagination.afterPageText"/>',           
			displayMsg: '<spring:message code="pagination.displayMsg"/>',
			onSelectPage: function (pageNumber,pageSize) {//分页触发		
		    	 find(pageNumber,pageSize);
				 clearAllocForm();
	         }
	
		});
		//设置分页控件	
		var p = $('#dg_L').datagrid('getPager'); 
		$(p).pagination({           
			pageSize: 10,//每页显示的记录条数，默认为10           
			beforePageText: '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
			afterPageText: '<spring:message code="pagination.afterPageText"/>',           
			displayMsg: '<spring:message code="pagination.displayMsg"/>',
			onSelectPage: function (pageNumber,pageSize) {//分页触发		
		    	 find(pageNumber,pageSize);
				 clearAllocForm();
	         }
	
		});
	});
	$(function() {
		$('#alloc_dialog_validType').combobox({
			 onChange: function (n, o) {
					if('1'==n){
						$('#alloc_dialog_creditNumber').textbox('enable');
					}else if('2'==n){
						$('#alloc_dialog_customerCode').textbox('enable');
					}
		         }  
		 });
	});
    function search_h(){
    	$("#dg").datagrid("loading");
    	$('#searchform').form('submit', {
			url:'${vat}/invoiceAlloc/loadInvoiceAllocPage.do',		
			success : function(result) {
				if (result!=null) {
					var result = $.parseJSON(result);
					$("#dg").datagrid('loadData', result);
					$("#dg").datagrid("loaded"); 				
				}
		     }
		});

    }
  
    function search_L(){ 
   		$("#dg_L").datagrid("loading");
    	var idTmp = $("#edit_id").val();
    	var pagenumber = $('#dg_L').datagrid('options').pageNumber;
    	var pagesize = $('#dg_L').datagrid('options').pageSize;
    	
    	var senddata = 'invoiceAllotHId='+idTmp+'&pageNumber='+pagenumber+'&pageSize='+pagesize;
	
    	$.ajax({  
	        url: '${vat}/invoiceAlloc/loadInvoiceAllocLPage.do',  
	        type: "POST",  
	        async: true,  
	        data: senddata, //不能直接写成 {id:"123",code:"tomcat"} 
	        dataType: "json",  
	        cache: false,  
	        success: function(result){
				if (result!=null) {
					$("#dg_L").datagrid('loadData', result);
	 				$("#dg_L").datagrid("loaded"); 	
				}
	        }
	    });
    }
	function add(){
    	$("#saveButton").linkbutton('enable');
    	$("#addAllocToolbar").linkbutton('enable');
    	$("#delteAllocToolbar").linkbutton('enable');
    	
    	$('#div_text').hide();
    	$('#div_invoiceAllotNumber').hide();
    	
		clearAllocForm();
		$('#dg_L').datagrid('loadData', { total: 0, rows: [] })
    	 $('#alloc_dialog').dialog({
    	    onClose:function(){
    	    	clearValue();
    		}
    	});
		$("#alloc_dialog").dialog('open').dialog('setTitle',
		'新增');
		$("#alloc_dialog_Form").form('load', {invoiceAllotDate:myformatter(new Date())});
		var userCode = "<%=userCode%>";
		$("#invoiceAllotReqBy").textbox('setValue',userCode);
		$("#approvalStatus").textbox('setValue','草稿');
		$("#approvalStatus").textbox('readonly',true);
	}
	
	function edit(){
    	$("#saveButton").linkbutton('disable');
    	$("#addAllocToolbar").linkbutton('disable');
    	$("#delteAllocToolbar").linkbutton('disable');
    	$('#div_text').show();
    	$('#div_invoiceAllotNumber').show();
    	
		var row = $('#dg').datagrid('getSelections');
		if(row.length==0){
            $.messager.alert('提示',"请先选择一条记录",'info');  
            return;  
        } 
		if(row.length > 1){
           $.messager.alert('提示',"只能选择一条记录进行更新",'info');  
           return;  
        }
		var onerow = $('#dg').datagrid('getSelected');
		if(onerow.id!=''){				
			 clearAllocForm();			 		 			 
			 $('#dg_L').datagrid('loadData', {
					total : 0,
					rows : []
			 });
			 $("#invoiceAllotNumber").textbox('readonly',true);
			 $("#invoiceAllotReqBy").textbox('readonly',true);
			 $("#approvalStatus").textbox('readonly',true);
			 
			 $('#alloc_dialog_Form').form('load',{
				 invoiceAllotNumber:onerow.invoiceAllotNumber,
				 invoiceAllotReqBy:onerow.invoiceAllotReqBy,
				 approvalStatus:onerow.approvalStatus,
				 id:onerow.id
			 });  
			 search_L();
           	 $("#alloc_dialog").dialog('open').dialog('setTitle', '查看');
		}else{
    		$.messager.alert('<spring:message code="system.alert"/>','编辑的主键不能为空');
    	}	
	}	
	
	function approve(){
		var row = $('#dg').datagrid('getSelections');
		if(row.length==0){
           $.messager.alert('提示',"请先选择一条记录",'info');  
           return;  
       } 
		if(row.length > 1){  
          $.messager.alert('提示',"只能选择一条记录进行更新",'info');  
          return;  
       } 
		
		var onerow = $('#dg').datagrid('getSelected');
		$("#dg").datagrid("loading");
		if(onerow.id!=''){
			$.ajax({
                url:'${vat}/invoiceAlloc/approveById.do?id='+onerow.id,
                cache:false,
                dataType : "json",
                success : function(result) {
						$.messager.progress('close');
                        if (result.success) {	                        		                        	
                        	clearAllocForm(); 
        					$("#dg").datagrid("loaded");
        					search_h();
							$('#alloc_dialog').dialog('close');
                        }
                        $.messager.alert('<spring:message code="system.alert"/>',result.msg);
                }
            });	        
		}else{
	   		$.messager.alert('<spring:message code="system.alert"/>','编辑的主键不能为空');
	   	}		
	}
	
	function remove(){
		var row = $('#dg').datagrid('getSelected');	
		if(row!=null){
			var id = row.id;	
			var arguments = row.crvatInvoiceAllocNumber;
		    $.messager.confirm('<spring:message code="system.alert"/>','确认删除此请领单吗？',function(result){  
				  if (result){
				      $.ajax({
		                    url:"${vat}/invoiceAlloc/deleteAlloc.do?id="+id,
		                    dataType : "json",
		                    cache:false,
		                    success : function(result) {	                    	
		                       if (result.success) {	                        		                        	
		                        	search_h();
		        					clearAllocForm(); 
		                        }
		                        $.messager.alert('<spring:message code="system.alert"/>',result.msg);
		                    }
		                });
				     
				  }
			  });  		
		}else{
			$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="noselectrecord"/>');
		}
	}
	
	function remove_L(){
		var row = $('#dg_L').datagrid('getSelected');	
		if(row!=null){
			var id = row.id;	
			var arguments = row.crvatInvoiceAllocNumber;
		    $.messager.confirm('<spring:message code="system.alert"/>','确认删除此请领单明细数据吗？',function(result){  
				  if (result){
				      $.ajax({
		                    url:"${vat}/invoiceAlloc/deleteAllocL.do?id="+id,
		                    dataType : "json",
		                    cache:false,
		                    success : function(result) {	                    	
		                       if (result.success) {	                        		                        	
		                    	    search_L();
		                        	clearAlloc_LForm(); 
		                        }
		                        $.messager.alert('<spring:message code="system.alert"/>',result.msg);
		                    }
		                });
				     
				  }
			  });  		
		}else{
			$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="noselectrecord"/>');
		}
	}
	
	function save(){
		$('#dg_L').datagrid('acceptChanges');
		var rows = $("#dg_L").datagrid('getRows');
		for(var i=0; i<rows.length; i++){
			$('#dg_L').datagrid("endEdit", i);
		} 

        var validataData = JSON.stringify(rows);
        validataData = eval(validataData);
        var flag = 0;
        
        if(validataData.length == 0){
   			alert("请添加分发单明细!");
        }else{
    		for(var i=0; i<validataData.length; i++)  
    		{
		        var reg8=/^\d{8}$/;
		        var reg10=/^\d{10}$/;

    		   if(validataData[i].legalEntityId == undefined || validataData[i].invoiceCategory == undefined || validataData[i].invoiceCode == undefined ||validataData[i].invoiceAllotQty == ""
    					   || validataData[i].startInvoiceNumber == undefined || validataData[i].invoiceAllotQty == undefined|| validataData[i].equipmentId == undefined){
    			   alert("不能保存空数据!");
    			   flag = 0;
    			   break;
    		   }else if(!reg10.test($.trim(validataData[i].invoiceCode))){
    		       alert("发票代码必须为10位！");
    			   flag = 0;
    			   break;
    		   }else if(!reg8.test($.trim(validataData[i].startInvoiceNumber))){
    		       alert("起始发票号码必须为8位！");
    			   flag = 0;
    			   break;
    		   }else{
    				var validListTmp = validListString.split(",");
    				validListTmp = validListTmp.sort();
    	    		var startNumber = validataData[i].startInvoiceNumber;
    	    		var startPosition = validListTmp.indexOf(startNumber);
    	    		var quantity = validataData[i].invoiceAllotQty;
   	    			if(startPosition != -1){//起始发票在可用发票内
   	    				var lastNumber = parseInt(startNumber)+parseInt(quantity)-parseInt(1);
   	   	    			if(validListTmp.indexOf(lastNumber.toString()) == -1){//终止发票不在可用发票内
   	    	    			alert("终止发票号码不存在！");
   	    	    			flag = 0;
	   	      			    break;
   	   	    			}else{
   	   	    			   validListTmp.splice(startPosition,quantity);
   	      				   flag = 1;
   	   	    			}
   	    			}else{
    	    			alert("起始发票号码不存在！");
    	    			flag = 0;  				
   	      			    break;
 	    			}
    		   }
    		}
    		if(flag == 1){
   				$.messager.confirm("<spring:message code='client.datacommit.formsave.confirm.title'/>","<spring:message code='client.datacommit.formsave.confirm.text'/>",function(result){
					if (result){
						if($("#edit_id").val()){
		                    rows[0].invoiceAllotHId=$("#edit_id").val();  
		                    var rowstr = JSON.stringify(rows);
			                $.ajax({
						        url: '${vat}/invoiceAlloc/saveInvoiceAllocL.do',  
						        type: "POST",  
								contentType : 'application/json',
				        		async: true,  
						        data: rowstr,
						        dataType: "json",  
						        cache: false,  
						        success: function(result){
		    						$.messager.progress('close');
									if(result.status=='0'){	
										clearAllocForm();
										search_h();
										$('#alloc_dialog').dialog('close');
										$.messager.alert('<spring:message code="system.alert"/>','保存成功');
									} 	
						        }
						    });					
						}else{
							var rowdetail = $('#dg_L').datagrid('getRows');   							
							var rowstr = JSON.stringify(rowdetail);
							$('#alloc_dialog_Form').form('load',{
								details:rowstr    									
							});   								    						   								
							$("#alloc_dialog_Form").form('submit',{
								url:'${vat}/invoiceAlloc/saveInvoiceAllocH.do',	
								onSubmit : function() {
									$.messager.progress({title:'Please waiting',
											msg:'数据保存中。。。。'});
								},		
								success:function(result){
		    						$.messager.progress('close');
									clearAllocForm();
									search_h();
									$('#alloc_dialog').dialog('close');
									clearValue();
									$.messager.alert('<spring:message code="system.alert"/>','保存成功');
								} 
							});								
						}
					}
   				});
    		}    		
        }
	}
	
	function clearAllocForm(){
		$('#alloc_dialog_Form').form('clear');
	} 

	function clearAlloc_LForm(){
		$('#alloc_dialog_L_Form').form('clear');
	} 
	function clearSearchForm(){
		var pageNumber = $('#pageNumber').val();
		var pageSize = $('#pageSize').val()		
		$('#searchform').form('clear');
		$('#searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
	}
	
	function loadEntityData(pageNumber,pageSize){
    	var senddata = 'pageNumber='+pageNumber+'&pageSize='+pageSize;
	    $.ajax({
	        url: '${vat}/invoiceAlloc/loadLegalEntity.do',  
	        type: "POST",  
	        async: true,  
	        data: senddata,
	        dataType: "json",  
	        cache: false,  
	        success: function(result){
				if(result.status=='0'){
					$('#searchform_entity').combogrid("grid").datagrid('loadData', result.data);
				}else if(result.status=='1'){
					//$.messager.alert('<spring:message code="system.alert"/>',result.erroMessage,'info');
				}
				$('#searchform_entity').combogrid("grid").datagrid("loaded"); 	
	        }
	    });
	}
	
	function loadFormEntityData(pageNumber,pageSize){
    	var senddata = 'pageNumber='+pageNumber+'&pageSize='+pageSize;
	    $.ajax({
	        url: '${vat}/invoiceAlloc/loadLegalEntity.do',  
	        type: "POST",  
	        async: true,  
	        data: senddata,
	        dataType: "json",  
	        cache: false,  
	        success: function(result){
				if(result.status=='0'){
					$('#form_entity').combogrid("grid").datagrid('loadData', result.data);
				}else if(result.status=='1'){
					//$.messager.alert('<spring:message code="system.alert"/>',result.erroMessage,'info');
				}
				$('#form_entity').combogrid("grid").datagrid("loaded"); 	
	        }
	    });
	}
	function find(pageNumber,pageSize){
		$('#searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		search_h();
	}
	
	function clearValue(){
		usedNumber = 0;
		legalEntityIdValue = null;
		invoiceCategoryValue = null;
		invoiceCodeValue = null;
		invoiceCategoryColumn = null;
		invoiceAllotQtyColumn = null;
		invoiceAllotQtyValue = null;
	}
</script>
</html>