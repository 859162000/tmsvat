<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<head>
<%@ include file="/common/global.jsp"%>
</head>
<body class="easyui-layout">
	<div
		data-options="region:'west',split:true,title:'大小类'"
		style="width:40%;">
		<div class="easyui-panel" style="width: 100%; height: 100%;">
			<div style="margin: 10px 5px;">
				<a href="#" class="easyui-linkbutton" style="width:80px" onclick="collapseAll()"><spring:message
						code="button.CollapseAll" /></a> <a href="#"
					class="easyui-linkbutton" style="width:80px" onclick="expandAll()"><spring:message
						code="button.ExpandAll" /></a>  

			</div>
			<div class="easyui-panel"
				style="padding: 5px, width:100%; height: 93%">
				<ul id="tt" class="easyui-tree"></ul>
			</div>
		</div>


	</div>
	<div
		data-options="region:'center',title:'详细信息'">
		<div class="easyui-panel"
			style="width: 100%; height: 100%; background-color: #E0ECFF">
			<div style="padding: 5px 10px 20px 10px">
				<form id="ff" method="post" 
					commandName="defaultUrl">
					<table cellpadding="5">
					<tr>
							<td colspan="2"><input class="easyui-searchbox" id="findnode" type="text"
								name="findnode" 
								style="width: 275px" ></input>
								</td>
						</tr>
						<tr>
							<td>名称:</td>
							<td><input class="easyui-textbox" id="nameid" type="text"
								name="label" 
								style="width: 200px"></input></td>
						</tr>
						<tr>
							<td>代码:</td>
							<td><input class="easyui-textbox" id="iconid" type="text" 
								name="code" style="width: 200px"></input></td>
						</tr>
						<tr>
							<td>排序:</td>
							<td><input class="easyui-textbox" type="text" name="sortOrder"
								 style="width: 200px"></input></td>
						</tr>
						<tr>
							<td>描述:</td>
							<td><input id="strtargetid" class="easyui-textbox"
								name="description" style="width: 200px"
								data-options="panelHeight:'200px'"></input>

							</td>
						</tr>
						<tr>
							<td>层级:</td>
							<td><input class="easyui-textbox" name="level"
								style="width: 200px"></input></td>
						</tr>
						<tr>
							<td>是否根节点:</td>
						        <td style="text-align:left">
						            <span class="radioSpan">
						                <input type="radio" name="forNavigation" value="0"><spring:message code="no" /></input>
						                <input type="radio" name="forNavigation" value="1"><spring:message code="yes" /></input>
						            </span>
						        </td>
						</tr>
						<tr>
							<td><input  name="id"
								style="width: 200px" id="setid"  type="hidden"></td>
							<td><input   name="parentId"
								style="width: 200px;" type="hidden" id="parentId"></input></td>
								<td><input  id="parentstra"
								style="width: 200px;" name="parentstras" type="hidden"></input></td>
						</tr>

					</table>
				</form>
				<div style="text-align: left; padding: 5px">
				   <a href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-add'" style="width:80px" onclick="addTo()"><spring:message
						code="button.Add" /></a>				
					<a href="javascript:void(0)" class="easyui-linkbutton"
						data-options="iconCls:'icon-save'" style="width:80px" onclick="submitForm()"><spring:message
							code="button.Save" /></a> 
				<a href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-remove'" style="width:80px" onclick="remove2()"><spring:message
						code="button.Remove" /></a>
				</div>
			</div>
		</div>


	</div>

</body>
<script type="text/javascript">
	$.extend($.messager.defaults,{
	    ok:'<spring:message code="confirm"/>',
	    cancel:'<spring:message code="cancel"/>'
	});	
	$(document).ready(function() {
		InitTreeData();
	});
/**
 * 搜索方法
 */
 $('#findnode').searchbox({
	    searcher:function(value,name){
	        	$("#tt").tree("search", value);
	     }
	});

 
