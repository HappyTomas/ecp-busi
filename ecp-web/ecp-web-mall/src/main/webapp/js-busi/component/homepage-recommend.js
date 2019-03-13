;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([ 'jquery-powerSwitch','ecp-component/do-data' ], function(slide,adData) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".homepage-recommend").homepage_recommend();
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

	$homepage_recommend = {
		/**
		 * 调用获取数据组件
		 */
		"control":function(el,opts){
			$(".recommend-content", el).append("<div class='loading-small'></div>");
			$recommendData.getData({
				"placeWidth" : opts.placeWidth,
				"placeHeight" : opts.placeHeight,
				"status" : opts.status,
				"callback":function(datas){
					//返回楼层VM，填充楼层信息（页签、页签对应的DIV）
					$(el).empty();
					$(el).html(datas);
					//返回楼层VM，填充楼层信息（广告、标签、商品）
					var $floorTabUL = $(".new-tit a",el).eq(0);
					if($floorTabUL){//取第一条数据
						$homepage_recommend.queryRecommendInfo(el,$floorTabUL);
						$homepage_recommend.bindTabChange(el);
					}
				}
			});
		},
		"queryRecommendInfo":function(el,$floorTabUL){
			var recommendType = $floorTabUL.attr("id");
			$(".recommend-div",el).hide();
			$("#recommendDIV_"+recommendType).show();
			var isLoad = $floorTabUL.attr("isLoad");//是否加载，yes:已加载，no：未加载 
			var opts = $(el).data();
			opts.recommendType = recommendType;
			
			if(isLoad == "no"){
				$($floorTabUL).attr("isLoad","yes");
				$.eAjax({
					url : $webroot + '/homepage/queryRecommendInfo',
					data : {
						"recommendType" : opts.recommendType,
						"placeWidth" : opts.placeWidth,
						"placeHeight" : opts.placeHeight,
						"status" : opts.status
					},
					async : true,
					type : "post",
					dataType : "html",
					success : function(datas, textStatus) {
						$("#recommendDIV_"+recommendType).empty();
						$("#recommendDIV_"+recommendType).html(datas);
					}
				});
			}
		},
		"bindTabChange":function(el){
			//绑定tab页切换
			$(".new-tit a",el).live("mouseover",function(e){
				//获取楼层商品数据
				$(".new-tit a",el).removeClass("active");
				$(this).addClass("active");
				$homepage_recommend.queryRecommendInfo(el,$(this));
			});
		}
	};
	$.fn.homepage_recommend = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){

			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			
			var opts = $.ecpPlugin.parseOptions(this, defaultOpts);
			/*if(opts == null || opts.placeId =="" || opts.placeId=="undefined"){
				return ;
			}*/
			$homepage_recommend.control(this,opts);
			
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
