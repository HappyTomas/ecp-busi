$(function(){
	var timer = setInterval(function(){
		$.eAjax({
            url: GLOBAL.WEBROOT + '/payresult/getPayStatus',
            data: {"orderIds":$("#orderIds").val()},
            success: function (data) {
                if (data.payFlag=="1") { //支付状态为1表示支付成功
                	clearInterval(timer);
                	var joinOrderId = $("#joinOrderId").val();
                	var site = $("#site1").val();
                	window.location.replace(site +'/payresult/9007/'+joinOrderId);
                }
            }
        });
	}, 3000);
});