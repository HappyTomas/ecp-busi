$(function() {
	var _dlg = bDialog.getDialog();
//	 获得父窗口传递给弹出窗口的参数集
	var _param = bDialog.getDialogParams(_dlg);
	if (true == _param.ifCheck) {
		$(".spanlocal").find("input[type=checkbox]").each(function() {
			$(this).prop("disabled", "true");
			$(this).prop("checked", "checked");

		});

	}
	// 获取此次已经选择的地市列表
//	var _cityList = _param.cityList;
//	if (_cityList.length > 0) {
//		$.each(_cityList.split(","), function(n, val) {
//			$(".spanlocal").find("input[name='" + val + "']").each(function() {
//				$(this).prop("checked", "checked");
//
//			});
//
//		});
//	}
	

	
//	$("#btnSave").click(function(o) {
//		var _paramback = new Object();
//		var _checkLength = $(".spanlocal").find("input[type=checkbox]:enabled:checked").size();
//		_paramback.selSize = _checkLength;
//		_paramback.provinceCode = _param.provinceCode;
//		var _cityArray = new Array();
//		$(".spanlocal").find("input[type=checkbox]").each(function() {
//			if ($(this).attr("checked") == "checked") {
//
//				if ($(this).attr("disabled") != "disabled") {
//					// / var s = $(this);
//					_cityArray.push($(this).val());
//				}
//
//			}
//		});
//		_paramback.cityArray = _cityArray;
//		bDialog.closeCurrent({
//			"citySelParam" : _paramback,
//			"provinceCode":_param.provinceCode
//		});
//
//	});

	$("#btnReturn").click(function(o) {

		bDialog.closeCurrent();

	});

});