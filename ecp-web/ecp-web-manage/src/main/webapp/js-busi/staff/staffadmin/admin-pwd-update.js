$(function(){
	var url =  GLOBAL.WEBROOT + '/staffadmin/pwdupdate';

	$('#btnFormSave').click(function(){ 
		if(!$("#detailInfoForm").valid())return false;
		
		$.eAjax({
			url : url,
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				if(returnInfo.resultMsg){
					eDialog.alert(returnInfo.resultMsg);
					//跳转回工作台首页
					window.location = GLOBAL.WEBROOT;
				}
			}
		});
		
	});
	$('#btnReturn').click(function(){
		history.back();
	});
	
});