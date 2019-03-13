;
(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD模式
		define([], function() {
			factory(jQuery);
			// /返回对象，要求，其中必须要有一个 componentInit的方法；
			return {
				componentInit : function() {
					$(".modular-wap-couponVert").modular_wap_couponVert();
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
	
	$modularWapCouponVert = {
		/**
		 * 调用获取数据组件
		 */
		"control":function(el,opts){
			var coupIds = $modularWapCouponVert.getCoupIds(el);
			if(coupIds){
				$.eAjax({
					url : $webroot + '/cmscommongetdata/getcouponinfo',
					data : {
						"coupIds" : coupIds
					},
					async : true,
					type : "post",
					dataType : "json",
					success : function(returnInfo) {
						if(returnInfo && returnInfo.resultFlag == 'ok'){
							$modularWapCouponVert.doData(el,opts,returnInfo.coupons);
						}else{
							eDialog.alert("初始化优惠券服务器异常！");
						}
		    		},
		    		exception : function() {
		    			eDialog.alert("初始化优惠券异常！");
		    		},
		    		error:function(){
		    			//eDialog.alert("初始化优惠券错误！");
		    		}
				});
			}else{//去掉点击领取按钮
				$(".yhq-btn",$(el)).remove();
			}
		},
		//将优惠券信息填充到对应的优惠券中 并绑定事件
		"doData":function(el,opts,datas){
			datas = datas || [];
			var $el = $(el);
			for(var i in datas){
				var data = datas[i];
				if(data.id == null){
					continue;
				}
				var $item = $("li[coupon-id="+data.id+"]",$el).not(".usefull-coup").eq(0) ;//为了支持选择两张一样的优惠券
				if(+data.coupValue <= -1){
					$(".price-box",$item).html('<div class="price"> <span class="price">抵用券</span></div>');
				}else{
					var value = +data.coupValue*100;
					if(!value){
						continue;
					}
					
					var useRuleObj = null;
					var useRuleCode = data.useRuleCode;
					try {
						useRuleObj = JSON.parse(data.useRuleCode);
					} catch (e) {
						useRuleObj = {};
					}
					useRuleObj = useRuleObj || {};
					if(1 <= +useRuleObj["240"]){ // 折扣券
						$(".item-left",$item).html('<span class="price">'+(value/100000).toFixed(1)+'</span><em>折</em>')
					}else{
						$(".price",$item).text(parseInt(value/10000));
					}
				}
				$(".p-sel",$item).text(data.conditionsShow||"");
				
				//标记为有效优惠券
				$item.addClass("usefull-coup");
			}
			//发布页面则绑定领取事件
			if(opts && opts.isPub){
				$("li",$el).not(".usefull-coup").remove();
				if(0 >= $(".usefull-coup",$el).length){
					$el.remove();
				}else{
					if(typeof opts.displayPlatType == 'string' && opts.displayPlatType.toLowerCase() == 'app'){//app访问则用a链接
						$modularWapCouponVert.setHrefOfCoups(el,opts.webRoot);
					}else{//否则用点击事件
						$modularWapCouponVert.bindGainCoup(el,opts.webRoot);
					}
				}
			}else{
				$("li",$el).not(".usefull-coup").show();
			}
		},
		//获取优惠券ids  以","隔开
		"getCoupIds":function(el){
			var $dataList = $("li",$(el));
			var coupIds = "";
			if($dataList && $dataList.length > 0){
				for(var i in $dataList){
					coupIds += $dataList.eq(i).attr("coupon-id") || "";
					coupIds += ","
				}
			}
			return coupIds;
		},
		//绑定领取一张优惠券事件
		"bindGainCoup":function(el,webRoot){
			var sCallback = function(returnInfo,$item) {//成功回调函数
	    			if(returnInfo && returnInfo.resultFlag=='ok'){
	    				if(AmLoad){
	    					new AmLoad({content:'领取成功！'});
	    				}else{
	    					eDialog.alert('领取成功！');
	    				}
					}else{
						if(AmLoad){
	    					new AmLoad({content:returnInfo.resultMsg},'2');
	    				}else{
	    					eDialog.alert(returnInfo.resultMsg);
	    				}
					}
	    			if($item){
	    				$($item).bind("click.gc",doBind);
	    			}
				}
			
			var eCallback = function(msg,$item){//失败回调函数
				eDialog.alert(msg);
				if($item){
    				$($item).bind("click.gc",doBind);
    			}
			}
			
			var doBind = function(){
				$(this).unbind("click.gc");
				var coupId = $(this).attr("coupon-id");
				$modularWapCouponVert.gainCoup(webRoot,coupId,sCallback,eCallback,$(this));
			}
			
			$(".receive",$(el)).unbind("click.gc").bind("click.gc",doBind);
		},
		//设置领取优惠券链接地址   主要为app调用拦截使用
		"setHrefOfCoups":function(el,webRoot){
			webRoot = webRoot || $webroot;
			$(".receive",$(el)).each(function(){
				var $this = $(this);
				var coupId = $this.attr("coupon-id");
				if(coupId){
					$("a",$this).attr("href",webRoot + '/gaincoup/appgain?id='+coupId);
				}else{
					$("a",$this).attr("href","");
				}
			});
		},
		//领取优惠券
		"gainCoup":function(webRoot,coupId,sCallback,eCallback,$clickObj){
			webRoot = webRoot || $webroot;
			if(coupId){
				$.eAjax({
					url : webRoot + '/gaincoup/gain?id='+coupId,
					async : true,
					type : "post",
					dataType : "json",
					success : function(returnInfo) {
		    			if(sCallback && $.isFunction(sCallback)){
		    				sCallback(returnInfo,$clickObj);
		    			}
		    		},
		    		exception : function() {
		    			if(eCallback && $.isFunction(eCallback)){
		    				eCallback("领取异常，稍后再试！",$clickObj);
		    			}
		    		},
		    		error : function(returnInfo) {
		    			if(eCallback && $.isFunction(eCallback)){
		    				eCallback("领取出错，稍后再试！",$clickObj);
		    			}
		    		}
				});
			}else{
				if(eCallback && $.isFunction(eCallback)){
    				eCallback("无效优惠券！",$clickObj);
    			}
			}
		}
	};

	$.fn.modular_wap_couponVert = function() {
		return this.each(function() {
			var status = $(this).data("comStatus");
			if(!status || status == "" || status =="undefined" ||status==undefined || status == "init"){

			} else {
				return $(this);
			}
			$(this).data("comStatus","do");
			
			var opts = $.ecpPlugin.parseOptions(this, defaultOpts);
			$modularWapCouponVert.control(this,opts);
			
			$(this).data("comStatus","end");
			return $(this);
		});
	};
	
	
}));
