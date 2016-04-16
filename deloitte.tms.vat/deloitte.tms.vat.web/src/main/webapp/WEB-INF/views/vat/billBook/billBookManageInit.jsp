<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
	<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
    <div region="north" split="true" border="false" style="overflow:hidden; height: 99%;">  
        <div class="easyui-panel" title="" style="background-color: #E0ECFF">		
		    <form id="billBookManageInit_searchform" method="post"  scroll="no">
		    	<table cellpadding="10"   >
		    		<tr>
	    				<td><spring:message code='billBook.search.bookName'/></td>
		    			<td>
		    				<select id="billBookManageInit_bookName" name="bookName" class="easyui-combogrid"  style="width:200px;"></select>
		    			</td>
		    			<td>
		    			   <div style="text-align:center;padding:10px">
		    	             <a href="#" id="searchbtn"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px" onclick="billBookManageInit_Search()"><spring:message code='client.search.button.find'/></a>
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
	         <table class="easyui-datagrid" id="billBookManageInit_dataGrid" title="<spring:message code='billbook.manage.datagrid.title'/>" style="width:100%;height:88%" data-options="					
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
	   <div id="billBookManageInit_add_dlg" class="easyui-dialog"
			style="width: 800px; height: 500px;" closed="true"
			buttons="#billBookManageInit_add_dlg-buttons" modal="true">
				<form id="billBookManageInit_editform" class="easyui-form" method="post" data-options="novalidate:true" commandsName="billBookManageForm">
					<table >
		    		<tr>
	    				<td><font color=red>*</font><spring:message code='client.clientName'/>:</td>
		    			<td>
		    				<input  type="hidden" name="id"></input>
		    				<input class="easyui-textbox" type="text" name="bookName" 
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
		    	</table>
		    </form>
		</div>
		<div id="billBookManageInit_add_dlg-buttons">
			<a  href="javascript:void(0)" class="easyui-linkbutton"	iconCls="icon-ok" onclick="billBookManageInit_Save()">
					<spring:message	code="button.Save" />
			</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#billBookManageInit_add_dlg').dialog('close')"><spring:message
					code="button.Close" />
			</a>
		</div>
    </div>
  </body>  
