//页面初始化模块
$(function() {
	var pInit = function() {
		var init = function() {
			var skuName = $("#skuName").val();
			var skuId = $("#skuId").val();
			var isbn = $("#isbn").val();
			var shopId = $("#shopId").val();
			var data = new Object();
			data.shopId = shopId;
			data.isbn = isbn;
			data.skuName = skuName;
			data.skuId = skuId;
			// 分页
			$('#pageControlbar').bPage({
				url : GLOBAL.WEBROOT + '/seller/gift/gridskulist',
				asyncLoad : true,
				asyncTarget : '#giftSkuListDiv',
				params : function() {
					return data;
				}
			});

			$('a[name=del]')
					.click(
							function() {

								var giftId = $(this).attr("giftId");
								var shopId = $("#shopId").val();
								var param = {
									giftId : giftId,
									shopId : shopId
								};
								eDialog
										.confirm(
												"确定要删除信息吗？",
												{
													'title' : "提示",
													'buttons' : [
															{
																caption : '确定',
																callback : function() {

																	$
																			.eAjax({
																				url : GLOBAL.WEBROOT
																						+ "/seller/gift/giftremove",
																				data : param,
																				success : function(
																						returnInfo) {
																					if (returnInfo.ecpBaseResponseVO.resultFlag == 'ok') {
																						eDialog
																								.success('删除成功！');
																						$(
																								'#giftSearchBtn')
																								.trigger(
																										'click');
																					}
																				}
																			});

																}
															},
															{
																caption : '取消',
																callback : $.noop
															}]
												});
							});

			$('a[name=check]').click(function() {
				var skuId = $(this).attr("skuId");
				var skuName = $(this).attr("skuName");
				var gdsId = $(this).attr("gdsId");
				bDialog.closeCurrent({
					'gdsId' : gdsId,
					'skuId' : skuId,
					'skuName' : skuName
				});

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
			var giftList = new pInit();
			giftList.init();
		}
	});
});
