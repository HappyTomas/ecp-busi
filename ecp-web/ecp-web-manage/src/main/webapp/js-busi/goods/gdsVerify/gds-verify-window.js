$(function(){
	// 获得当前弹出窗口对象
	var _dlg = bDialog.getDialog();
	// 获得父窗口传递给弹出窗口的参数集
	var _param = bDialog.getDialogParams(_dlg);
	// 获得父窗口设置的回调函数
	var _callback = bDialog.getDialogCallback(_dlg);
	var _shopId = _param.shopId;//店铺编码id
	var _operateId = _param.operateId;//批量操作的商品编码id
	$('#btnReturn').click(function() {
		bDialog.closeCurrent();
	});
	$(".gdsVerify").live('click',function(e){
		if (!$("#gdsVerify").valid()) {
			return;
		}
		var type = $(this).attr('value');
		var params = {};
		if(type == '01'){
			//审核通过
			params.verifyStatus = '01';
		}else if(type == '02'){
			//审核拒绝
			params.verifyStatus = '02';
		}
		params.shopId = _shopId;
		params.verifyOption = $("#verifyOption").val();
		params.operateId = _operateId;
		$.gridLoading({
			"messsage" : "正在操作中...."
		});
		$.eAjax({
			url : GLOBAL.WEBROOT + "/gdsverify/batchverifygds",
			data : params,
			success : function(returnInfo) {
				$.gridUnLoading();
				if(returnInfo.resultFlag=='ok'){
					eDialog.success(returnInfo.resultMsg, {
						buttons : [{
							caption : "确定",
							callback : function() {
								bDialog.closeCurrent({'param':_shopId});
							}
						}]
					});
				}else{
					eDialog.error(returnInfo.resultMsg);
				}
			},
			exception : function() {
				$.gridUnLoading();
			}
		});
		e.preventDefault();
	});
});