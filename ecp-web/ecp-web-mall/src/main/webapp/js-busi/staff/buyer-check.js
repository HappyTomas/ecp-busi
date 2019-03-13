$(function(){
	//提交邮箱
	$('#btnSend').click(function(){
		if(!$("#mailForm").valid()) return false;
		
        var val = ebcForm.formParams($("#mailForm"));
        
        $("#mailForm").submit();
	});
	
	//手机验证确认
	$('#savePhoneBtn').click(function(){
		//表单校验
		if(!$("#phoneForm").valid()) return false;
		
		if ($('#checkCode').val() == '') {
			$('#sendCon').html('请输入验证码。');
			return ;
		}
		//表单参数
        var val = ebcForm.formParams($("#phoneForm"));
        
        $.eAjax({
			url : $webroot + 'buyer/check/checkphone',
			data : val,
			datatype:'json',
			success : function(info) {
				if (info.resultFlag == 'ok') {
					//发送验证码按钮处理
					settime($('#checkCodeBtn'));
					//跳转到个人中心
					eDialog.alert("手机验证成功。",function(){
						window.location.href = $webroot + "buyercenter/index";
					}); 
				} else {
					$('#sendCon').html('手机验证失败。');
					eDialog.alert('手机验证失败。');
				}
			}
		});
	});
	
	
	//重新发送验证码倒计时
	var dealTime = 60; 
	var task;
	var settime = function(btn) {
		if (dealTime == 0) { 
			btn.attr("disabled",false);    
			btn.val("获取验证码"); 
			dealTime = 60; 
			clearTimeout(task);//停止
			return;
		} else { 
			btn.attr("disabled", true); 
			btn.val("获取验证码(" + dealTime + ")S"); 
			dealTime--; 
		} 
		task = setTimeout(function() { 
			settime(btn);
		},1000);
	};
	
	//发送验证码
	$('#checkCodeBtn').click(function(){
		//校验手机必填，且是否合法
		if(!$("#phoneForm").valid()) return false;
		//表单参数
        var val = ebcForm.formParams($("#phoneForm"));
		//获取后台验证码
		$.eAjax({
			url : $webroot + 'buyer/check/checkcode',
			data : val,
			datatype:'json',
			success : function(info) {
				if (info.resultFlag == 'ok') {
					$('#sendCon').html('验证码已发送至手机，请查收。有效期5分钟。');
					//发送验证码按钮处理
					settime($('#checkCodeBtn'));
				} else {
					if (info.resultMsg == '') {
						$('#sendCon').html('验证码发送失败。');
					} else {
						$('#sendCon').html(info.resultMsg);
					}
					
				}
			}
		});
		
	});
});