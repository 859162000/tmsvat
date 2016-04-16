<#assign pojo=oneToManyPojo.getOne()>
<#assign many=oneToManyPojo.getMany()>
<#assign ones=oneToManyPojo.getOnes()>
<#assign pojoview=pojo.getDeclarationNameFirstLetterLower()+"View">
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign pojoclassname = "${pojo.getPackageName()}.model.${declarationName}">
<#assign masterid = pojo.getDeclarationNameFirstLetterLower()+"Id">
// @Bind view.onReady
!function(self, arg) {

}

// @Bind view.onCreate
!function(self, arg) {
	this.reset${declarationName}Data = function() {
		var dataSet${declarationName} = this.id("dataSet${declarationName}");
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
	this.save${declarationName}Data = function() {
		var action = this.id("updateActionSaveAdd${declarationName}");
		action.execute(function() {

		});
	}

}

//@Bind #saveBtn${declarationName}.onClick
!function(self, arg) {
	this.save${declarationName}Data();
}

// @Bind #resetBtn${declarationName}.onClick
!function(self, arg) {
	this.reset${declarationName}Data();
}

// @Bind #selectorg.onValueSelect
!function(self, arg) {
	var datas = arg.selectedValue;
	var value = datas[0];
	var entity = this.id("dataSet${declarationName}").getData("#");
	entity.set("parentId", value.code);
	entity.set("parentName", value.name);
	arg.processDefault = false;
}

<#foreach child in many>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>
<#assign childpojoclassname = "${child.getPackageName()}.model.${childdeclarationName}">  
<#assign childpropertyname = "${child.getDeclarationNameFirstLetterLower()}s">          

// @Bind #addBtn${childpropertyname}.onClick
!function(self, arg) {

	var data = this.id("dataSet${declarationName}").getData("#.rel${childpropertyname}")
	if (data) {
		// dorado.MessageBox.alert("来自从表toolBarButton添加按键的onClick事件, 补充主表元素");
		data.insert({
			${declarationName}Id : this.id("dataSet${declarationName}").getData("#.id")
		});
		this.id("dialog${childdeclarationName}").show();
	} else {
		dorado.MessageBox.alert("当前没有选中的主表信息，不能添加子表信息！");
	}

}

// @Bind #delBtn${childpropertyname}.onClick
!function(self, arg) {

	var data = this.id("dataSet${declarationName}").getData("#.rel${childpropertyname}");
	if (data.current) {
		var action = this.id("updateActionSave${childdeclarationName}");
		dorado.MessageBox.confirm("您真的要删除当前子表信息吗？", function() {
			data.remove();
			action.execute();
		});
	} else {
		dorado.MessageBox.alert("当前没有子表信息可供删除！");
	}

}

// @Bind #modifyBtn${childpropertyname}.onClick
!function(self, arg) {

	var data = this.id("dataSet${declarationName}").getData("#.rel${childpropertyname}");
	if (data.current) {
		this.id("dialog${childdeclarationName}").show();
	} else {
		dorado.MessageBox.alert("当前没有子表信息可供修改！");
	}

}

// @Bind #dialog${childdeclarationName}.onHide
!function(self, arg) {
	this.id("dataSet${declarationName}").getData("#.rel${childpropertyname}").cancel();

}

// @Bind #saveSingleBtn${childdeclarationName}.onClick
!function(self, arg) {
	var view = this;
	view.id("updateActionSave${childdeclarationName}").execute(function() {
		view.id("dialog${childdeclarationName}").hide();
	});

}

// @Bind #cancelBtn${childdeclarationName}.onClick
!function(self, arg) {
	this.id("dialog${childdeclarationName}").hide();

}
</#foreach>
<#foreach child in ones>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>
// @Bind #saveBtn${childdeclarationName}.onClick
!function(self, arg) {
	var view = this;
	view.id("updateActionSave${childdeclarationName}").execute(function() {
		view.id("dialog${childdeclarationName}").hide();
	});

}
</#foreach>