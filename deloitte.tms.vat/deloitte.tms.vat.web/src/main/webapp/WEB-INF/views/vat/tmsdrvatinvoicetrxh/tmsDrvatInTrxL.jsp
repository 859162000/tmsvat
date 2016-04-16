
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
		style="overflow: hidden; height: 30%;">
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
						singleSelect:true,
						autoRowHeight:false,
						pagination:true,
						pageSize:10,
						remoteSort:false,
					    multiSort:true	
						">
				</table>
			</div>
					
	   <div id="dlg" class="easyui-dialog"
			style="width: 400px; height: 260px;" closed="true"
			data-options="				
				buttons: [{
					text:'导出',
					iconCls:'icon-ok',
					handler:function(){
						exporttemplate();
					}
				},
				
				{
					text:'<spring:message code="button.Close"/>',
					iconCls:'icon-cancel',
					handler:function(){
						$('#dlg').dialog('close');
					}
				}]
			" >

			<div style="margin:60px 0;"></div>		
		
			<div style="margin-bottom:20px;">
		&nbsp;&nbsp;默认导出Template.xls文件到桌面.
		</div>
		
		<div >
			<div >&nbsp;&nbsp;导出文件路径: &nbsp;&nbsp;<input class="easyui-textbox" style="width:50%"  id="exportPath"  />
			</div>
		
		</div>
		
	
	
	</div>
	
	
	
	
	
	
	
	   <div id="dlg2" class="easyui-dialog"
			style="width: 500px; height: 260px;" closed="true"
			data-options="				
				buttons: [
				{
					text:'导入',
					iconCls:'icon-ok',
					handler:function(){
						importexcel();
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
		
		<div style="margin-bottom:20px;color: red;">
		&nbsp;&nbsp;请必须使用IE浏览器导入! 其它浏览器选中的文件路径可能会被系统屏蔽.
		</div>
			
		<div style="margin-bottom:20px">
			&nbsp;&nbsp;选取导入文件:&nbsp;&nbsp;
			<input class="easyui-filebox" name="importfile1"  id="importfile1" data-options="prompt:'选取excel文件...'" style="width:50%">
		</div>
		   			
		</div>	
	</div>
	
	
	
</body>
<script type="text/javascript">


function exporttemplate(){


var exportPath =$('#exportPath').textbox('getValue');
alert('will export to : '+exportPath);

$.post('${vat}/tmsDrvatInvoiceTrxL/exportTemplate.do', {

	exportPath : exportPath
 

}, function(result) {


 alert("result is:"+result);
 
// var result = $.parseJson(result);
 
 //alert("after json result is:"+result);
 
 if(result.success){
	// alert("导出模板成功");
	 
	 alert(result.msg);
 }



}, 'json');  



}






function checkImportFile(importfile1){
	
	
  if(	typeof(importfile1) == 'undefined' || $.trim( importfile1 ) ==''   ){
	  
	  return false;
  }
	
  return true;
}



function importexcel(){

	alert('will import template');
	
	
	var importfile1=$('#importfile1').filebox('getValue');
	
	
	if(!checkImportFile(importfile1)){
		
		alert('请选择要导入的Excel文件');
		return;
	}
	
	alert(importfile1);
	//todo validation importfile1
	

	 $.post('${vat}/tmsDrvatInvoiceTrxL/importExcel.do', {
		
		 importExcelName : importfile1
		 
		
	}, function(result) {

		
		 alert("result is:"+result);
		 
		// var result = $.parseJson(result);
		 
		 //alert("after json result is:"+result);
		 
		 if(result.success){
			 
			 callRefresh();
			 alert("导入数据成功");
			 
			// alert(result.msg);
		 }

		
	}, 'json');  

	 

	}



function callRefresh(){
	
		$('#topsearchform').form('load', {
			pageNumber : $('#invoicetrxldetail').datagrid('options').pageNumber,
			pageSize : $('#invoicetrxldetail').datagrid('options').pageSize
		}); 
 		
 		Search();

	
	
}

function addTo(){//导出模板
	
	
	$("#dlg").dialog('open').dialog('setTitle',	'导出模板');
	

}

function edit(){//导入excel
	

	$("#dlg2").dialog('open').dialog('setTitle','导入excel数据');
	
	}


	$(document).ready(function() {
			
			callRefresh();
	
	});
	
	$(function(){
	
		
		$('#invoicetrxldetail').datagrid({
			url: '',			
			fitColumns: true,
			nowrap: false,
			pagination:true,
			rownumbers:true,
			singleSelect:false,
			
			//striped: true,
			//idField:'id', //主键字段  ??????????? 
			columns:[[
			 //   { field:'id', title:'发票批次ID',width:100,align:'center'},
			    
			 //   { field:'invoiceTrxHId', title:'进项税发票登记ID',width:100,align:'center'},
				{ field:'invoiceCode', title:'发票代码',width:100,align:'center'}, 
				{ field:'invoiceNumber', title:'发票编码',width:100,align:'center'},
				{ field:'invoicingDate', title:'发票日期',width:100,align:'center'},
				{ field:'enteredAmount', title:'净额',width:100,align:'center'},				
				{ field:'vatAmount', title:'税额',width:100,align:'center'},
				{ field:'venderRegistrationNumber', title:'销方纳税人识别号',width:100,align:'center'},
				 
				
				
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
	
/* 	
	function initTrxL(){


	var pageNumber = $('#invoicetrxldetail').datagrid('options').pageNumber;
	var pageSize = $('#invoicetrxldetail').datagrid('options').pageSize;
	
	// tiger:            $.post('crvatInvoicePre/searchPre.do', {
		
	$.post('${vat}/tmsDrvatInvoiceTrxL/initTrxL.do', {
	
		pageNumber : pageNumber,
		pageSize : pageSize
	}, function(result) {
		
		alert("result is:"+result);
		alert(  'xx-->:'+ $('#invoicetrxldetail')   );
		
	
		$('#invoicetrxldetail').datagrid('loadData', result);									 				
	}, 'json');  

	} */
	
	
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
	
	
	function remove2(){
		var row = $('#invoicetrxldetail').datagrid('getSelected');	
		if(row!=null){
			var id = row.id;	
		//	var arguments = row.legalEntityName;
		    $.messager.confirm('<spring:message code="system.alert"/>','确认删除选中的记录吗?',function(result){  
				  if (result){  			      
				      $.ajax({
		                    url:"taxOrgMgt/delTaxOrg.do?id="+id,
		                    dataType : "json",
		                    cache:false,
		                    success : function(result) {	                    	
		             			 if (result.success) {	 
		             				 
		             				clearSaveForm();//needed??????????
		                        	Search();
		        					
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
	
</script>
</html>