<#assign maindeclarationName = mainpojo.importType(mainpojo.getDeclarationName())>
<#assign pojoview=mainpojo.getDeclarationNameFirstLetterLower()+"View">
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign pojoclassname = "${pojo.getPackageName()}.${pojo.getClassName()}">
<#assign realPackageAndClassPath = "${pojo.getRealPackageAndClassPath()}">
<#assign masterid = mainpojo.getDeclarationNameFirstLetterLower()+"Id">
<#assign childpropertyname = "${pojo.getDeclarationNameFirstLetterLower()}s"> 
<#assign fields=pojo.getPojoFields()>
<#assign mainpojodeclarationName = mainpojo.importType(mainpojo.getDeclarationName())>
<#assign mainpojorealPackageAndClassPath = "${mainpojo.getRealPackageAndClassPath()}">
<?xml version="1.0" encoding="UTF-8"?>
<ViewConfig>
  <Property name="metaData">
    <Property name="securityTag">${declarationName}.manager</Property>
  </Property>
  <View>
    <DataSet id="dataSet${declarationName}">
      <Property name="dataType">[dataType${declarationName}InParam]</Property>
      <Property name="dataProvider">${pojoview}#load${declarationName}</Property>
      <Property name="loadMode">lazy</Property>
      <Property name="parameter">
        <Entity>
          <Property name="${masterid}">${r"${param[&quot;"}${masterid}&quot;]}</Property>
        </Entity>
      </Property>
    </DataSet>
    <UpdateAction id="updateActionSave${declarationName}">
      <Property name="dataResolver">${pojoview}#save${declarationName}</Property>
      <Property name="executingMessage">数据提交中...</Property>
      <Property name="successMessage">数据提交成功!</Property>
      <UpdateItem>
        <Property name="dataSet">dataSet${declarationName}</Property>
        <Property name="dataPath">!DIRTY_TREE</Property>
      </UpdateItem>
    </UpdateAction>   
    <ToolBar>
      <DataPilot id="dataPilot${childpropertyname}">
        <Property name="dataSet">dataSet${declarationName}</Property>
      </DataPilot>
      <ToolBarButton id="searchBtn${childpropertyname}">
        <Property name="caption">查询</Property>
        <Property name="icon">url(>skin>common/icons.gif) -40px -100px</Property>
      </ToolBarButton>
      <ToolBarButton id="addBtn${childpropertyname}">
        <Property name="caption">添加</Property>
        <Property name="icon">url(>skin>common/icons.gif) -120px -0px</Property>
      </ToolBarButton>
      <ToolBarButton id="modifyBtn${childpropertyname}">       
        <Property name="caption">修改</Property>
        <Property name="icon"> url(>skin>common/icons.gif) -200px -0px</Property>
      </ToolBarButton>
      <ToolBarButton id="delBtn${childpropertyname}">
        <Property name="caption">删除</Property>
        <Property name="icon">url(>skin>common/icons.gif) -140px -0px</Property>
      </ToolBarButton>
    </ToolBar>
    <DataGrid id="dataGrid${declarationName}">
      <Property name="dataSet">dataSet${declarationName}</Property>
      <Property name="readOnly">true</Property>
      <Property name="filterMode">serverSide</Property>
      <Property name="showFilterBar">false</Property>
      <Property name="selectionMode">multiRows</Property>
      <Property name="highlightSelectedRow">false</Property>
      <Property name="height">300</Property>
      <RowSelectorColumn/>
<#foreach field in fields>
<#if field.getIsSampleField()&&field.getIsNotBaseEntiry()>
      <DataColumn>
        <Property name="name">${field.getName()}</Property>
        <Property name="property">${field.getName()}</Property>
        <Property name="width">100</Property>
        <#if pojo.getEditType().toString()=="grid"&&field.getName()=="id"><Property name="readOnly">true</Property><#else></#if>
        <#if field.getDataType()=="Date"><Property name="trigger">autoDateDropDown</Property><#else></#if>
        <#if field.getDropdown()?exists><#assign dropdown=field.getDropdown()><Property name="trigger">dropDown${dropdown.relClass()}</Property></#if>
      </DataColumn>
</#if>                   
</#foreach>
      <DataColumn id="col${declarationName}operation">
        <Property name="width">100</Property>
      </DataColumn> 
    </DataGrid>
    <Dialog id="dialog${declarationName}">
      <#if pojo.getComment()?exists><Property name="caption">${pojo.getComment()}</Property></#if>
      <Property name="modal">true</Property>
      <Property name="center">true</Property>
      <Property name="width">95%</Property>
      <Property name="height">95%</Property>
      <Buttons>
        <Button id="saveSingleBtn${declarationName}">
          <Property name="caption">确定</Property>
          <Property name="icon"></Property>
        </Button>
        <Button id="cancelBtn${declarationName}">
          <Property name="caption"> 取消</Property>
          <Property name="icon"></Property>
        </Button>
      </Buttons>
      <Children>
        <AutoForm>
          <Property name="dataSet">dataSet${declarationName}</Property>
          <Property name="cols">*,*,*</Property>
          <Property name="labelAlign">right</Property>
          <Property name="labelWidth">120</Property>
<#foreach field in fields>
<#if field.getIsSampleField()&&field.getIsNotBaseEntiry()>
          <AutoFormElement>
            <Property name="property">${field.getName()}</Property>
            <Property name="name">${field.getName()}</Property>
            <Property name="label"><#if field.getComment()?exists>${field.getComment()}<#else>${field.getName()}</#if></Property>
            <#if field.getDataType()=="Date"><Property name="trigger">autoDateDropDown</Property><#else></#if>
            <Editor/>
          </AutoFormElement>
</#if>                      
</#foreach>
        </AutoForm>
      </Children>
    </Dialog>
    <DateDropDown id="autoDateDropDown">
      <Property name="autoOpen">true</Property>
    </DateDropDown>
  </View>
</ViewConfig>