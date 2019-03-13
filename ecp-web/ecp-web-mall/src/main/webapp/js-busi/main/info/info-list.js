$(function(){
    $.eAjax({
		url : GLOBAL.WEBROOT + "/info/qryinfolist?pageSize=20",
		data : {
			placeId : $("#placeId").val()
		},
		dataType : "html",
		success : function(returnInfo) {
			//$.gridLoading({"messsage":"正在加载数据...."}); 
			$("#infoList").empty();
			$("#infoList").html(returnInfo);
			//$.gridUnLoading();
		}
	});
});