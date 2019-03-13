$(function(){
	//验证码看不清再换一张
	$("#refleshCaptchaCode").click(function(){
		$("#captchaCodeImg").attr("src",$webroot+"captcha/CapthcaImage?"+(new Date()).getTime());
	});
	/*//更新操作
	$('#btnFormSave').click(function(){
		if(!$("#aptitudeinfo3").valid()) return false;
		//debugger;
		var param = ebcForm.formParams($("#aptitudeinfo3"));
		$.eAjax({
			url : GLOBAL.WEBROOT + "/information/updatecompany",
			data : param,	
			datatype:'json',
			success : function(returnInfo) {
				eDialog.success('保存成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							location.reload(false);
				        }
					}]
				}); 
			}
		});
	});*/
	//提交申请
	$('#btnSave1').click(function(){
		if(!$("#aptitudeinfo1").valid()) return false;
		//debugger;
		var param = ebcForm.formParams($("#aptitudeinfo1"));
		$.eAjax({
			url : GLOBAL.WEBROOT + "/information/registcomany",
			data : param,	
			datatype:'json',
			async : false,
			success : function(returnInfo) {
				if(returnInfo.resultFlag == "fail"){
					eDialog.alert(returnInfo.resultMsg);
				}else{
					eDialog.alert(returnInfo.resultMsg,function(){
						location.reload(false);
					});
				}
				
			}
		});
	});
	
});