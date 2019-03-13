;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([], function(gdsData) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".modular-mall-category").modular_mall_category();
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
				url : $webroot + '/cmshomepagegetdata/qryMallCatgList',
				data : {
					"gdsCategory" : opts.gdsCategory,
					"placeHeight" : opts.placeHeight,
					"returnUrl" : "mallhomepage/mall-category-template"
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
//						alert(data);
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
			$("#mall-category-render",$(el)).empty();
			$("#mall-category-render",$(el)).html(datas);
			
			$mallCategory.bandYsHomeCatg(el);
		},
		/**
		 * 调用获取数据组件
		 */
		"control":function(el,opts){
			    $("#mall-category-render",$(el)).append("<div class='tpl-loading'></div>");
			    $mallCategory.getData({
					"gdsCategory" : opts.gdsCategory,
					"placeHeight" : opts.placeHeight,
					"callback":function(catgs){
						$mallCategory.doData(el,catgs,opts);
					}
				});
		},
		"bandYsHomeCatg":function(el){
			 var childs=$('.navList .navChild',$(el));
	         var navBoxs=$('.navBoxs .navBox-hover',$(el));
	         navBoxs.each(function(){
	        	    if(childs.eq($(this).index()).children().length <= 0){
	        	    	$(this).removeClass("navBox-hover");
	        	    }
	        	  });
	         
	         navBoxs=$('.navBoxs .navBox-hover',$(el));
	         navBoxs.hover(function(){
	             $(this).addClass('hover').siblings().removeClass('hover');
	             var index=$(this).index();
	             childs.hide().eq(index).show();
	         },function(e){
	             if(!$(e.relatedTarget).hasClass('.navList')
	                     && $(e.relatedTarget).parents('.navList').size()<=0){
	                 childs.hide();
	                 $(this).removeClass('hover');
	             }
	         });
	         childs.on('mouseleave',function(e){
	             if(!$(e.relatedTarget).hasClass('.navBox')){
	                 childs.hide();
	                 navBoxs.eq($(this).index()).removeClass('hover');
	             }
	         });
		}
	};

	$.fn.modular_mall_category = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){

			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			var opts = $.ecpPlugin.parseOptions(this, defaultOpts);
			if(opts == null){
				opts ={} ;
			}
			$mallCategory.control(this,opts);
			
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
