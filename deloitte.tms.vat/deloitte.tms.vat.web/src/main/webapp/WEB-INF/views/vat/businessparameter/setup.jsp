<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">    
    <div region="north" split="true" border="false" style="overflow: hidden; height: 20%;">  
        <div class="easyui-panel" title="<spring:message code="searchgroup"/>" style="width:100%;height:100%; background-color: #E0ECFF">		
		    <form id="searchform" method="get" ><!-- commandName="taxItemSearchForm" -->
		    	<table>
		    		<tr>
		    			<td><spring:message code="businessParameter.org"/>:</td>
		    			<td><input class="easyui-combobox"  id="org" name="categoryId"></input></td>
		    			<td><spring:message code="businessParameter.pram"/>:</td>
		    			<td><input class="easyui-combobox" style="width:150px;" name="itemName"></input></td>
		    			<td>
		    			   <div style="text-align:center;padding:10px">
		    	             <a href="#" id="searchbtn"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px" onclick="Search()">查询</a>
		    	             <a href="#" class="easyui-linkbutton" style="width:80px" onclick="clearSearchForm()">清除</a>			                      
		                   </div>    			   
		    			</td>
		    		</tr>
		    		<tr style="display:none">
		    		   <td><input id="pageNumber" class="easyui-textbox" type="text" style="width:0px;" name="pageNumber" value=""></input></td>
		    		   <td><input id="pageSize" class="easyui-textbox" type="text" style="width:0px;" name="pageSize" value=""></input></td>
		    		   <td><input id="categoryName" name="categoryName" class="easyui-combobox"  style="width:0px;"></input></td>
		    		</tr>		 	    	
		    	</table>
		    </form>
	    </div>
	    	         
    </div>
    <div data-options="region:'center',border:false" style="background-color: #E0ECFF">
    	<div style="width:100%;height:100%">
	         <table class="easyui-datagrid" id="dg" title="<spring:message code="taxItemsmaintain.itemInfo"/>" style="width:100%;height:100%" data-options="					
					singleSelect:true,
					autoRowHeight:false,
					pagination:true,
					pageSize:10,
					remoteSort:false,
				    multiSort:true	
					">			 
		   </table>   
	    </div>		     
	   <div id="business_param_dlg" class="easyui-dialog"
			style="width: 550px; height: 400px;" closed="true"
			buttons="#dlg-buttons">
	         <form id="businessParamForm" method="post" commandName="taxItems">
		        	<table>
				  		<tr>
							<td><spring:message code="businessParameter.org" />：</td>
							<td><input class="easyui-combobox" id="business_param_dlg_org" name="categoryName" style="width: 150px;"></td>
							<!-- <td><select id="tax_items_dlg_categoryName" name="categoryName" class="easyui-combobox" style="width: 150px;"></td> -->
							<td><spring:message code="businessParameter.pramName" />：</td>
							<td><input class="easyui-combobox" name="pramName" style="width: 150px;" ></td>
						</tr>
						<tr>
							<td><spring:message code="businessParameter.threshold" />：</td>
							<td><input id="business_param_dlg_threshold" name=threshold class="easyui-combobox" style="width: 150px;"></td>
							<td><spring:message code="businessParameter.effectDay"/>:</td>
			    			<td>
			    				 <input id="to" class="easyui-datebox" width="90" name="effectDay" /></input>
			    			</td>
						</tr>
						<tr>
							<td><spring:message code="businessParameter.invalidDay" />：</td>
							<td><input id="business_param_dlg_invalidDay" name="invalidDay" class="easyui-datebox" style="width: 150px;"></td>
						</tr>
						<tr style="display: none">
							<td input id="id" name="id" class="easyui-textbox" style="height:20px"></td>
							<td><input id="uuid" class="easyui-textbox" name="appuseruuid"
								style="height:20px"></input></td>
							<td><input id="tax_items_dlg_categoryId" name="categoryId" class="easyui-textbox" style="height:20px"></td>
						</tr>	    
				  	</table>
			  </form>
			</div>
			<div id="dlg-buttons">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-ok" onclick="saveItems()"><spring:message
						code="button.Save" /></a> <a href="javascript:void(0)"
					class="easyui-linkbutton" iconCls="icon-cancel"
					onclick="javascript:$('#tax_items_dlg').dialog('close')"><spring:message
						code="button.Close" /></a>
			</div>
    </div>
