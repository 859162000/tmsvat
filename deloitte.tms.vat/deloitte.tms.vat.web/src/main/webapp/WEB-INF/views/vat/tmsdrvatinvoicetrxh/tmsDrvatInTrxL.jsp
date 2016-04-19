
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/common/global.jsp"%>
<meta charset="UTF-8">
</head>

	
 
 <body class="easyui-layout" id="layoutid" style="overflow-y: hidden"	scroll="no">

	<div region="north" split="true" border="false"
		style="overflow: hidden; height: 15%;">
		<div class="easyui-panel"
			style="width: 100%; height: 100%; background-color: #E0ECFF">


			<form id="topsearchform" method="post">
				<table cellpadding="5">
					<tr>
					
					<td>发票代码:</td>
						<td><input class="easyui-textbox" type="text"
							style="width: 150px;" name="invoiceCode"></input></td>
						<td>发票编号:</td>
						<td><input class="easyui-textbox" type="text"
							style="width: 150px;" name="invoiceNumber"></input></td>
						
						<td>
							<div style="text-align: center; padding: 10px">
								<a href="#" id="searchbtn" class="easyui-linkbutton"
									data-options="iconCls:'icon-search'" style="width: 80px"
									onclick="callRefresh()"><spring:message code="button.Search" /></a>
								<a href="#" class="easyui-linkbutton" style="width: 80px"
									onclick="clearSearchForm()"><spring:message
										code="button.Clear" /></a>
							</div>
						</td>
					</tr>
					<tr style="display: none">
						<td><input id="pageNumber" class="easyui-textbox" type="text"
							style="width: 0px;" name="pageNumber" value=""></input></td>
						<td><input id="pageSize" class="easyui-textbox" type="text"
							style="width: 0px;" name="pageSize" value=""></input></td>
					</tr>
				</table>
			</form>


		</div>




	</div>


	<div data-options="region:'center'" title="<spring:message code="进项发票列表"/>">

	
			<div style="width: 100%; height: 100%">
				<table class="easyui-datagrid" id="invoicetrxldetail" 
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
	   <div id="dlg2" class="easyui-dialog"
			style="width: 500px; height: 260px;" closed="true"
			data-options="				
				buttons: [
				{
					text:'导入',
					iconCls:'icon-ok',
					handler:function(){
						processImportexcel();
					}
				}
				,{
					text:'<spring:message code="button.Close"/>',
					iconCls:'icon-cancel',
					handler:function(){
						$('#dlg2').dialog('close');
					}
				}]
			" >
			<div style="margin:60px 0;"></div>	
			<form id="myfilesform" action="${vat}/tmsDrvatInvoiceTrxL/importExcel.do" method="post" enctype="multipart/form-data">		
				<div style="margin-bottom:20px">
					&nbsp;&nbsp;选取导入文件:&nbsp;&nbsp;
					<input class="easyui-filebox" name="myfiles"  id="myfiles" data-options="prompt:'选取excel文件...'" style="width:50%">
				</div>	
			</form>
		</div>	
	</div>
	
	
	
</body>
<script type="text/javascript">
function addTo(){//导出模板	
	window.location.href='${vat}/template/DrvatInTrxTemplate.xls';
}

function edit(){//导入excel
	$("#dlg2").dialog('open').dialog('setTitle','导入excel数据');
}

function processImportexcel(){
	
	var myfiles=$('#myfiles').filebox('getValue');
	if(!checkImportFile(myfiles)){
		alert('请选择要导入的Excel文件');
		return;
	}	
	
	$('#myfilesform').form('submit', {
		url:'${vat}/tmsDrvatInvoiceTrxL/importExcel.do',	
		onSubmit : function() {
			//return $(this).form('enableValidation').form('validate');
		},			
		success:function(result){
			
			try{
			
			var result = $.parseJSON(result);				
			
			$.messager.alert('<spring:message code="system.alert"/>',result.msg);
			
			}catch(exc){
				alert(exc);
			}
		} 
	});
	
	
	//$("#myfilesform").submit();
}


function checkImportFile(myfiles){
	if(typeof(myfiles) == 'undefined' || $.trim( myfiles ) ==''   ){
		return false;
	}
	return true;
}
function callRefresh(){
	$('#topsearchform').form('load', {
		pageNumber : $('#invoicetrxldetail').datagrid('options').pageNumber,
		pageSize : $('#invoicetrxldetail').datagrid('options').pageSize
	}); 
	Search();
}
$(document).ready(function() {
	callRefresh();
	$("#myfilesform")[0].reset();
});
	
