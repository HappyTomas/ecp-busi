//页面初始化模块
$(function() {
	var pInit = function() {
		var init = function() {

			// //绑定提交按钮事件
			$('#evalSearchBtn').unbind("click").click(function() {

				var url = GLOBAL.WEBROOT + '/seller/gdsevalshop/gridlist';

				var p = ebcForm.formParams($('#evalForm'));
				$('#evalListDiv').empty();
				$('#evalListDiv').load(url, p);
			});

			$("#evalResetBtn").unbind("click").click(function() {
				$("#evalForm")[0].reset();
			});

		};
		return {
			init : init
		};
	};
	pageConfig.config({
		// 指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
		plugin : [],
		// 指定页面
		init : function() {
			var giftList = new pInit();
			giftList.init();
		}
	});
});
