$(function(){
	$('#btnReturn').unbind("click").click(function() {
		window.location.href = GLOBAL.WEBROOT + '/order/pay/paywaygrid';
	});
	
	$('#myTab a').click(function (e) {
		e.preventDefault();
		$(this).tab('show');
    });
	
	$('#btnFormSave').click(function(){
		if (!$("#paywayForm").valid())
			return false;
		$.gridLoading({
			"message" : "正在保存数据中...."
		});
		var url = "/order/pay/paywayAdd"
		$.eAjax({
			url : GLOBAL.WEBROOT + url,
			data : ebcForm.formParams($("#paywayForm")),
			success : function(returnInfo) {
				eDialog.success('保存成功！', {
							buttons : [{
								caption : "确定",
								callback : function() {
									window.location.href = $webroot
											+ '/order/pay/paywaygrid';
								}
							}]
						});

			},
			exception : function() {
				$.gridUnLoading();
			}
		});
	});
})
