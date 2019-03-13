;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([ 'jquery-powerSwitch','ecp-component/do-data' ], function(slide,adData) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".homepage-jcarousel").electronic_boxJc();
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

	var $floorjcarousel = {
		/**
		 * 生成信息；
		 * @param el
		 * @param images
		 */
		"doData" : function(el, datas) {
			//获取楼层信息
			var floorRespDTO =datas.floorRespDTO;
			//获取楼层商品数据
			var gdsList =datas.gdsList;
			//获取html对象
			//var $trigger = $(".slide-trigger", el);
			var $gdsdiv = $(".homepage-jc-body", el);
			var $tabdiv = $(".homepage-jc-tt", el);
			if (floorRespDTO && floorRespDTO.id) {//有楼层
				$('.font-tit',$tabdiv).append(floorRespDTO.floorName);
				 //显示楼层名
				$tabdiv.removeClass("hide");
				
				//2 获取楼层页签数据，填充楼层页签
			    var floorTabList =datas.floorTabList;
				if(floorTabList){
				  $homepage_floor.doTabList($tabdiv,$gdsdiv,floorTabList);
				 }
					
				if (gdsList && gdsList.length > 0) {//如果有商品数据
					$floorjcarousel.doAdList($gdsdiv, floorRespDTO.placeId, gdsList);
				}else{//没有商品数据
					$container.empty();
					$container.append("<div style = 'width:752px' class ='pro-empty'>亲，暂无数据！</div>");
				}
			}else{//无楼层
				$gdsdiv.empty();
				$gdsdiv.append("<div style = 'width:752px' class ='pro-empty'>亲，这家伙太懒，暂未配置数据！</div>");
			}
		},
		"control":function(el,opts){
			var $container = $(".homepage-jc-body", el);
			$container.append('<div class="loading" style="width:750px"></div>');
			$floorGdsData.getData({
				"placeId" : opts.placeId,
				"gdsSize" : opts.gdsSize,
				"tabSize" : opts.tabSize,
				"placeWidth" : opts.placeWidth,
				"placeHeight" : opts.placeHeight,
				"status" : opts.status,
				"callback":function(ads){
					$floorjcarousel.doData(el,ads);
					$floorjcarousel.bindTabChange(el);
				}
			});
		},
		"getHref":function(href){
			if(href){
				return href;
			}
			return 'javascript:void(0)';
		},
		"doTabList":function(_obj,_gdsdiv,dataList){
			var str = "<ul class=\"new-tab clearfix\">";
			
			var strc="";
			$.each(dataList, function(i, n) {
				str += "<li>";
				str += "<a href='"+$floorjcarousel.getHref(n.linkUrl)+"' tabId="+n.id; 
				strc +='<div ';
				if(i == 0){
					str +=" class='active'";
				}
				if(i > 0){
					strc+= 'class="hide"';
				}
				str +=">"+n.tabName+"</a></li>";
				strc +='><div class="loading"></div></div>';
			});
			str += "</ul>";
			_obj.append(str);
			//tab对应的内容
			_gdsdiv.empty().append(strc);
			//第一次加载标志
			_obj.find('.new-tab').children().eq(0).data('load',true);
		},
		"loadContanier":function($container,index){
			if(index==-1){
				$container.append('<div><div class="loading"></div></div>');
				index=0;
			}else{
				$container.children().hide().eq(index).show();
			}
	
		},
		"doAdList":function(_obj,_placeId,dataList2){
			// 拼装轮播商品数据
			_obj.empty();
			var dataList=U.dealData(dataList2);
			var swtarget="switchc";
			
		//	var imgStr = "<div class='box-con box-two'>"+
			var imgStr = "<div class='slide jcarousel box-jcarousel'>"+
			              "<div class='slide-trigger'>"+
			              "<a href='javascript:' class='slide-prev' data-rel='"+swtarget+"'></a>"+
			              "<a href='javascript:' class='slide-next' data-rel='"+swtarget+"'></a>"+
                          "</div>"+
			              "<div class='slide-box'>"+
			              "<ul class='jcarousel-container'>";
			$.each(dataList, function(i, n) {
				var k = i + 1;

				//获取作者属性
				var authorValue = doGdsProp(n,"1001");
				authorValue=authorValue?authorValue:'';
				
				imgStr += "<li class='"+swtarget+"'>";
				imgStr += "<a href='"+GLOBAL.WEBROOT+n.url+"'><img src='"+n.mainPic.url+"'></a>";
				imgStr += "<p class=\"name\"><a href='"+GLOBAL.WEBROOT+n.url+"'>"+n.gdsName+"</a></p>";
				imgStr += "<p class=\"author\">"+authorValue+"</p>";
				imgStr += "<p class=\"price\">";
				imgStr += "<span class=\"rob\">"+accDiv(n.skuInfo.realPrice,100)+"</span>";
				imgStr += "<span class=\"price_r\">"+accDiv(n.guidePrice,100)+"</span>";
				imgStr += "</p>";
				imgStr += "</li>";
			});
			    imgStr += "</ul></div></div>";
			    _obj.append(imgStr);
			
				var _trigger=_obj.find('.slide-trigger');
				var _container=_obj.find('.jcarousel-container');
				_trigger.find("a").powerSwitch({
					 number: 4,
	                 classDisabled:'',
	                 container: _container
	            });
				_obj = null;
		},
		"bindTabChange":function(el){
			//绑定tab页切换
			$(".homepage-jc-tt a",el).live("mouseover",function(e){
				//获取楼层商品数据
				$(".homepage-jc-tt a",el).removeClass("active");
				
				$(this).addClass("active");
			
				var opts = $(el).data();
				var tabId =$(this).attr("tabId");
				opts.tabId = tabId;
				var $index=$(this).parent().index();
				if(!$(this).parent().data('load')){
					$(this).parent().data('load',true);
					$homepage_floor.queryGdsByTabId(el,opts,$index);
				}else{
					$(".homepage-jc-body",el).children().hide().eq($index).show();
				}
	
		
			});
		}
	};

	$.fn.electronic_boxJc = function() {
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
			$floorjcarousel.control(this,opts);
			$(this).data("comStatus","end");
			return $(this);
		
		});
	};
}));
