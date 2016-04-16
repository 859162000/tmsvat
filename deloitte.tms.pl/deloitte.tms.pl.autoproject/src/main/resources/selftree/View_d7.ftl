<#assign pojoview=pojo.getDeclarationNameFirstLetterLower()+"View">
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign pojoclassname = "${pojo.getPackageName()}.model.${declarationName}InParam">
<#assign masterid = pojo.getDeclarationNameFirstLetterLower()+"Id">
<ViewConfig>
  <Property name="metaData">
    <Property name="securityTag">${declarationName}.manager</Property>
  </Property>
  <Model>
    <DataType name="dataType${declarationName}">
      <Property name="creationType">${pojoclassname}</Property>
<#assign fields=pojo.getPojoFields()>
<#foreach field in fields>
<#if field.getIsSampleField()>
      <PropertyDef name="${field.getName()}">
        <Property name="dataType">${field.getDataType()}</Property>
        <Property name="label"><#if field.getComment()?exists>${field.getComment()}<#else>${field.getName()}</#if></Property>
      </PropertyDef>
</#if>
</#foreach>
      <Reference name="${declarationName?lower_case}">
        <Property name="dataType">[dataType${declarationName}]</Property>
        <Property name="parameter">
          <Entity>
            <Property name="parentId">${"$"+"$"+"{"+"this.id"+"}"}</Property>
          </Entity>
        </Property>
        <Property name="dataProvider">${pojoview}#load${declarationName}</Property>
      </Reference>
    </DataType>
  </Model>
  <View>
    <ClientEvent name="onReady">
		window.$CurrentOperation${declarationName};&#xD;
        window.createTopNode${declarationName} = function(){&#xD;
        	var entity = view.id(&quot;dataSet${declarationName}&quot;).getData().insert({
    		});
    		view.id(&quot;dataTree${declarationName}&quot;).set(&quot;currentEntity&quot;, entity);
    		var dialog = view.id(&quot;dialog${declarationName}&quot;);&#xD;
    		dialog.set(&quot;caption&quot;, &quot;添加顶层节点&quot;)
    		dialog.status = &quot;insert&quot;;
    		dialog.show();
        }&#xD;
        &#xD;
        window.createSubNode${declarationName} = function(){&#xD;
            var tree = view.id(&quot;dataTree${declarationName}&quot;);&#xD;
            var currentEntity = tree.get(&quot;currentEntity&quot;);&#xD;
            if (currentEntity) {&#xD;
                var dsMenu = view.id(&quot;dataSet${declarationName}&quot;);&#xD;
                newEntity = currentEntity.createChild(&quot;${declarationName?lower_case}&quot;, {&#xD;
					parentId:currentEntity.get(&quot;id&quot;) 
                });&#xD;
                &#xD;
                tree.get(&quot;currentNode&quot;).expand();&#xD;
                tree.set(&quot;currentEntity&quot;, newEntity);&#xD;
                var dialog = view.id(&quot;dialog${declarationName}&quot;);&#xD;
                dialog.set(&quot;caption&quot;, &quot;添加子节点&quot;);&#xD;
                dialog.show();&#xD;
            }else {&#xD;
                dorado.MessageBox.alert(&quot;请先选中一个节点后再进行此操作！&quot;);&#xD;
            }&#xD;
        }&#xD;
        window.editNode${declarationName} = function(){&#xD;
            var tree = view.id(&quot;dataTree${declarationName}&quot;);&#xD;
            var currentEntity = tree.get(&quot;currentEntity&quot;);&#xD;
            if (currentEntity) {&#xD;
                var dialog = view.id(&quot;dialog${declarationName}&quot;);&#xD;
                dialog.set(&quot;caption&quot;, &quot;编辑节点&quot;);&#xD;
                dialog.show();&#xD;
            }else {&#xD;
                dorado.MessageBox.alert(&quot;请先选中一个要编辑的节点&quot;);&#xD;
            }&#xD;
        }&#xD;
        &#xD;
        window.deleteNode${declarationName} = function(){&#xD;
            var currentEntity = view.id(&quot;dataTree${declarationName}&quot;).get(&quot;currentEntity&quot;);&#xD;
            var action = view.id(&quot;updateAction${declarationName}&quot;);&#xD;
            if (currentEntity) {&#xD;
                var currentNode = view.id(&quot;dataTree${declarationName}&quot;).get(&quot;currentNode&quot;);&#xD;
                if (!currentNode.get(&quot;expanded&quot;)) {&#xD;
                    currentNode.expand();&#xD;
                }&#xD;
                if (currentNode.get(&quot;nodes&quot;).size == 0) {&#xD;
					dorado.MessageBox.show({&#xD;
                        title: &quot;提示&quot;,&#xD;
                        icon: dorado.MessageBox.INFO_ICON,&#xD;
                        message: &quot;您真的要删除当前节点吗？&quot;,&#xD;
                        buttons: [&quot;yes&quot;, &quot;no&quot;],&#xD;
                        detailCallback: function(buttonId){&#xD;
                            if (buttonId == &quot;yes&quot;) {&#xD;
                                currentEntity.remove();&#xD;
                                action.execute();&#xD;
                            }&#xD;
                        }&#xD;
                    })&#xD;
                }else {&#xD;
                    dorado.MessageBox.show({&#xD;
                        title: &quot;操作错误&quot;,&#xD;
                        icon: dorado.MessageBox.ERROR_ICON,&#xD;
                        message: &quot;当前选中节点含有子节点项，请先删除子节点！&quot;,&#xD;
                        buttons: [&quot;ok&quot;]&#xD;
                    })&#xD;
                }&#xD;
            }else {&#xD;
                dorado.MessageBox.show({&#xD;
                    title: &quot;操作错误&quot;,&#xD;
                    icon: dorado.MessageBox.ERROR_ICON,&#xD;
                    message: &quot;请选中一个节点节点后再进行此操作！&quot;,&#xD;
                    buttons: [&quot;ok&quot;]&#xD;
                })&#xD;
            }&#xD;
        }&#xD;
    </ClientEvent>
    <DataSet id="dataSet${declarationName}">
      <Property name="dataType">[dataType${declarationName}]</Property>
      <Property name="dataProvider">${pojoview}#load${declarationName}</Property>
      <Property name="readOnly">false</Property>
    </DataSet>
    <Menu id="menuOperate${declarationName}">
      <MenuItem>
        <ClientEvent name="onClick">window.createTopNode${declarationName}();</ClientEvent>
        <Property name="name">newNode</Property>
        <Property name="caption">添加顶层节点</Property>
        <Property name="icon">url(>skin>common/icons.gif) -280px -100px</Property>
      </MenuItem>
      <MenuItem>
        <ClientEvent name="onClick">window.createSubNode${declarationName}();&#xD;
