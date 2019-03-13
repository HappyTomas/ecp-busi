;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([], function() {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".modular-mall-guess").modular_mall_guess();
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

	$mall_guess = {
		/**
		 * 获取猜你喜欢数据，则调用数据处理的回调函数(callback);否则直接返回；
		 * callback : 回调函数，对于数据处理的回调函数；
		 * @author zhanbh
		 */
		getData : function(opts,el){
			$.eAjax({
				url : $webroot + '/cmshomepagegetdata/queryguessgds',
				data : {
					"status" : opts.status,
					"gdsSize" : opts.gdsSize,
					"ifAnalys" : opts.ifAnalys,
					"placeWidth" : opts.placeWidth,
					"placeHeight" : opts.placeHeight
				},
				async : true,
				type : "post",
				dataType : "html",
				success : function(data, textStatus) {
					if (data == null) {
						$(".guess-data-body",$(el)).html("数据丢失！");
					} else {
						if(opts.callback && $.isFunction(opts.callback)){
							opts.callback(data);
						}
					}
				},
				error:function(data){
						$(".guess-data-body",$(el)).html("网络请求异常！");
				}
			});
		},
		/**
		 * 生成商品信息；
		 * @param el
		 * @param 
		 * @author zhanbh
		 */
		"doData" : function(el, datas,opts) {
			$(".guess-data-body",$(el)).html(datas);
		},
		/**
		 * 调用获取数据组件
		 */
		"control":function(el,opts){
			$mall_guess.getData({
					"status" : opts.status,
					"gdsSize" : opts.gdsSize,
					"ifAnalys" : opts.ifAnalys,
					"placeWidth" : opts.placeWidth,
					"placeHeight" : opts.placeHeight,
					"callback":function(datas){
						$mall_guess.doData(el,datas,opts);
					}
				},el);
		}
	};

	$.fn.modular_mall_guess = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){

			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			
			var opts = $.ecpPlugin.parseOptions(this,defaultOpts);
			if(opts == null){
				return this;
			}
			$mall_guess.control(this,opts);
			
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
