<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/common/global.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<script type="text/javascript">
var addEqu_dataGridPN=1;
var addEqu_dataGridPS=10;
var leftGloLegId;

var map3= {};
var products = [
    		    {productid:'0',name:'否'},
    		    {productid:'1',name:'是'}
    		    ];

/* 
$(function(){  
    var addr_tree = $("#legalEntityTree").tree({  
        url:'',  
        method:"post",  
        onSelect:function(node){}  
    });  
});
 */

$(function(){
	$('#dg').datagrid({
		url: 'legalEquipment/listLegalAll.do',			
		fitColumns: true,
		nowrap: false,
		pagination:false,
		rownumbers:true,		
		columns:[[
			{field:'legalEntityName',title:'纳税主体名称',width:100,align:'center'},
		/* 	{field:'registrationNumber',title:'纳税人识别号',width:80,align:'center'},
			{field:'numberOfEmployees',title:'从业人数',width:80,align:'center'},
			{field:'annualPaymentAmount',title:'年度缴纳税额',width:100,align:'center'},
			{field:'registrationContactAddress',title:'注册地址',width:100,align:'center'},
			{field:'registrationContactPhone',title:'注册地址联系电话',width:80,align:'center'}, */
			
		]],
		
		
/* 		toolbar:[
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
                	remove2();  
                }  
            },'-'], */
            
            
		
		onClickRow:function(index,data){
			var row = $('#dg').datagrid('getSelected');
			if (row){
				
		/* 		alert('index:'+index);
				alert('data:'+data);
				var id = row.id;
				var legalEntityName = row.legalEntityName;
				alert('id:'+id);
				alert('legalEntityName:'+legalEntityName); */
				//todo post to get related equipment
				if(data.id != row.id){
					
					leftGloLegId=row.id;
					alert('strange: data.id != row.id');
				}else{
					leftGloLegId=data.id;
				}
				
				
				var id = row.id;
				getRelatedEqu(id);
			}
		}
	});
	//设置分页控件	
/* 	var p = $('#dg').datagrid('getPager'); 
	$(p).pagination({           
		pageSize: 10,//每页显示的记录条数，默认为10           
		//pageList: [5,10,15,20],//可以设置每页记录条数的列表           
		beforePageText: '第',//页数文本框前显示的汉字           
		afterPageText: '页',           
		displayMsg: '<spring:message code="pagination.displayMsg"/>',
		onSelectPage: function (pageNumber, pageSize) {//分页触发		
	    	// alert('分页触发');
			find(pageNumber,pageSize);
			
			clearSaveForm(); //??????????????needed???
			 Search(); 
			 
         }

	}); */
	
	var lastIndex;
	
	$('#equDatagrid').datagrid({
				url : '',
				fitColumns : true,
				nowrap : false,
				pagination : false,
				rownumbers : true,
				singleSelect : true,
				striped : true,
				idField : 'id', //主键字段  
				columns : [ [
				{
					field : 'equipmentName',
					title : '设备名称',
					width : 70,
					align : 'center'
				}, {
					field : 'equipmentCode',
					title : '设备编号',
					width : 50,
					align : 'center',

				}, {
					field : 'equipmentType',
					title : '设备类型',
					width : 50,
					align : 'center',
					formatter:equipmentTypeFormatter
				},
				
				{
					field : 'isDefault',
					title : '默认打印设备',
					width : 30,
					align : 'center',
					formatter:isDefaultFormatter,
					editor:{
						type:'combobox',
						options:{
							valueField:'productid',
							textField:'name',
							data:products,
							required:true
						}
				
					}
				}, {
						field : 'equipmentIp',
						title : '设备IP',
						width : 30,
						align : 'center'
					}, {
						field : 'parentEquipmentName',
						title : '所属服务器',
						width : 40,
						align : 'center'
					}
				
				
				] ],
				toolbar : [ {
					text : '添加打印设备',
					iconCls : 'icon-add',
					handler : function() {
						//addEntity();
						
						addEqu();
						
					}
				}, '-', {
					text : '删除打印设备',
					iconCls : 'icon-remove',
					handler : function() {
					//	removeEqu();
						
						removeEqu();
						
					}
				}, '-' , {
					text : '更改默认打印设备',
					iconCls : 'icon-remove',
					handler : function() {
					//	removeEqu();
						
						updateDefaultEqu2();
						
					}
				}, '-'
				
				],
				onLoadSuccess : function() {
					
			
				},
				
				onClickRow:function(rowIndex){
					if (lastIndex != rowIndex){
						$('#equDatagrid').datagrid('endEdit', lastIndex);
						$('#equDatagrid').datagrid('beginEdit', rowIndex);
					}
					lastIndex = rowIndex;
				}
				
			});
	//设置分页控件	
	
/* 	var p = $('#equDatagrid').datagrid('getPager');
	
	$(p).pagination(
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
	
	 */
	
});
 
 
	function isDefaultFormatter(value){
		
	//	alert(value);
		if(value=='1'){
			return '是';
		}else{
			return '否';
		}
	/* 	
		for(var i=0; i<-[=''].length; i++){
			if (products[i].productid == value) return products[i].name;
		}
		return value; */
	}
	
	function equipmentTypeFormatter(value){
		
		//	alert(value);
			if(value=='1'){
				
				return '税控服务器';
			}else if(value=='2'){
				
				return '税控打印终端';
			}
		/* 	
			for(var i=0; i<-[=''].length; i++){
				if (products[i].productid == value) return products[i].name;
			}
			return value; */
		}


	function getNoRelatedEqu(legalId){
		
	//	alert('getNoRelatedEqu> legalId:'+legalId);
		
		
		
		$("#addEqu_dataGrid").datagrid("loading");

		
		if(typeof(legalId)==undefined){
			return;
		}
		
		//todo Integer pageNumber, Integer pageSize

			$.post('legalEquipment/getNoRelatedEqu.do', {
				
				legalId : legalId,
				pageNumber : addEqu_dataGridPN,
				pageSize : addEqu_dataGridPS
				
			}, function(result) {
				
				if (result.success) {
					//alert(result.rows[0][0]);
					//alert(result.rows[0][1].parent.equipmentName);
					$("#addEqu_dataGrid").datagrid('loadData', result);
					$("#addEqu_dataGrid").datagrid("loaded");
					
					
				} else {
					$("#addEqu_dataGrid").datagrid("loaded");
					return;
				}
			}, 
			
			'json');
				
	}

 
 
	
	function getRelatedEqu(legalId){
		
		//alert('getRelatedEqu> legalId:'+legalId);
		
		
		
		$("#equDatagrid").datagrid("loading");
		
		
/* 		$('#searchform').form('load', {
			pageNumber : $('#dg').datagrid('options').pageNumber,
			pageSize : $('#dg').datagrid('options').pageSize
		});
		 */
		
		if(typeof(legalId)==undefined){
			return;
		}
		
		//todo Integer pageNumber, Integer pageSize

			$.post('legalEquipment/getRelatedEqu.do', {
				
				legalId : legalId,
				pageNumber : 1,
				pageSize : 10
				
			}, function(result) {
				
			
				
				if (result.success) {
					//$.messager.alert('<spring:message code="system.alert"/>',result.msg);		
					
					//todo: datagride list
					//var results = $.parseJSON(result);
					//$("#equDatagrid").datagrid('loadData', results);
					
					
						//loading效果
    	//$("#equDatagrid").datagrid("loading");  
					
					$("#equDatagrid").datagrid('loadData', result);
					$("#equDatagrid").datagrid("loaded");
					
					
				} else {
					return;
				}
			}, 
			
			'json');
				
	}



	

	function removeEqu() {
		if (($('#equDatagrid').datagrid('getChecked').length < 1)) {
			$.messager.alert("操作提示", "请选择需要删除的数据");
		} else {
			$.messager
					.confirm(
							"<spring:message code='client.datacommit.delete.confirm.title'/>",
							"<spring:message code='client.datacommit.delete.confirm.text'/>",
							function(result) {
								if (result) {
									
									var legalId = getLeftLegalId();
									
									
									var row_list = $(
											'#equDatagrid')
											.datagrid('getChecked');
									
									//var row=$('#clientManageInit_dataGrid').datagrid('getSelected');
									if (row_list) {
										
										var urlString = "";
										var willAddEquId='';
										
										$.each(row_list, function(index, item) {
											if (index == 0){
												
												willAddEquId=willAddEquId+item.id;
												urlString += "id=" + item.id;
												
											}else{
												
												willAddEquId= willAddEquId +','  +item.id;
												urlString += "&id=" + item.id;
											}
												
												
										});
										if (willAddEquId != '') {
											
											$.post('legalEquipment/removeEqu.do', {
												
												legalId : legalId,
												equIds : willAddEquId
												
												
												///pageNumber : 1,
												//pageSize : 10
												
											}, function(result) {
												
											try{
												
												if (result.success) {
								
												//todo refresh equ datagrid, close current diag, notice success
												//	$("#addEqu_dlg").dialog('close');
												
													alert(result.msg);
													getRelatedEqu(legalId);
													return;
													
												} else {
													
													alert(result.errorMsg);
													return;
												}
												
											}catch(e){
												alert(e);
											}
												
											}, 
											
											'json');
								
										
										}else{
											
											alert('请先选择要删除的打印设备');
										}
									}
								}

							});
		}
	}
	 
	

	
	function changedRowkey(){/* 
		var rows = $('#equDatagrid').datagrid('getChanges');
		
		var changedEquIds='';
		var len=rows.length;
		var i=1;
		
		for(var r : rows){
			
			changedEquIds = changedEquIds + r.id;
			
			if(i<len){
				changedEquIds=changedEquIds+',';
			}
			
			
		
		}
		
		alert(changedEquIds);
		alert('changed rows: ' + rows.length + ' lines');
	 */}
	
	var defaultEquIds='';
	var defaultEqus='';
	
	
	function clearSearchForm(){
		//var pageNumber = $('#pageNumber').val();
		//var pageSize = $('#pageSize').val()
		$('#legalEntityAndEquipmentSearchForm').form('clear');
/* 		$('#searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		}); */
	}
	
	function dupDefault(){
		
		var decount=0;

		 defaultEquIds='';
		 defaultEqus='';
		
		for(var prop in map3){
			
			if(map3.hasOwnProperty(prop)){
				
				if( map3[prop]=='1' ){
					++decount;
				}
				
				if(defaultEquIds!=''){
					
				
				defaultEquIds = defaultEquIds + ',' + prop;
				defaultEqus = defaultEqus + ',' + map3[prop];
				}else{
					defaultEquIds=prop;
					defaultEqus=map3[prop];
					
				}
				//console.log('key is ' + prop +' and value is' + map[prop]);    
				
			}
		}
		
		if(decount > 1){
			
			$('#tt').datagrid('rejectChanges');
			$("#equDatagrid").datagrid("loaded");
			alert('只能选择最多一个设备为 默认打印设备, 请正确选择');
			return true;
		}else{
			
		//	$('#equDatagrid').datagrid('acceptChanges');
			$("#equDatagrid").datagrid("loaded");
			return false;
		}
		
		
	}
	
	
	function updateDefaultEqu2(){
		
		$('#equDatagrid').datagrid('acceptChanges');
		$("#equDatagrid").datagrid("loading");
		
	//	alert('updateDefaultEqu2');
	 var rows =	$('#equDatagrid').datagrid('getRows');
	 
	 var len = rows.length;
		 
	 map3={};
	 
		 for(var x=0; x<len; x++){
			 
			 var r = rows[x];
			 
			 var k = r.id;
			 var v = r.isDefault;
		//	 alert('k:'+k+';v:'+v);
			 
			 map3[k]=v;
			
			 //map3[r.id]= r.isDefault;
		 }
		 
		if( dupDefault() ){
			//$("#equDatagrid").datagrid("loaded");
			return;
		}else {
			
		}

	 
	 var legalId = getLeftLegalId();
	 
	 
	var defaultMapEquId = '';
	 
	 var defaultMap = map3;
	 
		$.post('legalEquipment/updateDefaultEqu.do', {
			
			//defaultMap : defaultMap,
			legalId : legalId,
			defaultEquIds : defaultEquIds,
			defaultEqus : defaultEqus
			
			///pageNumber : 1,
			//pageSize : 10
			
		}, function(result) {
			
		try{
			
			if (result.success) {

			//todo refresh equ datagrid, close current diag, notice success
			//	$("#addEqu_dlg").dialog('close');
			
				alert(result.msg);
				getRelatedEqu(legalId);
				return;
				
			} else {
				
				alert(result.errorMsg);
				return;
			}
			
		}catch(e){
			alert(e);
		}
			
		}, 
		
		'json');

	
	}
	
	 
		function updateDefaultEqu() {/* 
			if (($('#equDatagrid').datagrid('getChecked').length < 1)) {
				$.messager.alert("操作提示", "请选择需要更新提交的数据");
			} else {
				$.messager
						.confirm(
								"<spring:message code='client.datacommit.delete.confirm.title'/>",
								"<spring:message code='client.datacommit.delete.confirm.text'/>",
								function(result) {
									if (result) {
										
										var legalId = getLeftLegalId();
										
										
										var row_list = $(
												'#equDatagrid')
												.datagrid('getChecked');
										
										//var row=$('#clientManageInit_dataGrid').datagrid('getSelected');
										if (row_list) {
											
											var urlString = "";
											var willAddEquId='';
											var map2 ={
													
											}
											
											$.each(row_list, function(index, item) {
												if (index == 0){
													
													willAddEquId=willAddEquId+item.id;
													urlString += "id=" + item.id;
													
												}else{
													
													willAddEquId= willAddEquId +','  +item.id;
													urlString += "&id=" + item.id;
												}
												
												alert("item.isDefault:"+item.isDefault);
													
													
											});
											if (willAddEquId != '') {
												
												$.post('legalEquipment/updateDefaultEqu.do', {
													
													legalId : legalId,
													equIds : willAddEquId
													
													
													///pageNumber : 1,
													//pageSize : 10
													
												}, function(result) {
													
												try{
													
													if (result.success) {
									
													//todo refresh equ datagrid, close current diag, notice success
													//	$("#addEqu_dlg").dialog('close');
													
														alert(result.msg);
														getRelatedEqu(legalId);
														return;
														
													} else {
														
														alert(result.errorMsg);
														return;
													}
													
												}catch(e){
													alert(e);
												}
													
												}, 
												
												'json');
									
											
											}else{
												
												alert('请先选择要更新的打印设备');
											}
										}
									}

								});
			}
		 */}
	 
	 

	function initAddEqu() {
		$('#addEqu_dataGrid').datagrid({
			url : '',
			fitColumns : true,
			nowrap : false,
			pagination : true,
			rownumbers : true,
			singleSelect : false,
			striped : true,
			idField : 'id', //主键字段  
			columns : [ [
			//显示复选框   
			{
				field : 'ck',
				checkbox : true,
				width : 2
			}, {
				field : 'equipmentName',
				title : '设备名称',
				width : 60,
				align : 'center'
			}, {
				field : 'equipmentCode',
				title : '设备编号',
				width : 50,
				align : 'center',

			}, {
				field : 'equipmentType',
				title : '设备类型',
				width : 30,
				align : 'center',
				formatter:equipmentTypeFormatter
			}, {
				field : 'equipmentIp',
				title : '设备IP',
				width : 30,
				align : 'center'
			}, {
				field : 'parentEquipmentName',
				title : '所属服务器',
				width : 40,
				align : 'center'
			}
			
			] ],
			toolbar : [ {
				text : '确定关联选中的设备到纳税主体',
				iconCls : 'icon-add',
				handler : function() {
					
					//alert('will...');
					addEquInDialog();
				}
			}, '-' ],
			onLoadSuccess : function() {
				$('#addEqu_dataGrid').datagrid('clearSelections')
			},
			onClickRow : function(index, data) {
				var row = $('#addEqu_dataGrid').datagrid('getSelected');
				if (row) {
					//loadSaveFormData(row);
				}
			}
		});
		
		//设置分页控件
 		
		var p = $('#addEqu_dataGrid').datagrid('getPager');
		$(p).pagination(
						{
							pageSize : 10,//每页显示的记录条数，默认为10           
							//pageList: [10,20,30],//可以设置每页记录条数的列表           
							beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
							afterPageText : '<spring:message code="pagination.afterPageText"/>',
							displayMsg : '<spring:message code="pagination.displayMsg"/>',
							onSelectPage : function(pageNumber, pageSize) {//分页触发		
								
								addEqu_dataGridPN=pageNumber;
								addEqu_dataGridPS=pageSize;
								
								getNoRelatedEqu(leftGloLegId);
							}
						});
		
		
		
	}

	
	
	
	/* 
	
	function InitTreeData() {
		$('#legalEntityTree').tree({
			url : 'legalEquipment/createtree.do',
			checkbox : false,
			animate : true,
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
				 	 var row = $('#dg').datagrid('getSelected');
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
				 
			}

		});
	}

	 */


	function addEquInDialog() {
		 /* 
		if (($('#addEqu_dataGrid').datagrid('getChecked').length == 0)
				|| ($('#addEqu_dataGrid').datagrid('getChecked').length > 1)) 
		 */
		
		if (   $('#addEqu_dataGrid').datagrid('getChecked').length < 1	)
		{
			$.messager.alert("操作提示", "请选择需要关联的数据");
		} else {
			$.messager
					.confirm(
							"<spring:message code='提示'/>",
							"<spring:message code='确认添加吗？'/>",
							function(result) {

								if (result) {
									var row_list = $('#addEqu_dataGrid')
											.datagrid('getChecked');
									//var row=$('#clientManageInit_dataGrid').datagrid('getSelected');
									if (row_list) {
										var urlString = "";
										
										var willAddEquId='';
										
										$.each(row_list, function(index, item) {
											if (index == 0){
												
												urlString += "id=" + item.id;
												
												willAddEquId=willAddEquId+item.id;
											}else{
												
												willAddEquId= willAddEquId +','  +item.id;
												
												urlString += "&id=" + item.id;
											}
												
										});
									//	alert('urlString:'+urlString);
									//	alert('willAddEquId:'+willAddEquId);
										
										var row = $('#dg').datagrid('getSelected');
										
										if(row){
											
										}else{
											alert('请先选择左边的纳税主体 ');
											return;
										}
										
										var legalId=row.id;
										
										if (willAddEquId != '') {
											
											$.post('legalEquipment/bindEqu.do', {
												
												legalId : legalId,
												equIds : willAddEquId
												
												
												///pageNumber : 1,
												//pageSize : 10
												
											}, function(result) {
												
											try{
												
												if (result.success) {
								
												//todo refresh equ datagrid, close current diag, notice success
													$("#addEqu_dlg").dialog('close');
												
													alert(result.msg);
													getRelatedEqu(legalId);
													return;
													
												} else {
													
													alert(result.errorMsg);
													return;
												}
												
											}catch(e){
												alert(e);
											}
												
											}, 
											
											'json');
								
										}
									}
								}

							});
		}
	}
	 
	 
	 function getLeftLegalId(){
		 
			var row = $('#dg').datagrid('getSelected');
			
			if(row){
				
				var legalId=row.id;
				
				leftGloLegId=legalId;
				
				return legalId;
				
			}else{
				alert('请先选择左边的纳税主体 ');
				return;
			}		

	 }
	 
	 
	 //could no use this function, just use a global parametr , the two way are same
	 //
	 function getLeftGloLegId(){
		 
		 var row = $('#dg').datagrid('getSelected');
			if (row){
			
				leftGloLegId = row.id;
				
				return leftGloLegId;
				
			}else{
				
				//todo if no left legal entity , could go on
				
				alert('请先选中左边的纳税实体');
				return;
			}
	 }
	 
	 
		function addEqu() {
			
			var row = $('#dg').datagrid('getSelected');
			if (row){
				
				$("#addEqu_dlg").dialog('open').dialog('setTitle', "关联打印设备到纳税主体");

				initAddEqu();
				var id = row.id;
				getNoRelatedEqu(id);
			}else{
				
				//todo if no left legal entity , could go on
				
				alert('请先选中左边的纳税实体');
			}			
		
		}

