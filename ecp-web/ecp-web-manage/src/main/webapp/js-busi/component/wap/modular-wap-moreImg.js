;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([], function() {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".modular-wap-moreImg").modular_wap_moreImg();
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
	
	$modularWapMoreImg = {
		/**
		 * 调用获取数据组件
		 */
		"control":function(el,opts){
			$modularWapMoreImg.doData(el,opts);
			
			var w = 0;
	        $('#proList li',$(el)).each(function () {
	            w = w + $(this).width() + 11;
	        });
	        $('#proList .tScroll',$(el)).width(w);
	        var $scrollObj = $('#proList',$(el))[0];
	        if(opts.pc!=true&&opts.isPub==true){ //手机羰
	        	new $.AMUI.iScroll($scrollObj, {
	        		scrollX: true,
	        		scrollY: false,
	        		preventDefault:false
	        	});
	        }else{
	        	new $.AMUI.iScroll($scrollObj, {
	        		scrollX: true,
	        		scrollY: false
	        	});
	        }
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
				var imgSrc = $img.attr("lazy-src");
				if(imgSrc){
					$img.attr("src",imgSrc);	
				}
			});
		}
	};

	$.fn.modular_wap_moreImg = function() {
		return this.each(function() {
			var opts = $.ecpPlugin.parseOptions(this, defaultOpts);
			$modularWapMoreImg.control(this,opts);
			return $(this);
		});
	};
	
	
}));
