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
	<div data-options="region:'center',iconCls:'icon-ok'" title="发票寄送查找条件">
		<div class="easyui-layout" style="width: 100%; height: 100%;">
			<div data-options="region:'north'" style="width: 100%; height: 15%">
				<div class="easyui-panel"
					style="width: 100%; height: 100%; background-color: #E0ECFF">
					<form id="customerFormForEveryCombox" method="post">
						<table>
							<tr style="display: none">
								<td><input id="pageNumberforCombogrid"
									class="easyui-textbox" type="text" style="width: 0px;"
									name="pageNumber" value=""></input></td>
								<td><input id="pageSizeforCombogrid" class="easyui-textbox"
									type="text" style="width: 0px;" name="pageSize" value=""></input></td>
							</tr>
						</table>
					</form>
					<form id="invoiceSend_searchform" method="post"
						commandName="invoiceSendHInParam">
						<table>
							<tr>
								<td>寄送单号：</td>
								<td><input id="expressNoSendId" class="easyui-textbox"
									type="text" style="width: 120px;" name="expressNo" value=""></input></td>
								<td>客户名称：</td>
								<td><select id="customerNameListInSendForm"
									name="customerName" class="easyui-combogrid"
									style="width: 120px;"></select></td>
								<td>寄件日期：</td>
								<td><input id="deliveryDateSendId" class="easyui-datebox"
									style="width: 120px" name="deliveryDate" /></td>
								<td>寄件状态：</td>
								<td><select name="deliveryStatus" class="easyui-combobox"
									style="width: 120px">
										<option value="NotSet"></option>
										<option value="NotSend">未发</option>
										<option value="Sending">已发</option>
										<option value="success">已签收</option>
										<option value="failure">投递失败</option>
								</select></td>

							</tr>
							<tr>
								<td>寄件人：</td>
								<td><select name="deliveryBy" class="easyui-combobox"
									style="width: 120px">
										<option value="NotSet"></option>
										<option value="a">中信北京</option>
										<option value="b">中信上海</option>
										<option value="c">中信广州</option>
										<option value="d">中信深圳</option>
								</select></td>
								<td>快递公司：</td>
								<td><select name="expressCompany" class="easyui-combobox"
									style="width: 120px">
										<option value="NotSet"></option>
										<option value="SF">顺丰</option>
										<option value="EMS">EMS</option>

								</select></td>
								<td><a href="#" id="searchbtn" class="easyui-linkbutton"
									data-options="iconCls:'icon-search'" style="width: 80px"
									onclick="Search()"><spring:message
											code='client.search.button.find' /></a></td>
								<td align="left"><a href="#" class="easyui-linkbutton"
									style="width: 100px" onclick="clearSendForm()">重置</a></td>

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
			</div>
			<div data-options="region:'center'" style="width: 100%; height: 80%">
				<div style="width: 100%; height: 100%">
					<table class="easyui-datagrid" id="invoiceSend_dataGrid"
						title="发票寄送管理" style="width: 100%; height: 100%"
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
		<div class="easyui-dialog" id="chooseSendType_dialog" title="选择寄送类型"
			style="width: 500px; height: 300px" closed="true">
			<form id="chooseSendType_form">
				<div>
					<table>
						<tr>
							<td>寄送类型</td>
							<td><input type="radio" id="internalIsSelected"
								name="sendType" value="internal" checked
								onclick="changeTypeToInternal()" /> 内部寄送</td>
							<td><input type="radio" id="externalIsSelected"
								name="sendType" value="external"
								onclick="changeTypeToExternal()" /> 外部寄送</td>
							<td></td>
						</tr>
					</table>
				</div>
				<div id="customerCodeDiv" style="display: none">
					<table>
						<tr>
							<td>请选择纳税主体</td>
							<td><select id="taxEntitySearchBoxInDialog"
								name="customerName" class="easyui-combogrid"
								style="width: 150px;"></select></td>

						</tr>
					</table>
				</div>
				<div id="legalEntityIdDiv" style="display: block">
					<table>
						<tr>
							<td>请选择客户</td>
							<td><select id="customerSearchBoxInDialog"
								name="customerName" class="easyui-combogrid"
								style="width: 150px;"></select></td>
						</tr>
					</table>
				</div>

				<div style="text-align: center; padding: 10px">

					<a href="#" class="easyui-linkbutton" style="width: 100px"
						onclick="makeSureChangeType()">确认</a> <a href="#"
						class="easyui-linkbutton" style="width: 100px"
						onclick="CancelChangetype()">取消</a>
				</div>
			</form>
		</div>
	</div>
	<div data-options="region:'east',split:false" title="添加发票寄送单信息"
		style="width: 100%;" border:0>
		<div class="easyui-layout" style="width: 100%; height: 100%;">
			<div data-options="region:'north'" style="width: 100%; height: 50%">
				<div class="easyui-panel" style="width: 100%; height: 100%;">
					<form id="addInvoiceSend_searchform" method="post"
						commandName="invoiceSendHInParam">
						<div class="easyui-panel" style="border: 0px" title="快递信息">
							<table>
								<tr>
									<td>寄送单流水号：</td>
									<td><input id="deliveryNumberId" class="easyui-textbox"
										type="text" style="width: 150px;" name="deliveryNumber"></input></td>
									<td>登记日期：</td>
									<td><input id="bookedDateIdInSend" class="easyui-datebox"
										style="width: 150px;" name="bookedDate"
										data-options="formatter:myformatter,parser:myparser,required:false" />

									</td>
									<td>寄送单号：</td>
									<td><input id="expressNoId" class="easyui-textbox"
										type="text" style="width: 150px;" name="expressNo"></input></td>

									<td></td>
									<td></td>
								</tr>
								<tr>
									<td>寄送日期：</td>
									<td><input id="deliveryDateIdInAdd" class="easyui-datebox"
										style="width: 150px;" name="deliveryDate"
										data-options="formatter:myformatter,parser:myparser,required:false" />

									</td>

									<td>快递公司：</td>
									<td><select name="expressCompany" class="easyui-combobox"
										style="width: 150px">
											<option value="NotSet"></option>
											<option value="SF">顺丰</option>
											<option value="EMS">EMS</option>

									</select></td>
									<td>纳税主体：</td>
									<td><input id="legalEntityIdInAddVoice"
										class="easyui-textbox" type="text" style="width: 150px;"
										name="legalEntityId"></input></td>
								</tr>
							</table>
						</div>
						<div title="寄件方信息" class="easyui-panel" style="border: 1px">
							<table>
								<tr>
									<td><p style="width: 90px;">寄件公司：</p></td>
									<td>
										<!-- 	<input id="recipientCompanyId" class="easyui-textbox"
							type="text" style="width: 150px;" name="recipientCompany">
							</input> -->
									</td>
									<td><p style="width: 80px;">联络人：</p></td>
									<td>
										<!-- <input id="recipientNameId" class="easyui-textbox"
							type="text" style="width: 150px;" name="recipientName"></input> -->
									</td>
									<td><p style="width: 80px;">地址：</p></td>
									<td>
										<!-- <input id="recipientAddrId" class="easyui-textbox"
							type="text" style="width: 150px;" name="recipientAddr"></input> -->

									</td>
									<td><p style="width: 80px;">联系电话：</p></td>
									<td>
										<!-- <input id="recipientPhoneId" class="easyui-textbox"
							type="text" style="width: 150px;" name="recipientPhone"></input> -->
									</td>
								</tr>
							</table>
						</div>
						<div title="收件方信息" class="easyui-panel" style="border: 1px">
							<table>
								<tr>
									<td><p style="width: 90px;">收件公司：</p></td>
									<td><input id="recipientCompanyId" class="easyui-textbox"
										type="text" style="width: 150px;" name="recipientCompany"></input>
									</td>
									<td><p style="width: 80px;">联络人：</p></td>
									<td><input id="recipientNameId" class="easyui-textbox"
										type="text" style="width: 150px;" name="recipientName"></input></td>
									<td><p style="width: 80px;">地址：</p></td>
									<td><input id="recipientAddrId" class="easyui-textbox"
										type="text" style="width: 150px;" name="recipientAddr"></input></td>
									<td><p style="width: 80px;">联系电话：</p></td>
									<td><input id="recipientPhoneId" class="easyui-textbox"
										type="text" style="width: 150px;" name="recipientPhone"></input>
									</td>
								</tr>

							</table>
						</div>



						<table>
							<tr style="display: none">
								<td><input id="ids" class="easyui-textbox" type="text"
									style="width: 150px;" name="invoiceIDs"></input></td>
								<td><input id="pageNumber" class="easyui-textbox"
									type="text" style="width: 0px;" name="pageNumber" value=""></input></td>
								<td><input id="pageSize" class="easyui-textbox" type="text"
									style="width: 0px;" name="pageSize" value=""></input></td>
								<td><input id="entityIDid" class="easyui-textbox"
									type="text" style="width: 0px;" name="entityID" value=""></input></td>
								<td><input id="invoiceSendHID" class="easyui-textbox"
									type="text" style="width: 0px;" name="id" value=""></input></td>
								<td>
									<%-- <input id="invoiceSendHID" class="easyui-textbox" type="text"
							style="width: 0px;" name="id" value=""></input> --%>
								</td>
								<td></td>
								<td></td>
							</tr>

						</table>
					</form>

				</div>
			</div>
			<div data-options="region:'center'" style="width: 100%; height: 50%">
				<div class="easyui-panel"
					style="width: 100%; height: 100%; padding: 0px;">
					<table class="easyui-datagrid" id="addInvoiceList_datagrid"
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


		<!-- 
			<div style="width: 100%; height: 100%">
					<table class="easyui-datagrid" id="invoiceSend_dataGrid"
						title="发票寄送管理" style="width: 100%; height: 100%"
						data-options="					
						singleSelect:false,
						autoRowHeight:false,
						pagination:true,
						pageSize:10,
						remoteSort:false,
					    multiSort:true	
						">
					</table>
			   </div> -->















		<div id="invoiceSend_invoiceList_dlg" class="easyui-dialog"
			style="width: 1000px; height: 550px;" closed="true">
			<form id="invoiceList_searchform" class="easyui-form" method="post"
				data-options="novalidate:true">
				<table>
					<tr>
						<td>发票代码:</td>
						<td><input class="easyui-textbox" id="invoiceCodeId"
							type="text" name=invoiceCode /></td>
						<td>发票号码:</td>
						<td><input class="easyui-textbox" id="invoiceNumberId"
							type="text" name="invoiceNumber" /></td>
						<td>开具日期:</td>
						<td><input id="invoicePrintDateIdInVoiceList"
							class="easyui-datebox" width="90" name="invoicePrintDate" /></td>
						<td>发票开具人:</td>
						<td><select name="invoicePrintBy" class="easyui-combobox"
							style="width: 120px">
								<option value="NotSet"></option>
								<option value="NotSend">管理员</option>
								<option value="Sending">张三</option>
								<option value="success">李四</option>
								<option value="failure">王五</option>
						</select>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td><a href="javascript:void(0)" class="easyui-linkbutton"
							data-options="iconCls:'icon-search'" onclick="invoiceSearch()">发票查询</a></td>
						<td><a href="javascript:void(0)" class="easyui-linkbutton"
							onclick="clearInvoiceList_searchform()">重置</a></td>
						<td><a href="javascript:void(0)" class="easyui-linkbutton"
							data-options="iconCls:'icon-back'" onclick="clearInvoiceForm()">返回上级菜单</a></td>

						<td></td>
						<td></td>
					</tr>

				</table>
			</form>
			<table class="easyui-datagrid" id="invoiceList_datagrid" title="发票信息"
				style="width: 100%; height: 88%"
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
	$(document).ready(function() {
		$("#layoutid").layout('collapse', 'east');
		//$('#layoutid').layout('setRegionToolVisableState',{region:'east',visible:false}); 

	});
	$(document).ready(
			function() {
				pageDataSettingInit();
				//	InitCombobox();
				$('#invoiceSend_searchform').form(
						'load',
						{
							pageNumber : $('#invoiceSend_dataGrid').datagrid(
									'options').pageNumber,
							pageSize : $('#invoiceSend_dataGrid').datagrid(
									'options').pageSize
						});
				Search();
				//InitCombobox(); //初始化下拉列表
			});
	var entityId = '';//全局变量，记录实体Id
	//页面表格绑定初始化
	function pageDataSettingInit() {
		$('#invoiceSend_dataGrid').datagrid({
			//method:'post',  
			//iconCls:'icon-edit', //图标  
			//height:360, //高度  
			//queryParams:{}, //查询条件 
			//title:'用户列表', //标题
			url : '',
			loadMsg : "<spring:message code='client.datagrid.dataloding'/>",
			striped : true,//奇偶行颜色不同
			singleSelect : false, //多选
			collapsible : false,//可折叠  
			fitColumns : true,//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
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
				field : 'deliveryNumber',
				title : "寄送单流水号",
				width : 80,
				align : 'center'
			}, {
				field : 'expressNo',
				title : "寄送单号",
				width : 80,
				align : 'center'
			}, {
				field : 'printStatus',
				title : "打印状态",
				width : 60,
				align : 'center'
			}, {
				field : 'customerName',
				title : "客户名称",
				width : 80,
				align : 'center'
			}, {
				field : 'bookedDate',
				title : "登记日期",
				width : 80,
				align : 'center',
				formatter : function(value) {
					if (typeof (value) != "undefined" && value != 0) {
						return myformatter(new Date(value));
					}
					//return value;
				}
			}, {
				field : 'deliveryDate',
				title : "寄件日期",
				width : 80,
				align : 'center',
				formatter : function(value) {
					if (typeof (value) != "undefined" && value != 0) {
						return myformatter(new Date(value));
					}
					//return value;
				}
			}, {
				field : 'deliveryStatus',
				title : "寄件状态",
				width : 60,
				align : 'center'
			}, {
				field : 'compAddr',
				title : "内含发票张数",
				width : 60,
				align : 'center'
			}, {
				field : 'compAddr',
				title : "发票总金额",
				width : 80,
				align : 'center'
			}, {
				field : 'compAddr',
				title : "发票总税额",
				width : 80,
				align : 'center'
			}, {
				field : 'deliveryBy',
				title : "寄件人",
				width : 80,
				align : 'center'
			}, {
				field : 'expressCompany',
				title : "快递公司",
				width : 80,
				align : 'center'
			} ] ],
			toolbar : [ {
				text : "添加快递单",
				iconCls : 'icon-add',
				handler : function() {
					addPre();/*
										 $("#invoiceImportButton").linkbutton("disable"); //没有保存快递单之前禁用添加发票按钮
										 $("#invoiceSaveButton").linkbutton("disable"); //
										 $("#invoiceDeleteButton").linkbutton("disable"); //
										 
					 */
				}
			}, '-', {
				text : "修改快递单",
				iconCls : 'icon-edit',
				handler : function() {
					editPre();
				}
			}, '-', {
				text : "删除快递单",
				iconCls : 'icon-remove',
				handler : function() {
					invoiceRemove();
				}
			}, '-' ],
			onDblClickRow : function(index, data) {
				alert("双击表格显示详情功能正在建设中。。。。");
				/* var row = $('#invoiceSend_dataGrid').datagrid('getSelected');
				if (row){
					loadSaveFormData(row);
				} */
			},
			onLoadSuccess : function() {
				$('#invoiceSend_dataGrid').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
			}
		});

		//设置分页控件	
		var p1 = $('#invoiceSend_dataGrid').datagrid('getPager');//invoiceSend_dataGrid
		//alert(8);
		$(p1)
				.pagination(
						{
							pageSize : 10,//每页显示的记录条数，默认为10           
							//pageList: [5,10,15,20],//可以设置每页记录条数的列表           
							beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
							afterPageText : '<spring:message code="pagination.afterPageText"/>',
							displayMsg : '<spring:message code="pagination.displayMsg"/>',
							onSelectPage : function(pageNumber, pageSize) {//分页触发		
								find(pageNumber, pageSize);
								Search();
								clearSaveForm();
							}
						});
	}

	function initAddInvoiceTable() {
		$('#addInvoiceList_datagrid').datagrid({//addInvoiceList_datagrid
			//method:'post',  
			//iconCls:'icon-edit', //图标  
			//height:360, //高度  
			//queryParams:{}, //查询条件 
			//title:'用户列表', //标题
			url : '',
			loadMsg : "发票信息列表",
			striped : true,//奇偶行颜色不同

			singleSelect : false, //多选
			collapsible : false,//可折叠  
			fitColumns : true,//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
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
				field : 'invoiceCode',
				title : "发票代码",
				width : 80,
				align : 'center'
			}, {
				field : 'invoiceNumber',
				title : "发票号码",
				width : 80,
				align : 'center'
			}, {
				field : 'invoicePrintDate',
				title : "开票日期",
				width : 60,
				align : 'center',
				formatter : function(value) {
					if (typeof (value) != "undefined" && value != 0) {
						return myformatter(new Date(value));
					}
					//return value;
				}
			}, {
				field : 'invoicePrintBy',
				title : "发票开具人",
				width : 60,
				align : 'center'
			} ] ],
			toolbar : [ {
				text : "添加发票",
				id : "invoiceImportButton",
				iconCls : 'icon-add',
				handler : function() {
					addInvoiceList();
				}
			}, '-', {
				text : "保存",
				id : "invoiceSaveButton",
				iconCls : 'icon-save',
				handler : function() {
					invoiceEditAddSave();
				}
			}, '-', {
				text : "删除",
				id : "invoiceDeleteButton",
				iconCls : 'icon-remove',
				handler : function() {
					invoiceListDelete();
				}
			}, '-', {
				text : "返回上级菜单",
				iconCls : 'icon-back',
				handler : function() {
					backToTop();
				}
			}, '-', {
				text : "打印寄送单",
				iconCls : 'icon-print',
				handler : function() {
					print();
				}
			}, '-' ],
			onDblClickRow : function(index, data) {
				alert("双击表格显示详情功能正在建设中。。。。");
				/* var row = $('#invoiceSend_dataGrid').datagrid('getSelected');
				if (row){
					loadSaveFormData(row);
				} */
			},
			onLoadSuccess : function() {
				$('#addInvoiceList_datagrid').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
			}
		});

		//设置分页控件	
		var p2 = $('#addInvoiceList_datagrid').datagrid('getPager');
		//	alert(9);
		$(p2)
				.pagination(
						{
							pageSize : 10,//每页显示的记录条数，默认为10           
							//pageList: [5,10,15,20],//可以设置每页记录条数的列表           
							beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
							afterPageText : '<spring:message code="pagination.afterPageText"/>',
							displayMsg : '<spring:message code="pagination.displayMsg"/>',
							onSelectPage : function(pageNumber, pageSize) {//分页触发		
								//find(pageNumber, pageSize);
								//getInvoiceList();
								//clearSaveForm();
							}
						});
	}

	function invoiceListDelete() {

		$.messager
				.confirm(
						"<spring:message code='client.datacommit.delete.confirm.title'/>",
						"<spring:message code='client.datacommit.delete.confirm.text'/>",
						function(result) {
							if (result) {
								var row_list = $('#addInvoiceList_datagrid')
										.datagrid('getChecked');
								//var row=$('#invoiceSend_dataGrid').datagrid('getSelected');
								if (row_list) {
									var urlString = "";
									$.each(row_list, function(index, item) {
										if (index == 0)
											urlString += "id=" + item.id;
										else
											urlString += "&id=" + item.id;
									});
									if (urlString != '') {
										$
												.ajax({//removeInvoiceSendHs
													url : "${vat}/invoiceSendL/removeInvoiceSendLs.do",
													type : "POST",
													async : true,
													data : urlString, //不能直接写成 {id:"123",code:"tomcat"}  
													dataType : "json",
													// contentType: "charset=utf-8",  
													cache : false,
													success : function(result) {
														loadAddVoiceList()
														//Search();
														//	clearSaveForm();
													}
												});
									}
								}
							}

						});

	}
	function backToTop() {
		$('#invoiceSend_editform').form('clear');
		$("#layoutid").layout('collapse', 'east');
		pageDataSettingInit();
		Search();
	}
	function invoiceEditAddSave() {
		//把当前table里面勾选的数据id 放到form里面
		var row_list = $('#invoiceList_datagrid').datagrid('getChecked');
		if (row_list) {
			var id = '';
			$.each(row_list, function(index, item) {
				if (index == 0) {
					id = item.id;
				}
			});
			$("#addInvoiceSend_searchform").form('load', {
				invoiceIDs : id
			});
		}
		$('#addInvoiceSend_searchform').form(
				'load',
				{
					pageNumber : $('#addInvoiceList_datagrid').datagrid(
							'options').pageNumber,
					pageSize : $('#addInvoiceList_datagrid')
							.datagrid('options').pageSize,

				});
		$('#addInvoiceSend_searchform').form(
				'submit',
				{
					url : '${vat}/invoiceSend/saveInvoiceSendH.do',
					success : function(result) {
						$("#invoiceImportButton").linkbutton("enable"); //没有保存快递单之前禁用添加发票按钮
						//$("#invoiceSaveButton").linkbutton("disable"); //
						$("#invoiceDeleteButton").linkbutton("enable"); //
						$.messager
								.alert('<spring:message code="system.alert"/>',
										'保存成功');
					}
				});
	}
	function initInvoiceListTable() {
		//	alert(3);
		$('#invoiceList_datagrid').datagrid({
			//method:'post',  
			//iconCls:'icon-edit', //图标  
			//height:360, //高度  
			//queryParams:{}, //查询条件 
			//title:'用户列表', //标题
			url : '',
			loadMsg : "发票信息列表",
			striped : true,//奇偶行颜色不同
			singleSelect : true, //多选
			collapsible : false,//可折叠  
			fitColumns : true,//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
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
				field : 'invoiceCode',
				title : "发票代码",
				width : 50,
				align : 'center'
			}, {
				field : 'invoiceCode',
				title : "发票号码",
				width : 50,
				align : 'center'
			}, {
				field : 'invoicePrintDate',
				title : "开票日期",
				width : 50,
				align : 'center',
				formatter : function(value) {
					if (typeof (value) != "undefined" && value != 0) {
						return myformatter(new Date(value));
					}
					//return value;
				}
			}, {
				field : 'invoicePrintBy',
				title : "净额",
				width : 40,
				align : 'center'
			}, {
				field : 'invoicePrintBy',
				title : "税额",
				width : 40,
				align : 'center'

			}, {
				field : 'invoicePrintBy',
				title : "价税合计",
				width : 40,
				align : 'center'

			}, {
				field : 'invoicePrintBy',
				title : "发票开具人",
				width : 40,
				align : 'center'
			} ] ],
			toolbar : [ {
				text : "导入发票",
				iconCls : 'icon-add',
				handler : function() {
					addInvoiceDetails();
				}
			}, '-' ],
			onDblClickRow : function(index, data) {
				alert("双击表格显示详情功能正在建设中。。。。");
				/* var row = $('#invoiceSend_dataGrid').datagrid('getSelected');
				if (row){
					loadSaveFormData(row);
				} */
			},
			onLoadSuccess : function() {
				$('#invoiceList_datagrid').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题  
			}
		});

		//设置分页控件	
		var p3 = $('#invoiceList_datagrid').datagrid('getPager');
		//	alert(10);
		$(p3)
				.pagination(
						{
							pageSize : 10,//每页显示的记录条数，默认为10           
							//pageList: [5,10,15,20],//可以设置每页记录条数的列表           
							beforePageText : '<spring:message code="pagination.beforePageText"/>',//页数文本框前显示的汉字           
							afterPageText : '<spring:message code="pagination.afterPageText"/>',
							displayMsg : '<spring:message code="pagination.displayMsg"/>',
							onSelectPage : function(pageNumber, pageSize) {//分页触发		
								//find(pageNumber, pageSize);
								//getInvoiceList();
								//clearSaveForm();
							}
						});
	}
	function addInvoiceList() {
		//clearSaveForm();
		$("#invoiceSend_invoiceList_dlg").dialog('open').dialog('setTitle',
				"<spring:message code='client.dialog.clientadd.title'/>");
		$("#invoiceList_searchform").form('load', {
		//clientDate : myformatter(new Date())
		});
		initInvoiceListTable();
		loadInvoiceList();

	}
	function loadInvoiceList() {
		$("#invoiceList_datagrid").datagrid("loading");
		$('#invoiceList_searchform').form('submit', {
			url : '${vat}/invoiceSendL/loadInvoiceSendLPage.do',
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(result) {
				//数据加载以及绑定
				var result = $.parseJSON(result);
				$("#invoiceList_datagrid").datagrid('loadData', result);
				$("#invoiceList_datagrid").datagrid("loaded");
			}
		});
	}
	function addInvoiceDetails() {
		if ($('#invoiceList_datagrid').datagrid('getChecked').length == 0) {
			$.messager.alert("操作提示", "请选择需要导入的发票数据");
		} else {
			var row_list = $('#invoiceList_datagrid').datagrid('getChecked');
			if (row_list) {
				var id = '';
				$.each(row_list, function(index, item) {
					if (index == 0) {
						id = item.id;
					}
				});
				if (id != '') {
					$
							.post(
									'${vat}/invoiceSendL/loadVoicesToTable.do',
									{
										id : id
									},
									function(result) {
										//alert(result);
										//var result = $.parseJSON(result);
										if (result.success == 'true') {

											/* $("#addInvoiceList_datagrid").datagrid('loadData',
													result); */
											$("#addInvoiceList_datagrid")
													.datagrid("loaded");
											//	alert(11);
											$('#invoiceSend_invoiceList_dlg')
													.dialog('close');//关闭导入页面
											$.messager
													.alert(
															'<spring:message code="system.alert"/>',
															'导入成功');
											//根据当前页面头ID重新查询数据:invoiceSendHID 开始
											var invoiceSendHID = $("#invoiceSendHID").value;
											alert(invoiceSendHID);
											if (invoiceSendHID != null) {
														$.post(
																'${vat}/invoiceSend/getModifyInvoiceSendH.do',
																{
																	id : invoiceSendHID
																},
																function(result) {
																	if (result.success == 'true') {

																		$(
																				"#addInvoiceSend_searchform")
																				.form(
																						'load',
																						result.editInvoiceSendHForm);//得到数据库的数据信息*/
																		loadAddVoiceList();
																	} else if (result.success == 'false') {
																		$.messager
																				.alert(
																						'<spring:message code="system.alert"/>',
																						result.errorMsg);
																		Search();
																	}
																}, 'json');
											}
											//  //根据当前页面头ID重新筛选数据:invoiceSendHID 结束
										} else if (result.success == 'false') {
											$.messager
													.alert(
															'<spring:message code="system.alert"/>',
															result.errorMsg);
											//Search();
										}
									}, 'json');
				} else {
					$.messager.alert('<spring:message code="system.alert"/>',
							'请选择需导入的数据');
				}
			}
		}
	}
	function addPre() {
		$("#chooseSendType_dialog").dialog('open')
				.dialog('setTitle', "请选择寄送类型");
		$("#chooseSendType_form").form('load', {
		//clientDate : myformatter(new Date())
		});
		//initCientInDiaLog();
		changeTypeToInternal();//初始化纳税主体的下拉列表

	}

	function makeSureChangeType() {
		$('#chooseSendType_dialog').dialog('close');
		$('#layoutid').layout('expand', 'east');
		//clearSaveForm();
		loadAddVoiceForm();
		initAddInvoiceTable();
		$("#invoiceImportButton").linkbutton("disable"); //没有保存快递单之前禁用添加发票按钮
		//$("#invoiceSaveButton").linkbutton("disable"); //
		$("#invoiceDeleteButton").linkbutton("disable"); //
	}

	function loadAddVoiceForm() {
		//addInvoiceSend_searchform
		$('#addInvoiceSend_searchform').form(
				'load',
				{//customerSearchBoxInDialog
					legalEntityId : $('#taxEntitySearchBoxInDialog').combogrid(
							'getValue')
				});

		//	$("#customerNameInAddInvoiceForm").textbox('setValue', $('#customerSearchBoxInDialog').combogrid(
		//'getValue'));

	}
	function CancelChangetype() {
		$('#chooseSendType_dialog').dialog('close');
	}
	function editPre() {
		if (($('#invoiceSend_dataGrid').datagrid('getChecked').length > 1)
				|| ($('#invoiceSend_dataGrid').datagrid('getChecked').length == 0)) {
			$.messager.alert("操作提示", "请选择一条数据进行修改");
		} else {
			var row = $('#invoiceSend_dataGrid').datagrid('getSelected');
			entityId = row.id;
			$('#layoutid').layout('expand', 'east');//打开编辑页面
			initAddInvoiceTable();//初始化table
			var row_list = $('#invoiceSend_dataGrid').datagrid('getChecked');
			if (row_list) {
				var id = '';
				$.each(row_list, function(index, item) {
					if (index == 0) {
						id = item.id;
					}
				});
				if (id != '') {
					$.post('${vat}/invoiceSend/getModifyInvoiceSendH.do', {
						id : id
					}, function(result) {
						if (result.success == 'true') {

							$("#addInvoiceSend_searchform").form('load',
									result.editInvoiceSendHForm);//得到数据库的数据信息*/
							loadAddVoiceList();
						} else if (result.success == 'false') {
							$.messager.alert(
									'<spring:message code="system.alert"/>',
									result.errorMsg);
							Search();
						}
					}, 'json');
				} else {
					$.messager.alert('<spring:message code="system.alert"/>',
							'请选择需要编辑的数据');
				}
			}

		}
	}

	function loadAddVoiceList() {//初始化分页参数和 当前单号ID
		//clearInvoiceList_searchform();
		$('#addInvoiceSend_searchform').form(
				'load',
				{
					pageNumber : $('#addInvoiceList_datagrid').datagrid(
							'options').pageNumber,
					pageSize : $('#addInvoiceList_datagrid')
							.datagrid('options').pageSize,
					entityID : entityId
				});
		//加载发票数据
		//	alert(0);
		//alert(entityId);
		$("#addInvoiceList_datagrid").datagrid("loading");
		$('#addInvoiceSend_searchform').form('submit', {
			url : '${vat}/invoiceSendL/loadInvoiceSendL.do',
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(result) {
				//数据加载以及绑定
				var result = $.parseJSON(result);
				$("#addInvoiceList_datagrid").datagrid('loadData', result);
				$("#addInvoiceList_datagrid").datagrid("loaded");
			}
		});
	}

	function detail() {
		$('#layoutid').layout('expand', 'east');
	}
	function find(pageNumber, pageSize) {
		$('#invoiceSend_searchform').form('load', {
			pageNumber : pageNumber,
			pageSize : pageSize
		});
		Search();
	}
	function Search() {
		$("#invoiceSend_dataGrid").datagrid("loading");
		$('#invoiceSend_searchform').form('submit', {
			url : '${vat}/invoiceSend/loadInvoiceSendHPage.do',
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(result) {
				//数据加载以及绑定
				var result = $.parseJSON(result);
				$("#invoiceSend_dataGrid").datagrid('loadData', result);
				$("#invoiceSend_dataGrid").datagrid("loaded");
			}
		});
	}

	function InitCombobox() {

		//------
		$("#customerNameListInSendForm").combogrid({
			panelWidth : 200,
			value : '',//缺省值
			idField : "id",
			textField : "clientName",
			url : "",
			columns : [ [ {
				field : 'id',
				title : "<spring:message code='client.clientName'/>",
				width : 80
			}, {
				field : 'clientName',
				title : "<spring:message code='client.clientEntityNum'/>",
				width : 120
			} ] ]
		});

		$.ajax({
			url : "${vat}/client/getClientInfoList.do",
			type : "POST",
			async : true,
			data : '', //不能直接写成 {id:"123",code:"tomcat"}  
			dataType : "json",
			// contentType: "charset=utf-8",  
			cache : false,
			success : function(result) {
				//clearSaveForm();
				$('#customerNameListInSendForm').combogrid('grid').datagrid(
						'loadData', result.data);
			}
		});

		$("#invoiceSend_deliveryBySelect").combogrid({
			panelWidth : 200,
			value : '',//缺省值
			idField : "id",
			textField : "clientName",
			url : "",
			columns : [ [ {
				field : 'id',
				title : "<spring:message code='client.clientName'/>",
				width : 80
			}, {
				field : 'clientName',
				title : "<spring:message code='client.clientEntityNum'/>",
				width : 120
			} ] ]
		});

		$.ajax({
			url : "${vat}/client/getClientNameList.do",
			type : "POST",
			async : true,
			data : '', //不能直接写成 {id:"123",code:"tomcat"}  
			dataType : "json",
			// contentType: "charset=utf-8",  
			cache : false,
			success : function(result) {
				clearSaveForm();
				$('#invoiceSend_deliveryBySelect').combogrid('grid').datagrid(
						'loadData', result.data);
			}
		});
	}

	function invoicAdd() {
		clearSaveForm();
		$("#invoiceSend_addclient_dlg").dialog('open').dialog('setTitle',
				"<spring:message code='client.dialog.clientadd.title'/>");
		$("#invoiceSend_editform").form('load', {
		//clientDate : myformatter(new Date())
		});
	}
	function invoiceEdit() {
		clearSaveForm();
		var row_list = $('#invoiceSend_dataGrid').datagrid('getChecked');
		if (row_list) {
			var id = '';
			$.each(row_list, function(index, item) {
				if (index == 0) {
					id = item.id;
				}
			});
			if (id != '') {
				$
						.post(
								'${vat}/client/loadModifyClient.do',
								{
									id : id
								},
								function(result) {
									if (result.status == '0') {
										$("#invoiceSend_addclient_dlg")
												.dialog('open')
												.dialog('setTitle',
														"<spring:message code='client.dialog.clientedit.title'/>");
										$("#invoiceSend_editform").form('load',
												{
												//clientDate : myformatter(new Date(
												//result.data.clientDate))
												});
										$("#invoiceSend_editform").form('load',
												{
													isUsed : result.data.isUsed
												});
										$("#invoiceSend_editform").form('load',
												result.data);
									} else if (result.success == 'false') {
										$.messager
												.alert(
														'<spring:message code="system.alert"/>',
														result.errorMsg);
										Search();
										clearSaveForm();
										$('#invoiceSend_addclient_dlg').dialog(
												'close');
									}
								}, 'json');
			} else {
				$.messager.alert('<spring:message code="system.alert"/>',
						'编辑的主键不能为空');
			}
		}
	}

	function clientMangeEditSave() {
		$.messager
				.confirm(
						"<spring:message code='client.datacommit.formsave.confirm.title'/>",
						"<spring:message code='client.datacommit.formsave.confirm.text'/>",
						function(result) {
							if (result) {
								$('#invoiceSend_editform')
										.form(
												'submit',
												{
													url : '${vat}/client/saveClient.do',
													onSubmit : function() {
														return $(this)
																.form(
																		'enableValidation')
																.form(
																		'validate');
													},
													success : function(result) {
														var result = $
																.parseJSON(result);
														if (result.status == '0') {
															clearSaveForm();
															Search();
															$(
																	'#invoiceSend_addclient_dlg')
																	.dialog(
																			'close');
															$.messager
																	.alert(
																			'<spring:message code="system.alert"/>',
																			'保存成功');
														}

													}
												});
							}
						});

	}
	//删除一条记录

	function invoiceRemove() {
		$.messager
				.confirm(
						"<spring:message code='client.datacommit.delete.confirm.title'/>",
						"<spring:message code='client.datacommit.delete.confirm.text'/>",
						function(result) {
							if (result) {
								var row_list = $('#invoiceSend_dataGrid')
										.datagrid('getChecked');
								//var row=$('#invoiceSend_dataGrid').datagrid('getSelected');
								if (row_list) {
									var urlString = "";
									$.each(row_list, function(index, item) {
										if (index == 0)
											urlString += "id=" + item.id;
										else
											urlString += "&id=" + item.id;
									});
									if (urlString != '') {
										$
												.ajax({
													url : "${vat}/invoiceSend/removeInvoiceSendHs.do",
													type : "POST",
													async : true,
													data : urlString, //不能直接写成 {id:"123",code:"tomcat"}  
													dataType : "json",
													// contentType: "charset=utf-8",  
													cache : false,
													success : function(result) {
														Search();
														//	clearSaveForm();
													}
												});
									}
								}
							}

						});
	}
	function SearchSendBySelected() {
		$("#invoiceSend_searchform")
				.form(
						'load',
						{
							pageNumber : $('#invoiceSend_dataGrid').datagrid(
									'options').pageNumber,
							pageSize : $('#invoiceSend_dataGrid').datagrid(
									'options').pageSize
						});
		$("#invoiceSend_dataGrid").datagrid("loading");
		$('#invoiceSend_searchform').form('submit', {
			url : '${vat}/invoiceSend/loadInvoiceSendHBySelected.do',
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(result) {
				//数据加载以及绑定
				var result = $.parseJSON(result);
				$("#invoiceSend_dataGrid").datagrid('loadData', result);
				$("#invoiceSend_dataGrid").datagrid("loaded");
			}
		});
	}
	function invoiceSearch() {//it searched all , in progress
		//loadInvoiceList();
		$("#invoiceList_datagrid").datagrid("loading");
		$('#invoiceList_searchform').form('submit', {
			url : '${vat}/invoiceSendL/loadInvoiceSendLPageBySelected.do',
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(result) {
				//数据加载以及绑定
				var result = $.parseJSON(result);
				$("#invoiceList_datagrid").datagrid('loadData', result);
				$("#invoiceList_datagrid").datagrid("loaded");
			}
		});
	}
	/* function changeTypeToExternal() {
		$('#customerCodeDiv').css('display', 'none');
		$('#legalEntityIdDiv').css('display', 'block');
		initCustomerInDiaLog();
		//legalEntityIdDiv
	} */
	function changeTypeToInternal() {//display
		$('#customerCodeDiv').css('display', 'block');
		$('#legalEntityIdDiv').css('display', 'none');
		//找纳税主体
		initTaxEntity();

	}
	function initTaxEntity() {
		$('#taxEntitySearchBoxInDialog')
				.combogrid(
						{
							panelWidth : 500,
							idField : 'clientName', //ID字段  
							textField : 'clientName', //显示的字段  
							url : "",
							fitColumns : true,
							striped : true,
							editable : true,
							pagination : true, //是否分页
							rownumbers : true, //序号
							collapsible : false, //是否可折叠的
							fit : true, //自动大小
							method : 'post',
							columns : [ [ {
								field : 'id',
								title : '纳税主体编号',
								width : 10,
								hidden : true
							}, {
								field : 'clientName',
								title : '纳税主体名称',
								width : 100
							} ] ],
							toolbar : [
									{
										text : '纳税主体名称<input type="text" id="taxEntityId"/>'
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
											'#taxEntitySearchBoxInDialog')
											.combogrid('grid').datagrid(
													'getSelected');
									if (selected) {
										//取得选中行的rowIndex
										var index = $(
												'#taxEntitySearchBoxInDialog')
												.combogrid('grid')
												.datagrid('getRowIndex',
														selected);
										//向上移动到第一行为止
										if (index > 0) {
											$('#taxEntitySearchBoxInDialog')
													.combogrid('grid')
													.datagrid('selectRow',
															index - 1);
										}
									} else {
										var rows = $(
												'#taxEntitySearchBoxInDialog')
												.combogrid('grid').datagrid(
														'getRows');
										$('#taxEntitySearchBoxInDialog')
												.combogrid('grid').datagrid(
														'selectRow',
														rows.length - 1);
									}
								},
								down : function() { //【向下键】押下处理
									//取得选中行
									var selected = $(
											'#taxEntitySearchBoxInDialog')
											.combogrid('grid').datagrid(
													'getSelected');
									if (selected) {
										//取得选中行的rowIndex
										var index = $(
												'#taxEntitySearchBoxInDialog')
												.combogrid('grid')
												.datagrid('getRowIndex',
														selected);
										//向下移动到当页最后一行为止
										if (index < $(
												'#taxEntitySearchBoxInDialog')
												.combogrid('grid').datagrid(
														'getData').rows.length - 1) {
											$('#taxEntitySearchBoxInDialog')
													.combogrid('grid')
													.datagrid('selectRow',
															index + 1);
										}
									} else {
										$('#taxEntitySearchBoxInDialog')
												.combogrid('grid').datagrid(
														'selectRow', 0);
									}
								},

							},

						});

		//取得分页组件对象
		var pager = $('#taxEntitySearchBoxInDialog').combogrid('grid')
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
									$('#taxEntitySearchBoxInDialog').combogrid(
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
				url : "${vat}/client/getClientInfoList.do",
				type : "POST",
				async : true,
				data : '', //不能直接写成 {id:"123",code:"tomcat"}  
				dataType : "json",
				// contentType: "charset=utf-8",  
				cache : false,
				success : function(result) {
					//clearSaveForm();
					$('#taxEntitySearchBoxInDialog').combogrid('grid')
							.datagrid('loadData', result.data);
				}
			});
		};

		searchTaxEntityInDialod();//初始完后加载数据
	}

	function searchTaxEntityInDialod() {
		$.ajax({
			type : "POST",
			url : "${vat}/client/getClientInfoList.do",
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			},
			success : function(data) {
				var result = $.parseJSON(data);
				$('#taxEntitySearchBoxInDialog').combogrid('grid').datagrid(
						'loadData', result.data);
			}
		});
	}

	function findTaxEntity() {
		//need to update
		$.ajax({
			type : "POST",
			url : "${vat}/client/getClientInfoList.do",
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			},
			success : function(data) {
				var result = $.parseJSON(data);
				$('#taxEntitySearchBoxInDialog').combogrid('grid').datagrid(
						'loadData', result.data);
			}
		});
	}
	function clearInvoiceForm() {
		$('#invoiceSend_invoiceList_dlg').dialog('close');
	}
	function initCustomerInSendForm() {
		//初始化位于SendForm里面的客户信
		$('#customerNameListInSendForm').combogrid({
			panelWidth : 500,
			idField : 'id', //ID字段  
			textField : 'customerName', //显示的字段  customer
			url : "",
			fitColumns : true,
			striped : true,
			editable : true,
			pagination : true, //是否分页
			rownumbers : true, //序号
			collapsible : false, //是否可折叠的
			fit : true, //自动大小
			method : 'post',
			columns : [ [ {
				field : 'id',
				title : '客户编号',
				width : 10,
				hidden : true
			}, {
				field : 'customerName',
				title : '客户名称',
				width : 100
			}, {
				field : 'customerCode',
				title : '客户编码',
				width : 100
			} ] ],
			toolbar : [ {
				text : '客户名称<input type="text" id="customerNameInSendForm"/>'
			}, {
				text : "查询",
				iconCls : 'icon-search',
				handler : function() {

					findCustomerName();

				}
			}, '-' ]
		});

		//取得分页组件对象
		var pager = $('#customerNameListInSendForm').combogrid('grid')
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
									$('#customerNameListInSendForm').combogrid(
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
			$('#customerFormForEveryCombox').form('load', {
				pageNumber : page,
				pageSize : rows
			});

			$('#customerFormForEveryCombox').form(
					'submit',
					{
						url : '${vat}/customer/loadCustomerPageList.do',
						onSubmit : function() {
							return $(this).form('enableValidation').form(
									'validate');
						},
						success : function(result) {
							//数据加载以及绑定
							var result = $.parseJSON(result);
							$('#customerNameListInSendForm').combogrid('grid')
									.datagrid('loadData', result.data);
							//	$("#invoiceList_datagrid").datagrid("loaded");
						}
					});
		};

	}

	$(document).ready(function() {
		initCustomerInSendForm();
		InitComboboxs();//加载位于SendForm里面的客户信息

	});
	function testf() {
		alert($('#clientNameInSendForm').val());
	}
	function findCustomerName() {
		$('#customerFormForEveryCombox').form(
				'load',
				{
					pageNumber : $('#customerNameListInSendForm').combogrid(
							'grid').datagrid('options').pageNumber,
					pageSize : $('#customerNameListInSendForm').combogrid(
							'grid').datagrid('options').pageSize
				});

		$('#customerFormForEveryCombox').form(
				'submit',
				{
					url : '${vat}/customer/loadCustomerPageList.do',
					onSubmit : function() {
						return $(this).form('enableValidation')
								.form('validate');
					},
					success : function(result) {
						//数据加载以及绑定
						var result = $.parseJSON(result);
						$('#customerNameListInSendForm').combogrid('grid')
								.datagrid('loadData', result.data);
						//	$("#invoiceList_datagrid").datagrid("loaded");
					}
				});

	}
	function InitComboboxs() {
		$('#customerFormForEveryCombox').form(
				'load',
				{
					pageNumber : $('#customerNameListInSendForm').combogrid(
							'grid').datagrid('options').pageNumber,
					pageSize : $('#customerNameListInSendForm').combogrid(
							'grid').datagrid('options').pageSize
				});

		$('#customerFormForEveryCombox').form(
				'submit',
				{
					url : '${vat}/customer/loadCustomerPageList.do',
					onSubmit : function() {
						return $(this).form('enableValidation')
								.form('validate');
					},
					success : function(result) {
						//数据加载以及绑定
						var result = $.parseJSON(result);
						$('#customerNameListInSendForm').combogrid('grid')
								.datagrid('loadData', result.data);
						//	$("#invoiceList_datagrid").datagrid("loaded");
					}
				});

	}

	function searchClientsInDialod() {
		$('#customerFormForEveryCombox').form(
				'load',
				{
					pageNumber : $('#customerSearchBoxInDialog').combogrid(
							'grid').datagrid('options').pageNumber,
					pageSize : $('#customerSearchBoxInDialog')
							.combogrid('grid').datagrid('options').pageSize
				});

		$('#customerFormForEveryCombox').form(
				'submit',
				{
					url : '${vat}/customer/loadCustomerPageList.do',
					onSubmit : function() {
						return $(this).form('enableValidation')
								.form('validate');
					},
					success : function(result) {
						//数据加载以及绑定
						var result = $.parseJSON(result);
						$('#customerSearchBoxInDialog').combogrid('grid')
								.datagrid('loadData', result.data);
						//	$("#invoiceList_datagrid").datagrid("loaded");
					}
				});
	}

	function initCustomerInDiaLog() {

		$('#customerSearchBoxInDialog')
				.combogrid(
						{
							panelWidth : 500,
							idField : 'customerName', //ID字段  
							textField : 'customerName', //显示的字段  
							url : "",
							fitColumns : true,
							striped : true,
							editable : true,
							pagination : true, //是否分页
							rownumbers : true, //序号
							collapsible : false, //是否可折叠的
							fit : true, //自动大小
							method : 'post',
							columns : [ [ {
								field : 'id',
								title : '用户编号',
								width : 10,
								hidden : true
							}, {
								field : 'customerName',
								title : '客户名称',
								width : 60
							}, {
								field : 'customerCode',
								title : '客户编码',
								width : 60
							} ] ],
							toolbar : [
									{
										text : '客户名称<input type="text" id="customerNameInDiablog"/>'
									}, {
										text : "查询",
										iconCls : 'icon-search',
										handler : function() {
											findClients();
										}
									}, '-' ],
							keyHandler : {
								up : function() { //【向上键】押下处理
									//取得选中行
									var selected = $(
											'#customerSearchBoxInDialog')
											.combogrid('grid').datagrid(
													'getSelected');
									if (selected) {
										//取得选中行的rowIndex
										var index = $(
												'#customerSearchBoxInDialog')
												.combogrid('grid')
												.datagrid('getRowIndex',
														selected);
										//向上移动到第一行为止
										if (index > 0) {
											$('#customerSearchBoxInDialog')
													.combogrid('grid')
													.datagrid('selectRow',
															index - 1);
										}
									} else {
										var rows = $(
												'#customerSearchBoxInDialog')
												.combogrid('grid').datagrid(
														'getRows');
										$('#customerSearchBoxInDialog')
												.combogrid('grid').datagrid(
														'selectRow',
														rows.length - 1);
									}
								},
								down : function() { //【向下键】押下处理
									//取得选中行
									var selected = $(
											'#customerSearchBoxInDialog')
											.combogrid('grid').datagrid(
													'getSelected');
									if (selected) {
										//取得选中行的rowIndex
										var index = $(
												'#customerSearchBoxInDialog')
												.combogrid('grid')
												.datagrid('getRowIndex',
														selected);
										//向下移动到当页最后一行为止
										if (index < $(
												'#customerSearchBoxInDialog')
												.combogrid('grid').datagrid(
														'getData').rows.length - 1) {
											$('#customerSearchBoxInDialog')
													.combogrid('grid')
													.datagrid('selectRow',
															index + 1);
										}
									} else {
										$('#customerSearchBoxInDialog')
												.combogrid('grid').datagrid(
														'selectRow', 0);
									}
								},

							},

						});

		//取得分页组件对象
		var pager = $('#customerSearchBoxInDialog').combogrid('grid').datagrid(
				'getPager');

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
									$('#customerSearchBoxInDialog').combogrid(
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
				url : "${vat}/client/getClientInfoList.do",
				type : "POST",
				async : true,
				data : '', //不能直接写成 {id:"123",code:"tomcat"}  
				dataType : "json",
				// contentType: "charset=utf-8",  
				cache : false,
				success : function(result) {
					//clearSaveForm();
					$('#customerSearchBoxInDialog').combogrid('grid').datagrid(
							'loadData', result.data);
				}
			});
		};

		searchClientsInDialod()//初始完后加载数据
	}

	//清空dialog内容
	function clearInvoiceList_searchform() {
		$('#invoiceList_searchform').form('clear');//invoiceList_searchform
	}
	function clearSaveForm() {
		//alert(000);
		$('#addInvoiceSend_searchform').form('clear');//
	}
	function clearSendForm() {
		//alert(000);
		$('#invoiceSend_searchform').form('clear');//
	}
	//屏蔽layout east 部分的小三角形
	$.extend($.fn.layout.methods, {
		setRegionToolVisableState : function(jq, params) {
			return jq.each(function() {
				if (params.region == "center")
					return;
				var panels = $.data(this, 'layout').panels;
				var panel = panels[params.region];
				var tool = panel.panel('header').find('>div.panel-tool');
				tool.css({
					display : params.visible ? 'block' : 'none'
				});
				var first = params.region.substring(0, 1);
				var others = params.region.substring(1);
				var expand = 'expand' + first.toUpperCase() + others;
				if (panels[expand]) {
					panels[expand].panel('header').find('>div.panel-tool').css(
							{
								display : params.visible ? 'block' : 'none'
							});
				}
			});
		}
	});
	$('#layout').layout('setRegionToolVisableState', {
		region : 'east',
		visible : false
	});
</script>
</html>