;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([], function() {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".modular-second-gdsrecommend").modular_second_gdsrecommend();
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

	var $modularGdsRecom = {
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
						"returnUrl":"secondpage/second-gdsrecommend-template"
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
				$modularGdsRecom.getData({
					"placeId" : opts.placeId,
					"callback":function(datas){
						if(datas){
							//返回楼层VM，填充楼层信息（页签、页签对应的DIV）
							$(".modular-body",$(el)).empty();
							$(".modular-body",$(el)).html(datas);
							
							//获取楼层商品
							var floorId = $(".recommend-data-body",el).attr("floor-id");
							
							$modularGdsRecom.queryFloorInfo(el,floorId);
						}else{
							$(".modular-body", el).empty().html("<div class='pro-empty'>楼层数据获取错误！</div>");
						}
					}
				});
			},
			"queryFloorInfo":function(el,floorId){
				if(floorId == undefined){//如果没有楼层id  则表示错误数据 
					$("#modular-body", el).empty().html("楼层ID获取错误！");
					return this;
				}
				
				var opts = $(el).data();
				opts.floorId = floorId;
				
				var $contenter =$(".recommend-data-body", el);
				
				$contenter.empty().html("<div class='tpl-loading'></div>");
				$.eAjax({
					url : $webroot + '/cmshomepagegetdata/queryFloorInfoVM',
					data : {
						"floorId" : opts.floorId,
						"gdsSize" : opts.gdsSize || 1,
						"placeWidth" : 170,
						"placeHeight" : 170,
						"returnUrl":"/modular/loading/list/secondpage/second-gdsrecommend-list-template"
					},
					async : true,
					type : "post",
					dataType : "html",
					success : function(datas, textStatus) {
						$contenter.empty().html(datas);
					},
					error:function(){
						$contenter.empty().html("<div class='pro-empty'>数据错误！</div>");
					}
				});
			}
	};

	$.fn.modular_second_gdsrecommend = function() {
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
			$modularGdsRecom.control(this,opts);
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
