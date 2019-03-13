//页面初始化模块
$(function() {
	var pInit = function() {
		var init = function() {
			//分页
			$('#pageControlbar').bPage(
					{
						url : GLOBAL.WEBROOT
								+ '/seller/promcoup/couponlist',
						asyncLoad : true,
						asyncTarget : '#coupListDiv',
						params:function(){
							return {
							shopId:$('#shopId').val(),
							coupTypeId:$('#coupTypeId').val(),
						    status:$('#status').val(),
							coupName:$('#coupName').val()
							};
						}
					});
			$("tr").click(function() {
				if ($(this).find(":radio").attr("checked")) {
					$(this).find(":radio").attr("checked", false);
				}else{
					$(this).find(":radio").attr("checked", true);
				}
			});
			$("input:radio").click(function() {
				if ($(this).attr("checked")){
					$(this).attr("checked", false);
				}else{
					$(this).attr("checked", true);
				}
			});
		};
		return {
			init : init
		};
	};
	pageConfig.config({
		//指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
		plugin : [ 'bPage' ],
		//指定页面
		init : function() {
			var coupList = new pInit();
			coupList.init();
		}
	});
});