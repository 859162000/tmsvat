<#assign pojo=oneToManyPojo.getOne()>
<#assign many=oneToManyPojo.getMany()>
<#assign ones=oneToManyPojo.getOnes()>
<#assign pojoview=pojo.getDeclarationNameFirstLetterLower()+"View">
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign declarationName_firstLower = pojo.getDeclarationNameFirstLetterLower()>
<#assign pojoclassname = "${pojo.getPackageName()}.model.${declarationName}">
<#assign realPackageAndClassPath = "${pojo.getRealPackageAndClassPath()}">
<#assign masterid = pojo.getDeclarationNameFirstLetterLower()+"Id">

window.flush${declarationName}=function(){
	var dataset = view.id("dataSet${declarationName}");
	dataset.set("parameter",view.id("dataSetQuery${declarationName}").getData().toJSON());
	dataset.flushAsync();
}
// @Bind view.onCreate
!function(self, arg) {

}

// @Bind #dataSetQuery${declarationName}.onReady
!function(self, arg) {
	self.insert({
		flag : true
	});
	window.flush${declarationName}();
}

// @Bind #buttonQuery${declarationName}.onClick
!function(self, arg) {
	window.flush${declarationName}();
}

// @Bind #buttonAdd${declarationName}.onClick
!function(self, arg) {
	var dataSet${declarationName}Current = view.id("dataSet${declarationName}Current");
	dataSet${declarationName}Current.clear();
	var entity = dataSet${declarationName}Current.insert({virtual:true});
	view.id("dialog${declarationName}Detail").show();

}
// @Bind @dataTypeQuery${declarationName}.onDataChange
!function(self, arg) {
	window.flush${declarationName}();
}

// @Bind #buttonPre${declarationName}.onClick
!function(self, arg) {
	var treeGrid = view.id("dataTree${declarationName}");
	var currentEntity = treeGrid.get("currentNode.data");
	var currentEntityid = currentEntity.get("id");
	var selections = treeGrid.getCheckedNodes();
	var highlightindex=0;
	if (selections.length > 0) {
		for ( var i = 0; i < selections.length; i++) {
			var node = selections[i];
			var id = node.get("data").get("id");
			if (id == currentEntityid) {
				if (i > 0) {
					highlightindex=i-1;
				}else{
					highlightindex=selections.length-1;
				}				
			}
		}
		treeGrid.set("currentNode", selections[highlightindex]);
	}	
}

// @Bind #buttonNext${declarationName}.onClick
!function(self, arg) {
	var treeGrid = view.id("dataTree${declarationName}");
	var currentEntity = treeGrid.get("currentNode.data");
	var currentEntityid = currentEntity.get("id");
	var selections = treeGrid.getCheckedNodes();
	var highlightindex=0;
	if (selections.length > 0) {
		for (var i = 0; i < selections.length; i++) {
			var node = selections[i];
			var id = node.get("data").get("id");
			if (id == currentEntityid) {
				if (i < selections.length - 1) {
					highlightindex=i+1;
				}
			}
		}
		treeGrid.set("currentNode", selections[highlightindex]);
	}
}

// @Bind #button${declarationName}Save.onClick
!function(self, arg) {
//	var data=view.id("dataSet${declarationName}Current").getData("#");
//	if(!data.get("virtual")){//非实体单位
//		var coordinator=data.get("coordinator");
//		if(coordinator==null){
//			dorado.MessageBox.alert('${r"${"}res["iris.${declarationName}/coordinatorcannotbenull"]}');
//			return;
//		}
//	}
	view.id("updateActionSave${declarationName}s").execute(function() {
		view.id("dataSet${declarationName}Current").clear();
		view.id("dialog${declarationName}Detail").hide();
		window.flush${declarationName}();
	});

}

// @Bind #button${declarationName}Cancle.onClick
!function(self, arg) {
	view.id("dataSet${declarationName}Current").clear();
	view.id("dialog${declarationName}Detail").hide();
}

//@Bind #buttonClearSelect${declarationName}.onClick
!function(self, arg) {
	var dropDown = dorado.widget.DropDown.findDropDown(self);
	dropDown.close({id:null,name:null});
}
// @Bind #dataTree${declarationName}.onDoubleClick
!function(self, arg) {
	var currentEntity = view.id("dataTree${declarationName}").get("currentNode.data");
	var ${declarationName_firstLower}s=currentEntity.get("rel${declarationName}s");
	view.id("dataSet${declarationName}Current").insert(currentEntity.toJSON());
	view.id("dialog${declarationName}Detail").show();
}

// @Bind #dataTree${declarationName}.onRenderNode
!function(self, arg) {
	var node = arg.node, data = node.get("data"), xCreateConfig = [];
	xCreateConfig.push({
		tagName : "SPAN",
		contentText : node.get("label")
	});
	if (data.get("hot")) {
		xCreateConfig.push({
			tagName : "IMG",
			src : $url(">static/images/hot.gif"),
			style : "position: relative; left: 4px; top: 4px"
		});
	}
	;
	if (xCreateConfig.length) {
		$(arg.dom).empty().xCreate(xCreateConfig);
	} else {
		arg.processDefault = true;
	}

}

// @Bind #dialog${declarationName}Detail.onShow
!function(self, arg) {
//	var autoFormElementUserText = view.id("autoFormElementUserText");
//	var virtual = view.id("dataTree${declarationName}").get("currentNode.data.virtual");
//	if (virtual) {
//		autoFormElementUserText.set("readOnly", true);
//	} else {
//		autoFormElementUserText.set("readOnly", false);
//	}

}

// @Bind #customDropDownSelectParent${declarationName}.onExecute
!function(self, arg) {
	self.flush();

}

// @Bind #irisUserMultiSelectForRelUsers.beforeExecute
!function(self, arg) {
	var selectentitys = new Array();
	var users = view.id("dataSet${declarationName}Current").getData().get("relUsers");
	if (users) {
		users.each(function(item) {
			selectentitys.push(item.toJSON());
		});
	}
	self.selectentitys = selectentitys;

}

// @Bind #irisUserMultiSelectForRelUsers.onValueSelect
!function(self, arg) {
	var current${declarationName} = view.id("dataSet${declarationName}Current").getData();
	current${declarationName}.set("relUsers", arg.selectedValue);
}

// @Bind #checkBoxVirtual.onValueChange
!function(self, arg) {
//	var virtual=self.get("value");
//	var autoFormElementUserText=view.id("autoFormElementUserText");
//	if(virtual){
//		var currentdata = view.id("dataSet${declarationName}Current").getData();
//		//if (currentdata && currentdata.get("virtual") == true && currentdata.get("relUsers").toArray().length > 0) {
//		//    dorado.MessageBox.confirm("将清空实体单位的单位成员,确定么?", {
//		//		detailCallback: function(button, text) {
//		//			alert(button);
//		//			if(button == 'yes'){
//						currentdata.set("relUsers",null);
//			        	autoFormElementUserText.set("readOnly",true);
//		//			}else if(button == 'no'){
//		//				self.set("value",false);
//		//				self.set("checked",false);
//		//			}
//		//	}
//		//	});
//		//}
//	}else{
//		autoFormElementUserText.set("readOnly",false);
//	}
}
// @Bind #${declarationName_firstLower}SelectParent.beforeExecute
!function(self, arg) {
	var currentId = view.id("dataSet${declarationName}Current").getData().get("code");
	self.set("parameter", {
		currentId : currentId
	});
}