/**
 * 菜单树生成
 */
	function InitTreeData() {
		$('#tt').tree({
			url : 'functionMaintain/createtree.do',//数据请求路径			
			animate : true,//定义是否在展开或折叠节点时显示动画效果。
			onClick : function(node) {//当用户单击节点时触发	
				if(node.navigationFlag=='1'){
					$("input[name=forNavigation]").get(1).checked = true;
				}else{
					$("input[name=forNavigation]").get(0).checked = true;
				}				
				$('#ff').form('load', {//修改增加from表单赋值
					label : node.text,//名称
					description : node.strTarget,//描述
					sortOrder : node.action,//排序
					code : node.iconCls,//代码
					level : node.description,//层级
					id : node.id,//节点id
					parentId:node.pid//父节点id
				});				
			}
		});
		$('#tt').tree('collapseAll');
	}

	function expandAll() {//展开所有树
		$('#tt').tree('expandAll');
	}
	function collapseAll() {//折叠所有树
		$('#tt').tree('collapseAll');

	}
	function remove2() {	//删除树
		var node = $('#tt').tree('getSelected');//得到选中节点
		if(node!=null){
			var uuid = node.id;//得到节点id	
			var arguments = node.text;//得到节点内容
		    $.messager.confirm('<spring:message code="system.alert"/>','<spring:message code="functionmaintain.delete.confirm" arguments="'+arguments+'"/>',//确认删除
		    		function(result){ 
				  if (result){ //确认  			      
				      $.ajax({
		                    url : "functionMaintain/delete.do?uuid="+uuid,//删除请求路径
		                    dataType : "json",
		                    success : function(result) {//请求结果
		                        if (result.success) {	                        	
		                        	$.messager.alert('<spring:message code="system.alert"/>',result.msg);//成功信息
		                        	InitTreeData();//刷新树
		                        	clearForm();//清除from表单信息
		                        } else {
		                            $.messager.alert('<spring:message code="system.alert"/>',result.msg);//删除失败提示信息
		                        }
		                        
		                    }
		                });
				     
				  }
			  });  		
		}else{
			$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="noselectrecord"/>');//取消删除提示信息
		}
			      
	    
	}
function addTo(){
	var id = $("#setid").val();
	var parentId = $("#parentId").val();
	$("#ff").form('clear');
	$("#setid").val(id);
	$("#parentstra").val("add");
	 $("#parentId").val(parentId);
}	
	

/**
 * 保存
 */
	function submitForm() {//提交from表单
		$('#ff').form('submit', {
			url:'functionMaintain/save.do',	
			onSubmit : function() {//enableValidation启用表单有效性验证。此方法从版本1.3.4开始引入。
				return $(this).form('enableValidation').form('validate');//validate执行表单字段有效性验证，当所有表单字段都有效时返回true。此方法结合validatebox插件一起使用。
			},
			success:function(result){//请求成功
				var result = $.parseJSON(result);//将返回值转化成json格式		
				if(result.success){//判断请求处理成功与否
					$.messager.alert('<spring:message code="system.alert"/>',result.msg);//成功提示信息
					InitTreeData();//刷新树					
	            	clearForm(); //清除from表单
	            	$("#parentstra").val("a");
				}else{
					$.messager.alert('<spring:message code="system.alert"/>',result.errorMsg);//失败提示信息					
				}
			} 
		});
	}
