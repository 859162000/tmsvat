<#assign pojo=oneToManyPojo.getOne()>
<#assign many=oneToManyPojo.getMany()>
<#assign ones=oneToManyPojo.getOnes()>
<#assign pojoview=pojo.getDeclarationNameFirstLetterLower()+"View">
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign declarationName_firstLower = pojo.getDeclarationNameFirstLetterLower()>
<#assign pojoclassname = "${pojo.getPackageName()}.model.${declarationName}">
<#assign realPackageAndClassPath = "${pojo.getRealPackageAndClassPath()}">
<#assign masterid = pojo.getDeclarationNameFirstLetterLower()+"Id">
<?xml version="1.0" encoding="UTF-8"?>
<ViewConfig>
  <Arguments/>
  <Context/>
  <Model>
    <DataType name="dataType${declarationName}Param" parent="$dataTypeIrisDefineBase">
      <Property name="creationType">${realPackageAndClassPath}Param</Property>
<#assign fields=pojo.getPojoFields()>
<#foreach field in fields>
<#if field.getIsSampleField()>
      <PropertyDef name="${field.getName()}">
        <Property name="dataType">${field.getDataType()}</Property>
<#if field.getDropdown()?exists><#assign dropdown=field.getDropdown()>
<#if dropdown.dropDownType()=="datasetDropDown">
		<ClientEvent name="onSet">this.onSet${dropdown.relClass()}DropDown(self,arg);&#xD;
</ClientEvent>
        <Validator type="custom">
          <ClientEvent name="onValidate">this.on${dropdown.relClass()}DropDownValidate(self,arg);&#xD;
</ClientEvent>
        </Validator>
</#if>
</#if>
      </PropertyDef>
</#if>
</#foreach>
<#foreach child in many>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>
      <Reference name="rel${child.getDeclarationNameFirstLetterLower()}s">
        <Property name="dataType">[dataType${childdeclarationName}Param]</Property>
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
      <Reference name="rel${child.getDeclarationNameFirstLetterLower()}">
        <Property name="dataType">dataType${childdeclarationName}Param</Property>
        <Property name="dataProvider">${pojoview}#find${childdeclarationName}</Property>
        <Property name="parameter">
          <Entity>
            <Property name="${masterid}">${"$"+"$"+"{"+"this.id"+"}"}</Property>
          </Entity>
        </Property>
      </Reference>
</#foreach>
	  <PropertyDef name="rel${declarationName}s">
        <Property name="dataType">[SELF]</Property>
      </PropertyDef>
    </DataType>
    <DataType name="dataTypeQuery${declarationName}">
      <PropertyDef name="name"/>
      <PropertyDef name="matchonly">
        <Property name="dataType">boolean</Property>
      </PropertyDef>
      <PropertyDef name="flag">
        <Property name="dataType">boolean</Property>
        <Property name="defaultValue">true</Property>
      </PropertyDef>
    </DataType>
<#foreach child in many>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>
<#assign childpojoclassname = "${child.getPackageName()}.model.${childdeclarationName}">
<#assign childrealPackageAndClassPath = "${child.getRealPackageAndClassPath()}">
    <DataType name="dataType${childdeclarationName}Param">
      <Property name="creationType">${childrealPackageAndClassPath}Param</Property>
<#assign fields=child.getPojoFields()>
<#foreach field in fields>
<#if field.getIsSampleField()>
      <PropertyDef name="${field.getName()}">
        <Property name="dataType">${field.getDataType()}</Property>
      </PropertyDef>
</#if>      
</#foreach>
    </DataType>
</#foreach>
<#foreach child in ones>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>
<#assign childpojoclassname = "${child.getPackageName()}.model.${childdeclarationName}">
<#assign childrealPackageAndClassPath = "${child.getRealPackageAndClassPath()}">
    <DataType name="dataType${childdeclarationName}Param">
      <Property name="creationType">${childrealPackageAndClassPath}Param</Property>
<#assign fields=child.getPojoFields()>
<#foreach field in fields>
<#if field.getIsSampleField()>
      <PropertyDef name="${field.getName()}">
        <Property name="dataType">${field.getDataType()}</Property>
      </PropertyDef>
</#if>      
</#foreach>
    </DataType>
