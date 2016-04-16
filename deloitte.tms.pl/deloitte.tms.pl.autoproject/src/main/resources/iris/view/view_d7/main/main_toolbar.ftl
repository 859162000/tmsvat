    <ToolBar id="toolBar${declarationName}">
      <DataPilot>
        <Property name="dataSet">dataSet${declarationName}</Property>
      </DataPilot>
      <Separator/>
      <ToolBarButton id="searchBtn${declarationName}">
        <Property name="caption">${r"${res[&quot;core.common"}/Query&quot;]}</Property>
        <Property name="icon">url(>skin>common/icons.gif) -40px -100px</Property>
      </ToolBarButton>
      <Separator/>
      <ToolBarButton id="addBtn${declarationName}">
        <Property name="caption">${r"${res[&quot;core.common"}/Add&quot;]}</Property>
        <Property name="icon">url(>skin>common/icons.gif) -120px 0px</Property>
      </ToolBarButton>
      <#if pojo.getEditType().toString()=="form">
      <ToolBarButton id="modifyBtn${declarationName}">
        <Property name="caption">${r"${res[&quot;core.common"}/Modify&quot;]}</Property>
        <Property name="icon">url(>skin>common/icons.gif) -200px -0px</Property>
      </ToolBarButton>
      <#else>
      </#if>
      <ToolBarButton id="delBtn${declarationName}">
        <Property name="caption">${r"${res[&quot;core.common"}/Delete&quot;]}</Property>
        <Property name="icon">url(>skin>common/icons.gif) -140px 0px</Property>
      </ToolBarButton>
      <Separator/>
      <ToolBarButton id="saveBtn${declarationName}">
        <Property name="caption">${r"${res[&quot;core.common"}/Save&quot;]}</Property>
        <Property name="icon"> url(>skin>common/icons.gif) -160px -20px</Property>
      </ToolBarButton>
      <Separator/>
<#if pojo.getIsIntro()>      
      <Fill/>
      <TextEditor id="textEditorHelp${declarationName}">
        <Property name="trigger">introDropDown${declarationName}</Property>
        <Property name="blankText">GoTo</Property>
      </TextEditor>
      <ToolBarButton id="helpBtn${declarationName}">
        <Property name="caption">${r"${res[&quot;core.common"}/Help&quot;]}</Property>
        <Property name="icon">url(>skin>common/icons.gif) -160px -40px</Property>
      </ToolBarButton>
</#if>      
    </ToolBar>  