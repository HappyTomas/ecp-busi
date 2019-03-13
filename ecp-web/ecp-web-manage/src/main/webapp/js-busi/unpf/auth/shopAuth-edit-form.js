$(function(){
	$('#btnFormSave').click(function(){ 
		$('#btnFormSave').attr("disabled","true");
		$.eAjax({
			url : GLOBAL.WEBROOT + "/platauth/update",
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
				$('#btnFormAuthSave').removeAttr("disabled");
			}
		});
	});
	//返回
	$('#btnReturn').click(function(){
		window.location.href = GLOBAL.WEBROOT+'/platauth';
	});
});