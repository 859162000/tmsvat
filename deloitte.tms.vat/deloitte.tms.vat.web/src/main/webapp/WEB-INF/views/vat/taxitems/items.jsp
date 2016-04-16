<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">    
    <div region="north" split="true" border="false" style="overflow: hidden; height: 15%;">  
        <div class="easyui-panel" title="<spring:message code="searchgroup"/>" style="width:100%;height:100%; background-color: #E0ECFF">		
		    <form id="searchform" method="get" ><!-- commandName="taxItemSearchForm" -->
		    	<table>
		    		<tr>
		    			<td nowrap="nowrap"><spring:message code="taxItemsmaintain.categoryName"/>:</td>
		    			<td>
		    			     <input class="easyui-combobox" id="selectCategoryId" name="categoryId"
			                        data-options="url:'items/getTaxCategoryList.do',valueField:'value',textField:'text'"></input><!-- onChange="findItemsName(newValue,oldValue)" -->
		    			     
		    				<!-- <select id="selectCategoryId" name="categoryId" class="easyui-combobox"  style="width:150px;" data-options="valueField:'value',textField:'text'"></select> -->
		    			</td>
		    			<td nowrap="nowrap"><spring:message code="taxItemsmaintain.itemName"/>:</td>
		    			<td><input class="easyui-combogrid" type="text" style="width:150px;" id="itmeNameId" name="itemName"></input></td><!-- data-options="valueField:'key',textField:'value'" -->
		    			<%-- <td><spring:message code="status"/>:</td>
		    			<td>
		    		       <input id="searchStatus" class="easyui-combobox" style="width:150px;" data-options="panelHeight:'auto'" name="status"></input>
		    		    </td>		    			
		    		</tr>
		    		<tr> --%>
		    		    <td nowrap="nowrap"><spring:message code="taxItemsmaintain.itemTaxRules"/>:</td>
		    			<td><input id="selectItemsTaxRules" class="easyui-combobox" type="text" style="width:150px;" name="itemTaxRules"
		    						data-options="url:'items/getTaxRulesName.do',valueField:'comboboxId',textField:'itemTaxRules'"></input></td>	
		    			<td>
		    			   <div style="text-align:center;padding:10px">
		    	             <a href="#" id="searchbtn"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px" onclick="Search()"><spring:message code='button.Search' /></a>
		    	             <a href="#" class="easyui-linkbutton" style="width:80px" onclick="clearSearchForm()"><spring:message code='button.Clear' /></a>			                      
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
	   <div id="tax_items_dlg" class="easyui-dialog"
			style="width: 450px; height: 400px;" closed="true"
			buttons="#dlg-buttons">
	         <form id="itemsForm" method="post" commandName="taxItems">
		        	<table>
				  		<tr>
							<td><spring:message code="taxItemsmaintain.categoryName" />：</td>
							<td><input class="easyui-combobox" id="tax_items_dlg_categoryName" name="categoryId"
			                        data-options="url:'items/getTaxCategoryList.do',valueField:'value',textField:'text'" style="width: 150px;"></td>
							<!-- <td><select id="tax_items_dlg_categoryName" name="categoryName" class="easyui-combobox" style="width: 150px;"></td> -->
							<td><spring:message code="taxItemsmaintain.itemName" />：</td>
							<td><input  name="itemName" style="width: 150px;" ></td>
						</tr>
						<tr>
							<td><spring:message code="taxItemsmaintain.adaptIndustryId" />：</td>
							<td><select id="tax_items_dlg_adaptIndustryId" name="adaptIndustryId" class="easyui-combobox" style="width: 150px;"></td>
							<td><spring:message code="taxItemsmaintain.itemTaxRules" />：</td>
							<td><select id="tax_items_dlg_itemTaxRules" name="itemTaxRules" class="easyui-combobox" 
							 		data-options="url:'items/getTaxRulesName.do',valueField:'comboboxId',textField:'itemTaxRules'" style="width: 150px;"></td><!-- data-options="url:'items/getTaxRulesName.do',valueField:'comboboxId',textField:'itemTaxRules'" -->
							<!-- class="easyui-validatebox" required="true"  -->
						</tr>
						<tr>
							<td><spring:message code="taxItemsmaintain.itemTaxMethod" />：</td>
							<td><select id="tax_items_dlg_itemTaxMethod" name="itemTaxMethod" class="easyui-combobox" style="width: 150px;"></td>
							<td><spring:message code="taxItemsmaintain.itemTaxBasis" />：</td>
							<td><input id="tax_items_dlg_itemTaxBasis" name="itemTaxBasis" class="easyui-textbox" style="width: 150px;"></td>
						</tr>
						<tr>
							<td><spring:message code="taxItemsmaintain.isUsed" />：</td>
							<td>
							<select id="tax_items_dlg_isUsed" name="isUsed" class="easyui-combobox" style="width:120px">
		    					<option value="-1" selected>&nbsp;</option>
		    					<option value="1">是</option>
		    					<option value="0" >否</option>
		    				</select>
		    				</td>
						</tr>
						<tr style="display: none">
							<td input id="id" name="id" class="easyui-textbox"></td>
							<td><input id="uuid" class="easyui-textbox" name="appuseruuid"></input></td>
							<td><input id="tax_items_dlg_categoryName" name="categoryName" class="easyui-textbox"></td>
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
			    {field:'categoryName',title:'<spring:message code="taxItemsmaintain.categoryName"/>',width:100,align:'center'},
				/* {field:'categoryId',title:'<spring:message code="taxItemsmaintain.categoryId"/>',width:100,align:'center'}, */
				{field:'itemName',title:'<spring:message code="taxItemsmaintain.itemName"/>',width:80,align:'center'},
				//{field:'itemInfo',title:'<spring:message code="taxItemsmaintain.itemInfo"/>',width:100,align:'center'},
				{field:'adaptIndustryId',title:'<spring:message code="taxItemsmaintain.adaptIndustryId"/>',width:80,align:'center'},
				{field:'itemTaxMethod',title:'<spring:message code="taxItemsmaintain.itemTaxMethod"/>',width:80,align:'center'},
				{field:'itemTaxBasis',title:'<spring:message code="taxItemsmaintain.itemTaxBasis"/>',width:80,align:'center'},
				{field:'itemTaxRules',title:'<spring:message code="taxItemsmaintain.itemTaxRules"/>',width:100,align:'center'},
				{field:'isUsed',title:'<spring:message code="taxItemsmaintain.isUsed"/>',width:80,align:'center',formatter:formatIsUsed},
				{field:'createDate',title:'<spring:message code="createdate"/>',width:80,align:'center',formatter:function(value){
					return myformatter(new Date(value));
				}},
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
	$(function() {
		$('#itmeNameId')
				.combogrid(
						{
							panelWidth : 500,
							idField : 'id', //ID字段  
							textField : 'itemName', //显示的字段  
							url : "",
							fitColumns : false,
							striped : true,
							editable : false,
							pagination : true, //是否分页
							rownumbers : true, //序号
							collapsible : false, //是否可折叠的
							fit : false, //自动大小
							method : 'post',
							columns : [ [ {
								field : 'id',
								title : '税目编号',
								width : 10,
								hidden : true
							}, {
								field : 'itemName',
								title : '税目名称',
								width : 50
							}/* , {
								field : 'gender',
								title : '用户性别',
								width : 100
							} */ ] ],
							keyHandler : {
								up : function() { //【向上键】押下处理
									//取得选中行
									var selected = $('#itmeNameId').combogrid('grid')
											.datagrid('getSelected');
									if (selected) {
										//取得选中行的rowIndex
										var index = $('#itmeNameId').combogrid('grid')
												.datagrid('getRowIndex',
														selected);
										//向上移动到第一行为止
										if (index > 0) {
											$('#itmeNameId').combogrid('grid')
													.datagrid('selectRow',
															index - 1);
										}
									} else {
										var rows = $('#itmeNameId').combogrid('grid')
												.datagrid('getRows');
										$('#itmeNameId').combogrid('grid').datagrid(
												'selectRow', rows.length - 1);
									}
								},
								down : function() { //【向下键】押下处理
									//取得选中行
									var selected = $('#itmeNameId').combogrid('grid')
											.datagrid('getSelected');
									if (selected) {
										//取得选中行的rowIndex
										var index = $('#itmeNameId').combogrid('grid')
												.datagrid('getRowIndex',
														selected);
										//向下移动到当页最后一行为止
										if (index < $('#itmeNameId').combogrid('grid')
												.datagrid('getData').rows.length - 1) {
											$('#itmeNameId').combogrid('grid')
													.datagrid('selectRow',
															index + 1);
										}
									} else {
										$('#itmeNameId').combogrid('grid').datagrid(
												'selectRow', 0);
									}
								},
								query : function(keyword) { //【动态搜索】处理
									//设置查询参数
									var queryParams = $('#itmeNameId')
											.combogrid("grid").datagrid(
													'options').queryParams;
									queryParams.keyword = keyword;
									$('#itmeNameId').combogrid("grid").datagrid(
											'options').queryParams = queryParams;
									//重新加载
									$('#itmeNameId').combogrid("setValue", keyword);
									 $('#itmeNameId').combogrid("grid").datagrid("reload", { 'keyword': keyword });
								}
							},
							/* onSelect : function() { //选中处理
								$('#txtgender').val(
										$('#itmeNameId').combogrid('grid').datagrid(
												'getSelected').gender);
							} */
						});

		//取得分页组件对象
		var pager = $('#itmeNameId').combogrid('grid').datagrid('getPager');

		if (pager) {
			$(pager)
					.pagination(
							{
								pageSize: 10,//每页显示的记录条数，默认为10           
								//pageList: [5,10,15,20],//可以设置每页记录条数的列表           
								beforePageText: '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
								afterPageText: '<spring:message code="pagination.afterPageText"/>',           
								displayMsg: '<spring:message code="pagination.displayMsg"/>',
								//选择页的处理
								onSelectPage : function(pageNumber, pageSize) {
									//按分页的设置取数据
									getData(pageNumber, pageSize);
									//设置表格的pageSize属性，表格变化时按分页组件设置的pageSize显示数据
									$('#itmeNameId').combogrid("grid").datagrid(
											'options').pageSize = pageSize;
									//将隐藏域中存放的查询条件显示在combogrid的文本框中
									//$('#itmeNameId').combogrid("setValue",
									//		$('#hdKeyword').val());
									
								},
								//改变页显示条数的处理
								//（处理后还是走onSelectPage事件，所以设置也写到onSelectPage事件中了）
								onChangePageSize : function() {
								},
								//点击刷新的处理
								onRefresh : function(pageNumber, pageSize) {
									//按分页的设置取数据
									getData(pageNumber, pageSize);
									//将隐藏域中存放的查询条件显示在combogrid的文本框中
									/* $('#itmeNameId').combogrid("setValue",
											$('#hdKeyword').val()); */
									
								}
							});
		}
		
		
			//InitCombobox();	
			
			
		
		
		

		var getData = function(page, rows) {
			$.ajax({
				type : "POST",
				url : "combogrid/search.do",
				data : "page=" + page + "&rows=" + rows + "&keyword="
						+ $('#hdKeyword').val(),
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					//alert(textStatus);
					$.messager.progress('close');
				},
				success : function(data) {					
					/* var result = $.parseJSON(data);
					alert(result); */
					$('#itmeNameId').combogrid('grid').datagrid('loadData', data);
				}
			});
		};
	});
	function InitCombobox(){
		 $('#selectCategoryId').combobox({
			 onChange: function (n, o) {
				 $("#itmeNameId").combogrid("setValue",'');//清空税目信息  
		          var id = $('#selectCategoryId').combobox('getValue');
			      $.ajax({  
			        type: "POST",   
			        /* url: '${pageContext.request.contextPath}/loadCourse?id=' + id, */
			        url:'items/getTaxItemsName.do?categoryId='+id,
			        cache: false,  
			        dataType : "json",  
			        success: function(data){
			       /*  var result = $.parseJSON(data);	
			        /* $("#itmeNameId").combogrid("loadData",result); */ 
			        $('#itmeNameId').combogrid('grid').datagrid('loadData', data);
			                 }  
			            });       
			         }  
		 });
	}
	
	function find(pageNumber,pageSize){
		$('#searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		/* Search(); */
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
	
	/* function save(){		
		$('#saveform').form('submit', {
			url:'usermaintain/save.do',	
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},			
			success:function(result){
				var result = $.parseJSON(result);				
				if(result.success){						
					Search();
					clearSaveForm(); 
				}
				$.messager.alert('<spring:message code="system.alert"/>',result.msg);
			} 
		});
	} */
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
        					$("#tax_items_dlg").dialog('open').dialog('setTitle',
        	        		'<spring:message code="taxItemsmaintain.edittax"/>');
        					$("#itemsForm").form('load', result.taxItmesForm);
        					//$("#selectCategoryId").combobox('setValue', result.taxItmesForm.categoryName);
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
        	$('#itemsForm').form('submit', {
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
		$("#tax_items_dlg").dialog('open').dialog('setTitle',
		'<spring:message code="taxItemsmaintain.add"/>');
	}
	
	function clearSaveForm(){
		$('#itemsForm').form('clear');
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
		$('#itemsForm').form('load', {
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
	function findItemsName(newValue,oldValue){
		  $("#itmeNameId").combogrid("setValue",'');//清空税目信息  
          var id = $('#selectCategoryId').combobox('getValue');
	      $.ajax({  
	        type: "POST",  
	        /* url: '${pageContext.request.contextPath}/loadCourse?id=' + id, */
	        url:'items/getTaxItemsName?categoryId='+id,
	        cache: false,  
	        dataType : "json",  
	        success: function(data){  
	        $("#itmeNameId").combogrid("loadData",data);
               }  
          });       
       } 
	
</script>
</html>