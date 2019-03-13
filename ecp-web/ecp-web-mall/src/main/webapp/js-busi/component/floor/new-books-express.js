;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define(['ecp-component/do-data' ], function(gdsData) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".new-books-express").new_books_express();
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

	$newBooksShader = {
		/**
		 * 生成首页新书速递信息；
		 * @param el
		 * @param 
		 * @author zhanbh
		 */
		"doData" : function(el, datas) {
			var $container = $(".new-books-gds", el);
			if(datas && $.isPlainObject(datas)){//数据正确的处理
				var $floorTabs = null;
				var $floorName = $(".floor-name", el).find("span");
				var $fistTab = null;
				//获取楼层数据
				var floorRespDTO =datas.floorRespDTO;
				//获取楼层页签数据
				var floorTabList =datas.floorTabList;
				//获取楼层商品数据
				var gdsList =datas.gdsList;
				if(floorRespDTO && floorRespDTO.id){//有楼层处理
					$(".floor-name", el).removeClass("hide");//显示楼层名
					if(floorTabList && floorTabList.length>0){//有tab页
						 $floorTabs = $(".floor-tabs", el);
						 $fistTab = floorTabList[0].id ? floorTabList[0].id : 'error';
					}else{//无tab页
						$fistTab = 'notab';
					}
					$newBooksShader.doTabList($floorName,$floorTabs,$container,floorRespDTO,floorTabList);
					$newBooksShader.doGdsList($fistTab,$container,gdsList,el);
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
			var $container = $(".new-books-gds", el);
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
				"callback":function(gds){
					$newBooksShader.doData(el,gds);
					$newBooksShader.bindTabChange(el);
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
//				var opts = $(el).data();
				if(dataList2 && dataList2.length > 0){//有数据
				    var dataList=U.dealData(dataList2);//处理
					//拼接列表
					$.each(dataList, function(i, n) {
//						//获取作者属性
						var authorValue = doGdsProp(n,"1001");
						str += "<li>";
	            		str += "<a target='_blank' href='"+(n.url?(GLOBAL.WEBROOT+n.url):'javascript:void(0)')+"'><img src='"+n.mainPic.url+"'></a>";
	            		str += "<p class=\"name\"><a target='_blank' href='"+(n.url?(GLOBAL.WEBROOT+n.url):'javascript:void(0)')+"'>"+(n.gdsName?n.gdsName:"")+"</a></p>";
	            		str += "<p class=\"author\">"+(authorValue?(authorValue+" 著"):'')+"</p>";
	            		str += "<p class=\"price\">";
	            		str += "<span class=\"rob\">"+(n.skuInfo.discountPrice?("&yen;"+ebcUtils.numFormat(accDiv(n.skuInfo.discountPrice,100),2)):"")+"</span>";
	            		str += "<span class=\"price_r\">"+(n.guidePrice?("&yen;"+ebcUtils.numFormat(accDiv(n.guidePrice,100),2)):'')+"</span>";
	            		str += "</p>";
	            		str += "</li>";
					});
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
					var strTabs = "<ul class=\"new-tab  clearfix\">";
					$.each(tabsList, function(i, n) {
						if(i == 0){
							strTabs += "<li tabId='"+(n.id?n.id:'error')+"' class=\"active\" tabdata = \"already\">";
							strTabs += "<a "+$newBooksShader.getTarget(n.linkUrl)+" href='"+$newBooksShader.getHref(n.linkUrl)+"' >"+n.tabName+"</a>";
							strTabs += "</li>";
						}else{
							strTabs += "<li tabId='"+(n.id?n.id:'error')+"' tabdata=''>";
							strTabs += "<a "+$newBooksShader.getTarget(n.linkUrl)+" href='"+$newBooksShader.getHref(n.linkUrl)+"'  >"+n.tabName+"</a>";
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
			gdsInfoDivs +="<div  class='ecp-gds-info-tab pro-empty hide' tabId = 'error' >亲，页签错误！</div>";
			
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
			return 'javascript:void(0);';
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
						var $container = $(".new-books-gds",el);
						$newBooksShader.doGdsList(tabId,$container,datas.gdsList,el);
					}
				}
			});
		},
		"bindTabChange":function(el){
			//绑定tab页切换
			$(".floor-tabs li",el).live("mouseover",function(e){
				//隐藏所有tab页
				$(".floor-tabs li",el).removeClass("active");
				//隐藏所有new-books-gds div
				$(".ecp-gds-info-tab",el).addClass("hide");
				//获取当前tab
				var $thisTab = $(this);
				//获取参数
				var opts = $(el).data();
				var tabId =$thisTab.attr("tabId");
				opts.tabId = tabId;
				//获取当前tab 的 new-books-gds div
				var $thisGdsDiv = $(".new-books-gds",el).find("[tabid="+tabId+"]");
				
				$thisTab.addClass("active");
				$thisGdsDiv.removeClass("hide");
				
				var tabdata = $thisTab.attr("tabdata")
				if (tabdata != "already") {
					$thisTab.attr("tabdata", "already");
					$newBooksShader.queryGdsByTabId(el, opts,tabId);
				}
			});
		}
	};

	$.fn.new_books_express = function() {
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
			$newBooksShader.control(this,opts);
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
