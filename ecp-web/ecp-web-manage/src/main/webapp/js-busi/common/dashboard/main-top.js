$(function() {
	/*$.eAjax({
		url : GLOBAL.WEBROOT + "/reportitem/managelogo",
		success : function(returnInfo) {
			$("#logoPath").attr("src",returnInfo.imageUrl);
		},
		error : function() {
			eDialog.error('浏览器错误！');
		}
	});*/
	
	$.eAjax({
		url : GLOBAL.WEBROOT + "/reportitem/saleAnalyze",
		success : function(returnInfo) {
			$("#saleAnalyze").attr("href",returnInfo.imageUrl);
		},
		error : function() {
			eDialog.error('浏览器错误！');
		}
	});

}
);



