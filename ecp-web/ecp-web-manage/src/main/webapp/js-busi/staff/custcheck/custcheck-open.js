$(function(){
	//获得当前弹出窗口对象
	var _dlg = bDialog.getDialog();
	//获得父窗口传递给弹出窗口的参数集
	var _param = bDialog.getDialogParams(_dlg);
	//获得父窗口设置的回调函数
	var _callback = bDialog.getDialogCallback(_dlg);
	
	
	$('#btn_code_checkremark').click(function(){
			var _remark = $('#remark').val();
			var _val = _param.val;
			$.eAjax({
				url : GLOBAL.WEBROOT + "/custcheck/oncheck",
				data : {'id':_val[0].id,'staffId':_val[0].staffId,'remark':_remark},
				datatype:'json',
				success : function(returnInfo) {
					eDialog.success('审核不通过成功！'); 
					bDialog.closeCurrent();
				}
			});
	
	});
});