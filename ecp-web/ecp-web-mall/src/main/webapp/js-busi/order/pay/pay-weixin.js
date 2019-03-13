$(function(){
	var timer = setInterval(function(){
		$.eAjax({
	        url: GLOBAL.WEBROOT + '/payresult/getPayStatus?time='+new Date().getTime()+'&orderIds='+$("#orderIds").val(),
	        success: function (data) {
	            if (data.payFlag=="1") { //支付状态为1表示支付成功
	            	clearInterval(timer);
	            	var joinOrderId = $("#joinOrderId").val();
	            	window.location.replace(GLOBAL.WEBROOT+'/payresult/9007/'+joinOrderId);
	            }
	        }
	    });
	}, 3000);
});