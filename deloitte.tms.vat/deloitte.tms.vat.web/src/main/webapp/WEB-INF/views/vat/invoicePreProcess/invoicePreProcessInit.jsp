<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
	<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
		<div data-options="region:'center'" title=""border="false">
		<div class="easyui-layout" style="width: 100%; height: 100%;"border="false">
			<div data-options="region:'center',border:false" style="background-color: #E0ECFF">
				<form id="crvatInvoicePreProcess_searchform" method="post"  scroll="no">
			    	<input id="pageNumber" type="hidden" style="width:0px;" name="pageNumber" value=""/>
			    	<input id="pageSize"  type="hidden" style="width:0px;" name="pageSize" value=""/>
			    	</form>
			    <form id="crvatInvoicePreProcess_editform" method="post"  scroll="no">
			    	<input type='hidden'/>
			    	<input type='hidden' />
			    </form>
				<div style="width: 100%; height: 100%">
					<table class="easyui-datagrid" id="crvatInvoicePreProcess_dataGrid"
						title="待审核列表""
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
	$(document).ready(function(){
		pageDataSettingInit();
		//InitCombobox();	
		$('#crvatInvoicePreProcess_searchform').form('load', {
			pageNumber: $('#crvatInvoicePreProcess_dataGrid').datagrid('options').pageNumber,
			pageSize: $('#crvatInvoicePreProcess_dataGrid').datagrid('options').pageSize
		});
		crvatInvoicePreProcess_Search();
	});
	//页面表格绑定初始化
	function pageDataSettingInit(){
		$('#crvatInvoicePreProcess_dataGrid').datagrid({
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
			    { field:'crvatInvoicePreNumber', title:'准备单编号',width:150,align:'center'},
				{ field:'invoiceReqNum', title:'申请单编号',width:150,align:'center'}, 
				{ field:'customerNumber', title:'客户号',width:120,align:'center'},
				{ field:'customerName', title:'购方单位名称',width:100,align:'center'},
				{ field:'custRegistrationCode', title:'购方证件类型',width:80,align:'center'},
				{ field:'custRegistrationNumber', title:'购方证件号码',width:130,align:'center'},
				{ field:'requestDate', title:'申请日期',width:80,align:'center'},
				{ field:'requestPerson', title:'开票申请人',width:100,align:'center'},
				/* { field:'requestPerson11', title:'准备单受理人',width:80,align:'center'}, */
				{ field:'astatus', title:'受理状态',width:80,align:'center'},
				/* { field:'actualamount', title:'受理日期',width:80,align:'center'}, */
				{ field:'invoiceAmount', title:'合计金额',width:80,align:'center'},
				{ field:'acctdAmountCr', title:'实际开票金额',width:80,align:'center'}
			]],
		   toolbar:[{  
                 text:'审核通过',  
                iconCls:'icon-ok',  
                handler:function(){  
                	crvatInvoicePreProcessAgree();  
                }  
             },'-',{  
                 text:'退回',  
                 iconCls:'icon-cancel',  
                 handler:function(){  
                	 crvatInvoicePreProcessDisAgree();  
                 }  
             },'-',{  
                 text:'刷新',  
                 iconCls:'icon-reload',  
                 handler:function(){  
                	 crvatInvoicePreProcess_Search();  
                 }  
             }],  
			 onLoadSuccess:function(){  
				 $('#crvatInvoicePreProcess_dataGrid').datagrid('clearSelections');
			 },
			onClickRow:function(index,data){/* 
				var row = $('#dg').datagrid('getSelected');
				if (row){
					loadSaveFormData(row);
				} */
			}
		});
		//设置分页控件	
		var p = $('#crvatInvoicePreProcess_dataGrid').datagrid('getPager'); 
			$(p).pagination({           
				pageSize: 10,//每页显示的记录条数，默认为10           
				//pageList: [5,10,15,20],//可以设置每页记录条数的列表           
				beforePageText: '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
				afterPageText: '<spring:message code="pagination.afterPageText"/>',           
				displayMsg: '<spring:message code="pagination.displayMsg"/>',
				onSelectPage: function (pageNumber, pageSize) {//分页触发		
					 crvatInvoicePreProcess_find(pageNumber,pageSize);
					 crvatInvoicePreProcess_Search(); 
					 crvatInvoicePreProcess_clearForm();
		         }
		});
	}
	function crvatInvoicePreProcess_find(pageNumber,pageSize){
		$('#crvatInvoicePreProcess_searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		crvatInvoicePreProcess_Search();
	}
	function crvatInvoicePreProcess_Search(){
    	$("#crvatInvoicePreProcess_dataGrid").datagrid("loading");
    	$('#crvatInvoicePreProcess_searchform').form('submit', {
			url:'${vat}/invoicePreProcess/invoicePreProcessList.do',			
			onSubmit : function(){
				return $(this).form('enableValidation').form('validate');
			},
			success:function(result){
				var result = $.parseJSON(result);
				if(result.status=='0'){
				     $("#crvatInvoicePreProcess_dataGrid").datagrid('loadData', result.data);
				}else if(result.status=='1'){
					$.messager.alert('<spring:message code="system.alert"/>',result.erroMessage,'info');
				}
				$("#crvatInvoicePreProcess_dataGrid").datagrid("loaded"); 
		     }
		});
    }
	
	function crvatInvoicePreProcessDisAgree(){
		var row_list=$('#crvatInvoicePreProcess_dataGrid').datagrid('getChecked');
		if(row_list!=''&&row_list!='undefined'){
			if(row_list.length<1){  
                $.messager.alert('提示',"请选择记录",'info');  
                return false;  
            } 
		$.messager.confirm("提示","确认提交？",function(result){ 
			if (result){
				
				//var row=$('#clientManageInit_dataGrid').datagrid('getSelected');
				if(row_list){
					var urlString="";
					$.each(row_list,function(index,item){ 
		                   if(index==0)   
		                	   urlString += "id="+item.id;  
		                    else  
		                    	urlString += "&id="+item.id;  
		                });
		        	if(urlString!=''){
		        		$.ajax({  
		        	        url: "${vat}/invoicePreProcess/invoicePreProcessDisAgree.do",  
		        	        type: "POST",  
		        	        async: true,  
		        	        data: urlString, //不能直接写成 {id:"123",code:"tomcat"}  
		        	        dataType: "json",  
		        	        cache: false,  
		        	        success: function(result){
		        	        	if(result.status=='0'){
		        	        		$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="system.delete.success.text"/>');
		        	        	}else{
		        	        		$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="system.delete.fail.text"/>');
		        	        	}
		        	        	crvatInvoicePreProcess_Search();
		        	        }
		        	    });
		        	}
		        }
			}
			  
        }); 
		}
	}
	
	function crvatInvoicePreProcessAgree(){
		var row_list=$('#crvatInvoicePreProcess_dataGrid').datagrid('getChecked');
		if(row_list!=''&&row_list!='undefined'){
			if(row_list.length<1){  
                $.messager.alert('提示',"请选择记录",'info');  
                return false;  
            } 
		$.messager.confirm("提示","确认提交？",function(result){ 
			if (result){
				//var row=$('#clientManageInit_dataGrid').datagrid('getSelected');
				if(row_list){
					var urlString="";
					$.each(row_list,function(index,item){ 
		                   if(index==0)   
		                	   urlString += "id="+item.id;  
		                    else  
		                    	urlString += "&id="+item.id;  
		                });
		        	if(urlString!=''){
		        		$.ajax({  
		        	        url: "${vat}/invoicePreProcess/invoicePreProcessAgree.do",  
		        	        type: "POST",  
		        	        async: true,  
		        	        data: urlString, //不能直接写成 {id:"123",code:"tomcat"}  
		        	        dataType: "json",  
		        	        cache: false,  
		        	        success: function(result){
		        	        	if(result.status=='0'){
		        	        		$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="system.delete.success.text"/>');
		        	        	}else{
		        	        		$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="system.delete.fail.text"/>');
		        	        	}
		        	        	crvatInvoicePreProcess_Search();
		        	        }
		        	    });
		        	}
		        }
			}
			  
        }); 
		}
	}
	//清空dialog内容
	
	function crvatInvoicePreProcess_clearForm(){
		$('#crvatInvoicePreProcess_editform').form('clear');
	}
</script>
</html>