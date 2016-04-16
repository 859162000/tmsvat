<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body id="layoutid" class="easyui-layout" style="overflow-y: hidden"  scroll="no">
	
	<div data-options="region:'center'" title="" scroll="no">
		<div class="easyui-layout" style="width: 100%; height: 100%;" scroll="no">
			<div region="north" split="true" border="false"
				style="overflow: hidden; height: 25%;">
				<div class="easyui-panel"
					title="<spring:message code="searchgroup"/>"
					style="height: 100%; background-color: #E0ECFF" scroll="no">
					<form id="printPoolHQueryInit_searchform" method="post">
						<!-- commandName="taxItemSearchForm" -->
						<table  style="padding:0px"  >
				    		<tr >
				    			<td align="right">销方单位名称:</td>
				    			<td>
				    				<input class="easyui-textbox" style='width:120px'  name="legalEntityName"  />
				    			</td>
				    			<td align="right">发票类型:</td>
				    			<td>
				    				<input:select easyuiStyle="width:120px;" id="printPoolHQueryInit_searchform_invoiceCategory" name="invoiceCategory" value="$printPoolHQueryInit_searchform_invoiceCategory" easyuiClass="easyui-combobox" >
            								<option value="">不限</option>
            								<input:systemStatusOption parentCode="VAT_CR_INVOICE_TYPE"/>
									</input:select>
				    				
				    			</td>
				    			<td align="right">开具状态:</td>
				    			<td>
				    				<input:select easyuiStyle="width:120px;" id="printPoolHManageInit_searchform_invoicePrintStatus" name="invoicePrintStatus" value="$printPoolHManageInit_searchform_invoicePrintStatus" easyuiClass="easyui-combobox" >
            								<option value="">不限</option>
            								<input:systemStatusOption parentCode="VAT_CR_INVOICE_STATUS"/>
									</input:select>
				    			</td>
				    			<td align='right'>申请日期:</td>
				    			<td>
				    				<input class="easyui-datebox" style='width:120px' value=" " name="sBeginDate" data-options="formatter:myformatter,parser:myparser" />&nbsp;至&nbsp;
				    				<input class="easyui-datebox"  style='width:120px'  value=" " name="sEndDate" data-options="formatter:myformatter,parser:myparser" />
				    			</td>  
				    		</tr>
				    		<tr>
				    			<td align="right">申请单编号:</td>
				    			<td>
				    				<input class="easyui-textbox" style='width:120px'  name="invoiceReqNumber"  />
				    			</td>
				    			<td align="right">发票号码:</td>
				    			<td>
				    				<input class="easyui-textbox" style='width:120px'  name="invoiceNumber"  />
				    			</td>
				    			<td align="right">发票代码:</td>
				    			<td>
				    				<input class="easyui-textbox" style='width:120px'  name="invoiceCode"  />
				    			</td>
				    			
				    			<td align="right" >来源:</td>
				    			<td>
				    				<input:select easyuiStyle="width:120px;" id="printPoolHQueryInit_searchform_invoiceReqType" name="invoiceReqType" value="$printPoolHQueryInit_searchform_invoiceReqType" easyuiClass="easyui-combobox" >
            							<option value="">不限</option>
   										<input:systemStatusOption parentCode="VAT_CR_INVOICE_ISSUE_SOURCE"/>
									</input:select>
				    			</td>
				    			
				    		</tr> 
				    		<tr >
				    			<td style="padding-left:20px">打印终端编号:</td>
				    			<td>
									<%-- <input  class="easyui-combobox" name="invoiceReqType" style="width:120px" 
				    							data-options="url:'${vat}/invoicePrintPoolH/getDicEntityByParentCode.do?parentCode=tms.vat.salesinvoice.comefrom',
                                                              method:'get',
                                                              valueField:'code',
                                                              textField:'name',
                                                              panelHeight:'auto'"> --%>
                                    <input class="easyui-textbox" style="width:120px"/>
				    			</td>
				    			<td align="right">申请组织:</td>
				    			<td>
				    				<input:select easyuiStyle="width:120px;" id="printPoolHQueryInit_searchform_appOrgCode" name="appOrgCode" value="$printPoolHQueryInit_searchform_appOrgCode" easyuiClass="easyui-combobox" >
   										<option value="">不限</option>
   										<input:systemStatusOption parentCode="BASE_BIZ_ORG"/>
									</input:select>
				    			</td>
				    			<td colspan='2'>
				    			   <div style="text-align:center;padding:10px">
				    	             <a href="#" id="searchbtn"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px" onclick="printPoolHQueryInit_Search()"><spring:message code='client.search.button.find'/></a>
				    	              <a href="#" id="resetbtn"  class="easyui-linkbutton"  style="width:80px" onclick="printPoolHQueryInit_clearSearchform()">重置</a>
				                   </div>    			   
				    			</td>
				    		</tr>
				    		<tr style="display:none;padding:5px" >
				    		   <td><input id="pageNumber" class="easyui-textbox" type="text" style="width:0px;" name="pageNumber" value=""></input></td>
				    		   <td><input id="pageSize" class="easyui-textbox" type="text" style="width:0px;" name="pageSize" value=""></input></td>
				    		</tr>
				    	</table>
					</form>
				</div>
			</div>
			<div data-options="region:'center',border:false" style="background-color: #E0ECFF">
				<div style="width: 100%; height: 100%">
					<table class="easyui-datagrid" id="printPoolHQueryInit_dataGrid"
						title="查询结果"
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
		</div>
	</div>
	<div data-options="region:'east',split:true"
		title="发票头信息"
		style="width: 100%;">
		<div class="easyui-panel" border="false"
			style="width: 100%; height: 100%; padding: 0px;">
			<div region="north" split="true" border="false" 
				style="overflow: hidden;height:20%">
				<div class="easyui-panel"border="false"
					style="width: 1500px; height: 100%; background-color: #E0ECFF">
					<form id="printPoolHQueryInit_details_searchform" method="get">
						<!-- commandName="taxItemSearchForm" -->
						<table  style="padding:0px"  >
				    		<tr >
			    				<td align='right'>购方名称:</td>
			    				<td><input name="customerName" readonly/></td>
			    				<td align='right'>购方纳税人识别号:</td>
			    				<td><input name="custRegistrationNumber" readonly/></td>
			    				<td align='right'>地址:</td>
			    				<td><input name="custRegistrationAddress" readonly/></td>
			    				<td align='right'>电话:</td>
			    				<td><input name="custContactPhone" readonly/></td>
			    				
				    		</tr>
				    		<tr >
				    			<td align='right'>开户行:</td>
			    				<td><input name="custDepositBankName" readonly/></td>
			    				<td align='right'>银行账号:</td>
			    				<td><input name="custDepositBankAccountNum" readonly/></td>
			    				<td align='right'>销方名称:</td>
			    				<td><input name="legalEntityName" readonly/></td>
			    				<td align='right'>销方纳税人识别号:</td>
				    			<td><input name="registrationNumber" readonly/></td>
				    		</tr>
				    		<tr>
			    				<td align='right'>发票类型:</td>
			    				<td><input name="invoiceCategoryName" readonly/></td>
			    				<td align='right'>发票代码:</td>
			    				<td><input name="invoiceCode" readonly/></td>
			    				<td align='right'>发票号码:</td>
			    				<td><input name="invoiceNumber" readonly/></td>
			    				<td align='right'>税额:</td>
			    				<td><input name="" readonly/></td>
				    		</tr>
				    		<tr>
				    			<td align='right'>开具日期:</td>
				    			<td><input name="invoicePrintDate"  readonly/></td>
			    				<td align='right'>开具人:</td>
			    				<td><input name="invoicePrintBy" readonly/></td>
			    				<td align='right'>合计金额(含税):</td>
			    				<td><input name="invoiceAmount" readonly/></td>
			    				<td align='right'>不含税金额:</td>
			    				<td><input name="acctdAmountCR" readonly/></td>
				    		</tr>
		    		</table>
					</form>
				</div>
			</div>
			<div style="width: 100%; height: 80%;" border="false">
				<table class="easyui-datagrid" id="printPoolHQueryInit_details_dataGrid"
					toolbar="#dgpredetailtoolbar" title="发票明细行(双击行查看交易详情)"style="width: 100%; height: 100%"
					data-options="					
						autoRowHeight:true,
						pagination:true,
						pageSize:10,
						remoteSort:false,
					    multiSort:true,
						">
				</table>
			</div>
		</div>
	</div>
	<div data-options="region:'center',border:false" style="background-color: #E0ECFF">
	   <div id="printPoolHQueryInit_printSubmit_dlg" class="easyui-dialog"
			style="width: 1068px; height: 500px;" closed="true" 
			buttons="#printPoolHQueryInit_printSubmit_dlg-buttons" modal="true">
				<form id="printPoolHQueryInit_printSubmit_editform" class="easyui-form" method="post" data-options="novalidate:true" commandsName="billBookManageForm">
					<table >
		    		<tr>
		    			<td align="right"><font color=red>*</font>发票打印起始号：</td>
		    			<td>
		    				
		    			</td>
		    		</tr>
		    	</table>
		    </form>
		    <div style="width: 100%; height: 100%;" border="false">
				<table class="easyui-datagrid" id="printPoolHQueryInit_printSubmit_dataGrid"
					toolbar="" title="待打印发票列表"style="width: 100%; height: 100%"
					data-options="					
						autoRowHeight:true,
						pagination:false,
						pageSize:10,
						remoteSort:false,
					    multiSort:true,
						">
				</table>
			</div>
		</div>
		<div id="printPoolHQueryInit_printSubmit_dlg-buttons">
			<a  href="javascript:void(0)" class="easyui-linkbutton"	iconCls="icon-ok" onclick="printPoolHQueryInit_printSubmit_confirm()">
					确认打印
			</a> 
			<%-- <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#customerManageInit_add_dlg').dialog('close')"><spring:message
					code="button.Close" />
			</a> --%>
		</div>
    </div>
	<div data-options="region:'center',border:false" style="background-color: #E0ECFF">
	   <div id="printPoolHQueryInit_poolDDetail_dlg" class="easyui-dialog"
			closed="true" width="800" height="500" data-options="draggable:false"
			buttons="#printPoolHQueryInit_poolDDetail_dlg-buttons" modal="true"  >
			<!-- maximized='true' -->
			<div class="easyui-panel" border="false"
				style="width: 100%; padding: 10px;height:100%;">
		    	<table align='center' class="easyui-datagrid" id="printPoolHQueryInit_poolDDetail_dataGrid"
								title="发票交易流水信息"
								style="width: 98%;min-height:440px;padding: 20px"
								data-options="					
									singleSelect:true,
									autoRowHeight:false,
									pagination:false,
									pageSize:10,
									remoteSort:false,
								    multiSort:true	
										">
				</table>
			</div>
		</div>
		<%-- <div id="customerManageInit_add_dlg-buttons">
			<a  href="javascript:void(0)" class="easyui-linkbutton"	iconCls="icon-ok" onclick="customerManageInit_Save()">
					<spring:message	code="button.Save" />
			</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#customerManageInit_add_dlg').dialog('close')"><spring:message
					code="button.Close" />
			</a>
		</div> --%>
    	</div>
 </body>  
