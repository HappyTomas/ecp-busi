;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([ 'scrollTo','onePageNav'], function(scrollTo,onePageNav) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".homepage-elevator").homepage_elevator();
					/*$(".homepage-elevator").onePageNav({
						    filter: ':not(.external)',
						    scrollThreshold: 0.25
					   });   */
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

	var $homepage_elevator = {
		/**
		 * 楼层导航；
		 * @param el
		 * @param images
		 * @author jiangzh
		 */
		"doData" : function(el, datas2) {
			//var $container = $(".homepage-info-slides", el);
			var $container=$(el);
			$container.empty();
			var str = "<ul>";
			var datas=U.dealData(datas2);
			
			$.each(datas, function(i, n) {
				str += "<li><a href='#floor-"+(n.id)+"'>"+n.placeName+"</a></li>";
			});
			str += "</ul>";
			$container.append(str);
			
			 $(window).on('scroll.navToggle',function(){
				 var floors=$('[id*="floor-"]');
				 if(floors.size()>0){
					 if($(this).scrollTop() >= $('[id*="floor-"]').eq(0).offset().top){
					    $container.show();
					  }else{
					     $container.hide();
					  }
				   $container.onePageNav({
					    filter: ':not(.external)',
					    scrollThreshold: 0.25
				   }); 
				 }
				
			 });
			
		},
		"control":function(el,opts){
			$.eAjax({
				url : $webroot + '/homepage/qryFloorByTemplateId',
				data : {
					"templateId" : opts.templateId,
					"placeType" : opts.placeType,
					"status" : opts.status
				},
				async : true,
				type : "post",
				dataType : "json",
				success : function(data, textStatus) {
					var respList=data?data.floorRespList:null;
					if (respList == null) {
						return;
					} else {
						$homepage_elevator.doData(el,respList);
					}
				}
			});
		}
	};

	$.fn.homepage_elevator = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){

			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			
			var opts = $.ecpPlugin.parseOptions(this,defaultOpts);
			if(opts == null || opts.templateID =="" || opts.templateID=="undefined"){
				return ;
			}
			$homepage_elevator.control(this,opts);
			
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
