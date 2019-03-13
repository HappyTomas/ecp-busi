;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([], function(gdsData) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".wechat-mall-category").mall_category();
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

	$mallCategory = {
		/**
		 * 获取当前页面的全部分类菜单目录  的信息，则调用数据处理的回调函数(callback);否则直接返回；
		 * callback : 回调函数，对于数据处理的回调函数；
		 * @author zhanbh
		 */
		getData : function(opts){
			$.eAjax({
				url : $webroot + '/category/qryMallCatgList',
				data : {
					"placeId" : opts.placeId,
					"placeHeight" : opts.placeHeight,
					"returnUrl" : "/main/category/list/category-list"
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
				},
				error:function(data){
//							alert(data);
				}
			});
		},
		/**
		 * 生成商品分类菜单信息；
		 * @param el
		 * @param images
		 * @author zhanbh
		 */
		"doData" : function(el, datas,opts) {
			$(el).empty();
			$(el).html(datas);
		},
		/**
		 * 调用获取数据组件
		 */
		"control":function(el,opts){
		    $mallCategory.getData({
				"placeId" : opts.placeId,
				"callback":function(catgs){
					$mallCategory.doData(el,catgs,opts);
				}
			});
		}
	};
	$.fn.mall_category = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			
			$(this).data("comStatus","do");
			
			var opts = $.ecpPlugin.parseOptions(this, defaultOpts);
			if(opts == null || opts.placeId =="" || opts.placeId=="undefined"){
				return ;
			}
			$mallCategory.control(this,opts);
			
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
