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
		    			<td align="right">入库单号:</td>

		    			<td><input class="easyui-textbox" id="searform_crvatInvoiceTrxNumber" name="crvatInvoiceTrxNumber" style="width: 150px;"></td>

		    		    <td align="right">入库单状态:</td>
		    			<td>
							<input:select id="searchform_status" name="approvalStatus" value="$searchform_status" easyuiClass="easyui-combobox" easyuiStyle="width:150px;">
								<option value=""></option>
								<input:systemStatusOption parentCode="VAT_CR_INVOICE_REQUEST_STATUS"/>
							</input:select>
		    			</td>
		    			<td align="right">入库日期:</td>
		    			<td>
		    				 <input id="searchform_time" name="startDate" class="easyui-datebox" width="90" /></input>
		    			</td>
		    			<td>
		    				 至: <input id="searchform_endDate" name="endDate" class="easyui-datebox" width="90" />
		    			</td>
	    			</tr>
	    			<tr>
		    			<td>
		    	             <a href="#" id="searchbtn"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px;" onclick="search_h()">查询</a>
		    			</td>
		    			<td>
	    					<a href="#" class="easyui-linkbutton" style="width:80px" onclick="clearSearchForm()">清除</a>
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
		<div id="trx_dialog" class="easyui-dialog"
			style="width: 880px; height: 432px;" closed="true" buttons="#trx_dialog-buttons">
			<form id="trx_dialog_Form" class="easyui-form" method="post" data-options="novalidate:true" commandName="trxForm">
				<table>
					<tr>
						<td id="div_text" align="right">入库单号：</td>
						<td id="div_crvatInvoiceTrxNumber"><input class="easyui-textbox" id="edit_crvatInvoiceTrxNumber" name="crvatInvoiceTrxNumber" style="width: 120px;"></td>
						<td align="right">申请人：</td>
						<td><input class="easyui-textbox" id="requestionBy" name="requestionBy" style="width: 120px;"></td>
						<td align="right">入库状态：</td>
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
		</div>
			<div id="trx_dialog-buttons">
				<a id="saveButton" href="javascript:void(0)" class="easyui-linkbutton"	iconCls="icon-ok" onclick="save()"> 
					<spring:message	code="button.Save" /></a> 
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" 
					onclick="javascript:$('#trx_dialog').dialog('close')"><spring:message code="button.Close" /></a>
			</div>  		
	</div>		     
