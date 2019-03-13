$(function(){
    var pInit = function(){
    	var init = function(){

    		var orderId = $('input[name="orderId"]').val();
	    	//分页
	    	$('#pageControlbar').bPage({
	    		
	    	    url : GLOBAL.WEBROOT + '/seller/order/delivery/ordersubs?orderId='+orderId,
	    	    asyncLoad : true,
	    	    asyncTarget : '#subOrdersTableContextdiv',
	    	    params : {
//	    	    	beginDate : $('input[name="begDate"]').val(),
//	    	    	endDate : $('input[name="endDate"]').val(),
//	    	    	scoreType : $('#siteId').val()
	    	    }
	    	});
	    	
		};

    	return {
    		init : init
    	};
	};    	
	pageConfig.config({
		//指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
		plugin : ['bPage'],
		//指定页面
		init : function(){
			var scoreList = new pInit();
			scoreList.init();
		}
	});
});