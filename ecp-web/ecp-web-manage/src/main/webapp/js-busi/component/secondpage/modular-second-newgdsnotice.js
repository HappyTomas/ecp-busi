;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([ 'jquery-powerSwitch'], function(slide) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".modular-second-newgdsnotice").modular_second_newgdsnotice();
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

	$modularMallNewGdsNotice = {
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
//			debugger;
//			alert(opts.showamount);
//			$.eAjax({
//				url : $webroot + '/cmshomepagegetdata/queryNewGdsNotice',
//				data : {pageSize:opts.showamount},
//				async : true,
//				type : "post",
//				dataType : "html",
//				success : function(data, textStatus) {
//					if (data == null) {
//						return;
//					} else {
//						if(opts.callback && $.isFunction(opts.callback)){
//							opts.callback(data);
//						}
//					}
//				}
//			});
		},
			
		/**
		 * 调用获取数据组件
		 */
		"control":function(el,opts){
			var modular_body = $('#modular-body',$(el));
			var slide_body=$('<div class="slide-body"></div>');
			$.eAjax({
				url : $webroot + '/cmshomepagegetdata/queryNewGdsNotice',
				data : {pageSize:opts.showamount},
				async : true,
				type : "post",
				success : function(returnInfo){
					modular_body.empty();
					if(returnInfo.length>0){
						slide_body.append('<div class="slide-switch"></div>');
						var ol=$('<ol class="slide-page"></ol>');
//						var pageSize=parseInt(parseInt(opts.showamount)/parseInt(opts.showway));
						//计算页数
						var pageSize=parseInt((parseInt(opts.showamount) + parseInt(opts.showway) -1)/parseInt(opts.showway));
						for (var i = 0; i < pageSize; i++) {
							if(i==0){
								ol.append('<li><a class="current" href="javascript:" data-rel="prob1">●</a></li>');
							}else{
								ol.append('<li><a href="javascript:" data-rel="prob'+(i+1)+'">●</a></li>');
							}
						}
						slide_body.append(ol);
						
						var ul=$('<ul class="slide-list"></ul>');
						for (var i = 0; i < pageSize; i++) {
							var displayStyle='';
							if(i==0){
								displayStyle='style="display: block"';
							}
							var li=$('<li id="prob'+(i+1)+'"'+displayStyle+' class="newBookSaleLi"></li>');
							for (var j =parseInt(i*parseInt(opts.showway)+1); j <=parseInt((i+1)*parseInt(opts.showway)) && j<=returnInfo.length; j++) {
								var divObj=$('<div class="clearfix">');
								divObj.append('<p class="img-w"><a href="'+GLOBAL.MALLSITEURL+returnInfo[j-1].url+'"><img src="'+returnInfo[j-1].mainPic.url+'"></a></p>');
								divObj.append('<h2><a class="clamp" href="'+GLOBAL.MALLSITEURL+returnInfo[j-1].url+'">'+returnInfo[j-1].gdsName+'</a></h2>');
								divObj.append('<p class="red">新品预告</p><p class="price-j">￥'+parseFloat(returnInfo[j-1].discountPrice/100).toFixed(2)+'</p>');
								divObj.append('<p class="price_r">￥'+parseFloat(returnInfo[j-1].guidePrice/100).toFixed(2)+'</p>');
								li.append(divObj);
							}
							ul.append(li);
						}
						slide_body.append(ul);
						
					}else{
						slide_body.append('<div class="pro-empty">暂无数据，敬请期待！</div>');
					}
					
					//计算高度
					modular_body.css("height",(returnInfo.length <= parseInt(opts.showway) ? (returnInfo.length*140 + "px") : parseInt(opts.showway)*140+ "px"));
					modular_body.append(slide_body);
					$(".slide-page a").powerSwitch({
			             classAdd: "current",
			             animation: "translate",
			             hoverStop: false,
			             container: $(".slide-switch"),
			             onSwitch:function(item){
			             }
			         });
				}
			});
		}
	};
	$.fn.modular_second_newgdsnotice = function() {
		return this.each(function() {
			var opts = $.ecpPlugin.parseOptions(this, defaultOpts);
			$modularMallNewGdsNotice.control(this,opts);
			return $(this);
		});
	};
}));