</body>
<script type="text/javascript">
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
			singleSelect:false,
			fitColumns: true,
			striped: true,
			idField:'id', //主键字段  
			columns:[[
			    {field:'ck',checkbox:true,width:2}, //显示复选框     
			    {field:'categoryName',title:'<spring:message code="businessParameter.org"/>',width:100,align:'center'},
				/* {field:'categoryId',title:'<spring:message code="taxItemsmaintain.categoryId"/>',width:100,align:'center'}, */
				{field:'itemName',title:'<businessParameter.pramName"/>',width:80,align:'center'},
				//{field:'itemInfo',title:'<spring:message code="taxItemsmaintain.itemInfo"/>',width:100,align:'center'},
				{field:'adaptIndustryId',title:'<spring:message code="businessParameter.threshold"/>',width:80,align:'center'},
				{field:'itemTaxMethod',title:'<spring:message code="businessParameter.effectDay"/>',width:80,align:'center'},
				{field:'itemTaxBasis',title:'<spring:message code="businessParameter.invalidDay"/>',width:80,align:'center'},
				{field:'itemTaxRules',title:'<spring:message code="createdate"/>',width:100,align:'center'},
				{field:'isUsed',title:'<spring:message code="createperson"/>',width:80,align:'center'},
				{field:'updatedate',title:'<spring:message code="updatedate"/>',width:80,align:'center'},
				//{field:'id',title:'<spring:message code="taxItemsmaintain.id"/>',width:100,align:'center',display:'none'}
				/* {field:'updateby',title:'<spring:message code="updateperson"/>',width:80,align:'center'},
				{field:'updatedate',title:'<spring:message code="updatedate"/>',width:100,align:'center'}, */
			]],
		   toolbar:[{  
                 text:'新增',  
                iconCls:'icon-add',  
                handler:function(){  
                     addTo();  
                }  
             },'-',{  
                 text:'编辑',  
                 iconCls:'icon-edit',  
                 handler:function(){  
                     edit();  
                 }  
             },'-',{  
                 text:'删除',  
                 iconCls:'icon-remove',  
                 handler:function(){  
                     remove2();  
                 }  
             },'-'],  
			 onLoadSuccess:function(){  
				 $('#dg').datagrid('clearSelections')
			 },
			onClickRow:function(index,data){
				var row = $('#dg').datagrid('getSelected');
				if (row){
					loadSaveFormData(row);
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
			onSelectPage: function (pageNumber,pageSize) {//分页触发		
		    	 find(pageNumber,pageSize);
				 Search(); 
				 clearSaveForm();
	         }
	
		});
	});
	$(document).ready(function() {
		InitCombobox();	
		$('#searchform').form('load', {
			pageNumber : $('#dg').datagrid('options').pageNumber,
			pageSize : $('#dg').datagrid('options').pageSize
		});
		Search();
	});
	
	function InitCombobox(){
		 
	}
	function find(pageNumber,pageSize){
		$('#searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		Search();
	}
    function Search(){ 
    	$("#dg").datagrid("loading");
    	$('#searchform').form('submit', {
			url:'items/getItems.do',			
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(result) {
				/* {"status":0,"data":{"recordCount":1,"pageIndex":1,"pageSize":10,"result":[{"createdBy":null,"createDate":null,"modifiedBy":null,"modifiedDate":null,"flag":null,"status":null,"dataowner":null,"companyId":null,"id":1,"categoryId":"1","itemCode":null,"itemName":"1","orgId":null,"itemTaxRules":"1","isUsed":"1","comments":null,"adaptIndustryId":"1","sDate":null,"eDate":null}],"recordIndex":0,"pageCount":1}} */
			   var result = $.parseJSON(result);
		       $("#dg").datagrid('loadData', result);		       
		       $("#dg").datagrid("loaded"); 
		     }
		});

    }
	
	function edit(){
		var row = $('#dg').datagrid('getChecked');
		if(row.length==0){  
            $.messager.alert('提示',"请选择你要更新的用户",'info');  
            return;  
        } 
		if(row.length > 1){  
           $.messager.alert('提示',"只能选择一位用户进行更新",'info');  
           return;  
        } 
		var tax_item;
		$.each(row,function(index,item){
			if(index==0){
				tax_item=item;
			}
		});
		clearSaveForm();
		if(tax_item.id!=''){        		
        	$.post('items/loadModifyItems.do',
        			{id:tax_item.id}, 
        			function(result) {
        				if (result.success=='true'){
        					$("#business_param_dlg").dialog('open').dialog('setTitle',
        	        		'<spring:message code="businessParameter.editPram"/>');
        					$("#businessParamForm").form('load', result.taxItmesForm);
        					$("#selectCategoryId").combobox('setValue', result.taxItmesForm.categoryName);
        					$("#tax_items_dlg_itemTaxRules").combobox('setValue', result.taxItmesForm.itemTaxRules);
        					}else if(result.success=='false'){
        						$.messager.alert('<spring:message code="system.alert"/>',result.errorMsg);
        						Search();
        						clearSaveForm();
        						$('#tax_items_dlg').dialog('close');
        					}
					},
					'json'); 
    	}else{
    		$.messager.alert('<spring:message code="system.alert"/>','编辑的主键不能为空');
    	}
	}
	function remove2(){
	 $.messager.confirm('提示','确定要删除吗?',function(result){  
            if (result){  
                var rows = $('#dg').datagrid('getChecked');  
                var ps = "";  
                $.each(rows,function(i,n){  
                    if(i==0)   
                        ps +=n.id;  
                    else  
                        ps += ","+n.id;  
                }); 
                $.ajax({
                    url : "items/removeItems.do?ids="+ps,
                    dataType : "json",
                    cache:false,
                    success : function(object) {
                        if (object.result) {	                        		                        	
                        	Search();
                        } 
                        $.messager.alert('<spring:message code="system.alert"/>',object.msg);
                    }
                });	
            }  
        });  
	}
	function saveItems(){
        	$('#businessParamForm').form('submit', {
    			url:'items/saveItems.do',
    			cache:false,
    			onSubmit : function() {
    				return $(this).form('enableValidation').form('validate');
    			},			
    			success:function(object){ 
    				var object = $.parseJSON(object);
    				clearSaveForm();
    				$('#tax_items_dlg').dialog('close');
    				$.messager.alert('<spring:message code="system.alert"/>',object.msg);
    				Search();
    			} 
    		});    	
	}
	function addTo(){
		clearSaveForm();
		$("#business_param_dlg").dialog('open').dialog('setTitle',
		'<spring:message code="taxItemsmaintain.add"/>');
	}
	
	function clearSaveForm(){
		$('#businessParamForm').form('clear');
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
	
	function loadSaveFormData(row){
		$('#status').combobox('setValue', row.status);
		$('#businessParamForm').form('load', {
			id:row.id,
			name  :row.itemName,
			adaptIndustryId :row.adaptIndustryId,
			itemTaxMethod :row.itemTaxMethod,
			itemTaxBasis :row.itemTaxBasis,
			itemTaxRules:row.itemTaxRules,
			createby:row.createby,
			createdate:row.createdate,
			updateby:row.updateby,
			updatedate:row.updatedate,
			/* appuseruuid:row.appuseruuid */
		});
	}
</script>
</html>