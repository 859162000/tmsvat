                <Dialog id="dialog${childdeclarationName}">
                  <Property name="caption"><#if child.getComment()?exists>${child.getComment()}</#if></Property>
                  <Property name="modal">true</Property>
                  <Property name="center">true</Property>
                  <Property name="width">100%</Property>
                  <Property name="height">100%</Property>
                  <Buttons>
                    <Button id="saveSingleBtn${childdeclarationName}">
                      <Property name="caption">确定</Property>
                      <Property name="icon"></Property>
                    </Button>
                    <Button id="cancelBtn${childdeclarationName}">
                      <Property name="caption"> 取消</Property>
                      <Property name="icon"></Property>
                    </Button>
                  </Buttons>
                  <Children>
                    <AutoForm>
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
                        <Property name="label"><#if field.getComment()?exists>${field.getComment()}<#else>${field.getName()}</#if></Property>
                        <#if field.getDataType()=="Date"><Property name="trigger">autoDateDropDown</Property><#else></#if>
                        <Editor/>
                      </AutoFormElement>
</#if>                      
</#foreach>                       
                    </AutoForm>
                  </Children>
                </Dialog>