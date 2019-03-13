$(function(){
	$('#btnFormSave').click(function(){ 
		$('#btnFormSave').attr("disabled","true");
		$.eAjax({
			url : GLOBAL.WEBROOT + "/promauth/update",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				$('#btnFormSave').removeAttr("disabled");
				if(returnInfo.resultFlag=='ok'){
					window.location.href = GLOBAL.WEBROOT+'/promauth';
				}else{
					eDialog.alert(returnInfo.resultMsg,null);
				}
			}
		});
	});
	//返回
	$('#btnReturn').click(function(){
		window.location.href = GLOBAL.WEBROOT+'/promauth';
	});
	//错误信息
	$('#btnShowError').click(function(){
		$('div.formValidateMessages').slideToggle('fast');
	});
});