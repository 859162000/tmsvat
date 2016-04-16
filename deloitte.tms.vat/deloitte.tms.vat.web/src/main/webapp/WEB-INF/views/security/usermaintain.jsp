<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="content-language" content="zh-CN" />
	<title>Basic Form - jQuery EasyUI Demo</title>   
   <!--  <link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.4/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.4/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.4/demo.css">
	<script type="text/javascript" src="jquery-easyui-1.4.4/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.4.4/jquery-1.8.0.js"></script> 
	<script type="text/javascript" src="common/commonjs/common.js"></script>  -->
	
	 <%@ include file="/common/global.jsp" %> 
</head>


 

<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">    
    <div region="north" split="true" border="false" style="overflow: hidden; height:20%;">  
        <div class="easyui-panel" title="<spring:message code="searchgroup"/>" style="width:100%;height:100%; background-color: #E0ECFF">		
		    <form id="searchform" method="post" commandName="userSearchForm">
		    	<table cellpadding="5">
		    		<tr>
		    			<td><spring:message code="usermaintain.username"/>:</td>
				    	<td><input class="easyui-textbox" type="text" style="width:150px;" name="username" ></input></td>
				    	<td><spring:message code="usermaintain.userid"/>:</td>
				    	<td><input class="easyui-textbox" type="text" style="width:150px;" name="userCode"></input></td>    				    				    		
			    		<td>
			    		  <div style="text-align:center;padding:10px">
			    	          <a href="#" id="searchbtn"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px" onclick="Search()"><spring:message code="button.Search"/></a>
			    	          <a href="#" class="easyui-linkbutton" style="width:80px" onclick="clearSearchForm()"><spring:message code="button.Clear"/></a>			                      
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
	        	         
    </div>
    <div data-options="region:'center',border:false" style="background-color: #E0ECFF">  
	    <div style="width:100%;height:100%">
	         <table class="easyui-datagrid" id="dg" title="<spring:message code="usermaintain.userinfo"/>" style="width:100%;height:100%" data-options="					
					singleSelect:true,
					autoRowHeight:false,
					pagination:true,
					pageSize:10,
					remoteSort:false,
				    multiSort:true	
					">			 
		   </table>   
	   </div>		
	   <div id="dlg" class="easyui-dialog"
			style="width: 800px; height: 300px;" closed="true"
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

			<div style="margin:20px 0;"></div>			
				    <form id="saveform" method="post" commandName="defaultUser">
				    	<table cellpadding="10">
				    		<tr>			   		
				    			<td><spring:message code="usermaintain.username"/>:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" id="usernameid" name="username" data-options="required:true"></input></td>
				    			<td><spring:message code="usermaintain.usercode"/>:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="userCode"></input></td>
				    			<td><spring:message code="usermaintain.email"/>:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="email" data-options="validType:'email'"></input></td>
				    		</tr>
				    		<tr>			   		
				    			<td><spring:message code="usermaintain.cnname"/>:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="cname"></input></td>
				    			<td><spring:message code="usermaintain.enname"/>:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="ename"></input></td>
				    			<td><spring:message code="usermaintain.tel"/>:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="mobile"></input></td>
				    		</tr>
				    		<tr>			   						    			
				    			<td><spring:message code="usermaintain.userremark"/>:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="remark"></input></td>
				    			<td><spring:message code="usermaintain.validatedatefrom"/>:</td>
				    			<td><input class="easyui-datebox" type="text" style="width:150px;" name="effectDate" data-options="formatter:myformatter,parser:myparser"></input></td>
				    			<td><spring:message code="usermaintain.validatedateto"/>:</td>
				    			<td><input class="easyui-datebox" type="text" style="width:150px;" name="quitDate" data-options="formatter:myformatter,parser:myparser"></input></td>
				    		</tr>
				    		<tr style="display: none">							
							<td><input id="userId" class="easyui-textbox" name="userId"
								style="width: 200px"></input></td>
				</tr>	 					    		
				    			    				    			 	    	
		    	       </table>
				    </form>			   			
		</div>
		
		
		<div id="dlgsetuser" class="easyui-dialog"
			style="width: 450px; height: 400px;" closed="true"
			buttons="#dlgsetuser-buttons">
			<table id="dgUserRole" class="easyui-datagrid"  style="width:100%;height:100%"
			  data-options="rownumbers:true">
				<thead>
					<tr>	
					    <th data-options="field:'ck',checkbox:true"></th>				   
					    <th field="name" width="100"><spring:message code="rolemaintain.name" /></th>
					    <th field="desc" width="150"><spring:message code="rolemaintain.desc" /></th>
					    <th field="type" width="100"><spring:message code="rolemaintain.roletype" /></th>					
					</tr>
				</thead>
	        </table>
		</div>
		
		
		<div id="dlgsetuser-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-ok" onclick="saveUserRole()"><spring:message
					code="button.Save" /></a> <a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#dlgsetuser').dialog('close')"><spring:message
					code="button.Close" /></a>
		</div>	
				
		<div id="dlgsetorg" class="easyui-dialog"
			style="width: 450px; height: 400px;" closed="true"
			 data-options="				
				buttons: [{
					text:'<spring:message code="button.Save"/>',
					iconCls:'icon-ok',
					handler:function(){
						saveUserOrg();
					}
				},{
					text:'<spring:message code="button.Close"/>',
					iconCls:'icon-cancel',
					handler:function(){
						$('#dlgsetorg').dialog('close');
					}
				}]
			">
		
		
				<ul id="setorgtree"></ul>

		
		
		</div>
		
	
	         
    </div>

    
   
