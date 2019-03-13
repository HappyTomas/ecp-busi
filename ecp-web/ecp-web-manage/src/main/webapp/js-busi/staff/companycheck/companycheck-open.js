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
			var id =_param.Id;
			$.eAjax({
				url : GLOBAL.WEBROOT + "/companycheck/oncheck",
				data : {'companyId':_val,'remark':_remark,'id':id},
				datatype:'json',
				success : function(returnInfo) {
					eDialog.success('审核不通过成功！',{buttons:[{
						caption:"确定",
						callback:function(){
							bDialog.closeCurrent({'ifsubmit': true});
				        }
					}]
					}); 
					
				}
			});
	
	});
	
	$('#btn_code_back').click(function(){
		
		bDialog.closeCurrent({'ifsubmit': false});
	})
});