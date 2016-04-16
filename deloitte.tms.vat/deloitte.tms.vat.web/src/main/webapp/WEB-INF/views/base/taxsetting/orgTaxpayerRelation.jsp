<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<!--  
  纳税主体与组织关系页面
 @see com.deloitte.tms.base.masterdata.model
 @author JasonPu
 /-->
<html>
<head>
<%@ include file="/common/global.jsp"%>
<%@ taglib uri="/tags/input" prefix="input"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">

	<div title="<spring:message code="orgTaxpayerRelation.search"></spring:message>" data-options="region: 'north',border:false" style="width: 100%; height: 29%;background-color: #E0ECFF">
		<form id="orgTaxpayer_searchform" method="post">
					<table>
						<tr>
							<td>组织代码:</td>
							<%-- 
							<td><input id="orgcodeCombogrid" class="easyui-combogrid"  style="width:150px;" name="orgCode" ></input></td>
							 --%>
							 <td><input id="orgcodeCombogrid"  class="easyui-textbox"  style="width:150px;" name="orgCode" ></input></td>
							 
							 
							<td><spring:message code="orgTaxpayerRelation.orgName"></spring:message>:</td>
							<td><input id="orgNameText" class="easyui-textbox" style="width:150px;" name="orgName" ></input></td>
							</tr>
							<tr>
							<td>纳税人识别码:</td>
													 
							 <td><input id="legalEntityCombogrid"  class="easyui-textbox"  style="width:150px;" name="registrationNumber" ></input></td>
							 
							
							<td><spring:message code="orgTaxpayerRelation.taxpayerName"></spring:message>:</td>
							<td><input id="legalEntityNameText" class="easyui-textbox" style="width:150px;" name="legalEntityName" ></td>
							<td><a href="#"
								id="searchbtn" class="easyui-linkbutton"
								data-options="iconCls:'icon-search'" style="width: 80px"
								onclick="searchForm2()"><spring:message
										code='client.search.button.find' /></a>
							</td>
							<td>
								<div style="text-align: center; padding: 10px">
									<a href="#" class="easyui-linkbutton" style="width: 100px"
										onclick="clearSearchForm()"><spring:message
										code='重置' /></a> 
								</div>
							</td>
						</tr>
						<tr style="display: none">
						
						   <%-- 
						   <td><input id="legalEntityId" type="text" style="width:0px;" name="legalEntityId"></td>
						    --%>
			    		   <td><input id="pageNumber" class="easyui-textbox"  style="width:50px;" name="pageNumber" value="1"></input></td>
			    		   <td><input id="pageSize" class="easyui-textbox"  style="width:50px;" name="pageSize" value="10"></input></td>
			    		</tr>
					</table>
			</form>
	</div>
    <div data-options="region:'center',border:false" style="background-color: #E0ECFF">  
	    <div style="width:100%;height:100%">
	         <table class="easyui-datagrid" id="dg" title="<spring:message code="orgTaxpayerRelation.info"/>" style="width:100%;height:100%" data-options="					
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
			style="width: 800px; height: 300px;" closed="true"
			data-options="				
				buttons: [{
					text:'<spring:message code="button.Save"/>',
					iconCls:'icon-ok',
					handler:function(){
						save();
					}
				},{
					text:'<spring:message code="button.Close"/>',
					iconCls:'icon-cancel',
					handler:function(){
						$('#dlg').dialog('close');
					}
				}]
			">

			<div style="margin:20px 0;"></div>			
				    <form id="saveform" method="post"><!-- commandName="TmsMdOrgLegalEntityInParam" -->
				    	<table cellpadding="10">
				    		<tr>			   		
				    			<td><spring:message code="orgTaxpayerRelation.orgName"/>:</td>
		 		    			<td>
		 		    				  
		 		  <!-- 
		 		  <ul id="setorgtree"></ul>  
		 		   -->
		 		   <select class="easyui-combotree"  id="setorgtree" name="orgName" data-options="cascadeCheck:false" style="width:160px;" required='true' ></select>
		
   					
		 		  			 
		 		    			</td>
		 		    			<!-- data-options="url:'orgTaxpayerRelation/orgsearch.do',valueField:'value',textField:'text'" -->
				    		
				    		
				    		<td><spring:message code="orgTaxpayerRelation.level"/>:</td>
				    			<td>
				    			
				    			<%-- <input id="dlg_legalEntityType" class="easyui-textbox" style="width:100px;"  name="legalEntityType"  required='true'></input>
				    			 --%>
				    	    <select id="dlg_legalEntityType" name="legalEntityType" class="easyui-combobox" style="width:160px"  required='true'>
		    					<option value="1" >一般增值税纳税人</option>
		    					<option value="2"  >小规模增值税纳税人</option>
		    					<option value="3"  >非增值税纳税人</option>
		    				</select>
				    			
				    			</td>
				    		</tr>
				    		<tr>			   		
				    			<td><spring:message code="orgTaxpayerRelation.taxpayerName"/>:</td>  <!--   纳税人名称 -->
				    			<td>
				    			
				    			<input id="dlg_taxpayerName" class="easyui-combogrid" style="width:160px;" name="legalEntityName"   required='true'></input>
				    			
				    			</td>
				    			
				    			<%-- 
				    				<td><spring:message code="orgTaxpayerRelation.taxpayerID"/>:</td>
				    			
				    			
				    			<td style="display: none;"><input id="dlg_legalEntityId" class="easyui-textbox"  style="width:150px;" name="legalEntityId"></input></td>
				    			 --%>
				    			
				    			
				    			<%--  <td><input  class="easyui-textbox"  style="width:150px;" name="registrationNumber"></input></td>
				    			  --%>
				    			<td><spring:message code="orgTaxpayerRelation.openStatus"/>:</td>
				    			<td>
			    				<select id="saveform_openStatus" name="enabledFlag" class="easyui-combobox" style="width:40px"  required='true'>
		    					<option value="1" >是</option>
		    					<option value="0"  selected ="selected">否</option>
		    				</select>
			    			</td>
				    			<%-- <td><input class="easyui-textbox" type="text" style="width:100px;" name="openStatus"></input></td> --%>
				    		</tr>
				    		
				    		<tr style="display: none">							
							
							
								<td>           
									legalEntityId:<input id="dla_legalEntityId" class="easyui-textbox" name="legalEntityId"
									style="width: 30px"></input>
								</td>
								<td>id:<input id="dla_id" class="easyui-textbox" name="id"
									style="width: 30px"></input></td>
								<td>
									orgId:<input id="dla_orgId" class="easyui-textbox" name="orgId"
									style="width: 30px"></input>
								</td>
							</tr>	
							
							<tr style="display: none">
							<td></td><td></td><td><input type="button"  onclick = 'test()'/></td>
							
							</tr> 					    		
				    			    				    			 	    	
		    	       </table>
				    </form>			   			
		</div>

    </div>

    
   
