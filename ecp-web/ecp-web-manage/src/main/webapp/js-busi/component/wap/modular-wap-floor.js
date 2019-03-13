;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define(["ecp-component/wap/cms-url-tool"], function(urlTool) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".modular-wap-floor").modular_wap_floor();
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
	
	$modularWapFloor = {
		/**
		 * 根据FileID获得文件URL
		 * @param fileId
		 */
		"loadImgUrlById" : function(fileId,floorTemplateId,imgNum){	
			var imageType="_214x320!";
			if(32==floorTemplateId){
				imageType="_214x320!";
			}else if(10==floorTemplateId){
				imageType="_640x65!";
			}else if(30==floorTemplateId){
				if(imgNum==1){
					imageType="_280x280!";
				}else{
					imageType="_300x140!";
				}
			}else if(31==floorTemplateId){
				if(imgNum==3){
					imageType="_280x280!";
				}else{
					imageType="_300x140!";
				}
			}else if(50==floorTemplateId){
				if(imgNum<=3){
					imageType="_203x273!";
				}else{
					imageType="_306x202!";
				}
			}
			var url = '';
			if(!fileId) return '';
			//根据FileId读取图片
			$.eAjax({
				url : $webroot + "modular-dynamic/loadImgById",
				data : {fileId : fileId+imageType},
				async : false,//同步执行
				success : function(returnInfo){
					url = returnInfo.imgUrl;
				}
			});
			return url;
		},
		/**
		 * 调用获取数据组件
		 */
		"control":function(el,opts){
			$modularWapFloor.doData(el,opts);
		},
		"doData":function(el,opts){
			//加载链接
			var $dataList = $("a",$(el));
			$dataList = $.merge($dataList,$(el).prev(".modular-head").find('a'));
			$dataList.each(function(i){
				var href = $(this).attr("lazy-href");
				var adid = $(this).attr("lazy-adid");
				if(opts.isPub && href){
//					if(href.indexOf("?")== -1){
//						href+="?adid="+adid;
//					}else{
//						href+="&adid="+adid;
//					}
					$(this).attr("href",_eCmsUrlTool.getHtmlAbsUrl(opts.webRoot,href));
				}
				var $img = $(this).parent().find("img");
				var srcV = $img.attr("src");
				var lazy_src = $img.attr("lazy-src");
				if(null!=lazy_src&&lazy_src.length!=0&&lazy_src!=""){
//					var imgUrl = $modularWapFloor.loadImgUrlById(lazy_src,opts.floorTemplateId,i+1);
					$img.attr("src",lazy_src);
				}
			});
		}
	};

	$.fn.modular_wap_floor = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){
			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			var opts = $.ecpPlugin.parseOptions(this, defaultOpts);
			$modularWapFloor.control(this,opts);
			$(this).data("comStatus","end");
			return $(this);
		});
	};
	
	
}));
