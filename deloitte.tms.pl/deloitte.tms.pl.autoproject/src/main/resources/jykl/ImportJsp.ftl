<#assign classbody>
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.bstek.com/dorado" prefix="d"%>
<html>
<head>
<title></title>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/button.css" />
</head>
<body scroll="yes">
<d:View config="${pojo.getPackageName()}.view.${declarationName}Import">
	<d:Layout type="border">
		<d:Pane position="top">
			<d:Layout type="vflow">
				<d:Pane >
					<d:Layout type="hflow">

					<d:Pane align="left">
			<d:Button id="buttonUpload" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			
			<d:Button id="btnSave" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<d:Button id="buttonExport" />
		</d:Pane>
		
					
					</d:Layout>
				</d:Pane>
			</d:Layout>
		</d:Pane>
		<d:Pane position="center" >
			<d:DataTable id="tblEntity" height="100%" />
				
		</d:Pane>
		
	</d:Layout>
</d:View>
</body>
</html>
</#assign>
${classbody}
