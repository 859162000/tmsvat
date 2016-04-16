    <AutoForm id="formCondition${declarationName}" layoutConstraint="top">
      <Property name="cols">300,300</Property>
      <Property name="showHint">false</Property>
      <Property name="createPrivateDataSet">false</Property>
<#assign fields=pojo.getPojoFields()>
<#foreach field in fields>
<#if field.getIsSampleField()&&field.getIsNotBaseEntiry()>
      <AutoFormElement>
        <Property name="name">${field.getName()}</Property>
        <Property name="property">${field.getName()}</Property>
        <Property name="label"><#if field.getComment()?exists>${field.getComment()}<#else>${field.getName()}</#if></Property>
        <#if field.getDataType()=="Date"><Property name="trigger">autoDateDropDown</Property>
        	<Editor>
              <TextEditor>
                <Property name="dataType">Date</Property>
              </TextEditor>
            </Editor>
         <#else></#if>
        <Editor/>
      </AutoFormElement>
</#if>
</#foreach>
    </AutoForm>