<script type="text/javascript">
	$(document).ready(function(){
		$("#layoutid").layout('collapse', 'east');
		pageDataSettingInit();
		InitCombobox();	
		$('#printPoolHQueryInit_searchform').form('load', {
			pageNumber: $('#printPoolHQueryInit_dataGrid').datagrid('options').pageNumber,
			pageSize: $('#printPoolHQueryInit_dataGrid').datagrid('options').pageSize
		});
		printPoolHQueryInit_Search();
	});
	//页面表格绑定初始化
	function pageDataSettingInit(){
		
		$('#printPoolHQueryInit_poolDDetail_dataGrid').datagrid({
			url: '',
			loadMsg:"<spring:message code='system.datagrid.dataloding.msg'/>",
			striped:true,//奇偶行颜色不同
			checkbox:false,
			collapsible:false,//可折叠  
			fitColumns: false,//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			nowrap: false,
			iconCls: 'icon icon-list',
			pagination:false, //显示分页 
			rownumbers:true, //显示行号
			idField:'id',//主键字段
			columns:[[
     					{field:'inventoryItemNumber',title:"货物及应税劳务编码",width:140,align:'center'},
     					{field:'inventoryItemDescripton',title:"货物及应税劳务名称",width:140,align:'center'},
     					{field:'inventoryItemModels',title:"规格型号",width:120,align:'center'},
     					{field:'uomCodeDescripton',title:"单位",width:90,align:'center'},
     					{field:'inventoryItemQty',title:"数量",width:90,halign:'center',align:'right'},
     					{field:'customerName',title:"单价",width:90,halign:'center',align:'right'},
     					{field:'invoiceAmount',title:"金额",width:90,halign:'center',align:'right',formatter:function(value,rec){
     							//var temp_value='<span style="color:red">'+value+'</span>';
     							var temp_value=value;
     						return temp_value;
     					}},
     					/* {field:'bankAccountNum',title:"净额",width:80,align:'center'},
     					{field:'bankAccountNum',title:"税额",width:80,align:'center'},
     					{field:'bankAccountNum',title:"合计",width:80,align:'center'}, */
     					{field:'taxRate',title:"税率",width:90,halign:'center',align:'right'},
     					{field:'vatAmount',title:"税额",width:90,halign:'center',align:'right'},
     			]],
			onLoadSuccess:function(){  
                $('#printPoolHQueryInit_poolDDetail_dataGrid').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
            } 
		});
		
		$('#printPoolHQueryInit_details_dataGrid').datagrid({
			url: '',
			loadMsg:"<spring:message code='system.datagrid.dataloding.msg'/>",
			striped:true,//奇偶行颜色不同
			checkbox:false,
			collapsible:false,//可折叠  
			fitColumns: false,//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			nowrap: false,
			iconCls: 'icon icon-list',
			pagination:false, //显示分页 
			rownumbers:true, //显示行号
			idField:'id',//主键字段
			columns:[[
   					{field:'inventoryItemNumber',title:"货物及应税劳务编码",width:130,align:'center'},
   					{field:'inventoryItemDescripton',title:"货物及应税劳务名称",width:130,align:'center'},
   					{field:'inventoryItemModels',title:"规格型号",width:80,align:'center'},
   					{field:'uomCodeDescripton',title:"单位",width:80,align:'center'},
   					{field:'inventoryItemQty',title:"数量",width:80,halign:'center',align:'right'},
   					{field:'customerName',title:"单价",width:80,halign:'center',align:'right'},
   					{field:'invoiceAmount',title:"金额",width:80,halign:'center',align:'right',formatter:function(value,rec){
   							//var temp_value='<span style="color:red">'+value+'</span>';
   							var temp_value=value;
   						return temp_value;
   					}},
   					/* {field:'bankAccountNum',title:"净额",width:80,align:'center'},
   					{field:'bankAccountNum',title:"税额",width:80,align:'center'},
   					{field:'bankAccountNum',title:"合计",width:80,align:'center'}, */
   					{field:'taxRate',title:"税率",width:80,halign:'center',align:'right'},
   					{field:'vatAmount',title:"税额",width:80,halign:'center',align:'right'},
   			]],
 			onDblClickRow:function(index,data){
 				printPoolHQueryInit_poolD_view(index,data);
			},
			onLoadSuccess:function(){  
                $('#printPoolHQueryInit_details_dataGrid').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
            } 
		});
		$('#printPoolHQueryInit_dataGrid').datagrid({
			url: '',
			loadMsg:"<spring:message code='system.datagrid.dataloding.msg'/>",
			striped:true,//奇偶行颜色不同
			singleSelect:true, //多选
			collapsible:false,//可折叠  
			fitColumns: false,//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			nowrap: false,
			iconCls: 'icon icon-list',
			pagination:true, //显示分页 
			rownumbers:true, //显示行号
			idField:'id',//主键字段
			columns:[[
					{field:'invoiceReqTypeName',title:"来源",width:80,align:'center'},
   					
   					{field:'invoiceReqNumber',title:"开票申请单号",width:150,align:'center'},
   					{field:'invoicePreNumber',title:"开票准备单号",width:150,align:'center'},
   					{field:'orgName',title:"申请组织",width:80,align:'center'},
   					{field:'invoicePrintStatusName',title:"发票状态",width:100,align:'center'},
   					{field:'equipmentCode',title:"打印终端编号",width:100,align:'center'},
   					{field:'equipmentName',title:"打印终端名称",width:100,align:'center'},
   					{field:'invoiceCategoryName',title:"发票类型",width:100,align:'center'},
   					{field:'acctdAmountCR',title:"金额",width:100,halign:'center',align:'right'},
   					{field:'vatAmount',title:"税额",width:100,halign:'center',align:'right'},
   					{field:'invoiceAmount',title:"合计金额",width:100,halign:'center',align:'right'},
   					{field:'customerName',title:"购方单位名称",width:100,align:'center'},
   					{field:'legalEntityName',title:"销方单位名称",width:100,align:'center'},
   					{field:'invoiceCode',title:"发票代码",width:100,align:'center'},
   					{field:'invoiceNumber',title:"发票号码",width:100,align:'center'},
   					{field:'invoicePrintBy',title:"发票开具人",width:100,align:'center'},
   					{field:'invoicePrintDate',title:"发票开具日期",width:120,align:'center',formatter:function(value,rec){
   						if (typeof(value)!="undefined"&&value!=0&&value!=null){
								return new Date(value).format("yyyy-MM-dd");
							}
  					}}
				]],
	            onDblClickRow:function(index,data){
	            	printPoolHQueryInit_view(index,data);
				},
			onLoadSuccess:function(){  
                $('#printPoolHQueryInit_dataGrid').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
            } 
		});
		//设置分页控件	
		var p = $('#printPoolHQueryInit_dataGrid').datagrid('getPager'); 
			$(p).pagination({           
				pageSize: 10,//每页显示的记录条数，默认为10           
				//pageList: [5,10,15,20],//可以设置每页记录条数的列表           
				beforePageText: '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
				afterPageText: '<spring:message code="pagination.afterPageText"/>',           
				displayMsg: '<spring:message code="pagination.displayMsg"/>',
				onSelectPage: function (pageNumber, pageSize) {//分页触发		
					 printPoolHQueryInit_find(pageNumber,pageSize);
					 printPoolHQueryInit_Search(); 
					 printPoolHQueryInit_clearForm();
		         }
		});
		$('#printPoolHQueryInit_printSubmit_dataGrid').datagrid({
				url: '',
				loadMsg:"<spring:message code='system.datagrid.dataloding.msg'/>",
				striped:true,//奇偶行颜色不同
				singleSelect:false, //多选
				collapsible:false,//可折叠  
				fitColumns: true,//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				nowrap: false,
				iconCls: 'icon icon-list',
				pagination:false, //显示分页 
				rownumbers:true, //显示行号
				idField:'id',//主键字段
				frozenColumns:[[
								{field:'id',hidden:true,width:80,align:'center'}
				                ]],
				                columns:[[
				       					{field:'invoiceReqTypeName',title:"来源",width:80,align:'center'},
					   					{field:'invoiceReqNumber',title:"开票申请单号",width:150,align:'center'},
					   					{field:'invoicePreNumber',title:"开票准备单号",width:150,align:'center'},
					   					{field:'operationOrgCode',title:"申请组织",width:80,align:'center'},
					   					{field:'invoicePrintStatusName',title:"发票状态",width:100,align:'center'},
					   					{field:'equipmentCode',title:"打印终端编号",width:100,align:'center'},
					   					{field:'equipmentName',title:"打印终端名称",width:100,align:'center'},
					   					{field:'invoiceCategory',title:"发票类型",width:100,align:'center'},
					   					{field:'acctdAmountCR',title:"金额",width:100,halign:'center',align:'right'},
					   					{field:'vatAmount',title:"税额",width:100,halign:'center',align:'right'},
					   					{field:'invoiceAmount',title:"合计金额",width:100,halign:'center',align:'right'},
					   					{field:'customerName',title:"购方单位名称",width:100,align:'center'},
					   					{field:'legalEntityName',title:"销方单位名称",width:100,align:'center'},
					   					{field:'invoiceCode',title:"发票代码",width:100,align:'center'},
					   					{field:'invoiceNumber',title:"发票号码",width:100,align:'center'},
					   					{field:'invoicePrintBy',title:"发票开具人",width:100,align:'center'},
					   					{field:'invoicePrintDate',title:"发票开具日期",width:120,align:'center',formatter:function(value,rec){
					   						if (typeof(value)!="undefined"&&value!=0&&value!=null){
													return new Date(value).format("yyyy-MM-dd");
												}
					  					}}
				       			]],
		            onDblClickRow:function(index,data){
		            	//printPoolHQueryInit_view(index,data);
					},
				onLoadSuccess:function(){  
	                $('#printPoolHQueryInit_printSubmit_dataGrid').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
	            } 
			});
	}
	function printPoolHQueryInit_find(pageNumber,pageSize){
		$('#printPoolHQueryInit_searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		printPoolHQueryInit_Search();
	}
	function printPoolHQueryInit_Search(){
    	$("#printPoolHQueryInit_dataGrid").datagrid("loading");
    	$('#printPoolHQueryInit_searchform').form('submit', {
			url:'${vat}/invoicePrintPoolH/loadInvoicePrintedPoolHPage.do',			
			onSubmit : function(){
				return $(this).form('enableValidation').form('validate');
			},
			success:function(result){
				var result = $.parseJSON(result);
				if(result.status=='0'){
				     $("#printPoolHQueryInit_dataGrid").datagrid('loadData', result.data);
				}else if(result.status=='1'){
					//$.messager.alert('<spring:message code="system.alert"/>',result.erroMessage,'info');
				}
				$("#printPoolHQueryInit_dataGrid").datagrid("loaded"); 
		     }
		});
    }
	
	function InitCombobox(){
		/*
		var urlString="parentCode=INVOICE_TYPE"
		$.ajax({  
	        url: "${vat}/invoicePrintPoolH/getDictionaryEntityByParentCode.do",  
	        type: "POST",  
	        async: true,  
	        data: urlString, //不能直接写成 {id:"123",code:"tomcat"}  
	        dataType: "json",  
	        // contentType: "charset=utf-8",  
	        cache: false,  
	        success: function (result){
	        	$('#printPoolHQueryInit_searchform_invoiceCategory').combobox({
	        		data:result.data,
	        		idField:"code",
	 			   	textField:"name",
	        		onLoadSuccess:function(data){
	        			 if(data){
	        				$('#printPoolHQueryInit_searchform_invoiceCategory').combobox('setValue',data[0].value);
	        			} 
	        		}
	        	});
	        } 
	    });
		 */
	}
    //发票明细查询
    function printPoolHQueryInit_view(index,data){
    	$('#layoutid').layout('expand', 'east');
    	var urlString='invoicePrtPoolHId='+data.id;
    	$.ajax({  
    		url: "${vat}/invoicePrintPoolH/loadInvoicePrintPoolHDetail.do", 
	        type: "POST",  
	        async: true,  
	        data: urlString, //不能直接写成 {id:"123",code:"tomcat"}  
	        dataType: "json",  
	        cache: false,  
	        success: function(result){
	        	if(result.status=='0'){
	        		
	        		$("#printPoolHQueryInit_details_searchform").form('load',result.data);
	        		$("#printPoolHQueryInit_details_searchform").form('load',{invoicePrintDate:function(){
		        			var value=result.data.invoicePrintDate;
		        			if (typeof(value)!="undefined"&&value!=0&&value!=null){
								return new Date(value).format("yyyy-MM-dd");
							}
	        			}
	        		});
	        		//myformatter(new Date(result.data.invoicePrintDate))
	        		$('#printPoolHQueryInit_details_dataGrid').datagrid('loadData',result.data.printPoolLInParamList);
	        	}else{
	        		//$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="system.delete.fail.text"/>');
	        	}
	        } 
	    });
    }
    
    function printPoolHQueryInit_poolD_view(index,data){
    	var urlString='id='+data.id;
    	$.ajax({  
	        url: "${vat}/invoicePrintPoolH/getInvoicePrintPoolDInParamDetailList.do",
	        type: "POST",  
	        async: true,  
	        data: urlString, //不能直接写成 {id:"123",code:"tomcat"}  
	        dataType: "json",  
	        cache: false,  
	        success: function(result){
	        	if(result.status=='0'){
	        		$("#printPoolHQueryInit_poolDDetail_dlg").dialog('open').dialog('setTitle',
	        		"明细查看");
	        		$("#printPoolHQueryInit_poolDDetail_dataGrid").datagrid('loadData',result.data.invoicePrintPoolDList);
	        		//$("#printPoolHManageInit_details_searchform").form('load',result.data);
	        		//$('#printPoolHManageInit_details_dataGrid').datagrid('loadData',result.data.invoicePrintPoolLInParamList);
	        	}else{
	        		//$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="system.delete.fail.text"/>');
	        	}
	        } 
	    });
}
    
	function printPoolHQueryInit_clearForm(){
		$('#printPoolHQueryInit_editform').form('clear');
	}
	function printPoolHQueryInit_clearSearchform(){
		$('#printPoolHQueryInit_searchform').form('reset');
		$('#printPoolHQueryInit_searchform').form('load', {
			pageNumber: $('#printPoolHQueryInit_dataGrid').datagrid('options').pageNumber,
			pageSize: $('#printPoolHQueryInit_dataGrid').datagrid('options').pageSize
		});
	}
	
</script>
</html>