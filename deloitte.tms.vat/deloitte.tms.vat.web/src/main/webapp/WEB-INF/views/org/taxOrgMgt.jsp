<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Basic Form - jQuery EasyUI Demo</title>   
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.4/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.4/themes/icon.css">
	<!-- <link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.4/demo.css"> -->
	<script type="text/javascript" src="jquery-easyui-1.4.4/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
	<!-- <script type="text/javascript" src="jquery-easyui-1.4.4/jquery-1.8.0.js"></script>  -->
	<script type="text/javascript" src="common/commonjs/common.js"></script> 
	
	<%-- <%@ include file="/common/global.jsp" %> --%>
</head>


 

<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">    
    <div region="north" split="true" border="false" style="overflow: hidden; height:20%;">  
        <div class="easyui-panel" title="<spring:message code="searchgroup"/>" style="width:100%;height:100%; background-color: #E0ECFF">		
		    <form id="searchform" method="post" >
		    	<table cellpadding="5">
		    		<tr>
		    		
		    			<td>纳税人名称:</td>
				    	<td><input class="easyui-textbox" type="text" style="width:150px;" name="legalEntityName"></input></td>
		    		
		    			<td>纳税人识别号:</td>
				    	<td><input class="easyui-textbox" type="text" style="width:150px;" name="registrationNumber" ></input></td>
				    	    				    				    		
			    		<td>
			    		  <div style="text-align:center;padding:10px">
			    	          <a href="#" id="searchbtn"  class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px" onclick="Search()"><spring:message code="button.Search"/></a>
			    	          <a href="#" class="easyui-linkbutton" style="width:80px" onclick="clearSearchForm()"><spring:message code="button.Clear"/></a>			                      
			              </div>    			   
			    		</td>
		    		</tr>		    		
		    		<tr style="display: none">
		    		   <td><input id="pageNumber" class="easyui-textbox" type="text" style="width:0px;" name="pageNumber" value=""></input></td>
		    		   <td><input id="pageSize" class="easyui-textbox" type="text" style="width:0px;" name="pageSize" value=""></input></td>
		    		</tr>		 	    	
		    	</table>
		    </form>
	    </div>
	        	         
    </div>
    <div data-options="region:'center',border:false" style="background-color: #E0ECFF">  
	    <div style="width:100%;height:100%">
	         <table class="easyui-datagrid" id="dg" title="纳税实体信息" style="width:100%;height:100%" data-options="					
					singleSelect:true,
					autoRowHeight:false,
					pagination:true,
					pageSize:10,
					remoteSort:false,
				    multiSort:true	
					">			 
		   </table>   
	   </div>		
	   <div id="dlg" class="easyui-dialog"
			style="width: 800px; height: 300px;" closed="true"
			data-options="				
				buttons: [{
					text:'<spring:message code="button.Save"/>',
					iconCls:'icon-ok',
					handler:function(){
						save();
					}
				},{
					text:'<spring:message code="button.Close"/>',
					iconCls:'icon-cancel',
					handler:function(){
						$('#dlg').dialog('close');
					}
				}]
			" >

			<div style="margin:20px 0;"></div>			
				    <form id="saveform" method="post"  commandName="tmsMdLegalEntity" >
				  
				    	<table cellpadding="10">
				    		<tr>			   		
				    			<td>纳税人名称:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" id="legalEntityName" name="legalEntityName" data-options="required:true"></input></td>
				    			<td>纳税人识别号:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="registrationNumber"　data-options="required:true"></input></td>
				    			<td>从业人数:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="numberOfEmployees" data-options="validType:'number'"></input></td>
				    		
				    		
				    		</tr>
				    		
				    		<tr>			   		
				    			<td>纳税主体代码:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;"  name="legalEntityCode" ></input></td>
				    			
				    			
				    			 
				    			<td>纳税主体类型:</td>
				    			<td>
				    			<select name="legalEntityType"　>
				    			<option value="1">一般增值税纳税人</option>
				    			<option value="2">小规模增值税纳税人</option>
				    			<option value="3">非增值税纳税人</option>
				    			
				    			</select>
				    			
				    		<!-- 	<input class="easyui-textbox" type="text" style="width:150px;" name="legalEntityType"　></input>
				    			 -->
				    			</td>
				    			
				    			
				    			<td>组织代码:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="orgCode2" ></input></td>				    		
				    		
				    		</tr>
				    		
				    <!-- 		<tr>			   		
				    			
				    			<td>描述:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;"  name="description" ></input></td>
				    			
				    			
				    			
				    		 	<td>发票开取限额:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="invoiceLimitAmount" ></input></td>				    		
				    		 
				    		</tr> -->
				    		
			<!-- 	    		<tr>			   		
				    			
				    			<td>是否缴纳汇总:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;"  name="isPaymentCollect" ></input></td>
				    			
				    			
				    			
				    			<td>描述:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;"  name="description" ></input></td>
				    			
				    			
				    			 
				    			<td>纳税识别号类型:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="registrationCode" ></input></td>				    		
				    		
				    		</tr> -->
				    		
				    		
				    		<tr>			   		
				    		<!-- 	<td>年度缴纳税额:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="annualPaymentAmount"></input></td>
				    			 -->
				    			 <td>纳税人名称(寄件人姓名):</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="taxPayerName"　 data-options="required:true"></input></td>
				    			
				    			 
				    			<td>注册地址:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="registrationContactAddress" data-options="required:true"></input></td>
				    			<td>邮政编码:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="zipCode"></input></td>
				    		</tr>
				    		
				    		<tr>			   		
				    			<td>注册地址联系电话:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="registrationContactPhone"  data-options="required:true"></input></td>
				    			<td>生产经营地址:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="contactAddress" ></input></td>
				    	<!-- 		
				    			<td>开业设定日期:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="dateOfEstablish"></input></td>
				    			 -->
				    			<td>描述:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;"  name="description" ></input></td>
				    			
				    			
				    		</tr>
				    		
				    		<tr>			   		
				    			<td>工商机关名称:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="adminIndustryCommerce"  ></input></td>
				    			<td>证照名称:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="licenseName" ></input></td>
				    			<td>证照号码:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="licenseNo"></input></td>
				    		</tr>
				    		
				    		<tr>			   		
				    			<td>国标行业(主行业):</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="gbTradePrimaryIndustry"  ></input></td>
				    			<td>国标行业(附属行业):</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="gbTradeAffiliatedIndustry" ></input></td>
				    			<td>营业税国地征收隶属关系:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="businessTaxRelation"></input></td>
				    		</tr>
				    		
				    		<tr>			   		
				    			<td>企业所得税征收隶属关系:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="incomeTaxRelation"  ></input></td>
				    			<td>注册资本投资总额:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="registCapitalInvestmen" ></input></td>
				    			<td>开户银行:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="bankBranchName"  data-options="required:true"></input></td>
				    		</tr>
				    		
				    		<tr>			   		
				    			<td>开户银行账号:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="bankAccountNum"  data-options="required:true"></input></td>
				    			<td>是否为增值税(一般纳税人):</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="isVat" ></input></td>
				    			<td>是否独立申报缴税:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="isIndependtTax"></input></td>
				    		</tr>
				    		
				    		<tr>			   		
				    			<td>登记注册类型:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="registTypeId"  ></input></td>
				    			<td>单位性质:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="companyTypeId" ></input></td>
				    			<td>机构类型:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="agencyTypeId"></input></td>
				    		</tr>
				    		
				    		<tr>			   		
				    			<td>经营范围:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="businessScopeId"  ></input></td>
				    			<td>使用会计制度:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="accountModeId" ></input></td>
				    			<td>资产关联关系:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="assetsRelationId"></input></td>
				    		</tr>
				    		
				    		<tr>			   		
				    			<td>核算方式:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="payMethodId"  ></input></td>
				    			<td>企业变更项:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="enterpriseChangeId" ></input></td>
				    			<td>集团纳税人识别号:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="groupCustRegistNum"></input></td>
				    		</tr>
				    		
				    		<tr>			   		
				    			<td>国税主管税务机关:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="stateAcgency"  ></input></td>
				    			<td>地税主管税务机关:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="loalAcgency" ></input></td>
				    			
				    			<td></td>
				    			<td></td>
				    			
				    			<!-- <td>是否启用:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="enabledFlag"></input></td>
				    			 -->
				    		</tr>
				    		
