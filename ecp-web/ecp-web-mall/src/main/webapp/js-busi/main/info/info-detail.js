$(function() {

	//根据静态文件路径，填充富文本内容。
	var staticUrl = $("#staticUrl").val();
	if(staticUrl !=""){
		var editorText = $("#editorText"); 
		var url = staticUrl;
		$.ajax({
			url : url,
			async : true,
			type : "get",
			dataType : 'jsonp',
			jsonp :'jsonpCallback',//注意此处写死jsonCallback
			success: function (data) {
				editorText.empty();
            	editorText.html(data.result);
		    }
		});
	}
	
});