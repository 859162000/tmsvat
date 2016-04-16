<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
	<div data-options="region:'center'" title="" 
		style="width:100%">
		<div class="easyui-panel" border="false"
			style="width: 100%; height: 100%; padding: 0px;overflow: hidden;">
			<div class="easyui-layout" style="width: 100%; height: 100%;overflow: hidden;">
				<div region="north" split="true" border="false"
						style="width: 100%; height: 20%;">
						<div class="easyui-panel"
							title="<spring:message code="searchgroup"/>"
							 style=" height: 100%; background-color: #E0ECFF ;">
								<form id="customerManageInit_searchform" method="post"  scroll="no">
									<table    >
								    	<tr style="padding:5px">
						    				<td align="right" >客户名称:</td>
							    			<td  >
							    				<input id="customerManageInit_searchform_customerName" name="customerName" class="easyui-textbox"  style="width:150px;" />
							    			</td>
							    			<td align="right">纳税人类型:</td>
							    			<td>
							    				<input:select  easyuiStyle="width:150px;" id="customerManageInit_searchform_custLegalEntityType" name="custLegalEntityType" value="$customerManageInit_searchform_custLegalEntityType" easyuiClass="easyui-combobox">
            										<option  value="">不限</option>
            										<input:systemStatusOption parentCode="BASE_LEGAL_ENTITY_TYPE"/>
												</input:select>
							    			</td>

							    			<td align="right">是否预约:</td>
							    			<td>
							    				<input:select easyuiStyle="width:150px;" id="customerManageInit_searchform_isAppointInvoice" name="isAppointInvoice" value="$customerManageInit_searchform_isAppointInvoice" easyuiClass="easyui-combobox" >
            										<option  value="">不限</option>
            										<input:systemStatusOption parentCode="VAT_IS_APPOINT_ISSUE_INVOICE"/>
												</input:select>
							    			</td>
							    		
							    			<td align="right">是否有效:</td>
							    			<td >
							    				<input:select easyuiStyle="width:150px;" id="customerManageInit_searchform_enabled" name="enabledFlag" value="$customerManageInit_searchform_enabled" easyuiClass="easyui-combobox" >
            										<option  value="">不限</option>
            										<input:systemStatusOption parentCode="BASE_IS_VALID"/>
												</input:select>
							    			</td>
							    		</tr>
							    		</table>
							    		<table>
							    		<tr style="padding:5px">
							    			<td align="right">证件类型:</td>
							    			<td >
							    				<input id="customerManageInit_searchform_discOption" name="discOption" class="easyui-combobox" style="width: 150px;" />
							    				
							    				<%-- <input:select  easyuiStyle="width:150px;" id="customerManageInit_searchform_disc_option" name="disc_option" value="$customerManageInit_searchform_disc_option" easyuiClass="easyui-combobox" >
            										<input:systemStatusOption parentCode="VAT_CUSTOMER_DISC_OPTION"/>
												</input:select> --%>
							    			</td>
							    			
							    			<td></td>
							    			<td align="right" id="customerManageInit_searchform_customerEntityNumText" style="display: none;">纳税人识别号:</td>
							    			<td>
							    				<input class="easyui-textbox" type="text"  style='width:150px'name="searchNumber" />
							    			</td>
							    			<td colspan="3">
							    	            <a href="#" id="searchbtn"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px" onclick="customerManageInit_Search()"><spring:message code='client.search.button.find'/></a>
							                   	<a href="#" id="researchbtn"  class="easyui-linkbutton" data-options="" style="width:80px" onclick="customerManageInit_clearSearchForm()">重置</a>
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
						<table class="easyui-datagrid" id="customerManageInit_dataGrid"
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
	</div>
	
	
	
	
    <div data-options="region:'center',border:false" style="background-color: #E0ECFF">
	   <div id="customerManageInit_add_dlg" class="easyui-dialog"
			closed="true"
			buttons="#customerManageInit_add_dlg-buttons" modal="true"  maximized='true'>

			<div class="easyui-panel" border="false"
				style="width: 100%; min-height: 800px; padding: 0px;">
				<form id="customerManageInit_editform" class="easyui-form" method="post" data-options="novalidate:true" commandsName="billBookManageForm">
					<input name="id" type="hidden"/>
					<table>
		    			<tr >		    					    			
		    				<td align='right'><font color='red'></font>客户编码:</td>
		    				<td><input name="customerNumber" style="width:150px;"class="easyui-textbox" required="true" /></td>
		    				<td align='right'><font color='red'></font>客户名称:</td>
		    				<td><input name="customerName" style="width:150px;" class="easyui-textbox" required="true" /></td>
		    				<td align='right'><font color='red'></font>客户类型:</td>
		    				<td>
		    				<input:select easyuiStyle="width:150px; required:true" id="customerManageInit_editform_customerType"  name="customerType" value="$customerManageInit_editform_customerType" easyuiClass="easyui-combobox" >
            						<input:systemStatusOption parentCode="BASE_CUSTOMER_CATEGORY"/>
							</input:select>
		    				</td>		    				
		    					<td align='right'><font color='red'></font>纳税人类型:</td>
			    				<td>			    	 
			    					<input:select easyuiStyle="width:150px;" id="customerManageInit_editform_custLegalEntityType"  name="custLegalEntityType" value="$customerManageInit_editform_custLegalEntityType" easyuiClass="easyui-combobox" 
			    					>			    					
            						<input:systemStatusOption  parentCode="BASE_LEGAL_ENTITY_TYPE" />
								</input:select>
			    				</td>		    				    				
				    	    </tr>
				    		<tr>
				
			    				<td align='right' class="custRegistrationNumber_change_two"><font color='red'></font>开票使用证件类型:</td>
			    				<td class="custRegistrationNumber_change_two">
			    					<input:select easyuiStyle="width:150px;" id="customerManageInit_editform_custRegistrationCode" name="custRegistrationCode" value="$customerManageInit_editform_custRegistrationCode" easyuiClass="easyui-combobox" >
            							<input:systemStatusOption parentCode="VAT_CR_INVOICE_ISSUE_CODE_TYPE"/>
									</input:select>
								</td>			    							    				
			    				<td align='right' class="custRegistrationNumber_change_two" >纳税人识别号:</td>
			    				<td class="custRegistrationNumber_change_two" ><input id="custRegistrationNumberId" type="text" name="custRegistrationNumber" style="width:150px;" class="easyui-textbox"/></td>			    							    	
			    				<td align='right' class="custRegistrationNumber_change_two">统一社会信用代码:</td>
			    				<td class="custRegistrationNumber_change_two"><input id="gsnRegistrationNumber_id" name="gsnRegistrationNumber"style="width:150px;"class="easyui-textbox"/></td>			    							    				  		
				    		<td align='right'><font color='red'></font>是否有效:</td>
		    				<td>		    					    		
		    					<input:select easyuiStyle="width:150px;required:true" id="customerManageInit_editform_enabledFlag" name="enabledFlag" value="$customerManageInit_editform_enabledFlag" easyuiClass="easyui-combobox" >           						 
            						<input:systemStatusOption parentCode="BASE_IS_VALID"/>				
								</input:select>    
		    				</td>
	
				    		</tr>
				    		<tr >
				    			<td align='right'><font color='red'></font>开户银行:</td>
			    				<td><input id="custDepositBankName_id" name="custDepositBankName" style="width:150px;"class="easyui-textbox" /></td>
			    				<td align='right'><font color='red'></font>开户账号:</td>
			    				<td><input id="custDepositBankAccountNum_id" name="custDepositBankAccountNum" style="width:150px;"class="easyui-textbox" /></td>
			    				<td align='right'><font color='red'></font>联系人:</td>
				    			<td><input id="contactName_id" name="contactName" style="width:150px;"class="easyui-textbox"  /></td>
			    				
			    				<!-- 原本是联系人电话  数据库字段和公司电话一样 故取公司电话      <td align='right'>联系电话:</td> -->
			    				<td align='right'>公司电话:</td>
			    				<td><input id="contactPhone_id" name="contactPhone" style="width:150px;"class="easyui-textbox" /></td>
				    		</tr>
				    		<tr>				    		
				    		  
				    		  <!-- 
				    		   <td align='right'><font color='red'></font>公司电话:</td>
				    			<td><input id="contactPhone_id_two" name="contactPhone_two" style="width:150px;"class="easyui-textbox"  /></td>
			    				 -->
			    				 
			    				<td align='right'><font color='red'></font>公司地址:</td>
			    				<td><input id="custRegistrationAddress_id" name="custRegistrationAddress" style="width:150px;"class="easyui-textbox" /></td>				    	
				    			<td align='right' style="display: none;"><font color='red'></font>是否开具发票:</td>				    							  				    							    				    	
				    			<td  style="display: none;">
				    				<input:select easyuiStyle="width:150px;"  id="customerManageInit_editform_isInvoiceProviding" name="isInvoiceProviding" value="$customerManageInit_editform_isInvoiceProviding" easyuiClass="easyui-combobox" >
           							<input:systemStatusOption parentCode="VAT_IS_ISSUE_INVOICE"/>
									</input:select>
				    			</td>
	
			    				<td align='right'><font color='red'></font>是否预约开票:</td>
			    				<td>
			    					<input:select easyuiStyle="width:150px;"  id="customerManageInit_editform_isAppointInvoice" name="isAppointInvoice" value="$customerManageInit_editform_isAppointInvoice" easyuiClass="easyui-combobox" >
            							<input:systemStatusOption parentCode="VAT_IS_APPOINT_ISSUE_INVOICE"/>
									</input:select>
			    				</td>
			    					<td align='right'>客户经纪关系:</td>
							<td><select id="TmsMdFlexStructuresListInSendForm"
								name="flexStructuresDescription" class="easyui-combogrid"
								style="width: 150px; " >					
								</select></td>										
						   </td>			    				
				    	</tr>			
		    			<tr >
		    				<td align='right'>预约开票间隔单位:</td>
		    				<td>
		    					<input:select easyuiStyle="width:150px;" id="customerManageInit_editform_billingPeriod" name="billingPeriod" value="$customerManageInit_editform_billingPeriod" easyuiClass="easyui-combobox" >
            						<input:systemStatusOption parentCode="VAT_CR_INVOICE_PERIOD"/>
								</input:select>
		    				</td>
		    					<td align='right'>运行间隔:</td>
		    				<td>
		    					<input id="appointRunInterval_id" name="appointRunInterval" style="width:150px;" class="easyui-numberspinner" data-options="min:1,max:100000"/>
		    				</td>
		    				
		    				<td align='right'>预约开票管理组织:</td>
		    				
		    					<td>
		    					<input:select easyuiStyle="width:150px;" id="appointInvoiceOrgCode_id" name="appointInvoiceOrgCode" value="$customerManageInit_editform_billingPeriod" easyuiClass="easyui-combobox" >
            						
								</input:select>
		    				</td>
		    				<!-- 
		    				<td><input id="appointInvoiceOrgCode_id" name="appointInvoiceOrgCode"style="width:150px;" class="easyui-textbox"/></td>
				    	    -->
				    	</tr>
		    	</table>
	
		    	
		    	<table  class="easyui-datagrid" id="customerManageInit_editform_dataGrid"
								title="账户"
								style="width: 98%; min-height:300px"
								data-options="					
									singleSelect:true,
									autoRowHeight:false,
									pagination:false,
									pageSize:10,
									remoteSort:false,
								    multiSort:true
										">
			</table>
			<table class="easyui-datagrid" id="customerManageInit_editform_address_dataGrid"
								title="地址"
								style="width: 98%;min-height:300px"
								data-options="					
									singleSelect:true,
									autoRowHeight:false,
									pagination:false,
									pageSize:10,
									remoteSort:false,
								    multiSort:true	
										">
				</table>
		    </form>		    
			</div>	
						
		</div>
		<div id="customerManageInit_add_dlg-buttons">
			<a  href="javascript:void(0)" class="easyui-linkbutton"	iconCls="icon-ok" onclick="changeSign()">
					<spring:message	code="button.Save" />
			</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#customerManageInit_add_dlg').dialog('close')"><spring:message
					code="button.Close" />
			</a>
		</div>
    </div>
  </body>  