<script type="text/javascript">
	$(document).ready(function(){
		pageDataSettingInit();
		InitCombobox();	
		$('#billBookManageInit_searchform').form('load', {
			pageNumber: $('#billBookManageInit_dataGrid').datagrid('options').pageNumber,
			pageSize: $('#billBookManageInit_dataGrid').datagrid('options').pageSize
		});
		billBookManageInit_Search();
	});
	//页面表格绑定初始化
	function pageDataSettingInit(){
		$('#billBookManageInit_dataGrid').datagrid({
			url: '',
			loadMsg:"<spring:message code='system.datagrid.dataloding.msg'/>",
			striped:true,//奇偶行颜色不同
			singleSelect:true, //多选
			collapsible:false,//可折叠  
			fitColumns: true,//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			nowrap: false,
			iconCls: 'icon icon-list',
			pagination:true, //显示分页 
			rownumbers:true, //显示行号
			idField:'id',//主键字段
			frozenColumns:[[
							{field:'checkFlag',checkbox:true,width:80,align:'center'},
							{field:'id',hidden:true,width:80,align:'center'}
			                ]],
			columns:[[
				 {field:'billBookName',title:"测试名称",width:80,align:'center'},
			]],
			toolbar:[
			         {text:"<spring:message code='button.Add'/>",  
                	iconCls:'icon-add',  
                	handler:function(){  
                			billBookManageInit_Add();  
                			}  
            		},'-',{  
            			text:"<spring:message code='button.Edit'/>",  
	                iconCls:'icon-edit',  
	                handler:function(){  
	                	billBookManageInit_Edit();
	                }  
	            },'-',{  
	            	text:"<spring:message code='button.Remove'/>",  
	                iconCls:'icon-remove',  
	                handler:function(){  
	                	billBookManageInit_Remove();  
	                }  
	            },'-'],
	            onDblClickRow:function(index,data){
					alert("双击表格显示详情功能正在建设中。。。。");
				/* var row = $('#billBookManageInit_dataGrid').datagrid('getSelected');
				if (row){
					loadSaveFormData(row);
				} */
			},
			onLoadSuccess:function(){  
                $('#billBookManageInit_dataGrid').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
            } 
		});
		//设置分页控件	
		var p = $('#billBookManageInit_dataGrid').datagrid('getPager'); 
			$(p).pagination({           
				pageSize: 10,//每页显示的记录条数，默认为10           
				//pageList: [5,10,15,20],//可以设置每页记录条数的列表           
				beforePageText: '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
				afterPageText: '<spring:message code="pagination.afterPageText"/>',           
				displayMsg: '<spring:message code="pagination.displayMsg"/>',
				onSelectPage: function (pageNumber, pageSize) {//分页触发		
					 billBookManageInit_find(pageNumber,pageSize);
					 billBookManageInit_Search(); 
					 billBookManageInit_clearForm();
		         }
		});
	}
	function billBookManageInit_find(pageNumber,pageSize){
		$('#billBookManageInit_searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		billBookManageInit_Search();
	}
	function billBookManageInit_Search(){
    	$("#billBookManageInit_dataGrid").datagrid("loading");
    	$('#billBookManageInit_searchform').form('submit', {
			url:'${vat}/billBook/loadBillBookPageList.do',			
			onSubmit : function(){
				return $(this).form('enableValidation').form('validate');
			},
			success:function(result){
				var result = $.parseJSON(result);
				if(result.status=='0'){
				     $("#billBookManageInit_dataGrid").datagrid('loadData', result.data);
				}else if(result.status=='1'){
					//$.messager.alert('<spring:message code="system.alert"/>',result.erroMessage,'info');
				}
				$("#billBookManageInit_dataGrid").datagrid("loaded"); 
		     }
		});
    }
	
	function InitCombobox(){
		//------
		$("#billBookManageInit_EntityTypeSelect").combogrid({
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
		//ajax提交请求获取数据
		$.ajax({  
	        url: "${vat}/client/getClientInfoList.do",  
	        type: "POST",  
	        async: true,  
	        data: '', //不能直接写成 {id:"123",code:"tomcat"}  
	        dataType: "json",  
	        // contentType: "charset=utf-8",  
	        cache: false,  
	        success: function (result){
				billBookManageInit_clearForm();
				$('#billBookManageInit_EntityTypeSelect').combogrid('grid').datagrid('loadData',result.data);
	        } 
	    });
		
	}
	
    //客户关系添加
    function billBookManageInit_Add(){
    	billBookManageInit_clearForm();
		$("#billBookManageInit_add_dlg").dialog('open').dialog('setTitle',
		"<spring:message code='client.dialog.clientadd.title'/>");
		//$("#billBookManageInit_editform").form('load', {clientDate:myformatter(new Date())});
	} 
    function billBookManageInit_Edit(){
    	billBookManageInit_clearForm();
    	var row_list=$('#billBookManageInit_dataGrid').datagrid('getChecked');
    	//if(row_list!=''&&row_list!='undefined'){
    	if(true){
    		var id='';
    		
    		/* $.each(row_list,function(index,item){
     			if(index==0){
     				id = item.id;
     			}
    		}); */
    		//if(id!=''){        		
            if(true){
            	$.ajax({  
        	        url: "${vat}/billBook/loadModifyBillBook.do",  
        	        type: "GET",  
        	        async: true,  
        	        //data: urlString, //不能直接写成 {id:"123",code:"tomcat"}  
        	        data: 'id=1', //不能直接写成 {id:"123",code:"tomcat"} 
        	        dataType: "json",  
        	        cache: false,  
        	        success: function(result){
        	        	if(result.status=='0'){
        	        		$("#billBookManageInit_add_dlg").dialog('open').dialog('setTitle',
								"<spring:message code='client.dialog.clientedit.title'/>");
							$("#billBookManageInit_editform").form('load', result.data);
        	        	}else{
        	        		$.messager.alert('<spring:message code="system.alert"/>',result.erroMessage);
    						billBookManageInit_Search();
    						billBookManageInit_clearForm();
    						$('#billBookManageInit_add_dlg').dialog('close');
        	        	}
        	        } 
        	    });
        	}else{
        		$.messager.alert('<spring:message code="system.alert"/>','编辑的主键不能为空');
        	}
    	}
    }
    
    
	function billBookManageInit_Save(){
		$.messager.confirm("<spring:message code='client.datacommit.formsave.confirm.title'/>","<spring:message code='client.datacommit.formsave.confirm.text'/>",function(result){
			if (result){
				$('#billBookManageInit_editform').form('submit',{
					url:'${vat}/billBook/saveBillBook.do',	
					onSubmit : function() {
						return $(this).form('enableValidation').form('validate');
					},			
					success:function(result){
						var result = $.parseJSON(result);
						if(result.status=='0'){	
							billBookManageInit_clearForm();
							billBookManageInit_Search();
							$('#billBookManageInit_add_dlg').dialog('close');
							$.messager.alert('<spring:message code="system.alert"/>','保存成功');
						}
						
					} 
				});
			}
		});
		
		
	}
	//删除一条记录
	
	function billBookManageInit_Remove(){
		var row_list=$('#billBookManageInit_dataGrid').datagrid('getChecked');
		//var row=$('#billBookManageInit_dataGrid').datagrid('getSelected');
		//if(row_list!=''&&row_list!='undefined'){
		if(true){
			$.messager.confirm("<spring:message code='client.datacommit.delete.confirm.title'/>","<spring:message code='client.datacommit.delete.confirm.text'/>",function(result){ 
				if (result){
					var urlString="";
					$.each(row_list,function(index,item){ 
		                   if(index==0)   
		                	   urlString += "id="+item.id;  
		                    else  
		                    	urlString += "&id="+item.id;  
		                });
		        	//if(urlString!=''){
		        	if(true){
		        		$.ajax({  
		        	        url: "${vat}/billBook/removeBillBooks.do",  
		        	        type: "POST",  
		        	        async: true,  
		        	        //data: urlString, //不能直接写成 {id:"123",code:"tomcat"}  
		        	        data: 'id=1&id=2', //不能直接写成 {id:"123",code:"tomcat"} 
		        	        dataType: "json",  
		        	        cache: false,  
		        	        success: function(result){
		        	        	if(result.status=='0'){
		        	        		$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="system.delete.success.text"/>');
		        	        	}else{
		        	        		$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="system.delete.fail.text"/>');
		        	        	}
		        	        	billBookManageInit_Search();
		    					billBookManageInit_clearForm();
		        	        } 
		        	    });
		        	}
				}
	        });
        }
	}
	//清空dialog内容
	
	function billBookManageInit_clearForm(){
		$('#billBookManageInit_editform').form('clear');
	}
</script>
</html>