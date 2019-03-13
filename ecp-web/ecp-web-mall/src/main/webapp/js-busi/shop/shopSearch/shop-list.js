// 页面初始化模块
$(function() {
	var pInit = function() {

		// 定义列表刷新操作枚举

		if (typeof ShopListOpt == "undefined") {
			var ShopListOpt = {};
			ShopListOpt.Pre = 0;
			ShopListOpt.Back = 1;
			ShopListOpt.DefSort = 2;
			ShopListOpt.SaleSort = 3;
		}
		;
		// 增加商品列表的新品和热卖的页签的事件监听
		var initGdsTabEvent = function() {
			$(".fav-tabt")
					.find("li")
					.unbind("click")
					.click(
							function() {
								$(this).parent().find("a.active").removeClass(
										"active");
								$(this).find("a").addClass("active");

								var shopId = $(this).parent().parent().attr(
										"shopId");
								var gdsurl = "";
								if ($(this).attr("key") == "new") {
									gdsurl = GLOBAL.WEBROOT
											+ '/shopsearch/newlist';
								} else {
									gdsurl = GLOBAL.WEBROOT
											+ '/shopsearch/hotlist';
								}
								var gdsData = new Object();
								gdsData.id = shopId;
								$(this)
										.parent()
										.parent()
										.find(".fav-tabc")
										.load(
												gdsurl,
												gdsData,
												function() {

													$(
															"#jcarouseln-"
																	+ shopId
																	+ " a")
															.powerSwitch(
																	{
																		number : 4,
																		container : $("#jcarouselc-"
																				+ shopId)
																	});

												});

							});

		};
		var initGdsList = function() {
			$(".shopGds").each(function() {

				var shopId = $(this).attr("shopId");
				var gdsurl = GLOBAL.WEBROOT + '/shopsearch/hotlist';
				var gdsData = new Object();
				gdsData.id = shopId;
				$(this).find(".fav-tabc").load(gdsurl, gdsData, function() {

					$("#jcarouseln-" + shopId + " a").powerSwitch({
						number : 4,
						container : $("#jcarouselc-" + shopId)
					});

				});

			});
		};

		var gatherPageParams = function(pageSize, pageNumber) {

			var data = new Object();
			// 获取排序信息
			var sortField = $("ul.sl-order").find("li.selected").attr("sort");
			if (sortField && sortField != "default") {
				data.field = sortField;
				data.sort = "desc";
			}
			// 获取过滤信息
			var evalFilterLevel = $("#evalRateLevel").find(
					"li[selected=selected]").attr("level");
			if (evalFilterLevel && evalFilterLevel != "0") {
				data.evalRate = evalFilterLevel;
			}

			data.pageSize = pageSize;
			data.pageNumber = pageNumber;
			if ($("#searchTxt").val() && $("#searchTxt").val() != "") {
				var keyword = $("#keyword").val();
				if (keyword) {
					data.keyword = keyword;
				}
			} else {
				data.keyword = $("#searchTxt").val();

			}

			return data;
		};

		var gatherParams = function() {
			var data = new Object();
			// 获取排序信息
			var sortField = $("ul.sl-order").find("li.selected").attr("sort");
			if (sortField && sortField != "default") {
				data.field = sortField;
				data.sort = "desc";
			}
			// 获取过滤信息
			var evalFilterLevel = $("#evalRateLevel").find(
					"li[selected=selected]").attr("level");
			if (evalFilterLevel && evalFilterLevel != "0") {
				data.evalRate = evalFilterLevel;
			}

			if ($("#searchTxt").val() && $("#searchTxt").val() != "") {
				var keyword = $("#keyword").val();
				if (keyword) {
					data.keyword = keyword;
				}
			} else {
				data.keyword = $("#searchTxt").val();

			}
			
			return data;
		};

		var searchListRequestF = function(type) {

			var pageSize = $("#pageSize").val();
			var pageNumber = $("#pageNumber").val();
			var totalPage = $("#totalPage").val();
			pageNumber = parseInt(pageNumber, 10);

			// 如果当前页已经是最后一页，则不可点击

			if (type == ShopListOpt.Back && pageNumber == totalPage) {
				// $("#backBtn").addClass("disabled");
			} else if (0 == totalPage) {
				// $("#backBtn").addClass("disabled");
			} else if (type == ShopListOpt.Pre && pageNumber == 1) {

			} else {
				if (type == ShopListOpt.Back) {
					pageNumber = pageNumber + 1;
				} else if (type == ShopListOpt.Pre) {
					pageNumber = pageNumber - 1;
				} else if (type == ShopListOpt.DefSort) {
					$("#sortDef").addClass("selected");
					$("#sortSale").removeClass("selected");
				} else if (type == ShopListOpt.SaleSort) {
					$("#sortSale").addClass("selected");
					$("#sortDef").removeClass("selected");
				}
				var data = gatherPageParams(pageSize, pageNumber);
				var url = GLOBAL.WEBROOT + '/shopsearch/list';
				$("#shopList").empty();
				$("#shopList").load(url, data, function(response, status, xhr) {
					if (status == "success") {

						
						
						
						var pageSize = $("#spageSize").val();
						var pageNumber = $("#spageNumber").val();
						var totalPage = $("#stotalPage").val();
						var totalNumber = $("#stotalNumber").val();
						var keyword = $("#skeyword").val();
						
						$("#pageSize").val(pageSize);
						$("#pageNumber").val(pageNumber);
						$("#totalPage").val(totalPage);
						$("#totalNumber").val(totalNumber);
						$("#keyword").val(keyword);
						
						$("#totalRow").text(totalNumber);
						pageNumber = parseInt(pageNumber, 10);
						// 如果当前页已经是最后一页，则不可点击
						if (pageNumber == totalPage) {
							$("#backBtn").addClass("disable");
						}
						if (pageNumber > 1) {
							$("#preBtn").removeClass("disable");
						}

						initGdsList();

					}

				});
			}

		}

		var init = function() {
//			var data = gatherParams();

			// 分页
			$('#pageControlbar').bPage({
				url : GLOBAL.WEBROOT + '/shopsearch/list',
				asyncLoad : true,
				asyncTarget : '#shopList',
				pageSizeMenu : [ 10 ],
				params : function() {
					return gatherParams();
				},
				callback : function() {
					initGdsList();
				}
			});

			// 更新父页面的当前页数
			var pageSize = $("#spageSize").val();
			var pageNumber = $("#spageNumber").val();
			var totalPage = $("#stotalPage").val();
			if(totalPage==0){
				$("#prepage").text(0);
			}else{
				$("#prepage").text(pageNumber);
			}
			if (totalPage) {
				$("#totalPageComp").text(totalPage);
			}

			pageNumber = parseInt(pageNumber, 10);

			if(totalPage==0){
				$("#backBtn").addClass("disable");
			}else{
				// 如果当前页已经是最后一页，则不可点击
				if (pageNumber == totalPage) {
					$("#backBtn").addClass("disable");
				} else {
					$("#backBtn").removeClass("disable");
				}
			}

			if (pageNumber > 1) {
				$("#preBtn").removeClass("disable");
			} else {
				$("#preBtn").addClass("disable");
			}

			$("#preBtn").die("click").live("click", function() {
				searchListRequestF(ShopListOpt.Pre)
			});

			$("#backBtn").die("click").live("click", function() {
				searchListRequestF(ShopListOpt.Back)
			});

			$("#sortDef").die("click").live("click", function() {
				searchListRequestF(ShopListOpt.DefSort)
			});

			$("#sortSale").die("click").live("click", function() {
				searchListRequestF(ShopListOpt.SaleSort)
			});
			$("#evalRateLevel").find("li").die("click").live("click",
					function() {
						searchListRequestF();
					});

			initGdsTabEvent();

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
			var shopList = new pInit();
			shopList.init();
		}
	});
});
