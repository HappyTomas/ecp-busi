$(function(){
	
	$("#refleshCaptchaCode").click(function(){
		$("#captchaCodeImg").attr("src","captcha/CapthcaImage?"+(new Date()).getTime());
	});
	
	$('.btn-danger').on('click',function(){
		if(!$("#formRegist").valid()) return false;
		if ($('#isExist').html() != "该用户名可以使用") return false;
		var val = ebcForm.formParams($("#formRegist"));
	 	$.eAjax({
			url : $webroot+'/register/regist',
			data : val,
			datatype:'json',
			async : false,
			success : function(returnInfo) {
				if(returnInfo.resultFlag=='ok'){
					$('#j_username').val($('#staffCode').val());
					$('#j_password').val($('#StaffPassword').val());
					eDialog.alert(returnInfo.resultMsg,function(){
						$.eAjax({
			    			url : GLOBAL.WEBROOT+'/j_spring_security_check',
			    			data : ebcForm.formParams($("#loginform")),
			    			datatype:'json',
			    			success : function(returnInfo) {
			    			
			    				window.location.href = GLOBAL.WEBROOT + '/homepage';
			    			
			    			},
			    			exception:function(){
			    				eDialog.alert("登录异常",function(){
			    					$("#captchaCodeImg").attr("src","captcha/CapthcaImage?"+(new Date()).getTime());
			    				});
			    			}
			    			});
						
					});
				    }else{
				    	eDialog.alert(returnInfo.resultMsg,function(){
				    		$("#captchaCodeImg").attr("src","captcha/CapthcaImage?"+(new Date()).getTime());
				    	});
				}
			},
			exception:function(){
				$("#captchaCodeImg").attr("src","captcha/CapthcaImage?"+(new Date()).getTime());
		      }
		});
		
		
	});
	//校验用户名是否存在
	$('#staffCode').blur(function(){
		var staffCode = $('#staffCode').val();
		var strExp=/^[a-zA-Z0-9~!@#$%^&*()_-]{4,18}$/;
		//这个校验，必须与统一的校验脚本一致ecp-web-js包的e.validate.method.js
		if (strExp.test(staffCode)) {
			$.eAjax({
    			url : GLOBAL.WEBROOT+'/register/staffcode/check?staffCode=' + staffCode,
    			datatype:'json',
    			success : function(returnInfo) {
    				if (returnInfo.resultFlag != 'ok') {
    					$('#isExist').html("<font color='red'>用户名已经存在</font>");
    				} else {
    					$('#isExist').html("该用户名可以使用");
    				}
    			},
    		});
		}
	});
	
	$.smsDialogPlugin.show({
		PhoneNoId : "serialNumber",
		sendButtonId:"sms-win-getcode"
	});
	
	
});