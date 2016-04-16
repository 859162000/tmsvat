<#assign pojo=oneToManyPojo.getOne()>
<#assign many=oneToManyPojo.getMany()>
<#assign ones=oneToManyPojo.getOnes()>
<#assign pojoview=pojo.getDeclarationNameFirstLetterLower()+"View">
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign pojoclassname = "${pojo.getPackageName()}.model.${declarationName}">
<#assign masterid = pojo.getDeclarationNameFirstLetterLower()+"Id">
<?xml version="1.0" encoding="UTF-8"?>
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
<#foreach child in many>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>
      <Reference name="${child.getDeclarationNameFirstLetterLower()}s">
        <Property name="dataType">[dataType${childdeclarationName}]</Property>
        <Property name="dataProvider">${pojoview}#load${childdeclarationName}</Property>
        <Property name="pageSize">10</Property>
        <Property name="parameter">
          <Entity>
            <Property name="${masterid}">${"$"+"$"+"{"+"this.id"+"}"}</Property>
          </Entity>
        </Property>
      </Reference>
</#foreach>
<#foreach child in ones>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>
      <Reference name="${child.getDeclarationNameFirstLetterLower()}">
        <Property name="dataType">dataType${childdeclarationName}</Property>
        <Property name="dataProvider">${pojoview}#find${childdeclarationName}</Property>
        <Property name="parameter">
          <Entity>
            <Property name="${masterid}">${"$"+"$"+"{"+"this.id"+"}"}</Property>
          </Entity>
        </Property>
      </Reference>
</#foreach>
    </DataType>
<#foreach child in many>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>
<#assign childpojoclassname = "${child.getPackageName()}.model.${childdeclarationName}">
    <DataType name="dataType${childdeclarationName}">
      <Property name="creationType">${childpojoclassname}</Property>
<#assign fields=child.getPojoFields()>
<#foreach field in fields>
<#if field.getIsSampleField()>
      <PropertyDef name="${field.getName()}">
        <Property name="dataType">${field.getDataType()}</Property>
        <Property name="label"><#if field.getComment()?exists>${field.getComment()}<#else>${field.getName()}</#if></Property>
      </PropertyDef>
</#if>      
</#foreach>
      <PropertyDef name="${pojo.getDeclarationNameFirstLetterLower()}">
        <Property name="dataType">dataType${declarationName}</Property>
        <Property name="label">主表信息</Property>
      </PropertyDef>
    </DataType>
</#foreach>
<#foreach child in ones>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>
<#assign childpojoclassname = "${child.getPackageName()}.model.${childdeclarationName}">
    <DataType name="dataType${childdeclarationName}">
      <Property name="creationType">${childpojoclassname}</Property>
<#assign fields=child.getPojoFields()>
<#foreach field in fields>
<#if field.getIsSampleField()>
      <PropertyDef name="${field.getName()}">
        <Property name="dataType">${field.getDataType()}</Property>
        <Property name="label"><#if field.getComment()?exists>${field.getComment()}<#else>${field.getName()}</#if></Property>
      </PropertyDef>
</#if>      
</#foreach>
      <PropertyDef name="${pojo.getDeclarationNameFirstLetterLower()}">
        <Property name="dataType">dataType${declarationName}</Property>
        <Property name="label">主表信息</Property>
      </PropertyDef>
    </DataType>
</#foreach>
  </Model>
  <View>
    <ClientEvent name="onReady">var intro=view.get('#intro${declarationName}');&#xD;
setTimeout(function(){&#xD;
	intro.start();&#xD;
&#xD;
},1000);&#xD;
</ClientEvent>
    <DataSet id="dataSet${declarationName}">
      <Property name="dataType">[dataType${declarationName}]</Property>
      <Property name="dataProvider">${pojoview}#load${declarationName}</Property>
      <Property name="pageSize">10</Property>
    </DataSet>
    <UpdateAction id="updateActionSave${declarationName}">
      <Property name="dataResolver">${pojoview}#save${declarationName}</Property>
      <Property name="confirmMessage">确定要保存当前数据?</Property>
      <Property name="executingMessage">数据提交中...</Property>
      <Property name="successMessage">数据提交成功!</Property>
      <UpdateItem>
        <Property name="dataSet">dataSet${declarationName}</Property>
      </UpdateItem>
    </UpdateAction>
