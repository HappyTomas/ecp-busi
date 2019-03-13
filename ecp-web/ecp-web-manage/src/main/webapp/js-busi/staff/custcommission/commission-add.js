$(function() {
	// 删除积分商城店铺的选项
	//$('#shopId').find('option[value=101]').remove();

	// 平台分类回填
	$('#CatgName').catgDropDown({
		backfillCatgName : 'CatgName',
		backfillCatgCode : 'CatgCode',
		width : '170px', // 默认宽度。
		height : '150px'// 默认高度。
	});
	// 初始化平台分类下拉
	$('#CatgName').catgDropDown.change(1);

	// 保存
	$('#btnFormSave').click(function() {
		if (!$("#detailInfoForm").valid())
			return false;

//		eDialog.confirm("您确认删除该记录吗？", {
//			buttons : [ {
//				caption : '确认',
//				callback : function() {
//					$.eAjax({
//						url : GLOBAL.WEBROOT + "/custcommission/save",
//						data : ebcForm.formParams($("#detailInfoForm")),
//						success : function(returnInfo) {
//							if (returnInfo.resultFlag == 'ok') {
//								eDialog.success('保存成功！', {
//									onClose : function() {
//
//										bDialog.closeCurrent();
//									}
//								});
//							} else {
//								eDialog.alert(returnInfo.resultMsg);
//							}
//						}
//					});
//				}
//			}, {
//				caption : '取消',
//				callback : $.noop
//			} ]
//		});
		
		$.eAjax({
			url : GLOBAL.WEBROOT + "/custcommission/ifExit",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				if (returnInfo.resultFlag != '1' & returnInfo.resultFlag != '0') {
					eDialog.confirm("该分类已存在提成比例值为【"+returnInfo.resultFlag+"】，您确认覆盖该记录吗？", {
						buttons : [ {
							caption : '确认',
							callback : function() {
								$.eAjax({
									url : GLOBAL.WEBROOT + "/custcommission/save",
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
							}
						}, {
							caption : '取消',
							//callback : $.noop
							callback : function() {
								$.noop;
								bDialog.closeCurrent();
							}

						} ]
					});

				} else {
					$.eAjax({
						url : GLOBAL.WEBROOT + "/custcommission/save",
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