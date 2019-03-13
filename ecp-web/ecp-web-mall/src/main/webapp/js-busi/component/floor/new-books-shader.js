;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define(['ecp-component/do-data' ], function(gdsData) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".new-books-shader").new_books_shader();
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
		 * 生成新书速递信息；
		 * @param el
		 * @param images
		 * @author zhanbh
		 */
		"doData" : function(el, datas) {
			var $container = $(".new-books-gds", el);
			if(datas && $.isPlainObject(datas)){
				//显示名称
				$(".floor-name", el).removeClass("hide");
				// 获取楼层商品数据，填充商品数据
				var gdsList =datas.gdsList;
				$newBooksShader.doGdsList($container,gdsList,0);
			}else{
				$container.empty();
				$container.append("<div class ='pro-empty'>亲，出错啦！</div>");
			}
		},
		/**
		 * 调用获取数据组件
		 */
		"control":function(el,opts){
			var $container = $(".new-books-gds", el);
			    $container.empty();
			    $newBooksShader.loadContanier($container,-1);
			$newBooksGdsData.getData({
				"gdsSize" : opts.gdsSize,
				"placeWidth" : opts.placeWidth,
				"placeHeight" : opts.placeHeight,
				"callback":function(gds){
					$newBooksShader.doData(el,gds);
//					$newBooksShader.bindTabChange(el);
				}
			});
		},
		"doGdsList":function(_obj,dataList,index){
			
			//	_obj.empty();
            var _clist=_obj.children().eq(index);
            _clist.empty();
            if(dataList && dataList.length > 0 ){
            	var str = "";
            	$.each(dataList, function(i, n) {
				//获取作者属性
            		var allPropMaps = n.allPropMaps;
            		var authorValue = "";//作者属性值
            		if(allPropMaps && $.isPlainObject(allPropMaps)){
            			var propObj = allPropMaps["1001"];
            			if(propObj){
            				var authorValues = propObj.values;
            				if(authorValues && authorValues.length > 0){
            					authorValue = authorValues[0].propValue;
            				}
            			}
            		}
            		if(!(n.mainPic) ){
            			n.mainPic = {};
            		}
            		str += "<li>";
            		str += "<a target='_blank' href='"+(n.url?(GLOBAL.WEBROOT+n.url):'javascript:void(0)')+"'><img src='"+n.mainPic.url+"'></a>";
            		str += "<p class=\"name\"><a target='_blank' href='"+(n.url?(GLOBAL.WEBROOT+n.url):'javascript:void(0)')+"'>"+(n.gdsName?n.gdsName:"")+"</a></p>";
            		str += "<p class=\"author\">"+(authorValue?(authorValue+" 著"):'')+"</p>";
            		str += "<p class=\"price\">";
            		str += "<span class=\"rob\">"+(n.discountPrice?("&yen;"+ebcUtils.numFormat(accDiv(n.discountPrice,100),2)):"")+"</span>";
            		str += "<span class=\"price_r\">"+(n.guidePrice?("&yen;"+ebcUtils.numFormat(accDiv(n.guidePrice,100),2)):'')+"</span>";
            		str += "</p>";
            		str += "</li>";

					});
					_clist.append(str);
					
            }else{//无数据
            	_clist.append("<div class ='pro-empty'>亲，暂无数据！</div>");
            }
			_obj == null;
		},
//		//加载未完成时显示的内容
		"loadContanier":function($container,index){
			var strl='<div><div class="loading"></div></div>';
			if(index==-1){
				$container.append(strl);
				index=0;
			}else{
				$container.children().eq(index-1).after(strl);
			}
			$container.children().hide().eq(index).show();
		}

	};

	$.fn.new_books_shader = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){

			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			
			var opts = $.ecpPlugin.parseOptions(this, defaultOpts);
			if(opts == null || opts.placeWidth =="" || opts.placeWidth=="undefined"||opts.placeHeight =="" || opts.placeHeight=="undefined"){
				return ;
			}
			$newBooksShader.control(this,opts);
			
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
