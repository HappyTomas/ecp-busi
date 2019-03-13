$(function(){
	$('#btnFormUpdate').click(function(){ 
		 if(!$("#detailInfoForm").valid())return false;
		$('#btnFormUpdate').attr("disabled","true");
		$.eAjax({
			url : GLOBAL.WEBROOT + "/msgauth/update",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				$('#btnFormUpdate').removeAttr("disabled");
				//成功返回grid
				if(returnInfo.resultFlag=='ok'){
					window.location.href = GLOBAL.WEBROOT+'/msgauth/'+$('#shopAuthId').val();
				//数据验证错误	
				}else if(returnInfo.resultFlag=='expt'){
					eDialog.alert(returnInfo.resultMsg,null);
				}
			},
			exception : function(returnInfo){
				$('#btnFormUpdate').removeAttr("disabled");
			}
		});
	});
	
	$('#btnFormSave').click(function(){ 
		 if(!$("#detailInfoForm").valid())return false;
		$('#btnFormSave').attr("disabled","true");
		$.eAjax({
			url : GLOBAL.WEBROOT + "/msgauth/save",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				$('#btnFormSave').removeAttr("disabled");
				//成功返回grid
				if(returnInfo.resultFlag=='ok'){
					window.location.href = GLOBAL.WEBROOT+'/msgauth/'+$('#shopAuthId').val();
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
		window.location.href = GLOBAL.WEBROOT+'/msgauth/'+$('#shopAuthId').val();
	});
});