;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([], function() {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".modular-shop-category").modular_shop_category();
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
		getData:function(opts){
			$.eAjax({
				url :  $webroot+'/cmscommongetdata/qryCatgList',
				data : {
					"gdsCategory" : opts.gdsCategory,
					"shopId" : opts.shopId,
					"title" : opts.title
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
			    $(el).append("<div style = 'height:"+opts.placeHeight+"px' class='sidebar-box'></div>");
			    $sidebarCatgShader.getData({
				"gdsCategory" : opts.gdscategory,
				"title" : opts.title,
				"shopId" : opts.shopId,
				"callback":function(catgs){
					$sidebarCatgShader.doData(el,catgs);
				}
			});
		}
	};

	$.fn.modular_shop_category = function() {
		return this.each(function() {
			var opts = $.ecpPlugin.parseOptions(this, defaultOpts);
			$sidebarCatgShader.control(this,opts);
			return $(this);
		});
	};
}));
