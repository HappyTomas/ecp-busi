$(function(){
	//处理弹出窗口登录超时问题
	//2016.06.08
	//曾海沥
	if(window.self != window.top) window.top.location.replace($webroot + 'login');
	
	//处理嵌入式页面登录超时问题
	//2016.06.16
	//曾海沥
	var body = $('#loginMainBox').closest('body');
	if(!$(body).hasClass('loginMainBox')) window.top.location.replace($webroot + 'login');
	
	
	$("#refleshCaptchaCode").click(function(){
		$("#captchaCodeImg").attr("src",GLOBAL.WEBROOT + "/captcha/CapthcaImage?"+(new Date()).getTime());
	});
	
	$('#loginsubmit').click(function(){
		$.eAjax({
			url : GLOBAL.WEBROOT+'/j_spring_security_check',
			data : ebcForm.formParams($("#loginform")),
			datatype:'json',
			success : function(returnInfo) {	
				window.location.href = GLOBAL.WEBROOT + '/serv/chat';
			},
			exception:function(){
				$("#captchaCodeImg").attr("src",GLOBAL.WEBROOT + "/captcha/CapthcaImage?"+(new Date()).getTime());
			}
		});
	});
	
	$('#btnReset').click(function(){
		ebcForm.resetForm('#loginform');
	});
	
	$('#checkcodediv').keydown(function(e){
		if(e.keyCode==13) $('#loginsubmit').click();
	});
	
	
	//清空菜单定位参数
	//曾海沥
	$.removeCookie('currentPageMenuId',{ path: '/' });
	$('#usernamediv').focus();
});