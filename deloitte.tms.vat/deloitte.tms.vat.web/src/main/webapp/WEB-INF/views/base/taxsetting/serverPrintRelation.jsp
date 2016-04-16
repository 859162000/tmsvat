<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/common/global.jsp"%>
<%@ taglib uri="/tags/input" prefix="input"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body class="easyui-layout" id="layoutid" style="overflow-y: hidden;">
	<div class="easyui-panel" title="East"
		data-options="fit:true,border:false"></div>
	<div data-options="region:'center',border:false"
		style="background-color: #E0ECFF">

		<form id="searchform" method="post">
			<table>
				<tr style="display: none">

					<td><input id="pageNumber" class="easyui-textbox" type="text"
						style="width: 0px;" name="pageNumber" value=""></input></td>
					<td><input id="pageSize" class="easyui-textbox" type="text"
						style="width: 0px;" name="pageSize" value=""></input></td>

				</tr>

			</table>
		</form>
		<div style="width: 100%; height: 100%">

			<table class="easyui-datagrid" id="dg"
				title="<spring:message code="contract.contractinfo"/>"
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



	<div id="dlg" class="easyui-dialog"
		style="width: 900px; height: 500px;" closed="true"
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

		<div style="margin: 20px 0;"></div>
		<form id="saveform" method="post" commandName="tmsMdContractInParam">
			<table>
				<tr style="display: none">

					<td><input id="projData" class="easyui-textbox" type="text"
						style="width: 0px;" name="projData"></input></td>

				</tr>

			</table>
			<table cellpadding="10">
				<tr>
					<td><spring:message code="contract.contractName" />:</td>
					<td><input class="easyui-textbox" type="text"
						style="width: 150px;" id="contractName" name="contractName"
						data-options="required:true"></input></td>
					<td><spring:message code="contract.contractNumber" />:</td>
					<td><input class="easyui-textbox" type="text"
						style="width: 150px;" name="contractNumber"
						data-options="required:true"></input></td>
					<td><spring:message code="contract.totalMoney" />:</td>
					<td><input class="easyui-textbox" type="text"
						style="width: 150px;" name="contractAmount"
						data-options="required:true"></input></td>
				</tr>
				<tr>
					<td><spring:message code="contract.projectName" />:</td>
					<td><input class="easyui-textbox" type="text"
						style="width: 150px;" id="projectName" name="projectName"></input></td>
					<td><spring:message code="contract.projectNumber" />:</td>
					<td><input class="easyui-textbox" type="text"
						style="width: 150px;" id="projectNumber" name="projectNumber"></input></td>
					<td><spring:message code="contract.projectTotalMoney" />:</td>
					<td><input class="easyui-textbox" type="text"
						style="width: 150px;" id="projectTotalMoney" name="projectTotalMoney"></input></td>
				</tr>
			</table>
			
			<div style="width: 100%; height: 100%">

		<table class="easyui-datagrid" id="projdg" title="项目"
			style="width: 860px; height: 400px"
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
		</form>
	</div>

	



