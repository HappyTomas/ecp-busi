$(function(){
	// 获得当前弹出窗口对象
	var _dlg = bDialog.getDialog();
	// 获得父窗口传递给弹出窗口的参数集
	var _param = bDialog.getDialogParams(_dlg);
	// 获得父窗口设置的回调函数
	var _callback = bDialog.getDialogCallback(_dlg);
	var _id = _param.id;//店铺编码id
	$('#btnReturn').click(function() {
		bDialog.closeCurrent();
	});
	$("#btnConfirm").live('click',function(e){
		var clear = '0';
		$("input[type='radio']").each(function(){
			var $this = $(this);
			if($this.attr("checked")=="checked"){
				clear = $this.attr('id');
			}
		});
		$.gridLoading({
			"messsage" : "正在操作中...."
		});
		var params = {
				id : _id,
				clear : clear
				};
		$.eAjax({
			url : GLOBAL.WEBROOT + "/solrmanage/restartindex",
			data : params,
			success : function(returnInfo) {
				$.gridUnLoading();
				if(returnInfo.resultFlag=='ok'){
					eDialog.success(returnInfo.resultMsg, {
						buttons : [{
							caption : "确定",
							callback : function() {
								bDialog.closeCurrent({'param':'ok'});
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
	});
});