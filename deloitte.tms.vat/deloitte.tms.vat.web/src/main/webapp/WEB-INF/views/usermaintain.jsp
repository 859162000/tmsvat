<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Basic Form - jQuery EasyUI Demo</title>
<!-- 	<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="themes/icon.css">
	<link rel="stylesheet" type="text/css" href="themes/demo.css"> 
	<script type="text/javascript" src="resource/corejs/jquery.min.js"></script>
    <script type="text/javascript" src="resource/corejs/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="resource/corejs/jquery.easyui.min.js"></script> -->   
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.4/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.4/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.4/demo.css">
	<script type="text/javascript" src="jquery-easyui-1.4.4/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.4.4/jquery-1.8.0.js"></script> 
	 <script type="text/javascript" src="resource/corejs/dateformat.js"></script>   
</head>


 

<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">    
    <div region="north" split="true" border="false" style="overflow: hidden; height: 65%;">  
        <div class="easyui-panel" title="<spring:message code="searchgroup"/>" style="width:100%;height:25%; background-color: #E0ECFF">		
		    <form id="searchform" method="post" commandName="userSearchForm">
		    	<table cellpadding="10">
		    		<tr>
		    			<td><spring:message code="usermaintain.userid"/>:</td>
		    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="userid"></input></td>
		    			<td><spring:message code="usermaintain.username"/>:</td>
		    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="name"></input></td>
		    			<td><spring:message code="createdatefrom"/>:</td>
		    			<td><input class="easyui-datebox" type="text" style="width:150px;" name="createdatefrom" data-options="formatter:myformatter,parser:myparser"></input></td>
		    			<td><spring:message code="To"/>:</td>
		    			<td><input class="easyui-datebox" type="text" style="width:150px;" name="createdateto" data-options="formatter:myformatter,parser:myparser"></input></td>
		    		    <td><spring:message code="status"/>:</td>
		    			<td>
		    		       <input id="searchStatus" class="easyui-combobox" style="width:150px;" data-options="panelHeight:'auto'" name="status"></input>
		    		    </td>
		    			<td>
		    			   <div style="text-align:center;padding:10px">
		    	             <a href="#" id="searchbtn"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px" onclick="Search()"><spring:message code="button.search"/></a>
		    	             <a href="#" class="easyui-linkbutton" style="width:80px" onclick="clearSearchForm()"><spring:message code="button.clear"/></a>			                      
		                    
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
	         <table class="easyui-datagrid" id="dg" title="<spring:message code="usermaintain.userinfo"/>" style="width:100%;height:75%" data-options="					
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
       <div style="padding:5px 10px;">
		<a href="#" id="usermaintain.add" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addTo()"><spring:message code="button.Add"/></a>
		<a href="#" id="usermaintain.save" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="save()"><spring:message code="button.Save"/></a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="remove2()"><spring:message code="button.Remove"/></a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="setRole()"><spring:message code="button.SetRole"/></a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="reSetPsw()"><spring:message code="button.ResetPsw"/></a>		
	   </div>
	   <div style="padding:5px 10px;">
	       <form id="saveform" method="post" commandName="appuser">
	    	<table cellpadding="5">
	    		<tr>
	    		    <td><spring:message code="usermaintain.userid"/>:</td>
		    		<td><input class="easyui-textbox" type="text" style="width:150px;" name="userid" data-options="required:true" onblur="checkUserId(this);"></input></td>
		    		<td><spring:message code="usermaintain.username"/>:</td>
		    		<td><input class="easyui-textbox" type="text" style="width:150px;" name="name"></input></td>
	    			<td><spring:message code="usermaintain.email"/>:</td>
	    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="email" data-options="validType:'email'"></input></td>
	    			<td><spring:message code="usermaintain.fax"/>:</td>
		    		<td><input class="easyui-textbox" type="text" style="width:150px;" name="fax"></input></td>
	    		</tr>
	    		<tr>
	    			
		    		<td><spring:message code="usermaintain.tel"/>:</td>
		    		<td><input class="easyui-textbox" type="text" style="width:150px;" name="tel"></input></td>
	    			<td><spring:message code="status"/>:</td>
	    			<td>
		    		  <input id="status" class="easyui-combobox" style="width:150px;" data-options="panelHeight:'auto',required:true" name="status"></input>
		    		</td>
		    		<td><spring:message code="createperson"/>:</td>
		    		<td><input class="easyui-textbox" type="text" style="width:150px;" data-options="disabled:true" name="createby"></input></td>
		    		<td><spring:message code="createdate"/>:</td>
		    		<td><input class="easyui-textbox" type="text" style="width:150px;" data-options="disabled:true" name="createdate"></input></td>
	    		</tr>
	    		<tr>
	    			<td><spring:message code="updateperson"/>:</td>
		    		<td><input class="easyui-textbox" type="text" style="width:150px;" data-options="disabled:true" name="updateby"></input></td>
		    		<td><spring:message code="updatedate"/>:</td>
		    		<td><input class="easyui-textbox" type="text" style="width:150px;" data-options="disabled:true" name="updatedate"></input></td>
	    		</tr>
	    		<tr style="display: none">
							<td></td>
							<td><input id="uuid" class="easyui-textbox" name="appuseruuid"
								style="width: 200px"></input></td>
				</tr>	    	
	    	</table>
	    </form>
	   </div>
	   
	   <div id="dlg" class="easyui-dialog"
			style="width: 450px; height: 400px;" closed="true"
			buttons="#dlg-buttons">
			<table id="dgUserRole" class="easyui-datagrid"  style="width:100%;height:100%"
			  data-options="rownumbers:true,singleSelect:false">
				<thead>
					<tr>
					    <th data-options="field:'useruuid', width:5, hidden:true"></th>
					    <th data-options="field:'roleuuid', width:5, hidden:true"></th>
						<th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'code',width:80" ><spring:message code="rolemaintain.code" /></th>
						<th data-options="field:'name',width:100"><spring:message code="rolemaintain.name" /></th>
						<th data-options="field:'setPerson',width:80"><spring:message code="usermaintain.rolesetperson" /></th>
						<th data-options="field:'setDate',width:110" formatter="formattime"><spring:message code="usermaintain.rolesetdate" /></th>						
					</tr>
				</thead>
	        </table>
		</div>

		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" onclick="saveUserRole()"><spring:message
					code="button.Save" /></a> <a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#dlg').dialog('close')"><spring:message
					code="button.close" /></a>
		</div>
		
		
		<div id="dlgresetpsw" class="easyui-dialog"
			style="width: 350px; height: 180px; padding: 10px 10px" closed="true"
			buttons="#dlgresetpsw-buttons">	
			  <form id="newpassword" method="post">		
				<table cellspacing="10px;">	
				    <tr style="display: none">
						<td></td>
						<td><input id="uuid" name="uuid" style="width: 200px;"></td>
					</tr>									
					<tr>
						<td><spring:message code="usermaintain.newpassword" />：</td>
						<td><input  name="password" class="easyui-validatebox"
							required="true" style="width: 200px;" ></td>
					</tr>
				</table>
			 </form>
		</div>

		<div id="dlgresetpsw-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" onclick="savePassword()"><spring:message
					code="button.Save" /></a> <a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#dlgresetpsw').dialog('close')"><spring:message
					code="button.close" /></a>
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
			columns:[[
				{field:'userid',title:'<spring:message code="usermaintain.userid"/>',width:100,align:'center'},
				{field:'name',title:'<spring:message code="usermaintain.username"/>',width:80,align:'center'},
				{field:'email',title:'<spring:message code="usermaintain.email"/>',width:100,align:'center'},
				{field:'fax',title:'<spring:message code="usermaintain.fax"/>',width:80,align:'center'},
				{field:'tel',title:'<spring:message code="usermaintain.tel"/>',width:80,align:'center'},
				{field:'strStatus',title:'<spring:message code="status"/>',width:80,align:'center'},
				{field:'createby',title:'<spring:message code="createperson"/>',width:80,align:'center'},
				{field:'createdate',title:'<spring:message code="createdate"/>',width:100,align:'center'},
				{field:'updateby',title:'<spring:message code="updateperson"/>',width:80,align:'center'},
				{field:'updatedate',title:'<spring:message code="updatedate"/>',width:100,align:'center'},
			]],
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
			onSelectPage: function (pageNumber, pageSize) {//分页触发		
		    	 find(pageNumber,pageSize);
				 Search(); 
				 clearSaveForm();
	         }
	
		});
	});
	
	$(function(){
		$('#dgUserRole').datagrid({
			onLoadSuccess:function(data){
				if(data){
					$.each(data.rows,function(index,item){
						if(item.haveset){
							$('#dgUserRole').datagrid('checkRow', index);
						}
					});
				}
			}                    
		});
	})

	$(document).ready(function() {
		InitCombobox();	
		$('#searchform').form('load', {
			pageNumber : $('#dg').datagrid('options').pageNumber,
			pageSize : $('#dg').datagrid('options').pageSize
		});
	});
	

    
	function InitCombobox(){
		$('#status').combobox({
			url:'usermaintain/getstatus.do',
			valueField:'value',
			textField:'text'
		});
		$('#searchStatus').combobox({
			url:'usermaintain/getstatus.do',
			valueField:'value',
			textField:'text'
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
			url:'usermaintain/search.do',			
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
		clearSaveForm();
	}
	
	function save(){		
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
	}
	function remove2(){
		var row = $('#dg').datagrid('getSelected');		
		var uuid = row.appuseruuid;	
		var arguments = row.userid;
	    $.messager.confirm('<spring:message code="system.alert"/>','<spring:message code="usermaintain.delete.confirm" arguments="'+arguments+'"/>',function(result){  
			  if (result){  			      
			      $.ajax({
	                    url : "usermaintain/delete.do?uuid="+uuid,
	                    dataType : "json",
	                    cache:false,
	                    success : function(result) {	                    	
	                        if (result.success) {	                        		                        	
	                        	Search();
	        					clearSaveForm(); 
	                        } 
	                        $.messager.alert('<spring:message code="system.alert"/>',result.msg);
	                    }
	                });
			     
			  }
		  });  		
	}
	function setRole(){
		var row=$('#dg').datagrid('getSelected');
        if(row){
        	var useruuid = row.appuseruuid;
        	$("#dlg").dialog('open').dialog('setTitle',
    		'<spring:message code="usermaintain.setrole"/>');
        	$("#dgUserRole").datagrid("loading"); 
        	$.post('usermaintain/getuserrole.do', {
        		useruuid : useruuid
			}, function(result) {						
				 if (result.success) {	                        	                  	               						 
            		    $("#dgUserRole").datagrid('loadData', result);		       
            		    $("#dgUserRole").datagrid("loaded");                		    
                   } else {
                 	  $("#dgUserRole").datagrid("loaded");
                      
                   }
			}, 'json');       	
        }
		
	}
	function reSetPsw(){
		var row=$('#dg').datagrid('getSelected');
        if(row){       	
        	$("#dlgresetpsw").dialog('open').dialog('setTitle',
    		'<spring:message code="usermaintain.resetpassword"/>');
        	$('#newpassword').form('load', {				
				uuid : row.appuseruuid
			});
        }
	}
	function savePassword(){
		var row=$('#dg').datagrid('getSelected');		
        if(row){
        	$('#newpassword').form('submit', {
    			url:'usermaintain/resetpsw.do',	
    			onSubmit : function() {
    				return $(this).form('enableValidation').form('validate');
    			},			
    			success:function(result){ 
    				var result = $.parseJSON(result);	
    				$.messager.alert('<spring:message code="system.alert"/>',result.msg);   				
    			} 
    		});    	
        }
	}
	function saveUserRole(){
		var ss = [];
		var row=$('#dg').datagrid('getSelected');
		var useruuid=row.appuseruuid;
		var rows = $('#dgUserRole').datagrid('getSelections');					
		for(var i=0; i<rows.length; i++){
			var row = rows[i];				
			ss.push(row.roleuuid);
		}
		var roleuuids = ss.join(";") ;			
		$("#dgUserRole").datagrid("loading"); 
        $.post('usermaintain/saveuserrole.do', {
        	useruuid : useruuid,
        	roleuuids:roleuuids
		}, function(result) {						
			if (result.success) {	                        	                  	               						             		   		       
              $("#dgUserRole").datagrid("loaded");                		    
            } else {
                $("#dgUserRole").datagrid("loaded");                     
            }
		    $.messager.alert('<spring:message code="system.alert"/>',result.msg);
		}, 'json'); 

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
	
	function checkUserId(val){
		var userid = val.value;
		var uuid = $('#uuid').val();	
		if(userid!=""&&uuid==""){
			$.post('usermaintain/checkuserid.do', {
				userid : userid
			}, function(result) {						
				if (result.success) {					
					$.messager.alert('<spring:message code="system.alert"/>',result.msg);					
				} else {
					return;
				}
			}, 'json');
		}		
	}
	
</script>
</html>