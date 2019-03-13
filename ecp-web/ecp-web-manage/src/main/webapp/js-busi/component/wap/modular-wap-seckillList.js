;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define(["ecp-component/wap/cms-url-tool","ecp-component/wap/iScrollUtil"], function(urlTool,iScrollUtil) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".modular-wap-seckillList").modular_wap_seckillList();
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
	};
	var _tools = (function(){//工具
		return{
			"arrayFilter":function(array,filter){
				if(!array || Object.prototype.toString.call(array) !== '[object Array]'){
					return array;
				}   
				filter = $.isFunction(filter)?filter:$.noop;
				var r = [];
				var len = array.length;
			    for (var i = 0; i < len; i++) {
			        if (filter(array[i])) {
			            r.push(array[i]);
			        }
			    }
			    return r;
			},
			"addObservers":function(obj,name,observer,updateSort){
				if(typeof obj !== "object" || !name || !$.isFunction(observer)){
					return false;
				}
				name = name+'';
				var keys = obj.keys = obj.keys || [];
				var obses = obj.observers = obj.observers || {};
				var index = keys.indexOf(name);
				if(0 <= index){
					if(updateSort){
						keys[index]=null;
						_tools.arrayFilter(keys, function(a){
							return a;
						});
						keys.push(name);
					}
				}else{
					keys.push(name);
				}
				obses[name] = observer;
				return true;
			},
			"remObservers":function(obj,name){
				if(!obj || !name){
					return false;
				}
				var keys = obj.keys || [];
				var obses = obj.observers || {};
				var index = keys.indexOf(name);
				if(0 <= index){
					keys[index]=null;
					_tools.arrayFilter(keys, function(a){
						return a;
					});
					delete obses[name];
				}
				return true;
			},
			"clearObservers":function(obj){
				if(typeof obj !== "object"){
					obj = {};
				}
				obj.keys = [];
				obj.observers = {};
			},
			"activeObservers":function(obj,info){
				if(!obj){
					return false;
				}
				var keys = obj.keys || [];
				var obses = obj.observers || {};
				var len = keys.length;
				for(var i = 0 ;i < len;i++){
					var name = keys[i];
					if(name || $.isFunction(obses[name])){
						obses[name](info);
					}
				}
			}
		}
	})();
	var _datas =(function(){//程序运行必须数据
		var siteId = null;
		var isPc = false;
		var isPub = false;
		var webRoot = $webroot||"";
		var $el = null;
		var themes = {};
		var currThemeId = null;
		return {
			"setIsPub":function(p){
				isPub = p?true:false;
			},
			"getIsPub":function(){
				return isPub;
			},
			"setWebRoot":function(p){
				webRoot	= p ||$webroot||'';		
			},
			"getWebRoot":function(){
				return webRoot;
			},
			"setIsPc":function(p){
				isPc = p?true:false;
			},
			"getIsPc":function(){
				return isPc;
			},
			"setSiteId":function(p){
				siteId = p;
			},
			"getSiteId":function(){
				return siteId;
			},
			"setEl":function(el){
				if(el && 0 < $(el).length){
					$el = $(el);
				}
			},
			"getEl":function(){
				return $el;
			},
			"setTheme":function(themeId,obj){
				if(themeId && 0 < themeId && obj && obj.startTime && obj.endTime && obj.promTheme){
					themes[themeId+""] = {
							"id":themeId,
							"startTime":obj.startTime,
							"endTime" : obj.endTime,
							"promTheme" : obj.promTheme
					}
				}
			},
			"getTheme":function(themeId){
					return themeId ? $.extend({},themes[themeId]) : $.extend({},themes);
			},
			"getCurrTheme":function(){
				return currThemeId;
			},
			"setCurrTheme":function(id){
				id = parseInt(id);
				if(!id===id || 0 > id){
					return false;
				}
				currThemeId = id+"";
				return true;
			}
			
		}
	})();
	var _themeTab =(function(){//秒杀主题页签显示器
			var callbacks = {//对秒杀主题点击事件感兴趣的观察者队列
					keys:[],//观察者名称，用于保证顺序执行
					observers:{}//观察者队列
			};
			return {
				"addCallBack":function(name,callback,updateSort){
					if(!name || !callback || !$.isFunction(callback)){
						return false;
					}
					_tools.addObservers(callbacks, name, callback, updateSort);
				},
				"clearCallBacks":function(){
					_tools.clearObservers(callbacks);
				},
				"getThemeDatas":function(callback){
					if(!_datas.getSiteId()){
						return false;
					}
					if(!callback || !$.isFunction(callback)){
						callback = $.noop;
					}
					$.eAjax({
						url : $webroot + "commonmodular/getPromTabList",
						data : {siteId : _datas.getSiteId()},
						success : function(returnInfo){
							if(returnInfo && returnInfo.length && 0 < returnInfo.length){
								for(var i in returnInfo){
									var theme = returnInfo[i];
									if(!theme || !theme.id || 0 > theme.id){
										continue;
									}
									_datas.setTheme(theme.id, theme);
								}
							}
							callback(returnInfo);
						},
						exception:function(){
							callback(null);
						},
						error:function(){
							callback(null);
						}
					});
					return true;
				},
				"themeShader":function(){
					var themeList = _datas.getTheme();
					var $el = _datas.getEl();
					if(null == $el || !themeList || 0 >= Object.getOwnPropertyNames(themeList).length){
						$modularWapSeckillList.noDataShow("暂无活动,敬请期待",$el);
						return false;
					}
					var $tab = $("ul",".tabs-scroll",$el);
					if(!$tab || 0 >= $tab.length){
						return false;
					}
					$tab.empty();
					for(var theme in themeList){
						var liStr = "";
						liStr += "<li data-prom-id = '"+theme+"'><a href='javascript: void(0);'>";
						liStr += themeList[theme].promTheme||"";
						liStr += "</a></li>";
						$tab.append(liStr);
					}
					return true;
				},
				"bindTabSlider":function(){
					var $el = _datas.getEl();
					if(null == $el || !$.AMUI || !$.isFunction($.AMUI.iScroll)){ //
						return false;
					}
					var $tabScroll =$(".tabs-scroll",$el);
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
				},
				"bindTabsClick":function(){
					var $el = _datas.getEl();
					if(null == $el){ //
						return false;
					}
					var $tabs = $("li",".tabs-scroll",$el);
					if(!$tabs || 0 >= $tabs.length){
						return false;
					}
					$tabs.unbind("click.sk").bind("click.sk",function(){
						var $this = $(this);
						if($this.hasClass("am-active")){
							return false;
						}
						$tabs.filter(".am-active").removeClass("am-active");
						$this.addClass("am-active");
						var themeId = $this.data("promId");
						if(!themeId || !callbacks || 0 >= Object.getOwnPropertyNames(callbacks).length){
							return 0;
						}
						_datas.setCurrTheme(themeId);
						
						//通知观察者
						_tools.activeObservers(callbacks, _datas.getTheme(themeId+""));
					});
				},
				"unbindTabsClick":function(){
					var $el = _datas.getEl();
					if(null == $el){ 
						return false;
					}
					var $tabs = $("li",".tabs-scroll",$el);
					if(!$tabs || 0 >= $tabs.length){
						return false;
					}
					$tabs.unbind("click.sk");
				},
				"activeTab":function(index){
					index = parseInt(index) || 0;
					index = 0 <= index ?index:0;
					var $el = _datas.getEl();
					if(null != $el){ 
						$("li",".tabs-scroll",$el).eq(index).trigger("click.sk");
					}
				},
				"bindTabSticky":function(){
					var $el = _datas.getEl();
					if(null == $el){ 
						return false;
					}
					$(".tabs-scroll",$el).sticky({});
				}
			}
	})();
	var _timeShower =(function(){//倒计时显示器
		var STATICTIMES = [86400000,3600000,60000,1000];
		var callbacks = {//对时间计时器的状态字段flag改变感兴趣的观察者队列
				keys:[],//观察者名称，用于保证顺序执行
				observers:{}//观察者队列
		};
		var countTimer=null 
		var timeDiff = 0;
		
		var flag = null;//0 ：开始倒计时   1 结束倒计时     2 已结束  不计时
		var startTime = 0; //开始时间
		var endTime = 0; //结束时间
		return {
			"getStartTime":function(){
				return startTime;
			},
			"getEndTime":function(){
				return endTime;
			},
			"setTimeDiff":function(time){
				var time = parseInt(time);
				if(time!==time || 0 >= time){
					return false;
				}
					
				timeDiff = (new Date()).getTime() - time;
				
				return true;
			},
			"setFlag":function(p){
				p = parseInt(p);
				if( !p===p || 0 > p || 2 < p){
					flag = null;
					return 0;
				}
				var temp = flag;
				flag = p;
				if(!callbacks || temp == flag){
					return 2;
				}
				//通知观察者
				_tools.activeObservers(callbacks, {"flag":flag,"startTime":startTime,"endTime":endTime});
				return 1;
			},
			"addCallBack":function(name,callback,updateSort){
				if(!name || !callback || !$.isFunction(callback)){
					return false;
				}
				_tools.addObservers(callbacks, name, callback, updateSort);
			},
			"clearCallBacks":function(){
				_tools.clearObservers(callbacks);
			},
			"setTimes":function(sTime,eTime){
				sTime = parseInt(sTime) || 0;
				eTime = parseInt(eTime) || 0;
				if(sTime > eTime){
					sTime = eTime;
				}
				startTime = sTime;
				endTime = eTime;
			},
			"getTimeStatus":function(){
				var status ={};
				if(0 == endTime){
					status.flag = 2;
					status.time = 0;
					return status;
				}
				var nowTime = (new Date()).getTime() - timeDiff;
				
				if(STATICTIMES[3] < startTime - nowTime){
					status.flag = 0;
					status.time = startTime - nowTime;
				}else if(nowTime - endTime >= -STATICTIMES[3]){
					status.flag = 2;
					status.time = 0;
				}else{
					status.flag = 1;
					status.time = endTime - nowTime;
				}
				return status;
			},
			"timeFormat":function(time){
				var fomat = [];
				time = parseInt(time)||0;
				time = 0 <= time?time:0;
				
				var floor = 0;
				var tail = time;
				for(var i =0; i< 4; i++){
				    floor = tail?Math.floor(tail/STATICTIMES[i]):0;
				    tail=tail % STATICTIMES[i];
				    fomat[i] = 10 > floor ? "0"+floor:""+floor;
				}
				return fomat;
			},
			"timeShader":function(){
				var $el = _datas.getEl();
				if(null == $el){ 
					return false;
				}
				var $shader = $(".seckill-product-header",$el);
				if(!$shader || 0 >= $shader.length){
					return false;
				}
				var timeStatus = _timeShower.getTimeStatus();
				var timeFomat = _timeShower.timeFormat(timeStatus.time);
				var statusName = "";
				var leafName = "";
				var className = ""
				switch(timeStatus.flag){
					case 0:
						statusName = "即将开始";
						leafName = "距开始";
						break;
					case 1:
						statusName = "抢购中，先抢先得";
						leafName = "距结束";
						break;
					case 2:
						statusName = "已结束，谢谢惠顾";
						leafName = "距结束";
						className = "seckill-over";
						break;
				}
				$shader.removeClass("seckill-over").addClass(className);
				$(".status-name",$shader).html(statusName||"");
				$(".leaf-name",$shader).html(leafName||"");
				$(".leaf-days",$shader).html(timeFomat[0]||"00");
				$(".leaf-hours",$shader).html(timeFomat[1]||"00");
				$(".leaf-mins",$shader).html(timeFomat[2]||"00");
				$(".leaf-secs",$shader).html(timeFomat[3]||"00");
				_timeShower.setFlag(timeStatus.flag);
				return true;
			},
			"timeCounter":function(){
				countTimer && clearInterval(countTimer);
				var lastLocTime = (new Date()).getTime();
				countTimer =setInterval(function(){
					var nowLocTime = (new Date()).getTime();
					var twoTimeDiff = nowLocTime - lastLocTime;
					lastLocTime = nowLocTime;
					if(twoTimeDiff && (5000 <= twoTimeDiff || 0 >= twoTimeDiff)){//两次时间差晚5秒 或早1秒以上 则认为被调了本地时间，进行校正
						timeDiff += twoTimeDiff - 1000;
					}
					if(2 != flag){
						_timeShower.timeShader();
					}
				},1000);
			}
		}
	})();
	var _gdsHander =(function(){//商品处理器
		var callbacks = {//对商品渲染感兴趣的观察者队列
				keys:[],//观察者名称，用于保证顺序执行
				observers:{}//观察者队列
		};
		var loadCallbacks = {//对商品数据加载完成感兴趣的观察者队列
				keys:[],//观察者名称，用于保证顺序执行
				observers:{}//观察者队列
		};
		return {
			"addCallBack":function(name,callback,updateSort){
				if(!name || !callback || !$.isFunction(callback)){
					return false;
				}
				_tools.addObservers(callbacks, name, callback, updateSort);
			},
			"clearCallBacks":function(){
				_tools.clearObservers(callbacks);
			},
			"addLoadCallBack":function(name,callback,updateSort){
				if(!name || !callback || !$.isFunction(callback)){
					return false;
				}
				_tools.addObservers(loadCallbacks, name, callback, updateSort);
			},
			"clearLoadCallBacks":function(){
				_tools.clearObservers(loadCallbacks);
			},
			"getGdsDatas":function(){
				var promId = _datas.getCurrTheme();
				if(null == promId){
					return false;
				}
				
				$.eAjax({
					url : $webroot + "commonmodular/getSecKillInfo",
					data : {
						promId : promId,
						pageSize:_iScroller.getPageSize(),
						pageNo:_iScroller.getPageNo(),
						imgType:"_160x100!"
							},
					success : function(resultInfo){
						//通知观察者
						_tools.activeObservers(loadCallbacks,resultInfo);
					},
					exception:function(){
						//通知观察者
						_tools.activeObservers(loadCallbacks,null);
					},
					error:function(){
						//通知观察者
						_tools.activeObservers(loadCallbacks,null);
					}
				});
				return true;
			},
			"gdsShader":function(gdsList,isInit){
				var $el = _datas.getEl();
				if(null == $el){
					return false;
				}
				var $gdsContent = $("ul",".seckill-product-body",$el);
				if(!$gdsContent || 0 >= $gdsContent.length){
					return false;
				}
				gdsList = gdsList || [];
				
				isInit && $gdsContent.empty();
				if(0 >= gdsList.length){
					isInit && $modularWapSeckillList.noDataShow("该场暂无商品,敬请期待",$gdsContent);
				}else{
					var timeStatus = _timeShower.getTimeStatus(); 
					var startTime = new Date(_timeShower.getStartTime());
					for(var i = 0;i< gdsList.length; i++){
						var gdsInfo = gdsList[i];
						if(!gdsInfo.gdsId || !gdsInfo.gdsName){
							continue;
						}
						var gdsStr = "";
						gdsStr += "<li class='seckill-item unbind-click' data-detail-url = '"+(gdsInfo.detailURL ||"")+"'>";
						gdsStr += 	 "<div class='img-wrap'>";
						gdsStr += 		 "<img src='"+gdsInfo.url+"' alt='"+gdsInfo.gdsName+"' />";
						gdsStr += 	 "</div>";
						gdsStr +=    "<div class='box-wrap'>";
						gdsStr +=        "<h4>"+gdsInfo.gdsName+"</h4>";
						gdsStr +=        "<div class='price'>";
						gdsStr +=           "<span class='p-primary'>"+(gdsInfo.basePrice?("原价：<i>&yen;</i>"+ebcUtils.numFormat(accDiv(gdsInfo.basePrice,100),2)):"")+"</span>";
						gdsStr +=           "<span class='p-seckill'>"+(gdsInfo.killPrice?("秒杀价：<i>&yen;</i><em class='p-price'>"+ebcUtils.numFormat(accDiv(gdsInfo.killPrice,100),2))+"</em>":"")+"</span>";
						switch(timeStatus.flag){
						case 0:
							gdsStr +=       "<span  class='pbtn pbtn-start kill-flag'>即将开始</span>";
							break;
						case 1:
							if(gdsInfo.percent && 100 == gdsInfo.percent){
								gdsStr +=   "<span class='pbtn pbtn-soldOut kill-flag'>已抢完</span>";
							}else{
								gdsStr +=   "<span  class='pbtn kill-flag'>马上抢</span>";
							}
							break;
						case 2:
							gdsStr +=       "<span class='pbtn pbtn-soldOut kill-flag'>已结束</span>";
							break;
						}
						gdsStr +=        "</div>";
						gdsStr +=        "<div class='progress-box'>";
						switch(timeStatus.flag){
						case 0:
							gdsStr +=        "<div class='tips'>"+ebcDate.dateFormat(startTime , "yyyy年MM月dd日 hh:mm")+" 正式开抢</div>";
							break;
						case 1:
						case 2:
							if(false === gdsInfo.gdsTypeFlag){
								break;
							}
							gdsStr +=        "<div class='progressBar'>";
							gdsStr +=            "<div class='num'>";
							gdsStr +=               "<span> 已抢购"+(gdsInfo.percent||0)+"%</span>";
							gdsStr +=            "</div>";
							gdsStr +=            "<div class='progress'>";
							gdsStr +=                "<div class='bar' style='width: "+(gdsInfo.percent||0)+"%'></div>";
							gdsStr +=            "</div>";
							gdsStr +=         "</div>";
							break;
						}
						gdsStr +=        "</div>";
						gdsStr +=     "</div>";
						gdsStr += "</li>";
						
						$gdsContent.append(gdsStr);
					}
				}
				
				//通知观察者
				_tools.activeObservers(callbacks,gdsList);
				return true;
			},
			"resetGdsFlag":function(info){
				var $el = _datas.getEl();
				var flag = parseInt((info && info.flag))
				if(null == $el || !flag===flag || 0 > flag || 2 < flag ){
					return false;
				}
				var flagClass = "";
				var flagText = "";
				switch(flag){
				case 0:
					flagClass = "pbtn-start";
					flagText = "即将开始";
					break;
				case 1:
					flagClass = "";
					flagText = "马上抢";
					break;
				case 2:
					flagClass = "pbtn-soldOut";
					flagText = "已结束";
					break;
				}
				$(".kill-flag",".seckill-product-body",$el).removeClass("pbtn-soldOut").removeClass("pbtn-start").addClass(flagClass).text(flagText);
			},
			"bindGdsClick":function(){
				var $el = _datas.getEl();
				if(null == $el){
					return false;
				}
				var $gdsLis = $("li.unbind-click",".seckill-product-body",$el);
				if(!$gdsLis || 0 >= $gdsLis.length){
					return false;
				}
				$gdsLis.removeClass("unbind-click").unbind("click.gd").bind("click.gd",function(){
					var url = $(this).data("detailUrl");
					url && (location.href = _eCmsUrlTool.getHtmlAbsUrl(_datas.getWebRoot(),url));
				});
			}
		}
	})();
	var _iScroller = (function(){//下拉上拉事件器
		var loadCallbacks = {//对上啦加载商品数据加载完成感兴趣的观察者队列
				keys:[],//观察者名称，用于保证顺序执行
				observers:{}//观察者队列
		};
		
		var pageNo = 1;
		var pageSize = 10;
		var scroller = null;
		return {
			"addLoadCallBack":function(name,callback,updateSort){
				if(!name || !callback || !$.isFunction(callback)){
					return false;
				}
				_tools.addObservers(loadCallbacks, name, callback, updateSort);
			},
			"clearLoadCallBacks":function(){
				_tools.clearObservers(loadCallbacks);
			},
			"getPageNo":function(){
				return pageNo;
			},
			"setPageNo":function(no){
				no = parseInt(no);
				if(!no===no || 0 > no){
					return false;
				}
				pageNo = no;
			},
			"pageGoing":function(){
				pageNo += 1;
			},
			"getPageSize":function(){
				return pageSize;
			},
			"setPageSize":function(size){
				size = parseInt(size);
				if(!size===size || 0 > size){
					return false;
				}
				pageSize = size;
			},
			"initScroll":function(){
				var themeList = _datas.getTheme();
				if(!_datas.getIsPub() || !themeList || 0 >= Object.getOwnPropertyNames(themeList).length){
					return false;
				}
				$('#page-content').height($(window).height()-$('.am-header').height()-$(".am-navbar").height());
				scroller = new LoadScroll("page-content", {
		            url: $webroot + '/commonmodular/getSecKillInfo',
		            type:"get",
		            dataType: 'json',
		            isDownRefresh:false,//不启用下拉刷新
		            params:{
							promId : _datas.getCurrTheme(),
							pageSize:_iScroller.getPageSize(),
							pageNo:2,
							imgType:"_160x100!"
		            },
		            callback:function(els,resultInfo){
		            	//通知观察者
						_tools.activeObservers(loadCallbacks,resultInfo);
		            }
		        });
				return true;
			},
			"resetParams":function(){
				var promId = _datas.getCurrTheme();
				if(!promId && 0 != promId){
					return false;
				}
				scroller && scroller.setCount(1000);
				scroller && scroller.refreshParams({
					promId : promId,
					pageSize:_iScroller.getPageSize(),
					pageNo:2
				});
				return true;
			},
			"refreshScroll":function(){
				scroller && scroller.refresh();
			},
			"setNoData":function(gdsList){
				if(!gdsList || !gdsList.length || 0 >= gdsList.length){
					scroller && scroller.setCount(-1);
				}
			}
		}
	})();
	var $modularWapSeckillList = {
		/**
		 * 调用获取数据组件
		 */
		"control":function(opts,el){
			$modularWapSeckillList.initOpts(opts,el);
			$modularWapSeckillList.addObservers();
			_themeTab.getThemeDatas(function(){
				//主题页签数据加载完成回调函数
				_themeTab.themeShader();
				_themeTab.bindTabSlider();
				_themeTab.bindTabsClick();
				_themeTab.activeTab(0);
				_themeTab.bindTabSticky();
				
				_timeShower.timeCounter();
				_iScroller.initScroll();
				$(el).ajaxStop(function(){
					_iScroller.refreshScroll();
				});
			});
		},
		"addObservers":function(){//添加观察者
			/**
			 * 对主题页签点击事件感兴趣
			 */
			_themeTab.addCallBack("resetTime",function(theme){
				if(!theme || !theme.startTime || !theme.endTime){
					return false;
				}
				_timeShower.setTimes(theme.startTime,theme.endTime);
				return true;
			});
			_themeTab.addCallBack("setTimeFlagNull",function(){_timeShower.setFlag(null)});
			_themeTab.addCallBack("timeShader",_timeShower.timeShader);
			_themeTab.addCallBack("getGdsDatas",_gdsHander.getGdsDatas);
			_themeTab.addCallBack("resetScroll",_iScroller.resetParams);
			
			/**
			 * 对时间计时器状态改变感兴趣
			 */
			_timeShower.addCallBack("resetGdsFlag", _gdsHander.resetGdsFlag);
			
			/**
			 * 对商品处理器商品加载完成感兴趣
			 */
			_gdsHander.addLoadCallBack("refleshTimeDiff", function(resultInfo){
				//刷新当前时间
				resultInfo && resultInfo.promInfoResponseDTO && _timeShower.setTimeDiff(resultInfo.promInfoResponseDTO.nowTime);
			});
			_gdsHander.addLoadCallBack("gdsShader", function(resultInfo){
				var isInit = true;
				var gdsList = resultInfo && resultInfo.page && resultInfo.page.result;
				_gdsHander.gdsShader(gdsList,isInit);
			});
			
			/**
			 * 对商品渲染完成感兴趣
			 */
			_gdsHander.addCallBack("setIScrollNOData", _iScroller.setNoData);
			_gdsHander.addCallBack("refreshiScroll", _iScroller.refreshScroll);
			_gdsHander.addCallBack("freshScrollbar", $modularWapSeckillList.freshScrollbar);
			_gdsHander.addCallBack("bindGdsClick", _gdsHander.bindGdsClick);
			
			/**
			 * 对上拉滚动器上拉加载完成感兴趣
			 */
			_iScroller.addLoadCallBack("refleshTimeDiff", function(resultInfo){
				//刷新当前时间
				resultInfo && resultInfo.promInfoResponseDTO && _timeShower.setTimeDiff(resultInfo.promInfoResponseDTO.nowTime);
			});
			_iScroller.addLoadCallBack("gdsShader", function(resultInfo){
				var isInit = false;
				var gdsList = resultInfo && resultInfo.page && resultInfo.page.result;
				_gdsHander.gdsShader(gdsList,isInit);
			});
		},
		"initOpts":function(opts,el){//初始化数据
			/*var sysTime = (new Date(opts.sysTime)).getTime();
			_timeShower.setTimeDiff(sysTime);*/
			_datas.setIsPc(opts.isPc);
			_datas.setIsPub(opts.isPub);
			_datas.setSiteId(opts.siteId);
			_datas.setWebRoot(opts.webRoot);
			_datas.setEl(el);
			_iScroller.setPageSize(opts.pageSize);
			
		},
		"freshScrollbar":function(){//编辑预览页刷新滚动条
			var $pageDecorate = $("#pageDecorate");
			//不是发布页 刷新滚动条
			if(!_datas.getIsPub() && $.fn.mCustomScrollbar && $pageDecorate && 0 < $pageDecorate.length){
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
		},
		"noDataShow":function(text,$content){
			if(!$content || 0 >= $content.length){ 
				return false;
			}
			text = text || '无数据';
			$content.html("<div class='nodata' style='height:90px;text-align:center;padding-top:30px;'><span style='color: #aaa;font-size: 14px;'>"+text+"</span></div>");
		}
	};

	$.fn.modular_wap_seckillList = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){
			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			var opts = $.ecpPlugin.parseOptions(this, defaultOpts);
			if(!opts){
				opts = {};
			}
			$modularWapSeckillList.control(opts,this);
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
