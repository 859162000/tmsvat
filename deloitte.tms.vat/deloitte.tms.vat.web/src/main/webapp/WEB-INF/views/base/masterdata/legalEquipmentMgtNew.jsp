<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/common/global.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<script type="text/javascript">
$(function(){  
    var addr_tree = $("#legalEntityTree").tree({  
        url:'',  
        method:"post",  
        onSelect:function(node){}  
    });  
}) 
	$(function() {
		$('#taxEntityAndEquipment_dataGrid').datagrid(
				{
					url : '',
					fitColumns : true,
					nowrap : false,
					pagination : true,
					rownumbers : true,
					singleSelect : false,
					fitColumns : true,
					striped : true,
					idField : 'id', //主键字段  
					columns : [ [
					//显示复选框        
					{
						field : 'ck',
						checkbox : true,
						width : 2
					}, {
						field : 'legalEntityName',
						title : '纳税主体名称',
						width : 80,
						align : 'center'
					}, {
						field : 'legalEntityCode',
						title : '纳税主体编号',
						width : 100,
						align : 'center',

					}, {
						field : 'legalEntityType',
						title : '纳税主体类型',
						width : 80,
						align : 'center'
					}, {
						field : 'licenseName',
						title : '纳税人识别号',
						width : 80,
						align : 'center'
					}, ] ],
					toolbar : [ {
						text : '添加纳税主体',
						iconCls : 'icon-add',
						handler : function() {
							addEntity();
						}
					}, '-', {
						text : '删除纳税实体',
						iconCls : 'icon-remove',
						handler : function() {
							removeEntity();
						}
					}, '-' ],
					onLoadSuccess : function() {
						$('#taskScheduleInit_dataGrid').datagrid(
								'clearSelections')
					},
					onClickRow : function(index, data) {
						var row = $('#taskScheduleInit_dataGrid').datagrid(
								'getSelected');
						if (row) {
							//loadSaveFormData(row);
						}
					}
				});
		//设置分页控件	
		var p = $('#taxEntityAndEquipment_dataGrid').datagrid('getPager');
		$(p)
				.pagination(
						{
							pageSize : 10,//每页显示的记录条数，默认为10           
							//pageList: [10,20,30],//可以设置每页记录条数的列表           
							beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
							afterPageText : '<spring:message code="pagination.afterPageText"/>',
							displayMsg : '<spring:message code="pagination.displayMsg"/>',
							onSelectPage : function(pageNumber, pageSize) {//分页触发		
								//find(pageNumber, pageSize);
								//Search();
							}
						});
		InitTreeData();
	});

	function removeEntity() {
		if (($('#taxEntityAndEquipment_dataGrid').datagrid('getChecked').length == 0)) {
			$.messager.alert("操作提示", "请选择需要删除的数据");
		} else {
			$.messager
					.confirm(
							"<spring:message code='client.datacommit.delete.confirm.title'/>",
							"<spring:message code='client.datacommit.delete.confirm.text'/>",
							function(result) {
								if (result) {
									var row_list = $(
											'#taxEntityAndEquipment_dataGrid')
											.datagrid('getChecked');
									
									//var row=$('#clientManageInit_dataGrid').datagrid('getSelected');
									if (row_list) {
										var urlString = "";
										$.each(row_list, function(index, item) {
											if (index == 0)
												urlString += "id=" + item.id;
											else
												urlString += "&id=" + item.id;
										});
										if (urlString != '') {
											$('#legalEntityAndEquipmentSearchForm').form('load', {
												equipmentId : $('#legalEntityTree').tree('getSelected').id,
												ids:urlString

											});

											$('#legalEntityAndEquipmentSearchForm')
											.form(
													'submit',
													{
														url : 'legalEquipment/removeLegalEntityByCurrentPrinter.do',
														onSubmit : function() {
															return $(this).form('enableValidation').form(
																	'validate');
														},
														success : function(result) {
															//alert(1);
															//var results = $.parseJSON(result);
															loadTableBySelect();//刷新表格
														}
													});
											
										/* 	$
													.ajax({
														url : "${vat}/legalEquipment/removeLegalEntityByCurrentPrinter.do",
														type : "POST",
														async : true,
														data : urlString, //不能直接写成 {id:"123",code:"tomcat"}  
														dataType : "json",
														// contentType: "charset=utf-8",  
														cache : false,
														success : function(
																result) {
															Search();
															//clearSaveForm();
														}
													}); */
										}
									}
								}

							});
		}
	}

	function initLegalEntity() {
		$('#legalEntity_dataGrid').datagrid({
			url : '',
			fitColumns : true,
			nowrap : false,
			pagination : true,
			rownumbers : true,
			singleSelect : false,
			fitColumns : true,
			striped : true,
			idField : 'id', //主键字段  
			columns : [ [
			//显示复选框   
			{
				field : 'ck',
				checkbox : true,
				width : 2
			}, {
				field : 'legalEntityName',
				title : '纳税主体名称',
				width : 80,
				align : 'center'
			}, {
				field : 'legalEntityCode',
				title : '纳税主体编号',
				width : 100,
				align : 'center',

			}, {
				field : 'legalEntityType',
				title : '纳税主体类型',
				width : 80,
				align : 'center'
			}, {
				field : 'licenseName',
				title : '纳税人识别号',
				width : 80,
				align : 'center'
			}, ] ],
			toolbar : [ {
				text : '选择需要添加的纳税实体',
				iconCls : 'icon-add',
				handler : function() {
					addEntityInDialog();
				}
			}, '-' ],
			onLoadSuccess : function() {
				$('#legalEntity_dataGrid').datagrid('clearSelections')
			},
			onClickRow : function(index, data) {
				var row = $('#legalEntity_dataGrid').datagrid('getSelected');
				if (row) {
					//loadSaveFormData(row);
				}
			}
		});
		//设置分页控件	
		var p = $('#legalEntity_dataGrid').datagrid('getPager');
		$(p)
				.pagination(
						{
							pageSize : 10,//每页显示的记录条数，默认为10           
							//pageList: [10,20,30],//可以设置每页记录条数的列表           
							beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
							afterPageText : '<spring:message code="pagination.afterPageText"/>',
							displayMsg : '<spring:message code="pagination.displayMsg"/>',
							onSelectPage : function(pageNumber, pageSize) {//分页触发		
								//find(pageNumber, pageSize);
								//Search();
							}
						});
	}

	function InitTreeData() {
		$('#legalEntityTree').tree({
			url : 'legalEquipment/createtree.do',
			checkbox : false,
			//animate : true,
			onClick:function(node){
				if($('#legalEntityTree').tree('isLeaf',node.target)){
				//	alert(0);
					loadTableBySelect();
			        //treeInfo为$("#id")
			  }else{
				//  alert(2)
			   if(node.state=='closed'){//展开 收缩
				   $('#legalEntityTree').tree('expand',node.target);
			  }else{
				  $('#legalEntityTree').tree('collapse',node.target);
			  }
			  return;//如果是父节点，不往下执行点击事件
			 }
				
	          //  alert(node.id);
	          },
	          onContextMenu: function(e, node){  
                  e.preventDefault();  
                  $('#legalEntityTree').tree('select', node.target);  
                
              },  

			onLoadSuccess : function() {
				/* 	 var row = $('#dg').datagrid('getSelected');
					 if(row){
						 var roleid = row.id;					
						 $.post('rolemaintain/getrolefrmfunction.do', {
				        		roleid : roleid
							}, function(result) {
								if(result.success){	
									
									var array = result.frmuuis;  
									 for(var i=0;i<array.length;i++){					
										 var node = $('#tt').tree('find',array[i]);
										 if($('#tt').tree('isLeaf',node.target)){  
											 $('#tt').tree('check',node.target);
										   }  								
									 }  
									
								}
								 					

							}, 'json');  
					 }
				 */
			}

		});
	}
	$('#legalEntityTree').tree({
		
		onClick : function(node) {
			//alert(node.text); // alert node text property when clicked
		
		}
	});

	function addEntityInDialog() {
		if (($('#legalEntity_dataGrid').datagrid('getChecked').length == 0)
				|| ($('#legalEntity_dataGrid').datagrid('getChecked').length > 1)) {
			$.messager.alert("操作提示", "请选择需要添加的数据");
		} else {
			$.messager
					.confirm(
							"<spring:message code='提示'/>",
							"<spring:message code='确认添加吗？'/>",
							function(result) {

								if (result) {
									var row_list = $('#legalEntity_dataGrid')
											.datagrid('getChecked');
									//var row=$('#clientManageInit_dataGrid').datagrid('getSelected');
									if (row_list) {
										var urlString = "";
										$.each(row_list, function(index, item) {
											if (index == 0)
												urlString += "id=" + item.id;
											else
												urlString += "&id=" + item.id;
										});
										if (urlString != '') {
											
											$('#legalEntityAndEquipmentSearchForm').form('load', {
												equipmentId : $('#legalEntityTree').tree('getSelected').id,
												ids:urlString

											});

											$('#legalEntityAndEquipmentSearchForm')
											.form(
													'submit',
													{
														url : 'legalEquipment/addLegalEntityToParentTable.do',
														onSubmit : function() {
															return $(this).form('enableValidation').form(
																	'validate');
														},
														success : function(result) {
															var results = $.parseJSON(result);
															if(results.success=='false'){
																$.messager.alert("操作提示",results.msg,"info"); 
															}
															loadTableBySelect();//刷新表格
														}
													});
											
											/* $
													.ajax({
														url : "${vat}/legalEquipment/addLegalEntityToParentTable.do",
														type : "POST",
														async : true,
														data : urlString, //不能直接写成 {id:"123",code:"tomcat"}  
														dataType : "json",
														// contentType: "charset=utf-8",  
														cache : false,
														success : function(
																result) {
															Search();
															//	clearSaveForm();
														}
													}); */
										}
									}
								}

							});
		}
	}

	function addEntity() {
		$("#legalEntity_dlg").dialog('open').dialog('setTitle', "新增纳税实体");

		initLegalEntity();
		loadDataInLegalEntityDialog();
	}

	function loadDataInLegalEntityDialog() {

		//alert($('#legalEntityTree').tree('getSelected').id);
		$('#legalEntityAndEquipmentSearchForm').form('submit', {
			url : 'legalEquipment/loadTmsMdLegalEntityBesidCurrentTable.do',
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(result) {
				//alert(1);
				var results = $.parseJSON(result);
				$("#legalEntity_dataGrid").datagrid('loadData', results);
				$("#legalEntity_dataGrid").datagrid("loaded");
			}
		});
	}
	function Search() {

	}
	function hello() {
		alert($('#legalEntityTree').tree('getSelected').id);
	}
	function loadTableBySelect() {
		$('#legalEntityAndEquipmentSearchForm').form('load', {
			equipmentId : $('#legalEntityTree').tree('getSelected').id

		});

		//alert($('#legalEntityTree').tree('getSelected').id);
		$('#legalEntityAndEquipmentSearchForm')
				.form(
						'submit',
						{
							url : 'legalEquipment/loadTmsMdLegalEquipment.do',
							onSubmit : function() {
								return $(this).form('enableValidation').form(
										'validate');
							},
							success : function(result) {
								//alert(1);
								var results = $.parseJSON(result);
								
								$("#taxEntityAndEquipment_dataGrid").datagrid(
										'uncheckAll');
								$("#taxEntityAndEquipment_dataGrid").datagrid(
										'loadData', results);
								$("#taxEntityAndEquipment_dataGrid").datagrid(
										"loaded");
							}
						});
	}
	
	$('#legalEntityTree').tree({
	    onClick: function(node){
	      //  alert(node.text);  // alert node text property when clicked
	    }
	});
