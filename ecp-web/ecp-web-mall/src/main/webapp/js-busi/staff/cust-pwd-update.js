$(function(){
	var url =  GLOBAL.WEBROOT + '/information/pwdupdate';

	$('#btnFormSave').click(function(){ 
		if(!$("#detailInfoForm").valid())return false;
		
		$.eAjax({
			url : url,
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				if(returnInfo.resultMsg){
					eDialog.alert(returnInfo.resultMsg);
				}
			}
		});
		
	});
});