;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([ 'jquery-powerSwitch','ecp-component/do-data' ], function(slide,adData) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".gds-recommend").gds_recommend();
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

	var $gdsrecommendslider = {
		/**
		 * 生成重磅推出信息；
		 * @param el
		 * @param images
		 * @author jiangzh
		 */
		"doData" : function(el, datas) {
					if (datas && $.isPlainObject(datas)) {
						// 获取楼层数据
						var floorRespDTO = datas.floorRespDTO;
						var $container= $(".gds-recommend-list", el);
						var $floorName = $(".floor-name", el);
						// //获取楼层页签数据
						var floorTabList = datas.floorTabList;
						if (floorRespDTO && floorRespDTO.id) {// 有楼层处理
							$gdsrecommendslider.doTabList($floorName,floorRespDTO, floorTabList);
							$gdsrecommendslider.doGdsList($container,datas.gdsList);
						} else {// 没有楼层的处理
							$container.empty();
							$container.append("<div class ='pro-empty'>亲，暂无数据！</div>");
						}
					}else{
						$container.empty();
						$container.append("<div class ='pro-empty'>亲，出错啦！</div>");
					}
		},
		/**
		 * 调用获取数据组件
		 */
		"control":function(el,opts){
			var $container = $(".gds-recommend-list", el);
			$container.append('<div class="loading-small"></div>');
			$floorGdsData.getData({
				"placeId" : opts.placeId,
				"gdsSize" : opts.gdsSize,
				"tabSize" : opts.tabSize,
				"placeWidth" : opts.placeWidth,
				"placeHeight" : opts.placeHeight,
				"status" : opts.status,
				"callback":function(gds){
					$gdsrecommendslider.doData(el,gds);
				}
			});
		},
		"doGdsList":function(_obj,dataList){
			_obj.empty();
			if(dataList && dataList.length > 0){
				var str = "<ul>";
				var liStr = "";
				
			 $.each(dataList, function(i, n) {
				//获取作者属性
//				var authorValue = doGdsProp(n,"1001");
				if(!(n.mainPic) ){
					n.mainPic = {};
				}
				if(!(n.skuInfo) ){
					n.skuInfo = {};
				}
				liStr += "<li>";
				liStr += "<div class='b-img'>";
				liStr += "<a target='_blank' href='"+(n.url?(GLOBAL.WEBROOT+n.url):'javascript:void(0)')+"'><img src='"+(n.mainPic.url?n.mainPic.url:'')+"'></a></div>";
				liStr += "<p>特价   <span class=\"rob\">"+(n.skuInfo.discountPrice?("&yen;"+ebcUtils.numFormat(accDiv(n.skuInfo.discountPrice,100),2)):'')+"</span></p>";
				liStr += "<p><a target='_blank' href='"+(n.url?(GLOBAL.WEBROOT+n.url):'javascript:void(0)')+"'>"+(n.gdsName?n.gdsName:'')+"</a></p>";
				liStr += "<p>指导："+(n.mainCatgName?n.mainCatgName:'')+"</p>";
				liStr += "</li>";
				
			});
			    str += liStr;
				str += "</ul>";
				_obj.append(str);
				_obj = null;
			}else{// 有数据
				_obj.append("<div class ='pro-empty'>亲，暂无数据！</div>");
			}
		},
		"doTabList":function(_obj,respDTO,dataList){
			_obj.empty();
			_obj.append(respDTO.floorName);
			_obj.removeClass("hide");
//			var strFloor = "<span class=\"font-tit\"><i class=\"renwei-icon\"></i>"+respDTO.floorName+"</span>";
//			_obj.append(strFloor);
			_obj=null;
		}
	};

	$.fn.gds_recommend = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){

			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			var opts = $.ecpPlugin.parseOptions(this, defaultOpts);
			if(opts == null || opts.placeId =="" || opts.placeId=="undefined"){
				return ;
			}
			$gdsrecommendslider.control(this,opts);
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
