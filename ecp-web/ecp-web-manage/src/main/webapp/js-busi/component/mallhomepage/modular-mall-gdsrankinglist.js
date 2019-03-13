;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([ 'jquery-powerSwitch'], function(slide,adData) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".modular-mall-gdsrankinglist").modular_mall_gdsrankinglist();
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

	var $gdsRankingList = {
		getData : function(opts){
			$.eAjax({
				url : $webroot + '/cmshomepagegetdata/qryFloorList',
				data : {
					"placeId" : opts.placeId,
					"gdsSize" : opts.gdsSize,
					"tabSize" : opts.tabSize,
					"placeWidth" : opts.placeWidth,
					"placeHeight" : opts.placeHeight,
					"status" : opts.status
				},
				async : true,
				type : "post",
				dataType : "json",
				success : function(data, textStatus) {
					if (data == null) {
						return;
					} else {
						if(opts.callback && $.isFunction(opts.callback)){
							opts.callback(data);
						}
					}
				}
			});
		},
		/**
		 * 生成信息
		 * gxq
		 */
		"doData" : function(el, datas) {
			var $container = $("#gds-rankinglist-render", el);
			//tab页签
			 var $floorTabsEl = $(".tab-nav", el);
			 
			if(datas && $.isPlainObject(datas) && datas.floorRespDTO && datas.floorRespDTO.id){//数据正确的处理
				var fistTab = null;
				//获取楼层数据
				var floorRespDTO =datas.floorRespDTO;
				//获取楼层页签数据
				var floorTabList =datas.floorTabList;
				//获取楼层商品数据
				var gdsList =datas.gdsList;
				if(floorTabList && floorTabList.length>0){//有tab页
					 fistTab =  floorTabList[0].id || 'error';
				}else{//无tab页
					$floorTabsEl.remove();
					fistTab = 'notab';
				}
				$gdsRankingList.doTabList($floorTabsEl,floorTabList,$container);
				$gdsRankingList.doGdsList(fistTab,gdsList,$container,el);
			}else{//错误数据
				var $warn = $('<div class="nodata"></div>');
				var $warnSpan = $('<span></span>');
				$warn.append($warnSpan);
				if(!datas){
					$warnSpan.append("无任何数据返回，请检查网络！");
				}else if(datas.errMsg){
					$warnSpan.append(datas.errMsg);
				}else{
					$warnSpan.append('楼层未配置！');
				}
				$(".modular-body",el).replaceWith($warn);
			}
		},
		/**
		 * 调用获取数据组件
		 */
		"control":function(el,opts){
			//加加载动态图
			var $container = $("#gds-rankinglist-render", el);
			$container.empty();
			$container.append("<div class='tpl-loading'></div>");
			//加载数据
			$gdsRankingList.getData({
				"placeId" : opts.placeId,
				"gdsSize" : opts.gdsSize,
				"tabSize" : opts.tabSize,
				"placeWidth" : opts.placeWidth,
				"placeHeight" : opts.placeHeight,
				"status" : opts.status,
				"callback":function(ads){
					$gdsRankingList.doData(el,ads);
					$gdsRankingList.bindTabChange(el);
					$gdsRankingList.bindShowItem(el);
				}
			});
		},
		"doGdsList":function(_tabId,gdsList,$container,el){
			var $contain = $container.find("[tabid="+_tabId+"]");
			var $showAll = $(".more-bd",el).empty();
			var str ="<ul class='list'>";//商品渲染后字符串
			if(_tabId && _tabId != 'error'){//正常的tab  包括无tab页     _tabId ->  'notab'
				//清空内容
				$contain.empty();
				//拼接完整榜单链接的url
				var opts = $(el).data();
				var moreUrl = GLOBAL.MALLSITEURL + '/homepage/rankinglist?placeId='+opts.placeId;
				if(_tabId != 'notab'){//有tab页   完整榜单加上tabid查询字段
					moreUrl += '&tabId='+_tabId;
				}
				
				if(gdsList && gdsList.length > 0){//有数据
					//拼接列表
					$.each(gdsList, function(i, n) {
						n.skuInfo =n.skuInfo || {};
						
						str += "<li class='"+(i==0?"item-fir active":"")+"'>"+
							
	                         "<span class='rank "+($gdsRankingList.getRankNo(i+1))+"'>"+(i+1)+"</span>"+
	                         "<div class='tit'>"+
	                             "<a "+$gdsRankingList.getTarget(n.url)+" href='"+(n.url?(GLOBAL.MALLSITEURL+n.url):'javascript:void(0)')+"'> "+(n.gdsName ? n.gdsName : '')+"</a>"+
	                         "</div>"+
	                         "<div class='item-c'>"+
	                             "<a class='img-wrap' "+$gdsRankingList.getTarget(n.url)+" href=\""+(n.url?(GLOBAL.MALLSITEURL+n.url):'javascript:void(0)')+"\"><img src=\""+( n.mainPic.url ? n.mainPic.url : 'javascript:void' )+"\"></a>"+
	                             "<p class='name'><a "+$gdsRankingList.getTarget(n.url)+" href=\""+(n.url?(GLOBAL.MALLSITEURL+n.url):'javascript:void(0)')+"\">"+(n.gdsName ? n.gdsName : '')+"</a></p>"+
	                             "<p class='rob'>"+(n.skuInfo.discountPrice?('&yen;'+ebcUtils.numFormat(accDiv(n.skuInfo.discountPrice,100),2)):'')+"</p>"+
	                             "<p class='price_r'>"+(n.guidePrice?('&yen;'+ebcUtils.numFormat(accDiv(n.guidePrice,100),2)):'')+"</p>"+
	                         "</div>"+
	                     "</li>";
					});
					str += "</ul>";
					$contain.append(str);
					
					//处理查看完整榜单
					var ifShowAll = $(el).data("ifShowAll");
					if(ifShowAll+"" == "true"){
						$showAll.html("<a style='float:right;' target='_blank' href='"+moreUrl+"'>查看完整榜单&gt;&gt;</a>");
					}
				}else{//没有数据
					$contain.append("<div class ='pro-empty'>亲，暂无数据！</div>");
				}
			}
			
		},
		"doTabList":function($floorTabsEl,floorTabList,$container){
			//拼接tab 以及tab数据div
			var gdsInfoDivs = "";
			if(!floorTabList || floorTabList.length <=0){//没有tab页
				gdsInfoDivs += "<div  class='nItem' tabId = 'notab'></div>";
			}else{//有tab页
				if($floorTabsEl && $floorTabsEl.length > 0){
					$floorTabsEl.empty();
					var strTabs = "<ul>";
					$.each(floorTabList, function(i, n) {
						if(i == 0){
							strTabs += "<li class='active' tabdata = \"already\"><a "+$gdsRankingList.getTarget(n.linkUrl)+" href='"+$gdsRankingList.getHref(n.linkUrl)+"' tabId='"+(n.id?n.id:'error')+"' >"+n.tabName+"</a>";
							strTabs += "</li>";
						}else{
							strTabs += "<li>";
							strTabs += "<a "+$gdsRankingList.getTarget(n.linkUrl)+" href='"+$gdsRankingList.getHref(n.linkUrl)+"' tabId='"+(n.id?n.id:'error')+"' tabdata='' >"+n.tabName+"</a>";
							strTabs += "</li>";
						}
						if(n.id){
							gdsInfoDivs += "<div  class='nItem "+(i==0 ? "":" hide ")+"' tabId = '"+n.id+"'><div class='tpl-loading'></div></div>";
						}
						
					});//end of each
					strTabs += "</ul>";
					$floorTabsEl.empty();
					$floorTabsEl.append(strTabs);
					}
			}
			//添加错误tab的div
			gdsInfoDivs +="<div  class='nItem pro-empty hide' tabId = 'error' >亲，数据被搞丢了！</div>";
			
			//添加数据
			$container.empty();
			$container.append(gdsInfoDivs);
			$floorTabsEl=null;
			$container=null;
			
		},
		"queryGdsByTabId":function(el,opts,tabId){
			$.eAjax({
				url : $webroot + '/ cmshomepagegetdata/queryGdsByTabId',
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
						var $container = $("#gds-rankinglist-render",el);
						$gdsRankingList.doGdsList(tabId,datas.gdsList,$container,el);
						$gdsRankingList.bindShowItem(el);
					}
				}
			});
		},
		"bindShowItem" : function(el){
			$('.tab-content li',$(el)).on('mouseover', function () {
                $(this).addClass('active').siblings().removeClass('active');
            }).on('mouseleave', function (e) {
                if ($(e.relatedTarget).parents('.list').size() > 0) {
                    $(this).removeClass('active');
                }
            });
		},
		"bindTabChange":function(el){
			//绑定tab页切换
			
			$(".tab-nav li",el).live("mouseover",function(e){
				if($(this).hasClass("active")){
					return $(this);
				}
				//隐藏所有tab页
				$(".tab-nav li",el).removeClass("active");
				//隐藏所有gds-info div
				$(".nItem",el).addClass("hide");
				//获取当前tab
				var $thisTab = $(this);
				//获取参数
				var opts = $(el).data();
				var tabId =$("a",$thisTab).attr("tabId");
				opts.tabId = tabId;
				//获取当前tab 的 gds-info div
				var $thisGdsDiv = $("#gds-rankinglist-render",el).find("[tabid="+tabId+"]");
				
				$thisTab.addClass("active");
				$thisGdsDiv.removeClass("hide");
				
				var tabdata = $thisTab.attr("tabdata")
				if (tabdata != "already") {
					$thisTab.attr("tabdata", "already");
					$gdsRankingList.queryGdsByTabId(el, opts,tabId);
				}
			});
		},
		"getHref":function(href){
			if(href){
				return GLOBAL.MALLSITEURL+href;
			}
			return 'javascript:void(0)';
		},
		"getTarget":function(target){
			if(target){
				return ' target="_blank" ';
			}
			return '';
		},
		"getRankNo":function(i){
			switch (+i){
			case 1:return " rank-one ";
					break;
			case 2:return " rank-two ";
			break;
			case 3:return " rank-three ";
			break;
			/*case 4:return " rank-flour ";
			break;
			case 5:return " rank-five ";
			break;*/
			default:
				return " ";
			}
		}
	};

	$.fn.modular_mall_gdsrankinglist = function() {
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
			$gdsRankingList.control(this,opts);
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