<#foreach child in many>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>
<#assign childpojoclassname = "${child.getPackageName()}.model.${childdeclarationName}">    
<#assign childpropertyname = "${child.getDeclarationNameFirstLetterLower()}s"> 
    <UpdateAction id="updateActionSave${childdeclarationName}">
      <Property name="dataResolver">${pojoview}#save${childdeclarationName}</Property>
      <Property name="successMessage">操作成功！</Property>
      <UpdateItem>
        <Property name="dataSet">dataSet${declarationName}</Property>
        <Property name="dataPath">#.${childpropertyname}[#dirty]</Property>
      </UpdateItem>
    </UpdateAction>
</#foreach>
<#foreach child in ones>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>
<#assign childpojoclassname = "${child.getPackageName()}.model.${childdeclarationName}">    
<#assign childpropertyname = "${child.getDeclarationNameFirstLetterLower()}"> 
    <UpdateAction id="updateActionSave${childdeclarationName}">
      <Property name="dataResolver">${pojoview}#save${childdeclarationName}</Property>
      <Property name="successMessage">操作成功！</Property>
      <UpdateItem>
        <Property name="dataSet">dataSet${declarationName}</Property>
        <Property name="dataPath">#.${childpropertyname}[#current]</Property>
      </UpdateItem>
    </UpdateAction>
</#foreach>
<#if many?size gt 0>
    <SplitPanel>
      <Property name="position">200</Property>
      <Property name="direction">top</Property>
      <MainControl>
        <TabControl>
<#foreach child in many>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>
<#assign childpojoclassname = "${child.getPackageName()}.model.${childdeclarationName}">  
<#assign childpropertyname = "${child.getDeclarationNameFirstLetterLower()}s">          
          <ControlTab>
            <Property name="caption">${child.getComment()}</Property>
            <Panel>
              <Children>              
                <ToolBar>
                  <ToolBarButton>
                    <ClientEvent name="onClick">
