$(function(){

	//处理弹出窗口登录超时问题
	//2016.06.08
	//曾海沥
	if(window.self != window.top) window.top.location.replace(GLOBAL.WEBROOT + '/login');
	
	//处理嵌入式页面登录超时问题
	//2016.06.16
	//曾海沥
	var body = $('div.loginMainBodyBox').closest('body');
	if(!$(body).hasClass('loginMainBodyBox')) window.top.location.replace(GLOBAL.WEBROOT + '/login');


	$('#loginsubmit').click(function(){
	
		var Referer = $('#Referer').val();
		
		/*页面加上用户名，密码的非空校验  2016-5-3 by huangxl5*/
		var userName = $("input[name='j_username']").val();
		var password = $("input[name='j_password']").val();
		if (userName.trim() == '') {
			eDialog.alert("您好，请输入用户名。");
			return;
		}
		if (password.trim() == '') {
			eDialog.alert("您好，请输入密码。");
			return;
		}
		$.eAjax({
			url : GLOBAL.WEBROOT+'/j_spring_security_check',
			data : ebcForm.formParams($("#loginform")),
			datatype:'json',
			success : function(returnInfo) {
				if(!Referer || Referer == ""||Referer =="undefined" ||Referer==undefined){
				window.location.href = GLOBAL.WEBROOT + '/homepage';
				}else{
					if (Referer.indexOf('http') > -1) {
						window.location.href = Referer;
					} else {
						window.location.href = GLOBAL.WEBROOT + Referer;
					}
				}
			},
			exception:function(){
				//eDialog.alert("登录异常");
			}
			});
		});
	
	$('#loginform').keydown(function(e){
		if(e.keyCode==13){
		   $('#loginsubmit').click();
		}
		});

});