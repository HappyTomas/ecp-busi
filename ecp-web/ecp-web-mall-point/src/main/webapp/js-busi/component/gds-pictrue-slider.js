;
(function(factory) {
	
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define(['jquery-icheck',
		        'jquery-jqzoom','jquery-powerSwitch','ecp-component/gds-pictrue-data' ], function(powerSwitch,icheck,jqzoom,gdsPictrue) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".gdsPictrueSlide").gdsPictrue();
				}
			};
		});
	} else {
		// 全局模式 ，不使用AMD 规范的时候，使用的插件
		factory(jQuery);
	}
}(function($) {
	var defaultOpts = {
	};
	// / 具体插件的定义；
	$gdsPictrue = {
		/**
		 * 生产商品与放大效果
		 * @param el
		 * @param datas
		 */
			"getData" : function(el, datas) {
				var $container = $("#gdsPictrueSlide", el);
				$container.empty();
				var htmlContext = "";
				var pictrueNum = datas.pictrueNum;
				var pictrueHeight = datas.pictrueHeight;
				var pictrueWidth = datas.pictrueWidth;
				var pictrueMoreHeight = datas.pictrueMoreHeight;
				var pictrueMoreWidth = datas.pictrueMoreWidth;
				if(datas.result.length>=1){
					$.each(datas.result, function(i, n) {
						if(i==0){
							htmlContext += "<div class='pre-img'>"+
				                "<a href='"+n.bigUrl+"' rel='gal1' class='jqzoom'>"+
				                    "<img class='pcimg' height='"+pictrueMoreHeight/2+"' width='"+pictrueMoreWidth/2+"'  src='"+n.middleUrl+"' alt=''/>"+
				                "</a>"+
				            "</div>"+
							"<div class='pre-list'>"+
			                "<div class='slide jcarousel'>"+
			                    "<div id='zoomimgn' class='slide-trigger'>"+
			                        "<a href='javascript:' class='slide-prev' data-rel='zimg1'></a>"+
			                        "<a href='javascript:' class='slide-next' data-rel='zimg1'></a>"+
			                    "</div>"+
			                    "<div class='slide-box'>"+
			                        "<ul id='zoomimgc' class='jcarousel-container'>";
						}
						htmlContext +="<li class='zimg1'>";
						if(i==0){
							htmlContext +="<a href='javascript:;' class='it zoomThumbActive'";
						}else{
							htmlContext +="<a href='javascript:;' class='it'";
						}
						htmlContext +="rel=\"{gallery: 'gal1', smallimage: '"+n.middleUrl+"',largeimage: '"+n.bigUrl+"'}\">";
						htmlContext +=    "<img m='"+n.middleUrl+"' b='"+n.bigUrl+"' width='"+pictrueHeight+"' height='"+pictrueWidth+"' src='"+n.url+"' alt='Image 1'>"+
		                                "</a>"+
		                            "</li>";
					});
					htmlContext +="</ul>"+
				             "</div>"+
				         "</div>"+
				     "</div>";
					$container.html(htmlContext);
					$("#zoomimgn a").powerSwitch({
		                number: pictrueNum,
		                container: $("#zoomimgc")

		            });
//					$("#zoomimgc a").each(function(){
//						$(this).unbind('dbclick');
//						$(this).live('click',function(){
//							var b = $(this).find("img").attr('b');
//							var m = $(this).find("img").attr('m');
//							$(".jqzoom").attr('href',b);
//							$(".pcimg").attr('src',m);
//							$(this).addClass("zoomThumbActive");
//							$(this).parent().siblings().each(function(){
//								$(this).find("a").removeClass("zoomThumbActive");
//							});
//						});
//					});
				}else{
					htmlContext +=
					"<div class='pre-img'>"+
		                "<a href='"+datas.bigUrl+"' rel='gal1' class='jqzoom'>"+
		                    "<img class='pcimg' height='"+pictrueMoreHeight/2+"' width='"+pictrueMoreWidth/2+"' src='"+datas.middleUrl+"' alt=''/>"+
		                "</a>"+
		            "</div>";
					$container.html(htmlContext);
				}
				$('.jqzoom').jqzoom({
			         preloadImages: false,
			         title: false,
			         zoomWidth: pictrueMoreWidth/2,
			         zoomHeight: pictrueMoreHeight/2,
			         showPreload : false,
			         callback:function(){
			          // $('.pre-list .it').eq(0).click();
			         }
			     });
				
			},
		"control":function(el,opts){
			$gdsPictrueData.getData({
				"pictrueNum" : opts.pictrueNum,
				"pictrueHeight" : opts.pictrueHeight,
				"pictrueWidth" : opts.pictrueWidth,
				"pictrueMoreHeight" : opts.pictrueMoreHeight,
				"pictrueMoreWidth" : opts.pictrueMoreWidth,
				"gdsId" : opts.gdsId,
				"skuId" : opts.skuId,
				"callback":function(ads){
					$gdsPictrue.getData(el,ads);
				}
			});
		},
		parseOptions:function(el,defaultOpts,opts){
			var htmlOpts = $(el).data();
			var tmpOpts;
			if(defaultOpts){
				tmpOpts = $.extend({}, defaultOpts, htmlOpts);
			} else {
				tmpOpts = $.extend({}, htmlOpts);
			}
			
			if(opts){
				return $.extend({},tmpOpts,opts);
			} else {
				return tmpOpts;
			}
			
		}
	};

	$.fn.gdsPictrue = function() {
		return this.each(function() {
			var opts = $gdsPictrue.parseOptions(this, defaultOpts);
			if(opts == null){
				return ;
			}
			$gdsPictrue.control(this,opts);
			return $(this);
		});
	};
}));
