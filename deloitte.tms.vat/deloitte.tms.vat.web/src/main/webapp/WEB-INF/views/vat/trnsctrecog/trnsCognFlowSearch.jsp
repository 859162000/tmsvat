<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/common/global.jsp"%>
<%@ taglib uri="/tags/input" prefix="input"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body class="easyui-layout" id="layoutid" style="overflow-y: hidden;">
<div class="easyui-panel" title="East" data-options="fit:true,border:false"> 
</div>
	<div data-options="region:'center',iconCls:'icon-ok'" title="交易认定后结果查询">
		<div style="overflow: hidden; height: 99%;">
			<div class="easyui-panel" title=""
				style="width: 100%; background-color: #E0ECFF">
				<form id="_searchform" method="post"
					commandName="">
					<table>
						<tr>
							<td>组织：</td>
							<td><select id=""
								name="" class="easyui-combogrid"
								style="width: 150px;"></select></td>
							<td>来源系统：</td>
							<td><select id=""
								name="" class="easyui-combogrid"
								style="width: 150px;"></select></td>
							<td>客户名称：</td>
							<td><select id=""
								name="" class="easyui-combogrid"
								style="width: 150px;"></select></td>
							<td>涉税交易类型：</td>
							<td><select id=""
								name="" class="easyui-combogrid"
								style="width: 150px;"></select></td>
							<td>
		
								<a href="#"
								id="searchbtn" class="easyui-linkbutton" style="width: 70px"							
								onclick="clearSearchform()">清空查询</a>
								
							</td>
						</tr>
						<tr>
							<td>交易批次号：</td>
							<td><select id=""
								name="customerName" class="easyui-combogrid"
								style="width: 150px;"></select></td>
							<td>交易流水号：</td>
							<td><select id=""
								name="customerName" class="easyui-combogrid"
								style="width: 150px;"></select></td>
							<td>记账开始日期：</td>
							<td><input id="" class="easyui-datebox"
								width="90" name="deliveryDate" /></td>
							<td>记账结束日期：</td>
							<td><input id="" class="easyui-datebox"
								width="90" name="deliveryDate" /></td>
							<td>
								 <a href="#"
								id="searchbtn" class="easyui-linkbutton" style="width: 70px"
								data-options="iconCls:'icon-search'" 
								onclick="Search()"><spring:message
										code='client.search.button.find' /></a>
							</td>	
						</tr>
						<tr style="display: none">
							<td><input id="pageNumberSend" class="easyui-textbox"
								type="text" style="width: 0px;" name="pageNumber" value=""></input></td>
							<td><input id="pageSizeSend" class="easyui-textbox"
								type="text" style="width: 0px;" name="pageSize" value=""></input></td>
						</tr>
					</table>
				</form>
			</div>
			<div style="width: 100%; height: 80%">
				<table class="easyui-datagrid" id="_dataGrid"
					title="交易认定后结果查询列表" style="width: 100%; height: 86%"
					data-options="					
					singleSelect:false,
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
<div data-options="region:'east',split:false"
		style="width: 100%;" border:0>
