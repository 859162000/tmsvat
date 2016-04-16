<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
	<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
    <div region="north" split="true" border="false" style="overflow: hidden; height: 99%;">  
        <div class="easyui-panel" title="" style="width:100%; background-color: #E0ECFF">		
		    <form id="clientManageInit_searchform" method="post"  scroll="no">
		    	<table cellpadding="10" >
		    		<tr>
	    				<td><spring:message code='client.search.entityName'/></td>
		    			<td>
		    				<select id="clientManageInit_EntityNameSelect" name="entityName" class="easyui-combogrid"  style="width:200px;"></select>
		    			</td>
		    			<td><spring:message code='client.search.entityType'/></td>
		    			<td>
		    				<select id="clientManageInit_EntityTypeSelect" name="entityType" class="easyui-combogrid"  style="width:200px;"></select>
		    			</td>
		    			<td><spring:message code='client.search.isInv'/></td>
		    			<td>
		    				<select name="isInv" class="easyui-combobox" style="width:120px">
		    					<option value="Y"><spring:message code='client.select.option.yes'/></option>
		    					<option value="N" ><spring:message code='client.select.option.no'/></option>
		    					
		    				</select>
		    			</td>
		    			<td>
		    			   <div style="text-align:center;padding:10px">
		    	             <a href="#" id="searchbtn"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px" onclick="Search()"><spring:message code='client.search.button.find'/></a>
		    	             <%-- <a href="#" class="easyui-linkbutton" style="width:80px" onclick="clearSearchForm()"><spring:message code='client.search.button.clear'/></a> --%>			                      
		                   </div>    			   
		    			</td>
		    		</tr>
		    		<tr style="display:none">
		    		   <td><input id="pageNumber" class="easyui-textbox" type="text" style="width:0px;" name="pageNumber" value=""></input></td>
		    		   <td><input id="pageSize" class="easyui-textbox" type="text" style="width:0px;" name="pageSize" value=""></input></td>
		    		</tr>
		    	</table>
		    </form>
	    </div>
	    <div style="width:100%;height:100%">
	         <table class="easyui-datagrid" id="clientManageInit_dataGrid" title="<spring:message code='client.datagrid.title'/>" style="width:100%;height:88%" data-options="					
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
    <div data-options="region:'center',border:false" style="background-color: #E0ECFF">
	   <div id="clientManageInit_addclient_dlg" class="easyui-dialog"
			style="width: 800px; height: 500px;" closed="true"
			buttons="#clientManageInit_addclient_dlg-buttons">
			
				<form id="clientManageInit_editform" class="easyui-form" method="post" data-options="novalidate:true" commandsName="clientManageForm">
					<table >
		    		<tr>
	    				<td><font color=red>*</font><spring:message code='client.clientName'/>:</td>
		    			<td>
		    				<input  type="hidden" name="id"></input>
		    				<input class="easyui-textbox" type="text" name="clientName" 
		    					missingMessage=<spring:message code='client.model.missMsg'/> data-options="required:true"></input>
		    				
		    			</td>
		    			<td><font color=red>*</font><spring:message code='client.clientCode'/>:</td>
		    			<td>
		    				<input class="easyui-textbox" 
		    					type="text" name="clientCode" 
		    					missingMessage=<spring:message code='client.model.missMsg'/>
		    					data-options="required:true"></input>
		    			</td>
		    			<td><font color=red>*</font><spring:message code='client.clientEntityNum'/>:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="clientEntityNum" 
		    					missingMessage=<spring:message code='client.model.missMsg'/>
		    					data-options="required:true"></input>
		    			</td>
		    		</tr>
		    		<tr>
	    				<td><font color=red>*</font><spring:message code='client.clientType'/>:</td>
		    			<td>
		    				<select class="easyui-combobox" name="clientType" style="width:150px"
		    					missingMessage=<spring:message code='client.model.missMsg'/> data-options="required:true">
		    					<option value="1">Arabic</option>
		    					<option value="2">Bulgarian</option>
		    					<option value="3">Catalan</option>
		    					<option value="4">Chinese Traditional</option>
		    					<option value="5">Czech</option>
		    				</select>
		    			</td>
		    			<td><font color=red>*</font><spring:message code='client.entityType'/>:</td>
		    			<td>
		    				<select class="easyui-combobox" name="entityType" style="width:150px" data-options="required:true" missingMessage="<spring:message code='client.model.missMsg'/>">
		    					<option value="1" >Arabic</option>
		    					<option value="2">Bulgarian</option>
		    					<option value="3">Catalan</option>
		    					<option value="4">Chinese Traditional</option>
		    					<option value="5">Czech</option>
		    				</select>
		    			</td>
		    			<td><font color=red>*</font><spring:message code='client.clientDate'/>:</td>
		    			<td>
		    				 <input class="easyui-datebox" width="90" name="clientDate" data-options="formatter:myformatter,parser:myparser,required:true" missingMessage=<spring:message code='client.model.missMsg'/>></input>
		    			</td>
		    		</tr>
		    		<tr>
	    				<td><font color=red>*</font><spring:message code='client.bank'/>:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="bank" data-options="required:true" missingMessage=<spring:message code='client.model.missMsg'/>></input>
		    			</td>
		    			<td><font color=red>*</font><spring:message code='client.bankNum'/>:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="bankNum" missingMessage="<spring:message code='client.model.missMsg'/>" data-options="required:true"></input>
		    			</td>
		    			<td><font color=red>*</font><spring:message code='client.compAddr'/>:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="compAddr" missingMessage="<spring:message code='client.model.missMsg'/>" data-options="required:true"></input>
		    			</td>
		    		</tr>
		    		<tr>
	    				<td><font color=red>*</font><spring:message code='client.compPhone'/>:</td>
		    			<td>
		    				<input class="easyui-textbox"  type="text" name="compPhone" missingMessage="<spring:message code='client.model.missMsg'/>" data-options="required:true"></input>
		    			</td>
		    			<td><font color=red>*</font><spring:message code='client.isUsed'/>:</td>
		    			<td >
		    				<select id="clientManageInit_editform_isUsed_select" class="easyui-combobox" name="isUsed" style="width:150px" missingMessage=<spring:message code='client.model.missMsg'/> data-options="required:true">
		    					<option value='1' ><spring:message code='client.select.option.yes'/></option>
		    					<option value='2' ><spring:message code='client.select.option.no'/></option>
		    				</select>
		    			</td>
		    			<td><font color=red>*</font><spring:message code='client.isInv'/>:</td>
		    			<td>
		    				<select  id="clientManageInit_editform_isInv_select" class="easyui-combobox" name="isInv" style="width:150px" 
		    					data-options="required:true" missingMessage=<spring:message code='client.model.missMsg'/> onchange="isInvSelectChange()">
		    					<option value='1' ><spring:message code='client.select.option.yes'/></option>
		    					<option value='2' ><spring:message code='client.select.option.no'/></option>
		    				</select>
		    			</td>
		    		</tr>
		    	</table>
		    </form>
		</div>
		<div id="clientManageInit_addclient_dlg-buttons">
			<a  href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" onclick="clientMangeEditSave()"><spring:message
					code="button.Save" /></a> <a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#clientManageInit_addclient_dlg').dialog('close')"><spring:message
					code="button.Close" /></a>
		</div>
    </div>
  </body>  
