;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([ 'jquery-powerSwitch'], function(slide) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".modular-leaflet-slider").modular_leaflet_slider();
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
		getData : function(opts){
			$.eAjax({
				url : $webroot + '/cmscommongetdata/qryLeafletList',
				data : {
					"placeId" : opts.placeId,
					"shopId" : opts.shopId,
					"showAmount" : opts.showAmount,
					"placeSize" : opts.placeSize,
					"placeWidth":opts.placeWidth,
					"placeHeight":opts.placeHeight,
					"ifRoll" : opts.ifRoll,
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
					if(opts.callback && $.isFunction(opts.callback)){
						opts.callback(null);
					}
				}
			});
		},
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
				var $warn = $('<div class="nodata"></div>');
				var $warnSpan = $('<span style="color:red;"></span>');
				$warn.append($warnSpan);
				if(!datas){
					$warnSpan.append("无任何数据返回，请检查网络！");
				}else if(datas.errMsg){
					$warnSpan.append(datas.errMsg);
				}else{
					$warnSpan.append('亲，暂无广告！');
				}
				$(el).html($warn);
			}
		},
		"control":function(el,opts){
			$homepage_ad.calElWidth(el,opts);
			$(".homepage-ad-img", el).append("<div class='tpl-loading'></div>");
			$homepage_ad.getData({
				"placeId" : opts.placeid,
				"shopId" : parseInt(opts.shopId) ||"",
				"showAmount" : opts.showamount,
				"placeWidth":opts.placewidth,
				"placeHeight":opts.placeheight,
				"ifRoll":opts.ifroll,
				"status":opts.status,
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
				var bnId = $homepage_ad.getUseFullId(k) || "bnimg"+k;
				numStr += "<li>";
				if(i==0){
					numStr += "<a class=\"current\" href='javascript:void(0);' data-rel='"+bnId+"'>"+k+"</a>";
					imgStr += "<li id='"+bnId+"' style=\"display: block\">";
				}else{
					numStr += "<a href='javascript:void(0);' data-rel='"+bnId+"'>"+k+"</a>";
					imgStr += "<li id='"+bnId+"'>";
				}
				numStr += "</li>";
			    
				var globalurl = GLOBAL.MALLSITEURL ||"";
				n.linkUrl == n.linkUrl||"";
				if($homepage_ad.isAbsUrl(n.linkUrl)){//是绝对地址
					imgStr += "<a target='_blank' href='"+n.linkUrl +"'>";
				}else{
					imgStr += "<a target='_blank' href='"+globalurl.replace(/\/+$/,"") +"/"+n.linkUrl.replace(/^\/+/,"") +"'>";
				}
				
				imgStr += "<img src='"+n.vfsUrl+"' alt='"+n.advertiseTitle+"'>";
				imgStr += "</a>";
				
				imgStr += "</li>";
			});
			
			_img.append(imgStr);
			_img = null;
			
			if(dataList.length > 1){ //大于1张图片才启用轮播
				_num.append(numStr);
				
				var autoTime = 3000;
				if(opts.ifroll!="1"){
					autoTime = 0;//表示不自动轮播
				}
				_num.find("a").powerSwitch({
	                classAdd: "current",
	                animation: "translate",
	                autoTime: autoTime,
	                hoverStop: true,
	                eventType: "hover"
	            });
			}
			_num=null;
		},
		"isAbsUrl":function(url){//判断是否为绝对地址
			url = ""+url;
			var reg = /^(?:(?:\w+:(?:\/|\d+)\/)|(?:\w+\.))/ig;
			return reg.test(url);
		},
		"getUseFullId":(function(){//确保id唯一性
			var lastIndex = 0;
			return function(i){
				i = Math.abs(+i);
				i = i || 1;
				i = i > lastIndex ? i : lastIndex++;
				var id = null;
				var $ele = null;
				var time = 0;
				for(; true ; i++){
					time ++;
					id = "bnimg"+i;
					$ele = $("#"+id);
					if(i > lastIndex && (!$ele || 0 >= $ele.length || time >= 1000)){
						break;
					}
				}
				lastIndex = i;
				return id;
			}
		})(),
		"calElWidth":function(el,opts){
			var widht = Math.abs(opts.placewidth) || 0;
			var heigth = Math.abs(opts.placeheight) || 0;
			var elWidth = Math.abs($(el).width()) || 0;
			var elHeight = 0;
			if(widht && heigth && elWidth){
				elHeight = Math.floor((elWidth / widht ) * heigth);
			}
			if(elHeight){
				$(".tpl-banner",$(el)).css("height",elHeight);
			}
		}
	};

	$.fn.modular_leaflet_slider = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){

			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			var opts = $.ecpPlugin.parseOptions(this, defaultOpts);
			if(opts == null){
				opts = {};
			}
			$homepage_ad.control($(this),opts);
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
