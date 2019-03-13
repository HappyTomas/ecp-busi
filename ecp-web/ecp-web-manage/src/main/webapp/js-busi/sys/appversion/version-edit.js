$(function() {
	var url = '/appversion/saveEditVersion';

	$('#btnFormSave').unbind("click").click(function() {
		if (!$("#verEditForm").valid())
			return false;
		var _verEdit = new Object();
		_verEdit.id = $("#versionId").val();
		_verEdit.verProgram = $("select[name=verProgram]").val();
		_verEdit.verOs = $("select[name=verOs]").val();
		_verEdit.verAdaptOs = $("input[name=verAdaptOs]").val();
		_verEdit.verUrl = $("input[name=verUrl]").val();
		_verEdit.verPublishNo = $("input[name=verPublishNo]").val();
		_verEdit.verNo = $("input[name=verNo]").val();
		_verEdit.verDetail = $("textArea[name=verDetail]").val();
		_verEdit.ifForce = $("select[name=ifForce]").val();


		$.eAjax({
					url : GLOBAL.WEBROOT + url,
					data : _verEdit,
					success : function(returnInfo) {
						if (returnInfo && returnInfo.resultFlag == "ok") {
							eDialog.success('版本编辑成功！', {
										buttons : [{
											caption : "确定",
											callback : function(returnInfo) {

												window.location.href = GLOBAL.WEBROOT
														+ '/appversion/pageInit';

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
	$('#btnReturn').live('click', function() {

				window.location.href = GLOBAL.WEBROOT + '/appversion/pageInit';
			});
});
