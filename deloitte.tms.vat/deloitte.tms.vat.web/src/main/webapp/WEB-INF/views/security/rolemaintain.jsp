<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<!-- <link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.4/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.4/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.4/demo.css">
	<script type="text/javascript" src="jquery-easyui-1.4.4/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.4.4/jquery-1.8.0.js"></script> 
	<script type="text/javascript" src="resource/corejs/dateformat.js"></script>  -->

<%@ include file="/common/global.jsp" %>
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<div data-options="region:'east',split:true"
		title="<spring:message code="functionmaintain.mainlayout.westtitle"/>"
		style="width: 25%;">
		<div style="margin: 10px 5px;">
				<a href="#" class="easyui-linkbutton" style="width:80px" onclick="collapseAll()"><spring:message
						code="button.CollapseAll" /></a> <a href="#"
					class="easyui-linkbutton" style="width:80px" onclick="expandAll()"><spring:message
						code="button.ExpandAll" /></a> <a href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-save'" style="width:80px" onclick="saveRoleFrmfunction()"><spring:message
						code="button.Save" /></a> 
			</div>
		<div class="easyui-panel"
				style="padding: 5px, width:100%; height: 93%"">
				<ul id="tt" class="easyui-tree"></ul>
		</div>
	</div>
	<div data-options="region:'west',split:true"
		title="<spring:message code="usermaintain.userinfo"/>"
		style="width: 30%;">
		<table id="dgroleuser" class="easyui-datagrid"
			style="width: 100%; height: 100%" 
			 toolbar="#usertoolbar" rownumbers="true" fitColumns="true" pagination="true"
			singleSelect="false">
			<thead>
				<tr>
				    <th data-options="field:'ck',checkbox:true"></th>			    					
					<th data-options="field:'username',width:80"><spring:message code="usermaintain.username"/></th>
					<th data-options="field:'userCode', width:80"><spring:message code="usermaintain.usercode"/></th>						     										
				</tr>
			</thead>
		</table>
		<div id="usertoolbar">
			 <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="deleteUserRole()"><spring:message
					code="button.DeleteRoleUser" /></a> 
		</div>
		
	</div>
	<div data-options="region:'center',iconCls:'icon-ok'"
		title="<spring:message code="rolemaintain.roleinfo"/>">
		<table id="dg" class="easyui-datagrid"
			style="width: 100%; height: 100%" url="rolemaintain/rolelist.do"
			toolbar="#toolbar" rownumbers="true" fitColumns="true" 
			singleSelect="true">
			<thead>
				<tr>
					<th field="id" width="5" hidden="true"></th>
					<th field="name" width="40"><spring:message
							code="rolemaintain.name" /></th>
					<th field="desc" width="50"><spring:message
							code="rolemaintain.desc" /></th>
					<th field="type" width="50"><spring:message
							code="rolemaintain.roletype" /></th>				
					<th field="sortOrder" width="50"><spring:message
							code="rolemaintain.sequence" /></th>
				</tr>
			</thead>
		</table>	
			<div  id="toolbar" region="north" border="false"
				style="border-bottom: 1px solid #ddd; height: 32px; padding: 2px 5px; background: #fafafa;">
				<div style="float: left;">
					<a href="#" class="easyui-linkbutton" plain="true" onclick="newRole()"  icon="icon-add"><spring:message code="button.Add" /></a>
				</div>

				<div class="datagrid-btn-separator"></div>

				<div style="float: left;">
					<a href="#" class="easyui-linkbutton" plain="true" onclick="editRole()" icon="icon-edit"><spring:message code="button.Edit" /></a>
				</div>

				<div class="datagrid-btn-separator"></div>

				<div style="float: left;">
					<a href="#" class="easyui-linkbutton" plain="true" onclick="deleteRole()"
						icon="icon-remove"><spring:message code="button.Remove" /></a>
				</div>
				<%-- <div style="float: left;">
					<a href="#" class="easyui-linkbutton" plain="true" onclick="setRoleUser()"
						icon="icon-edit"><spring:message code="button.SetRoleUser" /></a>
				</div> --%>

				<div id="tb" style="float: right;">
					<input id="ss" class="easyui-searchbox"
						searcher="doSearch" prompt="<spring:message code="rolemaintain.searchname" />"
						style="width: 130px; vertical-align: middle;"></input> 
				</div>

			</div>
		
		
		
		
		
		
		

		<div id="dlg" class="easyui-dialog"
			style="width: 350px; height: 250px; padding: 10px 10px" closed="true"
			buttons="#dlg-buttons">
			<form id="fm" method="post" commandName="defaultRole">
				<table cellspacing="10px;">
					<tr style="display: none">
						<td></td>
						<td><input id="id" name="id" style="width: 200px;"></td>
					</tr>
					<tr>
						<td><spring:message code="rolemaintain.name" />：</td>					
						<td><input id="code" name="name" class="easyui-textbox"
							required="true" style="width: 200px;" onBlur="checkCode(this)"></td>
					</tr>
					<tr>
						<td><spring:message code="rolemaintain.desc" />：</td>
						<td><input name="desc" class="easyui-textbox"
							 style="width: 200px;" ></td>
					</tr>
					<tr>
						<td><spring:message code="rolemaintain.roletype" />：</td>
						<td><input name="type" class="easyui-textbox"
							 style="width: 200px;" ></td>
					</tr>
					<tr>
						<td><spring:message code="rolemaintain.sequence" />：</td>
						<td><input name="sortOrder" class="easyui-numberbox"
							 style="width: 200px;" ></td>
					</tr>
				</table>
			</form>
		</div>

		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" onclick="saveRole()"><spring:message
					code="button.Save" /></a> <a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#dlg').dialog('close')"><spring:message
					code="button.Close" /></a>
		</div>
		
		
		
		 <div id="dlguser" class="easyui-dialog"
			style="width: 450px; height: 400px;" closed="true"
			buttons="#dlguser-buttons">
			<table id="dgRoleUser" class="easyui-datagrid"  style="width:100%;height:100%"
			  data-options="rownumbers:true,singleSelect:false">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>											
						<th data-options="field:'username',width:80"><spring:message code="usermaintain.username"/></th>
						<th data-options="field:'userCode', width:80"><spring:message code="usermaintain.usercode"/></th>				     																								
					</tr>
				</thead>
	        </table>
		</div>

		<div id="dlguser-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" onclick="saveRoleUser()"><spring:message
					code="button.Save" /></a> <a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#dlguser').dialog('close')"><spring:message
					code="button.Close" /></a>
		</div>
		
		
		
		

	</div>

