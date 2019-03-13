;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define(['ecp-component/do-data' ], function(adData) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".homepage-info").homepage_info();
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

	$homepage_info = {
		/**
		 * 生成快报信息；
		 * @param el
		 * @param images
		 * @author jiangzh
		 */
		"doData" : function(el, datas) {
			var $container = $(".homepage-info-slides", el);
			$container.empty();// 清楚缓冲动态图
			if(datas && $.isPlainObject(datas)){
				var respList = datas.respList;
				if (respList && respList.length>0) {// 有数据
					var str = "";
					
					/*new year 的特殊处理，年后要换回来。*/
					var LiStyle = "";
					$.each(respList, function(i, n) {
						//限制标题大小
						/*if(n.infoTitle && n.infoTitle.length>14){
							n.infoTitle = n.infoTitle.substring(0,13)+"...";
						}*/
						
						/*new year 的特殊处理，年后要换回来。*/
						if(i<2){
							LiStyle = "keynode";
						}else{
							LiStyle = "";
						}
							
						str += "<li class = '"+LiStyle+"'><a target='_blank' href='" + GLOBAL.WEBROOT
								+ "/info/infodetail?id=" + n.id + "'>" + (n.typeName?("["
								+ n.typeName + "] "):'' )+ (n.infoTitle?n.infoTitle:"" )+ "</a></li>";
					});
					$(".info-more", el).show();//显示更多链接
					//用新页面打开
					$(".info-more", el).attr("target","_blank");
					
					$container.append(str);
				} else {//无数据
					$container.append("<div class ='pro-empty'>亲，暂无数据！</div>");
				}
			}else{
				$container.append("<div class ='pro-empty'>亲，出错啦！</div>");
			}
		},
		"control":function(el,opts){
			var $container = $(".homepage-info-slides", el);
			$container.append('<div class="loading-small"></div>');
			$InfoData.getData({
				"placeId":opts.placeId,
				"placeSize":opts.placeSize,
				"status" : opts.status,
				"callback":function(ads){
					$homepage_info.doData(el,ads);
				}
			});
		}
	};

	$.fn.homepage_info = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){

			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			
			var opts = $.ecpPlugin.parseOptions(this,defaultOpts);
			if(opts == null || opts.placeId =="" || opts.placeId=="undefined"){
				return ;
			}
			$homepage_info.control(this,opts);
			
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
