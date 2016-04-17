<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/tags/input" prefix="input"%>
<meta http-equiv="content-language" content="zh-CN" />
<c:set var="vat" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" type="text/css" href="${vat}/jquery-easyui-1.4.4/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${vat}/jquery-easyui-1.4.4/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${vat}/jquery-easyui-1.4.4/demo.css">
<script type="text/javascript" src="${vat}/jquery-easyui-1.4.4/jquery.min.js"></script>
<script type="text/javascript" src="${vat}/jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${vat}/jquery-easyui-1.4.4/jquery-1.8.0.js"></script> 
<script type="text/javascript" src="${vat}/common/commonjs/common.js"></script>
<script type="text/javascript" src="${vat}/common/commonjs/ui.js"></script>
<script type="text/javascript" src="${vat}/common/commonjs/json2.js"></script>
<script type="text/javascript" src="${vat}/jquery-easyui-1.4.4/locale/easyui-lang-zh_CN.js"></script> 


<script type="text/javascript">
 
function build_common_combo(id,id_field_name,field1,title1,field2,title2,url){
	
	var id_temp='';
	var id_temp_input_id='';
	var id_temp_input_id_tag='';
	if(id!=''&&typeof(id)!=='undefined'&&id.substr(0,1)=='#'){
		id_temp_input_id=id.substr(1)+"_input_value";
		id_temp_input_id_tag='<input type="easyui-textbox"  id="'+id_temp_input_id+'"/>'
	}
	$(id).combogrid({
			panelWidth : 330,
			panelHeight: 300,
			pagination:true,//分页
			url : "",				
			idField : id_field_name, //ID字段  
			textField : field1, //显示的字段
			fitColumns : true,
			striped : true,				
			rownumbers : true, //序号
			collapsible : false, //是否可折叠的
			fit : false, //自动大小
			onSelect: function(rec){
			},
			toolbar : [ {
				text : id_temp_input_id_tag
			}, {
				text : "查询",
				iconCls : 'icon-search',
				handler : function() {
					var searchcondition = $('#'+id_temp_input_id).val();
					var searchcondition_temp='&'+field1+'='+searchcondition;
					comoboxGridSearch(id,url,searchcondition_temp);
				}
			}, '-', {
				text : "清空",
				iconCls : '',
				handler : function() {
					$('#'+id_temp_input_id).val("");
					var searchcondition = "";
					 var searchcondition_temp='&'+field1+'='+searchcondition;
					comoboxGridSearch(id,url,searchcondition_temp);
				}
			}, '-' ],
			columns : [ [ {
				field:id_field_name,
				hidden:'true'
				},{
				field : field1,
				title : title1,
				width : 80
			}, {
				field : field2,
				title : title2,
				width : 120
			} ] ]
		});
	
		var page = $(id).combogrid('options').pageNumber;
		var pageSizes = $(id).combogrid('options').pageSize;
		var searchcondition = $('#'+id_temp_input_id).val();
		var searchcondition_temp='&'+field1+'='+searchcondition;
		 var data = "pageNumber="+page +"&pageSize="+pageSizes+searchcondition_temp;  
		//comoboxGridSearch(id,page,pageSizes,searchcondition,url);
		 $.ajax({
			url : "${vat}/"+url,//     tmsMdTaxTrxType/loadTaxTransactionType_id.do
			type : "POST",
			data : data,
			dataType : "json", 
			cache : false,
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				 /* alert(XMLHttpRequest.status);
				 alert(XMLHttpRequest.readyState);
				 alert(textStatus); */
				   },
			success : function(result) {
				$(id).combogrid('grid').datagrid(
						'loadData', result);
			}
		}); 
		
		
		var pager = $(id).combogrid('grid').datagrid('getPager');

		if (pager) {
			$(pager)
					.pagination(
							{
								pageSize: 10,//每页显示的记录条数，默认为10           
								//pageList: [5,10,15,20],//可以设置每页记录条数的列表           
								 beforePageText: '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
								 afterPageText: '<spring:message code="pagination.afterPageText"/>',           
								displayMsg: '<spring:message code="pagination.displayMsg"/>', 
								//选择页的处理
								onSelectPage : function(pageNumber, pageSize) {
									findComoboxGrid(id,pageNumber,pageSize,searchcondition_temp,url);
								}
								
							});
		}
}


 function init_common_combo_taxType(obj_id){
  	build_common_combo(obj_id,'id','taxTrxTypeCode','涉税交易类型编码','taxTrxTypeName','涉税交易类型名称','tmsMdTaxTrxType/loadTaxTransactionType_id.do');
	  $(obj_id).combogrid('grid').datagrid('options').onClickRow = 
		function(index, row) {
          search = false;
          $(obj_id).combogrid('hidePanel');
          $(obj_id).combogrid('setValue', row.id);
        
	}
}
function init_common_combo_customer(obj_id){
	build_common_combo(obj_id,'id','customerNumber','购方编号','customerName','购方名称','customer/getcustomergrid.do');
	  $(obj_id).combogrid('grid').datagrid('options').onClickRow = 
		function(index, row) {
          search = false;
          $(obj_id).combogrid('hidePanel');
          $(obj_id).combogrid('setValue', row.id);
      
	}
}
function init_common_combo_salesentity(obj_id){
	build_common_combo(obj_id,'id','legalEntityCode','销方编号','legalEntityName','销方名称','taxOrgMgt/searchcomobogridTaxOrg.do');
	  $(obj_id).combogrid('grid').datagrid('options').onClickRow = 
		function(index, row) {
          search = false;
          $(obj_id).combogrid('hidePanel');
          $(obj_id).combogrid('setValue', row.id);
          
	}
}

function comoboxGridSearch(id,url,searchcondition){
	var pageNumber = $(id).combogrid('options').pageNumber;
	var pageSize = $(id).combogrid('options').pageSize;
	/* alert(url); */
	//var searchcondition = $('#taxTransactionTypeInput_id').val();
	var data = "pageNumber="+pageNumber +"&pageSize="+pageSize +searchcondition; 
	
	/* alert(data); */
	$.ajax({
		url : "${vat}/"+url,//     tmsMdTaxTrxType/loadTaxTransactionType_id.do
		type : "POST",
		data : data,
		dataType : "json", 
		cache : false,
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			 /* alert(XMLHttpRequest.status);
			 alert(XMLHttpRequest.readyState);
			 alert(textStatus); */
			   },
		success : function(result) {
			$(id).combogrid('grid').datagrid(
					'loadData', result);
		}
	});
}

function findComoboxGrid(id,pageNumber,pageSize,searchconditon,url){
	var data = "pageNumber="+pageNumber +"&pageSize="+pageSize + searchconditon; 
	$.ajax({
		url : "${vat}/"+url,//     tmsMdTaxTrxType/loadTaxTransactionType_id.do
		type : "POST",
		data : data,
		dataType : "json", 
		cache : false,
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			 /* alert(XMLHttpRequest.status);
			 alert(XMLHttpRequest.readyState);
			 alert(textStatus); */
			   },
		success : function(result) {
			$(id).combogrid('grid').datagrid(
					'loadData', result);
		}
	});
}  
</script>