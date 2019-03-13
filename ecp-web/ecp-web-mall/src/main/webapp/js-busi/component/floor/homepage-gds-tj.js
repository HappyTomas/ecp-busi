;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([ 'jquery-powerSwitch','ecp-component/do-data' ], function(slide,adData) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".homepage-gds-tj").homepage_gdtj();
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

	var $homepage_gdtj = {
		/**
		 * 商品推荐；
		 * @param el
		 * @param images
		 * @author zhuqr
		 */
		"doData" : function(el, datas, menuType) {
				var $floorName = $(".homepage-gds-tjtt", el);
				var $container = $(".homepage-gds-tjcc", el);
				if (datas && $.isPlainObject(datas)) {
					// 获取楼层数据
					var floorRespDTO = datas.floorRespDTO
					if (floorRespDTO && floorRespDTO.id) {// 有楼层的处理
						$floorName.empty();
						var strFloor = "<span class=\"font-tit\"><i class=\"shopr-icon\"></i>" + floorRespDTO.floorName + "</span>";
						$floorName.append(strFloor);
						$floorName.attr('id','floor-'+floorRespDTO.id);
						// 获取楼层商品数据
						$homepage_gdtj.doGdsList($container, datas.gdsList,menuType);
					}else{//无楼层处理
						$container.empty();
						$container.append("<div class ='pro-empty'>亲，这家伙太懒，暂未配置数据！</div>");
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
			/* 加载loading */
			var $container = $(".homepage-gds-tjcc", el);
			$container.append('<div class="loading"></div>');
			
			$floorGdsData.getData({
				"placeId" : opts.placeId,
				"gdsSize" : opts.gdsSize,
				"tabSize" : opts.tabSize,
				"placeWidth" : opts.placeWidth,
				"placeHeight" : opts.placeHeight,
				"status" : opts.status,
				"callback":function(ads){
					$homepage_gdtj.doData(el,ads,opts.menuType);
				}
			});
		},
		"doGdsList":function(_obj,dataList2,menuType){
			_obj.empty();
			
			if(dataList2 && dataList2.length > 0){
				var str = "<ul class='ele-protj dig-protj clearfix'>";
				var liStr = "";
				var dataList=U.dealData(dataList2);
				$.each(dataList, function(i, n) {//有数据
					//获取作者属性
					var authorValue = doGdsProp(n,"1001");
					var introduction =  doGdsProp(n,"1020");//内容简介
					if(!introduction){
						introduction = n.gdsDesc;
					}
					authorValue=authorValue?(authorValue+" 编"):'';
					liStr += "<li>";
					liStr += "<a "+$homepage_gdtj.getTarget(n.url)+" href='"+(n.url?(GLOBAL.WEBROOT+n.url):'javascript:void(0)')+"'><img src='"+n.mainPic.url+"'></a>";
					liStr += "<p class=\"tit\""+(n.gdsName?("title='"+n.gdsName+"'"):'')+"><a "+$homepage_gdtj.getTarget(n.url)+" href='"+(n.url?(GLOBAL.WEBROOT+n.url):'javascript:void(0)')+"'>"+(n.gdsName?n.gdsName:"")+"</a></p>";
					liStr += "<p class=\"author\">"+authorValue+"</p>";
					liStr += "<p class=\"dec\" "+(introduction?("title='"+introduction+"'"):'')+">"+(introduction?introduction:"")+"</p>";
					liStr += "<div>";
					
					//根据menuType   确定样式
					var styleType = '';
					if(menuType == '0'){//楼层宽的样式
						styleType = " pull-left "
					}else if (menuType == '1'){//楼层窄的样式
						styleType = '';
					}
					liStr += "<p class=\"price "+styleType+"\">";
					liStr += "<span class=\"rob\">"+(n.skuInfo.discountPrice?("&yen;"+ebcUtils.numFormat(accDiv(n.skuInfo.discountPrice,100),2)):'')+"</span>";
					liStr += "<span class=\"price_r\">"+(n.guidePrice?("&yen;"+ebcUtils.numFormat(accDiv(n.guidePrice,100),2)):'')+"</span>";
					liStr += "</p>";
					//商品状态
					var buttonType = null;
					if(n.gdsStatus && n.gdsStatus == '11'){//已上架
						buttonType = "mbtn-orange ecp-add-cart";
					}else{
						buttonType = "mbtn-disable";
					}
					//根据menuType   确定样式
					var styleType = '';
					if(menuType == '0'){//楼层宽的样式
						styleType = " pull-right "
					}else if (menuType == '1'){//楼层窄的样式
						styleType = '';
					}
					liStr += "<div class='mbtn "+buttonType+styleType+"' data-gdsTypeId='"+n.gdsTypeId+"' data-image='"+n.mainPic.url+"' data-skuId = '"+n.skuInfo.id+"'>";
					liStr += "<i class='micon ep'></i>";
					liStr += "<b class='spit'></b>";
					liStr += "加入购物车";
					liStr += " </div>";
					liStr += "</div>";
					liStr += "</li>";
				});
				str += liStr;
				str += "</ul>";
				_obj.append(str);
			}else{//无数据
				_obj.append("<div class ='pro-empty'>亲，暂无数据！</div>");
			}
			_obj == null;
		},
		"getTarget":function(target){
			if(target){
				return ' target="_blank" ';
			}
			return '';
		}/*,
		"queryGdsByTabId":function(el,opts){
			$.eAjax({
				url : $webroot + '/homepage/queryGdsByTabId',
				data : {
					"tabId" : opts.tabId,
					"gdsSize" : opts.gdsSize,
					"tabSize" : opts.tabSize,
					"placeWidth" : opts.placeWidth,
					"placeHeight" : opts.placeHeight,
					"status" : opts.status
				},
				async : true,
				type : "post",
				dataType : "json",
				success : function(datas, textStatus) {
					if(datas && $.isPlainObject(datas)){
						var $container = $(".homepage-floor-gds",el);
						$homepage_gdtj.doGdsList($container,datas.gdsList,opts.menuType);
					}
				}
			});
		}*/
	};

	$.fn.homepage_gdtj = function() {
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
			$homepage_gdtj.control(this,opts);
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
