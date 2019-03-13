//页面初始化模块
$(function() {
	var pInit = function() {
		var init = function() {
			//获得当前弹出窗口对象
//			var _dlg = bDialog.getDialog();
			//获得父窗口传递给弹出窗口的参数集
//			var _param = bDialog.getDialogParams(_dlg);
			var skuId = $("#skuId").val();
			var gdsId = $("#gdsId").val();
			var data = new Object();
//			data.shopId = shopId;
			data.skuId = skuId;
			data.gdsId = gdsId;
 			// 分页
			$('#pageControlbar').bPage({
				url : GLOBAL.WEBROOT + '/seller/gdscollshop/collectorlist',
				asyncLoad : true,
				asyncTarget : '#gdsCollectListDiv',
				params :function(){
					return data;
				}
			});


		};
		return {
			init : init
		};
	};
	pageConfig.config({
		// 指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
		plugin : [ 'bPage' ],
		// 指定页面
		init : function() {
			var collectList = new pInit();
			collectList.init();
		}
	});
});
