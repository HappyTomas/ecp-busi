$(function() {

	var url = '/appversion/saveAddVersion';
	$('#btnReturn').live('click', function() {

				window.location.href = GLOBAL.WEBROOT + '/appversion/pageInit';
			});
	$('#btnFormSave').unbind("click").click(function() {
		if (!$("#verAddForm").valid())
			return false;
		var _verAdd = new Object();
		_verAdd.verProgram = $("select[name=verProgram]").val();
		_verAdd.verOs = $("select[name=verOs]").val();
		_verAdd.verAdaptOs = $("input[name=verAdaptOs]").val();
		_verAdd.verUrl = $("input[name=verUrl]").val();
		_verAdd.verPublishNo = $("input[name=verPublishNo]").val();
		_verAdd.verNo = $("input[name=verNo]").val();
		_verAdd.verDetail = $("textArea[name=verDetail]").val();
		_verAdd.ifForce = $("select[name=ifForce]").val();
		
		$.eAjax({
					url : GLOBAL.WEBROOT + url,
					data : _verAdd,
					success : function(returnInfo) {
						if (returnInfo && returnInfo.resultFlag == "ok") {
							eDialog.success('版本新增成功！', {
										buttons : [{
											caption : "确定",
											callback : function(returnInfo) {
												window.location.href = GLOBAL.WEBROOT
												+ '/appversion/pageInit';
												/*if (returnInfo.resultFlag == "ok") {
													window.location.href = $webroot
															+ '/appversion/pageInit';
												} else {
													eDialog
															.error('属性批量添加遇到异常！异常信息:'
																	+ returnInfo.resultMsg);

												}*/
											}
										}]
									});
						} else if (returnInfo
								&& returnInfo.resultFlag == "fail") {
							eDialog.error('属性批量添加遇到异常！异常信息:'
									+ returnInfo.resultMsg);
						} else {
							eDialog.error('属性批量添加遇到异常！');
						}
					},
					exception : function() {
//						$.gridUnLoading();

					}
				});
	});

});