</script>
<body class="easyui-layout">
	<div data-options="region:'west',split:true,title:'打印终端'"
		style="width: 20%;">

		<ul id="legalEntityTree" class="easyui-tree"
			>

		</ul>
	</div>
	<div data-options="region:'center',title:'纳税主体列表'"
		style="width: 30%; background-color: #E0ECFF;">
		<div>
			<form id="legalEntityAndEquipmentSearchForm" method="post" commandName="tmsMdLegalEquipmentInParam">
				<table>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="display: none">
					<td><input id="equipmentId" class="easyui-textbox"
							type="text" style="width: 0px;" name="equipmentId" value=""></input></td>
						<td><input id="legalEntityId" class="easyui-textbox"
							type="text" style="width: 0px;" name="legalEntityId" value=""></input></td>
						<td><input id="pageNumber" class="easyui-textbox" type="text"
							style="width: 0px;" name="pageNumber" value=""></input></td>
						<td><input id="pageSize" class="easyui-textbox" type="text"
							style="width: 0px;" name="pageSize" value=""></input>
							<input id="ids" class="easyui-textbox" type="text"
							style="width: 0px;" name="ids" value=""></input>
							</td>
						<td></td>
					</tr>
					<tr>
						<td>纳税主体名称</td>
						<td><input id="legalEntityName" class="easyui-textbox"
							type="text" style="width: 150px;" name="legalEntityName" value=""></input>
						</td>
						<td>纳税主体编号</td>
						<td><input id="legalEntityName" class="easyui-textbox"
							type="text" style="width: 150px;" name="legalEntityName" value=""></input>
						</td>
						<td>
						<div style="text-align: center; padding: 10px">
								<a href="#" id="searchbtn" class="easyui-linkbutton"
									data-options="iconCls:'icon-search'" style="width: 120px"
									onclick="Search()">查询</a>
							</div>
						</td>
					</tr>
					
				</table>
			</form>
		</div>
		<div style="width: 100%; height: 100%">
			<table class="easyui-datagrid" id="taxEntityAndEquipment_dataGrid"
				style="width: 100%; height: 88%"
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


		<div id="legalEntity_dlg" class="easyui-dialog"
			style="width: 800px; height: 400px;" closed="true">
			<table class="easyui-datagrid" id="legalEntity_dataGrid"
				title="<spring:message code='taskSchedule.title'/>"
				style="width: 100%; height: 100%"
				data-options="					
					singleSelect:true,
					autoRowHeight:false,				
					remoteSort:false,
				    multiSort:true	
					">
			</table>
		</div>
	</div>
</body>
</html>