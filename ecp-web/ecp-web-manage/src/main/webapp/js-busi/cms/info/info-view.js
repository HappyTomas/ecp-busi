//新增修改页面信息JS处理
$(function(){

	//根据静态文件路径，填充富文本内容。
	var staticUrl = $("#staticUrl").val();
	if(staticUrl !=""){
		var editorText = $("#editorText"); 
		var url = staticUrl;
		//var url = "http://192.168.1.102:8080/imageServer/static/html/55f68185cc964eefd42d1429";
		$.ajax({
			url : url,
			async : true,
			type : "get",
			dataType : 'jsonp',
			jsonp :'jsonpCallback',//注意此处写死jsonCallback
			success: function (data) {
				editorText.empty();
            	editorText.html(data.result);
            	KindEditor.html(editorText,data.result);
				KindEditor.sync(editorText);
		    }
		});
	}
	
	//返回按钮点击触发事件
	$('#btnReturn').click(function(){
		//跳转到页面信息列表查询页面
		//window.location.href = $webroot+'info/infogrid';
		var searchParams = $("#searchParams").val();
		SearchObj.openPage({
			"url": $webroot+'info/infogrid',
			"params" :{"searchParams":(searchParams?searchParams:"")},
			"method" :"post"
		});
	});
});