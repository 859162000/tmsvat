<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
	<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
		<div data-options="region:'center'" title=""border="false">
			<div class="easyui-panel" border="false"
			style="width: 100%; height: 100%; padding: 0px;overflow: hidden;"border="false">
				<div class="easyui-layout" style="width: 100%; height: 100%;"border="false">
					<div region="north" split="true" border="false"
						style="overflow: hidden; height: 12%;">
						<div class="easyui-panel"
							title=""
							style="height: 100%; background-color: #E0ECFF">
							<form id="invoicePreProcessQueryInit_searchform" method="post"  scroll="no">
								<table  style="padding:0px"  >
				    		<tr >
				    			<td><font color=red></font>受理日期:</td>
				    			<td>
				    				 <input class="easyui-datebox"  style="width:110px" value=" " name="approvedatefrom" data-options="formatter:myformatter,parser:myparser,required:true" missingMessage=<spring:message code='client.model.missMsg'/>></input>
				    			</td>
				    			<td>至</td>
				    			<td>
				    				<input class="easyui-datebox" style="width:110px" value=" " name="approvedateto" data-options="formatter:myformatter,parser:myparser,required:true" missingMessage=<spring:message code='client.model.missMsg'/>></input>
				    			</td>
				    			<td>
				    			   <div style="text-align:center;padding:10px">
				    	             <a href="#" id="searchbtn"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px" onclick="invoicePreProcessQueryInit_Search()"><spring:message code='client.search.button.find'/></a>
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
							<table class="easyui-datagrid" id="invoicePreProcessQueryInit_dataGrid"
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
	   <div id="invoicePreProcessQueryInit_add_dlg" class="easyui-dialog"
			style="width: 1068px; height: 500px;" closed="true"
			buttons="#invoicePreProcessQueryInit_add_dlg-buttons" modal="true">
				<form id="invoicePreProcessQueryInit_editform" class="easyui-form" method="post" data-options="novalidate:true" commandsName="billBookManageForm">
					<table >
		    		
		    		<tr>
		    			<td align="right"><font color=red>*</font><spring:message code='com.vat.base.model.customer.custRegistrationDate.text'/>:</td>
		    			<td>
		    				 <input class="easyui-datebox" width="90" value=" " name="custRegistrationDate" data-options="formatter:myformatter,parser:myparser,required:true" missingMessage=<spring:message code='client.model.missMsg'/>></input>
		    			</td>
		    			
		    		</tr>
		    	</table>
		    </form>
		</div>
		<div id="invoicePreProcessQueryInit_add_dlg-buttons">
			<a  href="javascript:void(0)" class="easyui-linkbutton"	iconCls="icon-ok" onclick="invoicePreProcessQueryInit_Save()">
					<spring:message	code="button.Save" />
			</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#invoicePreProcessQueryInit_add_dlg').dialog('close')"><spring:message
					code="button.Close" />
			</a>
		</div>
    </div>
  </body>  
