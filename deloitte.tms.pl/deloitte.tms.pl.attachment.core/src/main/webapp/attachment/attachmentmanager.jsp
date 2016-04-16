<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.bstek.com/dorado" prefix="d" %>
<html>
<head>
<title>附件管理</title>
</head>
<body scroll="yes">
  <d:View config="com.ling2.attachment.view.AttachmentManager">
<d:Layout type="border">
  <d:Pane position="center">
   <d:DataTable id="tableFiles" />
  </d:Pane>
</d:Layout>
   

  </d:View>
</body>
</html>
