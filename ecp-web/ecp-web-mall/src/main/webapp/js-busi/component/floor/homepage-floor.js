;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([ 'jquery-powerSwitch','ecp-component/do-data' ], function(slide,adData) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".homepage-floor").homepage_floor();
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

	var $homepage_floor = {
		/**
		 * 生成楼层商品信息；
		 * @param el
		 * @param images
		 * @author jiangzh
		 */
		"doData" : function(el, datas) {
			if(datas && $.isPlainObject(datas)){
				//1 获取楼层数据，填充楼层名称
				var floorRespDTO =datas.floorRespDTO;
				var $tabdiv = $(".homepage-floor-tab", el);
				var $gdsdiv = $(".homepage-floor-gds", el);
				if(floorRespDTO && floorRespDTO.id){
					var floorName = $tabdiv.find(".floor-name");
					floorName.append(floorRespDTO.floorName);
					floorName.removeClass("hide");
					floorName.attr('id','floor-'+floorRespDTO.id);
					
					//2 获取楼层页签数据，填充楼层页签
					var floorTabList =datas.floorTabList;
					if(floorTabList && floorTabList.length>0){
						$homepage_floor.doTabList($tabdiv,$gdsdiv,floorTabList);
					}
					
					//3 获取楼层商品数据，填充商品数据
					$homepage_floor.doGdsList($gdsdiv,datas.gdsList,0);
				}else{//没有楼层的处理
					$gdsdiv.empty();
					$gdsdiv.append("<div class ='pro-empty'>亲，这家伙太懒，暂未配置数据！</div>");
				}
			}else{
				$gdsdiv.empty();
				$gdsdiv.append("<div class ='pro-empty'>亲，出错啦！</div>");
			}
		},
		/**
		 * 调用获取数据组件
		 */
		"control":function(el,opts){
			var $container = $(".homepage-floor-gds", el);
		    	$container.empty();
			    $homepage_floor.loadContanier($container,-1);
			$floorGdsData.getData({
				"placeId" : opts.placeId,
				"gdsSize" : opts.gdsSize,
				"tabSize" : opts.tabSize,
				"placeWidth" : opts.placeWidth,
				"placeHeight" : opts.placeHeight,
				"status" : opts.status,
				"callback":function(gds){
					$homepage_floor.doData(el,gds);
					$homepage_floor.bindTabChange(el);
				}
			});
		},
		"doGdsList":function(_obj,dataList2,index){
			
            var _clist=_obj.children().eq(index);
            _clist.empty();
            
            if(dataList2 && dataList2.length > 0){
            	var dataList=U.dealData(dataList2);
            	var str = "";
            	$.each(dataList, function(i, n) {
					//获取作者属性
					var authorValue = doGdsProp(n,"1001");
					authorValue=authorValue?authorValue:'';
					str += "<li>";
					str += "<a "+$homepage_floor.getTarget(n.url)+" href='"+(n.url?(GLOBAL.WEBROOT+n.url):'javascript:void(0)')+"'><img src='"+n.mainPic.url+"'></a>";
					str += "<p class=\"name\"><a "+$homepage_floor.getTarget(n.url)+" href='"+(n.url?(GLOBAL.WEBROOT+n.url):'javascript:void(0)')+"'>"+(n.gdsName?n.gdsName:"")+"</a></p>";
					str += "<p class=\"author\">"+(authorValue?(authorValue+' 著'):'')+"</p>";
					str += "<p class=\"price\">";
					str += "<span class=\"rob\">"+(n.skuInfo.discountPrice?("&yen;"+ebcUtils.numFormat(accDiv(n.skuInfo.discountPrice ,100),2)):"")+"</span>";
					str += "<span class=\"price_r\">"+(n.guidePrice?("&yen;"+ebcUtils.numFormat(accDiv(n.guidePrice,100),2)):"")+"</span>";
					str += "</p>";
					str += "</li>";
				});
            	_clist.append(str);
            	
            }else{//无数据
            	_clist.append("<div class ='pro-empty'>亲，暂无数据！</div>");
            }
			_obj == null;
		},
		"getHref":function(href){
			if(href){
				return href;
			}
			return 'javascript:void(0)';
		},
		"getTarget":function(target){
			if(target){
				return ' target="_blank" ';
			}
			return '';
		},
		"doTabList":function(_obj,_gdsdiv,dataList){
			var str = "<ul class=\"nav-two homepage-floor-ui-tab\">";
			
			var strc="";
			$.each(dataList, function(i, n) {
				str += "<li>";
				str += "<a "+$homepage_floor.getTarget(n.linkUrl)+" href='"+$homepage_floor.getHref(n.linkUrl)+"' tabId="+n.id; 
				strc +='<ul ';
				if(i == 0){
					str +=" class='active'";
				}
				if(i > 0){
					strc+= 'class="hide"';
				}
				str +=">"+n.tabName+"</a></li>";
				strc +='><div class="loading"></div></ul>';
			});
			str += "</ul>";
			_obj.append(str);
			//tab对应的内容
			_gdsdiv.empty().append(strc);
			//第一次加载标志
			_obj.find('.homepage-floor-ui-tab').children().eq(0).data('load',true);
		},
		"loadContanier":function($container,index){
			if(index==-1){
				$container.append('<ul><div class="loading"></div></ul>');
				index=0;
			}else{
				$container.children().hide().eq(index).show();
			}
	
		},
		"queryGdsByTabId":function(el,opts,index){
			 var $container = $(".homepage-floor-gds",el);
			 $homepage_floor.loadContanier($container,index);
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
						$homepage_floor.doGdsList($container,datas.gdsList,index);
					}
				}
			});
		},
		"bindTabChange":function(el){
			//绑定tab页切换
			$(".homepage-floor-tab a",el).live("mouseover",function(e){
				//获取楼层商品数据
				$(".homepage-floor-tab a",el).removeClass("active");
				
				$(this).addClass("active");
			
				var opts = $(el).data();
				var tabId =$(this).attr("tabId");
				opts.tabId = tabId;
				var $index=$(this).parent().index();
				if(!$(this).parent().data('load')){
					$(this).parent().data('load',true);
					$homepage_floor.queryGdsByTabId(el,opts,$index);
				}else{
					$(".homepage-floor-gds",el).children().hide().eq($index).show();
				}
	
		
			});
		}
	};

	$.fn.homepage_floor = function() {
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
			$homepage_floor.control(this,opts);
			
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
