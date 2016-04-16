(function() {
	dorado.widget.ImportExcelAction = $extend(dorado.widget.Action, {
		$className : "dorado.widget.ImportExcelAction",
		ATTRIBUTES : {
			async : {
				defaultValue : true
			},
			timeout : {},
			batchable : {
				defaultValue : true
			},
			excelModelId : {},
			startRow : {
				defaultValue : 0
			},
			endRow : {
				defaultVaule : 0
			},
			showImportData : {
				defaultValue : true
			},
			width : {
				defaultVaule : "98%"
			},
			height : {
				defaultVaule : "98%"
			},
			parameter : {},
		},
		doExecute:function(callback){
			this.doExecuteAsync(callback);
		},
		execute:function(callback){
			this.doExecuteAsync(callback);
		},
		doExecuteAsync : function(callback) {
			var view = this._view, action = this, showImportData = this._showImportData;
			if (!action._excelModelId) {
				throw new dorado.Exception("excelModelId is null");
			}
			action.set("disabled", false);
			function doExecuteViewData() {
				uploadExcelDialog.close();
				jQuery(buttonUploadExcel.getDom()).remove();
				jQuery(buttonDwonLoadTemplate.getDom()).remove();
				var buttonParseExcel = new dorado.widget.Button({
					caption : "解析入库",
					icon : "url(>skin>common/icons.gif) -120px -280px",
					listener : {
						onClick : function(self, arg) {
							parseAction.execute(function(result) {
								self.set("disabled", true);
								showCacheDialog.close();
								$callback(callback, true, result);
							});
						}
					}
				});
				var showCacheDialog = new dorado.widget.Dialog({
					width : "98%",
					height : "98%",
					center : true,
					closeAction : "hide",
					status : "hidden",
					maximizeable : true,
					modal : true,
					caption : "导入Excel数据预览",
					view : view,
					animateType:"none",
					buttons : [buttonParseExcel],
					listener : {
						onClose : function(self, arg) {
							view.removeChild(parseAction);
							view.removeChild(uploadActionExcel);
						}
					}
				});
				var url = "dataimport.view.online.DataViewMaintain.ling?excelModelId="+action._excelModelId;
				var iframe = new dorado.widget.IFrame({
					path : url,
					height : "100%",
					width : "100%",
					path : url
				});
				showCacheDialog.addChild(iframe);
				showCacheDialog.show();
			}

			function doExecuteNoViewData() {
				dorado.MessageBox.confirm("文件上传成功，是否需要导入数据?", function() {
					parseAction.execute(function(result) {
					uploadExcelDialog.close();
						$callback(callback, true, result);
					});
				});
			}

			var parseAction = view.id("$parseAction");
			if (parseAction == null) {
				parseAction = new dorado.widget.AjaxAction({
					view : view,
					id : "$parseAction",
					service : "bdf.ExcelMaintain#processParserdExcelData",
					parameter:{
						excelModelId:action._excelModelId
					},
					executingMessage : "正在导入数据..."

				});
			}
			var uploadActionExcel = view.id("$uploadActionExcel");
			if (uploadActionExcel == null) {
				uploadActionExcel = new dorado.widget.UploadAction({
					id : "$uploadActionExcel",
					fileResolver : "excelImportUploadResolver#process",
					autoUpload : true,
					listener : {
						onFilesAdded : function(self, arg) {
							var s = "", name;
							if (arg.files.length > 1) {
								s = "请选择一个Excel格式的文件";
							} else {
								name = arg.files[0].name;
								if (!(new RegExp(".xls$").test(name) || new RegExp(".xlsx$").test(name))) {
									s = "请选择一个Excel格式的文件";
								}
							}
							if (s.length > 0) {
								uploadExcelDialog.close();
								throw new dorado.Exception(s);
							}
							var parameter={
									startRow : action._startRow || 0,
									endRow : action._endRow || 0,
									excelModelId : action._excelModelId
							};
							for(var attr in action._parameter){  
								parameter[attr]=action._parameter[attr];  
					        } 
							self.set("parameter", parameter);
							self.taskId = dorado.util.TaskIndicator.showTaskIndicator("正在上传文件" + name + "...", "main");
						},
						onFileUploaded : function(self, arg) {
							if (self.taskId) {
								dorado.util.TaskIndicator.hideTaskIndicator(self.taskId);
							}
							if (arg.returnValue&&arg.returnValue.length > 0) {
								uploadExcelDialog.close();
								throw new dorado.Exception(arg.returnValue);
							}
							if (showImportData != false) {
								doExecuteViewData();
							} else {
								doExecuteNoViewData();
							}
						},
						onError  : function(self, arg) {
							if (self.taskId) {
								dorado.util.TaskIndicator.hideTaskIndicator(self.taskId);
							}
							//throw new dorado.Exception(arg.error);
							return false;
						}
					}
				});
			}
			var buttonUploadExcel = new dorado.widget.Button({
				caption : "上传Excel文件",
				icon : "url(>skin>common/icons.gif) -120px -280px",
				action : "$uploadActionExcel"
			});
			var buttonDwonLoadTemplate = new dorado.widget.Button({
				caption : "下载模板",
				icon : "url(>skin>common/icons.gif) -140px -240px",
				listener : {
					onClick : function(self, arg) {
						window.location.href="attachment.download.action?groupId=exceltemplate&relationId="+action._excelModelId;
					}
				}
			});
			var uploadExcelDialog = new dorado.widget.Dialog({
				width : 350,
				height : 150,
				center : true,
				closeAction : "hide",
				status : "hidden",
				maximizeable : false,
				modal : true,
				caption : "导入Excel文件",
				view : view,
				animateType:"none",
				buttons : [buttonUploadExcel,buttonDwonLoadTemplate],
				listener : {
					onClose : function(self, arg) {
						view.removeChild(uploadActionExcel);
						view.removeChild(buttonDwonLoadTemplate);
						view.removeChild(parseAction);
					},
					onHide : function(self, arg) {
						view.removeChild(uploadActionExcel);
						view.removeChild(buttonDwonLoadTemplate);
						view.removeChild(parseAction);
					}
				}
			});
			view.addChild(uploadExcelDialog);
			uploadExcelDialog.show();
		}
	});
})();
