<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<head>
 <link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.4/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.4/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.4/demo.css">
	<script type="text/javascript" src="jquery-easyui-1.4.4/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.4.4/jquery-1.8.0.js"></script> 
	<script type="text/javascript" src="common/commonjs/common.js"></script> 
	
</script>


</head>
<body class="easyui-layout">
	<div
		data-options="region:'west',split:true,title:'功能'"
		style="width: 25%;">
		<div class="easyui-panel" style="width: 100%; height: 100%;">
			<div style="margin: 10px 5px;">
				<a href="#" class="easyui-linkbutton" style="width:80px" onclick="collapseAll()"><spring:message
						code="button.CollapseAll" /></a> 
						
						<a href="#"
					class="easyui-linkbutton" style="width:80px" onclick="expandAll()"><spring:message
						code="button.ExpandAll" /></a>  
						
	<%-- 					<a href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-remove'" style="width:80px" onclick="remove2()"><spring:message
						code="button.Remove" /></a>						
						
						<a href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-add'" style="width:80px" onclick="addTo()"><spring:message
						code="button.Add" /></a>	 --%>

			</div>
			<div class="easyui-panel"
				style="padding: 5px, width:100%; height: 93%"">
				<ul id="tt" class="easyui-tree"></ul>
			</div>
		</div>


	</div>
	<div
		data-options="region:'center',title:'明细'">
		<div class="easyui-panel"
			style="width: 100%; height: 100%; background-color: #E0ECFF">
			<div style="padding: 5px 10px 20px 10px">
				<form id="ff" method="post"  commandName="defaultDept">
				<!-- commandName="defaultDept" -->
					<table cellpadding="5">
						<%-- 
						<tr>
							<td><spring:message
									code="functionmaintain.mainlayout.functiondetail.viewindex" />:</td>
							<td><input class="easyui-numberbox" id="index" type="text"
								name="order" data-options="required:true"
								style="width: 200px"></input></td>
						</tr>
						 --%>
						 <tr style="display:none">
						<td >组织ID:</td>
							<td><input class="easyui-textbox" id="id" type="text" 
								name="id" style="width: 200px"></input></td>
						</tr>
						<tr>
							<td>组织代码:</td>
							<td><input class="easyui-textbox" id="orgCode" type="text" 
								name="orgCode" style="width: 200px"></input></td>
						</tr>
						
						 
						<tr>
							<td>组织名称:</td>
							<td><input class="easyui-textbox" type="text" name="orgName"
								 style="width: 200px"></input></td>
						</tr>
						<tr>
							<td>组织简介:</td>
							<td><input id="des" class="easyui-combobox"
								name="des" style="width: 200px"
								data-options="panelHeight:'auto'"></input>

							</td>
						</tr>
						
						<tr>
							<td>是否虚拟组织:</td>
							<td>
							<!-- <input  class="easyui-combobox"
								name="virtual" style="width: 200px"
								data-options="panelHeight:'auto'" ></input>
							 -->	
						<!-- 		<input name="virtual"  type="text" class="easyui-numberbox" 
								precision="0" max="1"  min="0"  size="1"   maxlength="1"  style="text-align:right;"/> -->
								
							<select name="virtual" >
								<option value="0">否</option>
								<option value="1">是</option>
						    </select>
							
							</td>
						</tr>
						
						<tr>
							<td>BIZ组织代码:</td>
							<td><input  class="easyui-combobox"
								name="bizOrgCode" style="width: 200px"
								data-options="panelHeight:'auto'"></input>

							</td>
						</tr>
						
												<tr>
							<td>组织类型:</td>
							<td><input  class="easyui-combobox"
								name="orgType" style="width: 200px"
								data-options="panelHeight:'auto'"></input>

							</td>
						</tr>
						
						
 						<tr style="display:none" >
						
							<td>上级组织ID:</td>
							<td><input class="easyui-textbox" name="parentId" id="parentuuid"
								style="width: 200px"></input></td>
								
						</tr>
						
						
						
						<tr style="display:none" >
						
							<td>creatOrUpdate:</td>
							<td><input class="easyui-textbox" name="creatOrUpdate" id="creatOrUpdate"
								style="width: 200px"></input></td>
								
						</tr>
						<%--
						<tr>
							<td>上级组织:</td>
							<td><input class="easyui-textbox" name="parentName"
								style="width: 200px"></input></td>
						</tr>
						
						
						
						<tr>
							<td>上级组织ID:</td>
							<td><input id="parentuuid" class="easyui-combobox"
								name="parentId" style="width: 200px"
								data-options="panelHeight:'auto'"></input>

							</td>
						</tr>
	
						 
						 <tr>
						    <td style="text-align:right;"><spring:message
									code="functionmaintain.mainlayout.functiondetail.isnavigation" />:</td>
						        <td style="text-align:left">
						            <span class="radioSpan">
						                <input type="radio" name="forNavigation" value="0"><spring:message code="no" /></input>
						                <input type="radio" name="forNavigation" value="1"><spring:message code="yes" /></input>
						            </span>
						        </td>
						</tr>
						
						<tr>
							<td><spring:message
									code="functionmaintain.mainlayout.functiondetail.description" />:</td>
							<td><input class="easyui-textbox" name="desc"
								style="width: 200px;height:100px"></input></td>
						</tr>
						
						 --%>


					</table>
				</form>
				<div style="text-align: left; padding: 5px">
				   <%-- <a href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-add'" style="width:80px" onclick="addTo()"><spring:message
						code="button.Add" /></a> --%>				
					<a href="javascript:void(0)" class="easyui-linkbutton"
						data-options="iconCls:'icon-save'" style="width:80px" onclick="updateForm()">
						更新提交</a> 
					<%-- <a href="javascript:void(0)"
						class="easyui-linkbutton" style="width:80px" onclick="clearForm()"><spring:message
							code="button.Clear" /></a> --%>
							
							
					<a href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-remove'" style="width:80px" onclick="remove2()"><spring:message
						code="button.Remove" /></a>						
						
					<a href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-add'" style="width:80px" onclick="createForm()">新增提交</a>
				</div>
			</div>
		</div>


	</div>
	<%-- <div data-options="region:'east',split:true"
		title="<spring:message code="functionmaintain.mainlayout.easttitle"/>"
		style="width: 48%;">
		<div id="p" class="easyui-panel"  style="width:100%;height:100%;background-color: #E0ECFF">
		    <div style="width:100%;height:50%">
		         <table class="easyui-datagrid" id="dg"  style="width:100%;height:100%" data-options="					
						singleSelect:true,
						autoRowHeight:false,												
						remoteSort:false,
					    multiSort:true	
						">			 
			     </table>   
	        </div>	
	        <div style="padding:5px 10px;">
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addFrmactionTo()"><spring:message code="button.Add"/></a>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="saveFrmaction()"><spring:message code="button.Save"/></a>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="removeFrmaction()"><spring:message code="button.Remove"/></a>					
	        </div>	
	        <div style="padding:5px 10px;">
		       <form id="frmactionform" method="post" commandName="frmaction">
		    	<table cellpadding="5">
		    		<tr>
		    		    <td><spring:message code="functionmaintain.id"/>:</td>
			    		<td><input class="easyui-textbox" type="text" style="width:150px;" name="id" data-options="required:true" tabindex="1"></input></td>
			    		<td><spring:message code="functionmaintain.label"/>:</td>
			    		<td><input class="easyui-textbox" type="text" style="width:150px;" name="lable" tabindex="2"></input></td>		    			
		    		</tr>
		    		<tr>
		    		    <td><spring:message code="functionmaintain.description"/>:</td>
		    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="description" tabindex="3"></input></td>			    			
			    		<td><spring:message code="functionmaintain.labelname"/>:</td>
			    		<td><input class="easyui-textbox" type="text" style="width:150px;" name="lableName" data-options="disabled:true"></input></td>		    					    		
		    		</tr>
		    		<tr>
			    		<td><spring:message code="createperson"/>:</td>
			    		<td><input class="easyui-textbox" type="text" style="width:150px;" data-options="disabled:true" name="createby"></input></td>
			    		<td><spring:message code="createdate"/>:</td>
			    		<td><input class="easyui-textbox" type="text" style="width:150px;" data-options="disabled:true" name="createdate"></input></td>
		    		</tr>
		    		<tr style="display: none">
						<td><input  class="easyui-textbox" name="uuid" style="width: 150px"></td>
						<td><input  class="easyui-textbox" name="frmfunctionmenuuuid" style="width: 150px"></input></td>
					</tr>	    	
		    	</table>
		      </form>
	       </div>
	    </div>
	   

	</div> --%>