/* 	function addEntity() {
		$("#addEqu_dlg").dialog('open').dialog('setTitle', "新增打印设备实体");

		initAddEqu();
		loadDataInLegalEntityDialog();
	} */

	function loadDataInLegalEntityDialog() {
		
		
		
		

		//alert($('#legalEntityTree').tree('getSelected').id);
/* 		$('#legalEntityAndEquipmentSearchForm').form('submit', {
			url : 'legalEquipment/loadTmsMdLegalEntityBesidCurrentTable.do',
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(result) {
				//alert(1);
				var results = $.parseJSON(result);
				$("#addEqu_dataGrid").datagrid('loadData', results);
				$("#addEqu_dataGrid").datagrid("loaded");
			}
		}); */
		
		
	}
	
	
	function Search() {
		
		var legalEntityName = $.trim( $('#legalEntityName').textbox('getValue') );
		var registrationNumber= $.trim( $('#registrationNumber').textbox('getValue') );

		$.post('legalEquipment/searchLegal.do', {
			
			legalEntityName : legalEntityName,
			registrationNumber : registrationNumber
			///pageNumber : 1,
			//pageSize : 10
			
		}, function(result) {
			
		try{
			
			if (result.success) {

			//todo refresh equ datagrid, close current diag, notice success
			$("#dg").datagrid("loading");
			$("#dg").datagrid('loadData', result);
			$("#dg").datagrid("loaded");
				
				return;
				
			} else {
				
				//alert(result.errorMsg);
				return;
			}
			
		}catch(e){
			alert(e);
		}
			
		}, 
		
		'json');
		

	}
	
	
	function hello() {
		alert($('#legalEntityTree').tree('getSelected').id);
	}
	function loadTableBySelect() {/* 
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
								
								$("#equDatagrid").datagrid(
										'uncheckAll');
								$("#equDatagrid").datagrid(
										'loadData', results);
								$("#equDatagrid").datagrid(
										"loaded");
							}
						});
	 */}
	

	
	