</ClientEvent>
        <Property name="caption">添加子节点</Property>
        <Property name="name">addSubNode</Property>
        <Property name="icon">url(>skin>common/icons.gif) -60px -120px</Property>
      </MenuItem>
      <MenuItem>
        <ClientEvent name="onClick">window.editNode${declarationName}();&#xD;
</ClientEvent>
        <Property name="name">nodeModify</Property>
        <Property name="caption">编辑当前节点</Property>
        <Property name="icon">url(>skin>common/icons.gif) -100px -120px</Property>
      </MenuItem>
      <MenuItem>
        <ClientEvent name="onClick">window.deleteNode${declarationName}();&#xD;
</ClientEvent>
        <Property name="name">menuItemDelete</Property>
        <Property name="caption">删除当前节点</Property>
        <Property name="icon">url(>skin>common/icons.gif) -80px -120px</Property>
      </MenuItem>
    </Menu>
    <ToolBar layoutConstraint="top">
      <ToolBarButton id="buttonAdd${declarationName}">
        <ClientEvent name="onClick">window.createTopNode${declarationName} ();</ClientEvent>
        <Property name="caption">添加顶层节点</Property>
        <Property name="icon">url(>skin>common/icons.gif) -280px -100px</Property>
      </ToolBarButton>
      <ToolBarButton id="buttonAdd${declarationName}Sub">
        <ClientEvent name="onClick">window.createSubNode${declarationName}();&#xD;
