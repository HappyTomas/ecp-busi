;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([], function() {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".modular-mall-new-gds").modular_mall_new_gds();
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

	var $modularNewGds = {
			/**
			 * 获取 楼层下的信息，如，页签、商品……；如果有符合要求的信息，则调用数据处理的回调函数(callback);否则直接返回；
			 * 入参：
			 * placeId : 内容位置Id；
			 * gdsSize : 位置展示商品个数
			 * tabSize : 页签个数
			 * placeWidth : 图片宽度
			 * placeHeight" : 图片长度
			 * status : 状态
			 * callback : 回调函数，对于数据处理的回调函数；
			 * @author gxq
			 */
			getData : function(opts){
				$.eAjax({
					url : $webroot + '/cmshomepagegetdata/qryFloorVM',
					data : {
						"placeId" : opts.placeId,
						"tabSize" : opts.tabSize,
						"status" : opts.status,
						"returnUrl":"mallhomepage/new-gds-show-template"
					},
					async : true,
					type : "post",
					dataType : "html",
					success : function(data, textStatus) {
						if(opts.callback && $.isFunction(opts.callback)){
							opts.callback(data);
						}
					}
				});
			},
				
			/**
			 * 调用获取数据组件
			 */
			"control":function(el,opts){
				//加载动态图
				$(".modular-body", el).append("<div class='tpl-loading'></div>");
				$modularNewGds.getData({
					"placeId" : opts.placeId,
					"tabSize" : opts.tabSize,
					"status" : opts.status,
					"callback":function(datas){
						if(datas){
							//返回楼层VM，填充楼层信息（页签、页签对应的DIV）
							$(".modular-body",$(el)).empty();
							$(".modular-body",$(el)).html(datas);
							
							$(".modular-body",$(el)).attr("data-img-width",$modularNewGds.getImgWidth(el,opts.showWay));
							
							//获取楼层商品
							var $firstTab = $(".tab-nav a",el).eq(0);
							
							var floorId = $(".tab-content",el).attr("floor-id");
							
							if($firstTab.length > 0){
								$modularNewGds.bindTabChange(el,floorId);
							}
							
							$modularNewGds.queryFloorInfo(el,floorId, $firstTab);
						}else{
							$(".modular-body", el).empty().html("<div class='pro-empty'>楼层数据获取错误！</div>");
						}
					}
				});
			},
			"queryFloorInfo":function(el,floorId,$firstTab){
				if(floorId == undefined){//如果没有楼层id  则表示错误数据 
					$("#modular-body", el).empty().html("楼层ID获取错误！");
					return this;
				}
				if(!$firstTab || $firstTab.length <= 0){
					$firstTab = null;
				}
				
				//--tab处理--start//
				var tabId = null;
				var isLoad = null;//是否加载，yes:已加载，no：未加载 
				if($firstTab){
					 tabId = $firstTab.attr("tab-id");
					 isLoad = $firstTab.attr("is-load");//是否加载，yes:已加载，no：未加载 
					 $($firstTab).attr("is-load","yes");
					 
					 $(".nItem", el).hide();
					 $("#floor_gds_" + tabId,el).show();
				}
				//--tab处理--end//
				
				var opts = $(el).data();
				opts.tabId = tabId;
				opts.floorId = floorId;
				if(!opts.showWay){
					opts.showWay = 4;
				}

				//获取图片规格
				var imgWidth = $(".modular-body",$(el)).attr("data-img-width");
				
				if(!$firstTab || isLoad == "no"){//$firstTab为空  无tab查找楼层商品  否则  isLoad == "no"该也签未获取数据查页签商品
					$($firstTab).attr("isLoad","yes");
					var $contenter = null; 
					if($firstTab){
						$contenter =$("#floor_gds_"+tabId, el);
					}else{
						$contenter = $(".tab-content", el);
					}
					$contenter.empty().html("<div class='tpl-loading'></div>");
					$.eAjax({
						url : $webroot + '/cmshomepagegetdata/queryFloorInfoVM',
						data : {
							"tabId" : opts.tabId,
							"floorId" : opts.floorId,
							"gdsSize" : opts.gdsSize,
							"placeWidth" : parseInt(imgWidth)||300,
							"placeHeight" : parseInt(imgWidth)||300,
							"returnUrl":"/modular/loading/list/mallhomepage/new-gds-show-list-template",
							"status" : opts.status
						},
						async : true,
						type : "post",
						dataType : "html",
						success : function(datas, textStatus) {
							var $gdsData = $(datas);
							$gdsData.addClass("template-"+opts.showWay);
							if(imgWidth){
								$gdsData.find("img").css({"width":imgWidth+"px","height":imgWidth+"px"});
							}
							
							$contenter.empty().html($gdsData);
						},
						error:function(){
							$contenter.empty().html("<div class='pro-empty'>数据错误！</div>");
						}
					});
				}
			},
			"bindTabChange":function(el,floorId){
				//绑定tab页切换
				$(".tab-nav li",el).live("mouseover",function(e){
					if($(this).hasClass("active")){
						return $(this);
					}
					//获取楼层商品数据
					$(".tab-nav li",el).removeClass("active");
					$(this).addClass("active");
					$modularNewGds.queryFloorInfo(el,floorId,$(this).find("a").eq(0));
				});
			},
			"getImgWidth":function(el,showWay){
				showWay = +showWay;
				if(!showWay || showWay <= 0){
					showWay = 4;
				}
				
				var tWidth=$(el).width();
				if(!tWidth){
					return 0;
				}
				
				var pWidth = (showWay-1)*0.005*tWidth + showWay * 20;
				if(pWidth >= tWidth){
					return 0;
				}
				
				return (tWidth - pWidth) / showWay;
			}
	};

	$.fn.modular_mall_new_gds = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){

			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			var opts = $.ecpPlugin.parseOptions(this, defaultOpts);
			if(opts == null){
				opts = {} ;
			}
			$modularNewGds.control(this,opts);
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
