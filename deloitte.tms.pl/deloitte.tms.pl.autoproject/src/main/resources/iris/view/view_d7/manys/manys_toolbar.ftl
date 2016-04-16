                <ToolBar>
                   <DataPilot id="dataPilot${childpropertyname}">
                    <Property name="dataSet">dataSet${declarationName}</Property>
                    <Property name="dataPath">#.rel${childpropertyname}</Property>
                  </DataPilot>
                  <ToolBarButton id="addBtn${childpropertyname}">
                    <Property name="caption">${r"${res[&quot;core.common"}/Add&quot;]}</Property>
                    <Property name="icon">url(>skin>common/icons.gif) -120px -0px</Property>
                  </ToolBarButton>
                  <ToolBarButton id="delBtn${childpropertyname}">
                    <Property name="caption">${r"${res[&quot;core.common"}/Remove&quot;]}</Property>
                    <Property name="icon">url(>skin>common/icons.gif) -140px -0px</Property>
                  </ToolBarButton>
                  <#if child.getEditType().toString()=="form"><Button id="modifyBtn${childpropertyname}">
                      <Property name="caption">${r"${res[&quot;core.common"}/Ok&quot;]}</Property>
                      <Property name="icon"></Property>
                    </Button>
                    <Button id="cancelBtn${childpropertyname}">
                      <Property name="caption">${r"${res[&quot;core.common"}/Cancel&quot;]}</Property>
                      <Property name="icon"></Property>
                    </Button></#if>
                </ToolBar>