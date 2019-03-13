;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define(['ecp-component/do-data' ], function(gdsData) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".homepage-floorvm").homepage_floorvm();
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

	$homepage_floorvm = {
		/**
		 * 调用获取数据组件
		 */
		"control":function(el,opts){
			//加载动态图
			$(".floorvm-content", el).append("<div class='loading-small'></div>");
			$floorVMData.getData({
				"adPlaceId" : opts.adPlaceId,
				"placeId" : opts.placeId,
				"gdsSize" : opts.gdsSize,
				"tabSize" : opts.tabSize,
				"placeWidth" : opts.placeWidth,
				"placeHeight" : opts.placeHeight,
				"status" : opts.status,
				"callback":function(datas){
					//返回楼层VM，填充楼层信息（页签、页签对应的DIV）
					$(el).empty();
					$(el).html(datas);
					//返回楼层VM，填充楼层信息（广告、标签、商品）
					var $floorTabUL = $(".new-tit-list a",el);//页签
					
					var floorId = $(".floor-modular",el).eq(0).attr("data-floor");
					
					if(floorId){//有楼层数据
						if($floorTabUL && $floorTabUL.length > 0){//有页签则查询第一个页签
							$homepage_floorvm.queryFloorInfo(el,floorId, $floorTabUL.eq(0));
							$homepage_floorvm.bindTabChange(el,floorId,$floorTabUL);
						}else{
							$homepage_floorvm.queryFloorInfo(el,floorId, null);
						}
					}
				}
			});
		},
		"queryFloorInfo":function(el,floorId,$floorTab){
			if(floorId == undefined && (!$floorTab || $floorTab.length <= 0) ){//如果没有楼层id  则表示错误数据 
				$("#digitc", el).empty().html("楼层数据获取错误！");
				return this;
			}
			
			//判断是否有页签  有的话做相应处理
			var tabId = null;
			var isLoad = null;//是否加载，yes:已加载，no：未加载 
			if($floorTab && $floorTab.length > 0){
				tabId = $floorTab.attr("id");
				isLoad = $floorTab.attr("isLoad");//是否加载，yes:已加载，no：未加载 
				$($floorTab).attr("isLoad","yes");
				$floorTab = null;
				$(".floor-div-gds", el).hide();//隐藏所有页签
				$("#floorGdsDIV_" + tabId,el).show();
			}
			
			var opts = $(el).data();
			opts.tabId = tabId || "";
			opts.floorId = floorId || "";
			
			if(!tabId || isLoad == "no"){
				$.eAjax({
					url : $webroot + '/homepage/queryFloorInfoVM',
					data : {
						"tabId" : opts.tabId,
						"floorId" : opts.floorId,
						"placeId" : opts.placeId,
						"gdsSize" : opts.gdsSize,
						"tabSize" : opts.tabSize,
						"placeWidth" : opts.placeWidth,
						"placeHeight" : opts.placeHeight,
						"status" : opts.status
					},
					async : true,
					type : "post",
					dataType : "html",
					success : function(datas, textStatus) {
						if(tabId){
							$("#floorGdsDIV_"+tabId, el).empty().html(datas);
						}else{
							$("#digitc", el).empty().html(datas);
						}
					}
				});
			}
		},
		"bindTabChange":function(el,floorId,$floorTabUL){
			
			//绑定tab页切换
			$floorTabUL.live("mouseover",function(e){
				//获取楼层商品数据
				if(!($(this).hasClass("active"))){
					$floorTabUL.removeClass("active");
					$(this).addClass("active");
					$homepage_floorvm.queryFloorInfo(el,floorId,$(this));
				}
			});
		}
	};
	$.fn.homepage_floorvm = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){

			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			
			var opts = $.ecpPlugin.parseOptions(this, defaultOpts);
			if(opts == null || opts.placeId =="" || opts.placeId=="undefined"){
				return ;
			}
			$homepage_floorvm.control(this,opts);
			
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
