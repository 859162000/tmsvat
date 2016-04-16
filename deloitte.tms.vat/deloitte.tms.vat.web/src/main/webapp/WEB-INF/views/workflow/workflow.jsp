<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/common/global.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<div class="easyui-tabs" style="width:100%;height:100%">
			<div title="待处理任务">
				<div style="width:100%;height:100%">
			        <table class="easyui-datagrid" id="workflowInit_dataGrid"						
						style="width: 100%; height: 100%"
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
			<div title="我处理的任务" >
				<div style="width:100%;height:100%">
			        <table class="easyui-datagrid" id="myworkflowInit_dataGrid"						
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
	<!--  src="invoicePreProcess/invoicePreProcessCheckInit.do" -->
	 <div id="workflow_dlg" class="easyui-dialog" style="width: 80%; height: 100%;" closed="true" buttons="#dlg-buttons" data-options="
			iconCls:'icon-save',
			onResize:function(){
				$(this).dialog('center');
			},
			onClose:function(){
				Search();
				SearchHistory();
		    }
			">			   			   			   		
		 <div class="easyui-panel" id="panel"  style="width:100%;height:100%; background-color: #E0ECFF">		
		    <iframe id="iframe" scrolling="auto" frameborder="0" src=""    style="width:100%;height:100%;"></iframe>
	    </div>				   			   						
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)"
					class="easyui-linkbutton" iconCls="icon-cancel"
					onclick="javascript:$('#workflow_dlg').dialog('close')"><spring:message
						code="button.Close" /></a>
	</div>		
	 
	 