/**
 * 清除from表单
 */
 function clearForm(){
	$("#ff").form('clear');
	
}
 


	
(function($) {	
		
		$.extend($.fn.tree.methods, {
			/**
			 * 扩展easyui tree的搜索方法
			 * @param tree easyui tree的根DOM节点(UL节点)的jQuery对象
			 * @param searchText 检索的文本
			 * @param this-context easyui tree的tree对象
			 */
			search: function(jqTree, searchText) {
				//easyui tree的tree对象。可以通过tree.methodName(jqTree)方式调用easyui tree的方法
				var tree = this;
				
				//获取所有的树节点
				var nodeList = getAllNodes(jqTree, tree);
				
		  		//如果没有搜索条件，则展示所有树节点
				searchText = $.trim(searchText);
		  		if (searchText == "") {
		  			for (var i=0; i<nodeList.length; i++) {
		  				$(".tree-node-targeted", nodeList[i].target).removeClass("tree-node-targeted");
		  	  			$(nodeList[i].target).show();
		  	  		}
		  			//展开已选择的节点（如果之前选择了）
		  			var selectedNode = tree.getSelected(jqTree);
		  			if (selectedNode) {
		  				tree.expandTo(jqTree, selectedNode.target);
		  			}
		  			return;
		  		}
		  		
		  		//搜索匹配的节点并高亮显示
		  		var matchedNodeList = [];
		  		if (nodeList && nodeList.length>0) {
		  			var node = null;
		  			for (var i=0; i<nodeList.length; i++) {
		  				node = nodeList[i];
		  				if (isMatch(searchText, node.text)) {
		  					matchedNodeList.push(node);
		  				}
		  			}
		  			
		  			//隐藏所有节点
		  	  		for (var i=0; i<nodeList.length; i++) {
		  	  			$(".tree-node-targeted", nodeList[i].target).removeClass("tree-node-targeted");
		  	  			$(nodeList[i].target).hide();
		  	  		}  			
		  			
		  			//折叠所有节点
		  	  		tree.collapseAll(jqTree);
		  			
		  			//展示所有匹配的节点以及父节点  			
		  			for (var i=0; i<matchedNodeList.length; i++) {
		  				showMatchedNode(jqTree, tree, matchedNodeList[i]);
		  			}
		  		} 	 
			},
			
			/**
			 * 展示节点的子节点（子节点有可能在搜索的过程中被隐藏了）
			 * @param node easyui tree节点
			 */
			showChildren: function(jqTree, node) {
				//easyui tree的tree对象。可以通过tree.methodName(jqTree)方式调用easyui tree的方法
				var tree = this;
				
				//展示子节点
				if (!tree.isLeaf(jqTree, node.target)) {
					var children = tree.getChildren(jqTree, node.target);
					if (children && children.length>0) {
						for (var i=0; i<children.length; i++) {
							if ($(children[i].target).is(":hidden")) {
								$(children[i].target).show();
							}
						}
					}
				}  	
			},
			
			/**
			 * 将滚动条滚动到指定的节点位置，使该节点可见（如果有滚动条才滚动，没有滚动条就不滚动）
			 * @param param {
			 * 	  treeContainer: easyui tree的容器（即存在滚动条的树容器）。如果为null，则取easyui tree的根UL节点的父节点。
			 *    targetNode:  将要滚动到的easyui tree节点。如果targetNode为空，则默认滚动到当前已选中的节点，如果没有选中的节点，则不滚动
			 * } 
			 */
			scrollTo: function(jqTree, param) {
				//easyui tree的tree对象。可以通过tree.methodName(jqTree)方式调用easyui tree的方法
				var tree = this;
				
				//如果node为空，则获取当前选中的node
				var targetNode = param && param.targetNode ? param.targetNode : tree.getSelected(jqTree);
				
				if (targetNode != null) {
					//判断节点是否在可视区域				
					var root = tree.getRoot(jqTree);
					var $targetNode = $(targetNode.target);
					var container = param && param.treeContainer ? param.treeContainer : jqTree.parent();
					var containerH = container.height();
					var nodeOffsetHeight = $targetNode.offset().top - container.offset().top;
					if (nodeOffsetHeight > (containerH - 30)) {
						var scrollHeight = container.scrollTop() + nodeOffsetHeight - containerH + 30;
						container.scrollTop(scrollHeight);
					}							
				}
			}
		});
		
		
		
		
		/**
		 * 展示搜索匹配的节点
		 */
		function showMatchedNode(jqTree, tree, node) {
	  		//展示所有父节点
	  		$(node.target).show();
	  		$(".tree-title", node.target).addClass("tree-node-targeted");
	  		var pNode = node;
	  		while ((pNode = tree.getParent(jqTree, pNode.target))) {
	  			$(pNode.target).show();  			
	  		}
	  		//展开到该节点
	  		tree.expandTo(jqTree, node.target);
	  		//如果是非叶子节点，需折叠该节点的所有子节点
	  		if (!tree.isLeaf(jqTree, node.target)) {
	  			tree.collapse(jqTree, node.target);
	  		}
	  	}  	 
		
		/**
		 * 判断searchText是否与targetText匹配
		 * @param searchText 检索的文本
		 * @param targetText 目标文本
		 * @return true-检索的文本与目标文本匹配；否则为false.
		 */
		function isMatch(searchText, targetText) {
	  		return $.trim(targetText)!="" && targetText.indexOf(searchText)!=-1;
	  	}
		
		/**
		 * 获取easyui tree的所有node节点
		 */
		function getAllNodes(jqTree, tree) {
			var allNodeList = jqTree.data("allNodeList");
			if (!allNodeList) {
				var roots = tree.getRoots(jqTree);
	  			allNodeList = getChildNodeList(jqTree, tree, roots);
	  			jqTree.data("allNodeList", allNodeList);
			}
	  		return allNodeList;
	  	}
	  	
		/**
		 * 定义获取easyui tree的子节点的递归算法
		 */
	  	function getChildNodeList(jqTree, tree, nodes) {
	  		var childNodeList = [];
	  		if (nodes && nodes.length>0) {  			
	  			var node = null;
	  			for (var i=0; i<nodes.length; i++) {
	  				node = nodes[i];
	  				childNodeList.push(node);
	  				if (!tree.isLeaf(jqTree, node.target)) {
	  					var children = tree.getChildren(jqTree, node.target);
	  					childNodeList = childNodeList.concat(getChildNodeList(jqTree, tree, children));
	  				}
	  			}
	  		}
	  		return childNodeList;
	  	}
})(jQuery);
	
	
</script>

</html>