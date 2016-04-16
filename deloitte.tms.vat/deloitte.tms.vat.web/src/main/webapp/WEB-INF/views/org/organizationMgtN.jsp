<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="content-language" content="zh-CN" />
	<title></title>   
  
	
	 <%@ include file="/common/global.jsp" %> 
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
				
					<table cellpadding="5">
					
						 <tr style="display:none">
						<td >组织ID:</td>
							<td><input class="easyui-textbox" id="id" 
								name="id" style="width: 200px"></input></td>
						</tr>
						<tr style="display:none" >
						
							<td>上级组织ID:</td>
							<td><input class="easyui-textbox" name="parentId" id="parentId"
								style="width: 200px"></input></td>
								
						</tr>
						
						<tr>
							<td>组织代码:</td>
							<td><input class="easyui-textbox" id="orgCode"  
								name="orgCode" style="width: 200px"></input></td>
						</tr>
						
						 
						<tr>
							<td>组织名称:</td>
							<td><input class="easyui-textbox"  name="orgName"
								 style="width: 200px" required='required'></input></td>
						</tr>
						<tr>
							<td>组织简介:</td>
							<td><input class="easyui-textbox"  name="des" 
								 style="width: 200px;height: 50px;"></input>						

							</td>
						</tr>
						
						<tr>
							<td>是否虚拟组织:</td>
							<td>
							
							<select name="virtual" >
								<option value="0">否</option>
								<option value="1">是</option>
						    </select>
							
							</td>
						</tr>
						
	<!-- 					<tr>
							<td>BIZ组织代码:</td>
							<td><input  class="easyui-combobox"
								name="bizOrgCode" style="width: 200px"
								data-options="panelHeight:'auto'"></input>

							</td>
						</tr> -->
						
						<tr>
							<td>组织类型:</td>
							<td>
							<input class="easyui-textbox"  name="orgType"
								 style="width: 200px" ></input>
								 
							<!-- <input  class="easyui-combobox"
								name="orgType" style="width: 200px"
								data-options="panelHeight:'auto'"></input>
 -->
							</td>
						</tr>
						
						<tr id="usernameTr">
							<td>所属用户:</td>
							<td>
							<input id="freeLegalList" name="allUsername"  style="width: 200px"  >
							 
							</td>
						</tr>
						
						<tr style="display:none" >
						
							<td>creatOrUpdate:</td>
							<td><input class="easyui-textbox" name="creatOrUpdate" id="creatOrUpdate"
								style="width: 200px"></input></td>
								
						</tr>
						


					</table>
				</form>
				<div style="text-align: left; padding: 5px">
								
								
								<a href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-remove'" style="width:80px" id='remove2But' onclick="remove2()"><spring:message
						code="button.Remove" /></a>				
								
					<a href="javascript:void(0)" class="easyui-linkbutton" id='updateNodeBut'
						data-options="iconCls:'icon-save'" style="width:80px" onclick="updateForm()">
						更新提交</a> 						
						
					<a href="javascript:void(0)" class="easyui-linkbutton" id='initNewNode2But' data-options="iconCls:'icon-save'" style="width:80px" onclick='initNewNode2()'>新增节点</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" id='initNewNode3But' data-options="iconCls:'icon-save'" style="width:100px" onclick='initNewNode3()'>新增根节点</a>
					<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="width:80px"  id='saveNewNodeBut' onclick="createForm()">新增提交</a>
				</div>
			</div>
		</div>


	</div>


