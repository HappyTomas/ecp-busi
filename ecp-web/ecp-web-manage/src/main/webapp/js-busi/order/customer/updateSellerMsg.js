$(function(){
	$('#savemsg').click(function(){
		if(!$("#detailfrom").valid()) return false;
		var val = ebcForm.formParams($("#detailfrom"));				
		$('#savemsg').attr("disabled",false);
		$.eAjax({
			url : GLOBAL.WEBROOT + "/order/customer/updateSellerMsg",
			data : val,
			datatype:'json',
			success : function(result) {
				bDialog.closeCurrent(result);
			}
		});
	});
	$('#btnCancel1').click(function(){
		var result = {}
		result.result='close';
		bDialog.closeCurrent(result);
	});
});