    <ToolBar id="toolBar${declarationName}">
      <ToolBarButton id="searchBtn${declarationName}">
        <Property name="caption">查询</Property>
        <Property name="icon">url(>skin>common/icons.gif) -40px -100px</Property>
      </ToolBarButton>
      <ToolBarButton id="addBtn${declarationName}">
        <Property name="caption">添加</Property>
        <Property name="icon">url(>skin>common/icons.gif) -120px 0px</Property>
      </ToolBarButton>
      <#if pojo.getEditType().toString()=="form">
      <ToolBarButton id="modifyBtn${declarationName}">
        <Property name="caption">修改</Property>
        <Property name="icon">url(>skin>common/icons.gif) -200px -0px</Property>
      </ToolBarButton>
      <#else>
      </#if>
      <ToolBarButton id="delBtn${declarationName}">
        <Property name="caption">删除</Property>
        <Property name="icon">url(>skin>common/icons.gif) -140px 0px</Property>
      </ToolBarButton>
      <ToolBarButton id="saveBtn${declarationName}">
        <Property name="caption">保存</Property>
        <Property name="icon"> url(>skin>common/icons.gif) -160px -20px</Property>
      </ToolBarButton>
      <Fill/>
      <DataPilot>
        <Property name="dataSet">dataSet${declarationName}</Property>
      </DataPilot>
<#if pojo.getIsIntro()>      
      <Fill/>
      <TextEditor id="textEditorHelp${declarationName}">
        <Property name="trigger">introDropDown${declarationName}</Property>
        <Property name="blankText">GoTo</Property>
      </TextEditor>
      <ToolBarButton id="helpBtn${declarationName}">
        <Property name="caption">帮助</Property>
        <Property name="icon">url(>skin>common/icons.gif) -160px -40px</Property>
      </ToolBarButton>
</#if>      
    </ToolBar>  