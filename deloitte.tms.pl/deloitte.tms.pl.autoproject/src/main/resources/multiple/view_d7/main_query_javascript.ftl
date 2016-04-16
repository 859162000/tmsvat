// @Bind view.onReady
!function(self, arg) {
	setTimeout(function() {
		// view.help${declarationName}();
	}, 1000);

}

// @Bind view.onCreate
!function(self, arg) {

	this.add${declarationName} = function() {
		this.id("iFrame${declarationName}Detail").set("path","view.${declarationName_lower}_add.ling");
		this.id("dialog${declarationName}").show();
	}
	this.del${declarationName} = function() {
		var masterData = this.id("dataSet${declarationName}").getData();
		var updateActionSave${declarationName} = this.id("updateActionSave${declarationName}");
		if (masterData.current) {
			var addresss = this.id("dataSet${declarationName}").getData("#.reladdresss");
			var phones = this.id("dataSet${declarationName}").getData("#.relphones");
			if (addresss.current || phones.current) {
				dorado.MessageBox.show({
					title : "操作错误",
					icon : dorado.MessageBox.ERROR_ICON,
					message : "当前选中节点含有子节点项，请先删除子节点！",
					buttons : [ "ok" ]
				})
			} else {
				dorado.MessageBox.confirm("您真的想删除当前主表信息吗？", function() {
					masterData.remove();
					updateActionSave${declarationName}.execute();
				});
			}
		}
	}
	this.edit${declarationName} = function() {
		var data = this.id("dataSet${declarationName}").getData();
		if (data.current) {
			this.id("iFrame${declarationName}Detail").set("path","view.${declarationName_lower}_modify.ling?id="+data.current.get("id"));
			this.id("dialog${declarationName}").show();
		} else {
			dorado.MessageBox.alert("当前主表没有信息可供编辑！");
		}
	}
	this.query${declarationName} = function() {
		var ds = this.id("dataSet${declarationName}");
		var form = this.id("formCondition${declarationName}");
		ds.set("parameter", form.get("entity")).flushAsync();
	}
	this.help${declarationName} = function() {
		this.id("intro${declarationName}").start();
	}
	this.inithelp${declarationName} = function() {
		var intro = this.get('#intro${declarationName}');
		var items = [], steps = intro.get('steps');
		for (var i = 0, len = steps.length; i < len; i++) {
			var step = steps[i];
			step.name && items.push(step.name);
		}
		;
		var dr = this.id("introDropDown${declarationName}");
		dr.set('items', items);
	}
	this.selecthelp${declarationName} = function(selectedValue) {
		var intro = view.get('#intro${declarationName}');
		selectedValue && intro.goTo(selectedValue);
	}

}

// @Bind #searchBtn${declarationName}.onClick
!function(self, arg) {
	this.query${declarationName}();
}

// @Bind #addBtn${declarationName}.onClick
!function(self, arg) {
	this.add${declarationName}();
}

// @Bind #modifyBtn${declarationName}.onClick
!function(self, arg) {
	this.edit${declarationName}();
}

// @Bind #delBtn${declarationName}.onClick
!function(self, arg) {
	this.del${declarationName}();
}

// @Bind #dataGrid${declarationName}.onDoubleClick
!function(self, arg) {
	this.edit${declarationName}();
}