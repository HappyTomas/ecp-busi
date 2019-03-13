;
/**
 * 首页顶部广告
 * @param factory
 */
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define(['ecp-component/do-data' ], function(adData) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".leaflet-sale").image_sale();
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

	$image_sale = {
		/**
		 * @param el
		 * @param datas
		 * @author jiangzh
		 */
		"doData" : function(el, datas,opts) {
			var $adList = $(el);
			if(datas && datas.respList && datas.respList.length > 0){//有数据
				$(el).removeClass("hide");
				$image_sale.doAdList($adList,datas.respList,opts);
			}else{//无数据
				$(el).remove();
			}
		},
		"control":function(el,opts){
			$AdData.getData({
				"placeId":opts.placeId,
				"placeSize":opts.placeSize,
				"placeWidth":opts.placeWidth,
				"placeHeight":opts.placeHeight,
				"status" : opts.status,
				"callback":function(ads){
					$image_sale.doData(el,ads,opts);
					$image_sale.adAction(el);
				}
			});
		},
		"doAdList":function(_obj,dataList){
			_obj.empty();
			var str = "";
			$.each(dataList, function(i, n) {
				str += "<div id='sale-ad' class='sale-ad'>";
				str +=   "<div class='sale-link'>"
				str +=     "<a target= '_blank' href='"+(n.linkUrl?n.linkUrl:"javascript:void(0);")+"'><img src='"+n.vfsUrl.replace(/\.jpg$/,".png")+"'/></a>";
				str +=   "</div>";
				str +=   "<span class='sale-close'>×</span>";
				str += "</div>";
				str += "<div id='sale-fade' class='modal-backdrop in'></div>";
			});
			_obj.append(str);
			_obj = null;
		},
		"adAction":function(el){
			$('.sale-close',el).live("click",function(){
				$('#sale-ad',el).remove();
                $('#sale-fade',el).remove();
			});
			$('.sale-link',el).live("click",function(){
				$('#sale-ad',el).remove();
                $('#sale-fade',el).remove();
			});
		}
	};
	$.fn.image_sale = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){

			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			
			var opts = $.ecpPlugin.parseOptions(this,defaultOpts);
			if(opts == null || !opts.placeId){
				return ;
			}
			$image_sale.control(this,opts);
			
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
