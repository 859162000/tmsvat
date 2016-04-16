<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
	<body class="easyui-layout" style="overflow-y: hidden"  scroll="no" border="false">
		<div data-options="region:'center'" title=""border="false">
		<div class="easyui-layout" style="width: 100%; height: 100%;"border="false">
			<div region="north" split="true" border="false"
				style="overflow: hidden; height: 20%;">
				<div class="easyui-panel"
					title="<spring:message code="searchgroup"/>"
					style="width: 1500px; height: 100%; background-color: #E0ECFF">
					<form id="priPlanSetManageInit_searchform" method="post"  scroll="no">
						<table  style="padding:0px"  >
			    		<tr style="padding:5px">
			    			<td>客户名称</td>
			    			<td>
			    				<select id="priPlanSetManageInit_searchform_customerName" name="customerName" class="easyui-combogrid priPlanSetManageInit_customerName"  style="width:120px;"></select>
			    			</td>
			    			<td align="right"><spring:message code='com.vat.base.model.customer.customerEntityNum.text'/>:</td>
			    			<td>
			    				<input class="easyui-textbox" type="text" name="customerEntityNum" />
			    			</td>
			    			<td>是否有效</td>
			    			<td>
			    				<select id="priPlanSetManageInit_searchform_isInvoiceProviding" name="isInvoiceProviding11" class="easyui-combobox"  style="width:120px;">
			    					<option value='2' ><spring:message code='client.select.option.yes'/></option>
			    					<option value='2' ><spring:message code='client.select.option.no'/></option>
			    				</select>
			    			</td>
			    			<td>
			    			   <div style="text-align:center;padding:10px">
			    	             <a href="#" id="searchbtn"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px" onclick="priPlanSetManageInit_Search()"><spring:message code='client.search.button.find'/></a>
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
					<table class="easyui-datagrid" id="priPlanSetManageInit_dataGrid"
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
    <div data-options="region:'center',border:false" style="background-color: #E0ECFF">
	   <div id="priPlanSetManageInit_add_dlg" class="easyui-dialog"
			style="width: 680px; height: 400px;" closed="true"
			buttons="#priPlanSetManageInit_add_dlg-buttons" modal="true">
				<form id="priPlanSetManageInit_editform" class="easyui-form" method="post" data-options="novalidate:true" commandsName="billBookManageForm">
					<table >
		    		<tr>
	    				<td align="right"><font color=red>*</font>客户名称:</td>
		    			<td>
		    				<input  type="hidden" name="id"></input>
		    				<select class="easyui-combobox priPlanSetManageInit_customerType" name="customerType" style="width:150px">
		    					<option value="1">Arabic</option>
		    					<option value="2">Bulgarian</option>
		    					<option value="3">Catalan</option>
		    					<option value="4">Chinese Traditional</option>
		    					<option value="5">Czech</option>
		    				</select>
		    			</td>
		    			<td align="right">打印频率:</td>
		    			<td>
		    				<select class="easyui-combobox priPlanSetManageInit_customerType" name="customerType" style="width:150px">
		    					<option value="1">Arabic</option>
		    					<option value="2">Bulgarian</option>
		    					<option value="3">Catalan</option>
		    					<option value="4">Chinese Traditional</option>
		    					<option value="5">Czech</option>
		    				</select>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td align="right"><font color=red>*</font><spring:message code='com.vat.base.model.customer.legalEntityType.text'/>:</td>
		    			<td>
		    				<select class="easyui-combobox priPlanSetManageInit_legalEntityType" name="legalEntityType" style="width:150px" data-options="required:true" missingMessage="<spring:message code='client.model.missMsg'/>">
		    					<option value="1" >Arabic</option>
		    					<option value="2">Bulgarian</option>
		    					<option value="3">Catalan</option>
		    					<option value="4">Chinese Traditional</option>
		    					<option value="5">Czech</option>
		    				</select>
		    			</td>
		    			<td align="right"><spring:message code='com.vat.base.model.customer.contactName.text'/>:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="contactName" data-options="required:false" ></input>
		    			</td>
		    			
		    			
		    		</tr>
		    		<tr>
		    			<td align="right"><spring:message code='com.vat.base.model.customer.bankAccountNum.text'/>:</td>
		    			<td >
		    				<input class="easyui-textbox" align="right" type="text" name="bankAccountNum"  data-options="required:false"></input>
		    			</td>
		    			<td align="right"><spring:message code='com.vat.base.model.customer.contactName.text'/>:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="contactName" data-options="required:false" ></input>
		    			</td>
		    		</tr>
		    		
		    	</table>
		    </form>
		</div>
		<div id="priPlanSetManageInit_add_dlg-buttons">
			<a  href="javascript:void(0)" class="easyui-linkbutton"	iconCls="icon-ok" onclick="priPlanSetManageInit_Save()">
					<spring:message	code="button.Save" />
			</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#priPlanSetManageInit_add_dlg').dialog('close')"><spring:message
					code="button.Close" />
			</a>
		</div>
    </div>
  </body>  
