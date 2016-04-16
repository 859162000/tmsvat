<#assign maindeclarationName = mainpojo.importType(mainpojo.getDeclarationName())>
<#assign pojoview=mainpojo.getDeclarationNameFirstLetterLower()+"View">
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign pojoclassname = "${pojo.getPackageName()}.${pojo.getClassName()}">
<#assign realPackageAndClassPath = "${pojo.getRealPackageAndClassPath()}">
<#assign masterid = mainpojo.getDeclarationNameFirstLetterLower()+"Id">
<#assign childpropertyname = "${pojo.getDeclarationNameFirstLetterLower()}s"> 
<#assign fields=pojo.getPojoFields()>
<#assign mainpojodeclarationName = mainpojo.importType(mainpojo.getDeclarationName())>
<#assign mainpojorealPackageAndClassPath = "${mainpojo.getRealPackageAndClassPath()}">
window.add${declarationName} = function() {
	var data = view.id("dataSet${declarationName}").getData();
	data.insert({
		${masterid}:'${r"${param["}"${masterid}"]}'
	});
	view.id("dialog${declarationName}").show();
}
window.del${declarationName} = function() {
	var data = view.id("dataSet${declarationName}").getData("#");
	var action = view.id("updateActionSave${declarationName}");
	if (data) {		
		dorado.MessageBox.confirm("您真的要删除当前子表信息吗？", function() {
			data.remove();
			action.execute();
		});
	} else {
		dorado.MessageBox.alert("当前没有子表信息可供删除！");
	}
}
window.edit${declarationName} = function() {
	var data = view.id("dataSet${declarationName}").getData("#");
	if (data) {
		view.id("dialog${declarationName}").show();
	} else {
		dorado.MessageBox.alert("当前没有子表信息可供修改！");
	}
}
window.query${declarationName} = function() {
	//var ds = view.id("dataSet${declarationName}");
	//var form = view.id("formCondition${declarationName}");
	//ds.set("parameter", form.get("entity")).flushAsync();
	view.id("dataSet${declarationName}").flushAsync();
}
window.save${declarationName} = function() {
	view.id("updateActionSave${declarationName}").execute(function() {
		view.id("dialog${declarationName}").hide();
	});
}
window.cancel${declarationName} = function() {
	view.id("dataSet${declarationName}").getData("#").cancel();
	view.id("dialog${declarationName}").hide();
}
// @Bind #searchBtn${childpropertyname}.onClick
!function(self, arg) {
	query${declarationName}();
}

// @Bind #addBtn${childpropertyname}.onClick
!function(self, arg) {
	add${declarationName}();
}

// @Bind #delBtn${childpropertyname}.onClick
!function(self, arg) {
	del${declarationName}();
}

// @Bind #modifyBtn${childpropertyname}.onClick
!function(self, arg) {
	edit${declarationName}();
}

// @Bind #dialog${declarationName}.onHide
!function(self, arg) {
	var current=view.id("dataSet${declarationName}").getData("#");
	if(current){
		current.cancel();
	}
}
// @Bind #dialog${declarationName}.onClose
!function(self, arg) {
	var current=view.id("dataSet${declarationName}").getData("#");
	if(current){
		current.cancel();
	}
}
// @Bind #saveSingleBtn${declarationName}.onClick
!function(self, arg) {
	save${declarationName}();
}

// @Bind #cancelBtn${declarationName}.onClick
!function(self, arg) {
	cancel${declarationName}();
}
