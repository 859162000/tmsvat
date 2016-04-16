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
	

<script type="text/javascript">

var allIDs="";

	$.extend($.messager.defaults,{
	    ok:'<spring:message code="confirm"/>',
	    cancel:'<spring:message code="cancel"/>'
	});	
	$(document).ready(function() {
		//$("input[name=forNavigation]").get(0).checked = true
		//allIDs="";
		
		alert("1");
		InitLegalCombobox();
InitEquCombobox();
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
			url : 'legalEquipment/treeLegaEquipment.do',			
			animate : true,
			onClick : function(node) {
				
			  
						  //alert("remove2 4:"+uuids +":--->"+allIDs);
						  
					      $.ajax({
			                    url : "legalEquipment/listLegalEquipmentBy.do",
			                    dataType : "json",
			                    success : function(result) {
			                        if (result.success) {	                        	
			                        //	$.messager.alert('<spring:message code="system.alert"/>',result.msg);
			                        	InitCombobox();
			                        	//clearForm();
			                        } else {
			                            $.messager.alert('<spring:message code="system.alert"/>',result.msg);
			                        }
			                        
			                    }
			                });
					     
			
				
				
				
			/**
			*need set parentuuid?
			*$('#parentuuid').combobox('setValue', node.pid);
			*/
				
			/* 	if(node.navigationFlag=='1'){
					$("input[name=forNavigation]").get(1).checked = true;
				}else{
					$("input[name=forNavigation]").get(0).checked = true;
				} */				
/* 				$('#ff').form('load', {
					id : node.id,
					//orgCode : node.orgCode,
					//strtarget : node.strTarget,
					//url : node.action,
					//icon : node.iconCls,
					//des : node.description,
					orgName : node.text,
					//parentName : node.parentName,
					parentId : node.pid,
					//virtual : node.virtual,
					//bizOrgCode : node.bizOrgCode,
					//orgType : node.orgType
					
				});	 */
				
				
				
			}

		});
		
		//alert($("#id")[0].value);
	}
	
 	function InitLegalCombobox(){
	
 		alert("2");
 		
 		$('#freeLegalList').combobox({
			url:'legalEquipment/listLegalFree.do',
			
			//multiple:true,
		//	panelHeight:'auto'
			valueField:'id',
			textField:'legalEntityName'
			
		});
		
		
		//$('#freeLegalList').combobox('setValue', '');
		
	}
 	
 	
 	function InitEquCombobox(){
		$('#freeEquList').combobox({
			url:'legalEquipment/listEquipmentFree.do',
			valueField:'id',
			textField:'equipmentName'
		});
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
			
			
			getAllID(node);
			//alert("remove2 2:"+allIDs);
			
			//alert("remove2 3:"+uuids);
			//alert(allIDs);
			var arguments = node.text;
		    $.messager.confirm('<spring:message code="system.alert"/>','<spring:message code="functionmaintain.delete.confirm" arguments="'+arguments+'"/>',function(result){
				  if (result){
					  //alert("remove2 4:"+uuids +":--->"+allIDs);
					  
				      $.ajax({
		                    url : "legalEquipment/delLegalEquipment.do?id="+allIDs,
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
	
	
	
	function linkEquToLegalTree(){
		
		var nowLegalEquNode = $('#tt').tree('getSelected');
		
		if(typeof( nowLegalEquNode ) == 'undefined'){
			
			$.messager.alert('<spring:message code="system.alert"/>','请先选中一条纳税主体与打印机关系树中的记录');
			return;
		}

	var nowEquVal = $("#freeEquList").val();
	var nowEquName = $("#freeEquList  option:selected").text();
	
	if( typeof(nowEquVal) == 'undefined' ||  typeof(nowEquName) == 'undefined'){
		
		$.messager.alert('<spring:message code="system.alert"/>','请先选中一台设备');
		return;
	}


	var legalName=nowLegalEquNode.attributes1.legalName; //???其它字段
	
	
		if(nowEquVal!=null){
			
	
		    $.messager.confirm('<spring:message code="system.alert"/>','确定把设备 "' + nowEquName +'" 绑定到纳税主体 ' + legalName +' 吗?' ,function(result){
				  if (result){
					  //alert("remove2 4:"+uuids +":--->"+allIDs);
					  
					  wrapAttribute1();
					  
				      $.ajax({
		                    url : "legalEquipment/linkEquToLegalTree.do",//post tmsMdLegalEquipment or two id 
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
			$.messager.alert('<spring:message code="system.alert"/>','请先选中一台设备');
		}		      
	
	
		
	}
	
	
	/**
	put left panel
	解除绑定
	*/
	function removeEquFromLegal(){
		

		var nowLegEquNode = $('#tt').tree('getSelected');
		
		if(nowLegEquNode!=null){
		
/*
 * 			
	map.put("equId", legEqu.getEquipmentId());
	map.put("equName", "default equName..");
	
	map.put("legId", legEqu.getLegalEntityId());
	map.put("legName", "default legName..");
	
	map.put("tmsMdLegalEquipment", legEqu);		

	
	node.setAttributes(map);
 */
			
			
			var legalName= nowLegEquNode.attributes.legalName;
			var equName=nowLegEquNode.attributes.equName;
		    $.messager.confirm('<spring:message code="system.alert"/>','确定解除设备 "'+ equName +'" 与纳税主体 ' + legalName +'" 的绑定吗?',function(result){
				  if (result){
					  //alert("remove2 4:"+uuids +":--->"+allIDs);
					  
					  wrapAttribute1();
					  
				      $.ajax({
		                    url : "legalEquipment/delEquipmentFromLegal.do",
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
	
	
	/*
	 * 			
		把纳税主体 加入树， 作为当前选择 纳税主体的 下级
	 */
	function addLegalToTree(){
		

		var nowLegEquNode = $('#tt').tree('getSelected');
		
		if(nowLegEquNode!=null){
		
			var nowLegVal = $("#freeLegalList").val();
			var nowLegName = $("#freeLegalList  option:selected").text();
			
			if( typeof(nowEquVal) == 'undefined'){
				
				$.messager.alert('<spring:message code="system.alert"/>','请先从可用纳税主体列表选中一条记录');
				return;
			}
			
			
			
			var legalName= nowLegEquNode.attributes.legalName;
			var equName=nowLegEquNode.attributes.equName;
		    $.messager.confirm('<spring:message code="system.alert"/>','确定添加 "'+ nowLegName +'" 到左边树作为 ' + legalName +'" 的下级纳税主体?',function(result){
				  if (result){
			
					  wrapAttribute1();
					  
					  
				      $.ajax({
		                    url : "legalEquipment/addLegalIntoTree.do",
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
	
	function wrapAttribute1(){
		
		//from tree
		
		/**
		var spe=#xTiger#x
		*attributes1=legalName + spe + equName + spe + newLegalId + spe + newLegalName + spe + newEquId + spe + newEquName
		*
		*/
		
		var nowLegEquNode = $('#tt').tree('getSelected');
		
		if(nowLegEquNode!=null){
		
			var legalName= nowLegEquNode.attributes.legalName;
			var equName=nowLegEquNode.attributes.equName;
			
		}
		
		//newly
		var newLegalId = $("#freeLegalList").val();
		var newLegalName = $("#freeLegalList  option:selected").text();
		
		var newEquId = $("#freeEquList").val();
		var newEquName = $("#freeEquList  option:selected").text();
		
		var spe='#xTiger#x';
		var attributes1=legalName + spe + equName + spe + newLegalId + spe + newLegalName + spe + newEquId + spe + newEquName;
		
		$("#attributes1").val()=attributes1;
		
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
	
	<div data-options="region:'center',title:'纳税实体及设备选择'"	>
		<div class="easyui-panel" style="width: 100%; height: 100%;">


				<div class="easyui-panel"	style="padding: 5px, width:50%; height: 93%"">
				
							
				
							<input  class="easyui-combobox" id="freeLegalList" name="freeLegalList"  >
							

							<a href="#" class="easyui-linkbutton" style="width:80px" onclick="addLegalToTree()">增加纳税主体</a> 

							
			
				</div>
				

	


			<div class="easyui-panel"	style="padding: 5px, width:50%; height: 93%"">
				
					<!-- 		<div style="margin: 10px 5px;"> -->
				
						<input id="freeEquList" name="freeEquList" >
						
						<a href="#"	class="easyui-linkbutton" style="width:80px" onclick="linkEquToLegalTree()">关联设备</a>  


							<!-- </div> -->
			
			</div>
			
			

		</div>

	 </div>
	



</body>


</html>