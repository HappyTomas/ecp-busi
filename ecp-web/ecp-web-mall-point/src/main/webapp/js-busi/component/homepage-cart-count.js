;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define(['ecp-component/cartcount-data' ], function(adData) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".cartCount").homepage_cart_count();
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

	$homepage_cart_count = {
		/**
		 * 购物车数量展示
		 * @param el
		 * @param datas
		 * @author wangbh
		 */
		"doData" : function(el, datas) {
			$(el).html("("+datas+")");
		},
		"control":function(el,opts){
			
			$CartCountData.getData({
				"placeId":opts.placeId,
				"placeSize":opts.placeSize,
				"placeWidth":opts.placeWidth,
				"placeHeight":opts.placeHeight,
				"status" : opts.status,
				"callback":function(ads){
					$homepage_cart_count.doData(el,ads);
				}
			});
		}
	
	};
	$.fn.homepage_cart_count = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){

			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			
			var opts = $.ecpPlugin.parseOptions(this,defaultOpts);
			if(opts == null || !opts.status){
				return ;
			}
			$homepage_cart_count.control(this,opts);
			
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
