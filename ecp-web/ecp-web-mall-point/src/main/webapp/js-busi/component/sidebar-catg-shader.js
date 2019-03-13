;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define(['ecp-component/do-data' ], function(gdsData) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".sidebar-catg-shader").sidebar_catg_shader();
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

	$sidebarCatgShader = {
		/**
		 * 生成商品分类菜单信息；
		 * @param el
		 * @param images
		 * @author zhanbh
		 */
		"doData" : function(el, datas) {
			$(el).empty();
			$(el).html(datas);
		},
		/**
		 * 调用获取数据组件
		 */
		"control":function(el,opts){
			    $(el).append("<div class='loading-small'></div>");
			    $sidebarCatgData.getData({
				"placeId" : opts.placeId,
				"placeHeight" : opts.placeHeight,
				"menuType" : opts.menuType,
				"callback":function(catgs){
					$sidebarCatgShader.doData(el,catgs);
				}
			});
		}
	};

	$.fn.sidebar_catg_shader = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			
			$(this).data("comStatus","do");
			
			var opts = $.ecpPlugin.parseOptions(this, defaultOpts);
			if(opts == null || opts.placeId =="" || opts.placeId=="undefined"){
				return ;
			}
			$sidebarCatgShader.control(this,opts);
			
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
