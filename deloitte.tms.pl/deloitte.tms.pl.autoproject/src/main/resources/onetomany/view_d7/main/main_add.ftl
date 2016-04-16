            	<AutoForm id="autoFormModify${declarationName}">
                  <Property name="dataSet">dataSet${declarationName}</Property>
	              <Property name="cols">*,*,*</Property>
	              <Property name="labelAlign">right</Property>
	              <Property name="labelWidth">120</Property>
<#foreach field in fields>
<#if field.getIsSampleField()&&field.getIsNotBaseEntiry()>
                  <AutoFormElement>
                    <Property name="name">${field.getName()}</Property>
                    <Property name="property">${field.getName()}</Property>
                    <#if field.getDataType()=="Date"><Property name="trigger">autoDateDropDown</Property><#else></#if>
                    <#if field.getDropdown()?exists><#assign dropdown=field.getDropdown()><Property name="trigger">dropDown${dropdown.relClass()}</Property></#if>
                    <Editor/>
                  </AutoFormElement>   
</#if>                             
</#foreach>
                </AutoForm>