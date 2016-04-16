<#assign pojo=oneToManyPojo.getOne()>
<#assign many=oneToManyPojo.getMany()>
<#assign ones=oneToManyPojo.getOnes()>
<#assign pojoview=pojo.getDeclarationNameFirstLetterLower()+"View">
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign declarationName_lower = pojo.getDeclarationNameFirstLetterLower()>
<#assign pojoclassname = "${pojo.getPackageName()}.model.${declarationName}">
<#assign masterid = pojo.getDeclarationNameFirstLetterLower()+"Id">
window.reset${declarationName}Data = function() {
	var dataSet${declarationName} = view.id("dataSet${declarationName}");
	dorado.MessageBox.show({
			title : "提示",
			icon : dorado.MessageBox.INFO_ICON,
			message : "确定要重置么?当前的修改将被取消!",
			buttons : [ "yes", "no" ],
			detailCallback : function(buttonId) {
				if (buttonId == "yes") {
					dataSet${declarationName}.flush();
				}
			}
		})
}
window.save${declarationName}Data = function() {
	var action = view.id("updateActionSaveAdd${declarationName}");
	action.execute(function() {
	});
}
// @Bind view.onReady
!function(self, arg) {

}

// @Bind view.onCreate
!function(self, arg) {

}

// @Bind #tabControl${declarationName}.beforeTabChange
!function(self, arg) {
	var newtabname = arg.newTab.get("name");
	if ("main" != newtabname) {
		var id = view.id("dataSet${declarationName}").getData("#.id");
		if (id == null) {
			throw new dorado.Exception("请先保存主要信息");
		}
	}

}

//@Bind #saveBtn${declarationName}.onClick
!function(self, arg) {
	save${declarationName}Data();
}

// @Bind #resetBtn${declarationName}.onClick
!function(self, arg) {
	reset${declarationName}Data();
}

// @Bind #selectorg.onValueSelect
!function(self, arg) {
	var datas = arg.selectedValue;
	var value = datas[0];
	var entity = view.id("dataSet${declarationName}").getData("#");
	entity.set("parentId", value.code);
	entity.set("parentName", value.name);
	arg.processDefault = false;
}

// @Bind #updateActionSaveAdd${declarationName}.onSuccess
!function(self, arg) {
	var data = view.id("dataSet${declarationName}").getData("#");
	if (data) {
<#foreach child in many>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>
<#assign childdeclarationName_lower = child.getDeclarationNameFirstLetterLower()>
		view.id("iFrame${childdeclarationName}").set("path","${pojo.getPackageName()}.view.${declarationName_lower}_${childdeclarationName_lower}_relation.ling?${masterid}="+data.get("id"));
</#foreach>
	}
}