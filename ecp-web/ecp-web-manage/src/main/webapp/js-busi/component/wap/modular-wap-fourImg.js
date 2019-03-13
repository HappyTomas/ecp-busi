;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define(["ecp-component/wap/cms-url-tool"], function(urlTool) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".modular-wap-fourImg").modular_wap_fourImg();
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
	
	$modularWapfourImg = {
		/**
		 * 调用获取数据组件
		 */
		"control":function(el,opts){
			$modularWapfourImg.doData(el,opts);
		},
		"doData":function(el,opts){
			//加载链接
			var $dataList = $("a",$(el));
			$dataList.each(function(){

				var href = $(this).attr("lazy-href");
				var adid = $(this).attr("lazy-adid");
				if(opts.isPub && href){
					if(href.indexOf("?")== -1){
						href+="?adid="+adid;
					}else{
						href+="&adid="+adid;
					}
					$(this).attr("href",_eCmsUrlTool.getHtmlAbsUrl(opts.webRoot,href));
				}
				var $img = $(this).find("img");
				var srcV = $img.attr("src");
				var lazy_src = $img.attr("lazy-src");
				if(null!=lazy_src&&lazy_src.length!=0&&lazy_src!=""){
					$img.attr("src",lazy_src);
				}
			});
		}
	};

	$.fn.modular_wap_fourImg = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){
			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			var opts = $.ecpPlugin.parseOptions(this, defaultOpts);
			$modularWapfourImg.control(this,opts);
			$(this).data("comStatus","end");
			return $(this);
		});
	};
	
	
}));
