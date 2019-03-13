
$(function () { 
	//保存
	$('#btnFormSave').click(function(){ 
		if(!$("#smsSetForm").valid())return false;
		$.eAjax({
			url : GLOBAL.WEBROOT + "/sms/save",
			data : ebcForm.formParams($("#smsSetForm")),
			success : function(data){
				if(data && data.flag=="1"){
				    eDialog.success('短信网关配置信息已经保存成功！');
				} else {
					eDialog.error(data.msg);
				}
			}
		});
	});
	
	//测试
	$('#btnTest').click(function(){ 
		//清空错误信息；
		$("#div-error").empty();
		$.eAjax({
			url : GLOBAL.WEBROOT + "/sms/test",
			data : ebcForm.formParams($("#smsSetForm")),
			success : function(data){
				if(data && data.flag=="1"){
				    eDialog.success('测试短信已发送，请查收！');
				} else {
					$("#div-error").html(data.msg);
				}
			}
		});
	});
});  