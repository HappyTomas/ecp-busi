//页面初始化模块
$(function(){ 
	var param = {};
	param.shopId = $("#shopId").val(); 
	$.eAjax({
		url:GLOBAL.WEBROOT+'/payresult/orderInfo',
		data:param,
		success:function(data){
			 
			
		}
	}); 
});