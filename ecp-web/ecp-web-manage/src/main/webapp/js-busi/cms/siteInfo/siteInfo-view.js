//新增修改页面信息JS处理
$(function(){
	
	//根据静态文件路径，填充富文本内容。
	var staticUrl = $("#staticUrl").val();
	if(staticUrl !=""){
		var editorText = $("#editorText"); 
		var url = staticUrl;
		//alert(url);
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
		//window.location.href = $webroot+'siteinfo/grid';
		//window.history.go(-1);
		var searchParams = $("#searchParams").val();
		SearchObj.openPage({
			"url": $webroot+'siteinfo/grid',
			"params" :{"searchParams":(searchParams?searchParams:"")},
			"method" :"post"
		});
	});
});