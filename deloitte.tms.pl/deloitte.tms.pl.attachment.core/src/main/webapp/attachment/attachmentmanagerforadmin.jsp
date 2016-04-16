<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.bstek.com/dorado" prefix="d" %>
<html>
<head>
<title>附件管理</title>
</head>
<body scroll="yes">
  <d:View config="com.ling2.attachment.view.AttachmentForAdmin">
<d:Layout type="border">
		<d:Pane position="top">

			<d:Layout type="Vflow">
				<d:Pane>
					<d:AutoForm id="formQuery"></d:AutoForm>
				</d:Pane>
				<d:Pane>
					<d:Layout type="Hflow">
						<d:Pane>
							<d:Button id="buttonQuery" />
						</d:Pane>
						<d:Pane>
							<d:Button id="buttondelete" />
						</d:Pane>
						<d:Pane>
							<d:Button id="buttonupload" />
						</d:Pane>
					</d:Layout>
				</d:Pane>
			</d:Layout>
		</d:Pane>
		<d:Pane position="center">
			<d:DataTable id="tableFiles" />
		</d:Pane>
		<d:Pane position="bottom">
		</d:Pane>
	</d:Layout>

  </d:View>
</body>
</html>
