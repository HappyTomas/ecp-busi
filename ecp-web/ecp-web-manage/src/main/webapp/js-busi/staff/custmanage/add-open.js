$(function(){
	//获得当前弹出窗口对象
	var _dlg = bDialog.getDialog();
	//获得父窗口传递给弹出窗口的参数集
	var _param = bDialog.getDialogParams(_dlg);
	//获得父窗口设置的回调函数
	var _callback = bDialog.getDialogCallback(_dlg);
	var url =  GLOBAL.WEBROOT + '/sensitive/saveSensitive';
	$('#btnFormSave').click(function(){
		
		if(!$("#detailInfoForm").valid())return false;
		var _val = ebcForm.formParams($("#detailInfoForm"));
		$.eAjax({
			url : url,
			data : _val,
			success : function(returnInfo) {
				if(returnInfo.resultFlag=='ok'){
					eDialog.success('转换成功！',{
						buttons:[{
							caption:"确定",
							callback:function(){
								bDialog.closeCurrent();
					        }
						}]
					}); 
				}else{
					eDialog.alert('转换失败');
				}
			
			}
		});
	});
	
	$('#btn_code_back').click(function(){
		bDialog.closeCurrent();
	});
	
});