;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define(['ecp-component/do-data' ], function(adData) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".homepage-ad-middle").homepage_ad_middle();
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

	$homepage_ad_middle = {
		/**
		 * 页面中部广告，公用；
		 * @param el
		 * @param datas
		 * @author jiangzh
		 */
		"doData" : function(el, datas,opts) {
			$adList = $(".ad-middle-list", el);
			$adList.empty();
			if(!datas.respList){
				datas.respList ={};
			}
			$homepage_ad_middle.doAdList($adList,datas.respList,opts);
		},
		"control":function(el,opts){
			/* 加载loading */
			$(".ad-middle-list", el).append('<div style = "height:120px;" class="loading"></div>');
			
			$AdData.getData({
				"placeId":opts.placeId,
				"placeSize":opts.placeSize,
				"placeWidth":opts.placeWidth,
				"placeHeight":opts.placeHeight,
				"status" : opts.status,
				"callback":function(ads){
					$homepage_ad_middle.doData(el,ads,opts);
					$homepage_ad_middle.adClose(el);
				}
			});
		},
		"doAdList":function(_obj,dataList,opts){
			_obj.empty();
			imgstyle = "width:"+ opts.placeWidth +"px;height:"+opts.placeHeight +"px;";
			var str = "";
			/*$.each(dataList, function(i, n) {
				if( i+1 ==  dataList.length){
					str += "<li class = 'last-list'><a href='"+n.linkUrl+"'><img style = \""+imgstyle+"\" src='"+n.vfsUrl+"' alt='"+n.advertiseTitle+"'/></a></li>";
				}else{
					str += "<li ><a href='"+n.linkUrl+"'><img style = \""+imgstyle+"\" src='"+n.vfsUrl+"' alt='"+n.advertiseTitle+"'/></a></li>";
				}
			});*/
			for(var i = 0;i < dataList.length;i++){//循环广告
				if( i ==  opts.placeSize - 1){
					str += "<li class = 'last-list'><a target='_blank' href='"+dataList[i].linkUrl+"'><img style = \""+imgstyle+"\" src='"+dataList[i].vfsUrl+"' alt='"+dataList[i].advertiseTitle+"'/></a></li>";
				}else{
					str += "<li ><a target='_blank' href='"+dataList[i].linkUrl+"'><img style = \""+imgstyle+"\" src='"+dataList[i].vfsUrl+"' alt='"+dataList[i].advertiseTitle+"'/></a></li>";
				}
			}
			for(;i < opts.placeSize;i++){//给缺失广告加提示
				if( i ==  opts.placeSize - 1){
					str += "<li style = \""+imgstyle+"\" class = 'last-list'><div  class ='pro-empty'>黄金广告位，征集中。。。</div></li>";
				}else{
					str += "<li style = \""+imgstyle+"\"><div class ='pro-empty'>黄金广告位，征集中。。。</div></li>";
				}
			}
			_obj.append(str);
			_obj = null;
		},
		"adClose":function(el){
			$(".ad-close",el).live("click",function(){
				$(el).addClass("hide");
			});
		}
	};
	$.fn.homepage_ad_middle = function() {
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
			$homepage_ad_middle.control(this,opts);
			
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
