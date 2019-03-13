;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([ ], function() {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".modular-shopIndex-shop-innerindex").modular_shopIndex_shop_innerindex();
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

	var $shopInnerIndex = {
			getData:function(opts){
				$.eAjax({
					url :  $webroot+'/cmscommongetdata/gdsgrid',
					data : {
						"ifShowTime" : opts.ifShowTime,
						"ifShowSale" : opts.ifShowSale,
						"ifShowPrice" : opts.ifShowPrice,
						"firstSort" : opts.firstSort,
						"showWay" : opts.showWay,
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
				$container.html("<div class ='pro-empty'>抱歉，查询不到数据！</div>");
			}
		},
		/**
		 * 调用获取数据组件
		 */
		"control":function(el,opts){
			/* 加载loading */
			var $container = $(el);
			$container.append('<div class="loading"></div>');
			$shopInnerIndex.getData({
				"ifShowTime" : opts.ifshowtime,
				"ifShowSale" : opts.ifshowsale,
				"ifShowPrice" : opts.ifshowprice,
				"showWay" : opts.showway,
				"firstSort" : opts.firstsort,
				"shopId" : opts.shopId,
				"callback":function(ads){
					$shopInnerIndex.doData(el,ads);
				}
			});
		},
	};

	$.fn.modular_shopIndex_shop_innerindex = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){

			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			var opts = $.ecpPlugin.parseOptions(this, defaultOpts);
			if(opts == null){
				opts = {};
			}
			$shopInnerIndex.control(this,opts);
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
