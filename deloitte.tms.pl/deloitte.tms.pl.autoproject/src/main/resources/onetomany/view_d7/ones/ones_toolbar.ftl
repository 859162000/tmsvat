                <ToolBar id="toolBar${childdeclarationName}">
                  <ToolBarButton id="saveBtn${childdeclarationName}">
                    <ClientEvent name="onClick">var view = this;
view.id(&quot;updateActionSave${childdeclarationName}&quot;).execute(function(){
	view.id(&quot;dialog${childdeclarationName}&quot;).hide();
});
</ClientEvent>
                    <Property name="caption">保存</Property>
                    <Property name="icon">url(>skin>common/icons.gif) -140px -20px</Property>
                  </ToolBarButton>
                </ToolBar>