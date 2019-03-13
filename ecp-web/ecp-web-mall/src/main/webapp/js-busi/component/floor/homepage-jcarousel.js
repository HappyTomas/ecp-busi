;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([ 'jquery-powerSwitch','ecp-component/do-data'], function(slide,adData) {
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
				$('.floor-name',$tabdiv).append(floorRespDTO.floorName);
				$tabdiv.attr('id','floor-'+floorRespDTO.id);
				 //显示楼层名
				$('.floor-name',$tabdiv).removeClass("hide");
				
				//2 获取楼层页签数据，填充楼层页签
			    var floorTabList =datas.floorTabList;
				if(floorTabList && floorTabList.length > 0){
					//启用tab样式
					$(el).addClass("recommend-tab");
					//添加tab页数据
					$floorjcarousel.doTabList($tabdiv,$gdsdiv,floorTabList);
				 }else{
					 //禁用tab样式
					 $(el).removeClass("recommend-tab");
				 }
					
				$floorjcarousel.doGdsList($gdsdiv, gdsList,0);
			}else{//无楼层
				$gdsdiv.empty();
				$gdsdiv.append("<div style = 'width:752px' class ='pro-empty'>亲，这家伙太懒，暂未配置数据！</div>");
			}
		},
		"control":function(el,opts){
			var $container = $(".homepage-jc-body", el);
			$container.append('<div><div class="loading" style="width:750px"></div></div>');
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
		"getTarget":function(target){
			if(target){
				return ' target="_blank" ';
			}
			return '';
		},
		"doTabList":function(_obj,_gdsdiv,dataList){
			var str = "<ul class=\"new-tab clearfix\">";
			
			var strc="";
			$.each(dataList, function(i, n) {
				str += "<li tabId="+n.id+"";
				if(i == 0){
					str +=" class='active'";
				}
				str += "><a "+$floorjcarousel.getTarget(n.linkUrl)+" href='"+$floorjcarousel.getHref(n.linkUrl)+"'>"+n.tabName+"</a></li>";
				strc +='<div ';
				if(i > 0){
					strc+= 'class="hide"';
				}
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
		"doGdsList":function(_obj,dataList2,index){
			// 拼装轮播商品数据
			 var _clist=_obj.children().eq(index);
	        _clist.empty();
			var dataList=U.dealData(dataList2);
			var tabn=['a','b','c','d','e','f','g','h','i','j','k','i'];
			var swtarget="switchc"+tabn[index];
			var dl=dataList.length;
			var l=0;
			
		//	var imgStr = "<div class='box-con box-two'>"+
			var imgStr = "<div class='slide jcarousel box-jcarousel'>";        
				if(dataList && dataList.length > 0){
					imgStr +=  "<div class='slide-trigger'>"+
				              "<a href='javascript:' class='slide-prev' data-rel='"+swtarget+"'></a>"+
				              "<a href='javascript:' class='slide-next' data-rel='"+swtarget+"'></a>"+
		                     "</div>"+
				              "<div class='slide-box' style ='text-align :left;'>"+
				              "<div class='jcarousel-container'>";
					$.each(dataList, function(i, n) {
						if(i%4==0){
							imgStr +="<ul class='item clearfix "+swtarget+"'>";
							l=0;
						}
						 l++;
						var k = i + 1;
	
						//获取作者属性
						var authorValue = doGdsProp(n,"1001");
						authorValue=authorValue?(authorValue + " 著"):'';
						
						imgStr += "<li>";
						imgStr += "<a "+$floorjcarousel.getTarget(n.url)+" href='"+(n.url?(GLOBAL.WEBROOT+n.url):'javascript:void(0)')+"'><img src='"+n.mainPic.url+"'></a>";
						imgStr += "<p class=\"name\"><a "+$floorjcarousel.getTarget(n.url)+" href='"+(n.url?(GLOBAL.WEBROOT+n.url):'javascript:void(0)')+"'>"+(n.gdsName?n.gdsName:"")+"</a></p>";
						imgStr += "<p class=\"author\">"+authorValue+"</p>";
						imgStr += "<p class=\"price\">";
						imgStr += "<span class=\"rob\">&yen;"+(n.skuInfo.discountPrice?ebcUtils.numFormat(accDiv(n.skuInfo.discountPrice,100),2):'')+"</span>";
						imgStr += "<span class=\"price_r\">"+(n.guidePrice?("&yen;"+ebcUtils.numFormat(accDiv(n.guidePrice,100),2)):"")+"</span>";
						imgStr += "</p>";
						imgStr += "</li>";
						if((l==4)||i==(dl-1)){
				            imgStr += '</ul>';
						}
					});
					   imgStr += "</div></div></div>";
					    _clist.append(imgStr);
					
						var _trigger=_clist.find('.slide-trigger');
						var _container=_clist.find('.jcarousel-container');
						_trigger.find("a").powerSwitch({
							 classDisabled: '',
		                     number: 1,
			                 container: _container
			            });
				}else{
				  imgStr +="<div style = 'width:752px;margin-top:50px' class ='pro-empty'>亲，暂无数据！</div>";
				  imgStr += "</div>";
				  _clist.append(imgStr);
				}
				_clist = null;
		},

		"queryGdsByTabId":function(el,opts,index){
			 var $container = $(".homepage-jc-body",el);
			 $floorjcarousel.loadContanier($container,index);
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
						var gdsList =datas.gdsList;
						$floorjcarousel.doGdsList($container,gdsList,index);
					}
				}
			});
		},
		"bindTabChange":function(el){
			//绑定tab页切换
			$(".homepage-jc-tt li",el).live("mouseover",function(e){
				//获取楼层商品数据
				$(".homepage-jc-tt li",el).removeClass("active");
				
				$(this).addClass("active");
			
				var opts = $(el).data();
				var tabId =$(this).attr("tabId");
				opts.tabId = tabId;
				var $index=$(this).index();
				if(!$(this).data('load')){
					$(this).data('load',true);
					$floorjcarousel.queryGdsByTabId(el,opts,$index);
				}else{
					$(".homepage-jc-body",el).children().hide().eq($index).show();
				}
	
		
			});
		}
	};

	$.fn.electronic_boxJc = function(scrollLoading) {
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
