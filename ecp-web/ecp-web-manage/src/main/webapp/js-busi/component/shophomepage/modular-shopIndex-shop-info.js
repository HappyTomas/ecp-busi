;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([ ], function() {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".modular-shopIndex-shop-info").modular_shopIndex_shop_info();
				}
			};
		});
	} else {
		// 全局模式 ，不使用AMD 规范的时候，使用的插件
		factory(jQuery);
	}
}(function($) {
	// / 具体插件的定义；
	var defaultOpts = {
	};

	var $shopIndexShopInfo = {
			getData:function(opts){
				$.eAjax({
					url : $webroot+'/cmscommongetdata/qyrshopinfo',
					data : {
						"ifShopFullName" : opts.ifShopFullName,
						"ifLinkPerson" : opts.ifLinkPerson,
						"ifLinkPhone" : opts.ifLinkPhone,
						"ifGoodsBaby" : opts.ifGoodsBaby,
						"ifRate" : opts.ifRate,
						"ifSales" : opts.ifSales,
						"ifCollectShop" : opts.ifCollectShop,
						"shopId" : opts.shopId,
					},
					async : true,
					type : "post",
					dataType : "html",
					success : function(data, textStatus) {
						if (data == null) {
							return;
						} else {
							if(opts.callback && $.isFunction(opts.callback)){
								opts.callback(data);
							}
						}
					}
				});
			},
		"doData" : function(el, datas) {
			var $container = $(el);
			if(datas){
				$container.html('');
				$container.html(datas);
			}else{
				$container.html("<div class ='pro-empty'>亲，这家伙太懒，暂未配置数据！</div>");
			}
		},
		/**
		 * 调用获取数据组件
		 */
		"control":function(el,opts){
			/* 加载loading */
			var $container = $(el);
			$container.append('<div class="loading"></div>');
			$shopIndexShopInfo.getData({
				"ifShopFullName" : opts.ifshopfullname,
				"ifLinkPerson" : opts.iflinkperson,
				"ifLinkPhone" : opts.iflinkphone,
				"ifGoodsBaby" : opts.ifgoodsbaby,
				"ifRate" : opts.ifrate,
				"ifSales" : opts.ifsales,
				"ifCollectShop" : opts.ifcollectshop,
				"shopId": opts.shopId,
				"callback":function(ads){
					$shopIndexShopInfo.doData(el,ads);
				}
			});
		},
	};

	$.fn.modular_shopIndex_shop_info = function() {
		return this.each(function() {
			var opts = $.ecpPlugin.parseOptions(this, defaultOpts);
			$shopIndexShopInfo.control(this,opts);
			return $(this);
		});
	};
}));