<script type="text/javascript">
	$(document).ready(function(){
		pageDataSettingInit();
		InitCombobox();	
		$('#invoicePreProcessQueryInit_searchform').form('load', {
			pageNumber: $('#invoicePreProcessQueryInit_dataGrid').datagrid('options').pageNumber,
			pageSize: $('#invoicePreProcessQueryInit_dataGrid').datagrid('options').pageSize
		});
		invoicePreProcessQueryInit_Search();
	});
	//页面表格绑定初始化
	function pageDataSettingInit(){
		$('#invoicePreProcessQueryInit_dataGrid').datagrid({
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
            columns:[[
      			    {field:'ck',checkbox:true,width:2}, //显示复选框     
			    { field:'crvatInvoicePreNumber', title:'准备单编号',width:150,align:'center'},
				{ field:'invoiceReqNum', title:'申请单编号',width:150,align:'center'}, 
				{ field:'customerNumber', title:'客户号',width:120,align:'center'},
				{ field:'customerName', title:'购方单位名称',width:100,align:'center'},
				{ field:'custRegistrationCodeStr', title:'购方证件类型',width:110,align:'center'},
				{ field:'custRegistrationNumber', title:'购方证件号码',width:130,align:'center'},
				{ field:'requestDate', title:'申请日期',width:80,align:'center'},
				{ field:'requestPerson', title:'开票申请人',width:100,align:'center'},
				{ field:'approvalBy', title:'准备单受理人',width:80,align:'center'},
				{ field:'astatus', title:'受理状态',width:80,align:'center'},
				{ field:'approveDate', title:'受理日期',width:80,align:'center',formatter:function(value){
					if (typeof (value) != "undefined" && value != 0) {
							return myformatter(new Date(value));
						}
				}},
				{ field:'invoiceAmount', title:'合计金额',width:80,align:'center'},
				{ field:'acctdAmountCr', title:'实际开票金额',width:80,align:'center'}
      			]],
	            onDblClickRow:function(index,data){
					//alert("双击表格显示详情功能正在建设中。。。。");
				/* var row = $('#invoicePreProcessQueryInit_dataGrid').datagrid('getSelected');
				if (row){
					loadSaveFormData(row);
				} */
			},
			onLoadSuccess:function(){  
                $('#invoicePreProcessQueryInit_dataGrid').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
            } 
		});
		//设置分页控件	
		var p = $('#invoicePreProcessQueryInit_dataGrid').datagrid('getPager'); 
			$(p).pagination({           
				pageSize: 10,//每页显示的记录条数，默认为10           
				//pageList: [5,10,15,20],//可以设置每页记录条数的列表           
				beforePageText: '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
				afterPageText: '<spring:message code="pagination.afterPageText"/>',           
				displayMsg: '<spring:message code="pagination.displayMsg"/>',
				onSelectPage: function (pageNumber, pageSize) {//分页触发		
					 invoicePreProcessQueryInit_find(pageNumber,pageSize);
					 invoicePreProcessQueryInit_Search(); 
					 invoicePreProcessQueryInit_clearForm();
		         }
		});
	}
	function invoicePreProcessQueryInit_find(pageNumber,pageSize){
		$('#invoicePreProcessQueryInit_searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		invoicePreProcessQueryInit_Search();
	}
	function invoicePreProcessQueryInit_Search(){
    	$("#invoicePreProcessQueryInit_dataGrid").datagrid("loading");
    	$('#invoicePreProcessQueryInit_searchform').form('submit', {
			url:'${vat}/invoicePreProcess/invoicePreProcessQueryList.do',			
			onSubmit : function(){
				return $(this).form('enableValidation').form('validate');
			},
			success:function(result){
				var result = $.parseJSON(result);
				if(result.status=='0'){
				     $("#invoicePreProcessQueryInit_dataGrid").datagrid('loadData', result.data);
				}else if(result.status=='1'){
					$.messager.alert('<spring:message code="system.alert"/>',result.erroMessage,'info');
				}
				$("#invoicePreProcessQueryInit_dataGrid").datagrid("loaded"); 
		     }
		});
    }
	
	function InitCombobox(){
		//------
		/* $("#invoicePreProcessQueryInit_searchform_customerName").combogrid({
			   panelWidth:200,
			   value:'',//缺省值
			   idField:"customerCode",
			   textField:"customerName",
			   url:"",
			   columns:[[  
			         {field:'customerCode',title:"<spring:message code='com.vat.base.model.customer.customerCode.text'/>",width:80},
					 {field:'customerName',title:"<spring:message code='com.vat.base.model.customer.customerName.text'/>",width:120} 
			      ]]
			  }); */
		//ajax提交请求获取数据
		/* $.ajax({  
	        url: "${vat}/customer/getCustomerNameList.do",  
	        type: "POST",  
	        async: true,  
	        data: '', //不能直接写成 {id:"123",code:"tomcat"}  
	        dataType: "json",  
	        // contentType: "charset=utf-8",  
	        cache: false,  
	        success: function (result){
				invoicePreProcessQueryInit_clearForm();
				$('#invoicePreProcessQueryInit_searchform_customerName').combogrid('grid').datagrid('loadData',result.data);
	        } 
	    }); */
		
	}
	
    //客户关系添加
    function invoicePreProcessQueryInit_Add(){
    	invoicePreProcessQueryInit_clearForm();
		$("#invoicePreProcessQueryInit_add_dlg").dialog('open').dialog('setTitle',
		"<spring:message code='client.dialog.clientadd.title'/>");
		$("#invoicePreProcessQueryInit_editform").form('load', {custRegistrationDate:myformatter(new Date())});
	} 
    function invoicePreProcessQueryInit_Edit(){
    	invoicePreProcessQueryInit_clearForm();
    	var row_list=$('#invoicePreProcessQueryInit_dataGrid').datagrid('getChecked');
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
    			$.post('${vat}/customer/loadModifyCustomer.do',
            			{id:id}, 
            			function(result) {
            				if(result.status=='0'){
            	        		invoicePreProcessQueryInit_clearForm();
            	        		$("#invoicePreProcessQueryInit_add_dlg").dialog('open').dialog('setTitle',
    								"<spring:message code='client.dialog.clientedit.title'/>");
            	        		$("#invoicePreProcessQueryInit_editform").form('load', 
            	        				{
            	        			id:result.data.id,
            	        			custRegistrationDate:myformatter(new Date(result.data.custRegistrationDate)),
            	        			customerCode:result.data.customerCode,
            	        			customerName:result.data.customerName,
            	        			contactPhone:result.data.contactPhone,
            	        			bankBranchName:result.data.bankBranchName,
            	        			bankAccountNum:result.data.bankAccountNum,
            	        			custRegistrationAddress:result.data.custRegistrationAddress,
            	        			customerEntityNum:result.data.customerEntityNum,
            	        			contactName:result.data.contactName,
            	        			zipCode:result.data.zipCode,
            	        			recipientComp:result.data.recipientComp,
            	        			recipientPhone:result.data.recipientPhone,
            	        			recipientAddr:result.data.recipientAddr,
            	        			recipientName:result.data.recipientName,
            	        				}
            	        		);
            	        		$(".invoicePreProcessQueryInit_customerType").combobox('setValue', result.data.customerType);
            	        		$(".invoicePreProcessQueryInit_legalEntityType").combobox('setValue', result.data.legalEntityType);
            	        		$(".invoicePreProcessQueryInit_isAppointInvoice").combobox('setValue', result.data.isAppointInvoice);
            	        		$(".invoicePreProcessQueryInit_isInvoiceProviding").combobox('setValue', result.data.isInvoiceProviding);
            	        		$(".invoicePreProcessQueryInit_bizOrgCode").combobox('setValue', result.data.bizOrgCode);
            	        		$(".invoicePreProcessQueryInit_bankAccountType").combobox('setValue', result.data.bankAccountType);
            	        		$(".invoicePreProcessQueryInit_enabled").combobox('setValue', result.data.enabled);
            	        		
    							//$("#invoicePreProcessQueryInit_editform").form('load', result.data);
            	        	}else{
            	        		$.messager.alert('<spring:message code="system.alert"/>',result.errorMessage);
        						invoicePreProcessQueryInit_Search();
        						invoicePreProcessQueryInit_clearForm();
        						$('#invoicePreProcessQueryInit_add_dlg').dialog('close');
            	        	}
    					},
    					'json');
        	}else{
        		$.messager.alert('<spring:message code="system.alert"/>','编辑的主键不能为空');
        	}
    	}
    }
	function invoicePreProcessQueryInit_Save(){
		var submit_form_container=$('#invoicePreProcessQueryInit_editform');
		if(submit_form_container.form('enableValidation').form('validate')){
			$.messager.confirm("<spring:message code='client.datacommit.formsave.confirm.title'/>","<spring:message code='client.datacommit.formsave.confirm.text'/>",function(result){
				if (result){
					$(submit_form_container).form('submit',{
						url:'${vat}/customer/saveCustomer.do',	
						onSubmit : function() {
								$.messager.progress({title:'Please waiting',
									msg:'数据保存中。。。。'});
						},			
						success:function(result){
							$.messager.progress('close');
							var result = $.parseJSON(result);
							if(result.status=='0'){	
								invoicePreProcessQueryInit_clearForm();
								invoicePreProcessQueryInit_Search();
								$('#invoicePreProcessQueryInit_add_dlg').dialog('close');
								$.messager.alert('<spring:message code="system.alert"/>','保存成功');
							}
							
						} 
					});
				}
			});
		}
	}
	//删除一条记录
	
	function invoicePreProcessQueryInit_Remove(){
		invoicePreProcessQueryInit_clearForm();
		var row_list=$('#invoicePreProcessQueryInit_dataGrid').datagrid('getChecked');
		//var row=$('#invoicePreProcessQueryInit_dataGrid').datagrid('getSelected');
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
		        	        	invoicePreProcessQueryInit_Search();
		    					invoicePreProcessQueryInit_clearForm();
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
	
	function invoicePreProcessQueryInit_clearForm(){
		$('#invoicePreProcessQueryInit_editform').form('clear');
	}
</script>
</html>