;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([ 'jquery-powerSwitch','ecp-component/do-data' ], function(slide,adData) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".channel-main-nav").main_nav_shader();
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
		path : ""
	};

	$main_nav_shader = {
		/**
		 * 调用获取数据组件
		 */
		"control":function(el,opts){
			//加载动态图
			$channelData.getData({
				"path":opts.path,
				"page":opts.page,
				"callback":function(datas){
					//返回楼层VM，填充楼层信息（页签、页签对应的DIV）
					$(el).empty();
					$(el).html(datas);
					$main_nav_shader.markActive();
				}
			});
		},
	    "markActive":function(){//用于将当前页标记
	    	var page = $("#current-page").val();
	    	var $navList = $("a",".main-mav-item");
	    	if(page!=null && page!=(void 0) && page!="" && $navList.length > 0){
	    		$navList.each(function(i,e){
	    			var url = $(e).attr("url");
	    			if(typeof url === "string" && url!="" && url.indexOf("page") >= 0){
	    				var subUrlList = url.split(/\?|\&/);
	    				if(subUrlList && subUrlList.length && subUrlList.length > 0){
	    					$.each(subUrlList,function(i,subUrl){
	    						if(typeof subUrl === "string" && subUrl!="" && subUrl.indexOf("=") >= 0){
	    							var subStrList = subUrl.split(/=/);
	    							if(subStrList && subStrList.length && subStrList.length == 2){
	    								if(subStrList[0] === "page" && subStrList[1] == page){
	    									$(e).addClass("active");
	    									return;
	    								}
	    							}
	    						}
	    					});
	    				}
	    			}
	    		});
	    	}
	    }
	};
	$.fn.main_nav_shader = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){

			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			
			var opts = $.ecpPlugin.parseOptions(this, defaultOpts);
			if(opts == null || opts.path =="" || opts.path=="undefined"){
				return ;
			}
			$main_nav_shader.control(this,opts);
			
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
