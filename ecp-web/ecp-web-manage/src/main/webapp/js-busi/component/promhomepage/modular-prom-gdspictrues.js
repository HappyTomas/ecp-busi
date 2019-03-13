;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([], function(adData) {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".modular-prom-gdspictrues").modular_prom_gdspictrues();//要求方法名为将类名中画线改为下划线   为了局部刷新
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

	$prom_floor_shader = {
		/**
		 * 获取促销楼层的信息，则调用数据处理的回调函数(callback);否则直接返回；
		 * @param opts
		 */
		getData : function(opts){
			$.eAjax({
				url : $webroot + '/cmshomepagegetdata/qryFloorVM',
				data : {
					"placeId" : opts.placeId,
					"coupSize" : opts.coupSize,
					"gdsSize" : opts.gdsSize,
					"tabSize" : opts.tabSize,
					"showWay" : opts.showWay,
					"placeWidth" : opts.placeWidth,
					"placeHeight" : opts.placeHeight,
					"status" : opts.status,
					"floorType" : opts.floorType,
					"returnUrl" : opts.returnUrl
				},
				async : true,
				type : "post",
				dataType : "html",
				success : function(data, textStatus) {
					if (data == null) {
						return;
					} else {
						if(opts.callback && $.isFunction(opts.callback)){
							opts.callback(data);
						}
					}
				},
				error:function(data){
					opts.callback(data);
				}
			});
		},
		/**
		 * 调用获取数据组件
		 */
		"control":function(el,opts){
			//加载动态图
			$("#promGdsPictruesRender",$(el)).append("<div class='tpl-loading'></div>");
			$prom_floor_shader.getData({
				"placeId" : opts.placeId,
				"coupSize" : opts.coupSize,
				"gdsSize" : opts.gdsSize,
				"tabSize" : opts.tabSize,
				"showWay" : opts.showway,
				"placeWidth" : opts.placeWidth,
				"placeHeight" : opts.placeHeight,
				"status" : opts.status,
				"floorType" : opts.floorType,
				"returnUrl" : opts.returnUrl,
				"callback":function(datas){
					if(datas){
						//返回楼层VM，填充楼层信息（页签、页签对应的DIV）
						$("#promGdsPictruesRender",$(el)).empty();
						$("#promGdsPictruesRender",$(el)).html(datas);
						//绑定点击领取优惠券事件
						$prom_floor_shader.gainCoup(el);
						
						//返回楼层VM，填充楼层信息（广告、标签、商品）
						var $floorTabUL = $(".floor-tab",el).eq(0);
						
						if($floorTabUL){
							$floorTabUL.addClass("active");
							$prom_floor_shader.queryTabGds(el,$floorTabUL);
							$prom_floor_shader.bindTabChange(el);
						}
					}else{
						$(".floor-content", el).empty();
						//$(".floor-content", el).append("<div>亲，数据搞丢了！</div>");
					}
				}
			});
		},
		"queryTabGds":function(el,$floorTabUL){
			var tabId = $floorTabUL.attr("tabId");
			var floorId = $floorTabUL.attr("floorId");
			var isLoad = $floorTabUL.attr("isLoad");//是否加载，yes:已加载，no：未加载 
			var returnUrl = $floorTabUL.attr("returnUrl");
			
			var opts = $(el).data();
			if(tabId){
				opts.tabId = tabId;
			}else{
				tabId = floorId+"_noTab";
			}
			
			opts.floorId = floorId;
			opts.returnUrl = returnUrl;
			//alert(tabId); 
			
			$(".nItem", el).hide();
			$("#floorGdsDIV_" + tabId, el).show();
			
			if(isLoad == "no" && returnUrl){
				$("#floorGdsDIV_"+tabId, el).empty();
				$("#floorGdsDIV_" + tabId, el).append("<div class='tpl-loading'></div>");
				$($floorTabUL).attr("isLoad","yes");
				$.eAjax({
					url : $webroot + '/cmshomepagegetdata/queryFloorInfoVM',
					data : {
						"tabId" : opts.tabId,
						"floorId" : opts.floorId,
						"placeId" : opts.placeId,
						"gdsSize" : opts.gdsSize,
						"showWay" : opts.showWay,
						"placeWidth" : opts.placeWidth,
						"placeHeight" : opts.placeHeight,
						"status" : opts.status,
						"returnUrl" : opts.returnUrl
					},
					async : true,
					type : "post",
					dataType : "html",
					success : function(datas, textStatus) {
						$("#floorGdsDIV_"+tabId, el).empty();
						$("#floorGdsDIV_"+tabId, el).html(datas);
					},
					exception : function(returnInfo) {
						$("#floorGdsDIV_"+tabId, el).empty();
						$("#floorGdsDIV_"+tabId, el).html("<span>发起请求异常！</span>");
		    		},
					error : function(returnInfo) {
						$("#floorGdsDIV_"+tabId, el).empty();
						$("#floorGdsDIV_"+tabId, el).html("<span>获取商品异常！</span>");
		    		}
				});
			}
		},
		"bindTabChange":function(el){
			//绑定tab页切换
			$(".floor-tab",el).live("mouseover",function(e){
				//获取楼层商品数据
				if(!$(this).hasClass("active")){
					$(".floor-tab",el).removeClass("active");
					$(this).addClass("active");
					$prom_floor_shader.queryTabGds(el,$(this));
				}
			});
		},
		"gainCoup":function(el){//领取优惠券
			$(".gainCoup",el).live("click",function(e){
				var coupId = $(this).attr("coupId");
				if(coupId){
    				$.eAjax({
    					url : $webroot + '/gaincoup/gain',
    					data : {
    						"id" : coupId
    					},
    					async : true,
    					type : "post",
    					dataType : "json",
    					success : function(returnInfo) {
    		    			if(returnInfo && returnInfo.resultFlag=='ok'){
    		    					eDialog.alert('领取成功！',null);
    		     		 			return ;
    		    			}else{
    		    					eDialog.alert(returnInfo.resultMsg,null);
    		       		 			return ;
    		    			}
    		    		},
    		    		exception : function(returnInfo) {
    		    			eDialog.alert('亲！系统发生异常了。请稍等再重试...',null);
    		    		 	return ;
    		    		}
    				});
				}else{
					eDialog.alert('亲！优惠券被搞丢啦！');
				}
			});
		}
	};
	$.fn.modular_prom_gdspictrues = function() {
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
			$prom_floor_shader.control(this,opts);
			
			$(this).data("comStatus","end");
			return $(this);
		});
	};
}));
