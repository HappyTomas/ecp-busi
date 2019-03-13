;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([ 'jquery-powerSwitch','ecp-component/do-data' ], function(slide,adData) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".ranking-gds-hot").ranking_gds_hot();
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

	var $rankingGdsHot = {
		/**
		 * 商品推荐；
		 * @param el
		 * @param images
		 * @author zhuqr
		 */
		"doData" : function(el, datas) {
			var $container = $(".floor-gds-data", el);
			if(datas && $.isPlainObject(datas)){
				//获取楼层数据
				var floorRespDTO = datas.floorRespDTO;
				var $floorName = $(".floor-name", el);
				
				if (floorRespDTO && floorRespDTO.id) {// 有楼层
					$floorName.text(floorRespDTO.floorName);
					// 获取楼层商品数据
					var gdsList = datas.gdsList;
					$rankingGdsHot.doGdsList($container, gdsList);
				}else{//无楼层处理
					$container.empty();
					$container.append("<div class ='pro-empty'>亲，这家伙太懒，暂未配置数据！</div>");
				}
			}else{
				$container.append("<div class ='pro-empty'>亲，出错啦！</div>");
			}
		},
		/**
		 * 调用获取数据组件
		 */
		"control":function(el,opts){
			/* 加载loading */
//			var $container = $(".homepage-gds-tjcc", el);
//			$container.append('<div class="loading"><div>');
			var $container = $(".floor-gds-data", el);
			$container.append('<div class="loading"></div>');
			$floorGdsData.getData({
				"placeId" : opts.placeId,
				"gdsSize" : opts.gdsSize,
				"tabSize" : opts.tabSize,
				"placeWidth" : opts.placeWidth,
				"placeHeight" : opts.placeHeight,
				"status" : opts.status,
				"callback":function(ads){
					$rankingGdsHot.doData(el,ads);
				}
			});
		},
		"doGdsList":function(_obj,dataList){
//			var dataList=U.dealData(dataList2);
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
					str += "<a target='_blank' href='"+(n.url?(GLOBAL.WEBROOT+n.url):'javascript:void(0)')+"'><img src='"+( n.mainPic.url ? n.mainPic.url : 'javascript:void' )+"'></a></div>";
					str += "<p><span>特价  </span><b>"+(n.skuInfo.discountPrice?('&yen;'+ebcUtils.numFormat(accDiv(n.skuInfo.discountPrice,100),2)):'')+"</b></p>";
					str += "<p><a target='_blank' href='"+(n.url?(GLOBAL.WEBROOT+n.url):'javascript:void(0)')+"'>"+(n.gdsName?n.gdsName:'')+"</a></p>";
					str += "<p>指导："+(n.mainCatgName?n.mainCatgName:'')+"</p>";
					str += "</li>";
				
				});
				_obj.append(str);
					
		      }else{//无数据
		    	  _obj.append("<div class ='pro-empty'>亲，暂无数据！</div>");
		      }
				_obj == null;
		}
	};

	$.fn.ranking_gds_hot = function() {
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
			$rankingGdsHot.control(this,opts);
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
