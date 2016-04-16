<#assign fields=pojo.getPojoFields()>
<#foreach field in fields>
<#if field.getDropdown()?exists>
<#assign dropdown=field.getDropdown()>
<#if dropdown.dropDownType().toString()=="datasetDropDown">
this.onSet${dropdown.relClass()}DropDown=function(self,arg){
	self.disableListeners();
	var value=arg.value;
	var datas = this.id("dataSet${dropdown.relClass()}").getData();
	var selectdata;
	datas.each(function(item){
	    var ${dropdown.disProperty()}= item.get("${dropdown.disProperty()}");
		if(value==${dropdown.disProperty()})
		{
			selectdata=item;
		}
	});
	var entity=arg.entity;
	if(selectdata)
	{
		//entity.set("commodityId",selectdata.get("id"));
	}else{
		//entity.set("commodityId",null);
	}
	self.enableListeners();
}
this.on${dropdown.relClass()}DropDownValidate=function(self,arg){
	var entity=arg.entity;
//	if(entity.get("commodityId")==null)
//	{
//		throw new dorado.Exception("请选择正确的商品");
//	}
}
<#elseif dropdown.dropDownType().toString()=="dialog">
this.dialogSelect${dropdown.relClass()}=function()
{
	var dialog=this.id("$dialog${dropdown.relClass()}");	
	var entity=this.id("dataSet${declarationName}").getData("#");
	dialog.$show({
		caption:"选择商品",
		width:700,
		selectionMode:"singleRow",
		view:this,
		onSelect:function(datas){
			var data=datas[0];
			if(data==null)
			{
				data=datas;
			}
			//entity.set("commodityId",data.get("id"));
			//entity.set("commodityName",data.get("name"));
			dialog.hide();
		}
	});
}
</#if>
</#if>
</#foreach>