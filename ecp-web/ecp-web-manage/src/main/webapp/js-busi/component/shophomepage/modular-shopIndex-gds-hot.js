;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([ ], function() {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".modular-shopIndex-gds-hot").modular_shopIndex_gds_hot();
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
		count : "10"
	};

	var $shopIndexGdsHot = {
			getData:function(opts){
				$.eAjax({
					url :  $webroot+'/cmscommongetdata/hotSales',
					data : {
						"count" : opts.count,
						"shopId" : opts.shopId,
					},
					async : true,
					type : "post",
					dataType : "json",
					success : function(data, textStatus) {
						if(opts.callback && $.isFunction(opts.callback)){
							opts.callback(data);
						}
					}
				});
			},
		"doData" : function(el, datas) {
			var $container = $(".floor-gds-data",el);
			if(datas && datas.gdsList && datas.gdsList.length > 0){
				$shopIndexGdsHot.doGdsList($container, datas.gdsList);
			}else{
				var $warn = $('<div class="nodata"></div>');
				var $warnSpan = $('<span style="color:red;"></span>');
				$warn.append($warnSpan);
				if(!datas){
					$warnSpan.append("无任何数据返回，请检查网络！");
				}else if(datas.errMsg){
					$warnSpan.append(datas.errMsg);
				}else{
					$warnSpan.append('暂无数据！');
				}
				$container.html($warn);
			}
		},
		/**
		 * 调用获取数据组件
		 */
		"control":function(el,opts){
			/* 加载loading */
			var $container = $(".floor-gds-data", el);
			$container.append('<div class="loading"></div>');
			$shopIndexGdsHot.getData({
				"count" : opts.count,
				"shopId": opts.shopId,
				"callback":function(ads){
					$shopIndexGdsHot.doData(el,ads);
				}
			});
		},
		"doGdsList":function(_obj,dataList){
			_obj.empty();
			var str = "";
			$.each(dataList, function(i, n) {
				//获取作者属性
				var authorValue = "";//doGdsProp(n,"1001");
				authorValue=authorValue?authorValue:'';
				if(!(n.mainPic) ){
					n.mainPic = {};
				}
				if(!(n.skuInfo) ){
					n.skuInfo = {};
				}
				str += "<li>";
				str += "<div class='pimg'>";
				str += "<a target='_blank' href='"+GLOBAL.MALLSITEURL+"/gdsdetail/"+n.id+"-"+n.firstSkuId+"'><img src='"+( n.imageUrl ? n.imageUrl : 'javascript:void' )+"'></a></div>";
				str += "<p><span>特价  </span><b>"+(n.discountPrice?('&yen;'+ebcUtils.numFormat(accDiv(n.discountPrice,100),2)):'')+"</b></p>";
				str += "<p><a target='_blank' href='"+(n.url?(GLOBAL.MALLSITEURL+"/"+n.url):'javascript:void(0)')+"'>"+(n.gdsName?n.gdsName:'')+"</a></p>";
				str += "<p>指导："+(n.mainCategoryValue?n.mainCategoryValue:'')+"</p>";
				str += "</li>";
			
			});
			_obj.append(str);
			_obj == null;
		}
	};

	$.fn.modular_shopIndex_gds_hot = function() {
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
			$shopIndexGdsHot.control(this,opts);
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
