$(function(){
	$('#btnFormSave').click(function(){
		if(!$("#detailInfoForm").valid()) return false;
		//debugger;
		var param = ebcForm.formParams($("#detailInfoForm"));
		$.eAjax({
			url : GLOBAL.WEBROOT + "/seller/shopmgr/shopset/save",
			data : param,	
			datatype:'json',
			success : function(returnInfo) {
				eDialog.success('保存成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							window.location.href = GLOBAL.WEBROOT+'/seller/shopmgr/shopset';
				        }
					}]
				}); 
			}
		});
					
	});
	
	$('#shopId').change(function(){
		$('#shopIds').val($('#shopId').val());
		$('#shopIdForm').submit();
		
	});
	
	
	
	$('#shoplogoUpload').click(function(evt){
		busSelector.uploader({
			'fileSizeLimit': '4MB',
			checktype : 'single',
			callback : function(data){
				if(data && data.results && data.results.length > 0){
					$('#logoMongodbKey').val(data.results[0].fileId);
					$('#imgPreview1').attr('src',data.results[0].url);
				}
			}
		}, evt);
	});
	
});
