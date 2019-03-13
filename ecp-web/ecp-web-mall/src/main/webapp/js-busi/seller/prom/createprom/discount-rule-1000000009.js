$(function() {

	// 页面业务逻辑处理内容
	var pageInit = function() {

		var init = function() {

			// 赠送类型 事件
			$(".sendType").click(function() {
				if ($(this).val() == '0') {
					// 固定值
					$("#help-inline0").removeClass("hide");
					$("#help-inline1").addClass("hide");
				} else {
					// 指定倍数
					$("#help-inline1").removeClass("hide");
					$("#help-inline0").addClass("hide");
				}
			});
			// 设置选中值 展示
			var _sendType = $('#sendType').val();
			if (_sendType == 1) {
				$(".sendType").eq(1).attr("checked", true);
			}
		};
		return {
			init : init
		};

	};
	pageConfig.config({
		// 指定需要加载的插件，名称请参考common中定义的插件名称，注意大小写
		plugin : [ 'bForm' ],
		// 指定页面
		init : function() {
			var p = new pageInit();
			p.init();
		}
	});

});

var discountRuleFun = {
	// 保存 提交验证
	valid : function() {
		var _reData = new Object();
		_reData.value = false;// 返回默认 失败
		var orderMoney = $('#orderMoney').val();
		if (!promCheck.priceNumber(orderMoney)) {
			eDialog.alert('促销规则-订单金额格式不正确，请最多保留两位小数。', null);
			_reData.value = false;// 返回默认 失败
			return _reData;
		}
		_reData.value = true;
		return _reData;
	}
};
