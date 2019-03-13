;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define(["ecp-component/wap/cms-url-tool"], function(urlTool) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".modular-wap-noticeInfo").modular_wap_noticeInfo();
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
	
	$modularWapNoticeInfo = {
		/**
		 * 获取公告数据
		 */
		"getData" : function(opts){		
			$.eAjax({
				url : $webroot + '/cmshomepagegetdata/infolistplug',
				data : {
					"siteId" : opts.siteId,
					"placeSize":opts.totalNum
				},
				async : true,
				type : "post",
				dataType : "json",
				success : function(data, textStatus) {
					if (data == null) {
						return;
					} else {
						if(opts.callback && $.isFunction(opts.callback)){
							opts.callback(data);
						}
					}
				},
				error :  function(){
					opts.callback(null);
				}
			});
		},
		"control":function(el,opts){
			var $container = $("#notice", el);
			$container.html('<div class="tpl-loading"></div>');
			if(!opts || !opts.siteId){
				$container.html('<div class="nodata">站点错误</div>');
			}else if(!opts || !opts.totalNum || opts.totalNum <=0){
				$container.html('<div class="nodata">总数配置错误</div>');
			}else{
				$modularWapNoticeInfo.getData({
					"siteId":opts.siteId,
					"totalNum":opts.totalNum,
					"callback":function(infos){
						$modularWapNoticeInfo.doData(el,opts,infos);
					}
				});
			}
		},
		"doData":function(el,opts,datas){
			var $container = $("#notice", el);
			if(datas && $.isPlainObject(datas)){
				var respList = datas.respList;
				if (respList && respList.length>0) {// 有数据
					$container.empty();
					$modularWapNoticeInfo.doInfoDatas($container,respList);
				}else{
					$container.html('<div class="nodata">暂无最新公告</div>');
				}
			}
			
			$modularWapNoticeInfo.doHref(el,opts);
			$modularWapNoticeInfo.doslide(el);
			
		},
		"doInfoDatas":function($container,infos){
			$container = $($container);
			var $ul = $('<ul class="am-slides"></ul>');
			for(var i in infos){
				var info = infos[i];
				if(info && info.id && info.infoTitle){
					var flag =info.typeName?'<i class="flag">'+info.typeName+'</i>':"";
					var href = 'info/infoDetail?id='+info.id||'';
					var $li = $('<li>'+flag+'<a href="javascript:void(0);" lazy-href="'+href+'">'+(info.infoTitle||"")+'</a></li>');
					$ul.append($li);
				}
			};
			$container.append($ul);
		},
		"doHref" : function(el,opts){
			if(opts.isPub){
				var $dataList = $("a",$(el));
				$dataList.each(function(){
					var href = $(this).attr("lazy-href");
					if(href){
						$(this).attr("href",_eCmsUrlTool.getHtmlAbsUrl(opts.webRoot,href));
					}
				});
			}
		},
		"doslide":function(el){
			//启用轮播
			var $datas = $("li",$(el));
			if($datas && $datas.length > 0){
				var params = {}
				if($datas.length == 1){//如果只有一张图片  则不启用循环轮播几触屏控制
				    params = {
							animationLoop: false,//循环轮播
							touch: false //触屏控制
					}
				}
				params = $.extend(params,{
	                direction:"vertical",
	                slideshowSpeed:2000,
	                controlNav:false,
	                directionNav:false
	            });
				$('#notice',$(el)).flexslider(params);
			}
		}
	};
	$.fn.modular_wap_noticeInfo = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){
			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			var opts = $.ecpPlugin.parseOptions(this, defaultOpts);
			$modularWapNoticeInfo.control(this,opts);
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
