// @Bind view.onReady
!function(self, arg) {
	setTimeout(function() {
		// view.help${declarationName}();
	}, 1000);

}

// @Bind view.onCreate
!function(self, arg) {

	window.add${declarationName} = function() {
		var masterData = view.id("dataSet${declarationName}").getData();
		masterData.insert();
		view.id("dialog${declarationName}").show();
	}
	window.del${declarationName} = function() {
		var masterData = view.id("dataSet${declarationName}").getData();
		var updateActionSave${declarationName} = view.id("updateActionSave${declarationName}");
		if (masterData.current) {
			var addresss = view.id("dataSet${declarationName}").getData("#.addresss");
			var phones = view.id("dataSet${declarationName}").getData("#.phones");
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
	window.edit${declarationName} = function() {
		var data = view.id("dataSet${declarationName}").getData();
		if (data.current) {
			view.id("dialog${declarationName}").show();
		} else {
			dorado.MessageBox.alert("当前主表没有信息可供编辑！");
		}
	}
	window.query${declarationName} = function() {
		var ds = view.id("dataSet${declarationName}");
		var parameter = view.id("dataSetQuery${declarationName}").getData().toJSON();
		ds.set("parameter", parameter).flushAsync();
	}
	window.save${declarationName} = function() {
		var action = view.id("updateActionSave${declarationName}");
		action.execute(function() {
		});
	}
	window.saveSingle${declarationName} = function() {
		var action = view.id("updateActionSave${declarationName}");
		action.execute(function() {
			view.id("dialog${declarationName}").hide();
		});
	}
	window.cancelSingle${declarationName} = function() {
		view.id("dataSet${declarationName}").getData().cancel();
		view.id("dialog${declarationName}").hide();
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
		}
		;
		var dr = view.id("introDropDown${declarationName}");
		dr.set('items', items);
	}
	window.selecthelp${declarationName} = function(selectedValue) {
		var intro = view.get('#intro${declarationName}');
		selectedValue && intro.goTo(selectedValue);
	}

}
// @Bind #dataSetQuery${declarationName}.onReady
!function(self, arg) {
	self.insert({});
}
// @Bind #searchBtn${declarationName}.onClick
!function(self, arg) {
	window.query${declarationName}();
}

// @Bind #addBtn${declarationName}.onClick
!function(self, arg) {
	window.add${declarationName}();
}

// @Bind #modifyBtn${declarationName}.onClick
!function(self, arg) {
	window.edit${declarationName}();
}

// @Bind #delBtn${declarationName}.onClick
!function(self, arg) {
	window.del${declarationName}();
}

// @Bind #saveBtn${declarationName}.onClick
!function(self, arg) {
	window.save${declarationName}();

}

// @Bind #dataGrid${declarationName}.onDoubleClick
!function(self, arg) {
	window.edit${declarationName}();
}

// @Bind #dialog${declarationName}.onClose
!function(self, arg) {
	window.cancelSingle${declarationName}();
}

// @Bind #saveSingleBtn${declarationName}.onClick
!function(self, arg) {
	window.saveSingle${declarationName}();

}

// @Bind #cancelBtn${declarationName}.onClick
!function(self, arg) {
	window.cancelSingle${declarationName}();

}
