;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define(['ecp-component/hotShopModule/hotshop-data' ], function(hotShopData) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".hotshop-module-shader").hotshop_module_shader();
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
		placeId : ""
	};

	$hotShopModuleShader = {

		"doData" : function(el, datas) {
			$(el).empty();
			$(el).html(datas);
		},
		/**
		 * 调用获取数据组件
		 */
		"control":function(el,opts){
			
			$hotShopData.getData({
				"placeId" : opts.placeId,
				"gdsSize" : opts.gdsSize,
				"tabSize" : opts.tabSize,
				"placeWidth" : opts.placeWidth,
				"placeHeight" : opts.placeHeight,
				"status" : opts.status,
				"callback":function(catgs){
					$hotShopModuleShader.doData(el,catgs);
				}
			});
		}
	};

	$.fn.hotshop_module_shader = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			
			$(this).data("comStatus","do");
			
			var opts = $.ecpPlugin.parseOptions(this, defaultOpts);
			if(opts == null ){
				return ;
			}
			$hotShopModuleShader.control(this,opts);
			
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
