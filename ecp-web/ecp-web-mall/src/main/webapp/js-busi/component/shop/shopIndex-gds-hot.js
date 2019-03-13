;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([ ], function() {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".shopIndex-gds-hot").shopIndex_gdshot();
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
					url : $webroot + '/shopIndex/hotSales',
					data : {
						"count" : opts.count,
						"shopId" : opts.shopId,
					},
					async : true,
					type : "post",
					dataType : "json",
					success : function(data, textStatus) {
						if (data == null) {
							return;
						} else {
							if(opts.callback && $.isFunction(opts.callback)){
								opts.callback(data);
							}
						}
					}
				});
			},
		"doData" : function(el, datas) {
			var $container = $(".floor-gds-data", el);
			if(datas){
				$shopIndexGdsHot.doGdsList($container, datas);
				
			}else{
				$container.append("<div class ='pro-empty'>亲，这家伙太懒，暂未配置数据！</div>");
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
			if(dataList && dataList.length > 0){//有数据
				var str = "";
				$.each(dataList, function(i, n) {
					//获取作者属性
					var authorValue = doGdsProp(n,"1001");
					authorValue=authorValue?authorValue:'';
					if(!(n.mainPic) ){
						n.mainPic = {};
					}
					if(!(n.skuInfo) ){
						n.skuInfo = {};
					}
					str += "<li>";
					str += "<div class='pimg'>";
					str += "<a target='_blank' href='"+GLOBAL.WEBROOT+"/gdsdetail/"+n.id+"-"+n.firstSkuId+"'><img src='"+( n.imageUrl ? n.imageUrl : 'javascript:void' )+"'></a></div>";
					str += "<p><span>特价  </span><b>"+(n.discountPrice?('&yen;'+ebcUtils.numFormat(accDiv(n.discountPrice,100),2)):'')+"</b></p>";
					str += "<p><a target='_blank' href='"+(n.url?(GLOBAL.WEBROOT+n.url):'javascript:void(0)')+"'>"+(n.gdsName?n.gdsName:'')+"</a></p>";
					str += "<p>指导："+(n.mainCategoryValue?n.mainCategoryValue:'')+"</p>";
					str += "</li>";
				
				});
				_obj.append(str);
					
		      }else{//无数据
		    	  _obj.append("<div class ='pro-empty'>亲，暂无数据！</div>");
		      }
				_obj == null;
		}
	};

	$.fn.shopIndex_gdshot = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){

			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			var opts = $.ecpPlugin.parseOptions(this, defaultOpts);
			if(opts == null || opts.floorId =="" || opts.floorId=="undefined"){
				return ;
			}
			$shopIndexGdsHot.control(this,opts);
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
