//页面初始化模块
$(function() {
	var pInit = function() {
		var init = function() {
			var shopId = $("#shopId").val();
			var skuId = $("#skuId").val();
			var gdsName = $("#gdsName").val();
			var data = new Object();
			data.shopId = shopId;
			data.skuId = skuId;
			data.gdsName = gdsName;
 			// 分页
			$('#pageControlbar').bPage({
				url : GLOBAL.WEBROOT + '/seller/gdscollshop/gridlist',
				asyncLoad : true,
				asyncTarget : '#collectListDiv',
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