</ClientEvent>
        <Property name="caption">添加子节点</Property>
        <Property name="icon">url(>skin>common/icons.gif) -60px -120px</Property>
      </ToolBarButton>
      <ToolBarButton id="buttonEdit${declarationName}">
        <ClientEvent name="onClick">window.editNode${declarationName}();</ClientEvent>
        <Property name="caption">编辑当前节点</Property>
        <Property name="icon">url(>skin>common/icons.gif) -100px -120px</Property>
      </ToolBarButton>
      <ToolBarButton id="buttonDelete${declarationName}">
        <ClientEvent name="onClick">window.deleteNode${declarationName}();&#xD;
</ClientEvent>
        <Property name="caption">删除当前节点</Property>
        <Property name="icon">url(>skin>common/icons.gif) -80px -120px</Property>
      </ToolBarButton>
    </ToolBar>
    <SplitPanel>
      <Property name="position">200</Property>
      <MainControl>
        <Panel id="panelMain">
          <Property name="caption">节点详细信息</Property>
          <Children>
            <AutoForm id="autoForm${declarationName}Detail" layoutConstraint="center">
              <Property name="dataSet">dataSet${declarationName}</Property>
              <Property name="dataPath">!current${declarationName}</Property>
              <Property name="cols">*,*</Property>
              <Property name="readOnly">true</Property>
              <Property name="labelAlign">right</Property>
              <Property name="dataType">dataType${declarationName}</Property>
<#assign fields=pojo.getPojoFields()>
<#foreach field in fields>
<#if field.getIsSampleField()>
              <AutoFormElement>
                <Property name="name">${field.getName()}</Property>
                <Property name="property">${field.getName()}</Property>
                <Editor/>
              </AutoFormElement>
