$(function(){
	if($("#showWhat").val()=="shop"){
		$("#shopTab").trigger('click');
	}
	//加载商品的
	$('#wrapper1').height($(window).height()-$('.am-header').height());
    var loadScroll = new LoadScroll("wrapper1", {
        url: GLOBAL.WEBROOT+'/favgoods/gridlist',
        isAjax:true,
        params:{
        }
	});
    //加载店铺的
	$('#wrapper2').height($(window).height()-$('.am-header').height());
	    var loadScroll = new LoadScroll("wrapper2", {
	        url: GLOBAL.WEBROOT+'/shopfav/listdata',
	        isAjax:true,
	        params:{
	            
	        }
	});
});
var FavGoods = {
      //删除一条记录
      addToCart : function(skuId,obj,gdsTypeId){
    	  var buyFlag = 0;
		  //虚拟产品，则判断是否已购买
			$.eAjax({
					url : GLOBAL.WEBROOT + "/gdsdetail/wetherbuyed",
			data : {skuId:skuId},
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
    	  if(buyFlag == 1){
    		  eDialog.alert("抱歉，该商品只允许购买一次！");
    		  return;
    	  }
    	  $.eAjax({
				url : GLOBAL.WEBROOT + "/order/cart/mini/add",
				data : {"skuId" : skuId,"amount" : 1},
				plugin : ['ePageTop'],
				success : function(returnInfo) {
					if(returnInfo.ecpBaseResponseVO.resultFlag=="ok"){
						new AmLoad({content:'加入购物车成功'});
					}else{
						new AmLoad({content:'加入购物车失败',type:'2'});
					}
				 }
			 });
      },
      deleteOne : function (id){
    	  eDialog.confirm("确定要取消关注吗？",function(){
    		 $.eAjax({
				url : GLOBAL.WEBROOT + "/favgoods/collremove",
				data : {"id" : id},
				success : function(returnInfo) {
					if(returnInfo.resultFlag=='ok'){
						window.location.href = GLOBAL.WEBROOT + "/favgoods"; 
					 }else{
						 new AmLoad({content:'取消关注失败',type:'2'});
					 }
				 }
			 });
      	});
      },
      //取消关注
      cancelShop : function (obj) {
      	eDialog.confirm("确定要取消关注吗？",function(){
      		$.eAjax({
  				url : GLOBAL.WEBROOT + "/shopfav/cancel",
  				data : {'shopId':obj},
  				datatype:'json',
  				success : function(returnInfo) {
  					window.location.href = GLOBAL.WEBROOT + "/shopfav/index"; 
  				}
  			});
      	});
      }
};