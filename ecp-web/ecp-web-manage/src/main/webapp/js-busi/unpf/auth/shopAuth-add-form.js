$(function(){

	$('#btnFormSave').click(function(){
		//保存提交
		if(!$("#detailInfoForm").valid())return false;
		$(this).attr("disabled","true");
		//ajax请求
		$.eAjax({
			url : GLOBAL.WEBROOT + "/platauth/save",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				$('#btnFormSave').removeAttr("disabled");
				//成功返回grid
				if(returnInfo.resultFlag=='ok'){
					window.location.href = GLOBAL.WEBROOT+'/platauth';
				//数据验证错误	
				}else if(returnInfo.resultFlag=='expt'){
					eDialog.alert(returnInfo.resultMsg,null);
				}
			},
			exception : function(returnInfo){
				$('#btnFormSave').removeAttr("disabled");
			}
		});
	
	});
	//返回
	$('#btnReturn').click(function(){
		window.location.href = GLOBAL.WEBROOT+'/platauth';
	});
	//错误信息
	$('#btnShowError').click(function(){
		$('div.formValidateMessages').slideToggle('fast');
	});
});