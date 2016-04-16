    <AutoForm id="formCondition${declarationName}" layoutConstraint="top">
      <Property name="cols">300,300</Property>
      <Property name="showHint">false</Property>
      <Property name="dataSet">dataSetQuery${declarationName}</Property>
      <Property name="createPrivateDataSet">false</Property>
<#assign fields=pojo.getPojoFields()>
<#foreach field in fields>
<#if field.getIsSampleField()&&field.getIsNotBaseEntiry()>
      <AutoFormElement>
        <Property name="name">${field.getName()}</Property>
        <Property name="property">${field.getName()}</Property>
        <#if field.getDataType()=="Date"><Property name="trigger">autoDateDropDown</Property><#else></#if>
        <Editor/>
      </AutoFormElement>
</#if>
</#foreach>
    </AutoForm>