var data=this.id(&quot;dataSet${declarationName}&quot;).getData(&quot;#.${childpropertyname}&quot;)&#xD;
if(data){&#xD;
	//dorado.MessageBox.alert(&quot;来自从表toolBarButton添加按键的onClick事件, 补充主表元素&quot;);
	data.insert({
		${masterid}:this.id(&quot;dataSet${declarationName}&quot;).getData(&quot;#.id&quot;)	
	});&#xD;
	this.id(&quot;dialog${childdeclarationName}&quot;).show();&#xD;
}else{&#xD;
	dorado.MessageBox.alert(&quot;当前没有选中的主表信息，不能添加子表信息！&quot;);&#xD;
}&#xD;

            </ClientEvent>
                    <Property name="caption">添加</Property>
                    <Property name="icon">url(>skin>common/icons.gif) -120px -0px</Property>
                  </ToolBarButton>
                  <ToolBarButton>
                    <ClientEvent name="onClick">
var data=this.id(&quot;dataSet${declarationName}&quot;).getData(&quot;#.${childpropertyname}&quot;);&#xD;
if(data.current){&#xD;
	var action=this.id(&quot;updateActionSave${childdeclarationName}&quot;);&#xD;
	dorado.MessageBox.confirm(&quot;您真的要删除当前子表信息吗？&quot;,function(){&#xD;
		data.remove();&#xD;
		action.execute();&#xD;
	});&#xD;
}else{&#xD;
	dorado.MessageBox.alert(&quot;当前没有子表信息可供删除！&quot;);&#xD;
}&#xD;
            </ClientEvent>
                    <Property name="caption">删除</Property>
                    <Property name="icon">url(>skin>common/icons.gif) -140px -0px</Property>
                  </ToolBarButton>
                  <ToolBarButton>
                    <ClientEvent name="onClick">
var data=this.id(&quot;dataSet${declarationName}&quot;).getData(&quot;#.${childpropertyname}&quot;);&#xD;
if(data.current){&#xD;
	this.id(&quot;dialog${childdeclarationName}&quot;).show();&#xD;
}else{&#xD;
	dorado.MessageBox.alert(&quot;当前没有子表信息可供修改！&quot;);&#xD;
}&#xD;
        </ClientEvent>
                    <Property name="caption">修改</Property>
                    <Property name="icon">url(>skin>common/icons.gif) -200px -0px</Property>
                  </ToolBarButton>                
                </ToolBar>
                <DataGrid id="dataGrid${childdeclarationName}">
                  <Property name="dataSet">dataSet${declarationName}</Property>
                  <Property name="dataPath">#.${childpropertyname}</Property>
                  <Property name="readOnly">true</Property>
                  <Property name="selectionMode">multiRows</Property>
                  <Property name="filterMode">serverSide</Property>
                  <Property name="showFilterBar">false</Property>
                  <RowSelectorColumn/>
<#assign fields=child.getPojoFields()>
<#foreach field in fields>
<#if field.getIsSampleField()>
                  <DataColumn>
                    <Property name="name">${field.getName()}</Property>
                    <Property name="property">${field.getName()}</Property>
                  </DataColumn>
</#if>                   
</#foreach>      
                </DataGrid>
                <Dialog id="dialog${childdeclarationName}">
                  <ClientEvent name="onHide">this.id(&quot;dataSet${declarationName}&quot;).getData(&quot;#.${childpropertyname}&quot;).cancel();
</ClientEvent>
                  <Property name="caption">${child.getComment()}</Property>
                  <Property name="modal">true</Property>
                  <Property name="center">true</Property>
                  <Property name="width">400</Property>
                  <Property name="height">400</Property>
                  <Buttons>
                    <Button>
                      <ClientEvent name="onClick">var view = this;
view.id(&quot;updateActionSave${childdeclarationName}&quot;).execute(function(){
	view.id(&quot;dialog${childdeclarationName}&quot;).hide();
});
</ClientEvent>
                      <Property name="caption">确定</Property>
                      <Property name="icon"></Property>
                    </Button>
                    <Button>
                      <ClientEvent name="onClick">this.id(&quot;dialog${childdeclarationName}&quot;).hide();
</ClientEvent>
                      <Property name="caption"> 取消</Property>
                      <Property name="icon"></Property>
                    </Button>
                  </Buttons>
                  <Children>
                    <AutoForm>
                      <Property name="dataSet">dataSet${declarationName}</Property>
                      <Property name="dataPath">#.#${childpropertyname}</Property>
                      <Property name="labelAlign">right</Property>
                      <Property name="cols">*</Property>
<#foreach field in fields>
<#if field.getIsSampleField()>
                      <AutoFormElement>
                        <Property name="property">${field.getName()}</Property>
                        <Property name="name">${field.getName()}</Property>
                        <Editor/>
                      </AutoFormElement>
</#if>                      
</#foreach>                       
                    </AutoForm>
                  </Children>
                </Dialog>
              </Children>
            </Panel>
          </ControlTab>
</#foreach>
<#foreach child in ones>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>
<#assign childpojoclassname = "${child.getPackageName()}.model.${childdeclarationName}">  
<#assign childpropertyname = "${child.getDeclarationNameFirstLetterLower()}">      
<#assign fields=child.getPojoFields()>    
          <ControlTab>
            <Property name="caption"><#if child.getComment()?exists>${child.getComment()}<#else></#if></Property>
            <Panel>
              <Children>
                <ToolBar>
                  <ToolBarButton>
                    <ClientEvent name="onClick">var view = this;
view.id(&quot;updateActionSave${childdeclarationName}&quot;).execute(function(){
	view.id(&quot;dialog${childdeclarationName}&quot;).hide();
});
</ClientEvent>
                    <Property name="caption">保存</Property>
                    <Property name="icon">url(>skin>common/icons.gif) -140px -20px</Property>
                  </ToolBarButton>
                </ToolBar>
                <AutoForm>
                      <Property name="dataSet">dataSet${declarationName}</Property>
                      <Property name="dataPath">#.#${childpropertyname}</Property>
                      <Property name="labelAlign">right</Property>
                      <Property name="cols">*</Property>
<#foreach field in fields>
<#if field.getIsSampleField()>
                      <AutoFormElement>
                        <Property name="property">${field.getName()}</Property>
                        <Property name="name">${field.getName()}</Property>
                        <Editor/>
                      </AutoFormElement>
</#if>                      
</#foreach>                       
                </AutoForm>
              </Children>
            </Panel>
          </ControlTab>
</#foreach>
<#assign fields=pojo.getPojoFields()>        
        </TabControl>
      </MainControl>
      <SideControl>
        <Panel>
          <Property name="caption">主表数据维护</Property>
          <Children>
</#if> 
<#assign fields=pojo.getPojoFields()>            
            <ToolBar id="toolBar${declarationName}">
      <DataPilot>
      <ToolBarButton id="searchBtn${declarationName}">
        <ClientEvent name="onClick">var ds = this.id(&quot;dataSet${declarationName}&quot;);
var form = this.id(&quot;formCondition${declarationName}&quot;);
ds.set(&quot;parameter&quot;,form.get(&quot;entity&quot;)).flushAsync();
</ClientEvent>
        <Property name="caption">查询</Property>
        <Property name="icon">url(>skin>common/icons.gif) -40px -100px</Property>
      </ToolBarButton>
      <Separator/>
      <ToolBarButton id="addBtn${declarationName}">
        <ClientEvent name="onClick">var masterData=this.id(&quot;dataSet${declarationName}&quot;).getData();&#xD;
masterData.insert();&#xD;
//this.id(&quot;dialog${declarationName}&quot;).show();       </ClientEvent>
        <Property name="caption">添加</Property>
        <Property name="icon">url(>skin>common/icons.gif) -120px 0px</Property>
      </ToolBarButton>
      <ToolBarButton id="modifyBtn${declarationName}">
        <ClientEvent name="onClick">var data = this.id(&quot;dataSet${declarationName}&quot;).getData();
if (data.current) {
    this.id(&quot;dialog${declarationName}&quot;).show();
}else {
    dorado.MessageBox.alert(&quot;当前主表没有信息可供编辑！&quot;);
}</ClientEvent>
        <Property name="caption">修改</Property>
        <Property name="icon">url(>skin>common/icons.gif) -200px -0px</Property>
      </ToolBarButton>
      <ToolBarButton id="delBtn${declarationName}">
        <ClientEvent name="onClick">var masterData = this.id(&quot;dataSet${declarationName}&quot;).getData();&#xD;
var updateActionSave${declarationName} = this.id(&quot;updateActionSave${declarationName}&quot;);&#xD;
if (masterData.current) {&#xD;
    dorado.MessageBox.confirm(&quot;您真的想删除当前主表信息吗？&quot;,function(){&#xD;
        masterData.remove();&#xD;
        updateActionSave${declarationName}.execute();&#xD;
    });&#xD;
}</ClientEvent>
        <Property name="caption">删除</Property>
        <Property name="icon">url(>skin>common/icons.gif) -140px 0px</Property>
      </ToolBarButton>
      <Separator/>
      <ToolBarButton id="saveBtn">
        <ClientEvent name="onClick">var view = this;&#xD;
var action = view.id(&quot;updateActionSave${declarationName}&quot;);&#xD;
action.execute(function(){&#xD;
})&#xD;
</ClientEvent>
        <Property name="caption">保存</Property>
      </ToolBarButton>
      <Separator/>
      <Fill/>
      <TextEditor id="textEditorHelp${declarationName}">
        <Property name="trigger">listDropDown${declarationName}</Property>
        <Property name="blankText">GoTo</Property>
      </TextEditor>
      <ToolBarButton id="helpBtn${declarationName}">
        <ClientEvent name="onClick" signature="self,arg,intro1">intro${declarationName}.start();</ClientEvent>
        <Property name="caption">帮助</Property>
        <Property name="icon">url(>skin>common/icons.gif) -160px -40px</Property>
      </ToolBarButton>
    </ToolBar>      
            <DataGrid id="dataGrid${declarationName}">
              <Property name="dataSet">dataSet${declarationName}</Property>
              <Property name="readOnly">true</Property>
              <Property name="showFilterBar">false</Property>
              <Property name="filterMode">serverSide</Property>
<#foreach field in fields>
<#if field.getIsSampleField()>
              <DataColumn>
                <Property name="name">${field.getName()}</Property>
                <Property name="property">${field.getName()}</Property>
              </DataColumn>
</#if>              
</#foreach>
            </DataGrid>
            <Dialog id="dialog${declarationName}">
              <ClientEvent name="onHide">this.id(&quot;dataSet${declarationName}&quot;).getData().cancel();
</ClientEvent>
              <Property name="caption">主表数据维护</Property>
              <Property name="modal">true</Property>
              <Property name="center">true</Property>
              <Property name="width">400</Property>
              <Property name="height">400</Property>
              <Buttons>
                <Button>
                  <ClientEvent name="onClick">var view = this;
var action = view.id(&quot;updateActionSave${declarationName}&quot;);
action.execute(function(){
	view.id(&quot;dialog${declarationName}&quot;).hide();
})
</ClientEvent>
                  <Property name="caption">确定</Property>
                  <Property name="icon"></Property>
                </Button>
                <Button>
                  <ClientEvent name="onClick">this.id(&quot;dialog${declarationName}&quot;).hide();
</ClientEvent>
                  <Property name="caption">取消</Property>
                  <Property name="icon"></Property>
                </Button>
              </Buttons>
              <Children>
                <AutoForm>
                  <Property name="dataSet">dataSet${declarationName}</Property>
                  <Property name="cols">*</Property>
                  <Property name="labelAlign">right</Property>
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
<#if many?size gt 0>
          </Children>
        </Panel>
      </SideControl>
    </SplitPanel> 
</#if>     
  </View>
</ViewConfig>
