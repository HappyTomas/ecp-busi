;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define(["ecp-component/wap/cms-url-tool"], function(urlTool) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".modular-wap-focus").modular_wap_focus();
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
	
	$modularWapFocus = {
		/**
		 * 调用获取数据组件
		 */
		"control":function(el,opts){
//			$(el).parent("div.phone-modular").addClass("tpl-slider");
			$modularWapFocus.doData(el,opts);
		},
		"doData":function(el,opts){
			//加载图片及链接
			var $dataList = $("a.link",$(el));
			$dataList.each(function(){
				var href = $(this).attr("lazy-href");
				var adid = $(this).attr("lazy-adid");
				if(opts.isPub && href){
					if(href.indexOf("?")== -1){
						href+="?adid="+adid;
					}else{
						href+="&adid="+adid;
					}
					var href_url=_eCmsUrlTool.getHtmlAbsUrl(opts.webRoot,href);
//					$(this).attr("href",_eCmsUrlTool.getHtmlAbsUrl(opts.webRoot,href));
					$(this).attr("href",href_url);
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
			
			//启用轮播
			var param = {}
			if($dataList.length == 1){//如果只有一张图片  则不启用循环轮播几触屏控制
			    param = {
						animationLoop: false,//循环轮播
						touch: false //触屏控制
				}
			}
			$(el).find("div.am-slider").flexslider(param);
			
			if($dataList.length == 1){//由于amazeui不会显示单个控制点 ，所以要手动加
				$("ol.am-control-nav",$(el)).append("<li><a href='javascript:void(0);' class='am-active'></a><i></i></li>")
			}

		}
	};

	$.fn.modular_wap_focus = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){
			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			var opts = $.ecpPlugin.parseOptions(this, defaultOpts);
			$modularWapFocus.control(this,opts);
			$(this).data("comStatus","end");
			return $(this);
		});
	};
	
	
}));
