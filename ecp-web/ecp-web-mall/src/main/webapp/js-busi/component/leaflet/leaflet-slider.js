;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([ 'jquery-powerSwitch','ecp-component/do-data' ], function(slide,adData) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".homepage-ad").homepage_ad();
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

	$homepage_ad = {
		/**
		 * 页面轮播广告，公用；
		 * @param el
		 * @param images
		 */
		"doData" : function(el, datas,opts) {
			if(datas && datas.respList && datas.respList.length > 0){
				var $homepage_ad_num = $(".homepage-ad-num", el);
				var $homepage_ad_img = $(".homepage-ad-img", el);
				$homepage_ad.doAdList($homepage_ad_num,$homepage_ad_img,datas.respList,opts);
				$(el).show();
			}else{//无数据
				$(el).empty();
				$(el).append("<div class ='pro-empty'>黄金广告位，征集中。。。</div>");
			}
		},
		"control":function(el,opts){
			$(".homepage-ad-img", el).append("<div class='loading-small'></div>");
			$AdData.getData({
				"placeId":opts.placeId,
				"shopId":opts.shopId,
				"placeSize":opts.placeSize,
				"placeWidth":opts.placeWidth,
				"placeHeight":opts.placeHeight,
				"status" : opts.status,
				"callback":function(ads){
					$homepage_ad.doData(el,ads,opts);
				}
			});
		},
		"doAdList":function(_num,_img,dataList,opts){
			// 拼装轮播广告下列数字  id:sy_lb_ul
			_num.empty();
			var numStr = "";
			// 拼装轮播广告图片  sy_lb_img
			_img.empty();
			var imgStr = "";
			$.each(dataList, function(i, n) {
				var k = i + 1;
				numStr += "<li>";
				if(i==0){
					numStr += "<a class=\"current\" href='javascript:void(0);' data-rel='bnimg"+k+"'>"+k+"</a>";
					imgStr += "<li id='bnimg"+k+"' style=\"display: block\">";
				}else{
					numStr += "<a href='javascript:void(0);' data-rel='bnimg"+k+"'>"+k+"</a>";
					imgStr += "<li id='bnimg"+k+"'>";
				}
				numStr += "</li>";
			    
				if(opts.isLink == '0'){
					imgStr += "<img src='"+n.vfsUrl+"' alt='"+n.advertiseTitle+"'>";
				}else{
					imgStr += "<a target='_blank' href=\""+n.linkUrl +"\">";
					imgStr += "<img src='"+n.vfsUrl+"' alt='"+n.advertiseTitle+"'>";
					imgStr += "</a>";
				}
				
				imgStr += "</li>";
			});
			
			if(dataList.length > 1){
				_num.append(numStr);
			}
			
			_img.append(imgStr);
			_img = null;
			
			_num.find("a").powerSwitch({
                classAdd: "current",
                animation: "translate",
                autoTime: 3000,
                hoverStop: true,
                eventType: "hover"
            });
			_num=null;
		}
	};

	$.fn.homepage_ad = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){

			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			
			var opts = $.ecpPlugin.parseOptions(this,defaultOpts);
			if(opts == null || !opts.placeId || !opts.placeSize ){
				return ;
			}
			$homepage_ad.control(this,opts);
			
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
