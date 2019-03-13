$(function(){
		$('#btnReturn').click(function(){
			var path = GLOBAL.WEBROOT+'/parameter';
			$("#btnForm").attr("action",path);
			$("#btnForm").submit();
			//window.location.href =GLOBAL.WEBROOT+'/parameter';
		});
	});
//多行文本输入框剩余字数计算  
function checkMaxInput(obj, maxLen) {
	if (obj == null || obj == undefined || obj == "") {
		return;
	}
	if (maxLen == null || maxLen == undefined || maxLen == "") {
		maxLen = 256;
	}
	var strResult;
	var $obj = $(obj);
	var newid = $obj.attr("id") + 'msg';

	if (obj.value.length > maxLen) { //如果输入的字数超过了限制  
		obj.value = obj.value.substring(0, maxLen); //就去掉多余的字  
		strResult = '<span id="' + newid + '" class=\'Max_msg\' >剩(<strong style="color:red">'
				+ (maxLen - obj.value.length) + '</strong>)字</span>'; //计算并显示剩余字数  
	} else {
		strResult = '<span id="' + newid + '" class=\'Max_msg\' >剩(<strong style="color:red">'
				+ (maxLen - obj.value.length) + '</strong>)字</span>'; //计算并显示剩余字数  
	}

	var $msg = $("#" + newid);
	if ($msg.length == 0) {
		$("#span").after(strResult);
	} else {
		$msg.html(strResult);
	}
}

//清空剩除字数提醒信息  
function resetMaxmsg() {
	$("span.Max_msg").remove();
}