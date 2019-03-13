$addCartAndColl  = (function(){
	var init = function(){
		//加入购物车
		$(".ecp-add-cart").live("click",function(){
			var _obj = $(this);
			var buyFlag = 0;
			if(_obj.attr('data-gdsTypeId')=='2'){
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
			 //获取参数数据
			 var param = {
			    "skuId" : $(this).attr("data-skuId"),
			    "amount" : $(this).attr("data-amount")
			 };
			 
			 $.eAjax({
				 url : $webroot + '/order/cart/mini/add',
					data : param,
					async : true,
					type : "post",
					dataType : "json",
					success : function(returnInfo) {
						if(returnInfo.ecpBaseResponseVO.resultFlag == 'ok'){//加入成功处理
							var startOffset = _obj.offset();
			                var endOffset = $("#carGus").offset();
			                var img = _obj.attr('data-image');
			                var flyer = $('<img class="u-flyer"  src="'+img+'">');
			                flyer.fly({
								speed : 1,
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
							$.pageTop.setOrderCount({url:$webroot + '/order/getcartcount'});
						}else{//失败
							eDialog.alert(returnInfo.ecpBaseResponseVO.resultMsg);
						}
					},
					error : function(){//失败
						eDialog.alert("加入购物车失败！请检查网络");
					}
			 });//end of eAjax
	        });//end of 购物车

		 //加入收藏
		$(".ecp-add-coll").live("click",function(){
			 //获取参数数据
			 var param = {
			    "skuId" : $(this).attr("data-skuId")
			 };
			 
			 $.eAjax({
				 url : $webroot + '/gdsdetail/add',
					data : param,
					async : true,
					type : "post",
					dataType : "json",
					success : function(returnInfo) {
						if (returnInfo.resultFlag == 'ok') {
							eDialog.success("您已成功收藏该商品！");
						} else {
							eDialog.error(returnInfo.resultMsg);
						}
					},
					error : function(){//失败
						eDialog.alert("加入收藏失败！请检查网络");
					}
			 });//end of 
	        });//end of 收藏
	};
	
	return {
		'init':init
	};

	
})();

pageConfig.config({
	//指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
	plugin : ['ePageTop'],
	//指定页面
	init : function(){
		var ppp = $addCartAndColl;
		ppp.init();
	}
});