</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#layoutid").layout('collapse', 'east');

	});
	$(document).ready(
			function() {
				pageDataSettingInit();
				$('#_searchform').form(
						'load',
						{
							pageNumber : $('#_dataGrid').datagrid(
									'options').pageNumber,
							pageSize : $('#_dataGrid').datagrid(
									'options').pageSize
						});
				Search();
				
			});
	var entityId = '';//全局变量，记录实体Id
	//页面表格绑定初始化
	function pageDataSettingInit() {
		$('#_dataGrid').datagrid({
			url : '',
			loadMsg : "<spring:message code='client.datagrid.dataloding'/>",
			striped : true,//奇偶行颜色不同
			singleSelect : false, //多选
			collapsible : false,//可折叠  
			fitColumns : false,//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			nowrap : false,
			iconCls : 'icon icon-list',
			pagination : true, //显示分页 
			rownumbers : true, //显示行号
			idField : 'id',//主键字段		
			columns : [ [ {
				field : 'ck',
				checkbox : true,
				width : 2
			}, //显示复选框   
			{
				field : 'expressCompany',
				title : "序号",
				width : 80,
				align : 'center'
			}, {
				field : 'expressCompany',
				title : "组织",
				width : 80,
				align : 'center'
			}, {
				field : 'expressCompany',
				title : "所属期间",
				width : 60,
				align : 'center'
			}, {
				field : 'expressCompany',
				title : "交易批次号",
				width : 80,
				align : 'center'
			}, {
				field : 'expressCompany',
				title : "交易分录行号",
				width : 60,
				align : 'center'
			}, {
				field : 'compAddr',
				title : "来源系统",
				width : 60,
				align : 'center'
			}, {
				field : 'compAddr',
				title : "交易时间",
				width : 80,
				align : 'center'
			}, {
				field : 'compAddr',
				title : "记账日期",
				width : 80,
				align : 'center'
			}, {
				field : 'deliveryBy',
				title : "业务数据结构",
				width : 100,
				align : 'center'
			}, {
				field : 'expressCompany',
				title : "业务数据结构说明",
				width : 100,
				align : 'center'
			} 
			, {
				field : 'expressCompany',
				title : "科目组合编码",
				width : 100,
				align : 'center'
			} 
			, {
				field : 'expressCompany',
				title : "科目组合说明",
				width : 100,
				align : 'center'
			} 
			, {
				field : 'expressCompany',
				title : "涉税交易类型名称",
				width : 100,
				align : 'center'
			} 
			, {
				field : 'expressCompany',
				title : "税率（%）",
				width : 80,
				align : 'center'
			} , {
				field : 'expressCompany',
				title : "是否计税",
				width : 80,
				align : 'center'
			} , {
				field : 'expressCompany',
				title : "开票规则",
				width : 80,
				align : 'center'
			} , {
				field : 'expressCompany',
				title : "属地/汇总",
				width : 80,
				align : 'center'
			} , {
				field : 'expressCompany',
				title : "计税基础",
				width : 80,
				align : 'center'
			} , {
				field : 'expressCompany',
				title : "计税方法",
				width : 80,
				align : 'center'
			}, {
				field : 'expressCompany',
				title : "原币币种",
				width : 80,
				align : 'center'
			}, {
				field : 'expressCompany',
				title : "原币借方金额",
				width : 80,
				align : 'center'
			}, {
				field : 'expressCompany',
				title : "原币贷方金额",
				width : 80,
				align : 'center'
			}
			, {
				field : 'expressCompany',
				title : "核算汇率",
				width : 80,
				align : 'center'
			}, {
				field : 'expressCompany',
				title : "本位币币种",
				width : 80,
				align : 'center'
			}, {
				field : 'expressCompany',
				title : "本位币借方金额",
				width : 80,
				align : 'center'
			}, {
				field : 'expressCompany',
				title : "本位币贷方金额",
				width : 80,
				align : 'center'
			}, {
				field : 'expressCompany',
				title : "开票汇率",
				width : 80,
				align : 'center'
			}, {
				field : 'expressCompany',
				title : "汇率日期",
				width : 80,
				align : 'center'
			}, {
				field : 'expressCompany',
				title : "客户名称",
				width : 80,
				align : 'center'
			}, {
				field : 'expressCompany',
				title : "导入日期",
				width : 80,
				align : 'center'
			}
			 
			]],
			toolbar : [ {
				text : "交易认定",
				iconCls : 'icon-add',
				handler : function() {
					addPre();
				}
			}, '-', {
				text : "交易认定回滚",
				iconCls : 'icon-edit',
				handler : function() {
					editPre();
				}
			}, '-'],
			onDblClickRow : function(index, data) {
				alert("双击表格显示详情功能正在建设中。。。。");
			},
			onLoadSuccess : function() {
				$('#invoiceSend_dataGrid').datagrid('clearSelections');   
			}
		});

	}

	function Search() {
		$("#_dataGrid").datagrid("loading");
		$('#_searchform').form('submit', {
			url : '${vat}/trnsctrecog/loadTrnsCogFlowSearchPage.do',
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(result) {
				//数据加载以及绑定
				var result = $.parseJSON(result);
				$("#_dataGrid").datagrid('loadData', result);
				$("#_dataGrid").datagrid("loaded");
			}
		});
	}
	
	function clearSearchform() {
		$('#_searchform').form('clear');
	}
	//alert($('#_dataGrid').datagrid('getPager'));
	//设置分页控件	
	var p = $('#_dataGrid').datagrid('getPager');
	alert($('#_dataGrid'));
	$(p).pagination({
		pageSize: 10,//每页显示的记录条数，默认为10           
		beforePageText: '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
		afterPageText: '<spring:message code="pagination.afterPageText"/>',           
		displayMsg: '<spring:message code="pagination.displayMsg"/>',
		onSelectPage: function (pageNumber, pageSize) {//分页触发	
		     alert('');
	    	 find(pageNumber,pageSize);
			 Search(); 
         }
	});
	
	function find(pageNumber, pageSize) {
		$('#_searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
	}
</script>
</html>