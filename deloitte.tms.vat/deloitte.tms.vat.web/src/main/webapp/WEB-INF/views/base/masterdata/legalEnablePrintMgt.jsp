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
	<script type="text/javascript" src="common/commonjs/common.js"></script>
</head>

<script type="text/javascript">
	var allIDs = "";
	var noTreeData = false;
	var createRoot = false;
	
	$.extend($.messager.defaults,{
	    ok:'<spring:message code="confirm"/>',
	    cancel:'<spring:message code="cancel"/>'
	});
	
	$(document).ready(function() {
		callRefresh();
	});
	
	function callRefresh(){
		createRoot=false;
		$('#saveNewNodeBut').css('display', 'none');
		InitLegalCombobox();
		InitTreeData();
	}
	
	function InitTreeData() {
		allIDs="";
		noTreeData=true;
		$('#tt').tree({
			url : 'legalPrint/treeLegalPrint.do',	
			state : 'open',
			animate : true,
			onClick : function(node) {
					$('#ff').form('load', {
						id : node.id,
						des : node.attributes.des,
						legalEntityName : node.attributes.legalEntityName,
						legalEntityId : node.attributes.legalEntityId,
						parentId : node.pid
					});	
					$('#effectDateShow').datebox('setValue', node.attributes.effectDateShow);
					$('#quitDateShow').datebox('setValue' , node.attributes.quitDateShow);
					$('#freeLegalList').combobox('setValue', node.attributes.legalEntityName);
					$('#freeLegalList').combobox('disable');
					var a = node.attributes.isEnablePrint;
					//isEnablePrint : node.attributes.isEnablePrint,
					var b=node.attributes.enabledFlag;
									
				 	if(true == a || 'true'==a || '1'==a){
				 		$('#isEnablePrint').val('true');
				 	}else{
				 		$('#isEnablePrint').val('false');
				 	}
				 	
				 	if(true == b || 'true'==b || '1'==b){
				 		$('#enabledFlag').val('true');
				 	}else{
				 		$('#enabledFlag').val('false');
				 	}
				 	$('#saveNewNodeBut').css('display', 'none');
				 	$('#updateNodeBut').css('display', 'inline-block');
					$('#initNewNode2But').css('display', 'inline-block');
					$('#remove2But').css('display', 'inline-block');
					$('#initNewNode3But').css('display', 'inline-block');
			}
		});
	}
 	
 	function InitLegalCombobox(){
		$('#freeLegalList').combobox({
			url:'legalPrint/listLegalFree.do',
			valueField:'id',
			textField:'legalEntityName',
			onSelect : function(record){
				var legalEntityId2 = $('#freeLegalList').combobox('getValue');
				$('#ff').form('load', {
					legalEntityId :legalEntityId2
				});
			}
		});	
	}
 	
 	function getChildren(){
 		var node = $('#tt').tree('getSelected');
 		if (node){
 			var children = $('#tt').tree('getChildren', node.target);
 		} else {
 			var children = $('#tt').tree('getChildren');
 		}
 		//var s = '';
 		var sid='';
 		for(var i=0; i<children.length; i++){
 			sid += children[i].attributes.legalEntityId + ',';
 		}
 		
 		return sid;
 	}
 	
 	function noTreeNode(){
 		var roots = $('#tt').tree('getRoots');
 		if(typeof(roots) == 'undefined' || roots==null || roots.length < 1){
 			//一个节点都没有 初始状态
 			return true;
 		}else{
 			return false;
 		}
 	}
 	
 	
 	function initNewNode2(){
 		//alert('initNewNode2()');
 		
 		clearForm();
 		createRoot = false;
 		if(noTreeNode()){
 			
 		}else{
	 		var nowLegEquNode = $('#tt').tree('getSelected');
	 		if(nowLegEquNode==null ||  typeof(nowLegEquNode) == 'undefined' ){
	 			alert('请先选中左边 主体树  的节点');
	 			return;
	 		}
	 		
	 		if( nowLegEquNode.attributes.legalEntityId == null || typeof(nowLegEquNode.attributes.legalEntityId )=='undefined' || $.trim( nowLegEquNode.attributes.legalEntityId ) == ''){
	 			alert('请先选中左边 主体树  的节点,不能获取到节点的legalEntityId');
	 			return;
	 		}
 		}
 		
 		$('#freeLegalList').combobox('enable');
 		$('#saveNewNodeBut').css('display', 'inline-block');
 		$('#updateNodeBut').css('display', 'none');
 		$('#initNewNode2But').css('display', 'none');
 		$('#remove2But').css('display', 'none');
 		$('#initNewNode3But').css('display', 'none');
 	}
 	function initNewNode3(){
 		clearForm();
 		createRoot = true;
 		$('#freeLegalList').combobox('enable');
 		$('#saveNewNodeBut').css('display', 'inline-block');
 		$('#updateNodeBut').css('display', 'none');
 		$('#initNewNode2But').css('display', 'none');
 		$('#remove2But').css('display', 'none');
 		$('#initNewNode3But').css('display', 'none');
 	}
 	
 	function saveNewNode() {
 		var v = $('#effectDateShow').datebox('getValue'); 
 		var v2 = $('#quitDateShow').datebox('getValue'); 
 		if( createRoot || noTreeNode() ){
 			//一个节点都没有 初始状态
 		}else{
 			try{
 				var node = $('#tt').tree('getSelected');
 				if(node==null || node ==undefined){
					$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="noselectrecord"/>');
					return;
				}
 				
 				var nowNodeLegalId = node.attributes.legalEntityId;
 				
 				$('#ff').form('load', {
 					parentId : nowNodeLegalId,
 					flag : '0'
 				});
 			}catch(e){
 				alert('saveNewNode() > '+e);
 			}
 		}
 		
 		$('#ff').form('submit', {
 			//url:'useLocalLegal/addNew.do',	//???????????????? todo
 			url: 'legalPrint/addNew.do',
 			onSubmit : function() {
 				return $(this).form('enableValidation').form('validate');
 			},
 			success:function(result){
 				if(result=='' || typeof(result)=='undefined'){
 					$.messager.alert('<spring:message code="system.alert"/>',result.errorMsg);
 					return;
 				}
 				
 				var result = $.parseJSON(result);				
 				if(result.success){
 					$.messager.alert('<spring:message code="system.alert"/>',result.msg);
 					callRefresh();
 	            	clearForm();
 				}else{
 					$.messager.alert('<spring:message code="system.alert"/>',result.errorMsg);					
 				}
 			} 
 		});
 	}
 	
 	function updateNode() {
 		try{
 			$('#ff').form('load', {
 				flag : '0'
 			});
 			
 			var node = $('#tt').tree('getSelected');
 			if(node==null || node ==undefined){
 					$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="noselectrecord"/>');
 					return;
 				}
 		}catch(e){
 			
 		}

 		$('#ff').form('submit', {
 			//url:'useLocalLegal/update.do',
 			url:'legalPrint/update.do',
 			onSubmit : function() {
 				return $(this).form('enableValidation').form('validate');
 			},
 			success:function(result){
 				var result = $.parseJSON(result);				
 				if(result.success){
 					$.messager.alert('<spring:message code="system.alert"/>',result.msg);
 					//InitTreeData();					
 	            	
 					callRefresh();
 	            	
 	            	clearForm();
 				}else{
 					$.messager.alert('<spring:message code="system.alert"/>',result.errorMsg);					
 				}
 			} 
 		});
 	}

	function expandAll() {
		$('#tt').tree('expandAll');
	}
	function collapseAll() {
		$('#tt').tree('collapseAll');
	}
	
	function getAllID(node){
		if(node!=null){
			if(allIDs==""){
				allIDs=node.id+"#xfxDeloitte#";
			}else{
				allIDs=allIDs+node.id+"#xfxDeloitte#";
			}
		}
		var len=node.children.length;
		if(len>0){
			for(var i=0; i<len;i++){
				childNode=node.children[i];
				allIDs=allIDs+	childNode.id  +"#xfxDeloitte#";
				if(childNode.children.length>0){
					getAllID(childNode.children[i]);
				}
			}
		}
	}
	/* function remove2() {
		var node = $('#tt').tree('getSelected');
		
		if(node!=null){
			allIDs="";
			
			
			getAllID(node);
			var postUrl='';
			var arguments = node.text;
		    $.messager.confirm('<spring:message code="system.alert"/>','确定从关系树中删除选中的纳税主体节点: '+arguments+' 吗?' ,  function(result){
				  if (result){
					  //alert("remove 4:"+uuids +":--->"+allIDs);
					  
					  postUrl='legalPrint/delLegalPrint.do?legalPrintIds='+allIDs;
					//  alert("remove2 will post url: "+postUrl);
					  
				      $.ajax({
		                    url : postUrl,
		                    //dataType : "json", ???????????????????
		                    
		                    success : function(result) {
		                        if (result.success) {	                        	
		                        	$.messager.alert('<spring:message code="system.alert"/>',result.msg);
		                        	
		                        	callRefresh();
		                        	
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
	
	} */
	
	function remove2() {
		var node = $('#tt').tree('getSelected');
		if(node!=null){
			var allRemoveIds=node.attributes.legalEntityId + ',' + getChildren();
			var postUrl='';
			var arguments = node.text;
		    $.messager.confirm('<spring:message code="system.alert"/>','确定从关系树中删除选中的纳税主体节点: '+arguments+' 吗?(包含子节点一起删除)' ,  function(result){
				  if (result){
					  		
					  postUrl='legalPrint/delLegalPrint.do?legalPrintIds='+allRemoveIds;
					 // postUrl='useLocalLegal/delUseLocalNode.do?useLocalIds='+allRemoveIds;
					  
				      $.ajax({
				    	    async:false,
		                    url : postUrl,
		                   // dataType : "json",
		                    
		                    success : function(result) {
		                    	
		                    	var result = $.parseJSON(result);
		                    	
		                        if (result.success || 'true'==result.success) {
		                        	
		                        	$.messager.alert('<spring:message code="system.alert"/>','成功删除');
		                        	clearForm();
		                        	callRefresh();
		                        	
		                        	
		                        } else {
		                        	$.messager.alert('<spring:message code="system.alert"/>','成功删除'); //????
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
	
	function empty(obj){
		if(obj==null || typeof(obj)=='undefined'){
			return true;
		}
		return false;
	}
	
	function wrapAttribute1(){
		return;
		var legalName="";
		var equName="";
		//from tree
		
		/**
		var spe=#xfxDeloitte#
		*attribute1=legalName + spe + equName + spe + newLegalId + spe + newLegalName + spe + newEquId + spe + newEquName
		*
		*/
		
		var nowLegEquNode = $('#tt').tree('getSelected');
		if(nowLegEquNode!=null){
		
			legalName= nowLegEquNode.attributes.legalName;
			if( empty(legalName)){
				legalName="";
			}
			
			equName=nowLegEquNode.attributes.equName;
			if(empty(equName)){
				equName="";
			}
		}
		//newly

		var newLegalId = $('#freeLegalList').combobox('getValue');
		if(empty(newLegalId)){
			newLegalId="";
		}
		var newLegalName =$('#freeLegalList').combobox('getText');
		if(empty(newLegalName)){
			newLegalName="";
		}
	}
	
	function clearForm() {
		$('#ff').form('clear');
	}
	
	function showGroup(){
		$('#printInfo').propertygrid({
			showGroup:true
		});
	}
	function hideGroup(){
		$('#printInfo').propertygrid({
			showGroup:false
		});
	}
	function hideHeader(){
		$('#printInfo').propertygrid({
			showHeader:false
		});
	}
	function getChanges(){
		var s = '';
		var rows = $('#printInfo').propertygrid('getChanges');
		for(var i=0; i<rows.length; i++){
			s += rows[i].name + ':' + rows[i].value + ',';
		}
		alert(s)
	}
</script>
<body class="easyui-layout">
	<div data-options="region:'west',split:true,title:'纳税主体与打印点关系树'" 	style="width: 30%;">
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
	
	<div data-options="region:'center',title:'纳税主体打印点属性'"   style="width: 30%;">
		<div class="easyui-panel" 	style="width: 100%; height: 100%; background-color: #E0ECFF">
			<div style="padding: 5px 10px 20px 10px">
			


	<form id="ff" method="post"  commandName="tmsMdLegalEnablePrint">
			
					<table cellpadding="5">
						
						<tr    style="display: none;">
						<td >主键ID:</td>
							<td><input class="easyui-textbox" id="id" type="text" 
								name="id" style="width: 200px"></input></td>
						</tr>
						
						
						 <tr  style="display: none;">
						<td >删除标志:</td>
							<td><input class="easyui-textbox" id="flag" type="text" value="0"
								name="flag" style="width: 200px"></input></td>
						</tr>
						
						
						<tr  style="display: none;" >
						
							<td>上级组织ID:</td>
							<td><input class="easyui-textbox" name="parentId" id="parentId"
								style="width: 200px"></input></td>
								
						</tr>

						
					  <tr>
							<td>纳税主体名称:</td>
							<td>
							<input id="freeLegalList" name="freeLegalList"  required='required'>								 
								 
								 </td>
						</tr>
						
						<tr style="display: none;">
						<td></td><td>
						
						<input class="easyui-textbox" type="text" name="legalEntityId"  
								 style="width: 200px;"></input></td>
						
						</tr>
						
						
						
						<tr>
							<td>是否打印点:</td>
							<td>			
								
							<select name="isEnablePrint"  id="isEnablePrint">
								<option value=false>否</option>
								<option value=true>是</option>
						    </select>

							</td>
						</tr>
						
						<tr>
							<td>是否启用:</td>
							<td>
					
							<select name="enabledFlag"  id="enabledFlag">
								<option value=false>否</option>
								<option value=true>是</option>
						    </select>
							
							</td>
						</tr>
						
						<tr>
							<td>有效日期:</td>
							<td>
								<input id="effectDateShow" name="effectDateShow" class="easyui-datebox" style="width:110px;" ></input>

							</td>
						</tr>
						
						
						<tr  style="display: none;"><td></td>
						<td>
						<input class="easyui-textbox" type="text" name="effectDate"   id="effectDate"  style="width: 200px;"></input>
						</td>
						</tr>
						
						<tr>
							<td>失效日期:</td>
							<td>
								<input id="quitDateShow" name="quitDateShow" class="easyui-datebox" style="width:110px;"   ></input>

							</td>
						</tr>
						
						<tr  style="display: none;">
							<td></td>
							<td>
								<input class="easyui-textbox" type="text" name="quitDate"   id="quitDate" style="width: 200px;"></input>
						 	</td>
						 </tr>
						
						
						
						<tr>
							<td>描述:</td>
							<td>
								
								<input class="easyui-textbox" type="text" name="des" 
								 style="width: 160px;height: 50px;"></input>
							

							</td>
						</tr>
				


					</table>
				</form>
				<div style="text-align: left; padding: 5px">
				 <!--  		
					<a href="javascript:void(0)" class="easyui-linkbutton"	data-options="iconCls:'icon-save'" style="width:80px" onclick="updateForm()">提交更新</a> 
			 -->
					<a href="javascript:void(0)" class="easyui-linkbutton" style="width:80px" id='remove2But' data-options="iconCls:'icon-remove'" onclick="remove2()">删除</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" id="updateNodeBut" data-options="iconCls:'icon-save'" style="width:80px" onclick="updateNode()">更新提交</a> 
					<a href="javascript:void(0)" class="easyui-linkbutton" id='initNewNode2But'	data-options="iconCls:'icon-save'" style="width:80px" onclick='initNewNode2()'>新增节点</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" id='initNewNode3But' data-options="iconCls:'icon-save'" style="width:100px" onclick='initNewNode3()'>新增根节点</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" id="saveNewNodeBut"	data-options="iconCls:'icon-save'" style="width:80px;display: none;" onclick="saveNewNode()" >新增提交</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>