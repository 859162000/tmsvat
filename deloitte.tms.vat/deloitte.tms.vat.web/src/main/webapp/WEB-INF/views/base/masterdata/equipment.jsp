<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/tags/input" prefix="input"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<%@ include file="/common/global.jsp" %>
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<div data-options="region:'west',split:true" title="树结构" style="width: 25%;">
		<div style="margin: 10px 5px;">
			<a href="#" class="easyui-linkbutton" style="width:80px" onclick="collapseAll()"><spring:message
						code="button.CollapseAll" /></a> 
			<a href="#" class="easyui-linkbutton" style="width:80px" onclick="expandAll()"><spring:message
						code="button.ExpandAll" /></a> 
		</div>
		<div class="easyui-panel" style="padding: 5px, width:100%; height: 93%"">
			<ul id="equipmentTree" class="easyui-tree"></ul>
		</div>
	</div>
	<div data-options="region:'center',iconCls:'icon-ok'"
		title="查询条件" >
		    <form id="searchform" method="post" >
		    	<table>
		    		<tr>
		    			<td align="right">终端名称:</td>
		    			<td>
		    				<input id="searchform_equipmentName" name="id" class="easyui-combogrid" type="text" style="width:150px;" ></input>
		    			</td>
	    			</tr>
	    			<tr>
		    			<td>
		    	             <a href="#" id="searchbtn"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px;" onclick="searchEquipment()">查询</a>
		    			</td>
		    			<td>
	    					<a href="#" class="easyui-linkbutton" style="width:80px" onclick="clearSearchForm()">重置</a>
		    			</td>
		    		</tr>
		    		<tr style="display:none">
		    		   <td><input id="pageNumber" class="easyui-textbox" type="text" style="width:0px;" name="pageNumber" value=""></input></td>
		    		   <td><input id="pageSize" class="easyui-textbox" type="text" style="width:0px;" name="pageSize" value=""></input></td>
		    		</tr>		 	    	
		    	</table>
		    </form>
		<div style="width:100%;height:438px">
			<table id="dg" class="easyui-datagrid"
				style="width: 100%; height: 100%"
				toolbar="#toolbar" rownumbers="true" fitColumns="true"
				singleSelect="true">
			</table>	
		</div>
	    <div id="equipment_dialog" class="easyui-dialog" style="width: 300px; height: 310px;" closed="true" buttons="#equipment_dialog-buttons">
			<form id="equipment_dialog_Form" class="easyui-form" method="post" data-options="novalidate:true" commandName="equipmentForm">
			    <table>
			  		<tr>
						<td align="right"><font color=red>*</font>终端编号：</td>
	    				<td>
		    				<input class="easyui-textbox" type="text" id="equipmentCode" name="equipmentCode" 
		    					missingMessage=<spring:message code='com.vat.base.model.customer.validate.text'/>
		    					data-options="required:true" style="width:172px"/>
	    				</td>
					</tr>
			  		<tr>
						<td align="right"><font color=red>*</font>终端名称：</td>
	    				<td>
		    				<input class="easyui-textbox" type="text" id="equipmentName" name="equipmentName" 
		    					missingMessage=<spring:message code='com.vat.base.model.customer.validate.text'/>
		    					data-options="required:true" style="width:172px"/>
	    				</td>					
	    			</tr>
			  		<tr>
						<td align="right"><font color=red>*</font>IP地址：</td>
	    				<td>
		    				<input class="easyui-textbox" type="text" id="equipmentIp" name="equipmentIp" 
		    					missingMessage=<spring:message code='com.vat.base.model.customer.validate.text'/>
		    					data-options="required:true" style="width:172px"/>
	    				</td>					
	    			</tr>
			  		<tr>
						<td align="right"><font color=red>*</font>端口：</td>
	    				<td>
		    				<input class="easyui-textbox" type="text" id="equipmentPort" name="equipmentPort" 
		    					missingMessage=<spring:message code='com.vat.base.model.customer.validate.text'/>
		    					data-options="required:true" style="width:172px"/>
	    				</td>					
	    			</tr>
			  		<tr>
						<td align="right"><font color=red>*</font>终端序列号：</td>
	    				<td>
		    				<input class="easyui-textbox" type="text" id="equipmentSeqNo" name="equipmentSeqNo" 
		    					missingMessage=<spring:message code='com.vat.base.model.customer.validate.text'/>
		    					data-options="required:true" style="width:172px"/>
	    				</td>					
	    			</tr>
	    			<tr id="terminaltype">
						<td align="right"><font color=red>*</font>终端类型：</td>
		    			<td>
							<input id="equipmentType" class="easyui-combobox" name="equipmentType" style="width:172px" missingMessage=<spring:message code='com.vat.base.model.customer.validate.text'/> data-options="url:'${vat}/equipment/getDictionary.do',
                                method:'get',
                                valueField:'value',
                                textField:'text',
                                required:true,
                                panelHeight:'auto'">
							
	    				</td>					
					</tr>
					<tr id="mainserver" style="display: none">
		 				<td align="right"><font color=red>*</font>主服务器：</td>
		    			<td>
		    				<input id="parentEquipmentName" class="easyui-combogrid" type="text" style="width:172px;" missingMessage=<spring:message code='com.vat.base.model.customer.validate.text'/>
		    					data-options="required:true" style="width:172px"></input>
		    			</td>
		    		</tr>
			  		<tr>
						<td align="right"></font>管理人员：</td>
	    				<td>
		    				<input class="easyui-textbox" type="text" name="equipmentManager" style="width:172px"/>
	    				</td>					
	    			</tr>
			  		<tr>
						<td align="right"><font color=red>*</font>是否启用：</td>
		    			<td>
						    <input:select name="enabledFlag" value="$enabledFlag" easyuiClass="easyui-combobox" easyuiStyle="width:172px;" missingMessage="该项为必填项" dataOptions="required:true">
					            <option value=""></option>
					            <input:systemStatusOption parentCode="BASE_IS_ENABLED"/>
							</input:select>
	    				</td>					
					</tr>
			  		<tr>
						<td align="right"></font>添加日期：</td>
	    				<td>
		    				<input class="easyui-textbox" type="text" name="equipmentEnterDate" style="width:172px"/>
	    				</td>
					</tr>
					<tr style="display: none">							
						<input id="id" name="id" type="hidden" value=""/>
						<input id="parentEquipmentId" name="parentEquipmentId" type="hidden" value=""/>
					</tr>
			  	</table>
			</form>
   		</div> 
		<div id="equipment_dialog-buttons">
			<a  href="javascript:void(0)" class="easyui-linkbutton"	iconCls="icon-ok" onclick="save()"> 
				<spring:message	code="button.Save" /></a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" 
				onclick="javascript:$('#equipment_dialog').dialog('close')"><spring:message code="button.Close" /></a>
		</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		initData();	
		$('#searchform').form('load', {
			pageNumber : $('#dg').datagrid('options').pageNumber,
			pageSize : $('#dg').datagrid('options').pageSize
		});
		loadInitPage($('#dg').datagrid('options').pageNumber,$('#dg').datagrid('options').pageSize);
	});
	$.extend($.messager.defaults,{
	    ok:'<spring:message code="confirm"/>',
	    cancel:'<spring:message code="cancel"/>'
	});
	function initData() {
		$('#equipmentType').combobox({
			 onSelect:function(record){
                 var eType = $('#equipmentType').combobox('getValue');
     			 if(eType == 2){
					 $('#equipment_dialog').height("262px");
					 loadServerData(1,10);
         			 $('#mainserver').show();		 
     			 }else{
					 $('#equipment_dialog').height("236px");
         			 $('#parentEquipmentName').combogrid({ required: false });
         			$('#mainserver').hide();
    			 }
         	}
		});
		
		$('#equipmentTree').tree({
			url : '${vat}/equipment/createtree.do',
			checkbox : false,
			onClick: function(node){
		    	$("#dg").datagrid("loading");
		    	
		    	var pagenumber = $('#dg').datagrid('options').pageNumber;
		    	var pagesize = $('#dg').datagrid('options').pageSize;
		    	var senddata = 'id='+node.id+'&pageNumber='+pagenumber+'&pageSize='+pagesize;
		    	
		    	$.ajax({  
			        url: '${vat}/equipment/loadEquipmentPage.do',  
			        type: "POST",  
			        async: true,  
			        data: senddata,
			        dataType: "json",  
			        cache: false,  
			        success: function(result){
			        	if(result!=null){
							$("#dg").datagrid('loadData', result);
							$("#dg").datagrid("loaded"); 	
			        	}
			        }
			    });
			}
		});
		
		$('#dg').datagrid({
			url: '',			
			fitColumns: true,
			nowrap: false,
			pagination:true,
			rownumbers:true,
			singleSelect:true,
			fitColumns: true,
			striped: true,
			idField:'id', //主键字段  
			columns:[[
			    { field:'equipmentCode', title:'终端编号',width:60,align:'center'},
				{ field:'equipmentName', title:'终端名称',width:100,align:'center'}, 
				{ field:'equipmentIp', title:'IP地址',width:60,align:'center'},
				{ field:'equipmentPort', title:'端口',width:60,align:'center'},
				{ field:'equipmentSeqNo', title:'终端序列号',width:60,align:'center'},
				{ field:'equipmentManager', title:'管理人员',width:80,align:'center'},
				{ field:'enabledFlag', title:'是否启用',width:60,align:'center'},
				{ field:'equipmentEnterDate', title:'添加日期',width:60,align:'center',formatter:function(value){
					if(value){
						return myformatter(new Date(value));
					}
				}},
			]],
		   toolbar:[{  
                 text:'新增',  
                iconCls:'icon-add',  
                handler:function(){  
                	add();  
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
                	 remove();  
                 }  
             },'-'],  
			 onLoadSuccess:function(){  
				 $('#dg').datagrid('clearSelections')
			 }
		});
		
		var p = $('#dg').datagrid('getPager'); 
		$(p).pagination({           
			pageSize: 10,//每页显示的记录条数，默认为10           
			beforePageText: '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
			afterPageText: '<spring:message code="pagination.afterPageText"/>',           
			displayMsg: '<spring:message code="pagination.displayMsg"/>',
			onSelectPage: function (pageNumber,pageSize) {//分页触发		
		    	 find(pageNumber,pageSize);
		    	 searchEquipment(); 
	         }
	
		});
		
		$('#searchform_equipmentName').combogrid({
			panelWidth : 300,
			idField : 'id',  
			textField : 'equipmentName', 
			url : '',
			fitColumns : true,
			striped : true,
			editable : true,
			pagination : true, //是否分页
			rownumbers : true, //序号
			collapsible : false, //是否可折叠的
			fit : true, //自动大小
			method : 'post',
			columns : [[{
				field : 'equipmentCode',
				title : '设备编号',
				width : 13
			}, {
				field : 'equipmentName',
				title : '设备名称',
				width : 20
			}]],
			keyHandler : {
				up : function() { //【向上键】押下处理
					var selected = $('#searchform_equipmentName').combogrid('grid')
							.datagrid('getSelected');
					if (selected) {
						var index = $('#searchform_equipmentName').combogrid('grid')
								.datagrid('getRowIndex',
										selected);
						if (index > 0) {
							$('#searchform_equipmentName').combogrid('grid')
									.datagrid('selectRow',
											index - 1);
						}
					} else {
						var rows = $('#searchform_equipmentName').combogrid('grid')
								.datagrid('getRows');
						$('#searchform_equipmentName').combogrid('grid').datagrid(
								'selectRow', rows.length - 1);
					}
				},
				down : function() { //【向下键】押下处理
					var selected = $('#searchform_equipmentName').combogrid('grid')
							.datagrid('getSelected');
					if (selected) {
						var index = $('#searchform_equipmentName').combogrid('grid')
								.datagrid('getRowIndex',
										selected);
						if (index < $('#searchform_equipmentName').combogrid('grid')
								.datagrid('getData').rows.length - 1) {
							$('#searchform_equipmentName').combogrid('grid')
									.datagrid('selectRow',
											index + 1);
						}
					} else {
						$('#searchform_equipmentName').combogrid('grid').datagrid(
								'selectRow', 0);
					}
				},
				enter : function() { //【回车键】押下处理
					$('#txtgender')
							.val(
									$('#searchform_equipmentName')
											.combogrid('grid')
											.datagrid(
													'getSelected').gender);
					$('#searchform_equipmentName').combogrid('hidePanel');
				},
				query : function(keyword) { //【动态搜索】处理
					var queryParams = $('#searchform_equipmentName')
							.combogrid("grid").datagrid(
									'options').queryParams;
					queryParams.keyword = keyword;
					$('#searchform_equipmentName').combogrid("grid").datagrid(
							'options').queryParams = queryParams;
					$('#searchform_equipmentName').combogrid("setValue", keyword);
					 $('#searchform_equipmentName').combogrid("grid").datagrid("reload", { 'keyword': keyword });
				}
			},
			onSelect : function() { //选中处理
				$('#txtgender').val(
						$('#searchform_equipmentName').combogrid('grid').datagrid(
								'getSelected').gender);
			}
		});
		var pager = $('#searchform_equipmentName').combogrid('grid').datagrid('getPager');
		if (pager) {
			$(pager).pagination({
				pageSize: 10,           
				beforePageText: '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
				afterPageText: '页',           
				displayMsg: '<spring:message code="pagination.displayMsg"/>',
				onSelectPage : function(pageNumber, pageSize) {
					$('#searchform_equipmentName').combogrid("grid").datagrid(
							'options').pageNumber = pageNumber;
					$('#searchform_equipmentName').combogrid("grid").datagrid(
							'options').pageSize = pageSize;
			    	loadEquipmentData(pageNumber,pageSize);
				},
				onChangePageSize : function() {
				},
				onRefresh : function(pageNumber, pageSize) {
			    	loadEquipmentData(pageNumber,pageSize);
				}
			});
		}
		
	   	var pageNumber = $('#searchform_equipmentName').combogrid("grid").datagrid('options').pageNumber;
    	var pageSize = $('#searchform_equipmentName').combogrid("grid").datagrid('options').pageSize;
    	loadEquipmentData(pageNumber,pageSize);
    	
		$('#parentEquipmentName').combogrid({
			panelWidth : 300,
			idField : 'id',  
			textField : 'equipmentName', 
			url : '',
			fitColumns : true,
			striped : true,
			editable : true,
			pagination : true, //是否分页
			rownumbers : true, //序号
			collapsible : false, //是否可折叠的
			fit : false, //自动大小
			method : 'post',
			columns : [[{
				field : 'equipmentCode',
				title : '设备编号',
				width : 13
			}, {
				field : 'equipmentName',
				title : '设备名称',
				width : 20
			}]],
			keyHandler : {
				up : function() { //【向上键】押下处理
					var selected = $('#parentEquipmentName').combogrid('grid')
							.datagrid('getSelected');
					if (selected) {
						var index = $('#parentEquipmentName').combogrid('grid')
								.datagrid('getRowIndex',
										selected);
						if (index > 0) {
							$('#parentEquipmentName').combogrid('grid')
									.datagrid('selectRow',
											index - 1);
						}
					} else {
						var rows = $('#parentEquipmentName').combogrid('grid')
								.datagrid('getRows');
						$('#parentEquipmentName').combogrid('grid').datagrid(
								'selectRow', rows.length - 1);
					}
				},
				down : function() { //【向下键】押下处理
					var selected = $('#parentEquipmentName').combogrid('grid')
							.datagrid('getSelected');
					if (selected) {
						var index = $('#parentEquipmentName').combogrid('grid')
								.datagrid('getRowIndex',
										selected);
						if (index < $('#parentEquipmentName').combogrid('grid')
								.datagrid('getData').rows.length - 1) {
							$('#parentEquipmentName').combogrid('grid')
									.datagrid('selectRow',
											index + 1);
						}
					} else {
						$('#parentEquipmentName').combogrid('grid').datagrid(
								'selectRow', 0);
					}
				},
				enter : function() { //【回车键】押下处理
					$('#txtgender')
							.val(
									$('#parentEquipmentName')
											.combogrid('grid')
											.datagrid(
													'getSelected').gender);
					$('#parentEquipmentName').combogrid('hidePanel');
				},
				query : function(keyword) { //【动态搜索】处理
					var queryParams = $('#parentEquipmentName')
							.combogrid("grid").datagrid(
									'options').queryParams;
					queryParams.keyword = keyword;
					$('#parentEquipmentName').combogrid("grid").datagrid(
							'options').queryParams = queryParams;
					$('#parentEquipmentName').combogrid("setValue", keyword);
					 $('#parentEquipmentName').combogrid("grid").datagrid("reload", { 'keyword': keyword });
				}
			},
			onSelect : function() { //选中处理
				$('#parentEquipmentId').val(
						$('#parentEquipmentName').combogrid('grid').datagrid(
								'getSelected').id);
			}
		});
		var pager = $('#parentEquipmentName').combogrid('grid').datagrid('getPager');
		if (pager) {
			$(pager).pagination({
				pageSize: 10,           
				beforePageText: '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
				afterPageText: '页',           
				displayMsg: '<spring:message code="pagination.displayMsg"/>',
				onSelectPage : function(pageNumber, pageSize) {
					$('#parentEquipmentName').combogrid("grid").datagrid(
							'options').pageNumber = pageNumber;
					$('#parentEquipmentName').combogrid("grid").datagrid(
							'options').pageSize = pageSize;
					loadServerData(pageNumber,pageSize);
				},
				onChangePageSize : function() {
				},
				onRefresh : function(pageNumber, pageSize) {
					loadServerData(pageNumber,pageSize);
				}
			});
		}
		
	   	var pageNumber = $('#parentEquipmentName').combogrid("grid").datagrid('options').pageNumber;
    	var pageSize = $('#parentEquipmentName').combogrid("grid").datagrid('options').pageSize;
    	loadServerData(pageNumber,pageSize);
	}
	
    function searchEquipment(){
    	$("#dg").datagrid("loading");
    	$('#searchform').form('submit', {
			url:'${vat}/equipment/loadEquipmentPage.do',		
			success : function(result) {
				var result = $.parseJSON(result);
	        	if(result!=null){
				     $("#dg").datagrid('loadData', result);
					$("#dg").datagrid("loaded"); 	
	        	}
		     }
		});
    }

	
	function loadEquipmentData(pageNumber,pageSize){
    	var senddata = 'pageNumber='+pageNumber+'&pageSize='+pageSize;
	    $.ajax({
	        url: '${vat}/equipment/loadEquipmentPage.do',  
	        type: "POST",  
	        async: true,  
	        data: senddata,
	        dataType: "json",  
	        cache: false,  
	        success: function(result){
	        	if(result!=null){
					$('#searchform_equipmentName').combogrid("grid").datagrid('loadData', result);
					$('#searchform_equipmentName').combogrid("grid").datagrid("loaded"); 	
	        	}
	        }
	    });
	}
	
	function loadServerData(pageNumber,pageSize){
    	var senddata = 'getparent=1&pageNumber='+pageNumber+'&pageSize='+pageSize;
	    $.ajax({
	        url: '${vat}/equipment/loadEquipmentPage.do',  
	        type: "POST",  
	        async: true,  
	        data: senddata,
	        dataType: "json",  
	        cache: false,  
	        success: function(result){
	        	if(result!=null){
					$('#parentEquipmentName').combogrid("grid").datagrid('loadData', result);
					$('#parentEquipmentName').combogrid("grid").datagrid("loaded"); 	
	        	}
	        }
	    });
	}
	
	function loadInitPage(pageNumber,pageSize){
    	var senddata = 'getparent=1&pageNumber='+pageNumber+'&pageSize='+pageSize;
	    $.ajax({
	        url: '${vat}/equipment/loadEquipmentPage.do',  
	        type: "POST",  
	        async: true,  
	        data: senddata,
	        dataType: "json",  
	        cache: false,  
	        success: function(result){
	        	if(result!=null){
				     $("#dg").datagrid('loadData', result);
					$("#dg").datagrid("loaded"); 	
	        	}
	        }
	    });
	}
	function loadEntityData(pageNumber,pageSize){
    	var senddata = 'pageNumber='+pageNumber+'&pageSize='+pageSize;
	    $.ajax({
	        url: '${vat}/equipment/loadLegalEntity.do',  
	        type: "POST",  
	        async: true,  
	        data: senddata,
	        dataType: "json",  
	        cache: false,  
	        success: function(result){
				if(result.status=='0'){
					$('#searchform_entity').combogrid("grid").datagrid('loadData', result.data);
				}else if(result.status=='1'){
					//$.messager.alert('<spring:message code="system.alert"/>',result.erroMessage,'info');
				}
				$('#searchform_entity').combogrid("grid").datagrid("loaded"); 	
	        }
	    });
	}
	
	function expandAll() {
		$('#equipmentTree').tree('expandAll');
	}
	function collapseAll() {
		$('#equipmentTree').tree('collapseAll');
	}
	function find(pageNumber,pageSize){
		$('#searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		searchEquipment();
	}
	function findData(pageNumber,pageSize){
		$('#searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		searchEquipment();
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
	function clearEquipmentForm(){
		$('#equipment_dialog_Form').form('clear');
	}
	
	function add(){
		clearEquipmentForm();
		$("#equipment_dialog").dialog('open').dialog('setTitle',
		'新增');
		$("#equipment_dialog_Form").form('load', {equipmentEnterDate:myformatter(new Date())});
		
	}
	
	function edit(){
		var row = $('#dg').datagrid('getChecked');
		if(row.length==0){
            $.messager.alert('提示',"请先选择一条记录",'info');  
            return;  
        } 
		if(row.length > 1){  
           $.messager.alert('提示',"只能选择一条记录进行更新",'info');  
           return;  
        } 
		var onerow = $('#dg').datagrid('getSelected');
		if(onerow.id!=''){
			clearEquipmentForm();
			 $('#equipment_dialog_Form').form('load', {
				 equipmentCode:onerow.equipmentCode,
				 equipmentName:onerow.equipmentName,
				 equipmentIp:onerow.equipmentIp,
				 equipmentPort:onerow.equipmentPort,
				 equipmentSeqNo:onerow.equipmentSeqNo,
				 equipmentType:onerow.equipmentType,
				 parentEquipmentName:onerow.parentEquipmentName,
				 equipmentManager:onerow.equipmentManager,
				 enabledFlag:onerow.enabledFlag,
				 equipmentEnterDate:onerow.equipmentEnterDate,
				 id:onerow.id				 
			 });
        	 $("#equipment_dialog").dialog('open').dialog('setTitle', '编辑');
	        	
    	}else{
    		$.messager.alert('<spring:message code="system.alert"/>','编辑的主键不能为空');
    	}
	}
	
	function remove(){
		var row = $('#dg').datagrid('getSelected');	
		if(row!=null){
			var id = row.id;	
		    $.messager.confirm('<spring:message code="system.alert"/>','确认删除此设备及其子设备吗？',function(result){  
				  if (result){
				      $.ajax({
		                    url:"${vat}/equipment/deleteEquipment.do?id="+id,
		                    dataType : "json",
		                    cache:false,
		                    success : function(result) {	                    	
		                       if (result.success) {
									$("#equipmentTree").tree('reload');
		                        	searchEquipment();
		        					clearEquipmentForm(); 
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
	function save(){
// 		if($('#equipmentType').val().toLowerCase().indexOf("print") != -1){
// 			$('#parentEquipmentName').combogrid({ required: false });
// 		}else{
// 			$('#parentEquipmentName').combogrid({ required: true });
// 		}
		
		var equipment_dialog_Form=$('#equipment_dialog_Form');
		if(equipment_dialog_Form.form('enableValidation').form('validate')){
			$.messager.confirm("<spring:message code='client.datacommit.formsave.confirm.title'/>","<spring:message code='client.datacommit.formsave.confirm.text'/>",function(result){
				if (result){
					var date = $('#equipmentEnterDate').val();
					$('#equipment_dialog_Form').form('submit',{
						url:'${vat}/equipment/saveEquipment.do',	
						onSubmit : function() {
								$.messager.progress({title:'Please waiting',
									msg:'数据保存中。。。。'});
						},			
						success:function(result){
							$.messager.progress('close');
							var result = $.parseJSON(result);
							if(result.status=='0'){	
								clearEquipmentForm();
								$("#equipmentTree").tree('reload');
								searchEquipment();
								$('#equipment_dialog').dialog('close');
								$.messager.alert('<spring:message code="system.alert"/>','保存成功');
							}
							
						} 
					});
				}
			});
		}
	}
</script>

</html>