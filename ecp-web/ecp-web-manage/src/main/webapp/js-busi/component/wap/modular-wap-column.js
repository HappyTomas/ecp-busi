;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define(["ecp-component/wap/cms-url-tool"], function(urlTool) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".modular-wap-column").modular_wap_column();
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
	
	$modularWapColumn = {
		/**
		 * 调用获取数据组件
		 */
		"control":function(el,opts){
//			$(el).parent("div.phone-modular").addClass("tpl-nav");
			$modularWapColumn.doData(el,opts);
		},
		"doData":function(el,opts){
			//加载图片及链接
			var $dataList = $("a.link",$(el));
			$dataList.each(function(){
				var href = $(this).attr("lazy-href");
				if(opts.isPub && href){

					$(this).attr("href",_eCmsUrlTool.getHtmlAbsUrl(opts.webRoot,href));

				}
				var $img = $(this).find("img");
				var imgSrc = $img.attr("lazy-src");
				var imgAlt = $img.attr("lazy-alt");
				if(imgSrc){
					$img.attr("src",imgSrc);	
				}
				if(imgAlt){
					$img.attr("alt",imgAlt);	
				}
			});
		}
	};

	$.fn.modular_wap_column = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){

			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			
			var opts = $.ecpPlugin.parseOptions(this, defaultOpts);
			/*if(opts == null || opts.placeId =="" || opts.placeId=="undefined"){
				return ;
			}*/
			$modularWapColumn.control(this,opts);
			
			$(this).data("comStatus","end");
			return $(this);
		});
	};
	
	
}));
