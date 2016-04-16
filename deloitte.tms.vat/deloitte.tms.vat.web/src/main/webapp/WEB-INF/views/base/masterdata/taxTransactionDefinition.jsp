<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/common/global.jsp"%>
<%@ taglib uri="/tags/input" prefix="input"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
 <body class="easyui-layout" style="overflow-y: hidden"  scroll="no" id="layoutid">    
    <div region="north" split="true" border="false" style="overflow: hidden; height:12%;" data-options="region:'west'">  
        <div class="easyui-panel" title="查询条件" style="width:100%;height:100%;margin-top:0px;" data-options="collapsible: true">		
		    <form id="taxTransactionDefinition_searchform" method="get" scroll="no">
		    	<table id="dga" style="width: 100%">
		    		<tr >
		    			<td>涉税交易类型:<select id="taxTransactionType_id"
								name="taxTransactionType" class="easyui-combogrid"
								style="width: 300px;" data-options="editable:false"></select>
		    		  <a href="#" id="searchbtn"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px" onclick="Search()">
		    		  查询
		    		  </a>
		    		  <a href="#" class="easyui-linkbutton" onclick="$('#taxTransactionDefinition_searchform').form('reset')" style="width:80px" >
		    		  <spring:message code="button.Clear"/>
		    		  </a>			                     
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
	
	         <table  id="taxTransactionDefinition_dataGrid"  style="width:100%;height:100%;text-align:center;" >	
	
		   </table>  
	   </div>	 
		</div>
<div id="addTaxTransactionDefinition" class="easyui-dialog" title="增加定义" style="width:500px;height:300px;"  
       data-options="	closed:true,			
				buttons: [{
					text:'<spring:message code="button.Save"/>',
					iconCls:'icon-ok',
					handler:function(){
						addTaxTransactionDefinition();
					}
				},{
					text:'<spring:message code="button.Close"/>',
					iconCls:'icon-cancel',
					handler:function(){
						$('#addTaxTransactionDefinition').dialog('close');
					}
				}]
			">   
      <form  method="post" id="addTaxTransactionDefinitionForm">
           <table style="text-align:center;line-height:30px;" align="center">
            <tr>
               <td colspan="2"><input  type="hidden" id="id_id" name="id" ></td>
            </tr>
            <tr>
               <td>涉税交易类型编码：</td>
               <td><input class="easyui-validatebox" type="text" id="taxTrxTypeCode_id" name="taxTrxTypeCode" data-options="required:true" style="width:200px;"></td>
            </tr>
            <tr>
               <td>涉税交易类型：</td>
               <td><input class="easyui-validatebox" type="text" id="taxTrxTypeName_id" name="taxTrxTypeName" data-options="required:true" style="width:200px;"></td>
            </tr>
            <tr>
               <td>生效日期：</td>
               <td><input class="easyui-datebox" type="text" id="startDate_id" name="startDate" data-options="required:true,editable:false" style="width:200px;"></td>
            </tr>
            <tr>
               <td>失效日期：</td>
               <td><input class="easyui-datebox" type="text" id="endDate_id" name="endDate" data-options="required:true,editable:false" style="width:200px;"></td>
            </tr>
            <tr>
               <td>涉是否启用：</td>
               <td>
               <select id="cc" class="easyui-combobox"  id="enabledFlag_id" name="enabledFlag" style="width:200px;" data-options="editable:false">   
                 <option value="1">是</option>   
                 <option value="0">否</option>    
             </select>

            </tr>
           </table>
      </form>
</div>  