</#foreach>
  </Model>
  <View>
    <Property name="packages">font-awesome</Property>
    <DataSet id="dataSet${declarationName}">
      <Property name="dataType">[dataType${declarationName}Param]</Property>
      <Property name="dataProvider">${pojoview}#load${declarationName}s</Property>
      <Property name="loadMode">manual</Property>
    </DataSet>
    <DataSet id="dataSetQuery${declarationName}">
      <Property name="dataType">dataTypeQuery${declarationName}</Property>
    </DataSet>
    <DataSet id="dataSet${declarationName}Current">
      <Property name="dataType">dataType${declarationName}Param</Property>
    </DataSet>
    <ToolBar>
      <FormElement id="formElementName">
        <Property name="property">name</Property>
        <Property name="labelWidth">40</Property>
        <Property name="dataSet">dataSetQuery${declarationName}</Property>
        <Property name="label">${r"${res[&quot;iris."}${declarationName}/name&quot;]}</Property>
        <Editor/>
      </FormElement>
      <Button id="buttonQuery${declarationName}">
        <Property name="iconClass">fa fa-search</Property>
        <Property name="caption">${r"${res[&quot;core.common"}/Query&quot;]}</Property>
      </Button>
      <Button id="buttonPre${declarationName}">
      	<Property name="caption">${r"${res[&quot;core.common"}/Pre&quot;]}</Property>
      </Button>
      <Button id="buttonNext${declarationName}">
		<Property name="caption">${r"${res[&quot;core.common"}/Next&quot;]}</Property>
      </Button>
      <CheckBox id="checkBoxMatchonly">
        <Property name="property">matchonly</Property>
        <Property name="dataSet">dataSetQuery${declarationName}</Property>
        <Property name="caption">${r"${res[&quot;core.common"}/Matchonly&quot;]}</Property>
      </CheckBox>
      <CheckBox id="checkBoxFlag">
        <Property name="property">flag</Property>
        <Property name="value">true</Property>
        <Property name="dataSet">dataSetQuery${declarationName}</Property>
        <Property name="caption">${r"${res[&quot;core.common"}/HideDisabled&quot;]}</Property>
      </CheckBox>
      <Fill/>
      <Button id="buttonAdd${declarationName}">
        <Property name="iconClass">icon-add</Property>
        <Property name="caption">${r"${res[&quot;core.common"}/Add&quot;]}</Property>
      </Button>
      <Button id="buttonFlush${declarationName}Cache">
        <Property name="action">ajaxActionFlush${declarationName}Cache</Property>
        <Property name="caption">${r"${res[&quot;core.common"}/FlushCache&quot;]}</Property>
      </Button>
    </ToolBar>
    <DataTreeGrid id="dataTree${declarationName}">
      <Property name="currentNodeDataPath">Current${declarationName}</Property>
      <Property name="dataSet">dataSet${declarationName}</Property>
      <Property name="treeColumn">dataType</Property>
      <Property name="readOnly">true</Property>
      <Property name="selectionMode">multiRows</Property>
      <BindingConfigs>
        <BindingConfig>
          <Property name="recursive">true</Property>
          <Property name="expandLevel">1</Property>
          <Property name="labelProperty">name</Property>
          <Property name="icon"> url(>skin>common/icons.gif) -260px -40px</Property>
          <Property name="childrenProperty">rel${declarationName}s</Property>
          <Property name="checkable">true</Property>
          <Property name="checkedProperty">checked</Property>
          <Property name="autoCheckChildren">false</Property>
        </BindingConfig>
      </BindingConfigs>
      <Columns>
        <DataColumn>
          <Property name="property">dataType</Property>
          <Property name="name">dataType</Property>
          <Editor/>
        </DataColumn>
        <DataColumn>
          <Property name="name">code</Property>
          <Property name="property">code</Property>
          <Editor/>
        </DataColumn>
        <DataColumn>
          <Property name="property">name</Property>
          <Property name="name">name</Property>
          <Editor/>
        </DataColumn>
        <DataColumn>
          <Property name="name">version</Property>
          <Property name="property">version</Property>
          <Editor/>
        </DataColumn>
        <DataColumn>
          <Property name="property">orgName</Property>
          <Property name="name">orgName</Property>
          <Editor/>
        </DataColumn>
        <DataColumn>
          <Property name="name">createdBy</Property>
          <Property name="property">createdBy</Property>
          <Editor/>
        </DataColumn>
        <DataColumn>
          <Property name="name">createDate</Property>
          <Property name="property">createDate</Property>
          <Editor/>
        </DataColumn>
      </Columns>
    </DataTreeGrid>
    <Dialog id="dialog${declarationName}Detail">
      <Property name="width">98%</Property>
      <Property name="height">98%</Property>
      <Property name="closeable">false</Property>
      <Buttons>
        <Button id="button${declarationName}Save" layoutConstraint="right:30">
          <Property name="icon"> url(>skin>common/icons.gif) -20px -0px</Property>
          <Property name="action">updateActionSave${declarationName}s</Property>
          <Property name="caption">${r"${res[&quot;core.common"}/Save&quot;]}</Property>
        </Button>
        <Button id="button${declarationName}Cancle">
        	<Property name="caption">${r"${res[&quot;core.common"}/Cancel&quot;]}</Property>
        </Button>
      </Buttons>
      <Children>
        <AutoForm id="autoForm${declarationName}" layoutConstraint="center">
          <Property name="dataSet">dataSet${declarationName}Current</Property>
          <Property name="labelWidth">120</Property>
          <AutoFormElement>
            <Property name="name">parentName</Property>
            <Property name="property">parentName</Property>
            <Property name="trigger">${declarationName_firstLower}SingleSelectParent</Property>
            <Editor/>
          </AutoFormElement>
          <AutoFormElement>
            <Property name="label"></Property>
            <Editor/>
          </AutoFormElement>
          <AutoFormElement>
            <Property name="name">code</Property>
            <Property name="property">code</Property>
            <Editor/>
          </AutoFormElement>
          <AutoFormElement>
            <Property name="label"></Property>
            <Editor/>
          </AutoFormElement>
          <AutoFormElement>
            <Property name="name">name</Property>
            <Property name="property">name</Property>
            <Editor/>
          </AutoFormElement>
          <AutoFormElement>
            <Property name="label"></Property>
            <Editor/>
          </AutoFormElement>
          <AutoFormElement>
            <Property name="name">virtual</Property>
            <Property name="property">virtual</Property>
            <Property name="label">${r"${res[&quot;iris."}${declarationName}/virtual&quot;]}</Property>
            <Editor>
              <CheckBox id="checkBoxVirtual"/>
            </Editor>
          </AutoFormElement>
          <AutoFormElement>
            <Property name="name">status</Property>
            <Property name="property">status</Property>
            <Editor/>
          </AutoFormElement>
          <AutoFormElement>
            <Property name="label">${r"${res[&quot;core.common"}/AvailabilityPeriodFrom&quot;]}</Property>
            <Editor>
              <Container layout="form cols:*,*;padding:0px">
                <Property name="contentOverflow">hidden</Property>
                <TextEditor>
                  <Property name="dataSet">dataSet${declarationName}Current</Property>
                  <Property name="property">effectDate</Property>
                </TextEditor>
                <FormElement>
                  <Property name="property">quitDate</Property>
                  <Property name="dataSet">dataSet${declarationName}Current</Property>
                  <Property name="label">${r"${res[&quot;core.common"}/AvailabilityPeriodTo&quot;]}</Property>
                  <Property name="labelWidth">20</Property>
                  <Editor/>
                </FormElement>
              </Container>
            </Editor>
          </AutoFormElement>
        </AutoForm>
      </Children>
      <Tools/>
    </Dialog>
    <UpdateAction id="updateActionSave${declarationName}s">
      <Property name="dataResolver">${pojoview}#save${declarationName}Node</Property>
      <Property name="successMessage">${r"${res[&quot;core.common"}/SaveDataSucess&quot;]}</Property>
      <UpdateItem>
        <Property name="dataSet">dataSet${declarationName}Current</Property>
        <Property name="dataPath">!CASCADE_DIRTY</Property>
      </UpdateItem>
    </UpdateAction>
    <AjaxAction id="ajaxActionCheck${declarationName}Children">
      <Property name="service">ling2.deptMaintain#countChildren</Property>
    </AjaxAction>
    <AjaxAction id="ajaxActionFlush${declarationName}Cache">
      <Property name="service">${pojoview}#flush${declarationName}Cache</Property>
    </AjaxAction>
    <${declarationName}SingleSelect id="${declarationName_firstLower}SingleSelectParent">
      <Property name="assignmentMap">parentId=id,parentName=name</Property>
      <Property name="autoOpen">true</Property>
      <Property name="height">300</Property>
    </${declarationName}SingleSelect>
  </View>
</ViewConfig>