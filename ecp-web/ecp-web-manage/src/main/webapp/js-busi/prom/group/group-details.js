$(function(){
	var url = '/promgroup/save';
	$('#btnFormSave').click(function(){ 
		if(!$("#detailInfoForm").valid())return false;
		
		$.eAjax({
			url : GLOBAL.WEBROOT + "/promgroup/save",
//			data : ebcForm.formParams($("#detailInfoForm")),
			data : [{name:"id",value:$("#id").val()}],
			success : function(returnInfo) {
				eDialog.success('信息保存成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							window.location.href = $webroot + '/promgroup';
				        }
					}]
				}); 
			}
		});
	});
	
	$('#btnShowError').click(function(){
		$('div.formValidateMessages').slideToggle('fast');
	});
});