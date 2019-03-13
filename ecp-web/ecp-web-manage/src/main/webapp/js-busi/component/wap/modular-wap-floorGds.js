;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define(["ecp-component/wap/cms-url-tool"], function(urlTool) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".modular-wap-floorGds").modular_wap_floorGds();
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
	
	$modularWapFloorGds = {
		/**
		 * 根据FileID获得文件URL
		 * @param fileId
		 */
		"getGdsInfo" : function(goodsId,promId,siteId,callback,staffId){		
			var gdsInfo =null;
			if(goodsId && siteId){
				//获取商品
				$.eAjax({
					url : $webroot + "cmscommongetdata/getPromGdsInfo",
					data : {goodsId : goodsId, promId: promId,standard:"277x277!",siteId: siteId,staffId: staffId},
					success : function(returnInfo){
						callback(returnInfo);
					}
				});
			}
			return gdsInfo;
		},
		/**
		 * 调用获取数据组件
		 */
		"control":function(el,opts){
			$(el).find('li').each(function(){
				var siteId=$('#siteId').val();
				var goodsId=$(this).attr("goodsId");
				var promId=$(this).attr("promId");
				var staffId=opts.staffId;
				var $thisObj=$(this);
				var callback=function (data){
					if(data && data.respDTO){
						$thisObj.find('img').attr('src',data.respDTO.mainPic.url);
						var newPrice=parseFloat(data.respDTO.skuInfo.discountPrice/100).toFixed(2);
						var oldPrice=parseFloat(data.respDTO.skuInfo.guidePrice/100).toFixed(2);
						$thisObj.find('#newPrice').html('<em class="yuan">¥</em>'+newPrice);
						$thisObj.find('#oldPrice').html('<em class="yuan">¥</em>'+oldPrice);
						$('#oldPrice',$thisObj).addClass('prc-del');
						if(opts.isPub){ //已发布
							$thisObj.find('a.link').attr('href',data.respDTO.url);
							$thisObj.find('a.carBtn').attr('href',data.respDTO.url);
						}
					}
				};
				$modularWapFloorGds.getGdsInfo(goodsId,promId,siteId,callback,staffId);
			});
			$modularWapFloorGds.doData(el,opts);
		},
		"doData":function(el,opts){
			//加载链接
			var $dataList = $("a",$(el));
			$dataList = $.merge($dataList,$(el).prev(".modular-head").find('a'));
			$dataList.each(function(){
				if(opts.isPub){
					var href = $(this).attr("lazy-href");
					if(href){
						if(_eCmsUrlTool.isAbsUrl(href)){
							$(this).attr("href",href);
						}else{
							$(this).attr("href",(_eCmsUrlTool.rmSlash(opts.webRoot,false)||"")+"/"+_eCmsUrlTool.rmSlash(href,true));
						}
					}
				}
				var $img = $(this).find("img");
				var srcV = $img.attr("src");
				var default_src = $img.attr("default_src");
				if(null==srcV||srcV.length==0||srcV==""){
					$img.attr("src",default_src);
				}
			});
		}
	};

	$.fn.modular_wap_floorGds = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){
			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			var opts = $.ecpPlugin.parseOptions(this, defaultOpts);
			$modularWapFloorGds.control(this,opts);
			$(this).data("comStatus","end");
			return $(this);
		});
	};
	
	
}));
