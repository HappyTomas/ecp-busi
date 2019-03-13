;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define(["ecp-component/wap/cms-url-tool","ecp-component/wap/iScrollUtil"], function(urlTool,iScrollutil) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".modular-wap-mulitFloors").modular_wap_mulitFloors();
				}
			};
		});
	} else {
		// 全局模式 ，不使用AMD 规范的时候，使用的插件
		factory(jQuery);
	}
}(function($) {
	//------以下是数据存储部分-------只有该部分与后台交换，且该部分不与界面交互，是一个类后台   对于一些事件完成会将信息广播出去   不关心信息处理者怎么处理//
	/**
	 * 楼层数据对象构造函数  只有其对应的建造者能修改数据   该类将需多次使用的数据保存起来  没有保留商品信息
	 */
	var MultiFloors = function(opts){
		//datas
		opts = $.extend({},opts);
		var builder = new Builder(this);
		var floors = {"keys":null,"values":null,"flag":null};
		var tabs = {};//tabs[floorId+'']={"keys":keys,"values":values,"flag":flag,"floorId":floorId};
		var tabsIndex = {};//页签楼层索引  方便查取页签数据
		var gdsPageInfo = {}//楼层页签商品的分页信息     {"pageNo":data.pageNo,"lastRowIndex":data.lastRowIndex,"flag":flag}  只有flag为 ’ok‘对上拉加载插件才是有效的  否则都为没有更多数据
		var listeners = {'floor':[],'tab':[],'gds':[]};//对信息感兴趣的观察者
		//private methods
		var activeListener = function(info,data){
			if(!info){
				return false;
			}
			var funs = listeners[info+""] || [];
			var len = funs.length;
			for(var i = 0 ; i < len ; i++){
				if($.isFunction(funs[i])){
					funs[i](data);
				}
			}
			return true;
		}
		//public data
		this.INFOTYPE = {'floor':'floor','tab':"tab",'gds':'gds'};//向外发布的信息类型   表示数据加载完成
		//methods
		this.getBuilder = function(){
			return builder;
		}
		this.getOpts = function(){
			return $.extend({},opts);
		}
		this.setFloors = function(keys,values,flag){
			floors["keys"] = keys;
			floors["values"] = values;
			floors["flag"] = flag;
			activeListener(this.INFOTYPE["floor"],floors);
		}
		this.setTabs = function(floorId,keys,values,flag){
			if(floorId || 0 == floorId){
				tabs[floorId+'']={"keys":keys,"values":values,"flag":flag,"floorId":floorId};
			}
			//设置索引
			keys = keys || [];
			var len = keys.length;
			for(var i = 0 ;i < len; i++){
				var tabId = keys[i];
				if((tabId || 0 == tabId) && "noTab" != tabId){
					tabsIndex[tabId+""] = floorId;
				}
			}
			activeListener(this.INFOTYPE["tab"],tabs[floorId+'']);
		}
		this.setGds = function(floorId,tabId,data,flag){//主要是设置商品分页信息  并将商品信息广播出去
			if(floorId || 0 == floorId){
				if(!tabId && 0 != tabId){
					tabId = "noTab";
				}
				pageInfos = gdsPageInfo[floorId+""] || {};
				gdsPageInfo[floorId+""] = pageInfos;
				pageInfos[tabId + ""] = {"pageNo":data.pageNo,"lastRowIndex":data.lastRowIndex,"flag":flag};
			}
			activeListener(this.INFOTYPE["gds"],{"floorId":floorId,"tabId":tabId,"data":data,"flag":flag});
		}
		this.setListeners = function(info,func){
			listeners[info+""].push(func);
		}
		this.getFloor = function(floorId){
			var floor = null;
			if(floorId || 0 == floorId){
				var values = floors["values"]||{};
				floor =  values[floorId+""]||null;
			}
			return floor;
		}
		this.getTab = function(tabId){
			var tab = null;
			if((tabId || 0 == tabId) && "noTab" != tabId){
				var floorId = tabsIndex[tabId+""];
				if(floorId || 0 == floorId){
					tab = tabs[floorId+""]["values"][tabId+""] || null;
				}
			}
			return tab;
		}
		this.getGdsPageInfo = function(floorId,tabId){
			var pageNo = 0;
			var lastRowIndex = -1;
			var flag = 'ok';
			var floorLevel = gdsPageInfo[floorId+""] || {};
			var pageInfo = floorLevel[tabId+""] || {};
			flag = pageInfo["flag"] || "ok";
			pageNo = pageInfo["pageNo"] || 0;
			lastRowIndex = pageInfo["lastRowIndex"];
			if((!lastRowIndex && 0 != lastRowIndex) || -1 > lastRowIndex){
				lastRowIndex = -1;
			}
			return {"pageNo":pageNo,"lastRowIndex" : lastRowIndex,"flag":flag};
		}
		this.checkFloorTab = function(floorId , tabId){
			//矫正页签与楼层的对应关系数据   注意：对于没有页签的楼层  tabId 会置为noTab  而有页签但没传tabId参数 会将tabId置为该楼层第一个页签的值
			var data = {"floorId":null,"tabId":null,"flag":"pError"}
			if((tabId || 0 == tabId) && "noTab" != tabId){//tab 优先级高
				floorId = tabsIndex[tabId+""] || null;
			}
			if(floorId || 0 == floorId){
				var floorTabs = tabs[floorId + ""];
				if(floorTabs && floorTabs.flag){
					data.floorId = floorId;
					data.flag = floorTabs.flag;
					if("empty" == floorTabs.flag){
						data.tabId = "noTab";
					}else if("ok" == floorTabs.flag){
						if((tabId || 0 == tabId) && "noTab" != tabId){
							data.tabId = tabId;
						}else{
							data.tabId = floorTabs.keys[0];
						}
					}
				}
			}
			return data;
		}
		this.getReqGdsParams = function(floorId,tabId){
			//获取请求后台商品数据时的 请求参数
			var params = null;
			var floorTab = this.checkFloorTab(floorId,tabId);
			if(floorTab){
				floorId = floorTab.floorId;
				tabId = floorTab.tabId;
			}
			var floorInfo = this.getFloor(floorId);
			if(!floorInfo || !floorInfo.id && 0 != floorInfo.id){//楼层数据是必须的
				return null;
			}
			params = {imgWidth:245,imgHeight:245};
			params.staffId = opts.staffId || '';
			params.pageSize = opts.pageSize || 10;
			params.id = floorInfo.id;
			params.catgCode = floorInfo.catgCode || '';
			params.countType = floorInfo.countType || '';
			params.dataSource =  floorInfo.dataSource || '';
			var pageInfo = this.getGdsPageInfo(floorId,tabId);
			params.pageNumber = pageInfo.pageNo + 1;
			params.startRowIndex = pageInfo.lastRowIndex + 1;
			params.flag = pageInfo.flag;
			var tabInfo = null;
			if((tabId || 0 == tabId) && "noTab" != tabId){
				tabInfo = this.getTab(tabId);
			}
			if(tabInfo && (tabInfo.id || 0 == tabInfo.id)){
				params.tabId = tabInfo.id;
				var catgCode = tabInfo.catgCode;
				var countType = tabInfo.countType;
				floorInfo.tabId = tabInfo.id;
				if(catgCode || 0 == catgCode){
					params.catgCode = catgCode;
				}
				if(countType || 0 == countType){
					params.countType = countType;
				}
			}else{
				params.tabId = "";
			}
			return params;
		}
	}
	/**
	 * 楼层数据对象建造者构造函数  对数据结构的建造  一些数据校验处理都在这里完成
	 */
	var Builder = function(product){
		this.setFloors = function(){//设置楼层数据
			var placeId = (product.getOpts() || {})["placeId"];
			if(placeId || 0 == placeId){
				$.eAjax({
					url : GLOBAL.WEBROOT + "/cmshomepagegetdata/getmultifloors",
					data : {
						"placeId" : placeId
					},
					success : function(returnInfo) {
						var keys = null;
						var values = null;
						var flag = "sError";
						if(returnInfo){
							var result = returnInfo.result;
							var returnFlag = returnInfo.resultFlag;
							if("ok" == returnFlag && result){
								var len = result.length || 0;
								keys = [];
								values={};
								for(var i = 0 ; i < len ; i++){
									if(result[i].id || 0 == result[i].id){
										keys.push(result[i].id)
										values[result[i].id + ""] = result[i];
									}
								}
								flag = 0 < keys.length ? "ok" : "empty";
							}else if("empty" == returnFlag){
								flag = "empty";
							}
						}
						product.setFloors(keys,values,flag);
					},
					error:function(){
						product.setFloors(null,null,"cError");
					},
					exception:function(){
						product.setFloors(null,null,"cError");
					}
				});
			}else{
				product.setFloors(null,null,"pError");
			}
		}
		this.setTabs = function(floorId){//设置楼层页签
			if(floorId || 0 == floorId){
				$.eAjax({
					url : GLOBAL.WEBROOT + "/cmshomepagegetdata/gettabsoffloor",
					data : {
						"id" : floorId
					},
					success : function(returnInfo) {
						var keys = null;
						var values = null;
						var flag = "sError";
						if(returnInfo){
							var result = returnInfo.result;
							var returnFlag = returnInfo.resultFlag;
							if("ok" == returnFlag && result){
								var len = result.length || 0;
								keys = [];
								values={};
								for(var i = 0 ; i < len ; i++){
									if(result[i].id || 0 == result[i].id){
										keys.push(result[i].id)
										values[result[i].id + ""] = result[i];
									}
								}
								flag = 0 < keys.length ? "ok" : "empty";
							}else if("empty" == returnFlag){
								flag = "empty";
							}
						}
						product.setTabs(floorId,keys,values,flag);
					},
					error:function(){
						product.setTabs(floorId,null,null,"cError");
					},
					exception:function(){
						product.setTabs(floorId,null,null,"cError");
					}
				});
			}
		}
		this.setGds = function(floorId,tabId){//设置商品
			var params = product.getReqGdsParams(floorId,tabId) || {};
			var data ={"values":null,"pageNo":(params.pageNumber || 1) - 1,"lastRowIndex" : (params.startRowIndex || 0) - 1};//页数默认为请求前数据 ，只有请求成功才会更新页数
			if(!params.id && 0 != params.id){
				product.setGds(floorId,tabId,data,"pError");
				return false;
			}
			$.eAjax({
				url : GLOBAL.WEBROOT + "/cmshomepagegetdata/getfloorgdsofwap",
				data : params,
				success : function(returnInfo) {
					var flag = "sError";
					var pageSize = params.pageSize || 10;
					if(returnInfo){
						var result = returnInfo.result;
						if("ok" == returnInfo.resultFlag && result){
							data.pageNo = returnInfo.pageNumber;
							data.lastRowIndex = returnInfo.lastRowIndex;
							data.values = {"result":result,"collectMap":returnInfo.collectMap,"promMap":returnInfo.promMap};
							var len = result.length || 0;
							flag = 0 < len ? "ok" : "empty";
							if(pageSize > len && 0 < len){
								flag = "end";
							}
						}else if("empty" == returnInfo.resultFlag){
							flag = "empty";
						}
					}
					product.setGds(floorId,tabId,data,flag);
				},
				error:function(){
					product.setGds(floorId,tabId,data,"cError");
				},
				exception:function(){
					product.setGds(floorId,tabId,data,"cError");
				}
			});
			return true;
		}
		this.setGdsforIScroll = function(floorId,tabId,returnInfo){
			//上拉加载插件比较特殊  其会自己请求后台  故这里提供一个专门的接口
			var pageSize = (product.getOpts()||{})["pageSize"] || 10;
			var data = {};
			var flag = "sError";
			if(returnInfo){
				var result = returnInfo.result;
				if("ok" == returnInfo.resultFlag && result){
					data.pageNo = returnInfo.pageNumber;
					data.lastRowIndex = returnInfo.lastRowIndex;
					data.values = {"result":result,"collectMap":returnInfo.collectMap,"promMap":returnInfo.promMap};
					var len = result.length || 0;
					flag = 0 < len ? "ok" : "empty";
					if(pageSize > len && 0 < len){
						flag = "end";
					}
				}else if("empty" == returnInfo.resultFlag){
					flag = "empty";
				}
			}
			product.setGds(floorId,tabId,data,flag);
		}
		this.addListenter = function(info,listenter){//设置观察者
			if(!info || !$.isFunction(listenter) || !product.INFOTYPE.hasOwnProperty(info+"")){
				return false;
			}
			product.setListeners(info,listenter);
			return true;
		}

	}
	//------以下htmlDom显示-------该部分负责与页面交互  为类前端   会将一些信息广播出去  如缺少商品信息  同样对信息处理者怎样处理信息不关心 但一般要有反馈 否则无法完成页面渲染//
	/**
	 * html代码生成器   所有html代码都在这里生成
	 */
	var HtmlProductor = function(displayer){
		var doGdsProp = function(obj,propName){
			var authorValue = "";//作者属性值
			if(!obj || !obj.skuInfo){
				return "";
			}
			var allPropMaps = obj.skuInfo.allPropMaps;
			if(allPropMaps && $.isPlainObject(allPropMaps)){
				var propObj = allPropMaps[propName];
				if(propObj){
					//author = 1001.propName;
					var authorValues = propObj.values;
					if(authorValues && authorValues.length > 0){
						return authorValue = authorValues[0].propValue;
					}
				}
			}
		}
		this.floorHtml = function(data){
			var str = ""
			if(data && data.keys &&  0 < data.keys.length){
				var len = data.keys.length || 0;
				var keys = data.keys;
				var values = data.values;
				str += '<div class="tScroll-wrap"><ul class="prom-tab clearfix">';
				for(var i = 0 ; i < len ; i++){
					var floor = values[keys[i]];
					str += '<li class="tab-item" data-id = "'+floor.id+'"><a href="javascript:void(0);">'+floor.floorName+'</a></li>';
				}
				str += '</ul></div>';
			}
			return str;
		}
		this.tabHtml = function(data){
			var str = "";
			var usefullNum = 0;
			//有页签处理
			if(data && data.keys &&  0 < data.keys.length){
				var len = data.keys.length || 0;
				var keys = data.keys;
				var values = data.values;
				str += '<ul class="key-list  floor-tabs" style="display:none;" data-floor-id="'+data.floorId+'">';
				for(var i = 0 ; i < len ; i++){
					var tab = values[keys[i]];
					if(tab && tab.tabName){
						usefullNum ++;
						str += '<li class="floor-tab" data-id = "'+tab.id+'"><a href="javascript:void(0);">'+tab.tabName+'</a></li>';
					}
				}
				str += '</ul>';
			}
			//空页签处理
			var tabClass = "";
			if(0 <= "pError,cError,sError,error".indexOf(data.flag)){
				tabClass = " data-error ";
			}
			if(0 === usefullNum){
				str = "";
				str += '<ul class="floor-tabs" data-floor-id="'+data.floorId+'">';
					str += '<li class="floor-tab ' + tabClass + '" data-id = "noTab"></li>';
				str += '</ul>';
			}
			return str;
		}
		this.gdsHtml = function(data){
			var str = "";
			if(data && data.values && data.values.result && 0 <= data.values.result.length){
				var gdsInfos = data.values.result;
				//var collectMap = data.values.collectMap || {};//由于跟app公用暂时没有方案
				var promMap = data.values.promMap || {};
				var len = gdsInfos.length;
				for(var i = 0 ; i < len ; i++){
					var gds = gdsInfos[i] || {};
					var id = gds.id;
					if(!id && 0 != id){
						continue;
					}
					var skuInfo = gds.skuInfo ||{};
					//判断是否是电子书或者数字教材
					var platCatgs = skuInfo.platCatgs || "";//分类路径
					//var isDigitbook = false; //是否是数字教材
					//var isEbook = false //是否是电子书
					var isBookStr = '';
					if(-1 < platCatgs.indexOf("<1200>")){//电子书
						//isEbook = true;
						isBookStr = '<div class="rw-icon rw-icon-lele"></div>';
					}else if(-1 < platCatgs.indexOf("<1201>")){//数字教材
						//isDigitbook = true;
						isBookStr = '<div class="rw-icon rw-icon-ltea"></div>';
					}
					/*var cllectionId = collectMap[id+""];
					if(!cllectionId && 0 != cllectionId){
						cllectionId = "";
					}
					cllectionId += "";*/
					var author = doGdsProp(gds,"1001");
					var content = doGdsProp(gds,"1020");
					var mainPic = gds.mainPic || {};
					var promType = promMap[id+""];
					str += '<div class="item">';
					str += '<div class="img-wrap">';
					str += '<a class="gds-link" lazy-href="'+(gds.url||"")+'"><img src="'+mainPic.url+'" alt=""></a>';
					str += isBookStr;
					str += '</div>';
					str += '<div class="item-cont">';
					str += '<p class="p-tit">';
					if(promType){
						str += '<span class="p-color p-color-radius">'+promType+'</span>';
					}
					str += '<a class="gds-link" lazy-href="'+(gds.url||"")+'">'+(gds.gdsName||"")+'</a>';
					str += '</p>';
					str += '<p class="p-tip">'+(gds.gdsDesc|| content || "")+'</p>';
					if(author){
						str += '<p class="p-auto">作者：'+author+'</p>';
					}
					str += '<p class="p-line">';
					str += '<span class="c-orange">'+(skuInfo.discountPrice?("&yen;"+ebcUtils.numFormat(accDiv(skuInfo.discountPrice ,100),2)):"")+'</span>';
					if(skuInfo.appSpecPrice && 0 < skuInfo.appSpecPrice){
						str += '<span class="act act-phone">手机专享</span>';
					}
						//str += '<a><i class="act act-collect '+(cllectionId?" act-collect-sc ":"")+'" data-gds-id = '+id+' data-coll-id = '+cllectionId+'></i></a>';
					str += '</p>';
					str += '</div>';
					str += '</div>';
				}
			}
			return str;
		}
		this.gdsHtmlHead = function(data){
			var str = "";
			var usefullNum = 0;
			if(!data){
				return str;
			}
			if(data.keys &&  0 < data.keys.length){
				var len = data.keys.length || 0;
				var keys = data.keys
				for(var i = 0 ; i < len ; i++){
					var tabId = keys[i];
					if((tabId || 0 == tabId) && "noTab" != tabId){
						usefullNum ++;
						str += '<div class="pro-list floor-gds" data-floor-id="'+data.floorId+'" data-tab-id="'+tabId+'" style="display:none;">';
						str += this.loadingHtml();
						str += '</div>';
					}
				}
			}
			if(0 === usefullNum){
				str += '<div class="pro-list floor-gds" data-floor-id="'+data.floorId+'" style="display:none;">';
				if(0 <= "pError,cError,sError,error".indexOf(data.flag)){
					str += this.emptyHtml(null,"error");
				}else{
					str += this.loadingHtml();
				}
				str += '</div>';
			}
			return str;
		}
		this.emptyHtml = function(msg,flag){
			msg = msg || '暂无数据';
			if(-1 < "pError,cError,sError,error".indexOf(flag)){
				msg = "出了点问题，请稍后再试";
			}
			var str = '<div class="nodata" style="height:90px;text-align:center;padding-top:30px;">';
				    str +='<span style="color:red;">'+msg+'</span>';
			    str +='</div>';
		    return str;	
		}
		this.loadingHtml=function(){
			return "<div class ='data-loading loading-lo'></div>";
		}
	}
	/**
	 * 模块对应的部分dom 显示器  主要负责改变页面展示
	 */
	var Displayer = function(el,opts){
		//private data
		var htmlProductor = new HtmlProductor(this);
		var eventManeger = new EventManager(this);
		var listeners = {'floor':[],'tab':[],'gds':[],"changeGdsShow":[]};//对信息感兴趣的观察者
		var currentShow = {};
		//private method
		var activeListener = function(info,data){
			if(!info){
				return false;
			}
			var funs = listeners[info+""] || [];
			var len = funs.length;
			for(var i = 0 ; i < len ; i++){
				if($.isFunction(funs[i])){
					funs[i](data);
				}
			}
			return true;
		}
		var freshScrollbar = function(){//编辑页才会刷新滚动条  为整个编辑页的滚动条
			var $pageDecorate = $("#pageDecorate");
			//不是发布页 刷新滚动条
			if(opts && !opts.isPub && $.fn.mCustomScrollbar && $pageDecorate && 0 < $pageDecorate.length){
				if($("#pageDecorate").hasClass('mCustomScrollbar')){
					$pageDecorate.mCustomScrollbar('update');
				}else{
					$pageDecorate.mCustomScrollbar({
						scrollInertia: 150,
						advanced: {
							autoScrollOnFocus: false
						}
					});
				}
			}
		}
		var doFloorsSlider = function(){//使楼层名可滑动
			if(!$.AMUI || !$.isFunction($.AMUI.iScroll)){ 
				return false;
			}
			var $tabScroll =$(".tScroll-wrap",$(el));
			var tabHeight = $tabScroll.outerHeight();
			if(tabHeight && 45 < tabHeight){
				var w = 0;
	            $('li',$tabScroll).each(function () {
	                w = w + $(this).outerWidth();
	            });
	            $('ul',$tabScroll).width(w + 10);
	            var $scrollObj = $tabScroll[0];
	            new $.AMUI.iScroll($scrollObj, {
	                scrollX: true,
	                scrollY: false,
	                preventDefault: false
	            });
			}
			return true;
		}
		//public data
		this.INFOTYPE = {'floor':'floor','tab':"tab",'gds':'gds',"changeGdsShow":"changeGdsShow"};//向外发布的信息类型   changeGdsShow表示商品展示区发生变动   其余皆表示缺少对应数据
		this.$el = $(el);
		this.opts = opts;
		
		//public methods
		this.getEventManeger = function(){
			return eventManeger;
		}
		this.turnOn =function(){
			if(0 >= $(".prom-tab",this.$el).length){
				$(".modular-body",this.$el).html(htmlProductor.loadingHtml());
				activeListener(this.INFOTYPE["floor"],null);
			}
		}
		this.showFloors = function(data){
			data = data || {};
			var htmlStr = htmlProductor.floorHtml(data);
			if(htmlStr){
				$(".modular-head",this.$el).html(htmlStr);
				doFloorsSlider();
				if(opts.isPub){
					eventManeger.bindEvent("tab-item","click");
				}
				this.activeFloor();
			}else{//展示无数据
				$(".modular-body",this.$el).html(htmlProductor.emptyHtml(null,data.flag));
			}
			freshScrollbar();
		}
		this.showTabs = function(data){
			var gdsHeadHtml = htmlProductor.gdsHtmlHead(data);
			if(gdsHeadHtml){
				var $modularBody =  $(".modular-body",this.$el);
				$modularBody.children(".data-loading").css("display","none");
				$modularBody.append(gdsHeadHtml);
			}
			var htmlStr = htmlProductor.tabHtml(data);
			if(htmlStr){
				$(".floor-tabs[data-floor-id="+data.floorId+"]",this.$el).remove();
				$(".modular-head",this.$el).append(htmlStr);
				if(opts.isPub){
					eventManeger.bindEvent("floor-tab","click");
				}
				this.activeTab(data.floorId);
				this.activeGds(data.floorId); 
			}
			freshScrollbar();
		}
		this.showGds = function(data){
			data = data || {};
			var isShowing = false;
			var datas = data.data || {};
			var floorId = data.floorId;
			var tabId = data.tabId;
			var htmlStr = htmlProductor.gdsHtml(datas);
			if('noTab' != tabId && ((tabId || 0 == tabId) && "noTab" != tabId)){
				$floorGds = $(".floor-gds[data-tab-id="+tabId+"]",this.$el);
				if(tabId == currentShow.tabId){
					isShowing = true;
				}
			}else{
				$floorGds = $(".floor-gds[data-floor-id="+floorId+"]",this.$el).eq(0);
				if(floorId == currentShow.floorId){
					isShowing = true;
				}
			}
			$(".nodata",$floorGds).remove();
			$(".data-loading",$floorGds).remove();
			if(!htmlStr){
				var $gds = $(".item",$floorGds);
				if(!$gds || 0 >= $gds.length){
					htmlStr = htmlProductor.emptyHtml("",datas.flag);
				}
			}
			if(htmlStr){
				$floorGds.append(htmlStr);
				this.doHref();
			}
			if(isShowing){
				activeListener(this.INFOTYPE["changeGdsShow"],$.extend({},currentShow));
			}
			freshScrollbar();
		}
		this.activeFloor = function(floorId){//将对应的楼层显示为激活状态   注意：只处理楼层部分  不会关联其他部分
			var $floors = $(".prom-tab",this.$el);
			var $floor = null;
			if($floors && 0 < $floors.length){
				if(!floorId && 0 != floorId){
					$floor = $("li" ,$floors).eq(0);
					floorId = $floor.data("id");
				}else{
					$floor = $("li[data-id="+floorId+"]" ,$floors);
				}
				if($floor && 0 < $floor.length){
					if(!$floor.hasClass("active")){
						$floor.siblings().removeClass("active");
						$floor.addClass("active");
					}
					if(!$floor.hasClass("loaded-tabs")){
						$floor.addClass("loaded-tabs");
						activeListener(this.INFOTYPE["tab"],floorId);
					}
				}
			}
		}
		this.activeTab = function(floorId , tabId){//将对应的页签显示出来  注意：只处理楼层部分  不会关联其他部分
			if(!floorId && 0 != floorId && !tabId && 0 != tabId){
				return false;
			}
			var $tab = null;
			var $tabs = null;
			if((tabId || 0 == tabId) && "noTab" != tabId){
				$tab = $(".floor-tab[data-id="+tabId+"]",this.$el);
				$tabs = $tab.closest(".floor-tabs");
				floorId = $tabs.data("floorId");
			}else{
				$tabs = $(".floor-tabs[data-floor-id="+floorId+"]",this.$el);
				$tab = $tabs.find(".floor-tab").eq(0);
				tabId = $tab.data("id");
			}
			if(0 >= $tabs.length || "none" == $tabs.css("display")){
				$(".floor-tabs",this.$el).css("display","none");
				$tabs.css("display","");
			}
			if(!$tab.hasClass("gray-b")){
				$tab.siblings().removeClass("gray-b");
				$tab.addClass("gray-b");
			}
			if($tab && 0 < $tab.length && !$tab.hasClass("error-data") && !$tab.hasClass("loaded-gds")){
				$tab.addClass("loaded-gds");
				activeListener(this.INFOTYPE["gds"],{"floorId":floorId,"tabId":tabId});
			}
		}
		this.activeGds = function(floorId , tabId){//将对应的商品显示出来
			if(!floorId && 0 != floorId && !tabId && 0 != tabId){
				return false;
			}
			var $floorGds = null;
			if((tabId || 0 == tabId) && "noTab" != tabId){
				$floorGds = $(".floor-gds[data-tab-id="+tabId+"]",this.$el);
			}else{
				$floorGds = $(".floor-gds[data-floor-id="+floorId+"]",this.$el).eq(0);
			}
			floorId = $floorGds.data("floorId");
			tabId = $floorGds.data("tabId");
			if(!$floorGds || 0 >= $floorGds.length ||"none" == $floorGds.css("display")){
				$(".floor-gds",this.$el).css("display","none");
				$floorGds.css("display","");
				currentShow.floorId = floorId;
				currentShow.tabId= tabId;
				activeListener(this.INFOTYPE["changeGdsShow"],{"floorId":floorId,"tabId":tabId});
			}
		}
		this.doHref = function(){//处理商品链接   只有发布页会加点击链接
			var webRoot = opts.webRoot || $webroot ||"";
			var $el = $(el);
			if(opts && opts.isPub && $el && $el.length > 0){
				var $linkList = $(".gds-link[lazy-href]",$el);
				$linkList.each(function(){
					var href = $(this).attr("lazy-href");
					$(this).removeAttr("lazy-href");
					if(href){
						$(this).attr("href",_eCmsUrlTool.getHtmlAbsUrl(webRoot,href));
					}
				});
			}
		}
		this.addListenter = function(info,listenter){//设置观察者
			if(!info || !$.isFunction(listenter) || !this.INFOTYPE.hasOwnProperty(info+"")){
				return false;
			}
			listeners[info+""].push(listenter);
			return true;
		}
	}
	/**
	 * 事件管理器
	 */
	var EventManager = function(displayer){
		var eventPool = {//事件池  其中将对应dom作为第一个参数传入 事件对象作为第二个参数  对应显示器对象作为第三个参数
			"tab-item":{//用于定位的类名  楼层项目
				"click":[//事件类型
				         function($this,event,displayer){
				        	 event.stopPropagation();
				        	 var floorId = $this.data("id");
				        	 if(floorId || 0 == floorId){
				        		 displayer.activeFloor(floorId); 
				        		 displayer.activeTab(floorId); 
				        		 displayer.activeGds(floorId); 
				        	 }
				         }
				    ]
				},
			"floor-tab":{//用于定位的类名   页签项目
				"click":[//事件类型
				         function($this,event,displayer){
				        	 event.stopPropagation();
				        	 var tabId = $this.data("id");
				        	 if((tabId || 0 == tabId) && "noTab" != tabId){
				        		 displayer.activeTab(null,tabId); 
				        		 displayer.activeGds(null,tabId);
				        	 }
				         }
				    ]
				}
		};
		this.bindEvent = function(className,eventType){//绑定事件  只绑定还没绑定过的
			if(!className || !eventType){
				return false;
			}
			var $el = displayer.$el;
			var funcList = eventPool[className][eventType];
			if(!funcList || 0 >= funcList.length){
				return false;
			}
			var $doms = $("."+className,$el).not("binded-"+className);
			if($doms && 0 < $doms.length){
				$doms.bind(eventType,function(){
					var $this = $(this);
					if($this.hasClass(className)){//只有拥有对应类名  才会执行 不支持不执行指定方法
						var len = funcList.length;
						for(var i = 0 ; i < len ; i++){
							var func = funcList[i];
							if($.isFunction(func)){
								func($this,event,displayer);
							}
						}
					}
				});
				$doms.addClass("binded-"+className);
			}
			return true;
		}
		this.addToPool = function(className,eventType,func){//将函数放入事件池  一旦放入就可生效
			if(!className || !eventType || !$.isFunction(func)){
				return false;
			}
			var classEvent = eventPool[className] = eventPool[className] || {};
			var eventList = classEvent[eventType] = classEvent[eventType] || [];
			eventList.push(func);
			return true;
		}
	}
	var _iScroller = (function(){//下拉上拉事件器
		var scroller = null;
		return {
			"initScroll":function(callback){
				$('#page-content').height($(window).height()-$('.am-header').height()-$(".am-navbar").height());
				scroller = new LoadScroll("page-content", {
		            url: $webroot + '/cmshomepagegetdata/getfloorgdsofwap',
		            type:"get",
		            dataType: 'json',
		            isDownRefresh:false,//不启用下拉刷新
		            keepOrderReq:true,
		            params:null,
		            callback:function(els,returnInfo){
		            	if($.isFunction(callback)){
		            		var floorId = null;
							var tabId = null;
							if(returnInfo){
								floorId = returnInfo.floorId;
								tabId = returnInfo.tabId;
							}
							if(floorId || 0 == floorId || ((tabId || 0 == tabId) && "noTab" != tabId)){
								callback(returnInfo);
							}else{
								_iScroller.setNoData();
							}
		            	}
		            }
		        });
				_iScroller.setNoData();
			},
			"resetParams":function(params){
				scroller && scroller.setCount(1000);
				scroller && scroller.refreshParams(params);
			},
			"refreshScroll":function(){
				scroller && scroller.refresh();
			},
			"setNoData":function(){
				scroller && scroller.setCount(-1);
			}
		}
	})();
	//------以下是controller-------//
	$modularWapMulitFloors = {//中间层控制器
			"control":function(el,opts){
				//创建对象
				var multiFloors = new MultiFloors(opts);
				var builder = multiFloors.getBuilder();
				var displayer = new Displayer(el,opts);
				
				/**
				 * 对象间信息订阅关系绑定    注意：对象间信息的传播依赖与信息的数据结构 
				 */
				//设置对类后台信息感兴趣的观察者
				builder.addListenter(multiFloors.INFOTYPE.floor, function(data){
					displayer.showFloors(data);
				});
				builder.addListenter(multiFloors.INFOTYPE.tab, function(data){
					displayer.showTabs(data);
				});
				builder.addListenter(multiFloors.INFOTYPE.gds, function(data){
					displayer.showGds(data);
				});
				
				//设置对类前台信息感兴趣的观察者
				displayer.addListenter(displayer.INFOTYPE.floor, builder.setFloors);
				displayer.addListenter(displayer.INFOTYPE.tab, function(floorId){
					builder.setTabs(floorId);
				});
				displayer.addListenter(displayer.INFOTYPE.gds, function(data){
					data = data || {};
					builder.setGds(data.floorId,data.tabId);
				});
				
				//上拉加载插件的一些处理   发布页面才会有的效果
				if(opts.isPub){
					_iScroller.initScroll(function(returnInfo){
						builder.setGdsforIScroll(returnInfo.floorId,returnInfo.tabId,returnInfo);
					});
					displayer.addListenter(displayer.INFOTYPE.changeGdsShow, function(data){
						data = data || {};
						var param = multiFloors.getReqGdsParams(data.floorId,data.tabId);
						if(param && 'ok' == param.flag){
							_iScroller.resetParams(param);
						}else{
							_iScroller.setNoData();
						}
						_iScroller.refreshScroll();
					});
				}
				
				//打开显示器
				displayer.turnOn();
			}
	}
	
	$.fn.modular_wap_mulitFloors = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){
			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			var opts = $.ecpPlugin.parseOptions(this, null);
			$modularWapMulitFloors.control(this,opts);   
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));