$(function() {
			var _dlg = bDialog.getDialog();
			// 获得父窗口传递给弹出窗口的参数集
			var _param = bDialog.getDialogParams(_dlg);
			if (true == _param.ifCheck) {
				$(".spanlocal").find("input[type=checkbox]").each(function() {
							$(this).prop("disabled", "true");
							$(this).prop("checked", "checked");

						});

			}
			// 获取此次已经选择的地市列表
			var _newCityList = _param.newCityList;
			if (_newCityList.length > 0) {
				$.each(_newCityList.split(","), function(n, val) {
							$(".spanlocal").find("input[name='" + val + "']")
									.each(function() {
												$(this).prop("checked",
														"checked");

											});

						});
			}
			// 如果省份选项框未被选中,获取此次已经取消的地市列表,取消勾选
			if (true != _param.ifCheck) {
				var _delCityList = _param.delCityList;
				if (_delCityList.length > 0) {
					$.each(_delCityList.split(","), function(n, val) {
								$(".spanlocal").find("input[name='" + val
										+ "']").each(function() {
											this.checked = false;

										});

							});
				}
			}
			$("#btnSave").click(function(o) {
				var _paramback = new Object();
				_paramback.provinceCode = _param.provinceCode;
				// 需要新增的适用范围数组
				var _newCityArray = new Array();
				// 需要删除的适用范围数组
				var _delCityArray = new Array();
				$(".spanlocal").find("input[type=checkbox]").each(function() {
					// 将新选择的适用范围加入新增数组
					if ($(this).attr("checked") == "checked") {

						if ($(this).attr("disabled") != "disabled") {
							// / var s = $(this);
							if ($(this).parent().find("input[type=hidden]")
									.attr("ifHasOver") != "true") {
								_newCityArray.push($(this).val());
							}
						}

					}
					// 将取消勾选的适用范围加入删除数组
					if ($(this).attr("checked") != "checked") {

						if ($(this).attr("disabled") != "disabled") {
							// / var s = $(this);
							if ($(this).parent().find("input[type=hidden]")
									.attr("ifHasOver") == "true") {
								_delCityArray.push($(this).val());
							}
						}

					}

				});
				var _checkLength = $(".spanlocal")
						.find("input[type=checkbox]:enabled:checked").size();
				_paramback.selSize = _checkLength;
				// 如果省份未选中
				if (true != _param.ifCheck) {
					_paramback.newCityArray = _newCityArray;
					_paramback.delCityArray = _delCityArray;
				}

				bDialog.closeCurrent({
							"citySelParam" : _paramback,
							"provinceCode" : _param.provinceCode
						});

			});

			$("#btnReturn").click(function(o) {

						bDialog.closeCurrent();

					});

		});