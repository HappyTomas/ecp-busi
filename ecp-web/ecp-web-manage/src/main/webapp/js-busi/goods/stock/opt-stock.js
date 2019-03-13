$(function() {
	var _dlg = bDialog.getDialog();
	// 获得父窗口传递给弹出窗口的参数集
	var _param = bDialog.getDialogParams(_dlg);
	var count = _param.availCount;
	$("select[name=turnType]").change(function() {
		if ($("select[name=turnType]").val() == "01") {
			$("input[name=turnCount]").removeAttr("max", count);
		} else if ($("select[name=turnType]").val() == "02") {
			$("input[name=turnCount]").attr("max", count);

		}

	});

	$("#btnSave").click(function(o) {
		if (!$("#repAddForm").valid())
			return false;
		var _turnType = $("select[name=turnType]").val();
		var _turnCount = $("input[name=turnCount]").val();
		var _paramback = new Object();
		_paramback.stockId = _param.stockId;
		_paramback.turnType = _turnType;
		_paramback.turnCount = _turnCount;
		_paramback.shopId = _param.shopId;
		_paramback.gdsId = _param.gdsId;
		$.gridLoading({
			"message" : "正在保存数据中...."
		});
		$.eAjax({
			url : GLOBAL.WEBROOT + "/goods/stockinfo/saveOptStockInfo",
			data : _paramback,
			success : function(returnInfo) {
				eDialog.success('库存调整成功！', {
					
					onClose:function(){
						
						bDialog.closeCurrent();

					}
				});

			},
			exception : function() {

				$.gridUnLoading();
			}
		});

	});

	$("#btnReturn").click(function(o) {

		bDialog.closeCurrent();

	});

});