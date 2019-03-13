;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([], function() {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".modular-wap-mypoint").modular_wap_mypoint();
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
	
	$modularWapMypoint = {
		/**
		 * 获取公告数据
		 */
		"getData" : function(opts){		
			$.eAjax({
				url : $webroot + '/cmscommongetdata/getpoint',
				type : "post",
				dataType : "json",
				success : function(data, textStatus) {
					if(opts.callback && $.isFunction(opts.callback)){
						opts.callback(data);
					}
				},
				error :  function(){
					if(opts.callback && $.isFunction(opts.callback)){
						opts.callback(null);
					}
				}
			});
		},
		"control":function(el,opts){
			if(opts.isPub){
				$modularWapMypoint.getData({
					"callback":function(pointInfos){
						$modularWapMypoint.doData(el,opts,pointInfos);
					}
				});
			}else{
				$(el).removeClass("hide");
			}
		},
		"doData":function(el,opts,datas){
			if(datas && "ok" == datas.resultFlag && 0 <= parseInt(datas.point) ){
				$(".point-score",el).text(datas.point);
				$(el).removeClass("hide");
				var $a =$("a",".point-btn",el);
				$a.attr("href",$a.attr("lazy-href"));
			}else{//未登录不展示
				$(el).remove();
			}
		}
	};
	$.fn.modular_wap_mypoint = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){
			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			var opts = $.ecpPlugin.parseOptions(this, defaultOpts);
			if(null == opts){
				opts = {};
			}
			$modularWapMypoint.control(this,opts);
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
