/**
 * gxq
 */
$(function(){
	//页面业务逻辑处理内容
   var _FLAG = "1";
   var _cartFlag = 0;//加入购物车的标识
   //异步获取商品图片===========start
	var queryGdsPictrues = function(gdsId,skuId){
    	$.eAjax({
			url : GLOBAL.WEBROOT + "/gdsdetail/querygdspictrues",
			data : {
				"skuId" :  skuId,
				"gdsId" : gdsId
			},
			dataType : "html",
			success : function(returnInfo) {
				$("#gdsPictruesList").empty();
				$("#gdsPictruesList").html(returnInfo);
			}
    	});
	};
	queryGdsPictrues($("#gdsId").val(),$("#skuId").val());
	//异步获取商品图片==========end
	
	//简介
	var getCkeditValue = function(obj,url){
		if(url && url != ""){
    		$.ajax({
				url : url,
				async : true,
				dataType : 'jsonp',
				jsonp : 'jsonpCallback',// 注意此处写死jsonCallback
				success : function(data) {
					obj.html(data.result);
				}
			 });
		}
	};
	
	if($("#gdsDesc").val()!=""){//有gdsDesc 展示gdsDesc
		$("#gdsDetailDesc").html('');
		getCkeditValue($("#gdsDetailDesc"),$("#gdsDesc").val());
	}
//        	getCkeditValue($("gdsDetailPartlist"),$("#gdsPartlist").val());
	$(".moreEditorParse").each(function(){
		var _this = $(this);
		getCkeditValue(_this,_this.attr('value'));
	});
	//获取分类路径=======start.
	$root_nav = $("#root-nav");
	$.eAjax({
		url : GLOBAL.WEBROOT + '/gdsdetail/querycatgcodelist',
		data : {
			"mainCatgs" : $("#mainCatgs").val()
		},
		async : true,
		type : "post",
		dataType : "json",
		success : function(datas) {
			$root_nav.html('');
			// 核心版本去除该特殊显示代码。
			/*if(datas.digitsBook==true){
				//是数字教材
				$("#gdsName-extends-desc").text('（数字教材）');
			}
			if(datas.ebook==true){
				//是电子书
				$("#gdsName-extends-desc").text('（电子书）');
			}
			if($("#offerReadOnline").val()=='1'&&datas.ifReadOnline=='1'){
				$("#shareMoreFunction").append("<a href='javascript:void(0);' id='readOnline'><i class='micon micon-page'></i>在线试读</a>");
			}*/
			var HTML = "<ul class='breadcrumb'>";//<li><a href='"+GLOBAL.WEBROOT+"/homepage'>首页</a></li>
			var cateroot = "<ul class='breadcrumb' style='padding: 0px 0px;background-color: #fff;color:#0043c5'>";
			$.each(datas.list, function(i, n) {
				
				if(i == 0){
					HTML +="<li>" +
					" <span class='divider'></span>" +
					"<a href='"+GLOBAL.WEBROOT+"/search?category="+n.catgCode+"'>"+n.catgName+"</a> " +
					"</li>";
			cateroot += "<li>" ;
				}else if(i>0){
					HTML +="<li>" +
					" <span class='divider'>></span>" +
					"<a href='"+GLOBAL.WEBROOT+"/search?category="+n.catgCode+"'>"+n.catgName+"</a> " +
					"</li>";
			cateroot += "<li>" ;
				}else{
					cateroot += " <span class='divider' style='color:#0043c5'>></span>";
				}
				cateroot += "<a style='color:#0043c5' href='"+GLOBAL.WEBROOT+"/search?category="+n.catgCode+"'>"+n.catgName+"</a> " +
				"</li>";
			});
			HTML += "</ul>";
			$("#cate-root-list").html(cateroot);
			$root_nav.html(HTML);
		}
	});
	//获取分类路径=======end
	
	//获取销售列表=======start
//	$("#seckill-buy").hide();
//	$("#pro-detail").hide();
//	$("#ynKill").show();
	var price = $("#appSpecPrice").val();
	if(price==null || price==0){
		price=$("#discountPrice").val();
	}
	var querySaleList = function(){
		var $containerMore = $("#promDetail");//点击查看更多促销详情
		var $containerOne = $("#onePromDetail");//展示在页面的促销详情
    	$.eAjax({
			url : GLOBAL.WEBROOT + '/gdsdetail/querysalelist',
			data : {
				"gdsId" : $("#gdsId").val(),
				"shopId" : $("#shopId").val(),
				"skuId" : $("#skuId").val(),
				"realPrice" : $("#price").val(),
				"discountPrice" : price,
				"gdsName" : $("#gdsName").val(),
				"shopName" : $("#shopName").val()
			},
			async : true,
			type : "post",
			dataType : "json",
			success : function(datas) {
				if(datas.seckill==null){
//					$("#seckill-buy").hide();
//					$("#pro-detail").hide();
//					$("#ynKill").show();
					$("#seckill-buy").css('display','none');
					$("#pro-detail").css('display','none');
					$("#ynKill").css('display','block');
				}else if(datas.seckill.exist==true && datas.seckill.start==true && datas.seckill !=null){
//					$("#seckill-buy").hide();
//					$("#pro-detail").show();
//					$("#ynKill").hide();
					$("#seckill-buy").css('display','none');
					$("#pro-detail").css('display','block');
					$("#ynKill").css('display','none');
					var systime;
					if(datas.seckill.systemTime != null){
						systime = datas.seckill.systemTime;
						$("#systime").val(systime);
					}else{
						systime = $("#systime").val();
					}
					countDown(datas.promInfoDTO.endTime,"#colockbox1");
					var readyTime = datas.promInfoDTO.endTime-systime;
					if(0<readyTime<1*60*20*1000){
						countReady(readyTime);
					}
				}else if(datas.seckill.exist==true && datas.seckill.start==false && datas.seckill !=null){
//					$("#pro-detail").hide();
//					$("#seckill-buy").show();
					$("#pro-detail").css('display','none');
					$("#seckill-buy").css('display','block');
					var startTime = "预计&nbsp"+getLocalTime(datas.promInfoDTO.startTime)+"&nbsp开始";
					$("#showStartTime").html(startTime);
					var systime;
					if(datas.seckill.systemTime != null){
						systime = datas.seckill.systemTime;
						$("#systime").val(systime);
					}else{
						systime = $("#systime").val();
					}
					var readyTime = datas.promInfoDTO.startTime-systime;
					if(0<readyTime<1*60*60*1000){
						countReady(readyTime);
					}
//					$("#ynKill").show();
					$("#ynKill").css('display','block');
				}else if(datas.seckill !=null && datas.seckill.exist==false && datas.seckill.start==false  ){
//					$("#seckill-buy").hide();
//					$("#pro-detail").hide();
//					$("#ynKill").show();
					
					$("#seckill-buy").css('display','none');
					$("#pro-detail").css('display','none');
					$("#ynKill").css('display','block');
					
				}
				$containerMore.html('');
				$containerOne.html('');
				var htmlContextMore = "";
				var htmlContextOne = "";
				if(datas.saleList != null && datas.saleList.length >= 1){
					htmlContextOne += "<span class='tit'>促销</span><div class='ui-selBox'>";
    				$.each(datas.saleList, function(i, n) {
    					if(i==0){
    						if(n.promSkuPriceRespDTO && n.promSkuPriceRespDTO !=null){
    							var beforePrice=$("#realPrice").html().replace(/[^0-9]/ig, ""); 
    							var flag=true;
    							if((beforePrice*100)+"" == n.promSkuPriceRespDTO.discountFinalPrice+""){
    								flag=false;
    							}
    							
    							if(n.promSkuPriceRespDTO.discountFinalPrice+"" !="" && n.promSkuPriceRespDTO.discountFinalPrice != null){
    								$("#realPrice").html("&yen;"+parseMoney(n.promSkuPriceRespDTO.discountFinalPrice));
    								$("#seckill").html("&yen;"+parseMoney(n.promSkuPriceRespDTO.discountFinalPrice));
    								$("#skuParamPrice").html("&yen;"+parseMoney(n.promSkuPriceRespDTO.discountFinalPrice));
    								
    							}
    							if(n.promSkuPriceRespDTO.discountCaclPrice+"" !="" && n.promSkuPriceRespDTO.discountCaclPrice != null && flag){
    								$("#guidePrice").html("&yen;"+parseMoney(n.promSkuPriceRespDTO.discountCaclPrice));
    							}
    						}
    					}
    					if(i <=2){
    						htmlContextOne += "" +
    						"<span class='selected'>"+n.promInfoDTO.promTypeName+"</span>";//"+n.promInfoDTO.promContent+"
    					}
    					htmlContextMore += "<li><span class='pro-mz'>"+n.promInfoDTO.promTypeName+"</span>" +
    							"<div class='mz-txt'>"+n.promInfoDTO.promTheme+"</div>";
    				});
    				htmlContextOne +="</div>"
    				htmlContextOne += "<span class='more'><i class='iconfont icon-more' data-am-offcanvas=\"{target: '#slideButtom'}\"></i></span>";
				}
			
				$containerOne.html(htmlContextOne);
				$containerMore.html(htmlContextMore);
			}
		});
	};
	if($("#gdsStatus").val()=='11'){
		querySaleList();
	}
	//获取销售列表=======end
	
	//秒杀时间====start
	function countDown(time,id){
		var day_elem = $(id).find('.day');
		var hour_elem = $(id).find('.hour');
		var minute_elem = $(id).find('.minute');
		var second_elem = $(id).find('.second');
		
		//if(typeof end_time == "string")
		var systime= $("#systime").val();
		var end_time = new Date(time).getTime(),//月份是实际月份-1
		sys_second = (end_time-systime)/1000;
		var timer = setInterval(function(){
			if (sys_second > 1) {
				sys_second -= 1;
				var day = Math.floor((sys_second / 3600) / 24);
				var hour = Math.floor((sys_second / 3600) % 24);
				var minute = Math.floor((sys_second / 60) % 60);
				var second = Math.floor(sys_second % 60);
				day_elem && $(day_elem).text(day);//计算天
				$(hour_elem).text(hour<10?"0"+hour:hour);//计算小时
				$(minute_elem).text(minute<10?"0"+minute:minute);//计算分钟
				$(second_elem).text(second<10?"0"+second:second);//计算秒杀
			} else { 
				clearInterval(timer);
//				alert("time to miaosha !");
			}
		}, 1000);
	}
	//秒杀时间====end
	
	$("span[name='skuprop']",$(".pro-ggTb")).each(function(){
		$this = $(this);
		$this.bind('click',function(){
			
			$(this).addClass("selected");
			$(this).siblings().removeClass("selected");
			_FLAG = '0';
			var hasSelectedSku = "";
			var skuParam = "[";
			$("span[name='skuprop']",$(".pro-ggTb")).each(function(){
				$obj = $(this);
				if($obj.hasClass('selected')){
					var propId = $obj.attr('propId');
        			var propName = $obj.attr('propName');
        			var value = $obj.attr('value');
        			var valueId = $obj.attr('valueId');
        			hasSelectedSku += "<span class='tit'>"+propName+"</span> <span>"+value+"</span> ";
        			skuParam = skuParam + "{propId:'"+propId+"',propName:'"+propName+"',value:'"+value+"',valueId:'"+valueId+"'},";
				}
			});
			skuParam += "]";
			$("#hasSelectedSku").html('');
			$("#hasSelectedSku").html(' <span>已选择</span> '+hasSelectedSku+'<span id="selectStock">1</span>件');
			var param = {};
			param.skuPropParam = skuParam;
			param.gdsId = $("#gdsId").val();
			param.shopId = $("#shopId").val();
			$.eAjax({
				url : GLOBAL.WEBROOT + "/gdsdetail/getskudetailinfo",
				data : param,
				success : function(returnInfo) {
					_FLAG = '1';
					var _realAmount = returnInfo.respDto.skuInfo.realAmount;
					$("#realPrice").html("&yen;"+parseMoney(returnInfo.respDto.skuInfo.discountPrice));
					$("#guidePrice").html("&yen;"+parseMoney(returnInfo.respDto.skuInfo.guidePrice));
					$("#skuParamPrice").html("&yen;"+parseMoney(returnInfo.respDto.skuInfo.discountPrice));
					$("#price").val(returnInfo.respDto.skuInfo.realPrice);
					$("#discountPrice").val(returnInfo.respDto.skuInfo.discountPrice);
					$("#gdsStatus").val(returnInfo.respDto.skuInfo.gdsStatus);
					$("#realAmount").text(_realAmount);
					$("#skuId").val(returnInfo.respDto.skuInfo.id);
					$("#skuParamSkuId").text(returnInfo.respDto.skuInfo.id);
					var $purchaseAmount = $("#purchaseAmount");
	        		if($purchaseAmount.attr("gdsTypeId")=="2"){
	        			purchaseAmount = $purchaseAmount.text();
	        		}else{
	        			$("#purchaseAmount").attr('max',_realAmount);
    					$("#purchaseAmount").val('1');
	        		}
					$("#skuProps").val(returnInfo.respDto.skuInfo.skuProps);
					var str = "";
					var _span = $("span[name='addToCart']");
					//核心添加根据是否需要库存
					if(returnInfo && returnInfo.respDto && returnInfo.respDto.gdsTypeRespDTO && returnInfo.respDto.gdsTypeRespDTO.ifNeedstock=="1"){//需要库存
						if(returnInfo.stockStatus == '00'){
							str = "缺货";
							$("#realAmount").text(_realAmount);
							$("#purchaseAmount").attr('max',_realAmount);
							if(!_span.hasClass('wbtn-disable')){
								_span.attr("disabled",'true');
								_span.addClass('wbtn-disable');
							}
						}else if(returnInfo.stockStatus == '01'){
							str = "紧张";
							if(_span.hasClass('wbtn-disable')){
								_span.removeAttr('disabled');
								_span.removeClass('wbtn-disable');
							}
						}else if(returnInfo.stockStatus == '02'){
							str = "充足";
							if(_span.hasClass('wbtn-disable')){
								_span.removeAttr('disabled');
								_span.removeClass('wbtn-disable');
							}
						}
					}else{
						//不需要库存，永远都有值
						str = "充足";
						$("#realAmount").text('999999999');
						$("#purchaseAmount").attr('max','999999999');
					}
					$("#stockStatusDesc").text(str);
					//商品图片
					$("img",$("#skuParamPic")).attr('src',returnInfo.respDto.skuInfo.mainPic.url);
					querySaleList();
					//queryautocombine();
				},
				exception : function(){
				}
			});
		});
	});
	var parseMoney = function(data){
		var str = (data/100).toFixed(2) + '';
		var intSum = str.substring(0,str.indexOf("."));//取到整数部分.replace( /\B(?=(?:\d{3})+$)/g, ',' )
		var dot = str.substring(str.length,str.indexOf("."));//取到小数部分
		return ret = intSum + dot;
	};
	//加入购物车
	var getaddToCartParam = function(obj,cartType,shopId,ordCartItemList,sCallback){
		var param = {
				cartType : cartType,
				shopId : shopId,
				ordCartItemList	: JSON.stringify(ordCartItemList)
		};
		$.eAjax({
			url : GLOBAL.WEBROOT + "/order/cart/add",
			data : param,
			success : function(returnInfo) {
				if(returnInfo.ecpBaseResponseVO.resultFlag == "ok"){
	                new AmLoad({content:'加入购物车成功'});
	                if(sCallback && $.isFunction(sCallback)){
						sCallback();
					}
                }else{
                	new AmLoad({content:'加入购物车失败',type:'2'});
                }
				_cartFlag = "0";
				$(obj).bind('click');
			},
			exception : function(){
				$(obj).bind('click');
			}
		});
	};
	
	//加入购物车事件绑定====================start
	$("span[name='addToCart']").click(function(){
		var $this = this;
		if(_cartFlag == '1'){
			return;
		}
		_cartFlag = 1;
		if($(this).attr('disabled')=='disabled'){
			return;
		}
		var buyFlag = 0;
		if($("#ifNeedstock").val()=='1'){
			var STOCK_LACK_THRESHOLD = Number($("#STOCK_LACK_THRESHOLD").val());
    		var purchaseAmount = Number($("#purchaseAmount").val());
    		var max = Number($("#purchaseAmount").attr('max'));
    		var _orderAlert = $("#orderAlert");
    		if(purchaseAmount>(max-STOCK_LACK_THRESHOLD)){
    			_orderAlert.show();
    			_orderAlert.html('购买量已超过实际库存量');
    			$(this).bind('click');
    			_cartFlag = 0;
    			return;
    		}else{
    			_orderAlert.hide();
    		}
		}

		if($("#ifBuyonce").val()=='0'){
			//虚拟产品，则判断是否已购买
			$.eAjax({
				url : GLOBAL.WEBROOT + "/gdsdetail/wetherbuyed",
				data : {skuId:$("#skuId").val(),gdsTypeId:$("#gdsTypeId").val()},
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
			_cartFlag = 0;
			return;
		}
		//购物车类型
		var cartType = "01";
		//店铺id
		var shopId = $("#shopId").val();
		//是否数字印刷
		var prnFlag = '0';
		var ordCartItemList = new Array();
		commonPriceAddToCart(prnFlag,cartType,shopId,ordCartItemList);
		getaddToCartParam($this,cartType,shopId,ordCartItemList,mainButtomNavbar.showBuyNum);
		//mainButtomNavbar.showBuyNum();
	});
	
	//普通价格商品加入购物车
	var commonPriceAddToCart = function(prnFlag,cartType,shopId,ordCartItemList){
		//购买数量
		var purchaseAmount = 0;
		var $purchaseAmount = $("#purchaseAmount");
		if($purchaseAmount.attr("gdsTypeId")=="2"){
			purchaseAmount = $purchaseAmount.text();
		}else{
			purchaseAmount = $purchaseAmount.val();
		}
		
		if(undefined == purchaseAmount || ""==purchaseAmount){
			purchaseAmount = "1";
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
		var ordCartItemListObj = {
				cartType : cartType,
				orderAmount : purchaseAmount,
				shopId : shopId,
				gdsId : gdsId,
				gdsName : gdsName,
				skuId : skuId,
				mainCatgs : mainCatgs,
				skuInfo : skuProps,
				groupDetail : groupDetail,
				groupType : groupType,
				promId : "",
				gdsType : gdsTypeId,
				prnFlag : prnFlag,
				scoreTypeId : ''
		};
		ordCartItemList.push(ordCartItemListObj);
	};
	//阶梯价格商品加入购物车
	var ladderPriceAddToCart = function(ordCartItemList){
		
	};
	//加入购物车事件绑定end==================
	
	
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
    		if(++value > 9999999){
    			return;
    		}
    		$("#purchaseAmount").val(value);
    		$("#selectStock").text(value);
			$("#realAmount").text(--stock);
    	} else {
    		$("#purchaseAmount").val(1);
    		$("#selectStock").text(1);
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
    		$("#selectStock").text(value);
			$("#realAmount").text(++stock);
    	} else {
    		$("#purchaseAmount").val(1);
    		$("#selectStock").text(1);
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
		if(Number(input.val())>9999999){
			input.val(9999999);
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
	/**
	 * 添加收藏
	 */
	var _collect = 0;
	$("#addCollect").click(function(){
		var $this = $(this);
		if(_collect == 1){
			return;
		}
		_collect = 1;
		var collectId = "";
		if($this.attr("collectId") != undefined || $this.attr("collectId")!= ""){
			collectId = $this.attr("collectId");
		}
		$.eAjax({
			url : GLOBAL.WEBROOT + "/addCollection/add",
			data : {
				"skuId" : $("#skuId").val(),
				"collectId" : collectId
			},
			success : function(returnInfo) {
				if (returnInfo.resultFlag == 'ok') {
					$this.attr("collectId",returnInfo.resultMsg);
					if(returnInfo.resultMsg==""){
						$this.removeClass();
						new AmLoad({content:'取消收藏商品成功！'});
					}else{
						$this.removeClass().addClass('active');
						new AmLoad({content:'收藏商品成功！'});
					}
				} else {
					new AmLoad({content:returnInfo.resultMsg,type:'2'});
				}
				_collect = 0;
			}
		});
	});
	//判断该商品是否已经被收藏 ======start
	$.eAjax({
		url : GLOBAL.WEBROOT + "/gdsdetail/querygdscollect",
		data : {"gdsId":$("#gdsId").val()},
		success : function(json) {
			if (json.ifHavFav == '1') {
				$("#addCollect").attr('collectId',json.collectId);
				$("#addCollect").removeClass().addClass('active');
			} else {
				$("#addCollect").removeClass();
			}
		},
		error : function(e, xhr, opt) {
		}
	});
	//判断该商品是否已经被收藏 ======end
	
	var fixedCombineBuyBtn = function(){
		//固定搭配套餐组合立即购买按钮事件绑定
    	$("span[name='fixedCombineBuyBtn']").each(function(){
    		$(this).click(function(){
    			var _statusC = 0;
    			var _notOnsaleInfo = "您好！</br>商品：";
    			var _this = $(this);
        		var _autoObj = _this.parent().parent().parent().parent();
        		//购物车类型
        		var cartType = "01";
        		//组合类型，自由购买
        		var groupType = "1";
        		//购买数量
        		var purchaseAmount = 1;
        		//商品名称
        		var gdsName = _autoObj.find("#fixedFixedGdsName").val();
        		//店铺id
        		var shopId = _autoObj.find("#fixedFixedShopId").val();
        		//店铺名称
//    	        		var shopName = _autoObj.find("#fixedFixedShopName").val();
        		//组合明细 	默认为skuId，组合搭配的话，为主skuId:次skuId:次skuId	1	
        		var ordCartItemList = new Array();
        		//固定商品的单品编码
        		var skuId  = _autoObj.find("#fixedFixedSkuId").val();
        		var fixedIndex = _autoObj.find("#fixedIndex").val();
        		var arr = new Array();
        		arr[fixedIndex] = skuId;
        		var tempGdsName=gdsName;
        		var fixedStatus = _autoObj.find("#fixedGdsStatus").val();
        		if(fixedStatus!="11"){
        			_statusC ++;
        			_notOnsaleInfo += tempGdsName;
        		}
        		if(_statusC >0){
        			eDialog.alert(_notOnsaleInfo+" 卖家还没有上架，您暂时还不能购买该组合！");
        			return ;
        		}
        		_autoObj.find('input[name="fixedCombineCheck"]').each(function(){
        			var $this = $(this);
        			//单品编码
	        		var skuIds  = $this.attr("skuId");
    	        	var autoSkuIndex = $this.attr("index");
    	        	var gdsStatus = $this.attr("gdsStatus");
    	        	var tempGdsName=$this.attr("gdsName");
    	        	if(gdsStatus!="11"){
    	        		_statusC ++;
    	        		_notOnsaleInfo += tempGdsName;
    	        	}
    	        	arr[autoSkuIndex] = skuIds;
        		});
        		if(_statusC >0){
        			eDialog.alert(_notOnsaleInfo+" 卖家还没有上架，您暂时还不能购买该组合！");
        			return ;
        		}
        		var groupDetail = "";
        		for(var i = 0;i < arr.length;i++){
        			//组合明细 	默认为skuId，组合搭配的话，为主skuId:次skuId:次skuId	1	
        			if(arr[i]!=undefined){
        				groupDetail  = groupDetail+":"+arr[i];
        			}
        		}
        		_autoObj.find('input[name="fixedCombineCheck"]').each(function(){
        			var $this = $(this);
        			//商品id
	        		var gdsId = $this.attr("gdsId");
	        		//商品名称
	        		var gdsName = $this.attr("gdsName");
	        		//商品主分类
	        		var mainCatgs = $this.attr("mainCatgs");
	        		//选择的单品编码
	        		var skuId  = $this.attr("skuId");
	        		//单品属性串
	        		var skuProps = $this.attr("skuProps");
	        		//组合明细 	默认为skuId，组合搭配的话，为主skuId:次skuId:次skuId	1	
	        		//促销活动ID
	        		var promId = $this.attr("promId");
	        		//商品类型id
	        		var gdsTypeId = $this.attr("gdsTypeId");
	        		var ordCartItemListObj = {
	        				cartType : cartType,
	        				orderAmount : purchaseAmount,
	        				shopId : shopId,
	        				gdsId : gdsId,
	        				gdsName : gdsName,
	        				skuId : skuId,
	        				mainCatgs : mainCatgs,
	        				skuInfo : skuProps,
	        				groupType : groupType,
	        				groupDetail : groupDetail,
	        				promId : promId,
	        				gdsType : gdsTypeId,
	        				prnFlag : '0',
	        				scoreTypeId : ''
	        		};
	        		ordCartItemList.push(ordCartItemListObj);
        		});
        		//商品id
        		var gdsId = _autoObj.find("#fixedFixedGdsId").val();
        		//商品主分类
        		var mainCatgs = _autoObj.find("#fixedFixedMainCatgs").val();
        		//商品名称
        		var gdsName = _autoObj.find("#fixedFixedGdsName").val();
        		//单品属性串
        		var skuProps = _autoObj.find("#fixedFixedSkuProps").val();
        		//促销活动ID
        		var promId = _autoObj.find("#fixedFixedPromId").val();
        		//商品类型id
        		var gdsTypeId = _autoObj.find("#fixedFixedGdsTypeId").val();
        		var ordCartItemListObj = {
        				cartType : cartType,
        				orderAmount : purchaseAmount,
        				shopId : shopId,
        				gdsId : gdsId,
        				gdsName : gdsName,
        				skuId : skuId,
        				mainCatgs : mainCatgs,
        				skuInfo : skuProps,
        				groupType : groupType,
        				groupDetail : groupDetail,
        				promId : promId,
        				gdsType : gdsTypeId,
        				prnFlag : '0',
        				scoreTypeId : ''
        		};
        		ordCartItemList.push(ordCartItemListObj);
        		getaddToCartParam(this,cartType,shopId,ordCartItemList,mainButtomNavbar.showBuyNum);
    		});
    	});
    	/*自由搭配选择绑定事件================end*/
	};
	
	//异步加载固定搭配套餐。这里只加载固定搭配的套餐===========start
	var queryfixedcombine = function(){
		$.eAjax({
			url : GLOBAL.WEBROOT + "/gdsdetail/queryfixedcombine",
			data : {
				"gdsId" : $("#gdsId").val(),
				"shopId" : $("#shopId").val(),
				"skuId" : $("#skuId").val()
			},
			dataType : "html",
			success : function(returnInfo) {
				$("#fixedCombine").empty();
				$("#fixedCombine").html(returnInfo);
	        	//固定搭配购买事件绑定
	        	fixedCombineBuyBtn();
			}
		});
	};
	queryfixedcombine();
	//异步加载固定搭配套餐。这里只加载固定搭配的套餐===========end
	var strToPrice = function(data) {
		var str = (data / 100).toFixed(2) + '';
		var intSum = str.substring(0, str.indexOf("."));// 取到整数部分.replace(
														// /\B(?=(?:\d{3})+$)/g,
														// ',' )
		var dot = str.substring(str.length, str.indexOf("."));// 取到小数部分
		var ret = intSum + dot;
		return ret;
	};
	//异步加载商品评论===========start
	$.eAjax({
		url : GLOBAL.WEBROOT + "/gdsdetail/querygdseval",
		data : {
			"gdsId" : $("#gdsId").val(),
			"skuId" : $("#skuId").val()
		},
		dataType : "html",
		success : function(returnInfo) {
			$("#evaluationList").empty();
			$("#evaluationList").html(returnInfo);
		}
	});
	
	//异步加载商品评论===========end
	
	//异步加载相同分类推荐===========start
	var querycommondcat = function(pageNumber,pageSize){
//        		$("#commondCat").append("<div class='loading-small'></div>");
    	$.eAjax({
			url : GLOBAL.WEBROOT + "/gdsdetail/querycommondcat",
			data : {
				"categCode" : $("#mainCatgs").val(),
				"gdsId" : $("#gdsId").val(),
				"skuId" : $("#skuId").val(),
				"pageNumber" : pageNumber,
				"pageSize" : pageSize
			},
			dataType : "html",
			success : function(returnInfo) {
				$("#sameCategory").empty();
				$("#sameCategory").html(returnInfo);
			}
    	});
	};
	querycommondcat(1,15);
	//异步加载相同分类推荐===========end
	//同类推荐换一批事件绑定
	$("#samecategoryChange").click(function(){
		$('.am-slider-category').flexslider('next');
//		var pageNumber = Number($("#samecategoryPageNo").val())+1;
//		querycommondcat(pageNumber,6);
	});
	mainButtomNavbar.showBuyNum();
	
});
function modiAmount(obj){
	$("#selectStock").text($(obj).val());
}
var mainButtomNavbar = {
		"showBuyNum":function(){
			$.eAjax({
				url : GLOBAL.WEBROOT + "/order/getcartcount",
				data : "",
				success : function(returnInfo) {
					if(0<returnInfo){
						$('.num-tip').html(returnInfo);
						$('.num-tip').show();
					}else{
						$('.num-tip').html("");
						$('.num-tip').hide();
					}
				},
				error: function(){
					$('.num-tip').html("");
					$('.num-tip').hide();
				},
				exception : function(){
					$('.num-tip').html("");
					$('.num-tip').hide();
				}
			});
		}
};
function getLocalTime(nS) {
	var date = new Date(nS);
	Y = date.getFullYear() + '年';
	M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '月';
	D = date.getDate() + '日 ';
	h = date.getHours() + ':';
	m = date.getMinutes();
	s = date.getSeconds();
	return Y+M+D+h+m;
//	return new Date(parseInt(nS) * 1000).toLocaleString().replace(/:\d{1,2}$/,' ');
	}
function countReady(time){
//	sys_second = (end_time-new Date().getTime())/1000;
	sys_second = time/1000;
	var timer = setInterval(function(){
		if (sys_second > 1) {
			sys_second -= 1;
			var day = Math.floor((sys_second / 3600) / 24);
			var hour = Math.floor((sys_second / 3600) % 24);
			var minute = Math.floor((sys_second / 60) % 60);
			var second = Math.floor(sys_second % 60);
		} else { 
			clearInterval(timer);
			window.location.reload();
		}
	}, 1000);
}
$('#abstract').click(function(){
    $(this).toggleClass('open');
})
