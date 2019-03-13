/**
 * 
 */

$(function() {
	var parseMoney = function(data){
		var str = (data/100).toFixed(2) + '';
		var intSum = str.substring(0,str.indexOf("."));//取到整数部分.replace( /\B(?=(?:\d{3})+$)/g, ',' )
		var dot = str.substring(str.length,str.indexOf("."));//取到小数部分
		return ret = intSum + dot;
	};
	$(".resultItem").each(
			function() {
				var $this = $(this);
				var gdsId = $this.find("input[name=pGdsId]").val();
				var skuId = $this.find("input[name=pSkuId]").val();
				var shopId = $this.find("input[name=pShopId]").val();
				var isLogin = $this.find("input[name=isLogin]").val();
				var realPrice = $this.find("input[name=pRealPrice]").val();
				var discountPrice = $this.find("input[name=pDiscountPrice]").val();
				var ifFree=$this.find("input[name=pIfFree]").val();
				var data = new Object();
				var item = $(this).find(".p-pjnum");
				var collect = $(this).find(".gdsCollect");
				data.gdsId = gdsId;
				data.skuId = skuId;
				data.shopId = shopId;
				data.isLogin = isLogin;
				data.realPrice = realPrice;
				data.discountPrice = discountPrice;
				data.ifFree=ifFree;
				$.eAjax({
					url : GLOBAL.WEBROOT + "/shopgdssearch/queryPromInfo",
					data : data,
					success : function(json) {
						
						var beforePrice=$this.find("b[name=discountPrice]").html().replace(/[^0-9]/ig, ""); 
						var flag=true;
						if(beforePrice+"" == json.price+""){
							flag=false;
						}
						if(json.price!=null  && json.price+"" !=""  && flag){
							$this.find("b[name=discountPrice]").html("&yen;"+parseMoney(json.price));
						}
						if(json.guidePrice!=null  && json.guidePrice+"" !=""  && flag){
							$this.find("b[name='guidePrice']").html("&yen;"+parseMoney(json.guidePrice));
						}
						$.each(json.promTypes, function(i, value) {
							item.append("<span class='mz'>" + value+ "</span>");
						});

						if (json.ifHavFav == '1') {
							collect.find("i").removeClass().addClass('micon micon-havfav');
						} else {
							collect.find("i").removeClass().addClass('micon micon-fav');
						}
					},
					error : function(e, xhr, opt) {
						// eDialog.alert(e);
					}
				});
			});

	$("#shopGdsPageMainBox").find(".item").each(function() {
		var $this = $(this);
		var gdsId = $this.find("input[name=pGdsId]").val();
		var skuId = $this.find("input[name=pSkuId]").val();
		var shopId = $this.find("input[name=pShopId]").val();
		var isLogin = $this.find("input[name=isLogin]").val();
		var realPrice = $this.find("input[name=pRealPrice]").val();
		var discountPrice = $this.find("input[name=pDiscountPrice]").val();
		var ifFree=$this.find("input[name=pIfFree]").val();
		var data = new Object();
		var item = $this.find(".pric");
		var collect = $this.find(".gdsCollect");
		data.gdsId = gdsId;
		data.skuId = skuId;
		data.shopId = shopId;
		data.isLogin = isLogin;
		data.realPrice = realPrice;
		data.discountPrice = discountPrice;
		data.ifFree=ifFree;
		$.eAjax({
			url : GLOBAL.WEBROOT + "/shopgdssearch/queryPromInfo",

			data : data,
			success : function(json) {
				var beforePrice=$this.find("span[class='rpric']").html().replace(/[^0-9]/ig, ""); 
				var flag=true;
				if(beforePrice+"" == json.price+""){
					flag=false;
				}
				if(json.price!=null  && json.price+"" !=""  && flag){
					$this.find("span[class='rpric']").html("&yen;"+parseMoney(json.price));
				}
				if(json.guidePrice!=null  && json.guidePrice+"" !=""  && flag){
					$this.find("span[class='dj']").html("定价：<b>&yen;"+parseMoney(json.guidePrice)+"</b>");
				}
				$.each(json.promTypes, function(i, value) {
					item.append("<span class='mz'>" + value + "<span>")
				});

				if (json.ifHavFav == '1') {
					collect.find("i").removeClass().addClass(
							'micon micon-havfav');
				} else {
					collect.find("i").removeClass().addClass(
							'micon micon-fav');
				}
			},
			error : function(e, xhr, opt) {
				// eDialog.alert("e");
			}
		});
	});
	
	/******************************gxq判断产品是否是数字教材还是电子书******************************/
	$(".resultItem").each(function() {
		var $this = $(this);
		$.eAjax({
			url : GLOBAL.WEBROOT + "/gdsdetail/ifebookordigitsbook",
			data : {mainCatgs:$this.find("input[name=pMainCatgs]").val()},
			async : false,
			success : function(returnInfo) {
				if (returnInfo.digitsBook == true) {
					$this.find("#gdsName-extends-desc").text('（数字教材）');
				}
				if(returnInfo.ebook == true){
					$this.find("#gdsName-extends-desc").text('（电子书）');
				}
			},
			exception : function(){
				
			}
		});
	});
	$("#shopGdsPageMainBox").find(".item").each(function() {
		var $this = $(this);
		$.eAjax({
			url : GLOBAL.WEBROOT + "/gdsdetail/ifebookordigitsbook",
			data : {mainCatgs:$this.find("input[name=pMainCatgs]").val()},
			async : false,
			success : function(returnInfo) {
				if (returnInfo.digitsBook == true) {
					$this.find("#gdsName-extends-desc").text('（数字教材）');
				}
				if(returnInfo.ebook == true){
					$this.find("#gdsName-extends-desc").text('（电子书）');
				}
			},
			exception : function(){
				
			}
		});
	});
	
});