$(function(){
	var type = $("input[name=flag]").val();
	
	if(type=='detail'){
		$('#btnFormSave').hide();
		$('#btnFormSave3').hide();
	}
	
	var url = '/promgroup/save';

	$('#btnFormSave').click(function(){ 
		if(!$("#detailInfoForm").valid())return false;
		$("#savetype").val("1");
//		$("#detailInfoForm").submit();
		$.eAjax({
			url : GLOBAL.WEBROOT + "/promgroup/save",
			data :ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				if(returnInfo.resultFlag=='ok'){
					eDialog.success('草稿保存成功！',{
						buttons:[{
							caption:"确定",
							callback:function(){
								window.location.href = $webroot + 'promgroup';
					        }
						}]
					}); 
				}else{
					eDialog.alert(returnInfo.resultMsg,null);
				}
			}
		});
		
	});
	
	$("#btnFormSave2").on('click',function(){
		$.eAjax({
			url : GLOBAL.WEBROOT + "/promgroup/save",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				if(returnInfo.resultFlag=='ok'){
					eDialog.success('信息提交成功！'); 
					window.location.href = $webroot + 'promgroup';
				}else{
					eDialog.alert(returnInfo.resultMsg,null);
				}	
				//刷新缓存
				/*$.eAjax({
					url : $webroot + 'code/refreshDictCache',
					success : function(returnInfo) { 
						eDialog.success('字典保存成功！'); 
						window.location.href = $webroot + 'jsp/manage/system/code/codeMain.jsp';
					}
				});*/
			}
		});
	});
	
	$('#btnReturn').click(function(){
		window.location.href = GLOBAL.WEBROOT+'/promgroup';
	});
	$('#btnShowError').click(function(){
		$('div.formValidateMessages').slideToggle('fast');
	});
	
});