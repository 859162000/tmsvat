<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/tags/input" prefix="input"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/common/global.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">    
      <div region="north" split="true" border="false" style="overflow: hidden; height:23%;">  
        <div class="easyui-panel" title="<spring:message code="searchgroup"/>" style="width:100%;height:100%; background-color: #E0ECFF">		
			 <form id="invoiceTrxPool_searchform" method="post" scroll="no">
				<table  style="text-align: center;" align="left" style="width: 1500px; height: 100%">
					<tr>					
					    <td><spring:message code="invoiceprint.validType"/>：</td>																											
						<td>
						  <div>
					    	<input:select id="invoiceTrxPool_searchform_customerInfoList_select"
												name="selectOption" value="$invoice_print_newSearch_validType"
												easyuiClass="easyui-combobox" easyuiStyle="width:120px;">
												<option value=""></option>
												<input:systemStatusOption parentCode="VAT_CUSTOMER_DISC_OPTION"/>
							</input:select> 	    			
					    	<input id="invoiceTrxPool_searchform_customerInfoList_input" class="easyui-textbox" style="width:120px" name="selectValue"/></input>
				    	 </div>
				    	</td>  				    				
						<td>购方客户：</td>
						<td><input id="invoiceTrxPool_searchform_customerName_input"
							class="easyui-textbox" style="width: 120px" name="customerId" />
						</td>
						<td >销方交易机构：</td>
						<td><input id="invoiceTrxPool_searchform_salesEntityName"
							class="easyui-textbox" style="width: 120px" name="legalEntityId" />
						</td>	
						
						
						<td><spring:message code="invoiceprint.taxType" /></td>
						<td class="3"><input id="invoiceTrxPool_searchform_taxType"
							class="easyui-textbox" style="width: 120px" name="taxType" />
							<input id="taxTrxTypeId" name="taxTrxTypeId" type="hidden"/></td>						
					</tr>
					<tr>					
					    <td>交易开始日期：</td>
				    	<td>
				    	  <div>
					    		<input id="invoiceTrxPool_searchform_glStartDate"
								class="easyui-datebox" style="width: 110px" name="trsStartDate"
								data-options="formatter:myformatter,parser:myparser,required:false,onSelect:onSelectStartDate" />					    			
					    	   <spring:message code="invoiceprint.search.to"/>：
					    		<input 
								class="easyui-datebox" style="width: 110px" name="trsEndDate"
								data-options="formatter:myformatter,parser:myparser,required:false,onSelect:onSelectEndDate" />
				    	  </div>
				    	</td>
				    	
				    	<td >交易批次号：</td>
						<td><input id="invoiceTrxPool_searchform_batchNum"
							class="easyui-textbox" style="width: 120px" name="batchNum" />
						</td>									
						<td>交易流水号：</td>
						<td><input id="invoiceTrxPool_searchform_sequence"
							class="easyui-textbox" style="width: 120px" name="trxNumber" />
						</td>
						<td>系统来源：</td>
						<td ><input id="invoiceTrxPool_searchform_source"
							class="easyui-textbox" style="width: 120px" name="source" />
						</td>	
				    						
					</tr>
					<tr>
						<td>
								<a href="#" id="searchbtn" class="easyui-linkbutton"
									data-options="iconCls:'icon-search'" style="width: 80px"
									onclick="callRefresh()"><spring:message code='button.Search' /></a>
						</td>
						<td align="left">
								<a href="#" id="resetbtn" class="easyui-linkbutton"
									data-options="iconCls:'icon-reload'" style="width: 80px"
									onclick="Reset()"><spring:message code='button.Clear' /></a>
						
						</td>
					</tr>
	
					<tr style="display: none">
						<td><input id="pageNumber" class="easyui-textbox" type="text"
							style="width: 0px;" name="pageNumber" value=""></input></td>
						<td><input id="pageSize" class="easyui-textbox" type="text"
							style="width: 0px;" name="pageSize" value=""></input></td>
					</tr>
				</table>
			</form>
	    </div>
	        	         
    </div>
    <div data-options="region:'center',border:false" style="background-color: #E0ECFF">  
	   	<div style="width: 100%; height: 100%">
			<table class="easyui-datagrid" id="invoiceTrxPool_dataGrid"
				title="<spring:message code='invoiceTrxPool.title'/>"
				style="width: 100%; height: 100%"
				data-options="					
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
</body>
<script type="text/javascript">
	$(document).ready(function(){
		var zjlx = $("#invoiceTrxPool_searchform_customerInfoList_select").val();
    	if(zjlx="请输入查询条件")
		{
    		$("#invoiceTrxPool_searchform_customerInfoList_input").textbox({disabled: true});
		}
    	
    	$('#invoiceTrxPool_searchform_customerInfoList_select').combobox({
		 onSelect:function(record){
            var eType = $('#invoiceTrxPool_searchform_customerInfoList_select').combobox('getValue');
            //alert("********"+eType);
            //if(eType="1"||eType="2"||eType="3"){
            	$("#invoiceTrxPool_searchform_customerInfoList_input").textbox({disabled: false});
            //}else{
            	//$("#invoiceTrxPool_searchform_customerInfoList_input").textbox({disabled: true});
            //}
         }
	    }); 
    	
    	pageDataSettingInit();
		InitCombobox();
		init_common_combo_taxType("#invoiceTrxPool_searchform_taxType");		
		init_common_combo_customer("#invoiceTrxPool_searchform_customerName_input");
		init_common_combo_salesentity("#invoiceTrxPool_searchform_salesEntityName");
		
		$('#invoiceTrxPool_searchform').form('load', {
			pageNumber: $('#invoiceTrxPool_dataGrid').datagrid('options').pageNumber,
			pageSize: $('#invoiceTrxPool_dataGrid').datagrid('options').pageSize
		});
		Search();
	});
	

	function onSelectStartDate(date){
		var date = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()
		$('#invoiceTrxPool_searchform_glEndDate').datebox('setValue',date)
	};
	
	function onSelect() {
		//alert($('#invoiceTrxPool_searchform_customerInfoList_select').combobox('getValue'));
	}
	
	function onSelectEndDate(){
		var endDate = $('#invoiceTrxPool_searchform_glEndDate').datebox('getValue');
		var startDate = $('#invoiceTrxPool_searchform_glStartDate').datebox('getValue');
		if(endDate<startDate) {
			alert("<spring:message code='invoiceTrxPool.error.dateerror'/>")
			$('#invoiceTrxPool_searchform_glEndDate').datebox('clear');
			$('#invoiceTrxPool_searchform_glEndDate').target.focus();	
		}
	};
	
	 // 查询方法
	function Search(){
    	$("#invoiceTrxPool_dataGrid").datagrid("loading");
    	$('#invoiceTrxPool_searchform').form('submit', {
			url:'invoiceTrxPool/getInvoiceTrxPool.do',			
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success:function(result) {
			   //数据加载以及绑定	
			   var result = $.parseJSON(result);
		       $("#invoiceTrxPool_dataGrid").datagrid('loadData', result);	
		       //$("#invoiceTrxPool_dataGrid").datagrid('loadData', result.data);		       
		       $("#invoiceTrxPool_dataGrid").datagrid("loaded"); 
		     }
		});
    };
    
    
    function callRefresh(){
    	var zjlx = $('#invoiceTrxPool_searchform_customerInfoList_select').combobox('getValue');
    	if(zjlx!="请输入查询条件")
		{
    		var zjlx2 = $("#invoiceTrxPool_searchform_customerInfoList_input").val();
    		if(zjlx2 == null||zjlx2 == ""){
    			alert("请输入证件号码！");
    			return false;
    		}
    	}
    	
    	$('#invoiceTrxPool_searchform').form('load', {
			pageNumber: $('#invoiceTrxPool_dataGrid').datagrid('options').pageNumber,
			pageSize: $('#invoiceTrxPool_dataGrid').datagrid('options').pageSize
		});
 		Search2();
    };
    
    function Search2(){
    	$("#invoiceTrxPool_dataGrid").datagrid("loading");
    	$('#invoiceTrxPool_searchform').form('submit', {
			url:'invoiceTrxPool/searchInvoiceTrxPool.do',			
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(result) {
			   var result = $.parseJSON(result);
		       $("#invoiceTrxPool_dataGrid").datagrid('loadData', result);		       
		       $("#invoiceTrxPool_dataGrid").datagrid("loaded"); 
			//}
		    }
		});

    };
    
	
	function Reset() {
		var pageNumber = $('#pageNumber').val();
		var pageSize = $('#pageSize').val()		
		$('#invoiceTrxPool_searchform').form('clear');
		 InitCombobox();
		$('#invoiceTrxPool_searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
	};

	 function myformatter(date){
         var y = date.getFullYear();
         var m = date.getMonth()+1;
         var d = date.getDate();
         return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
     }

	 function myparser(s){
         if (!s) return new Date();
         var ss = (s.split('-'));
         var y = parseInt(ss[0],10);
         var m = parseInt(ss[1],10);
         var d = parseInt(ss[2],10);
         if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
             return new Date(y,m-1,d);
         } else {
             return new Date();
         }
     }

	function InitCombobox(){
		$("#invoiceTrxPool_searchform_customerInfoList_select").combogrid({
			   panelWidth:150,
			   value:'<spring:message code="invoiceTrxPool.plsInputCriteria"/>',//缺省值
			   url:"", 
			  });
		var zjlx = $("#invoiceTrxPool_searchform_customerInfoList_select").val();
    	if(zjlx="请输入查询条件")
		{
    		$("#invoiceTrxPool_searchform_customerInfoList_input").textbox({disabled: true});
		};
		$("#invoiceTrxPool_searchform_salesEntityList_select").combogrid({
			   panelWidth:150,
			   value:'<spring:message code="invoiceTrxPool.plsInputCriteria"/>',//缺省值
			   url:"", 
			  });
		
		/* $("#invoiceTrxPool_searchform_customerInfoList_select").combogrid({
			 value:'<spring:message code="invoiceTrxPool.plsInputCriteria"/>',
			 onselect:function(record){
				 
			 }
		}); */
		
	}
	
	//页面表格绑定初始化
	function pageDataSettingInit(){
		$('#invoiceTrxPool_dataGrid').datagrid({
			url: '',
			loadMsg:"<spring:message code='system.datagrid.dataloding.msg'/>",
			striped:true,//奇偶行颜色不同
			singleSelect:true, //多选
			//collapsible:true,//可折叠 
			fitColumns: false,//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			nowrap: true,
			iconCls: 'icon icon-list',
			pagination:true, //显示分页 
			rownumbers:true, //显示行号 
			idField:'id',//主键字段
			frozenColumns:[[
							{field:'id',hidden:true,width:80,align:'center'},
						]],
			columns:[[
				 {field:'orgName',title:"销方交易机构代码",width:130,align:'center'},
				 {field:'legalEntityName',title:"销方交易机构名称",width:130,align:'center'},
				 {field:'periodName',title:"<spring:message code='invoiceTrxPool.periodName'/>",width:80,align:'center'},
				 {field:'trxBatchNum',title:"<spring:message code='invoiceTrxPool.trxBatchNum'/>",width:130,align:'center'},
				 {field:'trxNumber',title:"<spring:message code='invoiceTrxPool.trxNumber'/>",width:150,align:'center'},
				 {field:'trxEventId',title:"<spring:message code='invoiceTrxPool.trxEventId'/>",width:80,align:'center'},
				 {field:'sourceCodeName',title:"<spring:message code='invoiceTrxPool.sourceCode'/>",width:80,align:'center'},
				 {field:'trxDate',title:"<spring:message code='invoiceTrxPool.trxDate'/>",width:80,align:'center'},
				 {field:'glDate',title:"<spring:message code='invoiceTrxPool.glDate'/>",width:80,align:'center' },
				 
				 
				 
				 {field:'customerNumber',title:"购方客户编号",width:130,align:'center'},
				 {field:'customerName',title:"购方单位名称",width:80,align:'center'},
				 {field:'customerNumbType',title:"购方证件类型",width:130,align:'center'},
				 {field:'customerRegisTrationNum',title:"购方证件号码",width:150,align:'center'},
				 
				 {field:'custBankAccountNum',title:"购方客户账号",width:150,align:'center'},
				 {field:'custBankBranchName',title:"购方客户账号开户机构",width:180,align:'center'},		
				 
				 {field:'productNum',title:"商品及服务编码",width:150,align:'center'},
				 {field:'productName',title:"商品及服务名称",width:180,align:'center'},
				 
				 {field:'taxTrxTypeCode',title:"涉税交易类型编码",width:120,align:'center'},
				 {field:'taxTrxTypeName',title:"涉税交易类型名称",width:120,align:'center' },
				 {field:'taxItem',title:"税目",width:80,align:'center'},	
				 
				 {field:'taxSettingMethodName',title:"<spring:message code='invoiceTrxPool.taxSettingMethod'/>",width:80,align:'center'},
				 
				 {field:'taxRate',title:"<spring:message code='invoiceTrxPool.taxRate'/>%",width:80,align:'right',
					 formatter: function(value,row,index){
						return fmoney(value,0);
					 }
				 },
				 
				 /* {field:'isAccount',title:"是否核算",width:80,align:'center',
					 formatter: function(value,row,index){
						 if("1"==value) return "是";
						 else return "否";
					 }
				 }, */				 			 
				 {field:'isTax',title:"<spring:message code='invoiceTrxPool.isAccount'/>",width:80,align:'center',
					 formatter: function(value,row,index){
						 if("1"==value) return "是";
						 else return "否";
					 }
				 },
				 {field:'invoiceCategoryName',title:"<spring:message code='invoiceTrxPool.invoiceCategory'/>",width:80,align:'center'},
				 {field:'invoiceTypeName',title:"<spring:message code='invoiceTrxPool.invoiceType'/>",width:80,align:'center'},
				 {field:'taxBaseName',title:"<spring:message code='invoiceTrxPool.taxSettingRef'/>",width:80,align:'center'},
				 
				 {field:'accountRate',title:"<spring:message code='invoiceTrxPool.accountRate'/>",width:80,align:'right',
					 formatter: function(value,row,index){
						return fmoney(value,2);
					 }
				 },
				 {field:'exchangeRate',title:"<spring:message code='invoiceTrxPool.exchangeRate'/>",width:80,align:'right',
					formatter: function(value,row,index){
							return fmoney(value,2);
					}
				 },
				 {field:'rateDate',title:"<spring:message code='invoiceTrxPool.rateDate'/>",width:80,align:'center'},
				 
				 
				 {field:'origAmount',title:"原币金额",width:130,align:'right',
					 formatter: function(value,row,index){
						    
						    if(value!=''){
						    	return fmoney(value,2);
						    }
							
						 }
				 },
				 {field:'origCurrent',title:"原币币种",width:80,align:'center'},
				 {field:'currentAmount',title:"本位币金额",width:120,align:'right',
					 formatter: function(value,row,index){
						 if(value!=''){
						    	return fmoney(value,2);
						    }
						 }
				 },
				 {field:'current',title:"本位币币种",width:150,align:'center'},
				 {field:'totalAmount',title:"开票交易金额",width:80,align:'right',
					 formatter: function(value,row,index){
						 if(value!=''){
						    	return fmoney(value,2);
						    }
						 }
				 },
				 {field:'haveTotalAmount',title:"已开票金额",width:80,align:'right',
					 formatter: function(value,row,index){
						 if(value!=''){
						    	return fmoney(value,2);
						    }
						 }
				 },
				 {field:'remainAmount',title:"未开票金额",width:80,align:'right',
					 formatter: function(value,row,index){
						 if(value!=''){
						    	return fmoney(value,2);
						    }
						 }
				 },
				 
				 {field:'strStatus',title:"<spring:message code='status'/>",width:130,align:'center'}
				 
				 
			]],
			onLoadSuccess:function(){  
                $('#invoiceTrxPool_dataGrid').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
            } 
		});
		

		
		//设置分页控件	
		var p = $('#invoiceTrxPool_dataGrid').datagrid('getPager'); 
		$(p).pagination({ 
			pageSize: 10,//每页显示的记录条数，默认为10           
			beforePageText: '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
			afterPageText: '<spring:message code="pagination.afterPageText"/>',           
			displayMsg: '<spring:message code="pagination.displayMsg"/>',
			onSelectPage: function (pageNumber, pageSize) {//分页触发		
		    	 find(pageNumber,pageSize);
				 //Search(); 
	         }
		});
		
	}
	
	function find(pageNumber,pageSize){
		$('#invoiceTrxPool_searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		Search2();
	}
	
	
	function convertToName(url){
		var val;
	     $.ajax({
	        url: url,
	        type: "GET",
	        async: false,
	        data: '',
	        dataType: "json",
	        cache: false,  
	        success: function(result){
	        	val = result.value;
	        }
	     });
   	return val; 
	} 
</script>
</html>