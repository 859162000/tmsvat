<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body class="easyui-layout" style="overflow-y: hidden" fit="true" scroll="no">
    <div region="north" split="true" border="false" style="overflow:hidden; height: 99%;">  
        <div class="easyui-panel" title="<spring:message code='sourcesubject.quicksearch'/>" style="background-color: #E0ECFF">		
		    <form id="searchForm" method="post"  scroll="no">
		    	<table cellpadding="10"   >
		    		<tr>
	    				<td><spring:message code='sourcesubject.structure'/>:</td>
		    			<td>
		    				<select id="sourceSubjectManageInit_code" name="code" class="easyui-combogrid"  style="width:200px;"></select>
		    			</td>
		    			<td>
		    			   <div style="text-align:center;padding:10px">
		    	             <a href="#" id="searchbtn"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px" onclick="search()"><spring:message code='client.search.button.find'/></a>
		                   </div>    			   
		    			</td>
		    			<td>
		    			   <div style="text-align:center;padding:10px">
							<a href="#" class="easyui-linkbutton" style="width:80px" onclick="clearSearchForm()"><spring:message code="button.Clear" /></a>
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
	         <table class="easyui-datagrid" id="dg" title="<spring:message code='sourcesubject.structure'/>" style="width:100%;height:40%" data-options="					
					singleSelect:true,
					autoRowHeight:false,
					pagination:true,
					pageSize:10,
					remoteSort:false,
				    multiSort:true
					">			 
		   </table> 
        <table class="easyui-datagrid" id="dgPart" title="<spring:message code='sourcesubject.structure.part'/>" style="width:100%;height:100%" data-options="					
			singleSelect:true,
			autoRowHeight:false,
			pagination:true,
			pageSize:10,
			remoteSort:false,
		    multiSort:true
			">			 
  		 </table> 
	    <form id="partForm" method="post"  scroll="no">
		<input id="sourceSubjectCodePart" name="sourceSubjectCodePart" type="hidden" value=""/>
	       <tr style="display:none">
	   		   <td><input id="pageNumber" class="easyui-textbox" type="text" style="width:0px;" name="pageNumber" value=""></input></td>
	   		   <td><input id="pageSize" class="easyui-textbox" type="text" style="width:0px;" name="pageSize" value=""></input></td>
		   </tr>  
        </form>  
    </div>
    <div data-options="region:'center',border:false" style="background-color: #E0ECFF">
	   <div id="sourceSubjectManageInit_add_dlg" class="easyui-dialog"
			style="width: 400px; height: 400px;" closed="true"
			buttons="#sourceSubjectManageInit_add_dlg-buttons" modal="true">
				<form id="sourceSubjectManageInit_editform" class="easyui-form" method="post" data-options="novalidate:true" commandsName="sourceSubjectManageForm">
					<table>
		    		<tr>
	    				<td><font color=red>*</font><spring:message code='sourcesubject.structure.code'/>:</td>
		    			<td>
		    				<input  type="hidden" name="id"></input>
		    				<input class="easyui-textbox" type="text" name="code" 
		    					missingMessage=<spring:message code='client.model.missMsg'/> data-options="required:true"></input>
		    			</td>
		    		</tr>
<!-- 		    		<tr> -->
<%-- 		    			<td><font color=red>*</font><spring:message code='sourcesubject.structure.desc'/>:</td> --%>
<!-- 		    			<td> -->
<!-- 		    				<input class="easyui-textbox"  -->
<!-- 		    					type="text" name="clientCode"  -->
<!-- 		    					missingMessage=<spring:message code='client.model.missMsg'/> -->
<!-- 		    					data-options="required:true"></input> -->
<!-- 		    			</td> -->
<!-- 		    		</tr> -->
		    	</table>
		    </form>
		</div>
		<div id="sourceSubjectManageInit_add_dlg-buttons">
			<a  href="javascript:void(0)" class="easyui-linkbutton"	iconCls="icon-ok" onclick="sourceSubjectManageInit_Save()">
					<spring:message	code="button.Save" />
			</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#sourceSubjectManageInit_add_dlg').dialog('close')"><spring:message
					code="button.Close" />
			</a>
		</div>		
    </div>
    
    <div data-options="region:'center',border:false" style="background-color: #E0ECFF">
	   <div id="sourceSubjectManagePart_add_dlg" class="easyui-dialog"
			style="width: 400px; height: 400px;" closed="true"
			buttons="#sourceSubjectManagePart_add_dlg-buttons" modal="true">
				<form id="sourceSubjectManagePart_editform" class="easyui-form" method="post" data-options="novalidate:true" commandsName="sourceSubjectManageFormPart">
					<table>
		    		<tr>
	    				<td><font color=red>*</font><spring:message code='sourcesubject.structure.part.code'/>:</td>
		    			<td>
		    				<input  type="hidden" name="id"></input>
		    				<input class="easyui-textbox" type="text" name="code" 
		    					missingMessage=<spring:message code='client.model.missMsg'/> data-options="required:true"></input>
		    			</td>
		    		</tr>
