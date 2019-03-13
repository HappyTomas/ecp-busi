$(function() {
	// 删除积分商城店铺的选项
	//$('#shopId').find('option[value=101]').remove();
	// 保存
	$('#btnFormSave').click(function() {
		if (!$("#detailInfoForm").valid())
			return false;

		$.eAjax({
			url : GLOBAL.WEBROOT + "/gdsdiscount/editsave",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				if (returnInfo.resultFlag == 'ok') {
					eDialog.success('保存成功！', {
						onClose : function() {

							bDialog.closeCurrent();
						}
					});
				} else {
					eDialog.alert(returnInfo.resultMsg);
				}
			}
		});
	});

	// 返回
	$('#btnFormCancle').click(function() {
		bDialog.closeCurrent();
	});

});
