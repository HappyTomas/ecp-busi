$(function(){
	var url =  GLOBAL.WEBROOT + '/cust/savecust';

	$('#btnFormSave').click(function(){ 
		if(!$("#detailInfoForm").valid())return false;
		var _val = ebcForm.formParams($("#detailInfoForm"));
		$.eAjax({
			url : url,
			data : _val,
			success : function(returnInfo) {
				eDialog.success('用户保存成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							window.location.href = 'grid';
				        }
					}]
				}); 
				//刷新缓存
			}
		});
		
	});
	$('#btnReturn').click(function(){
		history.back();
	});
	
	$.smsDialogPlugin.show({
		url:GLOBAL.WEBROOT+'/company/getshoplist',
		parentId : "companyId",
		sonId:"shopId",
	});
	
	
	
	
});