<!-- 		    		<tr> -->
<%-- 		    			<td><font color=red>*</font><spring:message code='sourcesubject.structure.desc'/>:</td> --%>
<!-- 		    			<td> -->
<!-- 		    				<input class="easyui-textbox"  -->
<!-- 		    					type="text" name="clientCode"  -->
<!-- 		    					missingMessage=<spring:message code='client.model.missMsg'/> -->
<!-- 		    					data-options="required:true"></input> -->
<!-- 		    			</td> -->
<!-- 		    		</tr> -->
		    	</table>
		    </form>
		</div>
		<div id="sourceSubjectManagePart_add_dlg-buttons">
			<a  href="javascript:void(0)" class="easyui-linkbutton"	iconCls="icon-ok" onclick="sourceSubjectManagePart_Save()">
					<spring:message	code="button.Save" />
			</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#sourceSubjectManagePart_add_dlg').dialog('close')"><spring:message
					code="button.Close" />
			</a>
		</div>		
    </div>
    
</body>  
<script type="text/javascript">
	$(document).ready(function(){
		pageDataSettingInit();
		InitCombobox();	
		$('#searchForm').form('load', {
			pageNumber: $('#dg').datagrid('options').pageNumber,
			pageSize: $('#dg').datagrid('options').pageSize
		});
		search();
	});
	//页面表格绑定初始化
	function pageDataSettingInit(){
		$('#dg').datagrid({
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
							{field:'id',hidden:true,width:80,align:'center'}
			                ]],
			columns:[[
				 {field:'sourceSubjectCode',title:"来源数据结构编码",width:80,align:'center'},
				 {field:'useStatus',title:"启用状态",width:80,align:'center'}
			]],
			toolbar:[
			         {text:"<spring:message code='button.Add'/>",  
                	iconCls:'icon-add',  
                	handler:function(){  
                			sourceSubjectManageInit_Add();  
                			}  
            		},'-',{  
            			text:"<spring:message code='button.Edit'/>",  
	                iconCls:'icon-edit',  
	                handler:function(){  
	                	sourceSubjectManageInit_Edit();
	                }  
	            },'-',{  
	            	text:"<spring:message code='button.Remove'/>",  
	                iconCls:'icon-remove',  
	                handler:function(){  
	                	sourceSubjectManageInit_Remove();  
	                }  
	            },'-'],
	            onDblClickRow:function(index,data){
					alert("双击表格显示详情功能正在建设中。。。。");
				/* var row = $('#dg').datagrid('getSelected');
				if (row){
					loadSaveFormData(row);
				} */
			},
			onLoadSuccess:function(){  
                $('#dg').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
            },
            onClickRow: function (index, row) {  //easyui封装好的事件（被单击行的索引，被单击行的值）
                //需要传递的值
                $("#sourceSubjectCodePart").val( row["sourceSubjectCode"]);
                var sourceSubjectCode = row["sourceSubjectCode"];
                var useStatus = row["useStatus"];
                $('#partForm').form('load', {
        			pageNumber: $('#dgPart').datagrid('options').pageNumber,
        			pageSize: $('#dgPart').datagrid('options').pageSize
        		});
                $('#partForm').form('submit', {
        			url:'${vat}/sourceSubject/loadSourceSubjectPartList.do',
        			onSubmit : function(){
        				return $(this).form('enableValidation').form('validate');
        			},
        			success:function(result){
        				var result = $.parseJSON(result);
        				if(result.status=='0'){
        				     $("#dgPart").datagrid('loadData', result.data);
        				}else if(result.status=='1'){
        					//$.messager.alert('<spring:message code="system.alert"/>',result.erroMessage,'info');
        				}
        				$("#dgPart").datagrid("loaded"); 
        		     }
        		});
                }
		});
		$('#dgPart').datagrid({
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
							{field:'id',hidden:true,width:80,align:'center'}
			                ]],
			columns:[[
				 {field:'sourceSubjectCode',title:"段编码",width:80,align:'center'},
				 {field:'useStatus',title:"段说明",width:80,align:'center'}
			]],
			toolbar:[
			         {text:"<spring:message code='button.Add'/>",  
                	iconCls:'icon-add',  
                	handler:function(){  
                			sourceSubjectManagePart_Add();  
                			}  
            		},'-',{  
            			text:"<spring:message code='button.Edit'/>",  
	                iconCls:'icon-edit',  
	                handler:function(){  
	                	sourceSubjectManagePart_Edit();
	                }  
	            },'-',{  
	            	text:"<spring:message code='button.Remove'/>",  
	                iconCls:'icon-remove',  
	                handler:function(){  
	                	sourceSubjectManagePart_Remove();  
	                }  
	            },'-'],
	            onDblClickRow:function(index,data){
					alert("双击表格显示详情功能正在建设中。。。。");
				/* var row = $('#dg').datagrid('getSelected');
				if (row){
					loadSaveFormData(row);
				} */
			},
			onLoadSuccess:function(){  
                $('#dgPart').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
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
				 search(); 
				 clearSaveForm();
	         }
	
		});	
		//设置分页控件	
		var p = $('#dgPart').datagrid('getPager'); 
		$(p).pagination({           
			pageSize: 10,//每页显示的记录条数，默认为10           
			//pageList: [5,10,15,20],//可以设置每页记录条数的列表           
			beforePageText: '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
			afterPageText: '<spring:message code="pagination.afterPageText"/>',           
			displayMsg: '<spring:message code="pagination.displayMsg"/>',
			onSelectPage: function (pageNumber, pageSize) {//分页触发		
		    	 findPart(pageNumber,pageSize);
				 searchPart(); 
				 clearSaveForm();
	         }
	
		});
	}
	function find(pageNumber,pageSize){
		$('#searchForm').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		search();
	}
	function findPart(pageNumber,pageSize){
		$('#partForm').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		search();
	}
	function search(){
  	$("#dg").datagrid("loading");
    	$('#searchForm').form('submit', {
			url:'${vat}/sourceSubject/loadSourceSubjectPageList.do',			
			onSubmit : function(){
				return $(this).form('enableValidation').form('validate');
			},
			success:function(result){
				var result = $.parseJSON(result);
				if(result.status=='0'){
				     $("#dg").datagrid('loadData', result.data);
				}else if(result.status=='1'){
					//$.messager.alert('<spring:message code="system.alert"/>',result.erroMessage,'info');
				}
				$("#dg").datagrid("loaded"); 
		     }
		});
    }
	function searchPart(){
    	$("#dgPart").datagrid("loading");
    	$('#partForm').form('submit', {
			url:'${vat}/sourceSubject/loadSourceSubjectPartList.do',			
			onSubmit : function(){
				return $(this).form('enableValidation').form('validate');
			},
			success:function(result){
				var result = $.parseJSON(result);
				if(result.status=='0'){
				     $("#dgPart").datagrid('loadData', result.data);
				}else if(result.status=='1'){
					//$.messager.alert('<spring:message code="system.alert"/>',result.erroMessage,'info');
				}
				$("#dgPart").datagrid("loaded"); 
		     }
		});
    }
	
	function InitCombobox(){
		//------
		$("#sourceSubjectManageInit_EntityTypeSelect").combogrid({
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
				clearForm();
				$('#sourceSubjectManageInit_EntityTypeSelect').combogrid('grid').datagrid('loadData',result.data);
	        } 
	    });
		
	}
	
    //客户关系添加
    function sourceSubjectManageInit_Add(){
    	clearForm();
		$("#sourceSubjectManageInit_add_dlg").dialog('open').dialog('setTitle',
		"<spring:message code='client.dialog.clientadd.title'/>");
		//$("#sourceSubjectManageInit_editform").form('load', {clientDate:myformatter(new Date())});
	} 
    function sourceSubjectManageInit_Edit(){
    	clearForm();
    	var row_list=$('#dg').datagrid('getChecked');
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
        	        url: "${vat}/sourceSubject/loadModifySourceSubject.do",  
        	        type: "GET",  
        	        async: true,  
        	        //data: urlString, //不能直接写成 {id:"123",code:"tomcat"}  
        	        data: 'id=1', //不能直接写成 {id:"123",code:"tomcat"} 
        	        dataType: "json",  
        	        cache: false,  
        	        success: function(result){
        	        	if(result.status=='0'){
        	        		alert("bb");        	        	
     	        		$("#sourceSubjectManageInit_add_dlg").dialog('open').dialog('setTitle',
								"<spring:message code='client.dialog.clientedit.title'/>");
							$("#sourceSubjectManageInit_editform").form('load', result.data);
        	        	}else{
$.messager.alert('<spring:message code="system.alert"/>',result.erroMessage);
							search();
    						clearForm();
    						$('#sourceSubjectManageInit_add_dlg').dialog('close');
        	        	}
        	        } 
        	    });
        	}else{
        		$.messager.alert('<spring:message code="system.alert"/>','编辑的主键不能为空');
        	}
    	}
    }
    
	function sourceSubjectManageInit_Save(){
		$.messager.confirm("<spring:message code='client.datacommit.formsave.confirm.title'/>","<spring:message code='client.datacommit.formsave.confirm.text'/>",function(result){
			if (result){
				$('#sourceSubjectManageInit_editform').form('submit',{
					url:'${vat}/sourceSubject/saveSourceSubject.do',	
					onSubmit : function() {
						return $(this).form('enableValidation').form('validate');
					},			
					success:function(result){
						var result = $.parseJSON(result);
						if(result.status=='0'){	
							clearForm();
							search();
							$('#sourceSubjectManageInit_add_dlg').dialog('close');
							$.messager.alert('<spring:message code="system.alert"/>','保存成功');
						}
						
					} 
				});
			}
		});
		
		
	}
	
	//删除一条记录
	function sourceSubjectManageInit_Remove(){
		var row_list=$('#dg').datagrid('getChecked');
		//var row=$('#dg').datagrid('getSelected');
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
		        	        url: "${vat}/sourceSubject/removeSourceSubjects.do",  
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
		        	        	search();
		    					clearForm();
		        	        } 
		        	    });
		        	}
				}
	        });
        }
	}
		
	//清空dialog内容
	function clearForm(){
		$('#sourceSubjectManageInit_editform').form('clear');
	}
	
	function clearSearchForm() {
		$('#searchForm').form('clear');
	}
	
	function clearPartForm() {
		$('#sourceSubjectManagePart_editform').form('clear');
	}
	
	   //客户关系添加
    function sourceSubjectManagePart_Add(){
    	clearPartForm();
		$("#sourceSubjectManagePart_add_dlg").dialog('open').dialog('setTitle',
		"<spring:message code='client.dialog.clientadd.title'/>");
		//$("#sourceSubjectManagePart_editform").form('load', {clientDate:myformatter(new Date())});
	} 
    function sourceSubjectManagePart_Edit(){
    	clearPartForm();
    	var row_list=$('#dg').datagrid('getChecked');
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
        	        url: "${vat}/sourceSubject/loadModifySourceSubject.do",  
        	        type: "GET",  
        	        async: true,  
        	        //data: urlString, //不能直接写成 {id:"123",code:"tomcat"}  
        	        data: 'id=1', //不能直接写成 {id:"123",code:"tomcat"} 
        	        dataType: "json",  
        	        cache: false,  
        	        success: function(result){
        	        	if(result.status=='0'){
        	        		alert("bb");        	        	
     	        		$("#sourceSubjectManagePart_add_dlg").dialog('open').dialog('setTitle',
								"<spring:message code='client.dialog.clientedit.title'/>");
							$("#sourceSubjectManagePart_editform").form('load', result.data);
        	        	}else{
$.messager.alert('<spring:message code="system.alert"/>',result.erroMessage);
							search();
							clearPartForm();
    						$('#sourceSubjectManagePart_add_dlg').dialog('close');
        	        	}
        	        } 
        	    });
        	}else{
        		$.messager.alert('<spring:message code="system.alert"/>','编辑的主键不能为空');
        	}
    	}
    }
    
	function sourceSubjectManagePart_Save(){
		$.messager.confirm("<spring:message code='client.datacommit.formsave.confirm.title'/>","<spring:message code='client.datacommit.formsave.confirm.text'/>",function(result){
			if (result){
				$('#sourceSubjectManagePart_editform').form('submit',{
					url:'${vat}/sourceSubject/saveSourceSubjectPart.do',	
					onSubmit : function() {
						return $(this).form('enableValidation').form('validate');
					},			
					success:function(result){
						var result = $.parseJSON(result);
						if(result.status=='0'){	
							clearPartForm();
							search();
							$('#sourceSubjectManagePart_add_dlg').dialog('close');
							$.messager.alert('<spring:message code="system.alert"/>','保存成功');
						}
						
					} 
				});
			}
		});
		
		
	}
	
	//删除一条记录
	function sourceSubjectManagePart_Remove(){
		var row_list=$('#dg').datagrid('getChecked');
		//var row=$('#dg').datagrid('getSelected');
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
		        	        url: "${vat}/sourceSubject/removeSourceSubjectsPart.do",  
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
		        	        	search();
		        	        	clearPartForm();
		        	        } 
		        	    });
		        	}
				}
	        });
        }
	}	
</script>
</html>