<script type="text/javascript">
	//
	$(document).ready(function(){
		pageDataSettingInit();
		InitCombobox();	
		$('#clientManageInit_searchform').form('load', {
			pageNumber: $('#clientManageInit_dataGrid').datagrid('options').pageNumber,
			pageSize: $('#clientManageInit_dataGrid').datagrid('options').pageSize
		});
		Search();
	});
	//页面表格绑定初始化
	function pageDataSettingInit(){
		$('#clientManageInit_editform_isInv_select').combobox({    
		    onChange : function(n,o){
		    		//alert(n);
		    		//alert(o);
		    	}  
			});
		$('#clientManageInit_dataGrid').datagrid({
            //method:'post',  
            //iconCls:'icon-edit', //图标  
            //height:360, //高度  
            //queryParams:{}, //查询条件 
            //title:'用户列表', //标题
			url: '',
			loadMsg:"<spring:message code='client.datagrid.dataloding'/>",
			striped:true,//奇偶行颜色不同
			singleSelect:true, //多选
			collapsible:false,//可折叠  
			fitColumns: true,//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
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
							{field:'clientName',title:"<spring:message code='client.clientName'/>",width:80,align:'center'},
							{field:'clientCode',title:"<spring:message code='client.clientCode'/>",width:80,align:'center'},
			                ]],
			columns:[[
				
				 {field:'clientEntityNum',title:"<spring:message code='client.clientEntityNum'/>",width:80,align:'center'},
				 {field:'clientType',title:"<spring:message code='client.clientType'/>",width:80,align:'center'},
				 {field:'entityType',title:"<spring:message code='client.entityType'/>",width:80,align:'center'},
				 {field:'bank',title:"<spring:message code='client.bank'/>",width:80,align:'center'},
				 {field:'bankNum',title:"<spring:message code='client.bankNum'/>",width:80,align:'center'},
				 {field:'clientDate',title:"<spring:message code='client.clientDate'/>",width:80,align:'center',formatter:function (value){
					 	return myformatter(new Date(value));
				 	}
                 },
				 {field:'compAddr',title:"<spring:message code='client.compAddr'/>",width:80,align:'center'},
				 {field:'compPhone',title:"<spring:message code='client.compPhone'/>",width:80,align:'center'}
			]],
			toolbar:[
			         {text:"<spring:message code='client.button.clientAdd'/>",  
                	iconCls:'icon-add',  
                	handler:function(){  
                			clientMangeAdd();  
                			}  
            		},'-',{  
            			text:"<spring:message code='client.button.clientEdit'/>",  
	                iconCls:'icon-edit',  
	                handler:function(){  
	                	clientMangeEdit();
	                }  
	            },'-',{  
	            	text:"<spring:message code='client.button.clientRemove'/>",  
	                iconCls:'icon-remove',  
	                handler:function(){  
	                	clientManageRemove();  
	                }  
	            },'-'],
	            onDblClickRow:function(index,data){
					alert("双击表格显示详情功能正在建设中。。。。");
				/* var row = $('#clientManageInit_dataGrid').datagrid('getSelected');
				if (row){
					loadSaveFormData(row);
				} */
			},
			onLoadSuccess:function(){  
                $('#clientManageInit_dataGrid').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
            } 
		});
		
		//设置分页控件	
		var p = $('#clientManageInit_dataGrid').datagrid('getPager'); 
		$(p).pagination({           
			pageSize: 10,//每页显示的记录条数，默认为10           
			//pageList: [5,10,15,20],//可以设置每页记录条数的列表           
			beforePageText: '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
			afterPageText: '<spring:message code="pagination.afterPageText"/>',           
			displayMsg: '<spring:message code="pagination.displayMsg"/>',
			onSelectPage: function (pageNumber, pageSize) {//分页触发		
		    	 find(pageNumber,pageSize);
				 Search(); 
				 clearSaveForm();
	         }
		});
	}
	
	function find(pageNumber,pageSize){
		$('#clientManageInit_searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		Search();
	}
	function Search(){
    	$("#clientManageInit_dataGrid").datagrid("loading");
    	$('#clientManageInit_searchform').form('submit', {
			url:'${vat}/client/getClients.do',			
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success:function(result) {
				//数据加载以及绑定
			   var result = $.parseJSON(result);			 
		       $("#clientManageInit_dataGrid").datagrid('loadData', result.data);		       
		       $("#clientManageInit_dataGrid").datagrid("loaded"); 
		     }
		});
    }
	
	function InitCombobox(){
		//------
		$("#clientManageInit_EntityTypeSelect").combogrid({
			   panelWidth:200,
			   value:'safds',//缺省值
			   idField:"id",
			   textField:"clientName",
			   url:"",
			   columns:[[  
			         {field:'id',title:"<spring:message code='client.clientName'/>",width:80},
					 {field:'clientName',title:"<spring:message code='client.clientEntityNum'/>",width:120} 
			      ]]
			  });
		
		$.ajax({  
	        url: "${vat}/client/getClientInfoList.do",  
	        type: "POST",  
	        async: true,  
	        data: '', //不能直接写成 {id:"123",code:"tomcat"}  
	        dataType: "json",  
	        // contentType: "charset=utf-8",  
	        cache: false,  
	        success: function (result){
				clearSaveForm();
				$('#clientManageInit_EntityTypeSelect').combogrid('grid').datagrid('loadData',result.data);
	        } 
	    });
		
		$("#clientManageInit_EntityNameSelect").combogrid({
			   panelWidth:200,
			   value:'safds',//缺省值
			   idField:"id",
			   textField:"clientName",
			   url:"",
			   columns:[[  
			         {field:'id',title:"<spring:message code='client.clientName'/>",width:80},
					 {field:'clientName',title:"<spring:message code='client.clientEntityNum'/>",width:120} 
			      ]]
			  });
		
		$.ajax({  
	        url: "${vat}/client/getClientNameList.do",  
	        type: "POST",  
	        async: true,  
	        data: '', //不能直接写成 {id:"123",code:"tomcat"}  
	        dataType: "json",  
	        // contentType: "charset=utf-8",  
	        cache: false,  
	        success: function (result){
				clearSaveForm();
				$('#clientManageInit_EntityNameSelect').combogrid('grid').datagrid('loadData',result.data);
	        } 
	    });
	}
	
    //客户关系添加
    function clientMangeAdd(){
    	clearSaveForm();
		$("#clientManageInit_addclient_dlg").dialog('open').dialog('setTitle',
		"<spring:message code='client.dialog.clientadd.title'/>");
		$("#clientManageInit_editform").form('load', {clientDate:myformatter(new Date())});
	} 
    function clientMangeEdit(){
    	clearSaveForm();
    	var row_list=$('#clientManageInit_dataGrid').datagrid('getChecked');
    	if(row_list){
    		var id='';
    		$.each(row_list,function(index,item){
     			if(index==0){
     				id = item.id;
     			}
    		});
    		if(id!=''){        		
            	$.post('${vat}/client/loadModifyClient.do',
            			{id:id}, 
            			function(result) {
            				if (result.status=='0'){
	            					$("#clientManageInit_addclient_dlg").dialog('open').dialog('setTitle',
	            							"<spring:message code='client.dialog.clientedit.title'/>");
	            					$("#clientManageInit_editform").form('load', {clientDate:myformatter(new Date(result.data.clientDate))});
	            					$("#clientManageInit_editform").form('load', {isUsed:result.data.isUsed});
            						$("#clientManageInit_editform").form('load', result.data);
            					}else if(result.success=='false'){
            						$.messager.alert('<spring:message code="system.alert"/>',result.errorMsg);
            						Search();
            						clearSaveForm();
            						$('#clientManageInit_addclient_dlg').dialog('close');
            					}
    					},
    					'json'); 
        	}else{
        		$.messager.alert('<spring:message code="system.alert"/>','编辑的主键不能为空');
        	}
    	}
    }
    
    
	function clientMangeEditSave(){
		$.messager.confirm("<spring:message code='client.datacommit.formsave.confirm.title'/>","<spring:message code='client.datacommit.formsave.confirm.text'/>",function(result){
			if (result){
				$('#clientManageInit_editform').form('submit',{
					url:'${vat}/client/saveClient.do',	
					onSubmit : function() {
						return $(this).form('enableValidation').form('validate');
					},			
					success:function(result){
						var result = $.parseJSON(result);
						if(result.status=='0'){	
							clearSaveForm();
							Search();
							$('#clientManageInit_addclient_dlg').dialog('close');
							$.messager.alert('<spring:message code="system.alert"/>','保存成功');
						}
						
					} 
				});
			}
		});
		
		
	}
	//删除一条记录
	
	function clientManageRemove(){
		$.messager.confirm("<spring:message code='client.datacommit.delete.confirm.title'/>","<spring:message code='client.datacommit.delete.confirm.text'/>",function(result){ 
			if (result){
				var row_list=$('#clientManageInit_dataGrid').datagrid('getChecked');
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
		        	        url: "${vat}/client/removeClients.do",  
		        	        type: "POST",  
		        	        async: true,  
		        	        data: urlString, //不能直接写成 {id:"123",code:"tomcat"}  
		        	        dataType: "json",  
		        	        // contentType: "charset=utf-8",  
		        	        cache: false,  
		        	        success: function (result){
		        	        	Search();
		    					clearSaveForm();
		        	        } 
		        	    });
		        	}
		        }
			}
			  
        }); 
	}
	//清空dialog内容
	
	function clearSaveForm(){
		$('#clientManageInit_editform').form('clear');
	}
</script>
</html>