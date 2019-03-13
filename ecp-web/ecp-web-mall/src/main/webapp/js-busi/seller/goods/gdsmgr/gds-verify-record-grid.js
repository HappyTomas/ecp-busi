$(function(){
	//获得当前弹出窗口对象
	var _dlg = bDialog.getDialog();
	//获得父窗口传递给弹出窗口的参数集
	var _param = bDialog.getDialogParams(_dlg);
	//获得父窗口设置的回调函数
	//var _callback = bDialog.getDialogCallback(_dlg);
	var _gdsId = _param.gdsId;
	var _shopId = _param.shopId;
	
	$("#gdsId").val(_gdsId);
	$("#shopId").val(_shopId);
	
	var p = ebcForm.formParams($('#searchForm'));
	
	$('#listDiv').empty();
	
	$('#listDiv').load(GLOBAL.WEBROOT + '/seller/goods/gdsmanage/verifylist',
			p);
	
	$('#btnReturn').click(function(){
		bDialog.closeCurrent();
	});
	
});
