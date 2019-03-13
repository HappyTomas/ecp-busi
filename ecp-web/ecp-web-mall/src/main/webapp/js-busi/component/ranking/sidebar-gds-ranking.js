;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([ 'jquery-powerSwitch','ecp-component/do-data' ], function(slide,adData) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".sidebar-gds-ranking").sidebar_gds_ranking();
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

	var $sidebarRanking = {
		/**
		 * 生成信息；
		 * @param el
		 * @param 
		 * @author zhanbh
		 */
		"doData" : function(el, datas) {
			var $container = $(".gds-info", el);
			if(datas && $.isPlainObject(datas)){//数据正确的处理
				var $floorTabs = null;
				var $floorName = $floorName = $(".floor-name", el);
				var $fistTab = null;
				//获取楼层数据
				var floorRespDTO =datas.floorRespDTO;
				//获取楼层页签数据
				var floorTabList =datas.floorTabList;
				//获取楼层商品数据
				var gdsList =datas.gdsList;
				if(floorRespDTO && floorRespDTO.id){//有楼层处理
					if(floorTabList && floorTabList.length>0){//有tab页
						 $floorTabs = $(".floor-tabs", el);
						 $fistTab = floorTabList[0].id ? floorTabList[0].id : 'error';
					}else{//无tab页
						$fistTab = 'notab';
					}
					$sidebarRanking.doTabList($floorName,$floorTabs,$container,floorRespDTO,floorTabList);
					$sidebarRanking.doGdsList($fistTab,$container,gdsList,el);
				}else{//没有楼层的处理
					$container.empty();
					$container.append("<div class ='pro-empty'>亲，暂无数据！</div>");
				}
				
			}else{//错误数据
				$container.append("<div class ='pro-empty'>亲，出错了！</div>");
			}
		},
		/**
		 * 调用获取数据组件
		 */
		"control":function(el,opts){
			//加加载动态图
			var $container = $(".gds-info", el);
			$container.empty();
			$container.append("<div class='loading-small'></div>");
			//加载数据
			$floorGdsData.getData({
				"placeId" : opts.placeId,
				"gdsSize" : opts.gdsSize,
				"tabSize" : opts.tabSize,
				"placeWidth" : opts.placeWidth,
				"placeHeight" : opts.placeHeight,
				"status" : opts.status,
				"callback":function(ads){
					$sidebarRanking.doData(el,ads);
					$sidebarRanking.bindTabChange(el);
					$sidebarRanking.bindShowItem(el);
				}
			});
		},
		"doGdsList":function(_tabId,_obj,dataList2,el){

			var $contain = _obj.find("[tabid="+_tabId+"]");
			var str ="";//商品渲染后字符串
			if(_tabId && _tabId != 'error'){//正常的tab  包括无tab页     _tabId ->  'notab'
				//清空内容
				$contain.empty();
//				$contain.removeClass("hide");
				//拼接完整榜单链接的url
				var opts = $(el).data();
				var moreUrl = $webroot + 'homepage/rankinglist?placeId='+opts.placeId;
				if(_tabId != 'notab'){//有tab页   完整榜单加上tabid查询字段
					moreUrl += '&tabId='+_tabId;
				}
				
				if(dataList2 && dataList2.length > 0){//有数据
				    var dataList=U.dealData(dataList2);//处理
					//拼接列表
					$.each(dataList, function(i, n) {
//						//获取作者属性
//						var authorValue = doGdsProp(n,"1001");
						//默认样式
	                      var numberbg = "";          
	                      var dlclass = "clearfix";
	                      if(i == 0){
	                    	  numberbg = "onebg";
	                    	  dlclass = "clearfix boxtb active";
	                      }else if (i == 1){
	                    	  numberbg = "twobg";
	                      }else if (i == 2){
	                    	  numberbg = "threebg";
	                      }
	                      str += "<dl class=\""+dlclass+"\">";  
	                      str += "<dt class=\""+numberbg+"\">"+(i+1)+"</dt>";
                          str += "<dd class=\"order-t\">";
                          str += "<p class=\"name\">"+(n.gdsName ? n.gdsName : '')+"</p></dd>";
                          str += "<dd class=\"order-c\"><a "+$sidebarRanking.getTarget(n.url)+" href=\""+(n.url?(GLOBAL.WEBROOT+n.url):'javascript:void(0)')+"\"><img src=\""+( n.mainPic.url ? n.mainPic.url : 'javascript:void' )+"\"></a>";
                          str += "<p class=\"name\"><a "+$sidebarRanking.getTarget(n.url)+" href=\""+(n.url?(GLOBAL.WEBROOT+n.url):'javascript:void(0)')+"\">"+(n.gdsName ? n.gdsName : '')+"</a></p>";
                          str += "<p class=\"price\"><span class=\"rob\"> "+(n.skuInfo.discountPrice?('&yen;'+ebcUtils.numFormat(accDiv(n.skuInfo.discountPrice,100),2)):'')+"</span></p>";
                          str += "<p class=\"price_r\">"+(n.guidePrice?('&yen;'+ebcUtils.numFormat(accDiv(n.guidePrice,100),2)):'')+"</p></dd></dl>";
					});
					str += "<div class=\"pull-right more-bd\"><a target='_blank' href=\""+ moreUrl +"\"> 查看完整榜单>></a></div>";
					$contain.append(str);
				}else{//没有数据
					$contain.append("<div class ='pro-empty'>亲，暂无数据！</div>");
				}
			}
			
		},
		"doTabList":function(_floorName,_floorTabs,_gdsInfo,respDTO,tabsList){
			_floorName.empty();
			_floorName.text(respDTO.floorName);
			_floorName = null;
			
			//拼接tab 以及tab数据div
			var gdsInfoDivs = "";
			if(!(tabsList && tabsList.length)){//没有tab页
				gdsInfoDivs += "<div  class='ecp-gds-info-tab' tabId = 'notab'></div>";
			}else{//有tab页
				if(_floorTabs){
					_floorTabs.empty();
					var strTabs = "<ul>";
					$.each(tabsList, function(i, n) {
						if(i == 0){
							strTabs += "<li><a "+$sidebarRanking.getTarget(n.linkUrl)+" href='"+$sidebarRanking.getHref(n.linkUrl)+"' tabId='"+(n.id?n.id:'error')+"' class=\"active\" tabdata = \"already\" >"+n.tabName+"</a>";
							strTabs += "</li>";
						}else{
							strTabs += "<li>";
							strTabs += "<a "+$sidebarRanking.getTarget(n.linkUrl)+" href='"+$sidebarRanking.getHref(n.linkUrl)+"' tabId='"+(n.id?n.id:'error')+"' tabdata='' >"+n.tabName+"</a>";
							strTabs += "</li>";
						}
						if(n.id){
							gdsInfoDivs += "<div  class='ecp-gds-info-tab "+(i==0 ? "":" hide ")+"' tabId = '"+n.id+"'><div class='loading-small'></div></div>";
						}
						
					});//end of each
					strTabs += "</ul>";
					_floorTabs.empty();
					_floorTabs.append(strTabs);
					}
			}
			//添加错误tab的div
			gdsInfoDivs +="<div  class='ecp-gds-info-tab pro-empty hide' tabId = 'error' >亲，数据被搞丢了！</div>";
			
			//添加数据
			_gdsInfo.empty();
			_gdsInfo.append(gdsInfoDivs);
			_floorTabs=null;
			_gdsInfo=null;
			
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
		"queryGdsByTabId":function(el,opts,tabId){
			$.eAjax({
				url : $webroot + '/homepage/queryGdsByTabId',
				data : {
					"tabId" : tabId,
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
						var $container = $(".gds-info",el);
						$sidebarRanking.doGdsList(tabId,$container,datas.gdsList,el);
					}
				}
			});
		},
		"bindShowItem" : function(el){
			$('dl',el).live('mouseover', function () {
                $(this).addClass('active').siblings().removeClass('active');
            }).live('mouseleave', function (e) {
                if($(e.relatedTarget).parents('.box-order').size()<0){
                    $(this).removeClass('active');
                }
            });
		},
		"bindTabChange":function(el){
			//绑定tab页切换
			$(".floor-tabs a",el).live("mouseover",function(e){
				
				//隐藏所有tab页
				$(".floor-tabs a",el).removeClass("active");
				//隐藏所有gds-info div
				$(".ecp-gds-info-tab",el).addClass("hide");
				//获取当前tab
				var $thisTab = $(this);
				//获取参数
				var opts = $(el).data();
				var tabId =$thisTab.attr("tabId");
				opts.tabId = tabId;
				//获取当前tab 的 gds-info div
				var $thisGdsDiv = $(".gds-info",el).find("[tabid="+tabId+"]");
				
				$thisTab.addClass("active");
				$thisGdsDiv.removeClass("hide");
				
				var tabdata = $thisTab.attr("tabdata")
				if (tabdata != "already") {
					$thisTab.attr("tabdata", "already");
					$sidebarRanking.queryGdsByTabId(el, opts,tabId);
				}
			});
		}
//		,
//		"bindTabChange":function(el){
//			//绑定查看完整榜单
//			$(".more-bd a",el).live("click",function(e){
//				var opts = $(el).data();
//				opts.tabId
//				var tab = $(".floor-tabs a[class='active']",el);
//				if(tab) {
//					opts.tabId = tab.attr("tabid");
//				}
//				//异步请求
//				$.eAjax({
//					url : $webroot + '/homepage/rankinglist',
//					data : opts,
//					async : false,
//					type : "post",
//					dataType : "json",
//					success : function(datas, textStatus) {
//						alert();
//					}
//				});
//				
//			});
//		}
	};

	$.fn.sidebar_gds_ranking = function() {
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
			$sidebarRanking.control(this,opts);
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
