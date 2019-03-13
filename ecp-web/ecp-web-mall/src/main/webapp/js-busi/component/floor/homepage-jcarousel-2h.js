;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([ 'jquery-powerSwitch','ecp-component/do-data' ], function(slide,adData) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".box-tj2").jcarousel2h();
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

	var $floorjcarousel2 = {
		/**
		 * 生成信息；
		 * @param el
		 * @param images
		 */
		"doData" : function(el, datas) {
			//获取楼层商品数据
			var gdsList =datas.gdsList;
			
			var floorRespDTO =datas.floorRespDTO;
	
			if(floorRespDTO && floorRespDTO.id){
				 $(".box-jcarousel-tt", el).append(floorRespDTO.floorName);
				 //显示楼层名
				 $(".box-jcarousel-tt", el).removeClass("hide");
			}
			
			var $container = $(".jcarousel-container", el);
			if(gdsList && gdsList.length > 0){//有数据
				var $trigger = $(".slide-trigger", el);
				$floorjcarousel2.doFloorList($trigger,floorRespDTO.placeId,$container,gdsList);
			}else{//无数据
				$container.empty();
				$container.append("<div class ='pro-empty'>亲，暂无数据！</div>");
			}
		},
		"control":function(el,opts){
			var $container = $(".jcarousel-container", el);
			$container.append('<div class="loading" style="width:750px"></div>');
			$floorGdsData.getData({
				"placeId" : opts.placeId,
				"gdsSize" : opts.gdsSize,
				"tabSize" : opts.tabSize,
				"placeWidth" : opts.placeWidth,
				"placeHeight" : opts.placeHeight,
				"status" : opts.status,
				"callback":function(ads){
					$floorjcarousel2.doData(el,ads);
				}
			});
		},
		"doFloorList":function(_obj,_placeId,_img,dataList2){
			// 拼装轮播楼层
			var dataList=U.dealData(dataList2);
			_img.empty();
			var imgStr = "";
			var dl=dataList.length;
			var swtarget="switcha";
			$.each(dataList, function(i, n) {
				var k = i + 1;
				var l=0
                if(i/8==0){
                	imgStr += '<div class="item '+swtarget+'"><ul class="clearfix">';
                	l=0;
				}
                l++;
				//获取作者属性
				var authorValue = doGdsProp(n,"1001");
				authorValue=authorValue?(authorValue+ " 著"):'';
				
				imgStr += "<li>";
				imgStr += "<a target='_blank' href='"+(n.url?(GLOBAL.WEBROOT+n.url):'javascript:void(0)')+"'><img src='"+n.mainPic.url+"'></a>";
				imgStr += "<p class=\"name\"><a target='_blank' href='"+(n.url?(GLOBAL.WEBROOT+n.url):'javascript:void(0)')+"'>"+(n.gdsName?n.gdsName:"")+"</a></p>";
				imgStr += "<p class=\"author\">"+authorValue+"</p>";
				imgStr += "<p class=\"price\">";
				imgStr += "<span class=\"rob\">&yen;"+(n.skuInfo.discountPrice?("&yen;"+ebcUtils.numFormat(accDiv(n.skuInfo.discountPrice,100),2)):'')+"</span>";
				imgStr += "<span class=\"price_r\">&yen;"+(n.guidePrice?("&yen;"+ebcUtils.numFormat(accDiv(n.guidePrice,100),2)):'')+"</span>";
				imgStr += "</p>";
				imgStr += "</li>";
			    if(l>=8||i==(dl-1)){
	               imgStr += '</ul></div>';
				}

			});
			_img.append(imgStr);
	
			
			_obj.empty();
			_obj.append('<a href="javascript:" class="slide-prev" data-rel="'+swtarget+'"></a>'+
                         '<a href="javascript:" class="slide-next" data-rel="'+swtarget+'"></a>')
			_obj.find("a").powerSwitch({
				 number: 1,
                 classDisabled:'',
                 container: _img
            });
			_img = null;
		}
	};

	$.fn.jcarousel2h = function() {
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
			$floorjcarousel2.control(this,opts);
			$(this).data("comStatus","end");
			return $(this);
		
		});
	};
}));
