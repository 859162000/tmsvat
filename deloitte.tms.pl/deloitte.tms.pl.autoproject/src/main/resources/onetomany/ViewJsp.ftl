<#assign classbody>
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.bstek.com/dorado" prefix="d"%>
<html>
<head>
<title>管理</title>
</head>
<body scroll="yes">
<d:View config="${pojo.getPackageName()}.view.${declarationName}Manager">
	<d:Layout type="border" height="100%">
		<d:Pane position="top">
					<d:Layout type="Vflow">
						<d:Pane>
							<d:AutoForm id="frmQuery" />
						</d:Pane>
						<d:Pane>
							<d:Layout type="Hflow">
								<d:Pane>
							<d:Button id="btnQuery" />
							</d:Pane>
							<d:Pane>
								<d:Button id="btnReset" />
							</d:Pane>
							<d:Pane>
								<d:Button id="btnNew" />
							</d:Pane>
							<d:Pane>
								<d:Button id="btnDelete" />
							</d:Pane>
							<d:Pane>
								<d:Button id="buttonModify" />
							</d:Pane>
							</d:Layout>
						</d:Pane>
					</d:Layout>
		</d:Pane>
		<d:Pane position="center">
			<d:DataTable id="tblEntity" />
		</d:Pane>
		<d:Pane position="bottom">
			<d:PagePilot id="pageEntity" />
			<table cellpadding="5">
				<tr>
					<td nowrap bgcolor="#F5F7F9">数据提取模式:</td>
					<td bgcolor="#F5F7F9"><d:RadioGroup id="editorDataMode" /></td>
					<td bgcolor="#F5F7F9"><d:Button id="buttonExcel" /></td>
				</tr>
			</table>
		</d:Pane>
	</d:Layout>
	<d:SubWindow id="win1" width="450px" height="150px" draggable="true"
		resizable="true" status="hidden" showMinimizeButton="false"
		showMaximizeButton="false" showCloseButton="false">
		<d:Layout type="border">
			<d:Pane position="top">
				<d:AutoForm id="frmEntity" />
			</d:Pane>
			<d:Pane position="center" align="right">
				<d:Layout type="Hflow">
					<d:Pane>
						<d:Button id="btnSave" />
					</d:Pane>
					<d:Pane>
						<d:Button id="btnCancel" />
					</d:Pane>
				</d:Layout>
			</d:Pane>
		</d:Layout>
	</d:SubWindow>
</d:View>
</body>
</html>
</#assign>
${classbody}
