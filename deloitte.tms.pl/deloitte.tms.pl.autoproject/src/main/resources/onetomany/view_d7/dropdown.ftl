    <DateDropDown id="autoDateDropDown">
      <Property name="autoOpen">true</Property>
    </DateDropDown>
<#assign fields=pojo.getPojoFields()>
<#foreach field in fields>
<#if field.getDropdown()?exists>
<#assign dropdown=field.getDropdown()>
<#if dropdown.dropDownType().toString()=="datasetDropDown">
    <DataSet id="dataSet${dropdown.relClass()}">
      <Property name="dataType">[dataType${dropdown.relClass()}]</Property>
      <Property name="dataProvider">${dropdown.filterProvider()}</Property>
      <Property name="pageSize">10</Property>
    </DataSet>
    <DataSetDropDown id="dropDown${dropdown.relClass()}">
      <Property name="dataSet">dataSet${dropdown.relClass()}</Property>
      <Property name="autoOpen">true</Property>
      <Property name="dynaFilter">true</Property>
      <Property name="displayProperty">${dropdown.disProperty()}</Property>
      <Property name="filterOnOpen">true</Property>
      <Property name="assignmentMap">${dropdown.assignmentMap()}</Property>
      <Property name="reloadDataOnOpen">true</Property>
      <Property name="property">${dropdown.disProperty()}</Property>
      <Property name="editable">false</Property>
    </DataSetDropDown>
<#elseif dropdown.dropDownType().toString()=="dialog">
    <CustomDropDown id="dropDown${dropdown.relClass()}">
      <ClientEvent name="beforeExecute">this.dialogSelect${dropdown.relClass()}();&#xD;
</ClientEvent>
      <Property name="autoOpen">true</Property>
      <Property name="height">1</Property>
      <Property name="width">1</Property>
      <Import id="import${dropdown.relClass()}Select" src="${dropdown.filterProvider()}#$dialog${dropdown.relClass()}"/>
    </CustomDropDown>
</#if>
</#if>
</#foreach>