</body>
<script type="text/javascript">

var allIDs="";

	$.extend($.messager.defaults,{
	    ok:'<spring:message code="confirm"/>',
	    cancel:'<spring:message code="cancel"/>'
	});	
	$(document).ready(function() {
		//$("input[name=forNavigation]").get(0).checked = true
		//allIDs="";
		InitTreeData();
		//InitCombobox();
		
	});
	
	$(function(){
		
		
		
		/* 
		$('#dg').datagrid({
			url: '',			
			fitColumns: true,
			nowrap: false,			
			rownumbers:true,		
			columns:[[
				{field:'id',title:'<spring:message code="functionmaintain.id"/>',width:100,align:'center'},
				{field:'lable',title:'<spring:message code="functionmaintain.label"/>',width:80,align:'center'},
				{field:'lableName',title:'<spring:message code="functionmaintain.labelname"/>',width:100,align:'center'},	
				{field:'description',title:'<spring:message code="functionmaintain.description"/>',width:100,align:'center'},
				{field:'createby',title:'<spring:message code="createperson"/>',width:80,align:'center'},
				{field:'createdate',title:'<spring:message code="createdate"/>',formatter:function(value, rowData, rowIndex){return formattime(value, rowData, rowIndex)},width:120,align:'center'},				
			]],
			onClickRow:function(index,data){
				var row = $('#dg').datagrid('getSelected');
				if (row){
					loadFrmactionformData(row);
				}
			}
		});		
	 */});

	function InitTreeData() {
		//alert("InitTreeData calling");
		allIDs="";
		$('#tt').tree({
			url : 'orgMgt/createtree.do',			
			animate : true,
			onClick : function(node) {
			/**
			*need set parentuuid?
			*$('#parentuuid').combobox('setValue', node.pid);
			*/
				
			/* 	if(node.navigationFlag=='1'){
					$("input[name=forNavigation]").get(1).checked = true;
				}else{
					$("input[name=forNavigation]").get(0).checked = true;
				} */				
				$('#ff').form('load', {
					id : node.id,
					orgCode : node.orgCode,
					//strtarget : node.strTarget,
					//url : node.action,
					//icon : node.iconCls,
					des : node.description,
					orgName : node.text,
					//parentName : node.parentName,
					parentId : node.pid,
					virtual : node.virtual,
					bizOrgCode : node.bizOrgCode,
					orgType : node.orgType
					
				});	
			}

		});
		
		//alert($("#id")[0].value);
	}
