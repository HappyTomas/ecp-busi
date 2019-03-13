$(function(){
	//获得当前弹出窗口对象
	var _dlg = bDialog.getDialog();
	//获得父窗口传递给弹出窗口的参数集
	var _param = bDialog.getDialogParams(_dlg);
	//获得父窗口设置的回调函数
	var _callback = bDialog.getDialogCallback(_dlg);
	var _shopId = _param.shopId;
	var _catgCode = _param.catgCode;
	
	$("#shopId").val(_shopId);
	$("#catgCode").val(_catgCode);
	
	// 绑定搜索按钮事件.
	$('#btnFormSearch').click(function(){
		if(!$("#searchForm").valid()) {
			return;
		}
		var p = ebcForm.formParams($('#searchForm'));
		$('#listDiv').load(GLOBAL.WEBROOT + '/seller/goods/gdsinfoentry/gridshiptemplist',
				p);
	});
	// 搜索表单重置按钮绑定重置事件.
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
		//$("#gdsId").val(_gdsId);
		$("#shopId").val(_shopId);
		$("#catgCode").val(_catgCode);
	});
	$('#btnFormSearch').trigger('click');
	
});

function chooseMe(obj,id,shipTemplateName){
	if(shipTemplateName=="null"){
		shipTemplateName = "";
	}
	bDialog.closeCurrent({
		'tempId' : id,
		'tempName' : shipTemplateName
	});
}
