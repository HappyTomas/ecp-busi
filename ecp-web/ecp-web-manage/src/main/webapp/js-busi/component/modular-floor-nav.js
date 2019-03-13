;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([], function() {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".modular-floor-nav").modular_floor_nav();
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
	
	$modularFloorNav = {
		/**
		 * 根据FileID获得文件URL
		 * @param fileId
		 */
		"loadImgUrlById" : function(fileId){		
			var url = '';
			if(!fileId) return '';
			//根据FileId读取图片
			$.eAjax({
				url : $webroot + "modular-dynamic/loadImgById",
				data : {fileId : fileId},
				async : false,//同步执行
				success : function(returnInfo){
					url = returnInfo.imgUrl;
				}
			});
			return url;
		},
		/**
		 * 调用获取数据组件
		 */
		"control":function(el,opts){
			var main = $(el).closest('div.skipfloor');
			var isPub=opts.pagepub;
			var itemId = $('#itemId',$(main)).val();
			var list = $('ul.navigation',$(main));
			if(!itemId) return;
			var requestPath = 'page-pre';
			if(isPub) requestPath = 'page-pub';
			$.eAjax({
				url : $webroot + requestPath + '/loadLayoutItemProp',
				data : {'itemId' : itemId},
				success : function(returnInfo){
					if(returnInfo && $.type(returnInfo.itemProp)!='undefined' && $.isPlainObject(returnInfo.itemProp)){
						var item = returnInfo.itemProp;
						var l = isPub ? item.itemPropPubRespDTOList : item.itemPropPreRespDTOList;
						if(l && $.isArray(l) && l.length > 0){
							$(list).empty();
							for(var i =0 ; i < l.length ; i++){
								var elt = l[i];
								var propValues = isPub ? elt.itemPropValuePubRespDTOList : elt.itemPropValuePreRespDTOList;
								if(propValues && $.isArray(propValues) && propValues.length > 0){
									for(var j = 0 ; j < propValues.length; j++){
										var data = propValues[j];
										//目前只能依据PropId来决定属性类型
										if(data.propId == '1019') $(list).append('<li><a href="#layoutFloor_'+data.remark+'">'+(null==data.propValue?'&nbsp;':data.propValue)+'</a></li>');
									}
								}
							}
						}
					}
				}
			});
			
			//回到顶部统一处理
			$('span.backToTop',$(main)).click(function(e) {
				$('body,html').animate({"scrollTop":0},250);
			});
			var $rightNavSlide = $('.rightNavSlide',$(main));
			$rightNavSlide.click(function(e){
				if($rightNavSlide.hasClass('toLeft')){
					$('.tpl-floor',$(main)).show();
					$rightNavSlide.removeClass('toLeft');
				}else{
					$('.tpl-floor',$(main)).hide();
					$rightNavSlide.addClass('toLeft');
				}
			});
//			//获得父容器
//			var parentBox = $(main).closest('div.page-container2');
//			//非编辑页面则设置导航功能
//			if(!$(parentBox).hasClass('page-edit')){
//				//设置导航效果
//				$('ul.navigation',$(main)).onePageNav({
//				    currentClass: 'current',
//				    changeHash: false,
//				    scrollSpeed: 250,
//				    scrollThreshold: 0.5,
//				    filter: '',
//				    easing: 'swing',
//				    scrollChange: function($currentListItem) {
//				        //I get fired when you enter a section and I pass the list item of the section
//				    }
//				});
//				
//			}
		}
	};

	$.fn.modular_floor_nav = function() {
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
			$modularFloorNav.control(this,opts);
			$(this).data("comStatus","end");
			return $(this);
		});
	};
	
	
}));
