
$(function () { 
	//保存
	$('#btnFormSave').click(function(){ 
		if(!$("#mailsetForm").valid())return false;
		$.eAjax({
			url : GLOBAL.WEBROOT + "/msg/mgr/mailset/save",
			data : ebcForm.formParams($("#mailsetForm")),
			success : function(){
				eDialog.success('操作成功！'); 
			}
		});
	});
	
	//测试
	$('#btnTest').click(function(){ 
		if(!$("#mailsetForm").valid())return false;
		$.eAjax({
			url : GLOBAL.WEBROOT + "/msg/mgr/send/test",
			data : ebcForm.formParams($("#mailsetForm")),
			success : function(info){
				eDialog.success("验证成功与否，请查看测试接收邮件的地址"); 
			}
		});
	});
});  