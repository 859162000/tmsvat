<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	<!-- <style type="text/css">
	.min-width{
 		min-width: 333px;
 		width: expression_r( document.body.clientWidth < 1200px ? "333px" : "auto" );
 		}
	</style> -->
</head>
<body id="layoutid" class="easyui-layout " style="overflow-y: hidden;" scroll="no" >
	<div data-options="region:'center'" title="" 
		style="width:100%" border="false">
		<div class="easyui-panel" border="false"
			style="width: 100%; height: 100%; padding: 0px;overflow: hidden;"border="false">
				<div class="easyui-layout" style="width: 100%; height: 100%;overflow: hidden;">
					<div region="north" split="true" border="false"
						style="overflow: hidden; height: 25%;">
						<div class="easyui-panel"
							title="<spring:message code="searchgroup"/>"
							style=" height: 100%; background-color: #E0ECFF ;overflow: hidden;">
							<form id="printPoolHManageInit_searchform" method="post">
								<!-- commandName="taxItemSearchForm" -->
								<table  style="padding:0px"  >
				    		<tr >
				    			<td align="right">销方单位名称:</td>
				    			<td>
				    				<input id="printPoolHManageInit_searchform_legalEntityName_input"  style='width:120px'  name="legalEntityName"  />
				    			</td>
				    			<td align="right">发票类型:</td>
				    			<td>
				    				<input:select easyuiStyle="width:120px;" dataOptions="editable:false"id="printPoolHManageInit_searchform_invoiceCategory" name="invoiceCategory" value="$printPoolHManageInit_searchform_invoiceCategory" easyuiClass="easyui-combobox" >
            								<option value="">不限</option>
            								<input:systemStatusOption parentCode="VAT_CR_INVOICE_TYPE"/>
									</input:select>
				    			</td>
				    			<td align="right">发票状态:</td>
				    			<td>
				    				<input:select easyuiStyle="width:120px;" dataOptions="editable:false" id="printPoolHManageInit_searchform_invoicePrintStatus" name="invoicePrintStatus" value="$printPoolHManageInit_searchform_invoicePrintStatus" easyuiClass="easyui-combobox" >
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
				    			<td align='right'>购方客户：</td>
          						<td><input id="printPoolHManageInit_searchform_customerName_input" class="easyui-textbox" style="width: 120px" name="customerId" /></td>
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
				    			
				    			
				    		</tr> 
				    		<tr >
				    			<td style="padding-left:20px">打印终端编号:</td>
				    			<td>
									<%-- <input  class="easyui-combobox" name="equipmentCode" style="width:120px" 
				    							data-options="url:'${vat}/invoicePrintPoolH/getDicEntityByParentCode.do?parentCode=tms.vat.salesinvoice.comefrom',
                                                              method:'get',
                                                              valueField:'code',
                                                              textField:'name',
                                                              panelHeight:'auto'"> --%>
                                     <input class="easyui-textbox" style="width:120px"/>
				    			</td>
				    			<td align="right">申请机构:</td>
				    			<td>
				    				<%-- <input:select easyuiStyle="width:120px;" id="printPoolHManageInit_searchform_appOrgCode" name="appOrgCode" value="$printPoolHManageInit_searchform_appOrgCode" easyuiClass="easyui-combobox" >
   										<option value="">不限</option>
   										<input:systemStatusOption parentCode="BASE_BIZ_ORG"/>
									</input:select>  --%>
									<input id="printPoolHManageInit_searchform_appOrgCode"  class="easyui-combogrid" name="appOrgCode" style="width:120px" 
				    							data-options="
                                                              panelWidth:250,
                                                              method:'get',
                                                              idField:'orgId',
            												  textField:'orgCode',
                                                              columns:[[
													                {field:'orgCode',title:'机构编号',width:120},
													                {field:'orgName',title:'机构名称',width:120}
													            ]]">
				    			</td>
				    			<td align="right" >来源:</td>
				    			<td>
				    				<input:select easyuiStyle="width:120px;" id="printPoolHManageInit_searchform_invoiceReqType" name="invoiceReqType" value="$printPoolHManageInit_searchform_invoiceReqType" easyuiClass="easyui-combobox" dataOptions="editable:false">
   										<option value="">不限</option>
   										<input:systemStatusOption parentCode="VAT_CR_INVOICE_ISSUE_SOURCE"/>
									</input:select>
				    			</td>
				    			<td colspan='2'>
				    			   <div style="text-align:center;padding:10px">
				    	             <a href="#" id="searchbtn"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px" onclick="printPoolHManageInit_Search()"><spring:message code='client.search.button.find'/></a>
				    	              <a href="#" id="researchbtn"  class="easyui-linkbutton" data-options="" style="width:80px" onclick="printPoolHmanageInit_clearSearchForm()">重置</a>
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
							<table class="easyui-datagrid" id="printPoolHManageInit_dataGrid"
								title="查询结果(双击行记录可修改打印终端)"
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
					<form id="printPoolHManageInit_details_searchform" method="get">
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
			    				<td><input name="vatAmount" readonly/></td>
				    		</tr>
				    		<tr>
				    			<td align='right'>开具日期:</td>
				    			<td><input name="invoicePrintDate" data-options="formatter:myformatter,parser:myparser" readonly/></td>
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
				<table class="easyui-datagrid" id="printPoolHManageInit_details_dataGrid"
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
	   <div id="printPoolHManageInit_printSubmit_dlg" class="easyui-dialog"
			 closed="true" width="800" height="500"
			buttons="#printPoolHManageInit_printSubmit_dlg-buttons" modal="true">
				<form id="printPoolHManageInit_printSubmit_editform" class="easyui-form" method="post" data-options="novalidate:true" commandsName="billBookManageForm">
					<!-- <table >
		    		<tr>
		    			<td align="right"><font color=red>*</font>发票打印起始号：</td>
		    			<td>
		    				<input name="taxIdStart" readonly> 
		    			</td>
		    		</tr>
		    	</table> -->
		    </form>
		    <div style=" height: 100%;" border="false" >
				<table class="easyui-datagrid" id="printPoolHManageInit_printSubmit_dataGrid"
					toolbar="" border="false" title="待打印发票列表"style="width: 100%; height: 100%"
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
		<div id="printPoolHManageInit_printSubmit_dlg-buttons">
			<a  href="javascript:void(0)" id="printPoolHManageInit_printSubmit_print_buttons" class="easyui-linkbutton"	iconCls="icon-ok" onclick="printPoolHManageInit_printSubmit_confirm()">
					打印发票
			</a> 
			<%-- <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#customerManageInit_add_dlg').dialog('close')"><spring:message
					code="button.Close" />
			</a> --%>
		</div>
    </div>
    
    <div data-options="region:'center',border:false" style="background-color: #E0ECFF">
	   <div id="printPoolHManageInit_poolDDetail_dlg" class="easyui-dialog"
			closed="true" width="800" height="500" data-options="draggable:true"
			buttons="#printPoolHManageInit_poolDDetail_dlg-buttons" modal="true"  >
			<!-- maximized='true' -->
			<div class="easyui-panel" border="false"
				style="width: 100%; padding: 10px;height:100%;">
		    	<table align='center' class="easyui-datagrid" id="printPoolHManageInit_poolDDetail_dataGrid"
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
		$('#printPoolHManageInit_searchform').form('load', {
			pageNumber: $('#printPoolHManageInit_dataGrid').datagrid('options').pageNumber,
			pageSize: $('#printPoolHManageInit_dataGrid').datagrid('options').pageSize
		});
		
		$('#printPoolHManageInit_searchform_appOrgCode').combogrid({
			url:'${vat}/invoicePrintPoolH/loadApplyOrgListForInvoicePrint.do',
			onLoadSuccess: function(data){
				var id=data.rows[0].orgId;
				if(typeof(id)!='undefined'&&id!=''){
					$(this).combogrid('grid').datagrid('selectRecord',id);
				}
				printPoolHManageInit_Search();
				}
			});	
		
	});
	//页面表格绑定初始化
	function pageDataSettingInit(){
		//默认值设置
		$('#printPoolHManageInit_poolDDetail_dataGrid').datagrid({
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
     					{field:'acctdAmountCR',title:"金额",width:90,halign:'center',align:'right',formatter:function(value,rec){
     							//var temp_value='<span style="color:red">'+value+'</span>';
     							var temp_value=value;
     						return temp_value;
     					}},
     					/* {field:'bankAccountNum',title:"净额",width:80,align:'center'},
     					{field:'bankAccountNum',title:"税额",width:80,align:'center'},
     					{field:'bankAccountNum',title:"合计",width:80,align:'center'}, */
     					{field:'taxRate',title:"税率",width:90,halign:'center',align:'center',formatter:function(value,rec){
	   						if (typeof(value)!="undefined"&&value!=null){
								return value+"%";
							}
	  					}},
     					{field:'vatAmount',title:"税额",width:90,halign:'center',align:'right'},
     			]],
			onLoadSuccess:function(){  
                $('#printPoolHManageInit_poolDDetail_dataGrid').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
            } 
		});
		//发票行表信息
		$('#printPoolHManageInit_details_dataGrid').datagrid({
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
     					{field:'acctdAmountCR',title:"金额",width:90,halign:'center',align:'right',formatter:function(value,rec){
     							//var temp_value='<span style="color:red">'+value+'</span>';
     							var temp_value=value;
     						return temp_value;
     					}},
     					/* {field:'bankAccountNum',title:"净额",width:80,align:'center'},
     					{field:'bankAccountNum',title:"税额",width:80,align:'center'},
     					{field:'bankAccountNum',title:"合计",width:80,align:'center'}, */
     					{field:'taxRate',title:"税率",width:90,halign:'center',align:'center',formatter:function(value,rec){
	   						if (typeof(value)!="undefined"&&value!=null){
								return value+"%";
							}
	  					}},
     					{field:'vatAmount',title:"税额",width:90,halign:'center',align:'right'},
     			]],
     			onDblClickRow:function(index,data){
     				printPoolHManageInit_poolD_view(index,data);
				},
			onLoadSuccess:function(){  
                $('#printPoolHManageInit_details_dataGrid').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
            } 
		});
		
		$('#printPoolHManageInit_dataGrid').datagrid({
			url: '',
			loadMsg:"<spring:message code='system.datagrid.dataloding.msg'/>",
			striped:true,//奇偶行颜色不同
			singleSelect:false, //多选
			collapsible:false,//可折叠  
			fitColumns: false,//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			nowrap: false,
			iconCls: 'icon icon-list',
			pagination:true, //显示分页 
			rownumbers:true, //显示行号
			idField:'id',//主键字段
			columns:[[	{field:'checkFlag',checkbox:true,width:80,align:'center'},
			        	{field:'invoiceReqTypeName',title:"来源",width:80,align:'center'},
	 					{field:'invoiceReqNumber',title:"开票申请单号",width:150,align:'center'},
	 					{field:'invoicePreNumber',title:"开票准备单号",width:150,align:'center'},
	 					{field:'orgName',title:"申请组织",width:80,align:'center'},
	 					{field:'invoicePrintStatusName',title:"发票状态",width:80,align:'center'},
	 					{field:'legalEntityName',title:"销方单位名称",width:100,align:'center'},
	 					//{field:'invoicePrintStatusName',title:"发票状态",width:100,align:'center'},
	 					{field:'invoiceCode',title:"发票代码",width:100,align:'center'},
	   					{field:'invoiceNumber',title:"发票号码",width:100,align:'center'},
	   					{field:'customerNumber',title:"购方客户编码",width:100,align:'center'},
	   					{field:'customerName',title:"购方单位名称",width:100,align:'center'},
	   					{field:'custRegistrationCode',title:"购方证件类型",width:100,align:'center'}, 
	   					{field:'custRegistrationNumber',title:"购方证件号码",width:100,align:'center'},
	   					{field:'invoiceCategoryName',title:"发票类型",width:100,align:'center'},
	   					{field:'acctdAmountCR',title:"金额",width:100,halign:'center',align:'right'},
	   					{field:'vatAmount',title:"税额",width:100,halign:'center',align:'right'},
	   					{field:'invoiceAmount',title:"合计金额",width:100,halign:'center',align:'right'},
	 					{field:'equipmentCode',title:"打印终端编号",align:'center',width:100,editor: {
	 						type: 'combogrid', options: { required: false } 
	 						} 
	 					},
	 					{field:'equipmentName',title:"打印终端名称",width:100,align:'center'},
	   					{field:'invoicePrintBy',title:"发票开具人",width:100,align:'center'},
	   					{field:'invoicePrintDate',title:"发票开具日期",width:120,align:'center',formatter:function(value,rec){
	   						if (typeof(value)!="undefined"&&value!=0&&value!=null){
								return new Date(value).format("yyyy-MM-dd");
							}
	  					}}
	 			]],
			toolbar:[
			         {text:"发票打印",  
                	iconCls:'icon-print',
                	handler:function(){  
                		printPoolHManageInit_printSubmit();  
                			}  
            		},'-',{text:"批量打印",  
                    	iconCls:'icon-print',
                    	handler:function(){  
                    		printPoolHManageInit_printSubmit_confirm_new();  
                    			}  
                		},'-',{text:"发票详情",  
                        	iconCls:'icon-search',
                        	handler:function(){  
                        		printPoolHManageInit_view();  
                        			}  
                    		},'-'],
	            onDblClickRow:function(index,data){
	            	if(data.invoicePrintStatus!='10'){
	            		$.messager.alert('<spring:message code="system.alert"/>','该发票状态不具备更新打印终端的条件');
	            		return false;
	            	}
	            	//$(this).datagrid('acceptChanges');
	            	$(this).datagrid('beginEdit', index);
	            	
	            	var id=data.id;
	            	
	            	var row_1 = $(this).datagrid('getEditors', index);
	            	row_1[0].target.combogrid({
	            		required: false,
            			panelWidth : 200,
            			 idField : 'equipmentCode',  
            			textField : 'equipmentCode',  
               		 	url:"${vat}/invoicePrintPoolH/loadLegalEntityForInvoicePrint.do",
	               		fitColumns : true,
	         			striped : true,
	         			editable : true,
	         			rownumbers : true, //序号
	         			collapsible : false, //是否可折叠的
	         			queryParams:{ id:id},
	         			method : 'post',
            			columns : [[{
            				field : 'equipmentCode',
            				title : '打印终端编号',
            				width : 13
            			}, {
            				field : 'equipmentName',
            				title : '打印终端名称',
            				width : 20
            			}]],onSelect:function(){
            				var select_record=row_1[0].target.combogrid('grid').datagrid('getSelected');
            				var container=$('#printPoolHManageInit_dataGrid');
            				var row_temp = $(container).datagrid("selectRow", index).datagrid("getSelected");
            				 row_temp.equipmentName = select_record.equipmentName;
            				row_temp.equipmentCode = select_record.equipmentCode;
            				$.messager.confirm("提示","确认保存打印终端?",function(result){
            	    			if(result){
            	    				
            	    				var urlString="equipmentName="+select_record.equipmentName;
            	    					urlString=urlString+'&equipmentCode='+select_record.equipmentCode;
            	    					urlString=urlString+'&equipmentId='+select_record.equipmentId;
            	    					urlString=urlString+'&equipmentIp='+select_record.equipmentIp;
            	    					urlString=urlString+'&equipmentPort='+select_record.equipmentPort;
            	    					urlString=urlString+'&id='+data.id;
            	    					$.post('${vat}/invoicePrintPoolH/saveLegalEntityForInvoicePrint.do',
            	    						{
            	    							equipmentName: select_record.equipmentName,
            	    							equipmentCode: select_record.equipmentCode,
            	    							equipmentIp: select_record.equipmentIp,
            	    							equipmentPort: select_record.equipmentPort,
            	    							equipmentId: select_record.equipmentId,
            	    							id : data.id
            	    						},function(result) {
            	    							if(result.status=='0'){
            	    								$.messager.alert('提示','打印终端信息更新成功');
            	    								$(container).datagrid('endEdit', index);
            	    	            				$(container).datagrid('refreshRow', index);
            	    	            				$(container).datagrid('selectRow', index);
            	    	            				$(container).datagrid('clearSelections');
            	    							}else{
            	    								$.messager.alert('<spring:message code="system.alert"/>',result.erroMessage);
            	    							}
            	    						},
            	    					'json');
            	    				/* $.ajax({  
            	    			        url: "${vat}/invoicePrintPoolH/saveLegalEntityForInvoicePrint.do",  
            	    			        type: "POST",  
            	    			        async: true,  
            	    			        data: urlString, //不能直接写成 {id:"123",code:"tomcat"}  
            	    			        dataType: "json",  
            	    			        cache: false,  
            	    			        success:function(result){
            	    			        	if(result.status=='0'){
            	    			        		$.messager.alert('提示','发送税控系统成功操作成功');
            	    			        		//printPoolHManageInit_Search();
            	    			        		//$('#printPoolHManageInit_details_dataGrid').datagrid('loadData',result.data);
            	    			        		
            	    			        	}else{
            	    			        		$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="system.delete.fail.text"/>');
            	    			        	}
            	    			        } 
            	    			    }); */
            	    			}else{
            	    				$(container).datagrid('endEdit', index);
            	    				$(this).datagrid('rejectChanges');
            	    			}
            	    		});
            			}
	            	});
	            	//$('#printPoolHManageInit_dataGrid').datagrid('beginEdit', index);
	            	//printPoolHManageInit_view(index,data);
				},
			onLoadSuccess:function(){
               $('#printPoolHManageInit_dataGrid').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
            } 
		});
		//设置分页控件	
		var p = $('#printPoolHManageInit_dataGrid').datagrid('getPager'); 
			$(p).pagination({           
				pageSize: 10,//每页显示的记录条数，默认为10           
				//pageList: [5,10,15,20],//可以设置每页记录条数的列表           
				beforePageText: '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
				afterPageText: '<spring:message code="pagination.afterPageText"/>',           
				displayMsg: '<spring:message code="pagination.displayMsg"/>',
				onSelectPage: function (pageNumber, pageSize) {//分页触发		
					 printPoolHManageInit_find(pageNumber,pageSize);
					 printPoolHManageInit_Search(); 
					 printPoolHManageInit_clearForm();
		         }
		});
		$('#printPoolHManageInit_printSubmit_dataGrid').datagrid({
				url: '',
				loadMsg:"<spring:message code='system.datagrid.dataloding.msg'/>",
				striped:true,//奇偶行颜色不同
				singleSelect:false, //多选
				collapsible:false,//可折叠  
				fitColumns: false,//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				nowrap: false,
				iconCls: 'icon icon-list',
				pagination:false, //显示分页 
				rownumbers:true, //显示行号
				idField:'id',//主键字段
				frozenColumns:[[
				{field:'id',hidden:true,width:80,align:'center'},
				{field:'ck',checkbox:true,width:80,align:'center'}
                ]],
                columns:[[
         					{field:'checkFlag',checkbox:true,width:80,align:'center'},
				        	{field:'invoiceReqTypeName',title:"来源",width:80,align:'center'},
		 					{field:'invoiceReqNumber',title:"开票申请单号",width:150,align:'center'},
		 					{field:'invoicePreNumber',title:"开票准备单号",width:150,align:'center'},
		 					{field:'orgName',title:"申请组织",width:80,align:'center'},
		 					{field:'invoicePrintStatusName',title:"发票状态",width:80,align:'center'},
		 					{field:'legalEntityName',title:"销方单位名称",width:100,align:'center'},
		 					//{field:'invoicePrintStatusName',title:"发票状态",width:100,align:'center'},
		 					{field:'invoiceCode',title:"发票代码",width:100,align:'center'},
		   					{field:'invoiceNumber',title:"发票号码",width:100,align:'center'},
		   					{field:'customerNumber',title:"购方客户编码",width:100,align:'center'},
		   					{field:'customerName',title:"购方单位名称",width:100,align:'center'},
		   					{field:'custRegistrationCode',title:"购方证件类型",width:100,align:'center'}, 
		   					{field:'custRegistrationNumber',title:"购方证件号码",width:100,align:'center'},
		   					{field:'invoiceCategoryName',title:"发票类型",width:100,align:'center'},
		   					{field:'acctdAmountCR',title:"金额",width:100,halign:'center',align:'right'},
		   					{field:'vatAmount',title:"税额",width:100,halign:'center',align:'right'},
		   					{field:'invoiceAmount',title:"合计金额",width:100,halign:'center',align:'right'},
		 					{field:'equipmentCode',title:"打印终端编号",align:'center',width:100},
		 					{field:'equipmentName',title:"打印终端名称",width:100,align:'center'},
		   					{field:'invoicePrintBy',title:"发票开具人",width:100,align:'center'},
		   					{field:'invoicePrintDate',title:"发票开具日期",width:120,align:'center',formatter:function(value,rec){
		   						if (typeof(value)!="undefined"&&value!=0&&value!=null){
										return new Date(value).format("yyyy-MM-dd");
									}
		  					}}
         			]],
		            onDblClickRow:function(index,data){
		            	//printPoolHManageInit_view(index,data);
					},
					onLoadSuccess:function(result){
	                $('#printPoolHManageInit_printSubmit_dataGrid').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
	                $.each(result.rows,function(idx,val){//遍历JSON
	                      if(val.checkFlag){
	                        $("#printPoolHManageInit_printSubmit_dataGrid").datagrid("checkRow", idx);//如果数据行为已选中则选中改行
	                      }
	                }); 
	            } 
			});
	}
	function printPoolHManageInit_find(pageNumber,pageSize){
		$('#printPoolHManageInit_searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		printPoolHManageInit_Search();
	}
	function printPoolHManageInit_Search(){
    	$("#printPoolHManageInit_dataGrid").datagrid("loading");
    	$('#printPoolHManageInit_searchform').form('submit', {
			url:'${vat}/invoicePrintPoolH/loadInvoicePrintPoolHPage.do',			
			onSubmit : function(){
				return $(this).form('enableValidation').form('validate');
			},
			success:function(result){
				var result = $.parseJSON(result);
				if(result.status=='0'){
				     $("#printPoolHManageInit_dataGrid").datagrid('loadData', result.data);
				}else if(result.status=='1'){
					$.messager.alert('<spring:message code="system.alert"/>',result.erroMessage,'info');
				}
				$("#printPoolHManageInit_dataGrid").datagrid("loaded"); 
		     }
		});
    }
	
	function InitCombobox(){
		init_common_combo_customer("#printPoolHManageInit_searchform_customerName_input");
		init_common_combo_salesentity("#printPoolHManageInit_searchform_legalEntityName_input");
        //init_common_combo_salesentity("#invoiceTrxPool_searchform_salesEntityName");
//发票状态初始化		
		/* $.ajax({  
	        url: "${vat}/invoicePrintPoolH/poolManagePrintStatus.do",  
	        type: "POST",  
	        async: true,  
	        data: '', //不能直接写成 {id:"123",code:"tomcat"}  
	        dataType: "json",  
	        // contentType: "charset=utf-8",  
	        cache: false,  
	        success: function (result){
	        	$('#printPoolHManageInit_searchform_invoicePrintStatus').combobox({
	        		data:result.data,
	        		idField:"value",
	 			   	textField:"text",
	        		onLoadSuccess:function(data){
	        			 if(data){
	        				$('#printPoolHManageInit_searchform_invoicePrintStatus').combobox('setValue',data[0].value);
	        			} 
	        		}
	        	});
	        } 
	    }); */
	}
    //发票集中打印
    function printPoolHManageInit_printSubmit(){
    	printPoolHManageInit_clearForm();
		var row_list=$('#printPoolHManageInit_dataGrid').datagrid('getChecked');
		//var row=$('#customerManageInit_dataGrid').datagrid('getSelected');
		if(row_list!=''&&row_list!='undefined'){
			if(row_list.length<1){  
                $.messager.alert('提示信息',"请选择你要开具发票的记录",'info');  
                return false;  
            } 
			$.messager.confirm("提示信息","确认提交？",function(result){ 
				if (result){
					var urlString="";
					$.each(row_list,function(index,item){ 
		                   if(index==0)   
		                	   urlString += "id="+item.id;  
		                    else  
		                    	urlString += "&id="+item.id;  
		                });
		        	if(urlString!=''){
		        		$.messager.progress({title:'Please waiting',
		 					msg:'数据加载中'});
		        		$.ajax({  
		        	        url: "${vat}/invoicePrintPoolH/loadInvoicePrintPoolHSubmitList.do",  
		        	        type: "POST",  
		        	        async: true,  
		        	        data: urlString, //不能直接写成 {id:"123",code:"tomcat"}  
		        	        dataType: "json",  
		        	        cache: false,  
		        	        success: function(result){
		        	        	$.messager.progress('close');
		        	        	if(result.status=='0'){
		        	        		$('#printPoolHManageInit_printSubmit_print_buttons').linkbutton("enable");
		        	        		$("#printPoolHManageInit_printSubmit_dlg").dialog('open').dialog('setTitle',
		        	        		"发票开具接口调用");
		        	        		$("#printPoolHManageInit_printSubmit_dataGrid").datagrid('loadData',result.data);
		        	        		/* $('#printPoolHManageInit_printSubmit_editform').form('load', {
		        	        			taxIdStart : result.data.taxIdStart
		        	        		}); */
		        	        		//$("#printPoolHManageInit_editform").form('load', {custRegistrationDate:myformatter(new Date())});
		        	        		//$.messager.alert('操作结果','数据传送功能正在开发中');
		        	        	}else{
		        	        		$.messager.alert('<spring:message code="system.alert"/>',result.erroMessage);
		        	        	}
		        	        	/* printPoolHManageInit_Search();
		        	        	printPoolHManageInit_clearForm(); */
		        	        } 
		        	    });
		        	}
				}
	        });
        }else{
        	$.messager.alert('提示',"请选择发票",'info'); 
        }
	} 
    
    //发票明细查询
    
    function printPoolHManageInit_view(){
    	var row_list=$('#printPoolHManageInit_dataGrid').datagrid('getChecked');
    	if(row_list!=''&&row_list!='undefined'){
    		if(row_list.length==0){  
                $.messager.alert('提示',"请选择你要查看的记录",'info');  
                return false;  
            } 
    		if(row_list.length > 1){  
               $.messager.alert('提示',"请选择一条记录",'info');  
               return false;  
            } 
    		var id='';
    		 $.each(row_list,function(index,item){
     			if(index==0){
     				id = item.id;
     			}
    		});
    		 if(id!=''){
     			 $.messager.progress({title:'Please waiting',
 					msg:'数据加载中'}); 
     			//$("#printPoolHManageInit_details_dataGrid").datagrid("loading");
     			var urlString='invoicePrtPoolHId='+id;
     			$.ajax({  
     		        //url: "${vat}/invoicePrintPoolH/loadInvoicePrintPoolL.do",
     		        url: "${vat}/invoicePrintPoolH/loadInvoicePrintPoolHDetail.do",
     		        type: "POST",  
     		        async: true,  
     		        data: urlString, //不能直接写成 {id:"123",code:"tomcat"}  
     		        dataType: "json",  
     		        cache: false,  
     		        success: function(result){
     		        	//$("#printPoolHManageInit_details_dataGrid").datagrid("loaded"); 
     		        	 $.messager.progress('close');
     		        	if(result.status=='0'){
     		        		$('#layoutid').layout('expand', 'east');
     		        		$("#printPoolHManageInit_details_searchform").form('load',result.data);
     		        		$("#printPoolHManageInit_details_searchform").form('load',{invoicePrintDate:function(){
     			        			var value=result.data.invoicePrintDate;
     			        			if (typeof(value)!="undefined"&&value!=0&&value!=null){
     									return new Date(value).format("yyyy-MM-dd");
     								}
     		        			}
     		        		});
     		        		//$("#printPoolHManageInit_details_searchform").form('load',result.data);
     		        		$('#printPoolHManageInit_details_dataGrid').datagrid('loadData',result.data.printPoolLInParamList);
     		        	}else{
     		        		//$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="system.delete.fail.text"/>');
     		        	}
     		        } 
     		    });
    		 }
    		 
    	}
    	
    }
    	//发票明细信息列表查看
    function printPoolHManageInit_poolD_view(index,data){
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
    	        		$("#printPoolHManageInit_poolDDetail_dlg").dialog('open').dialog('setTitle',
    	        		"明细查看");
    	        		$("#printPoolHManageInit_poolDDetail_dataGrid").datagrid('loadData',result.data.invoicePrintPoolDList);
    	        		//$("#printPoolHManageInit_details_searchform").form('load',result.data);
    	        		//$('#printPoolHManageInit_details_dataGrid').datagrid('loadData',result.data.invoicePrintPoolLInParamList);
    	        	}else{
    	        		//$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="system.delete.fail.text"/>');
    	        	}
    	        } 
    	    });
    }
    
	function printPoolHManageInit_printSubmit_confirm_new(){
    	var row_list=$('#printPoolHManageInit_dataGrid').datagrid('getChecked');
    	
    	if(row_list==''||row_list=='undefined'){
    		if(row_list.length<1){  
                $.messager.alert('提示',"请选择你要打印的发票");  
                return false;  
            }
    	}
    	//批量打印判断
    	var invoiceCode="";
    	var check_value="";
    	var urlString="";
    	$.each(row_list,function(index,item){
 			if(item.invoicePrintStatus!='20'){
 				check_value=check_value+index;
 			}else{
 				if(invoiceCode==""){
 	 				invoiceCode=item.invoiceCode;
 	 			}else{
 	 				invoiceCode=invoiceCode+","+item.invoiceCode;
 	 			}
 				if(urlString==''){
             	   urlString += "id="+item.id;  
                }else {
             	   urlString += "&id="+item.id;
                }
 			}
		});
    	if(check_value!=''){
    		$.messager.alert('提示',"存在待开具发票，不能进行批量打印",'info');
    	}else{
    		$.messager.confirm("提示","即将打印的发票代码为："+invoiceCode+'请确认纸质发票是否放置！',function(result){
    			if(result){
    				$.ajax({  
    			        url: "${vat}/invoicePrintPoolH/loadInvoicePrintPoolHPrintSubmit.do",  
    			        type: "POST",  
    			        async: true,  
    			        data: urlString, //不能直接写成 {id:"123",code:"tomcat"}  
    			        dataType: "json",  
    			        cache: false,  
    			        success: function(result){
    			        	if(result.status=='0'){
    			        		//$.messager.alert('提示','发送税控系统成功操作成功');
    			        		$('#printPoolHManageInit_printSubmit_dlg').dialog('close');
    			        		printPoolHManageInit_Search();
    			        	}else{
    			        		$.messager.alert('<spring:message code="system.alert"/>',result.erroMessage);
    			        	}
    			        	printPoolHManageInit_Search();
	        	        	printPoolHManageInit_clearForm();
    			        } 
    			    });
    			}
    		});
    	}
    		
    }
    function printPoolHManageInit_printSubmit_confirm(){
    	var row_list=$('#printPoolHManageInit_printSubmit_dataGrid').datagrid('getChecked');
    	if(row_list==''||row_list=='undefined'){
    		if(row_list.length<1){  
                $.messager.alert('提示',"请选择你要打印的发票");  
                return false;  
            }
    	}
    	//批量打印判断
    	var invoiceCode="";
    	var check_value="";
    	var urlString="";
    	$.each(row_list,function(index,item){
 			if(item.invoicePrintStatus!='20'){
 				check_value=check_value+index;
 			}else{
 				if(invoiceCode==""){
 	 				invoiceCode=item.invoiceCode;
 	 			}else{
 	 				invoiceCode=invoiceCode+","+item.invoiceCode;
 	 			}
 				if(urlString==''){
 					urlString += "id="+item.id;
 				}else {
 					urlString += "&id="+item.id;
 				} 
 			}
		});
    	
    	if(check_value!=''){
    		$.messager.alert('提示',"存在待开具发票，不能进行批量打印",'info');
    		return false;
    	}else{
    		$.messager.confirm("提示","即将打印的发票代码为："+invoiceCode+'请确认纸质发票是否放置！',function(result){
    			if(result){
    				$('#printPoolHManageInit_printSubmit_print_buttons').linkbutton("disable");
    				$.ajax({  
    			        url: "${vat}/invoicePrintPoolH/loadInvoicePrintPoolHPrintSubmit.do",  
    			        type: "POST",  
    			        async: true,  
    			        data: urlString, //不能直接写成 {id:"123",code:"tomcat"}  
    			        dataType: "json",  
    			        cache: false,  
    			        success: function(result){
    			        	if(result.status=='0'){
    			        		$.messager.alert('提示','发票打印成功');
    			        		$('#printPoolHManageInit_printSubmit_dlg').dialog('close');
    			        		printPoolHManageInit_Search();
    			        		//$('#printPoolHManageInit_details_dataGrid').datagrid('loadData',result.data);
    			        	}else{
    			        		$.messager.alert('<spring:message code="system.alert"/>',result.erroMessage);
    			        	}
    			        } 
    			    });
    			}
    		});
    	}
    		
    }
    
	function printPoolHManageInit_clearForm(){
		$('#printPoolHManageInit_editform').form('clear');
	}
	function printPoolHmanageInit_clearSearchForm(){
		$('#printPoolHManageInit_searchform').form('reset');
		$('#printPoolHManageInit_searchform').form('load', {
			pageNumber: $('#printPoolHManageInit_dataGrid').datagrid('options').pageNumber,
			pageSize: $('#printPoolHManageInit_dataGrid').datagrid('options').pageSize
		});
	}

	
	
	
</script>
</html>