</body>
<script type="text/javascript">

var debugOpen=true;


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
				{field:'username',title:'<spring:message code="usermaintain.username"/>',width:100,align:'center'},
				{field:'userCode',title:'<spring:message code="usermaintain.usercode"/>',width:80,align:'center'},
				{field:'email',title:'<spring:message code="usermaintain.email"/>',width:80,align:'center'},
				{field:'cname',title:'<spring:message code="usermaintain.cnname"/>',width:100,align:'center'},
				{field:'ename',title:'<spring:message code="usermaintain.enname"/>',width:100,align:'center'},
				{field:'mobile',title:'<spring:message code="usermaintain.tel"/>',width:80,align:'center'},
				{field:'remark',title:'<spring:message code="usermaintain.userremark"/>',width:80,align:'center'},
				{field:'effectDate',title:'<spring:message code="usermaintain.validatedatefrom"/>',width:80,align:'center'},
				{field:'quitDate',title:'<spring:message code="usermaintain.validatedateto"/>',width:80,align:'center'},
				
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
	            },'-',
	            
	            /* {  
	                text:'<spring:message code="button.Remove"/>',  
	                iconCls:'icon-remove',  
	                handler:function(){  
	                	remove2();  
	                }  
	            },'-',
	             */
	            {  
	                text:'<spring:message code="button.SetRole"/>',  
	                iconCls:'icon-edit',  
	                handler:function(){  
	                	setRole();  
	                }  
	            },'-',{  
	                text:'<spring:message code="button.SetOrg"/>',  
	                iconCls:'icon-edit',  
	                handler:function(){  
	                	setOrg();  
	                }  
	            },'-'
	            
	            
	            ],
			
	            onLoadSuccess:function(row){//当表格成功加载时执行               
	                var rowData = row.rows;
	                $.each(rowData,function(idx,val){//遍历JSON
	                      if(val.checked){
	                        $("#dg").datagrid("selectRow", idx);//如果数据行为已选中则选中改行
	                      }
	                });              
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
				// clearSaveForm();
	         }
	
		});
		
		
	});
	
	 $(function(){
		$('#dgUserRole').datagrid({
			 onLoadSuccess:function(row){//当表格成功加载时执行               
	                var rowData = row.rows;		      
	                $.each(rowData,function(idx,val){//遍历JSON	                	
	                      if(val.checked){
	                        $("#dgUserRole").datagrid("checkRow", idx);//如果数据行为已选中则选中改行
	                      }
	                });              
	            }    
		});
	}) 

	$(document).ready(function() {
		//InitCombobox();	
		
		
		$('#searchform').form('load', {
			pageNumber : $('#dg').datagrid('options').pageNumber,
			pageSize : $('#dg').datagrid('options').pageSize
		});
		
		Search();
	});
	

    
	/* function InitCombobox(){
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
		
	} */
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
		$('#saveform').form('clear');
		 $('#usernameid').textbox('enable');
		$("#dlg").dialog('open').dialog('setTitle',
		'<spring:message code="usermaintain.add"/>');
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
			url:'usermaintain/save.do',	
			onSubmit : function() {
				//return $(this).form('enableValidation').form('validate');
				
			},			
			success:function(result){
				var result = $.parseJSON(result);				
				 if(result.success){						
					Search();
					clearSaveForm(); 
					
					$('#dlg').dialog('close');
				} 
				$.messager.alert('<spring:message code="system.alert"/>',result.msg);
			} 
		});
	}
	function remove2(){
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
	function setRole(){				
		 var row=$('#dg').datagrid('getSelected');
        if(row){
        	var username = row.username;
        	$("#dlgsetuser").dialog('open').dialog('setTitle',
    		'<spring:message code="usermaintain.setrole"/>');
        	
        	$.post('usermaintain/getuserrole.do', {
        		username : username
			}, function(result) {
				 $("#dgUserRole").datagrid('loadData', result);	
			}, 'json');       	
        }else{
        	$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="noselectrecord"/>');
        }
		
	} 
	
	function setOrg(){
		 var row=$('#dg').datagrid('getSelected');
       if(row){
       	var username = row.username;
       	$("#dlgsetorg").dialog('open').dialog('setTitle',
   		'<spring:message code="button.SetOrg"/>');
       	
       	
       	
       	
		$('#setorgtree').tree({
			url : 'usermaintain/getuserorg.do?username='+username,			
			animate : true,
			checkbox : true,
			cascadeCheck : false, 
			onClick : function(node) {
						
			
			}

		});
       	      	
       	
       }else{
       	$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="noselectrecord"/>');
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
		var username=row.username;
		var rows = $('#dgUserRole').datagrid('getChecked');					
		for(var i=0; i<rows.length; i++){
			var r = rows[i];				
			ss.push(r.id);
		}
		var roleids = ss.join(";") ;
        $.post('usermaintain/saveuserrole.do', {
        	username : username,
        	roleids:roleids
		}, function(result) {						
			if (result.success) {	                        	                  	               						             		   		       
				$('#dlgsetuser').dialog('close');                    
            }
		    $.messager.alert('<spring:message code="system.alert"/>',result.msg);
		}, 'json'); 

	}
	
	function dlog(obj){
		
		if(debugOpen){
			alert(obj);
		}else{
			
		}
	}
	
	function saveUserOrg(){
		
		var orgIds=[];
		var username='';
		var orgIds2='';
		
		var allNodes=$('#setorgtree').tree('getChecked');//没选什么也不为空， 而是为length=0的数组
		
		if(allNodes){
			
			var len = allNodes.length;
			
			if(len>0){
			
				for(var x=0; x<len; x++){
					
					var node =	allNodes[x];
					
					username=node.attributes.username;
					
				    orgIds.push(	node.id );
					}
			}else{
				
				var row=$('#dg').datagrid('getSelected');
				if(row){
					
					username=row.username;
					dlog('username from dg datagrid:'+username);
				}else{
					
					$('#dlgsetorg').dialog('close');
					alert('请先选中一条用户记录');
				}	
			}		
		
			
		}else{
			alert('strange:'+allNodes);
			
		}
		
		
		orgIds2=orgIds.join(' , ');
		
		//alert(orgIds2);
		
	      $.post('usermaintain/updateUserOrg.do', {
	        	username : username,
	        	orgIds2 : orgIds2
			}, function(result) {						
				if (result.success) {            	                  	               						             		   		       
					$('#dlgsetorg').dialog('close');                    
	            
					$.messager.alert('<spring:message code="system.alert"/>',result.msg);
				    
				}else{
					
					$.messager.alert('<spring:message code="system.alert"/>',result.errorMsg);
				}
			    
			    
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