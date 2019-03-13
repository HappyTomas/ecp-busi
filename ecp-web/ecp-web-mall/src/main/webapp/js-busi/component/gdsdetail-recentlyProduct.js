;
(function(factory) {
	
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define(['ecp-component/gds-recentlyProduct' ], function(gdsRecentlyProduct) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".gdsdetailRecentlyProduct").recentlyProducts();
				}
			};
		});
	} else {
		// 全局模式 ，不使用AMD 规范的时候，使用的插件
		factory(jQuery);
	}
}(function($) {
	var defaultOpts = {
			gdsSize : "",
			gdsId : ""
	};
	// / 具体插件的定义；
	$recentlyProduct = {
		/**
		 * 生成最近浏览过的产品
		 * @param el
		 * @param datas
		 */
		"getData" : function(el, datas) {
			var $container = $(".gdsdetail-recentlyProduct", el);
			$container.empty();
			var htmlContext = "";
			$.each(datas.list, function(i, n) {
				htmlContext += "<li>"+
	                 "<div class='pimg'>"+
	                     "<a href='"+ GLOBAL.WEBROOT+"/gdsdetail/"+n.gdsId+"-"+n.skuId+"' title="+n.gdsName+"><img width='122' height='122' src='"+n.mediaId+"' alt=''/></a>"+
	                 "</div>"+
	                 "<p>"+
	                     "<span>特价</span>"+
	                     "<b>&yen;"+$recentlyProduct.parseMoney(n.price)+"</b>"+
	                 "</p><p class='pro-name'><a href='"+ GLOBAL.WEBROOT+"/gdsdetail/"+n.gdsId+"-"+n.skuId+"' title="+n.gdsName+">"+n.gdsName+"</a></p>"+
                 "</li>";
			});
			$container.append(htmlContext);
		},
		"parseMoney" : function(data){
			var str = (data/100).toFixed(2) + '';
			var intSum = str.substring(0,str.indexOf("."));//取到整数部分.replace( /\B(?=(?:\d{3})+$)/g, ',' )
			var dot = str.substring(str.length,str.indexOf("."));//取到小数部分
			return ret = intSum + dot;
		},
		"control":function(el,opts){
			$gdsRecentlyProduct.getData({
				"gdsSize" : opts.gdsSize,
				"gdsId" : opts.gdsId,
				"callback":function(ads){
					$recentlyProduct.getData(el,ads);
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

	$.fn.recentlyProducts = function() {
		return this.each(function() {
			var opts = $recentlyProduct.parseOptions(this, defaultOpts);
			if(opts == null){
				return ;
			}
			$recentlyProduct.control(this,opts);
			return $(this);
		});
	};
}));