</body>
<script type="text/javascript">
var createRoot=false;
var allIDs="";

	$.extend($.messager.defaults,{
	    ok:'<spring:message code="confirm"/>',
	    cancel:'<spring:message code="cancel"/>'
	});	
	
	$(document).ready(function() {
		callRefresh();
	});
	
	
	
	function InitLegalCombobox(){
		
		//alert("InitLegalCombobox()...");
		
		$('#freeLegalList').combobox({
		url:'orgMgt/getAllUser.do',
		valueField:'username',
		textField:'username',
		multiple:true,
		//panelHeight:'auto',
		
		onSelect : function(record){
			
			var legalEntityId2 = $('#freeLegalList').combobox('getValues');
			//alert(legalEntityId2);
			//alert($('#freeLegalList').combobox('getValues'));
			//alert(legalEntityId2);
			
			//$('#legalEntityId').val(legalEntityId2);
			//alert($('#legalEntityId').val());
			
			//alert('legalEntityId maybe no show but have setinto');
			//$('#legalEntityId').text(legalEntityId2);
			
			//alert($('#legalEntityId').text());
			
			$('#ff').form('load', {
				
				//legalEntityId :legalEntityId2
			});
		}
	});
		
	
	//$('#freeLegalList').combobox('setValue', '');
	
}
	
	
	function callRefresh(){
		createRoot=false;
		$('#saveNewNodeBut').css('display', 'none');
		InitLegalCombobox();
		InitTreeData();
	}
	
	function noTreeNode(){
		
		var roots = $('#tt').tree('getRoots');
		
		if(typeof(roots) == 'undefined' || roots==null || roots.length < 1){
			//一个节点都没有 初始状态
			return true;
		}else{
			
			//for(root : root2){
				
				//alert(roots);
			//}
			return false;
		}
	}
	
	function initNewNode2(){
		createRoot=false;
		clearForm();
		if(noTreeNode()){
			
		}else{
			var nowLegEquNode = $('#tt').tree('getSelected');
			if(nowLegEquNode==null ||  typeof(nowLegEquNode) == 'undefined' ){
				alert('请先选中左边 树的节点');
				return;
			}
			if( nowLegEquNode.id == null || typeof(nowLegEquNode.id )=='undefined' || $.trim( nowLegEquNode.id ) == ''){
				alert('请先选中左边 树的节点,不能获取到节点的id');
				return;
			}
			$('#ff').form('load', {
				parentId : nowLegEquNode.id
			});
		}
		
		
		$('#freeLegalList').combobox('enable');
		$('#saveNewNodeBut').css('display', 'inline-block');
		$('#updateNodeBut').css('display', 'none');
		$('#initNewNode2But').css('display', 'none');
		$('#initNewNode3But').css('display', 'none');
		$('#remove2But').css('display', 'none');
		$('#usernameTr').css('display', 'none');
	}
	
	function initNewNode3(){
		createRoot=true;
		clearForm();
		if(createRoot||noTreeNode()){
			
		}else{
			var nowLegEquNode = $('#tt').tree('getSelected');
			if(nowLegEquNode==null ||  typeof(nowLegEquNode) == 'undefined' ){
				alert('请先选中左边树的节点');
				return;
			}
			if( nowLegEquNode.id == null || typeof(nowLegEquNode.id )=='undefined' || $.trim( nowLegEquNode.id ) == ''){
				alert('请先选中左边树的节点,不能获取到节点的id');
				return;
			}
			$('#ff').form('load', {
				parentId : nowLegEquNode.id
			});
		}
		
		$('#freeLegalList').combobox('enable');
		$('#saveNewNodeBut').css('display', 'inline-block');
		$('#updateNodeBut').css('display', 'none');
		$('#initNewNode2But').css('display', 'none');
		$('#initNewNode3But').css('display', 'none');
		$('#remove2But').css('display', 'none');
		$('#usernameTr').css('display', 'none');
	}
	
	
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
				//	bizOrgCode : node.bizOrgCode,
					orgType : node.orgType
					
				});	
				
				//$('#freeLegalList').combobox('disable');
				
			 	$('#saveNewNodeBut').css('display', 'none');
			 	
			 	$('#updateNodeBut').css('display', 'inline-block');
			 	
				$('#initNewNode2But').css('display', 'inline-block');
				$('#initNewNode3But').css('display', 'inline-block');
				$('#remove2But').css('display', 'inline-block');
				$('#usernameTr').css('display', '');
				
				setUsernames4Up(node.id);
			}
		});
	}
	
	function setUsernames4Up(orgId){
		if(orgId){
			MaskUtil.mask();
			
	    	$.post('orgMgt/loadUsernamesByOrgId.do', {
	    		orgId : orgId
			}, function(result) {
			
				MaskUtil.unmask(); 
				
			   $('#freeLegalList').combobox('setValues', result);

	           $("#dg").datagrid("loaded");                           
			}, 'json'); 
		}else{
			alert('从树中获取选中节点的组织id异常');
		}
	}
	

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
					   MaskUtil.mask(); 
					  
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
		                        
		                        MaskUtil.unmask();
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
		var node = $('#tt').tree('getSelected');
		if((!createRoot) &&( node==null || node ==undefined)){
			$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="noselectrecord"/>');
			return;
		}
		$('#ff').form('submit', {
			url:'orgMgt/create.do',	
			onSubmit : function() {
				MaskUtil.mask();
				return $(this).form('enableValidation').form('validate');				
			},
			success:function(result){
				
				MaskUtil.unmask();
				
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
				
				MaskUtil.mask();
				return $(this).form('enableValidation').form('validate');
			},
			success:function(result){
				
				MaskUtil.unmask();
				
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