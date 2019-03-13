$(function(){
	//获得当前弹出窗口对象
	var _dlg = bDialog.getDialog();
	//获得父窗口传递给弹出窗口的参数集
	var _param = bDialog.getDialogParams(_dlg);
	//获得父窗口设置的回调函数
	var _callback = bDialog.getDialogCallback(_dlg);
	var _gdsId = _param.gdsId;
	var _shopId = _param.shopId;
	var _catgCode = _param.catgCode;
	debugger;
	$("#gdsId").val(_gdsId);
	$("#shopId").val(_shopId);
	$("#catgCode").val(_catgCode);
	
	// 绑定搜索按钮事件.
	$('#btnFormSearch').click(function(){
		if(!$("#searchForm").valid()) {
			return;
		}
		var p = ebcForm.formParams($('#searchForm'));
		$('#listDiv').empty();
		$('#listDiv').load(GLOBAL.WEBROOT + '/seller/goods/gdsmanage/shiptemplist',
				p);
	});
	// 搜索表单重置按钮绑定重置事件.
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
		$("#gdsId").val(_gdsId);
		$("#shopId").val(_shopId);
	});
	// 返回按钮绑定点击事件.
	$('#btnReturn').click(function(){
		bDialog.closeCurrent();
	});
	// 保存按钮事件绑定
	$("#saveGdsInfo").click(function(){
		SetInfo.saveSetInfo();
	});
	
	$('#btnFormSearch').trigger('click');
	
});

var SetInfo = {
		saveSetInfo : function(){
			var _flag = "";
			var radio = $('input[type=radio][name=shiptempId]:checked');
			if(!radio || radio.length==0){
				eDialog.alert('请选择一条运费模板进行操作！');
				return ;
			}
			var param = {};
			param.shipTemplateId = radio.val();
			param.gdsId = $("#gdsId").val();
			param.shopId = $("#shopId").val();
			$.eAjax({
				url : GLOBAL.WEBROOT + "/seller/goods/gdsmanage/setshiptemp",
				data : param,
				success : function(returnInfo) {
					if(returnInfo.resultFlag=='ok'){
						_flag = "ok";
					}else{
						_flag = "fail";
					}
					bDialog.closeCurrent({
						'flag' : _flag
					});
				}
			});
		}
};

