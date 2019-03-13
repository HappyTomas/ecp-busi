$(function(){
});
function sendMes(staffName,replyId){
	var touser = "回复"+staffName+":";
	$("#content").val(touser);
	$("#touser").val(touser);
	$("#replyid").val(replyId);
}
$("#sendMes").click (function(){
	$.eAjax({
		url : GLOBAL.WEBROOT + "/gdsdetail/addEvalRep",
		data : {
			"evalId" : $("#evalId").val(),
			"gdsId" : $("#gdsId").val(),
			"replyId" : $("#replyid").val(),
			"content" : $("#touser").val()+$("#content").val()
		},
		async : true,
		type : "post",
		dataType : "html",
		success : function(returnInfo) {
			location.reload();
		}
	});
});