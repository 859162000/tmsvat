<#assign pojo=oneToManyPojo.getOne()>
<#assign many=oneToManyPojo.getMany()>
<#assign ones=oneToManyPojo.getOnes()>
<#assign pojoview=pojo.getDeclarationNameFirstLetterLower()+"View">
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign pojoclassname = "${pojo.getPackageName()}.model.${declarationName}">
<#assign masterid = pojo.getDeclarationNameFirstLetterLower()+"Id">
<#assign fields=pojo.getPojoFields()>
<?xml version="1.0" encoding="UTF-8"?>
<ViewConfig>
  <Property name="metaData">
    <Property name="securityTag">${declarationName}.select</Property>
  </Property>
  <Model/>
  <View>
    <ClientEvent name="onCreate">this.selectattachment_button = function(){&#xD;
    var parentview = parent.$id('viewMain').objects[0];&#xD;
    var grid = this.id(&quot;$dataGrid${declarationName}&quot;);&#xD;
    var datas = grid.get(&quot;selection&quot;);&#xD;
    parentview.onselectattachment(datas);    &#xD;
}&#xD;
this.selectattachment_click = function(){&#xD;
    var parentview = parent.$id('viewMain').objects[0];&#xD;
    var datas =  new Array();&#xD;
	var data = this.id(&quot;$dataSet${declarationName}&quot;).getData(&quot;#&quot;);&#xD;
	datas.push(data);&#xD;
    parentview.onselectattachment(datas);    &#xD;
}</ClientEvent>
    <Dialog id="$dialog${declarationName}">
      <ClientEvent name="onCreate">self.$show=function(config){&#xD;
	if(!config.onSelect){&#xD;
		dorado.MessageBox.alert(&quot;请先为当前dialog添加一个名为onSelect的函数&quot;);&#xD;
		return;&#xD;
	}&#xD;
	self.onSelect=config.onSelect;&#xD;
	if(config.width){&#xD;
		self.set(&quot;width&quot;,config.width);&#xD;
	}&#xD;
	&#xD;
	if(config.maxSelect){&#xD;
		self.maxSelect=config.maxSelect;&#xD;
	}&#xD;
	&#xD;
	if(config.height){&#xD;
		self.set(&quot;height&quot;,config.height);&#xD;
	}&#xD;
	if(config.caption){&#xD;
		self.set(&quot;caption&quot;,config.caption);&#xD;
	}&#xD;
	if(config.selected){&#xD;
		self.selected=config.selected;&#xD;
	}&#xD;
	if(config.cache==undefined){&#xD;
		self.cache=true;&#xD;
	}else{&#xD;
		self.cache=config.cache;&#xD;
	}&#xD;
	if(config.selectionMode){&#xD;
		view.id(&quot;$dataGrid${declarationName}&quot;).set(&quot;selectionMode&quot;,config.selectionMode);&#xD;
	}&#xD;
	self.show();&#xD;
}</ClientEvent>
      <Property name="center">true</Property>
      <Property name="modal">true</Property>
      <Property name="width">630</Property>
      <Property name="height">460</Property>
      <Buttons/>
      <Children>
        <DataSet id="$dataSet${declarationName}">
          <Property name="dataType">[dataType${declarationName}]</Property>
          <Property name="dataProvider">${pojoview}#load${declarationName}</Property>
          <Property name="pageSize">10</Property>
        </DataSet>
        <ToolBar>
          <ToolBarButton>
            <ClientEvent name="onClick">var grid = this.id(&quot;$dataGrid${declarationName}&quot;);&#xD;
var datas = grid.get(&quot;selection&quot;);&#xD;
if(!datas||datas.length==0){&#xD;
	dorado.MessageBox.alert(&quot;请选择一个${pojo.getComment()}后再进行此操作&quot;);&#xD;
	return;&#xD;
}&#xD;
this.id(&quot;$dialog${declarationName}&quot;).onSelect(datas);</ClientEvent>
            <Property name="caption">选择</Property>
          </ToolBarButton>
        </ToolBar>
            <DataGrid id="$dataGrid${declarationName}">
              <ClientEvent name="onDoubleClick">var datas =  new Array();&#xD;
var data = this.id(&quot;$dataSet${declarationName}&quot;).getData(&quot;#&quot;);&#xD;
datas.push(data);&#xD;
if(datas.length==0){&#xD;
	dorado.MessageBox.alert(&quot;请选择一个商品后再进行此操作&quot;);&#xD;
	return;&#xD;
}&#xD;
view.id(&quot;$dialog${declarationName}&quot;).onSelect(datas);</ClientEvent>
              <Property name="dataSet">$dataSet${declarationName}</Property>
              <Property name="readOnly">true</Property>
              <Property name="showFilterBar">false</Property>
              <Property name="filterMode">serverSide</Property>
		      <Property name="selectionMode">multiRows</Property>
		      <Property name="highlightSelectedRow">false</Property>
		      <RowSelectorColumn/>
<#foreach field in fields>
<#if field.getIsSampleField()&&field.getIsNotBaseEntiry()>
              <DataColumn>
                <Property name="name">${field.getName()}</Property>
                <Property name="property">${field.getName()}</Property>
              </DataColumn>
</#if>              
</#foreach>
            </DataGrid>
      </Children>
      <Tools/>
    </Dialog>
  </View>
</ViewConfig>