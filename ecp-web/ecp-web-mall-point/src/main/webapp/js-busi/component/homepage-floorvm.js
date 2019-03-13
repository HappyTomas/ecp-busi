;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([ 'jquery-powerSwitch','ecp-component/floor-datavm' ], function(slide,adData) {
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
			/*var $container = $(".homepage-floor-gds", el);
		    $container.empty();
			$homepage_floorvm.loadContanier($container,-1);*/
			//加加载动态图
			$(".floorvm-content", el).append("<div class='loading-small'></div>");
			$floorVMData.getData({
				"menuType" :opts.menuType,
				"placeId" : opts.placeId,
				"gdsSize" : opts.gdsSize,
				"tabSize" : opts.tabSize,
				"placeWidth" : opts.placeWidth,
				"placeHeight" : opts.placeHeight,
				"status" : opts.status,
				"callback":function(datas){
					//alert(datas);
					//返回楼层VM，填充楼层信息（页签、页签对应的DIV）
					$(el).empty();
					$(el).html(datas);
					//返回楼层VM，填充楼层信息（广告、标签、商品）
					var $floorTabUL = $(".new-tit-list a",el).eq(0);
					$homepage_floorvm.queryFloorInfo(el,$floorTabUL);
					$homepage_floorvm.bindTabChange(el);
				}
			});
		},
		"queryFloorInfo":function(el,$floorTabUL){
			var tabId = $floorTabUL.attr("id");
			var floorId = $floorTabUL.attr("floorId");
			var isLoad = $floorTabUL.attr("isLoad");//是否加载，yes:已加载，no：未加载 
			var opts = $(el).data();
			opts.tabId = tabId;
			opts.floorId = floorId;
			//alert(tabId); 
			
			$(".floor-div-gds",el).hide();
			$("#floorGdsDIV_"+tabId).show();
			
			if(isLoad == "no"){
				$($floorTabUL).attr("isLoad","yes");
				$.eAjax({
					url : $webroot + '/homepage/queryFloorInfoVM',
					data : {
						"menuType" :opts.menuType,
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
						$("#floorGdsDIV_"+tabId,el).empty();
						$("#floorGdsDIV_"+tabId,el).html(datas);
					}
				});
			}
		},
		"bindTabChange":function(el){
	        /*组合*/
	        /*U.tab('#digitn','#digitc',{
	            event:'mouseover'
	        });*/
			
			//绑定tab页切换
			$(".new-tit-list a",el).live("mouseover",function(e){
				//获取楼层商品数据
				$(".new-tit-list a",el).removeClass("active");
				$(this).addClass("active");
				$homepage_floorvm.queryFloorInfo(el,$(this));
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
