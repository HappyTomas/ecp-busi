// 页面初始化模块
$(function() {
			var pInit = function() {
				var init = function() {
					var shopId = $("#shopId").val();
					var gdsId = $("#gdsId").val();
					var catgCode = $("#catgCode").attr('catgCode');
					var gdsName = $("#gdsName").val();
					var isbn = $("#isbn").val();
					var repType = $("#repType").val();
					var stockStatus = $("#stockStatus").val();
					var data = new Object();
					data.shopId = shopId;
					data.gdsId = gdsId;
					data.catgCode = catgCode;
					data.gdsName = gdsName;
					data.isbn = isbn;
					data.repType = repType;
					data.stockStatus = stockStatus;
					// 分页
					$('#pageControlbar').bPage({
						url : GLOBAL.WEBROOT
								+ '/seller/goods/stockinfo/listStock',
						asyncLoad : true,
						asyncTarget : '#stockListDiv',
						params : function() {
							return data;
						}
					});

					$("a[name=stockrow]").unbind('click').click(function() {

				var shopId = $(this).attr("shopid");
				var id = $(this).attr("stockid");
				var gdsId = $(this).attr("gdsid");
				var availCount = $(this).attr("avaicount");
				bDialog.open({
							title : '库存调整',
							width : 540,
							height : 270,
							url : GLOBAL.WEBROOT + "/seller/goods/stockinfo/optStock",

							params : {
								"stockId" : id,
								"shopId" : shopId,
								"gdsId" : gdsId,
								"availCount" : availCount
							},
							callback : function(data) {
									 $('#stockSearchBtn').trigger('click');

							}
						});

			
							});

				};
				return {
					init : init
				};
			};
			pageConfig.config({
						// 指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
						plugin : ['bPage'],
						// 指定页面
						init : function() {
							var stockList = new pInit();
							stockList.init();
						}
					});
		});