</body>
<script type="text/javascript">
	$.extend($.messager.defaults,{
	    ok:'<spring:message code="confirm"/>',
	    cancel:'<spring:message code="cancel"/>'
	});	

	
	$(document).ready(function() {
		
		pageDataSettingInit();	
		
		initAddProjectTable();
		
		$('#searchform').form('load', {
			pageNumber : $('#dg').datagrid('options').pageNumber,
			pageSize : $('#dg').datagrid('options').pageSize
		});
		
		$('#searchform').form('submit', {
			url : 'serverPrint/search.do',
			success : function(result) {
				var result = $.parseJSON(result);
				$("#dg").datagrid('loadData', result);
				$("#dg").datagrid("loaded");
			}
		});
	});

	function pageDataSettingInit() {
		$('#dg').datagrid({
			url: '',			
			fitColumns: true,
			nowrap: false,
			pagination:true,
			fitColumns : true,
			rownumbers:true,		
			columns:[[
				{field : 'ck', checkbox : true,	width : 2}, //显示复选框   
				{field:'contractName',title:'<spring:message code="contract.contractName"/>',width:70,align:'center'},
				{field:'contractNumber',title:'<spring:message code="contract.contractNumber"/>',width:70,align:'center'},
				{field:'contractAmount',title:'<spring:message code="contract.totalMoney"/>',width:70,align:'center'},
				{field:'cname',title:'<spring:message code="contract.invoiceaMount"/>',width:40,align:'center'},
				{field:'ename',title:'<spring:message code="contract.accumulatedAmount"/>',width:40,align:'center'},
				//{field:'mobile',title:'<spring:message code="contract.projectName"/>',width:80,align:'center'},
				//{field:'remark',title:'<spring:message code="contract.projectNumber"/>',width:80,align:'center'},
				//{field:'effectDate',title:'<spring:message code="contract.projectTotalMoney"/>',width:80,align:'center'},
				{field:'quitDate',title:'<spring:message code="contract.projectTotalAmount"/>',width:40,align:'center'},
				{field:'quitDate',title:'<spring:message code="contract.projectAccumulatedAmount"/>',width:40,align:'center'},
				
			]],
			toolbar:[
			         {text:'<spring:message code="button.Add"/>',  
                	iconCls:'icon-add',  
                	handler:function(){  
                		addTo();  
                			}  
            		},'-',{  
	                text:'<spring:message code="button.Edit"/>',  
	                iconCls:'icon-edit',  
	                handler:function(){  
	                	edit();
	                }  
	            },'-',{  
	                text:'<spring:message code="button.Remove"/>',  
	                iconCls:'icon-remove',  
	                handler:function(){  
	                	remove();  
	                }
	            },'-'],
			
			onClickRow:function(index,data){
				var row = $('#dg').datagrid('getSelected');
				if (row){
					//loadSaveFormData(row);
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
		    	 //find(pageNumber,pageSize);
				 //Search(); 
				// clearSaveForm();
	         }
	
		});
	
	}

	function initAddProjectTable() {
		$('#projdg').datagrid({
			url : '',
			loadMsg : "项目列表",
			striped : true,//奇偶行颜色不同
			
			singleSelect : false, //多选
			collapsible : false,//可折叠  
			fitColumns : true,//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			nowrap : false,
			iconCls : 'icon icon-list',
			pagination : true, //显示分页 
			rownumbers : true, //显示行号
			idField : 'id',//主键字段		
			columns : [ [ {
				field : 'ck1',
				checkbox : true,
				width : 2
			}, //显示复选框   
			{
				field : 'projectName',
				title : "项目名称",
				width : 80,
				align : 'center'
			}, {
				field : 'projectNumber',
				title : "项目代码",
				width : 80,
				align : 'center'
			}, {
				field : 'projectTotalMoney',
				title : "项目总金额",
				width : 60,
				align : 'center',
			} ] ],
			toolbar : [ {
				text : "添加项目",
				iconCls : 'icon-add',
				handler : function() {
					addProj();
				}
			}, '-', {
				text : "删除",
				iconCls : 'icon-remove',
				handler : function() {
					delProj();
				}
			}, '-' ],
			/*onDblClickRow : function(index, data) {
				//alert("双击表格显示详情功能正在建设中。。。。");
				$('#projdg').datagrid('beginEdit', index);
				if (row){
					loadSaveFormData(row);
				}
			}, */
			onLoadSuccess : function() {
				$('#projdg').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
			}
		
		});

		//设置分页控件	
		var p2 = $('#projdg').datagrid('getPager');
		$(p2).pagination(
						{
							pageSize : 10,//每页显示的记录条数，默认为10           
							//pageList: [5,10,15,20],//可以设置每页记录条数的列表           
							beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
							afterPageText : '<spring:message code="pagination.afterPageText"/>',
							displayMsg : '<spring:message code="pagination.displayMsg"/>',
							onSelectPage : function(pageNumber, pageSize) {//分页触发		
								//find(pageNumber, pageSize);
								//getInvoiceList();
								//clearSaveForm();
							}
						});
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
			url:'${vat}/serverPrint/search.do',			
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(result) {
			   var result = $.parseJSON(result);
		       $("#dg").datagrid('loadData', result);		       
		       $("#dg").datagrid("loaded"); 
		     }
		});

    }
    
	function addTo(){
		$('#saveform').form('clear');
		//$('#layoutid').layout('expand', 'east');
		
		$("#dlg").dialog('open').dialog('setTitle', '新增合同');
	}
	
	function addProj(){
		
		//var row = $('#projdg').datagrid('getSelected');
		var data = $('#projdg').datagrid('getData');
		//alert(data.rows.length);		
			
			$('#projdg').datagrid('insertRow', 					
			{
			index:data.rows.length,
			row:{
			projectName:$('#projectName').val(),
			projectNumber:$('#projectNumber').val(),
			projectTotalMoney:$('#projectTotalMoney').val()}
			});
			
			//$('#projectName').attr("value","");
			$("input[name='projectName']").val("");
		
			//.datagrid('getRows').length-1;
			//$('#projdg').datagrid('selectRow',index);
		    //$('#projdg').datagrid('beginEdit',data.rows.length - 1);
	}
	
    function delProj(){
		
	}
	
	function edit(){
		var row = $('#dg').datagrid('getSelected');		
		if(row!=null){
			var userId = row.userId;	
			 $.ajax({
                 url:"usermaintain/getuserbyid.do?userId="+userId,
                 cache:false,
                 dataType : "json",
                 success : function(result) {                	               	
                	 $('#saveform').form('load', result);
                	 $("#dlg").dialog('open').dialog('setTitle',
             		'<spring:message code="usermaintain.edit"/>'); 
                	 $('#usernameid').textbox('disable');	
                 }
             });	
		}else{
			$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="noselectrecord"/>');
		}
	}
	
	function save(){		
		$('#saveform').form('submit', {			
			url:'serverPrint/save.do',	
			onSubmit : function() {
				console.log($('#projdg').datagrid('getData'));
				$('#projData').attr("value",$('#projdg').datagrid('getData'));
				return $(this).form('enableValidation').form('validate');
			},			
			success:function(result){
				var result = $.parseJSON(result);				
				 if(result.success){
					clearSaveForm(); 
					$('#dlg').dialog('close');
				} 
				$.messager.alert('<spring:message code="system.alert"/>',result.msg);
				//Search();
			} 
			
			
		});
	}
	
	function remove(){
		var row = $('#dg').datagrid('getSelected');	
		if(row!=null){
			var uuid = row.userId;	
			var arguments = row.userId;
		    $.messager.confirm('<spring:message code="system.alert"/>','<spring:message code="usermaintain.delete.confirm" arguments="'+arguments+'"/>',function(result){  
				  if (result){  			      
				      $.ajax({
		                    url:"usermaintain/delete.do?uuid="+uuid,
		                    dataType : "json",
		                    cache:false,
		                    success : function(result) {	                    	
		                       /*  if (result.success) {	                        		                        	
		                        	Search();
		        					clearSaveForm(); 
		                        }  */
		                        $.messager.alert('<spring:message code="system.alert"/>',result.msg);
		                    }
		                });
				     
				  }
			  });  		
		}else{
			$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="noselectrecord"/>');
		}
		
	}
		
	function clearSaveForm(){
		$('#saveform').form('clear');
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
		$('#saveform').form('load', {
			userid:row.userid,
			name  :row.name,
			email :row.email,
			fax   :row.fax,
			tel   :row.tel,
			createby:row.createby,
			createdate:row.createdate,
			updateby:row.updateby,
			updatedate:row.updatedate,
			appuseruuid:row.appuseruuid
		});
	}

	
	function pageData(list,total){
	     var obj=new Object(); 
	     obj.total=total; 
	     obj.rows=list; 
	     return obj; 
	 }
	
</script>
</html>