$(function(){
	$('#invoicetrxldetail').datagrid({
		url: '',			
		fitColumns: true,
		nowrap: false,
		pagination:true,
		rownumbers:true,
		singleSelect:true,
		
		//striped: true,
		idField:'id', //主键字段  ??????????? 
		columns:[[
		 //   { field:'id', title:'发票批次ID',width:100,align:'center'},
		    
		 //   { field:'invoiceTrxHId', title:'进项税发票登记ID',width:100,align:'center'},
		 
		 	{ field:'attribute1', title:'验证日期',width:100,align:'center'}, 
			{ field:'invoiceCode', title:'发票代码',width:100,align:'center'}, 
			{ field:'invoiceNumber', title:'发票号码',width:100,align:'center'},
			{ field:'invoicingDate', title:'开票日期',width:100,align:'center'},
			{ field:'attribute6', title:'含税金额',width:100,align:'center'},				
			/* 
			{ field:'vatAmount', title:'税额',width:100,align:'center'},
			 */
			 { field:'attribute7', title:'税额',width:100,align:'center'},
			 
			{ field:'venderRegistrationNumber', title:'销方纳税人识别号',width:100,align:'center'},
			
			{ field:'venderName', title:'销方纳税人名称',width:100,align:'center'},
			
			{ field:'attribute4', title:'发票验证人',width:100,align:'center'},
			
			{ field:'invoiceAuthenticationStatus', title:'发票验证状态',width:100,align:'center'}
			 
			
			
		]],
	
		toolbar:[
	         {text:'导出模板',  
              	iconCls:'icon-add',  
              	handler:function(){  
              		addTo();  
              			}  
          		},'-',{  
               text:'导入excel数据',  
               iconCls:'icon-edit',  
               handler:function(){  
               	edit();
               }  
           },'-',{  
               text:'<spring:message code="button.Remove"/>',  
               iconCls:'icon-remove',  
               handler:function(){  
               	remove2();  
               }  
           },'-'],
 
	 	onLoadSuccess:function(){
			$('#invoicetrxldetail').datagrid('clearSelections')
		},
		onClickRow:function(index,data){
			var row = $('#invoicetrxldetail').datagrid('getSelected');
			if (row){
			//loadSaveFormData(row);
			}
		}
});
		
		
		
		//设置分页控件	
		var p = $('#invoicetrxldetail').datagrid('getPager');
		$(p).pagination(
						{
							pageSize : 10,//每页显示的记录条数，默认为10           
							//pageList: [10,20,30],//可以设置每页记录条数的列表           
							beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
							afterPageText : '<spring:message code="pagination.afterPageText"/>',
							displayMsg : '<spring:message code="pagination.displayMsg"/>',
							onSelectPage : function(pageNumber, pageSize) {//分页触发		
								find(pageNumber, pageSize);
								
							}
						});
	});
	
	
	

	function find(pageNumber, pageSize) {
		$('#topsearchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
	
		Search();
	}

	

	
	//清空dialog内容

	function clearSearchForm() {
		$('#topsearchform').form('clear');
	}
    function Search(){
    	
    	//loading效果
    	//$("#invoicetrxldetail").datagrid("loading");  
    	
    	$('#topsearchform').form('submit', {
			url:'${vat}/tmsDrvatInvoiceTrxL/searchTrxL.do',			
			
			//'${vat}/tmsDrvatInvoiceTrxL/initTrxL.do', 
			
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(result) {
			if(typeof(result)=='undefined' || result == null || result==''){	
				
				alert(result);
			}else{
			   var result = $.parseJSON(result);
		       $("#invoicetrxldetail").datagrid('loadData', result);		       
		       $("#invoicetrxldetail").datagrid("loaded"); 
				}
		     }
		});

    }
	
	var allsids=[];
	
	function remove2(){
		var row = $('#invoicetrxldetail').datagrid('getSelected');	
		if(row!=null){
			
			//var ids = allsids.join(',');
			var ids =row.id;
			
			//	alert(ids);
		//	var arguments = row.legalEntityName;
		    $.messager.confirm('<spring:message code="system.alert"/>','确认删除选中的记录吗?',function(result){  
				  if (result){  			      
				      $.ajax({
		                    url:"${vat}/tmsDrvatInvoiceTrxL/delTrxL.do?ids="+ids,
		                    dataType : "json",
		                    cache:false,
		                    success : function(result) {  
		                    	
		                    	//clearSaveForm();//needed??????????
	                        	Search();
		                    	
		                    	
		             			 if (result.success) {             				 
		             				 
		             				
		                        	$.messager.alert('<spring:message code="system.alert"/>','删除成功');
		                        }else{
		                        	$.messager.alert('<spring:message code="system.alert"/>','删除异常');
		                        }	             			 
		                        
		                    }
		                });
				     
				  }
					  
				  
			  });  		
		}else{
			$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="noselectrecord"/>');
		}
	}
	
	
</script>
</html>