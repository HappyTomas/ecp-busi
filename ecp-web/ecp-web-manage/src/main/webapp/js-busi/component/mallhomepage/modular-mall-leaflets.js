;/*平铺图片组件*/
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([], function() {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".modular-mall-leaflets").modular_mall_leaflets();
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

	$mall_leaflets = {
		getData : function(opts){
			$.eAjax({
				url : $webroot + '/cmscommongetdata/qryLeafletList',
				data : {
					"placeId" : opts.placeId,
					"showAmount" : opts.placeSize,
					"placeWidth":opts.placeWidth
				},
				async : true,
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
		/**
		 * 页面平铺图片，公用；
		 * @param el
		 * @param images
		 */
		"doData" : function(el,opts,datas) {
			var $container = $(".modular-body",el);
			$container.empty();
			if(datas && datas.respList && datas.respList.length > 0){
				var dataList = datas.respList;
				var globalurl = GLOBAL.MALLSITEURL ||"";
				var imgStyle = "";
				if(opts.placewidth){
					imgStyle="style='width:"+opts.placewidth+"px;'";
				}
				var str = "";
				str +="<div class='ads'>";
				$.each(dataList, function(i, n) {
					str +="<span>";
					if(!(n.linkUrl)){
						str+="<a href='javascript:void(0);'>";
					}else{
						if(!$mall_leaflets.isAbsUrl(n.linkUrl)){
							n.linkUrl=	globalurl.replace(/\/+$/,"") +"/"+n.linkUrl.replace(/^\/+/,"");
						}
						str+="<a target='_blank' href='"+n.linkUrl +"'>";
					}
					str +=       "<img "+imgStyle+" src='"+n.vfsUrl+"' alt='"+(n.advertiseTitle||"")+"'>";
					str +=   "</a>";
					str +="</span>";
				});
                str +="</div>";
                $container.append(str);
			}else{//无数据
				var $warn = $('<div class="nodata"></div>');
				var $warnSpan = $('<span style="color:red;"></span>');
				$warn.append($warnSpan);
				if(!datas){
					$warnSpan.append("无任何数据返回，请检查网络！");
				}else if(datas.errMsg){
					$warnSpan.append(datas.errMsg);
				}else{
					$warnSpan.append('亲，暂无广告！');
				}
				$container.html($warn);
			}
		},
		"control":function(el,opts){
			$(".modular-body",el).append("<div class='tpl-loading'></div>");
			opts.placewidth = $mall_leaflets.getImgWidth(el,opts.showWay);
			$mall_leaflets.getData({
				"placeId" : opts.placeId,
				"placeSize" : opts.placeSize,
				"placeWidth":parseInt(opts.placewidth) || "",
				"callback":function(ads){
					$mall_leaflets.doData(el,opts,ads);
				}
			});
		},
		"isAbsUrl":function(url){//判断是否为绝对地址
			url = ""+url;
			var reg = /^(?:(?:\w+:(?:\/|\d+)\/)|(?:\w+\.))/ig;
			return reg.test(url);
		},
		"getImgWidth":function(el,showWay){
			showWay = +showWay;
			var imgWidth = 0;
			if(!showWay || showWay <= 0){
				showWay = 1;
			}
			
			var tWidth=Math.abs($(el).width());
			if(!tWidth){
				return imgWidth;
			}
			
			var pWidth = (showWay-1) * 10;
			if(pWidth >= tWidth){
				return imgWidth;
			}
			
			imgWidth = Math.floor((tWidth - pWidth) / showWay);
			if(0 == ((tWidth - pWidth) % showWay) && 1 < imgWidth){
				imgWidth -= 1;
			}
			return imgWidth;
		}
	};

	$.fn.modular_mall_leaflets = function() {
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
			$mall_leaflets.control(this,opts);
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