</body>
<script type="text/javascript">
function addTaxTransactionDefinition(){
	$('#addTaxTransactionDefinitionForm').form('submit',{    
	    url:"${vat}/tmsMdTaxTrxType/saveTmsMdTaxTrxType.do",     
	    success:function(data){    
	    	var result = $.parseJSON(data);
	    	//alert(result.success);
	        if(result.success){
	        	
	        	$.messager.alert('<spring:message code="system.alert"/>','保存成功');
	        	$('#addTaxTransactionDefinition').dialog('close');
	        	$('#taxTransactionDefinition_dataGrid').datagrid('loadData', result.rows);
	        }
	        
	    }    
	},'json');    

	
}
var createGridHeaderContextMenu = function(e, field) {  
	    e.preventDefault();  
	    var grid = $(this);/* grid本身 */  
	    var headerContextMenu = this.headerContextMenu;/* grid上的列头菜单对象 */  
	    var okCls = 'tree-checkbox1';//选中  
	    var emptyCls = 'tree-checkbox0';//取消  
	    if (!headerContextMenu) {  
	        var tmenu = $('<div style="width:100px;"></div>').appendTo('body');  
	        var fields = grid.datagrid('getColumnFields');  
	        for ( var i = 0; i < fields.length; i++) {  
	            var fildOption = grid.datagrid('getColumnOption', fields[i]);  
	            if (!fildOption.hidden) {  
	               $('<div iconCls="'+okCls+'" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);  
	            } else {  
	                $('<div iconCls="'+emptyCls+'" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);  
	            }  
	       }  
	        headerContextMenu = this.headerContextMenu = tmenu.menu({  
	            onClick : function(item) {  
	                var field = $(item.target).attr('field');  
	                if (item.iconCls == okCls) {  
	                    grid.datagrid('hideColumn', field);  
	                    $(this).menu('setIcon', {  
	                        target : item.target,  
	                        iconCls : emptyCls  
	                    });  
	                } else {  
	                    grid.datagrid('showColumn', field);  
	                    $(this).menu('setIcon', {  
	                        target : item.target,  
	                        iconCls : okCls  
	                    });  
	               }  
	            }  
	        });  
	    }  
	  headerContextMenu.menu('show', {  
	        left : e.pageX,  
	        top : e.pageY  
	    }); 
	};  
	$.fn.datagrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;  
	$.fn.treegrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;  

//初始化涉水交易类型信息显示表格
$("#taxTransactionDefinition_dataGrid").datagrid({
	loadMsg : "<spring:message code='client.datagrid.dataloding'/>",
	striped : true,//显示斑马线效果
	singleSelect : false, //多选
	collapsible : false,//可折叠  
	fitColumns : false,//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
	nowrap : false,//换行
	iconCls : 'icon icon-list',//图标
	idField : 'id',//主键字段
    rownumbers:true, //显示行数
    pagination:true,//分页
    url:'',  //数据请求地址
    border: true,
    remoteSort:false,//定义从服务器对数据进行排序。
    onDblClickRow : function (rowIndex, rowData) {
			$('#taxTransactionDefinition_dataGrid').datagrid('endEdit', obj.editRow);
	},
    columns:[[  
            {field:'id',title:'序号',checkbox:'true',align : 'center'},
            {field:'taxTrxTypeCode',title:'涉税交易类型编码',width:110,align : 'center',sortable:true,editor : {
				type : 'validatebox',
				options : {
					required : true,
				},
			},},
            {field:'taxTrxTypeName',title:'涉税交易类型',width:260,align : 'left',editor:'text',sortable:true},
            {field:'startDate',title:'生效日期',width:100,align : 'center',editor:'datebox',sortable:true} ,
            {field:'endDate',title:'失效日期',width:100,align : 'center',editor:'datebox',sortable:true} , 
            {field:'enabledFlag',title:'是否启用',width:100,align : 'left',editor:'text',sortable:true,formatter:function(value,row,index){
            	if(value){
            		return "是";
            	}else{
            		return "否";
            	}
            }} ,
          ]],
         	toolbar : [ {
				text : "新增",
				iconCls : 'icon-add',
				handler : function() {
					clearSaveForm()
					$("#addTaxTransactionDefinition").dialog("open");
				}
			}, '-', {
				text : "删除",
				iconCls : 'icon-remove',
				handler : function() {
			    var rows = $('#taxTransactionDefinition_dataGrid').datagrid('getSelections');
				var data = rows[0];
				var count = rows.length;
				if(count==0){
					alert("请至少选择一条数据！！");
					return;
				}
				$.messager.confirm('提示:','你确认要删除吗?',function(event){ 
					if(event){ 
						var ids = "";
						for(var i = 0;i < count;i++){
							ids=ids+","+rows[i].id;
						}
						var re = "tmsMdTaxTrxTypeKeys="+ids+"&pageNumber="+$('#taxTransactionDefinition_dataGrid').datagrid('options').pageNumber+"&pageSize="+$('#taxTransactionDefinition_dataGrid').datagrid('options').pageSize;
						$.ajax({
							url : "${vat}/tmsMdTaxTrxType/removeTmsMdTaxTrxTypes.do",
							type : "POST",
							async : true,
							data :re, 
							dataType : "json",
							cache : false,
							error: function(XMLHttpRequest, textStatus, errorThrown) {
								 alert(XMLHttpRequest.status);
								 alert(XMLHttpRequest.readyState);
								 alert(textStatus);
								   },
							success : function(result) {
								$('#taxTransactionDefinition_dataGrid').datagrid('clearSelections');
								$('#taxTransactionDefinition_dataGrid').datagrid('loadData', result);
								
							}
						});
						
					}else{ 
					     alert("你点击的是false"); 
					} 
					}); 

				
				
				
				}
			}, '-', {
				text : "编辑",
				iconCls : 'icon-edit',
				handler : function() {
						var row = $('#taxTransactionDefinition_dataGrid').datagrid('getSelections');
						var data = row[0];
						var count = row.length;
						if(count>1){
							alert("只能选择一条数据！");
							return;
						}
						if(count==0){
							alert("请至少选择一条数据！！");
							return;
						}
						$("#id_id").val(data.id);
						$("#taxTrxTypeCode_id").val(data.taxTrxTypeCode);
						$("#taxTrxTypeName_id").val(data.taxTrxTypeName);
						$("#enabledFlag_id").combobox('setValue',data.enabledFlag);
						$("#startDate_id").datebox('setValue',data.startDate);
						$("#endDate_id").datebox('setValue',data.endDate);
						$("#addTaxTransactionDefinition").dialog("open");
						
				}
			}, '-'],
	
	
	});
//得到分页控件
var p = $('#taxTransactionDefinition_dataGrid').datagrid('getPager');
//设置分页控件	
$(p).pagination({
	pageSize: 10,//每页显示的记录条数，默认为10           
	beforePageText: '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
	afterPageText: '<spring:message code="pagination.afterPageText"/>',           
	displayMsg: '<spring:message code="pagination.displayMsg"/>',
	onSelectPage: function (pageNumber, pageSize) {//用户选择一个新页面的时候触发	
    	 find(pageNumber,pageSize);//改变后的页数传给后台
		 Search(); 
     }
});
//对分页数数据条数改编后进行操作
function find(pageNumber, pageSize) {
	$('#taxTransactionDefinition_searchform').form('load', {//读取记录填充到表单中。数据参数可以是一个字符串或一个对象类型，如果是字符串则作为远程URL，否则作为本地记录。
		pageNumber : pageNumber,
		pageSize : pageSize
	});
}
$(document).ready(
		function() {
			$('#taxTransactionDefinition_searchform').form(//读取记录填充到表单中。数据参数可以是一个字符串或一个对象类型，如果是字符串则作为远程URL，否则作为本地记录。
					'load',
					{
						pageNumber : $('#taxTransactionDefinition_dataGrid')
								.datagrid('options').pageNumber,
						pageSize : $('#taxTransactionDefinition_dataGrid')
								.datagrid('options').pageSize
					});
			Search();
		});
/**
 * 请求查询涉税交易类型定义
 */
function Search() {
	$("#taxTransactionDefinition_dataGrid").datagrid("loading");//显示载入状态。
	$('#taxTransactionDefinition_searchform').form('submit', {//提交form表单
		url : '${vat}/tmsMdTaxTrxType/loadTmsMdTaxTrxTypePage.do',
		success : function(result) {//成功之后
			//数据加载以及绑定
			var result = $.parseJSON(result);//将数据格式化成json
			$("#taxTransactionDefinition_dataGrid").datagrid('loadData', result);//加载本地数据，旧的行将被移除。
			$("#taxTransactionDefinition_dataGrid").datagrid("loaded");//隐藏载入状态。
		}
	});
}
	/**
	 * 涉税交易类型下拉列表数据生成
	 */
		function taxTransactionType_id(data){
	$("#taxTransactionType_id").combogrid({
			panelWidth : 600,
			panelHeight : 400,
			pagination:true,//分页
			url : "${vat}/tmsMdTaxTrxType/loadTaxTransactionType_id.do",
			onSelect: function(rec){
			},
			queryParams : {
				customerNameInSendFormid : data,
			},
			toolbar : [ {
				text : '<input type="text" id="taxTransactionTypeInput_id"/>'
			}, {
				text : "查询",
				iconCls : 'icon-search',
				handler : function() {
					var taxTransactionTypeInput_id = $(
					"#taxTransactionTypeInput_id")
					.val();//得到输入框的值
					taxTransactionType_id(taxTransactionTypeInput_id);
			$("#taxTransactionType_id")
					.combogrid('hidePanel');
			
			$("#taxTransactionType_id")
					.combogrid('showPanel');
			$("#taxTransactionType_id").combogrid('grid').datagrid(
			'options').onClickRow = function(index, row) {
		search = false;
		$("#taxTransactionType_id").combogrid('hidePanel');
		$("#taxTransactionType_id").combogrid('setValue',
				row.id);
		$("#taxTransactionType_id").combogrid('setText',
				row.taxTrxTypeName);
	}
					
				}
			}, '-' ],
			columns : [ [ {
				field : 'id',
				title : "涉税交易类型id",
				width : 100,
				hidden:true
			},{
				field : 'taxTrxTypeCode',
				title : "涉税交易类型编码",
				width : 300
			}, {
				field : 'taxTrxTypeName',
				title : "涉税交易类型名称",
				width : 300
			} ] ]
		});
		}
/**
 * 初始化下拉列表数据
 */
$(document).ready(
		function() {
		taxTransactionType_id('');
		$("#taxTransactionType_id").combogrid('grid').datagrid('options').onClickRow = 
			function(index, row) {
	          search = false;
	          $("#taxTransactionType_id").combogrid('hidePanel');
	          $("#taxTransactionType_id").combogrid('setValue', row.taxTrxTypeCode);
	          $("#taxTransactionType_id").combogrid('setText', row.taxTrxTypeName);
		}
		
	});
	
function clearSaveForm() {
	$('#addTaxTransactionDefinitionForm').form('clear');
}

</script>


</html>