</script>
<body class="easyui-layout">
	<div data-options="region:'west',split:true,title:'纳税主体'"
		style="width: 20%;">

<!-- 		<ul id="legalEntityTree" class="easyui-tree"
			>

		</ul> -->
		
<!-- 		
		 <table class="easyui-datagrid" id="dg" title="纳税实体信息" style="width:100%;height:100%" data-options="					
					singleSelect:true,
					autoRowHeight:false,
					pagination:true,
					pageSize:10,
					remoteSort:false,
				    multiSort:true	
					">			 
		   </table>  -->  
		
		<!--remove pageSize:10, -->
		<table class="easyui-datagrid" id="dg"  style="width:100%;height:100%" data-options="					
					singleSelect:true,
					autoRowHeight:false,
					pagination:false,
					
					remoteSort:false,
				    multiSort:true
					">			 
		   </table>
		
		
		
		
	</div>
	<div data-options="region:'center',title:'打印设备列表'"
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
					<td>
					
					

							
							</td>
						<td>
						

							</td>
						<td><input id="pageNumber" class="easyui-textbox" type="text"
							style="width: 0px;" name="pageNumber" value=""></input></td>
						<td><input id="pageSize" class="easyui-textbox" type="text"
							style="width: 0px;" name="pageSize" value=""></input>
							
							
						<!-- 	<input id="ids" class="easyui-textbox" type="text"
							style="width: 0px;" name="ids" value=""></input>
							 -->
							
							
							</td>
						<td></td>
					</tr>
					<tr>
						<td>纳税主体名称</td>
						<td><input id="legalEntityName" class="easyui-textbox"
							type="text" style="width: 150px;" name="legalEntityName" value=""></input>
						</td>
						<td>纳税主体编号</td>
						<td><input id="registrationNumber" class="easyui-textbox"
							type="text" style="width: 150px;" name="registrationNumber" value=""></input>
						</td>
						<td>
						<div style="text-align: center; padding: 10px">
								<a href="#" id="searchbtn" class="easyui-linkbutton"
									data-options="iconCls:'icon-search'" style="width: 80px"
									onclick="Search()"><spring:message code="button.Search"/></a>
									
					<a href="#" class="easyui-linkbutton" style="width:80px" onclick="clearSearchForm()"><spring:message code="button.Clear"/></a>
							</div>
						</td>
					</tr>
					
				</table>
			</form>
		</div>
		<div style="width: 100%; height: 100%">
			<table class="easyui-datagrid" id="equDatagrid"
				style="width: 100%; height: 88%"
				data-options="					
					singleSelect:true,
					autoRowHeight:false,
					pagination:true,
					pageSize:10,
					remoteSort:false,
				    multiSort:true,
				    idField:'id'
					">
			</table>
		</div>


		<div id="addEqu_dlg" class="easyui-dialog"
			style="width: 800px; height: 400px;" closed="true">
			<table class="easyui-datagrid" id="addEqu_dataGrid"
				
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