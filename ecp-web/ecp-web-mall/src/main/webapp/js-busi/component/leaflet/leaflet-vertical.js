;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define(['ecp-component/do-data' ], function(adData) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".homepage-ad-vertical").homepage_ad_vertical();
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

	$homepage_ad_vertical = {
		/**
		 * 页面右侧广告，公用；
		 * @param el
		 * @param datas
		 * @author jiangzh
		 */
		"doData" : function(el, datas,opts) {
			if(datas && datas.respList && datas.respList.length > 0){
				$homepage_ad_vertical.doAdList($(el),datas.respList,opts);
			}else{//无数据
				$(el).empty();
				$(el).append("<div class ='pro-empty'>亲，暂无数据！</div>");
			}
		},
		"control":function(el,opts){
			/* 加载loading */
			$(el).append('<div class="loading-small"></div>')
			$AdData.getData({
				"placeId":opts.placeId,
				"placeSize":opts.placeSize,
				"placeWidth":opts.placeWidth,
				"placeHeight":opts.placeHeight,
				"status" : opts.status,
				"callback":function(ads){
					$homepage_ad_vertical.doData(el,ads,opts);
				}
			});
		},
		"doAdList":function(_obj,dataList,opts){
			_obj.empty();
			var str = "";
			imgstyle = "width : "+ opts.placeWidth +"px;height : "+opts.placeHeight +"px;";
			//是否有边框
			var borderBox = "";
			//第一个是否有上补白
			var firstMT  = "";
			
			if(opts.fristMt == "1"){
				firstMT = "mt10";
			}
			if(opts.borderBox == "1"){
			    borderBox = "border-box";
			}
			for(var i = 0;i < dataList.length;i++){//循环广告
				if(i==0){
					str += "<div class=\""+firstMT +" "+ borderBox +"\">";
				}
				else{
					str += "<div class=\"mt10 "+borderBox+"\">";
				}
				str += "<a target='_blank' href =\""+(dataList[i].linkUrl?dataList[i].linkUrl:"javascript:void(0);") +"\">";
				str += "<img style = \""+imgstyle+"\" src='"+dataList[i].vfsUrl+"'alt = '"+dataList[i].advertiseTitle +"'/>";
				str += "</a>";
				str += "</div>";
			};
			/*for(;i < opts.placeSize;i++){//给缺失广告加提示
				str += "<div style = \""+imgstyle+"\" class = ' border-box'><div  class ='pro-empty'>黄金广告位，征集中。。。</div></div>";
			}*/
			_obj.append(str);
			_obj = null;
		}
	};
	$.fn.homepage_ad_vertical = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){

			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			
			var opts = $.ecpPlugin.parseOptions(this,defaultOpts);
			if(opts == null || !opts.placeId || !opts.placeSize || !opts.placeWidth || !opts.placeHeight || !opts.status){
				return ;
			}
			$homepage_ad_vertical.control(this,opts);
			
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
