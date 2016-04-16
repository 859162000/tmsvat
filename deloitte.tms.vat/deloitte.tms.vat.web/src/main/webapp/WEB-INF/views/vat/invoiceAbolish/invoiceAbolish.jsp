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
    <div region="north" split="true" border="false" style="overflow: hidden; height: 23%;">  
        <div class="easyui-panel" title="查找条件" style="width:100%; background-color: #E0ECFF">		
		    <form id="searchForm" method="post"  scroll="no">
		    	<table cellpadding="2" style="width: 1000px; height: 100%"   >
		    		<tr>
	    				<td>作废类型</td>
		    			<td>
							<input:select id="searchform_abolishType" name="abolishType" value="$searchform_abolishType" easyuiClass="easyui-combobox" easyuiStyle="width:150px;">
							            <option value=""></option>
							            <input:systemStatusOption parentCode="VAT_CR_INVOICE_DISCARD_TYPE"/>
							</input:select>
		    			</td>
		    			<td>发票种类</td>
		    			<td>
							<input:select id="searchform_invoiceCategory" name="invoiceCategory" value="$searchform_invoiceCategory" easyuiClass="easyui-combobox" easyuiStyle="width:150px;">
							            <option value=""></option>
							            <input:systemStatusOption parentCode="VAT_CR_INVOICE_TYPE"/>
							</input:select>
		    			</td>
		    			<td>发票代码</td>
		    			<td>
		    				<input id="searchform_invoiceCode" class="easyui-textbox" name="invoiceCode" style="width: 150px;">
		    			</td>
		    			<td>发票号码</td>
		    			<td>
		    				<input id="searchform_invoiceNumber" class="easyui-textbox" name="invoiceNumber" style="width: 150px;">
		    			</td>
		    		</tr>
		    		<tr>
		    			<td>购方证件类型</td>
		    			<td>
		    				<input id="searchform_custRegistrationCode" class="easyui-textbox" name="custRegistrationCode" style="width: 150px;">
		    			</td>
		    			<td>购方证件号码</td>
		    			<td>
		    				<input id="searchform_custRegistrationNumber" class="easyui-textbox" name="custRegistrationNumber" style="width: 150px;">
		    			</td>
		    			<td>开票日期</td>
		    			<td>
		    				<input class="easyui-datebox" style="width: 90px" name="startDate" data-options="formatter:myformatter,parser:myparser,required:false"/>
		    				<spring:message code='invoiceAbolish.fromTo'/>
		    				<input class="easyui-datebox" style="width: 90px" name="endDate" data-options="formatter:myformatter,parser:myparser,required:false"/>
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
		<div id="abolish_dialog" class="easyui-dialog" style="width: 300px; height: 232px;" closed="true" buttons="#abolish_dialog-buttons">
			<form id="abolish_dialog_Form" class="easyui-form" method="post" data-options="novalidate:true" commandName="abolishForm">
				<table>
					<tr>
						<td align="right">作废类型：</td>
						<td><input class="easyui-textbox" id="abolishTypeText" name="abolishTypeText" style="width: 120px;"></td>
					</tr>
    				<tr>
	    				<td>作废原因：</td>
		    			<td>
							<input:select id="abolishReason" name="abolishReason" value="$abolishReason" easyuiClass="easyui-combobox" easyuiStyle="width:120px;">
					            <option value=""></option>
					            <input:systemStatusOption parentCode="VAT_CR_INVOICE_ABOLISH_REASON"/>
							</input:select>
		    			</td>
					</tr>
					<tr>
						<td align="right">作废申请日期：</td>
						<td><input class="easyui-textbox" id="abolishDate" name="abolishDate" style="width: 120px;"></td>
					</tr>
					<tr>
						<td align="right">作废发票代码：</td>
						<td><input class="easyui-textbox" id="invoiceCode" name="invoiceCode" style="width: 120px;"></td>
					</tr>
					<tr>						
					<td align="right">作废发票号码：</td>
						<td><input class="easyui-textbox" id="invoiceNumber" name="invoiceNumber" style="width: 120px;"></td>
					</tr>
					<tr>						
					<td align="right">确认发票号码：</td>
	    				<td>
		    				<input class="easyui-textbox" type="text" id="invoiceNumber2" style="width: 120px;" name="invoiceNumber2" 
		    					missingMessage=<spring:message code='com.vat.base.model.customer.validate.text'/>
		    					data-options="required:true" />
	    				</td>
					</tr>
					<tr style="display: none">							
						<td><input id="inventoryInvoiceId" class="easyui-textbox" name="inventoryInvoiceId"></input></td>
						<td><input class="easyui-textbox" id="requestBy" name="requestBy"></td>
						<td><input class="easyui-textbox" id="abolishType" name="abolishType"></td>
					</tr>
				</table>
			</form>
		</div>
			<div id="abolish_dialog-buttons">
				<a id="saveButton" href="javascript:void(0)" class="easyui-linkbutton"	iconCls="icon-ok" onclick="save()"> 
					<spring:message	code="button.Save" /></a> 
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" 
					onclick="javascript:$('#abolish_dialog').dialog('close')"><spring:message code="button.Close" /></a>
			</div>  		
	</div>	     	         
  </body>  
