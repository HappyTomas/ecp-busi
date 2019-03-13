$(function(){
	//发布优惠券
	$('.publish').click(function(){
		$(this).attr('disabled',"true"); 
		window.location.href = GLOBAL.WEBROOT+'/coup/add/'+this.id;
	});
	//返回
	$('.btnReturn').click(function(){
		window.location.href = GLOBAL.WEBROOT+'/coupinfo';
	});
	//错误信息
	$('#btnShowError').click(function(){
		$('div.formValidateMessages').slideToggle('fast');
	});
});
