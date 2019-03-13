;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([ 'jquery-powerSwitch'], function(slide,adData) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".wechat-mall-gdsrankinglist").gds_rankinglist();
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

	var $gdsRankingList = {
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
		 * @author zhanbh
		 */
		getData : function(el,opts){
			$.eAjax({
				url : $webroot + '/ranking/getRankVM',
				data : {
					"placeId" : opts.placeId,
					"tabSize" : opts.tabSize,
					"status" : opts.status,
				},
				async : true,
				type : "get",
				dataType : "html",
				success : function(data, textStatus) {
					if(opts.callback && $.isFunction(opts.callback)){
						opts.callback(data);
					}
				},
				error:function(data){
					$gdsRankingList.noDataShow("请求错误，请检查网络",$(el));
				}
			});
		},
		/**
		 * 调用获取数据组件
		 */
		"control":function(el,opts){
			//加载动态图
			$(".modular-body", el).append("<div class='tpl-loading'></div>");
			if(!opts || !opts.placeId){
				$gdsRankingList.noDataShow("内容位置未配置！",$(el));
				return false;
			}
			$gdsRankingList.getData(el,{
				"placeId" : opts.placeId,
				"tabSize" : opts.tabSize,
				"placeWidth" : opts.placeWidth,
				"placeHeight" : opts.placeHeight,
				"status" : opts.status,
				"callback":function(datas){
					if(!datas){
						$gdsRankingList.noDataShow("亲，商品数据丢失！",$(el));
					}
					$(el).html(datas);
					var $floorTabs = $(".floor-tabs a",el);
					var $firstTab = null;
					$.extend(opts,$(".floor-infos",el).data());
					if($floorTabs.length > 0){
						$gdsRankingList.bindTabChange(el,opts);
						$gdsRankingList.bindTabSlider(el);
						$firstTab = $floorTabs.eq(0);
					}
					//获取第一个页签 或者楼层下商品
					if(opts.floorId){
						$gdsRankingList.queryFloorInfo(el,opts, $firstTab);
					}
				}
			});
		},
		/**
		 * 查楼层下商品，如果有页签 查页签下商品
		 */
		"queryFloorInfo":function(el,opts,$floorTab){
			if($floorTab && $floorTab.length <= 0){
				$floorTab = null;
			}
			
			var tabId = null;
			var isLoad = null;//是否加载，yes:已加载，no：未加载
			var catgCode = opts.catgCode || "";
			if($floorTab){
				 tabId = $floorTab.data("id")||"";
				 catgCode = $floorTab.data("catgCode")||"";
				 isLoad = $floorTab.attr("isLoad");//是否加载，yes:已加载，no：未加载 
			}
			if(!$floorTab || isLoad == "no"){
				$floorTab && $($floorTab).attr("isLoad","yes");
				$.eAjax({
					url : $webroot + '/ranking/getRankGdsVM',
					data : {
						"tabId" : tabId,
						"floorId" : opts.floorId,
						"dataSource" : opts.dataSource,
						"countType" : opts.countType,
						"catgCode" : catgCode,
						"gdsSize" : opts.gdsSize,
						"placeWidth" : opts.placeWidth,
						"placeHeight" : opts.placeHeight,
						"status" : opts.status,
					},
					async : true,
					type : "get",
					dataType : "html",
					success : function(datas, textStatus) {
						if($floorTab && tabId){
							$("#floor-tab-"+tabId, el).empty().html(datas);
							//刷新下拉滚动
							$gdsRankingList.fleshScroll($("#floor-tab-"+tabId, el));
						}else{
							$(".floor-body", el).empty().html(datas);
							//刷新下拉滚动
							$gdsRankingList.fleshScroll($(".floor-body", el));
						}
					},
					error:function(){
						var $content = null;
						if($floorTab && tabId){
							$content = $("#floor-tab-"+tabId, el);
						}else{
							$content = $(".floor-body", el);
						}
						$gdsRankingList.noDataShow("网络错误",$content);
					}
				});
			}
		},
		"calHeight":function (id,winShowHi) {//id：使用scroll对应的htmlid ， winShowHi：显示窗口的高度
			if(id && winShowHi){
				var aHi = 0;
		        var $list = $('.scrollCont',"#"+id);
		        $list.children().each(function () {
		            aHi += $(this).outerHeight();
		        });
		        if (aHi <= winShowHi) {
		            $list.height(winShowHi);
		        } else {
		            $list.height("auto");
		        }
			}
	    },
	    "fleshScroll":function($context){
	    	var idStr = $(".scroll-wrap",$context).attr("id");
	    	if(idStr){
	    		var winShowHi = $(window).height()
                - $('.am-header').height()
                - $('.am-tabs-nav').height()
	    		- $('.am-navbar-nav').height();
	    		
	    		$('#'+idStr).height(winShowHi);
	    		
	    		//判断高度差
	    		$gdsRankingList.calHeight(idStr,winShowHi);
	    		
	            var loadScroll = new LoadScroll(idStr, {
	                downCallback: function () {
	                }
	            });
	    	}
	    },
		"bindTabChange":function(el,opts){
			//绑定tab页切换
			$(".floor-tabs li",el).on("click.rank",function(e){
				var $this = $(this);
				if($this.hasClass("am-active")){
					return false;
				}
				$this.siblings(".am-active").removeClass("am-active");
				$this.addClass("am-active");
				var $a = $("a",$this);
				var tabId = $a.data("id");
				var $tabBd = $("#floor-tab-"+tabId,el);
				$tabBd.siblings(".am-active").removeClass("am-active");
				$tabBd.addClass("am-active");
				$gdsRankingList.queryFloorInfo(el,opts,$a);
			});
		},
		"bindTabSlider":function(el){
			var $el = $(el);
			if(null == $el || !$.AMUI || !$.isFunction($.AMUI.iScroll)){ 
				return false;
			}
			var $tabScroll =$(".scroll-wrap",$el);
			var tabHeight = $tabScroll.innerHeight();
			if(tabHeight && 60 < tabHeight){//超过一行  启用滑动功能
				var w = 0;
	            $('li',$tabScroll).each(function () {
	                w = w + $(this).outerWidth();
	            });
	            $('ul',$tabScroll).width(w + 10);
	            var $scrollObj = $tabScroll[0];
	            new $.AMUI.iScroll($scrollObj, {
	                scrollX: true,
	                scrollY: false,
	                preventDefault: false
	            });
			}else{//不超过一行  使其左右顶格居中
				$tabScroll.closest('.tab-silde').removeClass("tab-silde");
				$(".floor-tabs",$tabScroll).removeClass("scroll");
			}
			return true;
		},
		"noDataShow":function(text,$content){
			if(!$content || 0 >= $content.length){ 
				return false;
			}
			text = text || '无数据';
			$content.html("<div class='pro-empty'>"+text+"</div>");
		}
	};
	$.fn.gds_rankinglist = function() {
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
			$gdsRankingList.control(this,opts);
			
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