<script type="text/javascript">
	$(document).ready(function(){
		pageDataSettingInit();
		InitCombobox();	
		$('#priPlanSetManageInit_searchform').form('load', {
			pageNumber: $('#priPlanSetManageInit_dataGrid').datagrid('options').pageNumber,
			pageSize: $('#priPlanSetManageInit_dataGrid').datagrid('options').pageSize
		});
		priPlanSetManageInit_Search();
	});
	//页面表格绑定初始化
	function pageDataSettingInit(){
		$('#priPlanSetManageInit_dataGrid').datagrid({
			url: '',
			loadMsg:"<spring:message code='system.datagrid.dataloding.msg'/>",
			striped:true,//奇偶行颜色不同
			singleSelect:false, //多选
			collapsible:false,//可折叠  
			fitColumns: true,//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			nowrap: false,
			iconCls: 'icon icon-list',
			pagination:true, //显示分页 
			rownumbers:true, //显示行号
			idField:'id',//主键字段
			frozenColumns:[[
							{field:'checkFlag',checkbox:true,width:80,align:'center'},
							{field:'id',hidden:true,width:80,align:'center'}
			                ]],
           	columns:[[
     					{field:'customerCode',title:"<spring:message code='com.vat.base.model.customer.customerCode.text'/>",width:80,align:'center'},
     					{field:'customerName',title:"<spring:message code='com.vat.base.model.customer.customerName.text'/>",width:80,align:'center'},
     					{field:'customerType',title:"<spring:message code='com.vat.base.model.customer.customerType.text'/>",width:80,align:'center'},
     					{field:'legalEntityType',title:"<spring:message code='com.vat.base.model.customer.legalEntityType.text'/>",width:80,align:'center'},
     					{field:'isInvoiceProviding',title:"<spring:message code='com.vat.base.model.customer.isInvoiceProviding.text'/>",width:80,align:'center'},
     					{field:'contactPhone',title:"<spring:message code='com.vat.base.model.customer.contactPhone.text'/>",width:80,align:'center'},
     					{field:'bankBranchName',title:"<spring:message code='com.vat.base.model.customer.bankBranchName.text'/>",width:80,align:'center'},
     					{field:'bankAccountNum',title:"<spring:message code='com.vat.base.model.customer.bankAccountNum.text'/>",width:80,align:'center'},
     					{field:'bankAccountType',title:"<spring:message code='com.vat.base.model.customer.bankAccountType.text'/>",width:80,align:'center',formatter:function(value,rec){
     							//var temp_value='<span style="color:red">'+value+'</span>';
     							var temp_value=value;
     						return temp_value;
     					}},
     					{field:'custRegistrationAddress',title:"<spring:message code='com.vat.base.model.customer.custRegistrationAddress.text'/>",width:80,align:'center'},
     			]],
			toolbar:[
			         {text:"<spring:message code='button.Add'/>",  
                	iconCls:'icon-add',  
                	handler:function(){  
                			priPlanSetManageInit_Add();  
                			}  
            		},'-',{  
            			text:"<spring:message code='button.Edit'/>",  
	                iconCls:'icon-edit',  
	                handler:function(){  
	                	priPlanSetManageInit_Edit();
	                }  
	            },'-',{  
	            	text:"<spring:message code='button.Remove'/>",  
	                iconCls:'icon-remove',  
	                handler:function(){  
	                	priPlanSetManageInit_Remove();  
	                }  
	            },'-'],
	            onDblClickRow:function(index,data){
					//alert("双击表格显示详情功能正在建设中。。。。");
				/* var row = $('#priPlanSetManageInit_dataGrid').datagrid('getSelected');
				if (row){
					loadSaveFormData(row);
				} */
			},
			onLoadSuccess:function(){  
                $('#priPlanSetManageInit_dataGrid').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
            } 
		});
		//设置分页控件	
		var p = $('#priPlanSetManageInit_dataGrid').datagrid('getPager'); 
			$(p).pagination({           
				pageSize: 10,//每页显示的记录条数，默认为10           
				//pageList: [5,10,15,20],//可以设置每页记录条数的列表           
				beforePageText: '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
				afterPageText: '<spring:message code="pagination.afterPageText"/>',           
				displayMsg: '<spring:message code="pagination.displayMsg"/>',
				onSelectPage: function (pageNumber, pageSize) {//分页触发		
					 priPlanSetManageInit_find(pageNumber,pageSize);
					 priPlanSetManageInit_Search(); 
					 priPlanSetManageInit_clearForm();
		         }
		});
	}
	function priPlanSetManageInit_find(pageNumber,pageSize){
		$('#priPlanSetManageInit_searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		priPlanSetManageInit_Search();
	}
	function priPlanSetManageInit_Search(){
    	$("#priPlanSetManageInit_dataGrid").datagrid("loading");
    	$('#priPlanSetManageInit_searchform').form('submit', {
			url:'${vat}/invoicePriPlanSet/loadInvoicePriPlanSetPage.do',			
			onSubmit : function(){
				return $(this).form('enableValidation').form('validate');
			},
			success:function(result){
				var result = $.parseJSON(result);
				if(result.status=='0'){
				     $("#priPlanSetManageInit_dataGrid").datagrid('loadData', result.data);
				}else if(result.status=='1'){
					$.messager.alert('<spring:message code="system.alert"/>',result.erroMessage,'info');
				}
				$("#priPlanSetManageInit_dataGrid").datagrid("loaded"); 
		     }
		});
    }
	
	function InitCombobox(){
		//------
		$("#priPlanSetManageInit_searchform_customerName").combogrid({
			   panelWidth:200,
			   value:'',//缺省值
			   idField:"customerCode",
			   textField:"customerName",
			   url:"",
			   columns:[[  
			         {field:'customerCode',title:"<spring:message code='com.vat.base.model.customer.customerCode.text'/>",width:80},
					 {field:'customerName',title:"<spring:message code='com.vat.base.model.customer.customerName.text'/>",width:120} 
			      ]]
			  });
		//ajax提交请求获取数据
		$.ajax({  
	        url: "${vat}/customer/getCustomerNameList.do",  
	        type: "POST",  
	        async: true,  
	        data: '', //不能直接写成 {id:"123",code:"tomcat"}  
	        dataType: "json",  
	        // contentType: "charset=utf-8",  
	        cache: false,  
	        success: function (result){
				priPlanSetManageInit_clearForm();
				$('#priPlanSetManageInit_searchform_customerName').combogrid('grid').datagrid('loadData',result.data);
	        } 
	    });
		
	}
	
    //客户关系添加
    function priPlanSetManageInit_Add(){
    	priPlanSetManageInit_clearForm();
		$("#priPlanSetManageInit_add_dlg").dialog('open').dialog('setTitle',
		"<spring:message code='client.dialog.clientadd.title'/>");
		$("#priPlanSetManageInit_editform").form('load', {custRegistrationDate:myformatter(new Date())});
	} 
    function priPlanSetManageInit_Edit(){
    	priPlanSetManageInit_clearForm();
    	var row_list=$('#priPlanSetManageInit_dataGrid').datagrid('getChecked');
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
    			$.post('${vat}/invoicePriPlanSet/loadModifyInvoicePriPlanSet.do',
            			{id:id}, 
            			function(result) {
            				if(result.status=='0'){
            	        		priPlanSetManageInit_clearForm();
            	        		$("#priPlanSetManageInit_add_dlg").dialog('open').dialog('setTitle',
    								"<spring:message code='client.dialog.clientedit.title'/>");
            	        		$("#priPlanSetManageInit_editform").form('load', 
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
            	        		$(".priPlanSetManageInit_customerType").combobox('setValue', result.data.customerType);
            	        		$(".priPlanSetManageInit_legalEntityType").combobox('setValue', result.data.legalEntityType);
            	        		$(".priPlanSetManageInit_isAppointInvoice").combobox('setValue', result.data.isAppointInvoice);
            	        		$(".priPlanSetManageInit_isInvoiceProviding").combobox('setValue', result.data.isInvoiceProviding);
            	        		$(".priPlanSetManageInit_bizOrgCode").combobox('setValue', result.data.bizOrgCode);
            	        		$(".priPlanSetManageInit_bankAccountType").combobox('setValue', result.data.bankAccountType);
            	        		$(".priPlanSetManageInit_enabled").combobox('setValue', result.data.enabled);
            	        		
    							//$("#priPlanSetManageInit_editform").form('load', result.data);
            	        	}else{
            	        		$.messager.alert('<spring:message code="system.alert"/>',result.errorMessage);
        						priPlanSetManageInit_Search();
        						priPlanSetManageInit_clearForm();
        						$('#priPlanSetManageInit_add_dlg').dialog('close');
            	        	}
    					},
    					'json');
        	}else{
        		$.messager.alert('<spring:message code="system.alert"/>','编辑的主键不能为空');
        	}
    	}
    }
	function priPlanSetManageInit_Save(){
		var submit_form_container=$('#priPlanSetManageInit_editform');
		if(submit_form_container.form('enableValidation').form('validate')){
			$.messager.confirm("<spring:message code='client.datacommit.formsave.confirm.title'/>","<spring:message code='client.datacommit.formsave.confirm.text'/>",function(result){
				if (result){
					$(submit_form_container).form('submit',{
						url:'${vat}/invoicePriPlanSet/saveInvoicePriPlanSet.do',	
						onSubmit : function() {
								$.messager.progress({title:'Please waiting',
									msg:'数据保存中。。。。'});
						},			
						success:function(result){
							$.messager.progress('close');
							var result = $.parseJSON(result);
							if(result.status=='0'){	
								priPlanSetManageInit_clearForm();
								priPlanSetManageInit_Search();
								$('#priPlanSetManageInit_add_dlg').dialog('close');
								$.messager.alert('<spring:message code="system.alert"/>','保存成功');
							}
							
						} 
					});
				}
			});
		}
	}
	//删除一条记录
	
	function priPlanSetManageInit_Remove(){
		priPlanSetManageInit_clearForm();
		var row_list=$('#priPlanSetManageInit_dataGrid').datagrid('getChecked');
		//var row=$('#priPlanSetManageInit_dataGrid').datagrid('getSelected');
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
		        	        url: "${vat}/invoicePriPlanSet/removeInvoicePriPlanSets.do",  
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
		        	        	priPlanSetManageInit_Search();
		    					priPlanSetManageInit_clearForm();
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
	
	function priPlanSetManageInit_clearForm(){
		$('#priPlanSetManageInit_editform').form('clear');
	}
</script>
</html>