/* 	function InitCombobox(){
		$('#parentuuid').combobox({
			url:'orgMgt/parentmenu.do',
			valueField:'id',
			textField:'text'
		});
	} */

	function expandAll() {
		$('#tt').tree('expandAll');
	}
	function collapseAll() {
		$('#tt').tree('collapseAll');

	}
	
	
	function addTo() {
		
		
		//$('#ff').form('clear');
		var tempId=$('#id')[0].value;
		
		$('#creatOrUpdate')[0].value='create';
		$('#id')[0].value=tempId;
		
		createForm();
		
		//$('#index').textbox('enable');		
	}
	
	
	function getAllID(node){
		
		if(node!=null){
			
			if(allIDs==""){
				allIDs=node.id+"xfxTiger";
			}else{
			
				allIDs=allIDs+node.id+"xfxTiger";
			}
			
		}
		var len=node.children.length;
		if(len>0){
			for(var i=0; i<len;i++){
				
				childNode=node.children[i];
				
				allIDs=allIDs+	childNode.id  +"xfxTiger";
				
				if(childNode.children.length>0){
					getAllID(childNode.children[i]);
				}
			}
			
		}
		
	}
	
	function remove2() {
		var node = $('#tt').tree('getSelected');
		
		if(node!=null){
			allIDs="";
			//var uuid = node.id;
			//alert("remove 1:"+allIDs);
			getAllID(node);
			//alert("remove 2:"+allIDs);
			var uuids = allIDs;
			//alert("remove 3:"+uuids);
			//alert(allIDs);
			var arguments = node.text;
		    $.messager.confirm('<spring:message code="system.alert"/>','<spring:message code="functionmaintain.delete.confirm" arguments="'+arguments+'"/>',function(result){
				  if (result){
					  //alert("remove 4:"+uuids +":--->"+allIDs);
					  
				      $.ajax({
		                    url : "orgMgt/delete.do?uuids="+allIDs,
		                    dataType : "json",
		                    success : function(result) {
		                        if (result.success) {	                        	
		                        	$.messager.alert('<spring:message code="system.alert"/>',result.msg);
		                        	InitTreeData();
		                        	//InitCombobox();
		                        	clearForm();
		                        } else {
		                            $.messager.alert('<spring:message code="system.alert"/>',result.msg);
		                        }
		                        
		                    }
		                });
				     
				  }else{
					  allIDs="";
				  }
			  });  		
		}else{
			$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="noselectrecord"/>');
		}		      
	
	}
	
	function createForm() {
		//alert("createForm:"+$('#id')[0].value);
		
		
		var node = $('#tt').tree('getSelected');

	if(node==null || node ==undefined){
			$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="noselectrecord"/>');
			return;
		}	
		
		$('#ff').form('submit', {
			url:'orgMgt/create.do',	
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success:function(result){
				var result = $.parseJSON(result);				
				if(result.success){
					$.messager.alert('<spring:message code="system.alert"/>',result.msg);
					InitTreeData();					
	            	//InitCombobox();
	            	clearForm(); 
				}else{
					$.messager.alert('<spring:message code="system.alert"/>',result.errorMsg);					
				}
			} 
		});
	}
	
	
	
	function updateForm() {

		
		try{
			var node = $('#tt').tree('getSelected');

			if(node==null || node ==undefined){
					$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="noselectrecord"/>');
					return;
				}
			var creatOrUpdate=$('#creatOrUpdate')[0].value;
			if(creatOrUpdate=='create'){
				createForm();
				return;
			}
			
		}catch(e){
			
		}
		//alert("updateForm:"+$('#id')[0].value);
		
		$('#ff').form('submit', {
			url:'orgMgt/update.do',	
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success:function(result){
				var result = $.parseJSON(result);				
				if(result.success){
					$.messager.alert('<spring:message code="system.alert"/>',result.msg);
					InitTreeData();					
	            	//InitCombobox();
	            	clearForm(); 
				}else{
					$.messager.alert('<spring:message code="system.alert"/>',result.errorMsg);					
				}
			} 
		});
	}
	
	function clearForm() {
		$('#ff').form('clear');
	}
	
	
	function addFrmactionTo(){
		clearFrmactionForm();
		
	}
	function clearFrmactionForm(){
		$('#frmactionform').form('clear');
		var node = $('#tt').tree('getSelected');		
		if(node){
			$('#frmactionform').form('load', {					
				frmfunctionmenuuuid : node.id
			});
		}
	}
	function saveFrmaction(){
		var node = $('#tt').tree('getSelected');
		$('#frmactionform').form('submit', {
			url:'functionMaintain/savefrmaction.do',	
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success:function(result){
				 var result = $.parseJSON(result);
				$.messager.alert('<spring:message code="system.alert"/>',result.msg);				
				if(result.success){
					loadDataToFrmaction(node);
					clearFrmactionForm();
				}
				
			} 
		});
	}
	function removeFrmaction(){
		
	}
	function loadDataToFrmaction(node){
		
		if(node){        	
        	 
        	$.post('orgMgt/getfrmaction.do', {
        		menuuuid : node.id
			}, function(result) {
				 if (result.success) {
					 $("#dg").datagrid("loading");
            		 $("#dg").datagrid('loadData', result.rows);	
            		  $("#dg").datagrid("selectRow",0);
            		 var row = $('#dg').datagrid('getSelected');
            		 loadFrmactionformData(row); 
                 }
               $("#dg").datagrid("loaded");                           
			}, 'json'); 
		}
					
	}
	function loadFrmactionformData(row){
		if(row){
			$('#frmactionform').form('load', {					
				uuid : row.uuid,
				id : row.id,
				lable : row.lable,
				description : row.description,
				createby : row.createby,
				createdate : textformattime(row.createdate),
				lableName : row.lableName
			});
		}
		
	}
	
</script>

</html>