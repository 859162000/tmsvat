            <Dialog id="dialog${declarationName}">
              <Property name="caption">${r"${res[&quot;core.common"}/DataEdit&quot;]}</Property>
              <Property name="modal">true</Property>
              <Property name="center">true</Property>
              <Property name="width">98%</Property>
              <Property name="height">98%</Property>
              <Buttons>
                <Button id="saveSingleBtn${declarationName}">
                  <Property name="caption">${r"${res[&quot;core.common"}/Save&quot;]}</Property>
                  <Property name="icon"></Property>
                  <Property name="icon"> url(>skin>common/icons.gif) -140px -20px</Property>
                </Button>
                <Button id="cancelBtn${declarationName}">
                  <Property name="caption">${r"${res[&quot;core.common"}/Cancel&quot;]}</Property>
                  <Property name="icon"></Property>
                  <Property name="icon"> url(>skin>common/icons.gif) -40px -0px</Property>
                </Button>
              </Buttons>
              <Children>
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
              </Children>
            </Dialog>