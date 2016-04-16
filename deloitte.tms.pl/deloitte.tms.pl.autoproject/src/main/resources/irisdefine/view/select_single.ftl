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
  <Model/>
  <View>
    <CustomDropDown id="DropDown${declarationName}SingleSelect">
      <ClientEvent name="onCreate">self.dataProvider=&quot;${r"${acomp"}.prop('dataProvider')}&quot;;&#xD;
self.PRIVATE_ATTRIBUTES.dataProvider.setter = function(dataProvider) {&#xD;
	self.dataProvider=dataProvider;&#xD;
};&#xD;
self.parameter=&quot;${r"${acomp"}.prop('parameter')}&quot;;&#xD;
self.PRIVATE_ATTRIBUTES.parameter.setter = function(parameter) {&#xD;
	self.parameter=parameter;&#xD;
	var queryForm=view.id(&quot;${r"${acomp"}.id('autoFormQuery${declarationName}')}&quot;);&#xD;
	queryForm.set(&quot;entity&quot;,parameter);&#xD;
	queryForm.refreshData();&#xD;
};&#xD;
self.flush=function(){&#xD;
	var dataset=view.id(&quot;${r"${acomp"}.id('dataSet${declarationName}')}&quot;);&#xD;
	if(self.dataProvider&amp;&amp;&quot;&quot;!=self.dataProvider){&#xD;
		dataset.set(&quot;dataProvider&quot;,self.dataProvider);&#xD;
	}&#xD;
	var queryForm=view.id(&quot;${r"${acomp"}.id('autoFormQuery${declarationName}')}&quot;);&#xD;
	var parameter=queryForm.get(&quot;entity&quot;)||{};&#xD;
	parameter.matchonly=true;&#xD;
	dataset.set(&quot;parameter&quot;,parameter);&#xD;
	dataset.flushAsync();&#xD;
	self.isload=true;&#xD;
}</ClientEvent>
      <ClientEvent name="onExecute">self.flush();</ClientEvent>
      <Panel>
        <Property name="width">98%</Property>
        <Property name="height">98%</Property>
        <Buttons>
          <Button id="${r"${acomp"}.id('buttonClearSelect${declarationName}')}">
            <ClientEvent name="onClick">var dropDown = dorado.widget.DropDown.findDropDown(self);&#xD;
dropDown.close({});</ClientEvent>
            <Property name="caption">${r"${res"}[&quot;core.common/ClearSelect&quot;]}</Property>
          </Button>
        </Buttons>
        <Children>
          <DataSet id="${r"${acomp"}.id('dataSet${declarationName}')}">
            <Property name="dataType">[$dataType${declarationName}Select]</Property>
            <Property name="dataProvider">${pojoview}#load${declarationName}s</Property>
            <Property name="loadMode">manual</Property>
          </DataSet>
          <AutoForm id="${r"${acomp"}.id('autoFormQuery${declarationName}')}">
            <Property name="dataSet">${r"${acomp"}.id('dataSetQuery${declarationName}')}</Property>
            <Property name="cols">*,*,80</Property>
            <Property name="labelWidth">50</Property>
            <AutoFormElement>
              <Property name="name">name</Property>
              <Property name="property">name</Property>
              <Editor/>
            </AutoFormElement>
            <AutoFormElement>
              <Property name="property">code</Property>
              <Property name="name">code</Property>
              <Editor/>
            </AutoFormElement>
            <Button>
              <ClientEvent name="onClick">var dropdown=view.id(&quot;${r"${acomp"}.id('DropDown${declarationName}SingleSelect')}&quot;);&#xD;
dropdown.flush();&#xD;
</ClientEvent>
              <Property name="caption">${r"${res"}[&quot;core.common/Query&quot;]}</Property>
            </Button>
          </AutoForm>
          <DataTreeGrid id="${r"${acomp"}.id('dataTree${declarationName}')}">
            <ClientEvent name="onDoubleClick">var currentEntity=view.id(&quot;${r"${acomp"}.id('dataTree${declarationName}')}&quot;).get(&quot;currentNode.data&quot;);&#xD;
var dropDown = dorado.widget.DropDown.findDropDown(self);&#xD;
dropDown.close(currentEntity);</ClientEvent>
            <Property name="treeColumn">dataType</Property>
            <Property name="dataSet">${r"${acomp"}.id('dataSet${declarationName}')}</Property>
            <Property name="selectionMode">singleRow</Property>
            <Property name="readOnly">true</Property>
            <BindingConfigs>
              <BindingConfig>
                <Property name="recursive">true</Property>
                <Property name="expandLevel">1</Property>
                <Property name="labelProperty">name</Property>
                <Property name="icon"> url(>skin>common/icons.gif) -260px -40px</Property>
                <Property name="childrenProperty">rel${declarationName}s</Property>
                <Property name="checkable">false</Property>
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
                <Property name="name">name</Property>
                <Property name="property">name</Property>
                <Editor/>
              </DataColumn>
              <DataColumn>
                <Property name="name">code</Property>
                <Property name="property">code</Property>
                <Editor/>
              </DataColumn>
            </Columns>
          </DataTreeGrid>
        </Children>
        <Tools/>
      </Panel>
    </CustomDropDown>
  </View>
</ViewConfig>