<script type="text/javascript">
	$(document).ready(function(){
		pageInit();
		///InitCombobox();	
		$('#searchForm').form('load', {
			pageNumber: $('#dg').datagrid('options').pageNumber,
			pageSize: $('#dg').datagrid('options').pageSize
		});
		search_h();
	});
	
	function search_h(){
    	$("#dg").datagrid("loading");
    	$('#searchForm').form('submit', {
			url:'${vat}/invoiceAbolish/loadInvoiceAbolishPage.do',			
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success:function(result) {
			   var result = $.parseJSON(result);			 
		       $("#dg").datagrid('loadData', result);		       
		       $("#dg").datagrid("loaded"); 
		     }
		});
    }
	
	//页面表格绑定初始化
	function pageInit(){
		$('#dg').datagrid({
            //method:'post',  
            //iconCls:'icon-edit', //图标  
            //height:360, //高度  
            //queryParams:{}, //查询条件 
            //title:'用户列表', //标题
			url: '',
			loadMsg:"<spring:message code='system.datagrid.dataloding.msg'/>",
			striped:true,//奇偶行颜色不同
			singleSelect:true, //多选
			collapsible:false,//可折叠  
			fitColumns: false,//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			nowrap: false,
			iconCls: 'icon icon-list',
			pagination:true, //显示分页 
			rownumbers:true, //显示行号
			//sortName: 'id', //排序的列  
            //sortOrder: 'desc', //倒序 
            //remoteSort: true, //服务器端排序  
			idField:'id',//主键字段
			frozenColumns:[[
							{field:'checkFlag',checkbox:true,width:80,align:'center'},
							{field:'id',hidden:true,width:80,align:'center'},
							{field:'status',hidden:true,width:80,align:'center'},
							{field:'invoiceAbolishId',hidden:true,width:80,align:'center'},
						]],
			columns:[[
				 {field:'invoiceCategory',title:"发票种类",width:120,align:'center'},
				 {field:'invoiceCode',title:"发票代码",width:80,align:'center'},
				 {field:'invoiceNumber',title:"发票号码",width:80,align:'center'},
				 {field:'customerName',title:"购方证件类型",width:80,align:'center'},
				 {field:'custRegistrationNumber',title:"购方证件号码",width:80,align:'center'},
				 {field:'invoiceLimitAmount',title:"发票限额",width:80,align:'center'},
				 {field:'acctdAmountCR',title:"发票净额",width:80,align:'center'},
				 {field:'vatAmount',title:"发票税额",width:80,align:'center'},
				 {field:'invoiceAmount',title:"价税合计",width:80,align:'center'},
				 {field:'invoicePrintDate',title:"开具日期",width:80,align:'center'},
				 {field:'approvalDate',title:"处理日期",width:80,align:'center'},
				 {field:'requestBy',title:"申请人",width:80,align:'center'},
				 {field:'abolishType',title:"作废类型",width:80,align:'center'},
				 {field:'abolishReason',title:"作废原因",width:80,align:'center'},
				 {field:'approvalStatus',title:"审批状态",width:80,align:'center'}
			]],
			toolbar:[
			         {text:"作废",  
                	  iconCls:'icon-no',  
 	                 id:'abolishtoolbar',
                	  handler:function(){  
                		 abolish();  
                	  }  
            		},'-',{  
            			text:"审批",  
	                iconCls:'icon-ok',  
	                 id:'approvetoolbar',
	                handler:function(){  
	                	approve();
	                }  
	            },'-'],
	            
	            onDblClickRow:function(index,data){
					alert("双击表格显示详情功能正在建设中。。。。");
			},
			onLoadSuccess:function(){  
                $('#dg').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
            },			
			onClickRow:function(rowIndex,rowData){
	            if (rowData.approvalStatus == undefined || rowData.approvalStatus == null || rowData.approvalStatus == "") {
	        		$("#abolishtoolbar").linkbutton('enable');		            	
	        		$("#approvetoolbar").linkbutton('disable');		            	
	            }else{
	        		$("#abolishtoolbar").linkbutton('disable');
	                if (rowData.approvalStatus == '草稿') {
		        		$("#approvetoolbar").linkbutton('enable');		            	
		            }else{
		        		$("#approvetoolbar").linkbutton('disable');		            	
		            }
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
			onSelectPage: function (pageNumber, pageSize) {//分页触发		
		    	 find(pageNumber,pageSize);
	         }
		});
	}
	
	function abolish(){
		var row = $('#dg').datagrid('getSelections');
		if(row.length==0){
            $.messager.alert('提示',"请先选择一条记录",'info');  
            return;  
        } 
		if(row.length > 1){
           $.messager.alert('提示',"只能选择一条记录作废",'info');  
           return;  
        } 
		var onerow = $('#dg').datagrid('getSelected');
		if(onerow.id!=''){
			clearAbolishForm();
			$("#abolish_dialog").dialog('open').dialog('setTitle',
			'作废');
			$("#abolish_dialog_Form").form('load', {abolishDate:myformatter(new Date())});
			var userCode = "<%=userCode%>";
			$('#inventoryInvoiceId').textbox('setValue',onerow.id);
			$("#requestBy").textbox('setValue',userCode);
			//1	已打印发票作废        2	空白发票作废
			$("#abolishTypeText").textbox('readonly',true);        	
			if(onerow.status == 3 || onerow.status == 4){
				$("#abolishType").textbox('setValue','1');
				$("#abolishTypeText").textbox('setValue','已打印发票作废');
			}else{
				$("#abolishType").textbox('setValue','2');
				$("#abolishTypeText").textbox('setValue','空白发票作废');
			}
			$("#abolishDate").textbox('readonly',true);        	
			$("#invoiceCode").textbox('setValue',onerow.invoiceCode);
			$("#invoiceCode").textbox('readonly',true);        	
			$("#invoiceNumber").textbox('setValue',onerow.invoiceNumber);
			$("#invoiceNumber").textbox('readonly',true);        	
    	}else{
    		$.messager.alert('<spring:message code="system.alert"/>','编辑的主键不能为空');
    	}
	}
	
	function save(){
		var abolish_dialog_Form=$('#abolish_dialog_Form');
		if(abolish_dialog_Form.form('enableValidation').form('validate')){
			if($('#invoiceNumber2').val() != $('#invoiceNumber').val()){
 		       alert("确认发票号码与作废发票号码不一致！");
 		    }
			else{
				$.messager.confirm("<spring:message code='client.datacommit.formsave.confirm.title'/>","<spring:message code='client.datacommit.formsave.confirm.text'/>",function(result){
					if (result){
						$('#abolish_dialog_Form').form('submit',{
							url:'${vat}/invoiceAbolish/saveInvoiceAbolish.do',	
							onSubmit : function() {
									$.messager.progress({title:'Please waiting',
										msg:'数据保存中。。。。'});
							},			
							success:function(result){
	    						$.messager.progress('close');
								var result = $.parseJSON(result);
								if(result.status=='0'){	
									clearAbolishForm();
									search_h();
									$('#abolish_dialog').dialog('close');
									$.messager.alert('<spring:message code="system.alert"/>','保存成功');
								}
							} 
						});
					}
				});				
			}
		}
	}
	function approve(){
		var row = $('#dg').datagrid('getSelections');
		if(row.length==0){
           $.messager.alert('提示',"请选择你要审批的数据",'info');  
           return;  
       } 
		if(row.length > 1){  
          $.messager.alert('提示',"只能选择一条记录进行审批",'info');  
          return;  
       }
		var onerow = $('#dg').datagrid('getSelected');
		$("#dg").datagrid("loading");
	
		if(onerow.invoiceAbolishId!=''){
			$.ajax({
                url:'${vat}/invoiceAbolish/approveById.do?id='+onerow.invoiceAbolishId,
                cache:false,
                dataType : "json",
                success : function(result) {
						$.messager.progress('close');
                        if (result.success != undefined) {	                        		                        	
                        	clearAbolishForm(); 
                        	search_h();
							$('#abolish_dialog').dialog('close');
                            $.messager.alert('<spring:message code="system.alert"/>',result.msg);
                        }else{
                            $.messager.alert('<spring:message code="system.alert"/>',result.erroMessage);
                           	clearAbolishForm(); 
                        	search_h();
							$('#abolish_dialog').dialog('close');
                      }
                }
            });	        	
	   	}else{
	   		$.messager.alert('<spring:message code="system.alert"/>','编辑的主键不能为空');
	   	}
	}	
	function clearAbolishForm(){
		$('#abolish_dialog').form('clear');
	}	
	function clearSearchForm(){
		var pageNumber = $('#pageNumber').val();
		var pageSize = $('#pageSize').val()		
		$('#searchForm').form('clear');
		$('#searchForm').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});		
	}	
	function find(pageNumber,pageSize){
		$('#searchForm').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		search_h();
	}
</script>
</html>