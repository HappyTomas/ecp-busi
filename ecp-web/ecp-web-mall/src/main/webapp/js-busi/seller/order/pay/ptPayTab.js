//页面初始化模块
$(function(){
    var pInit = function(){
    	var init = function(){
        	var pt_pay_url = GLOBAL.WEBROOT + '/seller/order/bankpay/ptPayList?';
        	pt_pay_url= addLoadParamsToPayUrl2(pt_pay_url);
	    	//分页
	    	$('#pageControlbar').bPage({
	    	    url : pt_pay_url,
	    	    asyncLoad : true,
	    	    asyncTarget : '#tabContentDiv',
	    	    params : {

	    	    }
	    	});
	    	//订单详情
			$('.orderId').click(function(){
				var url =  "";
	        	var tr = $(this).closest('tr');
	        	var orderId = $(".orderId",$(tr)).text();
	        	url = GLOBAL.WEBROOT+"/seller/order/orderdetail/detail/"+orderId;
	        	window.open(url);
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