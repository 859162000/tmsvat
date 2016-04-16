                <ToolBar>
                   <DataPilot id="dataPilot${childpropertyname}">
                    <Property name="dataSet">dataSet${declarationName}</Property>
                    <Property name="dataPath">#.rel${childpropertyname}</Property>
                  </DataPilot>
                  <ToolBarButton id="queryBtn${childpropertyname}">
                    <Property name="caption">查询</Property>
                    <Property name="icon"> url(>skin>common/icons.gif) -40px -100px</Property>
                  </ToolBarButton>
                  <ToolBarButton id="addBtn${childpropertyname}">
                    <Property name="caption">添加</Property>
                    <Property name="icon">url(>skin>common/icons.gif) -120px -0px</Property>
                  </ToolBarButton>
                  <ToolBarButton id="delBtn${childpropertyname}">
                    <Property name="caption">删除</Property>
                    <Property name="icon">url(>skin>common/icons.gif) -140px -0px</Property>
                  </ToolBarButton>
                  <#if child.getEditType().toString()=="form"><Button id="modifyBtn${childpropertyname}">
                      <Property name="caption">确定</Property>
                      <Property name="icon"></Property>
                    </Button>
                    <Button id="cancelBtn${childpropertyname}">
                      <Property name="caption"> 取消</Property>
                      <Property name="icon"></Property>
                    </Button></#if>
                </ToolBar>