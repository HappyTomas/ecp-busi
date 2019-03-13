
$(function(){
	$.validator.addMethod("postCode",function(value, element){
		var decimal = /^\d?$/;
		return (value == "" || !decimal.test(value));
	},"邮编格式不正确");
	window.errorInfo = "";
	$.validator.addMethod("defineRequired",function(value, element){
		errorInfo = $(element).attr('requiredInfo');
		return value!=""||$.trim(value)!="";
	},function(value, element){
		return $(element).attr('requiredInfo');
	});
	//保存收货地址
	$('#saveaddr').click(function(){
		if(!$("#addrdetailfrom").valid()) return false;
		var val = ebcForm.formParams($("#addrdetailfrom"));
		$.eAjax({
			url : GLOBAL.WEBROOT + "/custaddr/saveaddr",
			data : val,
			datatype:'json',
			success : function(returnInfo) {
				if(returnInfo.resultFlag=="ok"){
					window.location.href = GLOBAL.WEBROOT+'/custaddr/index';
				}else{
					eDialog.alert(returnInfo.resultMsg);
					//new AmLoad({content:returnInfo.resultMsg,type:'2'});
				}
				
			}
		});
	});
	$('#cancle').click(function(){
		history.back();
	});
});	

var buyeredit = {
		editaddr:function(addrid,staffid){
			$('#addrid').val(addrid);
			$('#staffid').val(staffid);
			$('#editAddrFrom').submit();
		}
};