<!-- 				    		
				    		<tr>			   		
				    			<td>有效日期:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="startDate"  ></input></td>
				    			<td>失效日期:</td>
				    			<td><input class="easyui-textbox" type="text" style="width:150px;" name="endDate" ></input></td>
				    			<td></td>
				    			<td><</td>
				    		</tr>
				    		 -->

				
				    		<tr style="display: none">
				    									
							<td><input id="id" class="easyui-textbox" name="id"
								style="width: 200px"></input></td>
							</tr>	 					    		
				    			    				    			 	    	
		    	       </table>
				    </form>			   			
		</div>	
	         
    </div>

    
   
</body>
<script type="text/javascript">
	$.extend($.messager.defaults,{
	    ok:'<spring:message code="confirm"/>',
	    cancel:'<spring:message code="cancel"/>'
	});	


	$(function(){
		$('#dg').datagrid({
			url: '',			
			fitColumns: true,
			nowrap: false,
			pagination:true,
			rownumbers:true,		
			columns:[[
				{field:'legalEntityName',title:'纳税人名称',width:100,align:'center'},
				{field:'registrationNumber',title:'纳税人识别号',width:80,align:'center'},
				{field:'numberOfEmployees',title:'从业人数',width:80,align:'center'},
				{field:'annualPaymentAmount',title:'年度缴纳税额',width:100,align:'center'},
				{field:'registrationContactAddress',title:'注册地址',width:100,align:'center'},
				{field:'registrationContactPhone',title:'注册地址联系电话',width:80,align:'center'},
				
			]],
			toolbar:[
			         {text:'<spring:message code="button.Add"/>',  
                	iconCls:'icon-add',  
                	handler:function(){  
                		addTo();  
                			}  
            		},'-',{  
	                text:'<spring:message code="button.Edit"/>',  
	                iconCls:'icon-edit',  
	                handler:function(){  
	                	edit();
	                }  
	            },'-',{  
	                text:'<spring:message code="button.Remove"/>',  
	                iconCls:'icon-remove',  
	                handler:function(){  
	                	remove2();  
	                }  
	            },'-'],
			
			onClickRow:function(index,data){
				var row = $('#dg').datagrid('getSelected');
				if (row){
					//loadSaveFormData(row);
				}
			}
		});
		//设置分页控件	
		var p = $('#dg').datagrid('getPager'); 
		$(p).pagination({           
			pageSize: 10,//每页显示的记录条数，默认为10           
			//pageList: [5,10,15,20],//可以设置每页记录条数的列表           
			beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
			//afterPageText: '页',
			afterPageText : '<spring:message code="pagination.afterPageText"/>',
			displayMsg : '<spring:message code="pagination.displayMsg"/>',
			
			onSelectPage: function (pageNumber, pageSize) {//分页触发		
		    	// alert('分页触发');
				find(pageNumber,pageSize);
				
				clearSaveForm(); //??????????????needed???
				 Search(); 
				 
	         }
	
		});
		
		
	});
	

	$(document).ready(function() {
		//InitCombobox();	
		$('#searchform').form('load', {
			pageNumber : $('#dg').datagrid('options').pageNumber,
			pageSize : $('#dg').datagrid('options').pageSize
		});
		
			
		//initSearch();
		Search();
	});
	
	  function initSearch(){
	    	
	    
	    	
	    	$('#searchform').form('submit', {
				url:'taxOrgMgt/initSearchTaxOrg.do',	
				dataType : "json",
				onSubmit : function() {
					return $(this).form('enableValidation').form('validate');
				},
				success : function(result) {
				if(typeof(result)=='undefined'){
					
					alert('result fail');
				}else{
				   var result = $.parseJSON(result);
			       $("#dg").datagrid('loadData', result);		       
			       $("#dg").datagrid("loaded"); 
					}
			     }
			});

	    }

    
	/* function InitCombobox(){
		$('#status').combobox({
			url:'usermaintain/getstatus.do',
			valueField:'value',
			textField:'text'
		});
		$('#searchStatus').combobox({
			url:'usermaintain/getstatus.do',
			valueField:'value',
			textField:'text'
		});
		
	} */
	function find(pageNumber,pageSize){
		$('#searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
	
	}
    function Search(){ 
    	
    	//loading效果
    	//$("#dg").datagrid("loading");  
    	
    	$('#searchform').form('submit', {
			url:'taxOrgMgt/searchTaxOrg.do',			
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(result) {
			if(typeof(result)=='undefined'){	
				
			}else{
			   var result = $.parseJSON(result);
		       $("#dg").datagrid('loadData', result);		       
		       $("#dg").datagrid("loaded"); 
				}
		     }
		});

    }
	function addTo(){
		
		clearSaveForm();
		
		$('#id').textbox('setValue', '');
		
		 $('#registrationNumber').textbox('enable');
		$("#dlg").dialog('open').dialog('setTitle',
		'新增纳税实体');
		
		
	}
	function edit(){
		var row = $('#dg').datagrid('getSelected');	
		
		//alert("edit()--------:"+row);
		
		if( typeof(row)!='undefined' &&   row!=null){
			var id = row.id;
			
			$('#registrationNumber').textbox('disable');
			//alert("id:"+id);
						
			$.ajax({
                 url:"taxOrgMgt/initUpdateTaxOrg.do?id="+id,
                 cache:false,
                 dataType : "json",
                 success : function(result) {          	
                	 $('#saveform').form('load', result);
                	 $("#dlg").dialog('open').dialog('setTitle',
             		'编辑纳税实体'); 
                	 $('#registrationNumber').textbox('disable');
                 }
             });
		}else{
			$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="noselectrecord"/>');
		}
	}
	
	function save(){
		
		/**
		*to-do:  check update or create new, update call url of taxOrgMgt/updateTaxOrg.do
		*
		*/
		//alert('save() begin...')
		$('#saveform').form('submit', {	
			url:'org/addTaxOrg.do',	
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},			
			success:function(result){
				var result = $.parseJSON(result);				
				 if(result.success){	
					 
					clearSaveForm();//???????????????needed? 
					Search();
					
					
					
					
					$('#dlg').dialog('close');
				} 
				$.messager.alert('<spring:message code="system.alert"/>',result.msg);
			} 
		});
	}
	function remove2(){
		var row = $('#dg').datagrid('getSelected');	
		if(row!=null){
			var id = row.id;	
			var arguments = row.legalEntityName;
		    $.messager.confirm('<spring:message code="system.alert"/>','<spring:message code="usermaintain.delete.confirm" arguments="'+arguments+'"/>',function(result){  
				  if (result){  			      
				      $.ajax({
		                    url:"taxOrgMgt/delTaxOrg.do?id="+id,
		                    dataType : "json",
		                    cache:false,
		                    success : function(result) {	                    	
		             			 if (result.success) {	 
		             				 
		             				clearSaveForm();//needed??????????
		                        	Search();
		        					
		                        } 
		                        $.messager.alert('<spring:message code="system.alert"/>',result.msg);
		                    }
		                });
				     
				  }
			  });  		
		}else{
			$.messager.alert('<spring:message code="system.alert"/>','<spring:message code="noselectrecord"/>');
		}
		
	}



	
	
	
	function clearSaveForm(){
		$('#saveform').form('clear');
	} 
	function clearSearchForm(){
		var pageNumber = $('#pageNumber').val();
		var pageSize = $('#pageSize').val()
		$('#searchform').form('clear');
		$('#searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
	}
	
	function loadSaveFormData(row){
		$('#status').combobox('setValue', row.status);
		$('#saveform').form('load', {
			userid:row.userid,
			name  :row.name,
			email :row.email,
			fax   :row.fax,
			tel   :row.tel,
			createby:row.createby,
			createdate:row.createdate,
			updateby:row.updateby,
			updatedate:row.updatedate,
			appuseruuid:row.appuseruuid
		});
	}

	
	function pageData(list,total){
	     var obj=new Object(); 
	     obj.total=total; 
	     obj.rows=list; 
	     return obj; 
	 }
	
	function checkUserId(val){
		var userid = val.value;
		var uuid = $('#uuid').val();	
		if(userid!=""&&uuid==""){
			$.post('usermaintain/checkuserid.do', {
				userid : userid
			}, function(result) {						
				if (result.success) {					
					$.messager.alert('<spring:message code="system.alert"/>',result.msg);					
				} else {
					return;
				}
			}, 'json');
		}		
	}
	
</script>
</html>