
$(function(){
	$('#addrnew').click(function(){
		window.location.href = GLOBAL.WEBROOT+'/custaddr/newaddr';
	});

	//企业入驻信息填写完后提交，进入下一步
	$('#saveaddr').click(function(){
		if(!$("#addrdetailfrom").valid()) return false;
		var val = ebcForm.formParams($("#addrdetailfrom"));
		$.eAjax({
			url : GLOBAL.WEBROOT + "/custaddr/saveaddr",
			data : val,
			datatype:'json',
			success : function(returnInfo) {
				window.location.href = GLOBAL.WEBROOT+'/custaddr/index';
			}
		});
	});
	$('#cancle').click(function(){
		history.back();
	});
});	

var buyeredit = {
		
		setaddr:function(addrid,staffid){
			$.eAjax({
				url : GLOBAL.WEBROOT + "/custaddr/setaddr",
				data : {'addrid':addrid,'staffid':staffid},
				datatype:'json',
				success : function(returnInfo) {
					window.location.href = GLOBAL.WEBROOT+'/custaddr/index';
				}
			});
		},
		deladdr:function(addrid,staffid){
			$.eAjax({
				url : GLOBAL.WEBROOT + "/custaddr/deladdr",
				data : {'addrid':addrid,'staffid':staffid},
				datatype:'json',
				success : function(returnInfo) {
					window.location.href = GLOBAL.WEBROOT+'/custaddr/index';
				}
			});
		},
		editaddr:function(addrid,staffid){
			$('#addrid').val(addrid);
			$('#staffid').val(staffid);
			$('#editAddrFrom').submit();
		}
};