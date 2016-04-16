<#assign pojo=oneToManyPojo.getOne()>
<#assign many=oneToManyPojo.getMany()>
<#assign ones=oneToManyPojo.getOnes()>
<#assign pojoview=pojo.getDeclarationNameFirstLetterLower()+"View">
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign declarationName_lower = pojo.getDeclarationNameFirstLetterLower()>
<#assign pojoclassname = "${pojo.getPackageName()}.model.${declarationName}">
<#assign masterid = pojo.getDeclarationNameFirstLetterLower()+"Id">
<#assign urlroot = "${pojo.getPackageName()}">
window.add${declarationName} = function() {
	view.id("iFrame${declarationName}Detail").set("path","${urlroot}.view.${declarationName_lower}_add.ling?date="+new Date());
	view.id("dialog${declarationName}").show();
}
window.del${declarationName} = function() {
	var masterData = view.id("dataSet${declarationName}").getData();
	var updateActionSave${declarationName} = view.id("updateActionSave${declarationName}");
	if (masterData.current) {
		dorado.MessageBox.confirm("您真的想删除当前主表信息吗？", function() {
				masterData.remove();
				updateActionSave${declarationName}.execute();
		});
	}
}
window.edit${declarationName} = function() {
	var data = view.id("dataSet${declarationName}").getData();
	if (data.current) {
		view.id("iFrame${declarationName}Detail").set("path","${urlroot}.view.${declarationName_lower}_modify.ling?date="+new Date()+"&id="+data.current.get("id"));
		view.id("dialog${declarationName}").show();
	} else {
		dorado.MessageBox.alert("当前主表没有信息可供编辑！");
	}
}
window.query${declarationName} = function() {
	var ds = view.id("dataSet${declarationName}");
	var form = view.id("formCondition${declarationName}");
	ds.set("parameter", form.get("entity")).flushAsync();
}
window.help${declarationName} = function() {
	view.id("intro${declarationName}").start();
}
window.inithelp${declarationName} = function() {
	var intro = view.get('#intro${declarationName}');
	var items = [], steps = intro.get('steps');
	for (var i = 0, len = steps.length; i < len; i++) {
		var step = steps[i];
		step.name && items.push(step.name);
	};
	var dr = view.id("introDropDown${declarationName}");
	dr.set('items', items);
}
window.selecthelp${declarationName} = function(selectedValue) {
	var intro = view.get('#intro${declarationName}');
	selectedValue && intro.goTo(selectedValue);
}
// @Bind view.onReady
!function(self, arg) {
	setTimeout(function() {
		// view.help${declarationName}();
	}, 1000);

}

// @Bind view.onCreate
!function(self, arg) {

}

// @Bind #searchBtn${declarationName}.onClick
!function(self, arg) {
	query${declarationName}();
}

// @Bind #addBtn${declarationName}.onClick
!function(self, arg) {
	add${declarationName}();
}

// @Bind #modifyBtn${declarationName}.onClick
!function(self, arg) {
	edit${declarationName}();
}

// @Bind #delBtn${declarationName}.onClick
!function(self, arg) {
	del${declarationName}();
}

// @Bind #dataGrid${declarationName}.onDoubleClick
!function(self, arg) {
	edit${declarationName}();
}
//@Bind #dialog${declarationName}.onClose
!function(self, arg) {
	query${declarationName}();
}

// @Bind #dialog${declarationName}.onHide
!function(self, arg) {
	query${declarationName}();
}