</body>
<script type="text/javascript">

	$(document).ready(		
	    function() {				
		  Search();
		  SearchHistory();
	});
	
	/*  $('#workflow_dlg').dialog({
		onClose:function(){
			Search();
			SearchHistory();
		}		
	});  */
	function closedialog(){
		alert(1);
	}
	$(function() {
		$('#workflowInit_dataGrid')
				.datagrid(
						{
							url : '',
							fitColumns : true,
							nowrap : false,
							pagination : true,
							rownumbers : true,
							singleSelect : false,
							fitColumns : true,
							striped : true,
							idField : 'processInstanceId', //主键字段  
							columns : [ [
									{
										field : 'ck',
										checkbox : true,
										width : 2
									}, //显示复选框        
									{
										field : 'taskName',
										title : '任务名称',
										width : 80,
										align : 'center'
									},
									{
										field : 'description',
										title : '描述',
										width : 100,
										align : 'center'
									},
									{
										field : 'type',
										title : '任务类型',
										width : 50,
										align : 'center'
									},
									{
										field : 'state',
										title : '状态',
										width : 50,
										align : 'center'
									},
									{
										field : 'owner',
										title : '所有者',
										width : 80,
										align : 'center'
									},
									{
										field : 'createDate',
										title : '创建日期',
										width : 80,
										align : 'center'
									},
									{
										field : 'duedate',
										title : '过期日期',
										width : 80,
										align : 'center'
									}, ] ],
									
									toolbar:[
									         {text:'处理任务',  
						                	iconCls:'icon-add',  
						                	handler:function(){  
						                		executeTask();  
						                			}  
						            		},'-'],
						            onLoadSuccess:function(){  
						                $('#workflowInit_dataGrid').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
						            } 
					
						
						});
		//设置分页控件	
		var p = $('#workflowInit_dataGrid').datagrid('getPager');
		$(p).pagination(
						{
							pageSize : 10,//每页显示的记录条数，默认为10           
							//pageList: [10,20,30],//可以设置每页记录条数的列表           
							beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
							afterPageText : '<spring:message code="pagination.afterPageText"/>',
							displayMsg : '<spring:message code="pagination.displayMsg"/>',
							onSelectPage : function(pageNumber, pageSize) {//分页触发		
								$("#workflowInit_dataGrid").datagrid("loading");
								 $.post('${vat}/toDoTask/getTodoTask.do', {
									 pageNumber : pageNumber,
								     pageSize : pageSize
								 }, function(result) {
									//var result = $.parseJSON(result);
									$("#workflowInit_dataGrid").datagrid('loadData', result);
									$("#workflowInit_dataGrid").datagrid("loaded");				 				
								 }, 'json');  	
							}
						});
		
		
		
		$('#myworkflowInit_dataGrid')
		.datagrid(
				{
					url : '',
					fitColumns : true,
					nowrap : false,
					pagination : true,
					rownumbers : true,
					singleSelect : false,
					fitColumns : true,
					striped : true,
					idField : 'processInstanceId', //主键字段  
					columns : [ [							 
							{
								field : 'taskName',
								title : '任务名称',
								width : 80,
								align : 'center'
							},
							{
								field : 'description',
								title : '描述',
								width : 100,
								align : 'center'
							},
							{
								field : 'type',
								title : '领取人',
								width : 50,
								align : 'center'
							},							
							{
								field : 'createDate',
								title : '创建日期',
								width : 80,
								align : 'center'
							},
							{
								field : 'endDate',
								title : '完成日期',
								width : 80,
								align : 'center'
							}, ] ]
							
				});
           //设置分页控件	
			var mp = $('#myworkflowInit_dataGrid').datagrid('getPager');
			$(mp).pagination(
				{
					pageSize : 10,//每页显示的记录条数，默认为10           
					//pageList: [10,20,30],//可以设置每页记录条数的列表           
					beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
					afterPageText : '<spring:message code="pagination.afterPageText"/>',
					displayMsg : '<spring:message code="pagination.displayMsg"/>',
					onSelectPage : function(pageNumber, pageSize) {//分页触发		
						$("#myworkflowInit_dataGrid").datagrid("loading");
						 $.post('${vat}/toDoTask/loadhistory.do', {
							 pageNumber : pageNumber,
						     pageSize : pageSize
						 }, function(result) {
							//var result = $.parseJSON(result);
							$("#myworkflowInit_dataGrid").datagrid('loadData', result);
							$("#myworkflowInit_dataGrid").datagrid("loaded");				 				
						 }, 'json');	
					}
				});					
	});
	function Search() {	
		$("#workflowInit_dataGrid").datagrid("loading");
		 $.post('${vat}/toDoTask/getTodoTask.do', {
			 pageNumber : $('#workflowInit_dataGrid').datagrid('options').pageNumber,
		     pageSize : $('#workflowInit_dataGrid').datagrid('options').pageSize
		 }, function(result) {
			//var result = $.parseJSON(result);
			$("#workflowInit_dataGrid").datagrid('loadData', result);
			$("#workflowInit_dataGrid").datagrid("loaded");				 				
		 }, 'json');  											
	}
	function SearchHistory() {		
		$("#myworkflowInit_dataGrid").datagrid("loading");
		 $.post('${vat}/toDoTask/loadhistory.do', {
			 pageNumber : $('#myworkflowInit_dataGrid').datagrid('options').pageNumber,
		     pageSize : $('#myworkflowInit_dataGrid').datagrid('options').pageSize
		 }, function(result) {
			//var result = $.parseJSON(result);
			$("#myworkflowInit_dataGrid").datagrid('loadData', result);
			$("#myworkflowInit_dataGrid").datagrid("loaded");				 				
		 }, 'json');
	}
	
	
	function executeTask() {			
		var row=$('#workflowInit_dataGrid').datagrid('getSelected');	
		if(row){		
			if(row.url==null||row.url==""){
				$.ajax({
					url : "${vat}/toDoTask/executeTask.do",
					type : "POST",
					async : true,
					data : 'taskId='+row.id, //不能直接写成 {id:"123",code:"tomcat"}  
					dataType : "json",
					// contentType: "charset=utf-8",  
					cache : false,
					success : function(result) {
						if(result.success){
							$.messager.alert('提示信息','处理成功！');
						}
						
					}
				});
			}else{//"invoicePreProcess/invoicePreProcessCheckInit.do"
				$('#iframe')[0].src=row.url;		
				$("#workflow_dlg").dialog('open').dialog('setTitle',
				'任务处理');
				
			}							
		}else{			
			
			$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="noselectrecord"/>');
		}			
	}
	function executeTestTask() {
		$.ajax({
			url : "${vat}/toDoTask/executeTestTask.do",
			type : "POST",
			async : true, 
			dataType : "json",
			// contentType: "charset=utf-8",  
			cache : false,
			success : function(result) {
				$.messager.alert('提示信息','调用成功');
			}
		});
	}
		
</script>
</html>