</body>
<script type="text/javascript">
	 var editRow = undefined;
     var startInvoiceNumberValue = null;
     var invoiceQtyValue = null;
     $(document).ready(function() {

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
					    { field:'crvatInvoiceTrxNumber', title:'入库单号',width:200,align:'center'},
						{ field:'requestionBy', title:'申请人',width:200,align:'center'},
						{ field:'orgName', title:'申请部门',width:200,align:'center'},
						{ field:'invoiceTrxDate', title:'申请日期',width:150,align:'center',formatter:function(value){
							if(value){
								return myformatter(new Date(value));
							}
						}},
						{ field:'approvalStatus', title:'状态',width:200,align:'center'},
						{ field:'approvalDate', title:'入库日期',width:150,align:'center',formatter:function(value){
							if(value){
								return myformatter(new Date(value));
							}
						}},
					]],
		   toolbar:[{  
                 text:'新增入库单',  
                 id:'addtoolbar',
                 iconCls:'icon-add',  
                 handler:function(){  
                	add();  
                }  
             },'-',{  
                 text:'查看入库单',  
                 id:'viewtoolbar',
                 iconCls:'icon-edit',  
                 handler:function(){  
                     edit();  
                 }  
             },'-',{  
                 text:'删除入库单',  
                 id:'deletetoolbar',
                 iconCls:'icon-remove',  
                 handler:function(){  
                     remove();  
                 } 
             },'-',{  
                 text:'审批入库单',  
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
		var dg = undefined;

		$('#dg_L').datagrid({
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
						{ field:'crvatInvoiceTrxHid', title:'crvatInvoiceTrxHid',hidden : true}, 
						{ field:'legalEntityId', title:'纳税人名称',width:160,align:'left',editor:{
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
													text : "清除",
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
    							required:true
    						}
    					}},
						{ field:'invoiceCode', title:'发票代码',width:100,align:'center',editor:{type:'numberbox', options: {required:true}}}, 
						{ field:'startInvoiceNumber', title:'起始发票号码',width:80,align:'center',editor:{type:'text', options: {required:true}}},
						{ field:'invoiceQty', title:'发票份数',width:100,align:'center',editor:{type:'text', options: {required:true}}},
						{ field:'endInvoiceNumber', title:'终止发票号码',width:80,align:'center',editor:{type:'text'}},
					]],
		   toolbar:[{  
                 text:'新增发票', 
                iconCls:'icon-add',  
                id:'addInvoiceToolbar',
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

	                     var cellEdit = $('#dg_L').datagrid('getEditor', {index:editRow,field:'endInvoiceNumber'});
	                     var $input = cellEdit.target; // 得到文本框对象
	                     $input.prop('readonly',true); // 设值只读                   
	                     
	                     var row = $('#dg_L').datagrid('getEditors', editRow);                       
	                     var startInvoiceNumberColumn = row[3];
	                     var invoiceQtyColumn = row[4];
	                     var endInvoiceNumberColumn = row[5];
	     
	                     startInvoiceNumberColumn.target.bind("change", function(){  
							 startInvoiceNumberValue = $(this).val();
	                    	 if(invoiceQtyValue != null){
								 row[5].target.val(parseInt(invoiceQtyValue)+parseInt($(this).val())-1);
	                    	 }
	                     }); 
	                     invoiceQtyColumn.target.bind("change", function(){  
	                    	 invoiceQtyValue = $(this).val();
	                    	 if(startInvoiceNumberValue != null){
								 row[5].target.val(parseInt(startInvoiceNumberValue)+parseInt($(this).val())-1);
	                    	 }
	                     }); 
	          		}
                } 
             },'-',{  
                 text:'删除发票',  
                 id:'deleteInvoiceToolbar',
                 iconCls:'icon-remove',  
                 handler:function(){  
                	 var row = $('#dg_L').datagrid('getSelected');
                	 if (row){
               	          var rowIndex = $('#dg_L').datagrid('getRowIndex', row);
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
                    var cellEdit = $('#dg_L').datagrid('getEditor', {index:editRow,field:'endInvoiceNumber'});
                    var $input = cellEdit.target; // 得到文本框对象
                    $input.prop('readonly',true); // 设值只读                   
                    
                    var row = $('#dg_L').datagrid('getEditors', editRow);                       
                    var startInvoiceNumberColumn = row[3];
                    var invoiceQtyColumn = row[4];
                    var endInvoiceNumberColumn = row[5];
    
                    startInvoiceNumberColumn.target.bind("change", function(){  
						 startInvoiceNumberValue = $(this).val();
	                   	 if(invoiceQtyValue != null){
							 row[5].target.val(parseInt(invoiceQtyValue)+parseInt($(this).val())-1);
                   	 	 }
	                }); 
                    invoiceQtyColumn.target.bind("change", function(){  
                   	 	invoiceQtyValue = $(this).val();
                   	 	if(startInvoiceNumberValue != null){
						 row[5].target.val(parseInt(startInvoiceNumberValue)+parseInt($(this).val())-1);
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
				 clearTrxForm();
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
				 clearTrxForm();
	         }
		});
	});
	$(function() {
		$('#trx_dialog_validType').combobox({
			 onChange: function (n, o) {
					if('1'==n){
						$('#trx_dialog_creditNumber').textbox('enable');
					}else if('2'==n){
						$('#trx_dialog_customerCode').textbox('enable');
					}
		         }  
		 });
	});
   function search_h(){
    	$("#dg").datagrid("loading");
    	$('#searchform').form('submit', {

			url:'${vat}/invoiceTrx/loadInvoiceTrxPage.do',		
			success : function(result) {
				var result = $.parseJSON(result);
				$("#dg").datagrid('loadData', result);
				$("#dg").datagrid("loaded"); 				
		     }
		});
    }
  
   function search_L(){ 
  		$("#dg_L").datagrid("loading");
  		
   	var idTmp = $("#edit_id").val();
   	var pagenumber = $('#dg_L').datagrid('options').pageNumber;
   	var pagesize = $('#dg_L').datagrid('options').pageSize;
   	
   	var senddata = 'crvatInvoiceTrxHid='+idTmp+'&pageNumber='+pagenumber+'&pageSize='+pagesize;
	
   	$.ajax({  
	        url: '${vat}/invoiceTrx/loadInvoiceTrxLPage.do',  
	        type: "POST",  
	        async: true,  
	        data: senddata, //不能直接写成 {id:"123",code:"tomcat"} 
	        dataType: "json",  
	        cache: false,  
	        success: function(result){
	        	if(result!=null){
					$("#dg_L").datagrid('loadData', result);
					$("#dg_L").datagrid("loaded"); 	
	        	}
	        }
	    });
   }
	function add(){
    	$("#saveButton").linkbutton('enable');
    	$("#addInvoiceToolbar").linkbutton('enable');
    	$("#deleteInvoiceToolbar").linkbutton('enable');
    	$('#div_text').hide();
    	$('#div_crvatInvoiceTrxNumber').hide();
		clearTrxForm();
		$('#dg_L').datagrid('loadData', { total: 0, rows: [] })
	    $('#trx_dialog').dialog({
    	    onClose:function(){
    			startInvoiceNumberValue = null;
    			invoiceQtyValue = null
    		}
    	});
		$("#trx_dialog").dialog('open').dialog('setTitle',
		'新增');

		var userCode = "<%=userCode%>";
		
		$("#requestionBy").textbox('setValue',userCode);
		$("#approvalStatus").textbox('setValue','草稿');
		$("#approvalStatus").textbox('readonly',true);
	}
	
	function edit(){
    	$("#saveButton").linkbutton('disable');
    	$("#addInvoiceToolbar").linkbutton('disable');
    	$("#deleteInvoiceToolbar").linkbutton('disable');
    	$('#div_text').show();
    	$('#div_crvatInvoiceTrxNumber').show();
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
			 clearTrxForm();			 		 			 
			 $('#dg_L').datagrid('loadData', {
					total : 0,
					rows : []
			 });
			 $("#edit_crvatInvoiceTrxNumber").textbox('readonly',true);
			 $("#requestionBy").textbox('readonly',true);
			 $("#approvalStatus").textbox('readonly',true);
				
			 $('#trx_dialog_Form').form('load',{
				 crvatInvoiceTrxNumber:onerow.crvatInvoiceTrxNumber,
				 requestionBy:onerow.requestionBy,
				 approvalStatus:onerow.approvalStatus,
				 id:onerow.id
			 });  
			 search_L();
           	 $("#trx_dialog").dialog('open').dialog('setTitle', '查看');
		}else{
			$.messager.alert('<spring:message code="system.alert"/>','编辑的主键不能为空');
		}
	}	

	function remove(){
		var row = $('#dg').datagrid('getSelected');	
		if(row!=null){
			var id = row.id;	
			var arguments = row.crvatInvoiceTrxNumber;
		    $.messager.confirm('<spring:message code="system.alert"/>','确认删除此入库单吗？',function(result){  
				  if (result){
				      $.ajax({
		                    url:"${vat}/invoiceTrx/deleteTrx.do?id="+id,
		                    dataType : "json",
		                    cache:false,
		                    success : function(result) {	                    	
		                       if (result.success) {	                        		                        	
		                        	search_h();
		        					clearTrxForm(); 
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

		var rowdetail = $('#dg_L').datagrid('getRows');  
        var validataData = JSON.stringify(rowdetail);
        validataData = eval(validataData);
     
        var flag = 0;
        
        if(validataData.length == 0){
   			alert("请添加入库单明细!");
        }else{
    		for(var i=0; i<validataData.length; i++)  
    		{  
		        var reg8=/^\d{8}$/;
		        var reg10=/^\d{10}$/;		       		        
		        if(validataData[i].legalEntityId == undefined || validataData[i].invoiceCategory == undefined || validataData[i].invoiceCode == undefined 
    					   || validataData[i].startInvoiceNumber == undefined || validataData[i].invoiceQty == undefined){
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
    			   flag = 1;
    		   }
    		}
    		if(flag == 1){
    				$.messager.confirm("<spring:message code='client.datacommit.formsave.confirm.title'/>","<spring:message code='client.datacommit.formsave.confirm.text'/>",function(result){
    						if (result){
    							if($("#edit_id").val()){
    			                    rows[0].crvatInvoiceTrxHid=$("#edit_id").val();  
    			                    var rowstr = JSON.stringify(rows);
    				                $.ajax({
    							        url: '${vat}/invoiceTrx/saveInvoiceTrxL.do',  
    							        type: "POST",  
    									contentType : 'application/json',
    					        		async: true,  
    							        data: rowstr,
    							        dataType: "json",  
    							        cache: false,  
    							        success: function(result){
    			    						$.messager.progress('close');
    										if(result.status=='0'){	
    											clearTrxForm();
    											search_h();
    											$('#trx_dialog').dialog('close');
    											$.messager.alert('<spring:message code="system.alert"/>','保存成功');
    										} 	
    							        }
    							    });					
    							}else{
    								var rowdetail = $('#dg_L').datagrid('getRows');   							
    								var rowstr = JSON.stringify(rowdetail);
    								$('#trx_dialog_Form').form('load',{
    									details:rowstr    									
    								});   								    						   								
    								$("#trx_dialog_Form").form('submit',{
    									url:'${vat}/invoiceTrx/saveInvoiceTrxH.do',	
    									onSubmit : function() {
    										$.messager.progress({title:'Please waiting',
    												msg:'数据保存中。。。。'});
    									},		
    									success:function(result){
    			    						$.messager.progress('close');
    										clearTrxForm();
											search_h();
											$('#trx_dialog').dialog('close');
    										$.messager.alert('<spring:message code="system.alert"/>','保存成功');
    									} 
    								});			
    							}
    						}
    					});
    		}      	
        }        
	}
	
	function save_L(){
		$('#invoiceCategoryD').val($('#editform_invoiceCategory').combobox('getValue'));
		var trx_dialog_L_Form=$('#trx_dialog_L_Form');
		if(trx_dialog_L_Form.form('enableValidation').form('validate')){
			$.messager.confirm("<spring:message code='client.datacommit.formsave.confirm.title'/>","<spring:message code='client.datacommit.formsave.confirm.text'/>",function(result){
				if (result){
					$('#trx_dialog_L_Form').form('submit',{

						url:'${vat}/invoiceTrx/saveInvoiceTrxL.do',	
						onSubmit : function() {
								$.messager.progress({title:'Please waiting',
									msg:'数据保存中。。。。'});
						},			
						success:function(result){
							$.messager.progress('close');
							var result = $.parseJSON(result);
							if(result.status=='0'){	
								clearTrx_LForm();
								search_L();
								$('#trx_dialog_L').dialog('close');
								$.messager.alert('<spring:message code="system.alert"/>','保存成功');
							}
						} 
					});
				}
			});
		}
	}
	
	function clearTrxForm(){
		$('#trx_dialog_Form').form('clear');
	} 

	function clearTrx_LForm(){
		$('#trx_dialog_L_Form').form('clear');
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
	
	function find(pageNumber,pageSize){
		$('#searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		search_h();
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
		if(onerow){
			$.ajax({
                url:'${vat}/invoiceTrx/approveById.do?id='+onerow.id,
                cache:false,
                dataType : "json",
                success : function(result) {
						$.messager.progress('close');
                        if (result.success) {	                        		                        	
        					clearTrxForm(); 
        					$("#dg").datagrid("loaded");
                        	search_h();
							$('#trx_dialog').dialog('close');
                        }
                        $.messager.alert('<spring:message code="system.alert"/>',result.msg);
                }
            });	        
		}else{
	   		$.messager.alert('<spring:message code="system.alert"/>','编辑的主键不能为空');
	   	}
	}
</script>
</html>