</body>
<script type="text/javascript">
	$(document).ready(function() {
		InitTreeData();		
	});
	$.extend($.messager.defaults,{
	    ok:'<spring:message code="confirm"/>',
	    cancel:'<spring:message code="cancel"/>'
	});
	$(function(){
		$('#dg').datagrid({
			onClickRow:function(index,data){
				var row = $('#dg').datagrid('getSelected');
				if (row){					
					loadDataToUserRole(row);
					InitTreeData()
					loadDataToUserFrmFunction(row);
				}
			}               
		});
		
		var p = $('#dgroleuser').datagrid('getPager'); 
		$(p).pagination({           
			pageSize: 10,//每页显示的记录条数，默认为10           
			//pageList: [5,10,15,20],//可以设置每页记录条数的列表           
			beforePageText: '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
			afterPageText: '<spring:message code="pagination.afterPageText"/>',           
			displayMsg: '<spring:message code="pagination.displayMsg"/>',
			onSelectPage: function (pageNumber, pageSize) {//分页触发
				var row = $('#dg').datagrid('getSelected');
				 if(row){
			        	var roleid = row.id;
			        	var pageNumber = $('#dgroleuser').datagrid('options').pageNumber;
						var pageSize = $('#dgroleuser').datagrid('options').pageSize;
			        	$("#dgroleuser").datagrid("loading"); 
			        	$.post('rolemaintain/getroleuser.do', {
			        		roleid : roleid,
			        		pageNumber : pageNumber,
			        		pageSize : pageSize
						}, function(result) {
							$("#dgroleuser").datagrid('loadData', result);	
							$("#dgroleuser").datagrid("loaded"); 
						}, 'json');       	
			        }
	         }
	
		});
		
		
		
		
	})
	function InitTreeData() {
		$('#tt').tree({
			url : 'functionMaintain/createtree.do',
			checkbox : true,
			//animate : true,
			 onLoadSuccess:function(){  
			 var row = $('#dg').datagrid('getSelected');
			 //绑定权限  
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
	
	function doSearch(value){		
		$.post('rolemaintain/rolelist.do', {
    		rolename : value
		}, function(result) {
			// var result = $.parseJSON(result);
		     $("#dg").datagrid('loadData', result);
		}, 'json');  
	}

	
	function loadDataToUserRole(row){
		 if(row){
	        	var roleid = row.id;
	        	var pageNumber = $('#dgroleuser').datagrid('options').pageNumber;
				var pageSize = $('#dgroleuser').datagrid('options').pageSize;
	        	$("#dgroleuser").datagrid("loading"); 
	        	$.post('rolemaintain/getroleuser.do', {
	        		roleid : roleid,
	        		pageNumber : pageNumber,
	        		pageSize : pageSize
				}, function(result) {
					$("#dgroleuser").datagrid('loadData', result);	
					$("#dgroleuser").datagrid("loaded"); 
				}, 'json');       	
	        }
	}
	function loadDataToUserFrmFunction(row){
		if(row){			
			var roleid = row.id;			
			$.post('rolemaintain/getrolefrmfunction.do', {
        		roleid : roleid
			}, function(result) {				
				if(result.success){					
					//InitTreeData();	
					var array = result.frmuuis;  				
					 for(var i=0;i<array.length;i++){					
						 var node = $('#tt').tree('find',array[i]);
						
						 $('#tt').tree('check',node.target);
					 }  
					
				}
				 					

			}, 'json');  
		}
	}
	
	
	function deleteRole() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('<spring:message code="system.alert"/>','<spring:message code="rolemaintain.delete.confirm" arguments="'+row.name+'"/>', function(r) {
				if (r) {
					$.post('rolemaintain/roleDelete.do', {
						id : row.id
					}, function(result) {						
						if (result.success) {
							$.messager.alert('<spring:message code="system.alert"/>',result.msg);
							$("#dg").datagrid("reload");
						} else {
							$.messager.alert('<spring:message code="system.alert"/>',result.msg);
						}
					}, 'json');
				}
			});
		}
	}
	

	function newRole() {
		$("#dlg").dialog('open').dialog('setTitle',
				'<spring:message code="rolemaintain.addroleinfo"/>');
		$('#fm').form('clear');
	}

	function editRole() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$("#dlg").dialog('open').dialog('setTitle',
					'<spring:message code="rolemaintain.editroleinfo"/>');
			$('#fm').form('load', row);		
		}
	}

	function saveRole() {
		$('#fm').form(
				'submit',
				{
					url : 'rolemaintain/roleSave.do',
					onSubmit : function() {
						return $(this).form('validate');
					},
					success : function(result) {
						var rt = $.parseJSON(result);
						if (rt.success) {
							$.messager.alert(
									'<spring:message code="system.alert"/>',
									rt.msg);
							$('#dlg').dialog('close');
							$("#dg").datagrid("reload");
						} else {
							$.messager.alert(
									'<spring:message code="system.alert"/>',
									rt.msg);
							return;
						}
					}
				});
	}
	function setRoleUser(){	
		var row=$('#dg').datagrid('getSelected');
		if(row!=null){
			$("#dlguser").dialog('open').dialog('setTitle',
			'<spring:message code="rolemaintain.adduser"/>');
		}else{
			$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="noselectrecord"/>');
		}
		
	}
	
	
	function saveRoleUser(){
		/* var ss = [];
		var row=$('#dg').datagrid('getSelected');
		var useruuid=row.appuseruuid;
		var rows = $("#dgRoleUser").datagrid('getSelections');					
		for(var i=0; i<rows.length; i++){
			var row = rows[i];				
			ss.push(row.roleuuid);
		}
		var roleuuids = ss.join(";") ;					
        $.post('usermaintain/saveuserrole.do', {
        	useruuid : useruuid,
        	roleuuids:roleuuids
		}, function(result) {						
			if (result.success) {	                        	                  	               						             		   		       
             
		    $.messager.alert('<spring:message code="system.alert"/>',result.msg);
		}, 'json');  */

	}
	
	
	function deleteUserRole(){		
		var row = $('#dg').datagrid('getSelected');
		var rows = $('#dgroleuser').datagrid('getSelections');
		var ss = [];
		for(var i=0; i<rows.length; i++){
			var item = rows[i];				
			ss.push(item.username);
		}		
		var usernames = ss.join(";") ;
		if (usernames!='') {
			$.messager.confirm('<spring:message code="system.alert"/>','<spring:message code="rolemaintain.deleteroleuser.confirm" arguments="'+row.name+','+usernames+'"/>', function(r) {
				if (r) {				
							
					$.post('rolemaintain/roleuserDelete.do', {
						usernames : usernames
					}, function(result) {
						$.messager.alert('<spring:message code="system.alert"/>',result.msg);
						if (result.success) {							
							loadDataToUserRole(row);
						}
					}, 'json');
				}
			});
		}else{
			$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="noselectrecord"/>');
		}
	
		
	}
	function checkCode(val){
		var code = val.value;
		var uuid = $('#uuid').val();		
		if(code!=""&&uuid==""){
			$.post('rolemaintain/checkcode.do', {
				code : code
			}, function(result) {						
				if (result.success) {					
					$.messager.alert('<spring:message code="system.alert"/>',result.msg);				
				} else {
					return;
				}
			}, 'json');
		}		
	}
	
	function expandAll() {
		$('#tt').tree('expandAll');
	}
	function collapseAll() {
		$('#tt').tree('collapseAll');
	}
	function saveRoleFrmfunction(){	
		var fnode = GetNode('fnode');		
		var child = GetNode('child');
		var row = $('#dg').datagrid('getSelected');
		var roleid = row.id;
		$.post('rolemaintain/saverolefunction.do', {
			roleid : roleid,
    		fnode : fnode,
    		child : child
		}, function(result) {
			//var result = $.parseJSON(result);			
			$.messager.alert('<spring:message code="system.alert"/>',result.msg);			
		}, 'json');  
		
	}
	function GetNode(type){  
		  var node = $('#tt').tree('getChecked');  
		  var cnodes='';  
		  var pnodes='';  
		              
		  var prevNode=''; //保存上一步所选父节点  
		   for(var i=0;i<node.length;i++){  
             
			  if($('#tt').tree('isLeaf',node[i].target)){  
			     cnodes+=node[i].id+';';    	        
		          var pnode = $('#tt').tree('getParent',node[i].target); //获取当前节点的父节点  
			      if(prevNode!=pnode.id) //保证当前父节点与上一次父节点不同  
			      {  
				       pnodes+=pnode.id+';';  
				       prevNode = pnode.id; //保存当前节点  
		          }  
			   }  
		   }  
		   cnodes = cnodes.substring(0,cnodes.length-1);  
		   pnodes = pnodes.substring(0,pnodes.length-1);  
		            
	       if(type=='child'){return cnodes;}  
		   else{return pnodes};  		       
  }
	
</script>

</html>