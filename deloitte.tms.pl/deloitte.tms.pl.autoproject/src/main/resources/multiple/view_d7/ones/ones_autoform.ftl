                <AutoForm id="autoFormModify${childpropertyname}">
                      <Property name="dataSet">dataSet${declarationName}</Property>
                      <Property name="dataPath">#.#rel${childpropertyname}</Property>
		              <Property name="cols">*,*,*</Property>
		              <Property name="labelAlign">right</Property>
		              <Property name="labelWidth">120</Property>
<#foreach field in fields>
<#if field.getIsSampleField()&&field.getIsNotBaseEntiry()>
                      <AutoFormElement>
                        <Property name="property">${field.getName()}</Property>
                        <Property name="name">${field.getName()}</Property>
                        <#if field.getDataType()=="Date"><Property name="trigger">autoDateDropDown</Property><#else></#if>
                        <Editor/>
                      </AutoFormElement>
</#if>                      
</#foreach>                       
                </AutoForm>