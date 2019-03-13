
//页面初始化模块
$(function(){
    var pInit = function(){
    	var init = function(){
    		var _load_url_ = '/seller/order/delivery/delyedlist?';
    		_load_url_= addLoadParamsToUrl(_load_url_);
	    	//分页
	    	$('#pageControlbar').bPage({
	    	    url : GLOBAL.WEBROOT + _load_url_,
	    	    asyncLoad : true,
	    	    asyncTarget : '#tabContentDiv',
	    	    params : {

	    	    }
	    	});
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
var delivery_list = {
		publish:function(id){
			
			if(isNotEmpty(id)){
				var shopId  = $('select[name="shopId"]').val();
				window.location.href = GLOBAL.WEBROOT + '/seller/order/delivery/dosend/'+shopId+'/'+id;
			}
		}
	};