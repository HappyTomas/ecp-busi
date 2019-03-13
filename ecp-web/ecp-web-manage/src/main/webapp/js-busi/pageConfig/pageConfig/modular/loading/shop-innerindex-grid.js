$(function() {
	$('#ct-nav .it').live('click',function () {
        $(this).parent().toggleClass('drawdown');
    });
	  
    $('#colShop').live('click',function () {
		if($('#isLogin').val() != 'true'){
				window.location.href = GLOBAL.WEBROOT+'/login';
				return;
		}
		var isCollect=$('#isCollect').val();
		var url=GLOBAL.WEBROOT + "/shopIndex/collectShop/"+$('#hiddenShopId').val();
		if(isCollect== 'true'){
			url=GLOBAL.WEBROOT + "/shopIndex/deleteShop/"+$('#hiddenShopId').val();
		}
		$.eAjax({
				url :url ,
				data : {},
				async : false,
				success : function(returnInfo) {
					if(returnInfo.resultFlag=="ok"){
						eDialog.success(returnInfo.resultMsg);
					}
					if(isCollect== 'true'){
						$('#isCollect').val('false');
						$('#colShop').html("收藏店铺");
					}else{
						$('#isCollect').val('true');
						$('#colShop').html("取消店铺收藏");
					}
				},
				exception : function() {
					eDialog.error(returnInfo.resultMsg);
				}
			});
      });
	  
	  $('#shopSearchBtn').click(function () {
		 var txtVal=$('#site-search #searchTxt').val();
		 if(txtVal!=''){
			var url=encodeURI(GLOBAL.WEBROOT+'/search?shopId='+$('#hiddenShopId').val()+'&keyword='+txtVal);
			// window.open(url);
			window.location.href = url;
		 }
		
    });
	  
	//获取排序字段
	shopIndex={
		getField:function(){
			var selection=$('#sr-order').children('.selected')[0];	
			return $(selection).attr("id");
		},
		getSort:function(){
			var selection=$('#sr-order').children('.selected')[0];	
			if($($(selection).children()[0]).hasClass('ticon-down')){
				return "desc";
			}else{
				return "asc";
			}
		},
		gdsListRefresh:function(opts){
			$('#pageControlbar').bPageRefresh({
				asyncLoad : true,
				asyncTarget : '#pageMainBox',
				async : false ,
				pageNumber:opts.pageNumber,//pageNumber必须指定
				params : function(){
			    	return {
				        "shopId":$('#hiddenShopId').val(),
				        "field":shopIndex.getField(),
				        "sort":shopIndex.getSort()
				    };
			    }
			});
			try {
				var total=$('#searchTotal').val();
				$('#numFound').html(total);
			} catch (e) {
				
			}
		}
	};
	
	var downClz='ticon-down';
	var upClz='ticon-downup';
	//销量排序事件
	$('#sales').live('click',function(){
		
		//设置选中样式
		if($(this).hasClass('selected')){
			
			//设置选中排序图标样式
			if($('#sales i:first').hasClass(downClz)){
				$('#sales i:first').removeClass(downClz).addClass(upClz);
			}else if($('#sales i:first').hasClass(upClz)){
				$('#sales i:first').removeClass(upClz).addClass(downClz);
			}
			
		}else{
			
			$('#sr-order li').removeClass('selected');			
			$(this).addClass('selected');

		}
		shopIndex.gdsListRefresh({"pageNumber":1});
	});
	
	//价格排序事件
	$('#discountPrice').live('click',function(){
		
		//设置选中样式
		if($(this).hasClass('selected')){
			
			//设置选中排序图标样式
			if($('#discountPrice i:first').hasClass(downClz)){
				$('#discountPrice i:first').removeClass(downClz).addClass(upClz);
			}else if($('#discountPrice i:first').hasClass(upClz)){
				$('#discountPrice i:first').removeClass(upClz).addClass(downClz);
			}
			
		}else{
			
			$('#sr-order li').removeClass('selected');			
			$(this).addClass('selected');
		}
		
		shopIndex.gdsListRefresh({"pageNumber":1});
		
	});
	
	//出版时间排序事件
	$('#newProductTime').live('click',function(){
		
		var field="newProductTime";
		var sort="desc";
		
		//设置选中样式
		if($(this).hasClass('selected')){
			
			//设置选中排序图标样式
			if($('#newProductTime i:first').hasClass(downClz)){
				$('#newProductTime i:first').removeClass(downClz).addClass(upClz);
				sort="asc";
			}else if($('#newProductTime i:first').hasClass(upClz)){
				$('#newProductTime i:first').removeClass(upClz).addClass(downClz);
				sort="desc";
			}
			
		}else{
			
			$('#sr-order li').removeClass('selected');			
			$(this).addClass('selected');

			//第一次更改排序字段，使用默认排序规则
			if($('#newProductTime i:first').hasClass('micon-down')){
				sort="desc";
			}else if($('#newProductTime i:first').hasClass('micon-downup')){
				sort="asc";
			}
		}
		
		shopIndex.gdsListRefresh({"pageNumber":1});
		
	});
  var getAddToCartCommon = function(_obj){
  	//购物车类型
		var cartType = "01";
		//购买数量
		var purchaseAmount = 1;
		//店铺id
		var shopId = _obj.children("input[name='shopId']").val();
		//店铺名称
		var shopName = _obj.children("input[name='shopName']").val();
		//商品id
		var gdsId = _obj.children("input[name='gdsId']").val();
		//商品类型id
		var gdsTypeId = _obj.children("input[name='gdsTypeId']").val();
		//商品名称
		var gdsName = _obj.children("input[name='gdsName']").val();
		//单品id
		var skuId = _obj.children("input[name='skuId']").val();
		//单品属性串
		var skuProps = _obj.children("input[name='skuProps']").val();
		//商品主分类
		var mainCatgs = _obj.children("input[name='mainCatgs']").val();
		//组合类型
		var groupType = "0";
		//组合明细 	默认为skuId，组合搭配的话，为主skuId:次skuId:次skuId	1	
		var groupDetail	= _obj.children("input[name='skuId']").val();
		
		var ordCartItemList = new Array();
		var ordCartItemListObj = {
				cartType : cartType,
				orderAmount : purchaseAmount,
				shopId : shopId,
				shopName : shopName,
				gdsId : gdsId,
				gdsType : gdsTypeId,
				gdsName : gdsName,
				skuId : skuId,
				mainCatgs : mainCatgs,
				skuInfo : skuProps,
				groupType : groupType,
				groupDetail : groupDetail,
				promId : "",
				prnFlag : "0",
  			scoreTypeId : ''
		};
		ordCartItemList.push(ordCartItemListObj);
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
					var startOffset = _obj.offset();
	                var endOffset = $("#carGus").offset();
	                var img = _obj.attr('img');
	                var flyer = $('<img class="u-flyer"  src="'+img+'">');
	                flyer.fly({
						speed : 1.4,
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
//					window.location.href = GLOBAL.WEBROOT+"/order/cart/list";
	            }else{
	            	eDialog.alert(returnInfo.ecpBaseResponseVO.resultMsg);
	            }
			}
		});
  };
  $(".addToCart").live("click",(function(){
  	var buyFlag = 0;
  	var _obj = $(this);
  	if(_obj.children("input[name='gdsTypeId']").val()=='2'){
			//虚拟产品，则判断是否已购买
			$.eAjax({
				url : GLOBAL.WEBROOT + "/gdsdetail/wetherbuyed",
				data : {skuId: _obj.children("input[name='skuId']").val()},
				async : false,
				success : function(returnInfo) {
					if(returnInfo.resultFlag=="ok"){
						if(returnInfo.buyedFlag==true){
							//已购买过咯
							buyFlag = 1;
						}
					}
				}
			});
		}
		if(buyFlag == 1){
			eDialog.alert("抱歉，该商品只允许购买一次！");
			return;
		}
		//判断是是否是数字教材
		$.eAjax({
			url : GLOBAL.WEBROOT + "/gdsdetail/ifebookordigitsbook",
			data : {mainCatgs: _obj.children("input[name='mainCatgs']").val()},
			async : false,
			success : function(returnInfo) {
				if (returnInfo.digitsBook == true || returnInfo.ebook == true) {
					eDialog.confirm("您加入购物车的商品是【数字产品-"+returnInfo.catgCodeName+"】", {
	        			buttons : [ {
	        				caption : '确定加入购物车',
	        				callback : function() {
	        					getAddToCartCommon(_obj);
	        				}
	        			}, {
	        				caption : '返回',
	        				callback : $.noop
	        			} ]
	        		});
				}else{
					getAddToCartCommon(_obj);
				}
			}
		});
	}));
	
	$(".gdsCollect").live("click",(function(e){
		$(this).unbind('click');
		if(!$(this).find("input[name=isLogin]").val() == 'true'){
			window.location.href = GLOBAL.WEBROOT+'/login';
			return;
		}
		var collect = $(this);
		var addToCart=$(this).siblings(".addToCart");
		
		//单品id
		var skuId = $(this).children("input[name='skuId']").val();

		var param = {
				skuId : skuId
		};
		$.eAjax({
			url : GLOBAL.WEBROOT + "/search/collect",
			data : param,
			success : function(returnInfo) {
				if (returnInfo.flag == '1') {
					eDialog.success("您已成功收藏该商品！");
				    collect.parent().addClass('hascoll');
				} else {
					eDialog.error(returnInfo.message);
				}
				$(this).bind('click');
			}
		});
		e.preventDefault();
	}));
	
	var parseMoney = function(data){
		var str = (data/100).toFixed(2) + '';
		var intSum = str.substring(0,str.indexOf("."));//取到整数部分.replace( /\B(?=(?:\d{3})+$)/g, ',' )
		var dot = str.substring(str.length,str.indexOf("."));//取到小数部分
		return ret = intSum + dot;
	};
	if(GLOBAL.WEBROOT.indexOf("manage") >-1){
		return;
	}else{
		$(".resultItem").each(function() {
			var $this = $(this);
			var gdsId = $this.find("input[name=pGdsId]").val();
			var skuId = $this.find("input[name=pSkuId]").val();
			var shopId = $this.find("input[name=pShopId]").val();
			var isLogin = $this.find("input[name=isLogin]").val();
			var realPrice = $this.find("input[name=pRealPrice]").val();
			var discountPrice = $this.find("input[name=pDiscountPrice]").val();
			var data = new Object();
			var item = $(this).find(".p-pjnum");
			var collect = $(this).find(".gdsCollect");
			data.gdsId = gdsId;
			data.skuId = skuId;
			data.shopId = shopId;
			data.isLogin = isLogin;
			data.realPrice = realPrice;
			data.discountPrice = discountPrice;
			$.eAjax({
				url : GLOBAL.WEBROOT + "/search/queryPromInfo",
				data : data,
				success : function(json) {
					if(json.price!=null  && json.price+"" !=""){
						$this.find("b[name=discountPrice]").html("&yen;"+parseMoney(json.price));
					}
					if(json.guidePrice!=null  && json.guidePrice+"" !=""){
						$this.find("b[name='guidePrice']").html("&yen;"+parseMoney(json.guidePrice));
					}
					$.each(json.promTypes, function(i, value) {
						item.append("<span class='p-mz'>" + value+ "</span>");
					});

					if (json.ifHavFav == '1') {
						collect.parent().addClass('hascoll');
					} else {
						collect.parent().removeClass('hascoll');
					}
				},
				error : function(e, xhr, opt) {
					// eDialog.alert(e);
				}
			});
		});
	}
	
});