</body>
<script type="text/javascript">
	$.extend($.messager.defaults,{
	    ok:'<spring:message code="confirm"/>',
	    cancel:'<spring:message code="cancel"/>'
	});	

	var commonPageNumber=1;
	var dlg_taxpayerNamePageSize=10;

	$(function(){
		
		//alert('function() for dg');
		
		$('#dg').datagrid({//初始化数据表格
			url: '',			
			fitColumns: true,
			nowrap: false,
			pagination:true,
			rownumbers:true,		
			columns:[[
				{field:'ck',checkbox:true,width:2}, //显示复选框
				{field:'orgCode',title:'组织代码',width:120,align:'left'},//组织代码
				{field:'orgName',title:'组织名称',width:120,align:'left'},//组织名称
				{field:'registrationNumber',title:'纳税人识别号/统一社会信用代码',width:150,align:'center'},//纳税人识别码
				{field:'legalEntityName',title:'<spring:message code="orgTaxpayerRelation.taxpayerName"/>',width:120,align:'left'},//纳税人名称
				{field:'legalEntityTypeName',title:'<spring:message code="orgTaxpayerRelation.level"/>',width:120,align:'center'},//级别
				{field:'enabledFlag',title:'<spring:message code="orgTaxpayerRelation.openStatus"/>',width:120,align:'center',formatter:formatIsUsed},//是否启用
			]],
			toolbar:[//工具栏
			         {text:'<spring:message code="button.Add"/>',  
                	iconCls:'icon-add',  
                	handler:function(){  
                		addTo();  
                			}  
            		},'-',{  
	                text:'<spring:message code="button.Remove"/>',  
	                iconCls:'icon-remove',  
	                handler:function(){  
	                	remove2();  
	                }  
	            },'-',{  
	                 text:'<spring:message code="button.Edit"/>',  
	                 iconCls:'icon-edit',  
	                 handler:function(){  
	                	 edit();
	                 }  
	             },'-'],
			
			onClickRow:function(index,data){//点击行事件
				var row = $('#dg').datagrid('getSelected');
				if (row){
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
				
				//alert('in dg pageination:'+pageNumber +':'+pageSize);
		    	 find(pageNumber,pageSize);
				 Search(); 
				// clearSaveForm();
	         }
	
		});
	
	});
	


	$(document).ready(function() {
		
		callRefresh();
	});
	
	 function callRefresh(){
		 /* 
			$('#orgTaxpayer_searchform').form('load', {
				pageNumber : $('#dg').datagrid('options').pageNumber,
				pageSize : $('#dg').datagrid('options').pageSize
			});
			 */
		 Search();
		 initOrgTree();
		 searchLegalFreeInPop(commonPageNumber, dlg_taxpayerNamePageSize);
	 }
	
	/*
	*legalEntity级联查询
	**/
	
	
	/*
	*级联查询完
	**/
	function findLegalEntityPage (pageNumber,pageSize){
		$('#legalEntityCombogrid').combogrid('loadData', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
	}
	
	/*
	*org级联查询
	**/	
	 function initOrgTree(){
		 
		 	var mainRow = $('#dg').datagrid('getSelected');
		 	var relationKeyId='';
		 	
		 	//if(wantAddNewRel){
		 	
		 	if(mainRow){
		 		relationKeyId=mainRow.id;
		 		//alert(relationKeyId);
		 	}
		 	//}
		 	
		 	
			$('#setorgtree').combotree({				
			
					url : 'orgTaxpayerRelation/getOrgTree.do?relationKeyId='+relationKeyId,			
					animate : true,
					//checkbox : true,
					cascadeCheck : false, 
					onClick : function(node) {
													
					},
					
					onSelect : function(node){
						
						if(node){
							$('#dla_orgId').textbox('setValue', node.id);
						}
					}
			});
	 }
	
	
	function searchLegalFreeInPop(pageNumber, pageSize){
		
		$.post('orgTaxpayerRelation/dialoglegalEntitysearch.do', {
			
			
			registrationNumber : $('#digloglegalEntityCode').val(),
			legalEntityName : $('#digloglegalEntityName').val(),
			pageNumber : pageNumber,
			pageSize : pageSize
		}, function(result) {			
			if (result.success) {
				
				//$.messager.alert('<spring:message code="system.alert"/>',result.msg);
				$('#dlg_taxpayerName').combogrid('grid').datagrid('loadData', result);
			} else {
				return;
			}
		}, 'json');
		
	}
	 
 
	 function test(){
		 
		 var xx=[];
		 xx.push('a');
		 xx.push('b');
		 alert(xx);
		var tt= $('#setorgtree').combotree('getValues');
		
		try{
		$('#setorgtree').combotree().setValues();
		
		}catch(e){
			//alert(e);
		}
		$('#setorgtree').combotree('setText','');
		//var tt= $('#setorgtree').combotree('setValues', xx);
		//vat ss = tt.joins(',');
		//alert(tt.length+":"+tt);
				 
	 }
	 

	/*
	*dlg框内legalEntity级联查询
	**/
	$(function() {
		$('#dlg_taxpayerName').combogrid({
							//panelWidth : 500,
							panelWidth : 800,
							//idField : 'legalEntityName', //ID字段 
							idField : 'id',
							textField : 'legalEntityName', //显示的字段  
							url : "",
							fitColumns : true,
							striped : true,
							editable : false,
							pagination : true, //是否分页
							rownumbers : true, //序号
							collapsible : false, //是否可折叠的
							fit : false, //自动大小
							method : 'post',
							columns : [ [ {
								field : 'id',
								title : '纳税人ID',
								width : 10,
								hidden : true
							},{
								field : 'registrationNumber',
								title : '纳税人识别码',
								width : 100
							}, {
								field : 'legalEntityName',
								title : '纳税人名称',
								width : 100
							}   ] ],
							toolbar : [
										{
											text : '纳税人识别号&nbsp;<input type="text" id="digloglegalEntityCode"/>'
										}, '-',{
											
											text : '纳税人名称&nbsp;<input type="text" id="digloglegalEntityName"/>'
										}, '-', {
											text : "查询",
											iconCls : 'icon-search',
											handler : function() {
												//alert('hello');
												findDialogLegalEntity();
											}
										}, '-', {
											text : "重置",
											iconCls : 'icon-remove',
											handler : function() {
												
												$('#digloglegalEntityCode').val('');
												$('#digloglegalEntityName').val('');
											}
										}, '-' ],
							
							 onSelect : function(index, record) {
								 
								// alert('onselect legal entity record in saveform');
								 if(record){
									 //alert('methord way 1');
									 $('#dla_legalEntityId').textbox('setValue', record.id); 
									 
								 }else if($('#dlg_taxpayerName').combogrid('getSelected') ){
									 //alert('methord way 2');
									 $('#dla_legalEntityId').textbox('setValue', $('#dlg_taxpayerName').combogrid('getSelected').id);
								 }else{
									 alert('获取选中纳税实体信息异常');
								 }
								 
								 
							} 
						});
		
		
		//设置分页控件	
		var namepager = $('#dlg_taxpayerName').combogrid('grid').datagrid('getPager'); 
		$(namepager).pagination({           
			pageSize: 10,//每页显示的记录条数，默认为10           
			//pageList: [5,10,15,20],//可以设置每页记录条数的列表           
			beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
			
			afterPageText : '<spring:message code="pagination.afterPageText"/>',
			displayMsg : '<spring:message code="pagination.displayMsg"/>',
			
			onSelectPage: function (pageNumber, pageSize) {//分页触发
				
				dlg_taxpayerNamePageSize=pageSize;
				searchLegalFreeInPop(pageNumber, pageSize);
				
	         }
	
		});
			
		
	});
	
	/*
	*级联查询完
	**/
	
	function find(pageNumber,pageSize){
		$('#orgTaxpayer_searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		
		
	}
	function findOrg(){//主页面组织下拉框
		var orgCode = $('#littleOrgName').val();
		$.ajax({
			type : "POST",
			url : "orgTaxpayerRelation/orgsearch.do?orgCode="+orgCode,
			success : function(result) {
				var object = $.parseJSON(result);
				$('#orgcodeCombogrid').combogrid('grid').datagrid(
						'loadData', object); 
			}
		}); 
	}
	function findDialogOrg(){//弹出框组织下拉框
		
		return;
		var orgCode = $('#dialogOrg').val();
		$.ajax({
			type : "POST",
			url : "orgTaxpayerRelation/dialogOrgsearchN.do?orgCode="+orgCode,
			success : function(result) {
				var object = $.parseJSON(result);
				$('#dlg_orgName').combogrid('grid').datagrid(
						'loadData', object); 
			}
		}); 
	}
	function findLegalEntity(){//主页面纳税人下拉框
		var legalEntityCode = $('#littlelegalEntityCode').val();
		$.ajax({
			type : "POST",
			url : "orgTaxpayerRelation/legalEntitysearch.do?legalEntityCode="+legalEntityCode,
			success : function(result) {					
				 var object = $.parseJSON(result);				
				$('#legalEntityCombogrid').combogrid('grid').datagrid('loadData', object);
			}
		});
	}
	function findDialogLegalEntity(){//弹出框纳税人识别码下拉框

	
	var pageNumber=$('#dlg_taxpayerName').combogrid('grid').datagrid('options').pageNumber;
	
	var pageSize=$('#dlg_taxpayerName').combogrid('grid').datagrid('options').pageSize;
	
	//alert("pageNumber:"+pageNumber+";pageSize"+pageSize);
	
		$.post('orgTaxpayerRelation/dialoglegalEntitysearch.do', {
			
			
			registrationNumber : $('#digloglegalEntityCode').val(),
			legalEntityName : $('#digloglegalEntityName').val(),
			pageNumber : pageNumber,
			pageSize : pageSize
		}, function(result) {			
			if (result.success) {
				
				//$.messager.alert('<spring:message code="system.alert"/>',result.msg);
				$('#dlg_taxpayerName').combogrid('grid').datagrid('loadData', result);
			} else {
				return;
			}
		}, 'json');
		
		

	}
	
	//中间主页面的搜索
    function Search(){ //搜索
		
	//	alert('Search():'+ $('#dg').datagrid('options').pageNumber+':'+$('#dg').datagrid('options').pageSize);
    
	//	alert('Search() correct in search form:'+ $('#pageNumber') +':'+  $('#pageSize') );
    	$("#dg").datagrid("loading");
    
/* 		$('#orgTaxpayer_searchform').form('load', {
			pageNumber : $('#dg').datagrid('options').pageNumber,
			pageSize : $('#dg').datagrid('options').pageSize
		}); */
		
	
    	$('#orgTaxpayer_searchform').form('submit', {
			url:'orgTaxpayerRelation/searchLegalEnility.do',			
			onSubmit : function() {
				//alert("sub");
				return $(this).form('enableValidation').form('validate');
			},
			success : function(result) {
			   var result = $.parseJSON(result);
		       $("#dg").datagrid('loadData', result);		       
		       $("#dg").datagrid("loaded"); 
		     }
		});

    }
	
    function searchForm2(){ //from表单
    	$("#dg").datagrid("loading");
    
    	$('#orgTaxpayer_searchform').form('load', {
    		
			pageNumber : 1			
		});
    
    /* this is for pagenumber 1, pagesize 10, but remark will use previous setting
    maybe previous user set it in pager option, need use this
    
    .........just pageNumber use 1, pageSize use previous
    	$('#orgTaxpayer_searchform').form('load', {
			pageNumber : 1,
			pageSize : $('#dg').datagrid('options').pageSize
		});
 */
    	$('#orgTaxpayer_searchform').form('submit', {//调教from表单
			url:'orgTaxpayerRelation/searchLegalEnility.do',			
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(result) {
			   var result = $.parseJSON(result);
		       $("#dg").datagrid('loadData', result);
		       
		       
				var p = $('#dg').datagrid('getPager'); 
				$(p).pagination({ 
					
					pageNumber: 1 
				});
		       
		       
		       
		       
		       $("#dg").datagrid("loaded"); 
		     }
		});

    }
	
    function searchForm(){/*  
    	$("#dg").datagrid("loading");
    	$('#orgTaxpayer_searchform').form('load', {
			pageNumber : 1,
			pageSize : $('#dg').datagrid('options').pageSize
		});
    	var pager = $('#dg').datagrid('getPager');
    	if (pager) {
			$(pager)
					.pagination(
							{
								pageSize: $('#dg').datagrid('options').pageSize,//每页显示的记录条数，默认为10           
								//pageList: [5,10,15,20],//可以设置每页记录条数的列表           
								pageNumber: 1
								
							});
		}
    	$('#orgTaxpayer_searchform').form('submit', {//调教from表单
			url:'orgTaxpayerRelation/searchLegalEnility.do',			
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(result) {
			   var result = $.parseJSON(result);
		       $("#dg").datagrid('loadData', result);		       
		       $("#dg").datagrid("loaded"); 
		     }
		});

     */}
	function addTo(){//增加
		
		$('#saveform').form('clear');
		$('#usernameid').textbox('enable');
		$("#dlg").dialog('open').dialog('setTitle',
		'<spring:message code="orgTaxpayerRelation.add"/>');
		//findDialogOrg();
		findDialogLegalEntity();
	}
	function edit(){//修改
		findDialogOrg();
		findDialogLegalEntity();
		var rows  = $('#dg').datagrid('getSelections');
		if(rows.length==1){
        var row = $('#dg').datagrid('getSelected');
		$("#dlg").dialog('open').dialog('setTitle',
		'<spring:message code="orgTaxpayerRelation.edit"/>');
		$("#saveform").form('load',row);
		}else{
		 	$.messager.confirm('<spring:message code="invoiceprint.reqinfo"/>','<spring:message code="invoiceprint.error"/>' );
		}
	}
	
	function save(){//保存
		
		
		
		$('#saveform').form('submit', {
			url:'orgTaxpayerRelation/save.do',
			
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success:function(result){
				var result = $.parseJSON(result);				
				 if(result.success){					 
					clearSaveForm();
					//Search();
					callRefresh();
					
					 
					$('#dlg').dialog('close');
					$.messager.alert('<spring:message code="system.alert"/>',result.msg);
				}else{
					$.messager.alert('<spring:message code="system.alert"/>',result.errorMsg);
				}
				
			} 
		});
	}
	
	function remove2(){//删除
		
		var row=$('#dg').datagrid('getSelected');
		
		if(row!=null){
			var legalEntityName = row.legalEntityName;
			 	//orgName = row.orgName;
			
			var id = row.id;
			
			
		    $.messager.confirm('<spring:message code="system.alert"/>','确定删除该组织与纳税人关系？',function(result){  
				  if (result){
				      $.ajax({
                    		url:"orgTaxpayerRelation/delete.do?id="+id,
		                    dataType : "json",
		                    cache:false,
		                    success : function(result) {	                    	
		                      if(result.success) {	                        		                        	
		                        	//Search();
		                        	clearSearchForm();
		                        	callRefresh();
		                        	$.messager.alert('<spring:message code="system.alert"/>',result.msg);
		                        }else{
		                        	$.messager.alert('<spring:message code="system.alert"/>',result.errorMsg);
		                        }
		                        
		                    }
		                });
				     
				  }
			  });  		
		}else{
			$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="noselectrecord"/>');
		}
		
	}

	function clearSaveForm(){//清除from表单
		$('#saveform').form('clear');
	} 
	function clearSearchForm(){//清除from表单
		var pageNumber = $('#pageNumber').val();
		var pageSize = $('#pageSize').val();	
		$('#orgTaxpayer_searchform').form('clear');
		$('#orgTaxpayer_searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
	}
	
</script>
</html>