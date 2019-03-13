;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define(["ecp-component/wap/cms-url-tool","ecp-component/wap/iScrollUtil"], function(urlTool,iScrollutil) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".modular-wap-guess").modular_wap_guess();
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
	};
	var _gdsHander = (function(){//商品处理器
			var isPub = false;
			var webRoot = $webroot||"";
			var isPc = false;
			
			var gdsHander = {
				"setIsPub":function(p){
					isPub = p?true:false;
				},
				"getIsPub":function(){
					return isPub;
				},
				"setWebRoot":function(p){
					webRoot	= p ||$webroot||'';		
				},
				"setIsPc":function(p){
					isPc = p?true:false;
				},
				"doHref":function($gdsEl){
					if(isPub && $gdsEl && $($gdsEl).length > 0){
						var $linkList = $("a[lazy-href]",$($gdsEl));
						$linkList.each(function(){
							var href = $(this).attr("lazy-href");
							$(this).removeAttr("lazy-href");
							if(href){
								$(this).attr("href",_eCmsUrlTool.getHtmlAbsUrl(webRoot,href));
							}
						});
					}
				},
				"bindCollect":function($gdsEl){
					if(!isPc && isPub && $gdsEl && $($gdsEl).length > 0){
						$(".do-collect",$gdsEl).click(function(){
							var $this = $(this);
							if(!$this.hasClass("do-collect")){
								return $this;
							}
							$this.removeClass("do-collect");//避免多次收藏
							_gdsHander.doCollect($this.data("skuId"),$this.data("collectId"),function(returnInfo){
								//成功回掉函数
								if (returnInfo.resultFlag == 'ok') {
									$this.data("collectId",returnInfo.resultMsg);
									if(returnInfo.resultMsg==""){
										$this.removeClass("ticon-hfav").addClass('ticon-fav');
										if(AmLoad){
											new AmLoad({content:'取消收藏商品成功！'});
					    				}else{
					    					eDialog.alert('取消收藏商品成功！');
					    				}
									}else{
										$this.removeClass("ticon-fav").addClass('ticon-hfav');
										if(AmLoad){
											new AmLoad({content:'收藏商品成功！'});
					    				}else{
					    					eDialog.alert('收藏商品成功！');
					    				}
									}
								} else {
									if(AmLoad){
										new AmLoad({content:returnInfo.resultMsg,type:'2'});
				    				}else{
				    					eDialog.alert(returnInfo.resultMsg);
				    				}
								}
								$this.addClass("do-collect");
							},function(msg){//错误回调函数
								$this.addClass("do-collect");
								if(AmLoad){
									new AmLoad({content:msg,type:'2'});
			    				}else{
			    					eDialog.alert(msg);
			    				}
							});
						});
					}
				},
				"doCollect":function(skuId,collectId,callback,errCallback){
					skuId = +skuId;
					collectId = +collectId;
					if(!skuId){
						if(errCallback && $.isFunction(errCallback)){
							errCallback("商品数据错误！");
						}
						return this;
					}
					$.eAjax({
						url : GLOBAL.WEBROOT + "/addCollection/add",
						data : {
							"skuId" : skuId,
							"collectId" : collectId || ""
						},
						success : function(returnInfo) {
							if(callback && $.isFunction(callback)){
								callback(returnInfo);
							}
						},
						error:function(){
							if(errCallback && $.isFunction(errCallback)){
								errCallback("请求错误！");
							}
						},
						exception:function(){
							if(errCallback && $.isFunction(errCallback)){
								errCallback("发生异常！");
							}
						}
					});
				}
			}
			
			return gdsHander;
	})();
	$modularWapGuess = {
		"pageNumber":1,
		"pageGoing":function(){
			$modularWapGuess.pageNumber += 1;
		},
		"iScroll":null,
		/**
		 * 获取猜你喜欢行为分析数据
		 */
		"getData" : function(el,opts){
			$modularWapGuess.pageGoing();
			$.eAjax({
				url : $webroot + '/cmshomepagegetdata/qryguessforwap',
				data : {
					"siteId":opts.siteId,
					"pageNumber":opts.pageNumber,
					"pageSize" : opts.pageSize,
					"placeHeight":opts.placeHeight,
					"placeWidth" : opts.placeWidth,
				},
				async : true,
				type : "post",
				dataType : "html",
				success : function(data, textStatus) {
					if(opts.callback && $.isFunction(opts.callback)){
						opts.callback(data);
					}
				},
				error :  function(){
					opts.callback(null);
				}
			});
		},
		"shutDown":function(el){
			if(el && $(el).length > 0){
				$(el).remove();
			}
		},
		"noServer":function(el){
			$(el).html('<div class="nodata" style="height:90px;text-align:center;padding-top:30px;">'+
					'<span style="color:red;">暂不支持该服务！</span></div>');
			$(el).removeClass("hide");
			$(el).show();
		},
		"startUp":function(el){
			if(el && $(el).length > 0){
				$(".guess-content",$(el)).empty();
				$(el).removeClass("hide");
				$(el).show();
			}
		},
		"control":function(el,opts){
			_gdsHander.setIsPub(opts.isPub);//设置商品处理器的模式
			_gdsHander.setWebRoot(opts.webRoot);//设置商品处理器的根地址
			_gdsHander.setIsPc(opts.isPc);//设置商品处理器为pc模式
			
			$modularWapGuess.getData(el,{
				"siteId":opts.siteId,
				"pageNumber":$modularWapGuess.pageNumber||1,
				"pageSize" : opts.pageSize,
				"placeHeight":opts.placeHeight,
				"placeWidth" : opts.placeWidth,
				"callback":function(gdsHtml){
					if(gdsHtml && gdsHtml.replace(/^\s+/ig, "")){
						$modularWapGuess.startUp(el);
						$modularWapGuess.doData(el,gdsHtml,opts);
						if(opts.isPub){
							$modularWapGuess.initScroll(el,opts);
						}
					}else{//第一页如果没有数据，则不显示猜你喜欢
						if(opts.isPub){
							$modularWapGuess.shutDown(el);
						}else{
							$modularWapGuess.noServer(el);
						}
					}
				}
			});
		},
		"doData":function(el,gdsHtml,opts){
			var $gdsDatas = $("<div>"+gdsHtml+"</div>");
			var $lis = $("li",$gdsDatas);
			$lis.each(function(){
				_gdsHander.doHref($(this));
				_gdsHander.bindCollect($(this));
			});
			$(".guess-content",$(el)).append($lis);
			//刷新滚动条
			$modularWapGuess.freshScrollbar();
		},
		"initScroll":function(el,opts){
			var $main = $('#page-content');
			$main.height($(window).height()-$('.am-header').height());
			$modularWapGuess.iScroll = new LoadScroll("page-content", {
	            url: $webroot + '/cmshomepagegetdata/qryguessforwap',
	            type:"get",
	            dataType: 'html',
	            isDownRefresh:false,//不启用下拉刷新
	            params:{
	            	"siteId":opts.siteId,
    				"pageNumber":$modularWapGuess.pageNumber||1,
    				"pageSize" : opts.pageSize,
    				"placeHeight":opts.placeHeight,
    				"placeWidth" : opts.placeWidth,
	            },
	            upBeforeCall:function(){
	            	//设置下一页
	            	$modularWapGuess.iScroll.refreshParams({"pageNumber":$modularWapGuess.pageNumber||1});
	            },
	            callback:function(els,gdsHtml){
	            	if(gdsHtml && gdsHtml.replace(/^\s+/ig, "")){
						$modularWapGuess.doData(el,gdsHtml,opts);
					}else{
						$modularWapGuess.iScroll.setCount(-1);
					}
	            	$modularWapGuess.pageGoing();
	            }
	        });
			
			//绑定ajax完成刷新下拉
			$main.ajaxStop(function(){
				$modularWapGuess.iScroll && ($modularWapGuess.iScroll.refresh());
			});
		},
		"freshScrollbar":function(){
			//不是发布页 刷新滚动条
			var $pageDecorate = $("#pageDecorate");
			if(!_gdsHander.getIsPub() && $.fn.mCustomScrollbar && $pageDecorate && 0 < $pageDecorate.length){
				if($("#pageDecorate").hasClass('mCustomScrollbar')){
					$pageDecorate.mCustomScrollbar('update');
				}else{
					$pageDecorate.mCustomScrollbar({
						scrollInertia: 150,
						advanced: {
							autoScrollOnFocus: false
						}
					});
				}
			}
		}
	};
	$.fn.modular_wap_guess = function() {//入口
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){
			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			var opts = $.ecpPlugin.parseOptions(this, defaultOpts);
			$modularWapGuess.control(this,opts);
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
