$(function() {
	// 删除积分商城店铺的选项
	//$('#shopId').find('option[value=101]').remove();

	// 平台分类回填
	$('#platCatgSelector').catgDropDown({
		backfillCatgName : 'platCatgSelector',
		backfillCatgCode : 'CatgCode',
		width : '170px', // 默认宽度。
		height : '150px'// 默认高度。
	});
	// 初始化平台分类下拉
	$('#platCatgSelector').catgDropDown.change(1);

	// 保存
	$('#btnFormSave').click(function() {
		if (!$("#detailInfoForm").valid())
			return false;

		$.eAjax({
			url : GLOBAL.WEBROOT + "/gdsdiscount/save",
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

var GdsDiscount = {

	chooseCatg : function() {
		bDialog.open({
			title : '分类选择',
			width : 350,
			height : 550,
			url : GLOBAL.WEBROOT
					+ "/goods/category/open/catgselect?catgType=1&catlogId=1",
			callback : function(data) {
				if (data && data.results && data.results.length > 0) {
					var _catgs = data.results[0].catgs;
					for ( var i in _catgs) {

						$("#catgName").val(_catgs[i].catgName);
						$("#catgCode").val(_catgs[i].catgCode);
					}
				}
			}
		});
	}

};