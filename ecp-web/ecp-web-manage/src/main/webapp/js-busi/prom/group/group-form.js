$(function(){
	var type = $("input[name=flag]").val();
	if(type=='detail'){
		$('#btnFormSave').hide();
		$('#btnFormSave2').hide();
	}
	if(type !='2'){
		$('#btnFormSave').hide();
	}
	

	$('#btnFormSave').click(function(){ 
		var sid = $("input[name=id]").val();
		if(!$("#detailInfoForm").valid())return false;
		$("#savetype").val("1");
		//eDialog.success('字典保存成功！'); 
		//checkUrl();
		$.eAjax({
			url : GLOBAL.WEBROOT + "/promgroup/save",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				eDialog.success('草稿保存成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							window.location.href = $webroot + 'promgroup';
				        }
					}]
				}); 
			}
		});
	});
	
	$('#btnFormSave2').click(function(){ 
		var sid = $("input[name=id]").val();
		if(!$("#detailInfoForm").valid())return false;
		//eDialog.success('字典保存成功！'); 
		//checkUrl();
		$.eAjax({
			url : GLOBAL.WEBROOT + "/promgroup/save",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				eDialog.success('信息提交成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							window.location.href = $webroot + 'promgroup';
				        }
					}]
				}); 
			}
		});
	});
	
	$('#btnReturn').click(function(){
		window.location.href = GLOBAL.WEBROOT+'/promgroup';
	});
	
});