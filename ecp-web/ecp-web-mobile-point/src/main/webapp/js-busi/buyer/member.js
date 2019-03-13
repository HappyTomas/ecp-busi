
//页面初始化模块
$(function(){
    //跳转个人信息
	$('#infomation').on('click',function(){
		window.location.href=GLOBAL.WEBROOT+"/infomation/index"; 
	});
	
	$('#message').on('click',function(){
		window.location.href=GLOBAL.WEBROOT+"/msg/index"; 
	});
	
	$('#score').click(function(){
		window.location.href=GLOBAL.WEBROOT+"/score/index";
	})
	
	$('#favgoods').on('click',function(){
		window.location.href=GLOBAL.WEBROOT+"/favgoods";
	});
	
	$('#membercoup').on('click',function(){
		window.location.href=GLOBAL.WEBROOT+"/membercoup/index";
	});
	
	$('#pointOrder').on('click',function(){
		window.location.href=GLOBAL.WEBROOT+"/point/order/my";
	});
	
	$('#pointOrder').on('click',function(){
		window.location.href=GLOBAL.WEBROOT+"/point/order/my";
	});
	
	$('#orderMy').on('click',function(){
		window.location.href=GLOBAL.WEBROOT+"/order/my";
	});
	
	$('#wallet').on('click',function(){
		window.location.href=GLOBAL.WEBROOT+"/wallet";
	});
});