</#if>
</#foreach>              
            </AutoForm>
          </Children>
        </Panel>
      </MainControl>
      <SideControl>
        <Panel id="panelTree">
          <Property name="caption">递归树</Property>
          <Property name="collapseable">true</Property>
          <Children>
            <DataTree id="dataTree${declarationName}">
              <ClientEvent name="onContextMenu">
				view.id(&quot;menuOperate${declarationName}&quot;).show({&#xD;
                	position: {&#xD;
                		left: arg.event.pageX,&#xD;
                		top: arg.event.pageY&#xD;
                	}&#xD;
                });&#xD;
              </ClientEvent>
              <ClientEvent name="onDragStop" signature="self,arg,updateAction${declarationName}">
				var sourceObject = arg.draggingInfo.get(&quot;object&quot;);&#xD;
                var targetObject = arg.draggingInfo.get(&quot;targetObject&quot;);&#xD;
                var sourceEntity = sourceObject.get(&quot;data&quot;);&#xD;
                var targetEntity = targetObject.get(&quot;data&quot;);&#xD;
                sourceEntity.set(&quot;parentId&quot;, targetEntity.get(&quot;id&quot;));&#xD;
                window.$CurrentOperation${declarationName} = &quot;DragTreeNode&quot;;
				updateAction${declarationName}.execute();
			  </ClientEvent>
              <Property name="dataSet">dataSet${declarationName}</Property>
              <Property name="currentNodeDataPath">current${declarationName}</Property>
              <Property name="draggable">true</Property>
              <Property name="droppable">true</Property>
              <Property name="dragTags">node</Property>
              <Property name="droppableTags">node</Property>
              <Property name="dropMode">onOrInsertItems</Property>
              <Property name="currentNodeDataPath">current${declarationName}</Property>
              <BindingConfigs>
                <BindingConfig>
                  <Property name="name">node</Property>
                  <Property name="labelProperty">name</Property>
                  <Property name="recursive">true</Property>
                  <Property name="childrenProperty">${declarationName?lower_case}</Property>
                </BindingConfig>
              </BindingConfigs>
            </DataTree>
          </Children>
        </Panel>
      </SideControl>
    </SplitPanel>
    <Dialog id="dialog${declarationName}">
      <Property name="center">true</Property>
      <Property name="width">500</Property>
      <Property name="height">310</Property>
      <Property name="modal">true</Property>
      <Buttons>
        <Button id="buttonSave${declarationName}">
          <ClientEvent name="onClick">
			var action = view.id(&quot;updateAction${declarationName}&quot;);&#xD;
            var dialog = view.id(&quot;dialog${declarationName}&quot;);&#xD;
            dorado.MessageBox.show({&#xD;
                title: &quot;提示&quot;,&#xD;
                icon: dorado.MessageBox.INFO_ICON,&#xD;
                message: &quot;确定要保存当前数据？&quot;,&#xD;
                buttons: [&quot;yes&quot;, &quot;no&quot;],&#xD;
                detailCallback: function(buttonId){&#xD;
                    if (buttonId == &quot;yes&quot;) {&#xD;
                        action.execute(function(){&#xD;
                            dialog.hide();&#xD;
                        });            &#xD;
                    }&#xD;
                    else {&#xD;
                    &#xD;
                    }&#xD;
                }&#xD;
            })&#xD;
          </ClientEvent>
          <Property name="caption">保存</Property>
          <Property name="width">80</Property>
          <Property name="icon">url(>skin>common/icons.gif) -140px -20px</Property>
        </Button>
        <Button id="buttonCancel${declarationName}">
          <ClientEvent name="onClick">
            var tree = view.id(&quot;dataTree${declarationName}&quot;);&#xD;
            var entity = tree.get(&quot;currentEntity&quot;);&#xD;
            var dialog = view.id(&quot;dialog${declarationName}&quot;);&#xD;
            if (entity) {&#xD;
                dorado.MessageBox.show({&#xD;
                    title: &quot;提示&quot;,&#xD;
                    icon: dorado.MessageBox.INFO_ICON,&#xD;
                    message: &quot;确定要取消当前操作？&quot;,&#xD;
                    buttons: [&quot;yes&quot;, &quot;no&quot;],&#xD;
                    detailCallback: function(buttonId){&#xD;
                        if (buttonId == &quot;yes&quot;) {&#xD;
                            entity.cancel();&#xD;
            				dialog.hide();&#xD;
                        }
                    }&#xD;
                })&#xD;
            }&#xD;
          </ClientEvent>
          <Property name="caption">取消</Property>
          <Property name="width">80</Property>
          <Property name="icon">url(>skin>common/icons.gif) -40px -0px</Property>
        </Button>
      </Buttons>
      <Children>
        <AutoForm id="autoForm${declarationName}">
          <Property name="dataSet">dataSet${declarationName}</Property>
          <Property name="dataPath">!current${declarationName}</Property>
          <Property name="labelAlign">right</Property>
          <Property name="dataType">dataType${declarationName}</Property>
<#foreach field in fields>
<#if field.getIsSampleField()>
              <AutoFormElement>
                <Property name="name">${field.getName()}</Property>
                <Property name="property">${field.getName()}</Property>
                <Editor/>
              </AutoFormElement>
</#if>
</#foreach>   
        </AutoForm>
      </Children>
    </Dialog>
    <UpdateAction id="updateAction${declarationName}">
      <Property name="dataResolver">${pojoview}#save${declarationName}</Property>
      <Property name="successMessage">数据已保存</Property>
      <UpdateItem>
        <Property name="dataSet">dataSet${declarationName}</Property>
        <Property name="dataPath">!DIRTY_TREE</Property>
        <Property name="refreshMode">value</Property>
      </UpdateItem>
    </UpdateAction>
  </View>
</ViewConfig>
