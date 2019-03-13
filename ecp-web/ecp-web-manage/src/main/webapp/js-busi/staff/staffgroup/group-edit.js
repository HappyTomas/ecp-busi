$(function(){
	var url = '/staffgroup/editgroup';
	/*var checkUrl = function(){
		if($('#operation').val()=='3') url = 'action/dml?op=code:3';
	};*/
	$('#btnFormSave').click(function(){ 
		if(!$("#detailInfoForm").valid())return false;
		//eDialog.success('字典保存成功！'); 
		
		//checkUrl();
		$.eAjax({
			url : GLOBAL.WEBROOT + url,
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				eDialog.success('分组保存成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							window.location.href = $webroot + '/staffgroup/grid';
				        }
					}]
				}); 
			}
		});
		
	});
	
	
	
	$('#btnReturn').click(function(){
		window.location.href = GLOBAL.WEBROOT+'/staffgroup/grid';
	});
	$('#btnShowError').click(function(){
		$('div.formValidateMessages').slideToggle('fast');
	});
	
	
});