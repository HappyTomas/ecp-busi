;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([], function() {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".modular-mall-announcement").modular_mall_announcement();
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

	$mall_announcement = {
		getData : function(opts){
			$.eAjax({
				url : $webroot + '/cmshomepagegetdata/infolistplug',
				data : {
					"placeId" : opts.placeId,
					"placeSize" : opts.placeSize,
					"status" : opts.status
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
		/**
		 * 生成快报信息；
		 * @param el
		 * @param images
		 * @author gxq
		 */
		"doData" : function(el, datas) {
			var $container = $(".info-body", el);
			$container.empty();// 清楚缓冲动态图
			if(datas && $.isPlainObject(datas) && datas.respList && datas.respList.length>0){
				var respList = datas.respList;
				var str = "";
				$.each(respList, function(i, n) {
					//限制标题大小
					if(n.infoTitle && n.infoTitle.length>14){
						n.infoTitle = n.infoTitle.substring(0,13)+"...";
					}
					str += "<li><a target='_blank' href='" + GLOBAL.MALLSITEURL
							+ "/info/infodetail?id=" + n.id + "'>" + (n.typeName?("["
							+ n.typeName + "] "):'' )+ (n.infoTitle||"" )+ "</a></li>";
				});
				$("#more-info", el).show();//显示更多链接
				//用新页面打开
				$("#more-info", el).attr("target","_blank");
				
				$container.html(str);
			}else{
				var $warn = $('<div class="nodata"></div>');
				var $warnSpan = $('<span></span>');
				$warn.append($warnSpan);
				if(!datas){
					$warnSpan.append("无任何数据返回，请检查网络！");
				}else if(datas.errMsg){
					$warnSpan.append(datas.errMsg);
				}else{
					$warnSpan.append('暂无数据！');
				}
				$container.html($warn);
			}
		},
		"control":function(el,opts){
			var $container = $(".homepage-info-slides", el);
			$container.append('<div class="tpl-loading"></div>');
			$mall_announcement.getData({
				"placeId":opts.placeId,
				"placeSize":opts.placeSize,
				"status" : opts.status,
				"callback":function(ads){
					$mall_announcement.doData(el,ads);
				}
			});
		}
	};

	$.fn.modular_mall_announcement = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){

			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			
			var opts = $.ecpPlugin.parseOptions(this,defaultOpts);
			if(opts == null){
				opts = {} ;
			}
			$mall_announcement.control(this,opts);
			
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