<script type="text/javascript">


	$(document).ready(function(){
		pageDataSettingInit();
		InitCombobox();	
		$('#customerManageInit_searchform').form('load', {
			pageNumber: $('#customerManageInit_dataGrid').datagrid('options').pageNumber,
			pageSize: $('#customerManageInit_dataGrid').datagrid('options').pageSize
		});
	//	noEditable();
		customerManageInit_Search();
		chooseToAppear();		
		initTaxEntity();
	});
	
	var sign;
	var signValue;
	//页面表格绑定初始化
	function pageDataSettingInit(){
		$("#customerManageInit_add_dlg").dialog({onClose:function(){
			//alert(12121);
		}});

		$('#customerManageInit_dataGrid').datagrid({
			url: '',
			loadMsg:"<spring:message code='system.datagrid.dataloding.msg'/>",
			striped:true,//奇偶行颜色不同
			singleSelect:false, //多选
			collapsible:false,//可折叠  
			fitColumns: true,//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			nowrap: true,
			iconCls: 'icon icon-list',
			pagination:true, //显示分页 
			rownumbers:true, //显示行号
		
		
			idField:'id',//主键字段
			frozenColumns:[[
							{field:'checkFlag',checkbox:true,width:80,align:'center'},
							{field:'id',hidden:true,width:80,align:'center'}
			                ]],
           	columns:[[
     					{field:'customerNumber',title:"客户编码",width:80,align:'center'},
     					{field:'customerName',title:"客户名称",width:80,align:'center'},
     					{field:'customerTypeName',title:"客户类型",width:50,align:'center'},
     					{field:'custLegalEntityTypeName',title:"纳税人类型",width:80,align:'center'},
     					//{field:'isInvoiceProvidingName',title:"是否开票",width:80,align:'center'},
     					{field:'custDepositBankName',title:"开户银行",width:80,align:'center'},
     					{field:'custDepositBankAccountNum',title:"开户账号",width:80,align:'center'},
     					{field:'custRegistrationAddress',title:"公司地址",width:80,align:'center'},
     					{field:'contactPhone',title:"公司电话",width:80,align:'center'},
     			]],
			toolbar:[
			         
			         {text:"导入客户信息",  
                	iconCls:'icon-add',  
                	handler:function(){  
                			
                			}  
            		},'-',{text:"新增客户",  
                	iconCls:'icon-add',  
                	handler:function(){  
                			customerManageInit_Add();  
                			}  
            		},'-',{  
            			text:"编辑客户",  
	                iconCls:'icon-edit',  
	                handler:function(){  
	                	customerManageInit_Edit();
	                }  
	            },'-',{  
	            	text:"删除客户",  
	                iconCls:'icon-remove',  
	                handler:function(){  
	                	customerManageInit_Remove();  
	                }  
	            },'-'],
	            onDblClickRow:function(index,data){
	            	
			},
			onLoadSuccess:function(){  
                $('#customerManageInit_dataGrid').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
            } 
		});
		//设置分页控件	
		var p = $('#customerManageInit_dataGrid').datagrid('getPager'); 
			$(p).pagination({           
				pageSize: 10,//每页显示的记录条数，默认为10           
				//pageList: [5,10,15,20],//可以设置每页记录条数的列表           
				beforePageText: '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
				afterPageText: '<spring:message code="pagination.afterPageText"/>',           
				displayMsg: '<spring:message code="pagination.displayMsg"/>',
				onSelectPage: function (pageNumber, pageSize) {//分页触发		
					 customerManageInit_find(pageNumber,pageSize);
					 customerManageInit_Search(); 
					 customerManageInit_clearForm();
		         }
		});
	}
	function customerManageInit_find(pageNumber,pageSize){
		$('#customerManageInit_searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		customerManageInit_Search();
	}
	function customerManageInit_Search(){
    	$("#customerManageInit_dataGrid").datagrid("loading");
    	$('#customerManageInit_searchform').form('submit', {
			url:'${vat}/customer/loadCustomerPageList.do',			
			onSubmit : function(){
				return $(this).form('enableValidation').form('validate');
			},
			success:function(result){
				var result = $.parseJSON(result);
				if(result.status=='0'){
				     $("#customerManageInit_dataGrid").datagrid('loadData', result.data);
				}else if(result.status=='1'){
					$.messager.alert('<spring:message code="system.alert"/>',result.erroMessage,'info');
				}
				$("#customerManageInit_dataGrid").datagrid("loaded"); 
		     }
		});
    }
	
	function InitCombobox(){
		$('#customerManageInit_searchform_discOption').combobox({
			url: "${vat}/customer/getDicEntityByParentCode.do?parentCode=VAT_CUSTOMER_DISC_OPTION", 
			valueField:"code",
    		method:'GET',
    		editable:true,
			textField:"name",
    		onLoadSuccess:function(data){
    			if(data){
    			//	$('#customerManageInit_searchform_discOption').combobox('setValue',data[0].code);
    				var temp=$('#customerManageInit_searchform_discOption').combobox('getText');
    				temp=temp+':';
    				$('#customerManageInit_searchform').find('#customerManageInit_searchform_customerEntityNumText').html(temp);
    			} 
    		},
    		onChange:function(n,o){
    			var temp=$('#customerManageInit_searchform_discOption').combobox('getText');
    			temp=temp+':';
    			$('#customerManageInit_searchform').find('#customerManageInit_searchform_customerEntityNumText').html(temp);
    		}
    	});
		
	}
    //客户关系添加
    function customerManageInit_Add(){
    	
    	$('#customerManageInit_editform').form('clear');
    	$('#customerManageInit_editform_dataGrid').datagrid('loadData', { total: 0, rows: [] });
    	$('#customerManageInit_editform_address_dataGrid').datagrid('loadData', { total: 0, rows: [] });
		$("#customerManageInit_add_dlg").dialog('open').dialog('setTitle',
		"新增客户信息");
		$("#customerManageInit_editform").form('load', {custRegistrationDate:myformatter(new Date())});
		$('#customerManageInit_editform_enabledFlag').combobox('setValue',"是");
		$('#customerManageInit_editform_isInvoiceProviding').combobox('setValue',"是");
		addDataGrid();
		 customerManageInit_Save();
	    
		
	} 
    function customerManageInit_Edit(){
    	
    	editDataGrid(); 
    //	customerManageInit_clearForm();
    	var row_list=$('#customerManageInit_dataGrid').datagrid('getChecked');
    	if(row_list!=''&&row_list!='undefined'){
    		if(row_list.length==0){  
                $.messager.alert('提示',"请选择你要更新的记录",'info');  
                return false;  
            } 
    		if(row_list.length > 1){  
               $.messager.alert('提示',"只能选择一条记录进行更新",'info');  
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
    			$.post('${vat}/customer/loadModifyCustomer.do',
            			{id:id}, 
            			function(result) {
            				$.messager.progress('close');
            				if(result.status=='0'){
            			    	$('#customerManageInit_editform_dataGrid').datagrid('loadData', result.data.accoutlist);
            			    	$('#customerManageInit_editform_address_dataGrid').datagrid('loadData', result.data.siteList);
            			    	
            	        		$("#customerManageInit_add_dlg").dialog('open').dialog('setTitle',
    								"<spring:message code='client.dialog.clientedit.title'/>");
            	        		 $("#customerManageInit_editform").form('load', 
            	        				{
            	        			 id:result.data.id,
             	        			customerName:result.data.customerName,
             	        			customerNumber:result.data.customerNumber,
             	        			customerType:result.data.customerType,
             	        			enabledFlag:result.data.enabledFlag,
             	        			custLegalEntityType:result.data.custLegalEntityType,
             	        			custRegistrationCode:result.data.custRegistrationCode,
             	        			custRegistrationNumber:result.data.custRegistrationNumber,
             	        			gsnRegistrationNumber:result.data.gsnRegistrationNumber,
             	        			custDepositBankName:result.data.custDepositBankName,
             	        			custDepositBankAccountNum:result.data.custDepositBankAccountNum,
             	        			contactName:result.data.contactName,
             	        			contactPhone:result.data.contactPhone,
             	        			custRegistrationAddress:result.data.custRegistrationAddress,
             	        			isInvoiceProviding:result.data.isInvoiceProviding,
									 isAppointInvoice:result.data.isAppointInvoice,
									 billingPeriod:result.data.billingPeriod,
									 appointRunInterval:result.data.appointRunInterval,
									 appointInvoiceOrgCode:result.data.appointInvoiceOrgCode,
            	        				}
            	        		); 
            	        		
            	        		/* $(".customerManageInit_customerType").combobox('setValue', result.data.customerType);
            	        		$(".customerManageInit_legalEntityType").combobox('setValue', result.data.legalEntityType);
            	        		$(".customerManageInit_isAppointInvoice").combobox('setValue', result.data.isAppointInvoice);
            	        		$(".customerManageInit_isInvoiceProviding").combobox('setValue', result.data.isInvoiceProviding);
            	        		$(".customerManageInit_bizOrgCode").combobox('setValue', result.data.bizOrgCode);
            	        		$(".customerManageInit_bankAccountType").combobox('setValue', result.data.bankAccountType);
            	        		$(".customerManageInit_enabled").combobox('setValue', result.data.enabled); */
            	        		
    							//$("#customerManageInit_editform").form('load', result.data);
            	        	}else{
            	        		$.messager.alert('<spring:message code="system.alert"/>',result.errorMessage);
        						customerManageInit_Search();
        						customerManageInit_clearForm();
        						$('#customerManageInit_add_dlg').dialog('close');
            	        	}
    					},
    					'json');
        	}else{
        		$.messager.alert('<spring:message code="system.alert"/>','编辑的主键不能为空');
        	}
    	}
    }
	function customerManageInit_Save(){
		//账户信息
		var submit_editform_dataGrid=$('#customerManageInit_editform_dataGrid');
			$(submit_editform_dataGrid).datagrid('acceptChanges');
		var rows_data=$(submit_editform_dataGrid).datagrid('getData');			
		var submit_editform_address_grid=$('#customerManageInit_editform_address_dataGrid');
		$(submit_editform_address_grid).datagrid('acceptChanges');
		var rows_address_data=$(submit_editform_address_grid).datagrid('getData');

		var submit_form_container=$('#customerManageInit_editform');
		if(submit_form_container.form('enableValidation').form('validate')){
			$.messager.confirm("提示","确认提交？",function(result){
				if (result){
					 $(submit_form_container).form('submit',{
						url:'${vat}/customer/saveCustomer.do',	
						onSubmit : function(param) {										
							param.accoutlist_temp=JSON.stringify(rows_data.rows);
							param.addressList_temp=JSON.stringify(rows_address_data.rows);
								$.messager.progress({title:'Please waiting',
									msg:'数据保存中。。。。'});
								//alert(param.accoutlist);
						},			
						success:function(result){
							$.messager.progress('close');
							var result = $.parseJSON(result);
							if(result.status=='0'){	
								customerManageInit_clearForm();
								customerManageInit_Search();
								$('#customerManageInit_add_dlg').dialog('close');
								$.messager.alert('<spring:message code="system.alert"/>','保存成功');
							}
							
						} 
					}); 
				}
			});
		}
	}
	//删除一条记录
	
	function customerManageInit_Remove(){
		customerManageInit_clearForm();
		var row_list=$('#customerManageInit_dataGrid').datagrid('getChecked');
		//var row=$('#customerManageInit_dataGrid').datagrid('getSelected');
		if(row_list!=''&&row_list!='undefined'){
			if(row_list.length<1){  
                $.messager.alert('提示',"请选择你要删除的记录",'info');  
                return false;  
            } 
			$.messager.confirm("<spring:message code='client.datacommit.delete.confirm.title'/>","<spring:message code='client.datacommit.delete.confirm.text'/>",function(result){ 
				if (result){
					var urlString="";
					$.each(row_list,function(index,item){ 
		                   if(index==0)   
		                	   urlString += "id="+item.id;  
		                    else  
		                    	urlString += "&id="+item.id;  
		                });
		        	if(urlString!=''){
		        		$.ajax({  
		        	        url: "${vat}/customer/removeCustomers.do",  
		        	        type: "POST",  
		        	        async: true,  
		        	        data: urlString, //不能直接写成 {id:"123",code:"tomcat"}  
		        	        dataType: "json",  
		        	        cache: false,  
		        	        success: function(result){
		        	        	if(result.status=='0'){
		        	        		$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="system.delete.success.text"/>');
		        	        	}else{
		        	        		$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="system.delete.fail.text"/>');
		        	        	}
		        	        	customerManageInit_Search();
		    					customerManageInit_clearForm();
		        	        } 
		        	    });
		        	}
				}
	        });
        }else{
        	$.messager.alert('提示',"请选择你要删除的记录",'info'); 
        }
	}
	//清空dialog内容
	
	function customerManageInit_clearForm(){
		$('#customerManageInit_editform').form('clear');
	}
	function customerManageInit_clearSearchForm(){
		$('#customerManageInit_searchform').form('reset');
		$('#customerManageInit_searchform').form('load', {
			pageNumber: $('#customerManageInit_dataGrid').datagrid('options').pageNumber,
			pageSize: $('#customerManageInit_dataGrid').datagrid('options').pageSize
		});
		$('#customerManageInit_searchform_discOption').combobox().reload();
	}
	
	
	
     //编辑页面生成
	function editDataGrid(){
	$('#customerManageInit_editform_dataGrid').datagrid({
		url: '',
		loadMsg:"<spring:message code='system.datagrid.dataloding.msg'/>",
		striped:true,//奇偶行颜色不同
		singleSelect:false, //多选
		collapsible:true,//可折叠  
		fitColumns: true,//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		nowrap: true,
		iconCls: 'icon icon-list',
		pagination:false, //显示分页 
		rownumbers:true, //显示行号
		idField:'id',//主键字段
       	columns:[[	{field:'checkFlag',checkbox:true,width:80,align:'center'},
       	     	    {field:'id',title:"账户ID",width:80,hidden:true,editor: { type: 'text'} ,align:'center'},
 					{field:'custBankAccountNum',title:"账户号码",width:80,editor: { type: 'text', options: { required: true  } } ,align:'center'},
 					{field:'custBankName',title:"账户名称",width:80,editor: { type: 'text', options: { required: true } } ,align:'center'},
 					{field:'custBankNumber',title:"账户代码",width:80,align:'center',editor: { type: 'text', options: { url:'${vat}/customer/getEnabledStatus.do',
						valueField:'value',
						textField:'text',
						panelHeight:'auto',
						required:true } } },
 					
 					{field:'enabledFlag',title:"是否有效(Y/N)",width:80,align:'center',hidden:true,editor:{
						type:'combobox',
						options:{
							url:'',
							valueField:'value',
							textField:'text',
							panelHeight:'auto',
							    
						
						}
					}},
					
 					{field:'startDate',title:"有效日期",width:80 ,align:'center',formatter:function(value){
	     						if (typeof (value) != "undefined" && value != 0) {
	     						
	     							return myformatter(new Date(value));
	     						}
 							},editor: { type: 'datebox', options: { required: true,showSeconds:false } }
					},
					{field:'endDate',title:"失效日期",width:80 ,align:'center',
						formatter:function(value,rec){
									if (typeof (value) != "undefined" && value != 0) {
									
									return new Date(value).format("yyyy-MM-dd hh:mm:ss");
 										}
									
							},editor:{type:'datebox',options:{showSeconds:false},formatter:function(value){
 								if (typeof(value)!="undefined"&&value!=0){
    								return new Date(value).format("yyyy-MM-dd hh:mm:ss");
     							}
							}
							}
						}
					
 			]],
		toolbar:[
		         {text:"新增",  
            	iconCls:'icon-add',  
            	handler:function(){
            		var editRow=undefined;
	                		var datagrid_container=$("#customerManageInit_editform_dataGrid");
	                		if (editRow != undefined) {
	                            $(datagrid_container).datagrid('endEdit', editRow);
	                        }
	                        if (editRow == undefined) {
	                            $(datagrid_container).datagrid('insertRow', {
	                                index: 0,
	                                row: {
	                                	enabledFlag: "是"                              
	                                	}
	                            });
	                            $(datagrid_container).datagrid('beginEdit', 0);
	                            editRow = 0;
	                        } 
            			}  
        		},'-',{  
            	text:"删除",  
                iconCls:'icon-remove',  
                handler:function(){  
                	var datagrid_container=$("#customerManageInit_editform_dataGrid");
                	$(datagrid_container).datagrid('acceptChanges');
                	var row = $(datagrid_container).datagrid('getSelected');
                	if (row) {
                	         var rowIndex = $(datagrid_container).datagrid('getRowIndex',row);
                	         $(datagrid_container).datagrid('deleteRow', rowIndex);  
                	         var rows=$(datagrid_container).datagrid('getData')
                	         $(datagrid_container).datagrid('reload',rows);
                	 }
                	
                                	              
                }  
            },'-',{
            	text:"注销",  
                iconCls:'icon-save',  
                handler:function(){ 
                	
                     var  existID;   
                	 var datagrid_container=$("#customerManageInit_editform_dataGrid");
                 	$(datagrid_container).datagrid('acceptChanges');
                 	var row = $(datagrid_container).datagrid('getSelected');
                 	if (row) {
                 	         var rowIndex = $(datagrid_container).datagrid('getRowIndex',row);
                 	        var rows=$('#customerManageInit_editform_dataGrid').datagrid('getRows');                 	                      	        
                 	       existID =  rows[0].id;                	         
                 	       if(existID !=null && existID != ""){                 	    	                           	    	
                 	    	rows[0].enabledFlag = "否"; 
                 	    	
                 	    	$('#customerManageInit_editform_dataGrid').datagrid('updateRow',{
                 	    		index: rowIndex,
                 	    		row: {
                 	    			    enabledFlag: "否",
                 	    				endDate: new Date().toString()
                 	    		}
                 	    	});	                 	    	
                 	    	$.messager.alert('<spring:message code="system.alert"/>','注销成功');
                 	       }else{
                 	    	   
                 	    	  $.messager.alert('<spring:message code="system.alert"/>','新增账户不能被注销');
                 	       }
                 	        
                 	 }
                	                                
                }
            } ], 
            onDblClickRow:function(index,data){
            
            	$(this).datagrid('beginEdit', index);
            	
		},
		onLoadSuccess:function(){  
            $('#customerManageInit_editform_dataGrid').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
        } 
	});
	
	
	$('#customerManageInit_editform_address_dataGrid').datagrid({
		url: '',
		loadMsg:"<spring:message code='system.datagrid.dataloding.msg'/>",
		striped:true,//奇偶行颜色不同
		singleSelect:false, //多选
		collapsible:true,//可折叠  
		fitColumns: true,//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		nowrap: true,
		iconCls: 'icon icon-list',
		pagination:false, //显示分页 
		rownumbers:true, //显示行号
		idField:'id',//主键字段
       	columns:[[	{field:'checkFlag',checkbox:true,width:80,align:'center'},
 					{field:'recipientAddrAlt',title:"地址简称",width:80,editor: { type: 'text', options: { required: true } } ,align:'center'},
 					{field:'recipientAddr',title:"地址",width:80,editor: { type: 'text', options: { required: true } } ,align:'center'},
 					{field:'recipientName',title:"收件人",width:80,align:'center',editor: { type: 'text', options: { required: true } } },
 				
 					{field:'enabledFlag',title:"是否有效(Y/N)",width:80,align:'center',hidden:true,editor:{
						type:'combobox',
						options:{
							url:'${vat}/customer/getEnabledStatus.do',
							valueField:'value',
							textField:'text',
							panelHeight:'auto',
							required:true
						}
					}},
					
					
					{field:'recipientComp',title:"收件人公司",width:80,align:'center',editor: { type: 'text', options: { required: true } } },
					{field:'isDefault',title:"是否默认",width:80,align:'center',editor:{
						type:'combobox',
						options:{
							url:'${vat}/customer/getEnabledStatus.do',
							valueField:'value',
							textField:'text',
							panelHeight:'auto',
							required:true
						}
					}},
					{field:'recipientPhone',title:"电话",width:80,align:'center',editor: { type: 'text', options: { required: true } } },
					{field:'zipCode',title:"邮编",width:80,align:'center',editor: { type: 'text', options: { required: true } } }
 			]],
		toolbar:[ {text:"新增",  
        	iconCls:'icon-add',  
        	handler:function(){
        		var editRow=undefined;
                		var datagrid_container=$("#customerManageInit_editform_address_dataGrid");
                		if (editRow != undefined) {
                            $(datagrid_container).datagrid('endEdit', editRow);
                        }
                        if (editRow == undefined) {
                            $(datagrid_container).datagrid('insertRow', {
                                index: 0,
                                row: {enabledFlag: "是"}
                            });
                            $(datagrid_container).datagrid('beginEdit', 0);
                            editRow = 0;
                        } 
        			}  
    		},'-',
		         {  
            	text:"删除",  
                iconCls:'icon-remove',  
                handler:function(){  
                	var datagrid_container=$("#customerManageInit_editform_address_dataGrid");
                	$(datagrid_container).datagrid('acceptChanges');
                	var row = $(datagrid_container).datagrid('getSelected');
                	if (row) {
                	         var rowIndex = $(datagrid_container).datagrid('getRowIndex',row);
                	         $(datagrid_container).datagrid('deleteRow', rowIndex);  
                	         var rows=$(datagrid_container).datagrid('getData')
                	         $(datagrid_container).datagrid('reload',rows);
                	 }
                }  
            },'-',{
            	text:"设置默认",  
                iconCls:'icon-save',  
                handler:function(){ 
                	                	                	
                	 var  existID;   
                	 var datagrid_container=$("#customerManageInit_editform_address_dataGrid");
                 	$(datagrid_container).datagrid('acceptChanges');
                 	var row = $(datagrid_container).datagrid('getSelected');
                 	if (row) {
                 	         var rowIndex = $(datagrid_container).datagrid('getRowIndex',row);
                 	        var rows=$('#customerManageInit_editform_address_dataGrid').datagrid('getRows');                 	                      	        
                 	       existID =  rows[0].id;                	         
                 	       if(existID !=null && existID != ""){                 	    	                           	    	
                 	    	                	    	
                 	    	$('#customerManageInit_editform_address_dataGrid').datagrid('updateRow',{
                 	    		index: rowIndex,
                 	    		row: {
                 	    			isDefault: "是"
                 	    				
                 	    		}
                 	    	});	   
                 	       }
                 	        
                 	 }else{
           	    	   
            	    	  $.messager.alert('<spring:message code="system.alert"/>','请选择地址'); 
            	       }

                	
                //	var submit_editform_dataGrid=$('#customerManageInit_editform_address_dataGrid');
            	//	$(submit_editform_dataGrid).datagrid('acceptChanges');  
                }
            }], 
            onDblClickRow:function(index,data){
            	$(this).datagrid('beginEdit', index);
		},
		onLoadSuccess:function(){  
            $('#customerManageInit_editform_address_dataGrid').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
        } 
	});
	
	
	}
	
	//新增页面 生成
	function addDataGrid(){
		
		$('#customerManageInit_editform_dataGrid').datagrid({
			url: '',
			loadMsg:"<spring:message code='system.datagrid.dataloding.msg'/>",
			striped:true,//奇偶行颜色不同
			singleSelect:false, //多选
			collapsible:true,//可折叠  
			fitColumns: true,//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			nowrap: true,
			iconCls: 'icon icon-list',
			pagination:false, //显示分页 
			rownumbers:true, //显示行号
			idField:'id',//主键字段
           	columns:[[	{field:'checkFlag',checkbox:true,width:80,align:'center'},
     					{field:'custBankAccountNum',title:"账户号码",width:80,editor: { type: 'text', options: { required: true } } ,align:'center'},
     					{field:'custBankName',title:"账户名称",width:80,editor: { type: 'text', options: { required: true } } ,align:'center'},
     					{field:'custBankNumber',title:"账户代码",width:80,align:'center',editor: { type: 'text', options: { url:'${vat}/customer/getEnabledStatus.do',
							valueField:'value',
							textField:'text',
							panelHeight:'auto',
							required:true } } },
     					
     					{field:'enabledFlag',title:"是否有效(Y/N)",width:80,align:'center',hidden:true,editor:{
    						type:'combobox',
    						options:{
    							
    							valueField:'value',
    							textField:'text',
    							panelHeight:'auto',
    						//	data: [{	
    						//		text: '是'   					
    						//	}]
    							
    						}
    					}},
    					
     					{field:'startDate',title:"有效日期",width:80 ,align:'center',formatter:function(value){
		     						if (typeof (value) != "undefined" && value != 0) {
		     							return myformatter(new Date(value));
		     						}
     							},editor: { type: 'datebox', options: { required: true,showSeconds:false } }
    					},
    					{field:'endDate',title:"失效日期",width:80 ,align:'center',
    						formatter:function(value,rec){
    									if (typeof (value) != "undefined" && value != 0) {
    									return new Date(value).format("yyyy-MM-dd hh:mm:ss");
     										}
 							},editor:{type:'datebox',options:{showSeconds:false},formatter:function(value){
 								if (typeof(value)!="undefined"&&value!=0){
	    								return new Date(value).format("yyyy-MM-dd hh:mm:ss");
	     							}
 							}
							}
    					}
     			]],
			toolbar:[
			         {text:"新增",  
                	iconCls:'icon-add',  
                	handler:function(){
                		var editRow=undefined;
		                		var datagrid_container=$("#customerManageInit_editform_dataGrid");
		                		if (editRow != undefined) {
		                            $(datagrid_container).datagrid('endEdit', editRow);
		          
		                        }
		                        if (editRow == undefined) {
		                            $(datagrid_container).datagrid('insertRow', {
		                                index: 0,
		                                row: {		                               
		                                	enabledFlag: "是"	
		                                }
		                            });
		                            $(datagrid_container).datagrid('beginEdit', 0);
		                            editRow = 0;		                           		                      		                            
		                        }
     
                			}  
            		},'-',{  
	            	text:"删除",  
	                iconCls:'icon-remove',  
	                handler:function(){  
	                	var datagrid_container=$("#customerManageInit_editform_dataGrid");
	                	$(datagrid_container).datagrid('acceptChanges');
	                	var row = $(datagrid_container).datagrid('getSelected');
	                	if (row) {
	                	         var rowIndex = $(datagrid_container).datagrid('getRowIndex',row);
	                	         $(datagrid_container).datagrid('deleteRow', rowIndex);  
	                	         var rows=$(datagrid_container).datagrid('getData')
	                	         $(datagrid_container).datagrid('reload',rows);
	                	 }
	                }  
	            }
            		/*
            		,'-',{
	            	text:"暂存",  
	                iconCls:'icon-save',  
	                handler:function(){ 
	                	var submit_editform_dataGrid=$('#customerManageInit_editform_dataGrid');
	            		$(submit_editform_dataGrid).datagrid('acceptChanges');  
	                }
	            }
            		*/
            		], 
	            onDblClickRow:function(index,data){
	            	alert(data);
	            	$(this).datagrid('beginEdit', index);
	            	
			},
			onLoadSuccess:function(){  
                $('#customerManageInit_editform_dataGrid').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
            } 
		});
						
		$('#customerManageInit_editform_address_dataGrid').datagrid({
			url: '',
			loadMsg:"<spring:message code='system.datagrid.dataloding.msg'/>",
			striped:true,//奇偶行颜色不同
			singleSelect:false, //多选
			collapsible:true,//可折叠  
			fitColumns: true,//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			nowrap: true,
			iconCls: 'icon icon-list',
			pagination:false, //显示分页 
			rownumbers:true, //显示行号
			idField:'id',//主键字段
           	columns:[[	{field:'checkFlag',checkbox:true,width:80,align:'center'},
     					{field:'recipientAddrAlt',title:"地址简称",width:80,editor: { type: 'text', options: { required: true } } ,align:'center'},
     					{field:'recipientAddr',title:"地址",width:80,editor: { type: 'text', options: { required: true } } ,align:'center'},
     					{field:'recipientName',title:"收件人",width:80,align:'center',editor: { type: 'text', options: { required: true } } },
     					{field:'enabledFlag',title:"是否有效(Y/N)",width:80,align:'center',hidden:true,editor:{
    						type:'combobox',
    						options:{
    							url:'${vat}/customer/getEnabledStatus.do',
    							valueField:'value',
    							textField:'text',
    							panelHeight:'auto',
    							required:true
    						}
    					}},
    					{field:'recipientComp',title:"收件人公司",width:80,align:'center',editor: { type: 'text', options: { required: true } } },
    					{field:'isDefault',title:"是否默认",width:80,align:'center',editor:{
    						type:'combobox',
    						options:{
    							url:'${vat}/customer/getEnabledStatus.do',
    							valueField:'value',
    							textField:'text',
    							panelHeight:'auto',
    							required:true
    						}
    					}},
    					{field:'recipientPhone',title:"电话",width:80,align:'center',editor: { type: 'text', options: { required: true } } },
    					{field:'zipCode',title:"邮编",width:80,align:'center',editor: { type: 'text', options: { required: true } } }
     			]],
			toolbar:[
			         {text:"新增",  
                	iconCls:'icon-add',  
                	handler:function(){
                		var editRow=undefined;
		                		var datagrid_container=$("#customerManageInit_editform_address_dataGrid");
		                		if (editRow != undefined) {
		                            $(datagrid_container).datagrid('endEdit', editRow);
		                        }
		                        if (editRow == undefined) {
		                            $(datagrid_container).datagrid('insertRow', {
		                                index: 0,
		                                row: {enabledFlag: "是"}
		                            });
		                            $(datagrid_container).datagrid('beginEdit', 0);
		                            editRow = 0;
		                        } 
                			}  
            		},'-',{  
	            	text:"删除",  
	                iconCls:'icon-remove',  
	                handler:function(){  
	                	var datagrid_container=$("#customerManageInit_editform_address_dataGrid");
	                	$(datagrid_container).datagrid('acceptChanges');
	                	var row = $(datagrid_container).datagrid('getSelected');
	                	if (row) {
	                	         var rowIndex = $(datagrid_container).datagrid('getRowIndex',row);
	                	         $(datagrid_container).datagrid('deleteRow', rowIndex);  
	                	         var rows=$(datagrid_container).datagrid('getData')
	                	         $(datagrid_container).datagrid('reload',rows);
	                	 }
	                }  
	            }
            		/*
            		,'-',{
	            	text:"暂存",  
	                iconCls:'icon-save',  
	                handler:function(){ 
	                	var submit_editform_dataGrid=$('#customerManageInit_editform_address_dataGrid');
	            		$(submit_editform_dataGrid).datagrid('acceptChanges');  
	                }
	            }
            	*/	
            		], 
	            onDblClickRow:function(index,data){
	            	$(this).datagrid('beginEdit', index);
			},
			onLoadSuccess:function(){  
                $('#customerManageInit_editform_address_dataGrid').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
            } 
		});
		
	}
	
	
	
	//#########################################################################
	//新增界面    逻辑变灰控制
	function chooseToAppear(){
		
		$('#customerManageInit_editform_custLegalEntityType').combobox({			
			onSelect:function(data){				
			var custLegalEntityType_value=$('#customerManageInit_editform_custLegalEntityType').combobox('getText');					
    			if(custLegalEntityType_value == "非增值税纳税人"){
    				$("#customerManageInit_editform_custRegistrationCode").combobox('disable');   				   					
    				$("#custRegistrationNumberId").combobox('disable');
    				$("#gsnRegistrationNumber_id").combobox('disable');   				
    			}else {				 
    				$("#customerManageInit_editform_custRegistrationCode").combobox('enable');
    				$("#custRegistrationNumberId").combobox('enable');
    				$("#gsnRegistrationNumber_id").combobox('enable');    			
				}
    		}	
    	});
							 
		}
	//############################################################
		/*
		  $('#customerManageInit_editform_custRegistrationCode').combobox({
				
				onSelect:function(data){
	    				
				var custRegistrationCode_value=$('#customerManageInit_editform_custRegistrationCode').combobox('getText');

					if(custRegistrationCode_value == "纳税人识别号"){
					
						alert(custRegistrationCode_value);
						
						var custRegistrationNumber_id_value = $("#custRegistrationNumberId").textbox('getValue');	
					    
						alert(custRegistrationNumber_id_value);
						
						if(custRegistrationNumber_id_value == null || custRegistrationNumber_id_value == "" ){
		
							alert("进来了");
							
							sign = 1; signValue = 1;
							
							alert(sign);
							
						}																				
					}
					
					if(custRegistrationCode_value == "统一社会征信号码 " ){
						
						
						var custRegistrationNumber_id_value = $("#gsnRegistrationNumber_id").textbox('getValue');	
						

						if(gsnRegistrationNumber_id_value == null || gsnRegistrationNumber_id_value == "" ){
		
							sign = 2;	signValue = 1;	
						}				
						
						
						
					}
						
						
																			
	    		}	
	    	});
             */
		  
		 
		  /*	
			$("#custRegistrationNumberId").textbox({
				   prompt : "请输入",
					required: true
							
					});		
			*/
			/*
			$("#custRegistrationNumberId").textbox({
		        
				 prompt : "",
				required: false
				
			});
			*/
          
//####################################################
 //以下方法校验必选项   
    function mustWrite(){
						
				var custLegalEntityType_value=$('#customerManageInit_editform_custLegalEntityType').combobox('getText');				
				if(custLegalEntityType_value == null || custLegalEntityType_value == ""){					
					sign = 100;					
				}else if(custLegalEntityType_value == "一般增值税纳税人"){					
					custRegistrationNumbeChanage();					
				}else if(custLegalEntityType_value == "非增值税纳税人"){					
					isAppointInvoiceChanage();					
				}
				
			}
      	
    function custLegalEntityTypeChange(){
    	 
	var value = $("#custDepositBankName_id").textbox('getValue');
	var custLegalEntityType_value=$('#customerManageInit_editform_custLegalEntityType').combobox('getText');
	if(custLegalEntityType_value == null || custLegalEntityType_value == ""){		
		sign = 100;		
	}else if(custLegalEntityType_value == "一般增值税纳税人"){	
	  	var custDepositBankName_id_value = $("#custDepositBankName_id").textbox('getValue');
		if(custDepositBankName_id_value == null || custDepositBankName_id_value == "" ){			
			sign = 3;						
		}else{				
		custDepositBankAccountNumChange();				
	}		
	}	  
}
    function custDepositBankAccountNumChange(){
   	     	   	
    	var custLegalEntityType_value=$('#customerManageInit_editform_custLegalEntityType').combobox('getText');
    	if(custLegalEntityType_value == "一般增值税纳税人"){ 	
    	 var custDepositBankAccountNum_id_value = $("#custDepositBankAccountNum_id").textbox('getValue');			  
    	 if(custDepositBankAccountNum_id_value != null && custDepositBankAccountNum_id_value != "" ){				
			   contactNameChange();				
			}else{				
				sign = 4;
			}	
    } 
    }
    
function contactNameChange(){
		
	var custLegalEntityType_value=$('#customerManageInit_editform_custLegalEntityType').combobox('getText');
	if(custLegalEntityType_value == "一般增值税纳税人"){
	var value = $("#contactName_id").textbox('getValue');		  
	if(value != null && value != "" ){			
		contactPhoneChanage();				
		}else{			
			sign = 5;			
		}		
      }
	
      }	
    /*
  function contactPhoneChange(){
	   
		var custLegalEntityType_value=$('#customerManageInit_editform_custLegalEntityType').combobox('getText');
		if(custLegalEntityType_value == "一般增值税纳税人"){
	   
	  var value = $("#contactName_id").textbox('getValue');	
	   if(value != null && value != "" ){
			
		   contactPhoneChanage();
			
		}else{
			
			
			
		}	
	  
		} 
  }  
    */
  function contactPhoneChanage(){
	  
		var custLegalEntityType_value=$('#customerManageInit_editform_custLegalEntityType').combobox('getText');
		if(custLegalEntityType_value == "一般增值税纳税人"){	   
	  var value = $("#contactPhone_id").textbox('getValue');	
	   if(value != null && value != "" ){			
		   custRegistrationAddressChanage();						
		}else{			
			sign=6;			
		}		  
		} 
	  
  }
  
 function  custRegistrationAddressChanage(){
	 
	 var custLegalEntityType_value=$('#customerManageInit_editform_custLegalEntityType').combobox('getText');
		if(custLegalEntityType_value == "一般增值税纳税人"){	   
	  var value = $("#custRegistrationAddress_id").textbox('getValue');	
	   if(value != null && value != "" ){			
		   isAppointInvoiceChanage();						
		}else{			
			sign = 7;			
		}		  
		}  
	 
 }
  
  
  
  //判断开票证件类型
  function custRegistrationNumbeChanage(){
	  
		var custRegistrationCode_value=$('#customerManageInit_editform_custRegistrationCode').combobox('getText');		
		if(custRegistrationCode_value != null && custRegistrationCode_value != ""){		
		if(custRegistrationCode_value == "纳税人识别号"){		
			var custRegistrationNumber_id_value = $("#custRegistrationNumberId").textbox('getValue');					
			if(custRegistrationNumber_id_value == null || custRegistrationNumber_id_value == "" ){				
				sign = 1;
				signValue = 1;				
			}else{				
				custLegalEntityTypeChange();
			}																				
		}
				
		if(custRegistrationCode_value == "统一社会征信号码 " ){					
			var gsnRegistrationNumber_id_value = $("#gsnRegistrationNumber_id").textbox('getValue');				
			if(gsnRegistrationNumber_id_value == null || gsnRegistrationNumber_id_value == "" ){
				sign = 2;
				signValue = 1;
			}else{				
				custLegalEntityTypeChange();
			}					
		}
		
		}else{			
			sign = 101;
			signValue = 1;
		}	 		
  }
  
  
  function isAppointInvoiceChanage(){
	  var value=$('#customerManageInit_editform_isAppointInvoice').combobox('getText');
		if(value == null || value == ""){		
			sign = 11;			
		}else  if(value == "是"){
			
			var value2 = $('#customerManageInit_editform_billingPeriod').combobox('getText');	
			var value3 = $("#appointRunInterval_id").textbox('getValue');
			var  value4 = $("#appointInvoiceOrgCode_id").textbox('getValue');			
			if(value2 == null || value2 == "" ){						
				sign = 8;			
			}else if(value3 == null || value3 == "" ){						
				sign = 9;
			}else if(value4 == null || value4 == "" ){							
				sign = 10;
			}
				
		}
  }
    
//###############################################	
	//新增页面保存方法
	  function changeSign(){

		  mustWrite();		     
		  if(signValue == 1){	    	  
	    	  signValue = -101;
	      }else{	    	  
	    	  custLegalEntityTypeChange();
	      }

		  if(sign == 100){	  	  
			  $.messager.alert('<spring:message code="system.alert"/>','纳税人类型不能为空');
			  sign = -1;			  		  
		  }else if(sign == 101){			  	  
			  $.messager.alert('<spring:message code="system.alert"/>','开票使用证件类型不能为空');
			  sign = -1;			  		  
		  }else if(sign == 1){			  	  
			  $.messager.alert('<spring:message code="system.alert"/>','纳税人识别号不能为空');
			  sign = -1;			  		  
		  }else if(sign == 2){
			  $.messager.alert('<spring:message code="system.alert"/>','统一社会信用代码不能为空');
			  sign = -1;
		  }else if(sign == 3){
			  $.messager.alert('<spring:message code="system.alert"/>','开户银行不能为空');			 
			  sign = -1;
		  }else if(sign == 4){
			  $.messager.alert('<spring:message code="system.alert"/>','开户账号不能为空');
			  sign = -1;
		  }else if(sign == 5){
			  $.messager.alert('<spring:message code="system.alert"/>','联系人不能为空');
			  sign = -1;
		  }else if(sign == 6){
			  $.messager.alert('<spring:message code="system.alert"/>','联系人电话不能为空');
			  sign = -1;
		  }else if(sign == 7){
			  $.messager.alert('<spring:message code="system.alert"/>','公司地址不能为空');
			  sign = -1;
		  }else if(sign == 8){
			  $.messager.alert('<spring:message code="system.alert"/>','预约开票间隔单位不能为空');
			  sign = -1;
		  }else if(sign == 9){
			  $.messager.alert('<spring:message code="system.alert"/>','运行间隔不能为空');
			  sign = -1;
		  }else if(sign == 10){
			  $.messager.alert('<spring:message code="system.alert"/>','预约开票管理组织不能为空');
			  sign = -1;
		  }else if(sign == 11){
			  $.messager.alert('<spring:message code="system.alert"/>','是否预约开票不能为空');
			  sign = -1;
		  }else{			  
			  customerManageInit_Save();			  
		  }
		   
	  }
	  
	
//###########################################################################
//新增页面    客户经纪关系 下拉面板生成
function initTaxEntity() {
	$('#TmsMdFlexStructuresListInSendForm')
			.combogrid(
					{
						panelWidth : 360,
						idField : 'flexStructuresDescription', //ID字段  
						textField : 'flexStructuresDescription', //显示的字段  
						url : "",
						fitColumns : true,
						striped : true,
						editable : true,
						pagination : true, //是否分页
						rownumbers : true, //序号
						collapsible : false, //是否可折叠的
						fit : false, //自动大小
						method : 'post',
						columns : [ [ {
							field : 'flexStructuresCode',
							title : '客户经纪关系',
							width : 200,
						//	hidden : true
						} ] ],
						toolbar : [
								{
									text : '客户经纪关系:<input type="text" id="taxEntityId" class="easyui-textbox"/>'
								}, {
									text : "查询",
									iconCls : 'icon-search',
									handler : function() {
										findTaxEntity();
									}
								}, '-' ],
						keyHandler : {
							up : function() { //【向上键】押下处理
								//取得选中行
								var selected = $(
										'#TmsMdFlexStructuresListInSendForm')
										.combogrid('grid').datagrid(
												'getSelected');
								if (selected) {
									//取得选中行的rowIndex
									var index = $(
											'#TmsMdFlexStructuresListInSendForm')
											.combogrid('grid')
											.datagrid('getRowIndex',
													selected);
									//向上移动到第一行为止
									if (index > 0) {
										$('#TmsMdFlexStructuresListInSendForm')
												.combogrid('grid')
												.datagrid('selectRow',
														index - 1);
									}
								} else {
									var rows = $(
											'#TmsMdFlexStructuresListInSendForm')
											.combogrid('grid').datagrid(
													'getRows');
									$('#TmsMdFlexStructuresListInSendForm')
											.combogrid('grid').datagrid(
													'selectRow',
													rows.length - 1);
								}
							},
							down : function() { //【向下键】押下处理
								//取得选中行
								var selected = $(
										'#TmsMdFlexStructuresListInSendForm')
										.combogrid('grid').datagrid(
												'getSelected');
								if (selected) {
									//取得选中行的rowIndex
									var index = $(
											'#TmsMdFlexStructuresListInSendForm')
											.combogrid('grid')
											.datagrid('getRowIndex',
													selected);
									//向下移动到当页最后一行为止
									if (index < $(
											'#TmsMdFlexStructuresListInSendForm')
											.combogrid('grid').datagrid(
													'getData').rows.length - 1) {
										$('#TmsMdFlexStructuresListInSendForm')
												.combogrid('grid')
												.datagrid('selectRow',
														index + 1);
									}
								} else {
									$('#TmsMdFlexStructuresListInSendForm')
											.combogrid('grid').datagrid(
													'selectRow', 0);
								}
							},

						},

					});

	//取得分页组件对象
	var pager = $('#TmsMdFlexStructuresListInSendForm').combogrid('grid')
			.datagrid('getPager');

	if (pager) {
		$(pager)
				.pagination(
						{
							pageSize : 10,//每页显示的记录条数，默认为10           
							//pageList: [5,10,15,20],//可以设置每页记录条数的列表           
							beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
							afterPageText : '<spring:message code="pagination.afterPageText"/>',
							displayMsg : '<spring:message code="pagination.displayMsg"/>',
							//选择页的处理
							onSelectPage : function(pageNumber, pageSize) {
								//按分页的设置取数据
								getData(pageNumber, pageSize);
								//设置表格的pageSize属性，表格变化时按分页组件设置的pageSize显示数据
								$('#TmsMdFlexStructuresListInSendForm').combogrid(
										"grid").datagrid('options').pageSize = pageSize;
								//将隐藏域中存放的查询条件显示在combogrid的文本框中
								//$('#combogrid').combogrid("setValue",
								//		$('#hdKeyword').val());

							},
							//改变页显示条数的处理
							//（处理后还是走onSelectPage事件，所以设置也写到onSelectPage事件中了）
							onChangePageSize : function() {
							},
							//点击刷新的处理
							onRefresh : function(pageNumber, pageSize) {
								//按分页的设置取数据
								getData(pageNumber, pageSize);
								//将隐藏域中存放的查询条件显示在combogrid的文本框中
								/* $('#combogrid').combogrid("setValue",
										$('#hdKeyword').val()); */

							}
						});
	}

	var getData = function(page, rows) {
		$.ajax({
			url : "${vat}tmsMdFlexStructures/getResult.do//",
			type : "POST",
			async : true,
			data : '', //不能直接写成 {id:"123",code:"tomcat"}  
			dataType : "json",
			// contentType: "charset=utf-8",  
			cache : false,
			success : function(result) {
				//clearSaveForm();
				$('#TmsMdFlexStructuresListInSendForm').combogrid('grid')
						.datagrid('loadData', result.data);
			}
		});
	};

	searchTaxEntityInDialod();//初始完后加载数据
}
	
function searchTaxEntityInDialod() {
	$.ajax({
		type : "POST",
		url : "${vat}tmsMdFlexStructures/getResult.do",
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		},
		success : function(data) {
			var result = $.parseJSON(data);
			$('#TmsMdFlexStructuresListInSendForm').combogrid('grid').datagrid(
					'loadData', result.data);
		}
	});
}

function noEditable2(){
	
	$('#customerManageInit_searchform_custLegalEntityType').combobox({
		 editable:false,
		});
	
	$('#customerManageInit_searchform_isAppointInvoice').combobox({
		 editable:false
		});
	$('#customerManageInit_searchform_enabled').combobox({
		 editable:false
		});
	$('#customerManageInit_searchform_enabled').combobox({
		 editable:false
		});
	$('#customerManageInit_editform_customerType').combobox({
		 editable:false
		});
	$('#customerManageInit_editform_custLegalEntityType').combobox({
		 editable:false
		});
	$('#customerManageInit_editform_custRegistrationCode').combobox({
		 editable:false
		});
	$('#customerManageInit_editform_enabledFlag').combobox({
		 editable:false
		});
	$('#customerManageInit_searchform_isAppointInvoice').combobox({
		 editable:false
		
		});
	
	
	
}

	
	
</script>
</html>