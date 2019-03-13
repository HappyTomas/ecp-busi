/**
 * gxq
 */
$(function(){
	//页面业务逻辑处理内容
    var pageInit = function(){
        var init = function(){
        	/**
             * 搜索
             */
            var search=function(keyword){
            	
            	keyword=keyword.trim();
            
        		//打开搜索组件页面
        		window.location.href = GLOBAL.WEBROOT+'/search?keyword='+keyword;
            	
            };
        	//为搜索按钮添加单击事件绑定
	    	$('#searchBtn').live('click',function(e){
	    		search($('#searchTxt').val());
		    });
	    	
        	//积分选择事件绑定=========start
        	$('.mselect li').click(function(){
                var ms=$(this).parents('.mselect');
                ms.find('.sv').text($(this).text());
                ms.find('.sv').attr('id',$(this).attr('id'));
                ms.removeClass('hover');
            });
            $('.mselect').hover(function(){
           	 	$(this).addClass('hover');
            },
            function(){
            	$(this).removeClass('hover');
            });
            //积分选择事件绑定=========end
            
            
        	//加入购物车
        	var getaddToCartParam = function(obj,cartType,shopId,shopName,ordCartItemList,form){
        		var param = {
        				cartType : cartType,
        				shopId : shopId,
        				shopName : shopName,
        				ordCartItemList	: JSON.stringify(ordCartItemList)
        		};
        		$.eAjax({
    				url : GLOBAL.WEBROOT + "/order/cart/add",
    				data : param,
    				plugin : ['ePageTop'],
    				success : function(returnInfo) {
    					if(returnInfo.ecpBaseResponseVO.resultFlag == "ok"){
    						if(form=="once"){
    							window.location.href = GLOBAL.WEBROOT+"/order/cart/list";
    						}else{
    							var startOffset = $(obj).offset();
				                var endOffset = $("#carGus").offset();
				                var img = $('.pcimg').attr('src');
				                var flyer = $('<img class="u-flyer"  src="'+img+'">');
				                flyer.fly({
									speed : 1.8,
				                    start: {
				                        left: startOffset.left,
				                        top: startOffset.top
				                    },
				                    end: {
				                        left: endOffset.left+50,
				                        top: endOffset.top+10,
				                        width: 0,
				                        height: 0
				                    },
				                    onEnd: function(){
				                        this.destory();
				                    }
				                });
    							$.pageTop.setOrderCount({url:GLOBAL.WEBROOT + '/order/getcartcount'}); 
    						}
    						
                        }else{
                        	eDialog.error(returnInfo.ecpBaseResponseVO.resultMsg);
                        }
    					$(obj).bind('click');
    				},
    				exception : function(){
						$(obj).bind('click');
					}
    			});
        	};
        	//加入购物车事件绑定====================start
        	$("#addToCart").live('click',function(){
        		$(this).unbind('click');
        		//购物车类型
        		var cartType = "01";
        		//店铺id
        		var shopId = $("#shopId").val();
        		//店铺名称
        		var shopName = $("#shopName").val();
        		var ordCartItemList = new Array();
        		var form = "ordinary";//普通方式加入购物车
        		commonPriceAddToCart(cartType,shopId,shopName,ordCartItemList);
        		getaddToCartParam(this,cartType,shopId,shopName,ordCartItemList,form);
        	});
        	//立即兑换事件绑定
        	$("#onceExchange").live('click',function(){
        		//购物车类型
        		var cartType = "01";
        		//店铺id
        		var shopId = $("#shopId").val();
        		//店铺名称
        		var shopName = $("#shopName").val();
        		var ordCartItemList = new Array();
        		var form = "once";//普通方式加入购物车
        		commonPriceAddToCart(cartType,shopId,shopName,ordCartItemList);
        		getaddToCartParam(this,cartType,shopId,shopName,ordCartItemList,form);
        	});
        	
        	//普通价格商品加入购物车
        	var commonPriceAddToCart = function(cartType,shopId,shopName,ordCartItemList){
        		
        		//购买数量
        		var purchaseAmount = 0;
        		var $purchaseAmount = $("#purchaseAmount");
        		if($purchaseAmount.attr("gdsTypeId")=="2"){
        			purchaseAmount = $.trim($purchaseAmount.text());
        		}else{
        			purchaseAmount = $purchaseAmount.val();
        		}
        		//商品id
        		var gdsId = $("#gdsId").val();
        		//商品名称
        		var gdsName = $("#gdsName").val();
        		//单品id
        		var skuId = $("#skuId").val();
        		//单品属性串
        		var skuProps = $("#skuProps").val();
        		//商品主分类
        		var mainCatgs = $("#mainCatgs").val();
        		//组合类型
        		var groupType = "0";
        		//组合明细 	默认为skuId，组合搭配的话，为主skuId:次skuId:次skuId	1	
        		var groupDetail	= $("#skuId").val();
        		//商品类型id
        		var gdsTypeId = $("#gdsTypeId").val();
        		//价格方式
        		var scoreTypeId = $('.mselect').find('.sv').attr('id');
        		var ordCartItemListObj = {
        				cartType : cartType,
        				orderAmount : purchaseAmount,
        				shopId : shopId,
        				shopName : shopName,
        				gdsId : gdsId,
        				gdsName : gdsName,
        				skuId : skuId,
        				mainCatgs : mainCatgs,
        				skuInfo : skuProps,
        				groupType : groupType,
        				groupDetail : groupDetail,
        				promId : "",
        				gdsType : gdsTypeId,
        				prnFlag : '0',
        				scoreTypeId : scoreTypeId
        		};
        		ordCartItemList.push(ordCartItemListObj);
        	};
        	//异步加载兑换排行榜===========start
        	$(function() {
        		$.eAjax({
        			url : GLOBAL.WEBROOT + "/searchExtern/rank",
        			dataType : 'html',
        			success : function(returnInfo) {
        				$("#exchangeRank").html(returnInfo);
        			},
        			exception : function() {

        			}
        		});
        	})
        	//异步加载兑换排行榜===========end
        	//商品描述============start
        	var setCkeditValue = function(edit,url){
        		if(url && url != ""){
	        		$.ajax({
	    				url : url,
	    				async : true,
	    				dataType : 'jsonp',
	    				jsonp : 'jsonpCallback',// 注意此处写死jsonCallback
	    				success : function(data) {
	    					$("#"+edit).html(data.result);
	    				}
	    			 });
        		 }
        	};
        	setCkeditValue("gdsDetailDesc",$("#gdsDesc").val());
        	//详情、包装清单============end
        	
        	//加入购物车减少商品数量
            $("#reduceCount").click(function(){
            	reduceCount('down');
            });
            //加入购物车增加商品数量
            $("#addCount").click(function(){
            	addCount('up');
            });
            var addCount = function(str){
            	var stock = $("#realAmount").text();
            	var value = $("#purchaseAmount").val();
            	var STOCK_LACK_THRESHOLD = $("#STOCK_LACK_THRESHOLD").val();
            	var max = $("#purchaseAmount").attr('max');
            	if(Number(stock)<=Number(STOCK_LACK_THRESHOLD)){
            		return;
            	}
            	if (value != '') {
            		if(++value>Number(max)-Number(STOCK_LACK_THRESHOLD)){
            			return;
            		}
            		$("#purchaseAmount").val(value);
        			$("#realAmount").text(--stock);
            	} else {
            		$("#purchaseAmount").val(1);
            		$("#realAmount").text(--stock);
            	}
            };
            var reduceCount = function(str){
            	var stock = $("#realAmount").text();
            	var value = $("#purchaseAmount").val();
            	if(Number(value)<=1){
            		return;
            	}
            	if (value != '') {
            		$("#purchaseAmount").val(--value);
        			$("#realAmount").text(++stock);
            	} else {
            		$("#purchaseAmount").val(1);
            		$("#realAmount").text(++stock);
            	}
            };
            $("#purchaseAmount").on('keyup',function(){
            	ladderLimitInputNum(this);
            });
            /**
        	 * 限制输入框输入的数字只能为正整数
        	 * @param obj
        	 */
        	var ladderLimitInputNum = function(obj){
        		var input=$(obj);
        		if($("#gdsTypeId").val()=='2'){
        			input.val(1);
        			return;
        		}
        		limitInputNum(obj);
        		var num=0;
        		var STOCK_LACK_THRESHOLD = $("#STOCK_LACK_THRESHOLD").val();
        		var maxAttr=input.attr("max");
        		var max=0;
        		if(maxAttr){
        			if(Number(maxAttr) > Number(STOCK_LACK_THRESHOLD)){
        				max=Number(maxAttr)- Number(STOCK_LACK_THRESHOLD);
        			}else{
        				max = 1;
        			}
        		}
        		if(Number(input.val())>max){
        			input.val(max);
        			num=max;
        			$("#realAmount").text(max-num+Number(STOCK_LACK_THRESHOLD));
        		}else{
        			num=input.val();
        			$("#realAmount").text(max-num+Number(STOCK_LACK_THRESHOLD));
        		}
        	};
        	/**
        	 * 限制只能输入正整数
        	 * @param obj
        	 */
        	var limitInputNum = function(obj){
        		var _amount = $(obj).val();
        		_amount=_amount.replace(/[^0-9]/g,'');
        		if(_amount.trim() == '' || _amount=='0'){
        			_amount=1;
        		}
        		$(obj).val(_amount);
        	};
        	
        };
        return {
            "init" : init
        };
    };
    pageConfig.config({
        //指定需要加载的插件，名称请参考common中定义的插件名称，注意大小写
        plugin : ['ePageTop'],
        //指定页面
        init : function(){
            var p = pageInit();
            p.init();
        }
    });
});