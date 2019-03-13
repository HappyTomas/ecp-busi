;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define(["ecp-component/wap/cms-url-tool"], function(urlTool) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".modular-wap-oneImg").modular_wap_oneImg();
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
	
	$modularWapOneImg = {
		/**
		 * 根据FileID获得文件URL
		 * @param fileId
		 */
		"loadImgUrlById" : function(fileId,callback){		
			var url = '';
			if(!fileId) return '';
			//根据FileId读取图片
			$.eAjax({
				url : $webroot + "modular-dynamic/loadImgById",
				data : {fileId : fileId+"_640x!"},
				success : function(returnInfo){
					url = returnInfo.imgUrl;
					callback(url);
				}
			});
			return url;
		},
		/**
		 * 调用获取数据组件
		 */
		"control":function(el,opts){
//			var main = $(el).closest('div.phone-modular');
//			$(main).addClass('tpl-ad');
			var picurl=opts.picurl;
			var $thisObj=$(el);
			var callback=function (url){
				$('#one_row_image',$thisObj).attr("src",url);
			};
			$modularWapOneImg.loadImgUrlById(picurl,callback);
			$modularWapOneImg.doData(el,opts);
		},
		"doData":function(el,opts){
			//加载链接
			if(opts.isPub){
				var $dataList = $("a",$(el));
				$dataList.each(function(){
					var href = $(this).attr("lazy-href");
					if(href){
						if(href.indexOf("?")== -1){
							href+="?adid="+opts.adid;
						}else{
							href+="&adid="+opts.adid;
						}
						var href_url=_eCmsUrlTool.getHtmlAbsUrl(opts.webRoot,href);
						$(this).attr("href",href_url);
					}
				});
			}
		}
	};

	$.fn.modular_wap_oneImg = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){
			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			var opts = $.ecpPlugin.parseOptions(this, defaultOpts);
			$modularWapOneImg.control(this,opts);
			$(this).data("comStatus","end");
			return $(this);
		});
	};
	
	
}));
