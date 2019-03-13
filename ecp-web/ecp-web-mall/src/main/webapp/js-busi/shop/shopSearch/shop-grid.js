// 页面初始化模块
$(function() {
	var pInit = function() {
		var gatherParams = function(pageSize, pageNumber) {
			var data = new Object();
			// 获取排序信息
			var sortField = $("ul.sl-order").find("li").attr("sort");
			if (sortField && sortField != "default") {
				data.sort = sortField;
			}
			// 获取过滤信息
			var evalFilterLevel = $("#evalRateLevel")
					.find("li[selected=selected]").attr("level");
			if (evalFilterLevel && evalFilterLevel != "0") {
				data.evalRate = evalFilterLevel;
			}

			data.pageSize = pageSize;
			data.pageNumber = pageNumber;

			return data;
		}

		// 增加商品列表的新品和热卖的页签的事件监听
		var initGdsTabEvent = function() {
			$(".fav-tabt").find("li").unbind("click").click(function() {
				$(this).parent().find("a.active").removeClass("active");
				$(this).find("a").addClass("active");

				var shopId = $(this).parent().parent().attr("shopId");
				var gdsurl = "";
				if ($(this).attr("key") == "new") {
					gdsurl = GLOBAL.WEBROOT + '/shopsearch/newlist';
				} else {
					gdsurl = GLOBAL.WEBROOT + '/shopsearch/hotlist';
				}
				var gdsData = new Object();
				gdsData.id = shopId;
				$(this).parent().parent().find(".fav-tabc").load(gdsurl,
						gdsData, function() {

							$("#jcarouseln-" + shopId + " a").powerSwitch({
										number : 4,
										container : $("#jcarouselc-" + shopId)
									});

						});

			});

		};
		var init = function() {

			$("ul.sl-order").find("li").each(function() {

				$(this).unbind("click").click(function() {

							$(this).parent().find("li").removeClass("selected");
							$(this).addClass("selected");
							var sortField = $(this).attr("sort");

						});

			});

			$("#evalRateLevel").find("li").each(function() {
						$(this).live("click", function() {
									$(this).parent().find("li")
											.removeAttr("selected");
									$(this).attr("selected", "true");
									$("#evalRateShow").text($(this).text());
								});

					});

			$(".shopGds").each(function() {

						var shopId = $(this).attr("shopId");
						var gdsurl = GLOBAL.WEBROOT + '/shopsearch/hotlist';
						var gdsData = new Object();
						gdsData.id = shopId;
						$(this).find(".fav-tabc").load(gdsurl, gdsData,
								function() {

									$("#jcarouseln-" + shopId + " a")
											.powerSwitch({
												number : 4,
												container : $("#jcarouselc-"
														+ shopId)
											});

								});

					})
			initGdsTabEvent();
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
					var shopGrid = new pInit();
					shopGrid.init();
				}
			});
});

//查看商品详情
function goodDetail(gdsId,skuId) {
	window.open(GLOBAL.WEBROOT + '/gdsdetail/'+gdsId+'-'+skuId);
}
