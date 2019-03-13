;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define(['ecp-component/do-data',"ecp-component/utils/cms-url-tool"], function(linksData,urlTool) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".home-links-shader").home_links_shader();
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

	$home_links_shader = {
		/**
		 * 生成首页底部链接；
		 * @param el
		 * @param datas
		 * @author zhanbh
		 */
		"doData" : function(el, datas) {
			if(datas){
				$(el).html(datas);
			}
		},
		"control":function(el,opts){
			$hLinksDataVM.getData({
				"callback":function(linksData){
					$home_links_shader.doData(el,linksData);
					$home_links_shader.doHref(el,opts);
				}
			});
		},
		"doHref":function(el,opts){
			opts.webRoot = opts.webRoot || $webroot;
			$("a",$(el)).each(function(){
			    var $this = $(this);
			    var href = $this.data("lazyHref") || "";
			    var finalHref = _eCmsUrlTool.getHtmlAbsUrl(opts.webRoot,href);
			    if(finalHref){
			    	$this.attr("href",finalHref);
			    }
			});
		}
	};

	$.fn.home_links_shader = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){

			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			
			var opts = $.ecpPlugin.parseOptions(this,defaultOpts);
			if(opts == null || opts.placeId =="" || opts.placeId=="undefined"){
				opts = {};
			}
			$home_links_shader.control(this,opts);
			
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
